package com.huawei.openalliance.ad.utils;

import java.lang.reflect.Array;

/* loaded from: classes5.dex */
public class l {
    public static int b(int[] iArr, int i) {
        return a(iArr, i, 0);
    }

    public static boolean a(int[] iArr, int i) {
        return b(iArr, i) != -1;
    }

    public static boolean a(int[] iArr) {
        return a((Object) iArr) == 0;
    }

    public static int a(int[] iArr, int i, int i2) {
        if (a(iArr)) {
            return -1;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        while (i2 < iArr.length) {
            if (i == iArr[i2]) {
                return i2;
            }
            i2++;
        }
        return -1;
    }

    public static int a(Object obj) {
        if (obj == null) {
            return 0;
        }
        return Array.getLength(obj);
    }
}
