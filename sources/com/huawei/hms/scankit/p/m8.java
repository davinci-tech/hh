package com.huawei.hms.scankit.p;

import com.huawei.hms.scankit.aiscan.common.BarcodeFormat;
import java.util.EnumMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class m8 implements o6 {

    /* renamed from: a, reason: collision with root package name */
    private Map<l1, Object> f5834a = new EnumMap(l1.class);

    @Override // com.huawei.hms.scankit.p.o6
    public s6 a(p pVar, Map<l1, ?> map) throws a {
        f3[] a2 = new h2(pVar.b()).a(map);
        int length = a2.length;
        if (length > 10) {
            return null;
        }
        int i = 3;
        char c = 0;
        if (length != 3) {
            int i2 = 0;
            while (i2 <= length - 3) {
                int i3 = i2 + 1;
                int i4 = i3;
                while (i4 <= length - 2) {
                    int i5 = i4 + 1;
                    int i6 = i5;
                    while (i6 <= length - 1) {
                        f3[] f3VarArr = new f3[i];
                        f3VarArr[c] = a2[i2];
                        f3VarArr[1] = a2[i4];
                        f3VarArr[2] = a2[i6];
                        int[] iArr = new int[i];
                        iArr[c] = i2;
                        iArr[1] = i4;
                        iArr[2] = i6;
                        u6.a(f3VarArr);
                        if (h2.a(f3VarArr[c], f3VarArr[1], f3VarArr[2]) && !h2.a(f3VarArr, a2, iArr)) {
                            return a(a2[i2], a2[i4], a2[i6]);
                        }
                        i6++;
                        i = 3;
                        c = 0;
                    }
                    i4 = i5;
                }
                i2 = i3;
            }
        } else if (h2.a(a2[0], a2[1], a2[2])) {
            return a(a2[0], a2[1], a2[2]);
        }
        return null;
    }

    private s6 a(f3 f3Var, f3 f3Var2, f3 f3Var3) {
        return new s6("WXCODE_TEXT", null, new u6[]{new u6(f3Var.b(), f3Var.c()), new u6(f3Var2.b(), f3Var2.c()), new u6(f3Var3.b(), f3Var3.c())}, BarcodeFormat.WXCODE);
    }
}
