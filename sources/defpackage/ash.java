package defpackage;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;

/* loaded from: classes.dex */
public class ash {
    public static int d(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str3)) {
            return -1;
        }
        return a(str, str3 + "_" + str2);
    }

    public static String d(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return "";
        }
        String b = b(str);
        String str3 = str2 + '_';
        return (b == null || !b.startsWith(str3)) ? "" : b.substring(str3.length());
    }

    public static int a(String str, String str2) {
        return jL_().edit().putString(str, str2).commit() ? 0 : -1;
    }

    public static String b(String str) {
        return jL_().getString(str, "");
    }

    private static SharedPreferences jL_() {
        return BaseApplication.getContext().getApplicationContext().getSharedPreferences("fit_SharedPreference", 0);
    }

    public static int d(String str) {
        SharedPreferences.Editor edit = jL_().edit();
        edit.remove(str);
        return edit.commit() ? 0 : -1;
    }
}
