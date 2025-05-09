package com.huawei.hms.scankit.p;

import com.huawei.hms.scankit.aiscan.common.BarcodeFormat;
import com.huawei.hms.scankit.util.LoadOpencvJNIUtil;
import java.util.Collection;
import java.util.Map;
import java.util.Vector;

/* loaded from: classes9.dex */
public class k7 {
    public static byte[] a(p pVar, Map<l1, Object> map, s6 s6Var, int[] iArr, double[] dArr) throws a {
        if (pVar == null) {
            return null;
        }
        Collection collection = map != null ? (Collection) map.get(l1.POSSIBLE_FORMATS) : null;
        if ((collection != null && !collection.contains(BarcodeFormat.QR_CODE) && !collection.contains(BarcodeFormat.PDF_417)) || s6Var == null || s6Var.k() != null) {
            iArr[0] = pVar.e();
            iArr[1] = pVar.c();
            return pVar.d();
        }
        if (collection != null) {
            collection.clear();
            l1 l1Var = l1.POSSIBLE_FORMATS;
            map.remove(l1Var);
            collection.add(s6Var.c());
            map.put(l1Var, collection);
        } else if (map != null) {
            l1 l1Var2 = l1.POSSIBLE_FORMATS;
            map.remove(l1Var2);
            Vector vector = new Vector();
            vector.add(s6Var.c());
            map.put(l1Var2, vector);
        }
        float f = 3.0f / r3.i;
        if (f < 1.0f) {
            f = 1.0f;
        }
        dArr[5] = f;
        return a(pVar, s6Var.j(), dArr, iArr);
    }

    private static byte[] a(p pVar, u6[] u6VarArr, double[] dArr, int[] iArr) throws a {
        if (u6VarArr == null) {
            iArr[0] = pVar.e();
            iArr[1] = pVar.c();
            return pVar.d();
        }
        int e = pVar.e();
        int i = e;
        int c = pVar.c();
        int i2 = 0;
        int i3 = 0;
        for (u6 u6Var : u6VarArr) {
            if (u6Var != null) {
                if (((int) u6Var.b()) < i) {
                    i = (int) u6Var.b();
                }
                if (((int) u6Var.c()) < c) {
                    c = (int) u6Var.c();
                }
                if (((int) u6Var.b()) > i2) {
                    i2 = (int) u6Var.b();
                }
                if (((int) u6Var.c()) > i3) {
                    i3 = (int) u6Var.c();
                }
            }
        }
        return a(u6VarArr, pVar, iArr, Math.max(i2 - i, i3 - c), dArr);
    }

    private static byte[] a(u6[] u6VarArr, p pVar, int[] iArr, float f, double[] dArr) throws a {
        float b = u6VarArr[0].b();
        float b2 = u6VarArr[1].b();
        float b3 = u6VarArr[2].b();
        float c = u6VarArr[0].c();
        float c2 = u6VarArr[1].c();
        float c3 = u6VarArr[2].c();
        if (b >= 0.0f && b2 >= 0.0f && b3 >= 0.0f && c >= 0.0f && c2 >= 0.0f && c3 >= 0.0f && b <= pVar.e() && b2 <= pVar.e() && b3 <= pVar.e() && c <= pVar.c() && c2 <= pVar.c() && c3 <= pVar.c()) {
            int i = ((int) (c + c3)) / 2;
            int i2 = (int) ((((int) (b + b3)) / 2) - f);
            if (i2 < 0) {
                i2 = 0;
            }
            int i3 = (int) (i - f);
            if (i3 < 0) {
                i3 = 0;
            }
            int i4 = ((int) f) * 2;
            int e = i2 + i4 <= pVar.e() ? i4 : pVar.e() - i2;
            if (i3 + i4 > pVar.c()) {
                i4 = pVar.c() - i3;
            }
            p a2 = pVar.a(i2, i3, e, i4);
            double degrees = dArr[0] + Math.toDegrees(a(u6VarArr[0], u6VarArr[1])) + 90.0d;
            dArr[0] = degrees;
            dArr[1] = i2;
            dArr[2] = i3;
            double d = e;
            dArr[3] = d;
            double d2 = i4;
            dArr[4] = d2;
            double radians = Math.toRadians(degrees);
            int abs = (int) (((Math.abs(Math.sin(radians)) * d) + (Math.abs(Math.cos(radians)) * d2)) * dArr[5]);
            int abs2 = (int) (((d2 * Math.abs(Math.sin(radians))) + (d * Math.abs(Math.cos(radians)))) * dArr[5]);
            iArr[0] = abs2;
            iArr[1] = abs;
            byte[] imageRotate = LoadOpencvJNIUtil.imageRotate(a2.d(), a2.c(), a2.e(), abs, abs2, (float) dArr[0], dArr[5]);
            if (imageRotate != null) {
                return imageRotate;
            }
            iArr[0] = pVar.e();
            iArr[1] = pVar.c();
            return pVar.d();
        }
        iArr[0] = pVar.e();
        iArr[1] = pVar.c();
        throw a.a();
    }

    public static double a(u6 u6Var, u6 u6Var2) {
        return Math.atan2(u6Var2.c() - u6Var.c(), u6Var2.b() - u6Var.b());
    }

    public static u6[] a(u6[] u6VarArr, int i, int i2, double[] dArr) {
        double d;
        u6[] u6VarArr2;
        int i3;
        u6[] u6VarArr3 = u6VarArr;
        if (u6VarArr3 == null) {
            return null;
        }
        double d2 = dArr[3];
        int i4 = d2 != 0.0d ? (int) d2 : i;
        double d3 = dArr[4];
        int i5 = d3 != 0.0d ? (int) d3 : i2;
        double d4 = dArr[5];
        u6[] u6VarArr4 = new u6[u6VarArr3.length];
        int i6 = 0;
        double radians = Math.toRadians(dArr[0]);
        double cos = Math.cos(radians) * d4;
        double sin = Math.sin(radians) * d4;
        double d5 = i5;
        double d6 = i4;
        double abs = (((Math.abs(sin) - sin) * d5) + ((Math.abs(cos) - cos) * d6)) / 2.0d;
        double d7 = -sin;
        double abs2 = ((d5 * (Math.abs(cos) - cos)) + (d6 * (Math.abs(sin) + sin))) / 2.0d;
        u6 u6Var = null;
        while (i6 < u6VarArr3.length) {
            if (u6VarArr3[i6] != null) {
                u6VarArr2 = u6VarArr4;
                i3 = i6;
                double b = (((r3.b() - abs) * cos) + ((abs2 - r3.c()) * sin)) / ((cos * cos) - (sin * d7));
                d = d7;
                u6VarArr2[i3] = new u6(Math.round(b) + ((int) dArr[1]), Math.round(sin == 0.0d ? (r3.c() - abs2) / cos : ((r3.b() - abs) - (cos * b)) / sin) + ((int) dArr[2]));
                u6Var = null;
            } else {
                d = d7;
                u6VarArr2 = u6VarArr4;
                i3 = i6;
                u6VarArr2[i3] = u6Var;
            }
            i6 = i3 + 1;
            u6VarArr3 = u6VarArr;
            u6VarArr4 = u6VarArr2;
            d7 = d;
        }
        return u6VarArr4;
    }
}
