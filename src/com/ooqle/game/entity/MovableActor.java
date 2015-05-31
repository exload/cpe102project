package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;
import com.ooqle.game.World;
import com.ooqle.game.util.Tuple;
import processing.core.PImage;

import java.util.List;

public abstract class MovableActor extends AnimatedActor
{
    private Tuple<List<Point>, List<Point>> travelled;
    private Boolean exists = true;

    public MovableActor(String name, String type, Point position, List<PImage> imgs, int rate, int animationRate)
    {
        super(name, type, position, imgs, rate, animationRate);
    }

    public abstract Class getGoalType();

    public Point nextPosition(World world, Point destPt)
    {
        travelled = world.createPath(this.getPosition(), destPt);

        if(travelled == null)
        {
            return this.getPosition();
        }
        List<Point> path = travelled.getValue();
        return path.get(1);
    }

    public List<Point> getVisited()
    {
        if(travelled != null) {
            return travelled.getKey().subList(2, travelled.getKey().size());
        }
        else
        {
            return null;
        }
    }

    public List<Point> getPath()
    {
        if(travelled != null) {
            return travelled.getValue().subList(2, travelled.getValue().size());
        }
        else
        {
            return null;
        }
    }

    public boolean doesThisExist()
    {
        return exists;
    }

    public void removedFromWorld()
    {
        exists = false;
    }
}
