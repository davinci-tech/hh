package com.huawei.agconnect.credential.obs;

import com.huawei.agconnect.common.api.AgcCrypto;
import com.huawei.agconnect.datastore.core.CryptoUtil;
import com.huawei.agconnect.datastore.core.SharedPrefUtil;

/* loaded from: classes8.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private static b f1762a;
    private SharedPrefUtil b = SharedPrefUtil.getInstance();
    private CryptoUtil c;

    public void d(a aVar) {
        if (aVar != null) {
            aVar.aaidString = (String) this.b.get("com.huawei.agconnect", c.f1765a, String.class, aVar.aaidString, AgcCrypto.class);
        }
    }

    public void c(a aVar) {
        d(aVar);
    }

    public void c() {
        this.b.remove("com.huawei.agconnect", c.f1765a);
    }

    public void b(a aVar) {
        if (aVar != null) {
            this.b.put("com.huawei.agconnect", c.f1765a, String.class, aVar.aaidString, AgcCrypto.class);
        }
    }

    public void b() {
        c();
    }

    public void a(a aVar) {
        b(aVar);
    }

    public static b a() {
        b bVar;
        synchronized (b.class) {
            if (f1762a == null) {
                f1762a = new b();
            }
            bVar = f1762a;
        }
        return bVar;
    }

    private b() {
    }
}
