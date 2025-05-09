package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/* loaded from: classes8.dex */
public class BleDevice extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<BleDevice> CREATOR = new zzd();
    private final String name;
    private final String zzaf;
    private final List<String> zzag;
    private final List<DataType> zzah;

    BleDevice(String str, String str2, List<String> list, List<DataType> list2) {
        this.zzaf = str;
        this.name = str2;
        this.zzag = Collections.unmodifiableList(list);
        this.zzah = Collections.unmodifiableList(list2);
    }

    public String getAddress() {
        return this.zzaf;
    }

    public String getName() {
        return this.name;
    }

    public List<String> getSupportedProfiles() {
        return this.zzag;
    }

    public List<DataType> getDataTypes() {
        return this.zzah;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BleDevice)) {
            return false;
        }
        BleDevice bleDevice = (BleDevice) obj;
        return this.name.equals(bleDevice.name) && this.zzaf.equals(bleDevice.zzaf) && new HashSet(this.zzag).equals(new HashSet(bleDevice.zzag)) && new HashSet(this.zzah).equals(new HashSet(bleDevice.zzah));
    }

    public int hashCode() {
        return Objects.hashCode(this.name, this.zzaf, this.zzag, this.zzah);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("name", this.name).add("address", this.zzaf).add("dataTypes", this.zzah).add("supportedProfiles", this.zzag).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getAddress(), false);
        SafeParcelWriter.writeString(parcel, 2, getName(), false);
        SafeParcelWriter.writeStringList(parcel, 3, getSupportedProfiles(), false);
        SafeParcelWriter.writeTypedList(parcel, 4, getDataTypes(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
