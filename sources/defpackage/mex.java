package defpackage;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hmf.tasks.OnCompleteListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.Tasks;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.pluginachievement.connectivity.config.AUserProfile;
import com.huawei.pluginachievement.impl.AchieveCallback;
import com.huawei.pluginachievement.manager.model.RecentMonthRecordFromDB;
import com.huawei.pluginachievement.manager.model.RecentWeekRecordFromDB;
import com.huawei.pluginachievement.manager.model.WeekAndMonthRecord;
import com.huawei.pluginachievement.report.bean.AnnualReportFitnessRaw;
import com.huawei.pluginachievement.report.bean.ReportDataBean;
import com.huawei.pluginachievement.report.bean.ReportFitnessData;
import com.huawei.pluginachievement.report.bean.ReportSwimData;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes6.dex */
public class mex {
    private static volatile mex d;

    /* renamed from: a, reason: collision with root package name */
    private Context f14940a;

    private mex(Context context) {
        if (context == null) {
            return;
        }
        this.f14940a = context.getApplicationContext();
    }

    public static mex b(Context context) {
        if (d == null) {
            synchronized (mex.class) {
                if (d == null) {
                    d = new mex(context);
                }
            }
        }
        return d;
    }

    private void b() {
        String b = mct.b(this.f14940a, "reportTimeZone");
        String am = CommonUtil.am();
        if (TextUtils.isEmpty(b)) {
            mct.b(this.f14940a, "reportTimeZone", am);
            c();
        } else if (!am.equals(b)) {
            LogUtil.a("PLGACHIEVE_AchieveReportDataService", "Timezone changed");
            c();
            mct.b(this.f14940a, "reportTimeZone", am);
        }
        if (TextUtils.isEmpty(mct.b(this.f14940a, "reportVersion"))) {
            mct.b(this.f14940a, "reportVersion", "2021");
            c();
        }
    }

    private void c() {
        WeekAndMonthRecord weekAndMonthRecord = new WeekAndMonthRecord();
        AUserProfile t = meh.c(this.f14940a).t();
        if (t == null) {
            LogUtil.h("PLGACHIEVE_AchieveReportDataService", "deleteAllData: userProfile is null");
        } else {
            weekAndMonthRecord.setHuid(t.getHuid());
        }
        mee.b(this.f14940a).d(weekAndMonthRecord);
    }

