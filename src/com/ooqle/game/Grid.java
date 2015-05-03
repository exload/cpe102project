package com.ooqle.game;

import com.ooqle.game.entity.Background;
import com.ooqle.game.entity.WorldObject;

import java.util.List;

/**
 * Created by augiedoebling on 5/2/15.
 */
public class Grid {

    private int width;
    private int height;
    private WorldObject[][] cells;

    public Grid(int width, int height, WorldObject occupancyValue)
    {
        this.width = width;
        this.height = height;
        this.cells = new WorldObject[height][width];

        for(int r = 0; r < cells.length; r++)
        {
            for(int c = 0; c < cells[r].length; c++)
            {
                this.cells[r][c] = occupancyValue;
            }
        }
    }

    public void setCell(Point pt, WorldObject value)
    {
        this.cells[pt.getY()][pt.getX()] = value;
    }

    public WorldObject getCell(Point pt)
    {
        return this.cells[pt.getY()][pt.getX()];
    }
}
