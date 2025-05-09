package com.huawei.health.manager.util;

import android.content.Context;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;

/* loaded from: classes.dex */
public class UnitUtilExt {
    private UnitUtilExt() {
    }

    public static String e(Context context, double d, int i, int i2) {
        String e = UnitUtil.e(d, i, i2);
        return LanguageUtil.m(context) ? e.replace(",", "") : e;
    }
}
