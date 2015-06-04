package com.ooqle.game.entity;

import com.ooqle.game.*;
import com.ooqle.game.util.Action;
import com.ooqle.game.util.GameUtils;
import com.ooqle.game.util.Tuple;
import processing.core.PImage;

import java.util.*;

/**
 * Created by augiedoebling on 5/31/15.
 */
public class Goblin extends MovableActor implements Attackable
{
    private MovableActor target;

    public Goblin(String name, String type, Point position, List<PImage> imgs, int rate, int animationRate, int health)
    {
        super(name, "goblin", position, imgs, rate, animationRate, health);
        target = null;
    }

    public Class getGoalType()
    {
        return Soldier.class;
    }

    public Class nearestTypeForSearching()
    {
        return Soldier.class;
    }

    Tuple<List<Point>, Boolean> applyAction(World world, Actor obj)
    {
        Point pos = this.getPosition();
        if (obj == null)
        {
            return new Tuple<>(Collections.singletonList(pos), false);
        }
        Point otherPt = obj.getPosition();
        if (pos.adjacent(otherPt))
        {
            MovableActor target = (MovableActor) world.getWorldObjectAt(otherPt);
            this.attack(target, world, "goblin");
            return new Tuple<>(new ArrayList<>(), true);
        } else
        {
            Point newPt = this.nextPosition(world, otherPt);
            return new Tuple<>(world.moveWorldObject(this, newPt), false);
        }
    }

    public Tuple<List<Point>, Boolean> getNearest(World world, Class type)
    {
        Point pos = this.getPosition();
        Actor nearestOfType = (Actor) world.findNearestOfType(pos, type);
        return this.applyAction(world, nearestOfType);
    }

    public void schedule(World world, long ticks)
    {
        this.scheduleAction(world, this.createAction(world), ticks + this.getRate());
        this.scheduleAnimation(world);
    }

    public Action createAction(World world)
    {
        Action a = (long currentTicks) ->
        {
            Tuple<List<Point>, Boolean> tup = this.getNearest(world, this.nearestTypeForSearching());
            if (!this.isDead())
            {
                this.scheduleAction(world, this.createAction(world), currentTicks + this.getRate());
            } else
            {
                this.setImages(GameUtils.getSpriteImages(Game.getImage("images/characters/goblin/goblin_die.png"), 9));
                this.die(world);
            }
            return tup.getKey();
        };
        this.removePendingAction(a);
        return a;
    }

    @Override
    public List<PImage> getAttackImages()
    {
        return GameUtils.getSpriteImages(Game.getImage("images/characters/goblin/goblin_attack_left.png"), 7);
    }

    @Override
    public List<PImage> getMoveImages()
    {
        return GameUtils.getSpriteImages(Game.getImage("images/characters/goblin/goblin_move_left.png"), 6);
    }

    @Override
    public MovableActor getTarget(World world)
    {
        List<MovableActor> availableToAttack = new ArrayList<>();
        for (WorldObject obj : world.getWorldObjects())
        {
            if (!(obj instanceof Goblin) && obj instanceof MovableActor && !BattleManager.isTargetted(obj))
            {
                availableToAttack.add((MovableActor) obj);
            }
        }

        Comparator<MovableActor> minerThenSoldier = (MovableActor a, MovableActor b) ->
        {
            if (a instanceof Soldier)
            {
                return 1;
            }

            if (b instanceof Soldier)
            {
                return 1;
            }
            return 0;
        };

        Comparator<MovableActor> distanceComparator = (MovableActor a, MovableActor b) ->
                GameUtils.euclideanDistanceSqaure(this.getPosition(), a.getPosition()) - GameUtils.euclideanDistanceSqaure(this.getPosition(), b.getPosition());

        availableToAttack.sort(minerThenSoldier.thenComparing(distanceComparator));

        if (availableToAttack.isEmpty())
        {
            return null;
        }

        BattleManager.assignTarget(this, availableToAttack.get(0));

        return availableToAttack.get(0);
    }
}
