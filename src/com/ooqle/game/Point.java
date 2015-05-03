package com.ooqle.game;
/*
* @author Ooqle Software
*/

public class Point
{
    private int x;
    private int y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }

    public boolean adjecent(Point p2)
    {
        return ((this.x == p2.x && Math.abs(this.y - p2.y) == 1) || (this.y == p2.y && Math.abs(this.x - p2.x) == 1));
    }

    public int distanceSq(Point p2)
    {
        return ((this.x - p2.x) * (this.x - p2.x)) + ((this.y - p2.y) * (this.y - p2.y));
    }
}
