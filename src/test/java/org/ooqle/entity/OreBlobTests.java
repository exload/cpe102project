package test.java.org.ooqle.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;
import com.ooqle.game.WorldModel;
import com.ooqle.game.entity.*;
import com.ooqle.game.util.Tuple;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OreBlobTests
{
    private OreBlob blob = new OreBlob("blob", new Point(0, 0), 1, 1);
    private WorldModel world = new WorldModel(10, 10, new Background("bg"));
    private Vein v = new Vein("vein", new Point(1, 0), 1, 1);

    @Test
    public void testToVein1()
    {
        Tuple expected = new Tuple<>(blob.getPosition(), false);
        assertTrue(expected.equals(blob.toVein(world, null)));
    }

    @Test
    public void testToVein2()
    {
        Tuple expected = new Tuple<>(v.getPosition(), true);
        assertEquals(expected, blob.toVein(world, v));
    }

    @Test
    public void testNextPosition1()
    {
        Point expected = blob.getPosition().clone();
        assertEquals(expected, blob.nextPosition(world, new Point(0, 0)));
    }

    @Test
    public void testNextPosition2()
    {
        Point expected = new Point(0, 1);
        world.addEntity(new Ore("e", new Point(0, 1), 1));
        assertEquals(expected, blob.nextPosition(world, new Point(0, 1)));
    }

    @Test
    public void testNextPosition3()
    {
        Point expected = new Point(-1, 0);
        world.addEntity(new Entity("e", "t", new Point(0, 0), 1));
        assertEquals(expected, blob.nextPosition(world, new Point(-1, -3)));
    }

    @Test
    public void testEntityString()
    {
        assertEquals("unknown", blob.entityString());
    }
}
