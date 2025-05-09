package com.huawei.datatype;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import java.util.List;

/* loaded from: classes.dex */
public class DeviceStatusParam implements Parcelable {
    public static final Parcelable.Creator<DeviceStatusParam> CREATOR = new Parcelable.Creator<DeviceStatusParam>() { // from class: com.huawei.datatype.DeviceStatusParam.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DeviceStatusParam[] newArray(int i) {
            return new DeviceStatusParam[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DeviceStatusParam createFromParcel(Parcel parcel) {
            return DeviceStatusParam.getDeviceStatusParam(parcel);
        }
    };
    private static final int UNKNOWN_CODE = -1;
    private List<DeviceInfo> mDeviceInfoList;
    private int mErrorCode = -1;
    private boolean mIsDeviceConnectDirectly;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getErrorCode() {
        return this.mErrorCode;
    }

    public void setErrorCode(int i) {
        this.mErrorCode = i;
    }

    public boolean isDeviceConnectDirectly() {
        return this.mIsDeviceConnectDirectly;
    }

    public void setIsDeviceConnectDirectly(boolean z) {
        this.mIsDeviceConnectDirectly = z;
    }

    public List<DeviceInfo> getDeviceInfoList() {
        return this.mDeviceInfoList;
    }

    public void setDeviceInfoList(List<DeviceInfo> list) {
        this.mDeviceInfoList = list;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mErrorCode);
        if (Build.VERSION.SDK_INT >= 29) {
            parcel.writeBoolean(this.mIsDeviceConnectDirectly);
        } else {
            parcel.writeInt(this.mIsDeviceConnectDirectly ? 1 : 0);
        }
        parcel.writeTypedList(this.mDeviceInfoList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static DeviceStatusParam getDeviceStatusParam(Parcel parcel) {
        boolean z;
        DeviceStatusParam deviceStatusParam = new DeviceStatusParam();
        deviceStatusParam.setErrorCode(parcel.readInt());
        if (Build.VERSION.SDK_INT >= 29) {
            z = parcel.readBoolean();
        } else {
            z = parcel.readInt() != 0;
        }
        deviceStatusParam.setIsDeviceConnectDirectly(z);
        deviceStatusParam.setDeviceInfoList(parcel.createTypedArrayList(DeviceInfo.CREATOR));
        return deviceStatusParam;
    }
}
