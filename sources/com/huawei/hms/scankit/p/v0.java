package com.huawei.hms.scankit.p;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hms.scankit.aiscan.common.BarcodeFormat;
import java.util.Arrays;
import java.util.Map;

/* loaded from: classes4.dex */
public final class v0 extends g5 {
    private static final char[] c = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".toCharArray();
    public static final int[] d;
    private static final int e;

    /* renamed from: a, reason: collision with root package name */
    private final StringBuilder f5894a = new StringBuilder(20);
    private final int[] b = new int[6];

    static {
        int[] iArr = {276, 328, 324, 322, 296, 292, 290, 336, OldToNewMotionPath.SPORT_TYPE_ROW_MACHINE, OldToNewMotionPath.SPORT_TYPE_OPEN_AREA_SWIM, 424, 420, 418, 404, 402, 394, 360, 356, 354, 308, 282, 344, 332, 326, 300, OldToNewMotionPath.SPORT_TYPE_BODY_BUILDING, 436, 434, 428, TypedValues.CycleType.TYPE_CUSTOM_WAVE_SHAPE, 406, 410, 364, 358, 310, 314, 302, 468, 466, 458, 366, 374, 430, 294, 474, 470, 306, 350};
        d = iArr;
        e = iArr[47];
    }

    private static int b(int[] iArr) {
        int i = 0;
        for (int i2 : iArr) {
            i += i2;
        }
        int length = iArr.length;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            int round = Math.round((iArr[i4] * 9.0f) / i);
            if (round < 1 || round > 4) {
                return -1;
            }
            if ((i4 & 1) == 0) {
                for (int i5 = 0; i5 < round; i5++) {
                    i3 = (i3 << 1) | 1;
                }
            } else {
                i3 <<= round;
            }
        }
        return i3;
    }

    @Override // com.huawei.hms.scankit.p.g5
    public s6 a(int i, r rVar, Map<l1, ?> map) throws a {
        int c2 = rVar.c(a(rVar)[1]);
        int e2 = rVar.e();
        int[] iArr = this.b;
        Arrays.fill(iArr, 0);
        StringBuilder sb = this.f5894a;
        sb.setLength(0);
        while (true) {
            g5.a(rVar, c2, iArr);
            int b = b(iArr);
            if (b < 0) {
                throw a.a();
            }
            char a2 = a(b);
            sb.append(a2);
            int i2 = c2;
            for (int i3 : iArr) {
                i2 += i3;
            }
            int c3 = rVar.c(i2);
            if (a2 == '*') {
                sb.deleteCharAt(sb.length() - 1);
                int i4 = 0;
                for (int i5 : iArr) {
                    i4 += i5;
                }
                if (c3 == e2 || !rVar.b(c3)) {
                    throw a.a();
                }
                if (sb.length() < 2) {
                    throw a.a();
                }
                a(sb);
                sb.setLength(sb.length() - 2);
                float f = i;
                return new s6(b(sb), null, new u6[]{new u6(r14[0], f), new u6(c2 + ((i4 * 10) / 9), f)}, BarcodeFormat.CODE_93);
            }
            c2 = c3;
        }
    }

    private static String b(CharSequence charSequence) throws a {
        int length = charSequence.length();
        StringBuilder sb = new StringBuilder(length);
        int i = 0;
        while (i < length) {
            char charAt = charSequence.charAt(i);
            if (charAt < 'a' || charAt > 'd') {
                sb.append(charAt);
            } else if (i < length - 1) {
                i++;
                sb.append(a(charAt, charSequence.charAt(i)));
            } else {
                throw a.a();
            }
            i++;
        }
        return sb.toString();
    }

    private int[] a(r rVar) throws a {
        int e2 = rVar.e();
        int c2 = rVar.c(0);
        Arrays.fill(this.b, 0);
        int[] iArr = this.b;
        int length = iArr.length;
        boolean z = false;
        int i = 0;
        int i2 = c2;
        while (c2 < e2) {
            if (rVar.b(c2) != z) {
                if (i >= 0 && i < iArr.length) {
                    iArr[i] = iArr[i] + 1;
                } else {
                    throw a.a();
                }
            } else {
                if (i != length - 1) {
                    i++;
                } else {
                    if (b(iArr) == e) {
                        return new int[]{i2, c2};
                    }
                    i2 += iArr[0] + iArr[1];
                    int i3 = i - 1;
                    System.arraycopy(iArr, 2, iArr, 0, i3);
                    iArr[i3] = 0;
                    iArr[i] = 0;
                    i--;
                }
                iArr[i] = 1;
                z = !z;
            }
            c2++;
        }
        throw a.a();
    }

    private static char a(int i) throws a {
        int i2 = 0;
        while (true) {
            int[] iArr = d;
            if (i2 < iArr.length) {
                if (iArr[i2] == i) {
                    return c[i2];
                }
                i2++;
            } else {
                throw a.a();
            }
        }
    }

    private static char a(char c2, char c3) throws a {
        int i;
        switch (c2) {
            case 'a':
                if (c3 >= 'A' && c3 <= 'Z') {
                    i = c3 - '@';
                    break;
                } else {
                    throw a.a();
                }
            case 'b':
                if (c3 >= 'A' && c3 <= 'E') {
                    i = c3 - '&';
                    break;
                } else if (c3 >= 'F' && c3 <= 'J') {
                    i = c3 - 11;
                    break;
                } else if (c3 >= 'K' && c3 <= 'O') {
                    i = c3 + 16;
                    break;
                } else if (c3 >= 'P' && c3 <= 'S') {
                    i = c3 + '+';
                    break;
                } else {
                    if (c3 < 'T' || c3 > 'Z') {
                        throw a.a();
                    }
                    return (char) 127;
                }
                break;
            case 'c':
                if (c3 >= 'A' && c3 <= 'O') {
                    i = c3 - ' ';
                    break;
                } else {
                    if (c3 == 'Z') {
                        return ':';
                    }
                    throw a.a();
                }
            case 'd':
                if (c3 >= 'A' && c3 <= 'Z') {
                    i = c3 + ' ';
                    break;
                } else {
                    throw a.a();
                }
            default:
                return (char) 0;
        }
        return (char) i;
    }

    private static void a(CharSequence charSequence) throws a {
        int length = charSequence.length();
        a(charSequence, length - 2, 20);
        a(charSequence, length - 1, 15);
    }

    private static void a(CharSequence charSequence, int i, int i2) throws a {
        int i3 = 0;
        int i4 = 1;
        for (int i5 = i - 1; i5 >= 0; i5--) {
            i3 += "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".indexOf(charSequence.charAt(i5)) * i4;
            i4++;
            if (i4 > i2) {
                i4 = 1;
            }
        }
        if (charSequence.charAt(i) != c[i3 % 47]) {
            throw a.a();
        }
    }
}
