package com.huawei.hms.framework.network.restclient.hwhttp;

import java.io.IOException;

@Deprecated
/* loaded from: classes4.dex */
public interface Callback {
    void onFailure(Submit submit, Throwable th);

    void onResponse(Submit submit, Response response) throws IOException;
}
