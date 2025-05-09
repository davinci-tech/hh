package com.huawei.health.devicemgr.business.entity.downloadresouce;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class DeviceDownloadSourceInfo implements Parcelable, Cloneable {
    public static final Parcelable.Creator<DeviceDownloadSourceInfo> CREATOR = new Parcelable.Creator<DeviceDownloadSourceInfo>() { // from class: com.huawei.health.devicemgr.business.entity.downloadresouce.DeviceDownloadSourceInfo.4
        @Override // android.os.Parcelable.Creator
        /* renamed from: QJ_, reason: merged with bridge method [inline-methods] */
        public DeviceDownloadSourceInfo createFromParcel(Parcel parcel) {
            return new DeviceDownloadSourceInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public DeviceDownloadSourceInfo[] newArray(int i) {
            return new DeviceDownloadSourceInfo[i];
        }
    };

    @SerializedName("configurationPoint")
    private String mConfigurationPoint;

    @SerializedName("indexName")
    private String mIndexName;

    @SerializedName("site")
    private int mSite;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public DeviceDownloadSourceInfo(Parcel parcel) {
        if (parcel != null) {
            this.mSite = parcel.readInt();
            this.mIndexName = parcel.readString();
            this.mConfigurationPoint = parcel.readString();
        }
    }

    public int getSite() {
        return this.mSite;
    }

    public void setSite(int i) {
        this.mSite = i;
    }

    public String getIndexName() {
        return this.mIndexName;
    }

    public void setIndexName(String str) {
        this.mIndexName = str;
    }

    public String getConfigurationPoint() {
        return this.mConfigurationPoint;
    }

    public void setConfigurationPoint(String str) {
        this.mConfigurationPoint = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel != null) {
            parcel.writeInt(this.mSite);
            parcel.writeString(this.mIndexName);
            parcel.writeString(this.mConfigurationPoint);
        }
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public DeviceDownloadSourceInfo m274clone() {
        DeviceDownloadSourceInfo deviceDownloadSourceInfo;
        try {
        } catch (CloneNotSupportedException unused) {
            deviceDownloadSourceInfo = new DeviceDownloadSourceInfo(null);
            deviceDownloadSourceInfo.setConfigurationPoint(this.mConfigurationPoint);
            deviceDownloadSourceInfo.setIndexName(this.mIndexName);
            deviceDownloadSourceInfo.setSite(this.mSite);
        }
        if (!(super.clone() instanceof DeviceDownloadSourceInfo)) {
            return null;
        }
        deviceDownloadSourceInfo = (DeviceDownloadSourceInfo) super.clone();
        return deviceDownloadSourceInfo;
    }

    public DeviceDownloadSourceInfo cloneOldDeviceDownloadSourceInfo(com.huawei.pluginmgr.hwwear.bean.DeviceDownloadSourceInfo deviceDownloadSourceInfo) {
        if (deviceDownloadSourceInfo == null) {
            return null;
        }
        DeviceDownloadSourceInfo deviceDownloadSourceInfo2 = new DeviceDownloadSourceInfo(null);
        deviceDownloadSourceInfo2.setConfigurationPoint(deviceDownloadSourceInfo.getConfigurationPoint());
        deviceDownloadSourceInfo2.setIndexName(deviceDownloadSourceInfo.getIndexName());
        deviceDownloadSourceInfo2.setSite(deviceDownloadSourceInfo.getSite());
        return deviceDownloadSourceInfo2;
    }
}
