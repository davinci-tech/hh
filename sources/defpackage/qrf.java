package defpackage;

import androidx.core.content.ContextCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.device.fatscale.multiusers.WeightDataManager;
import health.compact.a.Utils;

/* loaded from: classes7.dex */
public class qrf {
    public static int a(int i) {
        return i != 1 ? i != 2 ? i != 3 ? R.color._2131299364_res_0x7f090c24 : R.color._2131299360_res_0x7f090c20 : R.color._2131299362_res_0x7f090c22 : R.color._2131299361_res_0x7f090c21;
    }

    public static int b(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? R.drawable._2131427671_res_0x7f0b0157 : R.drawable._2131427668_res_0x7f0b0154 : R.drawable._2131427669_res_0x7f0b0155 : R.drawable._2131427667_res_0x7f0b0153 : R.drawable._2131427670_res_0x7f0b0156 : R.drawable._2131427666_res_0x7f0b0152;
    }

    public static int c(int i) {
        return i != 1 ? i != 2 ? i != 3 ? R.color._2131299360_res_0x7f090c20 : R.color._2131299363_res_0x7f090c23 : R.color._2131299362_res_0x7f090c22 : R.color._2131299361_res_0x7f090c21;
    }

    public static int d(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? R.drawable._2131427663_res_0x7f0b014f : R.drawable._2131427660_res_0x7f0b014c : R.drawable._2131427664_res_0x7f0b0150 : R.drawable._2131427662_res_0x7f0b014e : R.drawable._2131427661_res_0x7f0b014d : R.drawable._2131427659_res_0x7f0b014b;
    }

    public static int f(int i) {
        return i != 1 ? i != 2 ? i != 3 ? R.color._2131299364_res_0x7f090c24 : R.color._2131299360_res_0x7f090c20 : R.color._2131299363_res_0x7f090c23 : R.color._2131299362_res_0x7f090c22;
    }

    public static int j(int i) {
        return i != 1 ? R.color._2131299360_res_0x7f090c20 : R.color._2131299362_res_0x7f090c22;
    }

    public static int e(int i) {
        return ContextCompat.getColor(BaseApplication.e(), a(i));
    }

    public static int g(int i) {
        return ContextCompat.getColor(BaseApplication.e(), f(i));
    }

    public static int h(int i) {
        return ContextCompat.getColor(BaseApplication.e(), c(i));
    }

    public static int i(int i) {
        return ContextCompat.getColor(BaseApplication.e(), j(i));
    }

    public static double e(int i, cfe cfeVar) {
        if (cfeVar == null) {
            return -1.0d;
        }
        double b = b(i, cfeVar);
        if (!doj.b(b, -1.0d)) {
            return b;
        }
        double j = j(i, cfeVar);
        if (!doj.b(j, -1.0d)) {
            return j;
        }
        double f = f(i, cfeVar);
        if (doj.b(f, -1.0d)) {
            return -1.0d;
        }
        return f;
    }

    private static double b(int i, cfe cfeVar) {
        if (i == 1) {
            return doj.d(cfeVar.an(), cfeVar.e(), cfeVar.a(), 0);
        }
        if (i == 4) {
            return doj.b(cfeVar.an(), cfeVar.e(), cfeVar.ax(), cfeVar.d(), 0);
        }
        if (i != 14) {
            if (i == 400) {
                return doj.a(cfeVar.an(), cfeVar.e(), cfeVar.ax(), cfeVar.d(), 0);
            }
            switch (i) {
                case 100:
                    return doj.c(cfeVar.an(), cfeVar.e(), cfeVar.a(), 0);
                case 101:
                    return doj.a(cfeVar.an(), cfeVar.e(), cfeVar.a(), 0);
                case 102:
                    return doj.b(cfeVar.an(), cfeVar.e(), 0);
                case 103:
                    break;
                default:
                    return -1.0d;
            }
        }
        double e = doj.e(cfeVar.a(), cfeVar.ax());
        if (i == 14) {
            return doj.b(cfeVar.an(), cfeVar.e(), cfeVar.t(), e, 0);
        }
        return doj.c(cfeVar.an(), cfeVar.e(), cfeVar.t(), e, 1);
    }

