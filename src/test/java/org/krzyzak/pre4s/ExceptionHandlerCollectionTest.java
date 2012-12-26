package org.krzyzak.pre4s;

import com.google.common.base.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.krzyzak.pre4s.handlers.CheckedExceptionHandler;
import org.krzyzak.pre4s.handlers.IAEExceptionHandler;
import org.krzyzak.pre4s.handlers.ISEExceptionHandler;
import org.krzyzak.pre4s.handlers.RuntimeExceptionHandler;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

/**
 * Created with IntelliJ IDEA.
 * User: tk
 * Date: 21.12.12
 * Time: 00:24
 * To change this template use File | Settings | File Templates.
 */
@RunWith(MockitoJUnitRunner.class)
public class ExceptionHandlerCollectionTest {


    @Mock
    GenericsUtil genericsUtil;

    ExceptionHandlerCollection ehc;

    RuntimeExceptionHandler runtimeExceptionHandler = new RuntimeExceptionHandler();
    CheckedExceptionHandler checkedExceptionHandler = new CheckedExceptionHandler();
    ISEExceptionHandler iseExceptionHandler = new ISEExceptionHandler();
    IAEExceptionHandler iaeExceptionHandler = new IAEExceptionHandler();

    @Before
    public void before() {
        doReturn(RuntimeException.class).when(genericsUtil).getActualTypeParamters(runtimeExceptionHandler);
        doReturn(Exception.class).when(genericsUtil).getActualTypeParamters(checkedExceptionHandler);
        doReturn(IllegalStateException.class).when(genericsUtil).getActualTypeParamters(iseExceptionHandler);
        doReturn(IllegalArgumentException.class).when(genericsUtil).getActualTypeParamters(iaeExceptionHandler);

        ehc = new ExceptionHandlerCollection(Arrays.asList(runtimeExceptionHandler, checkedExceptionHandler, iaeExceptionHandler, iseExceptionHandler), genericsUtil);
    }

    @Test
    public void itShouldExtractExceptionHandlersTypeParameterWhenCreated() {


        verify(genericsUtil).getActualTypeParamters(runtimeExceptionHandler);
        verify(genericsUtil).getActualTypeParamters(checkedExceptionHandler);
        verify(genericsUtil).getActualTypeParamters(iaeExceptionHandler);
        verify(genericsUtil).getActualTypeParamters(iseExceptionHandler);

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
        ehc = new ExceptionHandlerCollection(Collections.<RestExceptionHandler<?>>emptyList(), genericsUtil);

        Optional<RestExceptionHandler<Exception>> e = ehc.getMatchingExceptionHandler(Exception.class);

        assertThat(e.isPresent()).isFalse();
    }


}
