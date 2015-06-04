package com.ooqle.game;


/**
 * Created by augiedoebling on 5/2/15.
 */
public class Grid<T>
{

    private int width;
    private int height;
    private T[][] cells;

    @SuppressWarnings("unchecked")
    public Grid(int width, int height, T initValue)
    {
        this.width = width;
        this.height = height;

        this.cells = (T[][]) new Object[height][width];

        for (int r = 0; r < cells.length; r++)
        {
            for (int c = 0; c < cells[r].length; c++)
            {
                this.cells[r][c] = initValue;
            }
        }
    }

    public void setCell(Point pt, T value)
    {
        this.cells[pt.getY()][pt.getX()] = value;
    }

    public T getCell(Point pt)
    {
        return this.cells[pt.getY()][pt.getX()];
    }

    public T[][] getGrid()
    {
        return this.cells;
    }
}
