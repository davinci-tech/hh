package com.huawei.wearengine.device;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import defpackage.tsc;

/* loaded from: classes7.dex */
public class Device implements Parcelable {
    public static final Parcelable.Creator<Device> CREATOR = new Parcelable.Creator<Device>() { // from class: com.huawei.wearengine.device.Device.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: fde_, reason: merged with bridge method [inline-methods] */
        public Device createFromParcel(Parcel parcel) {
            Device device = new Device();
            if (parcel == null) {
                return device;
            }
            device.setName(parcel.readString());
            device.setUuid(parcel.readString());
            device.setModel(parcel.readString());
            device.setProductType(parcel.readInt());
            device.setConnectState(parcel.readInt());
            device.setReservedness(parcel.readString());
            return device;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Device[] newArray(int i) {
            return (i > 65535 || i < 0) ? new Device[0] : new Device[i];
        }
    };
    private static final int DEFAULT_PRODUCT_TYPE = -1;
    public static final int DEVICE_CAPABILITY_NOT_SUPPORT = 1;
    public static final int DEVICE_CAPABILITY_SUPPORT = 0;
    public static final int DEVICE_CAPABILITY_UNKNOWN = 2;
    public static final int DEVICE_CONNECTED = 2;
    public static final int ERR_HASH_CODE = -1;
    private int mConnectState;
    private String mModel;
    private String mName;
    private int mProductType = -1;
    private String mReservedness;
    private String mUuid;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public String getUuid() {
        return this.mUuid;
    }

    public void setUuid(String str) {
        this.mUuid = str;
    }

    public String getModel() {
        return this.mModel;
    }

    public void setModel(String str) {
        this.mModel = str;
    }

    public int getProductType() {
        return this.mProductType;
    }

    public void setProductType(int i) {
        this.mProductType = i;
    }

    public void setConnectState(int i) {
        this.mConnectState = i;
    }

    public boolean isConnected() {
        return this.mConnectState == 2;
    }

    public String getReservedness() {
        String f = tsc.f(this.mReservedness);
        return TextUtils.isEmpty(f) ? this.mReservedness : f;
    }

    public void setReservedness(String str) {
        this.mReservedness = str;
    }

    public String getCapability() {
        return tsc.b(this.mReservedness);
    }

    public String getBasicInfo() {
        return tsc.a(this.mReservedness);
    }

    public String getIdentify() {
        return tsc.i(this.mReservedness);
    }

    public String getSoftwareVersion() {
        return tsc.n(this.mReservedness);
    }

    public boolean isSupportOta() {
        return tsc.l(this.mReservedness);
    }

    public int getP2pCapability() {
        return tsc.h(this.mReservedness);
    }

    public int getMonitorCapability() {
        return tsc.g(this.mReservedness);
    }

    public int getNotifyCapability() {
        return tsc.j(this.mReservedness);
    }

    public int getSensorCapability() {
        return tsc.k(this.mReservedness);
    }

    public String getDeviceCategory() {
        return tsc.d(this.mReservedness);
    }

    public String getExtra() {
        return tsc.e(this.mReservedness);
    }

    public int getDeviceType() {
        return tsc.c(this.mReservedness);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeString(this.mName);
        parcel.writeString(this.mUuid);
        parcel.writeString(this.mModel);
        parcel.writeInt(this.mProductType);
        parcel.writeInt(this.mConnectState);
        parcel.writeString(this.mReservedness);
    }

    public void readFromParcel(Parcel parcel) {
        if (parcel == null) {
            return;
        }
        this.mName = parcel.readString();
        this.mUuid = parcel.readString();
        this.mModel = parcel.readString();
        this.mProductType = parcel.readInt();
        this.mConnectState = parcel.readInt();
        this.mReservedness = parcel.readString();
    }

    public int hashCode() {
        String str = this.mUuid;
        if (str != null) {
            return str.hashCode();
        }
        return -1;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Device)) {
            return false;
        }
        Device device = (Device) obj;
        String str = this.mUuid;
        if (str == null || device.mUuid == null) {
            return false;
        }
        return str.equals(device.getUuid());
    }

    public String toString() {
        return "Device{mName='" + this.mName + "', mUuid='" + this.mUuid + "', mModel='" + this.mModel + "', mProductType='" + this.mProductType + "', mConnectState='" + this.mConnectState + "', mReservedness='" + getReservedness() + "', mSoftwareVersion='" + getSoftwareVersion() + "', isSupportOta='" + isSupportOta() + "', mP2pCapability='" + getP2pCapability() + "', mMonitorCapability='" + getMonitorCapability() + "', mNotifyCapability='" + getNotifyCapability() + "', mSensorCapability='" + getSensorCapability() + "', mDeviceCategory='" + getDeviceCategory() + "', mDeviceType='" + getDeviceType() + "'}";
    }
}
