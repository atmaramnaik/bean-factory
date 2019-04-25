package com.atmaram.beanfactory.generators;

import com.atmaram.beanfactory.generators.helpers.ExpressionEvaluator;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static com.atmaram.beanfactory.generators.DateGenerator.aDate;
public class DateGeneratorTest {
    @Test
    public void should_generate_date_from_range_when_provided() throws ParseException {
        String from="2000-01-01";
        String to="2019-10-10";
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date generatedDate=aDate(simpleDateFormat.parse(from),simpleDateFormat.parse(to)).generate();

        assertThat(generatedDate).isBetween(simpleDateFormat.parse(from),simpleDateFormat.parse(to));
    }
    @Test
    public void should_generate_date_from_range_when_no_from_provided() throws ParseException {
        String from="0001-01-01";
        String to="2019-10-10";
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date generatedDate=aDate(simpleDateFormat.parse(to)).generate();

        assertThat(generatedDate).isBetween(simpleDateFormat.parse(from),simpleDateFormat.parse(to));
    }
    @Test
    public void should_generate_date_from_range_when_no_from_and_to_provided() throws ParseException {
        String from="0001-01-01";
        Date to=new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date generatedDate=aDate().generate();

        assertThat(generatedDate).isBetween(simpleDateFormat.parse(from),to);
    }
}
