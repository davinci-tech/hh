package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.fitness.data.DataType;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes8.dex */
public class DataSourcesRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<DataSourcesRequest> CREATOR = new zzp();
    private final List<DataType> zzah;
    private final List<Integer> zzhc;
    private final boolean zzhd;
    private final com.google.android.gms.internal.fitness.zzbk zzhe;

    DataSourcesRequest(List<DataType> list, List<Integer> list2, boolean z, IBinder iBinder) {
        this.zzah = list;
        this.zzhc = list2;
        this.zzhd = z;
        this.zzhe = com.google.android.gms.internal.fitness.zzbl.zzd(iBinder);
    }

    public static class Builder {
        private DataType[] zzhf = new DataType[0];
        private int[] zzhg = {0, 1};
        private boolean zzhd = false;

        public Builder setDataTypes(DataType... dataTypeArr) {
            this.zzhf = dataTypeArr;
            return this;
        }

        public Builder setDataSourceTypes(int... iArr) {
            this.zzhg = iArr;
            return this;
        }

        public DataSourcesRequest build() {
            Preconditions.checkState(this.zzhf.length > 0, "Must add at least one data type");
            Preconditions.checkState(this.zzhg.length > 0, "Must add at least one data source type");
            return new DataSourcesRequest(this);
        }
    }

    private DataSourcesRequest(Builder builder) {
        this((List<DataType>) ArrayUtils.toArrayList(builder.zzhf), (List<Integer>) Arrays.asList(ArrayUtils.toWrapperArray(builder.zzhg)), false, (com.google.android.gms.internal.fitness.zzbk) null);
    }

    public DataSourcesRequest(DataSourcesRequest dataSourcesRequest, com.google.android.gms.internal.fitness.zzbk zzbkVar) {
        this(dataSourcesRequest.zzah, dataSourcesRequest.zzhc, dataSourcesRequest.zzhd, zzbkVar);
    }

    private DataSourcesRequest(List<DataType> list, List<Integer> list2, boolean z, com.google.android.gms.internal.fitness.zzbk zzbkVar) {
        this.zzah = list;
        this.zzhc = list2;
        this.zzhd = z;
        this.zzhe = zzbkVar;
    }

    public List<DataType> getDataTypes() {
        return this.zzah;
    }

    public String toString() {
        Objects.ToStringHelper add = Objects.toStringHelper(this).add("dataTypes", this.zzah).add("sourceTypes", this.zzhc);
        if (this.zzhd) {
            add.add("includeDbOnlySources", "true");
        }
        return add.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, getDataTypes(), false);
        SafeParcelWriter.writeIntegerList(parcel, 2, this.zzhc, false);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzhd);
        com.google.android.gms.internal.fitness.zzbk zzbkVar = this.zzhe;
        SafeParcelWriter.writeIBinder(parcel, 4, zzbkVar == null ? null : zzbkVar.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
