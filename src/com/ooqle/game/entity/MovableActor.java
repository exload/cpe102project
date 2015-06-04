package com.ooqle.game.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.BattleManager;
import com.ooqle.game.Point;
import com.ooqle.game.World;
import com.ooqle.game.util.Action;
import com.ooqle.game.util.Tuple;
import processing.core.PImage;

import java.util.List;

public abstract class MovableActor extends AnimatedActor
{
    private Tuple<List<Point>, List<Point>> travelled;
    private boolean exists = true;
    private boolean dead;
    private boolean attacking;
    private int health;

    public MovableActor(String name, String type, Point position, List<PImage> imgs, int rate, int animationRate, int health)
    {
        super(name, type, position, imgs, rate, animationRate);
        this.health = health;
        this.dead = false;
        this.attacking = false;
    }

    public abstract Class getGoalType();

    public abstract Action createAction(World world);

    public Point nextPosition(World world, Point destPt)
    {
        travelled = world.createPath(this.getPosition(), destPt);

        if (travelled == null)
        {
            return this.getPosition();
        }
        List<Point> path = travelled.getValue();
        return path.get(1);
    }

    public List<Point> getVisited()
    {
        if (travelled != null)
        {
            return travelled.getKey().subList(2, travelled.getKey().size());
        } else
        {
            return null;
        }
    }

    public List<Point> getPath()
    {
        if (travelled != null)
        {
            return travelled.getValue().subList(2, travelled.getValue().size());
        } else
        {
            return null;
        }
    }

    public boolean doesThisExist()
    {
        return exists;
    }

    public void removedFromWorld()
    {
        exists = false;
    }

    public void setHealth(int health)
    {
        this.health = health;
    }

    public int getHealth()
    {
        return this.health;
    }

    public void takeDamage(int damage)
    {
        this.setHealth(this.getHealth() - damage);
    }

    public void die(World world)
    {
        this.dead = true;
        this.scheduleDeath(world);
    }

    public boolean isDead()
    {
        return this.getHealth() <= 0;
    }

    private void scheduleDeath(World world)
    {
        world.scheduleActionWithWaitTime((long currentticks) ->
        {
            this.setAnimationRate(0);

            world.scheduleActionWithWaitTime((long otherticks) ->
            {
                world.removeEntityAt(this.getPosition());
                return null;
            }, 2000);

            return null;
        }, 600);
    }

    public abstract List<PImage> getAttackImages();
    public abstract List<PImage> getMoveImages();

    public void attack(MovableActor other, World world, String name)
    {
        attacking = true;
        this.setImages(this.getAttackImages());
        other.setImages(other.getAttackImages());
        BattleManager.fight(this, other);
        if (this.isDead())
        {
            this.die(world);
        } else
        {
            this.scheduleResetImages(other, world, name);
        }
        if(other.isDead())
        {
            other.die(world);
        }
    }

    private void scheduleResetImages(MovableActor other, World world, String name)
    {
        world.scheduleActionWithWaitTime((long currentTicks) ->
        {
            this.setImages(this.getMoveImages());
            other.setImages(other.getMoveImages());
            attacking = false;
            return null;
        }, 600);
    }

    public boolean isAttacking()
    {
        return this.attacking;
    }
}
