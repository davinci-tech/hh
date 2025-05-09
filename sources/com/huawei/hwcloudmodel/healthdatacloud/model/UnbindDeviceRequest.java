package com.huawei.hwcloudmodel.healthdatacloud.model;

import com.google.gson.annotations.SerializedName;
import com.huawei.networkclient.IRequest;
import health.compact.a.Utils;

/* loaded from: classes5.dex */
public class UnbindDeviceRequest implements IRequest {

    @SerializedName("deviceCode")
    private Long deviceCode;

    public Long getDeviceCode() {
        return this.deviceCode;
    }

    public void setDeviceCode(Long l) {
        this.deviceCode = l;
    }

    public String toString() {
        return "GetBindDeviceReq{deviceCode=" + this.deviceCode + '}';
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return Utils.e() + "/profile/device/unbindDevice";
    }
}
