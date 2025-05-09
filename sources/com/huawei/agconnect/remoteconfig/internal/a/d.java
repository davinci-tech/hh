package com.huawei.agconnect.remoteconfig.internal.a;

import com.huawei.agconnect.common.api.BaseRequest;
import com.huawei.agconnect.https.annotation.Field;
import com.huawei.agconnect.https.annotation.Header;
import com.huawei.agconnect.https.annotation.Url;
import java.util.List;
import java.util.Map;

/* loaded from: classes2.dex */
public class d extends BaseRequest {

    @Url
    private static final String URL = "http://localhost/agc/apigw/rcs/remote-configuration-service/v2/usage/config";

    @Field("aaId")
    private String aaId;

    @Field("country")
    private String country;

    @Field("customAttributes")
    private List<Map<String, String>> customProperties;

    @Field("dateTime")
    private long dateTime;

    @Field("deviceChip")
    private String deviceChip;

    @Field("language")
    private String language;

    @Field("platformVersion")
    private String platformVersion;

    @Field("script")
    private String script;

    @Header("tag")
    private String tag;

    @Field("userAttributes")
    private List<Map<String, String>> userProperties;

    @Field("versionName")
    private String versionName;

    public void setVersionName(String str) {
        this.versionName = str;
    }

    public void setUserProperties(List<Map<String, String>> list) {
        this.userProperties = list;
    }

    public void setTag(String str) {
        this.tag = str;
    }

    public void setScript(String str) {
        this.script = str;
    }

    public void setPlatformVersion(String str) {
        this.platformVersion = str;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    public void setDeviceChip(String str) {
        this.deviceChip = str;
    }

    public void setDateTime(long j) {
        this.dateTime = j;
    }

    public void setCustomProperties(List<Map<String, String>> list) {
        this.customProperties = list;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public void setAaId(String str) {
        this.aaId = str;
    }

    public String getVersionName() {
        return this.versionName;
    }

    public List<Map<String, String>> getUserProperties() {
        return this.userProperties;
    }

    public String getTag() {
        return this.tag;
    }

    public String getScript() {
        return this.script;
    }

    public String getPlatformVersion() {
        return this.platformVersion;
    }

    public String getLanguage() {
        return this.language;
    }

    public long getDateTime() {
        return this.dateTime;
    }

    public List<Map<String, String>> getCustomProperties() {
        return this.customProperties;
    }

    public String getCountry() {
        return this.country;
    }

    public String getAaId() {
        return this.aaId;
    }

    public d() {
        setSdkServiceName("agconnect-remoteconfig");
        setSdkVersion("1.9.1.304");
    }
}
