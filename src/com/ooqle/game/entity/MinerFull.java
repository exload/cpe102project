package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;
import com.ooqle.game.World;
import processing.core.PImage;

import java.util.List;

public class MinerFull extends Miner
{

    public MinerFull(String name, Point position, List<PImage> imgs, int rate, int animationRate, int resourceLimit)
    {
        super(name, "unknown", position, imgs, rate, animationRate, resourceLimit);
    }

    @Override
    Miner transform(World world)
    {
        return new MinerNotFull(this.getName(), this.getPosition(), this.getImages(), this.getRate(), this.getAnimationRate(), this.getResourceCount());
    }

    @Override
    Class nearestTypeForSearching()
    {
        return Blacksmith.class;
    }

   public String entityString()
   {
       return "unknown";
   }
}
