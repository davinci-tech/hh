package com.huawei.hwcloudmodel.model.intelligent;

import com.huawei.hwcloudmodel.model.CloudCommonReponse;

/* loaded from: classes5.dex */
public class StartDeviceLinkageResponse extends CloudCommonReponse {
    private String devId;
    private String device_prodId;
    private int expiresIn;

    public String getDevice_prodId() {
        return this.device_prodId;
    }

    public void setDevice_prodId(String str) {
        this.device_prodId = str;
    }

    public int getExpiresIn() {
        return this.expiresIn;
    }

    public void setExpiresIn(int i) {
        this.expiresIn = i;
    }

    public String getDevId() {
        return this.devId;
    }

    public void setDevId(String str) {
        this.devId = str;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        return "StartDeviceLinkageResponse{expiresIn=" + this.expiresIn + ", devId='" + this.devId + ", device_prodId=" + this.device_prodId + "}";
    }
}
