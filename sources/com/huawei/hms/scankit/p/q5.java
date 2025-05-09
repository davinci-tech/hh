package com.huawei.hms.scankit.p;

import com.huawei.hms.hmsscankit.WriterException;
import com.huawei.hms.network.okhttp.PublicSuffixDatabase;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import okio.Utf8;

/* loaded from: classes4.dex */
final class q5 {
    private static final byte[] c;

    /* renamed from: a, reason: collision with root package name */
    private static final byte[] f5862a = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 38, 13, 9, 44, 58, 35, 45, 46, 36, 47, 43, 37, 42, 61, 94, 0, 32, 0, 0, 0};
    private static final byte[] b = {59, 60, 62, 64, 91, 92, 93, 95, 96, 126, PublicSuffixDatabase.i, 13, 9, 44, 58, 10, 45, 46, 36, 47, 34, 124, 42, 40, 41, Utf8.REPLACEMENT_BYTE, 123, 125, 39, 0};
    private static final byte[] d = new byte[128];
    private static final Charset e = StandardCharsets.ISO_8859_1;

    static /* synthetic */ class a {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f5863a;

        static {
            int[] iArr = new int[y0.values().length];
            f5863a = iArr;
            try {
                iArr[y0.TEXT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f5863a[y0.BYTE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f5863a[y0.NUMERIC.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    static {
        byte[] bArr = new byte[128];
        c = bArr;
        Arrays.fill(bArr, (byte) -1);
        int i = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr2 = f5862a;
            if (i2 >= bArr2.length) {
                break;
            }
            byte b2 = bArr2[i2];
            if (b2 > 0) {
                c[b2] = (byte) i2;
            }
            i2++;
        }
        Arrays.fill(d, (byte) -1);
        while (true) {
            byte[] bArr3 = b;
            if (i >= bArr3.length) {
                return;
            }
            byte b3 = bArr3[i];
            if (b3 > 0) {
                d[b3] = (byte) i;
            }
            i++;
        }
    }

    static String a(String str, y0 y0Var, Charset charset) throws WriterException {
        StringBuilder sb = new StringBuilder(str.length());
        if (charset == null) {
            charset = e;
        } else if (e.equals(charset)) {
            o4.a("PDF417", "else");
        } else {
            o0 a2 = o0.a(charset.name());
            if (a2 != null) {
                a(a2.a(), sb);
            }
        }
        int length = str.length();
        int i = a.f5863a[y0Var.ordinal()];
        if (i == 1) {
            a(str, 0, length, sb, 0);
        } else if (i == 2) {
            byte[] bytes = str.getBytes(charset);
            a(bytes, 0, bytes.length, 1, sb);
        } else if (i != 3) {
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            while (i2 < length) {
                int a3 = a(str, i2);
                if (a3 >= 13) {
                    sb.append((char) 902);
                    a(str, i2, a3, sb);
                    i2 += a3;
                    i3 = 0;
                    i4 = 2;
                } else {
                    int b2 = b(str, i2);
                    if (b2 >= 5 || a3 == length) {
                        if (i4 != 0) {
                            sb.append((char) 900);
                            i3 = 0;
                            i4 = 0;
                        }
                        i3 = a(str, i2, b2, sb, i3);
                        i2 += b2;
                    } else {
                        int a4 = a(str, i2, charset);
                        if (a4 == 0) {
                            a4 = 1;
                        }
                        int i5 = a4 + i2;
                        byte[] bytes2 = str.substring(i2, i5).getBytes(charset);
                        if (bytes2.length == 1 && i4 == 0) {
                            a(bytes2, 0, 1, 0, sb);
                        } else {
                            a(bytes2, 0, bytes2.length, i4, sb);
                            i4 = 1;
                            i3 = 0;
                        }
                        i2 = i5;
                    }
                }
            }
        } else {
            sb.append((char) 902);
            a(str, 0, length, sb);
        }
        return sb.toString();
    }

    private static boolean a(char c2) {
        return c2 == ' ' || (c2 >= 'a' && c2 <= 'z');
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x0027, code lost:
    
        return (r1 - r7) - r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int b(java.lang.CharSequence r6, int r7) {
        /*
            int r0 = r6.length()
            r1 = r7
        L5:
            if (r1 >= r0) goto L39
            char r2 = r6.charAt(r1)
            r3 = 0
        Lc:
            r4 = 13
            if (r3 >= r4) goto L23
            boolean r5 = c(r2)
            if (r5 == 0) goto L23
            if (r1 >= r0) goto L23
            int r3 = r3 + 1
            int r1 = r1 + 1
            if (r1 >= r0) goto Lc
            char r2 = r6.charAt(r1)
            goto Lc
        L23:
            if (r3 < r4) goto L28
            int r1 = r1 - r7
            int r1 = r1 - r3
            return r1
        L28:
            if (r3 <= 0) goto L2b
            goto L5
        L2b:
            char r2 = r6.charAt(r1)
            boolean r2 = f(r2)
            if (r2 != 0) goto L36
            goto L39
        L36:
            int r1 = r1 + 1
            goto L5
        L39:
            int r1 = r1 - r7
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.scankit.p.q5.b(java.lang.CharSequence, int):int");
    }

    private static boolean b(char c2) {
        return c2 == ' ' || (c2 >= 'A' && c2 <= 'Z');
    }

    private static boolean c(char c2) {
        return c2 >= '0' && c2 <= '9';
    }

    private static boolean d(char c2) {
        byte[] bArr = c;
        if (w7.a(bArr, (int) c2)) {
            return bArr[c2] != -1;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    private static boolean e(char c2) {
        if (w7.a(c, (int) c2)) {
            return d[c2] != -1;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    private static boolean f(char c2) {
        return c2 == '\t' || c2 == '\n' || c2 == '\r' || (c2 >= ' ' && c2 <= '~');
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00f6 A[EDGE_INSN: B:21:0x00f6->B:22:0x00f6 BREAK  A[LOOP:0: B:2:0x000f->B:16:0x000f], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x000f A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int a(java.lang.CharSequence r16, int r17, int r18, java.lang.StringBuilder r19, int r20) {
        /*
            Method dump skipped, instructions count: 287
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.scankit.p.q5.a(java.lang.CharSequence, int, int, java.lang.StringBuilder, int):int");
    }

    private static void a(byte[] bArr, int i, int i2, int i3, StringBuilder sb) {
        int i4;
        if (i2 == 1 && i3 == 0) {
            sb.append((char) 913);
        } else if (i2 % 6 == 0) {
            sb.append((char) 924);
        } else {
            sb.append((char) 901);
        }
        if (i2 >= 6) {
            char[] cArr = new char[5];
            i4 = i;
            while ((i + i2) - i4 >= 6) {
                long j = 0;
                for (int i5 = 0; i5 < 6; i5++) {
                    j = (j << 8) + (bArr[i4 + i5] & 255);
                }
                for (int i6 = 0; i6 < 5; i6++) {
                    cArr[i6] = (char) (j % 900);
                    j /= 900;
                }
                for (int i7 = 4; i7 >= 0; i7--) {
                    sb.append(cArr[i7]);
                }
                i4 += 6;
            }
        } else {
            i4 = i;
        }
        while (i4 < i + i2) {
            sb.append((char) (bArr[i4] & 255));
            i4++;
        }
    }

    private static void a(String str, int i, int i2, StringBuilder sb) {
        StringBuilder sb2 = new StringBuilder((i2 / 3) + 1);
        BigInteger valueOf = BigInteger.valueOf(900L);
        BigInteger valueOf2 = BigInteger.valueOf(0L);
        int i3 = 0;
        while (i3 < i2) {
            sb2.setLength(0);
            int min = Math.min(44, i2 - i3);
            if (str.length() > 0) {
                StringBuilder sb3 = new StringBuilder("1");
                int i4 = i + i3;
                sb3.append(str.substring(i4, i4 + min));
                BigInteger bigInteger = new BigInteger(sb3.toString());
                do {
                    sb2.append((char) bigInteger.mod(valueOf).intValue());
                    bigInteger = bigInteger.divide(valueOf);
                } while (!bigInteger.equals(valueOf2));
            }
            for (int length = sb2.length() - 1; length >= 0; length--) {
                sb.append(sb2.charAt(length));
            }
            i3 += min;
        }
    }

    private static int a(CharSequence charSequence, int i) {
        return d4.a(charSequence, i);
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0029, code lost:
    
        return r1 - r6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int a(java.lang.String r5, int r6, java.nio.charset.Charset r7) throws com.huawei.hms.hmsscankit.WriterException {
        /*
            java.nio.charset.CharsetEncoder r7 = r7.newEncoder()
            int r0 = r5.length()
            r1 = r6
        L9:
            if (r1 >= r0) goto L58
            char r2 = r5.charAt(r1)
            r3 = 0
        L10:
            r4 = 13
            if (r3 >= r4) goto L26
            boolean r2 = c(r2)
            if (r2 == 0) goto L26
            int r3 = r3 + 1
            int r2 = r1 + r3
            if (r2 < r0) goto L21
            goto L26
        L21:
            char r2 = r5.charAt(r2)
            goto L10
        L26:
            if (r3 < r4) goto L2a
            int r1 = r1 - r6
            return r1
        L2a:
            char r2 = r5.charAt(r1)
            boolean r3 = r7.canEncode(r2)
            if (r3 == 0) goto L37
            int r1 = r1 + 1
            goto L9
        L37:
            com.huawei.hms.hmsscankit.WriterException r5 = new com.huawei.hms.hmsscankit.WriterException
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "Non-encodable character detected: "
            r6.<init>(r7)
            r6.append(r2)
            java.lang.String r7 = " (Unicode: "
            r6.append(r7)
            r6.append(r2)
            r7 = 41
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            r5.<init>(r6)
            throw r5
        L58:
            int r1 = r1 - r6
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.scankit.p.q5.a(java.lang.String, int, java.nio.charset.Charset):int");
    }

    private static void a(int i, StringBuilder sb) throws WriterException {
        if (i >= 0 && i < 900) {
            sb.append((char) 927);
            sb.append((char) i);
            return;
        }
        if (i < 810900) {
            sb.append((char) 926);
            sb.append((char) ((i / 900) - 1));
            sb.append((char) (i % 900));
        } else if (i < 811800) {
            sb.append((char) 925);
            sb.append((char) (810900 - i));
        } else {
            throw new WriterException("ECI number not in valid range from 0..811799, but was " + i);
        }
    }
}
