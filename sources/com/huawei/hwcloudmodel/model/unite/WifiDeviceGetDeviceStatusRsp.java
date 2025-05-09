package com.huawei.hwcloudmodel.model.unite;

import com.huawei.hwcloudmodel.model.CloudCommonReponse;

/* loaded from: classes5.dex */
public class WifiDeviceGetDeviceStatusRsp extends CloudCommonReponse {
    private String status;

    public void setStatus(String str) {
        this.status = str;
    }

    public String getStatus() {
        return this.status;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        return "WifiDeviceGetDeviceStatusRsp{status='" + this.status + "'}";
    }
}
