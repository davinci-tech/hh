package com.huawei.hms.iap.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.huawei.hms.iapfull.IapFullAPIFactory;
import com.huawei.hms.support.log.HMSLog;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class a {
    private static boolean b(String str, String str2) {
        if (a()) {
            return IapFullAPIFactory.isCountryAndCurrencySupport(str, str2);
        }
        return false;
    }

    private static boolean b(String str) {
        try {
            return "fullsdk".equals(new JSONObject(new JSONObject(str).optString("accountInfo")).optString("routeMode"));
        } catch (JSONException unused) {
            HMSLog.e("FullSdkUtils", "isRouteToFullSDK: JSONException");
            return false;
        }
    }

    private static boolean b(Context context, String str) {
        StringBuilder sb;
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                return packageManager.getPackageInfo(str, 16384) != null;
            }
        } catch (PackageManager.NameNotFoundException unused) {
            StringBuilder sb2 = new StringBuilder("isAppInstalled NameNotFoundException:");
            sb2.append(str);
            sb = sb2;
            HMSLog.w("FullSdkUtils", sb.toString());
            return false;
        } catch (RuntimeException e) {
            sb = new StringBuilder("isAppInstalled RuntimeException:");
            sb.append(e.getMessage());
            HMSLog.w("FullSdkUtils", sb.toString());
            return false;
        }
        return false;
    }

    private static boolean a(String str, String str2) {
        if (a()) {
            return IapFullAPIFactory.isWebPaySupport(str, str2);
        }
        return false;
    }

    private static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            HMSLog.i("FullSdkUtils", "reservedInfor is null!");
            return false;
        }
        try {
        } catch (JSONException unused) {
            HMSLog.e("FullSdkUtils", "getAccountInfo: JSONException");
        }
        return new JSONObject(str).has("accountInfo");
    }

    public static boolean a(Context context, String str, String str2, String str3, String str4) {
        return !a(context) && a(str) && (a(str, str4) || b(str2, str3));
    }

    public static boolean a(Context context, String str, String str2) {
        return !a(context) && a(str) && a(str, str2);
    }

    public static boolean a(Context context, String str) {
        String str2;
        if (!a()) {
            str2 = "canUseFullSdk no fullsdk";
        } else {
            if (a(str)) {
                if (!b(str)) {
                    return !a(context);
                }
                HMSLog.i("FullSdkUtils", "canUseFullSdk route to fullsdk");
                return true;
            }
            str2 = "canUseFullSdk no reserved account info";
        }
        HMSLog.i("FullSdkUtils", str2);
        return false;
    }

    private static boolean a(Context context) {
        if (context == null) {
            HMSLog.e("FullSdkUtils", "context is null");
            return false;
        }
        if (b(context, "com.huawei.hwid") || b(context, "com.huawei.hms") || b(context, "com.huawei.hwid.tv")) {
            HMSLog.i("FullSdkUtils", "isHMSCoreInstalled true");
            return true;
        }
        HMSLog.w("FullSdkUtils", "isHMSCoreInstalled false");
        return false;
    }

    private static boolean a() {
        try {
            Class.forName("com.huawei.hms.iapfull.pay.PayActivity");
            return true;
        } catch (ClassNotFoundException e) {
            HMSLog.w("FullSdkUtils", "className not found" + e.getMessage());
            return false;
        }
    }
}
