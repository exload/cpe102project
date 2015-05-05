package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;

public class AnimatedActor extends Actor
{
    private int animationRate;

    public AnimatedActor(String name, String type, Point position, int rate, int animationRate)
    {
        super(name, type, position, rate);
        this.animationRate = animationRate;
    }


    public int getAnimationRate()
    {
        return animationRate;
    }
}
