package com.huawei.haf.common.dfx.memory;

/* loaded from: classes.dex */
public final class MemoryMonitorConfig {

    /* renamed from: a, reason: collision with root package name */
    private final int f2088a;
    private final int b;
    private final int c;
    private final int d;

    public MemoryMonitorConfig(int i, int i2) {
        this(i, i2, 5);
    }

    public MemoryMonitorConfig(int i, int i2, int i3) {
        this.b = i;
        this.c = i + i3;
        this.d = i2;
        this.f2088a = i2 + i3;
    }

    public int b() {
        return this.b;
    }

    public int d() {
        return this.c;
    }

    public int c() {
        return this.d;
    }

    public int e() {
        return this.f2088a;
    }
}
