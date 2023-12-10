package zircon.example.android;

import android.app.Activity;
import android.os.Looper;
import zircon.ExMethod;
import zircon.data.ThrowRunnable;

public class ExAndroidObject {
    @ExMethod(ex = java.lang.Object.class)
    public static boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    @ExMethod(ex = java.lang.Object.class)
    public static boolean isBgThread() {
        return Looper.getMainLooper() != Looper.myLooper();
    }

    @ExMethod
    public static void runOnUi(Object context, ThrowRunnable runnable) {
        try {
            if (isBgThread()) {
                if (context instanceof Activity){
                    ((Activity) context).runOnUiThread(()-> {
                        try {
                            runnable.run();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
            } else {
                runnable.run();
            }
        }catch (Exception ex){
            throw (RuntimeException)ex;
        }
    }
}
