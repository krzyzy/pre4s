package org.krzyzak.pre4s.distance;

import org.fest.assertions.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.krzyzak.pre4s.tools.Pre4SAssertions;

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

    Random random = new Random();



    @Test
    public void itShouldReturnLt0WhenCompareSameWithInfinity() throws Exception {
        Pre4SAssertions.assertThat(ExceptionClassDistance.SAME).isLessThan(ExceptionClassDistance.NOT_RELATED);
    }

    @Test
    public void itShouldReturn0WhenCompareSameWitSame() {
        Pre4SAssertions.assertThat(ExceptionClassDistance.SAME).isEqualByComparingTo(ExceptionClassDistance.SAME);
    }

    @Test
    public void itShouldReturn0WhenCompareInfinityInfinity() {
        Pre4SAssertions.assertThat(ExceptionClassDistance.NOT_RELATED).isEqualByComparingTo(ExceptionClassDistance.NOT_RELATED);
    }

    @Test
    public void itShouldReturnLt0WhenSameCompareWithPositiveDistance() {
        Pre4SAssertions.assertThat(ExceptionClassDistance.SAME).isLessThan(ExceptionClassDistance.forNumericDistance(Math.abs(random.nextLong() + 1)));
    }

    @Test
    public void itShouldReturnLt0WhenComparePositiveDistanceWithInfinity() {
        Pre4SAssertions.assertThat(ExceptionClassDistance.forNumericDistance(Long.MAX_VALUE)).isLessThan(ExceptionClassDistance.NOT_RELATED);
    }

    @Test
    public void itShouldReturnInfinityWhenExceptionsAreNotRelated() throws Exception {
        ExceptionClassDistance classDistance = ExceptionClassDistance.fromClasses(IllegalStateException.class, NullPointerException.class);

        Assertions.assertThat(classDistance).isEqualTo(ExceptionClassDistance.NOT_RELATED);
    }

    @Test
    public void itShouldReturnSameWhenExceptionClassesEquals() {
        Assertions.assertThat(ExceptionClassDistance.fromClasses(IllegalStateException.class, IllegalStateException.class)).isEqualTo(ExceptionClassDistance.SAME);
    }

    @Test
    public void itShouldReturnBiggerThenSameWhenFirstExceptionIsSuperClassOfSecondOne() {
        Pre4SAssertions.assertThat(ExceptionClassDistance.fromClasses(Exception.class, RuntimeException.class)).isGreaterThanOrEqualTo(ExceptionClassDistance.SAME);
    }

    @Test
    public void itShouldReturn0WhenCompareDistanceBetweenExcetpionAndISEAndNPE() {
        Pre4SAssertions.assertThat(ExceptionClassDistance.fromClasses(Exception.class, IllegalStateException.class)).isEqualByComparingTo(ExceptionClassDistance.fromClasses(Exception.class, NullPointerException.class));
    }

    @Test
    public void itShouldBeLtZeroWHenCOmpareE_REWith_E_ISE() {
        Pre4SAssertions.assertThat(ExceptionClassDistance.fromClasses(Exception.class, RuntimeException.class)).isLessThan(ExceptionClassDistance.fromClasses(Exception.class, IllegalStateException.class));
    }

}
