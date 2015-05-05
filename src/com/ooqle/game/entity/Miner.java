package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;

public class Miner extends AnimatedActor
{
    private int resourceLimit;

    public Miner(String name, String type, Point position, int rate, int resourceLimit)
    {
        super(name, type, position, rate);
        this.resourceLimit = resourceLimit;
    }

    public int getResourceLimit()
    {
        return resourceLimit;
    }
}
