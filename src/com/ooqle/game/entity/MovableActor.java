package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;
import com.ooqle.game.util.GameUtils;
import com.ooqle.game.World;
import com.ooqle.game.util.Tuple;
import processing.core.PImage;

import java.util.Iterator;
import java.util.List;

public class MovableActor extends AnimatedActor
{
    public MovableActor(String name, String type, Point position, List<PImage> imgs, int rate, int animationRate)
    {
        super(name, type, position, imgs, rate, animationRate);
    }

    public Point nextPosition(World world, Point destPt)
    {
        Tuple<List<Point>, List<Point>> travelled = world.createPath(this.getPosition(), destPt);
        System.out.println("Path: " + travelled.getValue());
        if(travelled == null)
        {
            return this.getPosition();
        }
        List<Point> path = travelled.getValue();
        return path.get(1);
    }
}
