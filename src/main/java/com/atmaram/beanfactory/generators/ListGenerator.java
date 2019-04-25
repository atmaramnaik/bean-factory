package com.atmaram.beanfactory.generators;

import com.atmaram.beanfactory.generators.helpers.ExpressionEvaluator;
import com.atmaram.beanfactory.generators.helpers.IntRange;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListGenerator<T> implements Generator<List<T>> {
    private int minLength=0;
    private int maxLength=10;
    private Generator<T> memberGenerator;

    public Generator<T> getMemberGenerator() {
        return memberGenerator;
    }

    public void setMemberGenerator(Generator<T> memberGenerator) {
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

    public ListGenerator(Generator<T> memberGenerator) {
        this.memberGenerator = memberGenerator;
    }

    public ListGenerator(int maxLength, Generator<T> memberGenerator) {
        this.maxLength = maxLength;
        this.memberGenerator = memberGenerator;
    }
    public ListGenerator(String maxLength, Generator<T> memberGenerator) {
        this.maxLength = Integer.parseInt(ExpressionEvaluator.getVal(maxLength));
        this.memberGenerator = memberGenerator;
    }

    public ListGenerator(int minLength, int maxLength, Generator<T> memberGenerator) {
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.memberGenerator = memberGenerator;
    }
    public ListGenerator(String minLength, String maxLength, Generator<T> memberGenerator) {
        this.minLength = Integer.parseInt(ExpressionEvaluator.getVal(minLength));
        this.maxLength = Integer.parseInt(ExpressionEvaluator.getVal(maxLength));
        this.memberGenerator = memberGenerator;
    }

    @Override
    public List<T> generate() {
        Random random = new Random();
        int count= random.nextInt(this.maxLength - this.minLength + 1) + this.minLength;
        List<T> arrayList= new ArrayList<>();
        if (memberGenerator==null)
            return arrayList;
        for(int i=0;i<count;i++){
            arrayList.add(memberGenerator.generate());
        }
        return arrayList;
    }
    public static <P> ListGenerator<P> aList(Generator<P> memberGenerator){
        return new ListGenerator<>(memberGenerator);
    }
    public static <P> ListGenerator<P> aList(int max,Generator<P> memberGenerator){
        return new ListGenerator<>(max,memberGenerator);
    }
    public static <P> ListGenerator<P> aList(int min,int max,Generator<P> memberGenerator){
        return new ListGenerator<>(min,max,memberGenerator);
    }
}
