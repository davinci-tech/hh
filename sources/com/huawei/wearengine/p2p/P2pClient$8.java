package com.huawei.wearengine.p2p;

import com.huawei.wearengine.p2p.P2pSendCallback;
import defpackage.tpx;
import defpackage.tqa;

/* loaded from: classes9.dex */
public class P2pClient$8 extends P2pSendCallback.Stub {
    final /* synthetic */ tpx this$0;
    final /* synthetic */ tqa val$resendTask;
    final /* synthetic */ SendCallback val$sendCallback;

    P2pClient$8(tpx tpxVar, tqa tqaVar, SendCallback sendCallback) {
        this.this$0 = tpxVar;
        this.val$resendTask = tqaVar;
        this.val$sendCallback = sendCallback;
    }

    @Override // com.huawei.wearengine.p2p.P2pSendCallback
    public void onSendResult(int i) {
        synchronized (this.val$resendTask.e()) {
            this.this$0.d(i, this.val$resendTask, this.val$sendCallback);
        }
    }

    @Override // com.huawei.wearengine.p2p.P2pSendCallback
    public void onSendProgress(long j) {
        this.val$sendCallback.onSendProgress(j);
    }

    @Override // com.huawei.wearengine.p2p.P2pSendCallback
    public void onFileTransferReport(String str) {
        SendCallback sendCallback = this.val$sendCallback;
        if (sendCallback instanceof SendExtraCallback) {
            ((SendExtraCallback) sendCallback).onFileTransferReport(str);
        }
    }
}
