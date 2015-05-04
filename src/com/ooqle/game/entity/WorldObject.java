package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;

public class WorldObject
{
    private String name;
    private String type;
    private Point position;

    //TODO: Find out object type for list of images
    public WorldObject(String name, String type, Point position)
    {
        this.name = name;
        this.type = type;
        this.position = position;
    }

    public String getName()
    {
        return name;
    }

    public Point getPosition()
    {
        return position;
    }

    public void setPosition(Point position)
    {
        this.position = position;
    }

    public String entityString()
    {
        String s = " ";
        return this.type + s + this.getName() + s + this.getPosition().toString() + s;
    }
}
