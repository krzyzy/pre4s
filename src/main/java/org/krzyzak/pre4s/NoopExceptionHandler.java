package org.krzyzak.pre4s;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: tomasz
 * Date: 13.12.12
 * Time: 00:33
 * To change this template use File | Settings | File Templates.
 */
@Component
public class NoopExceptionHandler implements RestExceptionHandler<Exception> {
    @Override
    public ResponseEntity<?> handle(Exception e) {
        return null;
    }
}
