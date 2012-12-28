package org.krzyzak.pre4s.tools;

import org.krzyzak.pre4s.RestExceptionHandler;

import java.lang.reflect.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: tomasz
 * Date: 26.12.12
 * Time: 22:00
 * To change this template use File | Settings | File Templates.
 */
public class TypeHelper {

    private static Logger log = Logger.getLogger("TypeHelper");

    private static final int VALIDATOR_TYPE_INDEX = 0;

    public static Type extractType(Class<? extends RestExceptionHandler<?>> validator) {
        Map<Type, Type> resolvedTypes = new HashMap<Type, Type>();
        Type constraintValidatorType = resolveTypes(resolvedTypes, validator);

        //we now have all bind from a type to its resolution at one level
        Type validatorType = ((ParameterizedType) constraintValidatorType).getActualTypeArguments()[VALIDATOR_TYPE_INDEX];
        if (validatorType == null) {
            throw new RuntimeException("No type parameter");
        } else if (validatorType instanceof GenericArrayType) {
            validatorType = TypeHelper.getArrayType(TypeHelper.getComponentType(validatorType));
        }

        while (resolvedTypes.containsKey(validatorType)) {
            validatorType = resolvedTypes.get(validatorType);
        }
        //FIXME raise an exception if validatorType is not a class
        return validatorType;
    }

    private static Type resolveTypes(Map<Type, Type> resolvedTypes, Type type) {
        if (type == null) {
            return null;
        } else if (type instanceof Class) {
            Class<?> clazz = (Class<?>) type;
            final Type returnedType = resolveTypeForClassAndHierarchy(resolvedTypes, clazz);
            if (returnedType != null) {
                return returnedType;
            }
        } else if (type instanceof ParameterizedType) {
            ParameterizedType paramType = (ParameterizedType) type;
            if (!(paramType.getRawType() instanceof Class)) {
                return null; //don't know what to do here
            }
            Class<?> rawType = (Class<?>) paramType.getRawType();

            TypeVariable<?>[] originalTypes = rawType.getTypeParameters();
            Type[] partiallyResolvedTypes = paramType.getActualTypeArguments();
            int nbrOfParams = originalTypes.length;
            for (int i = 0; i < nbrOfParams; i++) {
                resolvedTypes.put(originalTypes[i], partiallyResolvedTypes[i]);
            }

            if (rawType.equals(RestExceptionHandler.class)) {
                //we found our baby
                return type;
            } else {
                Type returnedType = resolveTypeForClassAndHierarchy(resolvedTypes, rawType);
                if (returnedType != null) {
                    return returnedType;
                }
            }
        }
        //else we don't care I think
        return null;
    }

    public static Type getComponentType(Type type) {
        if (type instanceof Class<?>) {
            Class<?> klass = (Class<?>) type;

            return klass.isArray() ? klass.getComponentType() : null;
        }

        if (type instanceof GenericArrayType) {
            return ((GenericArrayType) type).getGenericComponentType();
        }

        return null;
    }

    public static Type getArrayType(Type componentType) {
        if (componentType instanceof Class<?>) {
            return Array.newInstance((Class<?>) componentType, 0).getClass();
        }

        return genericArrayType(componentType);
    }

    public static GenericArrayType genericArrayType(final Type componentType) {
        return new GenericArrayType() {

            public Type getGenericComponentType() {
                return componentType;
            }
        };
    }

    private static Type resolveTypeForClassAndHierarchy(Map<Type, Type> resolvedTypes, Class<?> clazz) {
        Type returnedType = resolveTypes(resolvedTypes, clazz.getGenericSuperclass());
        if (returnedType != null) {
            return returnedType;
        }
        for (Type genericInterface : clazz.getGenericInterfaces()) {
            returnedType = resolveTypes(resolvedTypes, genericInterface);
            if (returnedType != null) {
                return returnedType;
            }
        }
        return null;
    }

}
