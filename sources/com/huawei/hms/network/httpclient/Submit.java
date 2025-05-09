package com.huawei.hms.network.httpclient;

import java.io.IOException;

/* loaded from: classes4.dex */
public abstract class Submit<T> implements Cloneable {

    /* loaded from: classes.dex */
    public static abstract class Factory {
        public abstract Submit<ResponseBody> newSubmit(Request request);
    }

    public abstract void cancel();

    @Override // 
    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public abstract Submit<T> mo631clone();

    public abstract void enqueue(Callback<T> callback);

    public abstract Response<T> execute() throws IOException;

    public abstract RequestFinishedInfo getRequestFinishedInfo();

    public abstract boolean isCanceled();

    public abstract boolean isExecuted();

    public abstract Request request() throws IOException;
}
