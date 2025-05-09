package com.google.android.gms.fitness.result;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.RawBucket;
import com.google.android.gms.fitness.data.RawDataSet;
import com.huawei.hms.network.embedded.w;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes8.dex */
public class DataReadResult extends AbstractSafeParcelable implements Result {
    public static final Parcelable.Creator<DataReadResult> CREATOR = new zzc();
    private final List<DataSet> zzaj;
    private final List<DataSource> zzav;
    private final Status zzir;
    private final List<Bucket> zzis;
    private int zzit;

    DataReadResult(List<RawDataSet> list, Status status, List<RawBucket> list2, int i, List<DataSource> list3) {
        this.zzir = status;
        this.zzit = i;
        this.zzav = list3;
        this.zzaj = new ArrayList(list.size());
        Iterator<RawDataSet> it = list.iterator();
        while (it.hasNext()) {
            this.zzaj.add(new DataSet(it.next(), list3));
        }
        this.zzis = new ArrayList(list2.size());
        Iterator<RawBucket> it2 = list2.iterator();
        while (it2.hasNext()) {
            this.zzis.add(new Bucket(it2.next(), list3));
        }
    }

    private DataReadResult(List<DataSet> list, List<Bucket> list2, Status status) {
        this.zzaj = list;
        this.zzir = status;
        this.zzis = list2;
        this.zzit = 1;
        this.zzav = new ArrayList();
    }

    public static DataReadResult zza(Status status, List<DataType> list, List<DataSource> list2) {
        ArrayList arrayList = new ArrayList();
        Iterator<DataSource> it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(DataSet.create(it.next()));
        }
        Iterator<DataType> it2 = list.iterator();
        while (it2.hasNext()) {
            arrayList.add(DataSet.create(new DataSource.Builder().setType(1).setDataType(it2.next()).setName(w.j).build()));
        }
        return new DataReadResult(arrayList, Collections.emptyList(), status);
    }

    public DataSet getDataSet(DataType dataType) {
        for (DataSet dataSet : this.zzaj) {
            if (dataType.equals(dataSet.getDataType())) {
                return dataSet;
            }
        }
        return DataSet.create(new DataSource.Builder().setDataType(dataType).setType(1).build());
    }

    public DataSet getDataSet(DataSource dataSource) {
        for (DataSet dataSet : this.zzaj) {
            if (dataSource.equals(dataSet.getDataSource())) {
                return dataSet;
            }
        }
        return DataSet.create(dataSource);
    }

    public List<DataSet> getDataSets() {
        return this.zzaj;
    }

    public List<Bucket> getBuckets() {
        return this.zzis;
    }

    public final int zzaa() {
        return this.zzit;
    }

    public final void zzb(DataReadResult dataReadResult) {
        Iterator<DataSet> it = dataReadResult.getDataSets().iterator();
        while (it.hasNext()) {
            zza(it.next(), this.zzaj);
        }
        for (Bucket bucket : dataReadResult.getBuckets()) {
            Iterator<Bucket> it2 = this.zzis.iterator();
            while (true) {
                if (it2.hasNext()) {
                    Bucket next = it2.next();
                    if (next.zza(bucket)) {
                        Iterator<DataSet> it3 = bucket.getDataSets().iterator();
                        while (it3.hasNext()) {
                            zza(it3.next(), next.getDataSets());
                        }
                    }
                } else {
                    this.zzis.add(bucket);
                    break;
                }
            }
        }
    }

    private static void zza(DataSet dataSet, List<DataSet> list) {
        for (DataSet dataSet2 : list) {
            if (dataSet2.getDataSource().equals(dataSet.getDataSource())) {
                dataSet2.zza(dataSet.getDataPoints());
                return;
            }
        }
        list.add(dataSet);
    }

    @Override // com.google.android.gms.common.api.Result
    public Status getStatus() {
        return this.zzir;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DataReadResult)) {
            return false;
        }
        DataReadResult dataReadResult = (DataReadResult) obj;
        return this.zzir.equals(dataReadResult.zzir) && Objects.equal(this.zzaj, dataReadResult.zzaj) && Objects.equal(this.zzis, dataReadResult.zzis);
    }

    public int hashCode() {
        return Objects.hashCode(this.zzir, this.zzaj, this.zzis);
    }

    public String toString() {
        Object obj;
        Object obj2;
        Objects.ToStringHelper add = Objects.toStringHelper(this).add("status", this.zzir);
        if (this.zzaj.size() > 5) {
            int size = this.zzaj.size();
            StringBuilder sb = new StringBuilder(21);
            sb.append(size);
            sb.append(" data sets");
            obj = sb.toString();
        } else {
            obj = this.zzaj;
        }
        Objects.ToStringHelper add2 = add.add("dataSets", obj);
        if (this.zzis.size() > 5) {
            int size2 = this.zzis.size();
            StringBuilder sb2 = new StringBuilder(19);
            sb2.append(size2);
            sb2.append(" buckets");
            obj2 = sb2.toString();
        } else {
            obj2 = this.zzis;
        }
        return add2.add("buckets", obj2).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        ArrayList arrayList = new ArrayList(this.zzaj.size());
        Iterator<DataSet> it = this.zzaj.iterator();
        while (it.hasNext()) {
            arrayList.add(new RawDataSet(it.next(), this.zzav));
        }
        SafeParcelWriter.writeList(parcel, 1, arrayList, false);
        SafeParcelWriter.writeParcelable(parcel, 2, getStatus(), i, false);
        ArrayList arrayList2 = new ArrayList(this.zzis.size());
        Iterator<Bucket> it2 = this.zzis.iterator();
        while (it2.hasNext()) {
            arrayList2.add(new RawBucket(it2.next(), this.zzav));
        }
        SafeParcelWriter.writeList(parcel, 3, arrayList2, false);
        SafeParcelWriter.writeInt(parcel, 5, this.zzit);
        SafeParcelWriter.writeTypedList(parcel, 6, this.zzav, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
