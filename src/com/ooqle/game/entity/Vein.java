package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;
import com.ooqle.game.WorldModel;

public class Vein extends Actor
{
    private int resourceDistance;

    public Vein(String name, Point position, int rate, int resourceDistance)
    {
        super(name, "vein", position, rate);
        this.resourceDistance = resourceDistance;
    }

    public Vein(String name, Point position, int rate)
    {
        this(name, position, rate, 1);
    }

    public int getResourceDistance()
    {
        return resourceDistance;
    }

    public Point findOpenAround(WorldModel world)
    {
        for(int dy = (-1 * this.getResourceDistance()); dy < (this.getResourceDistance() + 1); dy++)
        {
            for(int dx = (-1 * this.getResourceDistance()); dx < (this.getResourceDistance() + 1); dx++)
            {
                Point newPt = new Point(this.getPosition().getX() + dx, this.getPosition().getY() + dy);

                if(world.withinBounds(newPt) && !world.isOccupied(newPt))
                {
                    return newPt;
                }
            }
        }
        return null;
    }

    public String entityString()
    {
        String s = " ";
        return super.entityString() + s + this.getRate() + s + this.getResourceDistance();
    }
}
