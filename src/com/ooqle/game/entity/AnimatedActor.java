package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;

public class AnimatedActor extends Actor
{
    private int animationRate;

    public AnimatedActor(String name, String type, Point position, int rate)
    {
        super(name, type, position, rate);
    }


    public int getAnimationRate()
    {
        return animationRate;
    }
}
