package defpackage;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.util.Size;
import android.view.WindowManager;
import com.huawei.openalliance.ad.constant.Constants;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes7.dex */
public class skr {
    private static final String e = "HwDeviceUtils";

    private static boolean a(Size size) {
        return size.getHeight() > 0 && Float.compare(((float) size.getWidth()) / ((float) size.getHeight()), 0.875f) >= 0;
    }

    public static boolean b() {
        String[] split = e("ro.config.hw_fold_disp", "").split(",");
        return split.length == 9 && Integer.parseInt(split[8]) == 1;
    }

    public static boolean c() {
        return e("ro.build.characteristics", "") != null && e("ro.build.characteristics", "").equalsIgnoreCase("tablet");
    }

    public static boolean c(Context context) {
        return a(dZR_(context));
    }

    public static boolean d() {
        return (e("ro.config.hw_fold_disp", "").isEmpty() && e("persist.sys.fold.disp.size", "").isEmpty()) ? false : true;
    }

    private static Size dZR_(Context context) {
        Point point = new Point();
        ((WindowManager) context.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR)).getDefaultDisplay().getRealSize(point);
        if (point.y < point.x) {
            return new Size(point.y, point.x);
        }
        return new Size(point.x, point.y);
    }

    private static String e(String str, String str2) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            Object invoke = cls.getMethod("get", String.class, String.class).invoke(cls, str, str2);
            return invoke instanceof String ? (String) invoke : str2;
        } catch (ClassNotFoundException e2) {
            Log.w(e, "getProperty() : Get property failed. classNotFoundException:" + e2.getMessage());
            return str2;
        } catch (IllegalAccessException e3) {
            Log.w(e, "getProperty() : Get property failed. illegalAccessException:" + e3.getMessage());
            return str2;
        } catch (NoSuchMethodException e4) {
            Log.w(e, "getProperty() : Get property failed. noSuchMethodException:" + e4.getMessage());
            return str2;
        } catch (InvocationTargetException e5) {
            Log.w(e, "getProperty() : Get property failed. invocationTargetException:" + e5.getMessage());
            return str2;
        }
    }
}
