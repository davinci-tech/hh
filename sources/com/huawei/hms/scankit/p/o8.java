package com.huawei.hms.scankit.p;

import android.graphics.Rect;

/* loaded from: classes9.dex */
public class o8 {
    public static float a(int i, int i2, u6[] u6VarArr) {
        float abs;
        float abs2;
        float f = 1.0f;
        if (u6VarArr.length < 3) {
            return 1.0f;
        }
        int i3 = 0;
        for (u6 u6Var : u6VarArr) {
            if (u6Var.d()) {
                i3++;
            }
        }
        if (r3.f5870a && !r3.b && i3 < 2) {
            return 1.0f;
        }
        float b = u6VarArr[0].b();
        float b2 = u6VarArr[1].b();
        float b3 = u6VarArr[2].b();
        float c = u6VarArr[0].c();
        float c2 = u6VarArr[1].c();
        float c3 = u6VarArr[2].c();
        u6 a2 = a(b, c, b2, c2, b3, c3);
        float b4 = a2.b();
        float c4 = a2.c();
        float max = Math.max(Math.max(Math.max(b, b2), b3), b4);
        float min = Math.min(Math.min(Math.min(b, b2), b3), b4);
        float max2 = Math.max(Math.max(Math.max(c, c2), c3), c4);
        float min2 = Math.min(Math.min(Math.min(c, c2), c3), c4);
        int min3 = (int) (Math.min(i2, i) * 0.85f);
        int i4 = (i - min3) / 2;
        int i5 = (i2 - min3) / 2;
        Rect rect = new Rect(i4, i5, min3 + i4, min3 + i5);
        if (min < rect.left && min2 < rect.top && max > rect.right && max2 > rect.bottom) {
            return 1.0f;
        }
        float abs3 = Math.abs(min2 - rect.top);
        float abs4 = Math.abs(min - rect.left);
        float abs5 = Math.abs(max - rect.right);
        float abs6 = Math.abs(max2 - rect.bottom);
        float f2 = (rect.left + rect.right) / 2.0f;
        float f3 = (rect.top + rect.bottom) / 2.0f;
        float min4 = Math.min(Math.min(Math.min(abs4, abs5), abs3), abs6);
        if (0.01f > Math.abs(abs4 - min4)) {
            abs = Math.abs(f2 - rect.left) * 1.0f;
            abs2 = Math.abs(f2 - min);
        } else if (0.01f > Math.abs(abs5 - min4)) {
            abs = Math.abs(f2 - rect.right) * 1.0f;
            abs2 = Math.abs(f2 - max);
        } else {
            if (0.01f <= Math.abs(abs3 - min4)) {
                if (0.01f > Math.abs(abs6 - min4)) {
                    abs = Math.abs(f3 - rect.bottom) * 1.0f;
                    abs2 = Math.abs(f3 - max2);
                }
                return Math.min(f, 2.0f) * 0.9f;
            }
            abs = Math.abs(f3 - rect.top) * 1.0f;
            abs2 = Math.abs(f3 - min2);
        }
        f = abs / abs2;
        return Math.min(f, 2.0f) * 0.9f;
    }

    public static float b(int i, int i2, u6[] u6VarArr) {
        float abs;
        float abs2;
        float f = 1.0f;
        if (u6VarArr.length < 3) {
            return 1.0f;
        }
        float b = u6VarArr[0].b();
        float b2 = u6VarArr[1].b();
        float b3 = u6VarArr[2].b();
        float c = u6VarArr[0].c();
        float c2 = u6VarArr[1].c();
        float c3 = u6VarArr[2].c();
        float max = Math.max(Math.max(b, b2), b3);
        float min = Math.min(Math.min(b, b2), b3);
        float max2 = Math.max(Math.max(c, c2), c3);
        float min2 = Math.min(Math.min(c, c2), c3);
        int min3 = (int) (Math.min(i2, i) * 0.1f);
        Rect rect = new Rect(min3, min3, i - min3, i2 - min3);
        if (min < rect.left && min2 < rect.top && max > rect.right && max2 > rect.bottom) {
            return 1.0f;
        }
        float abs3 = Math.abs(max - rect.right);
        float abs4 = Math.abs(max2 - rect.bottom);
        float abs5 = Math.abs(min2 - rect.top);
        float abs6 = Math.abs(min - rect.left);
        float f2 = (rect.left + rect.right) / 2.0f;
        float f3 = (rect.top + rect.bottom) / 2.0f;
        float min4 = Math.min(Math.min(Math.min(abs6, abs3), abs5), abs4);
        if (0.01f > Math.abs(abs6 - min4)) {
            abs = Math.abs(f2 - rect.left);
            abs2 = Math.abs(f2 - min);
        } else if (0.01f > Math.abs(abs3 - min4)) {
            abs = Math.abs(f2 - rect.right);
            abs2 = Math.abs(f2 - max);
        } else {
            if (0.01f <= Math.abs(abs5 - min4)) {
                if (0.01f > Math.abs(abs4 - min4)) {
                    abs = Math.abs(f3 - rect.bottom);
                    abs2 = Math.abs(f3 - max2);
                }
                return Math.min(f, 2.0f) * 0.9f;
            }
            abs = Math.abs(f3 - rect.top);
            abs2 = Math.abs(f3 - min2);
        }
        f = abs / abs2;
        return Math.min(f, 2.0f) * 0.9f;
    }

    private static u6 a(float f, float f2, float f3, float f4, float f5, float f6) {
        return new u6((f + f5) - f3, (f2 + f6) - f4);
    }
}
