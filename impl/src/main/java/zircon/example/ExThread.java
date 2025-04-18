package zircon.example;


import zircon.ExMethod;
import zircon.ExMethodIDE;

public class ExThread {
    @ExMethod(ex = Object.class)
    @ExMethodIDE(shouldInvokeDirectly = true)
    public static StackTraceElement getStackTrace(int index) {
        return getStackTrace(index + 1, true);
    }

    @ExMethod(ex = Object.class)
    @ExMethodIDE(shouldInvokeDirectly = true)
    public static StackTraceElement getStackTrace(int index, boolean skipLambdaOrAnonymous) {
        StackTraceElement[] stackTrace = Thread
                .currentThread().getStackTrace();
        if (skipLambdaOrAnonymous) stackTrace = ExArray.findAll(stackTrace, element -> {
            return !(element.getClassName().contains("$") || element.getMethodName().startsWith("lambda$"));
        });
        if (index < 0) {
            return ExArray.get(stackTrace, index);
        }
        return ExArray.get(stackTrace, index + 2);
    }

    @ExMethod(ex = Object.class)
    @ExMethodIDE(shouldInvokeDirectly = true)
    public static StackTraceElement getStackTrace() {
        return getStackTrace(1);
    }

    @ExMethod
    public static boolean is(StackTraceElement stackTraceElement, Class<?> clazz, String methodName) {
        if (stackTraceElement == null) return false;
        final String className = clazz.getName();
        if (ExObject.noEquals(stackTraceElement.getClassName(), className)) return false;
        if (ExObject.noEquals(stackTraceElement.getMethodName(), methodName)) return false;
        return true;
    }

}
