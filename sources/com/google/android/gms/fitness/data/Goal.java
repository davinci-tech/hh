package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.fitness.zzfa;
import com.huawei.operation.ble.BleConstants;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes8.dex */
public class Goal extends AbstractSafeParcelable {
    public static final Parcelable.Creator<Goal> CREATOR = new zzs();
    public static final int OBJECTIVE_TYPE_DURATION = 2;
    public static final int OBJECTIVE_TYPE_FREQUENCY = 3;
    public static final int OBJECTIVE_TYPE_METRIC = 1;
    private final long zzdj;
    private final long zzdk;
    private final List<Integer> zzdl;
    private final Recurrence zzdm;
    private final int zzdn;
    private final MetricObjective zzdo;
    private final DurationObjective zzdp;
    private final FrequencyObjective zzdq;

    public static class DurationObjective extends AbstractSafeParcelable {
        public static final Parcelable.Creator<DurationObjective> CREATOR = new zzp();
        private final long zzdr;

        public long getDuration(TimeUnit timeUnit) {
            return timeUnit.convert(this.zzdr, TimeUnit.NANOSECONDS);
        }

        DurationObjective(long j) {
            this.zzdr = j;
        }

        public DurationObjective(long j, TimeUnit timeUnit) {
            this(timeUnit.toNanos(j));
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            return (obj instanceof DurationObjective) && this.zzdr == ((DurationObjective) obj).zzdr;
        }

        public int hashCode() {
            return (int) this.zzdr;
        }

        public String toString() {
            return Objects.toStringHelper(this).add("duration", Long.valueOf(this.zzdr)).toString();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeLong(parcel, 1, this.zzdr);
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }

    public static class FrequencyObjective extends AbstractSafeParcelable {
        public static final Parcelable.Creator<FrequencyObjective> CREATOR = new zzr();
        private final int frequency;

        public int getFrequency() {
            return this.frequency;
        }

        public FrequencyObjective(int i) {
            this.frequency = i;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            return (obj instanceof FrequencyObjective) && this.frequency == ((FrequencyObjective) obj).frequency;
        }

        public int hashCode() {
            return this.frequency;
        }

        public String toString() {
            return Objects.toStringHelper(this).add("frequency", Integer.valueOf(this.frequency)).toString();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeInt(parcel, 1, getFrequency());
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }

    public static class MetricObjective extends AbstractSafeParcelable {
        public static final Parcelable.Creator<MetricObjective> CREATOR = new zzx();
        private final double value;
        private final String zzds;
        private final double zzdt;

        public String getDataTypeName() {
            return this.zzds;
        }

        public double getValue() {
            return this.value;
        }

        public MetricObjective(String str, double d, double d2) {
            this.zzds = str;
            this.value = d;
            this.zzdt = d2;
        }

        public MetricObjective(String str, double d) {
            this(str, d, 0.0d);
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof MetricObjective)) {
                return false;
            }
            MetricObjective metricObjective = (MetricObjective) obj;
            return Objects.equal(this.zzds, metricObjective.zzds) && this.value == metricObjective.value && this.zzdt == metricObjective.zzdt;
        }

        public int hashCode() {
            return this.zzds.hashCode();
        }

        public String toString() {
            return Objects.toStringHelper(this).add(BleConstants.DATA_TYPE_NAME, this.zzds).add("value", Double.valueOf(this.value)).add("initialValue", Double.valueOf(this.zzdt)).toString();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeString(parcel, 1, getDataTypeName(), false);
            SafeParcelWriter.writeDouble(parcel, 2, getValue());
            SafeParcelWriter.writeDouble(parcel, 3, this.zzdt);
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }

    public static class MismatchedGoalException extends IllegalStateException {
        public MismatchedGoalException(String str) {
            super(str);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface ObjectiveType {
    }

    public static class Recurrence extends AbstractSafeParcelable {
        public static final Parcelable.Creator<Recurrence> CREATOR = new zzab();
        public static final int UNIT_DAY = 1;
        public static final int UNIT_MONTH = 3;
        public static final int UNIT_WEEK = 2;
        private final int count;
        private final int zzdu;

        @Retention(RetentionPolicy.SOURCE)
        /* loaded from: classes2.dex */
        public @interface RecurrenceUnit {
        }

        public int getCount() {
            return this.count;
        }

        public int getUnit() {
            return this.zzdu;
        }

        public Recurrence(int i, int i2) {
            this.count = i;
            Preconditions.checkState(i2 > 0 && i2 <= 3);
            this.zzdu = i2;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Recurrence)) {
                return false;
            }
            Recurrence recurrence = (Recurrence) obj;
            return this.count == recurrence.count && this.zzdu == recurrence.zzdu;
        }

        public int hashCode() {
            return this.zzdu;
        }

        public String toString() {
            String str;
            Objects.ToStringHelper add = Objects.toStringHelper(this).add("count", Integer.valueOf(this.count));
            int i = this.zzdu;
            if (i == 1) {
                str = "day";
            } else if (i == 2) {
                str = "week";
            } else {
                if (i != 3) {
                    throw new IllegalArgumentException("invalid unit value");
                }
                str = "month";
            }
            return add.add("unit", str).toString();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeInt(parcel, 1, getCount());
            SafeParcelWriter.writeInt(parcel, 2, getUnit());
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        }
    }

