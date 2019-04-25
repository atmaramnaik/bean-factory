package com.atmaram.beanfactory.generators;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static com.atmaram.beanfactory.generators.LongGenerator.aLong;
public class LongGeneratorTest {
    @Test
    public void withoutRange(){
        long val=aLong().generate();
        assertThat(val).isBetween(0L,10000L);
    }
    @Test
    public void withMaxRange(){
        long val=aLong(2).generate();
        assertThat(val).isBetween(0L,2L);
    }
    @Test
    public void withBothRange(){
        long val=aLong(1000L,1001L).generate();
        assertThat(val).isBetween(1000L,1001L);
    }
}
