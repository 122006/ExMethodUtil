package zircon.example;

import zircon.ExMethod;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExDate {
    @ExMethod
    public static String format(Date date,String parseStr){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(parseStr);
        return simpleDateFormat.format(date);
    }
}
