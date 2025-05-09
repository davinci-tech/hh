package com.huawei.maps.offlinedata.utils;

import java.io.Closeable;
import java.io.IOException;

/* loaded from: classes9.dex */
public class e {
    public static void a(String str, Closeable... closeableArr) {
        for (Closeable closeable : closeableArr) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException unused) {
                    h.d(str, "IOException ::: cannot close io stream !");
                }
            }
        }
    }
}
