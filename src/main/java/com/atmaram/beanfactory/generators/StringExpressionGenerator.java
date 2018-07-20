package com.atmaram.beanfactory.generators;

import static com.atmaram.beanfactory.generators.helpers.ExpressionEvaluator.getVal;

public class StringExpressionGenerator implements Generator<String>{
    String expression;

    public StringExpressionGenerator(String expression) {
        this.expression = expression;
    }

    @Override
    public String generate() {
        return getVal(this.expression);
    }

}
