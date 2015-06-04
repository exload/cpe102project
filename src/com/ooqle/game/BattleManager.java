package com.ooqle.game;
/*
* @author Kenny Williams
*/

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.ooqle.game.entity.MovableActor;
import com.ooqle.game.entity.WorldObject;

import java.util.Collection;
import java.util.Random;

public class BattleManager
{
    private static Multimap<WorldObject, WorldObject> assignedTargets = ArrayListMultimap.create();

    public static Collection<WorldObject> getTargets(WorldObject attacker)
    {
        return assignedTargets.get(attacker);
    }

    public static void assignTarget(WorldObject attacker, WorldObject target)
    {
        assignedTargets.put(attacker, target);
    }

    public static void removeTarget(WorldObject attacker)
    {
        assignedTargets.removeAll(attacker);
    }

    //public static int getTotalTargets()

    public static boolean isTargetted(WorldObject target)
    {
        return assignedTargets.values().contains(target);
    }

    public static void fight(MovableActor a1, MovableActor a2)
    {
        Random rand = new Random();
        if(rand.nextInt(2) == 0)
        {
            a1.takeDamage(1);
        }else
        {
            a2.takeDamage(1);
        }
    }
}
