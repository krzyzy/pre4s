package org.krzyzak.pre4s;

import org.springframework.http.ResponseEntity;

/**
 * Created with IntelliJ IDEA.
 * User: tomasz
 * Date: 09.12.12
 * Time: 18:07
 * To change this template use File | Settings | File Templates.
 */
public interface ExceptionHandler<T extends Throwable> {

    ResponseEntity<?> handle(T e);

}
