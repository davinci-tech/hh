package com.huawei.health.hwhealthlinkage.interactors;

import com.huawei.hwbasemgr.IHeartRateCallback;

/* loaded from: classes3.dex */
public abstract class BaseInteractor {
    protected IHeartRateCallback mStatusCallback;

    public abstract void registerStatusListener();

    public abstract void start();

    public abstract void stop();

    public void unRegisterStatusListener() {
        this.mStatusCallback = null;
    }
}
