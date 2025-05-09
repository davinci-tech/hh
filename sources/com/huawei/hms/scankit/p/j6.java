package com.huawei.hms.scankit.p;

import com.huawei.hms.scankit.aiscan.common.BarcodeFormat;
import java.util.Map;

/* loaded from: classes9.dex */
public class j6 implements o6 {

    /* renamed from: a, reason: collision with root package name */
    private final v1 f5809a = new v1();

    public static void a(p4 p4Var) {
        int a2 = p4Var.a();
        if (a2 == p4Var.c() && a2 == 805) {
            r3.v[0] = true;
        }
    }

    public static void a(p4 p4Var, s6 s6Var) {
        boolean z;
        int a2 = p4Var.a();
        int c = p4Var.c();
        while (true) {
            if (r3.w.size() == 0) {
                z = false;
                break;
            }
            int intValue = r3.w.pop().intValue();
            if (intValue != 0 && a2 % intValue == 0) {
                z = true;
                break;
            }
        }
        if (a2 != c || !z || s6Var == null || s6Var.j() == null) {
            return;
        }
        float min = Math.min(Math.min(s6Var.j()[0].b(), s6Var.j()[1].b()), s6Var.j()[2].b());
        if ((Math.max(Math.max(s6Var.j()[0].b(), s6Var.j()[1].b()), s6Var.j()[2].b()) - min) * (Math.max(Math.max(s6Var.j()[0].c(), s6Var.j()[1].c()), s6Var.j()[2].c()) - Math.min(Math.min(s6Var.j()[0].c(), s6Var.j()[1].c()), s6Var.j()[2].c())) > a2 * c * 0.8d) {
            r3.v[1] = true;
        }
    }

    @Override // com.huawei.hms.scankit.p.o6
    public final s6 a(p pVar, Map<l1, ?> map) throws a {
        w1 w1Var;
        boolean z = true;
        r3.j++;
        try {
            j2 a2 = new g2(pVar.b()).a(map);
            boolean z2 = a(a2) > 0;
            try {
                w1Var = this.f5809a.a(a2.a(), map);
                z = false;
            } catch (Exception unused) {
                w1Var = null;
            }
            if (z && z2) {
                return new s6(null, null, a2.b(), BarcodeFormat.QR_CODE);
            }
            if (z) {
                throw a.a();
            }
            if (w1Var == null) {
                return null;
            }
            u6[] d = a2.d();
            if (w1Var.b() instanceof i6) {
                ((i6) w1Var.b()).a(d);
            }
            s6 s6Var = new s6(w1Var.d(), w1Var.c(), d, BarcodeFormat.QR_CODE);
            s6Var.b(a2.b());
            return s6Var;
        } catch (a unused2) {
            throw a.a();
        }
    }

    private int a(j2 j2Var) {
        r3.i = j2Var.c();
        s a2 = j2Var.a();
        int[] iArr = {3, a2.e() - 4, 3};
        int[] iArr2 = {3, 3, a2.c() - 4};
        int i = 0;
        for (int i2 = 0; i2 < 3; i2++) {
            if (a(a2, iArr[i2], iArr2[i2])) {
                i++;
            }
        }
        return i;
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [boolean, int] */
    private boolean a(s sVar, int i, int i2) {
        if (sVar == null || sVar.c() < 21 || sVar.e() < 21) {
            return false;
        }
        ?? b = sVar.b(i, i2);
        int i3 = b;
        if (sVar.b(i + 1, i2)) {
            i3 = b + 1;
        }
        int i4 = i3;
        if (!sVar.b(i + 2, i2)) {
            i4 = i3 + 1;
        }
        int i5 = i4;
        if (sVar.b(i + 3, i2)) {
            i5 = i4 + 1;
        }
        int i6 = i5;
        if (sVar.b(i - 1, i2)) {
            i6 = i5 + 1;
        }
        int i7 = i6;
        if (!sVar.b(i - 2, i2)) {
            i7 = i6 + 1;
        }
        int i8 = i7;
        if (sVar.b(i - 3, i2)) {
            i8 = i7 + 1;
        }
        int i9 = i8;
        if (sVar.b(i, i2 + 1)) {
            i9 = i8 + 1;
        }
        int i10 = i9;
        if (!sVar.b(i, i2 + 2)) {
            i10 = i9 + 1;
        }
        int i11 = i10;
        if (sVar.b(i, i2 + 3)) {
            i11 = i10 + 1;
        }
        int i12 = i11;
        if (sVar.b(i, i2 - 1)) {
            i12 = i11 + 1;
        }
        int i13 = i12;
        if (!sVar.b(i, i2 - 2)) {
            i13 = i12 + 1;
        }
        int i14 = i13;
        if (sVar.b(i, i2 - 3)) {
            i14 = i13 + 1;
        }
        return i14 > 10;
    }
}
