package test.java.org.ooqle.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;
import com.ooqle.game.entity.WorldObject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EntityTests
{
    private Entity worldObj = new Entity("worldobject", "type", new Point(1, 2), 1);

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

    @Test
    public void testGetType()
    {
        assertEquals("type", worldObj.getType());
    }

    @Test
    public void testGetRate()
    {
        assertEquals(1, worldObj.getRate());
    }

    @Test
    public void testEntityString()
    {
        String s = " ";
        String expected = worldObj.getType() + s + worldObj.getName() + s + worldObj.getPosition().toString();
        assertEquals(expected, worldObj.entityString());
    }
}
