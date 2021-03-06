package com.atmaram.beanfactory.generators;

import org.junit.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static com.atmaram.beanfactory.generators.SetGenerator.aSet;
import static com.atmaram.beanfactory.generators.StringGenerator.aString;
public class SetGeneratorTest {
    @Test
    public void should_return_empty_set_when_no_member_generator_defined(){
        SetGenerator<String> setGenerator=new SetGenerator<>(10,20);
        Set<String> set= setGenerator.generate();
        assertThat(set).isInstanceOf(Set.class);
        assertThat(set.size()).isEqualTo(0);

    }

    @Test
    public void should_return_set_with_certain_no_of_entries_when_no_range_defined(){
        Set<String> set= aSet(aString()).generate();
        assertThat(set).isInstanceOf(Set.class);
        assertThat(set.size()).isBetween(0,10);
        if(set.size()>0){
            for (String element:
                 set) {
                assertThat(element).isInstanceOf(String.class);
            }
        }
    }
    @Test
    public void should_return_set_with_certain_no_of_entries_when_max_range_defined(){
        Set<String> set= aSet(5,aString()).generate();
        assertThat(set).isInstanceOf(Set.class);
        assertThat(set.size()).isBetween(0,5);
        if(set.size()>0){
            for (String element:
                    set) {
                assertThat(element).isInstanceOf(String.class);
            }
        }

    }
    @Test
    public void should_return_set_with_certain_no_of_entries_when_both_range_defined(){
        Set<String> set= aSet(4,5,aString()).generate();
        assertThat(set).isInstanceOf(Set.class);
        assertThat(set.size()).isBetween(4,5);
        if(set.size()>0){
            for (String element:
                    set) {
                assertThat(element).isInstanceOf(String.class);
            }
        }

    }
}
