package org.krzyzak.pre4s;

import com.google.common.base.Optional;
import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created with IntelliJ IDEA.
 * User: tk
 * Date: 21.12.12
 * Time: 00:24
 * To change this template use File | Settings | File Templates.
 */
@RunWith(JUnit4.class)
public class ExceptionHandlerCollectionTest {

    ExceptionHandlerCollection ehc;

    @Before
    public void before(){
    }

    @Test
    public void itShouldReturnAbsentWhenNoMatchingExceptionHandler(){
        ehc = new ExceptionHandlerCollection(Collections.<RestExceptionHandler<?>>emptyList());

        Optional<RestExceptionHandler<Exception>> e = ehc.getMatchingExceptionHandler(Exception.class);

        Assertions.assertThat(e.isPresent()).isFalse();
    }


}
