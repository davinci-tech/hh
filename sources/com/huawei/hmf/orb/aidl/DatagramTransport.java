package com.huawei.hmf.orb.aidl;

import com.huawei.hmf.orb.IMessageEntity;
import com.huawei.hmf.orb.aidl.client.ApiClient;

/* loaded from: classes8.dex */
public interface DatagramTransport {

    /* loaded from: classes9.dex */
    public interface CallBack {
        void onCallback(int i, IMessageEntity iMessageEntity);
    }

    void post(ApiClient apiClient, CallBack callBack);

    void send(ApiClient apiClient, CallBack callBack);
}
