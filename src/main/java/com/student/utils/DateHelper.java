package com.student.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class DateHelper {

    public static String combine(Date begin, Date close) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        String start = format.format(begin);
        String end = format.format(close);
        return start+"/"+end;
    }
    public static Date stringToDate(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date newDate = null;
        try {
            newDate = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newDate;
    }
}
