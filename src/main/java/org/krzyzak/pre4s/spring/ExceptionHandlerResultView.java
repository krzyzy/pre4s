package org.krzyzak.pre4s.spring;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: tk
 * Date: 11.01.13
 * Time: 00:16
 * To change this template use File | Settings | File Templates.
 */
class ExceptionHandlerResultView extends MappingJacksonJsonView {

    private ResponseEntity<?> responseEntity;

    public ExceptionHandlerResultView(ResponseEntity<?> responseEntity) {
        this.responseEntity = responseEntity;
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setStatus(responseEntity.getStatusCode().value());
        for (String headerName : responseEntity.getHeaders().keySet()) {
            List<String> strings = responseEntity.getHeaders().get(headerName);
            if (strings.size() > 0)
                response.setHeader(headerName, strings.get(0));
        }
        super.renderMergedOutputModel(model, request, response);
    }
}
