package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Game;
import com.ooqle.game.Point;
import com.ooqle.game.World;
import com.ooqle.game.util.GameUtils;
import com.ooqle.game.util.Tuple;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class MinerFull extends Miner
{
    private boolean knighthood;

    public MinerFull(String name, Point position, List<PImage> imgs, int rate, int animationRate, int resourceLimit, UUID uuid)
    {
        super(name, "unknown", position, imgs, rate, animationRate, resourceLimit, uuid);
    }

    public MinerFull(String name, Point position, List<PImage> imgs, int rate, int animationRate, int resourceLimit)
    {
        this(name, position, imgs, rate, animationRate, resourceLimit, UUID.randomUUID());
    }

    @Override
    MovableActor transform(World world)
    {
        if(knighthood)
        {
            return new Soldier("soldier", this.getPosition(), GameUtils.getSpriteImages(Game.getImage("images/characters/soldier/soldier_move_left.png"), 6), 1000, 100);
        }
        return new MinerNotFull(this.getName(), this.getPosition(), this.getImages(), this.getRate(), this.getAnimationRate(), this.getResourceCount(), this.getUUID());
    }

    @Override
    Class nearestTypeForSearching()
    {
        return Base.class;
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
            if(world.getWorldObjectAt(orePt).getClass().equals(Blacksmith.class))
            {
                this.setResourceCount(obj.getResourceCount() + this.getResourceCount());
                this.setResourceCount(0);
            }
            else if(world.getWorldObjectAt(orePt).getClass().equals(Barracks.class))
            {
                this.bestowKnighthood(world);
            }
            return new Tuple<>(new ArrayList<>(), true);
        }else
        {
            Point newPt = this.nextPosition(world, orePt);
            return new Tuple<>(world.moveWorldObject(this, newPt), false);
        }
    }

    private void bestowKnighthood(World world)
    {
        knighthood = true;
        //world.removeEntityAt(this.getPosition());
        //Soldier soldier = new Soldier("soldier", new Point(1, 1), GameUtils.getSpriteImages(Game.getImage("images/characters/soldier/soldier_move_left.png"), 6), 1000, 100);
        //world.addWorldObject(soldier);
        //soldier.schedule(world, 0);
    }

    public Class getGoalType()
    {
        return Base.class;
    }

    public String entityString()
   {
       return "unknown";
   }
}
