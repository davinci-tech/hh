package defpackage;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Process;
import android.text.TextUtils;
import com.huawei.hihealth.HiAppInfo;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealthkit.context.QuickAppInfo;
import com.huawei.hwauthutil.HsfSignValidator;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HuaweiHealth;
import health.compact.a.Utils;

/* loaded from: classes7.dex */
public class ivw {
    private static final int b = Process.myUid();

    public static String d(int i) {
        if (i != 1) {
            if (i == 2) {
                return "com.huawei.bone";
            }
            if (i != 1001) {
                return i != 1002 ? "unknown" : "com.huawei.bone";
            }
        }
        return HuaweiHealth.b();
    }

    public static int b(String str) {
        if (HuaweiHealth.b().equals(str)) {
            return 1;
        }
        return "com.huawei.bone".equals(str) ? 2 : 0;
    }

    public static String a(String str) {
        if (HuaweiHealth.b().equals(str)) {
            return HuaweiHealth.b();
        }
        return "com.huawei.bone".equals(str) ? "华为穿戴" : "com.huawei.ah100".equals(str) ? "华为体脂称" : "未知APP";
    }

    public static String a(Context context) {
        if (context == null) {
            return null;
        }
        if (Binder.getCallingUid() == b) {
            return HuaweiHealth.b();
        }
        String a2 = iwi.a(context);
        if (!TextUtils.isEmpty(a2)) {
            if (!Utils.i()) {
                if ("com.huawei.bone".compareTo(a2) == 0 && !HsfSignValidator.c(a2)) {
                    LogUtil.h("HiH_HiAppUtil", "getBinderPackageName isAppValid verify fail packageName = ", a2);
                    throw new SecurityException("getBinderPackageName isAppValid Illegal APP");
                }
                if (iwk.b(a2) && HsfSignValidator.c(a2)) {
                    LogUtil.a("HiH_HiAppUtil", "getBinderPackageName currentapp will change packageName to health, current is ", a2);
                    return HuaweiHealth.b();
                }
            }
            if (a2.contains("android.uid.system")) {
                LogUtil.a("HiH_HiAppUtil", "getBinderPackageName packageName = ", a2);
                return HuaweiHealth.b();
            }
        }
        LogUtil.c("HiH_HiAppUtil", "getBinderPackageName packageName = ", a2);
        return a2;
    }

    public static String e(Context context) {
        return e(context, a(context));
    }

