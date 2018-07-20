package com.atmaram.beanfactory.generators;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringFormattedDateGenerator implements Generator<String>{
    String format;
    Generator<Date> dateGenerator;

    public StringFormattedDateGenerator(String format) {
        this(format,new DateGenerator());
    }
    public StringFormattedDateGenerator(String format,Date to) {
        this(format,new DateGenerator(to));
    }
    public StringFormattedDateGenerator(String format,Date start,Date end) {
        this(format,new DateGenerator(start,end));
    }

    public StringFormattedDateGenerator(String format, Generator<Date> dateGenerator) {
        this.format = format;
        this.dateGenerator = dateGenerator;
    }

    @Override
    public String generate() {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(this.format);
        return simpleDateFormat.format(dateGenerator.generate());
    }
}

