package com.huawei.hms.network.embedded;

import com.huawei.hms.network.embedded.bb;

/* loaded from: classes9.dex */
public final class va {

    /* renamed from: a, reason: collision with root package name */
    public static final String f5549a = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
    public static final int b = 128;
    public static final int c = 64;
    public static final int d = 32;
    public static final int e = 16;
    public static final int f = 15;
    public static final int g = 8;
    public static final int h = 128;
    public static final int i = 127;
    public static final int j = 0;
    public static final int k = 1;
    public static final int l = 2;
    public static final int m = 8;
    public static final int n = 9;
    public static final int o = 10;
    public static final long p = 125;
    public static final long q = 123;
    public static final int r = 126;
    public static final long s = 65535;
    public static final int t = 127;
    public static final int u = 1001;
    public static final int v = 1005;

    public static void b(int i2) {
        String a2 = a(i2);
        if (a2 != null) {
            throw new IllegalArgumentException(a2);
        }
    }

    public static void a(bb.c cVar, byte[] bArr) {
        int length = bArr.length;
        int i2 = 0;
        do {
            byte[] bArr2 = cVar.e;
            int i3 = cVar.f;
            int i4 = cVar.g;
            while (i3 < i4) {
                int i5 = i2 % length;
                bArr2[i3] = (byte) (bArr2[i3] ^ bArr[i5]);
                i3++;
                i2 = i5 + 1;
            }
        } while (cVar.s() != -1);
    }

    public static String a(String str) {
        return eb.d(str + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").g().b();
    }

    public static String a(int i2) {
        StringBuilder sb;
        if (i2 < 1000 || i2 >= 5000) {
            sb = new StringBuilder("Code must be in range [1000,5000): ");
            sb.append(i2);
        } else {
            if ((i2 < 1004 || i2 > 1006) && (i2 < 1012 || i2 > 2999)) {
                return null;
            }
            sb = new StringBuilder("Code ");
            sb.append(i2);
            sb.append(" is reserved and may not be used.");
        }
        return sb.toString();
    }

    public va() {
        throw new AssertionError("No instances.");
    }
}
