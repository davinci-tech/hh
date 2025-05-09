package defpackage;

import android.content.Context;
import android.text.TextUtils;
import android.text.format.DateUtils;
import androidx.core.util.Pair;
import com.google.gson.Gson;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.health.ecologydevice.open.data.model.RopeBestRecord;
import com.huawei.health.ecologydevice.open.data.model.RopeBestRecordBean;
import com.huawei.health.ecologydevice.open.data.model.RopePerformance;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.ui.commonui.linechart.common.DateType;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.HiDateUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/* loaded from: classes3.dex */
public class ddr {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f11613a = new Object();
    private static volatile ddr d;
    private final Map<String, String> b;
    private String c;
    private final Context e;
    private RopePerformance i;
    private float j;

    static /* synthetic */ void a(int i, Object obj) {
    }

    private ddr() {
        HashMap hashMap = new HashMap(10);
        this.b = hashMap;
        this.e = BaseApplication.getContext();
        hashMap.put("maxSkipSpeedRank", "fastedRopeSpeed");
        hashMap.put("enduranceAbilityRank", "ropeSpeedEndurance");
        hashMap.put("enduranceTimeAbilityRank", "ropeEnduranceTime");
        hashMap.put("skipNumAbilityRank", "ropeSkipNum");
        hashMap.put("maxSkippingTimesAbilityRank", "maxRopeSkippingTimes");
    }

    public static ddr d() {
        ddr ddrVar;
        if (d == null) {
            synchronized (f11613a) {
                if (d == null) {
                    d = new ddr();
                }
                ddrVar = d;
            }
            return ddrVar;
        }
        return d;
    }

