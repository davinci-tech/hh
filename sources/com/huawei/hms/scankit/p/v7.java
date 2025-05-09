package com.huawei.hms.scankit.p;

import java.util.List;

/* loaded from: classes9.dex */
public class v7 {
    public static List<s6> a(List<s6> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int size = list.size() - 1; size > i; size--) {
                if (list.get(i).k().equals(list.get(size).k()) && a(r2.j(), r3.j()) > 0.5d) {
                    list.remove(size);
                }
            }
        }
        return list;
    }

    public static float a(u6[] u6VarArr, u6[] u6VarArr2) {
        float f = Float.MIN_VALUE;
        float f2 = Float.MAX_VALUE;
        float f3 = Float.MIN_VALUE;
        float f4 = Float.MIN_VALUE;
        float f5 = Float.MAX_VALUE;
        float f6 = Float.MAX_VALUE;
        for (u6 u6Var : u6VarArr) {
            if (u6Var.b() > f3) {
                f3 = u6Var.b();
            }
            if (u6Var.b() < f5) {
                f5 = u6Var.b();
            }
            if (u6Var.c() > f4) {
                f4 = u6Var.c();
            }
            if (u6Var.c() < f6) {
                f6 = u6Var.c();
            }
        }
        float f7 = Float.MIN_VALUE;
        float f8 = Float.MAX_VALUE;
        for (u6 u6Var2 : u6VarArr2) {
            if (u6Var2.b() > f) {
                f = u6Var2.b();
            }
            if (u6Var2.b() < f2) {
                f2 = u6Var2.b();
            }
            if (u6Var2.c() > f7) {
                f7 = u6Var2.c();
            }
            if (u6Var2.c() < f8) {
                f8 = u6Var2.c();
            }
        }
        float f9 = (f < f3 ? f : f3) - (f2 > f5 ? f2 : f5);
        float f10 = (f7 < f4 ? f7 : f4) - (f8 > f6 ? f8 : f6);
        if (f9 < 0.0f) {
            f9 = 0.0f;
        }
        if (f10 < 0.0f) {
            f10 = 0.0f;
        }
        float f11 = f9 * f10;
        return f11 / ((((f3 - f5) * (f4 - f6)) + ((f - f2) * (f7 - f8))) - f11);
    }
}
