package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;

public class AnimatedActor extends Actor
{
    private int animationRate;

    public AnimatedActor(String name, String type, Point position)
    {
        super(name, type, position);
    }


    public int getAnimationRate()
    {
        return animationRate;
    }
}
