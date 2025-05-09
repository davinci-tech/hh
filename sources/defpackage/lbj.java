package defpackage;

import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.model.SportDetailChartDataType;
import com.huawei.hms.network.embedded.j;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes5.dex */
public class lbj {
    private static final int[] b = new int[0];

    public static int a(int i) {
        return i != 265 ? i != 281 ? i != 283 ? i != 273 ? i != 274 ? R.drawable._2131430591_res_0x7f0b0cbf : R.drawable._2131430372_res_0x7f0b0be4 : R.drawable._2131429896_res_0x7f0b0a08 : R.drawable._2131430440_res_0x7f0b0c28 : R.drawable._2131430571_res_0x7f0b0cab : R.drawable._2131429932_res_0x7f0b0a2c;
    }

    public static int a(int i, boolean z) {
        return i != 264 ? i != 265 ? R.string._2130840342_res_0x7f020b16 : z ? R.string._2130840346_res_0x7f020b1a : R.string._2130840348_res_0x7f020b1c : z ? R.string._2130840342_res_0x7f020b16 : R.string._2130840344_res_0x7f020b18;
    }

    public static int c(int i, boolean z) {
        return i != 264 ? i != 265 ? i != 273 ? i != 274 ? i != 281 ? i != 283 ? R.drawable._2131430551_res_0x7f0b0c97 : z ? R.drawable._2131430438_res_0x7f0b0c26 : R.drawable._2131430439_res_0x7f0b0c27 : z ? R.drawable._2131430569_res_0x7f0b0ca9 : R.drawable._2131430570_res_0x7f0b0caa : z ? R.drawable._2131430370_res_0x7f0b0be2 : R.drawable._2131430371_res_0x7f0b0be3 : z ? R.drawable._2131429894_res_0x7f0b0a06 : R.drawable._2131429895_res_0x7f0b0a07 : z ? R.drawable._2131429930_res_0x7f0b0a2a : R.drawable._2131429931_res_0x7f0b0a2b : z ? R.drawable._2131430551_res_0x7f0b0c97 : R.drawable._2131430552_res_0x7f0b0c98;
    }

    public static int d(int i, boolean z) {
        return i != 264 ? i != 265 ? R.string._2130840343_res_0x7f020b17 : z ? R.string._2130840347_res_0x7f020b1b : R.string._2130840349_res_0x7f020b1d : z ? R.string._2130840343_res_0x7f020b17 : R.string._2130840345_res_0x7f020b19;
    }

    public static int[] d(int i, int i2, int i3, String str) {
        int[] iArr = b;
        if (i == 264) {
            return b(i2, i3);
        }
        if (i == 265) {
            return e(i2, i3);
        }
        if (i == 273) {
            return d(i2);
        }
        if (i == 274) {
            return b(i2, str);
        }
        if (i != 281) {
            return i != 283 ? iArr : a(i2, i3);
        }
        return b(i2);
    }

    private static int[] b(int i, String str) {
        if (i == 0) {
            return str.equals("291") ? lbo.c : lbo.b;
        }
        if (i == 4) {
            return str.equals("291") ? lbo.h : lbo.d;
        }
        return b;
    }

    private static int[] d(int i) {
        if (i == 0) {
            return lbg.b;
        }
        if (i == 4) {
            return lbg.d;
        }
        return b;
    }

    private static int[] e(int i, int i2) {
        if (i == 0) {
            return e(i2);
        }
        if (i == 4) {
            if (i2 == 0) {
                return lbi.f;
            }
            if (i2 == 2) {
                return lbi.h;
            }
            return lbi.e;
        }
        if (i == 8) {
            return lbi.g;
        }
        if (i == 9) {
            return lbi.j;
        }
        if (i == 10) {
            return lbi.k;
        }
        return b;
    }

    private static int[] b(int i) {
        if (i == 0) {
            return lbp.b;
        }
        if (i == 2) {
            return lbp.c;
        }
        if (i == 3) {
            return lbp.e;
        }
        return b;
    }

    private static int[] b(int i, int i2) {
        if (i == 0) {
            return h(i2);
        }
        if (i == 1) {
            return lbl.r;
        }
        if (i == 2) {
            if (i2 == 0) {
                return lbl.k;
            }
            return lbl.f;
        }
        if (i == 3) {
            if (i2 == 2) {
                return lbl.n;
            }
            return lbl.l;
        }
        if (i == 4) {
            if (i2 == 0) {
                return lbl.i;
            }
            if (i2 == 2) {
                return lbl.h;
            }
            return lbl.g;
        }
        if (i == 8) {
            return lbl.j;
        }
        if (i == 9) {
            return lbl.o;
        }
        if (i == 10) {
            return lbl.w;
        }
        return b;
    }

    public static int[] c(int i) {
        if (i == 0) {
            return lbl.p;
        }
        if (i == 2) {
            return lbl.s;
        }
        return lbl.m;
    }

    private static int[] h(int i) {
        if (i == 0) {
            return lbl.b;
        }
        if (i == 2) {
            return lbl.e;
        }
        LogUtil.a(j.h, "getTreadmillArray, return default itemArray, targetType is ", Integer.valueOf(i));
        return lbl.f14745a;
    }

    private static int[] e(int i) {
        if (i == 0) {
            return lbi.d;
        }
        if (i == 2) {
            return lbi.b;
        }
        LogUtil.a(j.h, "getIndoorBikeTargetArray, return default itemArray, targetType is ", Integer.valueOf(i));
        return lbi.f14743a;
    }

    private static int[] a(int i, int i2) {
        if (i2 != 0) {
            if (i2 != 5 && i2 != 6) {
                if (i2 == 7) {
                    return lbm.c;
                }
                if (i2 == 8) {
                    return i(i);
                }
                if (i2 != 10 && i2 != 11) {
                    return b;
                }
            }
            return lbm.b;
        }
        return lbm.h;
    }

    private static int[] i(int i) {
        if (i == 5) {
            return lbm.f14746a;
        }
        if (i == 6) {
            return lbm.d;
        }
        return lbm.e;
    }

    public static SportDetailChartDataType[] a(int i, boolean z, boolean z2, String str) {
        if (i != 264) {
            if (i == 265) {
                return lbi.c;
            }
            if (i != 273) {
                return i != 274 ? new SportDetailChartDataType[0] : str.equals("291") ? lbo.e : lbo.f14748a;
            }
            return lbg.f14741a;
        }
        if (z) {
            return lbl.d;
        }
        if (z2) {
            return lbl.x;
        }
        return lbl.c;
    }

    public static SportDetailChartDataType[] c(int i, boolean z, String str) {
        if (i == 264) {
            return z ? lbl.t : lbl.q;
        }
        if (i == 265) {
            return lbi.c;
        }
        if (i != 273) {
            return i != 274 ? new SportDetailChartDataType[0] : str.equals("291") ? lbo.e : lbo.f14748a;
        }
        return lbg.f14741a;
    }
}
