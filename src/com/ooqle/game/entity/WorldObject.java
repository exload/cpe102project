package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;
import com.ooqle.game.World;
import com.ooqle.game.util.Action;
import org.json.simple.JSONObject;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WorldObject
{
    private String name;
    private String type;
    private Point position;
    private int rate;
    private int currentImg;
    private List<PImage> imgs;
    private List<Action> pendingActions;
    private UUID uuid;

    public WorldObject(String name, String type, Point position, List<PImage> imgs, int rate)
    {
        this.name = name;
        this.type = type;
        this.position = position;
        this.imgs = imgs;
        this.rate = rate;
        this.pendingActions = new ArrayList<>();
        this.uuid = UUID.randomUUID();
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
        currentImg = 0;
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
        this.getPendingActions().clear();
        world.removeEntity(this);
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
        world.unscheduleActions(this.getPendingActions());
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

    public JSONObject entityJSON()
    {
        JSONObject out = new JSONObject();
        out.put("type", this.type);
        out.put("location", this.getPosition().toJSON());
        return out;
    }

    public UUID getUUID()
    {
        return uuid;
    }

    public void setUUID(UUID uuid)
    {
        this.uuid = uuid;
    }
}
