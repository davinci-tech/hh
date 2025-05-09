package com.huawei.wearengine.p2p;

import android.os.RemoteException;
import com.huawei.hms.framework.common.ExceptionCode;
import com.huawei.hms.kit.awareness.barrier.internal.e.a;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.IOTAResultAIDLCallback;

/* loaded from: classes7.dex */
public class SendFileCallbackProxy extends IOTAResultAIDLCallback.Stub {

    /* renamed from: a, reason: collision with root package name */
    private String f11235a;
    private String b;
    private P2pSendCallback c;
    private SendFileCallbackCleanup d;
    private String e;

    public SendFileCallbackProxy(SendFileCallbackCleanup sendFileCallbackCleanup, P2pSendCallback p2pSendCallback, String str, String str2, String str3) {
        this.d = sendFileCallbackCleanup;
        this.c = p2pSendCallback;
        this.f11235a = str;
        this.b = str2;
        this.e = str3;
    }

    @Override // com.huawei.hwservicesmgr.IOTAResultAIDLCallback
    public void onFileTransferState(int i) throws RemoteException {
        LogUtil.c("WearEngine_SendFileCallbackProxy", "onFileTransferState enter");
        this.c.onSendProgress(i);
    }

    @Override // com.huawei.hwservicesmgr.IOTAResultAIDLCallback
    public void onUpgradeFailed(int i, String str) throws RemoteException {
        LogUtil.c("WearEngine_SendFileCallbackProxy", "onUpgradeFailed enter");
        this.c.onSendResult(c(i));
        this.d.onCleanup(this, this.f11235a, this.b, this.e);
    }

    @Override // com.huawei.hwservicesmgr.IOTAResultAIDLCallback
    public void onFileRespond(int i) throws RemoteException {
        LogUtil.c("WearEngine_SendFileCallbackProxy", "onFileRespond enter");
        this.c.onSendResult(c(i));
        this.d.onCleanup(this, this.f11235a, this.b, this.e);
    }

    @Override // com.huawei.hwservicesmgr.IOTAResultAIDLCallback
    public void onFileTransferReport(String str) throws RemoteException {
        LogUtil.c("WearEngine_SendFileCallbackProxy", "onFileTransferReport enter, transferWay:", str);
        this.c.onFileTransferReport(str);
    }

    private int c(int i) {
        LogUtil.a("WearEngine_SendFileCallbackProxy", "getInnerErrorCode old errorCode:" + i);
        if (i == 1) {
            return a.w;
        }
        int abs = (Math.abs(i) % ExceptionCode.CRASH_EXCEPTION) + 1990000000;
        LogUtil.a("WearEngine_SendFileCallbackProxy", "getInnerErrorCode new errorCode:" + abs);
        return abs;
    }
}
