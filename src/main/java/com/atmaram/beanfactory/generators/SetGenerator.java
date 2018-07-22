package com.atmaram.beanfactory.generators;

import com.atmaram.beanfactory.generators.Generator;
import com.atmaram.beanfactory.generators.helpers.ExpressionEvaluator;
import com.atmaram.beanfactory.generators.helpers.IntRange;

import java.util.*;

public class SetGenerator<T> implements Generator<Set<T>> {
    private int minLength=0;
    private int maxLength=10;
    private Generator<T> memberGenerator;

    public Generator<T> getMemberGenerator() {
        return memberGenerator;
    }

    public void setMemberGenerator(Generator<T> memberGenerator) {
        this.memberGenerator = memberGenerator;
    }

    public SetGenerator(int minLength, int maxLength) {
        this.minLength = minLength;
        this.maxLength = maxLength;
    }
    public SetGenerator(String minLength, String maxLength) {
        this.minLength = Integer.parseInt(ExpressionEvaluator.getVal(minLength));
        this.maxLength = Integer.parseInt(ExpressionEvaluator.getVal(maxLength));
    }
    public SetGenerator(IntRange range){
        this.minLength=range.getMin();
        this.maxLength=range.getMax();
    }
    public SetGenerator(int maxLength) {
        this.maxLength = maxLength;
    }
    public SetGenerator(String maxLength) {
        this.maxLength = Integer.parseInt(ExpressionEvaluator.getVal(maxLength));
    }

    public SetGenerator(){
    }

    public SetGenerator(Generator<T> memberGenerator) {
        this.memberGenerator = memberGenerator;
    }

    public SetGenerator(int maxLength, Generator<T> memberGenerator) {
        this.maxLength = maxLength;
        this.memberGenerator = memberGenerator;
    }
    public SetGenerator(String maxLength, Generator<T> memberGenerator) {
        this.maxLength = Integer.parseInt(ExpressionEvaluator.getVal(maxLength));
        this.memberGenerator = memberGenerator;
    }

    public SetGenerator(int minLength, int maxLength, Generator<T> memberGenerator) {
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.memberGenerator = memberGenerator;
    }
    public SetGenerator(String minLength, String maxLength, Generator<T> memberGenerator) {
        this.minLength = Integer.parseInt(ExpressionEvaluator.getVal(minLength));
        this.maxLength = Integer.parseInt(ExpressionEvaluator.getVal(maxLength));
        this.memberGenerator = memberGenerator;
    }

    @Override
    public Set<T> generate() {
        Random random = new Random();
        int count= random.nextInt(this.maxLength - this.minLength + 1) + this.minLength;
        Set<T> set= new HashSet<>();
        if (memberGenerator==null)
            return set;
        for(int i=0;i<count;i++){
            set.add(memberGenerator.generate());
        }
        return set;
    }
}
