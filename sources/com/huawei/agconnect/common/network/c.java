package com.huawei.agconnect.common.network;

import com.huawei.agconnect.datastore.annotation.DefaultCrypto;
import com.huawei.agconnect.datastore.core.CryptoUtil;
import com.huawei.agconnect.datastore.core.SharedPrefUtil;

/* loaded from: classes8.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    private static c f1701a;
    private SharedPrefUtil b = SharedPrefUtil.getInstance();
    private CryptoUtil c;

    public void d(b bVar) {
        if (bVar != null) {
            bVar.enableAccessNetwork = ((Boolean) this.b.get("AGConnectAccessNetwork", "enableAccessNetwork", Boolean.class, Boolean.valueOf(bVar.enableAccessNetwork), DefaultCrypto.class)).booleanValue();
        }
    }

    public void c(b bVar) {
        d(bVar);
    }

    public void c() {
        this.b.remove("AGConnectAccessNetwork", "enableAccessNetwork");
    }

    public void b(b bVar) {
        if (bVar != null) {
            this.b.put("AGConnectAccessNetwork", "enableAccessNetwork", Boolean.class, Boolean.valueOf(bVar.enableAccessNetwork), DefaultCrypto.class);
        }
    }

    public void b() {
        c();
    }

    public void a(b bVar) {
        b(bVar);
    }

    public static c a() {
        c cVar;
        synchronized (c.class) {
            if (f1701a == null) {
                f1701a = new c();
            }
            cVar = f1701a;
        }
        return cVar;
    }

    private c() {
    }
}
