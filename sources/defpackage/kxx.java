package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import health.compact.a.LogUtil;

/* loaded from: classes5.dex */
public class kxx {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f14690a = new Object();
    private static SharedPreferences b;
    private static Context c;
    private static kxx d;

    public static kxx b() {
        kxx kxxVar;
        synchronized (f14690a) {
            if (c == null) {
                c = BaseApplication.getContext();
            }
            if (d == null) {
                d = new kxx();
                b = c.getSharedPreferences("sleep_shared_pref_smart_msg", 0);
            }
            kxxVar = d;
        }
        return kxxVar;
    }

    public void a(String str) {
        String str2;
        if (c == null || str == null) {
            LogUtil.a("SharedPreferenceUtils", "mContext or resultData is null");
            return;
        }
        SharedPreferences sharedPreferences = b;
        if (sharedPreferences != null) {
            String string = sharedPreferences.getString("app_update_message", "");
            if (TextUtils.isEmpty(string)) {
                str2 = str;
            } else {
                str2 = (string + ",") + str;
            }
            LogUtil.c("SharedPreferenceUtils", "set appUpdateMsgInsertRecord is :", str);
            b.edit().putString("app_update_message", str2).commit();
        }
    }

    public String c() {
        SharedPreferences sharedPreferences;
        if (c == null || (sharedPreferences = b) == null) {
            LogUtil.e("SharedPreferenceUtils", "mContext or mSharedPreferences is null");
            return "";
        }
        return sharedPreferences.getString("app_update_message", "");
    }
}
