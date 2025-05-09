package defpackage;

import android.content.Context;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealthservice.store.merge.HiDataPointMerge;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiDateUtil;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class isq {
    public static igo e(Context context, int i, int i2, int i3) {
        if (context == null) {
            return null;
        }
        return ijd.c(context).d(i2, i3, iis.d().h(i));
    }

    public static int e(igo igoVar, double d, int i) {
        if (igoVar == null || 1 != i || 1 != igoVar.g() || d <= igoVar.l()) {
            return i;
        }
        LogUtil.c("Debug_HiSportStat", " getSyncStatus is SYNC_NONE! type = ", Integer.valueOf(igoVar.f()));
        return 0;
    }

    public static void c(HiDataInsertOption hiDataInsertOption, List<HiHealthData> list, int i) {
        if (list == null || list.size() <= 0) {
            return;
        }
        double a2 = iuz.a(BaseApplication.getContext(), igm.e().d(), HiDateUtil.c(list.get(0).getStartTime()), i);
        double value = list.get(0).getValue();
        LogUtil.c("Debug_HiSportStat", "insertHiHealthData() fitness exercise added");
        list.get(0).setValue(a2 + value);
        list.get(0).setSyncStatus(0);
        list.get(0).setUserId(igm.e().d());
        hiDataInsertOption.setDatas(list);
        hiDataInsertOption.setWriteStatType(0);
        c(hiDataInsertOption, value);
    }

    private static void c(HiDataInsertOption hiDataInsertOption, double d) {
        int d2 = igm.e().d();
        int h = iis.d().h(d2);
        igo d3 = ijd.c(BaseApplication.getContext()).d(HiDateUtil.c(hiDataInsertOption.getDatas().get(0).getStartTime()), 40003, h);
        LogUtil.c("Debug_HiSportStat", "insertHiHealthData() ExerciseCalorieAddToSummary fitness exercise statTable = ", d3, " statClient is ", Integer.valueOf(h));
        if (d3 != null) {
            double l = d3.l();
            d3.b(h);
            d3.c(20001);
            d3.d(40003);
            d3.j(d2);
            d3.a(d + l);
            LogUtil.c("Debug_HiSportStat", "insertHiHealthData() ExerciseCalorieAddToSummary fitness exercise update statTable = ", d3);
            LogUtil.c("Debug_HiSportStat", "insertHiHealthData() ExerciseCalorieAddToSummary fitness exercise fitnessCalories isSuccess is ", Boolean.valueOf(ijd.c(BaseApplication.getContext()).a(d3)));
        }
    }

    public static void a(long j) {
        List<Integer> g = iis.d().g(igm.e().d());
        LogUtil.c("Debug_HiSportStat", "calculateStepSumByClient clients is ", g);
        if (g == null || g.isEmpty()) {
            LogUtil.h("Debug_HiSportStat", "calculateStepSumByClient clients is empty");
            return;
        }
        Iterator<Integer> it = g.iterator();
        while (it.hasNext()) {
            e(j, it.next().intValue());
        }
    }

    public static void a(HiHealthData hiHealthData) {
        e(hiHealthData.getStartTime(), hiHealthData.getClientId());
    }

    private static void e(long j, int i) {
        List<HiHealthData> d = iiy.e(BaseApplication.getContext()).d(i, HiDateUtil.t(j), HiDateUtil.f(j), 2);
        if (HiCommonUtil.d(d)) {
            LogUtil.h("Debug_HiSportStat", "clientStepsSync hihealthDatas is null or empty");
            return;
        }
        Iterator<HiHealthData> it = d.iterator();
        double d2 = 0.0d;
        while (it.hasNext()) {
            d2 += it.next().getValue();
        }
        HiHealthData hiHealthData = d.get(0);
        LogUtil.c("Debug_HiSportStat", "clientStepsSync client is ", Integer.valueOf(i), " value is ", Double.valueOf(d2));
        hiHealthData.setValue(d2);
        hiHealthData.setStartTime(HiDateUtil.t(j));
        hiHealthData.setEndTime(HiDateUtil.f(j));
        hiHealthData.setSyncStatus(0);
        hiHealthData.setType(901);
        ikv a2 = ikr.b(BaseApplication.getContext()).a(igm.e().c(), igm.e().d(), iis.d().a(i));
        if (a2 == null) {
            LogUtil.h("Debug_HiSportStat", "clientStepsSync hihealthContext is null");
            return;
        }
        int b = a2.b();
        hiHealthData.setClientId(b);
        new HiDataPointMerge(BaseApplication.getContext()).c(hiHealthData, b, 0);
        a2.b(1);
        iis.d().b(a2);
    }
}
