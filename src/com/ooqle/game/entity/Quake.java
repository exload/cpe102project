package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;
import processing.core.PImage;

import java.util.List;

public class Quake extends AnimatedActor
{

    public Quake(String name, Point position, List<PImage> imgs, int rate, int animationRate)
    {
        super(name, "unknown", position, imgs, rate, animationRate);
    }

    public String entityString()
    {
        return "unknown";
    }
}
