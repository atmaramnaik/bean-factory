package com.atmaram.beanfactory.generators;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static com.atmaram.beanfactory.generators.FloatGenerator.aFloat;
public class FloatGeneratorTest {
    @Test
    public void withoutRange(){
        float val=aFloat().generate();
        assertThat(val).isBetween(0.0F,10000.0F);
    }
    @Test
    public void withMaxRange(){
        float val=aFloat(200.19F).generate();
        assertThat(val).isBetween(0.0F,200.19F);
    }
    @Test
    public void withBothRange(){
        float val=aFloat(200.18F,200.19F).generate();
        assertThat(val).isBetween(200.18F,200.19F);
    }
}
