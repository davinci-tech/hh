package com.huawei.notification;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes5.dex */
public class NotificationDeviceLinkage implements Parcelable {
    public static final Parcelable.Creator<NotificationDeviceLinkage> CREATOR = new Parcelable.Creator<NotificationDeviceLinkage>() { // from class: com.huawei.notification.NotificationDeviceLinkage.5
        @Override // android.os.Parcelable.Creator
        /* renamed from: bZm_, reason: merged with bridge method [inline-methods] */
        public NotificationDeviceLinkage createFromParcel(Parcel parcel) {
            return new NotificationDeviceLinkage(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public NotificationDeviceLinkage[] newArray(int i) {
            return new NotificationDeviceLinkage[i];
        }
    };

    @SerializedName("button_content")
    private String mButtonContent;

    @SerializedName("ping_class_name")
    private String mPingClassName;

    @SerializedName("ping_open_remote")
    private String mPingOpenRemote;

    @SerializedName("ping_open_type")
    private String mPingOpenType;

    @SerializedName("ping_package_name")
    private String mPingPackageName;

    @SerializedName("ping_url")
    private String mPingUrl;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public NotificationDeviceLinkage() {
    }

    protected NotificationDeviceLinkage(Parcel parcel) {
        if (parcel == null) {
            return;
        }
        this.mPingOpenRemote = parcel.readString();
        this.mPingOpenType = parcel.readString();
        this.mPingPackageName = parcel.readString();
        this.mPingClassName = parcel.readString();
        this.mPingUrl = parcel.readString();
        this.mButtonContent = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeString(this.mPingOpenRemote);
        parcel.writeString(this.mPingOpenType);
        parcel.writeString(this.mPingPackageName);
        parcel.writeString(this.mPingClassName);
        parcel.writeString(this.mPingUrl);
        parcel.writeString(this.mButtonContent);
    }

    public String getPingOpenRemote() {
        return this.mPingOpenRemote;
    }

    public void setPingOpenRemote(String str) {
        this.mPingOpenRemote = str;
    }

    public String getPingOpenType() {
        return this.mPingOpenType;
    }

    public void setPingOpenType(String str) {
        this.mPingOpenType = str;
    }

    public String getPingPackageName() {
        return this.mPingPackageName;
    }

    public void setPingPackageName(String str) {
        this.mPingPackageName = str;
    }

    public String getPingClassName() {
        return this.mPingClassName;
    }

    public void setPingClassName(String str) {
        this.mPingClassName = str;
    }

    public String getPingUrl() {
        return this.mPingUrl;
    }

    public void setPingUrl(String str) {
        this.mPingUrl = str;
    }

    public String getButtonContent() {
        return this.mButtonContent;
    }

    public void setButtonContent(String str) {
        this.mButtonContent = str;
    }

    public String toString() {
        return "NotificationDeviceLinkage{mPingOpenRemote=" + this.mPingOpenRemote + ", mPingOpenType=" + this.mPingOpenType + ", mPingPackageName=" + this.mPingPackageName + ", mPingClassName=" + this.mPingClassName + ", mPingUrl=" + this.mPingUrl + ", mButtonContent=" + this.mButtonContent + '}';
    }
}
