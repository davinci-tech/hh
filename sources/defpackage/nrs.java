package defpackage;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.WindowManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;

/* loaded from: classes.dex */
public class nrs {
    public static boolean a(Context context) {
        return b(context, 1.7777778f);
    }

    public static int c(Context context) {
        if (context == null) {
            LogUtil.b("HealthDisplayUtils", "getDeviceWidthPixels error: context is null");
            return 0;
        }
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int d(Context context) {
        if (context == null) {
            LogUtil.b("HealthDisplayUtils", "getDeviceWidthPixels error: context is null");
            return 0;
        }
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static boolean e(Context context) {
        return b(context, 2.0f);
    }

    private static boolean b(Context context, float f) {
        if (context == null) {
            LogUtil.h("HealthDisplayUtils", "isMoreThanResolutionDisplay: context is null");
            return false;
        }
        Object systemService = context.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR);
        if (!(systemService instanceof WindowManager)) {
            LogUtil.h("HealthDisplayUtils", "object is invalid type");
            return false;
        }
        if (nsn.ag(context) && nsn.l()) {
            return true;
        }
        WindowManager windowManager = (WindowManager) systemService;
        return ((float) windowManager.getDefaultDisplay().getHeight()) / ((float) windowManager.getDefaultDisplay().getWidth()) > f;
    }

    public static Resources cKi_(Resources resources, float f) {
        Configuration configuration = resources.getConfiguration();
        if (configuration.fontScale > f) {
            configuration.fontScale = f;
            resources.updateConfiguration(null, null);
        }
        return resources;
    }
}
