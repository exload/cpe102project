package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.World;

public interface Attackable
{
    void getTarget(World world);

    void setHealth(int health);
    int getHealth();
}
