package com.huawei.ui.homehealth.runcard.operation.recommendalgo.model;

/* loaded from: classes9.dex */
public class SportLevelConstants {
    public static final int DAY_TO_MSEC = 86400000;
    public static final long MONTH_INTERVAL = 29;

    public static class Level {
        public static final int LEVEL_AVERAGE = 3;
        public static final int LEVEL_FAIR = 2;
        public static final int LEVEL_GOOD = 4;
        public static final int LEVEL_NONE = -1;
        public static final int LEVEL_POOR = 1;
        public static final int LEVEL_TREADMILL = 6;
        public static final int LEVEL_VERY_GOOD = 5;
        public static final int LEVEL_VERY_POOR = 0;
    }

    public static class Reason {
        public static final int REASON_BY_AVG_PACE = 7;
        public static final int REASON_BY_SHORT_AVG_DISTANCE = 6;
        public static final int REASON_BY_VO2MAX = 5;
        public static final int REASON_DEFAULT = 0;
        public static final int REASON_NO_HEARTRATE_DEVICE = 8;
        public static final int REASON_NO_SPORT_HISTORY = 4;
        public static final int REASON_NULL_USER_INFO = 1;
        public static final int REASON_OUTOF_AGE = 3;
        public static final int REASON_UNKOWN_GENDER = 2;
    }

    private SportLevelConstants() {
    }
}
