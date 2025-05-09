package com.huawei.health.sport.utils;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.huawei.openalliance.ad.constant.ConfigMapDefValues;
import defpackage.jec;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/* loaded from: classes4.dex */
public class FitnessDataQueryDefine {

    public enum FitnessSleepTotalDataId {
        FITNESS_UNSPECIFIC_SLEEP_TIME,
        FITNESS_TOTAL_SLEEP_TIME,
        FITNESS_DEEP_SLEEP_TIME,
        FITNESS_SHALLOW_SLEEP_TIME,
        FITNESS_WAKEUP_DURATION,
        FITNESS_WAKEUP_TIME
    }

    public enum FitnessQueryId {
        FITNESS_TYPE_DEFAULT_HISTOGRAM(0),
        FITNESS_TYPE_DAY_HISTOGRAM(1),
        FITNESS_TYPE_WEEK_HISTOGRAM(2),
        FITNESS_TYPE_MONTH_HISTOGRAM(3),
        FITNESS_TYPE_YEAR_HISTOGRAM(4),
        FITNESS_TYPE_DAY_HISTOGRAM_STEP(5),
        FITNESS_TYPE_UP_SPORT_DETAIL(10),
        FITNESS_TYPE_UP_SLEEP_DETAIL(11),
        FITNESS_TYPE_MYFIT_WALK_DETAIL(12),
        FITNESS_TYPE_MYFIT_CALORIE_DETAIL(13),
        FITNESS_TYPE_DAY_STATISTIC(101),
        FITNESS_TYPE_WEEK_STATISTIC(102),
        FITNESS_TYPE_MONTH_STATISTIC(103),
        FITNESS_TYPE_YEAR_STATISTIC(104),
        FITNESS_TYPE_DAY_STATISTIC_DETAIL(120),
        FITNESS_TYPE_WEEK_STATISTIC_DETAIL(121),
        FITNESS_TYPE_MONTH_STATISTIC_DETAIL(122),
        FITNESS_TYPE_YEAR_STATISTIC_DETAIL(123),
        FITNESS_TYPE_SLEEP_DAY_HISTOGRAM(500),
        FITNESS_TYPE_SLEEP_WEEK_HISTOGRAM(501),
        FITNESS_TYPE_SLEEP_MONTH_HISTOGRAM(TypedValues.PositionType.TYPE_DRAWPATH),
        FITNESS_TYPE_SLEEP_YEAR_HISTOGRAM(503),
        FITNESS_TYPE_SLEEP_DAY_STATISTIC(TypedValues.PositionType.TYPE_POSITION_TYPE),
        FITNESS_TYPE_SLEEP_WEEK_STATISTIC(511),
        FITNESS_TYPE_SLEEP_MONTH_STATISTIC(512),
        FITNESS_TYPE_SLEEP_YEAR_STATISTIC(513),
        FITNESS_TYPE_SLEEP_DAY_STATISTIC_DETAIL(520),
        FITNESS_TYPE_SLEEP_WEEK_STATISTIC_DETAIL(521),
        FITNESS_TYPE_SLEEP_MONTH_STATISTIC_DETAIL(522),
        FITNESS_TYPE_SLEEP_YEAR_STATISTIC_DETAIL(523),
        FITNESS_TYPE_HEARRATE_DAY_LAST_VALUE(600),
        FITNESS_TYPE_HEARRATE_WEEK_LAST_VALUE(601),
        FITNESS_TYPE_HEARRATE_MONTH_LAST_VALUE(602),
        FITNESS_TYPE_HEARRATE_YEAR_LAST_VALUE(TypedValues.MotionType.TYPE_EASING);

        private int mQueryId;

        FitnessQueryId(int i) {
            this.mQueryId = i;
        }

        public int getFitnessQueryId() {
            return this.mQueryId;
        }
    }

    public static int a(long j, FitnessQueryId fitnessQueryId) {
        switch (AnonymousClass5.c[fitnessQueryId.ordinal()]) {
            case 1:
                return 60;
            case 2:
                return 30;
            case 3:
            case 4:
            case 5:
            case 6:
            case 13:
            case 14:
                return ConfigMapDefValues.DEFAULT_APPLIST_INTERVAL;
            case 7:
                return 43800;
            case 8:
                return ConfigMapDefValues.DEFAULT_APPLIST_INTERVAL * e(j);
            case 9:
            case 10:
            case 11:
            case 12:
                return 1;
            case 15:
            case 16:
            case 17:
                return 10080;
            default:
                return e(j, fitnessQueryId);
        }
    }

