package com.huawei.hms.scankit.p;

import com.huawei.hms.hmsscankit.WriterException;
import com.huawei.hwcommonmodel.fitnessdatatype.FitnessSleepType;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes4.dex */
public final class w2 {

    /* renamed from: a, reason: collision with root package name */
    private static final int[] f5902a = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 36, -1, -1, -1, 37, 38, -1, -1, -1, -1, 39, 40, -1, 41, 42, 43, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 44, -1, -1, -1, -1, -1, -1, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, -1, -1, -1, -1, -1};

    static /* synthetic */ class a {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f5903a;

        static {
            int[] iArr = new int[u4.values().length];
            f5903a = iArr;
            try {
                iArr[u4.NUMERIC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f5903a[u4.ALPHANUMERIC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f5903a[u4.BYTE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f5903a[u4.KANJI.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private static int a(c0 c0Var) {
        return r4.a(c0Var) + r4.b(c0Var) + r4.c(c0Var) + r4.d(c0Var);
    }

    static void b(CharSequence charSequence, r rVar) {
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            int charAt = charSequence.charAt(i) - '0';
            int i2 = i + 2;
            if (i2 < length) {
                rVar.a((charAt * 100) + ((charSequence.charAt(i + 1) - '0') * 10) + (charSequence.charAt(i2) - '0'), 10);
                i += 3;
            } else {
                i++;
                if (i < length) {
                    rVar.a((charAt * 10) + (charSequence.charAt(i) - '0'), 7);
                    i = i2;
                } else {
                    rVar.a(charAt, 4);
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00a2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.huawei.hms.scankit.p.h6 a(java.lang.String r6, com.huawei.hms.scankit.p.b3 r7, java.util.Map<com.huawei.hms.scankit.p.u2, ?> r8) throws com.huawei.hms.hmsscankit.WriterException {
        /*
            if (r8 == 0) goto Lc
            com.huawei.hms.scankit.p.u2 r0 = com.huawei.hms.scankit.p.u2.CHARACTER_SET
            boolean r0 = r8.containsKey(r0)
            if (r0 == 0) goto Lc
            r0 = 1
            goto Ld
        Lc:
            r0 = 0
        Ld:
            if (r0 == 0) goto L1a
            com.huawei.hms.scankit.p.u2 r1 = com.huawei.hms.scankit.p.u2.CHARACTER_SET
            java.lang.Object r1 = r8.get(r1)
            java.lang.String r1 = r1.toString()
            goto L1c
        L1a:
            java.lang.String r1 = "ISO-8859-1"
        L1c:
            com.huawei.hms.scankit.p.u4 r2 = a(r6, r1)
            com.huawei.hms.scankit.p.r r3 = new com.huawei.hms.scankit.p.r
            r3.<init>()
            com.huawei.hms.scankit.p.u4 r4 = com.huawei.hms.scankit.p.u4.BYTE
            if (r2 != r4) goto L34
            if (r0 == 0) goto L34
            com.huawei.hms.scankit.p.o0 r0 = com.huawei.hms.scankit.p.o0.a(r1)
            if (r0 == 0) goto L34
            a(r0, r3)
        L34:
            if (r8 == 0) goto L57
            com.huawei.hms.scankit.p.u2 r0 = com.huawei.hms.scankit.p.u2.GS1_FORMAT
            boolean r0 = r8.containsKey(r0)
            if (r0 == 0) goto L57
            com.huawei.hms.scankit.p.u2 r0 = com.huawei.hms.scankit.p.u2.GS1_FORMAT
            java.lang.Object r0 = r8.get(r0)
            java.lang.String r0 = r0.toString()
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L57
            com.huawei.hms.scankit.p.u4 r0 = com.huawei.hms.scankit.p.u4.FNC1_FIRST_POSITION
            a(r0, r3)
        L57:
            a(r2, r3)
            com.huawei.hms.scankit.p.r r0 = new com.huawei.hms.scankit.p.r
            r0.<init>()
            a(r6, r2, r0, r1)
            if (r8 == 0) goto L8f
            com.huawei.hms.scankit.p.u2 r1 = com.huawei.hms.scankit.p.u2.QR_VERSION
            boolean r5 = r8.containsKey(r1)
            if (r5 == 0) goto L8f
            java.lang.Object r8 = r8.get(r1)
            java.lang.String r8 = r8.toString()
            int r8 = java.lang.Integer.parseInt(r8)
            com.huawei.hms.scankit.p.b8 r8 = com.huawei.hms.scankit.p.b8.c(r8)
            int r1 = a(r2, r3, r0, r8)
            boolean r1 = a(r1, r8, r7)
            if (r1 == 0) goto L87
            goto L93
        L87:
            com.huawei.hms.hmsscankit.WriterException r6 = new com.huawei.hms.hmsscankit.WriterException
            java.lang.String r7 = "Data too big for requested version"
            r6.<init>(r7)
            throw r6
        L8f:
            com.huawei.hms.scankit.p.b8 r8 = a(r7, r2, r3, r0)
        L93:
            com.huawei.hms.scankit.p.r r1 = new com.huawei.hms.scankit.p.r
            r1.<init>()
            r1.a(r3)
            if (r2 != r4) goto La2
            int r6 = r0.f()
            goto La6
        La2:
            int r6 = r6.length()
        La6:
            a(r6, r8, r2, r1)
            r1.a(r0)
            com.huawei.hms.scankit.p.b8$b r6 = r8.a(r7)
            int r0 = r8.e()
            int r3 = r6.d()
            int r0 = r0 - r3
            a(r0, r1)
            int r3 = r8.e()
            int r6 = r6.c()
            com.huawei.hms.scankit.p.r r6 = a(r1, r3, r0, r6)
            com.huawei.hms.scankit.p.h6 r0 = new com.huawei.hms.scankit.p.h6
            r0.<init>()
            r0.a(r7)
            r0.a(r2)
            r0.a(r8)
            int r1 = r8.d()
            com.huawei.hms.scankit.p.c0 r2 = new com.huawei.hms.scankit.p.c0
            r2.<init>(r1, r1)
            int r1 = a(r6, r7, r8, r2)
            r0.b(r1)
            com.huawei.hms.scankit.p.t4.a(r6, r7, r8, r1, r2)
            r0.a(r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.scankit.p.w2.a(java.lang.String, com.huawei.hms.scankit.p.b3, java.util.Map):com.huawei.hms.scankit.p.h6");
    }

    private static b8 a(b3 b3Var, u4 u4Var, r rVar, r rVar2) throws WriterException {
        return a(a(u4Var, rVar, rVar2, a(a(u4Var, rVar, rVar2, b8.c(1)), b3Var)), b3Var);
    }

    private static int a(u4 u4Var, r rVar, r rVar2, b8 b8Var) {
        return rVar.e() + u4Var.a(b8Var) + rVar2.e();
    }

    static int a(int i) {
        int[] iArr = f5902a;
        if (i < iArr.length) {
            return iArr[i];
        }
        return -1;
    }

    private static u4 a(String str, String str2) {
        if ("Shift_JIS".equals(str2) && a(str)) {
            return u4.KANJI;
        }
        boolean z = false;
        boolean z2 = false;
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (charAt >= '0' && charAt <= '9') {
                z2 = true;
            } else {
                if (a(charAt) == -1) {
                    return u4.BYTE;
                }
                z = true;
            }
        }
        if (z) {
            return u4.ALPHANUMERIC;
        }
        if (z2) {
            return u4.NUMERIC;
        }
        return u4.BYTE;
    }

    private static boolean a(String str) {
        try {
            byte[] bytes = str.getBytes("Shift_JIS");
            int length = bytes.length;
            if (length % 2 != 0) {
                return false;
            }
            for (int i = 0; i < length; i += 2) {
                int i2 = bytes[i] & 255;
                if ((i2 < 129 || i2 > 159) && (i2 < 224 || i2 > 235)) {
                    return false;
                }
            }
            return true;
        } catch (UnsupportedEncodingException unused) {
            return false;
        }
    }

    private static int a(r rVar, b3 b3Var, b8 b8Var, c0 c0Var) throws WriterException {
        int i = Integer.MAX_VALUE;
        int i2 = -1;
        for (int i3 = 0; i3 < 8; i3++) {
            t4.a(rVar, b3Var, b8Var, i3, c0Var);
            int a2 = a(c0Var);
            if (a2 < i) {
                i2 = i3;
                i = a2;
            }
        }
        return i2;
    }

    private static b8 a(int i, b3 b3Var) throws WriterException {
        for (int i2 = 1; i2 <= 40; i2++) {
            b8 c = b8.c(i2);
            if (a(i, c, b3Var)) {
                return c;
            }
        }
        throw new WriterException("Data too big");
    }

    private static boolean a(int i, b8 b8Var, b3 b3Var) {
        return b8Var.e() - b8Var.a(b3Var).d() >= (i + 7) / 8;
    }

    static void a(int i, r rVar) throws WriterException {
        int i2 = i * 8;
        if (rVar.e() <= i2) {
            for (int i3 = 0; i3 < 4 && rVar.e() < i2; i3++) {
                rVar.a(false);
            }
            int e = rVar.e() & 7;
            if (e > 0) {
                while (e < 8) {
                    rVar.a(false);
                    e++;
                }
            }
            int f = rVar.f();
            for (int i4 = 0; i4 < i - f; i4++) {
                rVar.a((i4 & 1) == 0 ? FitnessSleepType.HW_FITNESS_DREAM : 17, 8);
            }
            if (rVar.e() != i2) {
                throw new WriterException("Bits size does not equal capacity");
            }
            return;
        }
        throw new WriterException("data bits cannot fit in the QR Code" + rVar.e() + " > " + i2);
    }

    static void a(int i, int i2, int i3, int i4, int[] iArr, int[] iArr2) throws WriterException {
        if (i4 < i3) {
            int i5 = i % i3;
            int i6 = i3 - i5;
            int i7 = i / i3;
            int i8 = i2 / i3;
            int i9 = i8 + 1;
            int i10 = i7 - i8;
            int i11 = (i7 + 1) - i9;
            if (i10 != i11) {
                throw new WriterException("EC bytes mismatch");
            }
            if (i3 != i6 + i5) {
                throw new WriterException("RS blocks mismatch");
            }
            if (i != ((i8 + i10) * i6) + ((i9 + i11) * i5)) {
                throw new WriterException("Total bytes mismatch");
            }
            if (i4 < i6) {
                iArr[0] = i8;
                iArr2[0] = i10;
                return;
            } else {
                iArr[0] = i9;
                iArr2[0] = i11;
                return;
            }
        }
        throw new WriterException("Block ID too large");
    }

    static r a(r rVar, int i, int i2, int i3) throws WriterException {
        if (rVar.f() == i2) {
            ArrayList arrayList = new ArrayList(i3);
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            for (int i7 = 0; i7 < i3; i7++) {
                int[] iArr = new int[1];
                int[] iArr2 = new int[1];
                a(i, i2, i3, i7, iArr, iArr2);
                int i8 = iArr[0];
                byte[] bArr = new byte[i8];
                rVar.a(i4 * 8, bArr, 0, i8);
                byte[] a2 = a(bArr, iArr2[0]);
                arrayList.add(new y(bArr, a2));
                i5 = Math.max(i5, i8);
                i6 = Math.max(i6, a2.length);
                i4 += iArr[0];
            }
            if (i2 == i4) {
                r rVar2 = new r();
                for (int i9 = 0; i9 < i5; i9++) {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        byte[] a3 = ((y) it.next()).a();
                        if (i9 < a3.length) {
                            rVar2.a(a3[i9], 8);
                        }
                    }
                }
                for (int i10 = 0; i10 < i6; i10++) {
                    Iterator it2 = arrayList.iterator();
                    while (it2.hasNext()) {
                        byte[] b = ((y) it2.next()).b();
                        if (i10 < b.length) {
                            rVar2.a(b[i10], 8);
                        }
                    }
                }
                if (i == rVar2.f()) {
                    return rVar2;
                }
                throw new WriterException("Interleaving error: " + i + " and " + rVar2.f() + " differ.");
            }
            throw new WriterException("Data bytes does not match offset");
        }
        throw new WriterException("Number of bits and data bytes does not match");
    }

    static byte[] a(byte[] bArr, int i) {
        int length = bArr.length;
        int[] iArr = new int[length + i];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = bArr[i2] & 255;
        }
        new q6(o3.l).a(iArr, i);
        byte[] bArr2 = new byte[i];
        for (int i3 = 0; i3 < i; i3++) {
            bArr2[i3] = (byte) iArr[length + i3];
        }
        return bArr2;
    }

    static void a(u4 u4Var, r rVar) {
        rVar.a(u4Var.a(), 4);
    }

    static void a(int i, b8 b8Var, u4 u4Var, r rVar) throws WriterException {
        int a2 = u4Var.a(b8Var);
        int i2 = 1 << a2;
        if (i < i2) {
            rVar.a(i, a2);
            return;
        }
        throw new WriterException(i + " is bigger than " + (i2 - 1));
    }

    static void a(String str, u4 u4Var, r rVar, String str2) throws WriterException {
        int i = a.f5903a[u4Var.ordinal()];
        if (i == 1) {
            b(str, rVar);
            return;
        }
        if (i == 2) {
            a((CharSequence) str, rVar);
            return;
        }
        if (i == 3) {
            a(str, rVar, str2);
        } else {
            if (i != 4) {
                throw new WriterException("Invalid mode: " + u4Var);
            }
            a(str, rVar);
        }
    }

    static void a(CharSequence charSequence, r rVar) throws WriterException {
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            int a2 = a(charSequence.charAt(i));
            if (a2 == -1) {
                throw new WriterException();
            }
            int i2 = i + 1;
            if (i2 < length) {
                int a3 = a(charSequence.charAt(i2));
                if (a3 != -1) {
                    rVar.a((a2 * 45) + a3, 11);
                    i += 2;
                } else {
                    throw new WriterException();
                }
            } else {
                rVar.a(a2, 6);
                i = i2;
            }
        }
    }

    static void a(String str, r rVar, String str2) throws WriterException {
        try {
            for (byte b : str.getBytes(str2)) {
                rVar.a(b, 8);
            }
        } catch (UnsupportedEncodingException e) {
            throw new WriterException(e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0035 A[LOOP:0: B:4:0x0008->B:11:0x0035, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0044 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static void a(java.lang.String r6, com.huawei.hms.scankit.p.r r7) throws com.huawei.hms.hmsscankit.WriterException {
        /*
            java.lang.String r0 = "Shift_JIS"
            byte[] r6 = r6.getBytes(r0)     // Catch: java.io.UnsupportedEncodingException -> L4d
            int r0 = r6.length
            r1 = 0
        L8:
            if (r1 >= r0) goto L4c
            r2 = r6[r1]
            r2 = r2 & 255(0xff, float:3.57E-43)
            int r2 = r2 << 8
            int r3 = r1 + 1
            r3 = r6[r3]
            r3 = r3 & 255(0xff, float:3.57E-43)
            r2 = r2 | r3
            r3 = -1
            r4 = 33088(0x8140, float:4.6366E-41)
            if (r2 < r4) goto L23
            r5 = 40956(0x9ffc, float:5.7392E-41)
            if (r2 > r5) goto L23
            goto L30
        L23:
            r4 = 57408(0xe040, float:8.0446E-41)
            if (r2 < r4) goto L32
            r4 = 60351(0xebbf, float:8.457E-41)
            if (r2 > r4) goto L32
            r4 = 49472(0xc140, float:6.9325E-41)
        L30:
            int r2 = r2 - r4
            goto L33
        L32:
            r2 = r3
        L33:
            if (r2 == r3) goto L44
            int r3 = r2 >> 8
            int r3 = r3 * 192
            r2 = r2 & 255(0xff, float:3.57E-43)
            int r3 = r3 + r2
            r2 = 13
            r7.a(r3, r2)
            int r1 = r1 + 2
            goto L8
        L44:
            com.huawei.hms.hmsscankit.WriterException r6 = new com.huawei.hms.hmsscankit.WriterException
            java.lang.String r7 = "Invalid byte sequence"
            r6.<init>(r7)
            throw r6
        L4c:
            return
        L4d:
            r6 = move-exception
            com.huawei.hms.hmsscankit.WriterException r7 = new com.huawei.hms.hmsscankit.WriterException
            r7.<init>(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.scankit.p.w2.a(java.lang.String, com.huawei.hms.scankit.p.r):void");
    }

    private static void a(o0 o0Var, r rVar) {
        rVar.a(u4.ECI.a(), 4);
        rVar.a(o0Var.a(), 8);
    }
}
