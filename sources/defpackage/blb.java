package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import health.compact.a.LogUtil;

/* loaded from: classes3.dex */
public class blb {
    public static String c(String str, Context context) {
        if (str == null || context == null) {
            LogUtil.c("CommandPackage", "getKeyFromSharedPreferences with parameter incorrect.");
            return "";
        }
        String string = context.getSharedPreferences("btsdk_sharedpreferences_name4", 0).getString(str, "");
        LogUtil.c("CommandPackage", "getKeyFromSharedPreferences ok");
        return string;
    }

    public static void d(String str, String str2, String str3, Context context) {
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str3)) {
            return;
        }
        SharedPreferences.Editor edit = context.getSharedPreferences(str3, 0).edit();
        edit.putString(str, str2);
        edit.commit();
        LogUtil.c("CommandPackage", "putInfoToSharedPreferences ok");
    }

    public static String d(String str, Context context) {
        if (str == null || context == null) {
            LogUtil.c("CommandPackage", "getBindIdFromSharedPreferences with parameter incorrect.");
            return "";
        }
        String string = context.getSharedPreferences("btsdk_sharedpreferences_bindid", 0).getString(str, "");
        LogUtil.c("CommandPackage", "getBindIdFromSharedPreferences ok, bindId: ", blq.d(string));
        return string;
    }
}
