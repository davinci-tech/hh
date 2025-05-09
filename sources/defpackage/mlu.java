package defpackage;

import android.app.Activity;
import android.content.Context;
import android.view.Window;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.KakaConstants;

/* loaded from: classes6.dex */
public class mlu {
    public static int f(Context context) {
        return nsn.h(context);
    }

    public static int j(Context context) {
        return nsn.f(context);
    }

    public static int e(Context context) {
        return ((f(context) / 2) - nsn.c(context, 140.0f)) / 2;
    }

    public static int i(Context context) {
        return ((f(context) / 2) - b(context)) / 2;
    }

    public static int b(Context context) {
        return nsn.c(context, 150.0f);
    }

    public static int a(Context context) {
        return nsn.c(context, 140.0f);
    }

    public static int d(Context context) {
        if (nsn.ag(context)) {
            return (c(context) * 660) / KakaConstants.TASK_RUN_BEHAVIOR;
        }
        return (c(context) * 686) / 720;
    }

    public static int g(Context context) {
        if (nsn.ag(context)) {
            return nsn.c(context, 65.0f);
        }
        return nsn.c(context, 72.0f);
    }

    public static int c(Context context) {
        return f(context);
    }

    public static void clx_(Activity activity) {
        if (activity == null) {
            LogUtil.h("PLGACHIEVE_ScreenUtil", "setImmersive activity is null");
            return;
        }
        Window window = activity.getWindow();
        if (window == null) {
            LogUtil.h("PLGACHIEVE_ScreenUtil", "setImmersive window is null");
            return;
        }
        window.clearFlags(201326592);
        window.getDecorView().setSystemUiVisibility(HealthData.WEIGHT);
        window.addFlags(Integer.MIN_VALUE);
        window.setStatusBarColor(0);
        window.setNavigationBarColor(0);
    }
}
