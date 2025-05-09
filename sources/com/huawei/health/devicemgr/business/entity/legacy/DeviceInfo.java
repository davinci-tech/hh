package com.huawei.health.devicemgr.business.entity.legacy;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import defpackage.jdy;

/* loaded from: classes3.dex */
public class DeviceInfo implements Parcelable {
    public static final Parcelable.Creator<DeviceInfo> CREATOR = new Parcelable.Creator<DeviceInfo>() { // from class: com.huawei.health.devicemgr.business.entity.legacy.DeviceInfo.4
        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public DeviceInfo[] newArray(int i) {
            return new DeviceInfo[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: QK_, reason: merged with bridge method [inline-methods] */
        public DeviceInfo createFromParcel(Parcel parcel) {
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            int readInt = parcel.readInt();
            int readInt2 = parcel.readInt();
            int readInt3 = parcel.readInt();
            int readInt4 = parcel.readInt();
            int readInt5 = parcel.readInt();
            int readInt6 = parcel.readInt();
            String readString3 = parcel.readString();
            int readInt7 = parcel.readInt();
            DeviceInfo deviceInfo = new DeviceInfo();
            deviceInfo.setDeviceName(readString);
            deviceInfo.setDeviceIdentify(readString2);
            deviceInfo.setDeviceProtocol(readInt);
            deviceInfo.setProductType(readInt2);
            deviceInfo.setDeviceActiveState(readInt3);
            deviceInfo.setDeviceConnectState(readInt4);
            deviceInfo.setEncryptType(readInt5);
            deviceInfo.setDeviceBtType(readInt6);
            deviceInfo.setUuid(readString3);
            deviceInfo.setDeviceIdType(readInt7);
            return deviceInfo;
        }
    };
    private static final int DEFAULT_DEVICE_BT_TYPE = -1;
    private String mDeviceName = "";
    private String mDeviceIdentify = "";
    private int mDeviceProtocol = -1;
    private int mProductType = -1;
    private int mDeviceActiveState = 0;
    private int mDeviceConnectState = 0;
    private int mEncryptType = 0;

    @SerializedName("mDeviceBTType")
    private int mDeviceBtType = -1;

    @SerializedName("UUID")
    private String mUuid = "";

    @SerializedName("mDevIdType")
    private int mDeviceIdType = 0;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getUuid() {
        return this.mUuid;
    }

    public void setUuid(String str) {
        this.mUuid = str;
    }

    public void setDeviceName(String str) {
        this.mDeviceName = str;
    }

    public String getDeviceName() {
        return this.mDeviceName;
    }

    public void setDeviceIdentify(String str) {
        this.mDeviceIdentify = str;
    }

    public String getDeviceIdentify() {
        return this.mDeviceIdentify;
    }

    public void setDeviceProtocol(int i) {
        this.mDeviceProtocol = i;
    }

    public int getDeviceProtocol() {
        return this.mDeviceProtocol;
    }

    public void setProductType(int i) {
        this.mProductType = i;
    }

    public int getProductType() {
        return this.mProductType;
    }

    public void setDeviceActiveState(int i) {
        this.mDeviceActiveState = i;
    }

    public int getDeviceActiveState() {
        return this.mDeviceActiveState;
    }

    public void setDeviceConnectState(int i) {
        this.mDeviceConnectState = i;
    }

    public int getDeviceConnectState() {
        return this.mDeviceConnectState;
    }

    public int getEncryptType() {
        return this.mEncryptType;
    }

    public void setEncryptType(int i) {
        this.mEncryptType = i;
    }

    public int getDeviceBtType() {
        return this.mDeviceBtType;
    }

    public void setDeviceBtType(int i) {
        this.mDeviceBtType = i;
    }

    public int getDeviceIdType() {
        return ((Integer) jdy.d(Integer.valueOf(this.mDeviceIdType))).intValue();
    }

    public void setDeviceIdType(int i) {
        this.mDeviceIdType = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mDeviceName);
        parcel.writeString(this.mDeviceIdentify);
        parcel.writeInt(this.mDeviceProtocol);
        parcel.writeInt(this.mProductType);
        parcel.writeInt(this.mDeviceActiveState);
        parcel.writeInt(this.mDeviceConnectState);
        parcel.writeInt(this.mEncryptType);
        parcel.writeInt(this.mDeviceBtType);
        parcel.writeString(this.mUuid);
        parcel.writeInt(this.mDeviceIdType);
    }

    public String toString() {
        return "[mDeviceName = " + this.mDeviceName + ",mProductType = " + this.mProductType + ",mDeviceConnectState = " + this.mDeviceConnectState + "]";
    }

    public com.huawei.health.devicemgr.business.entity.DeviceInfo convertToCommonDevice() {
        com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo = new com.huawei.health.devicemgr.business.entity.DeviceInfo();
        deviceInfo.setDeviceName(getDeviceName());
        deviceInfo.setDeviceIdentify(getDeviceIdentify());
        deviceInfo.setDeviceProtocol(getDeviceProtocol());
        deviceInfo.setProductType(getProductType());
        deviceInfo.setDeviceActiveState(getDeviceActiveState());
        deviceInfo.setDeviceConnectState(getDeviceConnectState());
        deviceInfo.setEncryptType(getEncryptType());
        deviceInfo.setDeviceBluetoothType(getDeviceBtType());
        deviceInfo.setUuid(getUuid());
        deviceInfo.setDeviceIdType(getDeviceIdType());
        return deviceInfo;
    }
}
