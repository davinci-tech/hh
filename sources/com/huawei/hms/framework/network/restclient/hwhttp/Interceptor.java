package com.huawei.hms.framework.network.restclient.hwhttp;

import java.io.IOException;

@Deprecated
/* loaded from: classes.dex */
public interface Interceptor {

    @Deprecated
    /* loaded from: classes9.dex */
    public interface Chain {
        Response proceed(Request request) throws IOException;

        Request request();
    }

    Response intercept(Chain chain) throws IOException;
}
