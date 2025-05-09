package com.huawei.hmf.orb.aidl.client;

import com.huawei.hmf.orb.aidl.IAIDLInvoke;

/* loaded from: classes9.dex */
public interface ApiClient {
    String getAppID();

    String getPackageName();

    int getProtocol();

    IAIDLInvoke getService();

    boolean isConnected();
}
