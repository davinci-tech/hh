package com.huawei.unitedevice.hwcommonfilemgr;

import android.os.RemoteException;
import com.huawei.health.deviceconnect.callback.SendCallback;
import com.huawei.hms.framework.common.ExceptionCode;
import com.huawei.hms.kit.awareness.barrier.internal.e.a;
import com.huawei.unitedevice.callback.IResultAIDLCallback;
import health.compact.a.LogUtil;

/* loaded from: classes9.dex */
public class SendFileCallbackProxy extends IResultAIDLCallback.Stub {

    /* renamed from: a, reason: collision with root package name */
    private SendCallback f10793a;

    public SendFileCallbackProxy(SendCallback sendCallback) {
        this.f10793a = sendCallback;
    }

    @Override // com.huawei.unitedevice.callback.IResultAIDLCallback
    public void onFileTransferState(int i, String str) throws RemoteException {
        LogUtil.d("SendFileCallbackProxy", "onFileTransferState enter");
        this.f10793a.onSendProgress(i);
    }

    @Override // com.huawei.unitedevice.callback.IResultAIDLCallback
    public void onTransferFailed(int i, String str) throws RemoteException {
        LogUtil.d("SendFileCallbackProxy", "onUpgradeFailed enter");
        SendCallback sendCallback = this.f10793a;
        if (sendCallback != null) {
            sendCallback.onSendResult(b(i));
        }
    }

    @Override // com.huawei.unitedevice.callback.IResultAIDLCallback
    public void onFileRespond(int i, String str) throws RemoteException {
        LogUtil.d("SendFileCallbackProxy", "onFileRespond enter");
        SendCallback sendCallback = this.f10793a;
        if (sendCallback != null) {
            sendCallback.onSendResult(b(i));
        }
    }

    @Override // com.huawei.unitedevice.callback.IResultAIDLCallback
    public void onFileTransferReport(String str) throws RemoteException {
        LogUtil.d("SendFileCallbackProxy", "onFileTransferReport enter");
        SendCallback sendCallback = this.f10793a;
        if (sendCallback != null) {
            sendCallback.onFileTransferReport(str);
        }
    }

    private int b(int i) {
        LogUtil.c("SendFileCallbackProxy", "getInnerErrorCode old errorCode: ", Integer.valueOf(i));
        if (i == 1) {
            return a.w;
        }
        int abs = (Math.abs(i) % ExceptionCode.CRASH_EXCEPTION) + 1990000000;
        LogUtil.c("SendFileCallbackProxy", "getInnerErrorCode new errorCode: ", Integer.valueOf(abs));
        return abs;
    }
}
