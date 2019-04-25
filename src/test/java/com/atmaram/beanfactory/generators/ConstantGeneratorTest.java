package com.atmaram.beanfactory.generators;

import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import static com.atmaram.beanfactory.generators.ConstantGenerator.aConstant;
public class ConstantGeneratorTest {
    @Test
    public void should_generate_constant_value(){
        ConstantGenerator<Integer> constantGenerator=aConstant(10);
        int const1=constantGenerator.generate();
        int const2=constantGenerator.generate();
        assertThat(const1).isEqualTo(10);
        assertThat(const2).isEqualTo(10);
    }
}
