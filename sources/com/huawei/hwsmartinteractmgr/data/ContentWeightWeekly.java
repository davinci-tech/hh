package com.huawei.hwsmartinteractmgr.data;

import java.util.Arrays;

/* loaded from: classes5.dex */
public class ContentWeightWeekly {
    private static final int TIME_PERIOD_SIZE = 2;
    private double changeData;
    private long people;
    private long[] timeperiod = new long[2];
    private int upDown;

    public ContentWeightWeekly(int i, double d, long j) {
        this.upDown = i;
        this.changeData = d;
        this.people = j;
    }

    public long getTimePeriod(int i) {
        if (i < 0 || i >= 2) {
            return 0L;
        }
        return this.timeperiod[i];
    }

    public void setTimePeriod(int i, long j) {
        if (i < 0 || i >= 2) {
            return;
        }
        this.timeperiod[i] = j;
    }

    public int getUpDown() {
        return this.upDown;
    }

    public void setUpDown(int i) {
        this.upDown = i;
    }

    public double getChangeData() {
        return this.changeData;
    }

    public void setChangeData(double d) {
        this.changeData = d;
    }

    public long getPeople() {
        return this.people;
    }

    public void setPeople(int i) {
        this.people = i;
    }

    public String toString() {
        return "ContentWeightWeekly{timeperiod='" + Arrays.toString(this.timeperiod) + "', upDown=" + this.upDown + "', people=" + this.people + '}';
    }
}
