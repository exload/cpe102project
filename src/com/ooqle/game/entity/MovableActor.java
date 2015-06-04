package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;
import com.ooqle.game.World;
import com.ooqle.game.util.Action;
import com.ooqle.game.util.Tuple;
import processing.core.PImage;

import java.util.List;

public abstract class MovableActor extends AnimatedActor
{
    private Tuple<List<Point>, List<Point>> travelled;
    private Boolean exists = true;
    private int health;

    public MovableActor(String name, String type, Point position, List<PImage> imgs, int rate, int animationRate, int health)
    {
        super(name, type, position, imgs, rate, animationRate);
        this.health = health;
    }

    public abstract Class getGoalType();
    public abstract Action createAction(World world);

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

    public void setHealth(int health)
    {
        this.health = health;
    }

    public int getHealth()
    {
        return this.health;
    }
}
