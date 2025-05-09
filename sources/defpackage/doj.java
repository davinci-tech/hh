package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.UnitUtil;
import java.math.BigDecimal;

/* loaded from: classes3.dex */
public class doj {
    public static final double[] d = {18.5d, 23.9d, 28.0d};
    private static final double[][] i = {new double[]{23.0d, 26.0d, 28.0d}, new double[]{15.0d, 17.0d, 19.0d}};
    private static final double[][] f = {new double[]{27.0d, 28.0d, 29.0d}, new double[]{16.0d, 17.0d, 19.0d}};
    private static final double[][] p = {new double[]{15.7d, 16.1d, 16.7d, 17.3d, 18.05d, 18.7d, 19.4d, 20.0d, 20.25d, 20.6d, 20.7d, 21.0d}, new double[]{16.45d, 17.05d, 17.65d, 18.25d, 18.9d, 19.35d, 19.75d, 20.2d, 20.55d, 20.9d, 21.3d, 22.0d}};
    private static final double[] bf = {2.12d, 96.33d};
    private static final double[] ci = {10.59d, 137.61d};
    private static final double[] z = {10.59d, 137.61d};
    private static final double[] cb = {0.05d, 8.97d};
    private static final double[] ar = {0.25d, 22.87d};
    private static final double[] cc = {0.297d, 10.01d};
    private static final double[] as = {0.9775d, 21.0375d};
    private static final double[] ad = {0.0d, 10.0d};
    private static final double[] bj = {0.0d, 42.0d};
    private static final double[] o = {50.0d, 100.0d};
    private static final double[] t = {0.0d, 9.0d};
    private static final double[] r = {0.0d, 5.0d};
    private static final double[] m = {10.0d, 55.0d};
    private static final double[] n = {10.0d, 60.0d};
    private static final double[] k = {17.0d, 32.0d};
    private static final double[] g = {17.0d, 31.0d};
    private static final double[] ai = {3.0d, 60.0d};
    private static final double[] bb = {10.07d, 130.89d};
    private static final double[] bn = {4.25935d, 77.35545d};
    private static final double[] bl = {4.28d, 15.0d};
    private static final double[] ch = {1.0d, 34.0d};
    private static final double[] bw = {25.0d, 80.0d};
    private static final double[] be = {2.12d, 96.33d};
    private static final double[] v = {0.61d, 4.24d};
    private static final double[] b = {244.78d, 6127.56d};
    private static final double[] al = {1.0d, 300.0d};
    private static final double[] ck = {0.4d, 1.5d};
    private static final double[] az = {0.0d, 99.0d};
    private static final double[] l = {18.5d, 24.9d, 30.0d};
    private static final double[][] j = {new double[]{13.3d, 16.6d, 17.9d}, new double[]{13.5d, 17.2d, 19.0d}, new double[]{13.6d, 18.0d, 20.0d}, new double[]{13.8d, 18.7d, 21.1d}, new double[]{14.0d, 19.4d, 22.2d}, new double[]{14.3d, 20.1d, 23.3d}, new double[]{14.5d, 20.9d, 24.4d}, new double[]{14.9d, 21.7d, 25.5d}, new double[]{15.4d, 22.5d, 26.3d}, new double[]{15.9d, 23.0d, 26.8d}, new double[]{16.3d, 23.4d, 27.3d}, new double[]{16.7d, 23.8d, 27.7d}};
    private static final double[][] h = {new double[]{12.9d, 16.4d, 17.8d}, new double[]{13.0d, 17.0d, 18.8d}, new double[]{13.1d, 17.9d, 19.7d}, new double[]{13.2d, 18.8d, 20.7d}, new double[]{13.4d, 19.8d, 21.8d}, new double[]{13.8d, 20.8d, 23.0d}, new double[]{14.2d, 21.7d, 24.2d}, new double[]{14.8d, 22.4d, 25.3d}, new double[]{15.3d, 22.9d, 26.1d}, new double[]{16.1d, 23.3d, 26.8d}, new double[]{16.5d, 23.7d, 27.3d}, new double[]{16.7d, 23.9d, 27.7d}};
    private static final double[][] ac = {new double[]{12.0d, 15.0d, 18.0d, 21.0d, 18.0d, 20.0d, 22.0d}, new double[]{13.0d, 15.0d, 10.0d, 10.0d, 10.0d, 12.0d, 14.0d}};
    private static final double[][] ah = {new double[]{24.0d, 28.0d, 32.0d, 34.0d, 28.0d, 32.0d, 34.0d}, new double[]{22.0d, 28.0d, 25.0d, 20.0d, 20.0d, 22.0d, 24.0d}};
    private static final double[][] ab = {new double[]{33.0d, 36.0d, 38.0d, 37.0d, 33.0d, 37.0d, 39.0d}, new double[]{31.0d, 36.0d, 35.0d, 30.0d, 25.0d, 27.0d, 29.0d}};
    private static final double[][] ae = {new double[]{12.0d, 15.0d, 18.0d, 21.0d, 20.0d, 21.0d, 22.0d}, new double[]{13.0d, 15.0d, 10.0d, 10.0d, 10.0d, 11.0d, 13.0d}};
    private static final double[][] af = {new double[]{24.0d, 28.0d, 32.0d, 34.0d, 34.0d, 35.0d, 36.0d}, new double[]{22.0d, 28.0d, 25.0d, 20.0d, 20.0d, 21.0d, 22.0d, 24.0d}};
    private static final double[][] aa = {new double[]{33.0d, 36.0d, 38.0d, 37.0d, 39.0d, 40.0d, 41.0d}, new double[]{31.0d, 36.0d, 35.0d, 30.0d, 26.0d, 27.0d, 29.0d}};
    private static final double[][] au = {new double[]{0.717d, 0.688d, 0.646d, 0.631d, 0.73d, 0.7d, 0.68d}, new double[]{0.728d, 0.685d, 0.725d, 0.745d, 0.81d, 0.79d, 0.77d}};
    private static final double[] at = {21.9d, 32.9d, 36.5d};
    private static final double[] aw = {34.7d, 37.5d, 42.5d};
    private static final double[] ax = {38.5d, 44.0d, 49.4d};
    private static final double[] av = {46.5d, 52.4d, 59.4d};
    private static final double[][] ce = {new double[]{7.3d, 10.3d, 15.4d, 21.0d, 21.6d}, new double[]{7.3d, 10.0d, 9.5d, 9.7d, 11.7d}};
    private static final double[][] bz = {new double[]{19.5d, 24.0d, 33.3d, 34.0d, 34.9d}, new double[]{22.8d, 28.7d, 30.3d, 23.7d, 18.7d}};
    private static final double[][] aq = {new double[]{19.5d, 22.1d, 25.3d, 28.3d, 28.4d}, new double[]{15.5d, 16.9d, 14.6d, 11.6d, 15.0d}};
    private static final double[][] ao = {new double[]{30.3d, 33.9d, 39.6d, 36.8d, 37.3d}, new double[]{32.2d, 37.3d, 37.6d, 27.6d, 22.6d}};
    private static final double[][] bu = {new double[]{5.7d, 8.8d, 14.4d, 22.0d, 17.5d}, new double[]{5.0d, 8.8d, 9.3d, 12.1d, 14.0d}};
    private static final double[][] bs = {new double[]{19.7d, 24.6d, 34.5d, 37.0d, 30.0d}, new double[]{22.9d, 30.1d, 33.5d, 32.6d, 27.0d}};
    private static final double[][] cd = {new double[]{0.03906d, 0.03744d, 0.03519d, 0.034291d, 0.0442d, 0.0424d, 0.0411d}, new double[]{0.0396d, 0.03726d, 0.03951d, 0.04059d, 0.049d, 0.0478d, 0.0466d}};
    private static final double[][] ap = {new double[]{0.12152d, 0.11648d, 0.10948d, 0.10668d, 0.12155d, 0.1166d, 0.11303d}, new double[]{0.1232d, 0.11592d, 0.12292d, 0.12628d, 0.13475d, 0.13145d, 0.12815d}};
    private static final double[][] bx = {new double[]{0.11284d, 0.10816d, 0.10166d, 0.9906d}, new double[]{0.1144d, 0.10764d, 0.11414d, 0.11726d}};
    private static final double[][] ca = {new double[]{0.11041d, 0.10588d, 0.10285d}, new double[]{0.12251d, 0.11949d, 0.11646d}};
    private static final double[][][] by = {new double[][]{new double[]{0.83d, 1.03d}, new double[]{0.99d, 1.21d}, new double[]{1.23d, 1.46d}}, new double[][]{new double[]{0.86d, 1.05d}, new double[]{0.96d, 1.16d}, new double[]{1.0d, 1.53d}}};
    private static final double[][] bi = {new double[]{0.434d, 0.416d, 0.391d, 0.381d, 0.442d, 0.424d, 0.411d}, new double[]{0.44d, 0.414d, 0.439d, 0.451d, 0.49d, 0.478d, 0.466d}};
    private static final double[] bm = {16.0d, 18.9d, 22.1d};
    private static final double[] bk = {20.6d, 23.7d, 30.3d};
    private static final double[] br = {21.2d, 24.8d, 29.6d};
    private static final double[] bp = {26.6d, 34.6d, 43.2d};
    private static final double[][] bd = {new double[]{4.395d, 4.705d, 4.816d, 5.434d, 5.525d}, new double[]{4.224d, 4.839d, 5.656d, 6.78d, 7.102d}};
    private static final double[][] bg = {new double[]{5.338d, 5.837d, 6.247d, 6.853d, 6.531d}, new double[]{5.834d, 6.661d, 7.733d, 8.784d, 8.558d}};
    private static final double[] cg = {5.0d, 10.0d, 15.0d};
    private static final double[] cf = {4.5d, 9.5d, 14.5d};
    private static final double[][] bt = {new double[]{47.61d, 45.3d, 42.52d, 42.04d, 41.0d}, new double[]{48.44d, 44.62d, 46.32d, 48.79d, 47.23d}};
    private static final double[][] bq = {new double[]{65.59d, 63.55d, 59.59d, 57.47d, 55.94d}, new double[]{66.47d, 63.63d, 68.12d, 68.64d, 70.0d}};
    private static final double[][] bv = {new double[]{47.61d, 45.3d, 42.52d, 42.04d, 45.0d}, new double[]{48.44d, 44.62d, 46.32d, 48.79d, 50.0d}};
    private static final double[][] bo = {new double[]{65.59d, 63.55d, 59.59d, 57.47d, 60.0d}, new double[]{66.47d, 63.63d, 68.12d, 68.64d, 65.0d}};
    private static final double[][] ba = {new double[]{12.66d, 12.04d, 11.36d, 11.23d, 12.9162d, 11.2316d, 7.64d}, new double[]{12.86d, 11.86d, 12.37d, 13.14d, 18.5976d, 17.2668d, 16.0936d}};
    private static final double[][] ay = {new double[]{17.52d, 16.8d, 15.83d, 15.36d, 21.3418d, 19.6124d, 19.064d}, new double[]{17.75d, 16.9d, 18.21d, 18.52d, 24.8824d, 23.7532d, 21.0344d}};
    private static final double[] bh = {16.0d, 20.0d};
    private static final double[] an = {0.724d, 0.986d, 1.248d, 1.566d, 2.067d, 2.87d};
    private static final double[] ak = {1.083d, 1.393d, 1.78d, 2.561d, 3.084d, 3.43d};
    private static final double[] am = {0.719d, 0.955d, 1.252d, 1.678d, 2.078d};
    private static final double[] aj = {1.041d, 1.399d, 2.026d, 2.444d, 2.834d};
    private static final double[] s = {1.526d, 1.862d, 2.122d};
    private static final double[] q = {2.074d, 2.538d, 2.878d};
    private static final double[] w = {2.236d, 2.545d, 2.87d};
    private static final double[] y = {2.764d, 3.054d, 3.43d};
    private static final double[] u = {1.8d, 2.2d, 2.5d};
    private static final double[] x = {2.5d, 2.9d, 3.2d};
    private static final double[][] c = {new double[]{26.993d, 22.03d, 18.978d, 18.568d, 20.266d, 19.268d, 18.038d}, new double[]{26.298d, 21.65d, 20.007d, 19.928d, 21.305d, 20.7d, 20.161d}};
    private static final double[][] e = {new double[]{35.337d, 29.606d, 25.275d, 23.733d, 22.816d, 21.582d, 20.026d}, new double[]{34.955d, 29.189d, 26.788d, 25.624d, 23.929d, 23.032d, 21.269d}};

