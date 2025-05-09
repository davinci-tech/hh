package com.huawei.operation.h5pro.ble.bean;

import com.google.gson.annotations.SerializedName;
import com.huawei.operation.ble.BleConstants;

/* loaded from: classes9.dex */
public class JsBrConnectBean {

    @SerializedName(BleConstants.KEY_BR_MAC)
    private String brMac;

    @SerializedName("brUuid")
    private String brUuid;

    @SerializedName("deviceId")
    private String deviceId;

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public String getBrMac() {
        return this.brMac;
    }

    public void setBrMac(String str) {
        this.brMac = str;
    }

    public String getBrUuid() {
        return this.brUuid;
    }

    public void setBrUuid(String str) {
        this.brUuid = str;
    }
}
