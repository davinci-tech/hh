package defpackage;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes6.dex */
public class ncd {
    public static int b(Context context) {
        int i;
        if (!c() || !e(context)) {
            i = 1;
        } else if (!a(context)) {
            i = 2;
        } else if (!h(context)) {
            i = 3;
        } else if (!d(context)) {
            i = 13;
        } else if (!g(context)) {
            i = 4;
        } else if (!j(context)) {
            i = 14;
        } else if (f(context)) {
            LogUtil.c("ErrorStatusUtils", "getEsimErrorStatus else branch");
            i = 0;
        } else {
            i = 6;
        }
        LogUtil.a("ErrorStatusUtils", "getEsimErrorStatus errorStatus = ", Integer.valueOf(i));
        return i;
    }

    public static String a(Context context, int i) {
        if (i == 1) {
            return context.getResources().getString(R.string._2130848008_res_0x7f022908);
        }
        if (i == 2) {
            return context.getResources().getString(R.string._2130848067_res_0x7f022943);
        }
        if (i == 3) {
            return context.getResources().getString(R.string._2130848121_res_0x7f022979);
        }
        if (i == 6) {
            return context.getResources().getString(R.string.IDS_plugin_multi_esim_device_no_support);
        }
        if (i == 7) {
            return context.getResources().getString(R.string.IDS_plugin_device_info_fail);
        }
        if (i == 8) {
            return context.getResources().getString(R.string._2130848093_res_0x7f02295d);
        }
        if (i == 13) {
            return context.getResources().getString(R.string._2130848109_res_0x7f02296d);
        }
        if (i == 14) {
            return context.getResources().getString(R.string._2130848120_res_0x7f022978);
        }
        return c(context, i);
    }

    private static String c(Context context, int i) {
        if (i == 4) {
            return c(context);
        }
        if (i == 11) {
            return context.getResources().getString(R.string._2130848018_res_0x7f022912);
        }
        if (i == 12) {
            return context.getResources().getString(R.string._2130848024_res_0x7f022918);
        }
        LogUtil.h("ErrorStatusUtils", "getEsimOtherErrorTips default");
        return "";
    }

    private static String c(Context context) {
        List<Map<String, Object>> a2 = ncf.a(context);
        if (!a2.isEmpty()) {
            if (a2.size() == 1) {
                String b = b(context, a2.get(0));
                if (!TextUtils.isEmpty(b)) {
                    return String.format(Locale.ENGLISH, context.getString(R.string._2130848045_res_0x7f02292d), b);
                }
            }
            return context.getString(R.string._2130848046_res_0x7f02292e);
        }
        return context.getString(R.string._2130847998_res_0x7f0228fe);
    }

    private static String b(Context context, Map<String, Object> map) {
        nca e;
        String b = ncf.b(context, map.get("imsi") instanceof String ? (String) map.get("imsi") : "");
        if (TextUtils.isEmpty(b) && (map.get("spn") instanceof String)) {
            b = (String) map.get("spn");
        }
        return (!TextUtils.isEmpty(b) || (e = nce.e(map)) == null) ? b : e.b();
    }

    public static boolean c() {
        int g = iyl.d().g();
        LogUtil.a("ErrorStatusUtils", "bluetoothSwitchState = ", Integer.valueOf(g));
        return g == 3;
    }

    public static boolean e(Context context) {
        int c = ktx.e().c();
        LogUtil.a("ErrorStatusUtils", "connectStatus = ", Integer.valueOf(c));
        return c == 2;
    }

    public static boolean a(Context context) {
        Object systemService = context.getSystemService("connectivity");
        boolean z = false;
        if (systemService instanceof ConnectivityManager) {
            ConnectivityManager connectivityManager = (ConnectivityManager) systemService;
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(0);
            NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(1);
            if ((networkInfo != null && networkInfo.isConnected()) || (networkInfo2 != null && networkInfo2.isConnected())) {
                z = true;
            }
        }
        LogUtil.a("isNetWorkConnected = ", Boolean.valueOf(z));
        return z;
    }

    private static boolean h(Context context) {
        String[] strArr;
        if (Build.VERSION.SDK_INT > 29) {
            strArr = new String[]{"android.permission.READ_PHONE_NUMBERS"};
        } else {
            strArr = new String[]{"android.permission.READ_PHONE_STATE"};
        }
        if (jdi.c(context, strArr)) {
            return true;
        }
        LogUtil.h("ErrorStatusUtils", "getDefaultSimCardInfo missing phonePermission");
        return false;
    }

    public static boolean d(Context context) {
        Object systemService = context.getSystemService("phone");
        boolean z = false;
        if (!(systemService instanceof TelephonyManager)) {
            LogUtil.h("ErrorStatusUtils", "get system service error");
            return false;
        }
        int simState = ((TelephonyManager) systemService).getSimState();
        LogUtil.a("ErrorStatusUtils", "simState = ", Integer.valueOf(simState));
        if (simState != 0 && simState != 1) {
            z = true;
        }
        LogUtil.a("ErrorStatusUtils", "isHaveSimCard = ", Boolean.valueOf(z));
        return z;
    }

    private static boolean j(Context context) {
        List<Map<String, Object>> a2 = ncf.a(context);
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= a2.size()) {
                break;
            }
            if (!TextUtils.isEmpty(a2.get(i).get("imsi") instanceof String ? (String) a2.get(i).get("imsi") : "")) {
                z = true;
                break;
            }
            i++;
        }
        ReleaseLogUtil.e("R_SportRecordImageDownloadUtil", "isHasImsiInformation = ", Boolean.valueOf(z));
        return z;
    }

    private static boolean g(Context context) {
        boolean z = true;
        if (!nce.d()) {
            return true;
        }
        List<Map<String, Object>> a2 = ncf.a(context);
        int i = 0;
        while (true) {
            if (i >= a2.size()) {
                z = false;
                break;
            }
            nca e = nce.e(a2.get(i));
            if (e != null && !TextUtils.isEmpty(e.a())) {
                break;
            }
            i++;
        }
        LogUtil.a("ErrorStatusUtils", "isHaveSupportCard = ", Boolean.valueOf(z));
        return z;
    }

    private static boolean f(Context context) {
        boolean m = ktx.e().m();
        LogUtil.a("ErrorStatusUtils", "isSupportNewEsim = ", Boolean.valueOf(m));
        return m;
    }

    public static boolean b(String str, int i) {
        if (i != 2 || CommonUtil.bh()) {
            return false;
        }
        return !ncf.e() || "52001".equals(str) || "52003".equals(str);
    }
}
