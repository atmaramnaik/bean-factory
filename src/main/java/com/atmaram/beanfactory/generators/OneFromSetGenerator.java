package com.atmaram.beanfactory.generators;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class OneFromSetGenerator<T> implements Generator<T> {
    Set<T> set;

    public OneFromSetGenerator(Set<T> set) {
        this.set = set;
    }

    @Override
    public T generate() {
        Random random = new Random();
        int index = random.nextInt(set.size());
        Iterator<T> iter = set.iterator();
        for (int i = 0; i < index; i++) {
            iter.next();
        }
        return iter.next();
    }
}
