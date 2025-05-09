package com.huawei.haf.common.dfx.block;

import com.huawei.haf.common.dfx.DfxMonitorTask;
import health.compact.a.DfxMonitorCenter;

/* loaded from: classes.dex */
public final class ThreadMonitorTask implements DfxMonitorTask {

    /* renamed from: a, reason: collision with root package name */
    private final long f2084a;
    private final MonitorCallback b;
    private final Thread d;
    private volatile boolean e = false;

    public ThreadMonitorTask(Thread thread, MonitorCallback monitorCallback) {
        this.d = thread;
        this.b = monitorCallback;
        this.f2084a = a(monitorCallback.monitorTimeInterval());
    }

    public void e(String str) {
        e(null, 0L);
        this.b.begin(this.d, str, System.currentTimeMillis());
        DfxMonitorCenter.d(this);
        this.e = true;
    }

    public long a() {
        return this.b.getBeginTime();
    }

    public void c() {
        e(null, 0L);
    }

    public void e(Throwable th, long j) {
        if (this.e) {
            DfxMonitorCenter.b(this);
            this.e = false;
            if (j <= this.b.getBeginTime()) {
                j = System.currentTimeMillis();
            }
            this.b.end(this.d, th, j, this.f2084a);
        }
    }

    private long a(long j) {
        return Math.max((j / 1000) * 1000, 1000L);
    }

    @Override // com.huawei.haf.common.dfx.DfxMonitorTask
    public long monitorDelayTime() {
        return this.f2084a;
    }

    @Override // com.huawei.haf.common.dfx.DfxMonitorTask
    public void onMonitor() {
        if (this.e) {
            if (!this.d.isAlive()) {
                this.e = false;
            } else if (this.b.check(this.d, System.currentTimeMillis(), this.f2084a)) {
                DfxMonitorCenter.d(this);
            }
        }
    }
}
