package com.huawei.agconnect.credential.obs;

import com.huawei.agconnect.datastore.annotation.DefaultCrypto;
import com.huawei.agconnect.datastore.core.CryptoUtil;
import com.huawei.agconnect.datastore.core.SharedPrefUtil;
import com.huawei.pluginachievement.manager.model.MedalConstants;

/* loaded from: classes2.dex */
public class j {

    /* renamed from: a, reason: collision with root package name */
    private static j f1773a;
    private SharedPrefUtil b = SharedPrefUtil.getInstance();
    private CryptoUtil c;

    public void d(i iVar) {
        if (iVar != null) {
            iVar.randomKey = (String) this.b.get("com.huawei.appgallery.datastore", MedalConstants.EVENT_KEY, String.class, iVar.randomKey, DefaultCrypto.class);
        }
    }

    public void c(i iVar) {
        d(iVar);
    }

    public void c() {
        this.b.remove("com.huawei.appgallery.datastore", MedalConstants.EVENT_KEY);
    }

    public void b(i iVar) {
        if (iVar != null) {
            this.b.put("com.huawei.appgallery.datastore", MedalConstants.EVENT_KEY, String.class, iVar.randomKey, DefaultCrypto.class);
        }
    }

    public void b() {
        c();
    }

    public void a(i iVar) {
        b(iVar);
    }

    public static j a() {
        j jVar;
        synchronized (j.class) {
            if (f1773a == null) {
                f1773a = new j();
            }
            jVar = f1773a;
        }
        return jVar;
    }

    private j() {
    }
}
