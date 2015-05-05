package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;
import com.ooqle.game.WorldModel;
import com.ooqle.game.util.GameUtils;
import com.ooqle.game.util.Tuple;

public class OreBlob extends MovableActor
{

    public OreBlob(String name, Point position, int rate)
    {
        super(name, "unknown", position, rate);
    }

    public Tuple toVein(WorldModel world, Vein vein)
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
            WorldObject oldEntity = world.getTileOccpant(newPt);
            if(oldEntity instanceof Ore)
            {
                ((Ore) oldEntity).removeEntity(world);
            }
            return new Tuple<>(world.moveEntity(this, newPt), false);
        }
    }

    public Point nextPosition(WorldModel world, Point destPt)
    {
        int horiz = GameUtils.sign(destPt.getX() - this.getPosition().getX());
        Point newPt = new Point(this.getPosition().getX() + horiz, this.getPosition().getY());

        if(horiz == 0 || (world.isOccupied(newPt) && !(world.getTileOccpant(newPt) instanceof Ore)))
        {
            int vert = GameUtils.sign(destPt.getY() - this.getPosition().getY());
            newPt = new Point(this.getPosition().getX(), this.getPosition().getY() +  vert);
            if(vert == 0 || !(world.getTileOccpant(newPt) instanceof Ore))
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
