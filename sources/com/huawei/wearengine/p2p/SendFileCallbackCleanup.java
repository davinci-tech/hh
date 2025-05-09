package com.huawei.wearengine.p2p;

/* loaded from: classes2.dex */
public interface SendFileCallbackCleanup {
    void onCleanup(SendFileCallbackProxy sendFileCallbackProxy, String str, String str2, String str3);

    void onCleanupCancelFileTransfer(CancelFileTransferCallBackProxy cancelFileTransferCallBackProxy, String str, String str2, String str3);
}
