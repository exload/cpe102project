package com.ooqle.game;
/*
* @author Ooqle Software
*/

import org.json.simple.JSONObject;

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

    public boolean adjacent(Point p2)
    {
        return ((this.x == p2.x && Math.abs(this.y - p2.y) == 1) || (this.y == p2.y && Math.abs(this.x - p2.x) == 1));
    }

    public int distanceSq(Point p2)
    {
        int dx = (this.x - p2.x);
        int dy = (this.y - p2.y);
        return dx * dx + dy * dy;
    }

    public boolean equals(Object other)
    {
        if(other == this)
        {
            return true;
        }

        if(!(other instanceof Point))
        {
            return false;
        }

        Point otherPt = (Point) other;

        return otherPt.getX() == this.getX() && otherPt.getY() == this.getY();
    }

    public Point clone()
    {
        return new Point(this.getX(), this.getY());
    }

    public String toString()
    {
        return this.getX() + " " + this.getY();
    }

    public JSONObject toJSON()
    {
        JSONObject out = new JSONObject();
        out.put("x", this.getX());
        out.put("y", this.getY());
        return out;
    }
}
