package com.huawei.watchface;

import com.huawei.watchface.utils.HwLog;
import java.io.Closeable;

/* loaded from: classes7.dex */
public class cq {
    public static void a(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (Exception e) {
            HwLog.e("CloseUtils", HwLog.printException(e));
        }
    }
}
