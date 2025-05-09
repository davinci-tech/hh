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
import com.google.android.gms.internal.fitness.zzck;
import com.google.android.gms.internal.fitness.zzcl;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes8.dex */
public class SessionReadRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<SessionReadRequest> CREATOR = new zzaw();
    private final List<DataType> zzah;
    private final List<DataSource> zzgm;
    private final boolean zzgw;
    private final String zzic;
    private final String zzid;
    private boolean zzie;
    private final List<String> zzif;
    private final zzck zzig;
    private final long zzs;
    private final long zzt;

    SessionReadRequest(String str, String str2, long j, long j2, List<DataType> list, List<DataSource> list2, boolean z, boolean z2, List<String> list3, IBinder iBinder) {
        this.zzic = str;
        this.zzid = str2;
        this.zzs = j;
        this.zzt = j2;
        this.zzah = list;
        this.zzgm = list2;
        this.zzie = z;
        this.zzgw = z2;
        this.zzif = list3;
        this.zzig = zzcl.zzh(iBinder);
    }

    public static class Builder {
        private String zzic;
        private String zzid;
        private long zzs = 0;
        private long zzt = 0;
        private List<DataType> zzah = new ArrayList();
        private List<DataSource> zzgm = new ArrayList();
        private boolean zzih = false;
        private boolean zzgw = false;
        private List<String> zzif = new ArrayList();

        public Builder setTimeInterval(long j, long j2, TimeUnit timeUnit) {
            this.zzs = timeUnit.toMillis(j);
            this.zzt = timeUnit.toMillis(j2);
            return this;
        }

        public Builder setSessionName(String str) {
            this.zzic = str;
            return this;
        }

        public Builder setSessionId(String str) {
            this.zzid = str;
            return this;
        }

        public Builder read(DataSource dataSource) {
            Preconditions.checkNotNull(dataSource, "Attempting to add a null data source");
            if (!this.zzgm.contains(dataSource)) {
                this.zzgm.add(dataSource);
            }
            return this;
        }

        public Builder read(DataType dataType) {
            Preconditions.checkNotNull(dataType, "Attempting to use a null data type");
            if (!this.zzah.contains(dataType)) {
                this.zzah.add(dataType);
            }
            return this;
        }

        public Builder readSessionsFromAllApps() {
            this.zzih = true;
            return this;
        }

        public Builder excludePackage(String str) {
            Preconditions.checkNotNull(str, "Attempting to use a null package name");
            if (!this.zzif.contains(str)) {
                this.zzif.add(str);
            }
            return this;
        }

        public Builder enableServerQueries() {
            this.zzgw = true;
            return this;
        }

        public SessionReadRequest build() {
            long j = this.zzs;
            Preconditions.checkArgument(j > 0, "Invalid start time: %s", Long.valueOf(j));
            long j2 = this.zzt;
            Preconditions.checkArgument(j2 > 0 && j2 > this.zzs, "Invalid end time: %s", Long.valueOf(j2));
            return new SessionReadRequest(this);
        }
    }

    private SessionReadRequest(Builder builder) {
        this(builder.zzic, builder.zzid, builder.zzs, builder.zzt, (List<DataType>) builder.zzah, (List<DataSource>) builder.zzgm, builder.zzih, builder.zzgw, (List<String>) builder.zzif, (zzck) null);
    }

    public SessionReadRequest(SessionReadRequest sessionReadRequest, zzck zzckVar) {
        this(sessionReadRequest.zzic, sessionReadRequest.zzid, sessionReadRequest.zzs, sessionReadRequest.zzt, sessionReadRequest.zzah, sessionReadRequest.zzgm, sessionReadRequest.zzie, sessionReadRequest.zzgw, sessionReadRequest.zzif, zzckVar);
    }

    private SessionReadRequest(String str, String str2, long j, long j2, List<DataType> list, List<DataSource> list2, boolean z, boolean z2, List<String> list3, zzck zzckVar) {
        this(str, str2, j, j2, list, list2, z, z2, list3, zzckVar == null ? null : zzckVar.asBinder());
    }

    public long getStartTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzs, TimeUnit.MILLISECONDS);
    }

    public long getEndTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzt, TimeUnit.MILLISECONDS);
    }

    public String getSessionName() {
        return this.zzic;
    }

    public String getSessionId() {
        return this.zzid;
    }

    public List<DataType> getDataTypes() {
        return this.zzah;
    }

    public List<DataSource> getDataSources() {
        return this.zzgm;
    }

    public boolean includeSessionsFromAllApps() {
        return this.zzie;
    }

    public List<String> getExcludedPackages() {
        return this.zzif;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SessionReadRequest)) {
            return false;
        }
        SessionReadRequest sessionReadRequest = (SessionReadRequest) obj;
        return Objects.equal(this.zzic, sessionReadRequest.zzic) && this.zzid.equals(sessionReadRequest.zzid) && this.zzs == sessionReadRequest.zzs && this.zzt == sessionReadRequest.zzt && Objects.equal(this.zzah, sessionReadRequest.zzah) && Objects.equal(this.zzgm, sessionReadRequest.zzgm) && this.zzie == sessionReadRequest.zzie && this.zzif.equals(sessionReadRequest.zzif) && this.zzgw == sessionReadRequest.zzgw;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzic, this.zzid, Long.valueOf(this.zzs), Long.valueOf(this.zzt));
    }

    public String toString() {
        return Objects.toStringHelper(this).add("sessionName", this.zzic).add("sessionId", this.zzid).add("startTimeMillis", Long.valueOf(this.zzs)).add("endTimeMillis", Long.valueOf(this.zzt)).add("dataTypes", this.zzah).add("dataSources", this.zzgm).add("sessionsFromAllApps", Boolean.valueOf(this.zzie)).add("excludedPackages", this.zzif).add("useServer", Boolean.valueOf(this.zzgw)).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getSessionName(), false);
        SafeParcelWriter.writeString(parcel, 2, getSessionId(), false);
        SafeParcelWriter.writeLong(parcel, 3, this.zzs);
        SafeParcelWriter.writeLong(parcel, 4, this.zzt);
        SafeParcelWriter.writeTypedList(parcel, 5, getDataTypes(), false);
        SafeParcelWriter.writeTypedList(parcel, 6, getDataSources(), false);
        SafeParcelWriter.writeBoolean(parcel, 7, includeSessionsFromAllApps());
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzgw);
        SafeParcelWriter.writeStringList(parcel, 9, getExcludedPackages(), false);
        zzck zzckVar = this.zzig;
        SafeParcelWriter.writeIBinder(parcel, 10, zzckVar == null ? null : zzckVar.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
