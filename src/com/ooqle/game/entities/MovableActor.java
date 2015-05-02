package com.ooqle.game.entities;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;

import java.util.List;

public class MovableActor extends AnimatedActor
{

    public MovableActor(String name, Point position, List<String> imgs)
    {
        super(name, position, imgs);
    }
}
