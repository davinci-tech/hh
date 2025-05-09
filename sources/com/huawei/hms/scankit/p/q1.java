package com.huawei.hms.scankit.p;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;

/* loaded from: classes9.dex */
final class q1 {

    /* renamed from: a, reason: collision with root package name */
    private static final char[] f5859a = ";<>@[\\]_`~!\r\t,:\n-.$/\"|*()?{}'".toCharArray();
    private static final char[] b = "0123456789&\r\t,:#-.$/+%*=^".toCharArray();
    private static final BigInteger[] c;

    static /* synthetic */ class a {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f5860a;

        static {
            int[] iArr = new int[b.values().length];
            f5860a = iArr;
            try {
                iArr[b.ALPHA.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f5860a[b.LOWER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f5860a[b.MIXED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f5860a[b.PUNCT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f5860a[b.ALPHA_SHIFT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f5860a[b.PUNCT_SHIFT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    enum b {
        ALPHA,
        LOWER,
        MIXED,
        PUNCT,
        ALPHA_SHIFT,
        PUNCT_SHIFT
    }

    static {
        BigInteger[] bigIntegerArr = new BigInteger[16];
        c = bigIntegerArr;
        bigIntegerArr[0] = BigInteger.ONE;
        BigInteger valueOf = BigInteger.valueOf(900L);
        bigIntegerArr[1] = valueOf;
        int i = 2;
        while (true) {
            BigInteger[] bigIntegerArr2 = c;
            if (i >= bigIntegerArr2.length) {
                return;
            }
            bigIntegerArr2[i] = bigIntegerArr2[i - 1].multiply(valueOf);
            i++;
        }
    }

    static w1 a(int[] iArr, String str, Map<l1, ?> map) throws com.huawei.hms.scankit.p.a {
        int i;
        int a2;
        StringBuilder sb = new StringBuilder(iArr.length * 2);
        Charset charset = StandardCharsets.ISO_8859_1;
        int i2 = iArr[1];
        s5 s5Var = new s5();
        Charset charset2 = charset;
        int i3 = 2;
        int i4 = i2;
        while (true) {
            int i5 = iArr[0];
            if (i3 > i5 || (i3 == i5 && sb.length() > 0)) {
                break;
            }
            if (i4 == 927) {
                a2 = i3 + 1;
                charset2 = Charset.forName(o0.a(iArr[i3]).name());
            } else {
                a2 = a(sb, i4, iArr, i3, charset2, s5Var);
            }
            if (a2 >= iArr.length) {
                throw com.huawei.hms.scankit.p.a.a();
            }
            i3 = a2 + 1;
            i4 = iArr[a2];
        }
        if (sb.length() == 0) {
            throw com.huawei.hms.scankit.p.a.a();
        }
        if (charset2 != StandardCharsets.ISO_8859_1) {
            w1 w1Var = new w1(null, sb.toString(), null, str);
            w1Var.a(s5Var);
            return w1Var;
        }
        int length = sb.length();
        byte[] bArr = new byte[length];
        for (i = 0; i < length; i++) {
            bArr[i] = (byte) sb.charAt(i);
        }
        try {
            w1 w1Var2 = new w1(null, new String(bArr, c7.a(bArr, map)), null, str);
            w1Var2.a(s5Var);
            return w1Var2;
        } catch (UnsupportedEncodingException unused) {
            throw com.huawei.hms.scankit.p.a.a();
        }
    }

    private static boolean a(int i) {
        return i == 901 || i == 924 || i == 902 || i == 928 || i == 923 || i == 922;
    }

    private static int b(int[] iArr, int i, StringBuilder sb) throws com.huawei.hms.scankit.p.a {
        int i2 = (iArr[0] - i) * 2;
        int[] iArr2 = new int[i2];
        int[] iArr3 = new int[i2];
        boolean z = false;
        int i3 = 0;
        while (i < iArr[0] && !z) {
            int i4 = i + 1;
            int i5 = iArr[i];
            if (i5 < 900) {
                iArr2[i3] = i5 / 30;
                iArr2[i3 + 1] = i5 % 30;
                i3 += 2;
            } else if (i5 == 900) {
                iArr2[i3] = 900;
                i3++;
            } else if (i5 == 913) {
                iArr2[i3] = 913;
                i += 2;
                iArr3[i3] = iArr[i4];
                i3++;
            } else {
                if (!a(i5)) {
                    throw com.huawei.hms.scankit.p.a.a();
                }
                z = true;
            }
            i = i4;
        }
        a(iArr2, iArr3, i3, sb);
        return i;
    }

    private static b[] c(StringBuilder sb, int[] iArr, b bVar, b bVar2, int[] iArr2) throws com.huawei.hms.scankit.p.a {
        b bVar3;
        int i = iArr2[1];
        if (i < 26) {
            iArr2[2] = (char) (i + 97);
        } else if (i == 900) {
            bVar = b.ALPHA;
        } else if (i != 913) {
            switch (i) {
                case 26:
                    iArr2[2] = 32;
                    break;
                case 27:
                    bVar3 = b.ALPHA_SHIFT;
                    bVar2 = bVar;
                    bVar = bVar3;
                    break;
                case 28:
                    bVar = b.MIXED;
                    break;
                case 29:
                    bVar3 = b.PUNCT_SHIFT;
                    bVar2 = bVar;
                    bVar = bVar3;
                    break;
                default:
                    throw com.huawei.hms.scankit.p.a.a();
            }
        } else {
            sb.append((char) iArr[iArr2[0]]);
        }
        return new b[]{bVar, bVar2};
    }

    private static b[] d(StringBuilder sb, int[] iArr, b bVar, b bVar2, int[] iArr2) throws com.huawei.hms.scankit.p.a {
        int i = iArr2[1];
        if (i < 25) {
            iArr2[2] = b[i];
        } else {
            if (i != 900) {
                if (i != 913) {
                    switch (i) {
                        case 25:
                            bVar = b.PUNCT;
                            break;
                        case 26:
                            iArr2[2] = 32;
                            break;
                        case 27:
                            bVar = b.LOWER;
                            break;
                        case 28:
                            break;
                        case 29:
                            bVar2 = bVar;
                            bVar = b.PUNCT_SHIFT;
                            break;
                        default:
                            throw com.huawei.hms.scankit.p.a.a();
                    }
                } else {
                    sb.append((char) iArr[iArr2[0]]);
                }
            }
            bVar = b.ALPHA;
        }
        return new b[]{bVar, bVar2};
    }

    private static b[] e(StringBuilder sb, int[] iArr, b bVar, b bVar2, int[] iArr2) throws com.huawei.hms.scankit.p.a {
        int i = iArr2[1];
        if (i < 29) {
            iArr2[2] = f5859a[i];
        } else if (i == 29 || i == 900) {
            bVar = b.ALPHA;
        } else {
            if (i != 913) {
                throw com.huawei.hms.scankit.p.a.a();
            }
            sb.append((char) iArr[iArr2[0]]);
        }
        return new b[]{bVar, bVar2};
    }

    private static b[] f(StringBuilder sb, int[] iArr, b bVar, b bVar2, int[] iArr2) throws com.huawei.hms.scankit.p.a {
        b bVar3;
        int i = iArr2[1];
        if (i < 29) {
            iArr2[2] = f5859a[i];
        } else {
            if (i == 29 || i == 900) {
                bVar3 = b.ALPHA;
                return new b[]{bVar3, bVar2};
            }
            if (i != 913) {
                throw com.huawei.hms.scankit.p.a.a();
            }
            sb.append((char) iArr[iArr2[0]]);
        }
        bVar3 = bVar2;
        return new b[]{bVar3, bVar2};
    }

    private static b[] b(StringBuilder sb, int[] iArr, b bVar, b bVar2, int[] iArr2) throws com.huawei.hms.scankit.p.a {
        b bVar3;
        int i = iArr2[1];
        if (i < 26) {
            iArr2[2] = (char) (i + 65);
        } else {
            if (i != 26) {
                if (i == 900) {
                    bVar3 = b.ALPHA;
                    return new b[]{bVar3, bVar2};
                }
                throw com.huawei.hms.scankit.p.a.a();
            }
            iArr2[2] = 32;
        }
        bVar3 = bVar2;
        return new b[]{bVar3, bVar2};
    }

    private static int a(StringBuilder sb, int i, int[] iArr, int i2, Charset charset, s5 s5Var) throws com.huawei.hms.scankit.p.a {
        if (i != 913) {
            if (i != 928) {
                switch (i) {
                    case 900:
                        return b(iArr, i2, sb);
                    case 901:
                        break;
                    case 902:
                        return a(iArr, i2, sb);
                    default:
                        switch (i) {
                            case 922:
                            case 923:
                                throw com.huawei.hms.scankit.p.a.a();
                            case 924:
                                break;
                            case 925:
                                break;
                            case 926:
                                return i2 + 2;
                            default:
                                return b(iArr, i2 - 1, sb);
                        }
                }
                return a(i, iArr, charset, i2, sb);
            }
            return a(iArr, i2, s5Var);
        }
        sb.append((char) iArr[i2]);
        return i2 + 1;
    }

    static int a(int[] iArr, int i, s5 s5Var) throws com.huawei.hms.scankit.p.a {
        int i2 = 0;
        if (i + 2 <= iArr[0]) {
            int[] iArr2 = new int[2];
            while (i2 < 2) {
                iArr2[i2] = iArr[i];
                i2++;
                i++;
            }
            try {
                s5Var.c(Integer.parseInt(a(iArr2, 2)));
                StringBuilder sb = new StringBuilder();
                int b2 = b(iArr, i, sb);
                s5Var.b(sb.toString());
                int i3 = iArr[b2] == 923 ? b2 + 1 : -1;
                a(b2, iArr, s5Var);
                if (i3 != -1) {
                    int i4 = b2 - i3;
                    if (s5Var.a()) {
                        i4--;
                    }
                    s5Var.a(Arrays.copyOfRange(iArr, i3, i4 + i3));
                }
                return b2;
            } catch (Exception unused) {
                throw com.huawei.hms.scankit.p.a.a();
            }
        }
        throw com.huawei.hms.scankit.p.a.a();
    }

    private static void a(int i, int[] iArr, s5 s5Var) throws com.huawei.hms.scankit.p.a {
        while (i < iArr[0]) {
            int i2 = iArr[i];
            if (i2 == 923) {
                int i3 = iArr[i + 1];
                if (i3 == 0) {
                    StringBuilder sb = new StringBuilder();
                    i = b(iArr, i + 2, sb);
                    s5Var.c(sb.toString());
                } else if (i3 == 3) {
                    StringBuilder sb2 = new StringBuilder();
                    i = b(iArr, i + 2, sb2);
                    s5Var.d(sb2.toString());
                } else if (i3 == 4) {
                    StringBuilder sb3 = new StringBuilder();
                    i = b(iArr, i + 2, sb3);
                    s5Var.a(sb3.toString());
                } else if (i3 == 1) {
                    StringBuilder sb4 = new StringBuilder();
                    i = a(iArr, i + 2, sb4);
                    s5Var.b(Integer.parseInt(sb4.toString()));
                } else if (i3 == 2) {
                    StringBuilder sb5 = new StringBuilder();
                    i = a(iArr, i + 2, sb5);
                    s5Var.b(Long.parseLong(sb5.toString()));
                } else if (i3 == 6) {
                    StringBuilder sb6 = new StringBuilder();
                    i = a(iArr, i + 2, sb6);
                    s5Var.a(Integer.parseInt(sb6.toString()));
                } else if (i3 == 5) {
                    StringBuilder sb7 = new StringBuilder();
                    i = a(iArr, i + 2, sb7);
                    s5Var.a(Long.parseLong(sb7.toString()));
                } else {
                    throw com.huawei.hms.scankit.p.a.a();
                }
            } else if (i2 == 922) {
                i++;
                s5Var.a(true);
            } else {
                throw com.huawei.hms.scankit.p.a.a();
            }
        }
    }

    private static void a(int[] iArr, int[] iArr2, int i, StringBuilder sb) throws com.huawei.hms.scankit.p.a {
        b bVar = b.ALPHA;
        b bVar2 = bVar;
        int i2 = 0;
        while (i2 < i) {
            int[] iArr3 = {i2, iArr[i2], 0};
            b[] bVarArr = {bVar, bVar2};
            switch (a.f5860a[bVar.ordinal()]) {
                case 1:
                    bVarArr = a(sb, iArr2, bVar, bVar2, iArr3);
                    break;
                case 2:
                    bVarArr = c(sb, iArr2, bVar, bVar2, iArr3);
                    break;
                case 3:
                    bVarArr = d(sb, iArr2, bVar, bVar2, iArr3);
                    break;
                case 4:
                    bVarArr = e(sb, iArr2, bVar, bVar2, iArr3);
                    break;
                case 5:
                    bVarArr = b(sb, iArr2, bVar, bVar2, iArr3);
                    break;
                case 6:
                    bVarArr = f(sb, iArr2, bVar, bVar2, iArr3);
                    break;
            }
            bVar = bVarArr[0];
            b bVar3 = bVarArr[1];
            int i3 = iArr3[0];
            char c2 = (char) iArr3[2];
            if (c2 != 0) {
                sb.append(c2);
            }
            i2 = i3 + 1;
            bVar2 = bVar3;
        }
    }

    private static b[] a(StringBuilder sb, int[] iArr, b bVar, b bVar2, int[] iArr2) throws com.huawei.hms.scankit.p.a {
        int i = iArr2[1];
        if (i < 26) {
            iArr2[2] = (char) (i + 65);
        } else if (i == 900) {
            bVar = b.ALPHA;
        } else if (i != 913) {
            switch (i) {
                case 26:
                    iArr2[2] = 32;
                    break;
                case 27:
                    bVar = b.LOWER;
                    break;
                case 28:
                    bVar = b.MIXED;
                    break;
                case 29:
                    bVar2 = bVar;
                    bVar = b.PUNCT_SHIFT;
                    break;
                default:
                    throw com.huawei.hms.scankit.p.a.a();
            }
        } else {
            sb.append((char) iArr[iArr2[0]]);
        }
        return new b[]{bVar, bVar2};
    }

    private static int a(int i, int[] iArr, Charset charset, int i2, StringBuilder sb) throws com.huawei.hms.scankit.p.a {
        int a2;
        int i3;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (i == 901) {
            int[] iArr2 = new int[6];
            int i4 = i2 + 1;
            int i5 = iArr[i2];
            boolean z = false;
            int i6 = 0;
            long j = 0;
            while (true) {
                i3 = iArr[0];
                if (i4 < i3 && !z) {
                    int i7 = i6 + 1;
                    iArr2[i6] = i5;
                    j = (j * 900) + i5;
                    int i8 = i4 + 1;
                    int i9 = iArr[i4];
                    if (i9 != 928) {
                        switch (i9) {
                            case 900:
                            case 901:
                            case 902:
                                break;
                            default:
                                switch (i9) {
                                    case 922:
                                    case 923:
                                    case 924:
                                        break;
                                    default:
                                        if (i7 % 5 != 0 || i7 <= 0) {
                                            i4 = i8;
                                            i5 = i9;
                                            i6 = i7;
                                            break;
                                        } else {
                                            for (int i10 = 0; i10 < 6; i10++) {
                                                byteArrayOutputStream.write((byte) (j >> ((5 - i10) * 8)));
                                            }
                                            i4 = i8;
                                            j = 0;
                                            i5 = i9;
                                            i6 = 0;
                                            break;
                                        }
                                        break;
                                }
                        }
                    }
                    z = true;
                    i5 = i9;
                    i6 = i7;
                }
            }
            if (i4 == i3 && i5 < 900) {
                iArr2[i6] = i5;
                i6++;
            }
            for (int i11 = 0; i11 < i6; i11++) {
                byteArrayOutputStream.write((byte) iArr2[i11]);
            }
            a2 = i4;
        } else {
            a2 = i == 924 ? a(i2, iArr, false, 0, 0L, byteArrayOutputStream) : i2;
        }
        sb.append(new String(byteArrayOutputStream.toByteArray(), charset));
        return a2;
    }

    private static int a(int i, int[] iArr, boolean z, int i2, long j, ByteArrayOutputStream byteArrayOutputStream) throws com.huawei.hms.scankit.p.a {
        while (i < iArr[0] && !z) {
            int i3 = i + 1;
            int i4 = iArr[i];
            if (i4 < 900) {
                i2++;
                j = (j * 900) + i4;
                i = i3;
            } else {
                if (i4 != 928) {
                    switch (i4) {
                        default:
                            switch (i4) {
                                case 922:
                                case 923:
                                case 924:
                                    break;
                                default:
                                    throw com.huawei.hms.scankit.p.a.a();
                            }
                        case 900:
                        case 901:
                        case 902:
                            z = true;
                            break;
                    }
                }
                z = true;
            }
            if (i2 % 5 == 0 && i2 > 0) {
                for (int i5 = 0; i5 < 6; i5++) {
                    byteArrayOutputStream.write((byte) (j >> ((5 - i5) * 8)));
                }
                j = 0;
                i2 = 0;
            }
        }
        return i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x003e, code lost:
    
        r10.append(a(r0, r3));
        r3 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0047, code lost:
    
        return r9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int a(int[] r8, int r9, java.lang.StringBuilder r10) throws com.huawei.hms.scankit.p.a {
        /*
            r0 = 15
            int[] r0 = new int[r0]
            r1 = 0
            r2 = r1
            r3 = r2
        L7:
            r4 = r8[r1]
            if (r9 >= r4) goto L47
            if (r2 != 0) goto L47
            int r5 = r9 + 1
            r6 = r8[r9]
            r7 = 1
            if (r5 != r4) goto L15
            r2 = r7
        L15:
            r4 = 900(0x384, float:1.261E-42)
            if (r6 >= r4) goto L1f
            r0[r3] = r6
            int r3 = r3 + 1
            r9 = r5
            goto L32
        L1f:
            if (r6 == r4) goto L31
            r2 = 901(0x385, float:1.263E-42)
            if (r6 == r2) goto L31
            r2 = 928(0x3a0, float:1.3E-42)
            if (r6 == r2) goto L31
            switch(r6) {
                case 922: goto L31;
                case 923: goto L31;
                case 924: goto L31;
                default: goto L2c;
            }
        L2c:
            com.huawei.hms.scankit.p.a r8 = com.huawei.hms.scankit.p.a.a()
            throw r8
        L31:
            r2 = r7
        L32:
            int r4 = r3 % 15
            if (r4 == 0) goto L3c
            r4 = 902(0x386, float:1.264E-42)
            if (r6 == r4) goto L3c
            if (r2 == 0) goto L7
        L3c:
            if (r3 <= 0) goto L7
            java.lang.String r3 = a(r0, r3)
            r10.append(r3)
            r3 = r1
            goto L7
        L47:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.scankit.p.q1.a(int[], int, java.lang.StringBuilder):int");
    }

    private static String a(int[] iArr, int i) throws com.huawei.hms.scankit.p.a {
        BigInteger bigInteger = BigInteger.ZERO;
        for (int i2 = 0; i2 < i; i2++) {
            bigInteger = bigInteger.add(c[(i - i2) - 1].multiply(BigInteger.valueOf(iArr[i2])));
        }
        String bigInteger2 = bigInteger.toString();
        if (bigInteger2.charAt(0) == '1') {
            return bigInteger2.substring(1);
        }
        throw com.huawei.hms.scankit.p.a.a();
    }
}
