package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;
import com.ooqle.game.World;
import com.ooqle.game.util.Action;
import com.ooqle.game.util.Tuple;
import org.json.simple.JSONObject;
import processing.core.PImage;

import java.util.List;
import java.util.UUID;

public abstract class Miner extends MovableActor implements Attackable
{
    private int resourceLimit;

    public Miner(String name, String type, Point position, List<PImage> imgs, int rate, int animationRate, int resourceLimit, UUID uuid)
    {
        super(name, type, position, imgs, rate, animationRate, 3);
        this.resourceLimit = resourceLimit;
        this.setUUID(uuid);
    }

    public Miner(String name, String type, Point position, List<PImage> imgs, int rate, int animationRate, int resourceLimit)
    {
        this(name, type, position, imgs, rate, animationRate, resourceLimit, UUID.randomUUID());
    }

    abstract MovableActor transform(World world);
    abstract Class nearestTypeForSearching();
    abstract Tuple<List<Point>, Boolean> applyAction(World world, Actor obj);
    public abstract Class getGoalType();

    public int getResourceLimit()
    {
        return resourceLimit;
    }

    public Tuple<List<Point>, Boolean> getNearest(World world, Class type)
    {
        Point pos = this.getPosition();
        Actor nearestOfType = (Actor) world.findNearestOfType(pos, type);
        return this.applyAction(world, nearestOfType);
    }

    private MovableActor tryTransform(World world)
    {
        MovableActor newObj = this.transform(world);
        if(this != newObj)
        {
            this.clearPendingActions(world);
            world.removeEntityAt(this.getPosition());
            world.addWorldObject(newObj);
            newObj.scheduleAnimation(world);
        }
        return newObj;
    }

    public Action createAction(World world)
    {
        Action a = (long currentTicks) ->
        {
            Tuple<List<Point>, Boolean> tup = this.getNearest(world, this.nearestTypeForSearching());
            boolean found = tup.getValue();
            MovableActor newEntity = this;

            if(found)
            {
                newEntity = this.tryTransform(world);
            }

            newEntity.scheduleAction(world, newEntity.createAction(world), currentTicks + this.getRate());
            return tup.getKey();
        };
        this.removePendingAction(a);
        return a;
    }

    public void schedule(World world, long ticks)
    {
        this.scheduleAction(world, this.createAction(world), ticks + this.getRate());
        this.scheduleAnimation(world);
    }

    public JSONObject entityJSON()
    {
        JSONObject out = super.entityJSON();
        out.put("resourceLimit", this.getResourceLimit());
        return out;
    }

    @Override
    public void getTarget(World world)
    {

    }

}
