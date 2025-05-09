package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.Bucket;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Device;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* loaded from: classes8.dex */
public class DataReadRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<DataReadRequest> CREATOR = new zzn();
    public static final int NO_LIMIT = 0;
    private final int limit;
    private final List<DataType> zzah;
    private final int zzak;
    private final List<DataSource> zzgm;
    private final List<DataType> zzgr;
    private final List<DataSource> zzgs;
    private final long zzgt;
    private final DataSource zzgu;
    private final boolean zzgv;
    private final boolean zzgw;
    private final com.google.android.gms.internal.fitness.zzbh zzgx;
    private final List<Device> zzgy;
    private final List<Integer> zzgz;
    private final List<Long> zzha;
    private final List<Long> zzhb;
    private final long zzs;
    private final long zzt;

    DataReadRequest(List<DataType> list, List<DataSource> list2, long j, long j2, List<DataType> list3, List<DataSource> list4, int i, long j3, DataSource dataSource, int i2, boolean z, boolean z2, IBinder iBinder, List<Device> list5, List<Integer> list6, List<Long> list7, List<Long> list8) {
        this.zzah = list;
        this.zzgm = list2;
        this.zzs = j;
        this.zzt = j2;
        this.zzgr = list3;
        this.zzgs = list4;
        this.zzak = i;
        this.zzgt = j3;
        this.zzgu = dataSource;
        this.limit = i2;
        this.zzgv = z;
        this.zzgw = z2;
        this.zzgx = iBinder == null ? null : com.google.android.gms.internal.fitness.zzbi.zzc(iBinder);
        this.zzgy = list5 == null ? Collections.emptyList() : list5;
        this.zzgz = list6 == null ? Collections.emptyList() : list6;
        List<Long> emptyList = list7 == null ? Collections.emptyList() : list7;
        this.zzha = emptyList;
        List<Long> emptyList2 = list8 == null ? Collections.emptyList() : list8;
        this.zzhb = emptyList2;
        Preconditions.checkArgument(emptyList.size() == emptyList2.size(), "Unequal number of interval start and end times.");
    }

    public static class Builder {
        private DataSource zzgu;
        private long zzs;
        private long zzt;
        private List<DataType> zzah = new ArrayList();
        private List<DataSource> zzgm = new ArrayList();
        private List<DataType> zzgr = new ArrayList();
        private List<DataSource> zzgs = new ArrayList();
        private List<Long> zzha = new ArrayList();
        private List<Long> zzhb = new ArrayList();
        private int zzak = 0;
        private long zzgt = 0;
        private int limit = 0;
        private boolean zzgv = false;
        private boolean zzgw = false;
        private final List<Device> zzgy = new ArrayList();
        private final List<Integer> zzgz = new ArrayList();

        public Builder read(DataSource dataSource) {
            Preconditions.checkNotNull(dataSource, "Attempting to add a null data source");
            Preconditions.checkArgument(!this.zzgs.contains(dataSource), "Cannot add the same data source as aggregated and detailed");
            if (!this.zzgm.contains(dataSource)) {
                this.zzgm.add(dataSource);
            }
            return this;
        }

        public Builder read(DataType dataType) {
            Preconditions.checkNotNull(dataType, "Attempting to use a null data type");
            Preconditions.checkState(!this.zzgr.contains(dataType), "Cannot add the same data type as aggregated and detailed");
            if (!this.zzah.contains(dataType)) {
                this.zzah.add(dataType);
            }
            return this;
        }

        public Builder aggregate(DataSource dataSource, DataType dataType) {
            Preconditions.checkNotNull(dataSource, "Attempting to add a null data source");
            Preconditions.checkState(!this.zzgm.contains(dataSource), "Cannot add the same data source for aggregated and detailed");
            DataType dataType2 = dataSource.getDataType();
            List<DataType> aggregatesForInput = DataType.getAggregatesForInput(dataType2);
            Preconditions.checkArgument(!aggregatesForInput.isEmpty(), "Unsupported input data type specified for aggregation: %s", dataType2);
            Preconditions.checkArgument(aggregatesForInput.contains(dataType), "Invalid output aggregate data type specified: %s -> %s", dataType2, dataType);
            if (!this.zzgs.contains(dataSource)) {
                this.zzgs.add(dataSource);
            }
            return this;
        }

        public Builder aggregate(DataType dataType, DataType dataType2) {
            Preconditions.checkNotNull(dataType, "Attempting to use a null data type");
            Preconditions.checkState(!this.zzah.contains(dataType), "Cannot add the same data type as aggregated and detailed");
            List<DataType> aggregatesForInput = DataType.getAggregatesForInput(dataType);
            Preconditions.checkArgument(!aggregatesForInput.isEmpty(), "Unsupported input data type specified for aggregation: %s", dataType);
            Preconditions.checkArgument(aggregatesForInput.contains(dataType2), "Invalid output aggregate data type specified: %s -> %s", dataType, dataType2);
            if (!this.zzgr.contains(dataType)) {
                this.zzgr.add(dataType);
            }
            return this;
        }

        public Builder bucketByTime(int i, TimeUnit timeUnit) {
            int i2 = this.zzak;
            Preconditions.checkArgument(i2 == 0, "Bucketing strategy already set to %s", Integer.valueOf(i2));
            Preconditions.checkArgument(i > 0, "Must specify a valid minimum duration: %d", Integer.valueOf(i));
            this.zzak = 1;
            this.zzgt = timeUnit.toMillis(i);
            return this;
        }

        public Builder bucketByActivityType(int i, TimeUnit timeUnit) {
            int i2 = this.zzak;
            Preconditions.checkArgument(i2 == 0, "Bucketing strategy already set to %s", Integer.valueOf(i2));
            Preconditions.checkArgument(i > 0, "Must specify a valid minimum duration for an activity segment: %d", Integer.valueOf(i));
            this.zzak = 3;
            this.zzgt = timeUnit.toMillis(i);
            return this;
        }

        public Builder bucketByActivityType(int i, TimeUnit timeUnit, DataSource dataSource) {
            int i2 = this.zzak;
            Preconditions.checkArgument(i2 == 0, "Bucketing strategy already set to %s", Integer.valueOf(i2));
            Preconditions.checkArgument(i > 0, "Must specify a valid minimum duration for an activity segment: %d", Integer.valueOf(i));
            Preconditions.checkArgument(dataSource != null, "Invalid activity data source specified");
            Preconditions.checkArgument(dataSource.getDataType().equals(DataType.TYPE_ACTIVITY_SEGMENT), "Invalid activity data source specified: %s", dataSource);
            this.zzgu = dataSource;
            this.zzak = 3;
            this.zzgt = timeUnit.toMillis(i);
            return this;
        }

        public Builder addFilteredDataQualityStandard(int i) {
            Preconditions.checkArgument(this.zzgy.isEmpty(), "Cannot add data quality standard filter when filtering by device.");
            this.zzgz.add(Integer.valueOf(i));
            return this;
        }

        public Builder bucketByActivitySegment(int i, TimeUnit timeUnit) {
            int i2 = this.zzak;
            Preconditions.checkArgument(i2 == 0, "Bucketing strategy already set to %s", Integer.valueOf(i2));
            Preconditions.checkArgument(i > 0, "Must specify a valid minimum duration for an activity segment: %d", Integer.valueOf(i));
            this.zzak = 4;
            this.zzgt = timeUnit.toMillis(i);
            return this;
        }

        public Builder bucketByActivitySegment(int i, TimeUnit timeUnit, DataSource dataSource) {
            int i2 = this.zzak;
            Preconditions.checkArgument(i2 == 0, "Bucketing strategy already set to %s", Integer.valueOf(i2));
            Preconditions.checkArgument(i > 0, "Must specify a valid minimum duration for an activity segment: %d", Integer.valueOf(i));
            Preconditions.checkArgument(dataSource != null, "Invalid activity data source specified");
            Preconditions.checkArgument(dataSource.getDataType().equals(DataType.TYPE_ACTIVITY_SEGMENT), "Invalid activity data source specified: %s", dataSource);
            this.zzgu = dataSource;
            this.zzak = 4;
            this.zzgt = timeUnit.toMillis(i);
            return this;
        }

        public Builder bucketBySession(int i, TimeUnit timeUnit) {
            int i2 = this.zzak;
            Preconditions.checkArgument(i2 == 0, "Bucketing strategy already set to %s", Integer.valueOf(i2));
            Preconditions.checkArgument(i > 0, "Must specify a valid minimum duration for a session: %d", Integer.valueOf(i));
            this.zzak = 2;
            this.zzgt = timeUnit.toMillis(i);
            return this;
        }

        public Builder setTimeRange(long j, long j2, TimeUnit timeUnit) {
            this.zzs = timeUnit.toMillis(j);
            this.zzt = timeUnit.toMillis(j2);
            return this;
        }

        public Builder enableServerQueries() {
            this.zzgw = true;
            return this;
        }

        public Builder setLimit(int i) {
            Preconditions.checkArgument(i > 0, "Invalid limit %d is specified", Integer.valueOf(i));
            this.limit = i;
            return this;
        }

        public DataReadRequest build() {
            Preconditions.checkState((this.zzgm.isEmpty() && this.zzah.isEmpty() && this.zzgs.isEmpty() && this.zzgr.isEmpty()) ? false : true, "Must add at least one data source (aggregated or detailed)");
            if (this.zzak != 5) {
                long j = this.zzs;
                Preconditions.checkState(j > 0, "Invalid start time: %s", Long.valueOf(j));
                long j2 = this.zzt;
                Preconditions.checkState(j2 > 0 && j2 > this.zzs, "Invalid end time: %s", Long.valueOf(j2));
            }
            boolean z = this.zzgs.isEmpty() && this.zzgr.isEmpty();
            if (this.zzak == 0) {
                Preconditions.checkState(z, "Must specify a valid bucketing strategy while requesting aggregation");
            }
            if (!z) {
                Preconditions.checkState(this.zzak != 0, "Must specify a valid bucketing strategy while requesting aggregation");
            }
            return new DataReadRequest(this);
        }
    }

    private DataReadRequest(Builder builder) {
        this((List<DataType>) builder.zzah, (List<DataSource>) builder.zzgm, builder.zzs, builder.zzt, (List<DataType>) builder.zzgr, (List<DataSource>) builder.zzgs, builder.zzak, builder.zzgt, builder.zzgu, builder.limit, false, builder.zzgw, (com.google.android.gms.internal.fitness.zzbh) null, (List<Device>) builder.zzgy, (List<Integer>) builder.zzgz, (List<Long>) builder.zzha, (List<Long>) builder.zzhb);
    }

    public DataReadRequest(DataReadRequest dataReadRequest, com.google.android.gms.internal.fitness.zzbh zzbhVar) {
        this(dataReadRequest.zzah, dataReadRequest.zzgm, dataReadRequest.zzs, dataReadRequest.zzt, dataReadRequest.zzgr, dataReadRequest.zzgs, dataReadRequest.zzak, dataReadRequest.zzgt, dataReadRequest.zzgu, dataReadRequest.limit, dataReadRequest.zzgv, dataReadRequest.zzgw, zzbhVar, dataReadRequest.zzgy, dataReadRequest.zzgz, dataReadRequest.zzha, dataReadRequest.zzhb);
    }

    private DataReadRequest(List<DataType> list, List<DataSource> list2, long j, long j2, List<DataType> list3, List<DataSource> list4, int i, long j3, DataSource dataSource, int i2, boolean z, boolean z2, com.google.android.gms.internal.fitness.zzbh zzbhVar, List<Device> list5, List<Integer> list6, List<Long> list7, List<Long> list8) {
        this(list, list2, j, j2, list3, list4, i, j3, dataSource, i2, z, z2, zzbhVar == null ? null : zzbhVar.asBinder(), list5, list6, list7, list8);
    }

    public long getStartTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzs, TimeUnit.MILLISECONDS);
    }

    public long getEndTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzt, TimeUnit.MILLISECONDS);
    }

    public List<DataType> getDataTypes() {
        return this.zzah;
    }

    public List<DataSource> getDataSources() {
        return this.zzgm;
    }

    public List<DataType> getAggregatedDataTypes() {
        return this.zzgr;
    }

    public List<DataSource> getAggregatedDataSources() {
        return this.zzgs;
    }

    public int getBucketType() {
        return this.zzak;
    }

    public long getBucketDuration(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzgt, TimeUnit.MILLISECONDS);
    }

    public DataSource getActivityDataSource() {
        return this.zzgu;
    }

    public int getLimit() {
        return this.limit;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DataReadRequest)) {
            return false;
        }
        DataReadRequest dataReadRequest = (DataReadRequest) obj;
        return this.zzah.equals(dataReadRequest.zzah) && this.zzgm.equals(dataReadRequest.zzgm) && this.zzs == dataReadRequest.zzs && this.zzt == dataReadRequest.zzt && this.zzak == dataReadRequest.zzak && this.zzgs.equals(dataReadRequest.zzgs) && this.zzgr.equals(dataReadRequest.zzgr) && Objects.equal(this.zzgu, dataReadRequest.zzgu) && this.zzgt == dataReadRequest.zzgt && this.zzgw == dataReadRequest.zzgw && this.limit == dataReadRequest.limit && this.zzgv == dataReadRequest.zzgv && Objects.equal(this.zzgx, dataReadRequest.zzgx) && Objects.equal(this.zzgy, dataReadRequest.zzgy) && Objects.equal(this.zzgz, dataReadRequest.zzgz);
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zzak), Long.valueOf(this.zzs), Long.valueOf(this.zzt));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("DataReadRequest{");
        if (!this.zzah.isEmpty()) {
            Iterator<DataType> it = this.zzah.iterator();
            while (it.hasNext()) {
                sb.append(it.next().zzm());
                sb.append(" ");
            }
        }
        if (!this.zzgm.isEmpty()) {
            Iterator<DataSource> it2 = this.zzgm.iterator();
            while (it2.hasNext()) {
                sb.append(it2.next().toDebugString());
                sb.append(" ");
            }
        }
        if (this.zzak != 0) {
            sb.append("bucket by ");
            sb.append(Bucket.zza(this.zzak));
            if (this.zzgt > 0) {
                sb.append(" >");
                sb.append(this.zzgt);
                sb.append("ms");
            }
            sb.append(": ");
        }
        if (!this.zzgr.isEmpty()) {
            Iterator<DataType> it3 = this.zzgr.iterator();
            while (it3.hasNext()) {
                sb.append(it3.next().zzm());
                sb.append(" ");
            }
        }
        if (!this.zzgs.isEmpty()) {
            Iterator<DataSource> it4 = this.zzgs.iterator();
            while (it4.hasNext()) {
                sb.append(it4.next().toDebugString());
                sb.append(" ");
            }
        }
        sb.append(String.format(Locale.US, "(%tF %tT - %tF %tT)", Long.valueOf(this.zzs), Long.valueOf(this.zzs), Long.valueOf(this.zzt), Long.valueOf(this.zzt)));
        if (this.zzgu != null) {
            sb.append("activities: ");
            sb.append(this.zzgu.toDebugString());
        }
        if (!this.zzgz.isEmpty()) {
            sb.append("quality: ");
            Iterator<Integer> it5 = this.zzgz.iterator();
            while (it5.hasNext()) {
                sb.append(DataSource.zzd(it5.next().intValue()));
                sb.append(" ");
            }
        }
        if (this.zzgw) {
            sb.append(" +server");
        }
        sb.append("}");
        return sb.toString();
    }

    public List<Integer> getFilteredDataQualityStandards() {
        return this.zzgz;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, getDataTypes(), false);
        SafeParcelWriter.writeTypedList(parcel, 2, getDataSources(), false);
        SafeParcelWriter.writeLong(parcel, 3, this.zzs);
        SafeParcelWriter.writeLong(parcel, 4, this.zzt);
        SafeParcelWriter.writeTypedList(parcel, 5, getAggregatedDataTypes(), false);
        SafeParcelWriter.writeTypedList(parcel, 6, getAggregatedDataSources(), false);
        SafeParcelWriter.writeInt(parcel, 7, getBucketType());
        SafeParcelWriter.writeLong(parcel, 8, this.zzgt);
        SafeParcelWriter.writeParcelable(parcel, 9, getActivityDataSource(), i, false);
        SafeParcelWriter.writeInt(parcel, 10, getLimit());
        SafeParcelWriter.writeBoolean(parcel, 12, this.zzgv);
        SafeParcelWriter.writeBoolean(parcel, 13, this.zzgw);
        com.google.android.gms.internal.fitness.zzbh zzbhVar = this.zzgx;
        SafeParcelWriter.writeIBinder(parcel, 14, zzbhVar == null ? null : zzbhVar.asBinder(), false);
        SafeParcelWriter.writeTypedList(parcel, 16, this.zzgy, false);
        SafeParcelWriter.writeIntegerList(parcel, 17, getFilteredDataQualityStandards(), false);
        SafeParcelWriter.writeLongList(parcel, 18, this.zzha, false);
        SafeParcelWriter.writeLongList(parcel, 19, this.zzhb, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
