package com.ooqle.game.entities;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;

import java.util.List;

public class Obstacle extends Entity
{

    public Obstacle(String name, Point position, List<String> imgs)
    {
        super(name, position, imgs);
    }
}
