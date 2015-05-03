package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;
import com.ooqle.game.WorldModel;

public class MovableActor extends AnimatedActor
{


    public MovableActor(String name, Point position)
    {
        super(name, position);
    }

    public Point nextPosition(WorldModel world, Point destPt)
    {
        //TODO: Implement me
        return null;
    }
}
