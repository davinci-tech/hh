package com.huawei.hms.scankit.p;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
final class r1 {

    /* renamed from: a, reason: collision with root package name */
    private static final char[] f5868a = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ $%*+-./:".toCharArray();

    static /* synthetic */ class a {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f5869a;

        static {
            int[] iArr = new int[u4.values().length];
            f5869a = iArr;
            try {
                iArr[u4.NUMERIC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f5869a[u4.ALPHANUMERIC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f5869a[u4.BYTE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f5869a[u4.KANJI.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f5869a[u4.TERMINATOR.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f5869a[u4.FNC1_FIRST_POSITION.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f5869a[u4.FNC1_SECOND_POSITION.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f5869a[u4.STRUCTURED_APPEND.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f5869a[u4.ECI.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f5869a[u4.HANZI.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    static w1 a(byte[] bArr, b8 b8Var, b3 b3Var, Map<l1, ?> map) throws com.huawei.hms.scankit.p.a {
        int i;
        int i2;
        w wVar = new w(bArr);
        StringBuilder sb = new StringBuilder(50);
        ArrayList arrayList = new ArrayList(1);
        int i3 = -1;
        int i4 = -1;
        int i5 = 0;
        while (true) {
            try {
                u4 a2 = wVar.a() < 4 ? u4.TERMINATOR : u4.a(wVar.a(4));
                int[] iArr = {i5, i3, i4};
                a(a2, wVar, sb, b8Var, iArr, null, arrayList, map);
                i5 = iArr[0] == 1 ? 1 : 0;
                i = iArr[1];
                i2 = iArr[2];
                if (a2 == u4.TERMINATOR) {
                    break;
                }
                i3 = i;
                i4 = i2;
            } catch (IllegalArgumentException unused) {
                throw com.huawei.hms.scankit.p.a.a();
            }
        }
        return new w1(bArr, sb.toString(), arrayList.isEmpty() ? null : arrayList, b3Var == null ? null : b3Var.toString(), i, i2);
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
            if (w7.a(bArr, i2)) {
                int i5 = i2 + 1;
                if (w7.a(bArr, i5)) {
                    bArr[i2] = (byte) (i4 >> 8);
                    bArr[i5] = (byte) i4;
                    i2 += 2;
                    i--;
                }
            }
            throw new ArrayIndexOutOfBoundsException();
        }
        try {
            sb.append(new String(bArr, "SJIS"));
        } catch (UnsupportedEncodingException unused) {
            throw com.huawei.hms.scankit.p.a.a();
        }
    }

    private static void c(w wVar, StringBuilder sb, int i) throws com.huawei.hms.scankit.p.a {
        while (i >= 3) {
            if (wVar.a() < 10) {
                throw com.huawei.hms.scankit.p.a.a();
            }
            int a2 = wVar.a(10);
            if (a2 >= 1000) {
                throw com.huawei.hms.scankit.p.a.a();
            }
            sb.append(a(a2 / 100));
            sb.append(a((a2 / 10) % 10));
            sb.append(a(a2 % 10));
            i -= 3;
        }
        if (i == 2) {
            if (wVar.a() < 7) {
                throw com.huawei.hms.scankit.p.a.a();
            }
            int a3 = wVar.a(7);
            if (a3 >= 100) {
                throw com.huawei.hms.scankit.p.a.a();
            }
            sb.append(a(a3 / 10));
            sb.append(a(a3 % 10));
            return;
        }
        if (i == 1) {
            if (wVar.a() < 4) {
                throw com.huawei.hms.scankit.p.a.a();
            }
            int a4 = wVar.a(4);
            if (a4 >= 10) {
                throw com.huawei.hms.scankit.p.a.a();
            }
            sb.append(a(a4));
        }
    }

    private static void a(u4 u4Var, w wVar, StringBuilder sb, b8 b8Var, int[] iArr, o0 o0Var, List<byte[]> list, Map<l1, ?> map) throws com.huawei.hms.scankit.p.a {
        int[] iArr2 = a.f5869a;
        switch (iArr2[u4Var.ordinal()]) {
            case 5:
                return;
            case 6:
            case 7:
                iArr[0] = 1;
                return;
            case 8:
                if (wVar.a() >= 16) {
                    iArr[1] = wVar.a(8);
                    iArr[2] = wVar.a(8);
                    return;
                }
                throw com.huawei.hms.scankit.p.a.a();
            case 9:
                if (o0.a(a(wVar)) == null) {
                    throw com.huawei.hms.scankit.p.a.a();
                }
                return;
            case 10:
                int a2 = wVar.a(4);
                int a3 = wVar.a(u4Var.a(b8Var));
                if (a2 == 1) {
                    a(wVar, sb, a3);
                    return;
                }
                return;
            default:
                int a4 = wVar.a(u4Var.a(b8Var));
                int i = iArr2[u4Var.ordinal()];
                if (i == 1) {
                    c(wVar, sb, a4);
                    return;
                }
                if (i == 2) {
                    a(wVar, sb, a4, iArr[0] == 1);
                    return;
                } else if (i == 3) {
                    a(wVar, sb, a4, o0Var, list, map);
                    return;
                } else {
                    if (i != 4) {
                        throw com.huawei.hms.scankit.p.a.a();
                    }
                    b(wVar, sb, a4);
                    return;
                }
        }
    }

    private static void a(w wVar, StringBuilder sb, int i) throws com.huawei.hms.scankit.p.a {
        if (i * 13 <= wVar.a()) {
            byte[] bArr = new byte[i * 2];
            int i2 = 0;
            while (i > 0) {
                int a2 = wVar.a(13);
                int i3 = ((a2 / 96) << 8) | (a2 % 96);
                int i4 = i3 + (i3 < 959 ? 41377 : 42657);
                if (w7.a(bArr, i2)) {
                    int i5 = i2 + 1;
                    if (w7.a(bArr, i5)) {
                        bArr[i2] = (byte) ((i4 >> 8) & 255);
                        bArr[i5] = (byte) (i4 & 255);
                        i2 += 2;
                        i--;
                    }
                }
                throw new ArrayIndexOutOfBoundsException();
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

    private static void a(w wVar, StringBuilder sb, int i, o0 o0Var, Collection<byte[]> collection, Map<l1, ?> map) throws com.huawei.hms.scankit.p.a {
        String name;
        if (i * 8 <= wVar.a()) {
            byte[] bArr = new byte[i];
            for (int i2 = 0; i2 < i; i2++) {
                bArr[i2] = (byte) wVar.a(8);
            }
            if (o0Var == null) {
                name = c7.a(bArr, map);
            } else {
                name = o0Var.name();
            }
            try {
                sb.append(new String(bArr, name));
                collection.add(bArr);
                return;
            } catch (UnsupportedEncodingException unused) {
                throw com.huawei.hms.scankit.p.a.a();
            }
        }
        throw com.huawei.hms.scankit.p.a.a();
    }

    private static char a(int i) throws com.huawei.hms.scankit.p.a {
        char[] cArr = f5868a;
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

    private static int a(w wVar) throws com.huawei.hms.scankit.p.a {
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
}
