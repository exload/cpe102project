package test.java.org.ooqle.util;

import com.ooqle.game.util.GameUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by augiedoebling on 5/4/15.
 */
public class GameUtilsTests {
    GameUtils utils = new GameUtils();

    @Test
    public void testSign() {
        assertEquals(utils.sign(2), 1);
        assertEquals(utils.sign(-3), -1);
    }
}
