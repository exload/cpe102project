package com.ooqle.game;
/*
* @author Kenny Williams
*/

import com.ooqle.game.entity.WorldObject;

import java.util.HashMap;

public class BattleManager
{
    private static HashMap<WorldObject, WorldObject> assignedTargets = new HashMap<>();

    public static WorldObject getTarget(WorldObject attacker)
    {
        return assignedTargets.get(attacker);
    }

    public static void assignTarget(WorldObject attacker, WorldObject target)
    {
        assignedTargets.put(attacker, target);
    }

    public static void removeTarget(WorldObject attacker)
    {
        assignedTargets.remove(attacker);
    }

    public static boolean isTargetted(WorldObject target)
    {
        return assignedTargets.values().contains(target);
    }
}
