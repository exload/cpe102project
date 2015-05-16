package com.ooqle.game;
/*
* @author Kenny Williams
*/

import processing.core.PApplet;
import processing.core.PImage;

import java.io.File;
import java.util.HashMap;

public class Game extends PApplet
{
    private static HashMap<String, PImage> imgs;

    public HashMap<String, PImage> loadImages(File dir)
    {
        HashMap<String, PImage> out = new HashMap<>();
        final String[] okFileExtensions = new String[]{"jpg", "png", "gif"};
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

    public void setup()
    {
        size(640, 480);
        imgs = loadImages(new File("res"));
    }

    public void keyPressed()
    {

    }

    public void draw()
    {

    }

    /*
    Static shiz
     */

    public static PImage getImage(String path)
    {
        return imgs.get(path);
    }

    public static void main(String[] args)
    {
        PApplet.main("Metro Monsters");
    }
}
