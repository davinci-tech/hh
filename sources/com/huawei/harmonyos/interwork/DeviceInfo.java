package com.huawei.harmonyos.interwork;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class DeviceInfo implements Parcelable {
    public static final Parcelable.Creator<DeviceInfo> CREATOR = new Parcelable.Creator<DeviceInfo>() { // from class: com.huawei.harmonyos.interwork.DeviceInfo.5
        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ DeviceInfo[] newArray(int i) {
            return new DeviceInfo[i];
        }

        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ DeviceInfo createFromParcel(Parcel parcel) {
            return new DeviceInfo(parcel);
        }
    };
    public static final int FLAG_GET_ALL_DEVICE = 0;
    public static final int FLAG_GET_OFFLINE_DEVICE = 2;
    public static final int FLAG_GET_ONLINE_DEVICE = 1;
    private static final String TAG = "DefKitLib_DeviceInfo";
    private String mDeviceId;
    private String mDeviceName;
    private DeviceState mDeviceState;
    private DeviceType mDeviceType;

    public enum DeviceState {
        UNKNOWN,
        ONLINE,
        OFFLINE
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public DeviceInfo(String str, String str2) {
        this.mDeviceId = str;
        this.mDeviceName = str2;
        this.mDeviceType = DeviceType.PHONE;
        this.mDeviceState = DeviceState.OFFLINE;
    }

    private DeviceInfo(Parcel parcel) {
        if (parcel == null) {
            return;
        }
        parcel.enforceInterface(TAG);
        this.mDeviceId = parcel.readString();
        this.mDeviceName = parcel.readString();
        int readInt = parcel.readInt();
        int i = 0;
        this.mDeviceType = DeviceType.values()[(readInt < 0 || readInt >= DeviceType.values().length) ? 0 : readInt];
        int readInt2 = parcel.readInt();
        if (readInt2 >= 0 && readInt2 < DeviceState.values().length) {
            i = readInt2;
        }
        this.mDeviceState = DeviceState.values()[i];
    }

    public void setDeviceInfo(String str, String str2) {
        this.mDeviceId = str;
        this.mDeviceName = str2;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.mDeviceType = deviceType;
    }

    public void setDeviceState(DeviceState deviceState) {
        Log.i(TAG, "device state change: " + this.mDeviceState + " -> " + deviceState);
        this.mDeviceState = deviceState;
    }

    public String getDeviceId() {
        return this.mDeviceId;
    }

    public String getDeviceName() {
        return this.mDeviceName;
    }

    public DeviceType getDeviceType() {
        return this.mDeviceType;
    }

    public DeviceState getDeviceState() {
        return this.mDeviceState;
    }

    public boolean isDeviceOnline() {
        return this.mDeviceState == DeviceState.ONLINE;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeInterfaceToken(TAG);
        parcel.writeString(this.mDeviceId);
        parcel.writeString(this.mDeviceName);
        parcel.writeInt(this.mDeviceType.ordinal());
        parcel.writeInt(this.mDeviceState.ordinal());
    }

    public enum DeviceType {
        UNKNOWN_TYPE(0),
        AUDIO(10),
        PHONE(14),
        PAD(17),
        WATCH(109),
        CAR(131),
        TELEVISION(156);

        private static final Map<Integer, DeviceType> DEVICE_TYPE_MAP = new HashMap();
        private final int mVal;

        static {
            for (DeviceType deviceType : values()) {
                DEVICE_TYPE_MAP.put(Integer.valueOf(deviceType.value()), deviceType);
            }
        }

        DeviceType(int i) {
            this.mVal = i;
        }

        public final int value() {
            return this.mVal;
        }

        public static DeviceType valueOf(int i) {
            return DEVICE_TYPE_MAP.getOrDefault(Integer.valueOf(i), UNKNOWN_TYPE);
        }
    }
}
