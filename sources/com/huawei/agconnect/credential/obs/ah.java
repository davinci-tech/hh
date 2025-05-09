package com.huawei.agconnect.credential.obs;

import com.huawei.agconnect.datastore.annotation.DefaultCrypto;
import com.huawei.agconnect.datastore.core.CryptoUtil;
import com.huawei.agconnect.datastore.core.SharedPrefUtil;

/* loaded from: classes2.dex */
public class ah {

    /* renamed from: a, reason: collision with root package name */
    private static ah f1745a;
    private SharedPrefUtil b = SharedPrefUtil.getInstance();
    private CryptoUtil c;

    public void a(ag agVar) {
    }

    public void b() {
    }

    public void b(ag agVar) {
    }

    public void f(ag agVar, String str) {
        if (agVar == null || str == null) {
            return;
        }
        agVar.validTime = ((Long) this.b.get("agc_site", "validTime_" + str, Long.class, null, DefaultCrypto.class)).longValue();
    }

    public void e(ag agVar, String str) {
        if (agVar == null || str == null) {
            return;
        }
        agVar.backUrl = (String) this.b.get("agc_site", "backup_" + str, String.class, null, DefaultCrypto.class);
    }

    public void d(ag agVar, String str) {
        if (agVar == null || str == null) {
            return;
        }
        agVar.mainUrl = (String) this.b.get("agc_site", "main_" + str, String.class, null, DefaultCrypto.class);
    }

    public void c(String str) {
        this.b.remove("agc_site", "validTime_" + str);
    }

    public void c(ag agVar, String str) {
        if (agVar == null || str == null) {
            return;
        }
        this.b.put("agc_site", "validTime_" + str, Long.class, Long.valueOf(agVar.validTime), DefaultCrypto.class);
    }

    public void b(String str) {
        this.b.remove("agc_site", "backup_" + str);
    }

    public void b(ag agVar, String str) {
        if (agVar == null || str == null) {
            return;
        }
        this.b.put("agc_site", "backup_" + str, String.class, agVar.backUrl, DefaultCrypto.class);
    }

    public void a(String str) {
        this.b.remove("agc_site", "main_" + str);
    }

    public void a(ag agVar, String str) {
        if (agVar == null || str == null) {
            return;
        }
        this.b.put("agc_site", "main_" + str, String.class, agVar.mainUrl, DefaultCrypto.class);
    }

    public static ah a() {
        ah ahVar;
        synchronized (ah.class) {
            if (f1745a == null) {
                f1745a = new ah();
            }
            ahVar = f1745a;
        }
        return ahVar;
    }

    private ah() {
    }
}
