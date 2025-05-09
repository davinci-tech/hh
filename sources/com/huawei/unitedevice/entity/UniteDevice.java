package com.huawei.unitedevice.entity;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.devicesdk.entity.ExternalDeviceCapability;

/* loaded from: classes7.dex */
public class UniteDevice implements Parcelable {
    public static final Parcelable.Creator<UniteDevice> CREATOR = new Parcelable.Creator<UniteDevice>() { // from class: com.huawei.unitedevice.entity.UniteDevice.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: ejC_, reason: merged with bridge method [inline-methods] */
        public UniteDevice createFromParcel(Parcel parcel) {
            if (parcel == null) {
                return null;
            }
            UniteDevice uniteDevice = new UniteDevice();
            uniteDevice.setIdentify(parcel.readString());
            uniteDevice.setDeviceInfo((DeviceInfo) parcel.readParcelable(DeviceInfo.class.getClassLoader()));
            uniteDevice.setCapability((ExternalDeviceCapability) parcel.readParcelable(ExternalDeviceCapability.class.getClassLoader()));
            return uniteDevice;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public UniteDevice[] newArray(int i) {
            if (i > 65535 || i < 0) {
                return null;
            }
            return new UniteDevice[i];
        }
    };
    private ExternalDeviceCapability capability;
    private DeviceInfo deviceInfo;
    private String identify;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getIdentify() {
        return this.identify;
    }

    public void setIdentify(String str) {
        this.identify = str;
    }

    public DeviceInfo getDeviceInfo() {
        return this.deviceInfo;
    }

    public void setDeviceInfo(DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public ExternalDeviceCapability getCapability() {
        return this.capability;
    }

    public void setCapability(ExternalDeviceCapability externalDeviceCapability) {
        this.capability = externalDeviceCapability;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.identify);
        parcel.writeParcelable(this.deviceInfo, i);
        parcel.writeParcelable(this.capability, i);
    }

    public UniteDevice build(String str, DeviceInfo deviceInfo, ExternalDeviceCapability externalDeviceCapability) {
        this.identify = str;
        this.deviceInfo = deviceInfo;
        this.capability = externalDeviceCapability;
        return this;
    }
}
