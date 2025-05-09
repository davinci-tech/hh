package com.huawei.hms.support.api.paytask;

import android.content.Context;
import com.huawei.hms.common.internal.AbstractClientBuilder;
import com.huawei.hms.common.internal.BaseHmsClient;
import com.huawei.hms.common.internal.ClientSettings;

/* loaded from: classes4.dex */
public class PayHmsClientBuilder extends AbstractClientBuilder<PayHmsClient, PayOptions> {
    @Override // com.huawei.hms.common.internal.AbstractClientBuilder
    public PayHmsClient buildClient(Context context, ClientSettings clientSettings, BaseHmsClient.OnConnectionFailedListener onConnectionFailedListener, BaseHmsClient.ConnectionCallbacks connectionCallbacks) {
        return new PayHmsClient(context, clientSettings, onConnectionFailedListener, connectionCallbacks);
    }
}
