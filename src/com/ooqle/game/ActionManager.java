package com.ooqle.game;

import com.ooqle.game.entity.*;
import com.ooqle.game.util.WorldObjectSettings;
import processing.core.PImage;

import java.util.*;

/**
 * Created by augiedoebling on 5/15/15.
 */
public class ActionManager
{

    private static int randomWithRange(int min, int max)
    {
        return (int) (Math.random() * ((max - min) + 1)) + min;
    }

    public static OreBlob createBlob(World world, String name, Point pt, int rate, long ticks)
    {
        List<PImage> blobImgs = Game.getImages("images/blob*.bmp", 12);

        OreBlob blob = new OreBlob(name, pt, blobImgs, rate, randomWithRange(WorldObjectSettings.BLOB_ANIMATION_MIN,
                WorldObjectSettings.BLOB_ANIMATION_MAX) * WorldObjectSettings.BLOB_ANIMATION_RATE_SCALE);
        blob.schedule(world, ticks);

        return blob;
    }

    public static Ore createOre(World world, String name, Point pt, long ticks)
    {
        PImage oreImgs = Game.getImage("images/ore.bmp");
        int newRate = randomWithRange(WorldObjectSettings.ORE_CORRUPT_MIN, WorldObjectSettings.ORE_CORRUPT_MAX);
        Ore ore = new Ore(name, pt, Collections.singletonList(oreImgs), newRate);
        ore.schedule(world, ticks);

        return ore;
    }

    public static Quake createQuake(World world, Point pt, long ticks)
    {
        List<PImage> quakeImgs = Game.getImages("images/quake*.bmp", 6);
        Quake quake = new Quake("quake", pt, quakeImgs, WorldObjectSettings.QUAKE_ANIMATION_RATE, 2);
        quake.schedule(world, ticks);

        return quake;
    }

    public static Vein createVein(String name, Point pt, int rate)
    {

        return new Vein("vein" + name, pt, Collections.singletonList(Game.getImage("images/vein.bmp")), rate);
    }
}
