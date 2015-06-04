package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;
import com.ooqle.game.World;
import com.ooqle.game.util.Action;
import org.json.simple.JSONObject;
import processing.core.PImage;

import java.util.Collections;
import java.util.List;

public class AnimatedActor extends Actor
{
    private int animationRate;

    public AnimatedActor(String name, String type, Point position, List<PImage> imgs, int rate, int animationRate)
    {
        super(name, type, position, imgs, rate);
        this.animationRate = animationRate;
    }

    public int getAnimationRate()
    {
        return animationRate;
    }

    public void setAnimationRate(int newrate)
    {
        animationRate = newrate;
    }

    public Action createAnimationAction(World world, int repeatCount)
    {
        Action a = (long currentTicks) ->
        {
            this.nextImage();

            if (repeatCount != -1)
            {
                this.scheduleAction(world, this.createAnimationAction(world, Math.max(repeatCount - 1, 0)), currentTicks + this.getAnimationRate());
            }
            return Collections.singletonList(this.getPosition());
        };
        this.removePendingAction(a);
        return a;
    }

    public void scheduleAnimation(World world, int repeatCount)
    {
        this.scheduleAction(world, this.createAnimationAction(world, repeatCount), this.getAnimationRate());
    }

    public void scheduleAnimation(World world)
    {
        scheduleAnimation(world, 0);
    }

    public JSONObject entityJSON()
    {
        JSONObject out = super.entityJSON();
        out.put("animationRate", this.getAnimationRate());
        return out;
    }
}
