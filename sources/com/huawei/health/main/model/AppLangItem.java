package com.huawei.health.main.model;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes3.dex */
public class AppLangItem implements Parcelable {
    public static final Parcelable.Creator<AppLangItem> CREATOR = new Parcelable.Creator<AppLangItem>() { // from class: com.huawei.health.main.model.AppLangItem.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: ajT_, reason: merged with bridge method [inline-methods] */
        public AppLangItem createFromParcel(Parcel parcel) {
            return new AppLangItem(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public AppLangItem[] newArray(int i) {
            return new AppLangItem[i];
        }
    };
    private String appIconPath;
    private String appId;
    private String appName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public AppLangItem() {
    }

    protected AppLangItem(Parcel parcel) {
        this.appId = parcel.readString();
        this.appName = parcel.readString();
        this.appIconPath = parcel.readString();
    }

    public String getAppId() {
        return this.appId;
    }

    public String getAppName() {
        return this.appName;
    }

    public String getAppIconPath() {
        return this.appIconPath;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public void setAppName(String str) {
        this.appName = str;
    }

    public void setAppIconPath(String str) {
        this.appIconPath = str;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("AppLangItem{appId=");
        stringBuffer.append(this.appId);
        stringBuffer.append(", appName='").append(this.appName).append("', appIconPath='");
        stringBuffer.append(this.appIconPath).append("'}");
        return stringBuffer.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.appId);
        parcel.writeString(this.appName);
        parcel.writeString(this.appIconPath);
    }
}
