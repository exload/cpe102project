package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;
import com.ooqle.game.WorldModel;

public class Entity extends WorldObject
{
    private int rate;

    public Entity(String name, Point position)
    {
        super(name, position);
    }


    public int getRate()
    {
        return rate;
    }

    public void removeEntity(WorldModel world)
    {
        //TODO: Implement me
    }
}
