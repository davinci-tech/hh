package com.huawei.openalliance.ad.utils;

import java.math.BigDecimal;

/* loaded from: classes5.dex */
public class bm {
    public static boolean a(float f, float f2) {
        return Math.abs(f - f2) < 1.0E-6f;
    }

    public static Double a(Double d, int i, int i2) {
        if (d == null || Double.isInfinite(d.doubleValue()) || Double.isNaN(d.doubleValue())) {
            return null;
        }
        return Double.valueOf(new BigDecimal(d.doubleValue()).setScale(i, i2).doubleValue());
    }
}
