package com.ooqle.game;
/*
* @author Kenny Williams
*/

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.ooqle.game.entity.WorldObject;

import java.util.Collection;

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
}
