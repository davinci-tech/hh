package com.huawei.haf.common.dfx.memory;

import com.huawei.haf.common.dfx.DfxMonitorTask;
import health.compact.a.DfxMonitorCenter;

/* loaded from: classes.dex */
public final class MemoryMonitor implements DfxMonitorTask {
    private static DfxMonitorTask b;
    private static MemoryCallback e;

    @Override // com.huawei.haf.common.dfx.DfxMonitorTask
    public long monitorDelayTime() {
        return 60000L;
    }

    private MemoryMonitor() {
    }

    public static void a(MemoryCallback memoryCallback) {
        if (e != null || memoryCallback == null) {
            return;
        }
        e = memoryCallback;
        if (memoryCallback.isAutoCheck()) {
            MemoryMonitor memoryMonitor = new MemoryMonitor();
            b = memoryMonitor;
            DfxMonitorCenter.d(memoryMonitor);
        }
    }

    public static void c() {
        if (e != null) {
            DfxMonitorTask dfxMonitorTask = b;
            if (dfxMonitorTask != null) {
                DfxMonitorCenter.b(dfxMonitorTask);
                b = null;
            }
            e = null;
            CloseGuardMonitor.e();
        }
    }

    public static void c(boolean z) {
        MemoryCallback memoryCallback = e;
        if (memoryCallback == null || b != null) {
            return;
        }
        memoryCallback.check(z);
    }

    public static boolean a() {
        return e != null;
    }

    public static void b(String str, String str2, String str3, long j) {
        MemoryCallback memoryCallback = e;
        if (memoryCallback != null) {
            memoryCallback.reportLeak(str, str2, str3, j);
        }
    }

    public static void d() {
        if (a()) {
            CloseGuardMonitor.a();
        }
    }

    @Override // com.huawei.haf.common.dfx.DfxMonitorTask
    public void onMonitor() {
        MemoryCallback memoryCallback = e;
        if (memoryCallback != null) {
            DfxMonitorCenter.d(this);
            memoryCallback.check(true);
        }
    }
}
