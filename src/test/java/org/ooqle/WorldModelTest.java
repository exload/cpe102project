package test.java.org.ooqle;

import com.ooqle.game.Point;
import com.ooqle.game.WorldModel;
import com.ooqle.game.entity.Background;
import com.ooqle.game.entity.Entity;
import com.ooqle.game.entity.WorldObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by augiedoebling on 5/3/15.
 */
public class WorldModelTest {
    private Background ooqle =  new Background("ooqle", new Point(1, 1));
    private WorldModel theworld = new WorldModel(10, 10, ooqle);
    private Entity kenny = new Entity("kenny", new Point(1, 2));
    private Entity eric = new Entity("eric", new Point(1, 3));
    private Entity sameer = new Entity("sameer", new Point(1, 4));

    @Test
    public void testWithinBounds() {
        assertEquals(theworld.withinBounds(new Point(9, 9)), true);
        assertEquals(theworld.withinBounds(new Point(11, 3)), false);
    }

    //TODO: add findnearestoftype

//    @Test
//    public void testFindNearestOfType() {
//
//    }

    @Test
    public void testAddEntity() {
        Entity kenny = new Entity("kenny", new Point(1, 2));
        Entity eric = new Entity("eric", new Point(1, 3));
        Entity sameer = new Entity("sameer", new Point(1, 4));

        theworld.addEntity(kenny);
        theworld.addEntity(eric);
        theworld.addEntity(sameer);

        List<Entity> entities = theworld.getEntities();

        assertEquals(entities.get(0).getName(), "kenny");
        assertEquals(entities.get(1).getName(), "eric");
    }

    @Test
    public void testMoveEntity() {
        Point kennyposition2 = new Point(1, 5);
        theworld.moveEntity(kenny, kennyposition2);

        assertEquals(kenny.getPosition().getY(), kennyposition2.getY());
        assertEquals(kenny.getPosition().getX(), kennyposition2.getX());
    }

    @Test
    public void testRemoveEntity() {
        Point newemptypoint = sameer.getPosition();

        theworld.removeEntityAt(newemptypoint);

        assertEquals(theworld.getTileOccpant(newemptypoint), null);
    }

    @Test
    public void testGetBackground() {
        Point backgroundpoint = new Point(2, 1);

        WorldObject getbackgroundobject = theworld.getBackground(backgroundpoint);

        assertEquals(getbackgroundobject, ooqle);
    }

    @Test
    public void testSetBackground() {
        Point newbackgroundpoint = new Point(2, 3);
        Background google = new Background("google", new Point(2, 4));

        theworld.setBackground(newbackgroundpoint, google);

        assertEquals(theworld.getBackground(newbackgroundpoint), google);
    }

//    @Test
//    public void testGetOccupant() {
//        assertEquals(theworld.getTileOccpant(eric.getPosition()), eric);
//        assertEquals(theworld.getTileOccpant(kenny.getPosition()), kenny);
//    }

    @Test
    public void testGetEntities() {
        assertEquals(theworld.getEntities().get(0), eric);
        assertEquals(theworld.getEntities().get(1), kenny);
        assertEquals(theworld.getEntities().get(2), null);
    }
}
