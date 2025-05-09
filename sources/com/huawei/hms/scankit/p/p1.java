package com.huawei.hms.scankit.p;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
final class p1 {

    /* renamed from: a, reason: collision with root package name */
    private static final char[] f5851a = "0123456789abcdefghijklmnopqrstuvwxyz !-./:=?T".toCharArray();
    private static final char[] b = "0123456789ABCDEF".toCharArray();

    static /* synthetic */ class a {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f5852a;

        static {
            int[] iArr = new int[v4.values().length];
            f5852a = iArr;
            try {
                iArr[v4.TERMINATOR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f5852a[v4.FNC1_FIRST_POSITION.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f5852a[v4.FNC1_SECOND_POSITION.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f5852a[v4.STRUCTURED_APPEND.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f5852a[v4.ECI.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f5852a[v4.HANZI.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f5852a[v4.NUMERIC.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f5852a[v4.ALPHANUMERIC.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f5852a[v4.HEXADECIMAL.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f5852a[v4.HEXABYTE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f5852a[v4.BYTE.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f5852a[v4.KANJI.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0093 A[LOOP:0: B:2:0x001b->B:13:0x0093, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0073 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static com.huawei.hms.scankit.p.w1 a(byte[] r15, com.huawei.hms.scankit.p.a8 r16, com.huawei.hms.scankit.p.c3 r17, java.util.Map<com.huawei.hms.scankit.p.l1, ?> r18) throws com.huawei.hms.scankit.p.a {
        /*
            r0 = r16
            com.huawei.hms.scankit.p.w r1 = new com.huawei.hms.scankit.p.w
            r3 = r15
            r1.<init>(r15)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r4 = 50
            r2.<init>(r4)
            java.util.ArrayList r4 = new java.util.ArrayList
            r5 = 1
            r4.<init>(r5)
            r6 = -1
            r7 = 0
            r8 = 0
            r10 = r7
            r9 = r8
            r8 = r6
        L1b:
            int r11 = r1.a()     // Catch: java.lang.IllegalArgumentException -> L98
            r12 = 4
            if (r11 >= r12) goto L25
            com.huawei.hms.scankit.p.v4 r11 = com.huawei.hms.scankit.p.v4.TERMINATOR     // Catch: java.lang.IllegalArgumentException -> L98
            goto L2d
        L25:
            int r11 = r1.a(r12)     // Catch: java.lang.IllegalArgumentException -> L98
            com.huawei.hms.scankit.p.v4 r11 = com.huawei.hms.scankit.p.v4.a(r11)     // Catch: java.lang.IllegalArgumentException -> L98
        L2d:
            int[] r12 = com.huawei.hms.scankit.p.p1.a.f5852a     // Catch: java.lang.IllegalArgumentException -> L98
            int r13 = r11.ordinal()     // Catch: java.lang.IllegalArgumentException -> L98
            r12 = r12[r13]     // Catch: java.lang.IllegalArgumentException -> L98
            switch(r12) {
                case 1: goto L5a;
                case 2: goto L62;
                case 3: goto L62;
                case 4: goto L48;
                case 5: goto L43;
                case 6: goto L3f;
                default: goto L38;
            }     // Catch: java.lang.IllegalArgumentException -> L98
        L38:
            r12 = r18
            com.huawei.hms.scankit.p.z0 r13 = a(r0, r12, r1, r2)     // Catch: java.lang.IllegalArgumentException -> L98
            goto L68
        L3f:
            a(r0, r1, r2, r11)     // Catch: java.lang.IllegalArgumentException -> L98
            goto L5a
        L43:
            com.huawei.hms.scankit.p.o0 r10 = a(r1)     // Catch: java.lang.IllegalArgumentException -> L98
            goto L5a
        L48:
            int r6 = r1.a()     // Catch: java.lang.IllegalArgumentException -> L98
            r8 = 16
            if (r6 < r8) goto L5d
            r6 = 8
            int r8 = r1.a(r6)     // Catch: java.lang.IllegalArgumentException -> L98
            int r6 = r1.a(r6)     // Catch: java.lang.IllegalArgumentException -> L98
        L5a:
            r12 = r18
            goto L6b
        L5d:
            com.huawei.hms.scankit.p.a r0 = com.huawei.hms.scankit.p.a.a()     // Catch: java.lang.IllegalArgumentException -> L98
            throw r0     // Catch: java.lang.IllegalArgumentException -> L98
        L62:
            r12 = r18
            r9 = r8
            r8 = r6
            r6 = r5
            goto L6f
        L68:
            a(r13, r4, r10, r9, r11)     // Catch: java.lang.IllegalArgumentException -> L98
        L6b:
            r14 = r8
            r8 = r6
            r6 = r9
            r9 = r14
        L6f:
            com.huawei.hms.scankit.p.v4 r13 = com.huawei.hms.scankit.p.v4.TERMINATOR     // Catch: java.lang.IllegalArgumentException -> L98
            if (r11 != r13) goto L93
            java.lang.String r0 = r2.toString()
            boolean r1 = r4.isEmpty()
            if (r1 == 0) goto L7f
            r5 = r7
            goto L80
        L7f:
            r5 = r4
        L80:
            if (r17 != 0) goto L84
            r6 = r7
            goto L89
        L84:
            java.lang.String r1 = r17.toString()
            r6 = r1
        L89:
            com.huawei.hms.scankit.p.w1 r1 = new com.huawei.hms.scankit.p.w1
            r2 = r1
            r3 = r15
            r4 = r0
            r7 = r9
            r2.<init>(r3, r4, r5, r6, r7, r8)
            return r1
        L93:
            r14 = r9
            r9 = r6
            r6 = r8
            r8 = r14
            goto L1b
        L98:
            com.huawei.hms.scankit.p.a r0 = com.huawei.hms.scankit.p.a.a()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.scankit.p.p1.a(byte[], com.huawei.hms.scankit.p.a8, com.huawei.hms.scankit.p.c3, java.util.Map):com.huawei.hms.scankit.p.w1");
    }

    private static void b(w wVar, StringBuilder sb, int i) throws com.huawei.hms.scankit.p.a {
        if (i * 13 > wVar.a()) {
            throw com.huawei.hms.scankit.p.a.a();
        }
        byte[] bArr = new byte[i * 2];
        int i2 = 0;
        while (i > 0) {
            int a2 = wVar.a(13);
            int i3 = ((a2 / 192) << 8) | (a2 % 192);
            int i4 = i3 + (i3 < 7936 ? 33088 : 49472);
            bArr[i2] = (byte) (i4 >> 8);
            bArr[i2 + 1] = (byte) i4;
            i2 += 2;
            i--;
        }
        try {
            sb.append(new String(bArr, "SJIS"));
        } catch (UnsupportedEncodingException unused) {
            throw com.huawei.hms.scankit.p.a.a();
        }
    }

    private static void c(w wVar, StringBuilder sb, int i, boolean z) throws com.huawei.hms.scankit.p.a {
        while (i > 1) {
            if (wVar.a() < 8) {
                throw com.huawei.hms.scankit.p.a.a();
            }
            int a2 = wVar.a(8);
            sb.append(b(a2 / 16));
            sb.append(b(a2 % 16));
            i -= 2;
        }
        if (i == 1) {
            if (wVar.a() < 4) {
                throw com.huawei.hms.scankit.p.a.a();
            }
            sb.append(b(wVar.a(4)));
        }
        if (z) {
            for (int length = sb.length(); length < sb.length(); length++) {
                if (sb.charAt(length) == '%') {
                    if (length < sb.length() - 1) {
                        int i2 = length + 1;
                        if (sb.charAt(i2) == '%') {
                            sb.deleteCharAt(i2);
                        }
                    }
                    sb.setCharAt(length, (char) 29);
                }
            }
        }
    }

    private static char b(int i) throws com.huawei.hms.scankit.p.a {
        char[] cArr = b;
        if (i < cArr.length) {
            return cArr[i];
        }
        throw com.huawei.hms.scankit.p.a.a();
    }

    private static void b(w wVar, StringBuilder sb, int i, boolean z) throws com.huawei.hms.scankit.p.a {
        while (i > 1) {
            if (wVar.a() >= 8) {
                int a2 = wVar.a(8);
                sb.append(b(a2 / 16));
                sb.append(b(a2 % 16));
                i -= 2;
            } else {
                throw com.huawei.hms.scankit.p.a.a();
            }
        }
        if (i == 1) {
            if (wVar.a() >= 4) {
                sb.append(b(wVar.a(4)));
            } else {
                throw com.huawei.hms.scankit.p.a.a();
            }
        }
        if (z) {
            for (int length = sb.length(); length < sb.length(); length++) {
                if (sb.charAt(length) == '%') {
                    if (length < sb.length() - 1) {
                        int i2 = length + 1;
                        if (sb.charAt(i2) == '%') {
                            sb.deleteCharAt(i2);
                        }
                    }
                    sb.setCharAt(length, (char) 29);
                }
            }
        }
    }

    private static void c(w wVar, StringBuilder sb, int i) throws com.huawei.hms.scankit.p.a {
        while (i >= 3) {
            if (wVar.a() >= 10) {
                int a2 = wVar.a(10);
                if (a2 < 1000) {
                    sb.append(a(a2 / 100));
                    sb.append(a((a2 / 10) % 10));
                    sb.append(a(a2 % 10));
                    i -= 3;
                } else {
                    throw com.huawei.hms.scankit.p.a.a();
                }
            } else {
                throw com.huawei.hms.scankit.p.a.a();
            }
        }
        if (i == 2) {
            if (wVar.a() >= 7) {
                int a3 = wVar.a(7);
                if (a3 < 100) {
                    sb.append(a(a3 / 10));
                    sb.append(a(a3 % 10));
                    return;
                }
                throw com.huawei.hms.scankit.p.a.a();
            }
            throw com.huawei.hms.scankit.p.a.a();
        }
        if (i == 1) {
            if (wVar.a() >= 4) {
                int a4 = wVar.a(4);
                if (a4 < 10) {
                    sb.append(a(a4));
                    return;
                }
                throw com.huawei.hms.scankit.p.a.a();
            }
            throw com.huawei.hms.scankit.p.a.a();
        }
    }

    private static int b(w wVar) throws com.huawei.hms.scankit.p.a {
        int a2 = wVar.a(8);
        if ((a2 & 128) == 0) {
            return a2 & 127;
        }
        if ((a2 & 192) == 128) {
            return wVar.a(8) | ((a2 & 63) << 8);
        }
        if ((a2 & 224) == 192) {
            return wVar.a(16) | ((a2 & 31) << 16);
        }
        throw com.huawei.hms.scankit.p.a.a();
    }

    private static z0 a(a8 a8Var, Map<l1, ?> map, w wVar, StringBuilder sb) {
        return new z0(a8Var, map, wVar, sb);
    }

    private static void a(z0 z0Var, List<byte[]> list, o0 o0Var, boolean z, v4 v4Var) throws com.huawei.hms.scankit.p.a {
        a(new h7(z0Var.b, z0Var.c, z0Var.d, list), o0Var, z, v4Var, z0Var.c.a(v4Var.a(z0Var.f5929a)));
    }

    private static o0 a(w wVar) throws com.huawei.hms.scankit.p.a {
        o0 a2 = o0.a(b(wVar));
        a(a2);
        return a2;
    }

    private static void a(o0 o0Var) throws com.huawei.hms.scankit.p.a {
        if (o0Var == null) {
            throw com.huawei.hms.scankit.p.a.a();
        }
    }

    private static void a(a8 a8Var, w wVar, StringBuilder sb, v4 v4Var) throws com.huawei.hms.scankit.p.a {
        int a2 = wVar.a(4);
        int a3 = wVar.a(v4Var.a(a8Var));
        if (a2 == 1) {
            a(wVar, sb, a3);
        }
    }

    private static void a(h7 h7Var, o0 o0Var, boolean z, v4 v4Var, int i) throws com.huawei.hms.scankit.p.a {
        switch (a.f5852a[v4Var.ordinal()]) {
            case 7:
                c(h7Var.b, h7Var.c, i);
                return;
            case 8:
                a(h7Var.b, h7Var.c, i, z);
                return;
            case 9:
                c(h7Var.b, h7Var.c, i, z);
                return;
            case 10:
                b(h7Var.b, h7Var.c, i, z);
                return;
            case 11:
                a(new b0(h7Var.b, h7Var.c), i, o0Var, h7Var.d, h7Var.f5790a);
                return;
            case 12:
                b(h7Var.b, h7Var.c, i);
                return;
            default:
                throw com.huawei.hms.scankit.p.a.a();
        }
    }

    private static void a(w wVar, StringBuilder sb, int i) throws com.huawei.hms.scankit.p.a {
        if (i * 13 <= wVar.a()) {
            byte[] bArr = new byte[i * 2];
            int i2 = 0;
            while (i > 0) {
                int a2 = wVar.a(13);
                int i3 = ((a2 / 96) << 8) | (a2 % 96);
                int i4 = i3 + (i3 < 2560 ? 41377 : 42657);
                bArr[i2] = (byte) ((i4 >> 8) & 255);
                bArr[i2 + 1] = (byte) (i4 & 255);
                i2 += 2;
                i--;
            }
            try {
                sb.append(new String(bArr, "GB2312"));
                return;
            } catch (UnsupportedEncodingException unused) {
                throw com.huawei.hms.scankit.p.a.a();
            }
        }
        throw com.huawei.hms.scankit.p.a.a();
    }

    private static void a(b0 b0Var, int i, o0 o0Var, Collection<byte[]> collection, Map<l1, ?> map) throws com.huawei.hms.scankit.p.a {
        String name;
        if (i * 8 <= b0Var.f5739a.a()) {
            byte[] bArr = new byte[i];
            for (int i2 = 0; i2 < i; i2++) {
                bArr[i2] = (byte) b0Var.f5739a.a(8);
            }
            if (o0Var == null) {
                name = c7.a(bArr, map);
            } else {
                name = o0Var.name();
            }
            try {
                b0Var.b.append(new String(bArr, name));
                collection.add(bArr);
                return;
            } catch (UnsupportedEncodingException unused) {
                throw com.huawei.hms.scankit.p.a.a();
            }
        }
        throw com.huawei.hms.scankit.p.a.a();
    }

    private static char a(int i) throws com.huawei.hms.scankit.p.a {
        char[] cArr = f5851a;
        if (i < cArr.length) {
            return cArr[i];
        }
        throw com.huawei.hms.scankit.p.a.a();
    }

    private static void a(w wVar, StringBuilder sb, int i, boolean z) throws com.huawei.hms.scankit.p.a {
        while (i > 1) {
            if (wVar.a() >= 11) {
                int a2 = wVar.a(11);
                sb.append(a(a2 / 45));
                sb.append(a(a2 % 45));
                i -= 2;
            } else {
                throw com.huawei.hms.scankit.p.a.a();
            }
        }
        if (i == 1) {
            if (wVar.a() >= 6) {
                sb.append(a(wVar.a(6)));
            } else {
                throw com.huawei.hms.scankit.p.a.a();
            }
        }
        if (z) {
            for (int length = sb.length(); length < sb.length(); length++) {
                if (sb.charAt(length) == '%') {
                    if (length < sb.length() - 1) {
                        int i2 = length + 1;
                        if (sb.charAt(i2) == '%') {
                            sb.deleteCharAt(i2);
                        }
                    }
                    sb.setCharAt(length, (char) 29);
                }
            }
        }
    }
}
