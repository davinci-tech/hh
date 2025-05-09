package com.huawei.hms.scankit.p;

import com.huawei.hms.ml.scan.HmsScan;
import com.huawei.hms.ml.scan.HmsScanBase;
import java.util.regex.Pattern;

/* loaded from: classes9.dex */
public final class g6 extends t6 {
    private static final Pattern b = Pattern.compile("\\d+");

    protected static boolean a(CharSequence charSequence, int i) {
        return charSequence != null && i > 0 && i == charSequence.length() && b.matcher(charSequence).matches();
    }

    @Override // com.huawei.hms.scankit.p.t6
    public HmsScan b(s6 s6Var) {
        int a2 = t6.a(s6Var.c());
        if (a2 != HmsScanBase.EAN13_SCAN_TYPE && a2 != HmsScanBase.EAN8_SCAN_TYPE && a2 != HmsScanBase.UPCCODE_A_SCAN_TYPE && a2 != HmsScanBase.UPCCODE_E_SCAN_TYPE) {
            return null;
        }
        String a3 = t6.a(s6Var);
        if (a(a3, a3.length())) {
            return new HmsScan(a3, t6.a(s6Var.c()), a3, HmsScan.ARTICLE_NUMBER_FORM, s6Var.i(), t6.a(s6Var.j()), null, null);
        }
        return null;
    }
}
