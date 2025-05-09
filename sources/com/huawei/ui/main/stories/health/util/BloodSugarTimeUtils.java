package com.huawei.ui.main.stories.health.util;

import java.util.Calendar;

/* loaded from: classes7.dex */
public final class BloodSugarTimeUtils {
    public static long c(int i, long j) {
        DefaultTime byTimePeriod = DefaultTime.getByTimePeriod(i);
        if (byTimePeriod == null) {
            return j;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        calendar.set(11, byTimePeriod.getHours());
        calendar.set(12, byTimePeriod.getMinute());
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    enum DefaultTime {
        TIME_MID_NIGHT(2015, 3, 0),
        TIME_BEFORE_BREAKFAST(2008, 6, 30),
        TIME_AFTER_BREAKFAST(2009, 9, 0),
        TIME_BEFORE_LUNCH(2010, 11, 30),
        TIME_AFTER_LUNCH(2011, 14, 0),
        TIME_BEFORE_DINNER(2012, 17, 30),
        TIME_AFTER_DINNER(2013, 20, 0),
        TIME_BEFORE_SLEEP(2014, 22, 0);

        private int mHours;
        private int mMinute;
        private int mTimePeriodCode;

        DefaultTime(int i, int i2, int i3) {
            this.mTimePeriodCode = i;
            this.mHours = i2;
            this.mMinute = i3;
        }

        static DefaultTime getByTimePeriod(int i) {
            for (DefaultTime defaultTime : values()) {
                if (defaultTime != null && defaultTime.getTimePeriodCode() == i) {
                    return defaultTime;
                }
            }
            return null;
        }

        int getTimePeriodCode() {
            return this.mTimePeriodCode;
        }

        int getHours() {
            return this.mHours;
        }

        int getMinute() {
            return this.mMinute;
        }
    }
}
