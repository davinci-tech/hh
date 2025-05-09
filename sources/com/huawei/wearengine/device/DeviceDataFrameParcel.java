package com.huawei.wearengine.device;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes9.dex */
public class DeviceDataFrameParcel implements Parcelable {
    public static final Parcelable.Creator<DeviceDataFrameParcel> CREATOR = new Parcelable.Creator<DeviceDataFrameParcel>() { // from class: com.huawei.wearengine.device.DeviceDataFrameParcel.5
        @Override // android.os.Parcelable.Creator
        /* renamed from: fdf_, reason: merged with bridge method [inline-methods] */
        public DeviceDataFrameParcel createFromParcel(Parcel parcel) {
            DeviceDataFrameParcel deviceDataFrameParcel = new DeviceDataFrameParcel();
            if (parcel == null) {
                return deviceDataFrameParcel;
            }
            deviceDataFrameParcel.setServiceId(parcel.readString());
            deviceDataFrameParcel.setCharacteristicId(parcel.readString());
            deviceDataFrameParcel.setData(parcel.createByteArray());
            return deviceDataFrameParcel;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public DeviceDataFrameParcel[] newArray(int i) {
            return (i > 65535 || i < 0) ? new DeviceDataFrameParcel[0] : new DeviceDataFrameParcel[i];
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
        byte[] bArr = this.mData;
        return bArr != null ? (byte[]) bArr.clone() : new byte[0];
    }

    public void setData(byte[] bArr) {
        if (bArr != null) {
            this.mData = (byte[]) bArr.clone();
        } else {
            this.mData = null;
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeString(this.mServiceId);
        parcel.writeString(this.mCharacteristicId);
        parcel.writeByteArray(this.mData);
    }

    public void readFromParcel(Parcel parcel) {
        if (parcel == null) {
            return;
        }
        this.mServiceId = parcel.readString();
        this.mCharacteristicId = parcel.readString();
        this.mData = parcel.createByteArray();
    }
}
