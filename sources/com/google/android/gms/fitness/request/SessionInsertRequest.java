package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.internal.fitness.zzcq;
import com.google.android.gms.internal.fitness.zzcr;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes8.dex */
public class SessionInsertRequest extends AbstractSafeParcelable {
    private final List<DataSet> zzaj;
    private final zzcq zzgj;
    private final List<DataPoint> zzia;
    private final Session zzz;
    private static final TimeUnit zzhz = TimeUnit.MILLISECONDS;
    public static final Parcelable.Creator<SessionInsertRequest> CREATOR = new zzau();

    SessionInsertRequest(Session session, List<DataSet> list, List<DataPoint> list2, IBinder iBinder) {
        this.zzz = session;
        this.zzaj = Collections.unmodifiableList(list);
        this.zzia = Collections.unmodifiableList(list2);
        this.zzgj = zzcr.zzj(iBinder);
    }

    public static class Builder {
        private List<DataSet> zzaj = new ArrayList();
        private List<DataPoint> zzia = new ArrayList();
        private List<DataSource> zzib = new ArrayList();
        private Session zzz;

        public Builder setSession(Session session) {
            this.zzz = session;
            return this;
        }

        public Builder addDataSet(DataSet dataSet) {
            Preconditions.checkArgument(dataSet != null, "Must specify a valid data set.");
            DataSource dataSource = dataSet.getDataSource();
            Preconditions.checkState(!this.zzib.contains(dataSource), "Data set for this data source %s is already added.", dataSource);
            Preconditions.checkArgument(true ^ dataSet.getDataPoints().isEmpty(), "No data points specified in the input data set.");
            this.zzib.add(dataSource);
            this.zzaj.add(dataSet);
            return this;
        }

        public Builder addAggregateDataPoint(DataPoint dataPoint) {
            Preconditions.checkArgument(dataPoint != null, "Must specify a valid aggregate data point.");
            DataSource dataSource = dataPoint.getDataSource();
            Preconditions.checkState(true ^ this.zzib.contains(dataSource), "Data set/Aggregate data point for this data source %s is already added.", dataSource);
            DataSet.zzb(dataPoint);
            this.zzib.add(dataSource);
            this.zzia.add(dataPoint);
            return this;
        }

        public SessionInsertRequest build() {
            Preconditions.checkState(this.zzz != null, "Must specify a valid session.");
            Preconditions.checkState(this.zzz.getEndTime(TimeUnit.MILLISECONDS) != 0, "Must specify a valid end time, cannot insert a continuing session.");
            Iterator<DataSet> it = this.zzaj.iterator();
            while (it.hasNext()) {
                Iterator<DataPoint> it2 = it.next().getDataPoints().iterator();
                while (it2.hasNext()) {
                    zzd(it2.next());
                }
            }
            Iterator<DataPoint> it3 = this.zzia.iterator();
            while (it3.hasNext()) {
                zzd(it3.next());
            }
            return new SessionInsertRequest(this);
        }

        private final void zzd(DataPoint dataPoint) {
            long startTime = this.zzz.getStartTime(TimeUnit.NANOSECONDS);
            long endTime = this.zzz.getEndTime(TimeUnit.NANOSECONDS);
            long timestamp = dataPoint.getTimestamp(TimeUnit.NANOSECONDS);
            if (timestamp != 0) {
                if (timestamp < startTime || timestamp > endTime) {
                    timestamp = com.google.android.gms.internal.fitness.zze.zza(timestamp, TimeUnit.NANOSECONDS, SessionInsertRequest.zzhz);
                }
                Preconditions.checkState(timestamp >= startTime && timestamp <= endTime, "Data point %s has time stamp outside session interval [%d, %d]", dataPoint, Long.valueOf(startTime), Long.valueOf(endTime));
                if (dataPoint.getTimestamp(TimeUnit.NANOSECONDS) != timestamp) {
                    Log.w("Fitness", String.format("Data point timestamp [%d] is truncated to [%d] to match the precision [%s] of the session start and end time", Long.valueOf(dataPoint.getTimestamp(TimeUnit.NANOSECONDS)), Long.valueOf(timestamp), SessionInsertRequest.zzhz));
                    dataPoint.setTimestamp(timestamp, TimeUnit.NANOSECONDS);
                }
            }
            long startTime2 = this.zzz.getStartTime(TimeUnit.NANOSECONDS);
            long endTime2 = this.zzz.getEndTime(TimeUnit.NANOSECONDS);
            long startTime3 = dataPoint.getStartTime(TimeUnit.NANOSECONDS);
            long endTime3 = dataPoint.getEndTime(TimeUnit.NANOSECONDS);
            if (startTime3 == 0 || endTime3 == 0) {
                return;
            }
            if (endTime3 > endTime2) {
                endTime3 = com.google.android.gms.internal.fitness.zze.zza(endTime3, TimeUnit.NANOSECONDS, SessionInsertRequest.zzhz);
            }
            Preconditions.checkState(startTime3 >= startTime2 && endTime3 <= endTime2, "Data point %s has start and end times outside session interval [%d, %d]", dataPoint, Long.valueOf(startTime2), Long.valueOf(endTime2));
            if (endTime3 != dataPoint.getEndTime(TimeUnit.NANOSECONDS)) {
                Log.w("Fitness", String.format("Data point end time [%d] is truncated to [%d] to match the precision [%s] of the session start and end time", Long.valueOf(dataPoint.getEndTime(TimeUnit.NANOSECONDS)), Long.valueOf(endTime3), SessionInsertRequest.zzhz));
                dataPoint.setTimeInterval(startTime3, endTime3, TimeUnit.NANOSECONDS);
            }
        }
    }

    private SessionInsertRequest(Builder builder) {
        this(builder.zzz, (List<DataSet>) builder.zzaj, (List<DataPoint>) builder.zzia, (zzcq) null);
    }

    public SessionInsertRequest(SessionInsertRequest sessionInsertRequest, zzcq zzcqVar) {
        this(sessionInsertRequest.zzz, sessionInsertRequest.zzaj, sessionInsertRequest.zzia, zzcqVar);
    }

    private SessionInsertRequest(Session session, List<DataSet> list, List<DataPoint> list2, zzcq zzcqVar) {
        this.zzz = session;
        this.zzaj = Collections.unmodifiableList(list);
        this.zzia = Collections.unmodifiableList(list2);
        this.zzgj = zzcqVar;
    }

    public Session getSession() {
        return this.zzz;
    }

    public List<DataSet> getDataSets() {
        return this.zzaj;
    }

    public List<DataPoint> getAggregateDataPoints() {
        return this.zzia;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SessionInsertRequest)) {
            return false;
        }
        SessionInsertRequest sessionInsertRequest = (SessionInsertRequest) obj;
        return Objects.equal(this.zzz, sessionInsertRequest.zzz) && Objects.equal(this.zzaj, sessionInsertRequest.zzaj) && Objects.equal(this.zzia, sessionInsertRequest.zzia);
    }

    public int hashCode() {
        return Objects.hashCode(this.zzz, this.zzaj, this.zzia);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("session", this.zzz).add("dataSets", this.zzaj).add("aggregateDataPoints", this.zzia).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getSession(), i, false);
        SafeParcelWriter.writeTypedList(parcel, 2, getDataSets(), false);
        SafeParcelWriter.writeTypedList(parcel, 3, getAggregateDataPoints(), false);
        zzcq zzcqVar = this.zzgj;
        SafeParcelWriter.writeIBinder(parcel, 4, zzcqVar == null ? null : zzcqVar.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
