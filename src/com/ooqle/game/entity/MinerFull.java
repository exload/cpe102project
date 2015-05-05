package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;

public class MinerFull extends Miner
{

    public MinerFull(String name, Point position, int rate, int resourceLimit)
    {
        super(name, "unknown", position, rate, resourceLimit);
    }

   public String entityString()
   {
       return "unknown";
   }
}
