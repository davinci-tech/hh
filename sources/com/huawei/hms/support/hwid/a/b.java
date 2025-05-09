package com.huawei.hms.support.hwid.a;

import android.content.Context;
import com.huawei.hms.common.internal.AbstractClientBuilder;
import com.huawei.hms.common.internal.BaseHmsClient;
import com.huawei.hms.common.internal.ClientSettings;

/* loaded from: classes4.dex */
public class b extends AbstractClientBuilder<a, Object> {
    @Override // com.huawei.hms.common.internal.AbstractClientBuilder
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public a buildClient(Context context, ClientSettings clientSettings, BaseHmsClient.OnConnectionFailedListener onConnectionFailedListener, BaseHmsClient.ConnectionCallbacks connectionCallbacks) {
        return new a(context, clientSettings, onConnectionFailedListener, connectionCallbacks);
    }
}
