package com.huawei.hms.scankit.p;

import com.huawei.hms.ml.scan.HmsScan;

/* loaded from: classes9.dex */
public class y6 {
    public static HmsScan a(s6 s6Var) {
        HmsScan a2;
        if (s6Var == null) {
            return null;
        }
        return (!r3.f || (a2 = v5.a(s6Var)) == null) ? new HmsScan(s6Var.k(), t6.a(s6Var.c()), s6Var.k(), HmsScan.PURE_TEXT_FORM, s6Var.i(), t6.a(s6Var.j()), null, null).setZoomValue(s6Var.l()) : a2;
    }

    public static HmsScan[] a(s6[] s6VarArr) {
        if (s6VarArr == null || s6VarArr.length <= 0) {
            return new HmsScan[0];
        }
        HmsScan[] hmsScanArr = new HmsScan[s6VarArr.length];
        for (int i = 0; i < s6VarArr.length; i++) {
            hmsScanArr[i] = a(s6VarArr[i]);
        }
        return hmsScanArr;
    }
}
