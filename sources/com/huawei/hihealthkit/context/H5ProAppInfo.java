package com.huawei.hihealthkit.context;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes4.dex */
public class H5ProAppInfo extends OutOfBandData {
    public static final Parcelable.Creator<H5ProAppInfo> CREATOR = new Parcelable.Creator<H5ProAppInfo>() { // from class: com.huawei.hihealthkit.context.H5ProAppInfo.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: bwS_, reason: merged with bridge method [inline-methods] */
        public H5ProAppInfo createFromParcel(Parcel parcel) {
            H5ProAppInfo h5ProAppInfo = new H5ProAppInfo();
            h5ProAppInfo.appName = parcel.readString();
            h5ProAppInfo.appId = parcel.readString();
            h5ProAppInfo.pkgName = parcel.readString();
            h5ProAppInfo.certPrint = parcel.readString();
            h5ProAppInfo.accessToken = parcel.readString();
            h5ProAppInfo.url = parcel.readString();
            h5ProAppInfo.privilegedFlag = parcel.readInt();
            return h5ProAppInfo;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public H5ProAppInfo[] newArray(int i) {
            return new H5ProAppInfo[i];
        }
    };
    private String accessToken;
    private String appId;
    private String appName;
    private String certPrint;
    private String pkgName;
    private int privilegedFlag;
    private String url;

    @Override // com.huawei.hihealthkit.context.OutOfBandData, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public H5ProAppInfo() {
        this.tag = 2;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public String getCertPrint() {
        return this.certPrint;
    }

    public void setCertPrint(String str) {
        this.certPrint = str;
    }

    public String getPkgName() {
        return this.pkgName;
    }

    public void setPkgName(String str) {
        this.pkgName = str;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String str) {
        this.appName = str;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String str) {
        this.accessToken = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public int getPrivilegedFlag() {
        return this.privilegedFlag;
    }

    public void setPrivilegedFlag(int i) {
        this.privilegedFlag = i;
    }

    @Override // com.huawei.hihealthkit.context.OutOfBandData, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.appName);
        parcel.writeString(this.appId);
        parcel.writeString(this.pkgName);
        parcel.writeString(this.certPrint);
        parcel.writeString(this.accessToken);
        parcel.writeString(this.url);
        parcel.writeInt(this.privilegedFlag);
    }

    public String toString() {
        return "";
    }
}
