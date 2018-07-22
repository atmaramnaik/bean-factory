package com.atmaram.beanfactory.generators;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DoubleGeneratorTest {
    @Test
    public void withoutRange(){
        DoubleGenerator doubleGenerator=new DoubleGenerator();
        double val=doubleGenerator.generate();
        assertThat(val).isBetween(0.0,10000.0);
    }
    @Test
    public void withMaxRange(){
        DoubleGenerator doubleGenerator=new DoubleGenerator(200.19);
        double val=doubleGenerator.generate();
        assertThat(val).isBetween(0.0,200.19);
    }
    @Test
    public void withBothRange(){
        DoubleGenerator doubleGenerator=new DoubleGenerator(200.18,200.19);
        double val=doubleGenerator.generate();
        assertThat(val).isBetween(200.18,200.19);
    }
}
