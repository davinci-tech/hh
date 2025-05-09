package com.huawei.hms.iap;

import android.content.Context;
import com.huawei.hms.common.internal.AbstractClientBuilder;
import com.huawei.hms.common.internal.BaseHmsClient;
import com.huawei.hms.common.internal.ClientSettings;

/* loaded from: classes4.dex */
public class h extends AbstractClientBuilder<g, i> {
    @Override // com.huawei.hms.common.internal.AbstractClientBuilder
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public g buildClient(Context context, ClientSettings clientSettings, BaseHmsClient.OnConnectionFailedListener onConnectionFailedListener, BaseHmsClient.ConnectionCallbacks connectionCallbacks) {
        return new g(context, clientSettings, onConnectionFailedListener, connectionCallbacks);
    }
}
