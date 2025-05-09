package com.huawei.watchface.videoedit.gles.glutils;

import com.huawei.watchface.videoedit.param.SourceTimeRange;
import com.huawei.watchface.videoedit.param.TargetTimeRange;
import java.util.Objects;

/* loaded from: classes9.dex */
public final class TimeRange implements Cloneable {
    private long mDuration;
    private long mStartTime;

    public TimeRange(long j, long j2) {
        this.mStartTime = j;
        this.mDuration = j2;
    }

    public TimeRange(TargetTimeRange targetTimeRange) {
        if (targetTimeRange != null) {
            this.mStartTime = targetTimeRange.getStart();
            this.mDuration = targetTimeRange.getDuration();
        }
    }

    public TimeRange(SourceTimeRange sourceTimeRange) {
        if (sourceTimeRange != null) {
            this.mStartTime = sourceTimeRange.getStart();
            this.mDuration = sourceTimeRange.getDuration();
        }
    }

    public boolean contains(long j) {
        long j2 = this.mStartTime;
        return j >= j2 && j < j2 + this.mDuration;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TimeRange)) {
            return false;
        }
        TimeRange timeRange = (TimeRange) obj;
        return this.mStartTime == timeRange.mStartTime && this.mDuration == timeRange.mDuration;
    }

    public int hashCode() {
        return Objects.hash(Long.valueOf(this.mStartTime), Long.valueOf(this.mDuration));
    }

    public float getProcess(long j) {
        if (!contains(j)) {
            return -1.0f;
        }
        long j2 = this.mDuration;
        if (j2 != 0) {
            return (j - this.mStartTime) / j2;
        }
        return -1.0f;
    }

    public long getStartTime() {
        return this.mStartTime;
    }

    public long getEndTime() {
        return this.mStartTime + this.mDuration;
    }

    public long getMidTime() {
        return this.mStartTime + (this.mDuration / 2);
    }

    public boolean isPreSegment(long j) {
        return j >= this.mStartTime && j < getMidTime();
    }

    public long getDuration() {
        return this.mDuration;
    }

    public String toString() {
        return "startTime: " + this.mStartTime + ", endTime: " + (this.mStartTime + this.mDuration) + System.lineSeparator();
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public TimeRange m963clone() {
        return new TimeRange(this.mStartTime, this.mDuration);
    }

    public void setStartTime(long j) {
        this.mStartTime = j;
    }

    public void moveStartTime(long j) {
        this.mDuration = (this.mStartTime + this.mDuration) - j;
        this.mStartTime = j;
    }

    public void setDuration(long j) {
        this.mDuration = j;
    }

    public void setEndtime(long j) {
        this.mDuration = j - this.mStartTime;
    }
}
