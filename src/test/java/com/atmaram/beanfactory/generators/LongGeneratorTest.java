package com.atmaram.beanfactory.generators;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LongGeneratorTest {
    @Test
    public void withoutRange(){
        LongGenerator longGenerator=new LongGenerator();
        long val=longGenerator.generate();
        assertThat(val).isBetween(0L,10000L);
    }
    @Test
    public void withMaxRange(){
        LongGenerator longGenerator=new LongGenerator(2);
        long val=longGenerator.generate();
        assertThat(val).isBetween(0L,2L);
    }
    @Test
    public void withBothRange(){
        LongGenerator longGenerator=new LongGenerator(1000L,1001L);
        long val=longGenerator.generate();
        assertThat(val).isBetween(1000L,1001L);
    }
}
