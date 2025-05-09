package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import health.compact.a.LogUtil;
import java.nio.charset.StandardCharsets;

/* loaded from: classes3.dex */
public class bmf {

    /* renamed from: a, reason: collision with root package name */
    private static Context f435a = BaseApplication.e();

    public static String d(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("SharePreferenceUtil", "getSecretKeyFromSharedPreferences with parameter incorrect.");
            return "";
        }
        String e = bgv.e(str);
        if (i == 1) {
            return d(e, "share_preference_order_one");
        }
        if (i == 2) {
            return d(e, "share_preference_order_two");
        }
        LogUtil.a("SharePreferenceUtil", "getSecretKeyFromSharedPreferences keyOrder unknown.");
        return "";
    }

    public static boolean d(String str, int i, String str2) {
        if (TextUtils.isEmpty(str) || str2 == null) {
            LogUtil.a("SharePreferenceUtil", "saveSecretKeyToSharedPreferences with parameter incorrect.");
            return false;
        }
        LogUtil.c("SharePreferenceUtil", "deviceIdentify: ", blt.b(str), " keyOrder: ", Integer.valueOf(i), " secretKey: ", blt.b(str2), " secretKeyLength: ", Integer.valueOf(str2.length()));
        String e = bgv.e(str);
        if (i == 1) {
            return b(e, str2, "share_preference_order_one");
        }
        if (i == 2) {
            return b(e, str2, "share_preference_order_two");
        }
        LogUtil.a("SharePreferenceUtil", "saveSecretKeyToSharedPreferences keyOrder unknown.");
        return false;
    }

    public static void e() {
        LogUtil.c("SharePreferenceUtil", "clearSecretKey");
        e("share_preference_order_one");
        e("share_preference_order_two");
    }

    public static boolean c(String str, String str2) {
        if (TextUtils.isEmpty(str) || str2 == null) {
            LogUtil.a("SharePreferenceUtil", "saveBindIdToSharedPreferences with parameter incorrect.");
            return false;
        }
        return b(bgv.e(str), str2, "btsdk_sharedpreferences_bindid");
    }

    public static String c(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("SharePreferenceUtil", "getBindIdFromSharedPreferences with parameter incorrect.");
            return "";
        }
        return d(bgv.e(str), "btsdk_sharedpreferences_bindid");
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("SharePreferenceUtil", "getUpdateKeySuccessLabelShared with parameter incorrect.");
            return "";
        }
        return d(bgv.e(str), "btsdk_sharedpreferences_update_key_label");
    }

    public static boolean a(String str, String str2) {
        if (TextUtils.isEmpty(str) || str2 == null) {
            LogUtil.a("SharePreferenceUtil", "saveUpdateKeySuccessLabelShared with parameter incorrect.");
            return false;
        }
        return b(bgv.e(str), str2, "btsdk_sharedpreferences_update_key_label");
    }

    public static boolean e(String str, String str2) {
        if (TextUtils.isEmpty(str) || str2 == null) {
            LogUtil.a("SharePreferenceUtil", "saveIdentifyKeyToSharedPreferences with parameter incorrect.");
            return false;
        }
        return b(bgv.e(str), str2, "btsdk_sharedpreferences_identify_key");
    }

    public static boolean b(String str, String str2) {
        if (TextUtils.isEmpty(str) || str2 == null) {
            LogUtil.a("SharePreferenceUtil", "saveIdentifyKeyToSharedPreferences with parameter incorrect.");
            return false;
        }
        return b(bgv.e(str), str2, "btsdk_sharedpreferences_identify_key_hm");
    }

    public static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("SharePreferenceUtil", "getIdentifyKeyFromSharedPreferences with parameter incorrect.");
            return "";
        }
        return d(bgv.e(str), "btsdk_sharedpreferences_identify_key");
    }

    public static boolean d(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("SharePreferenceUtil", "savePhoneUdidToSharedPreferences with parameter incorrect.");
            return false;
        }
        return b("btsdk_sharedpreferences_phone_udid_key", bmy.b(str), "btsdk_sharedpreferences_phone_udid");
    }

    public static String a() {
        String d = d("btsdk_sharedpreferences_phone_udid_key", "btsdk_sharedpreferences_phone_udid");
        byte[] c = bmy.c(d);
        return c != null ? new String(c, StandardCharsets.UTF_8) : d;
    }

    private static boolean b(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str3) || str2 == null) {
            LogUtil.a("SharePreferenceUtil", "saveInfoToSharedPreferences failed with invalid parameter");
            return false;
        }
        SharedPreferences.Editor edit = f435a.getSharedPreferences(str3, 0).edit();
        edit.putString(str, str2);
        LogUtil.c("SharePreferenceUtil", "saveInfoToSharedPreferences()");
        edit.commit();
        return true;
    }

    private static String d(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.a("SharePreferenceUtil", "getDataFromSharedPreferences failed with invalid parameter");
            return "";
        }
        SharedPreferences sharedPreferences = f435a.getSharedPreferences(str2, 0);
        if (sharedPreferences == null) {
            LogUtil.a("SharePreferenceUtil", "getDataFromSharedPreferences failed with preferences error");
            return "";
        }
        String string = sharedPreferences.getString(str, "");
        LogUtil.c("SharePreferenceUtil", "getDataFromSharedPreferences ok");
        return string;
    }

    private static void e(String str) {
        f435a.getSharedPreferences(str, 0).edit().clear().commit();
    }
}