    public static String e(Context context, String str) {
        if (context == null) {
            LogUtil.h("HiH_HiAppUtil", "getInsertBinderPackageName context is null");
            return null;
        }
        if (HuaweiHealth.b().equals(str)) {
            return HuaweiHealth.b();
        }
        if (a(context, str)) {
            int a2 = iip.b().a(str);
            if (a2 <= 0) {
                a2 = (int) iip.b().e(b(context, str), 0);
            }
            LogUtil.a("HiH_HiAppUtil", "getInsertBinderPackageName() app = ", Integer.valueOf(a2), ", packageName = ", str);
            String a3 = iio.c().a(a2);
            if (a3 == null || "0".equals(a3) || str.equals(a3)) {
                LogUtil.a("HiH_HiAppUtil", "getInsertBinderPackageName() change packageName : com.huawei.health");
                return HuaweiHealth.b();
            }
        }
        LogUtil.a("HiH_HiAppUtil", "getInsertBinderPackageName packageName = ", str);
        return str;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static boolean a(Context context, String str) {
        char c;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        str.hashCode();
        switch (str.hashCode()) {
            case -1923356010:
                if (str.equals("com.huawei.health")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1819812587:
                if (str.equals(BaseApplication.APP_PACKAGE_GOOGLE_HEALTH)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1755550878:
                if (str.equals("com.huawei.ohos.healthservice")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -846861954:
                if (str.equals("com.huawei.bone")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -484109072:
                if (str.equals("com.huawei.ah100")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1380553715:
                if (str.equals("com.huawei.ohos.health")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1390569649:
                if (str.equals("com.huawei.ohos.health.device")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 2010617015:
                if (str.equals("com.huawei.audioaccessorymanager")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 3:
            case 4:
                if (!HsfSignValidator.c(str)) {
                    LogUtil.h("HiH_HiAppUtil", "isAppValid verify fail packageName = ", str);
                    return false;
                }
            case 0:
            case 1:
            case 2:
            case 5:
            case 6:
            case 7:
                return true;
            default:
                return false;
        }
    }

    public static HiAppInfo b(Context context, String str) {
        if (context == null) {
            return null;
        }
        PackageManager packageManager = context.getPackageManager();
        HiAppInfo hiAppInfo = new HiAppInfo();
        hiAppInfo.setPackageName(str);
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 0);
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 0);
            hiAppInfo.setVersion(packageInfo.versionName);
            hiAppInfo.setAppName((String) packageManager.getApplicationLabel(applicationInfo));
            hiAppInfo.setSignature(HiJsonUtil.e(packageInfo.signatures));
        } catch (PackageManager.NameNotFoundException e) {
            LogUtil.b("HiH_HiAppUtil", "createExplicitIntent() exception = ", e.getMessage());
        }
        return hiAppInfo;
    }

    public static HiAppInfo c(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return null;
        }
        PackageManager packageManager = context.getPackageManager();
        HiAppInfo hiAppInfo = new HiAppInfo();
        hiAppInfo.setPackageName(str);
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 0);
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 64);
            String e = HsfSignValidator.e(context, str);
            int i = applicationInfo.uid;
            hiAppInfo.setVersion(packageInfo.versionName);
            if (packageManager.getApplicationLabel(applicationInfo) instanceof String) {
                hiAppInfo.setAppName((String) packageManager.getApplicationLabel(applicationInfo));
            }
            hiAppInfo.setSignature(a(e, i));
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.b("HiH_HiAppUtil", "getKitAppInfo() NameNotFoundException");
        }
        return hiAppInfo;
    }

    public static HiAppInfo b(QuickAppInfo quickAppInfo) {
        if (quickAppInfo == null) {
            return null;
        }
        HiAppInfo hiAppInfo = new HiAppInfo();
        hiAppInfo.setPackageName(quickAppInfo.getPackageName());
        hiAppInfo.setAppName("QuickApp_".concat(quickAppInfo.getName()));
        hiAppInfo.setSignature(quickAppInfo.getFingerPrint() + "##" + quickAppInfo.getAppId());
        return hiAppInfo;
    }

    public static com.huawei.health.HiAppInfo c(QuickAppInfo quickAppInfo) {
        if (quickAppInfo == null) {
            return null;
        }
        com.huawei.health.HiAppInfo hiAppInfo = new com.huawei.health.HiAppInfo();
        hiAppInfo.setPackageName(quickAppInfo.getPackageName());
        hiAppInfo.setAppName(quickAppInfo.getName());
        return hiAppInfo;
    }

    public static com.huawei.health.HiAppInfo b(Context context) {
        String str;
        PackageManager packageManager = context.getPackageManager();
        String a2 = iwi.a(context);
        if (a2 == null) {
            return new com.huawei.health.HiAppInfo();
        }
        try {
            str = packageManager.getApplicationLabel(packageManager.getApplicationInfo(a2, 128)).toString();
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.b("HiH_HiAppUtil", "getPhoneKitAppInfo NameNotFoundException");
            str = null;
        }
        com.huawei.health.HiAppInfo hiAppInfo = new com.huawei.health.HiAppInfo();
        hiAppInfo.setPackageName(a2);
        hiAppInfo.setAppName(str);
        return hiAppInfo;
    }

    public static String a(String str, int i) {
        return str + "##" + String.valueOf(i);
    }
}
