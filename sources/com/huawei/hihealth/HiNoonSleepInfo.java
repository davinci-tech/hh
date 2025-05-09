package com.huawei.hihealth;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: classes.dex */
public class HiNoonSleepInfo {

    @SerializedName("noonSleepTotalTime")
    private double b;

    @SerializedName("noonSleepTimeIntervalList")
    private List<TimeInterval> c;

    public void d(double d) {
        this.b = d;
    }

    public void e(List<TimeInterval> list) {
        this.c = list;
    }

    public static class TimeInterval {

        /* renamed from: a, reason: collision with root package name */
        @SerializedName("timeZone")
        private final String f4048a;

        @SerializedName("startTime")
        private final long b;

        @SerializedName("endTime")
        private final long e;

        public TimeInterval(long j, long j2, String str) {
            this.b = j;
            this.e = j2;
            this.f4048a = str;
        }
    }
}
