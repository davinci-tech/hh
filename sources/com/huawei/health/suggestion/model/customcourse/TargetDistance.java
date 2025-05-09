package com.huawei.health.suggestion.model.customcourse;

import android.content.Context;
import com.huawei.health.R;
import health.compact.a.UnitUtil;

/* loaded from: classes4.dex */
public class TargetDistance {
    public static final int DEFAULT_VALUE = 1;
    private static final int MAX_KM = 100;
    private static final int MAX_METER = 950;
    public static final int METER_INTERVAL = 50;

    public static String[] getKiloMeters(Context context) {
        String[] strArr = new String[101];
        for (int i = 0; i < 101; i++) {
            if (UnitUtil.h()) {
                strArr[i] = context.getResources().getQuantityString(R.plurals._2130903218_res_0x7f0300b2, i, UnitUtil.e(i, 1, 0));
            } else {
                strArr[i] = context.getResources().getQuantityString(R.plurals._2130903239_res_0x7f0300c7, i, UnitUtil.e(i, 1, 0));
            }
        }
        return strArr;
    }

    public static String[] getMeters(Context context) {
        String[] strArr = new String[20];
        for (int i = 0; i < 20; i++) {
            int i2 = i * 50;
            if (UnitUtil.h()) {
                strArr[i] = context.getResources().getQuantityString(R.plurals._2130903226_res_0x7f0300ba, i, Integer.valueOf(i2));
            } else {
                strArr[i] = context.getResources().getQuantityString(R.plurals._2130903307_res_0x7f03010b, i2, UnitUtil.e(i2, 1, 0));
            }
        }
        return strArr;
    }
}
