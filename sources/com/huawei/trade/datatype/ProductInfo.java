package com.huawei.trade.datatype;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.hms.support.api.entity.pay.HwPayConstant;

/* loaded from: classes6.dex */
public class ProductInfo extends TradeBaseInfo {

    @SerializedName("areaInfo")
    private String areaInfo;

    @SerializedName("certChain")
    private String certChain;

    @SerializedName("clientVersion")
    private String clientVersion;

    @SerializedName("deviceCategory")
    private int deviceCategory;

    @SerializedName("deviceNumber")
    private String deviceNumber;

    @SerializedName("devicePerfPurchaseId")
    private String devicePerfPurchaseId;

    @SerializedName(HealthEngineRequestManager.PARAMS_DEVICE_SN)
    private String deviceSn;

    @SerializedName("deviceType")
    private String deviceType;

    @SerializedName("linkType")
    private int linkType;

    @SerializedName("linkValue")
    private String linkValue;

    @SerializedName(HwPayConstant.KEY_PRODUCTNAME)
    private String productName;

    @SerializedName("promotionId")
    private String promotionId;

    @SerializedName("promotionPolicyId")
    private String promotionPolicyId;

    @SerializedName("purchasePolicy")
    private int purchasePolicy;

    @SerializedName("resourceType")
    private int resourceType;

    @SerializedName("salt")
    private String salt;

    @SerializedName("trigResMemberPrice")
    private String trigResMemberPrice;

    @SerializedName("trigResName")
    private String trigResName;

    @SerializedName("trigResType")
    private String trigResType;

    public String getPromotionId() {
        return this.promotionId;
    }

    public void setPromotionId(String str) {
        this.promotionId = str;
    }

    public int getPurchasePolicy() {
        return this.purchasePolicy;
    }

    public void setPurchasePolicy(int i) {
        this.purchasePolicy = i;
    }

    public void setDeviceSn(String str) {
        this.deviceSn = str;
    }

    public String getDeviceSn() {
        return this.deviceSn;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceType(String str) {
        this.deviceType = str;
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

    public int getResourceType() {
        return this.resourceType;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setResourceType(int i) {
        this.resourceType = i;
    }

    public void setProductName(String str) {
        this.productName = str;
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

    public String getTrigResType() {
        return this.trigResType;
    }

    public String getTrigResName() {
        return this.trigResName;
    }

    public String getTrigResMemberPrice() {
        return this.trigResMemberPrice;
    }

    public String getAreaInfo() {
        return this.areaInfo;
    }

    public void setTrigResMemberPrice(String str) {
        this.trigResMemberPrice = str;
    }

    public void setTrigResName(String str) {
        this.trigResName = str;
    }

    public void setTrigResType(String str) {
        this.trigResType = str;
    }

    public void setAreaInfo(String str) {
        this.areaInfo = str;
    }

    public int getLinkType() {
        return this.linkType;
    }

    public void setLinkType(int i) {
        this.linkType = i;
    }

    public String getLinkValue() {
        String str = this.linkValue;
        return str == null ? "" : str;
    }

    public void setLinkValue(String str) {
        this.linkValue = str;
    }

    public String getPromotionPolicyId() {
        return this.promotionPolicyId;
    }

    public void setPromotionPolicyId(String str) {
        this.promotionPolicyId = str;
    }

    public int getDeviceCategory() {
        return this.deviceCategory;
    }

    public void setDeviceCategory(int i) {
        this.deviceCategory = i;
    }
}
