package com.huawei.health.ecologydevice.callback;

import defpackage.dcm;

/* loaded from: classes8.dex */
public interface NemoConnectCallback {
    void connectAndSendSuccess(dcm dcmVar);

    void connectOrSendError(String str);
}
