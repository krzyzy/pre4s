package org.krzyzak.pre4s;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.krzyzak.pre4s.distance.ExceptionClassDistanceCalculatorTest;
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
@Suite.SuiteClasses({ExceptionClassDistanceTest.class, ExceptionClassDistanceCalculatorTest.class, TypeHelperTest.class, ExceptionHandlerCollectionTest.class, Pre4SControllerIntegerationTest.class})
public class Pre4sTestSuite {
}