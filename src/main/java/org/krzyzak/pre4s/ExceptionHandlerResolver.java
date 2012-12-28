package org.krzyzak.pre4s;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.collect.Maps;
import org.krzyzak.pre4s.distance.ExceptionClassDistance;
import org.krzyzak.pre4s.tools.TypeHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: tk
 * Date: 21.12.12
 * Time: 00:25
 * To change this template use File | Settings | File Templates.
 */
public class ExceptionHandlerResolver {

    private final Map<Class<?extends Throwable>, ? extends ExceptionHandler<?>> handlersMap;

    public ExceptionHandlerResolver(List<? extends ExceptionHandler<?>> restExceptionHandlers) {
        handlersMap =   new HashMap<Class<? extends Throwable>, ExceptionHandler<?>>(Maps.uniqueIndex(restExceptionHandlers, new Function<ExceptionHandler<?>, Class<? extends Throwable>>() {
            @Override
            public Class<? extends Throwable> apply(ExceptionHandler<?> input) {
                Class<? extends ExceptionHandler> aClass = input.getClass();
                return (Class<? extends Throwable>) TypeHelper.extractType(aClass);
            }
        }));
    }

    public <T extends Throwable> Optional<ExceptionHandler<T>> getMatchingExceptionHandler(Class<T> exceptionClass) {
        ExceptionClassDistance closestDistance = ExceptionClassDistance.NOT_RELATED;
        ExceptionHandler<T> result = null;

        for (Map.Entry<Class<? extends Throwable>, ? extends ExceptionHandler<?>> entry : handlersMap .entrySet()) {
            ExceptionClassDistance distance = ExceptionClassDistance.fromClasses(entry.getKey(), exceptionClass);
            if (distance.compareTo(closestDistance)<0) {
                closestDistance = distance;
                result = (ExceptionHandler<T>) entry.getValue();
            }
        }

        return Optional.fromNullable(result);
    }
}
