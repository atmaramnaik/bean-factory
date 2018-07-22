package com.atmaram.beanfactory.generators;
@FunctionalInterface
public interface Generator<T> {
    public T generate();
}
