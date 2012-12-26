package org.krzyzak.pre4s;

import org.krzyzak.pre4s.tools.TypeHelper;

/**
 * Created with IntelliJ IDEA.
 * User: tk
 * Date: 21.12.12
 * Time: 15:25
 * To change this template use File | Settings | File Templates.
 */
public class GenericsUtil {

    public Class<? extends Throwable> getActualTypeParamters(RestExceptionHandler<?> restExceptionHandler) {
        return (Class<? extends Throwable>) TypeHelper.extractType((Class<? extends RestExceptionHandler<?>>) restExceptionHandler.getClass());
    }

}
