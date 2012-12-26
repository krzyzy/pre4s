package org.krzyzak.pre4s.handlers;

import org.krzyzak.pre4s.RestExceptionHandler;
import org.springframework.http.ResponseEntity;

/**
 * Created with IntelliJ IDEA.
 * User: tomasz
 * Date: 26.12.12
 * Time: 22:19
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractExceptionHandler<T extends Throwable> implements RestExceptionHandler<T> {
    @Override
    public ResponseEntity<?> handle(T e) {
        return null;
    }
}
