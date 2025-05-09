package com.huawei.hms.scankit.p;

import com.huawei.hms.scankit.aiscan.common.BarcodeFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/* loaded from: classes9.dex */
public final class z4 extends g5 {
    private static final g5[] b = new g5[0];

    /* renamed from: a, reason: collision with root package name */
    private final g5[] f5933a;

    public z4(Map<l1, ?> map) {
        Collection collection = map == null ? null : (Collection) map.get(l1.POSSIBLE_FORMATS);
        ArrayList arrayList = new ArrayList();
        if (collection != null) {
            if (collection.contains(BarcodeFormat.EAN_13) || collection.contains(BarcodeFormat.UPC_A) || collection.contains(BarcodeFormat.EAN_8) || collection.contains(BarcodeFormat.UPC_E)) {
                arrayList.add(new b5(map));
            }
            if (collection.contains(BarcodeFormat.CODE_39)) {
                arrayList.add(new t0(false));
            }
            if (collection.contains(BarcodeFormat.CODE_93)) {
                arrayList.add(new v0());
            }
            if (collection.contains(BarcodeFormat.CODE_128)) {
                arrayList.add(new r0());
            }
            if (collection.contains(BarcodeFormat.ITF)) {
                arrayList.add(new j4());
            }
            if (collection.contains(BarcodeFormat.CODABAR)) {
                arrayList.add(new p0());
            }
        }
        if (arrayList.isEmpty()) {
            arrayList.add(new b5(map));
            arrayList.add(new t0());
            arrayList.add(new p0());
            arrayList.add(new v0());
            arrayList.add(new r0());
            arrayList.add(new j4());
        }
        this.f5933a = (g5[]) arrayList.toArray(b);
    }

    @Override // com.huawei.hms.scankit.p.g5
    public s6 a(int i, r rVar, Map<l1, ?> map) throws a {
        for (g5 g5Var : this.f5933a) {
            try {
                return g5Var.a(i, rVar, map);
            } catch (a unused) {
            }
        }
        throw a.a();
    }
}
