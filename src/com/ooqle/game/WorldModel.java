package com.ooqle.game;

import com.ooqle.game.entities.Background;
import com.ooqle.game.entities.Entity;
import com.ooqle.game.entities.WorldObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by augiedoebling on 5/2/15.
 */
public class WorldModel {

    private int numRows;
    private int numCols;
    private Grid grid;
    private ArrayList<Entity> entities;
    private Grid occupancy;

    public WorldModel(int numRows, int numCols, Background thisbackground)
    {
        this.grid = new Grid(numCols, numRows, thisbackground);
        this.numRows = numRows;
        this.numCols = numCols;
        this.occupancy = new Grid(numCols, numRows, null);
        this.entities = new ArrayList<Entity>();
    }

    public boolean withinBounds(Point pt)
    {
        return (0 <= pt.getX()) && (pt.getX() < this.numCols) && (0 <= pt.getY()) && (pt.getY() < this.numRows);
    }

    public Entity findNearestOfType(Point pt, Class type)
    {
        HashMap<Entity, Integer> oftype = new HashMap<>();
        for(Entity e : this.entities)
        {
            if(type.isInstance(e))
            {
                oftype.put(e, pt.distanceSq(e.getPosition()));
            }
        }
        return nearestEntity(oftype);
    }

    public void addEntity(Entity entity)
    {
        Point pt = entity.getPosition();
        if(this.withinBounds(pt))
        {
            WorldObject oldEntity = this.occupancy.getCell(pt);
            if(oldEntity != null)
            {
                oldEntity.clearPendingActions();
            }
            this.occupancy.setCell(pt, entity);
            this.entities.add(entity);
        }
    }

    public List<Point> moveEntity(Entity entity, Point pt)
    {
        List<Point> tiles = new ArrayList<Point>();
        if(this.withinBounds(pt))
        {
            Point oldPt = entity.getPosition();
            this.occupancy.setCell(oldPt, null);
            tiles.add(oldPt);
            this.occupancy.setCell(pt, entity);
            tiles.add(pt);
            entity.setPosition(pt);
        }
        return tiles;
    }

    public void removeEntity(Entity entity)
    {
        this.removeEntityAt(entity.getPosition());
    }

    public void removeEntityAt(Point pt)
    {
        if(this.withinBounds(pt) && this.occupancy.getCell(pt) != null)
        {
            WorldObject entity = this.occupancy.getCell(pt);
            entity.setPosition(new Point(-1, -1));
            this.entities.remove(entity);
            this.occupancy.setCell(pt, null);
        }
    }

    //TODO: write schedule action

    //TODO: write unschdule action

    //TODO: write update on time

    public String getBackgroundImage(Point pt)
    {
        if(this.withinBounds(pt))
        {
            return this.grid.getCell(pt).getImage();
        }
        else
        {
            return null;
        }
    }

    public String getBackground(Point pt)
    {
        if(this.withinBounds(pt))
        {
            return this.grid.getCell(pt);
        }
        else
        {
            return null;
        }
    }

    public void setBackground(Point pt, Background backg)
    {
        if(this.withinBounds(pt))
        {
            this.grid.setCell(pt, backg);
        }
    }

    public WorldObject getTileOccpant(Point pt)
    {
        if(this.withinBounds(pt))
        {
            return this.occupancy.getCell(pt);
        }
        else
        {
            return null
        }
    }

    public List<Entity> getEntities()
    {
        return this.entities;
    }

    // TODO: save_world

    // TODO: load_world

    public static Entity nearestEntity(HashMap<Entity, Integer> entityDist)
    {
        if(!entityDist.isEmpty())
        {
            boolean first = true;
            Entity entity = null;
            Integer integer = null;
            for(Map.Entry<Entity, Integer> other : entityDist.entrySet())
            {
                if(first)
                {
                    entity = other.getKey();
                    integer = other.getValue();
                }
                else
                {
                    if(other.getValue() < integer)
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
