package test.java.org.ooqle.util;

import com.ooqle.game.util.GameUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by augiedoebling on 5/4/15.
 */
public class GameUtilsTests
{
    @Test
    public void testSign()
    {
        assertEquals(GameUtils.sign(2), 1);
        assertEquals(GameUtils.sign(-3), -1);
    }
}