    /* renamed from: a, reason: collision with root package name */
    private static final double[][] f11753a = {new double[]{1210.0d, 1170.0d, 1110.0d, 1010.0d}, new double[]{1550.0d, 1500.0d, 1350.0d, 1220.0d}};
    private static final double[] ag = {55.0d, 85.0d, 100.0d};
    private static final double[] cj = {0.85d, 0.9d};
    private static final double[] bc = {29.0d, 59.0d, 79.0d};

    public static int a(double d2) {
        if (d2 < 160.0d) {
            return 0;
        }
        return d2 < 170.0d ? 1 : 2;
    }

    public static int a(int i2) {
        if (i2 >= 6 && i2 <= 8) {
            return 0;
        }
        if (i2 >= 9 && i2 <= 11) {
            return 1;
        }
        if (i2 < 12 || i2 > 14) {
            return (i2 < 15 || i2 > 17) ? 4 : 3;
        }
        return 2;
    }

    public static int b(int i2) {
        if (i2 <= 39) {
            return 0;
        }
        return i2 <= 59 ? 1 : 2;
    }

    public static int c(byte b2) {
        return b2 == 0 ? 0 : 1;
    }

    public static int c(int i2) {
        if (i2 >= 6 && i2 <= 8) {
            return 0;
        }
        if (i2 >= 9 && i2 <= 11) {
            return 1;
        }
        if (i2 >= 12 && i2 <= 14) {
            return 2;
        }
        if (i2 >= 15 && i2 <= 17) {
            return 3;
        }
        if (i2 < 18 || i2 > 39) {
            return (i2 < 40 || i2 > 59) ? 6 : 5;
        }
        return 4;
    }

