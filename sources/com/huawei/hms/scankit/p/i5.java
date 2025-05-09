package com.huawei.hms.scankit.p;

import java.lang.reflect.Array;

/* loaded from: classes9.dex */
public class i5 {

    /* renamed from: a, reason: collision with root package name */
    private static final int[][] f5798a = {new int[]{1, 1, 1, 1, 1, 1, 1, 0}, new int[]{1, 0, 0, 0, 0, 0, 1, 0}, new int[]{1, 0, 1, 1, 1, 0, 1, 0}, new int[]{1, 0, 1, 1, 1, 0, 1, 0}, new int[]{1, 0, 1, 1, 1, 0, 1, 0}, new int[]{1, 0, 0, 0, 0, 0, 1, 0}, new int[]{1, 1, 1, 1, 1, 1, 1, 0}, new int[]{0, 0, 0, 0, 0, 0, 0, 0}};
    private static final int[][] b = {new int[]{1, 1, 1, 1, 1}, new int[]{1, 0, 0, 0, 1}, new int[]{1, 0, 1, 0, 1}, new int[]{1, 0, 0, 0, 1}, new int[]{1, 1, 1, 1, 1}};

    public static s a(s sVar, s sVar2, int i, double[][] dArr) {
        int i2;
        if (i == 21) {
            int i3 = 0;
            for (int i4 = 0; i4 < i; i4++) {
                for (int i5 = 0; i5 < i; i5++) {
                    if (i5 >= 8 || i4 >= 8) {
                        int i6 = i - 8;
                        if (i5 < i6 || i4 >= 8) {
                            if (i5 >= 8 || i4 < i6) {
                                double[] a2 = a((float) dArr[0][i3], (float) dArr[1][i3]);
                                if (sVar2.b(Math.round((float) a2[0]), Math.round((float) a2[1]))) {
                                    sVar.c(i5, i4);
                                }
                                i3++;
                            } else if (f5798a[i5][(i - 1) - i4] == 1) {
                                sVar.c(i5, i4);
                            }
                        } else if (f5798a[i4][(i - 1) - i5] == 1) {
                            sVar.c(i5, i4);
                        }
                    } else if (f5798a[i4][i5] == 1) {
                        sVar.c(i5, i4);
                    }
                }
            }
        } else {
            int i7 = 0;
            for (int i8 = 0; i8 < i; i8++) {
                for (int i9 = 0; i9 < i; i9++) {
                    if (i9 >= 8 || i8 >= 8) {
                        int i10 = i - 8;
                        if (i9 < i10 || i8 >= 8) {
                            if (i9 >= 8 || i8 < i10) {
                                int i11 = i - 9;
                                if (i9 < i11 || i9 >= i - 4 || i8 < i11 || i8 >= i2) {
                                    double[] a3 = a((float) dArr[0][i7], (float) dArr[1][i7]);
                                    if (sVar2.b(Math.round((float) a3[0]), Math.round((float) a3[1]))) {
                                        sVar.c(i9, i8);
                                    }
                                    i7++;
                                } else {
                                    int i12 = -i11;
                                    if (b[i12 + i8][i12 + i9] == 1) {
                                        sVar.c(i9, i8);
                                    }
                                }
                            } else if (f5798a[i9][(i - 1) - i8] == 1) {
                                sVar.c(i9, i8);
                            }
                        } else if (f5798a[i8][(i - 1) - i9] == 1) {
                            sVar.c(i9, i8);
                        }
                    } else if (f5798a[i8][i9] == 1) {
                        sVar.c(i9, i8);
                    }
                }
            }
        }
        return sVar;
    }

    public static double[][] a(double d, double[] dArr, String str) {
        int a2 = a(str);
        String[] split = str.split(";");
        double[][] dArr2 = (double[][]) Array.newInstance((Class<?>) Double.TYPE, 2, a2);
        int i = 0;
        for (int i2 = 0; i2 < split.length; i2++) {
            double[] a3 = a(split[i2].split(","));
            for (int i3 = 0; i3 < a3.length; i3++) {
                int i4 = i2 / 2;
                dArr2[0][i] = d - (dArr[i4] * Math.cos(a3[i3]));
                dArr2[1][i] = d - (dArr[i4] * Math.sin(a3[i3]));
                i++;
            }
        }
        return dArr2;
    }

    private static double[] a(float f, float f2) {
        double[] dArr = {0.0d, 0.0d};
        dArr[0] = f;
        dArr[1] = f2;
        return dArr;
    }

    public static StringBuffer a(double[] dArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (double d : dArr) {
            stringBuffer.append(d);
            stringBuffer.append(",");
        }
        stringBuffer.append(";");
        return stringBuffer;
    }

    private static double[] a(String[] strArr) {
        int length = strArr.length;
        double[] dArr = new double[length];
        for (int i = 0; i < length; i++) {
            dArr[i] = Double.valueOf(strArr[i]).doubleValue();
        }
        return dArr;
    }

    private static int a(String str) {
        int i = 0;
        for (String str2 : str.split(";")) {
            i += str2.split(",").length;
        }
        return i;
    }
}
