package com.huawei.agconnect.credential.obs;

import com.huawei.agconnect.datastore.annotation.DefaultCrypto;
import com.huawei.agconnect.datastore.core.CryptoUtil;
import com.huawei.agconnect.datastore.core.SharedPrefUtil;

/* loaded from: classes2.dex */
public class h {

    /* renamed from: a, reason: collision with root package name */
    private static h f1771a;
    private SharedPrefUtil b = SharedPrefUtil.getInstance();
    private CryptoUtil c;

    public void f(g gVar) {
        if (gVar != null) {
            gVar.fish = (String) this.b.get("com.huawei.appgallery.datastore", "fish", String.class, gVar.fish, DefaultCrypto.class);
        }
    }

    public void e(g gVar) {
        if (gVar != null) {
            gVar.jerry = (String) this.b.get("com.huawei.agconnect.internal", "jerry", String.class, gVar.jerry, DefaultCrypto.class);
        }
    }

    public void d(g gVar) {
        e(gVar);
        f(gVar);
    }

    public void d() {
        this.b.remove("com.huawei.appgallery.datastore", "fish");
    }

    public void c(g gVar) {
        if (gVar != null) {
            this.b.put("com.huawei.appgallery.datastore", "fish", String.class, gVar.fish, DefaultCrypto.class);
        }
    }

    public void c() {
        this.b.remove("com.huawei.agconnect.internal", "jerry");
    }

    public void b(g gVar) {
        if (gVar != null) {
            this.b.put("com.huawei.agconnect.internal", "jerry", String.class, gVar.jerry, DefaultCrypto.class);
        }
    }

    public void b() {
        c();
        d();
    }

    public void a(g gVar) {
        b(gVar);
        c(gVar);
    }

    public static h a() {
        h hVar;
        synchronized (h.class) {
            if (f1771a == null) {
                f1771a = new h();
            }
            hVar = f1771a;
        }
        return hVar;
    }

    private h() {
    }
}
