package com.huawei.hms.network.httpclient;

import com.huawei.hms.framework.common.ContextHolder;
import com.huawei.hms.network.NetworkKit;
import com.huawei.hms.network.NetworkKitProvider;
import com.huawei.hms.network.embedded.a1;
import com.huawei.hms.network.embedded.g;
import com.huawei.hms.network.embedded.h6;
import com.huawei.hms.network.embedded.j1;
import com.huawei.hms.network.httpclient.internal.IHttpClientBuilder;
import com.huawei.hms.network.restclient.internal.IRestClientBuilder;

/* loaded from: classes9.dex */
public class DefaultNetworkKitProvider extends NetworkKitProvider {
    @Override // com.huawei.hms.network.BaseProvider
    public boolean isDynamicProvider() {
        return ContextHolder.getKitContext() != null;
    }

    @Override // com.huawei.hms.network.BaseProvider
    public String getVersion() {
        return j1.getVersion();
    }

    @Override // com.huawei.hms.network.BaseProvider
    public String getName() {
        return "DefaultHttpClientProvider";
    }

    @Override // com.huawei.hms.network.BaseProvider
    public int getApiLevel() {
        return j1.getApiLevel();
    }

    @Override // com.huawei.hms.network.NetworkKitProvider
    public IRestClientBuilder createRestClientBuilder() {
        return new h6.b();
    }

    @Override // com.huawei.hms.network.NetworkKitProvider
    public NetworkKit createNetworkKit() {
        return g.getInstance();
    }

    @Override // com.huawei.hms.network.NetworkKitProvider
    public IHttpClientBuilder createHttpClientBuilder() {
        return new a1.b();
    }
}
