package com.atmaram.beanfactory.generators;

import com.atmaram.beanfactory.generators.Generator;

public class DoubleGenerator implements Generator<Double> {
    private double min=0;
    private double max=10000;

    public DoubleGenerator(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public DoubleGenerator(double max) {
        this.max = max;
    }

    public DoubleGenerator(){
    }

    @Override
    public Double generate() {
        double ret=this.min+(Math.random()*(this.max-this.min));

        return ret;
    }

    public static DoubleGenerator aDouble(){
        return new DoubleGenerator();
    }
    public static DoubleGenerator aDouble(double max){
        return new DoubleGenerator(max);
    }
    public static DoubleGenerator aDouble(double min,double max){
        return new DoubleGenerator(min, max);
    }
}
