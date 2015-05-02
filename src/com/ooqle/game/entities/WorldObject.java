package com.ooqle.game.entities;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;

import java.util.List;

public class WorldObject
{
    private String name;
    private Point position;
    private List<String> imgs;
    private int currentImg;

    //TODO: Find out object type for list of images
    public WorldObject(String name, Point position, List<String> imgs)
    {
        this.name = name;
        this.position = position;
        this.imgs = imgs;
        this.currentImg = 0;
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

    public List<String> getImgs()
    {
        return imgs;
    }

    public String getCurrentImg()
    {
        return this.imgs.get(this.currentImg);
    }

    public void nextImage()
    {
        this.currentImg = this.currentImg + 1 % this.imgs.size();
    }
}
