package test.java.org.ooqle.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;
import com.ooqle.game.entity.Actor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ActorTests
{
    private Actor a = new Actor("name", "t", new Point(1, 1), 2);

    @Test
    public void testGetResourceCount()
    {
        assertEquals(0, a.getResourceCount());
    }

    @Test
    public void testSetResourceCount()
    {
        a.setResourceCount(2);
        assertEquals(2, a.getResourceCount());
    }
}
