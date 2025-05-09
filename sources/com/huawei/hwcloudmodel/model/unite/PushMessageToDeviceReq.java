package com.huawei.hwcloudmodel.model.unite;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;

/* loaded from: classes5.dex */
public class PushMessageToDeviceReq implements IRequest {

    @SerializedName("devType")
    private String devType;

    @SerializedName("reconnectAble")
    private int reconnectAble;

    @SerializedName("reqId")
    private String reqId;

    @SerializedName("targetDevice")
    private String targetDevice;

    @SerializedName("timeout")
    private int timeout;

    @SerializedName("udid")
    private String udid;

    public String getReqId() {
        return this.reqId;
    }

    public void setReqId(String str) {
        this.reqId = str;
    }

    public String getUdId() {
        return this.udid;
    }

    public void setUdId(String str) {
        this.udid = str;
    }

    public int getTimeOut() {
        return this.timeout;
    }

    public void setTimeOut(int i) {
        this.timeout = i;
    }

    public String getTargetDevice() {
        return this.targetDevice;
    }

    public void setTargetDevice(String str) {
        this.targetDevice = str;
    }

    public String getDeviceType() {
        return this.devType;
    }

    public void setDeviceType(String str) {
        this.devType = str;
    }

    public int getReconnectAble() {
        return this.reconnectAble;
    }

    public void setReconnectAble(int i) {
        this.reconnectAble = i;
    }

    public String toString() {
        return "PushMessageToDeviceRequest{reqId='" + this.reqId + "', udId='" + this.udid + "', timeOut='" + this.timeout + "', targetDevice='" + this.targetDevice + "', deviceType='" + this.devType + "'}";
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("healthDeviceUrl") + "/deviceAgent/deviceLinkStartNotify";
    }
}
