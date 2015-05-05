package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;

public class Quake extends AnimatedActor
{

    public Quake(String name, Point position, int rate, int animationRate)
    {
        super(name, "unknown", position, rate, animationRate);
    }

    public String entityString()
    {
        return "unknown";
    }
}
