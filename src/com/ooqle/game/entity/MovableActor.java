package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;
import com.ooqle.game.WorldModel;

public class MovableActor extends AnimatedActor
{


    public MovableActor(String name, String type, Point position, int rate)
    {
        super(name, type, position, rate);
    }

    public Point nextPosition(WorldModel world, Point destPt)
    {
        //TODO: Implement me
        return null;
    }
}
