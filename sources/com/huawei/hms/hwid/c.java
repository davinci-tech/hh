package com.huawei.hms.hwid;

import android.content.Context;
import com.huawei.hms.common.internal.BaseHmsClient;
import com.huawei.hms.common.internal.ClientSettings;
import com.huawei.hms.common.internal.HmsClient;

/* loaded from: classes4.dex */
public class c extends HmsClient {
    @Override // com.huawei.hms.common.internal.BaseHmsClient
    public int getMinApkVersion() {
        return 40004000;
    }

    public c(Context context, ClientSettings clientSettings, BaseHmsClient.OnConnectionFailedListener onConnectionFailedListener, BaseHmsClient.ConnectionCallbacks connectionCallbacks) {
        super(context, clientSettings, onConnectionFailedListener, connectionCallbacks);
    }
}
