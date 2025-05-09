package com.huawei.pluginachievement.report.constant;

import com.huawei.hms.hihealth.HiHealthStatusCodes;

/* loaded from: classes6.dex */
public enum EnumAnnual {
    JAN(1),
    FEB(2),
    MAR(3),
    APR(4),
    MAY(5),
    JUN(6),
    JUL(7),
    AUG(8),
    SEPT(9),
    OCT(10),
    NOV(11),
    DEC(12),
    AM_5(5),
    AM_9(9),
    PM_17(17),
    PM_20(20),
    DATA_SYNC_FAIL(-1),
    DATA_NOT_ENOUGH(HiHealthStatusCodes.APP_MISMATCH_ERROR),
    DATA_SUCCESS(0),
    HORSE(1),
    LION(2),
    OWL(3),
    CHICKEN(4),
    DOG(5),
    KOALA(6),
    DOLPHIN(7),
    CAT(8),
    RABBIT(9),
    LARK(10),
    ZEBRAFISH(11);

    private int value;

    EnumAnnual(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
