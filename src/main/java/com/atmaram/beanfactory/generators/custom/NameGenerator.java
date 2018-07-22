package com.atmaram.beanfactory.generators.custom;


import com.atmaram.beanfactory.generators.Generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NameGenerator implements Generator<String> {
    int max_words=3;
    int min_words=1;
    int min_length=5;
    int max_length=15;

    public NameGenerator() {
    }

    public NameGenerator(int max_words, int max_length) {
        this.max_words = max_words;
        this.max_length = max_length;
    }

    public NameGenerator(int min_words, int max_words, int min_length, int max_length) {
        this.max_words = max_words;
        this.min_words = min_words;
        this.min_length = min_length;
        this.max_length = max_length;
    }

    @Override
    public String generate() {
        List<Character> allowedChars=new ArrayList<>();
        for(char i=97;i<=122;i++){
            allowedChars.add(new Character(i));
        }
        Random random=new Random();
        int words=min_words+random.nextInt(max_words-min_words+1);
        String str="";
        for(int i=0;i<words;i++){
            int word_length=min_length+random.nextInt(max_length-min_length+1);
            if(i!=0){
                str+=" ";
            }
            for(int j=0;j<word_length;j++)
            {
                Character charCode=allowedChars.get(random.nextInt(allowedChars.size()));
                str+=charCode.charValue();
            }
        }
        return str;
    }
}
