package com.ooqle.game.util;
/*
* @author Kenny Williams
*/

public class GameUtils
{
    public static int sign(int x)
    {
        if(x < 0)
        {
            return -1;
        }else if(x > 0)
        {
            return 1;
        }
        return 0;
    }
}
