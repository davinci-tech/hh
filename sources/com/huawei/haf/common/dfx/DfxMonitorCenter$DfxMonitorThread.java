package com.huawei.haf.common.dfx;

import android.os.HandlerThread;
import android.os.Looper;

/* loaded from: classes.dex */
public class DfxMonitorCenter$DfxMonitorThread {
    private static final DfxMonitorCenter$DfxMonitorThread b = new DfxMonitorCenter$DfxMonitorThread();
    private final HandlerThread c;

    private DfxMonitorCenter$DfxMonitorThread() {
        HandlerThread handlerThread = new HandlerThread("DfxMonitorThread");
        this.c = handlerThread;
        handlerThread.start();
    }

    static Looper xk_() {
        return b.c.getLooper();
    }
}
