package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Session;
import com.google.android.gms.internal.fitness.zzcq;
import com.google.android.gms.internal.fitness.zzcr;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public class DataDeleteRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<DataDeleteRequest> CREATOR = new zzj();
    private final List<DataType> zzah;
    private final zzcq zzgj;
    private final List<DataSource> zzgm;
    private final List<Session> zzgn;
    private final boolean zzgo;
    private final boolean zzgp;
    private final long zzs;
    private final long zzt;

    DataDeleteRequest(long j, long j2, List<DataSource> list, List<DataType> list2, List<Session> list3, boolean z, boolean z2, IBinder iBinder) {
        this.zzs = j;
        this.zzt = j2;
        this.zzgm = Collections.unmodifiableList(list);
        this.zzah = Collections.unmodifiableList(list2);
        this.zzgn = list3;
        this.zzgo = z;
        this.zzgp = z2;
        this.zzgj = zzcr.zzj(iBinder);
    }

    public static class Builder {
        private long zzs;
        private long zzt;
        private List<DataSource> zzgm = new ArrayList();
        private List<DataType> zzah = new ArrayList();
        private List<Session> zzgn = new ArrayList();
        private boolean zzgo = false;
        private boolean zzgp = false;

        public Builder setTimeInterval(long j, long j2, TimeUnit timeUnit) {
            Preconditions.checkArgument(j > 0, "Invalid start time :%d", Long.valueOf(j));
            Preconditions.checkArgument(j2 > j, "Invalid end time :%d", Long.valueOf(j2));
            this.zzs = timeUnit.toMillis(j);
            this.zzt = timeUnit.toMillis(j2);
            return this;
        }

        public Builder deleteAllData() {
            Preconditions.checkArgument(this.zzah.isEmpty(), "Specific data type already added for deletion. deleteAllData() will delete all data types and cannot be combined with addDataType()");
            Preconditions.checkArgument(this.zzgm.isEmpty(), "Specific data source already added for deletion. deleteAllData() will delete all data sources and cannot be combined with addDataSource()");
            this.zzgo = true;
            return this;
        }

        public Builder addDataType(DataType dataType) {
            Preconditions.checkArgument(!this.zzgo, "All data is already marked for deletion.  addDataType() cannot be combined with deleteAllData()");
            Preconditions.checkArgument(dataType != null, "Must specify a valid data type");
            if (!this.zzah.contains(dataType)) {
                this.zzah.add(dataType);
            }
            return this;
        }

        public Builder addDataSource(DataSource dataSource) {
            Preconditions.checkArgument(!this.zzgo, "All data is already marked for deletion.  addDataSource() cannot be combined with deleteAllData()");
            Preconditions.checkArgument(dataSource != null, "Must specify a valid data source");
            if (!this.zzgm.contains(dataSource)) {
                this.zzgm.add(dataSource);
            }
            return this;
        }

        public Builder addSession(Session session) {
            Preconditions.checkArgument(!this.zzgp, "All sessions already marked for deletion.  addSession() cannot be combined with deleteAllSessions()");
            Preconditions.checkArgument(session != null, "Must specify a valid session");
            Preconditions.checkArgument(session.getEndTime(TimeUnit.MILLISECONDS) > 0, "Cannot delete an ongoing session. Please stop the session prior to deleting it");
            this.zzgn.add(session);
            return this;
        }

        public Builder deleteAllSessions() {
            Preconditions.checkArgument(this.zzgn.isEmpty(), "Specific session already added for deletion. deleteAllData() will delete all sessions and cannot be combined with addSession()");
            this.zzgp = true;
            return this;
        }

        public DataDeleteRequest build() {
            long j = this.zzs;
            Preconditions.checkState(j > 0 && this.zzt > j, "Must specify a valid time interval");
            Preconditions.checkState((this.zzgo || !this.zzgm.isEmpty() || !this.zzah.isEmpty()) || (this.zzgp || !this.zzgn.isEmpty()), "No data or session marked for deletion");
            if (!this.zzgn.isEmpty()) {
                for (Session session : this.zzgn) {
                    Preconditions.checkState(session.getStartTime(TimeUnit.MILLISECONDS) >= this.zzs && session.getEndTime(TimeUnit.MILLISECONDS) <= this.zzt, "Session %s is outside the time interval [%d, %d]", session, Long.valueOf(this.zzs), Long.valueOf(this.zzt));
                }
            }
            return new DataDeleteRequest(this);
        }
    }

    private DataDeleteRequest(Builder builder) {
        this(builder.zzs, builder.zzt, (List<DataSource>) builder.zzgm, (List<DataType>) builder.zzah, (List<Session>) builder.zzgn, builder.zzgo, builder.zzgp, (zzcq) null);
    }

    public DataDeleteRequest(DataDeleteRequest dataDeleteRequest, zzcq zzcqVar) {
        this(dataDeleteRequest.zzs, dataDeleteRequest.zzt, dataDeleteRequest.zzgm, dataDeleteRequest.zzah, dataDeleteRequest.zzgn, dataDeleteRequest.zzgo, dataDeleteRequest.zzgp, zzcqVar);
    }

    private DataDeleteRequest(long j, long j2, List<DataSource> list, List<DataType> list2, List<Session> list3, boolean z, boolean z2, zzcq zzcqVar) {
        this.zzs = j;
        this.zzt = j2;
        this.zzgm = Collections.unmodifiableList(list);
        this.zzah = Collections.unmodifiableList(list2);
        this.zzgn = list3;
        this.zzgo = z;
        this.zzgp = z2;
        this.zzgj = zzcqVar;
    }

    public long getStartTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzs, TimeUnit.MILLISECONDS);
    }

    public long getEndTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzt, TimeUnit.MILLISECONDS);
    }

    public List<DataSource> getDataSources() {
        return this.zzgm;
    }

    public List<DataType> getDataTypes() {
        return this.zzah;
    }

    public List<Session> getSessions() {
        return this.zzgn;
    }

    public boolean deleteAllData() {
        return this.zzgo;
    }

    public boolean deleteAllSessions() {
        return this.zzgp;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DataDeleteRequest)) {
            return false;
        }
        DataDeleteRequest dataDeleteRequest = (DataDeleteRequest) obj;
        return this.zzs == dataDeleteRequest.zzs && this.zzt == dataDeleteRequest.zzt && Objects.equal(this.zzgm, dataDeleteRequest.zzgm) && Objects.equal(this.zzah, dataDeleteRequest.zzah) && Objects.equal(this.zzgn, dataDeleteRequest.zzgn) && this.zzgo == dataDeleteRequest.zzgo && this.zzgp == dataDeleteRequest.zzgp;
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.zzs), Long.valueOf(this.zzt));
    }

    public String toString() {
        return Objects.toStringHelper(this).add("startTimeMillis", Long.valueOf(this.zzs)).add("endTimeMillis", Long.valueOf(this.zzt)).add("dataSources", this.zzgm).add("dateTypes", this.zzah).add("sessions", this.zzgn).add("deleteAllData", Boolean.valueOf(this.zzgo)).add("deleteAllSessions", Boolean.valueOf(this.zzgp)).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.zzs);
        SafeParcelWriter.writeLong(parcel, 2, this.zzt);
        SafeParcelWriter.writeTypedList(parcel, 3, getDataSources(), false);
        SafeParcelWriter.writeTypedList(parcel, 4, getDataTypes(), false);
        SafeParcelWriter.writeTypedList(parcel, 5, getSessions(), false);
        SafeParcelWriter.writeBoolean(parcel, 6, deleteAllData());
        SafeParcelWriter.writeBoolean(parcel, 7, deleteAllSessions());
        zzcq zzcqVar = this.zzgj;
        SafeParcelWriter.writeIBinder(parcel, 8, zzcqVar == null ? null : zzcqVar.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
