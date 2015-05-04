package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;

public class OreBlob extends MovableActor
{

    public OreBlob(String name, Point position, int rate)
    {
        super(name, "unknown", position, rate);
    }

    public void toVein()
    {
        //TODO: Implement me
    }

    public void nextPosition()
    {
        //TODO: Implement me
    }

    public String entityString()
    {
        return "unknown";
    }
}
