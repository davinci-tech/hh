package com.huawei.hms.ads.jsb.inner.data;

/* loaded from: classes4.dex */
public class JsbCallBackData<T> {
    private String callBackName;
    private boolean complete;
    private T data;

    public JsbCallBackData(T t, boolean z, String str) {
        this.complete = z;
        this.data = t;
        this.callBackName = str;
    }
}
