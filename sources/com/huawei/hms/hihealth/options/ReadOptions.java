package com.huawei.hms.hihealth.options;

import android.os.Parcelable;
import com.huawei.hms.common.internal.Objects;
import com.huawei.hms.common.internal.Preconditions;
import com.huawei.hms.health.aabq;
import com.huawei.hms.health.aabv;
import com.huawei.hms.health.aabw;
import com.huawei.hms.health.aaby;
import com.huawei.hms.health.aabz;
import com.huawei.hms.health.aacs;
import com.huawei.hms.hihealth.data.DataCollector;
import com.huawei.hms.hihealth.data.DataType;
import com.huawei.hms.hihealth.data.DeviceInfo;
import com.huawei.hms.hihealth.data.Group;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public class ReadOptions extends aabq {
    public static final Parcelable.Creator<ReadOptions> CREATOR = new aabq.aab(ReadOptions.class);
    public static final int NO_LIMIT = 0;
    private static final String STRING_APPEND_SPACE = " ";
    private static final String TAG = "ReadOptions";

    @aaby(id = 7)
    private final DataCollector mActivityDataCollector;

    @aaby(id = 4)
    private final List<DataCollector> mDataCollectors;

    @aaby(id = 3)
    private final List<DataType> mDataTypes;

    @aaby(id = 2)
    private final long mEndTime;

    @aaby(id = 14)
    @Deprecated
    private final List<DeviceInfo> mFilteredDeviceInfos;

    @aaby(id = 9)
    private final List<Long> mGroupEndTimes;

    @aaby(id = 6)
    private final long mGroupPeriod;

    @aaby(id = 8)
    private final List<Long> mGroupStartTimes;

    @aaby(id = 5)
    private final int mGroupType;

    @aaby(id = 11)
    private final boolean mIsQueryFromNetwork;
    private transient boolean mIsQueryLocalDevice;

    @aaby(id = 10)
    private final int mPageSize;

    @aaby(id = 13)
    private final List<DataCollector> mPolymerizedDataCollectors;

    @aaby(id = 12)
    private final List<DataType> mPolymerizedDataTypes;

    @aaby(id = 1)
    private final long mStartTime;

    public static class Builder {
        private long aab;
        private long aaba;
        private List<DataType> aabb;
        private List<DataCollector> aabc;
        private int aabd;
        private long aabe;
        private DataCollector aabf;
        private int aabg;
        private boolean aabh;

        @Deprecated
        private List<Long> aabi;

        @Deprecated
        private List<Long> aabj;
        private List<DataType> aabk;
        private List<DataCollector> aabl;

        @Deprecated
        private final List<DeviceInfo> aabm;

        public Builder setTimeRange(long j, long j2, TimeUnit timeUnit) {
            this.aab = timeUnit.toMillis(j);
            this.aaba = timeUnit.toMillis(j2);
            Preconditions.checkState(this.aab > 1388505600000L, "Start time must be later than default start time: 20140101 00:00:000");
            Preconditions.checkState(this.aaba >= this.aab, "the start time must be less than the end time");
            return this;
        }

        public Builder setPolymerizeDataTypes(List<DataType> list) {
            this.aabk = list;
            return this;
        }

        public Builder setPageSize(int i) {
            Preconditions.checkArgument(i > 0, "PageSize is illegal");
            this.aabg = i;
            return this;
        }

        public Builder read(DataType dataType) {
            Preconditions.checkState(!this.aabk.contains(dataType), "For the one data type cannot be added repeatedly in the polymerization");
            Preconditions.checkNotNull(dataType, "Data type is null");
            if (!this.aabb.contains(dataType)) {
                this.aabb.add(dataType);
            }
            return this;
        }

        public Builder read(DataCollector dataCollector) {
            Preconditions.checkNotNull(dataCollector, "Data collector is null");
            Preconditions.checkArgument(!this.aabl.contains(dataCollector), "For the one data collector cannot be added repeatedly in the polymerization");
            if (!this.aabc.contains(dataCollector)) {
                this.aabc.add(dataCollector);
            }
            return this;
        }

        public Builder polymerize(DataType dataType, DataType dataType2) {
            Preconditions.checkNotNull(dataType, "Data type is null");
            Preconditions.checkState(!this.aabb.contains(dataType), "For the one data type cannot be added repeatedly in the polymerization");
            List<DataType> polymerizesForInput = DataType.getPolymerizesForInput(dataType);
            if (aabz.aaba(polymerizesForInput).booleanValue()) {
                Preconditions.checkArgument(polymerizesForInput.contains(dataType2), "Illegal output polymerize data type");
            }
            if (!this.aabk.contains(dataType)) {
                this.aabk.add(dataType);
            }
            return this;
        }

        public Builder polymerize(DataCollector dataCollector, DataType dataType) {
            Preconditions.checkNotNull(dataCollector, "Data collector is null");
            Preconditions.checkState(!this.aabc.contains(dataCollector), "For the one data collector cannot be added repeatedly in the polymerization");
            List<DataType> polymerizesForInput = DataType.getPolymerizesForInput(dataCollector.getDataType());
            if (aabz.aaba(polymerizesForInput).booleanValue()) {
                Preconditions.checkArgument(polymerizesForInput.contains(dataType), "Illegal output polymerize data type");
            }
            if (!this.aabl.contains(dataCollector)) {
                this.aabl.add(dataCollector);
            }
            return this;
        }

        public Builder groupByTime(int i, TimeUnit timeUnit) {
            Preconditions.checkArgument(this.aabd == 0, "Should not set another grouping strategy");
            Preconditions.checkArgument(i > 0, "Duration is illegal");
            this.aabd = 1;
            this.aabe = timeUnit.toMillis(i);
            return this;
        }

        @Deprecated
        public Builder groupByActivityType(DataCollector dataCollector, int i, TimeUnit timeUnit) {
            Preconditions.checkArgument(dataCollector != null, "Data collector is null");
            Preconditions.checkArgument(dataCollector.getDataType().equals(DataType.DT_CONTINUOUS_ACTIVITY_FRAGMENT), "Data collector is illegal");
            Preconditions.checkArgument(this.aabd == 0, "Should not set another grouping strategy");
            Preconditions.checkArgument(i > 0, "Duration is illegal");
            this.aabf = dataCollector;
            this.aabd = 3;
            this.aabe = timeUnit.toMillis(i);
            return this;
        }

        @Deprecated
        public Builder groupByActivityType(int i, TimeUnit timeUnit) {
            Preconditions.checkArgument(this.aabd == 0, "Should not set another grouping strategy");
            Preconditions.checkArgument(i > 0, "Duration is illegal");
            this.aabd = 3;
            this.aabe = timeUnit.toMillis(i);
            return this;
        }

        @Deprecated
        public Builder groupByActivitySampleSet(DataCollector dataCollector, int i, TimeUnit timeUnit) {
            Preconditions.checkArgument(dataCollector != null, "Data collector is null");
            Preconditions.checkArgument(dataCollector.getDataType().equals(DataType.DT_CONTINUOUS_ACTIVITY_FRAGMENT), "Data collector is illegal");
            Preconditions.checkArgument(this.aabd == 0, "Should not set another grouping strategy");
            Preconditions.checkArgument(i > 0, "Duration is illegal");
            this.aabd = 4;
            this.aabe = timeUnit.toMillis(i);
            this.aabf = dataCollector;
            return this;
        }

        @Deprecated
        public Builder groupByActivitySampleSet(int i, TimeUnit timeUnit) {
            Preconditions.checkArgument(this.aabd == 0, "Should not set another grouping strategy");
            Preconditions.checkArgument(i > 0, "Duration is illegal");
            this.aabd = 4;
            this.aabe = timeUnit.toMillis(i);
            return this;
        }

        @Deprecated
        public Builder groupByActivityRecord(int i, TimeUnit timeUnit) {
            Preconditions.checkArgument(this.aabd == 0, "Should not set another grouping strategy");
            Preconditions.checkArgument(i > 0, "Minimum duration should bigger than 0");
            this.aabd = 2;
            this.aabe = timeUnit.toMillis(i);
            return this;
        }

        public ReadOptions build() {
            aacs.aab(this.aab, this.aaba, TimeUnit.MILLISECONDS);
            Preconditions.checkState((this.aabc.isEmpty() && this.aabb.isEmpty() && this.aabl.isEmpty() && this.aabk.isEmpty()) ? false : true, "Must add at least one data collector");
            boolean z = this.aabl.isEmpty() && this.aabk.isEmpty();
            if (this.aabd == 0) {
                Preconditions.checkState(z, "Must set a valid grouping strategy while requesting polymerization");
            }
            if (!z) {
                Preconditions.checkState(this.aabd != 0, "Must set a valid grouping strategy while requesting polymerization");
            }
            return new ReadOptions(this);
        }

        public Builder allowRemoteInquiry() {
            this.aabh = true;
            return this;
        }

        public Builder(ReadOptions readOptions) {
            this.aabb = new ArrayList();
            this.aabc = new ArrayList();
            this.aabd = 0;
            this.aabe = 0L;
            this.aabg = 0;
            this.aabh = false;
            this.aabi = new ArrayList();
            this.aabj = new ArrayList();
            this.aabk = new ArrayList();
            this.aabl = new ArrayList();
            this.aabm = new ArrayList();
            this.aab = readOptions.mStartTime;
            this.aaba = readOptions.mEndTime;
            this.aabb = readOptions.mDataTypes;
            this.aabc = readOptions.mDataCollectors;
            this.aabd = readOptions.mGroupType;
            this.aabe = readOptions.mGroupPeriod;
            this.aabf = readOptions.mActivityDataCollector;
            this.aabg = readOptions.mPageSize;
            this.aabh = readOptions.mIsQueryFromNetwork;
            this.aabk = readOptions.mPolymerizedDataTypes;
            this.aabl = readOptions.mPolymerizedDataCollectors;
        }

        public Builder() {
            this.aabb = new ArrayList();
            this.aabc = new ArrayList();
            this.aabd = 0;
            this.aabe = 0L;
            this.aabg = 0;
            this.aabh = false;
            this.aabi = new ArrayList();
            this.aabj = new ArrayList();
            this.aabk = new ArrayList();
            this.aabl = new ArrayList();
            this.aabm = new ArrayList();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("ReadOptions{");
        if (!this.mDataTypes.isEmpty()) {
            Iterator<DataType> it = this.mDataTypes.iterator();
            while (it.hasNext()) {
                sb.append(it.next().toString());
                sb.append(" ");
            }
        }
        if (!this.mDataCollectors.isEmpty()) {
            Iterator<DataCollector> it2 = this.mDataCollectors.iterator();
            while (it2.hasNext()) {
                sb.append(it2.next().toString());
                sb.append(" ");
            }
        }
        if (this.mGroupType != 0) {
            sb.append(" group by ");
            sb.append(Group.getGroupTypeString(this.mGroupType));
            if (this.mGroupPeriod > 0) {
                sb.append(" >");
                sb.append(this.mGroupPeriod);
                sb.append("ms");
            }
            sb.append(": ");
        }
        if (!this.mPolymerizedDataTypes.isEmpty()) {
            Iterator<DataType> it3 = this.mPolymerizedDataTypes.iterator();
            while (it3.hasNext()) {
                sb.append(it3.next().toString());
                sb.append(" ");
            }
        }
        if (!this.mPolymerizedDataCollectors.isEmpty()) {
            Iterator<DataCollector> it4 = this.mPolymerizedDataCollectors.iterator();
            while (it4.hasNext()) {
                sb.append(it4.next().toString());
                sb.append(" ");
            }
        }
        sb.append(String.format(Locale.ENGLISH, "(%tF %tT - %tF %tT)", Long.valueOf(this.mStartTime), Long.valueOf(this.mStartTime), Long.valueOf(this.mEndTime), Long.valueOf(this.mEndTime)));
        if (this.mActivityDataCollector != null) {
            sb.append(" data collectors : ");
            sb.append(this.mActivityDataCollector.toString());
        }
        if (this.mIsQueryFromNetwork) {
            sb.append(" from HiHealth cloud");
        }
        sb.append("}");
        return sb.toString();
    }

    @Deprecated
    public void setQueryLocalDevice(boolean z) {
        this.mIsQueryLocalDevice = z;
    }

    @Deprecated
    public boolean isQueryLocalDevice() {
        return this.mIsQueryLocalDevice;
    }

    public boolean isQueryFromNetwork() {
        return this.mIsQueryFromNetwork;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.mGroupType), Long.valueOf(this.mStartTime), Long.valueOf(this.mEndTime));
    }

    public long getStartTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.mStartTime, TimeUnit.MILLISECONDS);
    }

    public List<DataType> getPolymerizedDataTypes() {
        return this.mPolymerizedDataTypes;
    }

    public List<DataCollector> getPolymerizedDataCollectors() {
        return this.mPolymerizedDataCollectors;
    }

    public int getPageSize() {
        return this.mPageSize;
    }

    public int getGroupType() {
        return this.mGroupType;
    }

    public long getGroupPeriod(TimeUnit timeUnit) {
        return timeUnit.convert(this.mGroupPeriod, TimeUnit.MILLISECONDS);
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

    @Deprecated
    public DataCollector getActivityDataCollector() {
        return this.mActivityDataCollector;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ReadOptions)) {
            return false;
        }
        ReadOptions readOptions = (ReadOptions) obj;
        return this.mStartTime == readOptions.mStartTime && this.mEndTime == readOptions.mEndTime && this.mDataTypes.equals(readOptions.mDataTypes) && this.mDataCollectors.equals(readOptions.mDataCollectors) && this.mGroupType == readOptions.mGroupType && this.mGroupPeriod == readOptions.mGroupPeriod && Objects.equal(this.mActivityDataCollector, readOptions.mActivityDataCollector) && this.mPageSize == readOptions.mPageSize && this.mIsQueryFromNetwork == readOptions.mIsQueryFromNetwork && this.mPolymerizedDataTypes.equals(readOptions.mPolymerizedDataTypes) && this.mPolymerizedDataCollectors.equals(readOptions.mPolymerizedDataCollectors) && Objects.equal(this.mFilteredDeviceInfos, readOptions.mFilteredDeviceInfos);
    }

    private ReadOptions(Builder builder) {
        this(builder.aab, builder.aaba, builder.aabb, builder.aabc, builder.aabd, builder.aabe, builder.aabf, builder.aabi, builder.aabj, builder.aabg, builder.aabh, builder.aabk, builder.aabl, builder.aabm);
    }

    @aabw
    private ReadOptions(@aabv(id = 1) long j, @aabv(id = 2) long j2, @aabv(id = 3) List<DataType> list, @aabv(id = 4) List<DataCollector> list2, @aabv(id = 5) int i, @aabv(id = 6) long j3, @aabv(id = 7) DataCollector dataCollector, @aabv(id = 8) List<Long> list3, @aabv(id = 9) List<Long> list4, @aabv(id = 10) int i2, @aabv(id = 11) boolean z, @aabv(id = 12) List<DataType> list5, @aabv(id = 13) List<DataCollector> list6, @aabv(id = 14) List<DeviceInfo> list7) {
        this.mStartTime = j;
        this.mEndTime = j2;
        this.mDataTypes = list;
        this.mDataCollectors = list2;
        this.mGroupType = i;
        this.mGroupPeriod = j3;
        this.mActivityDataCollector = dataCollector;
        List<Long> list8 = list3 == null ? Collections.EMPTY_LIST : list3;
        this.mGroupStartTimes = list8;
        List<Long> list9 = list4 == null ? Collections.EMPTY_LIST : list4;
        this.mGroupEndTimes = list9;
        this.mPageSize = i2;
        this.mIsQueryFromNetwork = z;
        this.mPolymerizedDataTypes = list5;
        this.mPolymerizedDataCollectors = list6;
        this.mFilteredDeviceInfos = list7 == null ? Collections.EMPTY_LIST : list7;
        Preconditions.checkArgument(list8.size() == list9.size(), "Start and end times are mismatch");
    }
}
