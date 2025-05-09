package com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype;

import defpackage.jdy;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class TrackAltitudeData implements Serializable {
    private static final long serialVersionUID = 8239372811980674006L;
    private double altitude;
    private long time;

    public TrackAltitudeData() {
    }

    public TrackAltitudeData(long j, double d) {
        this.time = j;
        this.altitude = d;
    }

    public long getTime() {
        return ((Long) jdy.d(Long.valueOf(this.time))).longValue();
    }

    public void setTime(long j) {
        this.time = ((Long) jdy.d(Long.valueOf(j))).longValue();
    }

    public double getAltitude() {
        return ((Double) jdy.d(Double.valueOf(this.altitude))).doubleValue();
    }

    public void setAltitude(double d) {
        this.altitude = ((Double) jdy.d(Double.valueOf(d))).doubleValue();
    }

    public String toString() {
        return "TrackAltitudeData [time=" + this.time + ", altitude=" + this.altitude + "]";
    }
}
