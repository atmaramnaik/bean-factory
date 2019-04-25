package com.atmaram.beanfactory.generators;

import java.text.ParseException;
import java.util.Date;
import static com.atmaram.beanfactory.generators.helpers.ExpressionEvaluator.getDateVal;
public class DateExpressionGenerator implements Generator<Date> {
    String expression;
    String format;

    public DateExpressionGenerator(String format,String expression) throws ParseException {
        this.format=format;
        this.expression = expression;
        getDateVal(format,expression);
    }
    @Override
    public Date generate() {
        try {
            return getDateVal(this.format,this.expression);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static DateExpressionGenerator aDateExpression(String format, String expression) throws ParseException{
        return new DateExpressionGenerator(format,expression);
    }
}
