package test.java.org.ooqle.entity;

import com.ooqle.game.Point;
import com.ooqle.game.entity.AnimatedActor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by augiedoebling on 5/4/15.
 */
public class AnimatedActorTest
{
    @Test
    public void testGetAnimationRate()
    {
        AnimatedActor actor = new AnimatedActor("augie", "actor", new Point(1, 1), 2, 4);
        assertEquals(actor.getAnimationRate(), 4);
    }
}
