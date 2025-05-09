package com.huawei.hms.health;

import android.content.Context;
import com.huawei.hms.common.internal.BaseHmsClient;
import com.huawei.hms.common.internal.ClientSettings;
import com.huawei.hms.common.internal.HmsClient;

/* loaded from: classes4.dex */
public class aabh extends HmsClient {
    @Override // com.huawei.hms.common.internal.BaseHmsClient
    public int getMinApkVersion() {
        return 40002300;
    }

    public aabh(Context context, ClientSettings clientSettings, BaseHmsClient.OnConnectionFailedListener onConnectionFailedListener, BaseHmsClient.ConnectionCallbacks connectionCallbacks) {
        super(context, clientSettings, onConnectionFailedListener, connectionCallbacks);
    }
}
