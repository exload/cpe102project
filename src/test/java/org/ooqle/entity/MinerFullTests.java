package test.java.org.ooqle.entity;

import com.ooqle.game.Point;
import com.ooqle.game.entity.MinerNotFull;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by augiedoebling on 5/4/15.
 */
public class MinerFullTests {
    MinerNotFull miner = new MinerNotFull("miner", new Point(2, 1), 3, 2, 5);

    @Test
    public void testEntityString(){
        assertEquals(miner.entityString(), "miner miner 2 1 5 3 2");
    }
}
