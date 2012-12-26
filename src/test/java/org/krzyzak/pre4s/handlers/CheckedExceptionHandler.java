package org.krzyzak.pre4s.handlers;

import org.krzyzak.pre4s.RestExceptionHandler;
import org.springframework.http.ResponseEntity;

/**
* Created with IntelliJ IDEA.
* User: tomasz
* Date: 26.12.12
* Time: 22:07
* To change this template use File | Settings | File Templates.
*/
public class CheckedExceptionHandler implements RestExceptionHandler<Exception> {

    @Override
    public ResponseEntity<?> handle(Exception e) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
