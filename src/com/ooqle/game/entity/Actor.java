package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;
import processing.core.PImage;

import java.util.List;

public class Actor extends WorldObject
{
    private int resourceCount;

    public Actor(String name, String type, Point position, List<PImage> imgs, int rate)
    {
        super(name, type, position, imgs, rate);
        //TODO: Set resource limit enum
        this.resourceCount = 0;
    }

    public int getResourceCount()
    {
        return resourceCount;
    }

    public void setResourceCount(int resourceCount)
    {
        this.resourceCount = resourceCount;
    }
}
