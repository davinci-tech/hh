package com.huawei.hms.hihealth.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.common.internal.Preconditions;
import com.huawei.hms.health.aacs;
import java.util.Objects;

/* loaded from: classes9.dex */
public class AppInfo implements Parcelable {
    public static final Parcelable.Creator<AppInfo> CREATOR = new aab();
    private String appId;
    private final String detailsUrl;
    private final String domainName;
    private final String packageName;
    private final String version;

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getPackageName());
        parcel.writeString(getDomainName());
        parcel.writeString(getVersion());
        parcel.writeString(getDetailsUrl());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("AppInfo{");
        if (this.packageName != null) {
            sb.append(":");
            sb.append(this.packageName);
        }
        if (this.domainName != null) {
            sb.append(":");
            sb.append(this.domainName);
        }
        if (this.version != null) {
            sb.append(":");
            sb.append(this.version);
        }
        if (this.detailsUrl != null) {
            sb.append(":");
            sb.append(this.detailsUrl);
        }
        sb.append("}");
        return sb.toString();
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public int hashCode() {
        return Objects.hash(this.packageName, this.domainName, this.version, this.detailsUrl);
    }

    public String getVersion() {
        return this.version;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public String getDomainName() {
        return this.domainName;
    }

    public String getDetailsUrl() {
        return this.detailsUrl;
    }

    public String getAppId() {
        return this.appId;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AppInfo)) {
            return false;
        }
        AppInfo appInfo = (AppInfo) obj;
        return Objects.equals(this.packageName, appInfo.packageName) && Objects.equals(this.domainName, appInfo.domainName) && Objects.equals(this.version, appInfo.version) && Objects.equals(this.detailsUrl, appInfo.detailsUrl);
    }

    public AppInfo(String str, String str2, String str3, String str4) {
        Preconditions.checkArgument(str == null || aacs.aabc(str), "PackageName Length Is Illegal!");
        this.packageName = str;
        this.domainName = str2;
        this.version = str3;
        this.detailsUrl = str4;
    }

    class aab implements Parcelable.Creator<AppInfo> {
        @Override // android.os.Parcelable.Creator
        public AppInfo createFromParcel(Parcel parcel) {
            return new AppInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public AppInfo[] newArray(int i) {
            return new AppInfo[i];
        }

        aab() {
        }
    }

    protected AppInfo(Parcel parcel) {
        this.packageName = parcel.readString();
        this.domainName = parcel.readString();
        this.version = parcel.readString();
        this.detailsUrl = parcel.readString();
    }
}
