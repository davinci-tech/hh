package defpackage;

import android.content.Context;
import android.util.Pair;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.google.android.gms.wearable.WearableStatusCodes;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.PluginAchieveAdapter;
import com.huawei.pluginachievement.impl.AchieveCallback;
import com.huawei.pluginachievement.report.bean.AnnualReportFitnessRaw;
import com.huawei.pluginachievement.report.constant.EnumAnnualType;
import com.huawei.pluginachievement.report.iterface.BaseCalculator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public class mga extends BaseCalculator {
    private long b;
    private long e;

    /* renamed from: a, reason: collision with root package name */
    private PluginAchieveAdapter f14961a = getPluginAchieveAdapter();
    private Context c = BaseApplication.getContext();

    @Override // com.huawei.pluginachievement.report.iterface.BaseCalculator
    public void compute(int i) {
        d(i);
        c(i);
        b(i);
    }

    public Map<String, Double> e(int i) {
        if (this.f14961a == null) {
            LogUtil.h("FitnessSportCalculator", "getAnnualSportData achieveAdapter is null");
            return Collections.emptyMap();
        }
        CountDownLatch countDownLatch = new CountDownLatch(2);
        ArrayList arrayList = new ArrayList(16);
        ArrayList arrayList2 = new ArrayList(16);
        a(i, countDownLatch, arrayList);
        e(i, countDownLatch, arrayList2);
        try {
            countDownLatch.await(8000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
            LogUtil.b("FitnessSportCalculator", "getAnnualSportData await(): catch InterruptedException");
        }
        HashMap hashMap = new HashMap();
        Pair<Map<String, Double>, Map<Integer, Double>> cgT_ = mhn.cgT_(arrayList, arrayList2, hashMap);
        if (i < 2020) {
            return (Map) cgT_.first;
        }
        d(i, (Map) cgT_.first, hashMap, (Map) cgT_.second);
        return (Map) cgT_.first;
    }

    private void a(int i, final CountDownLatch countDownLatch, final List<HiHealthData> list) {
        LogUtil.a("FitnessSportCalculator", "getFitSportSumData");
        this.f14961a.getFitSportSumDatas(i, new AchieveCallback() { // from class: mga.5
            @Override // com.huawei.pluginachievement.impl.AchieveCallback
            public void onResponse(int i2, Object obj) {
                if (obj instanceof List) {
                    list.addAll((List) obj);
                } else {
                    LogUtil.h("FitnessSportCalculator", "getNewSportSumData data is not correct");
                }
                countDownLatch.countDown();
            }
        });
    }

    private void e(int i, final CountDownLatch countDownLatch, final List<AnnualReportFitnessRaw> list) {
        LogUtil.a("FitnessSportCalculator", "getFitnessCourseData");
        this.f14961a.getAnnualFitnessReport(this.c, mht.b(i, true), mht.b(i, false), new AchieveCallback() { // from class: mga.3
            @Override // com.huawei.pluginachievement.impl.AchieveCallback
            public void onResponse(int i2, Object obj) {
                if (obj instanceof List) {
                    list.addAll((List) obj);
                } else {
                    LogUtil.h("FitnessSportCalculator", "getFitnessCourseData data is not correct");
                }
                countDownLatch.countDown();
            }
        });
    }

    public void b(int i) {
        if (this.f14961a == null) {
            LogUtil.h("FitnessSportCalculator", "getLongestFitDay mAchieveAdapter is null");
            return;
        }
        CountDownLatch countDownLatch = new CountDownLatch(2);
        ArrayList arrayList = new ArrayList(16);
        ArrayList arrayList2 = new ArrayList(16);
        b(i, countDownLatch, arrayList);
        e(i, countDownLatch, arrayList2);
        try {
            countDownLatch.await(8000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
            LogUtil.b("FitnessSportCalculator", "getLongestFitDay await(): catch InterruptedException");
        }
        HashSet hashSet = new HashSet(16);
        Map<String, Long> e = mhn.e(arrayList, arrayList2, hashSet);
        int c = mgx.c(new ArrayList(hashSet));
        LogUtil.a("FitnessSportCalculator", "calculateFitnessSum reachDays == ", Integer.valueOf(c));
        b(i, PrebakedEffectId.RT_COIN_DROP, String.valueOf(c));
        c(i, e);
    }

    private void b(int i, final CountDownLatch countDownLatch, final List<HiHealthData> list) {
        this.f14961a.getFitSportEverydayRecords(i, new AchieveCallback() { // from class: mga.1
            @Override // com.huawei.pluginachievement.impl.AchieveCallback
            public void onResponse(int i2, Object obj) {
                if (obj instanceof List) {
                    list.addAll((List) obj);
                }
                countDownLatch.countDown();
            }
        });
    }

    public void c(int i) {
        Map<String, Double> e = e(i);
        Map<String, Double> e2 = e(i - 1);
        b(i, 4006, String.valueOf(Double.valueOf(mht.e(e.containsKey("total_duration") ? e.get("total_duration").doubleValue() : 0.0d, e2.containsKey("total_duration") ? e2.get("total_duration").doubleValue() : 0.0d, 10)).intValue()));
    }

    public void d(final int i) {
        if (this.f14961a == null) {
            LogUtil.h("FitnessSportCalculator", "getTrackListBySwimType mAchieveAdapter is null");
            return;
        }
        this.f14961a.fetchSequenceDataBySportType(this.c, mht.b(i, true), mht.b(i, false), 262, new AchieveCallback() { // from class: mga.4
            @Override // com.huawei.pluginachievement.impl.AchieveCallback
            public void onResponse(int i2, Object obj) {
                if (obj instanceof List) {
                    try {
                        Map<String, Long> d = mhn.d((List<HiHealthData>) obj);
                        mga.this.d(i, d);
                        if (d.containsKey("swim_sum_distance")) {
                            mga.this.e = d.get("swim_sum_distance").longValue();
                        }
                        mga mgaVar = mga.this;
                        mgaVar.d(i, mgaVar.e, mga.this.b);
                        return;
                    } catch (ClassCastException unused) {
                        LogUtil.b("FitnessSportCalculator", "getTrackListBySwimType data ClassCastException");
                        return;
                    }
                }
                LogUtil.h("FitnessSportCalculator", "getTrackListBySwimType data is not list");
            }
        });
        int i2 = i - 1;
        this.f14961a.fetchSequenceDataBySportType(this.c, mht.b(i2, true), mht.b(i2, false), 262, new AchieveCallback() { // from class: mga.2
            @Override // com.huawei.pluginachievement.impl.AchieveCallback
            public void onResponse(int i3, Object obj) {
                if (obj instanceof List) {
                    try {
                        Map<String, Long> d = mhn.d((List<HiHealthData>) obj);
                        if (d.containsKey("swim_sum_distance")) {
                            mga.this.b = d.get("swim_sum_distance").longValue();
                        }
                        mga mgaVar = mga.this;
                        mgaVar.d(i, mgaVar.e, mga.this.b);
                    } catch (ClassCastException unused) {
                        LogUtil.b("FitnessSportCalculator", "getTrackListBySwimType data ClassCastException");
                    }
                }
            }
        });
    }

    private void d(int i, Map<String, Double> map, Map<String, String> map2, Map<Integer, Double> map3) {
        if (map.containsKey("total_duration")) {
            int intValue = map.get("total_duration").intValue();
            b(i, 4001, String.valueOf(intValue));
            addStarData("fitnessStar", mhn.b(intValue));
        }
        if (map.containsKey("exclude_run_course_total_duration")) {
            b(i, PrebakedEffectId.RT_JUMP, String.valueOf(map.get("exclude_run_course_total_duration").intValue()));
        }
        if (map.containsKey("total_Course_Day")) {
            b(i, 10029, String.valueOf(map.get("total_Course_Day").intValue()));
        }
        if (map.containsKey("maxFitnessContinuousDays")) {
            b(i, PrebakedEffectId.RT_COIN_DROP, String.valueOf(map.get("maxFitnessContinuousDays").intValue()));
        }
        if (map2.containsKey("favorite_Course_Name")) {
            b(i, PrebakedEffectId.RT_HEARTBEAT, map2.get("favorite_Course_Name"));
        }
        if (map.containsKey("total_count")) {
            b(i, 4002, String.valueOf(map.get("total_count").intValue()));
        }
        if (map.containsKey("favorite_sport_type")) {
            int intValue2 = map.get("favorite_sport_type").intValue();
            b(i, WearableStatusCodes.WIFI_CREDENTIAL_SYNC_NO_CREDENTIAL_FETCHED, intValue2 == 0 ? "" : gwg.e(BaseApplication.getContext(), intValue2));
        }
        if (map.containsKey("favorite_sport_total_duration")) {
            b(i, 4013, String.valueOf(map.get("favorite_sport_total_duration").intValue()));
        }
        b(i, 4014, HiJsonUtil.e(map3));
    }

    private void c(int i, Map<String, Long> map) {
        if (map.containsKey("longest_day_timestamp")) {
            b(i, WearableStatusCodes.INVALID_TARGET_NODE, String.valueOf(map.get("longest_day_timestamp").longValue()));
        }
        if (map.containsKey("longest_day_duration")) {
            b(i, WearableStatusCodes.DATA_ITEM_TOO_LARGE, String.valueOf(map.get("longest_day_duration").longValue()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, Map<String, Long> map) {
        if (map.containsKey("swim_max_distance_day")) {
            b(i, 4009, String.valueOf(map.get("swim_max_distance_day").longValue()));
        }
        if (map.containsKey("swim_longest_day_distance")) {
            b(i, 4010, String.valueOf(map.get("swim_longest_day_distance").longValue()));
        }
        if (map.containsKey("swim_sum_distance")) {
            b(i, 4011, String.valueOf(map.get("swim_sum_distance").longValue()));
        }
        if (map.containsKey("swim_sum_time")) {
            b(i, PrebakedEffectId.RT_FOOTSTEP, String.valueOf(map.get("swim_sum_time").longValue()));
        }
        if (map.containsKey("swim_sum_count")) {
            b(i, 4012, String.valueOf(map.get("swim_sum_count").longValue()));
        }
        if (map.containsKey("max_swim_continuous_days")) {
            b(i, 10032, String.valueOf(map.get("max_swim_continuous_days").longValue()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, long j, long j2) {
        b(i, PrebakedEffectId.RT_ICE, String.valueOf(j - j2));
    }

    private void b(int i, int i2, String str) {
        insertData(i, EnumAnnualType.REPORT_FITNESS.value(), i2, str);
    }
}
