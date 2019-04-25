package com.atmaram.beanfactory.generators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MapGenerator<P,Q> implements Generator<HashMap<P,Q>> {
    private int minSize=0;
    private int maxSize=10;
    private Generator<P> keyGenerator;
    private Generator<Q> valueGenerator;

    public MapGenerator(Generator<P> keyGenerator, Generator<Q> valueGenerator) {
        this.keyGenerator = keyGenerator;
        this.valueGenerator = valueGenerator;
    }

    public MapGenerator(int maxSize, Generator<P> keyGenerator, Generator<Q> valueGenerator) {
        this.maxSize = maxSize;
        this.keyGenerator = keyGenerator;
        this.valueGenerator = valueGenerator;
    }

    public MapGenerator(int minSize, int maxSize, Generator<P> keyGenerator, Generator<Q> valueGenerator) {
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.keyGenerator = keyGenerator;
        this.valueGenerator = valueGenerator;
    }

    public MapGenerator() {
    }

    public MapGenerator(int maxSize) {
        this.maxSize = maxSize;
    }

    public MapGenerator(int minSize, int maxSize) {
        this.minSize = minSize;
        this.maxSize = maxSize;
    }

    public Generator<P> getKeyGenerator() {
        return keyGenerator;
    }

    public void setKeyGenerator(Generator<P> keyGenerator) {
        this.keyGenerator = keyGenerator;
    }

    public Generator<Q> getValueGenerator() {
        return valueGenerator;
    }

    public void setValueGenerator(Generator<Q> valueGenerator) {
        this.valueGenerator = valueGenerator;
    }

    @Override
    public HashMap<P,Q> generate() {
        Random random = new Random();
        int count= random.nextInt(this.maxSize - this.minSize + 1) + this.minSize;
        HashMap<P,Q> map=new HashMap<>();
        if(keyGenerator==null || valueGenerator==null){
            return map;
        }
        for(int i=0;i<count;i++){
            map.put(keyGenerator.generate(),valueGenerator.generate());
        }
        return map;
    }

    public static <R,S> MapGenerator<R,S> aMap(Generator<R> keyGenerator, Generator<S> valueGenerator){
        return new MapGenerator<R,S>(keyGenerator,valueGenerator);
    }
    public static <R,S> MapGenerator<R,S> aMap(int maxSize,Generator<R> keyGenerator, Generator<S> valueGenerator){
        return new MapGenerator<R,S>(maxSize,keyGenerator,valueGenerator);
    }
    public static <R,S> MapGenerator<R,S> aMap(int minSize,int maxSize,Generator<R> keyGenerator, Generator<S> valueGenerator){
        return new MapGenerator<R,S>(minSize,maxSize,keyGenerator,valueGenerator);
    }

}
