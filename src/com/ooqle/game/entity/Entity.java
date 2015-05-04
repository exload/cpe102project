package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;
import com.ooqle.game.WorldModel;

public class Entity extends WorldObject
{
    private int rate;

    public Entity(String name, String type, Point position, int rate)
    {
        super(name, type, position);
        this.rate = rate;
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
