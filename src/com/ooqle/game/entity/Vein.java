package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;

public class Vein extends Actor
{
    private int resourceDistance;

    public Vein(String name, Point position, int rate, int resourceDistance)
    {
        super(name, "vein", position, rate);
        this.resourceDistance = resourceDistance;
    }

    public Vein(String name, Point position, int rate)
    {
        this(name, position, rate, 1);
    }

    public int getResourceDistance()
    {
        return resourceDistance;
    }

    public String entityString()
    {
        String s = " ";
        return super.entityString() + s + this.getRate() + s + this.getResourceDistance();
    }
}
