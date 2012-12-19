package org.krzyzak.pre4s.tools;

import org.fest.assertions.ComparableAssert;
import org.krzyzak.pre4s.distance.ExceptionClassDistance;

/**
 * Created with IntelliJ IDEA.
 * User: tk
 * Date: 20.12.12
 * Time: 00:37
 * To change this template use File | Settings | File Templates.
 */
public class ExceptionClassDistanceAssert extends ComparableAssert<ExceptionClassDistanceAssert, ExceptionClassDistance> {

    public ExceptionClassDistanceAssert(ExceptionClassDistance distance) {
        super(ExceptionClassDistanceAssert.class, distance);
    }
}
