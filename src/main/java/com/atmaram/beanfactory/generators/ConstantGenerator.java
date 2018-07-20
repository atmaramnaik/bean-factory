package com.atmaram.beanfactory.generators;

public class ConstantGenerator<T> implements Generator<T> {
    private T constantValue;

    public ConstantGenerator(T constantValue) {
        this.constantValue = constantValue;
    }

    @Override
    public T generate() {
        return this.constantValue;
    }
}
