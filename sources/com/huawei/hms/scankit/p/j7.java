package com.huawei.hms.scankit.p;

import java.util.HashMap;

/* loaded from: classes9.dex */
public class j7 {

    /* renamed from: a, reason: collision with root package name */
    private static float f5810a = 2.51f;
    private static float b = 0.03f;
    private static float c = 2.43f;
    private static float d = 0.59f;
    private static float e = 0.14f;
    private static HashMap<Integer, Integer> f = new HashMap<>(255);

    private static int a(int i, float f2) {
        if (f.containsKey(Integer.valueOf(i))) {
            return f.get(Integer.valueOf(i)).intValue();
        }
        float f3 = i / f2;
        int i2 = (int) ((f2 * (((f5810a * f3) + b) * f3)) / ((f3 * ((c * f3) + d)) + e));
        f.put(Integer.valueOf(i), Integer.valueOf(i2));
        return i2;
    }

    public static p4 b(p4 p4Var) {
        int a2 = a(p4Var);
        int c2 = p4Var.c();
        int a3 = p4Var.a();
        byte[] b2 = p4Var.b();
        byte[] bArr = new byte[a3 * c2];
        for (int i = 0; i < a3; i++) {
            for (int i2 = 0; i2 < c2; i2++) {
                int i3 = (i * c2) + i2;
                bArr[i3] = (byte) (a(b2[i3] & 255, a2) & 255);
            }
        }
        f = new HashMap<>(255);
        return new e6(bArr, c2, a3, 0, 0, c2, a3, false);
    }

    private static int a(p4 p4Var) {
        if (p4Var.b() == null) {
            return 1;
        }
        int c2 = p4Var.c();
        int a2 = p4Var.a();
        long j = 0;
        for (int i = a2 / 4; i < (a2 * 3) / 4; i++) {
            for (int i2 = c2 / 4; i2 < (c2 * 3) / 4; i2++) {
                j += r0[(i * c2) + i2] & 255;
            }
        }
        return (int) ((j / r0.length) * 4);
    }
}
