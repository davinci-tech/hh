package com.huawei.devicesdk.entity;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes3.dex */
public class DeviceDataFrameParcel implements Parcelable {
    public static final Parcelable.Creator<DeviceDataFrameParcel> CREATOR = new Parcelable.Creator<DeviceDataFrameParcel>() { // from class: com.huawei.devicesdk.entity.DeviceDataFrameParcel.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: rT_, reason: merged with bridge method [inline-methods] */
        public DeviceDataFrameParcel createFromParcel(Parcel parcel) {
            DeviceDataFrameParcel deviceDataFrameParcel = new DeviceDataFrameParcel();
            deviceDataFrameParcel.setServiceId(parcel.readString());
            deviceDataFrameParcel.setCharacteristicId(parcel.readString());
            deviceDataFrameParcel.setData(parcel.createByteArray());
            return deviceDataFrameParcel;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public DeviceDataFrameParcel[] newArray(int i) {
            if (i > 65535 || i < 0) {
                return null;
            }
            return new DeviceDataFrameParcel[i];
        }
    };
    private String mCharacteristicId;
    private byte[] mData;
    private String mServiceId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getServiceId() {
        return this.mServiceId;
    }

    public void setServiceId(String str) {
        this.mServiceId = str;
    }

    public String getCharacteristicId() {
        return this.mCharacteristicId;
    }

    public void setCharacteristicId(String str) {
        this.mCharacteristicId = str;
    }

    public byte[] getData() {
        return this.mData;
    }

    public void setData(byte[] bArr) {
        this.mData = bArr;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mServiceId);
        parcel.writeString(this.mCharacteristicId);
        parcel.writeByteArray(this.mData);
    }

    public void readFromParcel(Parcel parcel) {
        this.mServiceId = parcel.readString();
        this.mCharacteristicId = parcel.readString();
        this.mData = parcel.createByteArray();
    }
}
