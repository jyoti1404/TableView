package com.example.table;

import android.content.Context;
import android.text.format.DateFormat;

import java.util.Calendar;
import java.util.Locale;



public class CommonClass {
    Context mContext;

    public CommonClass(Context con) {
        this.mContext = con;

    }

    public static String timeStampAnswertoDate(String timeStamp) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(Long.parseLong(timeStamp));
        String date = DateFormat.format("dd/MMM/yyyy", cal).toString();
        return date;
    }
}