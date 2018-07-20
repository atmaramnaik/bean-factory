package com.atmaram.beanfactory.generators;

import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
public class StringGeneratorTest {
    @Test
    public void withoutRange(){
        StringGenerator stringGenerator=new StringGenerator();
        String val=stringGenerator.generate();
        assertThat(val.length()).isBetween(0,10000);
    }
    @Test
    public void withMaxRange(){
        StringGenerator stringGenerator=new StringGenerator(2);
        String val=stringGenerator.generate();
        assertThat(val.length()).isBetween(0,2);
    }
    @Test
    public void withBothRange(){
        StringGenerator stringGenerator=new StringGenerator(1000,1001);
        String val=stringGenerator.generate();
        assertThat(val.length()).isBetween(1000,1001);
    }
}
