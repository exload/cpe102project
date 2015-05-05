package test.java.org.ooqle.entity;

import com.ooqle.game.Point;
import com.ooqle.game.entity.Ore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by augiedoebling on 5/4/15.
 */
public class OreTests
{
    Ore ore = new Ore("augie", new Point(2, 1), 3);

    @Test
    public void testEntityString()
    {
        assertEquals(ore.entityString(), "ore augie 2 1 3");
    }
}