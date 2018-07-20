package com.atmaram.beanfactory.generators;

import java.util.Random;

public class BooleanGenerator implements Generator<Boolean>
{
    @Override
    public Boolean generate() {
        Random random = new Random();
        return random.nextBoolean();
    }

}
