package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;
import processing.core.PImage;

import java.util.List;

public class MinerNotFull extends Miner
{

    public MinerNotFull(String name, Point position, List<PImage> imgs, int rate, int animationRate, int resourceLimit)
    {
        super(name, "miner", position, imgs, rate, animationRate, resourceLimit);
    }

    public String entityString()
    {
        String s = " ";
        //TODO: This ordering is bad. Possibly refactor to parent
        return super.entityString() + s + this.getResourceLimit() + s + this.getRate() + s + this.getAnimationRate();
    }
}
