package com.huawei.hms.ads.jsb;

import android.content.Context;
import com.huawei.openalliance.ad.bz;
import com.huawei.openalliance.ad.download.app.interfaces.b;
import com.huawei.openalliance.ad.download.app.l;
import com.huawei.openalliance.ad.inter.HiAd;

/* loaded from: classes4.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private static final byte[] f4337a = new byte[0];
    private static a b;
    private Context c;

    public void a(JsbConfig jsbConfig) {
        synchronized (f4337a) {
            if (jsbConfig != null) {
                HiAd.getInstance(this.c).enableUserInfo(jsbConfig.a());
                HiAd.getInstance(this.c).initLog(jsbConfig.d(), 3);
                if (bz.c(this.c)) {
                    HiAd.getInstance(this.c).initGrs(jsbConfig.b());
                } else {
                    HiAd.getInstance(this.c).initGrs(jsbConfig.b(), jsbConfig.c());
                }
            }
        }
    }

    public b a() {
        return C0077a.f4338a;
    }

    private static a b(Context context) {
        a aVar;
        synchronized (f4337a) {
            if (b == null) {
                b = new a(context);
            }
            aVar = b;
        }
        return aVar;
    }

    /* renamed from: com.huawei.hms.ads.jsb.a$a, reason: collision with other inner class name */
    static class C0077a {

        /* renamed from: a, reason: collision with root package name */
        private static final b f4338a = new l();
    }

    public static a a(Context context) {
        return b(context);
    }

    private a(Context context) {
        this.c = context.getApplicationContext();
    }
}
