package com.huawei.hms.iapfull.network;

import android.content.Context;
import com.huawei.hms.iapfull.y0;
import com.huawei.hms.network.embedded.y;

/* loaded from: classes4.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f4732a = new Object();
    private static volatile IAPFullService b;

    public static IAPFullService a(Context context, String str) {
        y0.b("IAPFullClient", "start service");
        if (b == null) {
            synchronized (f4732a) {
                if (b == null) {
                    b = (IAPFullService) f.a(context, str, y.c).build().create(IAPFullService.class);
                }
            }
        }
        return b;
    }
}
