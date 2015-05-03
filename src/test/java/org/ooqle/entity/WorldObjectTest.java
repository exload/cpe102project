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

    public void testGetPosition()
    {
        assertEquals(new Point(1, 2), worldObj.getPosition());
    }
}
