package com.ooqle.game.entity;

import com.ooqle.game.Point;
import processing.core.PImage;

import java.util.List;

/**
 * Created by augiedoebling on 6/1/15.
 */
public class Soldier extends AnimatedActor {

    public Soldier(String name, String type, Point position, List<PImage> imgs, int rate, int animationRate)
    {
        super(name, type, position, imgs, rate, animationRate);
    }
}
