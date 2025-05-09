package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.fitness.zzfa;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes8.dex */
public class Bucket extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<Bucket> CREATOR = new zze();
    public static final int TYPE_ACTIVITY_SEGMENT = 4;
    public static final int TYPE_ACTIVITY_TYPE = 3;
    public static final int TYPE_SESSION = 2;
    public static final int TYPE_TIME = 1;
    private final int zzai;
    private final List<DataSet> zzaj;
    private final int zzak;
    private boolean zzal;
    private final long zzs;
    private final long zzt;
    private final Session zzz;

    Bucket(long j, long j2, Session session, int i, List<DataSet> list, int i2, boolean z) {
        this.zzs = j;
        this.zzt = j2;
        this.zzz = session;
        this.zzai = i;
        this.zzaj = list;
        this.zzak = i2;
        this.zzal = z;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public Bucket(com.google.android.gms.fitness.data.RawBucket r11, java.util.List<com.google.android.gms.fitness.data.DataSource> r12) {
        /*
            r10 = this;
            long r1 = r11.zzs
            long r3 = r11.zzt
            com.google.android.gms.fitness.data.Session r5 = r11.zzz
            int r6 = r11.zzdv
            java.util.List<com.google.android.gms.fitness.data.RawDataSet> r0 = r11.zzaj
            java.util.ArrayList r7 = new java.util.ArrayList
            int r8 = r0.size()
            r7.<init>(r8)
            java.util.Iterator r0 = r0.iterator()
        L17:
            boolean r8 = r0.hasNext()
            if (r8 == 0) goto L2c
            java.lang.Object r8 = r0.next()
            com.google.android.gms.fitness.data.RawDataSet r8 = (com.google.android.gms.fitness.data.RawDataSet) r8
            com.google.android.gms.fitness.data.DataSet r9 = new com.google.android.gms.fitness.data.DataSet
            r9.<init>(r8, r12)
            r7.add(r9)
            goto L17
        L2c:
            int r8 = r11.zzak
            boolean r9 = r11.zzal
            r0 = r10
            r0.<init>(r1, r3, r5, r6, r7, r8, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.fitness.data.Bucket.<init>(com.google.android.gms.fitness.data.RawBucket, java.util.List):void");
    }

    public long getStartTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzs, TimeUnit.MILLISECONDS);
    }

    public long getEndTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzt, TimeUnit.MILLISECONDS);
    }

    public Session getSession() {
        return this.zzz;
    }

    public String getActivity() {
        return zzfa.getName(this.zzai);
    }

    public final int getActivityType() {
        return this.zzai;
    }

    public List<DataSet> getDataSets() {
        return this.zzaj;
    }

    public DataSet getDataSet(DataType dataType) {
        for (DataSet dataSet : this.zzaj) {
            if (dataSet.getDataType().equals(dataType)) {
                return dataSet;
            }
        }
        return null;
    }

    public int getBucketType() {
        return this.zzak;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Bucket)) {
            return false;
        }
        Bucket bucket = (Bucket) obj;
        return this.zzs == bucket.zzs && this.zzt == bucket.zzt && this.zzai == bucket.zzai && Objects.equal(this.zzaj, bucket.zzaj) && this.zzak == bucket.zzak && this.zzal == bucket.zzal;
    }

    public final boolean zza(Bucket bucket) {
        return this.zzs == bucket.zzs && this.zzt == bucket.zzt && this.zzai == bucket.zzai && this.zzak == bucket.zzak;
    }

    public final boolean zza() {
        if (this.zzal) {
            return true;
        }
        Iterator<DataSet> it = this.zzaj.iterator();
        while (it.hasNext()) {
            if (it.next().zza()) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.zzs), Long.valueOf(this.zzt), Integer.valueOf(this.zzai), Integer.valueOf(this.zzak));
    }

    public String toString() {
        return Objects.toStringHelper(this).add("startTime", Long.valueOf(this.zzs)).add("endTime", Long.valueOf(this.zzt)).add("activity", Integer.valueOf(this.zzai)).add("dataSets", this.zzaj).add("bucketType", zza(this.zzak)).add("serverHasMoreData", Boolean.valueOf(this.zzal)).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.zzs);
        SafeParcelWriter.writeLong(parcel, 2, this.zzt);
        SafeParcelWriter.writeParcelable(parcel, 3, getSession(), i, false);
        SafeParcelWriter.writeInt(parcel, 4, this.zzai);
        SafeParcelWriter.writeTypedList(parcel, 5, getDataSets(), false);
        SafeParcelWriter.writeInt(parcel, 6, getBucketType());
        SafeParcelWriter.writeBoolean(parcel, 7, zza());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public static String zza(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? "bug" : "intervals" : "segment" : "type" : "session" : "time" : "none";
    }
}
