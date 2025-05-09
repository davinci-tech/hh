package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.hms.network.NetworkKit;

/* loaded from: classes5.dex */
public class ik {

    /* renamed from: a, reason: collision with root package name */
    private static Context f6945a;
    private static boolean b;
    private static iu c;

    private static void b(Context context) {
        if (!com.huawei.openalliance.ad.utils.ao.p(context) || !com.huawei.openalliance.ad.utils.ao.f()) {
            b = false;
            ho.b("MediaCacheFactory", "not enable user info, skip init.");
            return;
        }
        try {
            ho.b("MediaCacheFactory", "initNetowrkKit");
            NetworkKit.init(context, null);
            c = new in(8, 5000, 30000);
        } catch (Throwable th) {
            b = false;
            ho.c("MediaCacheFactory", "init networkKit error: %s", th.getClass().getSimpleName());
        }
    }

    public static void a(Context context) {
        if (b) {
            ho.a("MediaCacheFactory", "SdkFactory already initialized.");
            return;
        }
        ho.b("MediaCacheFactory", "init");
        b = true;
        Context applicationContext = context.getApplicationContext();
        f6945a = applicationContext;
        b(applicationContext);
    }

    public static iu a() {
        return c;
    }
}
