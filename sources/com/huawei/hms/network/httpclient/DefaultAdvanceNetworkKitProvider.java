package com.huawei.hms.network.httpclient;

import com.huawei.hms.framework.common.ContextHolder;
import com.huawei.hms.network.AdvanceNetworkKitProvider;
import com.huawei.hms.network.api.advance.AdvanceNetworkKit;
import com.huawei.hms.network.embedded.f;
import com.huawei.hms.network.embedded.j1;

/* loaded from: classes9.dex */
public class DefaultAdvanceNetworkKitProvider extends AdvanceNetworkKitProvider {
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
        return "com.huawei.hms.network.httpclient.DefaultAdvanceNetworkKitProvider";
    }

    @Override // com.huawei.hms.network.BaseProvider
    public int getApiLevel() {
        return j1.getApiLevel();
    }

    @Override // com.huawei.hms.network.AdvanceNetworkKitProvider
    public AdvanceNetworkKit createAdvanceNetworkKit() {
        return f.getInstance();
    }
}
