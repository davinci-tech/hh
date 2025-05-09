package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import com.huawei.haf.router.AppRouter;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.AccountConstants;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.OpAnalyticsUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes4.dex */
public class glz {
    private static Map<String, Integer> b = new HashMap();
    private static String c;
    private static long d;

    public static boolean a() {
        if (!LanguageUtil.m(BaseApplication.getContext())) {
            LogUtil.a("UserInfoActivityUtil", "not chineseSimplify");
            return false;
        }
        if (Utils.o()) {
            LogUtil.a("UserInfoActivityUtil", "oversea version, dont collect label");
            return false;
        }
        if (!LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode()) {
            return true;
        }
        LogUtil.a("UserInfoActivityUtil", "browse mode, dont collect label");
        return false;
    }

    public static boolean c() {
        if (CommonUtil.bu() || !Utils.i()) {
            return false;
        }
        return !CommonUtil.aa(BaseApplication.getContext());
    }

    public static boolean b() {
        if (TextUtils.isEmpty(c)) {
            c = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10000), AccountConstants.HEALTH_APP_THIRD_LOGIN);
        }
        return !Utils.i() || "1".equals(c);
    }

    public static boolean e() {
        return Utils.i() && CommonUtil.z(BaseApplication.getContext());
    }

    public static boolean d() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime - d < 500) {
            return true;
        }
        d = elapsedRealtime;
        return false;
    }

    public static void b(Context context, double d2, double d3) {
        if (context == null) {
            LogUtil.h("UserInfoActivityUtil", "joinInputActivity() context is null");
            return;
        }
        HashMap hashMap = new HashMap(1);
        hashMap.put("click", 1);
        ixx.d().d(context, AnalyticsValue.HEALTH_HEALTH_WEIGHT_DETAIL_RECORD_2030010.value(), hashMap, 0);
        Bundle bundle = new Bundle();
        bundle.putBoolean("isShowInput", true);
        bundle.putString("weight", String.valueOf(d2));
        bundle.putString("bodyFat", String.valueOf(d3));
        AppRouter.b("/Main/InputWeightActivity").zF_(bundle).c(context);
    }

    public static int a(float f) {
        return new BigDecimal(f).setScale(0, 4).intValue();
    }

    public static void b(String str) {
        if (b.containsKey(str)) {
            int intValue = b.get(str).intValue();
            b.put(str, Integer.valueOf(intValue + 1));
            if (intValue >= 50) {
                OpAnalyticsUtil.getInstance().setProbabilityProblemEvent("setReporting queryUserInfo", str + "\ngetStackTrace: " + Arrays.asList(Thread.currentThread().getStackTrace()).toString());
                b.put(str, 0);
                return;
            }
            return;
        }
        b.put(str, 0);
    }
}
