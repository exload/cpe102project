package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;
import com.ooqle.game.World;
import processing.core.PImage;

import java.util.Arrays;
import java.util.List;

public class WorldObject
{
    private String name;
    private String type;
    private Point position;
    private int rate;
    private List<PImage> imgs;

    //TODO: Find out object type for list of images
    public WorldObject(String name, String type, Point position, List<PImage> imgs, int rate)
    {
        this.name = name;
        this.type = type;
        this.position = position;
        this.imgs = imgs;
        this.rate = rate;
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

    public String getType()
    {
        return this.type;
    }

    public int getRate()
    {
        return rate;
    }

    public List<PImage> getImages()
    {
        return imgs;
    }

    public void setImages(List<PImage> imgs)
    {
        this.imgs = imgs;
    }

    public void removeEntity(World world)
    {
        //TODO: Implement me
    }

    public String entityString()
    {
        String s = " ";
        return this.type + s + this.getName() + s + this.getPosition().toString();
    }
}
