package org.krzyzak.pre4s.test.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.krzyzak.pre4s.ExceptionHandlerRepository;
import org.krzyzak.pre4s.spring.Pre4SHandlerExceptionResolver;
import org.krzyzak.pre4s.test.Pre4STestConfig;
import org.krzyzak.pre4s.test.handlers.IAEExceptionHandler;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.MvcResult;
import org.springframework.test.web.server.request.MockMvcRequestBuilders;
import org.springframework.test.web.server.setup.MockMvcBuilders;
import org.springframework.test.web.server.setup.StandaloneMockMvcBuilder;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.util.Arrays;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.any;

/**
 * Created with IntelliJ IDEA.
 * User: tomasz
 * Date: 09.12.12
 * Time: 20:00
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Pre4STestConfig.class}, loader = AnnotationConfigContextLoader.class)
public class Pre4STestControllerIntegerationTest {

    public static final String RANDOM_HEADER = "randomHeader";

    @Autowired
    private Pre4STestController pre4STestController;

    private Pre4SHandlerExceptionResolver pre4SHandlerExceptionResolver ;

    private MockMvc mockMvc;

    private IAEExceptionHandler mockExceptionHandler;

    @Before
    public void setUp() throws Exception {
        mockExceptionHandler = Mockito.mock(IAEExceptionHandler.class);
        pre4SHandlerExceptionResolver = new Pre4SHandlerExceptionResolver(new ExceptionHandlerRepository(Arrays.asList(mockExceptionHandler)));

        StandaloneMockMvcBuilder standaloneMockMvcBuilder = MockMvcBuilders.standaloneSetup(pre4STestController);
        standaloneMockMvcBuilder.setHandlerExceptionResolvers(Arrays.<HandlerExceptionResolver>asList(pre4SHandlerExceptionResolver));
        mockMvc = standaloneMockMvcBuilder.build();

    }

    @Test
    public void itShouldSetResponseCodeOnResponseUsingResponseCodeFromExceptinHandlerResult() throws Exception {
        ResponseEntity<String> responseEntity = createResponseEntity();
        Mockito.doReturn(responseEntity).when(mockExceptionHandler).handle(any(IllegalArgumentException.class));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/pre4s/test?fail=true").header("Accept", "application/json")).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(responseEntity.getStatusCode().value(), response.getStatus());
    }

    @Test
    public void itShouldSetResponseHeadersUsingHeadersFromExceptionHandlerResult() throws Exception {
        ResponseEntity<String> responseEntity = createResponseEntity();
        Mockito.doReturn(responseEntity).when(mockExceptionHandler).handle(any(IllegalArgumentException.class));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/pre4s/test?fail=true").header("Accept", "application/json")).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(responseEntity.getHeaders().get(RANDOM_HEADER), response.getHeaders(RANDOM_HEADER));
    }

    @Test
    public void itShouldSetBodyUsingBodyFromExceptionHandlerResult() throws Exception {
        ResponseEntity<String> responseEntity = createResponseEntity();
        Mockito.doReturn(responseEntity).when(mockExceptionHandler).handle(any(IllegalArgumentException.class));

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/pre4s/test?fail=true").header("Accept", "application/json")).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(result.getResponse().getContentAsString(), "\""+responseEntity.getBody()+"\"");
    }

    private ResponseEntity<String> createResponseEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(RANDOM_HEADER, "randomValue");
        return new ResponseEntity<String>("randombody", headers, HttpStatus.OK);
    }
}
