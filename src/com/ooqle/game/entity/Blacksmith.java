package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;

public class Blacksmith extends Actor
{
    private int resourceDistance;

    public Blacksmith(String name, Point position, int resourceDistance)
    {
        super(name, "blacksmith", position);
        this.resourceDistance = resourceDistance;
    }

    public Blacksmith(String name, Point position)
    {
        this(name, position, 1);
    }

    public int getResourceDistance()
    {
        return this.resourceDistance;
    }

    public String entityString()
    {
        String s = " ";
        return super.entityString() + s + this.getResourceLimit() + s + this.getRate() + s + this.getResourceDistance();
    }


}
