package com.atmaram.beanfactory.generators;

import java.util.Random;

public class StringGenerator implements Generator<String> {
    private int minLength=0;
    private int maxLength=10;

    public StringGenerator(int minLength, int maxLength) {
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    public StringGenerator(int maxLength) {

        this.maxLength = maxLength;
    }

    public StringGenerator(){
    }

    @Override
    public String generate() {
        Random random = new Random();
        int length=random.nextInt(this.maxLength - this.minLength + 1) + this.minLength;
        Random r = new Random(); // perhaps make it a class variable so you don't make a new one every time
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < length; i++) {
            char c = (char)(r.nextInt((int)(Character.MAX_VALUE)));
            sb.append(c);
        }
        return sb.toString();
    }
}
