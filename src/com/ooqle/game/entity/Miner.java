package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;

public class Miner extends AnimatedActor
{
    private int resourceLimit;

    public Miner(String name, String type, Point position, int rate, int animationRate, int resourceLimit)
    {
        super(name, type, position, rate, animationRate);
        this.resourceLimit = resourceLimit;
    }

    public int getResourceLimit()
    {
        return resourceLimit;
    }
}
