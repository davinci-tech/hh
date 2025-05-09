package com.huawei.hianalytics;

import android.content.Context;
import com.huawei.hms.common.internal.AbstractClientBuilder;
import com.huawei.hms.common.internal.BaseHmsClient;
import com.huawei.hms.common.internal.ClientSettings;

/* loaded from: classes4.dex */
public class r0 extends AbstractClientBuilder<t0, v0> {
    @Override // com.huawei.hms.common.internal.AbstractClientBuilder
    public t0 buildClient(Context context, ClientSettings clientSettings, BaseHmsClient.OnConnectionFailedListener onConnectionFailedListener, BaseHmsClient.ConnectionCallbacks connectionCallbacks) {
        return new t0(context, clientSettings, onConnectionFailedListener, connectionCallbacks);
    }
}
