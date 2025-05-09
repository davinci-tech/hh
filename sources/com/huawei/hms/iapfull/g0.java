package com.huawei.hms.iapfull;

import android.content.Context;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import com.huawei.hms.framework.network.grs.GrsClient;
import java.util.Map;

/* loaded from: classes4.dex */
public class g0 {

    /* renamed from: a, reason: collision with root package name */
    public String f4711a;
    public String b;

    public void a(Context context, String str, f0 f0Var) {
        d1.a().a(new a(context, str, f0Var));
    }

    static class b {

        /* renamed from: a, reason: collision with root package name */
        private static final g0 f4713a = new g0(null);
    }

    public static g0 a() {
        return b.f4713a;
    }

    class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f4712a;
        final /* synthetic */ String b;
        final /* synthetic */ f0 c;

        @Override // java.lang.Runnable
        public void run() {
            g0 g0Var = g0.this;
            Context context = this.f4712a;
            String str = this.b;
            f0 f0Var = this.c;
            g0Var.getClass();
            y0.b("GrsManager", "queryGrsFromSDK begin");
            GrsBaseInfo grsBaseInfo = new GrsBaseInfo();
            grsBaseInfo.setSerCountry(str);
            grsBaseInfo.setCountrySource(str);
            Map<String, String> synGetGrsUrls = new GrsClient(context, grsBaseInfo).synGetGrsUrls("com.huawei.cloud.pay.iap.global");
            if (synGetGrsUrls == null || synGetGrsUrls.isEmpty()) {
                y0.a("GrsManager", "queryGrsFromSDK, urlInfo is empty.");
                f0Var.a("705");
            } else {
                g0Var.f4711a = synGetGrsUrls.get(h0.b);
                g0Var.b = synGetGrsUrls.get("WebPay");
                y0.b("GrsManager", "queryGrsFromSDK success");
                f0Var.a();
            }
        }

        a(Context context, String str, f0 f0Var) {
            this.f4712a = context;
            this.b = str;
            this.c = f0Var;
        }
    }

    /* synthetic */ g0(a aVar) {
        this();
    }

    private g0() {
        this.f4711a = "";
        this.b = "";
    }
}
