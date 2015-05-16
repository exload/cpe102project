package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;
import processing.core.PImage;

import java.util.List;

public class MinerFull extends Miner
{

    public MinerFull(String name, Point position, List<PImage> imgs, int rate, int resourceLimit, int animationRate)
    {
        super(name, "unknown", position, imgs, rate, animationRate,resourceLimit);
    }

   public String entityString()
   {
       return "unknown";
   }
}
