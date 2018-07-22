package com.atmaram.beanfactory.generators.custom;

import com.atmaram.beanfactory.generators.StringGenerator;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NameGeneratorTest {
    @Test
    public void should_generate_names_with_specific_words_and_lengths_when_no_limits_for_words_and_length_defined(){
        NameGenerator nameGenerator=new NameGenerator();
        String val=nameGenerator.generate();
        String[] words=val.split(" ");
        assertThat(words.length).isBetween(1,3);
        for (String word:
             words) {
            assertThat(word.length()).isBetween(5,15);
        }
    }
    @Test
    public void should_generate_names_with_specific_words_and_lengths_when_max_limits_for_words_and_length_defined(){
        NameGenerator nameGenerator=new NameGenerator(10,12);
        String val=nameGenerator.generate();
        String[] words=val.split(" ");
        assertThat(words.length).isBetween(1,10);
        for (String word:
                words) {
            assertThat(word.length()).isBetween(5,12);
        }
    }
    @Test
    public void should_generate_names_with_specific_words_and_lengths_when_both_limots_for_words_and_length_defined(){
        NameGenerator nameGenerator=new NameGenerator(9,10,11,12);
        String val=nameGenerator.generate();
        String[] words=val.split(" ");
        assertThat(words.length).isBetween(9,10);
        for (String word:
                words) {
            assertThat(word.length()).isBetween(11,12);
        }
    }
}
