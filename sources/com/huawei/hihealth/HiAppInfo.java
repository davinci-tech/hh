package com.huawei.hihealth;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class HiAppInfo implements Parcelable {
    public static final Parcelable.Creator<HiAppInfo> CREATOR = new Parcelable.Creator<HiAppInfo>() { // from class: com.huawei.hihealth.HiAppInfo.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: bun_, reason: merged with bridge method [inline-methods] */
        public HiAppInfo createFromParcel(Parcel parcel) {
            return new HiAppInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public HiAppInfo[] newArray(int i) {
            return new HiAppInfo[i];
        }
    };
    private int appId;
    private String appName;
    private long cloudCode;
    private String packageName;
    private String signature;
    private String version;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HiAppInfo() {
    }

    protected HiAppInfo(Parcel parcel) {
        this.appId = parcel.readInt();
        this.packageName = parcel.readString();
        this.appName = parcel.readString();
        this.version = parcel.readString();
        this.signature = parcel.readString();
        this.cloudCode = parcel.readLong();
    }

    public int getAppId() {
        return this.appId;
    }

    public void setAppId(int i) {
        this.appId = i;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String str) {
        this.appName = str;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public String getSignature() {
        return this.signature;
    }

    public void setSignature(String str) {
        this.signature = str;
    }

    public long getCloudCode() {
        return this.cloudCode;
    }

    public void setCloudCode(long j) {
        this.cloudCode = j;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("HiAppInfo{appId=");
        stringBuffer.append(this.appId);
        stringBuffer.append(", packageName='").append(this.packageName).append("', appName='");
        stringBuffer.append(this.appName).append("', version='");
        stringBuffer.append(this.version).append("', cloudCode=");
        stringBuffer.append(this.cloudCode);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.appId);
        parcel.writeString(this.packageName);
        parcel.writeString(this.appName);
        parcel.writeString(this.version);
        parcel.writeString(this.signature);
        parcel.writeLong(this.cloudCode);
    }
}
