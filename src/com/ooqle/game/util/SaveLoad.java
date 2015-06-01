package com.ooqle.game.util;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Game;
import com.ooqle.game.Point;
import com.ooqle.game.World;
import com.ooqle.game.entity.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import processing.core.PImage;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SaveLoad
{
    public static World load()
    {
        File file = new File("res/newworld");

        List<PImage> miner_imgs = Game.getImages("images/miner*.png", 5);

        World newworld = new World(WorldObjectSettings.GAMEWIDTH, WorldObjectSettings.GAMEHEIGHT, new Background(Game.getImage("images/background/tile_dark_grey.png")));
        try
        {
            Scanner scanner = new Scanner(file);

            Object obj = JSONValue.parse(scanner.nextLine());

            JSONObject obj2 = (JSONObject) obj;

            for(Object ob : (JSONArray) obj2.get("background"))
            {
                JSONObject job = (JSONObject) ob;
                if(job.get("type").equals("rocks"))
                {
                    JSONObject location = (JSONObject) job.get("location");
                    newworld.setBackground(new Point(Integer.parseInt((String) location.get("x")), Integer.parseInt((String) location.get("y"))), new Background(Game.getImage("images/background/tile_light_grey.png")));
                }
            }

            JSONArray worldobjects = (JSONArray) obj2.get("worldObjects");

            for(Object worldob : worldobjects)
            {
                JSONObject jwob = (JSONObject) worldob;
                JSONObject location = (JSONObject) jwob.get("location");
                if(jwob.get("type").equals("miner"))
                {
                    MinerNotFull miner = (new MinerNotFull(
                            "miner",
                            new Point(Integer.parseInt((String) location.get("x")), Integer.parseInt((String) location.get("y"))),
                            miner_imgs,
                            Integer.parseInt((String) jwob.get("rate")),
                            Integer.parseInt((String) jwob.get("animationRate")),
                            Integer.parseInt((String) jwob.get("resourceLimit"))));

                    newworld.addWorldObject(miner);
                    scheduleEntity(newworld, miner);
                }
                else if(jwob.get("type").equals("vein"))
                {
                    Vein vein = new Vein(
                            "vein",
                            new Point(Integer.parseInt((String) location.get("x")), Integer.parseInt((String) location.get("y"))),
                            Arrays.asList(Game.getImage("images/vein.png")),
                            Integer.parseInt((String) jwob.get("rate")),
                            Integer.parseInt((String) jwob.get("resourceDistance")));

                    newworld.addWorldObject(vein);

                    scheduleEntity(newworld, vein);
                }
                else if(jwob.get("type").equals("blacksmith"))
                {
                    Blacksmith blacksmith = new Blacksmith(
                            "blacksmith",
                            new Point(Integer.parseInt((String) location.get("x")), Integer.parseInt((String) location.get("y"))),
                            Integer.parseInt((String) jwob.get("rate")),
                            Arrays.asList(Game.getImage("images/blacksmith.png")));

                    newworld.addWorldObject(blacksmith);
                    scheduleEntity(newworld, blacksmith);
                }
                else if(jwob.get("type").equals("obstacle"))
                {
                    Obstacle obstacle = new Obstacle(
                            "obstacle",
                            new Point(Integer.parseInt((String) location.get("x")), Integer.parseInt((String) location.get("y"))),
                            Arrays.asList(Game.getImage("images/obstacle.png")));

                    newworld.addWorldObject(obstacle);
                    scheduleEntity(newworld, obstacle);
                }
                else
                {
                    System.out.println("type: "+ jwob.get("type") + "not defined");
                }
            }
        }
        catch(IOException ex)
        {
            System.out.println(ex);
        }

        return newworld;
    }

    public static void scheduleEntity(World world, WorldObject worldobject)
    {
        if(worldobject instanceof Miner)
        {
            MinerNotFull miner = (MinerNotFull) worldobject;
            miner.schedule(world, 0);
        }
        if(worldobject instanceof Vein)
        {
            Vein vein = (Vein) worldobject;
            vein.schedule(world, 0);
        }
        if(worldobject instanceof Ore)
        {
            Ore ore = (Ore) worldobject;
            ore.schedule(world, 0);
        }
    }

    public static void main(String[] args) {
        load();
    }
}
