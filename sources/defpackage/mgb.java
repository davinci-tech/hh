package defpackage;

import android.content.Context;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.health.device.wifi.control.logic.AccessNetWorkTask;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.PluginAchieveAdapter;
import com.huawei.pluginachievement.impl.AchieveCallback;
import com.huawei.pluginachievement.report.bean.AnnualReportCycle;
import com.huawei.pluginachievement.report.constant.EnumAnnualType;
import com.huawei.pluginachievement.report.iterface.BaseCalculator;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public class mgb extends BaseCalculator {
    private static final int[] c = {0, AccessNetWorkTask.TIME_OUT_ALL, 300000};

    /* renamed from: a, reason: collision with root package name */
    private PluginAchieveAdapter f14965a = getPluginAchieveAdapter();
    private Context d = BaseApplication.getContext();

    @Override // com.huawei.pluginachievement.report.iterface.BaseCalculator
    public void compute(int i) {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        ArrayList arrayList = new ArrayList(16);
        ArrayList arrayList2 = new ArrayList(16);
        a(i, countDownLatch, arrayList);
        a(i - 1, countDownLatch, arrayList2);
        try {
            countDownLatch.await(8000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
            LogUtil.b("PLGACHIEVE_CycleCalculator", "getTrackListByCycleType.await(): catch InterruptedException");
        }
        AnnualReportCycle e = mhm.e(arrayList);
        b(e, i);
        a(e, mhm.e(arrayList2), i);
        d(e, arrayList);
    }

    private void d(AnnualReportCycle annualReportCycle, List<HiHealthData> list) {
        addStarData("cycleStar", (koq.b(list) || annualReportCycle == null) ? 0 : mht.d(annualReportCycle.acquireTotalDistance(), c));
    }

    public void e(int i) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ArrayList arrayList = new ArrayList(16);
        a(i, countDownLatch, arrayList);
        try {
            countDownLatch.await(8000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
            LogUtil.b("PLGACHIEVE_CycleCalculator", "compute2019.await(): catch InterruptedException");
        }
        b(mhm.e(arrayList), i);
    }

    private void a(AnnualReportCycle annualReportCycle, AnnualReportCycle annualReportCycle2, int i) {
        int acquireTotalDistance = annualReportCycle != null ? annualReportCycle.acquireTotalDistance() : 0;
        insertData(i, EnumAnnualType.REPORT_CYCLE.value(), 1005, String.valueOf(mht.d(acquireTotalDistance, annualReportCycle2 != null ? annualReportCycle2.acquireTotalDistance() : 0, 2000)));
        insertData(i, EnumAnnualType.REPORT_CYCLE.value(), 1001, String.valueOf(acquireTotalDistance));
    }

    private void a(int i, final CountDownLatch countDownLatch, final List<HiHealthData> list) {
        this.f14965a.fetchSequenceDataBySportType(this.d, mht.b(i, true), mht.b(i, false), 259, new AchieveCallback() { // from class: mgb.2
            @Override // com.huawei.pluginachievement.impl.AchieveCallback
            public void onResponse(int i2, Object obj) {
                if (obj instanceof List) {
                    list.addAll((List) obj);
                } else {
                    LogUtil.h("PLGACHIEVE_CycleCalculator", "getTrackListByCycleType data is not correct");
                }
                countDownLatch.countDown();
            }
        });
    }

    private void b(AnnualReportCycle annualReportCycle, int i) {
        if (annualReportCycle == null) {
            LogUtil.h("PLGACHIEVE_CycleCalculator", "saveCycleData annualReportCycle is null");
            return;
        }
        insertData(i, EnumAnnualType.REPORT_CYCLE.value(), 1003, String.valueOf(annualReportCycle.acquireMaxDistance()));
        insertData(i, EnumAnnualType.REPORT_CYCLE.value(), 1004, String.valueOf(annualReportCycle.acquireMaxDistanceDay()));
        insertData(i, EnumAnnualType.REPORT_CYCLE.value(), 1002, String.valueOf(annualReportCycle.acquireNumberOfTimes()));
        insertData(i, EnumAnnualType.REPORT_CYCLE.value(), PrebakedEffectId.RT_SPEED_UP, String.valueOf(annualReportCycle.getMaxCycleContinuousDays()));
        if (i <= 2019) {
            insertData(i, EnumAnnualType.REPORT_CYCLE.value(), 1001, String.valueOf(annualReportCycle.acquireTotalDistance()));
        }
    }
}
