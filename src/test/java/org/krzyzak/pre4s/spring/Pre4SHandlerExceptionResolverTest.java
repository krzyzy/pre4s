package org.krzyzak.pre4s.spring;

import com.google.common.base.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.krzyzak.pre4s.ExceptionHandlerRepository;
import org.krzyzak.pre4s.test.handlers.IAEExceptionHandler;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.same;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

/**
 * Created with IntelliJ IDEA.
 * User: tk
 * Date: 28.12.12
 * Time: 15:12
 * To change this template use File | Settings | File Templates.
 */
@RunWith(MockitoJUnitRunner.class)
public class Pre4SHandlerExceptionResolverTest {

    @Mock
    private IAEExceptionHandler iaeExceptionHandler;

    @Mock
    ExceptionHandlerRepository exceptionHandlerRepository;

    private Pre4SHandlerExceptionResolver resolver;

    @Before
    public void before(){
        doReturn(Optional.of(iaeExceptionHandler)).when(exceptionHandlerRepository).getMatchingExceptionHandler(IllegalArgumentException.class);

        resolver = new Pre4SHandlerExceptionResolver(exceptionHandlerRepository);
    }

    @Test
    public void itShouldAskRepositoryForHandlerWhenHandlingException(){
        IllegalArgumentException argumentException = new IllegalArgumentException();
        resolver.doResolveException(null, null, null, argumentException);

        verify(exceptionHandlerRepository).getMatchingExceptionHandler(same(IllegalArgumentException.class));
    }

}
