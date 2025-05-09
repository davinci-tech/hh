package com.huawei.health.basesport.wearengine;

import defpackage.cba;

/* loaded from: classes3.dex */
public interface DataReceiver {
    default boolean isMatch(int i) {
        return true;
    }

    void onDataReceived(cba cbaVar, int i, byte[] bArr);
}
