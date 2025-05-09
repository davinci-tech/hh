package com.google.android.clockwork.companion.partnerapi;

import android.bluetooth.BluetoothDevice;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class SmartWatchInfo implements Parcelable {
    public static final Parcelable.Creator<SmartWatchInfo> CREATOR = new Parcelable.Creator<SmartWatchInfo>() { // from class: com.google.android.clockwork.companion.partnerapi.SmartWatchInfo.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: bR_, reason: merged with bridge method [inline-methods] */
        public SmartWatchInfo createFromParcel(Parcel parcel) {
            return new SmartWatchInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public SmartWatchInfo[] newArray(int i) {
            if (i > 65535 || i < 0) {
                return null;
            }
            return new SmartWatchInfo[i];
        }
    };
    private BluetoothDevice mBluetoothDevice;
    private int mResId;
    private int mRssi;
    private long mTimeStampsMs;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SmartWatchInfo() {
    }

    protected SmartWatchInfo(Parcel parcel) {
        BluetoothDevice bluetoothDevice = (BluetoothDevice) parcel.readParcelable(BluetoothDevice.class.getClassLoader());
        int readInt = parcel.readInt();
        int readInt2 = parcel.readInt();
        long readLong = parcel.readLong();
        this.mBluetoothDevice = bluetoothDevice;
        this.mRssi = readInt;
        this.mResId = readInt2;
        this.mTimeStampsMs = readLong;
    }

    public BluetoothDevice getBluetoothDevice() {
        return this.mBluetoothDevice;
    }

    public void setBluetoothDevice(BluetoothDevice bluetoothDevice) {
        this.mBluetoothDevice = bluetoothDevice;
    }

    public int getRssiValue() {
        return this.mRssi;
    }

    public void setRssiValue(int i) {
        this.mRssi = i;
    }

    public int getDeviceModelImageResId() {
        return this.mResId;
    }

    public void setDeviceModelImageResId(int i) {
        this.mResId = i;
    }

    public long getTimeStampMs() {
        return this.mTimeStampsMs;
    }

    public void setTimeStampMs(long j) {
        this.mTimeStampsMs = j;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(getBluetoothDevice(), i);
        parcel.writeInt(getRssiValue());
        parcel.writeInt(getDeviceModelImageResId());
        parcel.writeLong(getTimeStampMs());
    }
}
