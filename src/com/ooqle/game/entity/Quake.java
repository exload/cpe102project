package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;
import com.ooqle.game.World;
import com.ooqle.game.util.Action;
import com.ooqle.game.util.WorldObjectSettings;
import processing.core.PImage;

import java.util.Collections;
import java.util.List;

public class Quake extends AnimatedActor
{

    public Quake(String name, Point position, List<PImage> imgs, int rate, int animationRate)
    {
        super(name, "unknown", position, imgs, rate, animationRate);
    }

    public Action createEntityDeathAction(World world)
    {
        Action a = (long currentTicks) ->
        {
            this.removeEntity(world);
            return Collections.singletonList(this.getPosition());
        };
        this.removePendingAction(a);
        return a;
    }

    public void schedule(World world, long ticks)
    {
        this.scheduleAnimation(world, WorldObjectSettings.QUAKE_STEPS);
        this.scheduleAction(world, this.createEntityDeathAction(world), ticks + WorldObjectSettings.QUAKE_DURATION);
    }

    public String entityString()
    {
        return "unknown";
    }
}
