package com.ooqle.game;
/*
* @author Kenny Williams
*/

import com.ooqle.game.entity.*;
import com.ooqle.game.ui.Button;
import com.ooqle.game.ui.ToggleButton;
import com.ooqle.game.ui.UIManager;
import com.ooqle.game.util.SaveLoad;
import com.ooqle.game.util.WorldObjectSettings;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;
import processing.core.PImage;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Game extends PApplet
{
    private Background thebackground;

    private World world;

    private boolean worldLive;
    private boolean showMenu;

    private int xShift;
    private int yShift;

    private int xSize;
    private int ySize;

    private long startTime;
    private long worldStartTime;

    private MovableActor hightlightedActor;
    private PImage redSquare;
    private PImage blackSquare;
    private PImage greenSquare;
    private PImage yellowSquare;
    private PImage splashImage;
    private PImage heart;
    private AudioPlayer happyMusicPlayer;
    private AudioPlayer battleMusicPlayer;
    private Minim minim;
    private boolean battleMode;

    private static HashMap<String, PImage> imgs;

    public HashMap<String, PImage> loadImages(File dir)
    {
        HashMap<String, PImage> out = new HashMap<>();
        final String[] okFileExtensions = new String[]{"jpg", "png", "gif", "bmp"};
        for (File f : dir.listFiles((File file) -> {
            for (String extension : okFileExtensions)
            {
                if (file.getName().toLowerCase().endsWith(extension) || file.isDirectory())
                {
                    return true;
                }
            }
            return false;
        }))
        {
            if (f.isDirectory())
            {
                out.putAll(loadImages(f));
            } else
            {
                String unixPath = f.getPath().replace("\\", "/");
                String name = unixPath.substring(4, unixPath.length());
                out.put(name, loadImage(f.getPath()));
            }
        }
        return out;
    }

    public void drawImage(PImage img, float a, float b)
    {
        super.image(img, a * 32, b * 32);
    }

    //setup world

    public void setup()
    {
        worldLive = false;
        frame.removeNotify();
        frame.setUndecorated(true);
        frame.addNotify();

        xShift = 0;
        yShift = 0;

        xSize = WorldObjectSettings.PIXELWIDTH;
        ySize = WorldObjectSettings.PIXELHEIGHT;

        size(xSize, ySize);
        frame.setTitle("Metro Monsters");
        imgs = loadImages(new File("res"));

        world = SaveLoad.load();

        startTime = System.currentTimeMillis();

        redSquare = getImage("images/redSquare.png");
        blackSquare = getImage("images/blackSquare.png");
        greenSquare = getImage("images/greenSquare.png");
        yellowSquare = getImage("images/yellowSquare.png");
        splashImage = getImage("images/splashscreen2.png");
        heart = getImage("images/heart.png");

        //cursor(getImage("images/cursor/cursor_default.png"));

        setupMenu();

        minim = new Minim(this);
        happyMusicPlayer = minim.loadFile("res/audio/happy_music.mp3");
        battleMusicPlayer = minim.loadFile("res/audio/battle_music.mp3");
        happyMusicPlayer.loop();
    }

    public void keyPressed()
    {
        if (worldLive)
        {
            if (key == 27)
            {
                key = 0;
            }
            switch (key)
            {
                case 'W':
                case 'w':
                    if (yShift > 0)
                    {
                        yShift -= 1;
                    }
                    break;

                case 'D':
                case 'd':
                    if (xShift < xSize / WorldObjectSettings.TILESIZE)
                    {
                        xShift += 1;
                    }
                    break;

                case 'S':
                case 's':
                    if (yShift < ySize / WorldObjectSettings.TILESIZE)
                    {
                        yShift += 1;
                    }
                    break;

                case 'A':
                case 'a':
                    if (xShift > 0)
                    {
                        xShift -= 1;
                    }
                    break;

                case 'q':
                    WorldObject ob = world.getWorldObjectAt(new Point(mouseX / 32, mouseY / 32));
                    if (ob != null)
                    {
                        System.out.println(ob.getClass());
                    }
                    break;

                case 'z':
                    Barracks barracks = new Barracks("barracks", new Point(10, 2), 1000);
                    world.addWorldObject(barracks);
                    break;

                case 0:
                    showMenu = !showMenu;
                    break;
            }
        }
    }

    private void enterBattleMode(Point cavepoint)
    {
        if (!(battleMode))
        {
            Lair lair = new Lair("lair", cavepoint, WorldObjectSettings.GOBLINSPAWNRATE);
            world.addWorldObject(lair);
            lair.schedule(world, 0);

            List<WorldObject> allWorldObjects = world.getWorldObjects();
            ArrayList<MinerNotFull> minerlist = new ArrayList<MinerNotFull>();
            ArrayList<Blacksmith> smithlist = new ArrayList<Blacksmith>();

            for (WorldObject entity : allWorldObjects)
            {
                if (entity.getClass().equals(MinerNotFull.class))
                {
                    minerlist.add(0, (MinerNotFull) entity);
                }

                else if (entity.getClass().equals(Blacksmith.class))
                {
                    smithlist.add(0, (Blacksmith) entity);
                }
            }

            callMinersToBase(minerlist);
            prepForBattle(smithlist);
            battleMode = true;
            happyMusicPlayer.pause();
            battleMusicPlayer.loop();
        }
    }

    private void prepForBattle(ArrayList<Blacksmith> smithlist)
    {
        for(Blacksmith smith : smithlist)
        {
            smith.armTheLand(world);
        }
    }

    private void callMinersToBase(ArrayList<MinerNotFull> minerlist)
    {
        for (MinerNotFull oldminer : minerlist)
        {
            oldminer.transformToFull(world);
        }
    }

    private AudioPlayer getAudioPlayer()
    {
        if(battleMode)
        {
            return battleMusicPlayer;
        }
        return happyMusicPlayer;
    }

    private void drawHearts()
    {
        for(WorldObject entity : world.getWorldObjects())
        {
            if(entity instanceof MovableActor)
            {
                int health = ((MovableActor) entity).getHealth();

                if(health > 0)
                {
                    image(heart, (entity.getPosition().getX() - xShift) * 32, (entity.getPosition().getY() - yShift) * 32 - 2);
                }

                if(health > 1)
                {
                    image(heart, (entity.getPosition().getX() - xShift) * 32 + 6, (entity.getPosition().getY() - yShift) * 32 - 2);
                }

                if(health > 2)
                {
                    image(heart, (entity.getPosition().getX() - xShift) * 32 + 12, (entity.getPosition().getY() - yShift) * 32 - 2);
                }

                if(health > 3)
                {
                    image(heart, (entity.getPosition().getX() - xShift) * 32 + 18, (entity.getPosition().getY() - yShift) * 32 - 2);
                }

                if(health > 4)
                {
                    image(heart, (entity.getPosition().getX() - xShift) * 32 + 24, (entity.getPosition().getY() - yShift) * 32 - 2);
                }
            }
        }
    }

    public void mouse()
    {
        int xPos = mouseX / 32;
        int yPos = mouseY / 32;

        drawImage(greenSquare, xPos, yPos);

        if (mousePressed)
        {
            enterBattleMode(new Point(xPos + xShift, yPos + yShift));
        }

        text((xPos + xShift) + ", " + (yPos + yShift) + ", " + (System.currentTimeMillis() - worldStartTime), 5, 15);

    }

    public void mousePressed()
    {
        if (showMenu)
        {
            for (Button btn : UIManager.getButtons())
            {
                if (UIManager.withinBounds(mouseX, mouseY, btn))
                {
                    btn.onClick();
                }
            }
        }
    }

    /*
    Drawing stuff
     */

    private void drawBG()
    {
        for (int y = 0; y < WorldObjectSettings.GAMEHEIGHT; y++)
        {
            for (int x = 0; x < WorldObjectSettings.GAMEWIDTH; x++)
            {
                drawImage(world.getBackgroundAt(new Point(x, y)).getImage(), x - xShift, y - yShift);
            }
        }
    }

    private void drawWorldObjects()
    {
        for (WorldObject wo : world.getWorldObjects())
        {
            drawImage(wo.getImage(), wo.getPosition().getX() - xShift, wo.getPosition().getY() - yShift);
        }
    }

    private void setupMenu()
    {
        Button closeGameBtn = UIManager.createButton(100, 100, getImage("images/button/dummy_button.png"), getImage("images/button/dummy_button_hover.png"));
        closeGameBtn.addClickHandler(() ->
        {
            happyMusicPlayer.pause();
            happyMusicPlayer.close();
            exit();
        });

        PImage soundIconOn = getImage("images/menu/sound_icon.png");
        ToggleButton soundBtn = UIManager.createToggleButton(500, 100, soundIconOn, soundIconOn, getImage("images/menu/sound_icon_off.png"));
        soundBtn.addClickHandler(() ->
        {
            if (soundBtn.isSelected())
            {
                getAudioPlayer().play();
            } else
            {
                getAudioPlayer().pause();
            }
        });
    }

    private void drawMenu()
    {
        UIManager.drawUI(this);
    }

    private long nextTime = 0;
    private boolean paused = false;

    public void draw()
    {
        if (System.currentTimeMillis() - startTime < 3000)
        {
            drawImage(splashImage, 0, 0);
            worldStartTime = System.currentTimeMillis();
        } else
        {
            worldLive = true;
            long currTime = System.currentTimeMillis();

            if (!paused)
            {
                if (currTime >= nextTime)
                {
                    nextTime = currTime + 100;
                    // manually adding the 8 seconds to reduce debugging time
                    world.updateOnTime(currTime - worldStartTime + 12000);
                }
            }

            drawBG();
            drawWorldObjects();
            drawHearts();

            UIManager.updateMousePosition(mouseX, mouseY);
            if (this.showMenu)
            {
                paused = true;
                tint(200);
                filter(BLUR, 2);
                drawMenu();
            } else
            {
                mouse();
                tint(255);
                paused = false;
            }
        }
    }

    /*
    Static shiz
     */

    /**
     * Loads an image given a path. The path root is in the /res directory. To load an image in the directory /res/images/
     * call example.png call getImage("images/example.png")
     *
     * @param path The path to the image from the /res directory
     * @return Returns a path to the image
     */
    public static PImage getImage(String path)
    {
        return imgs.get(path);
    }

    /**
     * Returns a list of PImages following the below format:
     * getImages("images/blob*.bmp", 12) will load all images in the images blob1.bmp to blob12.bmp
     *
     * @param path Path to images. Use one * for the variable.
     * @param end  The final image in the list
     * @return List of PImages 1 through end
     */
    public static List<PImage> getImages(String path, int end)
    {
        List<PImage> out = new ArrayList<>();
        for (int i = 1; i < end + 1; i++)
        {
            String newPath = path.replace("*", Integer.toString(i));
            out.add(getImage(newPath));
        }
        return out;
    }

    public static void main(String[] args)
    {
        PApplet.main("com.ooqle.game.Game");
    }
}
