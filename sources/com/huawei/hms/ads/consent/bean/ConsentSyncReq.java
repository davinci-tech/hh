package com.huawei.hms.ads.consent.bean;

import com.huawei.openalliance.ad.annotations.a;
import com.huawei.openalliance.ad.annotations.g;
import java.util.List;

/* loaded from: classes9.dex */
public class ConsentSyncReq extends ConsentSyncBase {

    @a
    private String accessToken;

    @g
    private String deviceId;
    private int deviceIdType;
    private String pkgName;
    private String sdkversion;
    private Long timestamp;

    public void setTimestamp(Long l) {
        this.timestamp = l;
    }

    public void setSdkversion(String str) {
        this.sdkversion = str;
    }

    public void setPkgName(String str) {
        this.pkgName = str;
    }

    public void setDeviceIdType(int i) {
        this.deviceIdType = i;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public void setAccessToken(String str) {
        this.accessToken = str;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public String getSdkversion() {
        return this.sdkversion;
    }

    public String getPkgName() {
        return this.pkgName;
    }

    public int getDeviceIdType() {
        return this.deviceIdType;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public ConsentSyncReq(List<String> list, int i, String str) {
        super(list, i);
        this.pkgName = str;
    }
}
