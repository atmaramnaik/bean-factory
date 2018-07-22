package com.atmaram.beanfactory.generators;

import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
public class ConstantGeneratorTest {
    @Test
    public void should_generate_constant_value(){
        ConstantGenerator<Integer> constantGenerator=new ConstantGenerator<>(10);
        int const1=constantGenerator.generate();
        int const2=constantGenerator.generate();
        assertThat(const1).isEqualTo(10);
        assertThat(const2).isEqualTo(10);
    }
}
