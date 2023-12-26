package zircon.example;


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
}
