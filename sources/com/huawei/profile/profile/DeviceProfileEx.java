package com.huawei.profile.profile;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes6.dex */
public class DeviceProfileEx extends DeviceProfile {
    public static final Parcelable.Creator<DeviceProfileEx> CREATOR = new Parcelable.Creator<DeviceProfileEx>() { // from class: com.huawei.profile.profile.DeviceProfileEx.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DeviceProfileEx createFromParcel(Parcel parcel) {
            return new DeviceProfileEx(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public DeviceProfileEx[] newArray(int i) {
            return new DeviceProfileEx[i];
        }
    };
    private List<ServiceProfileEx> services;

    public DeviceProfileEx(Parcel parcel) {
        super(parcel);
        ArrayList arrayList = new ArrayList();
        this.services = arrayList;
        if (parcel == null) {
            return;
        }
        parcel.readTypedList(arrayList, ServiceProfileEx.CREATOR);
    }

    public DeviceProfileEx() {
        this.services = new ArrayList();
    }

    public void setServices(List<ServiceProfileEx> list) {
        this.services = list;
    }

    public List<ServiceProfileEx> getServices() {
        return this.services;
    }

    @Override // com.huawei.profile.profile.DeviceProfile, com.huawei.profile.kv.ProfileValue
    public String toString() {
        return super.toString() + ", services: " + this.services + ']';
    }

    @Override // com.huawei.profile.profile.DeviceProfile, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        List<ServiceProfileEx> list = this.services;
        if (list == null) {
            list = Collections.emptyList();
        }
        parcel.writeTypedList(list);
    }
}
