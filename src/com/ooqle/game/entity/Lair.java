package com.ooqle.game.entity;

import com.ooqle.game.ActionManager;
import com.ooqle.game.Game;
import com.ooqle.game.Point;
import com.ooqle.game.World;
import com.ooqle.game.util.Action;
import com.ooqle.game.util.GameUtils;
import com.ooqle.game.util.WorldObjectSettings;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by augiedoebling on 5/31/15.
 */
public class Lair extends Actor {

    private int goblinCount;
    private Point position;
    private boolean initiated;

    public Lair(String name, Point tl_position, int rate)
    {
        super(name, "lair", tl_position, Arrays.asList(Game.getImage("images/cave.png")), rate);
        this.goblinCount = WorldObjectSettings.GOBLINSPAWNCOUNT;
        this.position = tl_position;
    }

    public int getGoblinCount()
    {
        return this.goblinCount;
    }

    public void setGoblinCount(int set)
    {
        this.goblinCount = set;
    }

    public String entityString()
    {
        String s = " ";
        return super.entityString() + s + this.getRate() + s + this.getGoblinCount();
    }
    public Point findOpenAround(World world)
    {
        Point south = new Point(this.position.getX(), this.position.getY() + 2);
        if(world.withinBounds(south) && (!(world.isOccupied(south))))
        {
            return south;
        }

        Point west = new Point(this.position.getX() -1, this.position.getY());
        if(world.withinBounds(west) && (!(world.isOccupied(west))))
        {
            return west;
        }
        Point north = new Point(this.position.getX(), this.position.getY() - 1);
        if(world.withinBounds(north) && (!(world.isOccupied(north))))
        {
            return north;
        }

        Point east = new Point(this.position.getX() +2, this.position.getY());
        if(world.withinBounds(east) && (!(world.isOccupied(east))))
        {
            return east;
        }

        return null;
    }

    public Action createAction(World world)
    {
        Action a = (long currentTicks) ->
        {
            List<Point> tiles = new ArrayList<>();
            Point openPt = this.findOpenAround(world);
            if(openPt != null && this.getGoblinCount() != 0)
            {
                Goblin goblin = ActionManager.createGoblin(world, openPt, currentTicks);
                world.addWorldObject(goblin);
                setGoblinCount(getGoblinCount() - 1);
                tiles.add(openPt);
            }

            this.scheduleAction(world, this.createAction(world), currentTicks + this.getRate());
            return tiles;
        };
        this.removePendingAction(a);
        return a;
    }

    public void schedule(World world, long ticks)
    {
        this.scheduleAction(world, this.createAction(world), ticks + this.getRate());
    }
}
