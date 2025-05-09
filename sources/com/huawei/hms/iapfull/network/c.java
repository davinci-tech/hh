package com.huawei.hms.iapfull.network;

import android.content.Context;
import com.huawei.hms.iapfull.y0;
import com.huawei.hms.network.embedded.y;

/* loaded from: classes4.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f4733a = new Object();
    private static volatile IAPWebPayService b;

    public static IAPWebPayService a(Context context, String str) {
        y0.b("IAPFullClient", "start service");
        if (b == null) {
            synchronized (f4733a) {
                if (b == null) {
                    b = (IAPWebPayService) f.a(context, str, y.c).build().create(IAPWebPayService.class);
                }
            }
        }
        return b;
    }
}
