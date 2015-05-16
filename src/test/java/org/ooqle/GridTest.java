package test.java.org.ooqle;

/**
 * Created by augiedoebling on 5/2/15.
 */

import com.ooqle.game.Game;
import com.ooqle.game.Grid;
import com.ooqle.game.Point;
import com.ooqle.game.entity.Background;
import com.ooqle.game.entity.WorldObject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GridTest
{

    private Grid thegrid = new Grid(5, 5, new WorldObject("Augie"));

    @Test
    public void testSetCell()
    {
        Point pt1 = new Point(3, 3);
        WorldObject kenny = new WorldObject("kenny", "miner", pt1, Game.getImages("images/miner*.bmp", 5), 3);

        thegrid.setCell(pt1, kenny);

        assertEquals(thegrid.getCell(pt1).getName(), kenny.getName());
    }

    @Test
    public void testGetCell()
    {
        WorldObject sameeer = new WorldObject("sameer");

        thegrid.setCell(new Point(4, 1), sameeer);

        assertEquals(thegrid.getCell(new Point(4, 1)).getName(), "sameer");
    }
}
