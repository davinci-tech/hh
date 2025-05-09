package com.huawei.hms.a.a.b;

import android.content.Context;
import com.huawei.hms.common.internal.BaseHmsClient;
import com.huawei.hms.common.internal.ClientSettings;
import com.huawei.hms.common.internal.HmsClient;
import com.huawei.hms.common.internal.constant.AuthInternalPickerConstant;

/* loaded from: classes4.dex */
public class a extends HmsClient {
    @Override // com.huawei.hms.common.internal.BaseHmsClient
    public int getMinApkVersion() {
        return AuthInternalPickerConstant.HMS_APK_VERSION_MIN;
    }

    public a(Context context, ClientSettings clientSettings, BaseHmsClient.OnConnectionFailedListener onConnectionFailedListener, BaseHmsClient.ConnectionCallbacks connectionCallbacks) {
        super(context, clientSettings, onConnectionFailedListener, connectionCallbacks);
    }
}
