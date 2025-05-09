package com.huawei.health.h5pro.service;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes8.dex */
public class H5ProInvokeResult {

    @SerializedName("result")
    public Object d;

    public Object getResult() {
        return this.d;
    }

    public H5ProInvokeResult(Object obj) {
        this.d = obj;
    }
}
