package com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro;

import com.huawei.gson.annotations.SerializedName;

/* loaded from: classes9.dex */
public class TransmitKaleidoscopeWatchFace<T> extends TransmitWatchFace<T> {

    @SerializedName("result")
    private T result;

    public T getResult() {
        return this.result;
    }

    public void setResult(T t) {
        this.result = t;
    }
}
