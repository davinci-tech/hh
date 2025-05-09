package com.huawei.pluginmgr.hwwear.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.huawei.openalliance.ad.constant.AdShowExtras;
import java.util.List;

/* loaded from: classes6.dex */
public class DeviceFittingsBean implements Parcelable {
    public static final Parcelable.Creator<DeviceFittingsBean> CREATOR = new Parcelable.Creator<DeviceFittingsBean>() { // from class: com.huawei.pluginmgr.hwwear.bean.DeviceFittingsBean.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: cor_, reason: merged with bridge method [inline-methods] */
        public DeviceFittingsBean createFromParcel(Parcel parcel) {
            return new DeviceFittingsBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public DeviceFittingsBean[] newArray(int i) {
            return new DeviceFittingsBean[i];
        }
    };

    @SerializedName("bluetooth_match")
    private String mBluetoothMatch;

    @SerializedName("bluetooth_name")
    private List<String> mBluetoothNameList;

    @SerializedName(AdShowExtras.DOWNLOAD_SOURCE)
    private DeviceDownloadSourceInfo mDeviceDownloadSourceInfo;

    @SerializedName("uuid")
    private List<String> mUuidList;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public DeviceFittingsBean(Parcel parcel) {
        if (parcel != null) {
            this.mBluetoothMatch = parcel.readString();
            this.mBluetoothNameList = parcel.readArrayList(DeviceFittingsBean.class.getClassLoader());
            this.mUuidList = parcel.readArrayList(DeviceFittingsBean.class.getClassLoader());
            this.mDeviceDownloadSourceInfo = (DeviceDownloadSourceInfo) parcel.readParcelable(DeviceFittingsBean.class.getClassLoader());
        }
    }

    public String getBluetoothMatch() {
        return this.mBluetoothMatch;
    }

    public void setBluetoothMatch(String str) {
        this.mBluetoothMatch = str;
    }

    public List<String> getBluetoothNameList() {
        return this.mBluetoothNameList;
    }

    public void setBluetoothNameList(List<String> list) {
        this.mBluetoothNameList = list;
    }

    public List<String> getUuid() {
        return this.mUuidList;
    }

    public void setUuidList(List<String> list) {
        this.mUuidList = list;
    }

    public DeviceDownloadSourceInfo getDeviceDownloadSourceInfo() {
        return this.mDeviceDownloadSourceInfo;
    }

    public void setDeviceDownloadSourceInfo(DeviceDownloadSourceInfo deviceDownloadSourceInfo) {
        this.mDeviceDownloadSourceInfo = deviceDownloadSourceInfo;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel != null) {
            parcel.writeString(this.mBluetoothMatch);
            parcel.writeList(this.mBluetoothNameList);
            parcel.writeList(this.mUuidList);
            parcel.writeParcelable(this.mDeviceDownloadSourceInfo, i);
        }
    }
}
