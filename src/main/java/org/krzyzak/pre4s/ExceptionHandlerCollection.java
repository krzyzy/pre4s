package org.krzyzak.pre4s;

import com.google.common.base.Optional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tk
 * Date: 21.12.12
 * Time: 00:25
 * To change this template use File | Settings | File Templates.
 */
public class ExceptionHandlerCollection {

    public ExceptionHandlerCollection(List<RestExceptionHandler<?>> restExceptionHandlers) {

    }

    public <T extends Throwable> Optional<RestExceptionHandler<T>> getMatchingExceptionHandler(Class<T> exceptionClass) {
        return Optional.absent();
    }
}
