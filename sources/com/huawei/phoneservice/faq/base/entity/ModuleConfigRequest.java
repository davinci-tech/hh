package com.huawei.phoneservice.faq.base.entity;

import com.google.gson.annotations.SerializedName;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;

/* loaded from: classes5.dex */
public class ModuleConfigRequest {

    @SerializedName("appId")
    private String appId;

    @SerializedName("appVersion")
    private String appVersion;

    @SerializedName("brandName")
    private String brand;

    @SerializedName("countryCode")
    private String countryCode;

    @SerializedName("emuiLangCode")
    private String emuiLanguageCode;

    @SerializedName(FaqConstants.FAQ_EMUIVERSION)
    private String emuiVersion;

    @SerializedName("machineType")
    private String machineType;

    @SerializedName("model")
    private String model;

    @SerializedName("sn")
    private String sn;

    public String toString() {
        return "ModuleConfigRequest{cCode='" + this.countryCode + "', eCode='" + this.emuiLanguageCode + "', b='" + this.brand + "', a='" + this.appId + "', eVersion='" + this.emuiVersion + "', aVersion='" + this.appVersion + "', m='" + this.model + "', m='" + this.machineType + "'}";
    }

    public void setSn(String str) {
        this.sn = str;
    }

    public void setModel(String str) {
        this.model = str;
    }

    public void setEmuiVersion(String str) {
        this.emuiVersion = str;
    }

    public void setEmuiLanguageCode(String str) {
        this.emuiLanguageCode = str;
    }

    public void setCountryCode(String str) {
        this.countryCode = str;
    }

    public void setBrand(String str) {
        this.brand = str;
    }

    public void setAppVersion(String str) {
        this.appVersion = str;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public String getSn() {
        return this.sn;
    }

    public String getModel() {
        return this.model;
    }

    public String getEmuiVersion() {
        return this.emuiVersion;
    }

    public String getEmuiLanguageCode() {
        return this.emuiLanguageCode;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public String getBrand() {
        return this.brand;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public String getAppId() {
        return this.appId;
    }

    public ModuleConfigRequest(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        this.countryCode = str;
        this.emuiLanguageCode = str2;
        this.brand = str3;
        this.appId = str4;
        this.sn = str5;
        this.emuiVersion = str6;
        this.appVersion = str7;
        this.model = str8;
        this.machineType = str9;
    }
}
