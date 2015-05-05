package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;
import com.ooqle.game.WorldModel;

public class Entity extends WorldObject
{
    private String type;
    private Point position;
    private int rate;

    public Entity(String name, String type, Point position, int rate)
    {
        super(name);
        this.type = type;
        this.position = position;
        this.rate = rate;
    }

    public Point getPosition()
    {
        return position;
    }

    public void setPosition(Point position)
    {
        this.position = position;
    }

    public String getType()
    {
        return this.type;
    }

    public int getRate()
    {
        return rate;
    }

    public String entityString()
    {
        String s = " ";
        return this.type + s + this.getName() + s + this.getPosition().toString();
    }

    public void removeEntity(WorldModel world)
    {
        //TODO: Implement me
    }
}
