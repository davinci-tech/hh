package com.huawei.hms.network.embedded;

import com.huawei.hms.network.httpclient.okhttp.OkHttpClientGlobal;
import com.huawei.hms.network.inner.api.ProtocolStackManager;
import java.util.List;

/* loaded from: classes9.dex */
public class z0 extends ProtocolStackManager {

    /* renamed from: a, reason: collision with root package name */
    public OkHttpClientGlobal f5587a;

    @Override // com.huawei.hms.network.inner.api.ProtocolStackManager
    public List<String> getHostsInConnectionPool() {
        if (this.f5587a == null) {
            this.f5587a = OkHttpClientGlobal.getInstance();
        }
        return this.f5587a.getHostsInConnectionPool();
    }
}
