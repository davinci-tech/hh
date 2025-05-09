package com.huawei.profile.profile;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/* loaded from: classes6.dex */
public class ServiceProfileEx extends ServiceProfile {
    public static final Parcelable.Creator<ServiceProfileEx> CREATOR = new Parcelable.Creator<ServiceProfileEx>() { // from class: com.huawei.profile.profile.ServiceProfileEx.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ServiceProfileEx createFromParcel(Parcel parcel) {
            return new ServiceProfileEx(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ServiceProfileEx[] newArray(int i) {
            return new ServiceProfileEx[i];
        }
    };
    private ServiceCharacteristicProfile characters;

    public ServiceProfileEx(Parcel parcel) {
        super(parcel);
        if (parcel == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        parcel.readTypedList(arrayList, ServiceCharacteristicProfile.CREATOR);
        this.characters = arrayList.isEmpty() ? null : (ServiceCharacteristicProfile) arrayList.get(0);
    }

    public ServiceProfileEx() {
    }

    public ServiceCharacteristicProfile getCharacters() {
        return this.characters;
    }

    public void setCharacters(ServiceCharacteristicProfile serviceCharacteristicProfile) {
        this.characters = serviceCharacteristicProfile;
    }

    @Override // com.huawei.profile.profile.ServiceProfile, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        ServiceCharacteristicProfile serviceCharacteristicProfile = this.characters;
        parcel.writeTypedList(serviceCharacteristicProfile == null ? Collections.emptyList() : Arrays.asList(serviceCharacteristicProfile));
    }

    @Override // com.huawei.profile.profile.ServiceProfile, com.huawei.profile.kv.ProfileValue
    public String toString() {
        return super.toString() + ", characters: " + this.characters;
    }
}
