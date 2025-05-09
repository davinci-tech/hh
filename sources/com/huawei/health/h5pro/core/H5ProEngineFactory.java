package com.huawei.health.h5pro.core;

import com.huawei.health.h5pro.vengine.H5ProVirtualEngine;
import com.huawei.health.h5pro.webkit.WebKitEngine;

/* loaded from: classes3.dex */
public class H5ProEngineFactory {
    public static H5ProVirtualEngine e;

    public static H5ProVirtualEngine getH5ProEngine() {
        if (e == null) {
            synchronized (H5ProEngineFactory.class) {
                if (e == null) {
                    e = new WebKitEngine();
                }
            }
        }
        return e;
    }

    public static void destroy() {
        synchronized (H5ProEngineFactory.class) {
            if (e != null) {
                e = null;
            }
        }
    }
}
