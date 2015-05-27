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
    public MovableActor(String name, String type, Point position, List<PImage> imgs, int rate, int animationRate)
    {
        super(name, type, position, imgs, rate, animationRate);
    }

    public abstract Class getGoalType();

    public Point nextPosition(World world, Point destPt)
    {
        Tuple<List<Point>, List<Point>> travelled = world.createPath(this.getPosition(), destPt);

        if(travelled == null)
        {
            System.out.println("NULL PATH");
            return this.getPosition();
        }
        System.out.println("Path: " + travelled.getValue());
        List<Point> path = travelled.getValue();
        return path.get(1);
    }
}
