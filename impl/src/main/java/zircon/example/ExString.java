package zircon.example;

import zircon.ExMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExString {
    @ExMethod
    public static List<String> regex(String str, String regex) {
        Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(str);
        List<String> list = new ArrayList<String>();
        while (matcher.find()) {
            String matchedStr = matcher.group();
            list.add(matchedStr);
        }
        return list;
    }

    @ExMethod(cover = true)
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
}
