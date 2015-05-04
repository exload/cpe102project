package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;

public class Quake extends AnimatedActor
{

    public Quake(String name, Point position, int rate)
    {
        super(name, "unknown", position, rate);
    }

    public String entityString()
    {
        return "unknown";
    }
}
