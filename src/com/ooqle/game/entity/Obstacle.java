package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;

public class Obstacle extends Entity
{
    public Obstacle(String name, Point position)
    {
        super(name, "obstacle", position, 0);
    }
}
