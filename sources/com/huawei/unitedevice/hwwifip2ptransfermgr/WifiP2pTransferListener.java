package com.huawei.unitedevice.hwwifip2ptransfermgr;

/* loaded from: classes7.dex */
public interface WifiP2pTransferListener {
    void onFail(int i, String str, int i2);

    void onProcess(int i, int i2);

    void onSuccess(int i, String str, int i2);
}
