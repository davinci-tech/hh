package com.huawei.hms.network.embedded;

import org.chromium.net.CronetEngine;

/* loaded from: classes9.dex */
public class c2 {

    /* renamed from: a, reason: collision with root package name */
    public CronetEngine f5199a;
    public boolean b = false;

    public void setEnableConnectionMigrated(boolean z) {
        this.b = z;
    }

    public void setCronetEngine(CronetEngine cronetEngine) {
        this.f5199a = cronetEngine;
    }

    public boolean isEnableConnectionMigrated() {
        return this.b;
    }

    public CronetEngine getCronetEngine() {
        return this.f5199a;
    }
}
