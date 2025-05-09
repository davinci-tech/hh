package com.huawei.openalliance.ad.utils;

import android.content.Context;
import com.huawei.openalliance.ad.beans.metadata.Content;
import com.huawei.openalliance.ad.tf;

/* loaded from: classes5.dex */
public class cl {
    public static void a(final Context context, final tf tfVar, final String str) {
        if (tfVar == null) {
            return;
        }
        m.a(new Runnable() { // from class: com.huawei.openalliance.ad.utils.cl.1
            @Override // java.lang.Runnable
            public void run() {
                new com.huawei.openalliance.ad.analysis.c(context).a(tfVar, str);
            }
        });
    }

    public static void a(final Context context, final Content content, final int i, final int i2) {
        m.a(new Runnable() { // from class: com.huawei.openalliance.ad.utils.cl.2
            @Override // java.lang.Runnable
            public void run() {
                new com.huawei.openalliance.ad.analysis.c(context).a(content, i, i2);
            }
        });
    }
}
