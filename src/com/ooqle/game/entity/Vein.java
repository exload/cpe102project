package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;

public class Vein extends Actor
{
    private int resourceDistance;

    public Vein(String name, Point position, int resourceDistance)
    {
        super(name, "vein", position);
        this.resourceDistance = resourceDistance;
    }

    public Vein(String name, Point position)
    {
        this(name, position, 1);
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
