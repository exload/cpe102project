package test.java.org.ooqle.entity;

import com.ooqle.game.Point;
import com.ooqle.game.WorldModel;
import com.ooqle.game.entity.MovableActor;
import com.ooqle.game.entity.WorldObject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by augiedoebling on 5/4/15.
 */
public class MoveableActorTest {

    MovableActor actor = new MovableActor("augie", "actor", new Point(2, 1), 2, 3);
    WorldModel world = new WorldModel(3, 2, new WorldObject("betsy"));
    Point point = new Point(5, 3);

    @Test
    public void testNextPosition() {
        assertEquals(actor.nextPosition(world, point), new Point(3, 1));
    }
}
