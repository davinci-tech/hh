package com.huawei.hms.scankit.p;

import org.apache.commons.io.FilenameUtils;

/* loaded from: classes4.dex */
public final class q0 extends h5 {

    /* renamed from: a, reason: collision with root package name */
    private static final char[] f5858a;
    private static final char[] b = {'T', 'N', '*', 'E'};
    private static final char[] c = {'/', ':', '+', FilenameUtils.EXTENSION_SEPARATOR};
    private static final char d;

    static {
        char[] cArr = {'A', 'B', 'C', 'D'};
        f5858a = cArr;
        d = cArr[0];
    }

    @Override // com.huawei.hms.scankit.p.h5
    public boolean[] a(String str) {
        int i;
        if (str.length() < 2) {
            StringBuilder sb = new StringBuilder();
            char c2 = d;
            sb.append(c2);
            sb.append(str);
            sb.append(c2);
            str = sb.toString();
        } else {
            char upperCase = Character.toUpperCase(str.charAt(0));
            char upperCase2 = Character.toUpperCase(str.charAt(str.length() - 1));
            char[] cArr = f5858a;
            boolean a2 = p0.a(cArr, upperCase);
            boolean a3 = p0.a(cArr, upperCase2);
            char[] cArr2 = b;
            boolean a4 = p0.a(cArr2, upperCase);
            boolean a5 = p0.a(cArr2, upperCase2);
            if (a2) {
                if (!a3) {
                    throw new IllegalArgumentException("Invalid start/end guards: error contents");
                }
            } else if (!a4) {
                if (a3 || a5) {
                    throw new IllegalArgumentException("Invalid start/end guards: error contents");
                }
                StringBuilder sb2 = new StringBuilder();
                char c3 = d;
                sb2.append(c3);
                sb2.append(str);
                sb2.append(c3);
                str = sb2.toString();
            } else if (!a5) {
                throw new IllegalArgumentException("Invalid start/end guards: error contents");
            }
        }
        int i2 = 20;
        for (int i3 = 1; i3 < str.length() - 1; i3++) {
            if (Character.isDigit(str.charAt(i3)) || str.charAt(i3) == '-' || str.charAt(i3) == '$') {
                i2 += 9;
            } else {
                if (!p0.a(c, str.charAt(i3))) {
                    throw new IllegalArgumentException("Cannot encode : '" + str.charAt(i3) + '\'');
                }
                i2 += 10;
            }
        }
        boolean[] zArr = new boolean[i2 + (str.length() - 1)];
        int i4 = 0;
        for (int i5 = 0; i5 < str.length(); i5++) {
            char upperCase3 = Character.toUpperCase(str.charAt(i5));
            if (i5 == 0 || i5 == str.length() - 1) {
                if (upperCase3 == '*') {
                    upperCase3 = 'C';
                } else if (upperCase3 == 'E') {
                    upperCase3 = 'D';
                } else if (upperCase3 == 'N') {
                    upperCase3 = 'B';
                } else if (upperCase3 == 'T') {
                    upperCase3 = 'A';
                }
            }
            int i6 = 0;
            while (true) {
                char[] cArr3 = p0.e;
                if (i6 >= cArr3.length) {
                    i = 0;
                    break;
                }
                if (upperCase3 == cArr3[i6]) {
                    i = p0.f[i6];
                    break;
                }
                i6++;
            }
            int i7 = 0;
            int i8 = 0;
            boolean z = true;
            while (i7 < 7) {
                zArr[i4] = z;
                i4++;
                if (((i >> (6 - i7)) & 1) == 0 || i8 == 1) {
                    z = !z;
                    i7++;
                    i8 = 0;
                } else {
                    i8++;
                }
            }
            if (i5 < str.length() - 1) {
                zArr[i4] = false;
                i4++;
            }
        }
        return zArr;
    }
}
