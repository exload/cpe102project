package test.java.org.ooqle.entity;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Point;
import com.ooqle.game.WorldModel;
import com.ooqle.game.entity.Background;
import com.ooqle.game.entity.OreBlob;
import com.ooqle.game.util.Tuple;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OreBlobTests
{
    private OreBlob blob = new OreBlob("blob", new Point(0, 0), 1, 1);
    private WorldModel world = new WorldModel(10, 10, new Background("bg"));

    @Test
    public void testToVein()
    {
        Tuple expected = new Tuple<>(blob.getPosition(), false);
        assertTrue(expected.equals(blob.toVein(world, null)));
    }
}
