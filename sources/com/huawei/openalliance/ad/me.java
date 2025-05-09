package com.huawei.openalliance.ad;

import com.iab.omid.library.huawei.adsession.AdSessionConfiguration;

/* loaded from: classes5.dex */
public class me {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f7203a = ma.a("com.iab.omid.library.huawei.adsession.AdSessionConfiguration");
    private AdSessionConfiguration b;

    public AdSessionConfiguration b() {
        return this.b;
    }

    public static boolean a() {
        return f7203a;
    }

    public static me a(mg mgVar, mj mjVar, mk mkVar, mk mkVar2, boolean z) {
        if (f7203a) {
            return new me(mgVar, mjVar, mkVar, mkVar2, z);
        }
        return null;
    }

    private me(mg mgVar, mj mjVar, mk mkVar, mk mkVar2, boolean z) {
        this.b = null;
        if (mg.a() && mj.a() && mk.a()) {
            this.b = AdSessionConfiguration.createAdSessionConfiguration(mg.a(mgVar), mj.a(mjVar), mk.a(mkVar), mk.a(mkVar2), z);
        }
    }
}
