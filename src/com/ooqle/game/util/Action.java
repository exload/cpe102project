package com.ooqle.game.util;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;

import java.util.List;

public interface Action
{
    List<Point> run(long currentTicks);
}
