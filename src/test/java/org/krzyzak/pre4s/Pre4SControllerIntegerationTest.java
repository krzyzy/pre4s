package org.krzyzak.pre4s;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.krzyzak.pre4s.controller.Pre4STestController;
import org.krzyzak.pre4s.spring.Pre4SHandlerExceptionResolver;
import org.krzyzak.pre4s.test.Pre4STestConfig;
import org.springframework.beans.factory.annotation.Autowired;
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

/**
 * Created with IntelliJ IDEA.
 * User: tomasz
 * Date: 09.12.12
 * Time: 20:00
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Pre4STestConfig.class}, loader = AnnotationConfigContextLoader.class)
public class Pre4SControllerIntegerationTest {

    @Autowired
    private Pre4STestController pre4STestController;

    @Autowired
    private Pre4SHandlerExceptionResolver pre4SHandlerExceptionResolver;

    protected MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        StandaloneMockMvcBuilder standaloneMockMvcBuilder = MockMvcBuilders.standaloneSetup(pre4STestController);
        standaloneMockMvcBuilder.setHandlerExceptionResolvers(Arrays.<HandlerExceptionResolver>asList(pre4SHandlerExceptionResolver));
        mockMvc = standaloneMockMvcBuilder.build();

    }

    @Test
    public void test() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/pre4s/test?fail=true").header("Accept", "application/json")).andReturn();
        MockHttpServletResponse response = result.getResponse();
        Assert.assertEquals(200, response.getStatus());
    }
}
