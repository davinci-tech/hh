package com.huawei.hwcloudmodel.model.intelligent;

import com.huawei.hwcloudmodel.model.CloudCommonReponse;

/* loaded from: classes5.dex */
public class DeviceLinkResultNotifyResponse extends CloudCommonReponse {
    private String reqId;

    public String getReqId() {
        return this.reqId;
    }

    public void setReqId(String str) {
        this.reqId = str;
    }

    @Override // com.huawei.hwcloudmodel.model.CloudCommonReponse
    public String toString() {
        return "DeviceLinkResultNotifyResponse{reqId='" + this.reqId + "'}";
    }
}
