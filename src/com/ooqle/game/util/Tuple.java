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
}
