package defpackage;

import com.huawei.hihealth.HiHealthData;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.PluginAchieveAdapter;
import com.huawei.pluginachievement.impl.AchieveCallback;
import com.huawei.pluginachievement.report.constant.EnumAnnualType;
import com.huawei.pluginachievement.report.iterface.BaseCalculator;
import health.compact.a.UnitUtil;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public class mfy extends BaseCalculator {

    /* renamed from: a, reason: collision with root package name */
    private PluginAchieveAdapter f14960a = getPluginAchieveAdapter();

    private double d(int i, int i2) {
        if (i2 != 0) {
            return (i * 1.0d) / i2;
        }
        return 0.0d;
    }

    @Override // com.huawei.pluginachievement.report.iterface.BaseCalculator
    public void compute(int i) {
        d(i);
    }

    public void d(int i) {
        if (this.f14960a == null) {
            this.f14960a = mcv.d(BaseApplication.getContext()).getAdapter();
        }
        CountDownLatch countDownLatch = new CountDownLatch(2);
        ArrayList arrayList = new ArrayList(16);
        ArrayList arrayList2 = new ArrayList(16);
        c(i, countDownLatch, arrayList);
        c(i - 1, countDownLatch, arrayList2);
        try {
            countDownLatch.await(8000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
            LogUtil.b("PLGACHIEVE_BloodPressureCalculator", "getBloodPressureRecordData.await(): catch a InterruptedException");
        }
        int d = d(arrayList);
        int c = c(arrayList);
        double d2 = d(d, c);
        double d3 = d2 - d(d(arrayList2), c(arrayList2));
        LogUtil.a("PLGACHIEVE_BloodPressureCalculator", "getBloodPressureRecordData diffNormalRatio ", Double.valueOf(d3));
        e(c, d, d2, d3, i);
    }

    private int c(List<HiHealthData> list) {
        if (koq.b(list)) {
            LogUtil.h("PLGACHIEVE_BloodPressureCalculator", "getTotalRecords records isEmpty.");
            return 0;
        }
        return list.size();
    }

    private int d(List<HiHealthData> list) {
        if (koq.b(list)) {
            LogUtil.h("PLGACHIEVE_BloodPressureCalculator", "getNormalRecordData records isEmpty.");
            return 0;
        }
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.getDefault());
        int i = 0;
        for (HiHealthData hiHealthData : list) {
            String e = UnitUtil.e(hiHealthData.getDouble("BLOOD_PRESSURE_SYSTOLIC"), 1, 0);
            String e2 = UnitUtil.e(hiHealthData.getDouble("BLOOD_PRESSURE_DIASTOLIC"), 1, 0);
            try {
                int intValue = numberFormat.parse(e).intValue();
                int intValue2 = numberFormat.parse(e2).intValue();
                int e3 = mhk.e((short) intValue, (short) intValue2);
                LogUtil.a("PLGACHIEVE_BloodPressureCalculator", "getNormalRecordData records highPressureRange = ", Integer.valueOf(intValue), " lowPressureRange ", Integer.valueOf(intValue2), " level ", Integer.valueOf(e3));
                if (e3 == 1) {
                    i++;
                }
            } catch (ParseException unused) {
                LogUtil.b("PLGACHIEVE_BloodPressureCalculator", "getNormalRecordData, ParseException");
            }
        }
        return i;
    }

    private List<HiHealthData> c(int i, final CountDownLatch countDownLatch, final List<HiHealthData> list) {
        this.f14960a.getAnnualBloodPressureRecordData(i, new AchieveCallback() { // from class: mfy.2
            @Override // com.huawei.pluginachievement.impl.AchieveCallback
            public void onResponse(int i2, Object obj) {
                if (obj instanceof List) {
                    list.addAll((List) obj);
                } else {
                    LogUtil.h("PLGACHIEVE_BloodPressureCalculator", "getTotalRecordData data is not correct");
                }
                countDownLatch.countDown();
            }
        });
        return list;
    }

    private void e(int i, int i2, double d, double d2, int i3) {
        insertData(i3, EnumAnnualType.REPORT_HEALTH.value(), 10004, String.valueOf(i));
        insertData(i3, EnumAnnualType.REPORT_HEALTH.value(), 10005, String.valueOf(i2));
        insertData(i3, EnumAnnualType.REPORT_HEALTH.value(), 10006, UnitUtil.e(d, 1, 2));
        insertData(i3, EnumAnnualType.REPORT_HEALTH.value(), 10007, UnitUtil.e(d2, 1, 2));
    }
}
