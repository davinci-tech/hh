package com.huawei.hms.network.embedded;

import androidx.core.view.MotionEventCompat;
import java.nio.charset.Charset;

/* loaded from: classes9.dex */
public final class cc {

    /* renamed from: a, reason: collision with root package name */
    public static final Charset f5207a = Charset.forName("UTF-8");

    public static int a(int i) {
        return ((i & MotionEventCompat.ACTION_POINTER_INDEX_MASK) << 8) | (((-16777216) & i) >>> 24) | ((16711680 & i) >>> 8) | ((i & 255) << 24);
    }

    public static long a(long j) {
        return ((j & 65280) << 40) | (((-72057594037927936L) & j) >>> 56) | ((71776119061217280L & j) >>> 40) | ((280375465082880L & j) >>> 24) | ((1095216660480L & j) >>> 8) | ((4278190080L & j) << 8) | ((16711680 & j) << 24) | ((255 & j) << 56);
    }

    public static short a(short s) {
        return (short) (((s & 65280) >>> 8) | ((s & 255) << 8));
    }

    public static <T extends Throwable> void b(Throwable th) throws Throwable {
        throw th;
    }

    public static boolean a(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            if (bArr[i4 + i] != bArr2[i4 + i2]) {
                return false;
            }
        }
        return true;
    }

    public static void a(Throwable th) {
        b(th);
    }

    public static void a(long j, long j2, long j3) {
        if ((j2 | j3) < 0 || j2 > j || j - j2 < j3) {
            throw new ArrayIndexOutOfBoundsException(String.format("size=%s offset=%s byteCount=%s", Long.valueOf(j), Long.valueOf(j2), Long.valueOf(j3)));
        }
    }
}
