package com.huawei.health.algorithm.model;

/* loaded from: classes8.dex */
public class SleepMonitorQueryByTime {
    private long endTime;
    private long startTime;

    public long getStartTime() {
        return this.startTime;
    }

    public long getEndTime() {
        return this.endTime;
    }

    public String toString() {
        return "SleepMonitorQueryByTime{startTime=" + this.startTime + ", endTime=" + this.endTime + '}';
    }
}
