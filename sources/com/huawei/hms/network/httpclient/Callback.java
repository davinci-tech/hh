package com.huawei.hms.network.httpclient;

import java.io.IOException;

/* loaded from: classes4.dex */
public abstract class Callback<T> {
    public abstract void onFailure(Submit<T> submit, Throwable th);

    public abstract void onResponse(Submit<T> submit, Response<T> response) throws IOException;
}
