package com.ooqle.game;
/*
* @author Kenny Williams
*/

import com.ooqle.game.entity.Background;
import com.ooqle.game.entity.WorldObject;
import com.ooqle.game.util.Action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class World
{
    private int width;
    private int height;
    private List<WorldObject> worldObjectList;
    private Map<Long, List<Action>> actionQueue;
    private Grid<Background> backgroundGrid;
    private Grid<WorldObject> worldObjectGrid;

    public World(int width, int height, Background initBackground)
    {
        this.width = width;
        this.height = height;
        backgroundGrid = new Grid<>(width, height, initBackground);
        worldObjectGrid = new Grid<>(width, height, null);
        actionQueue = new HashMap<>();
        worldObjectList = new ArrayList<>();
    }

    public Grid<Background> getBackgroundGrid()
    {
        return backgroundGrid;
    }

    public void setBackgroundGrid(Grid<Background> backgroundGrid)
    {
        this.backgroundGrid = backgroundGrid;
    }

    public Grid<WorldObject> getWorldObjectGrid()
    {
        return worldObjectGrid;
    }

    public void setWorldObjectGrid(Grid<WorldObject> worldObjectGrid)
    {
        this.worldObjectGrid = worldObjectGrid;
    }

    public boolean withinBounds(Point pt)
    {
        return pt.getX() >= 0 && pt.getX() < this.width && pt.getY() >= 0 && pt.getY() < this.height;
    }

    public boolean isOccupied(Point pt)
    {
        return this.withinBounds(pt) && this.getWorldObjectGrid().getCell(pt) != null;
    }

    public void addWorldObject(WorldObject obj)
    {
        Point pt = obj.getPosition();
        if (withinBounds(pt))
        {
            WorldObject oldEntity = this.getWorldObjectGrid().getCell(pt);
            if (oldEntity != null)
            {
                oldEntity.clearPendingActions(this);
            }
            this.getWorldObjectGrid().setCell(pt, obj);
            this.worldObjectList.add(obj);
        }
    }

    public void removeEntityAt(Point pt)
    {
        if (this.withinBounds(pt) && this.getWorldObjectGrid().getCell(pt) != null)
        {
            WorldObject obj = this.worldObjectGrid.getCell(pt);
            obj.setPosition(new Point(-1, -1));
            this.worldObjectList.remove(obj);
            this.getWorldObjectGrid().setCell(pt, null);
        }
    }

    public WorldObject getWorldObjectAt(Point pt)
    {
        if (this.withinBounds(pt))
        {
            return this.getWorldObjectGrid().getCell(pt);
        }
        return null;
    }

    public void setBackground(Point pt, Background bg)
    {
        this.getBackgroundGrid().setCell(pt, bg);
    }

    public Background getBackgroundAt(Point pt)
    {
        return this.getBackgroundGrid().getCell(pt);
    }

    public List<Point> moveWorldObject(WorldObject obj, Point pt)
    {
        List<Point> tiles = new ArrayList<>(2);
        if (this.withinBounds(pt))
        {
            Point oldPt = obj.getPosition();
            this.getWorldObjectGrid().setCell(oldPt, null);
            tiles.add(oldPt);
            this.getWorldObjectGrid().setCell(pt, obj);
            tiles.add(pt);
            obj.setPosition(pt);
        }
        return tiles;
    }

    public List<WorldObject> getWorldObjects()
    {
        return this.worldObjectList;
    }

    public WorldObject findNearestOfType(Point pt, Class type)
    {
        HashMap<WorldObject, Integer> oftype = new HashMap<>();
        for (WorldObject obj : this.worldObjectList)
        {
            if (type.isInstance(obj))
            {
                oftype.put(obj, pt.distanceSq(obj.getPosition()));
            }
        }
        return nearestEntity(oftype);
    }

    /*
    Actions
     */

    public void scheduleAction(Action action, long time)
    {
        List<Action> actions;
        if (!actionQueue.containsKey(time))
        {
            actions = new ArrayList<>();
        } else
        {
            actions = actionQueue.get(time);
        }
        actions.add(action);
        actionQueue.put(time, actions);
    }

    public void unscheduleAction(Action action)
    {
        for (List<Action> actions : actionQueue.values())
        {
            for (int i = 0; i < actions.size(); i++)
            {
                if (actions.get(i) == action)
                {
                    actions.remove(i);
                }
            }
        }
    }

    public void updateOnTime(long ticks)
    {
        for(Map.Entry<Long, List<Action>> kv : actionQueue.entrySet())
        {
            if(kv.getKey() < ticks)
            {
                for (Action a : actionQueue.get(ticks))
                {
                    a.run(ticks);
                }
            }
        }
    }

    /*
    Static Functions
     */

    public static WorldObject nearestEntity(HashMap<WorldObject, Integer> entityDist)
    {
        if (!entityDist.isEmpty())
        {
            boolean first = true;
            WorldObject entity = null;
            Integer integer = null;
            for (Map.Entry<WorldObject, Integer> other : entityDist.entrySet())
            {
                if (first)
                {
                    entity = other.getKey();
                    integer = other.getValue();
                    first = false;
                } else
                {
                    if (other.getValue() < integer)
                    {
                        entity = other.getKey();
                        integer = other.getValue();
                    }
                }
            }
            return entity;
        }
        return null;
    }
}