package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;
import processing.core.PImage;

import java.util.List;

public class Blacksmith extends Actor
{
    private int resourceDistance;

    public Blacksmith(String name, Point position, int rate, List<PImage> imgs, int resourceDistance)
    {
        super(name, "blacksmith", position, imgs, rate);
        this.resourceDistance = resourceDistance;
    }

    public Blacksmith(String name, Point position, int rate, List<PImage> imgs)
    {
        this(name, position, rate, imgs, 1);
    }

    public int getResourceDistance()
    {
        return this.resourceDistance;
    }

    public String entityString()
    {
        String s = " ";
        return super.entityString() + s + this.getRate() + s + this.getResourceDistance();
    }


}
