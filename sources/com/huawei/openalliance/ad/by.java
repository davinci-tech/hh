package com.huawei.openalliance.ad;

import android.content.Context;

/* loaded from: classes5.dex */
public class by {

    /* renamed from: a, reason: collision with root package name */
    private static by f6667a;
    private static final byte[] b = new byte[0];
    private Context c;

    public qn a(int i, int i2) {
        ho.a("AppDataCollectionManager", "get processor type : %s", Integer.valueOf(i));
        if (i != 1) {
            return null;
        }
        return new on(this.c, i2);
    }

    public static by a(Context context) {
        by byVar;
        synchronized (b) {
            if (f6667a == null) {
                f6667a = new by(context);
            }
            byVar = f6667a;
        }
        return byVar;
    }

    private by(Context context) {
        this.c = context.getApplicationContext();
    }
}
