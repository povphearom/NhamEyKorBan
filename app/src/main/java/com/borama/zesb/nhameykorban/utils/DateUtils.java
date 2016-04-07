package com.borama.zesb.nhameykorban.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by phearom on 4/7/16.
 */
public class DateUtils {
    public static String getDateString(String format) {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(c.getTime());
    }
}
