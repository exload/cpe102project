package test.java.org.ooqle;
/*
* @author Kenny Williams
*/

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.java.org.ooqle.entity.WorldObjectTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PointTest.class,
        WorldObjectTest.class
})
public class TestCases{}
