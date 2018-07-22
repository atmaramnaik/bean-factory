package com.atmaram.beanfactory.generators;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BooleanGeneratorTest {
    @Test
    public void should_generate_boolean_value(){
        BooleanGenerator booleanGenerator=new BooleanGenerator();
        assertThat(booleanGenerator.generate()).isInstanceOf(Boolean.class);
    }
}
