package com.huawei.hwcloudmodel.model.intelligent;

/* loaded from: classes5.dex */
public class DeviceLinkResultNotifyRequest {
    private String reqId;
    private int resultCode;

    public DeviceLinkResultNotifyRequest(String str, int i) {
        this.reqId = str;
        this.resultCode = i;
    }

    public String toString() {
        return "DeviceLinkResultNotifyRequest{reqId='" + this.reqId + "', resultCode=" + this.resultCode + '}';
    }
}
