package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.internal.fitness.zzcq;
import com.google.android.gms.internal.fitness.zzcr;
import java.util.concurrent.TimeUnit;

/* loaded from: classes8.dex */
public class DataUpdateRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<DataUpdateRequest> CREATOR = new zzz();
    private final DataSet zzeb;
    private final zzcq zzgj;
    private final long zzs;
    private final long zzt;

    public static class Builder {
        private DataSet zzeb;
        private long zzs;
        private long zzt;

        public Builder setTimeInterval(long j, long j2, TimeUnit timeUnit) {
            Preconditions.checkArgument(j > 0, "Invalid start time :%d", Long.valueOf(j));
            Preconditions.checkArgument(j2 >= j, "Invalid end time :%d", Long.valueOf(j2));
            this.zzs = timeUnit.toMillis(j);
            this.zzt = timeUnit.toMillis(j2);
            return this;
        }

        public Builder setDataSet(DataSet dataSet) {
            Preconditions.checkNotNull(dataSet, "Must set the data set");
            this.zzeb = dataSet;
            return this;
        }

        public DataUpdateRequest build() {
            Preconditions.checkNotZero(this.zzs, "Must set a non-zero value for startTimeMillis/startTime");
            Preconditions.checkNotZero(this.zzt, "Must set a non-zero value for endTimeMillis/endTime");
            Preconditions.checkNotNull(this.zzeb, "Must set the data set");
            for (DataPoint dataPoint : this.zzeb.getDataPoints()) {
                long startTime = dataPoint.getStartTime(TimeUnit.MILLISECONDS);
                long endTime = dataPoint.getEndTime(TimeUnit.MILLISECONDS);
                Preconditions.checkState(!(startTime > endTime || (startTime != 0 && startTime < this.zzs) || ((startTime != 0 && startTime > this.zzt) || endTime > this.zzt || endTime < this.zzs)), "Data Point's startTimeMillis %d, endTimeMillis %d should lie between timeRange provided in the request. StartTimeMillis %d, EndTimeMillis: %d", Long.valueOf(startTime), Long.valueOf(endTime), Long.valueOf(this.zzs), Long.valueOf(this.zzt));
            }
            return new DataUpdateRequest(this);
        }
    }

    public DataUpdateRequest(long j, long j2, DataSet dataSet, IBinder iBinder) {
        this.zzs = j;
        this.zzt = j2;
        this.zzeb = dataSet;
        this.zzgj = zzcr.zzj(iBinder);
    }

    private DataUpdateRequest(Builder builder) {
        this(builder.zzs, builder.zzt, builder.zzeb, null);
    }

    public DataUpdateRequest(DataUpdateRequest dataUpdateRequest, IBinder iBinder) {
        this(dataUpdateRequest.zzs, dataUpdateRequest.zzt, dataUpdateRequest.getDataSet(), iBinder);
    }

    public final long zzu() {
        return this.zzs;
    }

    public final long zzv() {
        return this.zzt;
    }

    public DataSet getDataSet() {
        return this.zzeb;
    }

    public IBinder getCallbackBinder() {
        zzcq zzcqVar = this.zzgj;
        if (zzcqVar == null) {
            return null;
        }
        return zzcqVar.asBinder();
    }

    public long getStartTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzs, TimeUnit.MILLISECONDS);
    }

    public long getEndTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzt, TimeUnit.MILLISECONDS);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DataUpdateRequest)) {
            return false;
        }
        DataUpdateRequest dataUpdateRequest = (DataUpdateRequest) obj;
        return this.zzs == dataUpdateRequest.zzs && this.zzt == dataUpdateRequest.zzt && Objects.equal(this.zzeb, dataUpdateRequest.zzeb);
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.zzs), Long.valueOf(this.zzt), this.zzeb);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("startTimeMillis", Long.valueOf(this.zzs)).add("endTimeMillis", Long.valueOf(this.zzt)).add("dataSet", this.zzeb).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.zzs);
        SafeParcelWriter.writeLong(parcel, 2, this.zzt);
        SafeParcelWriter.writeParcelable(parcel, 3, getDataSet(), i, false);
        SafeParcelWriter.writeIBinder(parcel, 4, getCallbackBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
