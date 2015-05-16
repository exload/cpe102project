package test.java.org.ooqle;
/*
* @author Kenny Williams
*/

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.java.org.ooqle.entity.*;
import test.java.org.ooqle.util.GameUtilsTests;
import test.java.org.ooqle.util.TupleTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PointTest.class,
        EntityTests.class,
        GridTest.class,
        WorldTest.class,
        AnimatedActorTest.class,
        TupleTests.class,
        MinerTests.class,
        MinerFullTests.class,
        MoveableActorTest.class,
        OreTests.class,
        VeinTests.class,
        WorldObjectTests.class,
        GameUtilsTests.class,
        OreBlobTests.class,
        MinerNotFullTests.class,
        BlacksmithTests.class
})
public class TestCases
{
}
