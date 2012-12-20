package org.krzyzak.pre4s.distance;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.krzyzak.pre4s.tools.Pre4S;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: tk
 * Date: 19.12.12
 * Time: 22:30
 * To change this template use File | Settings | File Templates.
 */
@RunWith(JUnit4.class)
public class ExceptionClassDistanceTest {

    Random random  = new Random();

    @Test
    public void itShouldReturnLt0WhenCompareSameWithInfinity() throws Exception {
        Pre4S.assertThat(ExceptionClassDistance.SAME).isLessThan(ExceptionClassDistance.NOT_RELATED);
    }

    @Test
    public void itShouldReturn0WhenCompareSameWitSame() {
        Pre4S.assertThat(ExceptionClassDistance.SAME).isEqualByComparingTo(ExceptionClassDistance.SAME);
    }

    @Test
    public void itShouldReturn0WhenCompareInfinityInfinity(){
        Pre4S.assertThat(ExceptionClassDistance.NOT_RELATED).isEqualByComparingTo(ExceptionClassDistance.NOT_RELATED);
    }

    @Test
    public void itShouldReturnLt0WhenSameCompareWithPositiveDistance(){
        Pre4S.assertThat(ExceptionClassDistance.SAME).isLessThan(ExceptionClassDistance.forNumericDistance(Math.abs(random.nextLong() + 1)));
    }

    @Test
    public void itShouldReturnLt0WhenComparePositiveDistanceWithInfinity(){
        Pre4S.assertThat(ExceptionClassDistance.forNumericDistance(Long.MAX_VALUE)).isLessThan(ExceptionClassDistance.NOT_RELATED);
    }
}
