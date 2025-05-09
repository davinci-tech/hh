package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import com.huawei.health.R;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsportmodel.CommonSegment;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public final class gwr {
    private static float a(float f, float f2) {
        if (f == -1.0f || f2 == -1.0f) {
            return -1.0f;
        }
        return f + f2;
    }

    private static long a(long j, long j2) {
        if (j == -1 || j2 == -1) {
            return -1L;
        }
        return j + j2;
    }

    public static boolean a(int i) {
        return i == 258 || i == 280;
    }

    public static boolean d(int i) {
        return i == 259;
    }

    private static float e(float f, long j) {
        if (f == -1.0f || j == -1) {
            return -1.0f;
        }
        return f * j;
    }

    private static long e(long j, long j2) {
        if (j == -1 || j2 == -1) {
            return -1L;
        }
        return j * j2;
    }

    private static long j(long j) {
        if (j < 40 || j > 220) {
            return -1L;
        }
        return j;
    }

    public static kwu e(List<kwu> list) {
        kwu kwuVar = new kwu();
        if (koq.b(list)) {
            kwuVar.x();
            return kwuVar;
        }
        if (list.size() == 1) {
            return new kwu(list.get(0));
        }
        kwuVar.u();
        long j = 0;
        long j2 = 0;
        for (kwu kwuVar2 : list) {
            if (kwuVar2 != null) {
                kwuVar.d(a(kwuVar.t(), kwuVar2.t()));
                kwuVar.e(a(kwuVar.f(), kwuVar2.f()));
                kwuVar.b(a(kwuVar.p(), kwuVar2.p()));
                kwuVar.c(a(kwuVar.q(), kwuVar2.q()));
                j2 = a(j2, e(j(kwuVar2.l()), kwuVar2.t()));
                j = a(j, e(kwuVar2.g(), kwuVar2.t()));
            }
        }
        kwuVar.j((int) b(e(kwuVar.t(), 100000L), kwuVar.f()));
        kwuVar.e((int) b(j, kwuVar.t()));
        kwuVar.h((int) j(b(j2, kwuVar.t())));
        c(list, kwuVar, j);
        return kwuVar;
    }

    private static void c(List<kwu> list, kwu kwuVar, long j) {
        Iterator<kwu> it = list.iterator();
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        float f = 0.0f;
        float f2 = 0.0f;
        float f3 = 0.0f;
        float f4 = 0.0f;
        long j5 = 0;
        while (it.hasNext()) {
            kwu next = it.next();
            if (next != null) {
                long e = e(next.g(), next.t());
                j2 = a(j2, e(next.m(), e));
                j4 = a(j4, e(next.c(), e));
                j3 = a(j3, e(next.i(), e));
                j5 = a(j5, e(next.s(), e));
                f3 = a(f3, e(next.j(), e));
                f4 = a(f4, e(next.d(), e));
                f2 = a(f2, e(next.e(), e));
                f = a(f, e(next.b(), e));
                it = it;
            }
        }
        int b = (int) b(e(kwuVar.f(), 60L), j);
        kwuVar.g(b);
        kwuVar.b((int) b(j2, j));
        kwuVar.c(-1);
        kwuVar.i((int) b(j5, j));
        kwuVar.a((int) b(j3, j));
        kwuVar.d((int) b(j4, j));
        kwuVar.b(a(f, j));
        float a2 = a(f2, j);
        kwuVar.d(a2);
        kwuVar.c(a(a2, b));
        kwuVar.a(a(f3, j));
        kwuVar.e(a(f4, j));
    }

    public static kwq a(List<kwq> list) {
        kwq kwqVar = new kwq();
        if (koq.b(list)) {
            kwqVar.f();
            return kwqVar;
        }
        if (list.size() == 1) {
            return new kwq(list.get(0));
        }
        kwqVar.n();
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        for (kwq kwqVar2 : list) {
            if (kwqVar2 != null) {
                kwqVar.b(a(kwqVar.h(), kwqVar2.h()));
                kwqVar.a(a(kwqVar.d(), kwqVar2.d()));
                kwqVar.e(a(kwqVar.g(), kwqVar2.g()));
                kwqVar.d(a(kwqVar.j(), kwqVar2.j()));
                j3 = a(j3, e(j(kwqVar2.b()), kwqVar2.h()));
                j2 = a(j2, e(kwqVar2.a(), kwqVar2.h()));
                j = a(j, e(kwqVar2.e(), kwqVar2.h()));
            }
        }
        kwqVar.d((int) b(j, kwqVar.h()));
        kwqVar.e((int) b(j2, kwqVar.h()));
        kwqVar.b((int) b(j(j3), kwqVar.h()));
        return kwqVar;
    }

    public static void a(nre<hjj, hjc, hjc, hjc, hjc, hjc> nreVar, int i) {
        if (nreVar == null) {
            LogUtil.h("Track_SegmentUtils", "addRunningSegmentTableTitle with error parameter tableDataSet is null, pls check");
            return;
        }
        Context context = BaseApplication.getContext();
        if (context == null) {
            LogUtil.h("Track_SegmentUtils", "addRunningSegmentTableTitle with context is null, pls check");
            nreVar.putRowColumnHeaderData(0, 0, null);
            return;
        }
        nreVar.putRowColumnHeaderData(0, 0, new hjc(aa(context)));
        ArrayList arrayList = new ArrayList();
        if (i == 0) {
            d(arrayList, context, nreVar);
        } else {
            if (i != 1) {
                return;
            }
            e(arrayList, context, nreVar);
        }
    }

    private static void d(List<hjj> list, Context context, nre<hjj, hjc, hjc, hjc, hjc, hjc> nreVar) {
        list.add(new hjj(ah(context), null));
        list.add(new hjj(s(context), p(context)));
        list.add(new hjj(x(context), v(context)));
        list.add(new hjj(r(context), u(context)));
        list.add(new hjj(a(context), d(context)));
        list.add(new hjj(z(context), ab(context)));
        list.add(new hjj(ai(context), af(context)));
        list.add(new hjj(ak(context), am(context)));
        list.add(new hjj(b(context), h(context)));
        list.add(new hjj(i(context), f(context)));
        list.add(new hjj(c(context), e(context)));
        list.add(new hjj(l(context), o(context)));
        list.add(new hjj(context.getResources().getString(R.string._2130843150_res_0x7f02160e), h(context)));
        list.add(new hjj(context.getResources().getString(R.string._2130845182_res_0x7f021dfe), null));
        list.add(new hjj(context.getResources().getString(R.string._2130845169_res_0x7f021df1), ab(context)));
        list.add(new hjj(context.getResources().getString(R.string._2130845170_res_0x7f021df2), null));
        list.add(new hjj(j(context), m(context)));
        list.add(new hjj(context.getResources().getString(R.string._2130845178_res_0x7f021dfa), g(context)));
        for (int i = 1; i < list.size() + 1; i++) {
            nreVar.putColumnHeaderData(0, i, list.get(i - 1));
        }
    }

    private static void e(List<hjj> list, Context context, nre<hjj, hjc, hjc, hjc, hjc, hjc> nreVar) {
        list.add(new hjj(aj(context), null));
        d(list, context, nreVar);
    }

    public static void b(nre<hjj, hjc, hjc, hjc, hjc, hjc> nreVar, kwu kwuVar, int i, boolean z) {
        if (nreVar == null || kwuVar == null) {
            LogUtil.h("Track_SegmentUtils", "addRunningSegmentToTableSet with error parameter tableDataSet:", nreVar, " trackRunningSegment:", kwuVar);
        } else {
            e(nreVar, nreVar.getRowsCount(), kwuVar, i, z);
        }
    }

    public static int c(List<CommonSegment> list) {
        Iterator<CommonSegment> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().getSportSegmentMode() == 1) {
                return 1;
            }
        }
        return 0;
    }

    public static void e(nre<hjj, hjc, hjc, hjc, hjc, hjc> nreVar, int i, kwu kwuVar, int i2, boolean z) {
        if (nreVar == null || kwuVar == null || i < 0) {
            LogUtil.h("Track_SegmentUtils", "putRunningSegmentToTableSet with error parameter tableDataSet:", nreVar, " trackRunningSegment:", kwuVar, " rowIndex:", Integer.valueOf(i));
            return;
        }
        nreVar.putRowHeaderData(i, 0, new hjc(g(kwuVar.n())));
        ArrayList arrayList = new ArrayList();
        if (i2 == 0) {
            c(nreVar, i, kwuVar, arrayList, z);
        } else {
            if (i2 != 1) {
                return;
            }
            d(nreVar, i, kwuVar, arrayList, z);
        }
    }

    private static void d(nre<hjj, hjc, hjc, hjc, hjc, hjc> nreVar, int i, kwu kwuVar, List<hjc> list, boolean z) {
        list.add(new hjc(a(BaseApplication.getContext(), kwuVar.y(), 258)));
        c(nreVar, i, kwuVar, list, z);
    }

    private static void c(nre<hjj, hjc, hjc, hjc, hjc, hjc> nreVar, int i, kwu kwuVar, List<hjc> list, boolean z) {
        if (!z) {
            list.add(new hjc(i(kwuVar.t())));
        } else {
            list.add(new hjc(UnitUtil.d((int) kwuVar.t())));
        }
        list.add(new hjc(c(kwuVar.f())));
        list.add(new hjc(d(kwuVar.k())));
        list.add(new hjc(j((int) j(kwuVar.l()))));
        list.add(new hjc(e(kwuVar.g())));
        list.add(new hjc(n(kwuVar.r())));
        list.add(new hjc(f(kwuVar.p())));
        list.add(new hjc(g(kwuVar.q())));
        list.add(new hjc(b(kwuVar.m())));
        list.add(new hjc(e(kwuVar.o())));
        list.add(new hjc(c(kwuVar.i())));
        list.add(new hjc(b(kwuVar.s())));
        list.add(new hjc(j(kwuVar.c())));
        list.add(new hjc(a(kwuVar.b())));
        list.add(new hjc(b(kwuVar.e())));
        list.add(new hjc(c(kwuVar.h())));
        list.add(new hjc(b(kwuVar.j())));
        list.add(new hjc(b(kwuVar.d())));
        for (int i2 = 1; i2 < list.size() + 1; i2++) {
            int i3 = i2 - 1;
            LogUtil.a("Track_SegmentUtils", "tableDataSet index is ", Integer.valueOf(i2), "data is ", list.get(i3));
            nreVar.putContentData(i, i2, list.get(i3));
        }
    }

    public static void e(nre<hjj, hjc, hjc, hjc, hjc, hjc> nreVar, kwu kwuVar, String str, int i, boolean z) {
        if (nreVar == null) {
            LogUtil.h("Track_SegmentUtils", "addRunningSegmentToTableSet with error parameter tableDataSet is null");
        } else {
            b(nreVar, nreVar.getRowsCount(), kwuVar, str, i, z);
        }
    }

    public static void e(nre<hjj, hjc, hjc, hjc, hjc, hjc> nreVar, kwq kwqVar, String str) {
        if (nreVar == null) {
            LogUtil.h("Track_SegmentUtils", "addBikeSegmentToTableSet with error parameter tableDataSet is null");
        } else {
            e(nreVar, nreVar.getRowsCount(), kwqVar, str);
        }
    }

    public static void e(nre<hjj, hjc, hjc, hjc, hjc, hjc> nreVar, int i, kwq kwqVar, String str) {
        if (nreVar == null || i < 0) {
            LogUtil.h("Track_SegmentUtils", "addBikeSegmentToTableSet with error parameter tableDataSet:", nreVar, " rowIndex", Integer.valueOf(i));
            return;
        }
        if (!TextUtils.isEmpty(str)) {
            nreVar.putStatisticHeaderData(i, 0, new hjc(str));
        }
        if (kwqVar == null) {
            LogUtil.h("Track_SegmentUtils", "addBikeSegmentToTableSet with null trackBikeSegment");
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new hjc(a(BaseApplication.getContext(), kwqVar.i(), 259)));
        arrayList.add(new hjc(i(kwqVar.h())));
        arrayList.add(new hjc(c(kwqVar.d())));
        arrayList.add(new hjc(h(kwqVar.a())));
        arrayList.add(new hjc(j((int) j(kwqVar.b()))));
        arrayList.add(new hjc(f(kwqVar.g())));
        arrayList.add(new hjc(g(kwqVar.j())));
        for (int i2 = 1; i2 < arrayList.size() + 1; i2++) {
            nreVar.putStatisticData(i, i2, (hjc) arrayList.get(i2 - 1));
        }
    }

    public static void b(nre<hjj, hjc, hjc, hjc, hjc, hjc> nreVar, int i, kwu kwuVar, String str, int i2, boolean z) {
        if (nreVar == null || i < 0) {
            LogUtil.h("Track_SegmentUtils", "addRunningSegmentToTableSet with error parameter tableDataSet:", nreVar, " rowIndex", Integer.valueOf(i));
            return;
        }
        if (!TextUtils.isEmpty(str)) {
            nreVar.putStatisticHeaderData(i, 0, new hjc(str));
        }
        if (kwuVar == null) {
            LogUtil.h("Track_SegmentUtils", "addRunningSegmentToTableSet with null trackRunningSegment");
            return;
        }
        ArrayList arrayList = new ArrayList();
        if (i2 == 0) {
            e(nreVar, i, kwuVar, arrayList, z);
        } else {
            if (i2 != 1) {
                return;
            }
            b(nreVar, i, kwuVar, arrayList, z);
        }
    }

    private static void b(nre<hjj, hjc, hjc, hjc, hjc, hjc> nreVar, int i, kwu kwuVar, List<hjc> list, boolean z) {
        list.add(new hjc(null));
        e(nreVar, i, kwuVar, list, z);
    }

    private static void e(nre<hjj, hjc, hjc, hjc, hjc, hjc> nreVar, int i, kwu kwuVar, List<hjc> list, boolean z) {
        if (!z) {
            list.add(new hjc(i(kwuVar.t())));
        } else {
            list.add(new hjc(UnitUtil.d((int) kwuVar.t())));
        }
        list.add(new hjc(c(kwuVar.f())));
        list.add(new hjc(d(kwuVar.k())));
        list.add(new hjc(j((int) j(kwuVar.l()))));
        list.add(new hjc(e(kwuVar.g())));
        list.add(new hjc(n(kwuVar.r())));
        list.add(new hjc(f(kwuVar.p())));
        list.add(new hjc(g(kwuVar.q())));
        list.add(new hjc(b(kwuVar.m())));
        list.add(new hjc(e(kwuVar.o())));
        list.add(new hjc(c(kwuVar.i())));
        list.add(new hjc(b(kwuVar.s())));
        list.add(new hjc(j(kwuVar.c())));
        list.add(new hjc(a(kwuVar.b())));
        list.add(new hjc(b(kwuVar.e())));
        list.add(new hjc(c(kwuVar.h())));
        list.add(new hjc(b(kwuVar.j())));
        list.add(new hjc(b(kwuVar.d())));
        for (int i2 = 1; i2 < list.size() + 1; i2++) {
            nreVar.putStatisticData(i, i2, list.get(i2 - 1));
        }
    }

    public static void a(nre<hjj, hjc, hjc, hjc, hjc, hjc> nreVar) {
        if (nreVar == null) {
            LogUtil.h("Track_SegmentUtils", "addSkiSegmentTableTitle with error parameter tableDataSet is null, pls check");
            return;
        }
        Context context = BaseApplication.getContext();
        if (context == null) {
            LogUtil.h("Track_SegmentUtils", "addSkiSegmentTableTitle with context is null, pls check");
            nreVar.putRowColumnHeaderData(0, 0, null);
            return;
        }
        nreVar.putRowColumnHeaderData(0, 0, new hjc(context.getString(R.string._2130839892_res_0x7f020954)));
        nreVar.putColumnHeaderData(0, 1, new hjj(context.getString(R.string._2130839890_res_0x7f020952), null));
        nreVar.putColumnHeaderData(0, 2, new hjj(context.getString(R.string._2130839891_res_0x7f020953), p(context)));
        nreVar.putColumnHeaderData(0, 3, new hjj(context.getString(R.string._2130842157_res_0x7f02122d), ac(context)));
        nreVar.putColumnHeaderData(0, 4, new hjj(context.getString(R.string._2130839763_res_0x7f0208d3), ac(context)));
    }

    public static void c(nre<hjj, hjc, hjc, hjc, hjc, hjc> nreVar) {
        if (nreVar == null) {
            LogUtil.h("Track_SegmentUtils", "addRowingMachineSegmentTableTitle with error parameter tableDataSet is null, pls check");
            return;
        }
        Context context = BaseApplication.getContext();
        if (context == null) {
            LogUtil.h("Track_SegmentUtils", "addRowingMachineSegmentTableTitle with context is null, pls check");
            nreVar.putRowColumnHeaderData(0, 0, null);
            return;
        }
        nreVar.putRowColumnHeaderData(0, 0, new hjc(context.getString(R.string._2130845946_res_0x7f0220fa)));
        nreVar.putColumnHeaderData(0, 1, new hjj(context.getString(R.string._2130839907_res_0x7f020963), null));
        nreVar.putColumnHeaderData(0, 2, new hjj(context.getString(R.string._2130838933_res_0x7f020595), null));
        nreVar.putColumnHeaderData(0, 3, new hjj(context.getString(R.string._2130845949_res_0x7f0220fd), y(context)));
        nreVar.putColumnHeaderData(0, 4, new hjj(context.getString(R.string._2130845948_res_0x7f0220fc), w(context)));
        nreVar.putColumnHeaderData(0, 5, new hjj(context.getString(R.string._2130839500_res_0x7f0207cc), u(context)));
    }

    public static void b(nre<hjj, hjc, hjc, hjc, hjc, hjc> nreVar, boolean z) {
        if (nreVar == null) {
            LogUtil.h("Track_SegmentUtils", "addRopeSkippingSegmentTableTitle with error parameter tableDataSet is null, pls check");
            return;
        }
        Context context = BaseApplication.getContext();
        if (context == null) {
            LogUtil.h("Track_SegmentUtils", "addRopeSkippingSegmentTableTitle with context is null, pls check");
            nreVar.putRowColumnHeaderData(0, 0, null);
            return;
        }
        nreVar.putRowColumnHeaderData(0, 0, new hjc(context.getString(R.string._2130845946_res_0x7f0220fa)));
        nreVar.putColumnHeaderData(0, 1, new hjj(context.getString(R.string._2130839907_res_0x7f020963), null));
        nreVar.putColumnHeaderData(0, 2, new hjj(context.getString(R.string._2130838933_res_0x7f020595), null));
        nreVar.putColumnHeaderData(0, 3, new hjj(context.getString(R.string._2130839763_res_0x7f0208d3), u(context)));
        if (z) {
            nreVar.putColumnHeaderData(0, 4, new hjj(context.getString(R.string._2130839500_res_0x7f0207cc), u(context)));
        } else {
            nreVar.putColumnHeaderData(0, 4, new hjj(context.getString(R.string._2130843709_res_0x7f02183d), null));
        }
    }

    public static boolean d(List<CommonSegment> list) {
        Iterator<CommonSegment> it = list.iterator();
        while (it.hasNext()) {
            if (((kwt) it.next()).d() > 0) {
                return true;
            }
        }
        return false;
    }

    public static void d(nre<hjj, hjc, hjc, hjc, hjc, hjc> nreVar) {
        if (nreVar == null) {
            LogUtil.h("Track_SegmentUtils", "addSkiSegmentTableTitle with error parameter tableDataSet is null, pls check");
            return;
        }
        Context context = BaseApplication.getContext();
        if (context == null) {
            LogUtil.h("Track_SegmentUtils", "addSkiSegmentTableTitle with context is null, pls check");
            nreVar.putRowColumnHeaderData(0, 0, null);
            return;
        }
        nreVar.putRowColumnHeaderData(0, 0, new hjc(aa(context)));
        nreVar.putColumnHeaderData(0, 1, new hjj(aj(context), null));
        nreVar.putColumnHeaderData(0, 2, new hjj(ah(context), null));
        nreVar.putColumnHeaderData(0, 3, new hjj(s(context), p(context)));
        nreVar.putColumnHeaderData(0, 4, new hjj(context.getString(R.string._2130844076_res_0x7f0219ac), ac(context)));
        nreVar.putColumnHeaderData(0, 5, new hjj(r(context), u(context)));
        nreVar.putColumnHeaderData(0, 6, new hjj(ai(context), af(context)));
        nreVar.putColumnHeaderData(0, 7, new hjj(ak(context), am(context)));
    }

    public static void e(nre<hjj, hjc, hjc, hjc, hjc, hjc> nreVar) {
        if (nreVar == null) {
            LogUtil.h("Track_SegmentUtils", "addOtherSegmentTableTitle with error parameter tableDataSet is null");
            return;
        }
        Context context = BaseApplication.getContext();
        if (context == null) {
            LogUtil.h("Track_SegmentUtils", "addOtherSegmentTableTitle with context is null, pls check");
            nreVar.putRowColumnHeaderData(0, 0, null);
        } else {
            nreVar.putRowColumnHeaderData(0, 0, new hjc(aa(context)));
            nreVar.putColumnHeaderData(0, 1, new hjj(aj(context), null));
            nreVar.putColumnHeaderData(0, 2, new hjj(ah(context), null));
            nreVar.putColumnHeaderData(0, 3, new hjj(r(context), u(context)));
        }
    }

    public static void c(nre<hjj, hjc, hjc, hjc, hjc, hjc> nreVar, kwq kwqVar) {
        if (nreVar == null || kwqVar == null) {
            LogUtil.h("Track_SegmentUtils", "addBikeSegmentToTableSet with error parameter tableDataSet:", nreVar, " bikeSegment:", kwqVar);
        } else {
            b(nreVar, nreVar.getRowsCount(), kwqVar);
        }
    }

    public static void a(nre<hjj, hjc, hjc, hjc, hjc, hjc> nreVar, kwp kwpVar) {
        if (nreVar == null || kwpVar == null) {
            LogUtil.h("Track_SegmentUtils", "addOtherSegmentToTableSet with error parameter tableDataSet:", nreVar, " otherSportSegment:", kwpVar);
        } else {
            a(nreVar, nreVar.getRowsCount(), kwpVar);
        }
    }

    public static void a(nre<hjj, hjc, hjc, hjc, hjc, hjc> nreVar, kwv kwvVar) {
        if (nreVar == null || kwvVar == null) {
            LogUtil.h("Track_SegmentUtils", "addSkiSegmentToTableSet with error parameter tableDataSet:", nreVar, " trackSkiSegment:", kwvVar);
        } else {
            c(nreVar, nreVar.getRowsCount(), kwvVar);
        }
    }

    public static void d(nre<hjj, hjc, hjc, hjc, hjc, hjc> nreVar, kwl kwlVar) {
        if (nreVar == null || kwlVar == null) {
            LogUtil.h("Track_SegmentUtils", "addRowingMachineSegmentToTableSet with error parameter tableDataSet:", nreVar, " rowingMachineSegment:", kwlVar);
        } else {
            a(nreVar, nreVar.getRowsCount(), kwlVar);
        }
    }

    public static void e(nre<hjj, hjc, hjc, hjc, hjc, hjc> nreVar, kwt kwtVar, boolean z) {
        if (nreVar == null || kwtVar == null) {
            LogUtil.h("Track_SegmentUtils", "addRopeSkippingSegmentToTableSet with error parameter tableDataSet:", nreVar, " trackJumpRopeSegment:", kwtVar);
        } else {
            e(nreVar, nreVar.getRowsCount(), kwtVar, z);
        }
    }

    private static void a(nre<hjj, hjc, hjc, hjc, hjc, hjc> nreVar, int i, kwp kwpVar) {
        if (nreVar == null || kwpVar == null || i < 0) {
            LogUtil.h("Track_SegmentUtils", "putOtherSegmentToTableSet with error parameter tableDataSet:", nreVar, " otherSegment:", kwpVar, " rowIndex:", Integer.valueOf(i));
            return;
        }
        Context context = BaseApplication.getContext();
        nreVar.putRowHeaderData(i, 0, new hjc(g(kwpVar.b())));
        nreVar.putContentData(i, 1, new hjc(a(context, kwpVar.d(), OldToNewMotionPath.SPORT_TYPE_OTHER_SPORT)));
        nreVar.putContentData(i, 2, new hjc(i(kwpVar.c())));
        nreVar.putContentData(i, 3, new hjc(j((int) j(kwpVar.a()))));
    }

    private static void b(nre<hjj, hjc, hjc, hjc, hjc, hjc> nreVar, int i, kwq kwqVar) {
        if (nreVar == null || kwqVar == null || i < 0) {
            LogUtil.h("Track_SegmentUtils", "putBikeSegmentToTableSet with error parameter tableDataSet:", nreVar, " bikeSegment:", kwqVar, " rowIndex:", Integer.valueOf(i));
            return;
        }
        Context context = BaseApplication.getContext();
        nreVar.putRowHeaderData(i, 0, new hjc(g(kwqVar.c())));
        nreVar.putContentData(i, 1, new hjc(a(context, kwqVar.i(), 259)));
        nreVar.putContentData(i, 2, new hjc(i(kwqVar.h())));
        nreVar.putContentData(i, 3, new hjc(c(kwqVar.d())));
        nreVar.putContentData(i, 4, new hjc(h(kwqVar.a())));
        nreVar.putContentData(i, 5, new hjc(j((int) j(kwqVar.b()))));
        nreVar.putContentData(i, 6, new hjc(f(kwqVar.g())));
        nreVar.putContentData(i, 7, new hjc(g(kwqVar.j())));
    }

    private static void c(nre<hjj, hjc, hjc, hjc, hjc, hjc> nreVar, int i, kwv kwvVar) {
        if (nreVar == null || kwvVar == null || i < 0) {
            LogUtil.h("Track_SegmentUtils", "putSkiSegmentToTableSet with error parameter tableDataSet:", nreVar, " trackSkiSegment:", kwvVar, " rowIndex:", Integer.valueOf(i));
            return;
        }
        nreVar.putRowHeaderData(i, 0, new hjc(g(kwvVar.j())));
        nreVar.putContentData(i, 1, new hjc(i(kwvVar.n())));
        nreVar.putContentData(i, 2, new hjc(a(kwvVar.c())));
        nreVar.putContentData(i, 3, new hjc(h(kwvVar.h())));
        nreVar.putContentData(i, 4, new hjc(h(kwvVar.a())));
    }

    private static void a(nre<hjj, hjc, hjc, hjc, hjc, hjc> nreVar, int i, kwl kwlVar) {
        if (nreVar == null || kwlVar == null || i < 0) {
            LogUtil.h("Track_SegmentUtils", "putRowMachineSegmentToTableSet with error parameter tableDataSet:", nreVar, " rowingMachineSegment:", kwlVar, " rowIndex:", Integer.valueOf(i));
            return;
        }
        nreVar.putRowHeaderData(i, 0, new hjc(g(kwlVar.a())));
        nreVar.putContentData(i, 1, new hjc(i(kwlVar.i())));
        nreVar.putContentData(i, 2, new hjc(String.valueOf(kwlVar.b())));
        int e = kwlVar.e();
        if (UnitUtil.h()) {
            e = (int) Math.round(UnitUtil.h(e));
        }
        nreVar.putContentData(i, 3, new hjc(String.valueOf(e)));
        nreVar.putContentData(i, 4, new hjc(String.valueOf(kwlVar.d())));
        nreVar.putContentData(i, 5, new hjc(String.valueOf(kwlVar.c())));
    }

    private static void e(nre<hjj, hjc, hjc, hjc, hjc, hjc> nreVar, int i, kwt kwtVar, boolean z) {
        if (nreVar == null || kwtVar == null || i < 0) {
            LogUtil.h("Track_SegmentUtils", "putRowMachineSegmentToTableSet with error parameter tableDataSet:", nreVar, " rowingMachineSegment:", kwtVar, " rowIndex:", Integer.valueOf(i));
            return;
        }
        nreVar.putRowHeaderData(i, 0, new hjc(g(kwtVar.b())));
        nreVar.putContentData(i, 1, new hjc(i(kwtVar.g())));
        nreVar.putContentData(i, 2, new hjc(String.valueOf(kwtVar.i())));
        nreVar.putContentData(i, 3, new hjc(String.valueOf(kwtVar.e())));
        if (z) {
            nreVar.putContentData(i, 4, new hjc(String.valueOf(kwtVar.d())));
        } else {
            nreVar.putContentData(i, 4, new hjc(String.valueOf(kwtVar.a())));
        }
    }

    public static CommonSegment d(MotionPathSimplify motionPathSimplify) {
        if (motionPathSimplify == null) {
            LogUtil.h("Track_SegmentUtils", "convertSimplifyToSegment with null parameter, pls check");
            return null;
        }
        int requestSportType = motionPathSimplify.requestSportType();
        if (a(requestSportType)) {
            return c(motionPathSimplify);
        }
        if (d(requestSportType)) {
            return b(motionPathSimplify);
        }
        return null;
    }

    private static CommonSegment b(MotionPathSimplify motionPathSimplify) {
        kwq kwqVar = new kwq();
        kwqVar.b(TimeUnit.MILLISECONDS.toSeconds(motionPathSimplify.requestTotalTime()));
        kwqVar.a(motionPathSimplify.requestTotalDistance() * 100);
        kwqVar.b((int) j(motionPathSimplify.requestAvgHeartRate()));
        kwqVar.d(motionPathSimplify.getExtendDataInt("crossTrainerCadence", -1));
        kwqVar.e((int) (360000.0f / motionPathSimplify.requestAvgPace()));
        kwqVar.d(Math.round(motionPathSimplify.requestTotalDescent() * 10.0f));
        kwqVar.e(Math.round(motionPathSimplify.requestCreepingWave() * 10.0f));
        kwqVar.a(0);
        return kwqVar;
    }

    private static CommonSegment c(MotionPathSimplify motionPathSimplify) {
        kwu kwuVar = new kwu();
        kwuVar.d(TimeUnit.MILLISECONDS.toSeconds(motionPathSimplify.requestTotalTime()));
        kwuVar.e(motionPathSimplify.requestTotalDistance() * 100);
        kwuVar.j(Math.round(motionPathSimplify.requestAvgPace()));
        kwuVar.h((int) j(motionPathSimplify.requestAvgHeartRate()));
        kwuVar.e(motionPathSimplify.requestAvgStepRate());
        kwuVar.g((int) Math.round((motionPathSimplify.requestTotalDistance() * 100.0d) / motionPathSimplify.requestTotalSteps()));
        kwuVar.c(Math.round(motionPathSimplify.requestTotalDescent() * 10.0f));
        kwuVar.b(Math.round(motionPathSimplify.requestCreepingWave() * 10.0f));
        kwuVar.b(motionPathSimplify.requestAvgGroundContactTime());
        kwuVar.c(motionPathSimplify.requestAvgGroundImpactAcceleration());
        kwuVar.a(motionPathSimplify.requestAvgEversionExcursion());
        kwuVar.i(motionPathSimplify.requestAvgSwingAngle());
        kwuVar.l(0);
        kwuVar.d(motionPathSimplify.requestAverageHangTime());
        kwuVar.b((float) UnitUtil.a(motionPathSimplify.getExtendDataFloat("avg_gc_tb"), 1));
        kwuVar.d((float) UnitUtil.a(motionPathSimplify.getExtendDataFloat("avg_v_osc"), 1));
        kwuVar.c((float) UnitUtil.a(motionPathSimplify.getExtendDataFloat("avg_v_s_r"), 1));
        kwuVar.a((float) UnitUtil.a(motionPathSimplify.getExtendDataFloat("avg_i_p"), 1));
        kwuVar.e((float) UnitUtil.a(motionPathSimplify.getExtendDataFloat("avg_v_i_r"), 1));
        return kwuVar;
    }

    public static void b(nre nreVar) {
        if (nreVar == null) {
            LogUtil.h("Track_SegmentUtils", "addGolfSegmentTableTitle with error parameter tableDataSet is null, pls check");
            return;
        }
        Context context = BaseApplication.getContext();
        if (context == null) {
            nreVar.putRowColumnHeaderData(0, 0, null);
            LogUtil.h("Track_SegmentUtils", "addGolfSegmentTableTitle with context is null, pls check");
            return;
        }
        nreVar.putRowColumnHeaderData(0, 0, new hjc(null));
        nreVar.putColumnHeaderData(0, 1, new hjj(ad(context), null));
        nreVar.putColumnHeaderData(0, 2, new hjj(n(context), k(context)));
        nreVar.putColumnHeaderData(0, 3, new hjj(t(context), q(context)));
        nreVar.putColumnHeaderData(0, 4, new hjj(ae(context), ag(context)));
    }

    public static void b(nre nreVar, kws kwsVar) {
        if (nreVar == null || kwsVar == null) {
            LogUtil.h("Track_SegmentUtils", "addGolfSegmentToTableSet with error parameter tableDataSet:", nreVar, " trackGolfSegment:", kwsVar);
        } else {
            b(nreVar, nreVar.getRowsCount(), kwsVar);
        }
    }

    public static void b(nre nreVar, int i, kws kwsVar) {
        if (nreVar == null || kwsVar == null || i < 0) {
            LogUtil.h("Track_SegmentUtils", "putGolfSegmentToTableSet with error parameter tableDataSet:", nreVar, " trackGolfSegment:", kwsVar, " rowIndex:", Integer.valueOf(i));
            return;
        }
        nreVar.putRowHeaderData(i, 0, new hjc(i(kwsVar.d())));
        nreVar.putContentData(i, 1, new hjc(l(kwsVar.c())));
        nreVar.putContentData(i, 2, new hjc(h(kwsVar.b())));
        nreVar.putContentData(i, 3, new hjc(f(kwsVar.a())));
        nreVar.putContentData(i, 4, new hjc(k(kwsVar.e())));
    }

    private static String g(int i) {
        return i == -1 ? "" : UnitUtil.e(i, 1, 0);
    }

    private static String a(Context context, int i, int i2) {
        if (i == 1) {
            return context.getResources().getString(R.string._2130841810_res_0x7f0210d2);
        }
        if (i == 2) {
            return e(context, i2);
        }
        if (i == 3) {
            return context.getResources().getString(R.string._2130839507_res_0x7f0207d3);
        }
        if (i != 4) {
            return i != 5 ? "" : context.getResources().getString(R.string.IDS_device_setting_other);
        }
        return context.getResources().getString(R.string._2130837880_res_0x7f020178);
    }

    private static String e(Context context, int i) {
        if (i == 258) {
            return context.getResources().getString(R.string.IDS_start_track_sport_type_run);
        }
        if (i != 259) {
            return i != 279 ? "" : context.getResources().getString(R.string.IDS_hwh_motiontrack_other);
        }
        return context.getResources().getString(R.string._2130842145_res_0x7f021221);
    }

    private static String aa(Context context) {
        return context.getResources().getString(R.string._2130839835_res_0x7f02091b);
    }

    private static String i(long j) {
        return j == -1 ? "" : hji.b(TimeUnit.SECONDS.toMillis(j));
    }

    private static String aj(Context context) {
        return context.getResources().getString(R.string._2130841779_res_0x7f0210b3);
    }

    private static String ah(Context context) {
        return context.getResources().getString(R.string._2130839907_res_0x7f020963);
    }

    private static String c(long j) {
        return j == -1 ? "" : hji.e(j / 100.0d);
    }

    private static String a(long j) {
        return j == -1 ? "" : hji.e(Math.round(j / 100.0d));
    }

    private static String s(Context context) {
        return context.getResources().getString(R.string._2130841530_res_0x7f020fba);
    }

    private static String p(Context context) {
        String string;
        if (UnitUtil.h()) {
            string = context.getResources().getString(R.string._2130844081_res_0x7f0219b1);
        } else {
            string = context.getResources().getString(R.string._2130844082_res_0x7f0219b2);
        }
        return context.getResources().getString(R.string._2130839866_res_0x7f02093a, string);
    }

    private static String ac(Context context) {
        String string;
        if (UnitUtil.h()) {
            string = context.getResources().getString(R.string._2130844079_res_0x7f0219af);
        } else {
            string = context.getResources().getString(R.string._2130844078_res_0x7f0219ae);
        }
        return context.getResources().getString(R.string._2130839866_res_0x7f02093a, string);
    }

    private static String h(long j) {
        double d = j / 100.0d;
        if (UnitUtil.h()) {
            return UnitUtil.e(d * 0.6213712d, 1, 2);
        }
        return UnitUtil.e(d, 1, 2);
    }

    private static String d(long j) {
        return j == -1 ? "" : hji.c(j);
    }

    private static String x(Context context) {
        return context.getResources().getString(R.string._2130839751_res_0x7f0208c7);
    }

    private static String v(Context context) {
        String string;
        if (UnitUtil.h()) {
            string = context.getResources().getString(R.string._2130839914_res_0x7f02096a);
        } else {
            string = context.getResources().getString(R.string._2130839913_res_0x7f020969);
        }
        return context.getResources().getString(R.string._2130839866_res_0x7f02093a, string);
    }

    private static String j(int i) {
        return i == -1 ? "" : UnitUtil.e(i, 1, 0);
    }

    private static String b(float f) {
        return f == -1.0f ? "" : UnitUtil.e(f, 1, 1);
    }

    private static String r(Context context) {
        return context.getResources().getString(R.string._2130839500_res_0x7f0207cc);
    }

    private static String u(Context context) {
        return context.getResources().getString(R.string._2130839866_res_0x7f02093a, context.getResources().getString(R.string.IDS_main_watch_heart_rate_unit_string));
    }

    private static String n(int i) {
        return i == -1 ? "" : hji.j(i);
    }

    private static String z(Context context) {
        return context.getResources().getString(R.string._2130839852_res_0x7f02092c);
    }

    private static String ab(Context context) {
        if (UnitUtil.h()) {
            return context.getResources().getString(R.string._2130839866_res_0x7f02093a, context.getResources().getString(R.string._2130841897_res_0x7f021129));
        }
        return context.getResources().getString(R.string._2130839866_res_0x7f02093a, context.getResources().getString(R.string._2130841416_res_0x7f020f48));
    }

    private static String f(long j) {
        return j < 0 ? "" : hji.d(j / 100.0f);
    }

    private static String ai(Context context) {
        return context.getResources().getString(R.string._2130842886_res_0x7f021506);
    }

    private static String af(Context context) {
        if (UnitUtil.h()) {
            return context.getResources().getString(R.string._2130839866_res_0x7f02093a, context.getResources().getString(R.string._2130841417_res_0x7f020f49));
        }
        return context.getResources().getString(R.string._2130839866_res_0x7f02093a, context.getResources().getString(R.string._2130841568_res_0x7f020fe0));
    }

    private static String g(long j) {
        return j < 0 ? "" : hji.d(j / 100.0f);
    }

    private static String ak(Context context) {
        return context.getResources().getString(R.string._2130842545_res_0x7f0213b1);
    }

    private static String am(Context context) {
        return af(context);
    }

    private static String b(long j) {
        return j == -1 ? "" : UnitUtil.e(j, 1, 0);
    }

    private static String b(Context context) {
        return context.getResources().getString(R.string._2130842711_res_0x7f021457);
    }

    private static String h(Context context) {
        return context.getResources().getString(R.string._2130839866_res_0x7f02093a, context.getResources().getString(R.string._2130842713_res_0x7f021459));
    }

    private static String e(long j) {
        return j == -1 ? "" : UnitUtil.e(j, 1, 0);
    }

    private static String i(Context context) {
        return context.getResources().getString(R.string._2130842891_res_0x7f02150b);
    }

    private static String f(Context context) {
        return context.getResources().getString(R.string._2130839866_res_0x7f02093a, context.getResources().getString(R.string._2130842716_res_0x7f02145c));
    }

    private static String c(int i) {
        return i == -1 ? "" : BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903247_res_0x7f0300cf, i, Integer.valueOf(i));
    }

    private static String c(Context context) {
        return context.getResources().getString(R.string._2130842722_res_0x7f021462);
    }

    private static String e(Context context) {
        return context.getResources().getString(R.string._2130839866_res_0x7f02093a, context.getResources().getString(R.string._2130842728_res_0x7f021468));
    }

    private static String b(int i) {
        return c(i);
    }

    private static String l(Context context) {
        return context.getResources().getString(R.string._2130842723_res_0x7f021463);
    }

    private static String o(Context context) {
        return context.getResources().getString(R.string._2130839866_res_0x7f02093a, context.getResources().getString(R.string._2130842728_res_0x7f021468));
    }

    private static String j(Context context) {
        return context.getResources().getString(R.string._2130845177_res_0x7f021df9);
    }

    private static String m(Context context) {
        return context.getResources().getString(R.string._2130839866_res_0x7f02093a, context.getResources().getString(R.string._2130845180_res_0x7f021dfc));
    }

    private static String g(Context context) {
        return context.getResources().getString(R.string._2130839866_res_0x7f02093a, context.getResources().getQuantityString(R.plurals._2130903324_res_0x7f03011c, 1, ""));
    }

    private static String e(int i) {
        return i == -1 ? "" : hji.b(i);
    }

    private static String a(Context context) {
        return context.getResources().getString(R.string._2130839765_res_0x7f0208d5);
    }

    private static String d(Context context) {
        return context.getResources().getString(R.string._2130839866_res_0x7f02093a, context.getResources().getString(R.string._2130839766_res_0x7f0208d6));
    }

    private static String y(Context context) {
        Resources resources = context.getResources();
        Object[] objArr = new Object[1];
        objArr[0] = context.getResources().getString(UnitUtil.h() ? R.string._2130843856_res_0x7f0218d0 : R.string._2130845952_res_0x7f022100);
        return resources.getString(R.string._2130839866_res_0x7f02093a, objArr);
    }

    private static String w(Context context) {
        return context.getResources().getString(R.string._2130839866_res_0x7f02093a, context.getResources().getString(R.string._2130843492_res_0x7f021764));
    }

    private static String a(float f) {
        if (f == -1.0f) {
            return "";
        }
        return BaseApplication.getContext().getResources().getString(R.string._2130845183_res_0x7f021dff, UnitUtil.e(f, 2, 1), UnitUtil.e(100.0f - f, 2, 1));
    }

    private static String c(float f) {
        return f == -1.0f ? "" : UnitUtil.e(f, 2, 1);
    }

    private static long b(long j, long j2) {
        if (j == -1 || j2 == -1 || j2 == 0) {
            return -1L;
        }
        return Math.round(j / j2);
    }

    private static float a(float f, long j) {
        if (f == -1.0f || j == -1 || j == 0) {
            return -1.0f;
        }
        return Math.round(f / j);
    }

    private static String i(int i) {
        return g(i);
    }

    private static String ad(Context context) {
        return context.getResources().getString(R.string._2130843677_res_0x7f02181d);
    }

    private static String l(int i) {
        return i == -1 ? "" : UnitUtil.e(i / 100.0d, 1, 1);
    }

    private static String n(Context context) {
        return context.getResources().getString(R.string._2130843679_res_0x7f02181f);
    }

    private static String k(Context context) {
        return context.getResources().getString(R.string._2130839896_res_0x7f020958);
    }

    private static String h(int i) {
        return i == -1 ? "" : UnitUtil.e(i / 1000.0d, 1, 2);
    }

    private static String t(Context context) {
        return context.getResources().getString(R.string._2130843680_res_0x7f021820);
    }

    private static String q(Context context) {
        return context.getResources().getString(R.string._2130839896_res_0x7f020958);
    }

    private static String f(int i) {
        return h(i);
    }

    private static String ae(Context context) {
        return context.getResources().getString(R.string._2130843678_res_0x7f02181e);
    }

    private static String ag(Context context) {
        String string;
        if (UnitUtil.h()) {
            string = context.getResources().getString(R.string._2130844079_res_0x7f0219af);
        } else {
            string = context.getResources().getString(R.string._2130844382_res_0x7f021ade);
        }
        return context.getResources().getString(R.string._2130839866_res_0x7f02093a, string);
    }

    private static String k(int i) {
        if (i == -1) {
            return "";
        }
        if (UnitUtil.h()) {
            return UnitUtil.e(UnitUtil.e((i / 100.0d) * 3.5999999046325684d, 3), 1, 1);
        }
        return UnitUtil.e(i / 100.0d, 1, 1);
    }

    public static boolean d(List<kwu> list, CommonSegment commonSegment) {
        boolean z = commonSegment != null && ((kwu) commonSegment).t() >= 3600;
        long j = 0;
        for (kwu kwuVar : list) {
            if (z) {
                break;
            }
            if (kwuVar.t() >= 3600) {
                return true;
            }
            j += kwuVar.t();
            if (j >= 3600) {
                return true;
            }
        }
        return z;
    }
}
