package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;
import com.ooqle.game.World;
import processing.core.PImage;

import java.util.List;

public class Blacksmith extends Base
{
    private int resourceCount;

    public Blacksmith(String name, Point position, int rate, List<PImage> imgs)
    {
        super(name, "blacksmith", position, rate, imgs);
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
        return super.entityString() + s + this.getRate() + s + this.getResourceCount();
    }

    public void armTheLand(World world)
    {
        world.removeEntityAt(this.getPosition());
        Barracks barracks = new Barracks("barracks", this.getPosition(), 100);
        world.addWorldObject(barracks);
    }
}
