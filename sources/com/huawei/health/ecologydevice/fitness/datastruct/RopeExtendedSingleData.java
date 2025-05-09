package com.huawei.health.ecologydevice.fitness.datastruct;

import defpackage.cyw;

/* loaded from: classes3.dex */
public class RopeExtendedSingleData extends BaseRopeData {
    private static final int HEART_RATE_PARA_LENGTH = 3;

    public byte[] getHeartRateByteArray() {
        if (this.mCode != 0) {
            return null;
        }
        byte[] bArr = new byte[3];
        cyw.a(bArr, this.mCommand, 0);
        cyw.a(bArr, this.mCode, 1);
        cyw.a(bArr, this.mPara[0], 2);
        return bArr;
    }
}
