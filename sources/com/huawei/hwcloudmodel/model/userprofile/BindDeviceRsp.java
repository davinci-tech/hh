package com.huawei.hwcloudmodel.model.userprofile;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;

/* loaded from: classes.dex */
public class BindDeviceRsp extends CloudCommonReponse {

    @SerializedName("deviceCode")
    private Long deviceCode = 0L;

    public Long getDeviceCode() {
        return this.deviceCode;
    }

    public void setDeviceCode(Long l) {
        this.deviceCode = l;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        return "BindDeviceRsp{deviceCode=" + this.deviceCode + '}';
    }
}
