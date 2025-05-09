package com.huawei.hms.network.file.api;

import com.huawei.hms.network.file.api.Request;
import com.huawei.hms.network.file.api.Result;
import java.util.List;

/* loaded from: classes4.dex */
public interface IRequestManager<R extends Request> {
    Result cancelRequest(long j);

    Result closeThreadPools();

    Result destoryRequests();

    List<R> getAllRequests();

    R getRequest(long j);

    Result.STATUS getRequestStatus(long j);

    Result start(R r, Callback callback);
}
