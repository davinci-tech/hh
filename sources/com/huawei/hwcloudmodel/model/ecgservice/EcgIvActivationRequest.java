package com.huawei.hwcloudmodel.model.ecgservice;

import com.google.gson.annotations.SerializedName;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.networkclient.IRequest;
import health.compact.a.GRSManager;

/* loaded from: classes5.dex */
public class EcgIvActivationRequest implements IRequest {

    @SerializedName("deviceCardPass")
    private String deviceCardPass;

    @SerializedName("deviceIv")
    private String deviceIv;

    @SerializedName(HealthEngineRequestManager.PARAMS_DEVICE_SN)
    private String deviceSn;

    public String getDeviceIv() {
        return this.deviceIv;
    }

    public void setDeviceIv(String str) {
        this.deviceIv = str;
    }

    public String getDeviceCardPass() {
        return this.deviceCardPass;
    }

    public void setDeviceCardPass(String str) {
        this.deviceCardPass = str;
    }

    public String getDeviceSn() {
        return this.deviceSn;
    }

    public void setDeviceSn(String str) {
        this.deviceSn = str;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.e()).getUrl("healthCloudUrl") + "/healthExpansion/device/v2/getDeviceInfo";
    }
}
