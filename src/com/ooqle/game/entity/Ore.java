package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;
import processing.core.PImage;

import java.util.List;

public class Ore extends Actor
{

    public Ore(String name, Point position, List<PImage> imgs, int rate)
    {
        super(name, "ore", position, imgs, rate);
    }

    public String entityString()
    {
        return super.entityString() + " " + this.getRate();
    }
}
