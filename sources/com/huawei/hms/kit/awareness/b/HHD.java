package com.huawei.hms.kit.awareness.b;

import android.content.Context;
import com.huawei.health.R;
import com.huawei.hms.common.internal.AbstractClientBuilder;
import com.huawei.hms.common.internal.BaseHmsClient;
import com.huawei.hms.common.internal.ClientSettings;
import com.huawei.hms.common.internal.HmsClient;

/* loaded from: classes4.dex */
class HHD extends AbstractClientBuilder<HmsClient, HHH> {

    static final class HHA extends HmsClient {
        @Override // com.huawei.hms.common.internal.BaseHmsClient
        public int getMinApkVersion() {
            return getContext().getResources().getInteger(R.integer._2131623936_res_0x7f0e0000);
        }

        HHA(Context context, ClientSettings clientSettings, BaseHmsClient.OnConnectionFailedListener onConnectionFailedListener, BaseHmsClient.ConnectionCallbacks connectionCallbacks) {
            super(context, clientSettings, onConnectionFailedListener, connectionCallbacks);
        }
    }

    @Override // com.huawei.hms.common.internal.AbstractClientBuilder
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public HmsClient buildClient(Context context, ClientSettings clientSettings, BaseHmsClient.OnConnectionFailedListener onConnectionFailedListener, BaseHmsClient.ConnectionCallbacks connectionCallbacks) {
        return new HHA(context, clientSettings, onConnectionFailedListener, connectionCallbacks);
    }

    HHD() {
    }
}
