package defpackage;

import com.huawei.haf.store.SharedStoreManager;

/* loaded from: classes3.dex */
public class bkx {
    public static String c() {
        return b("AndroidId", "");
    }

    public static String a() {
        return b("DeviceSn", "");
    }

    private static String b(String str, String str2) {
        return SharedStoreManager.zZ_("CommonInfo").getString(str, str2);
    }
}
