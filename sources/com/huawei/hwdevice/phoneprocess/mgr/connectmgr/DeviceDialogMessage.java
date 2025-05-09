package com.huawei.hwdevice.phoneprocess.mgr.connectmgr;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes5.dex */
public class DeviceDialogMessage implements Parcelable {
    public static final Parcelable.Creator<DeviceDialogMessage> CREATOR = new Parcelable.Creator<DeviceDialogMessage>() { // from class: com.huawei.hwdevice.phoneprocess.mgr.connectmgr.DeviceDialogMessage.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: bJB_, reason: merged with bridge method [inline-methods] */
        public DeviceDialogMessage createFromParcel(Parcel parcel) {
            return new DeviceDialogMessage(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public DeviceDialogMessage[] newArray(int i) {
            return new DeviceDialogMessage[i];
        }
    };
    private int mBluetoothType;
    private String mDeviceName;
    private boolean mIsStatusFlag;
    private int mMethod;
    private long mSelectId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public DeviceDialogMessage() {
        this.mBluetoothType = -1;
    }

    protected DeviceDialogMessage(Parcel parcel) {
        this.mBluetoothType = -1;
        this.mMethod = parcel.readInt();
        this.mIsStatusFlag = parcel.readByte() != 0;
        this.mSelectId = parcel.readLong();
        this.mDeviceName = parcel.readString();
        this.mBluetoothType = parcel.readInt();
    }

    public int getMethod() {
        return this.mMethod;
    }

    public void setMethod(int i) {
        this.mMethod = i;
    }

    public boolean isStatusFlag() {
        return this.mIsStatusFlag;
    }

    public void setIsStatusFlag(boolean z) {
        this.mIsStatusFlag = z;
    }

    public long getSelectId() {
        return this.mSelectId;
    }

    public void setSelectId(long j) {
        this.mSelectId = j;
    }

    public String getDeviceName() {
        return this.mDeviceName;
    }

    public void setDeviceName(String str) {
        this.mDeviceName = str;
    }

    public int getBluetoothType() {
        return this.mBluetoothType;
    }

    public void setBluetoothType(int i) {
        this.mBluetoothType = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mMethod);
        parcel.writeByte(this.mIsStatusFlag ? (byte) 1 : (byte) 0);
        parcel.writeLong(this.mSelectId);
        parcel.writeString(this.mDeviceName);
        parcel.writeInt(this.mBluetoothType);
    }
}
