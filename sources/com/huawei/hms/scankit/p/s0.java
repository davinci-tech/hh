package com.huawei.hms.scankit.p;

import com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap;
import com.huawei.hms.hmsscankit.WriterException;
import com.huawei.hms.scankit.aiscan.common.BarcodeFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes4.dex */
public final class s0 extends h5 {

    enum a {
        UNCODABLE,
        ONE_DIGIT,
        TWO_DIGITS,
        FNC_1
    }

    @Override // com.huawei.hms.scankit.p.h5, com.huawei.hms.scankit.p.l8
    public s a(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<u2, ?> map) throws WriterException {
        if (barcodeFormat == BarcodeFormat.CODE_128) {
            return super.a(str, barcodeFormat, i, i2, map);
        }
        throw new IllegalArgumentException("Can only encode CODE_128, but got " + barcodeFormat);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.huawei.hms.scankit.p.h5
    public boolean[] a(String str) {
        int length = str.length();
        if (length >= 1 && length <= 80) {
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                char charAt = str.charAt(i2);
                if (charAt < ' ' || charAt > '~') {
                    switch (charAt) {
                        case 241:
                        case InterfaceHiMap.POLY_LINE_MAX_SIZE /* 242 */:
                        case 243:
                        case 244:
                            break;
                        default:
                            throw new IllegalArgumentException("Bad character in input: " + charAt);
                    }
                }
            }
            ArrayList<int[]> arrayList = new ArrayList();
            int i3 = 1;
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            while (i4 < length) {
                int a2 = a(str, i4, i6);
                int i7 = 100;
                if (a2 == i6) {
                    switch (str.charAt(i4)) {
                        case 241:
                            i7 = 102;
                            i4++;
                            break;
                        case InterfaceHiMap.POLY_LINE_MAX_SIZE /* 242 */:
                            i7 = 97;
                            i4++;
                            break;
                        case 243:
                            i7 = 96;
                            i4++;
                            break;
                        case 244:
                            i4++;
                            break;
                        default:
                            if (i6 == 100) {
                                i7 = str.charAt(i4) - ' ';
                            } else {
                                try {
                                    i7 = Integer.parseInt(str.substring(i4, i4 + 2));
                                    i4++;
                                } catch (NumberFormatException unused) {
                                    throw new IllegalArgumentException("contents substring can not format integer");
                                }
                            }
                            i4++;
                            break;
                    }
                } else {
                    i7 = i6 == 0 ? a2 == 100 ? 104 : 105 : a2;
                    i6 = a2;
                }
                arrayList.add(r0.f5867a[i7]);
                i5 += i7 * i3;
                if (i4 != 0) {
                    i3++;
                }
            }
            int[][] iArr = r0.f5867a;
            arrayList.add(iArr[i5 % 103]);
            arrayList.add(iArr[106]);
            int i8 = 0;
            for (int[] iArr2 : arrayList) {
                for (int i9 : iArr2) {
                    i8 += i9;
                }
            }
            boolean[] zArr = new boolean[i8];
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                i += h5.a(zArr, i, (int[]) it.next(), true);
            }
            return zArr;
        }
        throw new IllegalArgumentException("Contents length should be between 1 and 80 characters, but got " + length);
    }

    private static a a(CharSequence charSequence, int i) {
        int length = charSequence.length();
        if (i >= length) {
            return a.UNCODABLE;
        }
        char charAt = charSequence.charAt(i);
        if (charAt == 241) {
            return a.FNC_1;
        }
        if (charAt < '0' || charAt > '9') {
            return a.UNCODABLE;
        }
        int i2 = i + 1;
        if (i2 >= length) {
            return a.ONE_DIGIT;
        }
        char charAt2 = charSequence.charAt(i2);
        if (charAt2 >= '0' && charAt2 <= '9') {
            return a.TWO_DIGITS;
        }
        return a.ONE_DIGIT;
    }

    private static int a(CharSequence charSequence, int i, int i2) {
        a aVar;
        a a2;
        a a3;
        a a4 = a(charSequence, i);
        a aVar2 = a.UNCODABLE;
        if (a4 != aVar2 && a4 != (aVar = a.ONE_DIGIT)) {
            if (i2 == 99) {
                return 99;
            }
            if (i2 == 100) {
                a aVar3 = a.FNC_1;
                if (a4 == aVar3 || (a2 = a(charSequence, i + 2)) == aVar2 || a2 == aVar) {
                    return 100;
                }
                if (a2 == aVar3) {
                    return a(charSequence, i + 3) == a.TWO_DIGITS ? 99 : 100;
                }
                int i3 = i + 4;
                while (true) {
                    a3 = a(charSequence, i3);
                    if (a3 != a.TWO_DIGITS) {
                        break;
                    }
                    i3 += 2;
                }
                return a3 == a.ONE_DIGIT ? 100 : 99;
            }
            if (a4 == a.FNC_1) {
                a4 = a(charSequence, i + 1);
            }
            if (a4 == a.TWO_DIGITS) {
                return 99;
            }
        }
        return 100;
    }
}