    public static int c(int i2, int i3) {
        if (i2 == 1) {
            if (i3 == 1) {
                return 1;
            }
            if (i3 != 2) {
                return i3 != 3 ? 0 : 3;
            }
            return 2;
        }
        if (i2 == 2) {
            if (i3 == 1) {
                return 4;
            }
            if (i3 != 2) {
                return i3 != 3 ? 0 : 6;
            }
            return 5;
        }
        if (i2 != 3 && i2 != 4) {
            return 0;
        }
        if (i3 == 1) {
            return 7;
        }
        if (i3 != 2) {
            return i3 != 3 ? 0 : 9;
        }
        return 8;
    }

    public static boolean d(int i2) {
        return i2 < 18 && i2 >= 6;
    }

    public static int e(int i2) {
        if (i2 >= 6 && i2 <= 7) {
            return 0;
        }
        if (i2 == 8) {
            return 1;
        }
        if (i2 == 9) {
            return 2;
        }
        if (i2 == 10) {
            return 3;
        }
        if (i2 == 11) {
            return 4;
        }
        if (i2 == 12) {
            return 5;
        }
        if (i2 == 13) {
            return 6;
        }
        if (i2 == 14) {
            return 7;
        }
        if (i2 == 15) {
            return 8;
        }
        if (i2 == 16) {
            return 9;
        }
        return i2 == 17 ? 10 : 11;
    }

    public static int g(double d2) {
        if (d2 < 45.0d) {
            return 0;
        }
        return d2 <= 60.0d ? 1 : 2;
    }

    public static int i(double d2) {
        if (d2 < 60.0d) {
            return 0;
        }
        return d2 <= 75.0d ? 1 : 2;
    }

