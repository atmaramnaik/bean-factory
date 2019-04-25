package com.atmaram.beanfactory.generators;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static com.atmaram.beanfactory.generators.DoubleGenerator.aDouble;
public class DoubleGeneratorTest {
    @Test
    public void withoutRange(){
        double val=aDouble().generate();
        assertThat(val).isBetween(0.0,10000.0);
    }
    @Test
    public void withMaxRange(){
        double val=aDouble(200.19).generate();
        assertThat(val).isBetween(0.0,200.19);
    }
    @Test
    public void withBothRange(){
        double val=aDouble(200.18,200.19).generate();
        assertThat(val).isBetween(200.18,200.19);
    }
}
