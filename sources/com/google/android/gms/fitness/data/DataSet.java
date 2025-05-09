package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes2.dex */
public final class DataSet extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<DataSet> CREATOR = new zzi();
    private final int versionCode;
    private boolean zzal;
    private final List<DataPoint> zzau;
    private final List<DataSource> zzav;
    private final DataSource zzr;

    DataSet(int i, DataSource dataSource, List<RawDataPoint> list, List<DataSource> list2, boolean z) {
        this.versionCode = i;
        this.zzr = dataSource;
        this.zzal = z;
        this.zzau = new ArrayList(list.size());
        this.zzav = i < 2 ? Collections.singletonList(dataSource) : list2;
        Iterator<RawDataPoint> it = list.iterator();
        while (it.hasNext()) {
            this.zzau.add(new DataPoint(this.zzav, it.next()));
        }
    }

    private DataSet(DataSource dataSource) {
        this.zzal = false;
        this.versionCode = 3;
        DataSource dataSource2 = (DataSource) Preconditions.checkNotNull(dataSource);
        this.zzr = dataSource2;
        this.zzau = new ArrayList();
        ArrayList arrayList = new ArrayList();
        this.zzav = arrayList;
        arrayList.add(dataSource2);
    }

    public DataSet(RawDataSet rawDataSet, List<DataSource> list) {
        this.zzal = false;
        this.versionCode = 3;
        this.zzr = list.get(rawDataSet.zzdw);
        this.zzav = list;
        this.zzal = rawDataSet.zzal;
        List<RawDataPoint> list2 = rawDataSet.zzdy;
        this.zzau = new ArrayList(list2.size());
        Iterator<RawDataPoint> it = list2.iterator();
        while (it.hasNext()) {
            this.zzau.add(new DataPoint(this.zzav, it.next()));
        }
    }

    public static DataSet create(DataSource dataSource) {
        Preconditions.checkNotNull(dataSource, "DataSource should be specified");
        return new DataSet(dataSource);
    }

    public final DataPoint createDataPoint() {
        return DataPoint.create(this.zzr);
    }

    public final void add(DataPoint dataPoint) {
        DataSource dataSource = dataPoint.getDataSource();
        Preconditions.checkArgument(dataSource.getStreamIdentifier().equals(this.zzr.getStreamIdentifier()), "Conflicting data sources found %s vs %s", dataSource, this.zzr);
        dataPoint.zzg();
        zzb(dataPoint);
        zza(dataPoint);
    }

    private final void zza(DataPoint dataPoint) {
        this.zzau.add(dataPoint);
        DataSource originalDataSource = dataPoint.getOriginalDataSource();
        if (originalDataSource == null || this.zzav.contains(originalDataSource)) {
            return;
        }
        this.zzav.add(originalDataSource);
    }

    public final void addAll(Iterable<DataPoint> iterable) {
        Iterator<DataPoint> it = iterable.iterator();
        while (it.hasNext()) {
            add(it.next());
        }
    }

    public final void zza(Iterable<DataPoint> iterable) {
        Iterator<DataPoint> it = iterable.iterator();
        while (it.hasNext()) {
            zza(it.next());
        }
    }

    public final DataSource getDataSource() {
        return this.zzr;
    }

    public final DataType getDataType() {
        return this.zzr.getDataType();
    }

    public final List<DataPoint> getDataPoints() {
        return Collections.unmodifiableList(this.zzau);
    }

    public final boolean isEmpty() {
        return this.zzau.isEmpty();
    }

    public final boolean zza() {
        return this.zzal;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DataSet)) {
            return false;
        }
        DataSet dataSet = (DataSet) obj;
        return Objects.equal(this.zzr, dataSet.zzr) && Objects.equal(this.zzau, dataSet.zzau) && this.zzal == dataSet.zzal;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzr);
    }

    public final String toString() {
        List<RawDataPoint> zzh = zzh();
        Locale locale = Locale.US;
        Object[] objArr = new Object[2];
        objArr[0] = this.zzr.toDebugString();
        Object obj = zzh;
        if (this.zzau.size() >= 10) {
            obj = String.format(Locale.US, "%d data points, first 5: %s", Integer.valueOf(this.zzau.size()), zzh.subList(0, 5));
        }
        objArr[1] = obj;
        return String.format(locale, "DataSet{%s %s}", objArr);
    }

    public static void zzb(DataPoint dataPoint) throws IllegalArgumentException {
        String zza = com.google.android.gms.internal.fitness.zzj.zza(dataPoint, zzf.zzam);
        if (zza == null) {
            return;
        }
        String valueOf = String.valueOf(dataPoint);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 20);
        sb.append("Invalid data point: ");
        sb.append(valueOf);
        Log.w("Fitness", sb.toString());
        throw new IllegalArgumentException(zza);
    }

    private final List<RawDataPoint> zzh() {
        return zza(this.zzav);
    }

    final List<RawDataPoint> zza(List<DataSource> list) {
        ArrayList arrayList = new ArrayList(this.zzau.size());
        Iterator<DataPoint> it = this.zzau.iterator();
        while (it.hasNext()) {
            arrayList.add(new RawDataPoint(it.next(), list));
        }
        return arrayList;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getDataSource(), i, false);
        SafeParcelWriter.writeList(parcel, 3, zzh(), false);
        SafeParcelWriter.writeTypedList(parcel, 4, this.zzav, false);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzal);
        SafeParcelWriter.writeInt(parcel, 1000, this.versionCode);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
