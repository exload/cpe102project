package test.java.org.ooqle.util;
/*
* @author Kenny Williams
*/

import com.ooqle.game.util.Tuple;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TupleTests
{
    private Tuple t = new Tuple<>("test", true);

    @Test
    public void testGetKey()
    {
        assertEquals("test", t.getKey());
    }

    @Test
    public void testGetValue()
    {
        assertEquals(true, t.getValue());
    }

    @Test
    public void testEquals()
    {
        Tuple o = new Tuple<>("test", true);
        assertTrue(t.equals(o));
    }
}
