package com.huawei.hihealthkit.context;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes4.dex */
public class QuickAppInfo extends OutOfBandData {
    public static final Parcelable.Creator<QuickAppInfo> CREATOR = new Parcelable.Creator<QuickAppInfo>() { // from class: com.huawei.hihealthkit.context.QuickAppInfo.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: bwV_, reason: merged with bridge method [inline-methods] */
        public QuickAppInfo createFromParcel(Parcel parcel) {
            QuickAppInfo quickAppInfo = new QuickAppInfo();
            quickAppInfo.name = parcel.readString();
            quickAppInfo.appId = parcel.readString();
            quickAppInfo.packageName = parcel.readString();
            quickAppInfo.fingerPrint = parcel.readString();
            return quickAppInfo;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public QuickAppInfo[] newArray(int i) {
            return new QuickAppInfo[i];
        }
    };
    private String appId;
    private String fingerPrint;
    private String name;
    private String packageName;

    @Override // com.huawei.hihealthkit.context.OutOfBandData, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public QuickAppInfo() {
        this.tag = 1;
    }

    public String getAppId() {
        return this.appId;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public void setPackageName(String str) {
        this.packageName = str;
    }

    public String getFingerPrint() {
        return this.fingerPrint;
    }

    public void setFingerPrint(String str) {
        this.fingerPrint = str;
    }

    @Override // com.huawei.hihealthkit.context.OutOfBandData, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.name);
        parcel.writeString(this.appId);
        parcel.writeString(this.packageName);
        parcel.writeString(this.fingerPrint);
    }
}
