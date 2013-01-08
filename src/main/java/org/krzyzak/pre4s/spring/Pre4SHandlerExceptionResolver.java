package org.krzyzak.pre4s.spring;

import com.google.common.base.Optional;
import com.google.common.net.HttpHeaders;
import org.krzyzak.pre4s.ExceptionHandler;
import org.krzyzak.pre4s.ExceptionHandlerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

            httpServletResponse.setStatus(responseEntity.getStatusCode().value());
            for (String headerName : responseEntity.getHeaders().keySet()) {
                List<String> strings = responseEntity.getHeaders().get(headerName);
                if (strings.size() > 0)
                    httpServletResponse.setHeader(headerName, strings.get(0));
            }

            MappingJacksonJsonView mappingJacksonJsonView = new MappingJacksonJsonView();
            mappingJacksonJsonView.setExtractValueFromSingleKeyModel(true);
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("model", responseEntity.getBody());
            return new ModelAndView(mappingJacksonJsonView, model);
        }
        return null;
    }

    private MediaType extractMediaType(HttpServletRequest httpServletRequest,MediaType defaultMimeType) {
        Enumeration headers = httpServletRequest.getHeaders(HttpHeaders.ACCEPT);
        if (headers!=null && headers.hasMoreElements()){
            defaultMimeType = MediaType.parseMediaType((String) headers.nextElement());
        }
        return defaultMimeType;
    }
}
