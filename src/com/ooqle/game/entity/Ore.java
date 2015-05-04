package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;

public class Ore extends Actor
{

    public Ore(String name, Point position, int rate)
    {
        super(name, "ore", position, rate);
    }

    public String entityString()
    {
        return super.entityString() + " " + this.getRate();
    }
}
