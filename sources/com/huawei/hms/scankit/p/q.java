package com.huawei.hms.scankit.p;

import com.huawei.hihealth.HiDataFilter;

/* loaded from: classes4.dex */
final class q extends i7 {
    private final short c;
    private final short d;

    q(i7 i7Var, int i, int i2) {
        super(i7Var);
        this.c = (short) i;
        this.d = (short) i2;
    }

    @Override // com.huawei.hms.scankit.p.i7
    public void a(r rVar, byte[] bArr) {
        int i = 0;
        while (true) {
            short s = this.d;
            if (i >= s) {
                return;
            }
            if (i == 0 || (i == 31 && s <= 62)) {
                rVar.a(31, 5);
                short s2 = this.d;
                if (s2 > 62) {
                    rVar.a(s2 - 31, 16);
                } else if (i == 0) {
                    rVar.a(Math.min((int) s2, 31), 5);
                } else {
                    rVar.a(s2 - 31, 5);
                }
            }
            rVar.a(bArr[this.c + i], 8);
            i++;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(HiDataFilter.DataFilterExpression.LESS_THAN);
        sb.append((int) this.c);
        sb.append("::");
        sb.append((this.c + this.d) - 1);
        sb.append('>');
        return sb.toString();
    }
}
