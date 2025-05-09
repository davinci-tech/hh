package com.huawei.openalliance.ad;

import android.content.Context;
import com.iab.omid.library.huawei.adsession.AdSessionContext;
import com.iab.omid.library.huawei.adsession.Partner;
import com.iab.omid.library.huawei.adsession.VerificationScriptResource;
import java.io.IOException;
import java.util.List;

/* loaded from: classes5.dex */
public class mf {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f7257a = ma.a("com.iab.omid.library.huawei.adsession.AdSessionContext");
    private Context b;

    public AdSessionContext a(ml mlVar, String str) {
        String str2;
        if (!ma.a("com.iab.omid.library.huawei.adsession.Partner") || !ma.a("com.iab.omid.library.huawei.adsession.VerificationScriptResource") || !ma.a("com.iab.omid.library.huawei.adsession.AdSessionContext")) {
            ho.c("AdSessionContextWrapper", "createNativeAdSessionContext, not available ");
            return null;
        }
        List<VerificationScriptResource> b = mlVar.b();
        if (b.isEmpty()) {
            return null;
        }
        try {
            str2 = com.huawei.openalliance.ad.utils.cz.a("openmeasure/omsdk-v1.js", this.b);
        } catch (IOException e) {
            ho.c("AdSessionContextWrapper", "getNativeAdSession: " + com.huawei.openalliance.ad.utils.dl.a(e.getMessage()));
            str2 = null;
        }
        if (str2 == null) {
            return null;
        }
        return AdSessionContext.createNativeAdSessionContext(Partner.createPartner("Huawei", "3.4.74.310"), str2, b, str, (String) null);
    }

    public static boolean a() {
        return f7257a;
    }

    public mf(Context context) {
        this.b = context;
    }
}
