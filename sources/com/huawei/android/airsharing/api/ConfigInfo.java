package com.huawei.android.airsharing.api;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class ConfigInfo implements Parcelable {
    public static final int ACTIVE_MATCH_TAG = 2;
    public static final Parcelable.Creator<ConfigInfo> CREATOR = new Parcelable.Creator<ConfigInfo>() { // from class: com.huawei.android.airsharing.api.ConfigInfo.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: du_, reason: merged with bridge method [inline-methods] */
        public ConfigInfo createFromParcel(Parcel parcel) {
            return new ConfigInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public ConfigInfo[] newArray(int i) {
            return new ConfigInfo[i];
        }
    };
    public static final int PASSIVE_BIND_TAG = 3;
    public static final int PASSIVE_MATCH_TAG = 1;
    public static final int UNSPEC_TAG = 0;
    private String mAuthData;
    private String mDeviceId;
    private String mPackageName;
    private int mScanType;
    private int mTriggerType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ConfigInfo() {
        this(48);
    }

    public ConfigInfo(int i) {
        this(i, null, 0, null, null);
    }

    public ConfigInfo(String str, int i, String str2, String str3) {
        this(48, str, i, str2, str3);
    }

    public ConfigInfo(int i, String str, int i2, String str2, String str3) {
        this.mScanType = i;
        this.mDeviceId = str;
        this.mTriggerType = i2;
        this.mAuthData = str2;
        this.mPackageName = str3;
    }

    protected ConfigInfo(Parcel parcel) {
        this(parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readString());
    }

    public void setScanType(int i) {
        this.mScanType = i;
    }

    public int getScanType() {
        return this.mScanType;
    }

    public String getDeviceId() {
        return this.mDeviceId;
    }

    public int getTriggerType() {
        return this.mTriggerType;
    }

    public String getAuthData() {
        return this.mAuthData;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeInt(this.mScanType);
        parcel.writeString(this.mDeviceId);
        parcel.writeInt(this.mTriggerType);
        parcel.writeString(this.mAuthData);
        parcel.writeString(this.mPackageName);
    }
}
