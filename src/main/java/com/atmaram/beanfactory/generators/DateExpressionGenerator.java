package com.atmaram.beanfactory.generators;

import java.util.Date;
import static com.atmaram.beanfactory.generators.helpers.ExpressionEvaluator.getDateVal;
public class DateExpressionGenerator implements Generator<Date> {
    String expression;
    String format;

    public DateExpressionGenerator(String format,String expression) {
        this.format=format;
        this.expression = expression;
    }
    @Override
    public Date generate() {
        return getDateVal(this.format,this.expression);
    }
}
