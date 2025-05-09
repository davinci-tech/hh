package com.huawei.devicesdk.strategy;

import defpackage.bgi;

/* loaded from: classes3.dex */
public abstract class ScanStrategy {
    public abstract void init(bgi bgiVar);

    public abstract void scan();

    public abstract void stopScan(boolean z);
}
