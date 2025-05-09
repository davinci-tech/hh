package com.huawei.health.h5pro.ext.interceptor;

/* loaded from: classes.dex */
public interface H5ProInterceptor<P, R> {

    /* loaded from: classes3.dex */
    public interface InterceptCallback<R> {
        void onIntercepted(R r);

        void onNotIntercepted();
    }

    void intercept(P p, InterceptCallback<R> interceptCallback);
}
