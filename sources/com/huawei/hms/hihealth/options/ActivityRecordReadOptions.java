package com.huawei.hms.hihealth.options;

import android.os.Parcelable;
import com.huawei.hms.common.internal.Objects;
import com.huawei.hms.common.internal.Preconditions;
import com.huawei.hms.health.aabq;
import com.huawei.hms.health.aabv;
import com.huawei.hms.health.aabw;
import com.huawei.hms.health.aaby;
import com.huawei.hms.health.aacc;
import com.huawei.hms.health.aacs;
import com.huawei.hms.hihealth.data.DataCollector;
import com.huawei.hms.hihealth.data.DataType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public class ActivityRecordReadOptions extends aabq {
    public static final Parcelable.Creator<ActivityRecordReadOptions> CREATOR = new aabq.aab(ActivityRecordReadOptions.class);
    private static final TimeUnit TIME_UNIT = TimeUnit.MILLISECONDS;

    @aaby(id = 2)
    private final String mActivityRecordId;

    @aaby(id = 1)
    private final String mActivityRecordName;

    @aaby(id = 10)
    private final List<Integer> mActivityTypeIdList;

    @aaby(id = 4)
    private final List<DataCollector> mDataCollectorList;

    @aaby(id = 3)
    private final List<DataType> mDataTypeList;

    @aaby(id = 6)
    private final long mEndTime;

    @aaby(id = 7)
    private boolean mIsFromAllApps;

    @aaby(id = 8)
    private final boolean mIsRemoteInquiry;

    @aaby(id = 9)
    private final List<String> mRemoveApplicationsList;

    @aaby(id = 5)
    private final long mStartTime;

    public static class Builder {
        private String aab;
        private String aaba;
        private long aabb = 0;
        private long aabc = 0;
        private List<DataType> aabd = new ArrayList();
        private List<DataCollector> aabe = new ArrayList();
        private boolean aabf = false;
        private boolean aabg = false;
        private List<String> aabh = new ArrayList();
        private List<Integer> aabi = new ArrayList();

        public Builder setTimeInterval(long j, long j2, TimeUnit timeUnit) {
            this.aabb = timeUnit.toMillis(j);
            this.aabc = timeUnit.toMillis(j2);
            Preconditions.checkState(this.aabb > 1388505600000L, "Start time must be later than default start time: 20140101 00:00:000");
            Preconditions.checkState(this.aabc >= this.aabb, "the start time must be less than the end time");
            return this;
        }

        public Builder setActivityTypeList(List<String> list) {
            if (list != null && !list.isEmpty()) {
                Iterator<String> it = list.iterator();
                while (it.hasNext()) {
                    this.aabi.add(Integer.valueOf(aacc.aab(it.next())));
                }
            }
            Preconditions.checkState(this.aabi.size() <= 20, "activityTypeList size cannot exceed 20");
            return this;
        }

        @Deprecated
        public Builder setActivityRecordName(String str) {
            this.aab = str;
            return this;
        }

        @Deprecated
        public Builder setActivityRecordId(String str) {
            this.aaba = str;
            return this;
        }

        public Builder removeApplication(String str) {
            Preconditions.checkNotNull(str, "Application name should not be null");
            if (!this.aabh.contains(str)) {
                this.aabh.add(str);
            }
            return this;
        }

        public Builder readActivityRecordsFromAllApps() {
            this.aabf = true;
            return this;
        }

        public Builder read(DataType dataType) {
            Preconditions.checkNotNull(dataType, "Cannot read a null data type");
            if (!this.aabd.contains(dataType)) {
                this.aabd.add(dataType);
            }
            return this;
        }

        @Deprecated
        public Builder read(DataCollector dataCollector) {
            Preconditions.checkNotNull(dataCollector, "Cannot read a null data collector");
            if (!this.aabe.contains(dataCollector)) {
                this.aabe.add(dataCollector);
            }
            return this;
        }

        public ActivityRecordReadOptions build() {
            aacs.aab(this.aabb, this.aabc, TimeUnit.MILLISECONDS);
            boolean z = false;
            Preconditions.checkArgument(this.aabb > 0, "Start time is illegal");
            long j = this.aabc;
            if (j > 0 && j > this.aabb) {
                z = true;
            }
            Preconditions.checkArgument(z, "End time is illegal");
            return new ActivityRecordReadOptions(this);
        }

        public Builder allowRemoteInquiry() {
            this.aabg = true;
            return this;
        }
    }

    public String toString() {
        return Objects.toStringHelper(this).add("activityRecordName", this.mActivityRecordName).add("activityRecordId", this.mActivityRecordId).add("dataTypes", this.mDataTypeList).add("dataCollectors", this.mDataCollectorList).add("startTime", Long.valueOf(this.mStartTime)).add("endTime", Long.valueOf(this.mEndTime)).add("fromAllApps", Boolean.valueOf(this.mIsFromAllApps)).add("useRemote", Boolean.valueOf(this.mIsRemoteInquiry)).add("removeApplications", this.mRemoveApplicationsList).add("activityTypes", this.mActivityTypeIdList).toString();
    }

    public boolean isAllAppsSelected() {
        return this.mIsFromAllApps;
    }

    public int hashCode() {
        return Objects.hashCode(this.mActivityRecordName, this.mActivityRecordId, Long.valueOf(this.mStartTime), Long.valueOf(this.mEndTime));
    }

    public long getStartTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.mStartTime, TIME_UNIT);
    }

    public List<String> getRemoveApplications() {
        return this.mRemoveApplicationsList;
    }

    public long getEndTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.mEndTime, TIME_UNIT);
    }

    public boolean getEnableServerQueries() {
        return this.mIsRemoteInquiry;
    }

    public List<DataType> getDataTypes() {
        return this.mDataTypeList;
    }

    @Deprecated
    public List<DataCollector> getDataCollectors() {
        return this.mDataCollectorList;
    }

    public List<Integer> getActivityTypeIdList() {
        return this.mActivityTypeIdList;
    }

    @Deprecated
    public String getActivityRecordName() {
        return this.mActivityRecordName;
    }

    @Deprecated
    public String getActivityRecordId() {
        return this.mActivityRecordId;
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (!(obj instanceof ActivityRecordReadOptions)) {
                return false;
            }
            ActivityRecordReadOptions activityRecordReadOptions = (ActivityRecordReadOptions) obj;
            if (!Objects.equal(this.mActivityRecordName, activityRecordReadOptions.mActivityRecordName) || !this.mActivityRecordId.equals(activityRecordReadOptions.mActivityRecordId) || this.mStartTime != activityRecordReadOptions.mStartTime || this.mEndTime != activityRecordReadOptions.mEndTime || !Objects.equal(this.mDataTypeList, activityRecordReadOptions.mDataTypeList) || !Objects.equal(this.mDataCollectorList, activityRecordReadOptions.mDataCollectorList) || this.mIsFromAllApps != activityRecordReadOptions.mIsFromAllApps || !this.mRemoveApplicationsList.equals(activityRecordReadOptions.mRemoveApplicationsList) || this.mIsRemoteInquiry != activityRecordReadOptions.mIsRemoteInquiry) {
                return false;
            }
        }
        return true;
    }

    @aabw
    private ActivityRecordReadOptions(@aabv(id = 1) String str, @aabv(id = 2) String str2, @aabv(id = 3) List<DataType> list, @aabv(id = 4) List<DataCollector> list2, @aabv(id = 5) long j, @aabv(id = 6) long j2, @aabv(id = 7) boolean z, @aabv(id = 8) boolean z2, @aabv(id = 9) List<String> list3, @aabv(id = 10) List<Integer> list4) {
        this.mActivityRecordName = str;
        this.mActivityRecordId = str2;
        this.mStartTime = j;
        this.mEndTime = j2;
        this.mDataTypeList = list;
        this.mDataCollectorList = list2;
        this.mIsFromAllApps = z;
        this.mIsRemoteInquiry = z2;
        this.mRemoveApplicationsList = list3;
        this.mActivityTypeIdList = list4;
    }

    private ActivityRecordReadOptions(Builder builder) {
        this(builder.aab, builder.aaba, builder.aabd, builder.aabe, builder.aabb, builder.aabc, builder.aabf, builder.aabg, builder.aabh, builder.aabi);
    }
}
