package com.huawei.openalliance.ad;

import android.content.Context;

/* loaded from: classes5.dex */
public class ca {

    /* renamed from: a, reason: collision with root package name */
    private static volatile cp f6672a;
    private static final byte[] b = new byte[0];

    public static cp a(Context context) {
        if (f6672a == null) {
            synchronized (b) {
                if (f6672a == null) {
                    f6672a = (bz.f(context) && com.huawei.openalliance.ad.utils.aj.b()) ? new ci(context) : com.huawei.openalliance.ad.utils.aj.a() ? new ck(context) : new cm(context);
                }
            }
        }
        return f6672a;
    }
}
