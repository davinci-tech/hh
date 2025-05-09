package com.huawei.watchface.mvp.model.datatype;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes7.dex */
public class DeviceCapability implements Parcelable {
    public static final Parcelable.Creator<DeviceCapability> CREATOR = new Parcelable.Creator<DeviceCapability>() { // from class: com.huawei.watchface.mvp.model.datatype.DeviceCapability.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DeviceCapability createFromParcel(Parcel parcel) {
            boolean z = parcel.readByte() == 1;
            DeviceCapability deviceCapability = new DeviceCapability();
            deviceCapability.setSupportWatchFaceAppId(z);
            return deviceCapability;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DeviceCapability[] newArray(int i) {
            return new DeviceCapability[i];
        }
    };

    @SerializedName("isSupportWatchFaceAppId")
    private boolean isSupportWatchFaceAppId = false;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.isSupportWatchFaceAppId ? (byte) 1 : (byte) 0);
    }

    public boolean isSupportWatchFaceAppId() {
        return this.isSupportWatchFaceAppId;
    }

    public void setSupportWatchFaceAppId(boolean z) {
        this.isSupportWatchFaceAppId = z;
    }
}
