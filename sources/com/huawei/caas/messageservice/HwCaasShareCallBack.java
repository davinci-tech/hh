package com.huawei.caas.messageservice;

import com.huawei.caas.messageservice.HwShareUtils;

/* loaded from: classes8.dex */
public interface HwCaasShareCallBack {
    void initFail(int i);

    void initSuccess(HwCaasShareHandler hwCaasShareHandler);

    void releaseSuccess();

    void sendResult(HwShareUtils.SendResultEnum sendResultEnum);
}
