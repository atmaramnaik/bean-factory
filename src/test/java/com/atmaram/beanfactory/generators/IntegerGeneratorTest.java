package com.atmaram.beanfactory.generators;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
public class IntegerGeneratorTest {
    @Test
    public void withoutRange(){
        IntegerGenerator integerGenerator=new IntegerGenerator();
        int val=integerGenerator.generate();
        assertThat(val).isBetween(0,10000);
    }
    @Test
    public void withMaxRange(){
        IntegerGenerator integerGenerator=new IntegerGenerator(2);
        int val=integerGenerator.generate();
        assertThat(val).isBetween(0,2);
    }
    @Test
    public void withBothRange(){
        IntegerGenerator integerGenerator=new IntegerGenerator(1000,1001);
        int val=integerGenerator.generate();
        assertThat(val).isBetween(1000,1001);
    }
}
