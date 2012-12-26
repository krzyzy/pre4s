package org.krzyzak.pre4s.tools;

import org.fest.assertions.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.krzyzak.pre4s.handlers.IAEExceptionHandler;
import org.krzyzak.pre4s.handlers.ISEExceptionHandler;
import org.krzyzak.pre4s.handlers.SubRestExceptionHandler;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: tomasz
 * Date: 26.12.12
 * Time: 22:09
 * To change this template use File | Settings | File Templates.
 */
@RunWith(JUnit4.class)
public class TypeHelperTest {

    @Test
    public void itShouldRecognizeTypeOfDirectImplementation() {
        assertThat(TypeHelper.extractType(IAEExceptionHandler.class)).isEqualTo(IllegalArgumentException.class);
        assertThat(TypeHelper.extractType(ISEExceptionHandler.class)).isEqualTo(IllegalStateException.class);
    }

    @Test
    public void itShouldRecognizeTypeOfAbstractSuperClass(){
        assertThat(TypeHelper.extractType(SubRestExceptionHandler.class)).isEqualTo(RuntimeException.class);
    }


}
