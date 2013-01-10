package org.krzyzak.pre4s.spring;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import org.krzyzak.pre4s.ExceptionHandler;
import org.krzyzak.pre4s.ExceptionHandlerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

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
        Optional<ExceptionHandler<Exception>> matchingExceptionHandler = this.exceptionHandlerResolver.getMatchingExceptionHandler(e.getClass());

        if (matchingExceptionHandler.isPresent()) {
            ResponseEntity<?> responseEntity = matchingExceptionHandler.get().handle(e);
            if (responseEntity == null)
                return null;

            return new ModelAndView(createView(responseEntity), createModel(responseEntity));
        }
        return null;
    }

    private ImmutableMap<String, Object> createModel(ResponseEntity<?> responseEntity) {
        return ImmutableMap.<String, Object>builder().put("model", responseEntity.getBody()).build();
    }

    private MappingJacksonJsonView createView(ResponseEntity<?> responseEntity) {
        MappingJacksonJsonView mappingJacksonJsonView = new ExceptionHandlerResultView(responseEntity);
        mappingJacksonJsonView.setExtractValueFromSingleKeyModel(true);
        return mappingJacksonJsonView;
    }

}
