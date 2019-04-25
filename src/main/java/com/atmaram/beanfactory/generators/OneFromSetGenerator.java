package com.atmaram.beanfactory.generators;

import java.util.*;

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
    public static <P> OneFromSetGenerator<P> oneOf(Set<P> set){
        return new OneFromSetGenerator<>(set);
    }
    public static <P> OneFromSetGenerator<P> oneOf(P ... values){
        Set<P> set=new HashSet<>();
        for (P value:
             values) {
            set.add(value);
        }
        return new OneFromSetGenerator<>(set);
    }
}
