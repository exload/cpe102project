package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;
import com.ooqle.game.World;
import com.ooqle.game.util.Tuple;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.Collections;
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

    @Override
    Tuple<List<Point>, Boolean> applyAction(World world, Actor obj)
    {
        Point pos = this.getPosition();
        if(obj == null)
        {
            return new Tuple<>(Collections.singletonList(pos), false);
        }
        Point orePt = obj.getPosition();
        if(pos.adjacent(orePt))
        {
            this.setResourceCount(obj.getResourceCount() + this.getResourceCount());
            this.setResourceCount(0);
            return new Tuple<>(new ArrayList<>(), true);
        }else
        {
            Point newPt = this.nextPosition(world, orePt);
            return new Tuple<>(world.moveWorldObject(this, newPt), false);
        }
    }

    public String entityString()
   {
       return "unknown";
   }
}
