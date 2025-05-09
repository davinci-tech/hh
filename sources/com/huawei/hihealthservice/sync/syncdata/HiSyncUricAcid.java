package com.huawei.hihealthservice.sync.syncdata;

import com.google.gson.annotations.SerializedName;
import com.huawei.operation.ble.BleConstants;

/* loaded from: classes7.dex */
public class HiSyncUricAcid {

    @SerializedName(BleConstants.URIC_ACID)
    private int uricAcid;

    public HiSyncUricAcid(int i) {
        this.uricAcid = i;
    }

    public double getUricAcid() {
        return this.uricAcid;
    }
}
