package zircon.example;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExDate {
    public String format(Date date,String parseStr){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(parseStr);
        return simpleDateFormat.format(date);
    }
}
