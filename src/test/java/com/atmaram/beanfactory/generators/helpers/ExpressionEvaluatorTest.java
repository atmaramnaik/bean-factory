package com.atmaram.beanfactory.generators.helpers;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class ExpressionEvaluatorTest {
    @Test
    public void should_provide_specific_date_between_range() throws ParseException {
        String from="2000-01-01";
        String to="2019-10-10";
        Date evaluatedDate=ExpressionEvaluator.getDateVal("yyyy-MM-dd","{"+from+","+to+"}");
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        assertThat(evaluatedDate).isBetween(simpleDateFormat.parse(from),simpleDateFormat.parse(to));
    }
    @Test
    public void should_provide_constant_date() throws ParseException {
        String cDate="2000-01-01";
        Date evaluatedDate=ExpressionEvaluator.getDateVal("yyyy-MM-dd",cDate);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        assertThat(evaluatedDate).isEqualTo(simpleDateFormat.parse(cDate));
    }
    @Test
    public void should_provide_specific_date_from_set() throws ParseException {
        String s1="2000-10-10";
        String s2="2001-10-10";
        String s3="2002-10-10";
        Date evaluatedDate=ExpressionEvaluator.getDateVal("yyyy-MM-dd","["+s1+","+s2+","+s3+"]");
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        assertThat(evaluatedDate).isIn(Arrays.asList(simpleDateFormat.parse(s1),simpleDateFormat.parse(s2),simpleDateFormat.parse(s3)));
    }

    @Test
    public void should_replace_hashes_with_numbers() {
        String start="JHKJHJKHJK";
        String s1=start+"###";
        String evaluatedString=ExpressionEvaluator.getVal(s1);
        List<String> expected=new ArrayList<>();
        for (int i=0;i<10;i++){
            expected.add(Integer.toString(i));
        }
        assertThat(evaluatedString.substring(0,evaluatedString.length()-3)).isEqualTo(start);
        assertThat(evaluatedString.substring(evaluatedString.length()-1,evaluatedString.length())).isIn(expected);
        assertThat(evaluatedString.substring(evaluatedString.length()-2,evaluatedString.length()-1)).isIn(expected);
        assertThat(evaluatedString.substring(evaluatedString.length()-3,evaluatedString.length()-2)).isIn(expected);
    }
    @Test
    public void should_provide_specific_string_from_set() {
        String s1="String1";
        String s2="String2";
        String s3="String3";
        String evaluatedString=ExpressionEvaluator.getVal("["+s1+","+s2+","+s3+"]");
        assertThat(evaluatedString).isIn(Arrays.asList(s1,s2,s3));
    }
    
}
