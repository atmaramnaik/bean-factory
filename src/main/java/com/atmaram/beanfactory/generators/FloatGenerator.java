package com.atmaram.beanfactory.generators;

import com.atmaram.beanfactory.generators.Generator;

public class FloatGenerator implements Generator<Float> {
    private float min=0;
    private float max=10000;

    public FloatGenerator(float min, float max) {
        this.min = min;
        this.max = max;
    }

    public FloatGenerator(float max) {
        this.max = max;
    }

    public FloatGenerator(){
    }

    @Override
    public Float generate() {
        float ret=this.min+(float)(Math.random()*(this.max-this.min));

        return ret;
    }
    public static FloatGenerator aFloat(){
        return new FloatGenerator();
    }
    public static FloatGenerator aFloat(float max){
        return new FloatGenerator(max);
    }
    public static FloatGenerator aFloat(float min,float max){
        return new FloatGenerator(min, max);
    }
}
