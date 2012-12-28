package org.krzyzak.pre4s.test.handlers;

import org.krzyzak.pre4s.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
* Created with IntelliJ IDEA.
* User: tomasz
* Date: 26.12.12
* Time: 22:07
* To change this template use File | Settings | File Templates.
*/
@Component
public class CheckedExceptionHandler implements ExceptionHandler<Exception> {

    @Override
    public ResponseEntity<?> handle(Exception e) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
