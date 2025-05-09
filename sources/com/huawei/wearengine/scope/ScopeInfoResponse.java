package com.huawei.wearengine.scope;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.Map;

/* loaded from: classes8.dex */
public class ScopeInfoResponse {
    private String appId;

    @SerializedName("appAttr")
    private Map<String, String> mAppAttr;

    @SerializedName("certFingerprint")
    private String mCertFingerprint;

    @SerializedName("certFingerprintExtra")
    private String mCertFingerprintExtra;

    @SerializedName("expireTime")
    private long mExpireTime;

    @SerializedName("failureExpireTime")
    private long mFailureExpireTime;

    @SerializedName("scopes")
    private List<ScopeInfo> mScopes;

    @SerializedName("venderCode")
    private String mVenderCode;
    private long timeStamp;

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(long j) {
        this.timeStamp = j;
    }

    public long getExpireTime() {
        return this.mExpireTime;
    }

    public void setExpireTime(long j) {
        this.mExpireTime = j;
    }

    public String getVenderCode() {
        return this.mVenderCode;
    }

    public void setVenderCode(String str) {
        this.mVenderCode = str;
    }

    public String getCertFingerprint() {
        return this.mCertFingerprint;
    }

    public void setCertFingerprint(String str) {
        this.mCertFingerprint = str;
    }

    public long getFailureExpireTime() {
        return this.mFailureExpireTime;
    }

    public void setFailureExpireTime(long j) {
        this.mFailureExpireTime = j;
    }

    public List<ScopeInfo> getScopes() {
        return this.mScopes;
    }

    public void setScopes(List<ScopeInfo> list) {
        this.mScopes = list;
    }

    public String getCertFingerprintExtra() {
        return this.mCertFingerprintExtra;
    }

    public void setCertFingerprintExtra(String str) {
        this.mCertFingerprintExtra = str;
    }

    public Map<String, String> getAppAttr() {
        return this.mAppAttr;
    }

    public void setAppAttr(Map<String, String> map) {
        this.mAppAttr = map;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ScopeInfoResponse:{expireTime=");
        sb.append(this.mExpireTime);
        sb.append(",certFingerprint=");
        sb.append(this.mCertFingerprint);
        sb.append(",certFingerprintExtra=");
        sb.append(this.mCertFingerprintExtra);
        sb.append(",failureExpireTime=");
        sb.append(this.mFailureExpireTime);
        sb.append(",venderCode=");
        sb.append(this.mVenderCode);
        if (this.mScopes != null) {
            sb.append(",scopes=");
            sb.append(this.mScopes.toString());
        }
        sb.append("}");
        return sb.toString();
    }
}
