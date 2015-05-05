package test.java.org.ooqle.entity;

import com.ooqle.game.Point;
import com.ooqle.game.entity.Blacksmith;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by augiedoebling on 5/4/15.
 */
public class BlacksmithTests {
    Blacksmith smith = new Blacksmith("augie", new Point(2, 1), 4, 2);

    @Test
    public void testEntityString() {
       assertEquals(smith.entityString(), "blacksmith augie 2 1 4 2");
    }

    @Test
    public void testGetResourceDistance() {
        assertEquals(smith.getResourceDistance(), 2);
    }

}
