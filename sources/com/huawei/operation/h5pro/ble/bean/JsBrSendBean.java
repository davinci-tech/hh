package com.huawei.operation.h5pro.ble.bean;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes9.dex */
public class JsBrSendBean {

    @SerializedName("data")
    private String data;

    @SerializedName("deviceId")
    private String deviceId;

    public String getData() {
        return this.data;
    }

    public void setSendData(String str) {
        this.data = str;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }
}
