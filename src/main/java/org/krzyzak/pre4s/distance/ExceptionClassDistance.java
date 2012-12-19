package org.krzyzak.pre4s.distance;

/**
 * Created with IntelliJ IDEA.
 * User: tk
 * Date: 19.12.12
 * Time: 22:14
 * To change this template use File | Settings | File Templates.
 */
public class ExceptionClassDistance implements Comparable<ExceptionClassDistance> {

    public static final ExceptionClassDistance NOT_RELATED = new ExceptionClassDistance(Long.MAX_VALUE) {
    };
    public static final ExceptionClassDistance SAME = new ExceptionClassDistance(0) {
    };

    private long numericDistance;

    private ExceptionClassDistance(long numericDistance) {
        this.numericDistance = numericDistance;
    }

    @Override
    public int compareTo(ExceptionClassDistance o) {
        if (o == NOT_RELATED) {
            if (this == NOT_RELATED) {
                return 0;
            } else {
                return -1;
            }
        }
        return this.numericDistance < o.numericDistance ? -1 : (this.numericDistance == o.numericDistance ? 0 : 1);
    }

    public static ExceptionClassDistance forNumericDistance(long numericDistance) {
        if (numericDistance == 0)
            return SAME;
        return new ExceptionClassDistance(numericDistance);
    }
}