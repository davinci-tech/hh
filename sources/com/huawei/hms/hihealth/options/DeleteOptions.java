package com.huawei.hms.hihealth.options;

import android.os.Parcelable;
import com.huawei.hms.common.internal.Objects;
import com.huawei.hms.common.internal.Preconditions;
import com.huawei.hms.health.aabq;
import com.huawei.hms.health.aabv;
import com.huawei.hms.health.aabw;
import com.huawei.hms.health.aaby;
import com.huawei.hms.health.aacs;
import com.huawei.hms.hihealth.data.ActivityRecord;
import com.huawei.hms.hihealth.data.DataCollector;
import com.huawei.hms.hihealth.data.DataType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public class DeleteOptions extends aabq {
    public static final Parcelable.Creator<DeleteOptions> CREATOR = new aabq.aab(DeleteOptions.class);

    @aaby(id = 6)
    private final List<ActivityRecord> mActivityRecords;

    @aaby(id = 2)
    private final List<DataCollector> mDataCollectors;

    @aaby(id = 1)
    private final List<DataType> mDataTypes;

    @aaby(id = 4)
    private final long mEndTime;

    @aaby(id = 7)
    private final boolean mIsDeleteAllActivityRecords;

    @aaby(id = 5)
    private final boolean mIsDeleteAllData;

    @aaby(id = 3)
    private final long mStartTime;

    public static class Builder {
        private long aabb;
        private long aabc;
        private List<DataType> aab = new ArrayList();
        private List<DataCollector> aaba = new ArrayList();
        private boolean aabd = false;
        private List<ActivityRecord> aabe = new ArrayList();
        private boolean aabf = false;

        public Builder setTimeInterval(long j, long j2, TimeUnit timeUnit) {
            Preconditions.checkArgument(j >= 0, "Start time is illegal");
            Preconditions.checkArgument(j2 >= j, "End time is illegal");
            this.aabb = timeUnit.toMillis(j);
            this.aabc = timeUnit.toMillis(j2);
            return this;
        }

        public Builder deleteAllData() {
            Preconditions.checkArgument(this.aab.isEmpty(), "deleteAllData() can not be used together with addDataType(), because deleteAllData() will delete all data");
            Preconditions.checkArgument(this.aaba.isEmpty(), "deleteAllData() can not be use together with addDataCollector(), because deleteAllData() will delete all data");
            this.aabd = true;
            return this;
        }

        public Builder deleteAllActivityRecords() {
            Preconditions.checkArgument(this.aabe.isEmpty(), "deleteAllActivityRecords() can not be used together with addActivityRecord(), because deleteAllActivityRecords() will delete all activityRecords");
            this.aabf = true;
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:25:0x0059  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public com.huawei.hms.hihealth.options.DeleteOptions build() {
            /*
                r9 = this;
                long r0 = r9.aabb
                r2 = 0
                int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
                r5 = 1
                r6 = 0
                if (r4 < 0) goto L16
                long r7 = r9.aabc
                int r2 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
                if (r2 <= 0) goto L16
                int r0 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
                if (r0 < 0) goto L16
                r0 = r5
                goto L17
            L16:
                r0 = r6
            L17:
                java.lang.String r1 = "Time range is invalid"
                com.huawei.hms.common.internal.Preconditions.checkState(r0, r1)
                boolean r0 = r9.aabd
                if (r0 != 0) goto L33
                java.util.List<com.huawei.hms.hihealth.data.DataCollector> r0 = r9.aaba
                boolean r0 = r0.isEmpty()
                if (r0 == 0) goto L33
                java.util.List<com.huawei.hms.hihealth.data.DataType> r0 = r9.aab
                boolean r0 = r0.isEmpty()
                if (r0 != 0) goto L31
                goto L33
            L31:
                r0 = r6
                goto L34
            L33:
                r0 = r5
            L34:
                boolean r1 = r9.aabf
                if (r1 != 0) goto L43
                java.util.List<com.huawei.hms.hihealth.data.ActivityRecord> r1 = r9.aabe
                boolean r1 = r1.isEmpty()
                if (r1 != 0) goto L41
                goto L43
            L41:
                r1 = r6
                goto L44
            L43:
                r1 = r5
            L44:
                if (r0 != 0) goto L4b
                if (r1 == 0) goto L49
                goto L4b
            L49:
                r0 = r6
                goto L4c
            L4b:
                r0 = r5
            L4c:
                java.lang.String r1 = "No data or activityRecord marked for deletion"
                com.huawei.hms.common.internal.Preconditions.checkState(r0, r1)
                java.util.List<com.huawei.hms.hihealth.data.ActivityRecord> r0 = r9.aabe
                boolean r0 = r0.isEmpty()
                if (r0 != 0) goto L99
                java.util.List<com.huawei.hms.hihealth.data.ActivityRecord> r0 = r9.aabe
                java.util.Iterator r0 = r0.iterator()
            L5f:
                boolean r1 = r0.hasNext()
                if (r1 == 0) goto L99
                java.lang.Object r1 = r0.next()
                com.huawei.hms.hihealth.data.ActivityRecord r1 = (com.huawei.hms.hihealth.data.ActivityRecord) r1
                java.util.concurrent.TimeUnit r2 = java.util.concurrent.TimeUnit.MILLISECONDS
                long r2 = r1.getStartTime(r2)
                long r7 = r9.aabb
                int r2 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
                if (r2 < 0) goto L85
                java.util.concurrent.TimeUnit r2 = java.util.concurrent.TimeUnit.MILLISECONDS
                long r2 = r1.getEndTime(r2)
                long r7 = r9.aabc
                int r2 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
                if (r2 > 0) goto L85
                r2 = r5
                goto L86
            L85:
                r2 = r6
            L86:
                java.lang.String r3 = "Start time or end time of activity record is outside the set time interval"
                com.huawei.hms.common.internal.Preconditions.checkState(r2, r3)
                java.lang.String r1 = r1.getPackageName()
                boolean r1 = com.huawei.hms.health.aacs.aabc(r1)
                java.lang.String r2 = "must specify the valid package name."
                com.huawei.hms.common.internal.Preconditions.checkArgument(r1, r2)
                goto L5f
            L99:
                com.huawei.hms.hihealth.options.DeleteOptions r0 = new com.huawei.hms.hihealth.options.DeleteOptions
                r1 = 0
                r0.<init>(r9)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.hihealth.options.DeleteOptions.Builder.build():com.huawei.hms.hihealth.options.DeleteOptions");
        }

        public Builder addDataType(DataType dataType) {
            Preconditions.checkArgument(!this.aabd, "addDataType() can not be used together with deleteAllData(), because deleteAllData() will be delete all data.");
            Preconditions.checkArgument(dataType != null, "data type should not be null");
            if (!this.aab.contains(dataType)) {
                this.aab.add(dataType);
            }
            return this;
        }

        public Builder addDataCollector(DataCollector dataCollector) {
            Preconditions.checkArgument(!this.aabd, "addDataCollector() can not be used together with deleteAllData(), because deleteAllData() will be delete all data.");
            Preconditions.checkArgument(dataCollector != null, "DataCollector should not be null");
            if (!this.aaba.contains(dataCollector)) {
                this.aaba.add(dataCollector);
            }
            return this;
        }

        public Builder addActivityRecord(ActivityRecord activityRecord) {
            Preconditions.checkArgument(!this.aabf, "addActivityRecord() can not be used together with deleteAllActivityRecords()");
            Preconditions.checkArgument(activityRecord != null, "ActivityRecord should not be null");
            Preconditions.checkArgument(activityRecord.getEndTime(TimeUnit.MILLISECONDS) > 0, "Cannot delete a activityRecord that has not ended");
            Preconditions.checkArgument(aacs.aabc(activityRecord.getPackageName()), "must specify the valid package name.");
            this.aabe.add(activityRecord);
            return this;
        }
    }

    public String toString() {
        return Objects.toStringHelper(this).add("dateTypes", this.mDataTypes).add("dataCollectors", this.mDataCollectors).add("startTime", Long.valueOf(this.mStartTime)).add("endTime", Long.valueOf(this.mEndTime)).add("deleteAllData", Boolean.valueOf(this.mIsDeleteAllData)).add("activityRecords", this.mActivityRecords).add("isDeleteAllActivityRecords", Boolean.valueOf(this.mIsDeleteAllActivityRecords)).toString();
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.mStartTime), Long.valueOf(this.mEndTime));
    }

    public long getStartTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.mStartTime, TimeUnit.MILLISECONDS);
    }

    public long getEndTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.mEndTime, TimeUnit.MILLISECONDS);
    }

    public List<DataType> getDataTypes() {
        return this.mDataTypes;
    }

    public List<DataCollector> getDataCollectors() {
        return this.mDataCollectors;
    }

    public List<ActivityRecord> getActivityRecords() {
        return this.mActivityRecords;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DeleteOptions)) {
            return false;
        }
        DeleteOptions deleteOptions = (DeleteOptions) obj;
        return Objects.equal(this.mDataTypes, deleteOptions.mDataTypes) && Objects.equal(this.mDataCollectors, deleteOptions.mDataCollectors) && this.mStartTime == deleteOptions.mStartTime && this.mEndTime == deleteOptions.mEndTime && this.mIsDeleteAllData == deleteOptions.mIsDeleteAllData && Objects.equal(this.mActivityRecords, deleteOptions.mActivityRecords) && this.mIsDeleteAllActivityRecords == deleteOptions.mIsDeleteAllActivityRecords;
    }

    public boolean deleteAllData() {
        return this.mIsDeleteAllData;
    }

    public boolean deleteAllActivityRecords() {
        return this.mIsDeleteAllActivityRecords;
    }

    @aabw
    public DeleteOptions(@aabv(id = 1) List<DataType> list, @aabv(id = 2) List<DataCollector> list2, @aabv(id = 3) long j, @aabv(id = 4) long j2, @aabv(id = 5) boolean z, @aabv(id = 6) List<ActivityRecord> list3, @aabv(id = 7) boolean z2) {
        this.mDataTypes = Collections.unmodifiableList(list);
        this.mDataCollectors = list2;
        this.mStartTime = j;
        this.mEndTime = j2;
        this.mIsDeleteAllData = z;
        this.mActivityRecords = list3;
        this.mIsDeleteAllActivityRecords = z2;
    }

    private DeleteOptions(Builder builder) {
        this(builder.aab, builder.aaba, builder.aabb, builder.aabc, builder.aabd, builder.aabe, builder.aabf);
    }
}
