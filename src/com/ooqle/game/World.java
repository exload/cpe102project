package com.ooqle.game;
/*
* @author Kenny Williams
*/

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.ooqle.game.entity.*;
import com.ooqle.game.util.Action;
import com.ooqle.game.util.Tuple;

import java.util.*;

public class World
{
    private int width;
    private int height;
    private List<WorldObject> worldObjectList;
    private TreeMap<Long, List<Action>> actionQueue;
    private Grid<Background> backgroundGrid;
    private Grid<WorldObject> worldObjectGrid;

    public World(int width, int height, Background initBackground)
    {
        this.width = width;
        this.height = height;
        backgroundGrid = new Grid<>(width, height, initBackground);
        worldObjectGrid = new Grid<>(width, height, null);
        actionQueue = new TreeMap<>();
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

    public void setWorldObjectAt(Point pt, WorldObject obj)
    {
        this.getWorldObjectGrid().setCell(pt, obj);
    }

    public void removeEntityAt(Point pt)
    {
        if (this.withinBounds(pt) && this.getWorldObjectAt(pt) != null)
        {
            WorldObject obj = this.getWorldObjectAt(pt);
            obj.setPosition(new Point(-1, -1));
            this.worldObjectList.remove(obj);
            this.setWorldObjectAt(pt, null);
        }
    }

    public void removeEntity(WorldObject obj)
    {
        this.removeEntityAt(obj.getPosition());
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

    public void updateOnTime(long ticks)
    {
        if (actionQueue.isEmpty())
        {
            return;
        }

        Map.Entry<Long, List<Action>> next = actionQueue.firstEntry();

        while (ticks >= next.getKey())
        {
            Iterator<Action> it = next.getValue().iterator();
            while (it.hasNext())
            {
                it.next().run(ticks);
                it.remove();
            }

            actionQueue.remove(next.getKey());
            next = actionQueue.firstEntry();
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

    private int euclideanDistanceSqaure(Point p1, Point p2)
    {
        return (int) (Math.pow((p1.getX() - p2.getX()), 2) + Math.pow((p1.getY() - p2.getY()), 2));
    }

    private int manhattanDistance(Point p1, Point p2)
    {
        return (Math.abs(p1.getX() - p2.getX()) + Math.abs(p1.getY() - p2.getY()));
    }

    private List<Point> getNeighbors(Point pt, Class goalType)
    {
        Point north = new Point(pt.getX(), pt.getY() - 1);
        Point east = new Point(pt.getX() + 1, pt.getY());
        Point south = new Point(pt.getX(), pt.getY() + 1);
        Point west = new Point(pt.getX() - 1, pt.getY());
        List<Point> neighbors = Arrays.asList(north, east, south, west);
        List<Point> out = new ArrayList<>();

        for (Point neighbor : neighbors)
        {
            if ((!this.isOccupied(neighbor) || goalType.isInstance(this.getWorldObjectAt(neighbor))) && this.withinBounds(pt))
            {
                out.add(neighbor);
            }
        }
        return out;
    }

    private List<Point> reconstructPath(Map<Point, Point> cameFrom, Point current)
    {
        List<Point> path = new ArrayList<>();
        path.add(current);
        while (cameFrom.containsKey(current))
        {
            current = cameFrom.get(current);
            path.add(0, current);
        }
        return path;
    }

    public Tuple<List<Point>, List<Point>> createPath(Point start, Point goal)
    {
        MovableActor actor = (MovableActor) this.getWorldObjectAt(start);

        List<Point> closedSet = new ArrayList<>();
        Multimap<Integer, Point> openSet = ArrayListMultimap.create();
        List<Point> visited = new ArrayList<>();
        Map<Point, Point> cameFrom = new HashMap<>();
        Map<Point, Integer> gScore = new HashMap<>();
        Map<Point, Integer> fScore = new HashMap<>();

        gScore.put(start, 0);
        fScore.put(start, gScore.get(start) + manhattanDistance(start, goal));
        openSet.put(gScore.get(start) + manhattanDistance(start, goal), start);
        while (!openSet.isEmpty())
        {
            Map.Entry<Integer, Point> firstEntry = openSet.entries().iterator().next();
            Integer firstKey = firstEntry.getKey();
            Point curr = firstEntry.getValue();


            if (curr.equals(goal))
            {
                return new Tuple<>(visited, reconstructPath(cameFrom, goal));
            }

            if (!visited.contains(curr))
            {
                visited.add(curr);
            }

            openSet.remove(firstKey, curr);
            closedSet.add(curr);

            for (Point neighbor : getNeighbors(curr, actor.getGoalType()))
            {
                if (closedSet.contains(neighbor))
                {
                    continue;
                }

                int tentativeGScore = gScore.get(curr) + 1; //TODO: Could change this to be a calculation

                if (!openSet.containsValue(neighbor) || tentativeGScore < gScore.get(neighbor))
                {
                    cameFrom.put(neighbor, curr);
                    gScore.put(neighbor, tentativeGScore);

                    int neighborFScore = gScore.get(neighbor) + manhattanDistance(neighbor, goal);
                    fScore.put(neighbor, neighborFScore);

                    if (!openSet.containsValue(neighbor))
                    {
                        openSet.put(neighborFScore, neighbor);
                    }
                }
            }
        }
        return null;
    }
}