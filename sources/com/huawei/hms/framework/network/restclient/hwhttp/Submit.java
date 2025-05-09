package com.huawei.hms.framework.network.restclient.hwhttp;

import java.io.IOException;

@Deprecated
/* loaded from: classes4.dex */
public interface Submit extends Cloneable {

    @Deprecated
    /* loaded from: classes.dex */
    public interface Factory {
        Submit newSubmit(Request request);
    }

    void cancel();

    Submit clone();

    void enqueue(Callback callback);

    Response execute() throws IOException;

    boolean isCanceled();

    boolean isExecuted();

    Request request() throws IOException;
}
