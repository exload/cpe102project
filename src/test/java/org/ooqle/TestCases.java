package test.java.org.ooqle;
/*
* @author Kenny Williams
*/

import com.ooqle.game.Grid;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.java.org.ooqle.entity.WorldObjectTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PointTest.class,
        WorldObjectTest.class,
        Grid.class
})
public class TestCases{}
