package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;

public class MinerNotFull extends Miner
{

    public MinerNotFull(String name, Point position, int rate)
    {
        super(name, "miner", position, rate);
    }

    public String entityString()
    {
        String s = " ";
        //TODO: This ordering is bad. Possibly refactor to parent
        return super.entityString() + s + this.getResourceLimit() + s + this.getRate() + s + this.getAnimationRate();
    }
}
