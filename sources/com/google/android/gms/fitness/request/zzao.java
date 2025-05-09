package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ClientIdentity;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.fitness.zzcq;
import com.google.android.gms.internal.fitness.zzcr;
import com.google.android.gms.location.LocationRequest;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes8.dex */
public final class zzao extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzao> CREATOR = new zzap();
    private final long zzec;
    private final int zzed;
    private final zzcq zzgj;
    private final PendingIntent zzhi;
    private com.google.android.gms.fitness.data.zzt zzhr;
    private final long zzhs;
    private final long zzht;
    private final List<LocationRequest> zzhu;
    private final long zzhv;
    private final List<ClientIdentity> zzhw;
    private DataType zzq;
    private DataSource zzr;

    zzao(DataSource dataSource, DataType dataType, IBinder iBinder, int i, int i2, long j, long j2, PendingIntent pendingIntent, long j3, int i3, List<LocationRequest> list, long j4, IBinder iBinder2) {
        this.zzr = dataSource;
        this.zzq = dataType;
        this.zzhr = iBinder == null ? null : com.google.android.gms.fitness.data.zzu.zza(iBinder);
        this.zzec = j == 0 ? i : j;
        this.zzht = j3;
        this.zzhs = j2 == 0 ? i2 : j2;
        this.zzhu = list;
        this.zzhi = pendingIntent;
        this.zzed = i3;
        this.zzhw = Collections.emptyList();
        this.zzhv = j4;
        this.zzgj = zzcr.zzj(iBinder2);
    }

    public zzao(SensorRequest sensorRequest, com.google.android.gms.fitness.data.zzt zztVar, PendingIntent pendingIntent, zzcq zzcqVar) {
        this(sensorRequest.getDataSource(), sensorRequest.getDataType(), zztVar, pendingIntent, sensorRequest.getSamplingRate(TimeUnit.MICROSECONDS), sensorRequest.getFastestRate(TimeUnit.MICROSECONDS), sensorRequest.getMaxDeliveryLatency(TimeUnit.MICROSECONDS), sensorRequest.getAccuracyMode(), null, Collections.emptyList(), sensorRequest.zzx(), zzcqVar);
    }

    private zzao(DataSource dataSource, DataType dataType, com.google.android.gms.fitness.data.zzt zztVar, PendingIntent pendingIntent, long j, long j2, long j3, int i, List<LocationRequest> list, List<ClientIdentity> list2, long j4, zzcq zzcqVar) {
        this.zzr = dataSource;
        this.zzq = dataType;
        this.zzhr = zztVar;
        this.zzhi = pendingIntent;
        this.zzec = j;
        this.zzht = j2;
        this.zzhs = j3;
        this.zzed = i;
        this.zzhu = null;
        this.zzhw = list2;
        this.zzhv = j4;
        this.zzgj = zzcqVar;
    }

    public final String toString() {
        return String.format("SensorRegistrationRequest{type %s source %s interval %s fastest %s latency %s}", this.zzq, this.zzr, Long.valueOf(this.zzec), Long.valueOf(this.zzht), Long.valueOf(this.zzhs));
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzr, i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzq, i, false);
        com.google.android.gms.fitness.data.zzt zztVar = this.zzhr;
        SafeParcelWriter.writeIBinder(parcel, 3, zztVar == null ? null : zztVar.asBinder(), false);
        SafeParcelWriter.writeInt(parcel, 4, 0);
        SafeParcelWriter.writeInt(parcel, 5, 0);
        SafeParcelWriter.writeLong(parcel, 6, this.zzec);
        SafeParcelWriter.writeLong(parcel, 7, this.zzhs);
        SafeParcelWriter.writeParcelable(parcel, 8, this.zzhi, i, false);
        SafeParcelWriter.writeLong(parcel, 9, this.zzht);
        SafeParcelWriter.writeInt(parcel, 10, this.zzed);
        SafeParcelWriter.writeTypedList(parcel, 11, this.zzhu, false);
        SafeParcelWriter.writeLong(parcel, 12, this.zzhv);
        zzcq zzcqVar = this.zzgj;
        SafeParcelWriter.writeIBinder(parcel, 13, zzcqVar != null ? zzcqVar.asBinder() : null, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzao)) {
            return false;
        }
        zzao zzaoVar = (zzao) obj;
        return Objects.equal(this.zzr, zzaoVar.zzr) && Objects.equal(this.zzq, zzaoVar.zzq) && Objects.equal(this.zzhr, zzaoVar.zzhr) && this.zzec == zzaoVar.zzec && this.zzht == zzaoVar.zzht && this.zzhs == zzaoVar.zzhs && this.zzed == zzaoVar.zzed && Objects.equal(this.zzhu, zzaoVar.zzhu);
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzr, this.zzq, this.zzhr, Long.valueOf(this.zzec), Long.valueOf(this.zzht), Long.valueOf(this.zzhs), Integer.valueOf(this.zzed), this.zzhu);
    }
}
