package test.java.org.ooqle;
/*
* @author Kenny Williams
*/

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.java.org.ooqle.entity.AnimatedActorTest;
import test.java.org.ooqle.entity.EntityTests;
import test.java.org.ooqle.entity.MinerTests;
import test.java.org.ooqle.util.TupleTests;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PointTest.class,
        EntityTests.class,
        GridTest.class,
        WorldModelTest.class,
        AnimatedActorTest.class,
        TupleTests.class,
        MinerTests.class
})
public class TestCases{}
