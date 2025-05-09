package com.huawei.hms.network.file.api;

import com.huawei.hms.network.file.api.Request;
import com.huawei.hms.network.file.api.exception.NetworkException;
import java.io.Closeable;

/* loaded from: classes4.dex */
public interface Callback<R extends Request, T> {
    void onException(R r, NetworkException networkException, Response<R, T, Closeable> response);

    void onProgress(R r, Progress progress);

    R onStart(R r);

    void onSuccess(Response<R, T, Closeable> response);
}
