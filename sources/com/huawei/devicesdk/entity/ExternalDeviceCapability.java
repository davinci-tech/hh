package com.huawei.devicesdk.entity;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;

/* loaded from: classes3.dex */
public class ExternalDeviceCapability implements Parcelable {
    public static final Parcelable.Creator<ExternalDeviceCapability> CREATOR = new Parcelable.Creator<ExternalDeviceCapability>() { // from class: com.huawei.devicesdk.entity.ExternalDeviceCapability.5
        @Override // android.os.Parcelable.Creator
        /* renamed from: rV_, reason: merged with bridge method [inline-methods] */
        public ExternalDeviceCapability createFromParcel(Parcel parcel) {
            if (parcel == null) {
                return null;
            }
            ExternalDeviceCapability externalDeviceCapability = new ExternalDeviceCapability();
            externalDeviceCapability.setCapacity(parcel.readString());
            externalDeviceCapability.setCompatibleCapacity((DeviceCapability) parcel.readParcelable(DeviceCapability.class.getClassLoader()));
            return externalDeviceCapability;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public ExternalDeviceCapability[] newArray(int i) {
            if (i > 65535 || i < 0) {
                return null;
            }
            return new ExternalDeviceCapability[i];
        }
    };
    private String capacity;
    private DeviceCapability compatibleCapacity = new DeviceCapability();

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getCapacity() {
        return this.capacity;
    }

    public void setCapacity(String str) {
        this.capacity = str;
    }

    public DeviceCapability getCompatibleCapacity() {
        return this.compatibleCapacity;
    }

    public void setCompatibleCapacity(DeviceCapability deviceCapability) {
        this.compatibleCapacity = deviceCapability;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.capacity);
        parcel.writeParcelable(this.compatibleCapacity, i);
    }
}
