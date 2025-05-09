package com.huawei.maps.offlinedata.handler.dto;

import com.google.gson.annotations.SerializedName;
import com.huawei.hms.kit.awareness.b.a.a;
import java.util.Set;

/* loaded from: classes9.dex */
public class ConfigParams {

    @SerializedName(a.g)
    private String apiKey;

    @SerializedName("cloudServiceUrl")
    private String cloudServiceUrl;

    @SerializedName("dataTypes")
    private Set<String> dataTypes;

    @SerializedName("deviceName")
    private String deviceName;

    @SerializedName("deviceType")
    private String deviceType;

    @SerializedName("political")
    private String political;

    @SerializedName("recommendCityIds")
    private String[] recommendCityIds;

    @SerializedName("versionCode")
    private String versionCode;

    public String[] getRecommendCityIds() {
        return this.recommendCityIds;
    }

    public void setRecommendCityIds(String[] strArr) {
        this.recommendCityIds = strArr;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public void setDeviceName(String str) {
        this.deviceName = str;
    }

    public String getApiKey() {
        return this.apiKey;
    }

    public void setApiKey(String str) {
        this.apiKey = str;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceType(String str) {
        this.deviceType = str;
    }

    public Set<String> getDataTypes() {
        return this.dataTypes;
    }

    public void setDataTypes(Set<String> set) {
        this.dataTypes = set;
    }

    public String getPolitical() {
        return this.political;
    }

    public void setPolitical(String str) {
        this.political = str;
    }

    public String getCloudServiceUrl() {
        return this.cloudServiceUrl;
    }

    public void setCloudServiceUrl(String str) {
        this.cloudServiceUrl = str;
    }

    public String isVersionCode() {
        return this.versionCode;
    }

    public void setVersionCode(String str) {
        this.versionCode = str;
    }
}
