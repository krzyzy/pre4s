package org.krzyzak.pre4s.distance;

import org.fest.assertions.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.krzyzak.pre4s.tools.KrzyzakAssertions;

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
        KrzyzakAssertions.assertThat(ExceptionClassDistance.SAME).isLessThan(ExceptionClassDistance.NOT_RELATED);
    }

    @Test
    public void itShouldReturn0WhenCompareSameWitSame() {
        KrzyzakAssertions.assertThat(ExceptionClassDistance.SAME).isEqualByComparingTo(ExceptionClassDistance.SAME);
    }

    @Test
    public void itShouldReturn0WhenCompareInfinityInfinity(){
        KrzyzakAssertions.assertThat(ExceptionClassDistance.NOT_RELATED).isEqualByComparingTo(ExceptionClassDistance.NOT_RELATED);
    }

    @Test
    public void itShouldReturnLt0WhenSameCompareWithPositiveDistance(){
        KrzyzakAssertions.assertThat(ExceptionClassDistance.SAME).isLessThan(ExceptionClassDistance.forNumericDistance(Math.abs(random.nextLong() + 1)));
    }

    @Test
    public void itShouldReturnLt0WhenComparePositiveDistanceWithInfinity(){
        KrzyzakAssertions.assertThat(ExceptionClassDistance.forNumericDistance(Long.MAX_VALUE)).isLessThan(ExceptionClassDistance.NOT_RELATED);
    }
}
