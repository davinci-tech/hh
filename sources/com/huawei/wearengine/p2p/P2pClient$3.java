package com.huawei.wearengine.p2p;

import com.huawei.wearengine.p2p.P2pSendCallback;
import defpackage.tpx;

/* loaded from: classes9.dex */
public class P2pClient$3 extends P2pSendCallback.Stub {
    final /* synthetic */ tpx this$0;
    final /* synthetic */ SendCallback val$sendInternalCallback;

    P2pClient$3(tpx tpxVar, SendCallback sendCallback) {
        this.this$0 = tpxVar;
        this.val$sendInternalCallback = sendCallback;
    }

    @Override // com.huawei.wearengine.p2p.P2pSendCallback
    public void onSendResult(int i) {
        this.val$sendInternalCallback.onSendResult(i);
    }

    @Override // com.huawei.wearengine.p2p.P2pSendCallback
    public void onSendProgress(long j) {
        this.val$sendInternalCallback.onSendProgress(j);
    }

    @Override // com.huawei.wearengine.p2p.P2pSendCallback
    public void onFileTransferReport(String str) {
        SendCallback sendCallback = this.val$sendInternalCallback;
        if (sendCallback instanceof SendExtraCallback) {
            ((SendExtraCallback) sendCallback).onFileTransferReport(str);
        }
    }
}
