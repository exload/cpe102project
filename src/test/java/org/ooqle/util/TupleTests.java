package test.java.org.ooqle.util;
/*
* @author Kenny Williams
*/

import com.ooqle.game.util.Tuple;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
}
