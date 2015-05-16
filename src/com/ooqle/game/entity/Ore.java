package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.ActionManager;
import com.ooqle.game.Point;
import com.ooqle.game.World;
import com.ooqle.game.util.Action;
import com.ooqle.game.util.WorldObjectSettings;
import processing.core.PImage;

import java.util.Collections;
import java.util.List;

public class Ore extends Actor
{

    public Ore(String name, Point position, List<PImage> imgs, int rate)
    {
        super(name, "ore", position, imgs, rate);
    }

    public Action createTransformAction(World world)
    {
        Action a = (long currentTicks) ->
        {
            OreBlob blob = ActionManager.createBlob(world,
                    this.getName() + " -- blob",
                    this.getPosition(),
                    (int) (this.getRate() / WorldObjectSettings.BLOB_RATE_SCALE),
                    currentTicks);
            this.removeEntity(world);
            world.addWorldObject(blob);
            return Collections.singletonList(blob.getPosition());
        };
        this.removePendingAction(a);
        return a;
    }

    public void schedule(World world, long ticks)
    {
        this.scheduleAction(world,
                this.createTransformAction(world),
                ticks + this.getRate());
    }

    public String entityString()
    {
        return super.entityString() + " " + this.getRate();
    }
}
