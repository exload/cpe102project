package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;

public class Ore extends Actor
{

    public Ore(String name, Point position)
    {
        super(name, "ore", position);
    }

    public String entityString()
    {
        return super.entityString() + " " + this.getRate();
    }
}
