package defpackage;

import android.content.Context;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.fitnessdatatype.SleepTotalData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.MainLoginCallBack;
import com.huawei.pluginachievement.PluginAchieveAdapter;
import com.huawei.pluginachievement.impl.AchieveCallback;
import com.huawei.pluginachievement.report.constant.EnumAnnualType;
import com.huawei.pluginachievement.report.iterface.BaseCalculator;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public class mgl extends BaseCalculator {
    private static final int[] c = {0, 63, 78};
    private PluginAchieveAdapter d = getPluginAchieveAdapter();

    /* renamed from: a, reason: collision with root package name */
    private Context f14975a = BaseApplication.getContext();

    @Override // com.huawei.pluginachievement.report.iterface.BaseCalculator
    public void compute(int i) {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        ArrayList arrayList = new ArrayList(16);
        ArrayList arrayList2 = new ArrayList(16);
        e(i, countDownLatch, arrayList);
        int i2 = i - 1;
        e(i2, countDownLatch, arrayList2);
        try {
            countDownLatch.await(8000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
            LogUtil.b("PLGACHIEVE_SleepCalculator", "compute.await(): catch InterruptedException");
        }
        int d = d(arrayList, i);
        insertData(i, EnumAnnualType.REPORT_SLEEP.value(), MainLoginCallBack.MSG_SHOW_HMS_DIALOG, String.valueOf(mht.d(d, d(arrayList2, i2), 5)));
        e(d, arrayList);
    }

    private void e(int i, List<SleepTotalData> list) {
        addStarData("sleepStar", koq.b(list) ? 0 : mht.d(i, c));
    }

    public void a(int i) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ArrayList arrayList = new ArrayList(16);
        e(i, countDownLatch, arrayList);
        try {
            countDownLatch.await(8000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
            LogUtil.b("PLGACHIEVE_SleepCalculator", "compute2019.await(): catch InterruptedException");
        }
        d(arrayList, i);
    }

    private void e(int i, final CountDownLatch countDownLatch, final List<SleepTotalData> list) {
        this.d.getAnnualSleepReport(this.f14975a, mht.b(i, true), mht.b(i, false), new AchieveCallback() { // from class: mgl.5
            @Override // com.huawei.pluginachievement.impl.AchieveCallback
            public void onResponse(int i2, Object obj) {
                if (obj instanceof List) {
                    list.addAll((List) obj);
                } else {
                    LogUtil.h("PLGACHIEVE_SleepCalculator", "getAnnualSleepRecords data is not correct");
                }
                countDownLatch.countDown();
            }
        });
    }

    private int d(List<SleepTotalData> list, int i) {
        int i2 = 0;
        if (koq.b(list)) {
            return 0;
        }
        HashMap hashMap = new HashMap(16);
        HashMap hashMap2 = new HashMap(16);
        HashMap hashMap3 = new HashMap(16);
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<SleepTotalData> it = list.iterator();
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        long j = 0;
        int i10 = 0;
        int i11 = 0;
        while (it.hasNext()) {
            Iterator<SleepTotalData> it2 = it;
            SleepTotalData next = it.next();
            int score = i4 + next.getScore();
            int deepSleepTime = i3 + next.getDeepSleepTime();
            int slumberSleepTime = next.getSlumberSleepTime();
            int shallowSleepTime = next.getShallowSleepTime();
            i8 += next.getFallTime();
            i6 += next.getWakeUpTime();
            i7 += next.getWakeupTimes();
            i5 += mhq.b(next.getSnoreFreq(), next.getSlumberSleepTime());
            arrayList.add(Integer.valueOf(next.getFallTime()));
            mhq.e(next, hashMap, hashMap2, hashMap3);
            if (next.getFallTime() <= 1440) {
                i9++;
            }
            if (i10 == 0 || i10 > next.getTotalSleepTime()) {
                i10 = next.getTotalSleepTime();
                j = next.getSleepMonitorStartTime();
            }
            i2 += shallowSleepTime;
            i11 += slumberSleepTime;
            it = it2;
            i3 = deepSleepTime;
            i4 = score;
        }
        int size = list.size();
        int i12 = i10;
        double d = size * 1.0d;
        double d2 = i4 / d;
        int i13 = i2;
        a(i, d2, e(i3, i11, i2, size), mht.d(i5 / d), size);
        d(i, b(b(i4, i3, i11, i13, i6), i7, i8, i5), arrayList, size);
        b(i, i8, i6, size);
        a(i, hashMap3);
        d(i, i9, i12, j);
        return mht.d(d2);
    }

    private int e(int i, int i2, int i3, int i4) {
        double d = i4 * 1.0d;
        return mht.d((i / d) + (i2 / d) + (i3 / d));
    }

    private SleepTotalData b(SleepTotalData sleepTotalData, int i, int i2, int i3) {
        sleepTotalData.setWakeupTimes(i);
        sleepTotalData.setFallTime(i2);
        sleepTotalData.setSnoreFreq(i3);
        return sleepTotalData;
    }

    private SleepTotalData b(int i, int i2, int i3, int i4, int i5) {
        SleepTotalData sleepTotalData = new SleepTotalData();
        sleepTotalData.setScore(i);
        sleepTotalData.setDeepSleepTime(i2);
        sleepTotalData.setSlumberSleepTime(i3);
        sleepTotalData.setShallowSleepTime(i4);
        sleepTotalData.setWakeUpTime(i5);
        return sleepTotalData;
    }

    private void d(int i, int i2, int i3, long j) {
        LogUtil.a("PLGACHIEVE_SleepCalculator", "saveSleepData year == ", Integer.valueOf(i), " fallTimeLess24Sum == ", Integer.valueOf(i2), " minSleepDuration == ", Integer.valueOf(i3), " minSleepDurationDay == ", Long.valueOf(j));
        insertData(i, EnumAnnualType.REPORT_SLEEP.value(), 10019, String.valueOf(i2));
        insertData(i, EnumAnnualType.REPORT_SLEEP.value(), 10021, String.valueOf(i3));
        insertData(i, EnumAnnualType.REPORT_SLEEP.value(), 10020, String.valueOf(j));
    }

    private void a(int i, Map<Integer, Double> map) {
        if (map == null) {
            return;
        }
        LogUtil.a("PLGACHIEVE_SleepCalculator", "saveEverySeasonScore seasonSleepAvgScoreMap == ", map.toString());
        int d = mhq.d(map);
        if (map.containsKey(Integer.valueOf(d))) {
            int d2 = mht.d(map.get(Integer.valueOf(d)).doubleValue());
            LogUtil.a("PLGACHIEVE_SleepCalculator", "saveSleepData year == ", Integer.valueOf(i), " maxSeasonScore == ", Integer.valueOf(d2), " maxSeasonIndex == ", Integer.valueOf(d));
            insertData(i, EnumAnnualType.REPORT_SLEEP.value(), 10018, String.valueOf(d2));
            insertData(i, EnumAnnualType.REPORT_SLEEP.value(), 10017, String.valueOf(d));
        }
    }

    private void a(int i, double d, int i2, int i3, int i4) {
        insertData(i, EnumAnnualType.REPORT_SLEEP.value(), 8001, UnitUtil.e(d, 1, 1));
        insertData(i, EnumAnnualType.REPORT_SLEEP.value(), 8002, String.valueOf(i2));
        insertData(i, EnumAnnualType.REPORT_SLEEP.value(), MainLoginCallBack.MSG_HMS_VERSION_ERROR, String.valueOf(i3));
        insertData(i, EnumAnnualType.REPORT_SLEEP.value(), MainLoginCallBack.MSG_START_APK_SERVICE_ERROR, String.valueOf(i4));
    }

    private void d(int i, SleepTotalData sleepTotalData, List<Integer> list, int i2) {
        insertData(i, EnumAnnualType.REPORT_SLEEP.value(), MainLoginCallBack.MSG_HWID_STOPED, String.valueOf(mhq.b(sleepTotalData, list, i2)));
    }

    private void b(int i, int i2, int i3, int i4) {
        double d = i4 * 1.0d;
        int d2 = mht.d(i2 / d) / 60;
        if (d2 >= 24) {
            d2 -= 24;
        }
        double d3 = i3 / d;
        int d4 = mht.d(d3) / 60;
        int d5 = mht.d(d3);
        insertData(i, EnumAnnualType.REPORT_SLEEP.value(), MainLoginCallBack.MSG_HWID_ACCOUNT_NOT_LOGIN, a(d2, (i2 / i4) % 60));
        insertData(i, EnumAnnualType.REPORT_SLEEP.value(), MainLoginCallBack.MSG_NO_NETWORK, a(d4, d5 % 60));
    }

    private String a(int i, int i2) {
        String valueOf;
        String valueOf2;
        if (i == 0 && i2 == 0) {
            return "00:00";
        }
        if (i < 10) {
            valueOf = "0" + i;
        } else {
            valueOf = String.valueOf(i);
        }
        if (i2 < 10) {
            valueOf2 = "0" + i2;
        } else {
            valueOf2 = String.valueOf(i2);
        }
        return valueOf + ":" + valueOf2;
    }
}
