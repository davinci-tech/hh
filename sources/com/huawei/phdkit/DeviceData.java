package com.huawei.phdkit;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;

/* loaded from: classes5.dex */
public class DeviceData implements Parcelable {
    public static final Parcelable.Creator<DeviceData> CREATOR = new Parcelable.Creator<DeviceData>() { // from class: com.huawei.phdkit.DeviceData.5
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public DeviceData[] newArray(int i) {
            return (i > 65535 || i < 0) ? new DeviceData[0] : new DeviceData[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: ccB_, reason: merged with bridge method [inline-methods] */
        public DeviceData createFromParcel(Parcel parcel) {
            byte[] bArr;
            DeviceData deviceData = new DeviceData();
            if (parcel != null) {
                deviceData.setUdid(parcel.readString());
                deviceData.setStatus(parcel.readInt());
                deviceData.setErrorCode(parcel.readInt());
                int readInt = parcel.readInt();
                if (readInt <= 0 || readInt >= 2048) {
                    bArr = null;
                } else {
                    bArr = new byte[readInt];
                    parcel.readByteArray(bArr);
                }
                deviceData.setDataContents(bArr);
                deviceData.setLinkType(parcel.readInt());
            }
            return deviceData;
        }
    };
    private byte[] mDataContents;
    private int mErrorCode;
    private int mLinkType;
    private int mStatus;
    private long mTimestamp;
    private String mUdid;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void setUdid(String str) {
        this.mUdid = str;
    }

    public String getUdid() {
        return this.mUdid;
    }

    public void setStatus(int i) {
        this.mStatus = i;
    }

    public int getStatus() {
        return this.mStatus;
    }

    public void setErrorCode(int i) {
        this.mErrorCode = i;
    }

    public int getErrorCode() {
        return this.mErrorCode;
    }

    public void setTimestamp(int i) {
        this.mTimestamp = i;
    }

    public long getTimestamp() {
        return this.mTimestamp;
    }

    public void setLinkType(int i) {
        this.mLinkType = i;
    }

    public int getLinkType() {
        return this.mLinkType;
    }

    public void setDataContents(byte[] bArr) {
        if (bArr != null) {
            this.mDataContents = Arrays.copyOf(bArr, bArr.length);
        }
    }

    public byte[] getDataContents() {
        byte[] bArr = this.mDataContents;
        return bArr != null ? Arrays.copyOf(bArr, bArr.length) : new byte[0];
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeString(this.mUdid);
        parcel.writeInt(this.mStatus);
        parcel.writeInt(this.mErrorCode);
        byte[] bArr = this.mDataContents;
        if (bArr == null) {
            parcel.writeInt(0);
        } else {
            parcel.writeInt(bArr.length);
        }
        byte[] bArr2 = this.mDataContents;
        if (bArr2 != null && bArr2.length > 0 && bArr2.length < 2048) {
            parcel.writeByteArray(bArr2);
        }
        parcel.writeInt(this.mLinkType);
    }

    public void readFromParcel(Parcel parcel) {
        if (parcel == null) {
            return;
        }
        this.mUdid = parcel.readString();
        this.mStatus = parcel.readInt();
        this.mErrorCode = parcel.readInt();
        int readInt = parcel.readInt();
        if (readInt > 0 && readInt < 2048) {
            byte[] bArr = new byte[readInt];
            parcel.readByteArray(bArr);
            this.mDataContents = bArr;
        }
        this.mLinkType = parcel.readInt();
    }
}
