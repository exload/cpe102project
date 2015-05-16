package test.java.org.ooqle;

import com.ooqle.game.Point;
import com.ooqle.game.World;
import com.ooqle.game.entity.Background;
import com.ooqle.game.entity.Entity;
import com.ooqle.game.entity.Ore;
import com.ooqle.game.entity.Quake;
import com.ooqle.game.entity.WorldObject;
import org.junit.Test;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by augiedoebling on 5/3/15.
 */
public class WorldTest
{
    private List<PImage> imgs = new ArrayList<>();
    private Background ooqle = new Background(new PImage());
    private World theworld = new World(10, 10, ooqle);
    private Ore kenny = new Ore("kenny", new Point(1, 2), imgs, 2);
    private Ore eric = new Ore("eric", new Point(1, 3), imgs, 2);
    private Ore sameer = new Ore("sameer", new Point(1, 4), imgs, 2);

    @Test
    public void testWithinBounds()
    {
        assertEquals(theworld.withinBounds(new Point(9, 9)), true);
        assertEquals(theworld.withinBounds(new Point(11, 3)), false);
    }

    //TODO: add findnearestoftype

    @Test
    public void testFindNearestOfType()
    {
        theworld.addWorldObject(new Ore("augie", new Point(6, 6), imgs, 3));
        theworld.addWorldObject(new Ore("kenny", new Point(8, 8), imgs, 1));

        assertEquals(theworld.findNearestOfType(new Point(4, 4), Ore.class).getName(), "augie");
    }

    @Test
    public void testAddEntity()
    {
        Ore kenny = new Ore("kenny", new Point(1, 2), imgs, 2);
        Ore eric = new Ore("eric", new Point(1, 3), imgs, 2);
        Ore sameer = new Ore("sameer", new Point(1, 4), imgs, 2);

        theworld.addWorldObject(kenny);
        theworld.addWorldObject(eric);
        theworld.addWorldObject(sameer);

        List<WorldObject> entities = theworld.getWorldObjects();

        assertEquals(entities.get(0).getName(), "kenny");
        assertEquals(entities.get(1).getName(), "eric");
    }

    @Test
    public void testMoveEntity()
    {
        Point kennyposition2 = new Point(1, 5);
        theworld.moveWorldObject(kenny, kennyposition2);

        assertEquals(kenny.getPosition().getY(), kennyposition2.getY());
        assertEquals(kenny.getPosition().getX(), kennyposition2.getX());
    }

    @Test
    public void testRemoveEntity()
    {
        Point newemptypoint = sameer.getPosition();

        theworld.removeEntityAt(newemptypoint);

        assertEquals(theworld.getWorldObjectAt(newemptypoint), null);
    }

    @Test
    public void testGetBackground()
    {
        Point backgroundpoint = new Point(2, 1);

        Background getbackgroundobject = theworld.getBackgroundAt(backgroundpoint);

        assertEquals(getbackgroundobject, ooqle);
    }

    @Test
    public void testSetBackground()
    {
        Point newbackgroundpoint = new Point(2, 3);
        Background google = new Background("google");

        theworld.setBackground(newbackgroundpoint, google);

        assertEquals(theworld.getBackground(newbackgroundpoint), google);
    }

    @Test
    public void testGetOccupant()
    {
        theworld.addEntity(eric);
        theworld.addEntity(kenny);
        assertEquals(theworld.getTileOccpant(eric.getPosition()), eric);
        assertEquals(theworld.getTileOccpant(kenny.getPosition()), kenny);
    }

    @Test
    public void testGetEntities()
    {
        theworld.addEntity(eric);
        theworld.addEntity(kenny);
        assertEquals(theworld.getEntities().get(0), eric);
        assertEquals(theworld.getEntities().get(1), kenny);
    }
}
