package org.krzyzak.pre4s.spring;

import com.google.common.base.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.krzyzak.pre4s.ExceptionHandler;
import org.krzyzak.pre4s.ExceptionHandlerRepository;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.DelegatingServletOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.mockito.Matchers.same;
import static org.mockito.Mockito.*;

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
    private ExceptionHandler iaeExceptionHandler;

    @Mock
    ExceptionHandlerRepository exceptionHandlerRepository;

    private Pre4SHandlerExceptionResolver resolver;

    @Before
    public void before(){
        doReturn(Optional.of(iaeExceptionHandler)).when(exceptionHandlerRepository).getMatchingExceptionHandler(IllegalArgumentException.class);

        resolver = new Pre4SHandlerExceptionResolver(exceptionHandlerRepository);
    }

    @Test
    public void itShouldAskRepositoryForHandlerWhenHandlingException() throws IOException {
        IllegalArgumentException argumentException = new IllegalArgumentException();
        HttpServletResponse response = mockServletResponse();
        resolver.doResolveException(mock(HttpServletRequest.class), response, null, argumentException);

        verify(exceptionHandlerRepository).getMatchingExceptionHandler(same(IllegalArgumentException.class));
    }

    @Test
    public void itShouldSetAttributesOfResponseBasedOnResultOfExceptionHandler() throws IOException {
        IllegalArgumentException argumentException = new IllegalArgumentException();
        ResponseEntity responseEntity = new ResponseEntity("body", HttpStatus.OK);

        doReturn(responseEntity).when(iaeExceptionHandler).handle(argumentException);
        HttpServletResponse response = mockServletResponse();

        resolver.doResolveException(mock(HttpServletRequest.class), response, null, argumentException);

        verify(response).setStatus(HttpStatus.OK.value());
    }

    private HttpServletResponse mockServletResponse() throws IOException {
        HttpServletResponse response = mock(HttpServletResponse.class);
        doReturn(new DelegatingServletOutputStream(new ByteArrayOutputStream())).when(response).getOutputStream();
        return response;
    }

}
