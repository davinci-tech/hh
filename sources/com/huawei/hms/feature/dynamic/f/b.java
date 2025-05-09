package com.huawei.hms.feature.dynamic.f;

import com.google.flatbuffers.reflection.BaseType;

/* loaded from: classes4.dex */
public final class b {

    /* renamed from: a, reason: collision with root package name */
    private static final char[] f4521a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final char[] b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String b(byte[] bArr, boolean z) {
        return new String(a(bArr, z));
    }

    private static char[] a(byte[] bArr, char[] cArr) {
        char[] cArr2 = new char[bArr.length << 1];
        int i = 0;
        for (byte b2 : bArr) {
            int i2 = i + 1;
            cArr2[i] = cArr[(b2 & 240) >>> 4];
            i += 2;
            cArr2[i2] = cArr[b2 & BaseType.Obj];
        }
        return cArr2;
    }

    public static char[] a(byte[] bArr, boolean z) {
        return a(bArr, z ? b : f4521a);
    }

    public static char[] a(byte[] bArr) {
        return a(bArr, false);
    }
}
