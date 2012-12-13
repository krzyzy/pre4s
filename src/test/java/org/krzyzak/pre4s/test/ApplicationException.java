package org.krzyzak.pre4s.test;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created with IntelliJ IDEA.
 * User: tomasz
 * Date: 13.12.12
 * Time: 00:29
 * To change this template use File | Settings | File Templates.
 */
public class ApplicationException extends Exception {
    public ApplicationException(String s) {
        super(s);
    }
}
