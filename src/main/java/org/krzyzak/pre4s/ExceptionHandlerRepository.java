package org.krzyzak.pre4s;

import com.google.common.base.Optional;
import org.krzyzak.pre4s.tools.ExceptionClassDistance;
import org.krzyzak.pre4s.tools.TypeHelper;

import java.lang.reflect.Type;
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
public class ExceptionHandlerRepository {

    private final Map<Type, ExceptionHandler<?>> handlersMap = new HashMap<Type, ExceptionHandler<?>>();

    public ExceptionHandlerRepository(List<? extends ExceptionHandler<?>> restExceptionHandlers) {
        for (ExceptionHandler<?> eh : restExceptionHandlers) {
            handlersMap.put(TypeHelper.extractType(eh.getClass()), eh);
        }
    }

    public <T extends Throwable> Optional<ExceptionHandler<T>> getMatchingExceptionHandler(Class<? extends T> exceptionClass) {
        ExceptionClassDistance closestDistance = ExceptionClassDistance.NOT_RELATED;
        ExceptionHandler<T> result = null;

        for (Map.Entry<Type, ExceptionHandler<?>> entry : handlersMap .entrySet()) {
            ExceptionClassDistance distance = ExceptionClassDistance.fromClasses((Class<?>) entry.getKey(), exceptionClass);
            if (distance.compareTo(closestDistance)<0) {
                closestDistance = distance;
                result = (ExceptionHandler<T>) entry.getValue();
            }
        }

        return Optional.fromNullable(result);
    }
}
