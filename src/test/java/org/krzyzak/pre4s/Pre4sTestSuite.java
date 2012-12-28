package org.krzyzak.pre4s;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.krzyzak.pre4s.controller.Pre4STestControllerIntegerationTest;
import org.krzyzak.pre4s.distance.ExceptionClassDistanceTest;
import org.krzyzak.pre4s.tools.TypeHelperTest;

/**
 * Created with IntelliJ IDEA.
 * User: tomasz
 * Date: 26.12.12
 * Time: 22:32
 * To change this template use File | Settings | File Templates.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ExceptionClassDistanceTest.class, TypeHelperTest.class, ExceptionHandlerResolverTest.class, Pre4STestControllerIntegerationTest.class})
public class Pre4sTestSuite {
}
