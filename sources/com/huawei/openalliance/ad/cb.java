package com.huawei.openalliance.ad;

import android.content.Context;

/* loaded from: classes5.dex */
public class cb {

    /* renamed from: a, reason: collision with root package name */
    private static volatile cq f6673a;
    private static final byte[] b = new byte[0];

    public static cq a(Context context) {
        if (f6673a == null) {
            synchronized (b) {
                if (f6673a == null) {
                    f6673a = bz.e(context) ? ch.b(context) : cl.b(context);
                }
            }
        }
        return f6673a;
    }
}