    private static double j(int i, cfe cfeVar) {
        int d;
        int e = cfeVar.e();
        byte an = cfeVar.an();
        int t = cfeVar.t();
        if (i == 3 || i == 300) {
            double ap = cfeVar.ap();
            if (!dph.t(ap)) {
                ap = cfeVar.al();
            }
            if (i == 3) {
                d = doj.a(an, ap, e);
            } else {
                d = doj.d(an, ap, e);
            }
        } else {
            if (i != 500) {
                if (i == 600) {
                    d = doj.e(an, e, t, cfeVar.z(), 0);
                } else if (i == 700) {
                    d = doj.a(a(cfeVar));
                } else if (i != 5) {
                    if (i == 6) {
                        d = doj.a(an, e, t, cfeVar.z(), 0);
                    } else {
                        if (i != 7) {
                            return -1.0d;
                        }
                        d = doj.b(a(cfeVar));
                    }
                }
            }
            double s = cfeVar.s();
            if (i == 5) {
                d = doj.h(s);
            } else {
                d = doj.f(s);
            }
        }
        return d;
    }

    private static dpv a(cfe cfeVar) {
        dpv dpvVar = new dpv();
        dpvVar.a(cfeVar.an());
        dpvVar.c(cfeVar.t());
        dpvVar.d(cfeVar.i());
        dpvVar.d(0);
        dpvVar.b(cfeVar.getFractionDigitByType(7));
        dpvVar.a(cfeVar.e());
        return dpvVar;
    }

    private static double f(int i, cfe cfeVar) {
        int b;
        if (i != 8) {
            if (i == 10) {
                b = doj.d(cfeVar.an(), cfeVar.e(), cfeVar.t(), cfeVar.aj(), 0);
            } else if (i != 800) {
                if (i != 1000) {
                    return -1.0d;
                }
                b = doj.h(cfeVar.an(), cfeVar.e(), cfeVar.t(), cfeVar.aj(), 0);
            }
            return b;
        }
        double ap = cfeVar.ap();
        if (!dph.t(ap)) {
            ap = cfeVar.al();
        }
        double d = doj.d(cfeVar.ax(), ap, cfeVar.a(), cfeVar.i(), cfeVar.getFractionDigitByType(0));
        if (i == 8) {
            b = doj.d(cfeVar.an(), cfeVar.e(), d);
        } else {
            b = doj.b(cfeVar.an(), cfeVar.e(), d);
        }
        return b;
    }

    public static String a(int i, cfe cfeVar) {
        if (cfeVar == null || i != 99) {
            return "";
        }
        return WeightDataManager.INSTANCE.getHealthAdvice(doj.b(cfeVar.j(), Utils.o(), cfeVar.an(), cfeVar.e()) - 1, doj.d(cfeVar.an(), cfeVar.e(), cfeVar.a(), 0) - 1, doj.a(cfeVar.an(), cfeVar.e(), cfeVar.t(), cfeVar.z(), 0) - 1);
    }

    public static double[] c(int i, cfe cfeVar) {
        if (cfeVar == null) {
            return new double[0];
        }
        int e = cfeVar.e();
        byte an = cfeVar.an();
        int t = cfeVar.t();
        if (i == 1) {
            return doj.c(an, e, 0);
        }
        if (i == 6) {
            return doj.a(an, e, t, 0);
        }
        if (i == 14) {
            return doj.c(an, e, t, 0);
        }
        if (i == 10) {
            return doj.b(an, e, t, 0);
        }
        if (i == 5) {
            return doj.aa();
        }
        if (i == 3) {
            return doj.f(an, e);
        }
        if (i == 8) {
            return doj.e(an, e);
        }
        if (i == 7) {
            return doj.e(an, t, e);
        }
        return i == 4 ? doj.b(an, e, cfeVar.ax(), 0) : new double[0];
    }

    public static String[] d(int i, cfe cfeVar) {
        if (cfeVar == null) {
            return new String[0];
        }
        int e = cfeVar.e();
        byte an = cfeVar.an();
        if (i == 1) {
            return doj.d(an, e, 0);
        }
        if (i == 5) {
            return doj.w();
        }
        if (i == 3) {
            return doj.c(an, e);
        }
        if (i == 8) {
            return doj.d(an, e);
        }
        return i == 4 ? doj.e(an, e, cfeVar.ax(), 0) : new String[0];
    }
}
