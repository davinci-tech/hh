package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.DataType;

/* loaded from: classes8.dex */
public class DataTypeResult extends AbstractSafeParcelable implements Result {
    public static final Parcelable.Creator<DataTypeResult> CREATOR = new zze();
    private final Status zzir;
    private final DataType zzq;

    public DataTypeResult(Status status, DataType dataType) {
        this.zzir = status;
        this.zzq = dataType;
    }

    public static DataTypeResult zzc(Status status) {
        return new DataTypeResult(status, null);
    }

    @Override // com.google.android.gms.common.api.Result
    public Status getStatus() {
        return this.zzir;
    }

    public DataType getDataType() {
        return this.zzq;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DataTypeResult)) {
            return false;
        }
        DataTypeResult dataTypeResult = (DataTypeResult) obj;
        return this.zzir.equals(dataTypeResult.zzir) && Objects.equal(this.zzq, dataTypeResult.zzq);
    }

    public int hashCode() {
        return Objects.hashCode(this.zzir, this.zzq);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("status", this.zzir).add("dataType", this.zzq).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getStatus(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, getDataType(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
