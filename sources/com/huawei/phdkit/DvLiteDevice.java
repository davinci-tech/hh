package com.huawei.phdkit;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* loaded from: classes5.dex */
public class DvLiteDevice implements Parcelable {
    public static final Parcelable.Creator<DvLiteDevice> CREATOR = new Parcelable.Creator<DvLiteDevice>() { // from class: com.huawei.phdkit.DvLiteDevice.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public DvLiteDevice[] newArray(int i) {
            return (i > 65535 || i < 0) ? new DvLiteDevice[0] : new DvLiteDevice[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: ccO_, reason: merged with bridge method [inline-methods] */
        public DvLiteDevice createFromParcel(Parcel parcel) {
            byte[] bArr;
            DvLiteDevice dvLiteDevice = new DvLiteDevice();
            if (parcel == null) {
                return dvLiteDevice;
            }
            dvLiteDevice.setDeviceName(parcel.readString());
            dvLiteDevice.setDeviceStatus(parcel.readInt());
            int readInt = parcel.readInt();
            if (readInt <= 0 || readInt >= 2048) {
                bArr = null;
            } else {
                bArr = new byte[readInt];
                parcel.readByteArray(bArr);
            }
            dvLiteDevice.setAbilityContents(bArr);
            dvLiteDevice.setUdid(parcel.readString());
            return dvLiteDevice;
        }
    };
    private byte[] mAbilityContents;
    private String mDeviceName;
    private int mDeviceStatus;
    private String mUdid;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void setDeviceName(String str) {
        this.mDeviceName = str;
    }

    public String getDeviceName() {
        return this.mDeviceName;
    }

    public void setDeviceStatus(int i) {
        this.mDeviceStatus = i;
    }

    public int getDeviceStatus() {
        return this.mDeviceStatus;
    }

    public void setAbilityContents(byte[] bArr) {
        if (bArr != null) {
            this.mAbilityContents = Arrays.copyOf(bArr, bArr.length);
        }
    }

    public byte[] getAbilityContents() {
        byte[] bArr = this.mAbilityContents;
        return bArr != null ? Arrays.copyOf(bArr, bArr.length) : new byte[0];
    }

    public void setUdid(String str) {
        this.mUdid = str;
    }

    public String getUdid() {
        return this.mUdid;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeString(this.mDeviceName);
        parcel.writeInt(this.mDeviceStatus);
        byte[] bArr = this.mAbilityContents;
        if (bArr == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(bArr.length);
        }
        byte[] bArr2 = this.mAbilityContents;
        if (bArr2 != null && bArr2.length > 0 && bArr2.length < 2048) {
            parcel.writeByteArray(bArr2);
        }
        parcel.writeString(this.mUdid);
    }

    public void readFromParcel(Parcel parcel) {
        if (parcel == null) {
            return;
        }
        this.mDeviceName = parcel.readString();
        this.mDeviceStatus = parcel.readInt();
        int readInt = parcel.readInt();
        if (readInt > 0 && readInt < 2048) {
            byte[] bArr = new byte[readInt];
            parcel.readByteArray(bArr);
            this.mAbilityContents = bArr;
        }
        this.mUdid = parcel.readString();
    }
}
