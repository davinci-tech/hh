package defpackage;

import android.view.Window;
import android.view.WindowManager;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes5.dex */
public class ktd {
    public static void bQL_(Window window) {
        bQM_(window, "addHwFlags");
    }

    public static void bQN_(Window window) {
        bQM_(window, "clearHwFlags");
    }

    private static void bQM_(Window window, String str) {
        try {
            ksy.b("ScreenShotUtils", "invokeScreenShot flags==" + str, true);
            WindowManager.LayoutParams attributes = window.getAttributes();
            Class<?> cls = Class.forName("com.huawei.android.view.LayoutParamsEx");
            Object newInstance = cls.getDeclaredConstructor(WindowManager.LayoutParams.class).newInstance(attributes);
            Method method = cls.getMethod(str, Integer.TYPE);
            method.invoke(newInstance, 4096);
            method.invoke(newInstance, 8192);
            window.clearFlags(0);
        } catch (ClassNotFoundException e) {
            ksy.c("ScreenShotUtils", "ClassNotFoundException--" + e.getClass().getSimpleName(), true);
        } catch (IllegalAccessException e2) {
            ksy.c("ScreenShotUtils", "IllegalAccessException--" + e2.getClass().getSimpleName(), true);
        } catch (InstantiationException e3) {
            ksy.c("ScreenShotUtils", "InstantiationException--" + e3.getClass().getSimpleName(), true);
        } catch (NoClassDefFoundError e4) {
            ksy.c("ScreenShotUtils", "NoClassDefFoundError--" + e4.getClass().getSimpleName(), true);
        } catch (NoSuchMethodException e5) {
            ksy.c("ScreenShotUtils", "NoSuchMethodException--" + e5.getClass().getSimpleName(), true);
        } catch (InvocationTargetException e6) {
            ksy.c("ScreenShotUtils", "InvocationTargetException--" + e6.getClass().getSimpleName(), true);
        } catch (Exception e7) {
            ksy.c("ScreenShotUtils", "Exception--" + e7.getClass().getSimpleName(), true);
        }
    }
}
