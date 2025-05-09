package com.huawei.hms.network.embedded;

import android.os.Build;
import java.net.InetSocketAddress;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes9.dex */
public class r8 {
    public static s8 a(CopyOnWriteArrayList<InetSocketAddress> copyOnWriteArrayList, int i, int i2) {
        int a2 = a();
        String property = System.getProperty("openConcurrent");
        boolean z = property == null || !property.trim().equalsIgnoreCase("false");
        if ((a2 < 29 || !z) && a2 < 30) {
            return new s8(copyOnWriteArrayList, i, i2);
        }
        return new q8(copyOnWriteArrayList, i, i2);
    }

    public static int a() {
        try {
            return Build.VERSION.SDK_INT;
        } catch (NoClassDefFoundError unused) {
            return 0;
        }
    }
}
