package com.ooqle.game;

import com.ooqle.game.Point;
import com.ooqle.game.entity.*;
import com.ooqle.game.util.WorldObjectSettings;
import processing.core.PImage;

import java.util.*;

/**
 * Created by augiedoebling on 5/15/15.
 */
public class ActionManager {

    public ActionManager()
    {
    }

    private int randomWithRange(int min, int max)
    {
        return (int) (Math.random() * ((max - min) + 1)) + min;
    }

    public OreBlob create_blob(WorldObject world, String name, Point pt, int rate, long ticks)
    {
        List<PImage> blob_imgs = Game.getImages("images/blob*.bmp", 12);

        OreBlob blob = new OreBlob(name, pt, blob_imgs, rate, randomWithRange(WorldObjectSettings.BLOB_ANIMATION_MIN,
                WorldObjectSettings.BLOB_ANIMATION_MAX) * WorldObjectSettings.BLOB_ANIMATION_RATE_SCALE);
        blob.schedule(world, ticks);

        return blob;
    }

    public Ore create_ore(WorldObject world, String name, Point pt, int rate, long ticks)
    {
        PImage ore_imgs = Game.getImage("images/ore.bmp");
        Ore ore = new Ore(name, pt, Arrays.asList(ore_imgs), rate);
        ore.schedule(world, ticks);

        return ore;
    }

    public Quake create_quake(WorldObject world, Point pt, int rate, long ticks)
    {
        List<PImage> quake_imgs = Game.getImages("images/quake*.bmp", 6);

        Quake quake = new Quake("quake", pt, quake_imgs, WorldObjectSettings.QUAKE_ANIMATION_RATE, 2);
        quake.schedule(world, ticks);

        return quake;
    }

    public Vein create_vein(String name, Point pt, int rate)
    {
        Vein vein = new Vein("vein"+ name, pt, Arrays.asList(Game.getImage("images/vein.bmp")), rate);

        return vein;
    }
}
