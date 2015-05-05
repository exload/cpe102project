package test.java.org.ooqle;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PointTest
{
    private Point pt = new Point(2, 2);

    @Test
    public void testGetX()
    {
        assertEquals(2, pt.getX());
    }

    @Test
    public void testGetY()
    {
        assertEquals(2, pt.getY());
    }

    @Test
    public void testAdjacent()
    {
        assertTrue(pt.adjacent(new Point(2, 3)));
    }

    @Test
    public void testDistanceSq()
    {
        assertEquals(25, pt.distanceSq(new Point(5, 6)));
    }

    @Test
    public void testPointEquals()
    {
        assertEquals(new Point(1, 2), new Point(1, 2));
    }
}
