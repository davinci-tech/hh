package com.huawei.hms.scankit.p;

import com.huawei.haf.bundle.extension.ComponentInfo;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hms.scankit.aiscan.common.BarcodeFormat;
import com.huawei.indoorequip.datastruct.MachineControlPointResponse;
import java.util.Arrays;
import java.util.Map;

/* loaded from: classes4.dex */
public final class t0 extends g5 {
    public static final int[] e = {52, ComponentInfo.PluginPay_A_N, 97, 352, 49, 304, 112, 37, 292, 100, OldToNewMotionPath.SPORT_TYPE_INDOOR_BIKE, 73, 328, 25, OldToNewMotionPath.SPORT_TYPE_CROSS_COUNTRY_RACE, 88, 13, 268, 76, 28, 259, 67, 322, 19, OldToNewMotionPath.SPORT_TYPE_ROW_MACHINE, 82, 7, 262, 70, 22, 385, 193, 448, 145, 400, com.huawei.hms.kit.awareness.barrier.internal.e.a.z, OldToNewMotionPath.SPORT_TYPE_VOLLEYBALL, 388, 196, 168, MachineControlPointResponse.OP_CODE_EXTENSION_SET_DYNAMIC_ENERGY, OldToNewMotionPath.SPORT_TYPE_PILATES, 42};

    /* renamed from: a, reason: collision with root package name */
    private final boolean f5882a;
    private final boolean b;
    private final StringBuilder c;
    private final int[] d;

    public t0() {
        this(false);
    }

    private static boolean b(int[] iArr) {
        int i = Integer.MAX_VALUE;
        int i2 = 0;
        for (int i3 : iArr) {
            if (i3 < i) {
                i = i3;
            }
            if (i3 > i2) {
                i2 = i3;
            }
        }
        return i2 / i > 6;
    }

    private static int c(int[] iArr) {
        int length = iArr.length;
        if (b(iArr)) {
            return -1;
        }
        int i = 0;
        while (true) {
            int i2 = Integer.MAX_VALUE;
            for (int i3 : iArr) {
                if (i3 < i2 && i3 > i) {
                    i2 = i3;
                }
            }
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            for (int i7 = 0; i7 < length; i7++) {
                int i8 = iArr[i7];
                if (i8 > i2) {
                    i6 |= 1 << ((length - 1) - i7);
                    i4++;
                    i5 += i8;
                }
            }
            if (i4 == 3) {
                for (int i9 = 0; i9 < length && i4 > 0; i9++) {
                    int i10 = iArr[i9];
                    if (i10 > i2) {
                        i4--;
                        if (i10 * 2 >= i5) {
                            return -1;
                        }
                    }
                }
                return i6;
            }
            if (i4 <= 3) {
                return -1;
            }
            i = i2;
        }
    }

    @Override // com.huawei.hms.scankit.p.g5
    public s6 a(int i, r rVar, Map<l1, ?> map) throws a {
        int[] iArr = this.d;
        Arrays.fill(iArr, 0);
        StringBuilder sb = this.c;
        sb.setLength(0);
        int[] a2 = a(rVar, iArr);
        int c = rVar.c(a2[1]);
        int e2 = rVar.e();
        while (true) {
            g5.a(rVar, c, iArr);
            int c2 = c(iArr);
            if (c2 < 0) {
                throw a.a();
            }
            char a3 = a(c2);
            sb.append(a3);
            int i2 = c;
            for (int i3 : iArr) {
                i2 += i3;
            }
            int c3 = rVar.c(i2);
            if (a3 == '*') {
                sb.setLength(sb.length() - 1);
                int i4 = 0;
                for (int i5 : iArr) {
                    i4 += i5;
                }
                if (c3 == e2 || ((c3 - c) - i4) * 5 >= i4) {
                    return a(sb, a2, c, i4, i);
                }
                throw a.a();
            }
            c = c3;
        }
    }

    public t0(boolean z) {
        this(z, false);
    }

