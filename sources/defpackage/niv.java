package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import health.compact.a.SharedPreferenceManager;

/* loaded from: classes.dex */
public class niv {
    public static boolean a(Context context) {
        if (context == null) {
            context = BaseApplication.e();
        }
        String b = SharedPreferenceManager.b(context, String.valueOf(10000), "SP_KEY_CUR_DISPLAY");
        return TextUtils.isEmpty(b) || "stepCard".equals(b);
    }

    public static boolean e(Context context) {
        if (context == null) {
            context = BaseApplication.e();
        }
        String b = SharedPreferenceManager.b(context, String.valueOf(10000), "SP_KEY_CUR_DISPLAY");
        return !TextUtils.isEmpty(b) && "threeCircleCard".equals(b);
    }

    public static boolean b(Context context) {
        if (context == null) {
            context = BaseApplication.e();
        }
        String b = SharedPreferenceManager.b(context, String.valueOf(10000), "SP_KEY_CUR_DISPLAY");
        return !TextUtils.isEmpty(b) && "threeLeafCard".equals(b);
    }
}
