package com.atmaram.beanfactory.generators;

import com.atmaram.beanfactory.generators.helpers.ExpressionEvaluator;
import com.atmaram.beanfactory.generators.helpers.IntRange;

import java.util.ArrayList;
import java.util.Random;

public class ListGenerator implements Generator<ArrayList> {
    private int minLength=0;
    private int maxLength=10;
    private Generator memberGenerator;

    public Generator getMemberGenerator() {
        return memberGenerator;
    }

    public void setMemberGenerator(Generator memberGenerator) {
        this.memberGenerator = memberGenerator;
    }

    public ListGenerator(int minLength, int maxLength) {
        this.minLength = minLength;
        this.maxLength = maxLength;
    }
    public ListGenerator(String minLength, String maxLength) {
        this.minLength = Integer.parseInt(ExpressionEvaluator.getVal(minLength));
        this.maxLength = Integer.parseInt(ExpressionEvaluator.getVal(maxLength));
    }
    public ListGenerator(IntRange range){
        this.minLength=range.getMin();
        this.maxLength=range.getMax();
    }
    public ListGenerator(int maxLength) {
        this.maxLength = maxLength;
    }
    public ListGenerator(String maxLength) {
        this.maxLength = Integer.parseInt(ExpressionEvaluator.getVal(maxLength));
    }

    public ListGenerator(){
    }

    public ListGenerator(Generator memberGenerator) {
        this.memberGenerator = memberGenerator;
    }

    public ListGenerator(int maxLength, Generator memberGenerator) {
        this.maxLength = maxLength;
        this.memberGenerator = memberGenerator;
    }
    public ListGenerator(String maxLength, Generator memberGenerator) {
        this.maxLength = Integer.parseInt(ExpressionEvaluator.getVal(maxLength));
        this.memberGenerator = memberGenerator;
    }

    public ListGenerator(int minLength, int maxLength, Generator memberGenerator) {
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.memberGenerator = memberGenerator;
    }
    public ListGenerator(String minLength, String maxLength, Generator memberGenerator) {
        this.minLength = Integer.parseInt(ExpressionEvaluator.getVal(minLength));
        this.maxLength = Integer.parseInt(ExpressionEvaluator.getVal(maxLength));
        this.memberGenerator = memberGenerator;
    }

    @Override
    public ArrayList generate() {
        Random random = new Random();
        int count= random.nextInt(this.maxLength - this.minLength + 1) + this.minLength;
        ArrayList arrayList=new ArrayList();
        if (memberGenerator==null)
            return arrayList;
        for(int i=0;i<count;i++){
            arrayList.add(memberGenerator.generate());
        }
        return arrayList;
    }
}
