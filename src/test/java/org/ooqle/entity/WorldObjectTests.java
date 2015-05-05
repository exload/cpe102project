package test.java.org.ooqle.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.entity.WorldObject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WorldObjectTests
{
    private WorldObject obj = new WorldObject("name");
    @Test
    public void testGetName()
    {
        assertEquals("name", obj.getName());
    }
}
