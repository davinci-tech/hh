package com.huawei.operation.h5pro.jsmodules.compact;

import com.huawei.health.h5pro.vengine.H5ProInstance;
import com.huawei.operation.adapter.SendServerErrorMsgCallback;

/* loaded from: classes5.dex */
public class SendServerErrorMsgCallbackCompact implements SendServerErrorMsgCallback {
    private H5ProInstance mInstance;

    public SendServerErrorMsgCallbackCompact(H5ProInstance h5ProInstance) {
        this.mInstance = h5ProInstance;
    }

    @Override // com.huawei.operation.adapter.SendServerErrorMsgCallback
    public void onSendServerErrorMsgCallback() {
        this.mInstance.getAppLoadListener().onException(this.mInstance, "");
    }
}