    private static int i(int i2) {
        if (i2 <= 29) {
            return 0;
        }
        if (i2 <= 49) {
            return 1;
        }
        return i2 <= 69 ? 2 : 3;
    }

    private static int j(double d2) {
        if (d2 < 150.0d) {
            return 0;
        }
        return d2 < 160.0d ? 1 : 2;
    }

    private static int l(double d2) {
        return CommonUtil.a(String.valueOf(new BigDecimal(d2).setScale(0, 4)), 10);
    }

    private static int d(double d2, double d3, double d4, double d5, double d6) {
        double abs = Math.abs(d2 - d3);
        if (abs != 0.0d) {
            d4 = ((Math.abs(d5 - d3) / abs) * (d4 - d6)) + d6;
        }
        return l(d4);
    }

    private static int b(double d2, double d3, double d4, double d5, double d6) {
        int d7 = d(d2, d3, d4, d5, d6);
        int l2 = l(3270.3333333333335d);
        int l3 = l(3144.3333333333335d);
        if (d7 > l3 && d7 < l2) {
            return l(l3);
        }
        int l4 = l(3396.3333333333335d);
        if (d7 > l2 && d7 < l4) {
            return l(l4);
        }
        int l5 = l(6729.666666666667d);
        int l6 = l(6603.666666666667d);
        if (d7 > l6 && d7 < l5) {
            return l(l6);
        }
        int l7 = l(6855.666666666667d);
        return (d7 <= l5 || d7 >= l7) ? d7 : l(l7);
    }

    private static int a(double d2, double d3, double d4, double d5, double d6) {
        int d7 = d(d2, d3, d4, d5, d6);
        int l2 = l(2400.0d);
        int l3 = l(2300.0d);
        if (d7 > l3 && d7 < l2) {
            return l(l3);
        }
        if (d7 > l2 && d7 < 2500.0d) {
            return l(2500.0d);
        }
        int l4 = l(4900.0d);
        if (d7 > l4 && d7 < 5000.0d) {
            return l(l4);
        }
        int l5 = l(5100.0d);
        double d8 = d7;
        if (d8 > 5000.0d && d7 < l5) {
            return l(l5);
        }
        int l6 = l(7600.0d);
        if (d8 > 7500.0d && d7 < l6) {
            return l(7500.0d);
        }
        int l7 = l(7700.0d);
        return (d7 <= l6 || d7 >= l7) ? d7 : l(l7);
    }

    private static double n(double d2) {
        return UnitUtil.a(d2, 0);
    }

    public static double d(double d2) {
        return UnitUtil.a(d2, 1);
    }

    public static boolean b(double d2, double d3) {
        return Math.abs(d2 - d3) < 1.0E-6d;
    }

    private static String m(double d2) {
        return UnitUtil.e(d2, 1, 0);
    }

    public static String e(double d2) {
        return UnitUtil.e(d2, 1, 1);
    }

    public static int d(double[] dArr, double d2) {
        if (d2 < dArr[0]) {
            return 1;
        }
        return d2 <= dArr[1] ? 2 : 3;
    }

    public static int a(double[] dArr, double[] dArr2, double d2, int i2) {
        double min;
        double d3;
        double d4;
        double d5;
        double d6;
        double d7;
        double d8;
        double d9 = dArr[0];
        double d10 = dArr[1];
        double d11 = dArr2[0];
        double d12 = dArr2[1];
        double a2 = UnitUtil.a(d2, i2);
        double d13 = 3270.3333333333335d;
        if (a2 < d11) {
            d3 = 0.0d;
            min = Math.max(a2, d9);
            d10 = d11;
        } else if (a2 > d12) {
            d13 = 10000.0d;
            min = Math.min(a2, d10);
            d9 = d12;
            d3 = 6729.666666666667d;
        } else {
            d4 = 6729.666666666667d;
            d5 = d11;
            d6 = d12;
            d7 = a2;
            d8 = 3270.3333333333335d;
            return b(d6, d5, d4, d7, d8);
        }
        d5 = d9;
        d6 = d10;
        d8 = d3;
        d7 = min;
        d4 = d13;
        return b(d6, d5, d4, d7, d8);
    }

    private static double[] k(double d2) {
        return new double[]{d(0.85d * d2), d(d2 * 1.15d)};
    }

    private static double[] b(double d2, byte b2, int i2) {
        double[] dArr = by[c(b2)][b(i2)];
        return new double[]{d(dArr[0] * d2), d(d2 * dArr[1])};
    }

    public static double e(double d2, double d3, double d4, double d5, double d6) {
        double d7 = d(d2);
        double d8 = d(d3);
        return (((d7 - d8) - d(d4)) - d(d5)) - d(d6);
    }

    public static double c(double d2, double d3, double d4, double d5, double d6) {
        double d7 = d(d2);
        double d8 = d(d3);
        return (((d7 - d8) - d(d4)) - d(d5)) - d(d6);
    }

    public static double a(double d2, int i2) {
        if (i2 <= 0) {
            return 0.0d;
        }
        return UnitUtil.a(d2 / Math.pow(i2 / 100.0d, 2.0d), 1);
    }

    public static double e(double d2, double d3, int i2) {
        return UnitUtil.a((UnitUtil.a(d3, i2) * d2) / 100.0d, i2);
    }