    private static int e(long j, FitnessQueryId fitnessQueryId) {
        switch (fitnessQueryId) {
            case FITNESS_TYPE_MONTH_STATISTIC:
            case FITNESS_TYPE_MONTH_STATISTIC_DETAIL:
            case FITNESS_TYPE_SLEEP_MONTH_STATISTIC:
                return 43200;
            case FITNESS_TYPE_YEAR_STATISTIC:
            case FITNESS_TYPE_YEAR_STATISTIC_DETAIL:
            case FITNESS_TYPE_SLEEP_YEAR_STATISTIC:
            case FITNESS_TYPE_SLEEP_YEAR_STATISTIC_DETAIL:
                Date date = new Date(j * 1000);
                int i = 0;
                for (int i2 = 0; i2 < 12; i2++) {
                    i += e(jec.n(jec.c(date, i2))) * ConfigMapDefValues.DEFAULT_APPLIST_INTERVAL;
                }
                return i;
            case FITNESS_TYPE_SLEEP_DAY_HISTOGRAM:
            case FITNESS_TYPE_SLEEP_DAY_STATISTIC_DETAIL:
                return 1;
            case FITNESS_TYPE_SLEEP_DAY_STATISTIC:
                return ConfigMapDefValues.DEFAULT_APPLIST_INTERVAL;
            default:
                return 0;
        }
    }

    public static int e(FitnessQueryId fitnessQueryId) {
        int i = AnonymousClass5.c[fitnessQueryId.ordinal()];
        switch (i) {
            case 1:
                return 24;
            case 2:
                return 48;
            case 3:
            case 4:
                return 14;
            case 5:
            case 6:
                return 60;
            case 7:
                return 12;
            case 8:
                return 365;
            default:
                if (i != 25 && i != 27) {
                    return 1;
                }
                break;
            case 9:
            case 10:
            case 11:
            case 12:
                return ConfigMapDefValues.DEFAULT_APPLIST_INTERVAL;
        }
    }

    public static int a(FitnessQueryId fitnessQueryId) {
        int i = AnonymousClass5.c[fitnessQueryId.ordinal()];
        switch (i) {
            case 1:
                return 24;
            case 2:
                return 48;
            case 3:
            case 4:
                return 7;
            case 5:
            case 6:
                return 30;
            case 7:
            case 8:
                return 12;
            default:
                if (i != 25 && i != 27) {
                    return 1;
                }
                break;
            case 9:
            case 10:
            case 11:
            case 12:
                return ConfigMapDefValues.DEFAULT_APPLIST_INTERVAL;
        }
    }

    public static int e(long j) {
        List asList = Arrays.asList("1", "3", "5", "7", "8", "10", "12");
        List asList2 = Arrays.asList("4", "6", "9", "11");
        Date date = new Date(j * 1000);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (asList.contains(String.valueOf(calendar.get(2) + 1))) {
            return 31;
        }
        if (asList2.contains(String.valueOf(calendar.get(2) + 1))) {
            return 30;
        }
        int year = date.getYear();
        return ((year % 4 != 0 || year % 100 == 0) && year % 400 != 0) ? 28 : 29;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0017 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0019 A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int c(com.huawei.health.sport.utils.FitnessDataQueryDefine.FitnessQueryId r1) {
        /*
            int[] r0 = com.huawei.health.sport.utils.FitnessDataQueryDefine.AnonymousClass5.c
            int r1 = r1.ordinal()
            r1 = r0[r1]
            r0 = 17
            if (r1 == r0) goto L1b
            r0 = 20
            if (r1 == r0) goto L1b
            switch(r1) {
                case 1: goto L19;
                case 2: goto L19;
                case 3: goto L1b;
                case 4: goto L1b;
                case 5: goto L1b;
                case 6: goto L1b;
                case 7: goto L17;
                case 8: goto L1b;
                default: goto L13;
            }
        L13:
            switch(r1) {
                case 23: goto L17;
                case 24: goto L17;
                case 25: goto L16;
                case 26: goto L1b;
                case 27: goto L1b;
                case 28: goto L1b;
                case 29: goto L1b;
                default: goto L16;
            }
        L16:
            goto L19
        L17:
            r1 = 5
            goto L1c
        L19:
            r1 = 1
            goto L1c
        L1b:
            r1 = 3
        L1c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.sport.utils.FitnessDataQueryDefine.c(com.huawei.health.sport.utils.FitnessDataQueryDefine$FitnessQueryId):int");
    }
}
