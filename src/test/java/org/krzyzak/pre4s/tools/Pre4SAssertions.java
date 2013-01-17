package org.krzyzak.pre4s.tools;

/**
 * Created with IntelliJ IDEA.
 * User: tk
 * Date: 20.12.12
 * Time: 00:43
 * To change this template use File | Settings | File Templates.
 */
public class Pre4SAssertions {

    public static ExceptionClassDistanceAssert assertThat(ExceptionClassDistance distance) {
        return new ExceptionClassDistanceAssert(distance);
    }
}
