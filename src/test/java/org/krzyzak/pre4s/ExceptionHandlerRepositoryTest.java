package org.krzyzak.pre4s;

import com.google.common.base.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.krzyzak.pre4s.test.handlers.CheckedExceptionHandler;
import org.krzyzak.pre4s.test.handlers.IAEExceptionHandler;
import org.krzyzak.pre4s.test.handlers.ISEExceptionHandler;
import org.krzyzak.pre4s.test.handlers.RuntimeExceptionHandler;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: tk
 * Date: 21.12.12
 * Time: 00:24
 * To change this template use File | Settings | File Templates.
 */
@RunWith(MockitoJUnitRunner.class)
public class ExceptionHandlerRepositoryTest {


    ExceptionHandlerRepository ehc;

    RuntimeExceptionHandler runtimeExceptionHandler = new RuntimeExceptionHandler();
    CheckedExceptionHandler checkedExceptionHandler = new CheckedExceptionHandler();
    ISEExceptionHandler iseExceptionHandler = new ISEExceptionHandler();
    IAEExceptionHandler iaeExceptionHandler = new IAEExceptionHandler();

    @Before
    public void before() {
        ehc = new ExceptionHandlerRepository(Arrays.asList(runtimeExceptionHandler, checkedExceptionHandler, iaeExceptionHandler, iseExceptionHandler));
    }

    @Test
    public void itShouldReturnHandlerWhenExactMatch() {
        assertThat(ehc.getMatchingExceptionHandler(RuntimeException.class).get()).isSameAs(runtimeExceptionHandler);
    }

    @Test
    public void itShouldReturnInherentlyMatchingHandlerWhenNoExactMatch() {
        assertThat(ehc.getMatchingExceptionHandler(NullPointerException.class).get()).isSameAs(runtimeExceptionHandler);
    }

    @Test
    public void itShouldReturnClosestInherentMatchWhenManyInherentMatches() {
        assertThat(ehc.getMatchingExceptionHandler(NumberFormatException.class).get()).isSameAs(iaeExceptionHandler);
    }

    @Test
    public void itShouldReturnAbsentWhenNoMatchingExceptionHandler() {
        ehc = new ExceptionHandlerRepository(Collections.<ExceptionHandler<?>>emptyList());

        Optional<ExceptionHandler<Exception>> e = ehc.getMatchingExceptionHandler(Exception.class);

        assertThat(e.isPresent()).isFalse();
    }


}
