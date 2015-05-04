package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;

public class Actor extends Entity
{
    private int resourceLimit;
    private int resourceCount;

    public Actor(String name, String type, Point position)
    {
        super(name, type, position);
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

    public int getResourceLimit()
    {
        return resourceLimit;
    }
}
