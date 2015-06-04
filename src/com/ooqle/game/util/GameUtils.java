package com.ooqle.game.util;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;
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

    public static int euclideanDistanceSqaure(Point p1, Point p2)
    {
        return (int) (Math.pow((p1.getX() - p2.getX()), 2) + Math.pow((p1.getY() - p2.getY()), 2));
    }

    public static int manhattanDistance(Point p1, Point p2)
    {
        return (Math.abs(p1.getX() - p2.getX()) + Math.abs(p1.getY() - p2.getY()));
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
        for(int i = 0; i < numberOfImages; i++)
        {
            sprites.add(sprite.get(i * W, 0, W, H));
        }
        return sprites;
    }
}
