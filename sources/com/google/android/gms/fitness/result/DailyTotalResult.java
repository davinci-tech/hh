package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;

/* loaded from: classes8.dex */
public class DailyTotalResult extends AbstractSafeParcelable implements Result {
    public static final Parcelable.Creator<DailyTotalResult> CREATOR = new zzb();
    private final DataSet zzeb;
    private final Status zzir;

    DailyTotalResult(Status status, DataSet dataSet) {
        this.zzir = status;
        this.zzeb = dataSet;
    }

    private DailyTotalResult(DataSet dataSet, Status status) {
        this.zzir = status;
        this.zzeb = dataSet;
    }

    public static DailyTotalResult zza(Status status, DataType dataType) {
        return new DailyTotalResult(DataSet.create(new DataSource.Builder().setDataType(dataType).setType(1).build()), status);
    }

    public DataSet getTotal() {
        return this.zzeb;
    }

    @Override // com.google.android.gms.common.api.Result
    public Status getStatus() {
        return this.zzir;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DailyTotalResult)) {
            return false;
        }
        DailyTotalResult dailyTotalResult = (DailyTotalResult) obj;
        return this.zzir.equals(dailyTotalResult.zzir) && Objects.equal(this.zzeb, dailyTotalResult.zzeb);
    }

    public int hashCode() {
        return Objects.hashCode(this.zzir, this.zzeb);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("status", this.zzir).add("dataPoint", this.zzeb).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getStatus(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, getTotal(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
