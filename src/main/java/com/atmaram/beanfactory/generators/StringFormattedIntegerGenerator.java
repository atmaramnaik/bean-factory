package com.atmaram.beanfactory.generators;

import java.util.Date;

public class StringFormattedIntegerGenerator implements Generator<String>{
    String format;
    Generator<Integer> integerGenerator;

    public StringFormattedIntegerGenerator(String format) {
        this(format,new IntegerGenerator());
    }
    public StringFormattedIntegerGenerator(String format,int max) {
        this(format,new IntegerGenerator(max));
    }
    public StringFormattedIntegerGenerator(String format,int min,int max) {
        this(format,new IntegerGenerator(min,max));
    }

    public StringFormattedIntegerGenerator(String format, Generator<Integer> integerGenerator) {
        this.format = format;
        this.integerGenerator = integerGenerator;
    }

    @Override
    public String generate() {
        return String.format(this.format,integerGenerator.generate());
    }

    public static StringFormattedIntegerGenerator aStringInteger(String format){
        return new StringFormattedIntegerGenerator(format);
    }
    public static StringFormattedIntegerGenerator aStringInteger(int max,String format){
        return new StringFormattedIntegerGenerator(format,max);
    }
    public static StringFormattedIntegerGenerator aStringInteger(int min,int max,String format){
        return new StringFormattedIntegerGenerator(format, min, max);
    }
}
