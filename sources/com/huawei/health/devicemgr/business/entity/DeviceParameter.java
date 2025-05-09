package com.huawei.health.devicemgr.business.entity;

import android.os.Parcel;
import android.os.Parcelable;
import defpackage.bmb;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class DeviceParameter implements Parcelable {
    public static final Parcelable.Creator<DeviceParameter> CREATOR = new Parcelable.Creator<DeviceParameter>() { // from class: com.huawei.health.devicemgr.business.entity.DeviceParameter.4
        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public DeviceParameter[] newArray(int i) {
            return new DeviceParameter[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: QH_, reason: merged with bridge method [inline-methods] */
        public DeviceParameter createFromParcel(Parcel parcel) {
            DeviceParameter deviceParameter = new DeviceParameter();
            deviceParameter.setBluetoothType(parcel.readInt());
            deviceParameter.setProductType(parcel.readInt());
            deviceParameter.setDeviceNameInfo(parcel.readString());
            deviceParameter.setMac(parcel.readString());
            deviceParameter.setFilePath(parcel.readString());
            deviceParameter.setVersion(parcel.readString());
            deviceParameter.setIsSupportHeartRate(parcel.readByte() != 0);
            deviceParameter.setNameFilter(parcel.readArrayList(DeviceParameter.class.getClassLoader()));
            deviceParameter.setProductPin(parcel.readString());
            deviceParameter.setIsBand(parcel.readByte() != 0);
            return deviceParameter;
        }
    };
    private int mBluetoothType;
    private String mDeviceName;
    private String mFilePath;
    private boolean mIsBand;
    private boolean mIsSupportHeartRate;
    private String mMac;
    private ArrayList<String> mNameFilter = new ArrayList<>();
    private String mProductPin = "";
    private int mProductType;
    private String mVersion;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getBluetoothType() {
        return ((Integer) bmb.d(Integer.valueOf(this.mBluetoothType))).intValue();
    }

    public void setBluetoothType(int i) {
        this.mBluetoothType = ((Integer) bmb.d(Integer.valueOf(i))).intValue();
    }

    public int getProductType() {
        return ((Integer) bmb.d(Integer.valueOf(this.mProductType))).intValue();
    }

    public void setProductType(int i) {
        this.mProductType = ((Integer) bmb.d(Integer.valueOf(i))).intValue();
    }

    public String getDeviceNameInfo() {
        return (String) bmb.d(this.mDeviceName);
    }

    public void setDeviceNameInfo(String str) {
        this.mDeviceName = (String) bmb.d(str);
    }

    public String getMac() {
        return (String) bmb.d(this.mMac);
    }

    public void setMac(String str) {
        this.mMac = (String) bmb.d(str);
    }

    public String getFilePath() {
        return (String) bmb.d(this.mFilePath);
    }

    public void setFilePath(String str) {
        this.mFilePath = (String) bmb.d(str);
    }

    public String getVersion() {
        return (String) bmb.d(this.mVersion);
    }

    public void setVersion(String str) {
        this.mVersion = (String) bmb.d(str);
    }

    public ArrayList<String> getNameFilter() {
        return this.mNameFilter;
    }

    public void setNameFilter(ArrayList<String> arrayList) {
        this.mNameFilter = arrayList;
    }

    public String getProductPin() {
        return this.mProductPin;
    }

    public void setProductPin(String str) {
        this.mProductPin = str;
    }

    public boolean isSupportHeartRate() {
        return this.mIsSupportHeartRate;
    }

    public void setIsSupportHeartRate(boolean z) {
        this.mIsSupportHeartRate = z;
    }

    public boolean isBand() {
        return this.mIsBand;
    }

    public void setIsBand(boolean z) {
        this.mIsBand = z;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mBluetoothType);
        parcel.writeInt(this.mProductType);
        parcel.writeString(this.mDeviceName);
        parcel.writeString(this.mMac);
        parcel.writeString(this.mFilePath);
        parcel.writeString(this.mVersion);
        parcel.writeByte(this.mIsSupportHeartRate ? (byte) 1 : (byte) 0);
        parcel.writeList(this.mNameFilter);
        parcel.writeString(this.mProductPin);
        parcel.writeByte(this.mIsBand ? (byte) 1 : (byte) 0);
    }
}
