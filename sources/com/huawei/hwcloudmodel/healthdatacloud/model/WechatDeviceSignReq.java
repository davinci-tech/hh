package com.huawei.hwcloudmodel.healthdatacloud.model;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.networkclient.IRequest;
import health.compact.a.Utils;

/* loaded from: classes9.dex */
public class WechatDeviceSignReq implements IRequest {

    @SerializedName(HealthEngineRequestManager.PARAMS_DEVICE_SN)
    private String deviceSn;

    @SerializedName("productId")
    private int productId;

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("WechatDeviceSignReq { productId = ");
        stringBuffer.append(this.productId);
        stringBuffer.append("deviceSn = ").append(this.deviceSn);
        stringBuffer.append(" }");
        return stringBuffer.toString();
    }

    public int getProductId() {
        return this.productId;
    }

    public void setProductId(int i) {
        this.productId = i;
    }

    public String getDeviceSn() {
        return this.deviceSn;
    }

    public void setDeviceSn(String str) {
        this.deviceSn = str;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return Utils.e() + "/dataOpen/wechatDevice/sign";
    }
}