    public void b(final long j, final long j2, final int i, final AchieveCallback achieveCallback) {
        ThreadPoolManager.d().b("PLGACHIEVE_AchieveReportDataService", null);
        ThreadPoolManager.d().c("PLGACHIEVE_AchieveReportDataService", new Runnable() { // from class: mex.3
            @Override // java.lang.Runnable
            public void run() {
                mex.this.e(Math.max(j, mkx.d(-2, j2, 1)), j2, i, achieveCallback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(long j, long j2, int i, AchieveCallback achieveCallback) {
        if (mcv.d(this.f14940a).getAdapter() == null) {
            LogUtil.a("PLGACHIEVE_AchieveReportDataService", "PluginAchieve.getInstance(mContext).getAdapter() is null");
            return;
        }
        b();
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(0);
        ArrayList<AnnualReportFitnessRaw>[] arrayListArr = {null};
        e(j, j2, i, concurrentHashMap, arrayListArr);
        CountDownLatch countDownLatch = new CountDownLatch(2);
        ConcurrentHashMap concurrentHashMap2 = new ConcurrentHashMap(0);
        ConcurrentHashMap concurrentHashMap3 = new ConcurrentHashMap(0);
        boolean o = Utils.o();
        Task callInBackground = Tasks.callInBackground(c(o, arrayListArr[0], concurrentHashMap2, concurrentHashMap));
        Task callInBackground2 = Tasks.callInBackground(a(o, arrayListArr[0], concurrentHashMap3, concurrentHashMap));
        mfj.d((Task<Void>) callInBackground, countDownLatch);
        mfj.d((Task<Void>) callInBackground2, countDownLatch);
        ArrayList<Map.Entry<String, Long>> e = mfj.e(j, j2);
        mfj.e(countDownLatch, OpAnalyticsConstants.H5_LOADING_DELAY, "generateReportDataEx");
        long[] jArr = {j, j2};
        e(concurrentHashMap2, e, jArr);
        c(concurrentHashMap3, e, jArr);
        d(concurrentHashMap2, concurrentHashMap3, jArr, achieveCallback);
    }

    private void d(Map<Long, RecentWeekRecordFromDB> map, Map<Long, RecentMonthRecordFromDB> map2, long[] jArr, AchieveCallback achieveCallback) {
        long j = jArr[0];
        boolean z = true;
        long j2 = jArr[1];
        boolean e = e(map, j, j2);
        boolean c = c(map2, j, j2);
        if (!e && !c) {
            z = false;
        }
        d(achieveCallback, z);
        if (e) {
            b(map);
        }
        if (c) {
            d(map2);
        }
        if (e || c) {
            d(achieveCallback, false);
        }
    }

    private void b(Map<Long, RecentWeekRecordFromDB> map) {
        RecentWeekRecordFromDB value;
        Iterator<Map.Entry<Long, RecentWeekRecordFromDB>> it = map.entrySet().iterator();
        while (true) {
            boolean z = false;
            while (it.hasNext()) {
                value = it.next().getValue();
                for (ReportDataBean reportDataBean : value.mReportDataMap.values()) {
                    if (reportDataBean.getCountDownLatch() != null) {
                        mfj.e(reportDataBean.getCountDownLatch(), OpAnalyticsConstants.H5_LOADING_DELAY, "WeekDetailTask:" + reportDataBean.getSportType());
                        d(reportDataBean);
                        z = true;
                    }
                }
                if (z) {
                    break;
                }
            }
            return;
            value.setReportDataDetail(c(value.mReportDataMap));
            a(value);
        }
    }

    private void d(Map<Long, RecentMonthRecordFromDB> map) {
        RecentMonthRecordFromDB value;
        Iterator<Map.Entry<Long, RecentMonthRecordFromDB>> it = map.entrySet().iterator();
        while (true) {
            boolean z = false;
            while (it.hasNext()) {
                value = it.next().getValue();
                for (ReportDataBean reportDataBean : value.mReportDataMap.values()) {
                    if (reportDataBean.getCountDownLatch() != null) {
                        mfj.e(reportDataBean.getCountDownLatch(), OpAnalyticsConstants.H5_LOADING_DELAY, "MonthDetailTask:" + reportDataBean.getSportType());
                        d(reportDataBean);
                        z = true;
                    }
                }
                if (z) {
                    break;
                }
            }
            return;
            value.setReportDataDetail(c(value.mReportDataMap));
            c(value);
        }
    }

    private void a(RecentWeekRecordFromDB recentWeekRecordFromDB) {
        a(new Gson().toJson(recentWeekRecordFromDB), recentWeekRecordFromDB.acquireMonday(), recentWeekRecordFromDB.acquireSunDay(), 1, 2);
    }

    private void c(RecentMonthRecordFromDB recentMonthRecordFromDB) {
        a(new Gson().toJson(recentMonthRecordFromDB), recentMonthRecordFromDB.acquireFirstday(), recentMonthRecordFromDB.acquireLastDay(), 2, 2);
    }

    private void d(ReportDataBean reportDataBean) {
        if (reportDataBean instanceof ReportFitnessData) {
            b(reportDataBean);
            reportDataBean.setSumTime(Math.round(reportDataBean.getSumTime() / 1000.0d));
            reportDataBean.setMaxTime(Math.round(reportDataBean.getMaxTime() / 1000.0d));
        }
        e(reportDataBean);
    }

    private boolean e(Map<Long, RecentWeekRecordFromDB> map, long j, long j2) {
        Iterator<Map.Entry<Long, RecentWeekRecordFromDB>> it = map.entrySet().iterator();
        boolean z = false;
        while (it.hasNext()) {
            RecentWeekRecordFromDB value = it.next().getValue();
            if (value.acquireMonday() >= j && value.acquireSunDay() <= j2) {
                for (ReportDataBean reportDataBean : value.mReportDataMap.values()) {
                    if (reportDataBean.getTaskList().size() > 0) {
                        a(reportDataBean);
                        z = true;
                    }
                }
            }
        }
        return z;
    }

    private boolean c(Map<Long, RecentMonthRecordFromDB> map, long j, long j2) {
        Iterator<Map.Entry<Long, RecentMonthRecordFromDB>> it = map.entrySet().iterator();
        boolean z = false;
        while (it.hasNext()) {
            RecentMonthRecordFromDB value = it.next().getValue();
            if (value.acquireFirstday() >= j && value.acquireLastDay() <= j2) {
                for (ReportDataBean reportDataBean : value.mReportDataMap.values()) {
                    if (reportDataBean.getTaskList().size() > 0) {
                        a(reportDataBean);
                        z = true;
                    }
                }
            }
        }
        return z;
    }

    private void a(ReportDataBean reportDataBean) {
        reportDataBean.setCountDownLatch(new CountDownLatch(1));
        d(reportDataBean, reportDataBean.getTaskList().get(0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(ReportDataBean reportDataBean, Callable<Void> callable) {
        c(Tasks.callInBackground(callable), reportDataBean);
    }

    private void c(Task<Void> task, final ReportDataBean reportDataBean) {
        task.addOnCompleteListener(new OnCompleteListener<Void>() { // from class: mex.2
            @Override // com.huawei.hmf.tasks.OnCompleteListener
            public void onComplete(Task<Void> task2) {
                reportDataBean.getTaskList().remove(0);
                if (reportDataBean.getTaskList().size() > 0) {
                    mex mexVar = mex.this;
                    ReportDataBean reportDataBean2 = reportDataBean;
                    mexVar.d(reportDataBean2, reportDataBean2.getTaskList().get(0));
                    return;
                }
                reportDataBean.getCountDownLatch().countDown();
            }
        });
    }

    private Callable<Void> e(final long j, final long j2, final ArrayList<AnnualReportFitnessRaw>[] arrayListArr) {
        return new Callable<Void>() { // from class: mex.5
            @Override // java.util.concurrent.Callable
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public Void call() {
                arrayListArr[0] = mfj.b(j, j2);
                return null;
            }
        };
    }

    private Callable<Void> d(final long j, final long j2, final int i, final Map<Integer, List<HiHealthData>> map) {
        return new Callable<Void>() { // from class: mex.7
            @Override // java.util.concurrent.Callable
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Void call() {
                List<HiHealthData> c = mfj.c(j, j2, i);
                if (c == null) {
                    return null;
                }
                map.put(1002, c);
                return null;
            }
        };
    }

    private Callable<Void> c(final boolean z, final ArrayList<AnnualReportFitnessRaw> arrayList, final Map<Long, RecentWeekRecordFromDB> map, final Map<Integer, List<HiHealthData>> map2) {
        return new Callable<Void>() { // from class: mex.10
            @Override // java.util.concurrent.Callable
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public Void call() {
                new mfa(z, map).d(arrayList, map2);
                return null;
            }
        };
    }

    private Callable<Void> a(final boolean z, final ArrayList<AnnualReportFitnessRaw> arrayList, final Map<Long, RecentMonthRecordFromDB> map, final Map<Integer, List<HiHealthData>> map2) {
        return new Callable<Void>() { // from class: mex.6
            @Override // java.util.concurrent.Callable
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public Void call() {
                new mew(z, map).b(arrayList, map2);
                return null;
            }
        };
    }

    private void e(Map<Long, RecentWeekRecordFromDB> map, ArrayList<Map.Entry<String, Long>> arrayList, long[] jArr) {
        long j = jArr[0];
        long j2 = jArr[1];
        Iterator<Map.Entry<Long, RecentWeekRecordFromDB>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            RecentWeekRecordFromDB value = it.next().getValue();
            if (value.acquireMonday() >= j && value.acquireSunDay() <= j2) {
                for (int i = 0; i < arrayList.size(); i++) {
                    Map.Entry<String, Long> entry = arrayList.get(i);
                    if (entry.getValue().longValue() >= value.acquireMonday() && entry.getValue().longValue() <= value.acquireSunDay()) {
                        value.acquireMedalId().add(entry.getKey());
                    }
                }
                b(value);
                a(value);
            }
        }
    }

    private void c(Map<Long, RecentMonthRecordFromDB> map, ArrayList<Map.Entry<String, Long>> arrayList, long[] jArr) {
        long j = jArr[0];
        long j2 = jArr[1];
        Iterator<Map.Entry<Long, RecentMonthRecordFromDB>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            RecentMonthRecordFromDB value = it.next().getValue();
            if (value.acquireFirstday() >= j && value.acquireLastDay() <= j2) {
                for (int i = 0; i < arrayList.size(); i++) {
                    Map.Entry<String, Long> entry = arrayList.get(i);
                    if (entry.getValue().longValue() >= value.acquireFirstday() && entry.getValue().longValue() <= value.acquireLastDay()) {
                        value.acquireMedalId().add(entry.getKey());
                    }
                }
                b(value);
                c(value);
            }
        }
    }

    private void b(RecentWeekRecordFromDB recentWeekRecordFromDB) {
        d(recentWeekRecordFromDB);
        c(recentWeekRecordFromDB);
        e(recentWeekRecordFromDB);
        recentWeekRecordFromDB.setReportDataDetail(c(recentWeekRecordFromDB.mReportDataMap));
    }

    private void b(RecentMonthRecordFromDB recentMonthRecordFromDB) {
        e(recentMonthRecordFromDB);
        a(recentMonthRecordFromDB);
        h(recentMonthRecordFromDB);
        recentMonthRecordFromDB.setReportDataDetail(c(recentMonthRecordFromDB.mReportDataMap));
    }

    private void c(RecentWeekRecordFromDB recentWeekRecordFromDB) {
        recentWeekRecordFromDB.setSumAllTime(Math.round(recentWeekRecordFromDB.acquireSumAllTime() / 1000.0d));
        Map<Long, Long> acquireSevenDayTime = recentWeekRecordFromDB.acquireSevenDayTime();
        Iterator<Map.Entry<Long, Long>> it = acquireSevenDayTime.entrySet().iterator();
        while (it.hasNext()) {
            acquireSevenDayTime.put(it.next().getKey(), Long.valueOf(Math.round(r4.getValue().longValue() / 1000.0d)));
        }
        mfj.c(recentWeekRecordFromDB.mReportDataMap);
    }

    private void e(RecentMonthRecordFromDB recentMonthRecordFromDB) {
        recentMonthRecordFromDB.setSumAllTime(Math.round(recentMonthRecordFromDB.acquireSumAllTime() / 1000.0d));
        Map<Long, Long> acquireWeekTime = recentMonthRecordFromDB.acquireWeekTime();
        Iterator<Map.Entry<Long, Long>> it = acquireWeekTime.entrySet().iterator();
        while (it.hasNext()) {
            acquireWeekTime.put(it.next().getKey(), Long.valueOf(Math.round(r4.getValue().longValue() / 1000.0d)));
        }
        mfj.c(recentMonthRecordFromDB.mReportDataMap);
    }

    private void e(RecentWeekRecordFromDB recentWeekRecordFromDB) {
        long acquireSunDay = recentWeekRecordFromDB.acquireSunDay();
        for (long acquireMonday = recentWeekRecordFromDB.acquireMonday(); acquireMonday <= acquireSunDay; acquireMonday = HiDateUtil.g(acquireMonday)) {
            if (!recentWeekRecordFromDB.acquireSevenDayTime().containsKey(Long.valueOf(acquireMonday))) {
                recentWeekRecordFromDB.acquireSevenDayTime().put(Long.valueOf(acquireMonday), 0L);
            }
        }
    }

    private void e(ReportDataBean reportDataBean) {
        if (reportDataBean instanceof ReportSwimData) {
            ReportSwimData reportSwimData = (ReportSwimData) reportDataBean;
            if (reportSwimData.getStroke().size() == 0) {
                return;
            }
            reportSwimData.setMaxStroke(((Integer) ((Map.Entry) Collections.max(new ArrayList(reportSwimData.getStroke().entrySet()), new Comparator() { // from class: mez
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return mex.d((Map.Entry) obj, (Map.Entry) obj2);
                }
            })).getKey()).intValue());
        }
    }

    static /* synthetic */ int d(Map.Entry entry, Map.Entry entry2) {
        if (((Integer) entry.getValue()).intValue() - ((Integer) entry2.getValue()).intValue() < 0) {
            return -1;
        }
        return ((Integer) entry.getValue()).intValue() - ((Integer) entry2.getValue()).intValue() == 0 ? 0 : 1;
    }

    private void b(ReportDataBean reportDataBean) {
        if (reportDataBean instanceof ReportFitnessData) {
            ReportFitnessData reportFitnessData = (ReportFitnessData) reportDataBean;
            int i = 0;
            for (Map.Entry<String, Integer> entry : reportFitnessData.getFirstLevelMap().entrySet()) {
                if (entry.getValue().intValue() > i) {
                    i = entry.getValue().intValue();
                    reportFitnessData.setMaxFirstLevel(entry.getKey());
                }
            }
            int i2 = 0;
            for (Map.Entry<String, Integer> entry2 : reportFitnessData.getCourseMap().entrySet()) {
                if (entry2.getValue().intValue() > i2) {
                    i2 = entry2.getValue().intValue();
                    reportFitnessData.setMaxCourse(entry2.getKey());
                }
            }
            if (reportFitnessData.getTrainingPointsMap().size() == 0) {
                return;
            }
            ArrayList arrayList = new ArrayList(reportFitnessData.getTrainingPointsMap().entrySet());
            Collections.sort(arrayList, new Comparator<Map.Entry<String, Integer>>() { // from class: mex.9
                @Override // java.util.Comparator
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public int compare(Map.Entry<String, Integer> entry3, Map.Entry<String, Integer> entry4) {
                    return entry4.getValue().intValue() - entry3.getValue().intValue();
                }
            });
            int intValue = ((Integer) ((Map.Entry) arrayList.get(0)).getValue()).intValue();
            for (int i3 = 0; i3 < arrayList.size() && intValue == ((Integer) ((Map.Entry) arrayList.get(i3)).getValue()).intValue(); i3++) {
                reportFitnessData.getMaxTrainingPoints().add((String) ((Map.Entry) arrayList.get(i3)).getKey());
            }
        }
    }

    private void d(RecentWeekRecordFromDB recentWeekRecordFromDB) {
        if (recentWeekRecordFromDB.acquireSportDays().size() == 0) {
            return;
        }
        recentWeekRecordFromDB.setAvgCalorie(Math.round(recentWeekRecordFromDB.acquireSumCalorie() / recentWeekRecordFromDB.acquireSportDays().size()));
    }

    private void a(RecentMonthRecordFromDB recentMonthRecordFromDB) {
        if (recentMonthRecordFromDB.acquireSportDays().size() == 0) {
            return;
        }
        recentMonthRecordFromDB.setDayAvgCalorie(Math.round(recentMonthRecordFromDB.acquireSumCalorie() / recentMonthRecordFromDB.acquireSportDays().size()));
    }

    private ArrayList<ReportDataBean> c(Map<Integer, ReportDataBean> map) {
        ArrayList<ReportDataBean> arrayList = new ArrayList<>(0);
        if (map.size() == 0) {
            return arrayList;
        }
        for (ReportDataBean reportDataBean : map.values()) {
            if (reportDataBean.getSumTime() > 0 && reportDataBean.getSportCount() > 0) {
                arrayList.add(reportDataBean);
            }
        }
        if (arrayList.size() == 0) {
            return arrayList;
        }
        Collections.sort(arrayList, new Comparator<ReportDataBean>() { // from class: mex.8
            @Override // java.util.Comparator
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public int compare(ReportDataBean reportDataBean2, ReportDataBean reportDataBean3) {
                return reportDataBean3.getSportCount() - reportDataBean2.getSportCount();
            }
        });
        return arrayList;
    }

    private void h(RecentMonthRecordFromDB recentMonthRecordFromDB) {
        float d2 = d(recentMonthRecordFromDB);
        if (d2 == 0.0f) {
            return;
        }
        recentMonthRecordFromDB.mPercentCalorie[4] = 100;
        float f = d2 * 1.0f;
        recentMonthRecordFromDB.mPercentCalorie[0] = (int) Math.floor((b(recentMonthRecordFromDB, 258) * 100.0f) / f);
        recentMonthRecordFromDB.mPercentCalorie[4] = recentMonthRecordFromDB.mPercentCalorie[4] - recentMonthRecordFromDB.mPercentCalorie[0];
        recentMonthRecordFromDB.mPercentCalorie[1] = (int) Math.floor((b(recentMonthRecordFromDB, 257) * 100.0f) / f);
        recentMonthRecordFromDB.mPercentCalorie[4] = recentMonthRecordFromDB.mPercentCalorie[4] - recentMonthRecordFromDB.mPercentCalorie[1];
        recentMonthRecordFromDB.mPercentCalorie[2] = (int) Math.floor((b(recentMonthRecordFromDB, 259) * 100.0f) / f);
        recentMonthRecordFromDB.mPercentCalorie[4] = recentMonthRecordFromDB.mPercentCalorie[4] - recentMonthRecordFromDB.mPercentCalorie[2];
        recentMonthRecordFromDB.mPercentCalorie[3] = (int) Math.floor((b(recentMonthRecordFromDB, 260) * 100.0f) / f);
        recentMonthRecordFromDB.mPercentCalorie[4] = recentMonthRecordFromDB.mPercentCalorie[4] - recentMonthRecordFromDB.mPercentCalorie[3];
        if (recentMonthRecordFromDB.mPercentCalorie[4] < 0) {
            recentMonthRecordFromDB.mPercentCalorie[4] = 0;
            LogUtil.h("PLGACHIEVE_AchieveReportDataService", "savePercentCalorie : percentCalorie error.");
        }
    }

    private float d(RecentMonthRecordFromDB recentMonthRecordFromDB) {
        return recentMonthRecordFromDB.acquireSumCalcCalorie();
    }

    private float b(RecentMonthRecordFromDB recentMonthRecordFromDB, int i) {
        ReportDataBean reportDataBean = recentMonthRecordFromDB.mReportDataMap.get(Integer.valueOf(i));
        if (reportDataBean != null) {
            return reportDataBean.getSumCalorie();
        }
        return 0.0f;
    }

    private void d(AchieveCallback achieveCallback, boolean z) {
        if (achieveCallback != null) {
            achieveCallback.onResponse(0, Boolean.valueOf(z));
        }
        BroadcastManagerUtil.bFF_(this.f14940a, new Intent().setAction("com.huawei.health.action.UPDATE_UI_CHART"));
    }

    private boolean a(String str, long j, long j2, int i, int i2) {
        WeekAndMonthRecord weekAndMonthRecord = new WeekAndMonthRecord();
        weekAndMonthRecord.setValue(str);
        weekAndMonthRecord.setStartTimestamp(Long.valueOf(j));
        weekAndMonthRecord.setEndTimestamp(Long.valueOf(j2));
        weekAndMonthRecord.setRecentType(i);
        weekAndMonthRecord.setDataSource(i2);
        weekAndMonthRecord.setHuid(meh.c(this.f14940a).t().getHuid());
        return mee.b(this.f14940a).b(weekAndMonthRecord);
    }

    private Callable<Void> a(final long j, final long j2, final Map<Integer, List<HiHealthData>> map) {
        return new Callable<Void>() { // from class: mex.15
            @Override // java.util.concurrent.Callable
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public Void call() {
                List<HiHealthData> c = mfj.c(j, j2);
                if (c == null) {
                    return null;
                }
                map.put(1000, c);
                return null;
            }
        };
    }

    private Callable<Void> d(final long j, final long j2, final Map<Integer, List<HiHealthData>> map) {
        return new Callable<Void>() { // from class: mex.1
            @Override // java.util.concurrent.Callable
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public Void call() {
                List<HiHealthData> a2 = mfj.a(j, j2);
                if (a2 == null) {
                    return null;
                }
                map.put(1001, a2);
                return null;
            }
        };
    }

    private Callable<Void> c(final long j, final long j2, final int i, final int i2, final Map<Integer, List<HiHealthData>> map) {
        return new Callable<Void>() { // from class: mex.4
            @Override // java.util.concurrent.Callable
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public Void call() {
                List<HiHealthData> b = mfj.b(j, j2, i);
                if (b == null) {
                    return null;
                }
                map.put(Integer.valueOf(i2), b);
                return null;
            }
        };
    }

    private void e(long j, long j2, int i, Map<Integer, List<HiHealthData>> map, ArrayList<AnnualReportFitnessRaw>[] arrayListArr) {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        mfj.d((Task<Void>) Tasks.callInBackground(d(j, j2, i, map)), countDownLatch);
        mfj.d((Task<Void>) Tasks.callInBackground(e(j, j2, arrayListArr)), countDownLatch);
        mfj.d((Task<Void>) Tasks.callInBackground(a(j, j2, map)), countDownLatch);
        mfj.d((Task<Void>) Tasks.callInBackground(d(j, j2, map)), countDownLatch);
        mfj.d((Task<Void>) Tasks.callInBackground(c(j, j2, 2147483646, 1004, map)), countDownLatch);
        mfj.d((Task<Void>) Tasks.callInBackground(c(j, j2, Integer.MAX_VALUE, 1003, map)), countDownLatch);
        mfj.e(countDownLatch, 16000L, "getDataFromDb");
    }
}
