package test.java.org.ooqle;
/*
* @author Kenny Williams
*/

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.java.org.ooqle.entity.*;
import test.java.org.ooqle.util.TupleTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PointTest.class,
        EntityTests.class,
        GridTest.class,
        WorldModelTest.class,
        AnimatedActorTest.class,
        TupleTests.class,
        MinerTests.class,
        MinerFullTests.class,
        MoveableActorTest.class,
})
public class TestCases{}
