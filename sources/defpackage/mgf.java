package defpackage;

import android.content.Context;
import com.huawei.android.airsharing.api.IEventListener;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hms.support.api.entity.auth.AuthCode;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.pluginachievement.PluginAchieveAdapter;
import com.huawei.pluginachievement.impl.AchieveCallback;
import com.huawei.pluginachievement.manager.model.TotalRecord;
import com.huawei.pluginachievement.report.constant.EnumAnnualType;
import com.huawei.pluginachievement.report.iterface.BaseCalculator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public class mgf extends BaseCalculator {
    private static final int[] c = {0, 730000, 2190000, 3650000, 5840000};

    /* renamed from: a, reason: collision with root package name */
    private PluginAchieveAdapter f14968a = getPluginAchieveAdapter();
    private Context d = BaseApplication.getContext();

    @Override // com.huawei.pluginachievement.report.iterface.BaseCalculator
    public void compute(int i) {
        d(i);
        c(i);
        CountDownLatch countDownLatch = new CountDownLatch(2);
        ArrayList arrayList = new ArrayList(16);
        ArrayList arrayList2 = new ArrayList(16);
        c(i, countDownLatch, arrayList);
        c(i - 1, countDownLatch, arrayList2);
        try {
            countDownLatch.await(8000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
            LogUtil.b("PLGACHIEVE_InitialDataCalculator", "compute.await(): catch a InterruptedException");
        }
        c(i, arrayList);
        a(i, arrayList, arrayList2);
    }

    public void a(int i) {
        d(i);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ArrayList arrayList = new ArrayList(16);
        c(i, countDownLatch, arrayList);
        try {
            countDownLatch.await(8000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
            LogUtil.b("PLGACHIEVE_InitialDataCalculator", "compute2019.await(): catch a InterruptedException");
        }
        c(i, arrayList);
    }

    private void a(int i, List<HiHealthData> list, List<HiHealthData> list2) {
        long a2 = mht.a(e(list), e(list2), 100000);
        int d = mht.d(a(list), a(list2), 5000);
        int d2 = mht.d(c(list), c(list2), 10000);
        insertData(i, EnumAnnualType.REPORT_INITAL.value(), 9010, String.valueOf(a2));
        insertData(i, EnumAnnualType.REPORT_INITAL.value(), 9009, String.valueOf(d));
        insertData(i, EnumAnnualType.REPORT_STEP.value(), IEventListener.EVENT_ID_DEVICE_SCAN_FINISH, String.valueOf(d2));
    }

    private int c(List<HiHealthData> list) {
        if (koq.b(list)) {
            LogUtil.h("PLGACHIEVE_InitialDataCalculator", "getTotalStep is empty, return");
            return 0;
        }
        return list.get(0).getInt("totalStep");
    }

    private int a(List<HiHealthData> list) {
        if (koq.b(list)) {
            LogUtil.h("PLGACHIEVE_InitialDataCalculator", "getTotalDistance is empty, return");
            return 0;
        }
        return list.get(0).getInt(BleConstants.TOTAL_DISTANCE);
    }

    private long e(List<HiHealthData> list) {
        if (koq.b(list)) {
            LogUtil.h("PLGACHIEVE_InitialDataCalculator", "getTotalCalorie is empty, return");
            return 0L;
        }
        return list.get(0).getLong("totalCalorie");
    }

    private void d(int i) {
        mcz d = meh.c(BaseApplication.getContext()).d(1, new HashMap(2));
        if (!(d instanceof TotalRecord)) {
            LogUtil.h("PLGACHIEVE_InitialDataCalculator", "computeInitialDataByYear totalRecord invalid");
            return;
        }
        TotalRecord totalRecord = (TotalRecord) d;
        int days = totalRecord.getDays();
        long startDate = totalRecord.getStartDate();
        insertData(i, EnumAnnualType.REPORT_INITAL.value(), 9002, String.valueOf(days));
        insertData(i, EnumAnnualType.REPORT_INITAL.value(), 9001, String.valueOf(startDate));
    }

    private void c(int i) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ArrayList arrayList = new ArrayList(16);
        mhr.a(BaseApplication.getContext(), i, countDownLatch, arrayList);
        try {
            countDownLatch.await(8000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
            LogUtil.b("PLGACHIEVE_InitialDataCalculator", "compute.await(): catch a InterruptedException");
        }
        e(i, mcv.d(BaseApplication.getContext()).getAdapter(), arrayList);
    }

    private void e(final int i, PluginAchieveAdapter pluginAchieveAdapter, final List<HiHealthData> list) {
        pluginAchieveAdapter.getSumYearData(BaseApplication.getContext(), mht.b(i, true), mht.b(i, false), 23, new AchieveCallback() { // from class: mgf.4
            @Override // com.huawei.pluginachievement.impl.AchieveCallback
            public void onResponse(int i2, Object obj) {
                int d = mhr.d(obj, list);
                LogUtil.a("PLGACHIEVE_InitialDataCalculator", "computeCurrentYearDays currentUseDays = ", Integer.valueOf(d));
                mgf.this.insertData(i, EnumAnnualType.REPORT_INITAL.value(), 10015, String.valueOf(d));
            }
        });
    }

    private void c(int i, final CountDownLatch countDownLatch, final List<HiHealthData> list) {
        this.f14968a.getSumYearData(this.d, mht.b(i, true), mht.b(i, false), 16, new AchieveCallback() { // from class: mgf.5
            @Override // com.huawei.pluginachievement.impl.AchieveCallback
            public void onResponse(int i2, Object obj) {
                if (obj instanceof List) {
                    list.addAll((List) obj);
                } else {
                    LogUtil.h("PLGACHIEVE_InitialDataCalculator", "getSumYearData data is not correct");
                }
                LogUtil.a("PLGACHIEVE_InitialDataCalculator", "getSumYearData success");
                countDownLatch.countDown();
            }
        });
    }

    private <T> void c(int i, T t) {
        if (t == null) {
            LogUtil.h("PLGACHIEVE_InitialDataCalculator", "saveAnnualSumData obj is null,return");
            return;
        }
        try {
            List list = (List) t;
            if (koq.b(list)) {
                LogUtil.h("PLGACHIEVE_InitialDataCalculator", "saveAnnualSumData dataInfoList is empty, return");
                return;
            }
            HiHealthData hiHealthData = (HiHealthData) list.get(0);
            int i2 = hiHealthData.getInt("totalStep");
            long j = hiHealthData.getLong("totalCalorie");
            int i3 = hiHealthData.getInt(BleConstants.TOTAL_DISTANCE);
            insertData(i, EnumAnnualType.REPORT_SUMARY.value(), AuthCode.StatusCode.PERMISSION_NOT_AUTHORIZED, String.valueOf(j));
            if (i <= 2019) {
                insertData(i, EnumAnnualType.REPORT_SUMARY.value(), AuthCode.StatusCode.CERT_FINGERPRINT_ERROR, String.valueOf(i3));
            } else {
                insertData(i, EnumAnnualType.REPORT_SUMARY.value(), 9003, String.valueOf(i3));
            }
            insertData(i, EnumAnnualType.REPORT_STEP.value(), 3001, String.valueOf(i2));
            insertData(i, EnumAnnualType.REPORT_STEP.value(), 3002, String.valueOf(mhr.e(i2)));
            insertData(i, EnumAnnualType.REPORT_INITAL.value(), 9003, String.valueOf(i3));
            insertData(i, EnumAnnualType.REPORT_INITAL.value(), AuthCode.StatusCode.PERMISSION_NOT_AUTHORIZED, String.valueOf(j));
            b(i2);
        } catch (ClassCastException unused) {
            LogUtil.b("PLGACHIEVE_InitialDataCalculator", "getWeightData data ClassCastException");
        }
    }

    private void b(int i) {
        addStarData("stepStar", mht.d(i, c));
    }
}
