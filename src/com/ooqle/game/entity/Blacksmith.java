package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;
import processing.core.PImage;

import java.util.List;

public class Blacksmith extends Actor
{
    private int resourceCount;

    public Blacksmith(String name, Point position, int rate, List<PImage> imgs)
    {
        super(name, "blacksmith", position, imgs, rate);
        this.resourceCount = 0;
    }

    @Override
    public int getResourceCount()
    {
        return resourceCount;
    }

    @Override
    public void setResourceCount(int resourceCount)
    {
        this.resourceCount = resourceCount;
    }

    public String entityString()
    {
        String s = " ";
        return super.entityString() + s + this.getRate() + s + this.getResourceDistance();
    }
}
