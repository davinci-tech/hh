package com.huawei.openalliance.ad.utils;

import android.content.Context;
import com.huawei.openalliance.ad.fh;

/* loaded from: classes5.dex */
public abstract class cv extends dj {
    public static void a(Context context, Runnable runnable) {
        if (context == null || !fh.b(context).aD()) {
            f7699a.a(runnable);
        } else {
            f7699a.a(runnable, null);
        }
    }
}
