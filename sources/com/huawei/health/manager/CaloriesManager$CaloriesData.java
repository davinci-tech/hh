package com.huawei.health.manager;

import defpackage.jdl;

/* loaded from: classes.dex */
public class CaloriesManager$CaloriesData {
    public long e = 0;
    public double b = 0.0d;

    public long d() {
        return this.e;
    }

    public void d(long j, double d) {
        this.e = jdl.t(j);
        this.b = d;
    }

    public void e() {
        this.e = 0L;
        this.b = 0.0d;
    }

    public void e(double d) {
        this.b += d;
    }
}
