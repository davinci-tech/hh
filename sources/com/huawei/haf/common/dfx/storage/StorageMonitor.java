package com.huawei.haf.common.dfx.storage;

import com.huawei.haf.common.dfx.DfxMonitorTask;
import health.compact.a.DfxMonitorCenter;
import health.compact.a.LogUtil;

/* loaded from: classes.dex */
public final class StorageMonitor implements DfxMonitorTask {
    private static StorageCallback e;

    @Override // com.huawei.haf.common.dfx.DfxMonitorTask
    public long monitorDelayTime() {
        return 60000L;
    }

    private StorageMonitor() {
    }

    public static void d(StorageCallback storageCallback) {
        if (storageCallback == null) {
            storageCallback = new DefaultStorageHandler("HAF_StorageMonitor");
        }
        e = storageCallback;
    }

    public static void d() {
        e = null;
    }

    public static void e() {
        DfxMonitorCenter.d(new StorageMonitor());
        LogUtil.c("HAF_StorageMonitor", "oneCheck");
    }

    public static void d(boolean z) {
        StorageCallback storageCallback = e;
        if (storageCallback != null) {
            storageCallback.dumpStorageInfo(z);
        }
    }

    @Override // com.huawei.haf.common.dfx.DfxMonitorTask
    public void onMonitor() {
        StorageCallback storageCallback = e;
        if (storageCallback != null) {
            storageCallback.checkStorageInfo(false);
        }
    }
}
