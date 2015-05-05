package com.ooqle.game.util;
/*
* @author Kenny Williams
*/

public class Tuple<T, U>
{
    private T t;
    private U u;

    public Tuple(T t, U u)
    {
        this.t = t;
        this.u = u;
    }

    public T getKey()
    {
        return t;
    }

    public U getValue()
    {
        return u;
    }

    public boolean equals(Object other)
    {
        if(this == other)
        {
            return true;
        }

        if(!(other instanceof Tuple))
        {
            return false;
        }
        Tuple t = (Tuple) other;
        return this.getKey().equals(t.getKey()) && this.getValue().equals(t.getValue());
    }

    public String toString()
    {
        return "(" + this.getKey() + ", " + this.getValue() + ")";
    }
}
