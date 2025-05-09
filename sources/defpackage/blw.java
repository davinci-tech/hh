package defpackage;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.huawei.haf.store.SharedStoreManager;
import health.compact.a.LogUtil;

/* loaded from: classes3.dex */
public class blw {
    public static String b(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.a("MultiProcessSpUtils", "getValue, name or key is null");
            return str3;
        }
        return SharedStoreManager.Ab_(str).getString(str2, str3);
    }

    public static void e(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.a("MultiProcessSpUtils", "setValue, name or key is null");
            return;
        }
        SharedPreferences.Editor edit = SharedStoreManager.Ab_(str).edit();
        edit.putString(str2, str3);
        edit.commit();
    }

    public static void b(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.a("MultiProcessSpUtils", "deleteKey, name or key is null");
            return;
        }
        SharedPreferences.Editor edit = SharedStoreManager.Ab_(str).edit();
        edit.remove(str2);
        edit.commit();
    }
}
