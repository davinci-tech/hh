package com.huawei.hwdevice.mainprocess.mgr.hwdfxmgr.callback;

/* loaded from: classes5.dex */
public interface DeviceDfxBaseResponseCallback {
    void onFailure(int i, String str);

    void onProgress(int i, String str);

    void onSuccess(int i, String str);
}
