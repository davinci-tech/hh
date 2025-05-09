package com.huawei.hms.network.file.a.k.b.j;

import android.text.TextUtils;
import com.huawei.hms.framework.common.ContextHolder;
import com.huawei.hms.framework.common.PLSharedPreferences;
import com.huawei.secure.android.common.encrypt.utils.EncryptUtil;
import com.huawei.secure.android.common.encrypt.utils.RootKeyUtil;

/* loaded from: classes4.dex */
class c {

    /* renamed from: a, reason: collision with root package name */
    private static volatile RootKeyUtil f5616a;
    private static final Object b = new Object();

    private static String a(String str, boolean z) {
        PLSharedPreferences pLSharedPreferences = new PLSharedPreferences(ContextHolder.getAppContext(), str);
        String string = pLSharedPreferences.getString(str, "");
        if (!TextUtils.isEmpty(string) || !z) {
            return string;
        }
        String generateSecureRandomStr = EncryptUtil.generateSecureRandomStr(16);
        pLSharedPreferences.putString(str, generateSecureRandomStr);
        return generateSecureRandomStr;
    }

    static RootKeyUtil a() {
        if (f5616a == null) {
            synchronized (b) {
                if (f5616a == null) {
                    EncryptUtil.setBouncycastleFlag(true);
                    String a2 = a("cat", true);
                    String a3 = a("dog", true);
                    String a4 = a("pig", false);
                    if (TextUtils.isEmpty(a4)) {
                        a4 = "945e7f66c50dfefe6132c4acdf33005e";
                    }
                    f5616a = RootKeyUtil.newInstance(a2, a3, a4, a("salt", true));
                }
            }
        }
        return f5616a;
    }
}
