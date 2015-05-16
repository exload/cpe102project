package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.ActionManager;
import com.ooqle.game.Point;
import com.ooqle.game.World;
import com.ooqle.game.util.Action;
import com.ooqle.game.util.GameUtils;
import com.ooqle.game.util.Tuple;
import processing.core.PImage;

import java.util.Collections;
import java.util.List;

public class OreBlob extends MovableActor
{

    public OreBlob(String name, Point position, List<PImage> imgs, int rate, int animationRate)
    {
        super(name, "unknown", position, imgs, rate, animationRate);
    }

    public Tuple<List<Point>, Boolean> toVein(World world, Vein vein)
    {
        Point entityPt = this.getPosition().clone();
        if (vein == null)
        {
            return new Tuple<>(Collections.singletonList(entityPt), false);
        }
        Point veinPt = vein.getPosition().clone();
        if(entityPt.adjacent(veinPt))
        {
            vein.removeEntity(world);
            return new Tuple<>(Collections.singletonList(veinPt), true);
        }else
        {
            Point newPt = this.nextPosition(world, veinPt);
            WorldObject oldEntity = world.getWorldObjectAt(newPt);
            if(oldEntity instanceof Ore)
            {
                oldEntity.removeEntity(world);
            }
            return new Tuple<>(world.moveWorldObject(this, newPt), false);
        }
    }

    public Point nextPosition(World world, Point destPt)
    {
        int horiz = GameUtils.sign(destPt.getX() - this.getPosition().getX());
        Point newPt = new Point(this.getPosition().getX() + horiz, this.getPosition().getY());

        if(horiz == 0 || (world.isOccupied(newPt) && !(world.getWorldObjectAt(newPt) instanceof Ore)))
        {
            int vert = GameUtils.sign(destPt.getY() - this.getPosition().getY());
            newPt = new Point(this.getPosition().getX(), this.getPosition().getY() +  vert);
            if(vert == 0 || !(world.getWorldObjectAt(newPt) instanceof Ore))
            {
                newPt = new Point(this.getPosition().getX(), this.getPosition().getY());
            }
        }
        return newPt;
    }

    public Action createAction(World world)
    {
        Action a = (long currentTicks) ->
        {
            Point pt = this.getPosition();
            Vein vein = (Vein) world.findNearestOfType(pt, Vein.class);
            Tuple<List<Point>, Boolean> tup = this.toVein(world, vein);

            long nextTime = currentTicks + this.getRate();

            if(tup.getValue())
            {
                Quake quake = ActionManager.createQuake(world, tup.getKey().get(0), currentTicks);
                world.addWorldObject(quake);
                nextTime = currentTicks + this.getRate() * 2;
            }

            this.scheduleAction(world, this.createAction(world), nextTime);

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

    public String entityString()
    {
        return "unknown";
    }
}
