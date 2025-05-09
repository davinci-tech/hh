package com.huawei.android.airsharing.util;

import android.os.SystemClock;

/* loaded from: classes8.dex */
public abstract class HwConditionLock {
    private static final int DEFAULT_INTERVAL = 50;

    public abstract boolean checkCondition();

    public boolean waitCondition(int i) {
        while (!checkCondition()) {
            SystemClock.sleep(50L);
            i--;
            if (i <= 0) {
                break;
            }
        }
        return checkCondition();
    }

    public boolean waitConditionWithInterval(int i, int i2) {
        while (!checkCondition()) {
            SystemClock.sleep(i2);
            i--;
            if (i <= 0) {
                break;
            }
        }
        return checkCondition();
    }
}
