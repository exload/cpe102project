package com.ooqle.game.entity;

/**
 * Created by augiedoebling on 6/1/15.
 */
import com.ooqle.game.Point;
import processing.core.PImage;

import java.util.List;

public class Base extends Actor
{
    private int resourceCount;

    public Base(String name, String type, Point position, int rate, List<PImage> imgs)
    {
        super(name, type, position, imgs, rate);
        this.resourceCount = 0;
    }

    public String entityString()
    {
        String s = " ";
        return super.entityString() + s + this.getRate() + s + this.getResourceCount();
    }
}