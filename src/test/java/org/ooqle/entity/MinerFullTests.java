package test.java.org.ooqle.entity;

import com.ooqle.game.Game;
import com.ooqle.game.Point;
import com.ooqle.game.entity.MinerFull;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by augiedoebling on 5/4/15.
 */
public class MinerFullTests
{
    MinerFull theminer = new MinerFull("miner", new Point(2, 1), Game.getImages("images/miner*.bmp", 5), 3, 2, 5);

    @Test
    public void testEntityString()
    {
        assertEquals(theminer.entityString(), "unknown");
    }
}
