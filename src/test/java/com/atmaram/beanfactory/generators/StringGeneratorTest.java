package com.atmaram.beanfactory.generators;

import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import static com.atmaram.beanfactory.generators.StringGenerator.aString;
public class StringGeneratorTest {
    @Test
    public void withoutRange(){
        String val=aString().generate();
        assertThat(val.length()).isBetween(0,10);
    }
    @Test
    public void withMaxRange(){
        String val=aString(2).generate();
        assertThat(val.length()).isBetween(0,2);
    }
    @Test
    public void withBothRange(){
        String val=aString(1000,1001).generate();
        assertThat(val.length()).isBetween(1000,1001);
    }
}