    public static double a(double d2, double d3, int i2) {
        return UnitUtil.a((UnitUtil.a(d3, i2) * d2) / 100.0d, i2);
    }

    public static double e(double d2, double d3) {
        return d(new BigDecimal(Double.toString(d3)).subtract(new BigDecimal(Double.toString(a(d2, d3, 1)))).doubleValue());
    }

    public static double d(double d2, double d3, double d4, double d5, int i2) {
        double[] t2 = t();
        double min = Math.min(Math.max((e(d2, d3, d4, d5, i2) / d2) * 100.0d, t2[0]), t2[1]);
        if (Double.isInfinite(min) || Double.isNaN(min)) {
            LogUtil.b("HealthStandard", "getProteinPercentage proteinPercentage is Infinity or NaN");
            return 0.0d;
        }
        return d(min);
    }

    public static double e(double d2, double d3, double d4, double d5, int i2) {
        double a2 = UnitUtil.a(d2, i2);
        double e2 = e(d3, d2, i2);
        return UnitUtil.a(((a2 - e2) - a(d4, d2, i2)) - UnitUtil.a(d5, i2), i2);
    }

    public static double b(byte b2, int i2, int i3) {
        if (c(b2) != 0) {
            return d(i[c(b2)][b(i2)]);
        }
        if (i3 == 0) {
            return d(f[c(b2)][b(i2)]);
        }
        return d(i[c(b2)][b(i2)]);
    }

    public static double a(byte b2, int i2, int i3) {
        return d(Math.pow(i2 / 100.0d, 2.0d) * p[c(b2)][e(i3)]);
    }

    public static double[] d(boolean z2) {
        if (z2) {
            double[] dArr = n;
            return new double[]{d(dArr[0]), d(dArr[1])};
        }
        double[] dArr2 = m;
        return new double[]{d(dArr2[0]), d(dArr2[1])};
    }

    public static double[] b(byte b2) {
        if (c(b2) == 0) {
            double[] dArr = g;
            return new double[]{d(dArr[0]), d(dArr[1])};
        }
        double[] dArr2 = k;
        return new double[]{d(dArr2[0]), d(dArr2[1])};
    }

    public static double[] e(boolean z2, byte b2, int i2) {
        double[] dArr;
        if (z2) {
            dArr = l;
        } else if (i2 >= 6 && i2 <= 17) {
            if (c(b2) == 0) {
                dArr = h[i2 - 6];
            } else {
                dArr = j[i2 - 6];
            }
        } else {
            dArr = d;
        }
        return new double[]{d(dArr[0]), d(dArr[1]), d(dArr[2])};
    }

    public static int b(double d2, boolean z2, byte b2, int i2) {
        double d3 = d(d2);
        double[] e2 = e(z2, b2, i2);
        if (d3 < e2[0]) {
            return 1;
        }
        if (d3 <= e2[1]) {
            return 2;
        }
        return d3 < e2[2] ? 3 : 4;
    }

    public static double[] i() {
        double[] dArr = ai;
        return new double[]{d(dArr[0]), d(dArr[1])};
    }

    public static double[] c(byte b2, int i2, int i3) {
        if (c(b2) != 0) {
            LogUtil.a("HealthStandard", "getGenderIndex != 0, user the user scale");
            return new double[]{d(ac[c(b2)][c(i2)]), d(ah[c(b2)][c(i2)]), d(ab[c(b2)][c(i2)])};
        }
        if (i3 == 0) {
            return new double[]{d(ae[c(b2)][c(i2)]), d(af[c(b2)][c(i2)]), d(aa[c(b2)][c(i2)])};
        }
        return new double[]{d(ac[c(b2)][c(i2)]), d(ah[c(b2)][c(i2)]), d(ab[c(b2)][c(i2)])};
    }

    public static String[] d(byte b2, int i2, int i3) {
        double[] c2 = c(b2, i2, i3);
        return new String[]{e(c2[0]), e(c2[1]), e(c2[2])};
    }

    public static int d(byte b2, int i2, double d2, int i3) {
        double d3 = d(d2);
        double[] c2 = c(b2, i2, i3);
        if (d3 < c2[0]) {
            return 1;
        }
        if (d3 <= c2[1]) {
            return 2;
        }
        return d3 <= c2[2] ? 3 : 4;
    }

    public static int a(byte b2, int i2, double d2, int i3) {
        double d3;
        double d4;
        double d5;
        double d6;
        double d7;
        double d8;
        double[] i4 = i();
        double[] c2 = c(b2, i2, i3);
        double d9 = i4[0];
        double d10 = i4[1];
        double d11 = c2[0];
        double d12 = c2[1];
        double d13 = c2[2];
        double d14 = d(d2);
        double d15 = 2400.0d;
        if (d14 < d11) {
            d14 = Math.max(d14, d9);
            d3 = 0.0d;
        } else {
            double d16 = 5000.0d;
            if (d14 <= d12) {
                d10 = d12;
            } else {
                d15 = 7600.0d;
                if (d14 <= d13) {
                    d11 = d13;
                    d3 = 5000.0d;
                    d9 = d12;
                } else {
                    d14 = Math.min(d14, d10);
                    d16 = 10000.0d;
                    d11 = d13;
                }
            }
            d4 = d16;
            d5 = d10;
            d6 = d11;
            d7 = d14;
            d8 = d15;
            return a(d5, d6, d4, d7, d8);
        }
        d8 = d3;
        d6 = d9;
        d5 = d11;
        d7 = d14;
        d4 = d15;
        return a(d5, d6, d4, d7, d8);
    }

