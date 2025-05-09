package com.huawei.profile.profile;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.profile.subscription.deviceinfo.DeviceProfileChange;

/* loaded from: classes9.dex */
public class ParcelableDeviceProfileChange implements DeviceProfileChange, Parcelable {
    public static final Parcelable.Creator<ParcelableDeviceProfileChange> CREATOR = new Parcelable.Creator<ParcelableDeviceProfileChange>() { // from class: com.huawei.profile.profile.ParcelableDeviceProfileChange.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParcelableDeviceProfileChange createFromParcel(Parcel parcel) {
            return new ParcelableDeviceProfileChange(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ParcelableDeviceProfileChange[] newArray(int i) {
            return new ParcelableDeviceProfileChange[i];
        }
    };
    String charId;
    String charType;
    String deviceId;
    String deviceType;
    String extraInfo;
    int flags;
    int opType;
    String serviceId;
    String serviceType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ParcelableDeviceProfileChange() {
    }

    protected ParcelableDeviceProfileChange(Parcel parcel) {
        this.deviceId = parcel.readString();
        this.deviceType = parcel.readString();
        this.serviceId = parcel.readString();
        this.serviceType = parcel.readString();
        this.charId = parcel.readString();
        this.charType = parcel.readString();
        this.flags = parcel.readInt();
        this.opType = parcel.readInt();
        this.extraInfo = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.deviceId);
        parcel.writeString(this.deviceType);
        parcel.writeString(this.serviceId);
        parcel.writeString(this.serviceType);
        parcel.writeString(this.charId);
        parcel.writeString(this.charType);
        parcel.writeInt(this.flags);
        parcel.writeInt(this.opType);
        parcel.writeString(this.extraInfo);
    }

    @Override // com.huawei.profile.subscription.deviceinfo.DeviceProfileChange
    public String getDeviceId() {
        return this.deviceId;
    }

    @Override // com.huawei.profile.subscription.deviceinfo.DeviceProfileChange
    public String getDeviceType() {
        return this.deviceType;
    }

    @Override // com.huawei.profile.subscription.deviceinfo.DeviceProfileChange
    public String getServiceId() {
        return this.serviceId;
    }

    @Override // com.huawei.profile.subscription.deviceinfo.DeviceProfileChange
    public String getServiceType() {
        return this.serviceType;
    }

    @Override // com.huawei.profile.subscription.deviceinfo.DeviceProfileChange
    public String getCharId() {
        return this.charId;
    }

    @Override // com.huawei.profile.subscription.deviceinfo.DeviceProfileChange
    public String getCharType() {
        return this.charType;
    }

    @Override // com.huawei.profile.subscription.deviceinfo.DeviceProfileChange
    public int getFlags() {
        return this.flags;
    }

    @Override // com.huawei.profile.subscription.deviceinfo.DeviceProfileChange
    public int getOpType() {
        return this.opType;
    }
}
