package com.google.android.gms.fitness.data;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public final class DataPoint extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<DataPoint> CREATOR = new zzh();
    private long zzao;
    private long zzap;
    private final Value[] zzaq;
    private DataSource zzar;
    private long zzas;
    private long zzat;
    private final DataSource zzr;

    public DataPoint(DataSource dataSource, long j, long j2, Value[] valueArr, DataSource dataSource2, long j3, long j4) {
        this.zzr = dataSource;
        this.zzar = dataSource2;
        this.zzao = j;
        this.zzap = j2;
        this.zzaq = valueArr;
        this.zzas = j3;
        this.zzat = j4;
    }

    DataPoint(List<DataSource> list, RawDataPoint rawDataPoint) {
        this(zza(list, rawDataPoint.zzo()), zza(list, rawDataPoint.zzp()), rawDataPoint);
    }

    private DataPoint(DataSource dataSource, DataSource dataSource2, RawDataPoint rawDataPoint) {
        this(dataSource, zza(Long.valueOf(rawDataPoint.getTimestampNanos()), 0L), zza(Long.valueOf(rawDataPoint.zzn()), 0L), rawDataPoint.zzc(), dataSource2, zza(Long.valueOf(rawDataPoint.zze()), 0L), zza(Long.valueOf(rawDataPoint.zzf()), 0L));
    }

    private static DataSource zza(List<DataSource> list, int i) {
        if (i < 0 || i >= list.size()) {
            return null;
        }
        return list.get(i);
    }

    private DataPoint(DataSource dataSource) {
        this.zzr = (DataSource) Preconditions.checkNotNull(dataSource, "Data source cannot be null");
        List<Field> fields = dataSource.getDataType().getFields();
        this.zzaq = new Value[fields.size()];
        Iterator<Field> it = fields.iterator();
        int i = 0;
        while (it.hasNext()) {
            this.zzaq[i] = new Value(it.next().getFormat());
            i++;
        }
    }

    public static DataPoint create(DataSource dataSource) {
        return new DataPoint(dataSource);
    }

    public static DataPoint extract(Intent intent) {
        if (intent == null) {
            return null;
        }
        return (DataPoint) SafeParcelableSerializer.deserializeFromIntentExtra(intent, "com.google.android.gms.fitness.EXTRA_DATA_POINT", CREATOR);
    }

    public final DataPoint setTimestamp(long j, TimeUnit timeUnit) {
        this.zzao = timeUnit.toNanos(j);
        return this;
    }

    public final DataPoint setTimeInterval(long j, long j2, TimeUnit timeUnit) {
        this.zzap = timeUnit.toNanos(j);
        this.zzao = timeUnit.toNanos(j2);
        return this;
    }

    public final Value zzb(int i) {
        DataType dataType = getDataType();
        Preconditions.checkArgument(i >= 0 && i < dataType.getFields().size(), "fieldIndex %s is out of range for %s", Integer.valueOf(i), dataType);
        return this.zzaq[i];
    }

    public final Value getValue(Field field) {
        return this.zzaq[getDataType().indexOf(field)];
    }

    public final Value[] zzc() {
        return this.zzaq;
    }

    public final DataPoint setFloatValues(float... fArr) {
        zzc(fArr.length);
        for (int i = 0; i < fArr.length; i++) {
            this.zzaq[i].setFloat(fArr[i]);
        }
        return this;
    }

    public final DataPoint setIntValues(int... iArr) {
        zzc(iArr.length);
        for (int i = 0; i < iArr.length; i++) {
            this.zzaq[i].setInt(iArr[i]);
        }
        return this;
    }

    private final void zzc(int i) {
        List<Field> fields = getDataType().getFields();
        int size = fields.size();
        Preconditions.checkArgument(i == size, "Attempting to insert %s values, but needed %s: %s", Integer.valueOf(i), Integer.valueOf(size), fields);
    }

    public final DataType getDataType() {
        return this.zzr.getDataType();
    }

    public final DataSource getDataSource() {
        return this.zzr;
    }

    public final DataSource getOriginalDataSource() {
        DataSource dataSource = this.zzar;
        return dataSource != null ? dataSource : this.zzr;
    }

    public final DataSource zzd() {
        return this.zzar;
    }

    public final long getTimestamp(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzao, TimeUnit.NANOSECONDS);
    }

    public final long zze() {
        return this.zzas;
    }

    public final long zzf() {
        return this.zzat;
    }

    public final long getStartTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzap, TimeUnit.NANOSECONDS);
    }

    public final long getEndTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzao, TimeUnit.NANOSECONDS);
    }

    public final void zzg() {
        Preconditions.checkArgument(getDataType().getName().equals(getDataSource().getDataType().getName()), "Conflicting data types found %s vs %s", getDataType(), getDataType());
        Preconditions.checkArgument(this.zzao > 0, "Data point does not have the timestamp set: %s", this);
        Preconditions.checkArgument(this.zzap <= this.zzao, "Data point with start time greater than end time found: %s", this);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DataPoint)) {
            return false;
        }
        DataPoint dataPoint = (DataPoint) obj;
        return Objects.equal(this.zzr, dataPoint.zzr) && this.zzao == dataPoint.zzao && this.zzap == dataPoint.zzap && Arrays.equals(this.zzaq, dataPoint.zzaq) && Objects.equal(getOriginalDataSource(), dataPoint.getOriginalDataSource());
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzr, Long.valueOf(this.zzao), Long.valueOf(this.zzap));
    }

    public final String toString() {
        Object[] objArr = new Object[7];
        objArr[0] = Arrays.toString(this.zzaq);
        objArr[1] = Long.valueOf(this.zzap);
        objArr[2] = Long.valueOf(this.zzao);
        objArr[3] = Long.valueOf(this.zzas);
        objArr[4] = Long.valueOf(this.zzat);
        objArr[5] = this.zzr.toDebugString();
        DataSource dataSource = this.zzar;
        objArr[6] = dataSource != null ? dataSource.toDebugString() : "N/A";
        return String.format("DataPoint{%s@[%s, %s,raw=%s,insert=%s](%s %s)}", objArr);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getDataSource(), i, false);
        SafeParcelWriter.writeLong(parcel, 3, this.zzao);
        SafeParcelWriter.writeLong(parcel, 4, this.zzap);
        SafeParcelWriter.writeTypedArray(parcel, 5, this.zzaq, i, false);
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzar, i, false);
        SafeParcelWriter.writeLong(parcel, 7, this.zzas);
        SafeParcelWriter.writeLong(parcel, 8, this.zzat);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    private static long zza(Long l, long j) {
        if (l != null) {
            return l.longValue();
        }
        return 0L;
    }
}