    public static double[] m() {
        double[] dArr = bb;
        return new double[]{d(dArr[0]), d(dArr[1])};
    }

    public static double[] a(byte b2, int i2, int i3, int i4) {
        double d2;
        double d3;
        double d4;
        if (c(b2) == 0) {
            double a2 = a(b2, i3, i2) * au[c(b2)][c(i2)];
            return new double[]{d(0.85d * a2), d(a2 * 1.15d)};
        }
        if (i4 != 0) {
            double a3 = a(b2, i3, i2) * au[c(b2)][c(i2)];
            d3 = 0.85d * a3;
            d4 = a3 * 1.15d;
        } else {
            if (b2 == 0) {
                d2 = at[j(i3)];
            } else {
                d2 = ax[a(i3)];
            }
            d3 = d2;
            if (b2 == 0) {
                d4 = aw[j(i3)];
            } else {
                d4 = av[a(i3)];
            }
        }
        return new double[]{d(d3), d(d4)};
    }

    public static int a(byte b2, int i2, int i3, double d2, int i4) {
        return d(a(b2, i2, i3, i4), d(d2));
    }

    public static int e(byte b2, int i2, int i3, double d2, int i4) {
        return a(m(), a(b2, i2, i3, i4), d(d2), 1);
    }

    public static double[] f() {
        double[] dArr = z;
        return new double[]{d(dArr[0]), d(dArr[1])};
    }

    public static double[] c(byte b2, int i2, int i3, int i4) {
        double[] a2 = a(b2, i2, i3, i4);
        double[] e2 = e(b2, i3, i2);
        return new double[]{d(a2[0] + e2[0]), d(a2[1] + e2[1])};
    }

    public static int b(byte b2, int i2, int i3, double d2, int i4) {
        return d(c(b2, i2, i3, i4), d(d2));
    }

    public static int c(byte b2, int i2, int i3, double d2, int i4) {
        return a(f(), c(b2, i2, i3, i4), d(d2), 1);
    }

    public static double[] i(byte b2, int i2) {
        return new double[]{d(ce[c(b2)][a(i2)]), d(bz[c(b2)][a(i2)])};
    }

    public static int i(byte b2, double d2, int i2) {
        return d(i(b2, i2), d(d2));
    }

    public static double[] b(byte b2, int i2) {
        return new double[]{d(aq[c(b2)][a(i2)]), d(ao[c(b2)][a(i2)])};
    }

    public static int c(byte b2, double d2, int i2) {
        return d(b(b2, i2), d(d2));
    }

    public static double[] g(byte b2, int i2) {
        return new double[]{d(bu[c(b2)][a(i2)]), d(bs[c(b2)][a(i2)])};
    }

    public static int j(byte b2, double d2, int i2) {
        return d(g(b2, i2), d(d2));
    }

    public static double[] y() {
        double[] dArr = cc;
        return new double[]{d(dArr[0]), d(dArr[1])};
    }

    public static double[] g(byte b2, int i2, int i3) {
        return k((a(b2, i3, i2) * cd[c(b2)][c(i2)]) - ((i2 < 6 || i2 > 17) ? 0.18d : 0.162d));
    }

    public static int e(byte b2, int i2, int i3, double d2) {
        return d(g(b2, i2, i3), d(d2));
    }

    public static double[] l() {
        double[] dArr = as;
        return new double[]{d(dArr[0]), d(dArr[1])};
    }

    public static double[] f(byte b2, int i2, int i3) {
        return k((a(b2, i3, i2) * ap[c(b2)][c(i2)]) - ((i2 < 6 || i2 > 17) ? 0.495d : 0.504d));
    }

    public static int b(byte b2, int i2, int i3, double d2) {
        return d(f(b2, i2, i3), d(d2));
    }

    public static double[] j(byte b2, int i2, int i3) {
        if (i2 >= 6 && i2 <= 17) {
            return k((a(b2, i3, i2) * bx[c(b2)][a(i2)]) - 0.468d);
        }
        return b((a(b2, i3, i2) * ca[c(b2)][b(i2)]) - 0.4583d, b2, i2);
    }

    public static int d(byte b2, int i2, int i3, double d2) {
        return d(j(b2, i2, i3), d(d2));
    }

    public static double[] r() {
        double[] dArr = bn;
        return new double[]{d(dArr[0]), d(dArr[1])};
    }

    public static double[] b(byte b2, int i2, int i3, int i4) {
        double d2;
        double d3;
        double d4;
        if (c(b2) == 0) {
            double a2 = (a(b2, i3, i2) * bi[c(b2)][c(i2)]) - 1.8d;
            double d5 = 0.85d * a2;
            double d6 = a2 * 1.15d;
            LogUtil.a("HealthStandard", "getSkeletalMuscleValue gender is female low = ", Double.valueOf(d5), ", high = ", Double.valueOf(d6));
            return new double[]{d(d5), d(d6)};
        }
        if (i4 != 0) {
            double a3 = (a(b2, i3, i2) * bi[c(b2)][c(i2)]) - 1.8d;
            d3 = 0.85d * a3;
            d4 = a3 * 1.15d;
        } else {
            if (b2 == 0) {
                d2 = bm[j(i3)];
            } else {
                d2 = br[a(i3)];
            }
            d3 = d2;
            if (b2 == 0) {
                d4 = bk[j(i3)];
            } else {
                d4 = bp[a(i3)];
            }
        }
        LogUtil.a("HealthStandard", "getSkeletalMuscleValue gender is male low = ", Double.valueOf(d3), ", high = ", Double.valueOf(d4));
        return new double[]{d(d3), d(d4)};
    }

