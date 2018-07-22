package com.atmaram.beanfactory.generators;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class DateExpressionGeneratorTest {
    @Test
    public void should_generate_constant_date_value() throws ParseException {
        String dateString="2019-10-10";
        DateExpressionGenerator dateExpressionGenerator=new DateExpressionGenerator("yyyy-MM-dd",dateString);
        assertThat(dateExpressionGenerator.generate()).isInstanceOf(Date.class);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        assertThat(dateExpressionGenerator.generate()).isEqualTo(simpleDateFormat.parse(dateString));
    }
}
