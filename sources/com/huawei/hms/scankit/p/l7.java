package com.huawei.hms.scankit.p;

import com.huawei.hms.scankit.aiscan.common.BarcodeFormat;
import java.util.Map;

/* loaded from: classes9.dex */
public final class l7 extends q7 {
    private final q7 h = new o2();

    @Override // com.huawei.hms.scankit.p.q7
    public boolean a(int i, int i2, r rVar) {
        return rVar.a(i2, (i2 - i) + i2, false, false);
    }

    @Override // com.huawei.hms.scankit.p.q7
    public s6 a(int i, r rVar, int[] iArr, Map<l1, ?> map) throws a {
        return a(this.h.a(i, rVar, iArr, map));
    }

    @Override // com.huawei.hms.scankit.p.q7, com.huawei.hms.scankit.p.g5
    public s6 a(int i, r rVar, Map<l1, ?> map) throws a {
        return a(this.h.a(i, rVar, map));
    }

    @Override // com.huawei.hms.scankit.p.g5, com.huawei.hms.scankit.p.o6
    public s6 a(p pVar, Map<l1, ?> map) throws a {
        return a(this.h.a(pVar, map));
    }

    @Override // com.huawei.hms.scankit.p.q7
    BarcodeFormat a() {
        return BarcodeFormat.UPC_A;
    }

    @Override // com.huawei.hms.scankit.p.q7
    protected int a(r rVar, int[] iArr, StringBuilder sb) throws a {
        return this.h.a(rVar, iArr, sb);
    }

    private static s6 a(s6 s6Var) throws a {
        String k = s6Var.k();
        if (k.charAt(0) == '0') {
            return new s6(k.substring(1), null, s6Var.j(), BarcodeFormat.UPC_A);
        }
        throw a.a();
    }

    @Override // com.huawei.hms.scankit.p.q7
    boolean a(int[] iArr, int[] iArr2) throws a {
        int i = iArr2[1];
        int i2 = iArr2[0];
        int i3 = iArr[1];
        int i4 = iArr[0];
        return Math.abs(((int) Math.round(((double) (i - i4)) / (((double) ((i - i2) + (i3 - i4))) / 6.0d))) + (-113)) <= 5;
    }
}