    public static int d(byte b2, int i2, int i3, double d2, int i4) {
        return d(b(b2, i2, i3, i4), d(d2));
    }

    public static int h(byte b2, int i2, int i3, double d2, int i4) {
        return a(r(), b(b2, i2, i3, i4), d(d2), 1);
    }

    public static double[] s() {
        double[] dArr = bl;
        return new double[]{d(dArr[0]), d(dArr[1])};
    }

    public static double[] a(byte b2, int i2) {
        return new double[]{d(bd[c(b2)][a(i2)]), d(bg[c(b2)][a(i2)])};
    }

    public static int e(byte b2, double d2, int i2) {
        return d(a(b2, i2), d(d2));
    }

    public static int b(byte b2, double d2, int i2) {
        return a(s(), a(b2, i2), d(d2), 1);
    }

    public static double[] v() {
        double[] dArr = ch;
        return new double[]{d(dArr[0]), d(dArr[1])};
    }

    public static double[] aa() {
        double[] dArr = cg;
        return new double[]{d(dArr[0]), d(dArr[1]), d(dArr[2])};
    }

    public static String[] w() {
        double[] aa2 = aa();
        return new String[]{e(aa2[0]), e(aa2[1]), e(aa2[2])};
    }

    public static int h(double d2) {
        double[] aa2 = aa();
        double d3 = aa2[0];
        double d4 = aa2[1];
        double d5 = aa2[2];
        double d6 = d(d2);
        if (d6 < d3) {
            return 1;
        }
        if (d6 < d4) {
            return 2;
        }
        return d6 < d5 ? 3 : 4;
    }

    public static int f(double d2) {
        double d3;
        double d4;
        double d5;
        double d6;
        double d7;
        double d8;
        double[] v2 = v();
        double[] aa2 = aa();
        double d9 = v2[0];
        double d10 = v2[1];
        double d11 = aa2[0];
        double d12 = aa2[1];
        double d13 = aa2[2];
        double d14 = d(d2);
        double d15 = 2400.0d;
        if (d14 < d11) {
            d14 = Math.max(d14, d9);
            d3 = 0.0d;
        } else {
            double d16 = 5000.0d;
            if (d14 < d12) {
                d10 = d12;
            } else {
                d15 = 7600.0d;
                if (d14 < d13) {
                    d11 = d13;
                    d3 = 5000.0d;
                    d9 = d12;
                } else {
                    d14 = Math.min(d14, d10);
                    d16 = 10000.0d;
                    d11 = d13;
                }
            }
            d4 = d16;
            d5 = d10;
            d6 = d11;
            d7 = d14;
            d8 = d15;
            return a(d5, d6, d4, d7, d8);
        }
        d8 = d3;
        d6 = d9;
        d5 = d11;
        d7 = d14;
        d4 = d15;
        return a(d5, d6, d4, d7, d8);
    }

    public static double[] x() {
        double[] dArr = bw;
        return new double[]{d(dArr[0]), d(dArr[1])};
    }

    public static double[] f(byte b2, int i2) {
        return new double[]{d(bt[c(b2)][a(i2)]), d(bq[c(b2)][a(i2)])};
    }

    public static String[] c(byte b2, int i2) {
        double[] f2 = f(b2, i2);
        return new String[]{e(f2[0]), e(f2[1])};
    }

    public static int a(byte b2, double d2, int i2) {
        return d(f(b2, i2), d(d2));
    }

    public static int d(byte b2, double d2, int i2) {
        return a(x(), f(b2, i2), d(d2), 1);
    }

    public static double[] t() {
        double[] dArr = be;
        return new double[]{d(dArr[0]), d(dArr[1])};
    }

    public static double[] e(byte b2, int i2) {
        return new double[]{d(ba[c(b2)][c(i2)]), d(ay[c(b2)][c(i2)])};
    }

    public static String[] d(byte b2, int i2) {
        double[] e2 = e(b2, i2);
        return new String[]{e(e2[0]), e(e2[1])};
    }

    public static int d(byte b2, int i2, double d2) {
        return d(e(b2, i2), d(d2));
    }

    public static int b(byte b2, int i2, double d2) {
        return a(new double[]{t()[0], 35.0d}, e(b2, i2), d(d2), 1);
    }

    public static double[] d() {
        double[] dArr = v;
        return new double[]{d(dArr[0]), d(dArr[1])};
    }

    public static double[] e(byte b2, int i2, int i3) {
        double d2;
        double d3;
        double a2 = a(b2, i2, i3);
        if (i3 < 6 || i3 > 17) {
            if (b2 == 0) {
                d2 = s[g(a2)];
            } else {
                d2 = w[i(a2)];
            }
            if (b2 == 0) {
                d3 = q[g(a2)];
            } else {
                d3 = y[i(a2)];
            }
        } else {
            if (b2 == 0) {
                d2 = am[g(a2)];
            } else {
                d2 = an[i(a2)];
            }
            if (b2 == 0) {
                d3 = aj[g(a2)];
            } else {
                d3 = ak[i(a2)];
            }
        }
        return new double[]{UnitUtil.a(d2, 2), UnitUtil.a(d3, 2)};
    }

