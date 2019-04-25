package com.atmaram.beanfactory.generators;

import java.util.Random;

public class IntegerGenerator implements Generator<Integer> {
    private int min=0;
    private int max=10000;

    public IntegerGenerator(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public IntegerGenerator(int max) {
        this.max = max;
    }

    public IntegerGenerator(){
    }

    @Override
    public Integer generate() {
        Random random = new Random();
        return random.nextInt(this.max - this.min + 1) + this.min;
    }

    public static IntegerGenerator aInteger(){
        return new IntegerGenerator();
    }
    public static IntegerGenerator aInteger(int max){
        return new IntegerGenerator(max);
    }
    public static IntegerGenerator aInteger(int min, int max){
        return new IntegerGenerator(min, max);
    }
}
