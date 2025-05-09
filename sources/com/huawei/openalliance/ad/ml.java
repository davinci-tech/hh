package com.huawei.openalliance.ad;

import com.huawei.openalliance.ad.beans.metadata.Om;
import com.iab.omid.library.huawei.adsession.VerificationScriptResource;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class ml {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f7266a = ma.a("com.iab.omid.library.huawei.adsession.VerificationScriptResource");
    private List<VerificationScriptResource> b = new ArrayList();

    public List<VerificationScriptResource> b() {
        return this.b;
    }

    public void a(Om om) {
        if (om == null || !f7266a) {
            ho.b("VerficationScriptResourceWrapper", "om is not avalible");
            return;
        }
        String a2 = om.a();
        URL a3 = a(om.b());
        String c = om.c();
        if (a2 == null || a3 == null || c == null) {
            ho.b("VerficationScriptResourceWrapper", "Parameters is null");
            return;
        }
        VerificationScriptResource createVerificationScriptResourceWithParameters = VerificationScriptResource.createVerificationScriptResourceWithParameters(a2, a3, c);
        if (createVerificationScriptResourceWithParameters == null) {
            ho.b("VerficationScriptResourceWrapper", "Create verificationScriptResource failed");
        } else {
            this.b.add(createVerificationScriptResourceWithParameters);
        }
    }

    public static boolean a() {
        return f7266a;
    }

    private URL a(String str) {
        if (str == null) {
            return null;
        }
        try {
            return new URL(str);
        } catch (MalformedURLException e) {
            ho.c("VerficationScriptResourceWrapper", "parseURL: " + com.huawei.openalliance.ad.utils.dl.a(e.getMessage()));
            return null;
        }
    }
}
