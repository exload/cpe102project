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
    private Point pt = new Point(7, -1);
    @Test
    public void testGetX()
    {
        assertEquals(7, pt.getX());
    }

    @Test
    public void testGetY()
    {
        assertEquals(-1, pt.getY());
    }

    @Test
    public void testAdjacent()
    {
        assertTrue(pt.adjecent(new Point(7, 1)));
    }

    @Test
    public void testPointEquals()
    {
        assertEquals(new Point(1, 2), new Point(1, 2));
    }
}
