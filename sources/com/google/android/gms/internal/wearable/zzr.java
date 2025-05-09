package com.google.android.gms.internal.wearable;

import java.nio.charset.Charset;
import java.util.Arrays;

/* loaded from: classes2.dex */
public final class zzr {
    protected static final Charset UTF_8 = Charset.forName("UTF-8");
    private static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    public static final Object zzhk = new Object();

    public static boolean equals(long[] jArr, long[] jArr2) {
        return (jArr == null || jArr.length == 0) ? jArr2 == null || jArr2.length == 0 : Arrays.equals(jArr, jArr2);
    }

    public static boolean equals(float[] fArr, float[] fArr2) {
        if (fArr == null || fArr.length == 0) {
            return fArr2 == null || fArr2.length == 0;
        }
        return Arrays.equals(fArr, fArr2);
    }

    public static boolean equals(Object[] objArr, Object[] objArr2) {
        int length = objArr == null ? 0 : objArr.length;
        int length2 = objArr2 == null ? 0 : objArr2.length;
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i2 >= length || objArr[i2] != null) {
                while (i < length2 && objArr2[i] == null) {
                    i++;
                }
                boolean z = i2 >= length;
                boolean z2 = i >= length2;
                if (z && z2) {
                    return true;
                }
                if (z != z2 || !objArr[i2].equals(objArr2[i])) {
                    return false;
                }
                i2++;
                i++;
            } else {
                i2++;
            }
        }
    }

    public static int hashCode(long[] jArr) {
        if (jArr == null || jArr.length == 0) {
            return 0;
        }
        return Arrays.hashCode(jArr);
    }

    public static int hashCode(float[] fArr) {
        if (fArr == null || fArr.length == 0) {
            return 0;
        }
        return Arrays.hashCode(fArr);
    }

    public static int hashCode(Object[] objArr) {
        int length = objArr == null ? 0 : objArr.length;
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            Object obj = objArr[i2];
            if (obj != null) {
                i = (i * 31) + obj.hashCode();
            }
        }
        return i;
    }

    public static void zza(zzn zznVar, zzn zznVar2) {
        if (zznVar.zzhc != null) {
            zznVar2.zzhc = (zzp) zznVar.zzhc.clone();
        }
    }
}
