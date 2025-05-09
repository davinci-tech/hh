package com.huawei.hms.scankit.p;

import com.huawei.hms.scankit.aiscan.common.BarcodeFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public final class r0 extends g5 {

    /* renamed from: a, reason: collision with root package name */
    public static final int[][] f5867a;

    static {
        int[] iArr = new int[6];
        // fill-array-data instruction
        iArr[0] = 1;
        iArr[1] = 2;
        iArr[2] = 2;
        iArr[3] = 2;
        iArr[4] = 3;
        iArr[5] = 1;
        f5867a = new int[][]{new int[]{2, 1, 2, 2, 2, 2}, new int[]{2, 2, 2, 1, 2, 2}, new int[]{2, 2, 2, 2, 2, 1}, new int[]{1, 2, 1, 2, 2, 3}, new int[]{1, 2, 1, 3, 2, 2}, new int[]{1, 3, 1, 2, 2, 2}, new int[]{1, 2, 2, 2, 1, 3}, new int[]{1, 2, 2, 3, 1, 2}, new int[]{1, 3, 2, 2, 1, 2}, new int[]{2, 2, 1, 2, 1, 3}, new int[]{2, 2, 1, 3, 1, 2}, new int[]{2, 3, 1, 2, 1, 2}, new int[]{1, 1, 2, 2, 3, 2}, new int[]{1, 2, 2, 1, 3, 2}, iArr, new int[]{1, 1, 3, 2, 2, 2}, new int[]{1, 2, 3, 1, 2, 2}, new int[]{1, 2, 3, 2, 2, 1}, new int[]{2, 2, 3, 2, 1, 1}, new int[]{2, 2, 1, 1, 3, 2}, new int[]{2, 2, 1, 2, 3, 1}, new int[]{2, 1, 3, 2, 1, 2}, new int[]{2, 2, 3, 1, 1, 2}, new int[]{3, 1, 2, 1, 3, 1}, new int[]{3, 1, 1, 2, 2, 2}, new int[]{3, 2, 1, 1, 2, 2}, new int[]{3, 2, 1, 2, 2, 1}, new int[]{3, 1, 2, 2, 1, 2}, new int[]{3, 2, 2, 1, 1, 2}, new int[]{3, 2, 2, 2, 1, 1}, new int[]{2, 1, 2, 1, 2, 3}, new int[]{2, 1, 2, 3, 2, 1}, new int[]{2, 3, 2, 1, 2, 1}, new int[]{1, 1, 1, 3, 2, 3}, new int[]{1, 3, 1, 1, 2, 3}, new int[]{1, 3, 1, 3, 2, 1}, new int[]{1, 1, 2, 3, 1, 3}, new int[]{1, 3, 2, 1, 1, 3}, new int[]{1, 3, 2, 3, 1, 1}, new int[]{2, 1, 1, 3, 1, 3}, new int[]{2, 3, 1, 1, 1, 3}, new int[]{2, 3, 1, 3, 1, 1}, new int[]{1, 1, 2, 1, 3, 3}, new int[]{1, 1, 2, 3, 3, 1}, new int[]{1, 3, 2, 1, 3, 1}, new int[]{1, 1, 3, 1, 2, 3}, new int[]{1, 1, 3, 3, 2, 1}, new int[]{1, 3, 3, 1, 2, 1}, new int[]{3, 1, 3, 1, 2, 1}, new int[]{2, 1, 1, 3, 3, 1}, new int[]{2, 3, 1, 1, 3, 1}, new int[]{2, 1, 3, 1, 1, 3}, new int[]{2, 1, 3, 3, 1, 1}, new int[]{2, 1, 3, 1, 3, 1}, new int[]{3, 1, 1, 1, 2, 3}, new int[]{3, 1, 1, 3, 2, 1}, new int[]{3, 3, 1, 1, 2, 1}, new int[]{3, 1, 2, 1, 1, 3}, new int[]{3, 1, 2, 3, 1, 1}, new int[]{3, 3, 2, 1, 1, 1}, new int[]{3, 1, 4, 1, 1, 1}, new int[]{2, 2, 1, 4, 1, 1}, new int[]{4, 3, 1, 1, 1, 1}, new int[]{1, 1, 1, 2, 2, 4}, new int[]{1, 1, 1, 4, 2, 2}, new int[]{1, 2, 1, 1, 2, 4}, new int[]{1, 2, 1, 4, 2, 1}, new int[]{1, 4, 1, 1, 2, 2}, new int[]{1, 4, 1, 2, 2, 1}, new int[]{1, 1, 2, 2, 1, 4}, new int[]{1, 1, 2, 4, 1, 2}, new int[]{1, 2, 2, 1, 1, 4}, new int[]{1, 2, 2, 4, 1, 1}, new int[]{1, 4, 2, 1, 1, 2}, new int[]{1, 4, 2, 2, 1, 1}, new int[]{2, 4, 1, 2, 1, 1}, new int[]{2, 2, 1, 1, 1, 4}, new int[]{4, 1, 3, 1, 1, 1}, new int[]{2, 4, 1, 1, 1, 2}, new int[]{1, 3, 4, 1, 1, 1}, new int[]{1, 1, 1, 2, 4, 2}, new int[]{1, 2, 1, 1, 4, 2}, new int[]{1, 2, 1, 2, 4, 1}, new int[]{1, 1, 4, 2, 1, 2}, new int[]{1, 2, 4, 1, 1, 2}, new int[]{1, 2, 4, 2, 1, 1}, new int[]{4, 1, 1, 2, 1, 2}, new int[]{4, 2, 1, 1, 1, 2}, new int[]{4, 2, 1, 2, 1, 1}, new int[]{2, 1, 2, 1, 4, 1}, new int[]{2, 1, 4, 1, 2, 1}, new int[]{4, 1, 2, 1, 2, 1}, new int[]{1, 1, 1, 1, 4, 3}, new int[]{1, 1, 1, 3, 4, 1}, new int[]{1, 3, 1, 1, 4, 1}, new int[]{1, 1, 4, 1, 1, 3}, new int[]{1, 1, 4, 3, 1, 1}, new int[]{4, 1, 1, 1, 1, 3}, new int[]{4, 1, 1, 3, 1, 1}, new int[]{1, 1, 3, 1, 4, 1}, new int[]{1, 1, 4, 1, 3, 1}, new int[]{3, 1, 1, 1, 4, 1}, new int[]{4, 1, 1, 1, 3, 1}, new int[]{2, 1, 1, 4, 1, 2}, new int[]{2, 1, 1, 2, 1, 4}, new int[]{2, 1, 1, 2, 3, 2}, new int[]{2, 3, 3, 1, 1, 1, 2}};
    }

    private static boolean a(r rVar, int i, int i2) {
        return rVar.a(i, i2, false, false);
    }

    private int[] c(StringBuilder sb, int[] iArr) throws a {
        int i;
        int i2;
        int i3 = iArr[0];
        int i4 = iArr[1] == 1 ? 1 : 0;
        int i5 = iArr[2] == 1 ? 1 : 0;
        int i6 = iArr[3] == 1 ? 1 : 0;
        int i7 = iArr[4];
        int i8 = iArr[5] == 1 ? 1 : 0;
        int i9 = iArr[6] == 1 ? 1 : 0;
        if (i3 < 100) {
            if (i3 < 10) {
                sb.append('0');
            }
            sb.append(i3);
            i = i6;
        } else {
            i = i3 == 106 ? i6 : 0;
            if (i3 != 106) {
                switch (i3) {
                    case 100:
                        i2 = 100;
                        break;
                    case 101:
                        i2 = 101;
                        break;
                    case 102:
                        break;
                    default:
                        throw a.a();
                }
                return new int[]{i3, i4, i5, i, i2, i8, i9};
            }
            i9 = 1;
        }
        i2 = i7;
        return new int[]{i3, i4, i5, i, i2, i8, i9};
    }

    private static int[] a(r rVar) throws a {
        int e = rVar.e();
        int c = rVar.c(0);
        int[] iArr = new int[6];
        boolean z = false;
        int i = 0;
        int i2 = c;
        while (c < e) {
            if (rVar.b(c) != z) {
                iArr[i] = iArr[i] + 1;
            } else {
                if (i == 5) {
                    int i3 = -1;
                    float f = 0.25f;
                    for (int i4 = 103; i4 <= 105; i4++) {
                        float a2 = g5.a(iArr, f5867a[i4], 0.7f);
                        if (a2 < f) {
                            i3 = i4;
                            f = a2;
                        }
                    }
                    if (i3 >= 0) {
                        return new int[]{i2, c, i3};
                    }
                    i2 += iArr[0] + iArr[1];
                    int i5 = i - 1;
                    System.arraycopy(iArr, 2, iArr, 0, i5);
                    iArr[i5] = 0;
                    iArr[i] = 0;
                    i = i5;
                } else {
                    i++;
                }
                iArr[i] = 1;
                z = !z;
            }
            c++;
        }
        throw a.a();
    }

    private static float b(r rVar, int[] iArr, int i) {
        int[] iArr2 = new int[7];
        System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
        for (int i2 : iArr) {
            i += i2;
        }
        boolean z = true;
        int i3 = 0;
        while (z && i < rVar.e()) {
            if (rVar.b(i)) {
                i3++;
                i++;
            } else {
                iArr2[6] = i3;
                z = false;
            }
        }
        int[][] iArr3 = f5867a;
        return g5.a(iArr2, iArr3[iArr3.length - 1], 0.7f);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private int[] b(StringBuilder sb, int[] iArr) throws a {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6 = iArr[0];
        int i7 = 1;
        int i8 = iArr[1] == 1 ? 1 : 0;
        int i9 = iArr[2] == 1 ? 1 : 0;
        int i10 = iArr[3] == 1 ? 1 : 0;
        int i11 = iArr[4];
        int i12 = iArr[5] == 1 ? 1 : 0;
        int i13 = iArr[6] == 1 ? 1 : 0;
        if (i6 >= 96) {
            if (i6 != 106) {
                i10 = 0;
            }
            if (i6 != 106) {
                int i14 = 101;
                switch (i6) {
                    case 96:
                    case 97:
                    case 102:
                        i7 = i8;
                        break;
                    case 98:
                        i = i13;
                        i5 = 1;
                        i7 = i8;
                        i2 = i9;
                        i3 = i10;
                        i4 = 101;
                        break;
                    case 99:
                        i14 = 99;
                        i7 = i8;
                        i2 = i9;
                        i3 = i10;
                        i5 = i12;
                        i4 = i14;
                        i = i13;
                        break;
                    case 100:
                        if (i9 == 0 && i8 != 0) {
                            i2 = 1;
                            i3 = i10;
                            i4 = i11;
                            i5 = i12;
                            i = i13;
                            i7 = 0;
                            break;
                        } else if (i9 != 0 && i8 != 0) {
                            i7 = 0;
                            i2 = 0;
                            i3 = i10;
                            i4 = i11;
                            i5 = i12;
                            i = i13;
                            break;
                        }
                        break;
                    case 101:
                        i7 = i8;
                        i2 = i9;
                        i3 = i10;
                        i5 = i12;
                        i4 = i14;
                        i = i13;
                        break;
                    default:
                        throw a.a();
                }
            } else {
                int i15 = i12;
                i = 1;
                i7 = i8;
                i2 = i9;
                i3 = i10;
                i4 = i11;
                i5 = i15;
            }
            return new int[]{i6, i7, i2, i3, i4, i5, i};
        }
        if (i8 == i9) {
            sb.append((char) (i6 + 32));
        } else {
            sb.append((char) (i6 + 160));
        }
        i7 = 0;
        i2 = i9;
        i3 = i10;
        i4 = i11;
        i5 = i12;
        i = i13;
        return new int[]{i6, i7, i2, i3, i4, i5, i};
    }

    private static int a(r rVar, int[] iArr, int i) throws a {
        float a2;
        g5.a(rVar, i, iArr);
        float f = 0.25f;
        int i2 = -1;
        int i3 = 0;
        while (true) {
            int[][] iArr2 = f5867a;
            if (i3 >= iArr2.length) {
                break;
            }
            int[] iArr3 = iArr2[i3];
            if (i3 == iArr2.length - 1) {
                a2 = b(rVar, iArr, i);
            } else {
                a2 = g5.a(iArr, iArr3, 0.7f);
            }
            if (a2 < f) {
                i2 = i3;
                f = a2;
            }
            i3++;
        }
        if (i2 >= 0) {
            return i2;
        }
        throw a.a();
    }

    @Override // com.huawei.hms.scankit.p.g5
    public s6 a(int i, r rVar, Map<l1, ?> map) throws a {
        int[] a2 = a(rVar);
        int i2 = a2[0];
        int i3 = i2 - (((a2[1] - i2) / 11) * 10);
        if (i3 > 0 && i3 < i2) {
            if (!a(rVar, i3, i2)) {
                throw a.a();
            }
        }
        int i4 = a2[2];
        ArrayList arrayList = new ArrayList(20);
        arrayList.add(Byte.valueOf((byte) i4));
        int i5 = i4 == 103 ? 101 : i4 == 104 ? 100 : i4 == 105 ? 99 : 0;
        if (i5 != 0) {
            StringBuilder sb = new StringBuilder(20);
            int[] iArr = new int[7];
            iArr[6] = i5;
            a(sb, a2, iArr, i4, rVar, arrayList);
            int i6 = iArr[0];
            int i7 = iArr[1];
            int i8 = iArr[2];
            int i9 = iArr[3];
            int i10 = iArr[4];
            boolean z = iArr[5] == 1;
            int i11 = iArr[6];
            if ((i9 - (i10 * i8)) % 103 == i8) {
                int length = sb.length();
                if (length != 0) {
                    if (length > 0 && z) {
                        if (i11 == 99) {
                            sb.delete(length - 2, length);
                        } else {
                            sb.delete(length - 1, length);
                        }
                    }
                    float f = a2[0];
                    float f2 = i6 + (((i7 - i6) * 13) / 11);
                    int size = arrayList.size();
                    byte[] bArr = new byte[size];
                    for (int i12 = 0; i12 < size; i12++) {
                        bArr[i12] = arrayList.get(i12).byteValue();
                    }
                    float f3 = i;
                    return new s6(sb.toString(), bArr, new u6[]{new u6(f, f3), new u6(f2, f3)}, BarcodeFormat.CODE_128);
                }
                throw a.a();
            }
            throw a.a();
        }
        throw a.a();
    }

    private void a(StringBuilder sb, int[] iArr, int[] iArr2, int i, r rVar, List<Byte> list) throws a {
        int i2;
        int i3;
        int i4 = 0;
        int i5 = iArr[0];
        int i6 = iArr[1];
        int i7 = 6;
        int[] iArr3 = new int[6];
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        int i15 = 1;
        int i16 = iArr2[6];
        int i17 = i6;
        int i18 = i5;
        int i19 = i;
        while (i12 == 0) {
            int a2 = a(rVar, iArr3, i17);
            list.add(Byte.valueOf((byte) a2));
            if (a2 != 106) {
                int i20 = i11 + 1;
                i19 += i20 * a2;
                i2 = i20;
                i3 = 1;
            } else {
                i2 = i11;
                i3 = i15;
            }
            int i21 = i17;
            for (int i22 = i4; i22 < i7; i22++) {
                i21 += iArr3[i22];
            }
            if (a2 != 105) {
                int i23 = i16;
                int[] iArr4 = {a2, i9, i10, i3, i16, 0, i12};
                int i24 = 101;
                if (i23 == 101) {
                    iArr4 = a(sb, iArr4);
                } else if (i23 == 100) {
                    iArr4 = b(sb, iArr4);
                } else if (i23 == 99) {
                    iArr4 = c(sb, iArr4);
                }
                int i25 = iArr4[i4];
                int i26 = iArr4[1] == 1 ? 1 : i4;
                int i27 = iArr4[2] == 1 ? 1 : i4;
                int i28 = iArr4[3] == 1 ? 1 : i4;
                int i29 = iArr4[5] == 1 ? 1 : i4;
                i12 = iArr4[6] == 1 ? 1 : 0;
                if (i13 == 0) {
                    i24 = iArr4[4];
                } else if (iArr4[4] == 101) {
                    i24 = 100;
                }
                i11 = i2;
                i18 = i17;
                i13 = i29;
                i9 = i26;
                i15 = i28;
                i8 = i14;
                i17 = i21;
                i4 = 0;
                i14 = i25;
                i16 = i24;
                i10 = i27;
                i7 = 6;
            } else {
                throw a.a();
            }
        }
        iArr2[i4] = i18;
        iArr2[1] = i17;
        iArr2[2] = i8;
        iArr2[3] = i19;
        iArr2[4] = i11;
        iArr2[5] = i15;
        iArr2[6] = i16;
    }

    private int[] a(StringBuilder sb, int[] iArr) throws a {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6 = iArr[0];
        int i7 = 1;
        int i8 = iArr[1] == 1 ? 1 : 0;
        int i9 = iArr[2] == 1 ? 1 : 0;
        int i10 = iArr[3] == 1 ? 1 : 0;
        int i11 = iArr[4];
        int i12 = iArr[5] == 1 ? 1 : 0;
        int i13 = iArr[6] == 1 ? 1 : 0;
        if (i6 < 64) {
            if (i8 == i9) {
                sb.append((char) (i6 + 32));
            } else {
                sb.append((char) (i6 + 160));
            }
        } else {
            if (i6 >= 96) {
                if (i6 != 106) {
                    i10 = 0;
                }
                if (i6 != 106) {
                    int i14 = 100;
                    switch (i6) {
                        case 96:
                        case 97:
                        case 102:
                            i7 = i8;
                            i2 = i9;
                            i3 = i10;
                            i4 = i11;
                            i5 = i12;
                            i = i13;
                            break;
                        case 98:
                            i = i13;
                            i5 = 1;
                            i7 = i8;
                            i2 = i9;
                            i3 = i10;
                            i4 = 100;
                            break;
                        case 99:
                            i14 = 99;
                        case 100:
                            i7 = i8;
                            i2 = i9;
                            i3 = i10;
                            i5 = i12;
                            i4 = i14;
                            i = i13;
                            break;
                        case 101:
                            if (i9 == 0 && i8 != 0) {
                                i2 = 1;
                                i3 = i10;
                                i4 = i11;
                                i5 = i12;
                                i = i13;
                                i7 = 0;
                                break;
                            } else {
                                if (i9 != 0 && i8 != 0) {
                                    i7 = 0;
                                    i2 = 0;
                                    i3 = i10;
                                    i4 = i11;
                                    i5 = i12;
                                    i = i13;
                                    break;
                                }
                                i2 = i9;
                                i3 = i10;
                                i4 = i11;
                                i5 = i12;
                                i = i13;
                            }
                        default:
                            throw a.a();
                    }
                } else {
                    int i15 = i12;
                    i = 1;
                    i7 = i8;
                    i2 = i9;
                    i3 = i10;
                    i4 = i11;
                    i5 = i15;
                }
                return new int[]{i6, i7, i2, i3, i4, i5, i};
            }
            if (i8 == i9) {
                sb.append((char) (i6 - 64));
            } else {
                sb.append((char) (i6 + 64));
            }
        }
        i7 = 0;
        i2 = i9;
        i3 = i10;
        i4 = i11;
        i5 = i12;
        i = i13;
        return new int[]{i6, i7, i2, i3, i4, i5, i};
    }
}
