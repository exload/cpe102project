package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;
import com.ooqle.game.World;
import com.ooqle.game.util.GameUtils;
import com.ooqle.game.util.Tuple;
import processing.core.PImage;

import java.util.List;

public class OreBlob extends MovableActor
{

    public OreBlob(String name, Point position, List<PImage> imgs, int rate, int animationRate)
    {
        super(name, "unknown", position, imgs, rate, animationRate);
    }

    public Tuple toVein(World world, Vein vein)
    {
        Point entityPt = this.getPosition().clone();
        if (vein == null)
        {
            return new Tuple<>(entityPt, false);
        }
        Point veinPt = vein.getPosition().clone();
        if(entityPt.adjacent(veinPt))
        {
            vein.removeEntity(world);
            return new Tuple<>(veinPt, true);
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

    public String entityString()
    {
        return "unknown";
    }
}
