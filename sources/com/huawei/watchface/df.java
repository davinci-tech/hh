package com.huawei.watchface;

import com.huawei.watchface.utils.HwLog;
import java.io.Closeable;
import java.io.IOException;

/* loaded from: classes7.dex */
public final class df {
    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
                HwLog.e("IoUtils", "An exception occurred while closing the 'Closeable' object.");
            }
        }
    }
}
