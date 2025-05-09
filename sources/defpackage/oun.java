package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.homehealth.HomeFragment;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class oun {

    /* renamed from: a, reason: collision with root package name */
    private static String f15965a = null;
    private static volatile boolean b = false;
    private Context c;
    private HomeFragment e;

    public oun(Context context, HomeFragment homeFragment) {
        LogUtil.a("StepsCardManager", "StepsCardManager");
        this.c = context;
        this.e = homeFragment;
    }

    public static boolean b() {
        return b;
    }

    public void b(boolean z) {
        b = z;
    }

    public static void d() {
        if (f15965a == null) {
            e(e());
        }
        LogUtil.a("StepsCardManager", "sDefaultType: ", f15965a);
    }

    public void a(String str) {
        f15965a = str;
    }

    public void b(String str) {
        LogUtil.a("StepsCardManager", "setDefaultDisplayType, type: ", str);
        f(str);
    }

    private static void e(String str) {
        LogUtil.a("StepsCardManager", "initCurDisPlayType, type: ", str);
        if (!TextUtils.isEmpty(str) && str.equals(f15965a)) {
            LogUtil.a("StepsCardManager", "initCurDisPlayType but type not change ");
        } else {
            f15965a = str;
            LogUtil.a("StepsCardManager", "initCurDisPlayType, res: ", Integer.valueOf(SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "SP_KEY_CUR_DISPLAY", str, (StorageParams) null)));
        }
    }

    private void f(String str) {
        LogUtil.a("StepsCardManager", "saveCurDisPlayType type ", str, " result ", Integer.valueOf(SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "SP_KEY_CUR_DISPLAY", str, (StorageParams) null)));
        if (!TextUtils.isEmpty(str) && str.equals(f15965a)) {
            LogUtil.a("StepsCardManager", "saveCurDisPlayType but type not change ");
        } else {
            f15965a = str;
        }
    }

    public static String a() {
        return f15965a;
    }

    public void d(String str) {
        if (nsn.aa(this.c)) {
            e(str, false);
        } else {
            e(str, true);
        }
    }

    public void e(String str, boolean z) {
        LogUtil.a("StepsCardManager", "notifyCardChanged: currentType: ", str, ", switchWithAnimation: ", Boolean.valueOf(z));
        this.e.b(str, z);
    }

    public void c(String str) {
        LogUtil.a("StepsCardManager", "setDisPlayTypeToSp, displayType: ", str);
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String str2 = "threeCircleCard";
        if (str.equals("threeCircleCard")) {
            str2 = "threeLeafCard";
        } else if (!str.equals("threeLeafCard")) {
            return;
        }
        LogUtil.a("StepsCardManager", "setDisPlayTypeToSp: ", str2);
        LogUtil.a("StepsCardManager", "setDisPlayTypeToSp, res: ", Integer.valueOf(SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "SP_KEY_STEP_CARD_AB", str2, (StorageParams) null)));
    }

    private static String e() {
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10000), "SP_KEY_STEP_CARD_AB");
        LogUtil.a("StepsCardManager", "strategyName: ", b2);
        if (TextUtils.isEmpty(b2)) {
            LogUtil.b("StepsCardManager", "json is null");
            return "threeCircleCard";
        }
        if (TextUtils.isEmpty(b2)) {
            LogUtil.b("StepsCardManager", "strategyName is null");
            return "threeCircleCard";
        }
        if (b2.equals("[B]")) {
            return "threeCircleCard";
        }
        if (b2.equals("threeCircleCard")) {
            b = true;
            return "threeCircleCard";
        }
        if (!b2.equals("threeLeafCard")) {
            return "threeCircleCard";
        }
        b = true;
        return "threeLeafCard";
    }

    public void b(String str, int i) {
        LogUtil.a("StepsCardManager", "biEventForSwitch currentType: ", str);
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        if ("threeCircleCard".equals(str)) {
            hashMap.put("model", "to_cloverLife");
            hashMap.put("reminderNum", Integer.valueOf(i));
        } else if ("threeLeafCard".equals(str)) {
            hashMap.put("model", "to_threeRing");
        } else {
            LogUtil.a("StepsCardManager", "no need bi");
        }
        ixx.d().d(this.c.getApplicationContext(), AnalyticsValue.HEALTH_SWITCHING_MODE_2010116.value(), hashMap, 0);
    }
}
