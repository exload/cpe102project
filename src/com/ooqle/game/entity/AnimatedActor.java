package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;
import processing.core.PImage;

import java.util.List;

public class AnimatedActor extends Actor
{
    private int animationRate;

    public AnimatedActor(String name, String type, Point position, List<PImage> imgs, int rate, int animationRate)
    {
        super(name, type, position, imgs, rate);
        this.animationRate = animationRate;
    }


    public int getAnimationRate()
    {
        return animationRate;
    }
}
