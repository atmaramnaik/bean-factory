package com.atmaram.beanfactory.generators;

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
}
