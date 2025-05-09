package com.huawei.hms.kit.awareness.barrier;

import com.huawei.hms.kit.awareness.barrier.internal.a.f.a;
import com.huawei.hms.kit.awareness.barrier.internal.a.f.b;
import com.huawei.hms.kit.awareness.barrier.internal.a.f.c;
import com.huawei.hms.kit.awareness.barrier.internal.a.f.d;
import com.huawei.hms.kit.awareness.barrier.internal.a.f.e;
import java.util.TimeZone;

/* loaded from: classes9.dex */
public final class TimeBarrier {
    public static final int FRIDAY_CODE = 6;
    public static final int MONDAY_CODE = 2;
    public static final int SATURDAY_CODE = 7;
    public static final int SUNDAY_CODE = 1;
    public static final int SUNRISE_CODE = 0;
    public static final int SUNSET_CODE = 1;
    public static final int THURSDAY_CODE = 5;
    public static final int TIME_CATEGORY_AFTERNOON = 2;
    public static final int TIME_CATEGORY_EVENING = 3;
    public static final int TIME_CATEGORY_HOLIDAY = 5;
    public static final int TIME_CATEGORY_MORNING = 1;
    public static final int TIME_CATEGORY_NIGHT = 4;
    public static final int TIME_CATEGORY_NOT_HOLIDAY = 8;
    public static final int TIME_CATEGORY_WEEKDAY = 6;
    public static final int TIME_CATEGORY_WEEKEND = 7;
    public static final int TUESDAY_CODE = 3;
    public static final int WEDNESDAY_CODE = 4;

    public static AwarenessBarrier inTimeCategory(int i) {
        e a2 = e.a(i);
        if (a2.j()) {
            return a2;
        }
        throw new IllegalArgumentException();
    }

    public static AwarenessBarrier inSunriseOrSunsetPeriod(int i, long j, long j2) {
        d a2 = d.a(i, j, j2);
        if (a2.j()) {
            return a2;
        }
        throw new IllegalArgumentException();
    }

    public static AwarenessBarrier duringTimePeriod(long j, long j2) {
        c a2 = c.a(j, j2);
        if (a2.j()) {
            return a2;
        }
        throw new IllegalArgumentException();
    }

    public static AwarenessBarrier duringPeriodOfWeek(int i, TimeZone timeZone, long j, long j2) {
        b a2 = b.a(i, timeZone, j, j2);
        if (a2.j()) {
            return a2;
        }
        throw new IllegalArgumentException();
    }

    public static AwarenessBarrier duringPeriodOfDay(TimeZone timeZone, long j, long j2) {
        a a2 = a.a(timeZone, j, j2);
        if (a2.j()) {
            return a2;
        }
        throw new IllegalArgumentException();
    }

    private TimeBarrier() {
    }
}
