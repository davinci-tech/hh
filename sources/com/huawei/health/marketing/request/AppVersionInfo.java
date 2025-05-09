package com.huawei.health.marketing.request;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes3.dex */
public class AppVersionInfo implements Parcelable {
    public static final Parcelable.Creator<AppVersionInfo> CREATOR = new Parcelable.Creator<AppVersionInfo>() { // from class: com.huawei.health.marketing.request.AppVersionInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AppVersionInfo createFromParcel(Parcel parcel) {
            return new AppVersionInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AppVersionInfo[] newArray(int i) {
            return new AppVersionInfo[i];
        }
    };
    private Integer appType;
    private String version;
    private Integer versionStatus;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Integer getAppType() {
        return this.appType;
    }

    public void setAppType(Integer num) {
        this.appType = num;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public Integer getVersionStatus() {
        return this.versionStatus;
    }

    public void setVersionStatus(Integer num) {
        this.versionStatus = num;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.appType);
        parcel.writeString(this.version);
        parcel.writeValue(this.versionStatus);
    }

    public AppVersionInfo() {
    }

    protected AppVersionInfo(Parcel parcel) {
        this.appType = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.version = parcel.readString();
        this.versionStatus = (Integer) parcel.readValue(Integer.class.getClassLoader());
    }
}
