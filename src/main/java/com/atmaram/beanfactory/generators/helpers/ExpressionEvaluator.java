package com.atmaram.beanfactory.generators.helpers;

import com.atmaram.beanfactory.generators.DateGenerator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ExpressionEvaluator {
    public static Date getDateVal(String format,String pattern) throws ParseException {
        SimpleDateFormat formatter=new SimpleDateFormat(format);
        if(pattern.startsWith("{") && pattern.endsWith("}")){
            String newpat=pattern.substring(1,pattern.length()-1);
            String start=newpat.split(",")[0];
            String end=newpat.split(",")[1];


            Date sDate= null;
            Date eDate=null;
            sDate = formatter.parse(start);
            eDate=formatter.parse(end);

            DateGenerator dateGenerator=new DateGenerator(sDate,eDate);
            return dateGenerator.generate();

        } else {
            try {
                return formatter.parse(getVal(pattern));
            } catch (ParseException e) {
                return null;
            }
        }
    }
    public static String getVal(String pattern){
        if(pattern.contains("{") && pattern.contains("}")){
            String newpat=pattern.substring(1,pattern.length()-1);
            String start=newpat.split(",")[0];
            String end=newpat.split(",")[1];
            return between(start,end);
        }else if(pattern.contains("[") && pattern.contains("]")){
            String newpat=pattern.substring(1,pattern.length()-1);
            return among(newpat);
        }else {
            String value=pattern;
            Random random=new Random();
            while(value.contains("#"))
            {
                value=value.replaceFirst("#", Integer.toString(random.nextInt(9)));
            }
            return value;
        }
    }
    public static String among(String list){
        String [] sList=list.split(",");
        int index=new Random().nextInt(sList.length);
        return getVal(sList[index]);
    }
    public static String between(String start,String end){
        start=getVal(start);
        end=getVal(end);
        int int1=Integer.parseInt(start);
        int int2=Integer.parseInt(end);
        return Integer.toString(new Random().nextInt(int2-int1+1)+int1);

    }
    public static IntRange getRange(String pattern){
        if(pattern.contains("{") && pattern.contains("}")){
            String newpat=pattern.substring(1,pattern.length()-1);
            String start=newpat.split(",")[0];
            String end=newpat.split(",")[1];
            return new IntRange(Integer.parseInt(getVal(start)),Integer.parseInt(getVal(end)));
        }
        return null;
    }
}
