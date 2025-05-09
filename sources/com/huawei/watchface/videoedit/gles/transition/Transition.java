package com.huawei.watchface.videoedit.gles.transition;

import com.huawei.watchface.videoedit.gles.glutils.TimeRange;

/* loaded from: classes9.dex */
public class Transition {
    private String mId;
    private boolean mIsOverLap = false;
    private String mName;
    private TimeRange mTimeRange;

    public Transition(String str, String str2, TimeRange timeRange) {
        this.mId = str;
        this.mName = str2;
        this.mTimeRange = timeRange;
    }

    public String getId() {
        return this.mId;
    }

    public String getName() {
        return this.mName;
    }

    public TimeRange getTimeRange() {
        return this.mTimeRange;
    }

    public boolean contains(long j) {
        return this.mTimeRange.contains(j);
    }

    public boolean isPreSegment(long j) {
        return this.mTimeRange.isPreSegment(j);
    }

    public String toString() {
        return "Id: " + this.mId + System.lineSeparator() + "name: " + this.mName + System.lineSeparator() + "isOverLap: " + this.mIsOverLap + System.lineSeparator() + "timeRange: " + this.mTimeRange;
    }

    public boolean isOverLap() {
        return this.mIsOverLap;
    }

    public Transition setOverLap(boolean z) {
        this.mIsOverLap = z;
        return this;
    }
}
