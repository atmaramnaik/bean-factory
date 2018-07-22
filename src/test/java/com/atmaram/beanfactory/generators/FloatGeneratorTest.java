package com.atmaram.beanfactory.generators;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FloatGeneratorTest {
    @Test
    public void withoutRange(){
        FloatGenerator floatGenerator=new FloatGenerator();
        float val=floatGenerator.generate();
        assertThat(val).isBetween(0.0F,10000.0F);
    }
    @Test
    public void withMaxRange(){
        FloatGenerator floatGenerator=new FloatGenerator(200.19F);
        float val=floatGenerator.generate();
        assertThat(val).isBetween(0.0F,200.19F);
    }
    @Test
    public void withBothRange(){
        FloatGenerator floatGenerator=new FloatGenerator(200.18F,200.19F);
        float val=floatGenerator.generate();
        assertThat(val).isBetween(200.18F,200.19F);
    }
}
