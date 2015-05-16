package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;
import com.ooqle.game.World;
import com.ooqle.game.util.Action;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WorldObject
{
    private String name;
    private String type;
    private Point position;
    private int rate;
    private int currentImg;
    private List<PImage> imgs;
    private List<Action> pendingActions;

    //TODO: Find out object type for list of images
    public WorldObject(String name, String type, Point position, List<PImage> imgs, int rate)
    {
        this.name = name;
        this.type = type;
        this.position = position;
        this.imgs = imgs;
        this.rate = rate;
        this.pendingActions = new ArrayList<>();
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

    public PImage getImage()
    {
        return this.getImages().get(this.currentImg);
    }

    public void nextImage()
    {
        this.currentImg = (this.currentImg + 1) % this.getImages().size();
    }

    public void removeEntity(World world)
    {
        this.getPendingActions().forEach(world::unscheduleAction);
        this.getPendingActions().clear();
    }

    public void addPendingAction(Action action)
    {
        this.getPendingActions().add(action);
    }

    public void removePendingAction(Action action)
    {
        this.getPendingActions().remove(action);
    }

    public List<Action> getPendingActions()
    {
        return this.pendingActions;
    }

    public void clearPendingActions(World world)
    {
        this.getPendingActions().forEach(world::unscheduleAction);
        this.getPendingActions().clear();
    }

    public void scheduleAction(World world, Action action, long time)
    {
        this.addPendingAction(action);
        world.scheduleAction(action, time);
    }

    public String entityString()
    {
        String s = " ";
        return this.type + s + this.getName() + s + this.getPosition().toString();
    }
}
