package com.huawei.agconnect.credential.obs;

import com.huawei.agconnect.common.api.AgcCrypto;
import com.huawei.agconnect.datastore.core.CryptoUtil;
import com.huawei.agconnect.datastore.core.SharedPrefUtil;

/* loaded from: classes8.dex */
public class aq {

    /* renamed from: a, reason: collision with root package name */
    private static aq f1753a;
    private SharedPrefUtil b = SharedPrefUtil.getInstance();
    private CryptoUtil c;

    public void a(ap apVar) {
    }

    public void b() {
    }

    public void b(ap apVar) {
    }

    public void f(ap apVar, String str) {
        if (apVar == null || str == null) {
            return;
        }
        apVar.validTime = ((Long) this.b.get("com.huawei.agconnect", "validTime_" + str, Long.class, null, AgcCrypto.class)).longValue();
    }

    public void e(ap apVar, String str) {
        if (apVar == null || str == null) {
            return;
        }
        apVar.expires = ((Long) this.b.get("com.huawei.agconnect", "expires_" + str, Long.class, null, AgcCrypto.class)).longValue();
    }

    public void d(ap apVar, String str) {
        if (apVar == null || str == null) {
            return;
        }
        apVar.tokenString = (String) this.b.get("com.huawei.agconnect", "tokenString_" + str, String.class, null, AgcCrypto.class);
    }

    public void c(String str) {
        this.b.remove("com.huawei.agconnect", "validTime_" + str);
    }

    public void c(ap apVar, String str) {
        if (apVar == null || str == null) {
            return;
        }
        this.b.put("com.huawei.agconnect", "validTime_" + str, Long.class, Long.valueOf(apVar.validTime), AgcCrypto.class);
    }

    public void b(String str) {
        this.b.remove("com.huawei.agconnect", "expires_" + str);
    }

    public void b(ap apVar, String str) {
        if (apVar == null || str == null) {
            return;
        }
        this.b.put("com.huawei.agconnect", "expires_" + str, Long.class, Long.valueOf(apVar.expires), AgcCrypto.class);
    }

    public void a(String str) {
        this.b.remove("com.huawei.agconnect", "tokenString_" + str);
    }

    public void a(ap apVar, String str) {
        if (apVar == null || str == null) {
            return;
        }
        this.b.put("com.huawei.agconnect", "tokenString_" + str, String.class, apVar.tokenString, AgcCrypto.class);
    }

    public static aq a() {
        aq aqVar;
        synchronized (aq.class) {
            if (f1753a == null) {
                f1753a = new aq();
            }
            aqVar = f1753a;
        }
        return aqVar;
    }

    private aq() {
    }
}
