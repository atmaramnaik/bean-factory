package com.atmaram.beanfactory.generators;

import org.junit.Test;

import java.util.List;
import static org.assertj.core.api.Assertions.*;

public class ListGeneratorTest {
    @Test
    public void should_return_empty_list_when_no_member_generator_defined(){
        ListGenerator<String> listGenerator=new ListGenerator(10,20);
        List<String> lst= listGenerator.generate();
        assertThat(lst).isInstanceOf(List.class);
        assertThat(lst.size()).isEqualTo(0);

    }

    @Test
    public void should_return_list_with_certain_no_of_entries_when_no_range_defined(){
        ListGenerator<String> listGenerator=new ListGenerator(()->"Hello");
        List<String> lst= listGenerator.generate();
        assertThat(lst).isInstanceOf(List.class);
        assertThat(lst.size()).isBetween(0,10);
        if(lst.size()>0){
            for (String element:
                 lst) {
                assertThat(element).isEqualTo("Hello");

            }
        }

    }
    @Test
    public void should_return_list_with_certain_no_of_entries_when_max_range_defined(){
        ListGenerator<String> listGenerator=new ListGenerator(5,()->"Hello");
        List<String> lst= listGenerator.generate();
        assertThat(lst).isInstanceOf(List.class);
        assertThat(lst.size()).isBetween(0,5);
        if(lst.size()>0){
            for (String element:
                    lst) {
                assertThat(element).isEqualTo("Hello");

            }
        }

    }
    @Test
    public void should_return_list_with_certain_no_of_entries_when_both_range_defined(){
        ListGenerator<String> listGenerator= new ListGenerator<>(4, 5, () -> "Hello");
        List<String> lst= listGenerator.generate();
        assertThat(lst).isInstanceOf(List.class);
        assertThat(lst.size()).isBetween(4,5);
        if(lst.size()>0){
            for (String element:
                    lst) {
                assertThat(element).isEqualTo("Hello");

            }
        }

    }
}
