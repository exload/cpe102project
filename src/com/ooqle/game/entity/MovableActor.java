package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;
import com.ooqle.game.util.GameUtils;
import com.ooqle.game.World;
import processing.core.PImage;

import java.util.List;

public class MovableActor extends AnimatedActor
{
    public MovableActor(String name, String type, Point position, List<PImage> imgs, int rate, int animationRate)
    {
        super(name, type, position, imgs, rate, animationRate);
    }

    public Point nextPosition(World world, Point destPt)
    {
        int horiz = GameUtils.sign(destPt.getX() - this.getPosition().getX());
        Point newPt = new Point(this.getPosition().getX() + horiz, this.getPosition().getY());

        if(horiz == 0 || world.isOccupied(newPt))
        {
            int vert = GameUtils.sign(destPt.getY() - this.getPosition().getY());
            newPt = new Point(this.getPosition().getX(), this.getPosition().getY() + vert);

            if(vert == 0 || world.isOccupied(newPt))
            {
                newPt = new Point(this.getPosition().getX(), this.getPosition().getY());
            }
        }
        return newPt;
    }
}
