package com.huawei.agconnect.https;

import com.huawei.hmf.tasks.Task;

/* loaded from: classes2.dex */
public interface Service {
    Task<HttpsResult> execute(Method method);

    public static class Factory {
        static Service get(HttpsKit httpsKit) {
            return new d(httpsKit.client(), httpsKit.executor());
        }
    }
}
