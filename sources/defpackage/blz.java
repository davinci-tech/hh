package defpackage;

import android.content.SharedPreferences;
import com.huawei.haf.store.SharedStoreManager;
import health.compact.a.LogUtil;

/* loaded from: classes3.dex */
public class blz {
    public static String d(String str, String str2) {
        if (str == null) {
            LogUtil.a("MmkvUtils", "getValue, key is null");
            return str2;
        }
        return SharedStoreManager.zZ_("keyvaldb_unencrypt").getString(str, str2);
    }

    public static void a(String str, String str2) {
        if (str == null) {
            LogUtil.a("MmkvUtils", "setValue, key is null");
            return;
        }
        SharedPreferences.Editor edit = SharedStoreManager.zZ_("keyvaldb_unencrypt").edit();
        edit.putString(str, str2);
        edit.commit();
    }

    public static void c(String str) {
        if (str == null) {
            LogUtil.a("MmkvUtils", "deleteKey, key is null");
            return;
        }
        SharedPreferences.Editor edit = SharedStoreManager.zZ_("keyvaldb_unencrypt").edit();
        edit.remove(str);
        edit.commit();
    }
}
