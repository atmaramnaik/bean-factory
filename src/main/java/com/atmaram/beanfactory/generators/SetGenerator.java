package com.atmaram.beanfactory.generators;

import java.util.List;
import java.util.Random;

public class SetGenerator<T> implements Generator<T> {
    List<T> set;

    public SetGenerator(List<T> set) {
        this.set = set;
    }

    @Override
    public T generate() {
        Random random = new Random();
        return set.get(random.nextInt(set.size()));
    }
}
