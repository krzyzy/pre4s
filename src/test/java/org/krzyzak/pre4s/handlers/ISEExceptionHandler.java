package org.krzyzak.pre4s.handlers;

import org.krzyzak.pre4s.RestExceptionHandler;
import org.springframework.http.ResponseEntity;

/**
* Created with IntelliJ IDEA.
* User: tomasz
* Date: 26.12.12
* Time: 22:08
* To change this template use File | Settings | File Templates.
*/
public class ISEExceptionHandler implements RestExceptionHandler<IllegalStateException> {

    @Override
    public ResponseEntity<?> handle(IllegalStateException e) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
