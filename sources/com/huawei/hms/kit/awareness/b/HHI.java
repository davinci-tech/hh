package com.huawei.hms.kit.awareness.b;

import com.huawei.hms.kit.awareness.AwarenessStatusCodes;

/* loaded from: classes4.dex */
public abstract class HHI<T> {
    private final T mStatus;

    protected abstract String getErrorMsg();

    protected T getStatus() {
        return this.mStatus;
    }

    public final void checkStatus(int i) {
        if (i == 0) {
            return;
        }
        throw new HHG(10009, getErrorMsg() + AwarenessStatusCodes.getMessage(i));
    }

    public HHI(T t) {
        this.mStatus = t;
    }
}
