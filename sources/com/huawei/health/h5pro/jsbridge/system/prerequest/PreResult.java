package com.huawei.health.h5pro.jsbridge.system.prerequest;

import com.google.gson.annotations.SerializedName;
import com.huawei.hms.network.file.core.util.Utils;

/* loaded from: classes3.dex */
public class PreResult<T> {

    @SerializedName(Utils.REQUEST_TIME)
    public long c;

    @SerializedName("data")
    public T d;

    public void setRequestTime(long j) {
        this.c = j;
    }

    public void setData(T t) {
        this.d = t;
    }

    public long getRequestTime() {
        return this.c;
    }

    public T getData() {
        return this.d;
    }

    public PreResult(long j, T t) {
        this.c = j;
        this.d = t;
    }

    public PreResult() {
    }
}
