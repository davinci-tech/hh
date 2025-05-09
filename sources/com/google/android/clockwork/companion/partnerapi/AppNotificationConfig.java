package com.google.android.clockwork.companion.partnerapi;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class AppNotificationConfig implements Parcelable {
    public static final Parcelable.Creator<AppNotificationConfig> CREATOR = new Parcelable.Creator<AppNotificationConfig>() { // from class: com.google.android.clockwork.companion.partnerapi.AppNotificationConfig.5
        @Override // android.os.Parcelable.Creator
        /* renamed from: bG_, reason: merged with bridge method [inline-methods] */
        public AppNotificationConfig createFromParcel(Parcel parcel) {
            return new AppNotificationConfig(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public AppNotificationConfig[] newArray(int i) {
            if (i > 65535 || i < 0) {
                return null;
            }
            return new AppNotificationConfig[i];
        }
    };
    private String appName;
    private boolean canChangeMuteSetting;
    private int notificationStatus;
    private String packageName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public AppNotificationConfig() {
    }

    protected AppNotificationConfig(Parcel parcel) {
        this.packageName = parcel.readString();
        this.appName = parcel.readString();
        this.canChangeMuteSetting = parcel.readInt() == 1;
        this.notificationStatus = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getPackageName());
        parcel.writeString(getAppName());
        parcel.writeInt(getCanChangeMuteSetting() ? 1 : 0);
        parcel.writeInt(getNotificationStatus());
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

    public boolean getCanChangeMuteSetting() {
        return this.canChangeMuteSetting;
    }

    private void setCanChangeMuteSetting(boolean z) {
        this.canChangeMuteSetting = z;
    }

    public int getNotificationStatus() {
        return this.notificationStatus;
    }

    public void setNotificationStatus(int i) {
        this.notificationStatus = i;
    }
}
