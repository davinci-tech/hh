package com.huawei.hms.hihealth.options;

import android.os.Parcelable;
import com.huawei.hms.common.internal.Objects;
import com.huawei.hms.common.internal.Preconditions;
import com.huawei.hms.health.aabq;
import com.huawei.hms.health.aabv;
import com.huawei.hms.health.aabw;
import com.huawei.hms.health.aaby;
import com.huawei.hms.health.aabz;
import com.huawei.hms.health.aac;
import com.huawei.hms.health.aacs;
import com.huawei.hms.hihealth.HiHealthStatusCodes;
import com.huawei.hms.hihealth.data.DataCollector;
import com.huawei.hms.hihealth.data.DataType;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public class HealthRecordReadOptions extends aabq {
    public static final Parcelable.Creator<HealthRecordReadOptions> CREATOR = new aabq.aab(HealthRecordReadOptions.class);
    private static final TimeUnit TIME_UNIT = TimeUnit.NANOSECONDS;

    @aaby(id = 3)
    private final DataCollector mDataCollector;

    @aaby(id = 4)
    private final DataType mDataType;

    @aaby(id = 2)
    private final long mEndTime;

    @aaby(id = 5)
    private final boolean mIsFromAllApps;

    @aaby(id = 6)
    private final boolean mIsRemoteInquiry;

    @aaby(id = 7)
    private final List<String> mRemoveApplicationsList;

    @aaby(id = 1)
    private final long mStartTime;

    @aaby(id = 8)
    private final List<DataType> mSubDataTypeList;

    public String toString() {
        return Objects.toStringHelper(this).add("startTime", Long.valueOf(this.mStartTime)).add("endTime", Long.valueOf(this.mEndTime)).add("dataCollector", this.mDataCollector).add("dataType", this.mDataType).add("fromAllApps", Boolean.valueOf(this.mIsFromAllApps)).add("useRemote", Boolean.valueOf(this.mIsRemoteInquiry)).add("removeApplications", this.mRemoveApplicationsList).add("subDataTypes", this.mSubDataTypeList).toString();
    }

    public boolean isAllAppsSelected() {
        return this.mIsFromAllApps;
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.mStartTime), Long.valueOf(this.mEndTime), this.mDataCollector, this.mDataType, Boolean.valueOf(this.mIsFromAllApps), Boolean.valueOf(this.mIsRemoteInquiry), this.mRemoveApplicationsList, this.mSubDataTypeList);
    }

    public List<DataType> getSubDataTypeList() {
        return this.mSubDataTypeList;
    }

    public static class Builder {
        private DataCollector aabb;
        private DataType aabc;
        private long aab = 0;
        private long aaba = 0;
        private boolean aabd = false;
        private boolean aabe = false;
        private List<String> aabf = new ArrayList();
        private List<DataType> aabg = new ArrayList();

        public Builder setTimeInterval(long j, long j2, TimeUnit timeUnit) {
            this.aab = TimeUnit.NANOSECONDS.convert(j, timeUnit);
            this.aaba = TimeUnit.NANOSECONDS.convert(j2, timeUnit);
            Preconditions.checkState(this.aab > 1388505600000000000L, "Start time must be later than default start time: 20140101 00:00:000");
            Preconditions.checkState(this.aaba >= this.aab, "the start time must be less than the end time");
            return this;
        }

        public Builder setSubDataTypeList(List<DataType> list) {
            if (list != null && !list.isEmpty()) {
                this.aabg = list;
            }
            return this;
        }

        public Builder removeApplication(String str) {
            Preconditions.checkNotNull(str, "Application name should not be null");
            if (!this.aabf.contains(str)) {
                this.aabf.add(str);
            }
            return this;
        }

        public Builder readHealthRecordsFromAllApps() {
            this.aabd = true;
            return this;
        }

        public Builder readByDataType(DataType dataType) {
            if (dataType != null) {
                this.aabc = dataType;
            }
            return this;
        }

        public Builder readByDataCollector(DataCollector dataCollector) {
            if (dataCollector != null) {
                this.aabb = dataCollector;
            }
            return this;
        }

        public HealthRecordReadOptions build() {
            aacs.aab(this.aab, this.aaba, TimeUnit.NANOSECONDS);
            Preconditions.checkArgument((this.aabc == null && this.aabb == null) ? false : true, "mDataType and mDataCollector,one of them must fill in");
            DataType dataType = this.aabc;
            if (dataType != null && this.aabb != null) {
                Preconditions.checkArgument(dataType.getName().equals(this.aabb.getDataType().getName()), "data type must be the same as that of the collector.");
            }
            long j = this.aaba;
            Preconditions.checkArgument(j > 0 && j > this.aab, "End time is illegal");
            DataType dataType2 = this.aabc;
            if (dataType2 != null && !aac.aab(dataType2.getName())) {
                aabz.aab("build HealthRecordReadOptions", "The input datatype is not supported by the health record type.");
                throw new SecurityException(String.valueOf(HiHealthStatusCodes.HEALTH_RECORDS_NOT_SUPPORT));
            }
            DataCollector dataCollector = this.aabb;
            if (dataCollector == null || dataCollector.getDataType() == null || aac.aab(this.aabb.getDataType().getName())) {
                return new HealthRecordReadOptions(this.aab, this.aaba, this.aabb, this.aabc, this.aabd, this.aabe, this.aabf, this.aabg);
            }
            aabz.aab("build HealthRecordReadOptions", "The input datatype is not supported by the health record type.");
            throw new SecurityException(String.valueOf(HiHealthStatusCodes.HEALTH_RECORDS_NOT_SUPPORT));
        }

        public Builder allowRemoteInquiry() {
            this.aabe = true;
            return this;
        }
    }

    public long getStartTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.mStartTime, TIME_UNIT);
    }

    public List<String> getRemoveApplicationsList() {
        return this.mRemoveApplicationsList;
    }

    public long getEndTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.mEndTime, TIME_UNIT);
    }

    public boolean getEnableServerQueries() {
        return this.mIsRemoteInquiry;
    }

    public DataType getDataType() {
        return this.mDataType;
    }

    public DataCollector getDataCollector() {
        return this.mDataCollector;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HealthRecordReadOptions)) {
            return false;
        }
        HealthRecordReadOptions healthRecordReadOptions = (HealthRecordReadOptions) obj;
        return java.util.Objects.equals(Long.valueOf(this.mStartTime), Long.valueOf(healthRecordReadOptions.mStartTime)) && java.util.Objects.equals(Long.valueOf(this.mEndTime), Long.valueOf(healthRecordReadOptions.mEndTime)) && java.util.Objects.equals(this.mDataCollector, healthRecordReadOptions.mDataCollector) && java.util.Objects.equals(this.mDataType, healthRecordReadOptions.mDataType) && java.util.Objects.equals(Boolean.valueOf(this.mIsFromAllApps), Boolean.valueOf(healthRecordReadOptions.mIsFromAllApps)) && java.util.Objects.equals(Boolean.valueOf(this.mIsRemoteInquiry), Boolean.valueOf(healthRecordReadOptions.mIsRemoteInquiry)) && java.util.Objects.equals(this.mRemoveApplicationsList, healthRecordReadOptions.mRemoveApplicationsList) && java.util.Objects.equals(this.mSubDataTypeList, healthRecordReadOptions.mSubDataTypeList);
    }

    @aabw
    private HealthRecordReadOptions(@aabv(id = 1) long j, @aabv(id = 2) long j2, @aabv(id = 3) DataCollector dataCollector, @aabv(id = 4) DataType dataType, @aabv(id = 5) boolean z, @aabv(id = 6) boolean z2, @aabv(id = 7) List<String> list, @aabv(id = 8) List<DataType> list2) {
        this.mStartTime = j;
        this.mEndTime = j2;
        this.mDataCollector = dataCollector;
        this.mDataType = dataType;
        this.mIsFromAllApps = z;
        this.mIsRemoteInquiry = z2;
        this.mRemoveApplicationsList = list;
        this.mSubDataTypeList = list2;
    }
}
