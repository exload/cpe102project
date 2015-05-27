package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;
import com.ooqle.game.util.GameUtils;
import com.ooqle.game.World;
import com.ooqle.game.util.Tuple;
import processing.core.PImage;

import java.util.List;

public class MovableActor extends AnimatedActor
{
    public MovableActor(String name, String type, Point position, List<PImage> imgs, int rate, int animationRate)
    {
        super(name, type, position, imgs, rate, animationRate);
    }

    public Point nextPosition(World world, Point destPt)
    {
        System.out.println(destPt);
        Tuple<List<Point>, List<Point>> path = world.createPath(this.getPosition(), destPt);
        if(path == null)
        {
            return this.getPosition();
        }
        return path.getValue().iterator().next();
    }
}
