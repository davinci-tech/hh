package com.huawei.hms.network.file.a;

import com.huawei.hms.network.file.api.RequestManager;
import com.huawei.hms.network.file.core.util.FLogger;

/* loaded from: classes4.dex */
public class g {

    /* renamed from: a, reason: collision with root package name */
    private Long f5600a;
    private Long b;
    private int c;
    private int d;
    private volatile boolean e;
    private volatile boolean f;

    public String toString() {
        return "PerformanceInfo{availableRamStart=" + this.f5600a + ", availableRamMid=" + this.b + ", cpuFreqStart=" + this.c + ", cpuFreqMid=" + this.d + super.toString() + '}';
    }

    public int d() {
        return this.c;
    }

    public int c() {
        return this.d;
    }

    public void b(Long l) {
        this.f5600a = l;
    }

    public void b(int i) {
        this.c = i;
    }

    public Long b() {
        return this.f5600a;
    }

    public void a(Long l) {
        this.b = l;
    }

    public void a(long j, long j2, int i) {
        if (!this.e) {
            b(Long.valueOf(com.huawei.hms.network.file.core.util.a.a(RequestManager.getAppContext())));
            b(com.huawei.hms.network.file.core.util.a.a());
            this.e = true;
            FLogger.d("PerformanceInfoCollect", "first data collect:availableRamStart_" + this.f5600a + ";cpuFreqStart_" + this.c, new Object[0]);
        }
        if (j + i < j2 / 2 || this.f) {
            return;
        }
        a(Long.valueOf(com.huawei.hms.network.file.core.util.a.a(RequestManager.getAppContext())));
        a(com.huawei.hms.network.file.core.util.a.a());
        this.f = true;
        FLogger.d("PerformanceInfoCollect", "middle data collect：availableRamMid_" + this.b + ";cpuFreqMid_" + this.d, new Object[0]);
    }

    public void a(int i) {
        this.d = i;
    }

    public Long a() {
        return this.b;
    }
}
