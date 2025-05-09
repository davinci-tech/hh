package com.google.android.gms.fitness.data;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import java.util.concurrent.TimeUnit;

/* loaded from: classes8.dex */
public class DataUpdateNotification extends AbstractSafeParcelable {
    public static final String ACTION = "com.google.android.gms.fitness.DATA_UPDATE_NOTIFICATION";
    public static final Parcelable.Creator<DataUpdateNotification> CREATOR = new zzn();
    public static final String EXTRA_DATA_UPDATE_NOTIFICATION = "vnd.google.fitness.data_udpate_notification";
    public static final int OPERATION_DELETE = 2;
    public static final int OPERATION_INSERT = 1;
    public static final int OPERATION_UPDATE = 3;
    private final long zzbz;
    private final long zzca;
    private final int zzcb;
    private final DataType zzq;
    private final DataSource zzr;

    public DataUpdateNotification(long j, long j2, int i, DataSource dataSource, DataType dataType) {
        this.zzbz = j;
        this.zzca = j2;
        this.zzcb = i;
        this.zzr = dataSource;
        this.zzq = dataType;
    }

    public static DataUpdateNotification getDataUpdateNotification(Intent intent) {
        return (DataUpdateNotification) SafeParcelableSerializer.deserializeFromIntentExtra(intent, EXTRA_DATA_UPDATE_NOTIFICATION, CREATOR);
    }

    public long getUpdateStartTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzbz, TimeUnit.NANOSECONDS);
    }

    public long getUpdateEndTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzca, TimeUnit.NANOSECONDS);
    }

    public int getOperationType() {
        return this.zzcb;
    }

    public DataSource getDataSource() {
        return this.zzr;
    }

    public DataType getDataType() {
        return this.zzq;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DataUpdateNotification)) {
            return false;
        }
        DataUpdateNotification dataUpdateNotification = (DataUpdateNotification) obj;
        return this.zzbz == dataUpdateNotification.zzbz && this.zzca == dataUpdateNotification.zzca && this.zzcb == dataUpdateNotification.zzcb && Objects.equal(this.zzr, dataUpdateNotification.zzr) && Objects.equal(this.zzq, dataUpdateNotification.zzq);
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.zzbz), Long.valueOf(this.zzca), Integer.valueOf(this.zzcb), this.zzr, this.zzq);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("updateStartTimeNanos", Long.valueOf(this.zzbz)).add("updateEndTimeNanos", Long.valueOf(this.zzca)).add("operationType", Integer.valueOf(this.zzcb)).add("dataSource", this.zzr).add("dataType", this.zzq).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.zzbz);
        SafeParcelWriter.writeLong(parcel, 2, this.zzca);
        SafeParcelWriter.writeInt(parcel, 3, getOperationType());
        SafeParcelWriter.writeParcelable(parcel, 4, getDataSource(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 5, getDataType(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
