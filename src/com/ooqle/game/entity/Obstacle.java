package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;
import processing.core.PImage;

import java.util.List;

public class Obstacle extends WorldObject
{
    public Obstacle(String name, Point position, List<PImage> imgs)
    {
        super(name, "obstacle", position, imgs, 0);
    }
}