    public void c() {
        c(new IBaseResponseCallback() { // from class: ddu
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                ddr.a(i, obj);
            }
        });
        a(new IBaseResponseCallback() { // from class: ddv
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                LogUtil.c("RopePerformanceHelper", "initData requestPerformanceBestOfHistory errorCode = ", Integer.valueOf(i));
            }
        });
        e(new IBaseResponseCallback() { // from class: ddy
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                LogUtil.c("RopePerformanceHelper", "initData requestPerformanceBestOfMonth errorCode = ", Integer.valueOf(i));
            }
        });
    }

    public float b(String str, float f, DateType dateType) {
        if (TextUtils.isEmpty(str) || dks.b(f, 0.0f) <= 0) {
            LogUtil.a("RopePerformanceHelper", "category is null or score <= 0");
            return 0.0f;
        }
        this.c = str;
        this.j = f;
        if (dateType == DateType.DATE_NONE) {
            return c(b());
        }
        if (dateType == DateType.DATE_MONTH) {
            return c(a());
        }
        LogUtil.a("RopePerformanceHelper", "Unsupported time type!");
        return 0.0f;
    }

    public void c(final IBaseResponseCallback iBaseResponseCallback) {
        GRSManager.a(BaseApplication.getContext()).e("healthCloudUrl", new GrsQueryCallback() { // from class: ddr.4
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str) {
                Map<String, String> addPublicHeaders = HealthEngineRequestManager.addPublicHeaders();
                Map b = ddr.this.b(HealthEngineRequestManager.addPublicParams());
                ddr.this.i = (RopePerformance) lqi.d().d(str + "/dataRecommend/getPopulationStat", addPublicHeaders, lql.b(b), RopePerformance.class);
                ddr.this.d(iBaseResponseCallback);
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i) {
                LogUtil.h("RopePerformanceHelper", "requestPerformanceRank getUrl failed resultCode = ", Integer.valueOf(i));
                iBaseResponseCallback.d(-1, null);
            }
        });
    }

    public void e(IBaseResponseCallback iBaseResponseCallback, DateType dateType) {
        if (!CommonUtil.aa(BaseApplication.getContext())) {
            d(iBaseResponseCallback, dateType);
            return;
        }
        if (dateType == DateType.DATE_ALL) {
            a(iBaseResponseCallback);
        } else if (dateType == DateType.DATE_MONTH) {
            e(iBaseResponseCallback);
        } else {
            LogUtil.h("RopePerformanceHelper", "quaryPerformanceOfBest() error dateType");
            iBaseResponseCallback.d(-1, null);
        }
    }

    private void d(IBaseResponseCallback iBaseResponseCallback, DateType dateType) {
        String str;
        LogUtil.a("RopePerformanceHelper", "quaryBestDataFromCache() dateType = ", dateType);
        if (dateType == DateType.DATE_ALL) {
            str = SharedPreferenceManager.b(this.e, "rope_performance_cache", "rope_cache_key_best_of_history");
        } else if (dateType == DateType.DATE_MONTH) {
            str = SharedPreferenceManager.b(this.e, "rope_performance_cache", "rope_cache_key_best_of_month");
        } else {
            LogUtil.h("RopePerformanceHelper", "quaryBestDataFromCache() error dateType");
            str = null;
        }
        if (str == null) {
            LogUtil.h("RopePerformanceHelper", "quaryBestDataFromCache() bestDataJson is null");
            iBaseResponseCallback.d(-1, null);
        } else {
            b((RopeBestRecord.BestRecordData) new Gson().fromJson(str, RopeBestRecord.BestRecordData.class), iBaseResponseCallback);
        }
    }

    public void a(final IBaseResponseCallback iBaseResponseCallback) {
        GRSManager.a(BaseApplication.getContext()).e("healthCloudUrl", new GrsQueryCallback() { // from class: ddr.5
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str) {
                Map<String, Object> addPublicParams = HealthEngineRequestManager.addPublicParams();
                addPublicParams.put("bestItems", ddr.this.e());
                Map<String, String> addPublicHeaders = HealthEngineRequestManager.addPublicHeaders();
                RopeBestRecord ropeBestRecord = (RopeBestRecord) lqi.d().d(str + "/dataQuery/report/getPersonalReport", addPublicHeaders, lql.b(addPublicParams), RopeBestRecord.class);
                if (ropeBestRecord == null) {
                    LogUtil.h("RopePerformanceHelper", "requestPerformanceBestOfHistory() ropeBestRecordData is null");
                    iBaseResponseCallback.d(-1, null);
                } else if (ropeBestRecord.getResultCode() != 0) {
                    LogUtil.h("RopePerformanceHelper", "requestPerformanceBestOfHistory fail resultCode is ", Integer.valueOf(ropeBestRecord.getResultCode()), " resultDesc is ", ropeBestRecord.getResultDesc());
                    iBaseResponseCallback.d(-1, null);
                } else {
                    RopeBestRecord.BestRecordData bestReports = ropeBestRecord.getBestReports();
                    ddr.this.b(bestReports, iBaseResponseCallback);
                    SharedPreferenceManager.e(ddr.this.e, "rope_performance_cache", "rope_cache_key_best_of_history", lql.b(bestReports), (StorageParams) null);
                }
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i) {
                LogUtil.h("RopePerformanceHelper", "requestPerformanceBestOfHistory getUrl failed resultCode = ", Integer.valueOf(i));
                iBaseResponseCallback.d(-1, null);
            }
        });
    }

    public void e(final IBaseResponseCallback iBaseResponseCallback) {
        GRSManager.a(BaseApplication.getContext()).e("healthCloudUrl", new GrsQueryCallback() { // from class: ddr.2
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str) {
                Map<String, Object> addPublicParams = HealthEngineRequestManager.addPublicParams();
                addPublicParams.put("bestItems", ddr.this.e());
                addPublicParams.put("queryType", 1);
                addPublicParams.put("startTime", Long.valueOf(ddr.this.d(0)));
                addPublicParams.put("endTime", Long.valueOf(ddr.this.d(1) - 1000));
                addPublicParams.put("timeZone", HiDateUtil.d((String) null));
                Map<String, String> addPublicHeaders = HealthEngineRequestManager.addPublicHeaders();
                RopeBestRecord ropeBestRecord = (RopeBestRecord) lqi.d().d(str + "/dataQuery/report/getCycleReport", addPublicHeaders, lql.b(addPublicParams), RopeBestRecord.class);
                if (ropeBestRecord == null) {
                    LogUtil.h("RopePerformanceHelper", "requestPerformanceBestOfMonth() ropeBestRecordData is null");
                    iBaseResponseCallback.d(-1, null);
                    return;
                }
                if (ropeBestRecord.getResultCode() != 0) {
                    LogUtil.h("RopePerformanceHelper", "requestPerformanceBestOfMonth fail resultCode is ", Integer.valueOf(ropeBestRecord.getResultCode()), " resultDesc is ", ropeBestRecord.getResultDesc());
                    iBaseResponseCallback.d(-1, null);
                    return;
                }
                RopeBestRecord.UserCycleReportData userCycleReport = ropeBestRecord.getUserCycleReport();
                if (userCycleReport == null) {
                    LogUtil.h("RopePerformanceHelper", "requestPerformanceBestOfMonth userCycleReport is null");
                    iBaseResponseCallback.d(-1, null);
                } else {
                    RopeBestRecord.BestRecordData bestReports = userCycleReport.getBestReports();
                    ddr.this.b(bestReports, iBaseResponseCallback);
                    SharedPreferenceManager.e(ddr.this.e, "rope_performance_cache", "rope_cache_key_best_of_month", lql.b(bestReports), (StorageParams) null);
                }
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i) {
                LogUtil.h("RopePerformanceHelper", "requestPerformanceBestOfMonth getUrl failed resultCode = ", Integer.valueOf(i));
                iBaseResponseCallback.d(-1, null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Set<String> e() {
        HashSet hashSet = new HashSet();
        hashSet.add(ParsedFieldTag.BEST_ROPE_SINGLE_COUNT);
        hashSet.add(ParsedFieldTag.BEST_ROPE_CONTINUOUS_COUNT);
        hashSet.add("bestRopeSkippingMaxSpeed1MIN");
        hashSet.add("bestRopeSkippingEnduranceAbility");
        hashSet.add("bestRopeSkippingEnduranceTimeAbility");
        return hashSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long d(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(2, i);
        calendar.set(5, 1);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(RopeBestRecord.BestRecordData bestRecordData, IBaseResponseCallback iBaseResponseCallback) {
        if (bestRecordData == null) {
            LogUtil.h("RopePerformanceHelper", "convertBestData() bestRecordData is null");
            iBaseResponseCallback.d(-1, null);
            return;
        }
        float b = b(bestRecordData.getBestRopeSkippingMaxSpeed1MIN());
        float b2 = b("maxSkipSpeedRank", b, DateType.DATE_NONE);
        float b3 = b("enduranceTimeAbilityRank", b(bestRecordData.getBestRopeSkippingEnduranceTimeAbility()), DateType.DATE_NONE);
        float b4 = b(bestRecordData.getBestRopeSkippingContinuousCount());
        float b5 = b("maxSkippingTimesAbilityRank", b4, DateType.DATE_NONE);
        float b6 = b(bestRecordData.getBestRopeSkippingSingleCount());
        float b7 = b("skipNumAbilityRank", b6, DateType.DATE_NONE);
        float b8 = b(bestRecordData.getBestRopeSkippingEnduranceAbility());
        float[] fArr = {b, b(bestRecordData.getBestRopeSkippingEnduranceTimeAbility()) / 60.0f, b4, b6, b8};
        float[] fArr2 = {b2, b3, b5, b7, b("enduranceAbilityRank", b8, DateType.DATE_NONE)};
        if (a(fArr) || a(fArr2)) {
            LogUtil.a("RopePerformanceHelper", "value or rank is invalid value");
            iBaseResponseCallback.d(-1, null);
        } else {
            iBaseResponseCallback.d(0, new Pair(fArr, fArr2));
        }
    }

    private boolean a(float[] fArr) {
        for (float f : fArr) {
            if (dks.b(f, 0.0f) > 0) {
                return false;
            }
        }
        return true;
    }

    private int b(List<RopeBestRecordBean> list) {
        if (koq.b(list)) {
            return 0;
        }
        Collections.sort(list, new Comparator() { // from class: ddt
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compare;
                compare = Integer.compare(((RopeBestRecordBean) obj2).getValue(), ((RopeBestRecordBean) obj).getValue());
                return compare;
            }
        });
        return list.get(0).getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("RopePerformanceHelper", "enter handleRankData");
        RopePerformance ropePerformance = this.i;
        if (ropePerformance == null) {
            LogUtil.h("RopePerformanceHelper", "mRopePerformance is null.");
            iBaseResponseCallback.d(-1, null);
            return;
        }
        if (ropePerformance.getResultCode() != 0) {
            LogUtil.h("RopePerformanceHelper", "Failed to handleRankData.");
            iBaseResponseCallback.d(-1, null);
            return;
        }
        List<RopePerformance.Performance> populationStats = this.i.getPopulationStats();
        if (c(b(), populationStats)) {
            d(populationStats);
        } else {
            LogUtil.c("RopePerformanceHelper", "handleRankData same cache current data.");
        }
        if (koq.b(a())) {
            a(populationStats);
        }
        iBaseResponseCallback.d(0, populationStats);
    }

    private float c(List<RopePerformance.Performance> list) {
        if (koq.b(list)) {
            LogUtil.a("RopePerformanceHelper", "parseRankData performances is null");
            return 0.0f;
        }
        for (RopePerformance.Performance performance : list) {
            if (performance != null) {
                if (Objects.equals(this.b.get(this.c), performance.getPortraitCategory())) {
                    return e(performance.getPortraitValue());
                }
            }
        }
        return 0.0f;
    }

    private float e(List<RopePerformance.Rank> list) {
        LogUtil.a("RopePerformanceHelper", "enter calculateRank");
        Collections.sort(list, new Comparator() { // from class: ddp
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int b;
                b = dks.b(((RopePerformance.Rank) obj).getValue(), ((RopePerformance.Rank) obj2).getValue());
                return b;
            }
        });
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (RopePerformance.Rank rank : list) {
            arrayList.add(Float.valueOf(rank.getThreshold()));
            arrayList2.add(Float.valueOf(rank.getValue()));
        }
        int size = arrayList.size();
        int i = 0;
        float f = 0.0f;
        while (true) {
            if (i >= size) {
                break;
            }
            if (dks.b(((Float) arrayList.get(0)).floatValue(), this.j) == 1) {
                f = 1.0f;
            } else if (dks.b(((Float) arrayList.get(size - 1)).floatValue(), this.j) == -1) {
                f = 100.0f;
            } else if (dks.b(((Float) arrayList.get(i)).floatValue(), this.j) == 0) {
                f = ((Float) arrayList2.get(i)).floatValue();
            } else if (dks.b(((Float) arrayList.get(i)).floatValue(), this.j) == 1) {
                float floatValue = ((Float) arrayList.get(i)).floatValue();
                int i2 = i - 1;
                float floatValue2 = ((Float) arrayList.get(i2)).floatValue();
                float floatValue3 = ((Float) arrayList2.get(i)).floatValue();
                float floatValue4 = ((Float) arrayList2.get(i2)).floatValue();
                LogUtil.a("RopePerformanceHelper", "maxThreshold = ", Float.valueOf(floatValue), ", minThreshold = ", Float.valueOf(floatValue2), ", maxValue = ", Float.valueOf(floatValue3), ", minValue = ", Float.valueOf(floatValue4));
                BigDecimal bigDecimal = new BigDecimal(floatValue);
                BigDecimal bigDecimal2 = new BigDecimal(floatValue2);
                BigDecimal bigDecimal3 = new BigDecimal(floatValue3);
                BigDecimal bigDecimal4 = new BigDecimal(floatValue4);
                BigDecimal b = b(new BigDecimal(this.j).subtract(bigDecimal2), bigDecimal.subtract(bigDecimal2));
                f = BigDecimal.ZERO.equals(b) ? 0.0f : b.multiply(bigDecimal3.subtract(bigDecimal4)).add(bigDecimal4).floatValue();
            } else {
                LogUtil.c("RopePerformanceHelper", "Unknown!");
            }
            if (f != 0.0f) {
                LogUtil.a("RopePerformanceHelper", "calculateRank value = ", Float.valueOf(f), ", score = ", Float.valueOf(this.j));
                break;
            }
            i++;
        }
        return f;
    }

    private BigDecimal b(BigDecimal bigDecimal, BigDecimal bigDecimal2) {
        try {
            return bigDecimal.divide(bigDecimal2, 1, 4);
        } catch (ArithmeticException unused) {
            return BigDecimal.ZERO;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<String, Object> b(Map<String, Object> map) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new RopePerformance.Performance("fastedRopeSpeed", "-1"));
        arrayList.add(new RopePerformance.Performance("ropeSpeedEndurance", "-1"));
        arrayList.add(new RopePerformance.Performance("ropeEnduranceTime", "-1"));
        arrayList.add(new RopePerformance.Performance("ropeSkipNum", "-1"));
        arrayList.add(new RopePerformance.Performance("maxRopeSkippingTimes", "-1"));
        map.put("categories", arrayList);
        map.put("timeZone", HiDateUtil.d((String) null));
        return map;
    }

    public void d(List<RopePerformance.Performance> list) {
        SharedPreferenceManager.e(this.e, "rope_performance_cache", "rope_cache_key_current_data", jdr.b(list, null), (StorageParams) null);
    }

    public void a(List<RopePerformance.Performance> list) {
        String f = f();
        LogUtil.a("RopePerformanceHelper", "setCacheData time: ", f);
        String b = jdr.b(list, null);
        SharedPreferenceManager.e(this.e, "rope_performance_cache", "rope_cache_key_time", f, (StorageParams) null);
        SharedPreferenceManager.e(this.e, "rope_performance_cache", "rope_cache_key_month_data", b, (StorageParams) null);
    }

    public List<RopePerformance.Performance> b() {
        String b = SharedPreferenceManager.b(this.e, "rope_performance_cache", "rope_cache_key_current_data");
        ArrayList arrayList = new ArrayList();
        jdr.d(b, getClass().getClassLoader(), null, arrayList);
        return arrayList;
    }

    public List<RopePerformance.Performance> a() {
        String f = f();
        String b = SharedPreferenceManager.b(this.e, "rope_performance_cache", "rope_cache_key_time");
        LogUtil.a("RopePerformanceHelper", "getCacheData time: ", f, ", cacheTime: ", b);
        if (f.equals(b)) {
            String b2 = SharedPreferenceManager.b(this.e, "rope_performance_cache", "rope_cache_key_month_data");
            ArrayList arrayList = new ArrayList();
            jdr.d(b2, getClass().getClassLoader(), null, arrayList);
            return arrayList;
        }
        return Collections.emptyList();
    }

    public boolean c(List<RopePerformance.Performance> list, List<RopePerformance.Performance> list2) {
        if (koq.b(list)) {
            LogUtil.a("RopePerformanceHelper", "isDiff cacheData is null.");
            return true;
        }
        return !jdr.b(list, null).equals(jdr.b(list2, null));
    }

    private String f() {
        return DateUtils.formatDateTime(this.e, System.currentTimeMillis(), 52);
    }
}
