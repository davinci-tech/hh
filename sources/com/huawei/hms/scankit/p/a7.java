package com.huawei.hms.scankit.p;

import com.huawei.hihealth.HiDataFilter;

/* loaded from: classes4.dex */
final class a7 extends i7 {
    private final short c;
    private final short d;

    a7(i7 i7Var, int i, int i2) {
        super(i7Var);
        this.c = (short) i;
        this.d = (short) i2;
    }

    @Override // com.huawei.hms.scankit.p.i7
    void a(r rVar, byte[] bArr) {
        rVar.a(this.c, this.d);
    }

    public String toString() {
        short s = this.c;
        int i = 1 << this.d;
        return HiDataFilter.DataFilterExpression.LESS_THAN + Integer.toBinaryString((s & (i - 1)) | i | (1 << this.d)).substring(1) + '>';
    }
}
