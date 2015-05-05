package test.java.org.ooqle.entity;

import com.ooqle.game.Point;
import com.ooqle.game.entity.Miner;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by augiedoebling on 5/4/15.
 */
public class MinerTests
{
    Miner miner = new Miner("augie", "theminer", new Point(2, 1), 1, 2, 4);

    @Test
    public void testGetResourceLimit()
    {
        assertEquals(miner.getResourceLimit(), 4);
    }
}
