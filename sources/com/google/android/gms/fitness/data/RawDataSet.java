package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.List;

/* loaded from: classes8.dex */
public final class RawDataSet extends AbstractSafeParcelable {
    public static final Parcelable.Creator<RawDataSet> CREATOR = new zzaa();
    public final boolean zzal;
    public final int zzdw;
    public final List<RawDataPoint> zzdy;

    public RawDataSet(int i, List<RawDataPoint> list, boolean z) {
        this.zzdw = i;
        this.zzdy = list;
        this.zzal = z;
    }

    public RawDataSet(DataSet dataSet, List<DataSource> list) {
        this.zzdy = dataSet.zza(list);
        this.zzal = dataSet.zza();
        this.zzdw = com.google.android.gms.internal.fitness.zzf.zza(dataSet.getDataSource(), list);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RawDataSet)) {
            return false;
        }
        RawDataSet rawDataSet = (RawDataSet) obj;
        return this.zzdw == rawDataSet.zzdw && this.zzal == rawDataSet.zzal && Objects.equal(this.zzdy, rawDataSet.zzdy);
    }

    public final int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zzdw));
    }

    public final String toString() {
        return String.format("RawDataSet{%s@[%s]}", Integer.valueOf(this.zzdw), this.zzdy);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzdw);
        SafeParcelWriter.writeTypedList(parcel, 3, this.zzdy, false);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzal);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
