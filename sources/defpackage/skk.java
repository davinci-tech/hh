package defpackage;

import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.util.Size;
import android.view.WindowManager;
import com.huawei.openalliance.ad.constant.Constants;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes7.dex */
public class skk {
    private static final String e = "HwDeviceUtils";

    public static boolean b() {
        String e2 = e("ro.build.characteristics", "default");
        return e2 != null && e2.equalsIgnoreCase("tablet");
    }

    public static boolean c(Context context) {
        return dYR_(a(context));
    }

    private static boolean dYR_(Size size) {
        return size != null && Float.compare(((float) size.getWidth()) / ((float) size.getHeight()), 0.875f) >= 0;
    }

    private static Size a(Context context) {
        Point point = new Point();
        ((WindowManager) context.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR)).getDefaultDisplay().getRealSize(point);
        int i = point.y;
        int i2 = point.x;
        if (i < i2) {
            return new Size(i, i2);
        }
        return new Size(i2, i);
    }

    private static String e(String str, String str2) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            Object invoke = cls.getMethod("get", String.class, String.class).invoke(cls, str, str2);
            return invoke instanceof String ? (String) invoke : str2;
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            Log.w(e, "Get property failed.");
            return str2;
        }
    }
}
