package com.huawei.hms.hihealth.options;

import android.os.Parcelable;
import com.huawei.hms.common.internal.Objects;
import com.huawei.hms.common.internal.Preconditions;
import com.huawei.hms.health.aabq;
import com.huawei.hms.health.aabv;
import com.huawei.hms.health.aabw;
import com.huawei.hms.health.aaby;
import com.huawei.hms.hihealth.data.SamplePoint;
import com.huawei.hms.hihealth.data.SampleSet;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public class UpdateOptions extends aabq {
    public static final Parcelable.Creator<UpdateOptions> CREATOR = new aabq.aab(UpdateOptions.class);

    @aaby(id = 3)
    private final long mEndTime;

    @aaby(id = 1)
    private final SampleSet mSampleSet;

    @aaby(id = 2)
    private final long mStartTime;

    public String toString() {
        return Objects.toStringHelper(this).add("sampleSet", this.mSampleSet).add("startTime", Long.valueOf(this.mStartTime)).add("endTime", Long.valueOf(this.mEndTime)).toString();
    }

    public int hashCode() {
        return Objects.hashCode(this.mSampleSet, Long.valueOf(this.mStartTime), Long.valueOf(this.mEndTime));
    }

    public long getStartTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.mStartTime, TimeUnit.MILLISECONDS);
    }

    public final long getStartTime() {
        return this.mStartTime;
    }

    public static class Builder {
        private SampleSet aab = null;
        private long aaba = 0;
        private long aabb = 0;

        public Builder setTimeInterval(long j, long j2, TimeUnit timeUnit) {
            Preconditions.checkArgument(j > 0, "Start time is illegal");
            Preconditions.checkArgument(j2 >= j, "End time is illegal");
            this.aaba = timeUnit.toMillis(j);
            this.aabb = timeUnit.toMillis(j2);
            Preconditions.checkState(this.aaba > 1388505600000L, "Start time must be later than default start time: 20140101 00:00:000");
            Preconditions.checkState(this.aabb >= this.aaba, "the start time must be less than the end time");
            return this;
        }

        public Builder setSampleSet(SampleSet sampleSet) {
            Preconditions.checkNotNull(sampleSet, "Cannot set a null sample set");
            this.aab = sampleSet;
            return this;
        }

        public UpdateOptions build() {
            Preconditions.checkNotNull(this.aab, "SampleSet should not be null");
            Preconditions.checkState(this.aaba != 0, "StartTime should not be zero");
            Preconditions.checkState(this.aabb != 0, "EndTime should not be zero");
            for (SamplePoint samplePoint : this.aab.getSamplePoints()) {
                long startTime = samplePoint.getStartTime(TimeUnit.MILLISECONDS);
                long endTime = samplePoint.getEndTime(TimeUnit.MILLISECONDS);
                Preconditions.checkState(startTime <= endTime, "SamplePoint's start time and end time should not be outside the UpdateOptions time range");
                Preconditions.checkState((startTime == 0 || startTime >= this.aaba) && (startTime == 0 || startTime <= this.aabb), "SamplePoint's start time and end time should not be outside the UpdateOptions time range");
                Preconditions.checkState(endTime <= this.aabb && endTime >= this.aaba, "SamplePoint's start time and end time should not be outside the UpdateOptions time range");
            }
            return new UpdateOptions(this);
        }
    }

    public SampleSet getSampleSet() {
        return this.mSampleSet;
    }

    public long getEndTime(TimeUnit timeUnit) {
        return timeUnit.convert(this.mEndTime, TimeUnit.MILLISECONDS);
    }

    public final long getEndTime() {
        return this.mEndTime;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof UpdateOptions)) {
            return false;
        }
        UpdateOptions updateOptions = (UpdateOptions) obj;
        return Objects.equal(this.mSampleSet, updateOptions.mSampleSet) && this.mStartTime == updateOptions.mStartTime && this.mEndTime == updateOptions.mEndTime;
    }

    private UpdateOptions(Builder builder) {
        this(builder.aab, builder.aaba, builder.aabb);
    }

    @aabw
    private UpdateOptions(@aabv(id = 1) SampleSet sampleSet, @aabv(id = 2) long j, @aabv(id = 3) long j2) {
        this.mSampleSet = sampleSet;
        this.mStartTime = j;
        this.mEndTime = j2;
    }
}
