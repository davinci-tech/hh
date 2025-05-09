package com.huawei.pluginachievement.connectivity.config;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes6.dex */
public class AUserProfile implements Parcelable {
    public static final Parcelable.Creator<AUserProfile> CREATOR = new Parcelable.Creator<AUserProfile>() { // from class: com.huawei.pluginachievement.connectivity.config.AUserProfile.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: cfP_, reason: merged with bridge method [inline-methods] */
        public AUserProfile createFromParcel(Parcel parcel) {
            AUserProfile aUserProfile = new AUserProfile();
            aUserProfile.huid = parcel.readString();
            aUserProfile.version = parcel.readString();
            aUserProfile.tokenType = parcel.readInt();
            aUserProfile.token = parcel.readString();
            aUserProfile.appId = parcel.readString();
            aUserProfile.deviceType = parcel.readString();
            aUserProfile.deviceId = parcel.readString();
            aUserProfile.sysVersion = parcel.readString();
            aUserProfile.bindDeviceType = parcel.readString();
            aUserProfile.appType = parcel.readInt();
            aUserProfile.iVersion = parcel.readInt();
            aUserProfile.language = parcel.readString();
            aUserProfile.environment = parcel.readInt();
            aUserProfile.upDeviceType = parcel.readString();
            return aUserProfile;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public AUserProfile[] newArray(int i) {
            return new AUserProfile[i];
        }
    };
    private String appId;
    private int appType;
    private String bindDeviceType;
    private String deviceId;
    private String deviceType;
    private int environment;
    private String huid;
    private int iVersion;
    private String language;
    private String sysVersion;
    private String token;
    private int tokenType;
    private String upDeviceType;
    private String version;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getHuid() {
        return this.huid;
    }

    public void setHuid(String str) {
        this.huid = str;
    }

    public String getAppVersion() {
        return this.version;
    }

    public void setAppVersion(String str) {
        this.version = str;
    }

    public void setTokenType(int i) {
        this.tokenType = i;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public void setDeviceType(String str) {
        this.deviceType = str;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public String getSysVersion() {
        return this.sysVersion;
    }

    public void setSysVersion(String str) {
        this.sysVersion = str;
    }

    public String getBindDeviceType() {
        return this.bindDeviceType;
    }

    public void setBindDeviceType(String str) {
        this.bindDeviceType = str;
    }

    public int getAppType() {
        return this.appType;
    }

    public void setAppType(int i) {
        this.appType = i;
    }

    public int getiVersion() {
        return this.iVersion;
    }

    public void setiVersion(int i) {
        this.iVersion = i;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    public int getEnvironment() {
        return this.environment;
    }

    public void setEnvironment(int i) {
        this.environment = i;
    }

    public String getUpDeviceType() {
        return this.upDeviceType;
    }

    public void setUpDeviceType(String str) {
        this.upDeviceType = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.huid);
        parcel.writeString(this.version);
        parcel.writeInt(this.tokenType);
        parcel.writeString(this.token);
        parcel.writeString(this.appId);
        parcel.writeString(this.deviceType);
        parcel.writeString(this.deviceId);
        parcel.writeString(this.sysVersion);
        parcel.writeString(this.bindDeviceType);
        parcel.writeInt(this.appType);
        parcel.writeInt(this.iVersion);
        parcel.writeString(this.language);
        parcel.writeInt(this.environment);
        parcel.writeString(this.upDeviceType);
    }

    public String toString() {
        return "UserProfile{, version=" + this.version + ", tokenType=" + this.tokenType + ", token=" + this.token + ", appId=" + this.appId + ", deviceType=" + this.deviceType + ", deviceId=" + this.deviceId + ", sysVersion=" + this.sysVersion + ", bindDeviceType=" + this.bindDeviceType + ", appType=" + this.appType + ", iVersion=" + this.iVersion + ", language=" + this.language + ", environment=" + this.environment + ", upDeviceType=" + this.upDeviceType + '}';
    }
}
