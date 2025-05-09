package com.huawei.operation.h5pro.jsmodules.healthengine.datastore;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes9.dex */
class HiHealthDeviceObj {

    @SerializedName("deviceModel")
    private String deviceModel;

    @SerializedName("deviceName")
    private String deviceName;

    @SerializedName("deviceUniqueCode")
    private String deviceUniqueCode;

    HiHealthDeviceObj() {
    }

    public String getDeviceUniqueCode() {
        return this.deviceUniqueCode;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public String getDeviceModel() {
        return this.deviceModel;
    }
}
