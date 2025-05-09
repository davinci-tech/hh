package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.Map;

/* loaded from: classes6.dex */
public class mct {
    public static void b(Context context, String str, String str2) {
        String accountInfo = LoginInit.getInstance(context).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            LogUtil.a("SharedPreferenceUtil", "setSharedPreference huid null");
            return;
        }
        SharedPreferenceManager.e(context, String.valueOf(20003), accountInfo + str, str2, (StorageParams) null);
    }

    public static String b(Context context, String str) {
        if (str == null) {
            return "";
        }
        String accountInfo = LoginInit.getInstance(context).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            LogUtil.a("SharedPreferenceUtil", "getSharedPreference huid null");
            return "";
        }
        String b = SharedPreferenceManager.b(context, String.valueOf(20003), accountInfo + str);
        return TextUtils.isEmpty(b) ? "" : b;
    }

    public static void b(Context context, Map<String, String> map) {
        if (context == null || map == null) {
            return;
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (key != null && value != null) {
                b(context, key, value);
            }
        }
    }

    public static void e(Context context, String str) {
        if (context == null || str == null) {
            return;
        }
        SharedPreferenceManager.c(context, String.valueOf(20003), str);
    }

    public static String e(Context context, String str, String str2) {
        String b = b(context, str);
        return TextUtils.isEmpty(b) ? str2 : b;
    }
}
