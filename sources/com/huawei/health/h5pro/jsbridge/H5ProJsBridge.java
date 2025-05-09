package com.huawei.health.h5pro.jsbridge;

import com.huawei.health.h5pro.core.H5ProBridgeManager;
import java.util.List;

/* loaded from: classes3.dex */
public class H5ProJsBridge {
    public static boolean e = false;

    public static void registerBridgeExtClass(H5ProBridgeManager h5ProBridgeManager, boolean z, List<H5ProJsExtModule> list) {
        h5ProBridgeManager.unRegisterBridgeExtClass();
        if (z) {
            return;
        }
        if (list != null && !list.isEmpty()) {
            for (H5ProJsExtModule h5ProJsExtModule : list) {
                h5ProBridgeManager.registerBridgeExtClass(h5ProJsExtModule.moduleName, h5ProJsExtModule.moduleClass);
            }
            return;
        }
        for (H5ProJsExtModule h5ProJsExtModule2 : H5ProJsExtModule.values()) {
            h5ProBridgeManager.registerBridgeExtClass(h5ProJsExtModule2.moduleName, h5ProJsExtModule2.moduleClass);
        }
    }

    public static void registerBridgeClass(boolean z, List<H5ProJsExtModule> list) {
        H5ProBridgeManager h5ProBridgeManager = H5ProBridgeManager.getInstance();
        registerBridgeExtClass(h5ProBridgeManager, z, list);
        if (e) {
            return;
        }
        e = true;
        for (H5ProJsModule h5ProJsModule : H5ProJsModule.values()) {
            h5ProBridgeManager.registerBridgeClass(h5ProJsModule.moduleName, h5ProJsModule.moduleClass);
        }
    }
}
