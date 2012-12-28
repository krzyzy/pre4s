package org.krzyzak.pre4s.spring;

import org.krzyzak.pre4s.ExceptionHandlerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * User: tomasz
 * Date: 09.12.12
 * Time: 17:55
 * To change this template use File | Settings | File Templates.
 */
@Component
public class Pre4SHandlerExceptionResolver extends AbstractHandlerExceptionResolver {

    private ExceptionHandlerRepository exceptionHandlerResolver;

    @Autowired
    public Pre4SHandlerExceptionResolver(ExceptionHandlerRepository exceptionHandlerResolver) {
        this.exceptionHandlerResolver = exceptionHandlerResolver;
    }

    @Override
    public ModelAndView doResolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        this.exceptionHandlerResolver.getMatchingExceptionHandler(e.getClass());
        return null;
    }
}