    public long getCreateTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.zzdj, TimeUnit.NANOSECONDS);
    }

    public long getStartTime(Calendar calendar, TimeUnit timeUnit) {
        if (this.zzdm == null) {
            return timeUnit.convert(this.zzdj, TimeUnit.NANOSECONDS);
        }
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(calendar.getTime());
        int i = this.zzdm.zzdu;
        if (i == 1) {
            calendar2.set(11, 0);
            return timeUnit.convert(calendar2.getTimeInMillis(), TimeUnit.MILLISECONDS);
        }
        if (i == 2) {
            calendar2.set(7, 2);
            calendar2.set(11, 0);
            return timeUnit.convert(calendar2.getTimeInMillis(), TimeUnit.MILLISECONDS);
        }
        if (i == 3) {
            calendar2.set(5, 1);
            calendar2.set(11, 0);
            return timeUnit.convert(calendar2.getTimeInMillis(), TimeUnit.MILLISECONDS);
        }
        int i2 = this.zzdm.zzdu;
        StringBuilder sb = new StringBuilder(24);
        sb.append("Invalid unit ");
        sb.append(i2);
        throw new IllegalArgumentException(sb.toString());
    }

    public long getEndTime(Calendar calendar, TimeUnit timeUnit) {
        if (this.zzdm != null) {
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(calendar.getTime());
            int i = this.zzdm.zzdu;
            if (i == 1) {
                calendar2.add(5, 1);
                calendar2.set(11, 0);
                return timeUnit.convert(calendar2.getTimeInMillis(), TimeUnit.MILLISECONDS);
            }
            if (i == 2) {
                calendar2.add(4, 1);
                calendar2.set(7, 2);
                calendar2.set(11, 0);
                return timeUnit.convert(calendar2.getTimeInMillis(), TimeUnit.MILLISECONDS);
            }
            if (i == 3) {
                calendar2.add(2, 1);
                calendar2.set(5, 1);
                calendar2.set(11, 0);
                return timeUnit.convert(calendar2.getTimeInMillis(), TimeUnit.MILLISECONDS);
            }
            int i2 = this.zzdm.zzdu;
            StringBuilder sb = new StringBuilder(24);
            sb.append("Invalid unit ");
            sb.append(i2);
            throw new IllegalArgumentException(sb.toString());
        }
        return timeUnit.convert(this.zzdk, TimeUnit.NANOSECONDS);
    }

    public String getActivityName() {
        if (this.zzdl.isEmpty() || this.zzdl.size() > 1) {
            return null;
        }
        return zzfa.getName(this.zzdl.get(0).intValue());
    }

    public Recurrence getRecurrence() {
        return this.zzdm;
    }

    public int getObjectiveType() {
        return this.zzdn;
    }

    private static String zze(int i) {
        if (i == 0) {
            return "unknown";
        }
        if (i == 1) {
            return "metric";
        }
        if (i == 2) {
            return "duration";
        }
        if (i == 3) {
            return "frequency";
        }
        throw new IllegalArgumentException("invalid objective type value");
    }

    public MetricObjective getMetricObjective() {
        zzf(1);
        return this.zzdo;
    }

    public DurationObjective getDurationObjective() {
        zzf(2);
        return this.zzdp;
    }

    public FrequencyObjective getFrequencyObjective() {
        zzf(3);
        return this.zzdq;
    }

    Goal(long j, long j2, List<Integer> list, Recurrence recurrence, int i, MetricObjective metricObjective, DurationObjective durationObjective, FrequencyObjective frequencyObjective) {
        this.zzdj = j;
        this.zzdk = j2;
        this.zzdl = list;
        this.zzdm = recurrence;
        this.zzdn = i;
        this.zzdo = metricObjective;
        this.zzdp = durationObjective;
        this.zzdq = frequencyObjective;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Goal)) {
            return false;
        }
        Goal goal = (Goal) obj;
        return this.zzdj == goal.zzdj && this.zzdk == goal.zzdk && Objects.equal(this.zzdl, goal.zzdl) && Objects.equal(this.zzdm, goal.zzdm) && this.zzdn == goal.zzdn && Objects.equal(this.zzdo, goal.zzdo) && Objects.equal(this.zzdp, goal.zzdp) && Objects.equal(this.zzdq, goal.zzdq);
    }

    public int hashCode() {
        return this.zzdn;
    }

    public String toString() {
        return Objects.toStringHelper(this).add("activity", getActivityName()).add("recurrence", this.zzdm).add("metricObjective", this.zzdo).add("durationObjective", this.zzdp).add("frequencyObjective", this.zzdq).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.zzdj);
        SafeParcelWriter.writeLong(parcel, 2, this.zzdk);
        SafeParcelWriter.writeList(parcel, 3, this.zzdl, false);
        SafeParcelWriter.writeParcelable(parcel, 4, getRecurrence(), i, false);
        SafeParcelWriter.writeInt(parcel, 5, getObjectiveType());
        SafeParcelWriter.writeParcelable(parcel, 6, this.zzdo, i, false);
        SafeParcelWriter.writeParcelable(parcel, 7, this.zzdp, i, false);
        SafeParcelWriter.writeParcelable(parcel, 8, this.zzdq, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    private final void zzf(int i) throws MismatchedGoalException {
        int i2 = this.zzdn;
        if (i != i2) {
            throw new MismatchedGoalException(String.format("%s goal does not have %s objective", zze(i2), zze(i)));
        }
    }
}
