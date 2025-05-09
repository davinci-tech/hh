package com.huawei.hms.hihealth.options;

import android.os.Parcelable;
import com.huawei.hms.common.internal.Preconditions;
import com.huawei.hms.health.aabq;
import com.huawei.hms.health.aabv;
import com.huawei.hms.health.aabw;
import com.huawei.hms.health.aaby;
import com.huawei.hms.health.aabz;
import com.huawei.hms.hihealth.data.DataType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public class ActivityRecordDeleteOptions extends aabq {
    public static final Parcelable.Creator<ActivityRecordDeleteOptions> CREATOR = new aabq.aab(ActivityRecordDeleteOptions.class);
    private static final TimeUnit TIME_UNIT = TimeUnit.MILLISECONDS;

    @aaby(id = 4)
    private final List<String> mActivityRecordIds;

    @aaby(id = 5)
    private final boolean mDeleteSubData;

    @aaby(id = 3)
    private final long mEndTime;

    @aaby(id = 2)
    private final long mStartTime;

    @aaby(id = 1)
    private final List<DataType> mSubDataTypeList;

    public static class Builder {
        private List<DataType> aab = new ArrayList();
        private long aaba;
        private long aabb;
        private List<String> aabc;
        private boolean aabd;

        public Builder setTimeInterval(long j, long j2, TimeUnit timeUnit) {
            Preconditions.checkArgument(j >= 0, "Start time is illegal");
            Preconditions.checkArgument(j2 >= j, "End time is illegal");
            this.aaba = ActivityRecordDeleteOptions.TIME_UNIT.convert(j, timeUnit);
            this.aabb = ActivityRecordDeleteOptions.TIME_UNIT.convert(j2, timeUnit);
            return this;
        }

        public Builder setSubDataTypeList(List<DataType> list) {
            this.aab = list;
            this.aabd = true;
            return this;
        }

        public Builder setActivityRecordIds(List<String> list) {
            this.aabc = list;
            return this;
        }

        @Deprecated
        public Builder isDeleteSubData(boolean z) {
            this.aabd = z;
            return this;
        }

        public ActivityRecordDeleteOptions build() {
            Preconditions.checkArgument(this.aaba >= 0, "Start time is illegal");
            Preconditions.checkArgument(this.aabb >= this.aaba, "End time is illegal");
            if (aabz.aab(this.aabc).booleanValue() && (this.aaba == 0 || this.aabb == 0)) {
                throw new IllegalStateException("deleteActivityRecord: should input starTime endTime or activityRecordId");
            }
            return new ActivityRecordDeleteOptions(this);
        }
    }

    @Deprecated
    public boolean isDeleteSubData() {
        return this.mDeleteSubData || aabz.aaba(this.mSubDataTypeList).booleanValue();
    }

    public List<DataType> getSubDataTypeList() {
        return this.mSubDataTypeList;
    }

    public long getStartTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.mStartTime, TIME_UNIT);
    }

    public long getEndTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.mEndTime, TIME_UNIT);
    }

    public List<String> getActivityRecordIds() {
        return this.mActivityRecordIds;
    }

    @aabw
    private ActivityRecordDeleteOptions(@aabv(id = 1) List<DataType> list, @aabv(id = 2) long j, @aabv(id = 3) long j2, @aabv(id = 4) List<String> list2, @aabv(id = 5) boolean z) {
        this.mSubDataTypeList = Collections.unmodifiableList(list);
        this.mStartTime = j;
        this.mEndTime = j2;
        this.mActivityRecordIds = list2;
        this.mDeleteSubData = z;
    }

    private ActivityRecordDeleteOptions(Builder builder) {
        this(builder.aab, builder.aaba, builder.aabb, builder.aabc, builder.aabd);
    }
}
