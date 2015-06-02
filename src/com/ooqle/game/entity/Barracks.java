package com.ooqle.game.entity;

import com.ooqle.game.Game;
import com.ooqle.game.Point;
import processing.core.PImage;

import java.util.Arrays;
import java.util.List;

/**
 * Created by augiedoebling on 6/1/15.
 */
public class Barracks extends Base {

    public Barracks(String name, Point position, int rate)
    {
        super(name, "blacksmith", position, rate, Arrays.asList(Game.getImage("images/structures/barracks.png")));
    }
}
