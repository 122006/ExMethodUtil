package zircon.example;

import zircon.ExMethod;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExString {
    @ExMethod
    public static List<String> regex(String str, String regex) {
        if (str == null) return new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(str);
        List<String> list = new ArrayList<String>();
        while (matcher.find()) {
            String matchedStr = matcher.group();
            list.add(matchedStr);
        }
        return list;
    }

    @ExMethod
    public static boolean nullOrEmpty(String str) {
        return str == null || str.length() == 0;
    }

    @ExMethod
    public static String nullOrEmpty(String str, String str2) {
        if (ExString.nullOrEmpty(str)) {
            return str2;
        }
        return str;
    }


    @ExMethod(cover = true)
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    @ExMethod
    public static Integer toInteger(String str) {
        if (str == null) return null;
        return Integer.valueOf(str);
    }

    @ExMethod
    public static Byte toByte(String str) {
        if (str == null) return null;
        return Byte.valueOf(str);
    }

    @ExMethod
    public static int toInt(String str) {
        if (str == null) return 0;
        return Integer.parseInt(str);
    }

    @ExMethod
    public static Long toLong(String str) {
        if (str == null) return null;
        return Long.valueOf(str);
    }

    @ExMethod
    public static long toLongValue(String str) {
        if (str == null) return 0;
        return Long.parseLong(str);
    }

    @ExMethod
    public static Double toDouble(String str) {
        if (str == null) return null;
        return Double.valueOf(str);
    }

    @ExMethod
    public static double toDoubleValue(String str) {
        if (str == null) return 0;
        return Double.parseDouble(str);
    }

    @ExMethod
    public static BigDecimal toBigDecimal(String str) {
        return new BigDecimal(str);
    }

    @ExMethod
    public static BigDecimal toBigDecimal(String str, int setPrecision) {
        return ExString.toBigDecimal(str, setPrecision, RoundingMode.HALF_UP);
    }

    @ExMethod
    public static BigDecimal toBigDecimal(String str, int newScale, RoundingMode setRoundingMode) {
        BigDecimal bigDecimal = str == null ? BigDecimal.ZERO : new BigDecimal(str);
        bigDecimal = bigDecimal.setScale(newScale, setRoundingMode);
        return bigDecimal;
    }

}
