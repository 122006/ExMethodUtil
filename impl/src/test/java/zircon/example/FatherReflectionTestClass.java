package zircon.example;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FatherReflectionTestClass {
    private static String value = "fv1";
    private static String value2 = "fv2";
    private static String fatherValue = "fv3";
    private String value3 = "fv03";

    private static String staticMethod() {
        return "fsm";
    }

    private String method1() {
        return "fm1";
    }

    private String method2(String a, int a2) {
        return "fm2";
    }

    private String method2(String a, String a2) {
        return "fm22";
    }

    private String method3() {
        return "m3";
    }

    private String method5(FatherReflectionTestClass obj) {
        return "m5";
    }

    public static class ReflectionReflectionTestClass extends FatherReflectionTestClass {
        private static String value = "v1";
        private static String value2 = "v2";
        private String value3 = "v03";
        private String value4 = "v4";


        private String staticMethod() {
            return "sm";
        }

        private String method1() {
            return "m1";
        }

        private String method2(String a, int a2) {
            return "m2";
        }

        private String method2(String a, String a2) {
            return "m22";
        }


    }

    public void test() {
        //例1：解析字符串"11,12,13,14,15"，找到其中的全部偶数
        final String str = "11,12,13,14,15";
        //=================使用for循环=========================
        List<String> list = new ArrayList<>();
        for (String s : str.split(",")) {
            if (Integer.parseInt(s) % 2 == 0) list.add(s);
        }
        final String[] v0 = list.toArray(new String[0]);
        //=================使用java的Stream筛选=================
        final String[] v1 = Arrays.stream(str.split(",")).filter(a -> Integer.parseInt(a) % 2 == 0).toArray(String[]::new);
        //=================使用zircon筛选=======================
        final String[] v2 = str.split(",").findAll(a -> a.toInt() % 2 == 0);


        final String[] v3 = str.split(",").findAll(a -> a.toInt() % 2 == 0);
    }
}
