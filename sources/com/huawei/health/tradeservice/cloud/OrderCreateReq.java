package com.huawei.health.tradeservice.cloud;

import com.google.gson.annotations.SerializedName;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.networkclient.IRequest;
import com.huawei.operation.utils.CloudParamKeys;
import health.compact.a.GRSManager;

/* loaded from: classes4.dex */
public class OrderCreateReq implements IRequest {

    @SerializedName("certChain")
    private String certChain;

    @SerializedName(CloudParamKeys.CLIENT_TYPE)
    private int clientType;

    @SerializedName("clientVersion")
    private String clientVersion;

    @SerializedName("country")
    private String country;

    @SerializedName("deviceNumber")
    private String deviceNumber;

    @SerializedName("devicePerfPurchaseId")
    private String devicePerfPurchaseId;

    @SerializedName("deviceSN")
    private String deviceSN;

    @SerializedName("deviceType")
    private String deviceType;

    @SerializedName("productId")
    private String productId;

    @SerializedName("salt")
    private String salt;

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return GRSManager.a(BaseApplication.getContext()).getUrl("tradeService") + "/tradeservice/v1/order/create";
    }

    public int getClientType() {
        return this.clientType;
    }

    public void setClientType(int i) {
        this.clientType = i;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String str) {
        this.productId = str;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceType(String str) {
        this.deviceType = str;
    }

    public String getDeviceSN() {
        return this.deviceSN;
    }

    public void setDeviceSN(String str) {
        this.deviceSN = str;
    }

    public String getSalt() {
        return this.salt;
    }

    public void setSalt(String str) {
        this.salt = str;
    }

    public String getDeviceNumber() {
        return this.deviceNumber;
    }

    public void setDeviceNumber(String str) {
        this.deviceNumber = str;
    }

    public String getCertChain() {
        return this.certChain;
    }

    public void setCertChain(String str) {
        this.certChain = str;
    }

    public String getDevicePerfPurchaseId() {
        return this.devicePerfPurchaseId;
    }

    public void setDevicePerfPurchaseId(String str) {
        this.devicePerfPurchaseId = str;
    }

    public String getClientVersion() {
        return this.clientVersion;
    }

    public void setClientVersion(String str) {
        this.clientVersion = str;
    }
}
