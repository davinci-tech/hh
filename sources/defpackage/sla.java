package defpackage;

import android.graphics.Rect;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes7.dex */
public class sla {
    private static Rect ebw_() {
        Object invoke;
        try {
            invoke = Class.forName("com.huawei.android.app.WindowManagerEx").getMethod("getDisplaySideSafeInsets", new Class[0]).invoke(null, new Object[0]);
        } catch (ClassNotFoundException unused) {
            Log.e("HwDisplaySizeUtil", "getDisplaySideSafeInsets: class not found");
        } catch (IllegalAccessException unused2) {
            Log.e("HwDisplaySizeUtil", "getDisplaySideSafeInsets: illegal access exception");
        } catch (NoSuchMethodException unused3) {
            Log.e("HwDisplaySizeUtil", "getDisplaySideSafeInsets: method not found");
        } catch (InvocationTargetException unused4) {
            Log.e("HwDisplaySizeUtil", "getDisplaySideSafeInsets: invocation target exception");
        }
        if (invoke instanceof Rect) {
            return (Rect) invoke;
        }
        Log.e("HwDisplaySizeUtil", "getDisplaySideSafeInsets: object is not Rect");
        return null;
    }

    private static Rect ebx_() {
        Object invoke;
        try {
            invoke = Class.forName("com.huawei.android.view.ExtDisplaySizeUtilEx").getMethod("getDisplaySafeInsets", new Class[0]).invoke(null, new Object[0]);
        } catch (ClassNotFoundException unused) {
            Log.e("HwDisplaySizeUtil", "getDisplaySafeInsets: class not found");
        } catch (IllegalAccessException unused2) {
            Log.e("HwDisplaySizeUtil", "getDisplaySafeInsets: illegal access exception");
        } catch (NoSuchMethodException unused3) {
            Log.e("HwDisplaySizeUtil", "getDisplaySafeInsets: method not found");
        } catch (InvocationTargetException unused4) {
            Log.e("HwDisplaySizeUtil", "getDisplaySafeInsets: invocation target exception");
        }
        if (invoke instanceof Rect) {
            return (Rect) invoke;
        }
        Log.e("HwDisplaySizeUtil", "getDisplaySafeInsets: object is not Rect");
        return new Rect();
    }

    public static Rect eby_() {
        Rect ebw_ = ebw_();
        return ebw_ == null ? ebx_() : ebw_;
    }
}
