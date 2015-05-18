package com.ooqle.game;
/*
* @author Kenny Williams
*/

import com.ooqle.game.entity.Background;
import com.ooqle.game.entity.WorldObject;
import com.ooqle.game.util.SaveLoad;
import com.ooqle.game.util.WorldObjectSettings;
import processing.core.PApplet;
import processing.core.PImage;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Game extends PApplet
{
    private  Background thebackground;

    private World theworld;

    private int xShift;
    private int yShift;

    private int xsize;
    private int ysize;

    private long startTime;

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
            }else
            {
                String unixPath = f.getPath().replace("\\", "/");
                String name = unixPath.split("res/")[1];
                out.put(name, loadImage(f.getPath()));
            }
        }
        return out;
    }

    public void image(PImage img, float a, float b)
    {
        super.image(img, a * 32, b * 32);
    }

    //setup world

    public void setup()
    {
        xShift = 0;
        yShift = 0;

        xsize = WorldObjectSettings.PIXELWIDTH;
        ysize = WorldObjectSettings.PIXELHEIGHT;

        size(xsize, ysize);
        frame.setTitle("Metro Monsters");
        imgs = loadImages(new File("res"));

        theworld = SaveLoad.load();

        startTime = System.currentTimeMillis();
    }

    public void keyPressed()
    {
        switch(key)
        {
            case 'W':

            case 'w':
                if(yShift > 0)
                {
                    yShift -= 1;
                }
                break;
            case 'D':

            case 'd':
                if(xShift < xsize/WorldObjectSettings.TILESIZE)
                {
                    xShift += 1;
                }
                break;
            case 'S':

            case 's':
                if(yShift < ysize/WorldObjectSettings.TILESIZE)
                {
                    yShift += 1;
                }
                break;
            case 'A':

            case 'a':
                if(xShift > 0)
                {
                    xShift -= 1;
                }
                break;
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
                image(theworld.getBackgroundAt(new Point(x, y)).getImage(), x - xShift, y - yShift);
            }
        }
    }

    private void drawWorldObjects()
    {
        for(WorldObject wo : theworld.getWorldObjects())
        {
            image(wo.getImage(), wo.getPosition().getX() - xShift, wo.getPosition().getY() - yShift);
        }
    }

    private long nextTime = 0;

    public void draw()
    {
        long currTime = System.currentTimeMillis();
        if(currTime >= nextTime)
        {
            nextTime = currTime + 100;
            theworld.updateOnTime(currTime - startTime);
        }

        drawBG();
        drawWorldObjects();
    }

    /*
    Static shiz
     */

    /**
     * Loads an image given a path. The path root is in the /res directory. To load an image in the directory /res/images/
     * call example.png call getImage("images/example.png")
     * @param path The path to the image from the /res directory
     * @return Returns a path to the image
     */
    public static PImage getImage(String path)
    {
        return imgs.get(path);
    }

    /**
     * Returns a list of PImages following the below format:
     *      getImages("images/blob*.bmp", 12) will load all images in the images blob1.bmp to blob12.bmp
     * @param path Path to images. Use one * for the variable.
     * @param end The final image in the list
     * @return List of PImages 1 through end
     */
    public static List<PImage> getImages(String path, int end)
    {
        List<PImage> out = new ArrayList<>();
        for(int i = 1; i < end + 1; i++)
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
