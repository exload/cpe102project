package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;
import com.ooqle.game.World;
import processing.core.PImage;

import java.util.List;

public class MinerNotFull extends Miner
{

    public MinerNotFull(String name, Point position, List<PImage> imgs, int rate, int animationRate, int resourceLimit)
    {
        super(name, "miner", position, imgs, rate, animationRate, resourceLimit);
    }

    @Override
    Miner transform(World world)
    {
        if(this.getResourceCount() < this.getResourceLimit())
        {
            return this;
        }
        return new MinerFull(this.getName(), this.getPosition(), this.getImages(), this.getRate(), this.getAnimationRate(), this.getResourceLimit());
    }

    @Override
    Class nearestTypeForSearching()
    {
        return Ore.class;
    }

    public String entityString()
    {
        String s = " ";
        //TODO: This ordering is bad. Possibly refactor to parent
        return super.entityString() + s + this.getResourceLimit() + s + this.getRate() + s + this.getAnimationRate();
    }
}
