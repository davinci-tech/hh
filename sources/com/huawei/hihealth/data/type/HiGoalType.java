package com.huawei.hihealth.data.type;

/* loaded from: classes.dex */
public class HiGoalType {

    public static class GoalPeriod {
    }

    public static int c(int i, int i2) {
        if (i2 == 1) {
            return i;
        }
        if (i2 == 2) {
            return i - 10;
        }
        if (i2 == 3) {
            return i - 20;
        }
        if (i2 == 4) {
            return i - 30;
        }
        if (i2 != 5) {
            return 0;
        }
        return i - 40;
    }

    public static int e(int i) {
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return 1;
            default:
                switch (i) {
                    case 11:
                    case 12:
                    case 13:
                    case 14:
                    case 15:
                    case 16:
                        return 2;
                    default:
                        switch (i) {
                            case 21:
                            case 22:
                            case 23:
                            case 24:
                            case 25:
                            case 26:
                                return 3;
                            default:
                                switch (i) {
                                    case 31:
                                    case 32:
                                    case 33:
                                    case 34:
                                    case 35:
                                    case 36:
                                        return 4;
                                    default:
                                        switch (i) {
                                            case 41:
                                            case 42:
                                            case 43:
                                            case 44:
                                            case 45:
                                            case 46:
                                                return 5;
                                            default:
                                                return -1;
                                        }
                                }
                        }
                }
        }
    }

    public static int e(int i, int i2) {
        if (i2 == 1) {
            return i;
        }
        if (i2 == 2) {
            return i + 10;
        }
        if (i2 == 3) {
            return i + 20;
        }
        if (i2 == 4) {
            return i + 30;
        }
        if (i2 != 5) {
            return 0;
        }
        return i + 40;
    }
}
