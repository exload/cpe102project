package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;

public class Quake extends AnimatedActor
{

    public Quake(String name, Point position)
    {
        super(name, "unknown", position);
    }

    public String entityString()
    {
        return "unknown";
    }
}
