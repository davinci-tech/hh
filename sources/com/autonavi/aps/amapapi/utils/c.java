package com.autonavi.aps.amapapi.utils;

import java.util.Calendar;
import java.util.Date;

/* loaded from: classes2.dex */
public final class c {
    public static long a(long j, long j2, int i) {
        if (i <= 0) {
            return j;
        }
        try {
            return Math.abs(j - j2) > ((long) i) * 31536000000L ? a(j, j2) : j;
        } catch (Throwable unused) {
            return j;
        }
    }

    private static long a(long j, long j2) {
        long b = b(j2) + a(j);
        long abs = Math.abs(b - j2);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(b));
        int i = calendar.get(11);
        if (i == 23 && abs >= 82800000) {
            b -= 86400000;
        }
        return (i != 0 || abs < 82800000) ? b : b + 86400000;
    }

    private static long a(long j) {
        return j - b(j);
    }

    private static long b(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(j));
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }
}
