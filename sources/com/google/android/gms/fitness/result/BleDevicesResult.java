package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes8.dex */
public class BleDevicesResult extends AbstractSafeParcelable implements Result {
    public static final Parcelable.Creator<BleDevicesResult> CREATOR = new zza();
    private final List<BleDevice> zziq;
    private final Status zzir;

    public BleDevicesResult(List<BleDevice> list, Status status) {
        this.zziq = Collections.unmodifiableList(list);
        this.zzir = status;
    }

    public static BleDevicesResult zzb(Status status) {
        return new BleDevicesResult(Collections.emptyList(), status);
    }

    public List<BleDevice> getClaimedBleDevices() {
        return this.zziq;
    }

    public List<BleDevice> getClaimedBleDevices(DataType dataType) {
        ArrayList arrayList = new ArrayList();
        for (BleDevice bleDevice : this.zziq) {
            if (bleDevice.getDataTypes().contains(dataType)) {
                arrayList.add(bleDevice);
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    @Override // com.google.android.gms.common.api.Result
    public Status getStatus() {
        return this.zzir;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BleDevicesResult)) {
            return false;
        }
        BleDevicesResult bleDevicesResult = (BleDevicesResult) obj;
        return this.zzir.equals(bleDevicesResult.zzir) && Objects.equal(this.zziq, bleDevicesResult.zziq);
    }

    public int hashCode() {
        return Objects.hashCode(this.zzir, this.zziq);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("status", this.zzir).add("bleDevices", this.zziq).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, getClaimedBleDevices(), false);
        SafeParcelWriter.writeParcelable(parcel, 2, getStatus(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
