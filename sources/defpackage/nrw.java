package defpackage;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;

/* loaded from: classes6.dex */
public class nrw {
    private static int c;

    public static void b(Context context, int i) {
        SharedPreferenceManager.e(context, Integer.toString(20002), "map_type_setting_key", Integer.toString(i), new StorageParams());
        SharedPreferenceManager.e(context, Integer.toString(20002), "auto_map_setting", Integer.toString(i), new StorageParams());
        c = i;
    }

    public static int a(Context context) {
        if (context == null) {
            LogUtil.h("Track_MapChangeUtils", "checkChinaOrAbroadByMCC context is null");
            return 0;
        }
        Object systemService = context.getSystemService("phone");
        if (!(systemService instanceof TelephonyManager)) {
            LogUtil.h("Track_MapChangeUtils", "checkChinaOrAbroadByMCC object is not instanceof TelephonyManager");
            return 0;
        }
        String networkOperator = ((TelephonyManager) systemService).getNetworkOperator();
        LogUtil.a("Track_MapChangeUtils", "checkChinaOrAbroadByMCC ", networkOperator);
        if (TextUtils.isEmpty(networkOperator) || d(networkOperator)) {
            LogUtil.a("Track_MapChangeUtils", "checkChinaOrAbroadByMCC: 0!");
            return 0;
        }
        if (b(networkOperator)) {
            LogUtil.a("Track_MapChangeUtils", "checkChinaOrAbroadByMCC: 1!");
            return 1;
        }
        LogUtil.a("Track_MapChangeUtils", "checkChinaOrAbroadByMCC: 2!");
        return 2;
    }

    public static boolean c(Context context) {
        if (context == null) {
            LogUtil.h("Track_MapChangeUtils", "checkSimCardStateIsOffLine context is null");
            return false;
        }
        Object systemService = context.getSystemService("phone");
        if (!(systemService instanceof TelephonyManager)) {
            LogUtil.h("Track_MapChangeUtils", "checkChinaOrAbroadByMCC object is not instanceof TelephonyManager");
            return false;
        }
        int simState = ((TelephonyManager) systemService).getSimState();
        LogUtil.a("Track_MapChangeUtils", "checkSimCardStateIsOffLine ", Integer.valueOf(simState));
        return simState == 1;
    }

    public static boolean e(Context context) {
        if (context == null) {
            LogUtil.h("Track_MapChangeUtils", "checkSimCardInChinaByMCC context is null");
            return false;
        }
        Object systemService = context.getSystemService("phone");
        if (!(systemService instanceof TelephonyManager)) {
            LogUtil.h("Track_MapChangeUtils", "checkChinaOrAbroadByMCC object is not instanceof TelephonyManager");
            return false;
        }
        String simOperator = ((TelephonyManager) systemService).getSimOperator();
        LogUtil.a("Track_MapChangeUtils", "checkSimCardInChinaByMCC simMccMnc ", simOperator);
        if (TextUtils.isEmpty(simOperator)) {
            return false;
        }
        return b(simOperator) || a(simOperator);
    }

    private static boolean a(String str) {
        return "204".equals(str.substring(0, 3)) && !Utils.o();
    }

    private static boolean b(String str) {
        if (str.length() < 3) {
            LogUtil.h("Track_MapChangeUtils", "isInChina mccAndMncCode.length() < 3");
            return false;
        }
        return Constants.DEFAULT_MCC_CODE.equals(str.substring(0, 3));
    }

    private static boolean d(String str) {
        if (str.length() < 3) {
            LogUtil.h("Track_MapChangeUtils", "isNoSim mccAndMncCode.length() < 3");
            return false;
        }
        return "000".equals(str.substring(0, 3));
    }

    public static void b(Context context) {
        String b = SharedPreferenceManager.b(context, Integer.toString(20002), "map_type_setting_key");
        if (b == null || "".equals(b)) {
            c = 0;
            return;
        }
        try {
            c = Integer.parseInt(b);
        } catch (NumberFormatException e) {
            LogUtil.a("Track_MapChangeUtils", "initMapType ", e.getMessage());
            c = 0;
        }
    }

    public static boolean a() {
        return c == 1;
    }

    public static boolean e() {
        return c == 2;
    }

    public static boolean c() {
        return c == 3;
    }
}
