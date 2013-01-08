package org.krzyzak.pre4s.test.handlers;

import org.krzyzak.pre4s.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
* Created with IntelliJ IDEA.
* User: tomasz
* Date: 26.12.12
* Time: 22:08
* To change this template use File | Settings | File Templates.
*/
@Component
public class IAEExceptionHandler implements ExceptionHandler<IllegalArgumentException> {

    @Override
    public ResponseEntity<?> handle(IllegalArgumentException e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
    }
}
