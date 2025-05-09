package defpackage;

import android.os.Handler;
import com.huawei.basichealthmodel.R$string;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes.dex */
public class bbb {
    private static Handler b;

    public static void mu_(Handler handler) {
        if (handler == null) {
            LogUtil.h("HealthLife_HealthModelHandlerHelper", "initHandler handler is null");
        } else {
            b = handler;
        }
    }

    public static void a(int i) {
        azi.lZ_(b, 5000, Integer.valueOf(i));
    }

    public static void a() {
        azi.lZ_(b, 8000, BaseApplication.e().getResources().getString(R$string.IDS_health_model_edit_error_time));
    }

    public static void b() {
        azi.lZ_(b, 9000, BaseApplication.e().getResources().getString(R$string.IDS_health_model_user_status_out));
    }

    public static void e() {
        azi.lZ_(b, 10000, BaseApplication.e().getResources().getString(R$string.IDS_health_model_edit_error));
    }

    public static void c() {
        Handler handler = b;
        if (handler == null) {
            LogUtil.h("HealthLife_HealthModelHandlerHelper", "releaseHandler sHandler is null");
        } else {
            handler.removeCallbacksAndMessages(null);
            b = null;
        }
    }
}
