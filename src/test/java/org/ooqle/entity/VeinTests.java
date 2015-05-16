package test.java.org.ooqle.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Game;
import com.ooqle.game.Point;
import com.ooqle.game.World;
import com.ooqle.game.entity.Background;
import com.ooqle.game.entity.Vein;
import com.ooqle.game.entity.WorldObject;
import org.junit.Test;
import processing.core.PImage;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class VeinTests
{
    private Vein v = new Vein("vein", new Point(0, 0), Arrays.asList(Game.getImage("images/vein.bmp")), 1);
    private World world = new World(10, 10, new Background(new PImage()));

    @Test
    public void testResourceDistance()
    {
        assertEquals(1, v.getResourceDistance());
    }


    @Test
    public void testFindOpenAround1()
    {
        Point p = v.findOpenAround(world);
        assertEquals(new Point(0, 0), p);
    }

    @Test
    public void testFindOpenAround2()
    {
        world.addWorldObject(new WorldObject("e", "t", new Point(0, 0), Arrays.asList(Game.getImage("images/ore.bmp")), 1));
        Point p = v.findOpenAround(world);
        assertEquals(new Point(1, 0), p);
    }

    @Test
    public void testEntityString()
    {
        String s = " ";
        String expected = v.getType() + s + v.getName() + s + v.getPosition().toString()
                + s + v.getRate() + s + v.getResourceDistance();
        assertEquals(expected, v.entityString());
    }
}
