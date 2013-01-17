package org.krzyzak.pre4s;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.krzyzak.pre4s.tools.ExceptionClassDistanceTest;
import org.krzyzak.pre4s.spring.Pre4SHandlerExceptionResolverTest;
import org.krzyzak.pre4s.test.controller.Pre4STestControllerIntegerationTest;
import org.krzyzak.pre4s.tools.TypeHelperTest;

/**
 * Created with IntelliJ IDEA.
 * User: tomasz
 * Date: 26.12.12
 * Time: 22:32
 * To change this template use File | Settings | File Templates.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ExceptionClassDistanceTest.class, TypeHelperTest.class, ExceptionHandlerRepositoryTest.class, Pre4SHandlerExceptionResolverTest.class, Pre4STestControllerIntegerationTest.class})
public class Pre4sTestSuite {
}
