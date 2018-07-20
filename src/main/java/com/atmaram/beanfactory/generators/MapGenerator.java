package com.atmaram.beanfactory.generators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MapGenerator implements Generator<HashMap> {
    private int minSize=0;
    private int maxSize=10;
    private Generator keyGenerator;
    private Generator valueGenerator;

    public MapGenerator(Generator keyGenerator, Generator valueGenerator) {
        this.keyGenerator = keyGenerator;
        this.valueGenerator = valueGenerator;
    }

    public MapGenerator(int maxSize, Generator keyGenerator, Generator valueGenerator) {
        this.maxSize = maxSize;
        this.keyGenerator = keyGenerator;
        this.valueGenerator = valueGenerator;
    }

    public MapGenerator(int minSize, int maxSize, Generator keyGenerator, Generator valueGenerator) {
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

    public Generator getKeyGenerator() {
        return keyGenerator;
    }

    public void setKeyGenerator(Generator keyGenerator) {
        this.keyGenerator = keyGenerator;
    }

    public Generator getValueGenerator() {
        return valueGenerator;
    }

    public void setValueGenerator(Generator valueGenerator) {
        this.valueGenerator = valueGenerator;
    }

    @Override
    public HashMap generate() {
        Random random = new Random();
        int count= random.nextInt(this.maxSize - this.minSize + 1) + this.minSize;
        HashMap map=new HashMap();
        if(keyGenerator==null || valueGenerator==null){
            return map;
        }
        for(int i=0;i<count;i++){
            map.put(keyGenerator.generate(),valueGenerator.generate());
        }
        return map;
    }
}
