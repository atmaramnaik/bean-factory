package com.atmaram.beanfactory.generators;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import static com.atmaram.beanfactory.generators.IntegerGenerator.aInteger;
public class IntegerGeneratorTest {
    @Test
    public void withoutRange(){
        int val=aInteger().generate();
        assertThat(val).isBetween(0,10000);
    }
    @Test
    public void withMaxRange(){
        int val=aInteger(2).generate();
        assertThat(val).isBetween(0,2);
    }
    @Test
    public void withBothRange(){
        int val=aInteger(1000,1001).generate();
        assertThat(val).isBetween(1000,1001);
    }
}
