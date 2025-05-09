package defpackage;

import com.huawei.hihealth.HiHealthData;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.PluginAchieveAdapter;
import com.huawei.pluginachievement.impl.AchieveCallback;
import com.huawei.pluginachievement.report.constant.EnumAnnualType;
import com.huawei.pluginachievement.report.iterface.BaseCalculator;
import health.compact.a.UnitUtil;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public class mfw extends BaseCalculator {
    private PluginAchieveAdapter b = getPluginAchieveAdapter();

    private double b(int i, int i2) {
        if (i2 != 0) {
            return (i * 1.0d) / i2;
        }
        return 0.0d;
    }

    @Override // com.huawei.pluginachievement.report.iterface.BaseCalculator
    public void compute(int i) {
        c(i);
    }

    public void c(int i) {
        if (this.b == null) {
            this.b = mcv.d(BaseApplication.getContext()).getAdapter();
        }
        CountDownLatch countDownLatch = new CountDownLatch(2);
        ArrayList arrayList = new ArrayList(16);
        ArrayList arrayList2 = new ArrayList(16);
        e(i, countDownLatch, arrayList);
        e(i - 1, countDownLatch, arrayList2);
        try {
            countDownLatch.await(8000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
            LogUtil.b("PLGACHIEVE_BloodSugarCalculator", "getBloodSugarRecordData.await(): catch a InterruptedException");
        }
        int e = e(arrayList);
        int a2 = a(arrayList);
        double b = b(e, a2);
        double b2 = b - b(e(arrayList2), a(arrayList2));
        LogUtil.a("PLGACHIEVE_BloodSugarCalculator", "getBloodPressureRecordData diffNormalRatio ", Double.valueOf(b2));
        b(a2, e, b, b2, i);
    }

    private int e(List<HiHealthData> list) {
        int i = 0;
        if (koq.b(list)) {
            LogUtil.h("PLGACHIEVE_BloodSugarCalculator", "getNormalRecordData records isEmpty.");
            return 0;
        }
        for (HiHealthData hiHealthData : list) {
            int type = hiHealthData.getType();
            double doubleValue = new BigDecimal(hiHealthData.getDouble("point_value")).setScale(1, RoundingMode.HALF_UP).doubleValue();
            boolean a2 = mhj.a(type, (float) doubleValue);
            LogUtil.a("PLGACHIEVE_BloodSugarCalculator", "getNormalRecordData records measurePeriod = ", Integer.valueOf(type), " bloodValue ", Double.valueOf(doubleValue), " isNormal ", Boolean.valueOf(a2));
            if (a2) {
                i++;
            }
        }
        return i;
    }

    private int a(List<HiHealthData> list) {
        if (koq.b(list)) {
            LogUtil.h("PLGACHIEVE_BloodSugarCalculator", "getTotalRecords records isEmpty.");
            return 0;
        }
        return list.size();
    }

    private void e(int i, final CountDownLatch countDownLatch, final List<HiHealthData> list) {
        this.b.getAnnualBloodSugarRecordData(i, new AchieveCallback() { // from class: mfw.5
            @Override // com.huawei.pluginachievement.impl.AchieveCallback
            public void onResponse(int i2, Object obj) {
                if (obj instanceof List) {
                    list.addAll((List) obj);
                } else {
                    LogUtil.h("PLGACHIEVE_BloodSugarCalculator", "getNewSportSumData data is not correct");
                }
                countDownLatch.countDown();
            }
        });
    }

    private void b(int i, int i2, double d, double d2, int i3) {
        insertData(i3, EnumAnnualType.REPORT_HEALTH.value(), 10008, String.valueOf(i));
        insertData(i3, EnumAnnualType.REPORT_HEALTH.value(), 10009, String.valueOf(i2));
        insertData(i3, EnumAnnualType.REPORT_HEALTH.value(), 10010, UnitUtil.e(d, 1, 2));
        insertData(i3, EnumAnnualType.REPORT_HEALTH.value(), 10011, UnitUtil.e(d2, 1, 2));
    }
}