    public t0(boolean z, boolean z2) {
        this.f5882a = z;
        this.b = z2;
        this.c = new StringBuilder(20);
        this.d = new int[9];
    }

    private s6 a(StringBuilder sb, int[] iArr, int i, int i2, int i3) throws a {
        String sb2;
        if (this.f5882a) {
            int length = sb.length() - 1;
            int i4 = 0;
            for (int i5 = 0; i5 < length; i5++) {
                i4 += "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%".indexOf(this.c.charAt(i5));
            }
            if (sb.charAt(length) == "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%".charAt(i4 % 43)) {
                sb.setLength(length);
            } else {
                throw a.a();
            }
        }
        if (sb.length() != 0) {
            if (this.b) {
                sb2 = a(sb);
            } else {
                sb2 = sb.toString();
            }
            float f = i + i2;
            float f2 = i3;
            return new s6(sb2, null, new u6[]{new u6(iArr[0], f2), new u6(f, f2)}, BarcodeFormat.CODE_39);
        }
        throw a.a();
    }

    private static int[] a(r rVar, int[] iArr) throws a {
        int e2 = rVar.e();
        int c = rVar.c(0);
        int length = iArr.length;
        boolean z = false;
        int i = 0;
        int i2 = c;
        while (c < e2) {
            if (rVar.b(c) != z) {
                if (i >= 0 && i < iArr.length) {
                    iArr[i] = iArr[i] + 1;
                } else {
                    throw a.a();
                }
            } else {
                if (i != length - 1) {
                    i++;
                } else {
                    if (c(iArr) == 148 && rVar.a(Math.max(0, i2 - ((c - i2) / 5)), i2, false, true)) {
                        return new int[]{i2, c};
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
            c++;
        }
        throw a.a();
    }

    private static char a(int i) throws a {
        int i2 = 0;
        while (true) {
            int[] iArr = e;
            if (i2 >= iArr.length) {
                if (i == 148) {
                    return '*';
                }
                throw a.a();
            }
            if (iArr[i2] == i) {
                return "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%".charAt(i2);
            }
            i2++;
        }
    }

    private static String a(CharSequence charSequence) throws a {
        int length = charSequence.length();
        StringBuilder sb = new StringBuilder(length);
        int i = 0;
        while (i < length) {
            char charAt = charSequence.charAt(i);
            if (charAt != '+' && charAt != '$' && charAt != '%' && charAt != '/') {
                sb.append(charAt);
            } else {
                i++;
                sb.append(a(charAt, charSequence.charAt(i)));
            }
            i++;
        }
        return sb.toString();
    }

    private static char a(char c, char c2) throws a {
        int i;
        if (c != '$') {
            if (c != '%') {
                if (c != '+') {
                    if (c == '/') {
                        if (c2 < 'A' || c2 > 'O') {
                            if (c2 == 'Z') {
                                return ':';
                            }
                            throw a.a();
                        }
                        i = c2 - ' ';
                    }
                    return (char) 0;
                }
                if (c2 < 'A' || c2 > 'Z') {
                    throw a.a();
                }
                i = c2 + ' ';
            } else if (c2 >= 'A' && c2 <= 'E') {
                i = c2 - '&';
            } else if (c2 >= 'F' && c2 <= 'J') {
                i = c2 - 11;
            } else if (c2 >= 'K' && c2 <= 'O') {
                i = c2 + 16;
            } else {
                if (c2 < 'P' || c2 > 'T') {
                    if (c2 != 'U') {
                        if (c2 == 'V') {
                            return '@';
                        }
                        if (c2 == 'W') {
                            return '`';
                        }
                        if (c2 == 'X' || c2 == 'Y' || c2 == 'Z') {
                            return (char) 127;
                        }
                        throw a.a();
                    }
                    return (char) 0;
                }
                i = c2 + '+';
            }
        } else {
            if (c2 < 'A' || c2 > 'Z') {
                throw a.a();
            }
            i = c2 - '@';
        }
        return (char) i;
    }
}
