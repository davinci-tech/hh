package com.huawei.hwcloudmodel.healthdatacloud.model;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.networkclient.IRequest;
import health.compact.a.Utils;
import java.util.List;

/* loaded from: classes9.dex */
public class WechatDeviceRegistReq implements IRequest {

    @SerializedName("deviceList")
    private List<WechatDevice> deviceList;

    @SerializedName("productId")
    private int productId;

    public static class WechatDevice {

        @SerializedName("deviceCode")
        private String deviceCode;

        @SerializedName(HealthEngineRequestManager.PARAMS_DEVICE_SN)
        private String deviceSn;

        public WechatDevice(String str, String str2) {
            this.deviceSn = str;
            this.deviceCode = str2;
        }

        public String getDeviceSn() {
            return this.deviceSn;
        }

        public void setDeviceSn(String str) {
            this.deviceSn = str;
        }

        public String getDeviceCode() {
            return this.deviceCode;
        }

        public void setDeviceCode(String str) {
            this.deviceCode = str;
        }

        public String toString() {
            return "DeviceSn{deviceSn='" + this.deviceSn + "', deviceCode='" + this.deviceCode + "'}";
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("WechatDeviceRegistReq { productId = ");
        stringBuffer.append(this.productId);
        stringBuffer.append("deviceList = ").append(this.deviceList);
        stringBuffer.append(" }");
        return stringBuffer.toString();
    }

    public int getProductId() {
        return this.productId;
    }

    public void setProductId(int i) {
        this.productId = i;
    }

    public List<WechatDevice> getDeviceList() {
        return this.deviceList;
    }

    public void setDeviceList(List<WechatDevice> list) {
        this.deviceList = list;
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return Utils.e() + "/dataOpen/wechatDevice/regist";
    }
}
