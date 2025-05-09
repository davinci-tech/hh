package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import java.util.List;

/* loaded from: classes4.dex */
public class fgf {
    public static boolean c(List<ffs> list) {
        if (!g(list)) {
            return false;
        }
        for (ffs ffsVar : list) {
            if (ffsVar != null && ffsVar.n() > 0.0f) {
                LogUtil.a("Track_SportAccessoriesUtils", "valid GcTimeBalance ", Float.valueOf(ffsVar.n()));
                return true;
            }
        }
        return false;
    }

    public static boolean g(List<ffs> list) {
        if (koq.b(list)) {
            return false;
        }
        long j = 0;
        for (ffs ffsVar : list) {
            if (ffsVar != null) {
                long a2 = a(ffsVar.g());
                if (a2 < a(j)) {
                    LogUtil.a("Track_SportAccessoriesUtils", "isHideRunningPostureView time is wrong");
                    return false;
                }
                j = a2;
            }
        }
        return true;
    }

    private static long a(long j) {
        long j2 = j / 5000;
        return j % 5000 > 2500 ? (j2 + 1) * 5 : j2 * 5;
    }

    public static boolean i(List<ffs> list) {
        if (!g(list)) {
            return false;
        }
        for (ffs ffsVar : list) {
            if (ffsVar != null && ffsVar.t() > 0.0f) {
                LogUtil.a("Track_SportAccessoriesUtils", "valid VerticalOscillation ", Float.valueOf(ffsVar.t()));
                return true;
            }
        }
        return false;
    }

    public static boolean b(List<ffs> list) {
        if (!g(list)) {
            return false;
        }
        for (ffs ffsVar : list) {
            if (ffsVar != null && ffsVar.m() > 0.0f) {
                LogUtil.a("Track_SportAccessoriesUtils", "valid ImpactPeak ", Float.valueOf(ffsVar.m()));
                return true;
            }
        }
        return false;
    }

    public static boolean h(List<ffs> list) {
        if (!g(list)) {
            return false;
        }
        for (ffs ffsVar : list) {
            if (ffsVar != null && ffsVar.r() > 0.0f) {
                LogUtil.a("Track_SportAccessoriesUtils", "valid getVerticalRatio ", Float.valueOf(ffsVar.r()));
                return true;
            }
        }
        return false;
    }

    public static boolean f(List<ffs> list) {
        if (!g(list)) {
            return false;
        }
        for (ffs ffsVar : list) {
            if (ffsVar != null && ffsVar.l() > 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean d(List<ffs> list) {
        if (!g(list)) {
            return false;
        }
        for (ffs ffsVar : list) {
            if (ffsVar != null && ffsVar.e() > 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean a(List<ffs> list) {
        if (!g(list)) {
            return false;
        }
        for (ffs ffsVar : list) {
            if (ffsVar != null && ffsVar.b() > 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean e(List<ffs> list) {
        return b(list) || i(list);
    }

    public static boolean j(List<ffs> list) {
        if (!g(list)) {
            return false;
        }
        for (ffs ffsVar : list) {
            if (ffsVar != null && ffsVar.o() > 0) {
                return true;
            }
        }
        return false;
    }
}
