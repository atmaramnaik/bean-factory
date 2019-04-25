package com.atmaram.beanfactory.generators;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static com.atmaram.beanfactory.generators.OneFromSetGenerator.oneOf;
public class OneFromSetGeneratorTest {
    @Test
    public void should_return_any_one_element_from_set(){
        Set<String> sourceSet=new HashSet<>();
        sourceSet.add("String1");
        sourceSet.add("String2");
        sourceSet.add("String3");
        String generatedString=oneOf("String1","String2","String3").generate();
        assertThat(generatedString).isIn(sourceSet);

    }

}
