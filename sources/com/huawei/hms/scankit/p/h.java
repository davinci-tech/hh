package com.huawei.hms.scankit.p;

import com.huawei.hms.scankit.aiscan.common.BarcodeFormat;
import java.util.Map;

/* loaded from: classes9.dex */
public final class h implements o6 {
    @Override // com.huawei.hms.scankit.p.o6
    public s6 a(p pVar, Map<l1, ?> map) throws a {
        u6[] u6VarArr;
        v6 v6Var;
        c2 c2Var = new c2(pVar.b());
        w1 w1Var = null;
        try {
            g a2 = c2Var.a(false);
            u6VarArr = a2.d();
            try {
                w1 a3 = new s1().a(a2, map);
                e = null;
                w1Var = a3;
            } catch (a e) {
                e = e;
            }
        } catch (a e2) {
            e = e2;
            u6VarArr = null;
        }
        if (w1Var == null) {
            try {
                g a4 = c2Var.a(true);
                u6VarArr = a4.d();
                w1Var = new s1().a(a4, map);
            } catch (a e3) {
                if (u6VarArr != null && !r3.c) {
                    return new s6(null, null, 0, (u6[]) u6VarArr.clone(), BarcodeFormat.AZTEC, System.currentTimeMillis());
                }
                if (e != null) {
                    throw e;
                }
                throw e3;
            }
        }
        u6[] u6VarArr2 = u6VarArr;
        if (map != null && (v6Var = (v6) map.get(l1.NEED_RESULT_POINT_CALLBACK)) != null && u6VarArr2 != null) {
            for (u6 u6Var : u6VarArr2) {
                v6Var.a(u6Var);
            }
        }
        return new s6(w1Var.d(), w1Var.c(), w1Var.a(), u6VarArr2, BarcodeFormat.AZTEC, System.currentTimeMillis());
    }
}
