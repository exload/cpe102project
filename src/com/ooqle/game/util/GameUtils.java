package com.ooqle.game.util;
/*
* @author Kenny Williams
*/

import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

public class GameUtils
{
    public static int sign(int x)
    {
        if(x < 0)
        {
            return -1;
        }else if(x > 0)
        {
            return 1;
        }
        return 0;
    }

    /**
     * Loads a sprite into a List of PImages
     * @param sprite The PImage of the sprite to load into a list
     * @param numberOfImages The number of images that are in the sprite
     * @return A list of PImages
     */
    public static List<PImage> getSpriteImages(PImage sprite, int numberOfImages)
    {
        List<PImage> sprites = new ArrayList<>();
        int W = sprite.width / numberOfImages;
        int H = sprite.height;
        for(int i = 0; i < numberOfImages + 1; i++)
        {
            sprites.add(sprite.get(i * W, 0, W, H));
        }
        return sprites;
    }
}