    public static String[] e(byte b2, int i2, int i3, int i4) {
        double[] e2 = e(b2, i2, i4);
        return new String[]{UnitUtil.e(UnitUtil.a(e2[0], i3), 1, i3), UnitUtil.e(UnitUtil.a(e2[1], i3), 1, i3)};
    }

    public static int b(dpv dpvVar) {
        return d(e(dpvVar.a(), dpvVar.e(), dpvVar.d()), UnitUtil.a(dpvVar.b(), dpvVar.c()));
    }

    public static int a(dpv dpvVar) {
        return a(d(), e(dpvVar.a(), dpvVar.e(), dpvVar.d()), UnitUtil.a(dpvVar.b(), dpvVar.c()), dpvVar.c());
    }

    public static double[] a() {
        double[] dArr = b;
        return new double[]{n(dArr[0]), n(dArr[1])};
    }

    public static double[] b(byte b2, int i2, double d2, int i3) {
        double d3;
        double d4;
        if (i3 == 0) {
            double d5 = f11753a[c(b2)][i(i2)];
            d3 = d5 * 0.9d;
            d4 = d5 * 1.1d;
        } else {
            d3 = c[c(b2)][c(i2)] * d2;
            d4 = e[c(b2)][c(i2)] * d2;
        }
        return new double[]{n(d3), n(d4)};
    }

    public static String[] e(byte b2, int i2, double d2, int i3) {
        double[] b3 = b(b2, i2, d2, i3);
        return new String[]{m(b3[0]), m(b3[1])};
    }

    public static int b(byte b2, int i2, double d2, double d3, int i3) {
        return d(b(b2, i2, d2, i3), n(d3));
    }

    public static int a(byte b2, int i2, double d2, double d3, int i3) {
        return a(new double[]{a()[0], 2500.0d}, b(b2, i2, d2, i3), n(d3), 1);
    }

    public static double[] g() {
        double[] dArr = al;
        return new double[]{n(dArr[0]), n(dArr[1])};
    }

    public static double[] j() {
        double[] dArr = ag;
        return new double[]{n(dArr[0]), n(dArr[1]), n(dArr[2])};
    }

    public static int b(double d2) {
        double n2 = n(d2);
        double[] j2 = j();
        if (n2 <= j2[0]) {
            return 1;
        }
        if (n2 <= j2[1]) {
            return 2;
        }
        return n2 <= j2[2] ? 3 : 4;
    }

    public static double[] z() {
        double[] dArr = ck;
        return new double[]{UnitUtil.a(dArr[0], 2), UnitUtil.a(dArr[1], 2)};
    }

    public static double[] d(byte b2) {
        return new double[]{UnitUtil.a(cj[c(b2)], 2)};
    }

    public static int b(byte b2, double d2) {
        return UnitUtil.a(d2, 2) < d(b2)[0] ? 1 : 2;
    }

    public static double[] n() {
        double[] dArr = az;
        return new double[]{n(dArr[0]), n(dArr[1])};
    }

    public static double[] k() {
        double[] dArr = bc;
        return new double[]{n(dArr[0]), n(dArr[1]), n(dArr[2])};
    }

    public static int c(double d2) {
        double n2 = n(d2);
        double[] k2 = k();
        if (n2 <= k2[0]) {
            return 1;
        }
        if (n2 <= k2[1]) {
            return 2;
        }
        return n2 <= k2[2] ? 3 : 4;
    }

    public static double[] q() {
        double[] dArr = bf;
        return new double[]{d(dArr[0]), d(dArr[1])};
    }

    public static double[] u() {
        double[] dArr = cb;
        return new double[]{d(dArr[0]), d(dArr[1])};
    }

    public static double[] o() {
        double[] dArr = ar;
        return new double[]{d(dArr[0]), d(dArr[1])};
    }

    public static double[] h() {
        double[] dArr = ad;
        return new double[]{n(dArr[0]), n(dArr[1])};
    }

    public static double[] p() {
        double[] dArr = bj;
        return new double[]{n(dArr[0]), n(dArr[1])};
    }

    public static double[] c() {
        double[] dArr = o;
        return new double[]{n(dArr[0]), n(dArr[1])};
    }

    public static double[] e() {
        double[] dArr = t;
        return new double[]{n(dArr[0]), n(dArr[1])};
    }

    public static double[] b() {
        double[] dArr = r;
        return new double[]{n(dArr[0]), n(dArr[1])};
    }

    public static int c(byte b2, int i2, double d2, int i3) {
        double d3 = d(d2);
        double[] c2 = c(b2, i2, i3);
        if (d3 < c2[0]) {
            return 1;
        }
        return d3 <= d((c2[1] + c2[2]) / 2.0d) ? 2 : 3;
    }

    public static int d(int i2, double d2) {
        int a2 = CommonUtil.a(String.valueOf(new BigDecimal(d2).setScale(0, 4)), 10);
        int i3 = 1;
        if (a2 != 1) {
            i3 = 2;
            if (a2 != 2) {
                i3 = 3;
                if (a2 != 3) {
                    return a2 != 4 ? a2 != 5 ? 0 : 5 : i2 == 0 ? 5 : 4;
                }
            }
        }
        return i3;
    }
}
