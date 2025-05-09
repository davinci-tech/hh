package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes8.dex */
public class DataSourcesResult extends AbstractSafeParcelable implements Result {
    public static final Parcelable.Creator<DataSourcesResult> CREATOR = new zzd();
    private final List<DataSource> zzgm;
    private final Status zzir;

    public DataSourcesResult(List<DataSource> list, Status status) {
        this.zzgm = Collections.unmodifiableList(list);
        this.zzir = status;
    }

    public List<DataSource> getDataSources() {
        return this.zzgm;
    }

    public List<DataSource> getDataSources(DataType dataType) {
        ArrayList arrayList = new ArrayList();
        for (DataSource dataSource : this.zzgm) {
            if (dataSource.getDataType().equals(dataType)) {
                arrayList.add(dataSource);
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
        if (!(obj instanceof DataSourcesResult)) {
            return false;
        }
        DataSourcesResult dataSourcesResult = (DataSourcesResult) obj;
        return this.zzir.equals(dataSourcesResult.zzir) && Objects.equal(this.zzgm, dataSourcesResult.zzgm);
    }

    public int hashCode() {
        return Objects.hashCode(this.zzir, this.zzgm);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("status", this.zzir).add("dataSources", this.zzgm).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, getDataSources(), false);
        SafeParcelWriter.writeParcelable(parcel, 2, getStatus(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
