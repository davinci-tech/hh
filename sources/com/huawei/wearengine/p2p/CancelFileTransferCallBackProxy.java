package com.huawei.wearengine.p2p;

import com.huawei.hms.framework.common.ExceptionCode;
import com.huawei.hms.kit.awareness.barrier.internal.e.a;
import com.huawei.hwservicesmgr.IBaseCallback;
import health.compact.a.util.LogUtil;

/* loaded from: classes7.dex */
public class CancelFileTransferCallBackProxy extends IBaseCallback.Stub {

    /* renamed from: a, reason: collision with root package name */
    private SendFileCallbackCleanup f11234a;
    private P2pCancelFileTransferCallBack b;
    private String c;
    private String d;
    private String e;

    public CancelFileTransferCallBackProxy(SendFileCallbackCleanup sendFileCallbackCleanup, P2pCancelFileTransferCallBack p2pCancelFileTransferCallBack, String str, String str2, String str3) {
        this.f11234a = sendFileCallbackCleanup;
        this.b = p2pCancelFileTransferCallBack;
        this.e = str;
        this.d = str2;
        this.c = str3;
    }

    @Override // com.huawei.hwservicesmgr.IBaseCallback
    public void onResponse(int i, String str) {
        LogUtil.d("WearEngine_CancelFileTransferCallBackProxy", "CancelFileTransferCallBackProxy onResponse enter, errCode: " + i);
        try {
            this.b.onCancelFileTransferResult(c(i), str);
        } catch (Exception unused) {
            LogUtil.e("WearEngine_CancelFileTransferCallBackProxy", "CancelFileTransferCallBackProxy onResponse Exception");
        }
        this.f11234a.onCleanupCancelFileTransfer(this, this.e, this.d, this.c);
    }

    private int c(int i) {
        LogUtil.d("WearEngine_CancelFileTransferCallBackProxy", "getInnerErrorCode old errorCode:" + i);
        if (i == 20003) {
            return a.w;
        }
        int abs = (Math.abs(i) % ExceptionCode.CRASH_EXCEPTION) + 1990000000;
        LogUtil.d("WearEngine_CancelFileTransferCallBackProxy", "getInnerErrorCode new errorCode:" + abs);
        return abs;
    }
}
