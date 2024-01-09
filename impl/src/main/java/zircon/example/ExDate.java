package zircon.example;

import zircon.ExMethod;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExDate {
    @ExMethod
    public static String format(Date date, String parseStr) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(parseStr);
        return simpleDateFormat.format(date);
    }

    @ExMethod(ex = Date.class)
    public static Date now() {
        return new Date();
    }

    @ExMethod(ex = Date.class)
    public static String nowDateStr() {
        return new Date().format("yyyy/MM/dd");
    }

    @ExMethod(ex = Date.class)
    public static String nowTimeStr() {
        return new Date().format("HH:mm:ss");
    }

    @ExMethod(ex = Date.class)
    public static String nowDateTimeStr() {
        return new Date().format("yyyy/MM/dd HH:mm:ss");
    }

    @ExMethod
    public static String dateStr(Date date) {
        return date.format("yyyy/MM/dd");
    }

    @ExMethod
    public static String timeStr(Date date) {
        return date.format("HH:mm:ss");
    }

    @ExMethod
    public static String dateTimeStr(Date date) {
        return date.format("yyyy/MM/dd HH:mm:ss");
    }

}
