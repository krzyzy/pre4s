package org.krzyzak.pre4s.distance;

/**
 * Created with IntelliJ IDEA.
 * User: tk
 * Date: 19.12.12
 * Time: 22:11
 * To change this template use File | Settings | File Templates.
 */
public class ExceptionClassDistanceCalculator {

    public ExceptionClassDistance calculate(Class<?> superClass, Class<?> subClass) {
        if (superClass.equals(subClass)) {
            return ExceptionClassDistance.SAME;
        } else if (superClass.isAssignableFrom(subClass)) {
            Class<?> iteratorClass = subClass;
            int distance = 0;
            while (!superClass.equals(iteratorClass)) {
                iteratorClass = iteratorClass.getSuperclass();
                distance++;
            }
            return ExceptionClassDistance.forNumericDistance(distance);
        }  else if (subClass.isAssignableFrom(superClass)) {

        }
        return ExceptionClassDistance.NOT_RELATED;
    }
}
