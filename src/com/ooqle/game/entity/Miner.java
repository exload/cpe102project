package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;
import processing.core.PImage;

import java.util.List;

public class Miner extends AnimatedActor
{
    private int resourceLimit;

    public Miner(String name, String type, Point position, List<PImage> imgs, int rate, int animationRate, int resourceLimit)
    {
        super(name, type, position, imgs, rate, animationRate);
        this.resourceLimit = resourceLimit;
    }

    public int getResourceLimit()
    {
        return resourceLimit;
    }
}
