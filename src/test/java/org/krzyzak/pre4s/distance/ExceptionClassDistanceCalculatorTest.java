package org.krzyzak.pre4s.distance;

import org.fest.assertions.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.krzyzak.pre4s.tools.Pre4S;

/**
 * Created with IntelliJ IDEA.
 * User: tk
 * Date: 19.12.12
 * Time: 22:06
 * To change this template use File | Settings | File Templates.
 */
@RunWith(JUnit4.class)
public class ExceptionClassDistanceCalculatorTest {

    private ExceptionClassDistanceCalculator distanceCalculator = new ExceptionClassDistanceCalculator();

    @Test
    public void itShouldReturnInfinityWhenExceptionsAreNotRelated() throws Exception {
        ExceptionClassDistance classDistance = distanceCalculator.calculate(IllegalStateException.class, NullPointerException.class);

        Assertions.assertThat(classDistance).isEqualTo(ExceptionClassDistance.NOT_RELATED);
    }

    @Test
    public void itShouldReturnSameWhenExceptionClassesEquals(){
        Assertions.assertThat(distanceCalculator.calculate(IllegalStateException.class, IllegalStateException.class)).isEqualTo(ExceptionClassDistance.SAME);
    }

    @Test
    public void itShouldReturnBiggerThenSameWhenFirstExceptionIsSuperClassOfSecondOne(){
        Pre4S.assertThat(distanceCalculator.calculate(Exception.class, RuntimeException.class)).isGreaterThanOrEqualTo(ExceptionClassDistance.SAME);
    }

    @Test
    public void itShouldReturn0WhenCompareDistanceBetweenExcetpionAndISEAndNPE(){
        Pre4S.assertThat(distanceCalculator.calculate(Exception.class, IllegalStateException.class)).isEqualByComparingTo(distanceCalculator.calculate(Exception.class, NullPointerException.class));
    }

    @Test
    public void itShouldBeLtZeroWHenCOmpareE_REWith_E_ISE(){
        Pre4S.assertThat(distanceCalculator.calculate(Exception.class, RuntimeException.class)).isLessThan(distanceCalculator.calculate(Exception.class, IllegalStateException.class));
    }

}
