package com.huawei.profile.executor;

import com.huawei.profile.trace.TraceIdManager;

/* loaded from: classes6.dex */
public final class TracedRunnable implements Runnable {
    private Runnable runnable;
    private String traceId = TraceIdManager.getTraceId();

    public TracedRunnable(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            String str = this.traceId;
            if (str == null) {
                str = TraceIdManager.genTraceId();
            }
            TraceIdManager.resetTraceId(str);
            this.runnable.run();
        } finally {
            TraceIdManager.clearTraceId();
        }
    }
}
