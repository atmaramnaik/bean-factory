package com.atmaram.beanfactory.generators;

import java.util.Date;

public class DateGenerator implements Generator<Date> {
    private Date from=new Date(0001,1,1,0,0,0);
    private Date to=new Date();

    public DateGenerator(Date to) {
        this.to=to;
    }

    public DateGenerator(Date from, Date to) {
        this.from = from;
        this.to = to;
    }

    public DateGenerator() {

    }
    @Override
    public Date generate() {
        long lFrom = from.getTime();
        long lTo = to.getTime();
        Date rDate=new Date(lFrom + (long)(Math.random() * (lTo - lFrom)));
        return rDate;
    }
}
