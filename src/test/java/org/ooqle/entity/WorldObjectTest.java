package test.java.org.ooqle.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;
import com.ooqle.game.entity.WorldObject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WorldObjectTest
{
    private WorldObject worldObj = new WorldObject("worldobject", new Point(1, 2));

    @Test
    public void testGetName()
    {
        assertEquals("worldobject", worldObj.getName());
    }

    @Test
    public void testGetPosition()
    {
        assertEquals(new Point(1, 2), worldObj.getPosition());
    }

    @Test
    public void testSetPosition()
    {
        worldObj.setPosition(new Point(-1, 0));
        assertEquals(new Point(-1, 0), worldObj.getPosition());
    }
}
