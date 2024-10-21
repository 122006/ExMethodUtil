package zircon.example;

import zircon.ExMethod;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExDate {
    @ExMethod
    public static String format(Date date, String parseStr) {
        if (date==null) return null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(parseStr);
        return simpleDateFormat.format(date);
    }

    @ExMethod(ex = Date.class)
    public static Date now() {
        return new Date();
    }

    @ExMethod(ex = Date.class)
    public static String nowDateStr() {
        return ExDate.format(new Date(), "yyyy/MM/dd");
    }

    @ExMethod(ex = Date.class)
    public static String nowTimeStr() {
        return ExDate.format(new Date(), "HH:mm:ss");
    }

    @ExMethod(ex = Date.class)
    public static String nowDateTimeStr() {
        return ExDate.format(new Date(), "yyyy/MM/dd HH:mm:ss");
    }

    @ExMethod
    public static String dateStr(Date date) {
        if (date==null) return null;
        return ExDate.format(date, "yyyy/MM/dd");
    }

    @ExMethod
    public static String timeStr(Date date) {
        if (date==null) return null;
        return ExDate.format(date, "HH:mm:ss");
    }

    @ExMethod
    public static String dateTimeStr(Date date) {
        if (date==null) return null;
        return ExDate.format(date, "yyyy/MM/dd HH:mm:ss");
    }

}
