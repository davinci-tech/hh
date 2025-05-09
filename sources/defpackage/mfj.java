package defpackage;

import android.content.Context;
import com.google.gson.JsonSyntaxException;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.h5pro.utils.GeneralUtil;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hmf.tasks.OnCompleteListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.impl.AchieveCallback;
import com.huawei.pluginachievement.manager.model.MedalLocation;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.pluginachievement.report.bean.AnnualReportFitnessRaw;
import com.huawei.pluginachievement.report.bean.ReportClimbData;
import com.huawei.pluginachievement.report.bean.ReportCrossTrainerData;
import com.huawei.pluginachievement.report.bean.ReportDataBean;
import com.huawei.pluginachievement.report.bean.ReportFitnessData;
import com.huawei.pluginachievement.report.bean.ReportHikingData;
import com.huawei.pluginachievement.report.bean.ReportOtherData;
import com.huawei.pluginachievement.report.bean.ReportRideData;
import com.huawei.pluginachievement.report.bean.ReportRowData;
import com.huawei.pluginachievement.report.bean.ReportRunData;
import com.huawei.pluginachievement.report.bean.ReportSwimData;
import com.huawei.pluginachievement.report.bean.ReportTrailRunData;
import com.huawei.pluginachievement.report.bean.ReportWalkData;
import com.huawei.pluginfitnessadvice.Attribute;
import com.huawei.pluginfitnessadvice.Classify;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.Workout;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public class mfj {

    /* renamed from: a, reason: collision with root package name */
    private static final ArrayList<Integer> f14950a = new ArrayList<Integer>() { // from class: mfj.3
        {
            add(258);
            add(264);
            add(262);
            add(Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_OPEN_AREA_SWIM));
            add(Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_CROSS_COUNTRY_RACE));
            add(257);
            add(281);
            add(260);
            add(Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_CROSS_TRAINER));
            add(Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_ROW_MACHINE));
            add(282);
            add(259);
            add(10001);
        }
    };
    private static final ArrayList<Integer> d = new ArrayList<Integer>() { // from class: mfj.7
        {
            add(259);
        }
    };
    private static final ArrayList<Integer> e = new ArrayList<Integer>() { // from class: mfj.6
        {
            add(258);
            add(264);
        }
    };

    public static ArrayList<Integer> e() {
        return f14950a;
    }

    private static void c(ReportRunData reportRunData, long j, long j2) {
        d(reportRunData, j, j2);
    }

    private static int e(List<koh> list) {
        int i = Integer.MAX_VALUE;
        for (koh kohVar : list) {
            if (kohVar.a() > 0) {
                i = Math.min(i, kohVar.a());
            }
        }
        return i;
    }

    private static void d(ReportRideData reportRideData, long j, long j2) {
        d((ReportDataBean) reportRideData, j, j2);
    }

    private static void b(ReportRunData reportRunData, HiTrackMetaData hiTrackMetaData) {
        e(b(hiTrackMetaData.getPaceMap()), reportRunData, hiTrackMetaData);
        Map<Double, Double> partTimeMap = hiTrackMetaData.getPartTimeMap();
        if (partTimeMap == null) {
            return;
        }
        if (partTimeMap.get(Double.valueOf(21.0975d)) != null) {
            reportRunData.setHalfMarathonMinTime(Math.min(reportRunData.getHalfMarathonMinTime(), partTimeMap.get(Double.valueOf(21.0975d)).doubleValue()));
            reportRunData.setHalfMarathonCount(reportRunData.getHalfMarathonCount() + 1);
        }
        if (partTimeMap.get(Double.valueOf(42.195d)) != null) {
            reportRunData.setFullMarathonMinTime(Math.min(reportRunData.getFullMarathonMinTime(), partTimeMap.get(Double.valueOf(42.195d)).doubleValue()));
            reportRunData.setFullMarathonCount(reportRunData.getFullMarathonCount() + 1);
        }
    }

    private static ArrayList<Map.Entry<Integer, Float>> b(Map<Integer, Float> map) {
        if (map == null || map.size() <= 0) {
            return null;
        }
        ArrayList<Map.Entry<Integer, Float>> arrayList = new ArrayList<>(map.entrySet().size());
        Iterator<Map.Entry<Integer, Float>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        Collections.sort(arrayList, new Comparator<Map.Entry<Integer, Float>>() { // from class: mfj.15
            @Override // java.util.Comparator
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public int compare(Map.Entry<Integer, Float> entry, Map.Entry<Integer, Float> entry2) {
                return entry.getKey().intValue() - entry2.getKey().intValue();
            }
        });
        return arrayList;
    }

    private static void e(ArrayList<Map.Entry<Integer, Float>> arrayList, ReportRunData reportRunData, HiTrackMetaData hiTrackMetaData) {
        if (koq.b(arrayList)) {
            return;
        }
        int totalDistance = hiTrackMetaData.getTotalDistance() / 1000;
        int i = 0;
        int i2 = 0;
        while (i < arrayList.size() && i < totalDistance) {
            i2 += Math.round(arrayList.get(i).getValue().floatValue());
            i++;
            if (i == 3) {
                reportRunData.setThreeKmMinTime(Math.min(reportRunData.getThreeKmMinTime(), i2));
            }
            if (i == 5) {
                reportRunData.setFiveKmMinTime(Math.min(reportRunData.getFiveKmMinTime(), i2));
            }
            if (i == 10) {
                reportRunData.setTenKmMinTime(Math.min(reportRunData.getTenKmMinTime(), i2));
            }
        }
    }

    private static void c(ArrayList<Map.Entry<Integer, Float>> arrayList, ReportRunData reportRunData, HiTrackMetaData hiTrackMetaData) {
        if (koq.b(arrayList)) {
            return;
        }
        long round = Math.round(UnitUtil.e(hiTrackMetaData.getTotalDistance() / 1000.0d, 3));
        int i = 0;
        int i2 = 0;
        while (i < arrayList.size() && i < round) {
            i2 += Math.round(arrayList.get(i).getValue().floatValue());
            i++;
            if (i == 3) {
                reportRunData.setBritishThreeKmMinTime(Math.min(reportRunData.getBritishThreeKmMinTime(), i2));
            }
            if (i == 5) {
                reportRunData.setBritishFiveKmMinTime(Math.min(reportRunData.getBritishFiveKmMinTime(), i2));
            }
            if (i == 10) {
                reportRunData.setBritishTenKmMinTime(Math.min(reportRunData.getBritishTenKmMinTime(), i2));
            }
        }
    }

    private static void d(ReportRunData reportRunData, HiTrackMetaData hiTrackMetaData) {
        c(b(hiTrackMetaData.getBritishPaceMap()), reportRunData, hiTrackMetaData);
        Map<Double, Double> britishPartTimeMap = hiTrackMetaData.getBritishPartTimeMap();
        if (britishPartTimeMap == null) {
            return;
        }
        if (britishPartTimeMap.get(Double.valueOf(21.0975d)) != null) {
            reportRunData.setBritishHalfMarathonMinTime(Math.min(reportRunData.getBritishHalfMarathonMinTime(), britishPartTimeMap.get(Double.valueOf(21.0975d)).doubleValue()));
            reportRunData.setBritishHalfMarathonCount(reportRunData.getBritishHalfMarathonCount() + 1);
        }
        if (britishPartTimeMap.get(Double.valueOf(42.195d)) != null) {
            reportRunData.setBritishFullMarathonMinTime(Math.min(reportRunData.getBritishFullMarathonMinTime(), britishPartTimeMap.get(Double.valueOf(42.195d)).doubleValue()));
            reportRunData.setBritishFullMarathonCount(reportRunData.getBritishFullMarathonCount() + 1);
        }
    }

    public static HiTrackMetaData b(HiHealthData hiHealthData) {
        HiTrackMetaData hiTrackMetaData;
        try {
            hiTrackMetaData = (HiTrackMetaData) HiJsonUtil.e(hiHealthData.getMetaData(), HiTrackMetaData.class);
        } catch (JsonSyntaxException unused) {
            LogUtil.b("PLGACHIEVE_AchieveReportHelper", "trackMetaData is error");
            hiTrackMetaData = null;
        }
        if (hiTrackMetaData == null || (hiTrackMetaData.getAbnormalTrack() == 0 && hiTrackMetaData.getDuplicated() == 0)) {
            return hiTrackMetaData;
        }
        return null;
    }

    public static void e(int i, Map<Integer, ReportDataBean> map, HiTrackMetaData hiTrackMetaData, long j, long j2) {
        switch (i) {
            case 2147483646:
                e(map, hiTrackMetaData, j, j2);
                break;
            case Integer.MAX_VALUE:
                int sportType = hiTrackMetaData.getSportType();
                if (!f14950a.contains(Integer.valueOf(sportType))) {
                    ReportDataBean a2 = a(map, sportType);
                    if (a2 instanceof ReportOtherData) {
                        d((ReportOtherData) a2, hiTrackMetaData);
                        break;
                    }
                }
                break;
        }
    }

    private static void e(Map<Integer, ReportDataBean> map, HiTrackMetaData hiTrackMetaData, long j, long j2) {
        int sportType = hiTrackMetaData.getSportType();
        if (sportType != 258) {
            if (sportType == 260) {
                ReportDataBean a2 = a(map, 260);
                if (a2 instanceof ReportClimbData) {
                    d((ReportClimbData) a2, hiTrackMetaData);
                    return;
                }
                return;
            }
            if (sportType != 264) {
                if (sportType == 273) {
                    ReportDataBean a3 = a(map, OldToNewMotionPath.SPORT_TYPE_CROSS_TRAINER);
                    if (a3 instanceof ReportCrossTrainerData) {
                        a((ReportCrossTrainerData) a3, hiTrackMetaData);
                        return;
                    }
                    return;
                }
                if (sportType == 274) {
                    ReportDataBean a4 = a(map, OldToNewMotionPath.SPORT_TYPE_ROW_MACHINE);
                    if (a4 instanceof ReportRowData) {
                        b((ReportRowData) a4, hiTrackMetaData, j, j2);
                        return;
                    }
                    return;
                }
                b(map, hiTrackMetaData, j, j2);
                return;
            }
        }
        ReportDataBean a5 = a(map, 258);
        if (a5 instanceof ReportRunData) {
            b((ReportRunData) a5, hiTrackMetaData, j, j2);
        }
    }

    private static void b(Map<Integer, ReportDataBean> map, HiTrackMetaData hiTrackMetaData, long j, long j2) {
        int sportType = hiTrackMetaData.getSportType();
        if (sportType != 257) {
            if (sportType == 259) {
                ReportDataBean a2 = a(map, 259);
                if (a2 instanceof ReportRideData) {
                    b((ReportRideData) a2, hiTrackMetaData, j, j2);
                    return;
                }
                return;
            }
            if (sportType == 262 || sportType == 266) {
                ReportDataBean a3 = a(map, 262);
                if (a3 instanceof ReportSwimData) {
                    e((ReportSwimData) a3, j, j2);
                    return;
                }
                return;
            }
            switch (sportType) {
                case OldToNewMotionPath.SPORT_TYPE_CROSS_COUNTRY_RACE /* 280 */:
                    ReportDataBean a4 = a(map, OldToNewMotionPath.SPORT_TYPE_CROSS_COUNTRY_RACE);
                    if (a4 instanceof ReportTrailRunData) {
                        e((ReportTrailRunData) a4, hiTrackMetaData);
                        break;
                    }
                    break;
                case 282:
                    ReportDataBean a5 = a(map, 282);
                    if (a5 instanceof ReportHikingData) {
                        d((ReportHikingData) a5, hiTrackMetaData);
                        break;
                    }
                    break;
            }
        }
        ReportDataBean a6 = a(map, 257);
        if (a6 instanceof ReportWalkData) {
            a((ReportWalkData) a6, hiTrackMetaData);
        }
    }

    private static void b(ReportRideData reportRideData, HiTrackMetaData hiTrackMetaData, long j, long j2) {
        reportRideData.setSumDescent(reportRideData.getSumDescent() + hiTrackMetaData.getTotalDescent());
        d(reportRideData, j, j2);
    }

    private static void b(ReportRunData reportRunData, HiTrackMetaData hiTrackMetaData, long j, long j2) {
        reportRunData.setSumDistance(reportRunData.getSumDistance() + hiTrackMetaData.getTotalDistance());
        reportRunData.setMaxDistance(Math.max(reportRunData.getMaxDistance(), hiTrackMetaData.getTotalDistance()));
        reportRunData.setSumTime(reportRunData.getSumTime() + hiTrackMetaData.getTotalTime());
        reportRunData.setSportCount(reportRunData.getSportCount() + 1);
        b(reportRunData, hiTrackMetaData);
        d(reportRunData, hiTrackMetaData);
        c(reportRunData, j, j2);
    }

    private static void d(ReportHikingData reportHikingData, HiTrackMetaData hiTrackMetaData) {
        reportHikingData.setSportCount(reportHikingData.getSportCount() + 1);
        reportHikingData.setSumCalorie(reportHikingData.getSumCalorie() + hiTrackMetaData.getTotalCalories());
        reportHikingData.setMaxDistance(Math.max(reportHikingData.getMaxDistance(), hiTrackMetaData.getTotalDistance()));
        reportHikingData.setSumDistance(reportHikingData.getSumDistance() + hiTrackMetaData.getTotalDistance());
        reportHikingData.setSumTime(reportHikingData.getSumTime() + hiTrackMetaData.getTotalTime());
    }

    private static void a(ReportWalkData reportWalkData, HiTrackMetaData hiTrackMetaData) {
        reportWalkData.setSportCount(reportWalkData.getSportCount() + 1);
        reportWalkData.setMaxDistance(Math.max(reportWalkData.getMaxDistance(), hiTrackMetaData.getTotalDistance()));
        reportWalkData.setSumDistance(reportWalkData.getSumDistance() + hiTrackMetaData.getTotalDistance());
        reportWalkData.setSumTime(reportWalkData.getSumTime() + hiTrackMetaData.getTotalTime());
        if (hiTrackMetaData.getBestStepRate() <= 300 && hiTrackMetaData.getAvgStepRate() != 0 && hiTrackMetaData.getAvgStepRate() <= hiTrackMetaData.getBestStepRate()) {
            reportWalkData.setMaxStepRate(Math.max(reportWalkData.getMaxStepRate(), hiTrackMetaData.getBestStepRate()));
        }
    }

    private static void d(ReportOtherData reportOtherData, HiTrackMetaData hiTrackMetaData) {
        reportOtherData.setSportCount(reportOtherData.getSportCount() + 1);
        reportOtherData.setSumCalorie(reportOtherData.getSumCalorie() + hiTrackMetaData.getTotalCalories());
        reportOtherData.setSumTime(reportOtherData.getSumTime() + hiTrackMetaData.getTotalTime());
        reportOtherData.setMaxTime(Math.max(reportOtherData.getMaxTime(), hiTrackMetaData.getTotalTime()));
    }

    private static void e(ReportTrailRunData reportTrailRunData, HiTrackMetaData hiTrackMetaData) {
        reportTrailRunData.setSumDistance(reportTrailRunData.getSumDistance() + hiTrackMetaData.getTotalDistance());
        reportTrailRunData.setMaxDistance(Math.max(reportTrailRunData.getMaxDistance(), hiTrackMetaData.getTotalDistance()));
        reportTrailRunData.setSumTime(reportTrailRunData.getSumTime() + hiTrackMetaData.getTotalTime());
        reportTrailRunData.setSportCount(reportTrailRunData.getSportCount() + 1);
        reportTrailRunData.setSumCalorie(reportTrailRunData.getSumCalorie() + hiTrackMetaData.getTotalCalories());
    }

    private static void e(ReportSwimData reportSwimData, long j, long j2) {
        d(reportSwimData, j, j2);
    }

    private static int d(List<kog> list) {
        return ((kog) Collections.max(list, new Comparator() { // from class: mfh
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return mfj.c((kog) obj, (kog) obj2);
            }
        })).d();
    }

    static /* synthetic */ int c(kog kogVar, kog kogVar2) {
        if (kogVar.d() - kogVar2.d() < 0) {
            return -1;
        }
        return kogVar.d() - kogVar2.d() == 0 ? 0 : 1;
    }

    private static int c(List<kol> list) {
        int i = Integer.MAX_VALUE;
        for (kol kolVar : list) {
            if (kolVar.c() > 0) {
                i = Math.min(i, kolVar.c());
            }
        }
        return i;
    }

    private static void b(ReportRowData reportRowData, HiTrackMetaData hiTrackMetaData, long j, long j2) {
        reportRowData.setSportCount(reportRowData.getSportCount() + 1);
        reportRowData.setSumCalorie(reportRowData.getSumCalorie() + hiTrackMetaData.getTotalCalories());
        reportRowData.setSumTime(reportRowData.getSumTime() + hiTrackMetaData.getTotalTime());
        reportRowData.setMaxTime(Math.max(reportRowData.getMaxTime(), hiTrackMetaData.getTotalTime()));
        d(reportRowData, j, j2);
    }

    private static void d(final ReportDataBean reportDataBean, final long j, final long j2) {
        reportDataBean.getTaskList().add(new Callable<Void>() { // from class: mfj.11
            @Override // java.util.concurrent.Callable
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public Void call() {
                mfj.c(ReportDataBean.this, j, j2);
                return null;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(final ReportDataBean reportDataBean, long j, long j2) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        hps.a(j, j2, new IBaseResponseCallback() { // from class: mfj.14
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (i == 0 && obj != null) {
                    MotionPath e2 = mfj.e(obj, (List<Integer>) mfj.d(ReportDataBean.this), ReportDataBean.this);
                    if (e2 != null) {
                        mfj.d(ReportDataBean.this, e2);
                    } else {
                        LogUtil.b("PLGACHIEVE_AchieveReportHelper", "getTrackDetailData motion path is null");
                    }
                } else {
                    LogUtil.b("PLGACHIEVE_AchieveReportHelper", "getTrackDetailData Not return valid data");
                }
                countDownLatch.countDown();
            }
        });
        e(countDownLatch, 8000L, "getTrackDetailData");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(ReportDataBean reportDataBean, MotionPath motionPath) {
        if (reportDataBean instanceof ReportRowData) {
            ReportRowData reportRowData = (ReportRowData) reportDataBean;
            List<knw> requestPaddleFrequencyList = motionPath.requestPaddleFrequencyList();
            if (koq.b(requestPaddleFrequencyList)) {
                return;
            }
            reportRowData.setMaxPaddle(Math.max(reportRowData.getMaxPaddle(), b(requestPaddleFrequencyList)));
            return;
        }
        if (reportDataBean instanceof ReportRunData) {
            ReportRunData reportRunData = (ReportRunData) reportDataBean;
            Map<Integer, Float> localePaceMap = motionPath.localePaceMap();
            if (localePaceMap == null) {
                d(reportRunData, motionPath);
                return;
            } else {
                d(reportRunData, localePaceMap, motionPath);
                return;
            }
        }
        if (reportDataBean instanceof ReportSwimData) {
            ReportSwimData reportSwimData = (ReportSwimData) reportDataBean;
            List<kol> requestSwolfList = motionPath.requestSwolfList();
            if (!koq.b(requestSwolfList)) {
                reportSwimData.setBestSwolf(Math.min(reportSwimData.getBestSwolf(), c(requestSwolfList)));
            }
            List<kog> requestPullFreqList = motionPath.requestPullFreqList();
            if (koq.b(requestPullFreqList)) {
                return;
            }
            reportSwimData.setBestFreq(Math.max(reportSwimData.getBestFreq(), d(requestPullFreqList)));
        }
    }

    private static void d(ReportRunData reportRunData, MotionPath motionPath) {
        if (koq.b(motionPath.requestRealTimePaceList())) {
            return;
        }
        reportRunData.setBestPace(Math.min(reportRunData.getBestPace(), e(r3)));
        reportRunData.setBritishBestPace(Math.round(UnitUtil.d(reportRunData.getBestPace(), 3)));
    }

    private static void d(ReportRunData reportRunData, Map<Integer, Float> map, MotionPath motionPath) {
        float floatValue = gvv.e(gvv.a(map))[0].floatValue();
        if (floatValue == 0.0f) {
            d(reportRunData, motionPath);
        } else {
            reportRunData.setBestPace(Math.min(reportRunData.getBestPace(), floatValue));
            reportRunData.setBritishBestPace(Math.round(UnitUtil.d(reportRunData.getBestPace(), 3)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<Integer> d(ReportDataBean reportDataBean) {
        if (reportDataBean instanceof ReportRowData) {
            return new ArrayList<Integer>() { // from class: mfj.13
                {
                    add(Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_ROW_MACHINE));
                }
            };
        }
        if (reportDataBean instanceof ReportRunData) {
            return e;
        }
        if (reportDataBean instanceof ReportRideData) {
            return d;
        }
        if (reportDataBean instanceof ReportSwimData) {
            return new ArrayList<Integer>() { // from class: mfj.12
                {
                    add(262);
                    add(Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_OPEN_AREA_SWIM));
                }
            };
        }
        return new ArrayList(0);
    }

    private static float b(List<knw> list) {
        return ((knw) Collections.max(list, new Comparator() { // from class: mfk
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return mfj.c((knw) obj, (knw) obj2);
            }
        })).b();
    }

    static /* synthetic */ int c(knw knwVar, knw knwVar2) {
        if (knwVar.b() - knwVar2.b() < 0.0f) {
            return -1;
        }
        return knwVar.b() - knwVar2.b() == 0.0f ? 0 : 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static <T> MotionPath e(T t, List<Integer> list, ReportDataBean reportDataBean) {
        if (!(t instanceof knu)) {
            LogUtil.b("PLGACHIEVE_AchieveReportHelper", "object Data is not fit path data ");
            return null;
        }
        knu knuVar = (knu) t;
        MotionPathSimplify b = knuVar.b();
        if (b.requestAbnormalTrack() != 0 || b.requestDuplicated() != 0 || !list.contains(Integer.valueOf(b.requestSportType()))) {
            return null;
        }
        d(reportDataBean, b);
        String str = BaseApplication.getContext().getFilesDir().getPath() + File.separator + knuVar.d();
        if (!GeneralUtil.isSafePath(str)) {
            LogUtil.h("PLGACHIEVE_AchieveReportHelper", "getMotionPath: untrusted -> " + str);
            return null;
        }
        if (new File(str).exists()) {
            return gwk.c(BaseApplication.getContext(), knuVar.d());
        }
        LogUtil.b("PLGACHIEVE_AchieveReportHelper", "file not exist");
        return null;
    }

    private static void d(ReportDataBean reportDataBean, MotionPathSimplify motionPathSimplify) {
        if (reportDataBean instanceof ReportRowData) {
            ReportRowData reportRowData = (ReportRowData) reportDataBean;
            reportRowData.setMaxPaddleTimes(Math.max(reportRowData.getMaxPaddleTimes(), motionPathSimplify.requestTotalSteps()));
            return;
        }
        if (reportDataBean instanceof ReportSwimData) {
            ReportSwimData reportSwimData = (ReportSwimData) reportDataBean;
            Integer num = motionPathSimplify.requestSportData().get("swim_stroke");
            if (num != null) {
                if (reportSwimData.getStroke().containsKey(num)) {
                    reportSwimData.getStroke().put(num, Integer.valueOf(reportSwimData.getStroke().get(num).intValue() + 1));
                    return;
                } else {
                    reportSwimData.getStroke().put(num, 1);
                    return;
                }
            }
            return;
        }
        if (reportDataBean instanceof ReportRideData) {
            ReportRideData reportRideData = (ReportRideData) reportDataBean;
            float requestBestPace = motionPathSimplify.requestBestPace();
            if (Float.compare(requestBestPace, 0.0f) <= 0) {
                return;
            }
            if (reportRideData.getBestPace() < 1.0E-6d) {
                reportRideData.setBestPace(requestBestPace);
            } else {
                reportRideData.setBestPace(Math.min(reportRideData.getBestPace(), requestBestPace));
            }
            LogUtil.a("PLGACHIEVE_AchieveReportHelper", "reportRideData getBestPace() = ", Double.valueOf(reportRideData.getBestPace()));
        }
    }

    private static void a(ReportCrossTrainerData reportCrossTrainerData, HiTrackMetaData hiTrackMetaData) {
        reportCrossTrainerData.setSumTime(reportCrossTrainerData.getSumTime() + hiTrackMetaData.getTotalTime());
        reportCrossTrainerData.setMaxTime(Math.max(reportCrossTrainerData.getMaxTime(), hiTrackMetaData.getTotalTime()));
        reportCrossTrainerData.setSportCount(reportCrossTrainerData.getSportCount() + 1);
        reportCrossTrainerData.setSumCalorie(reportCrossTrainerData.getSumCalorie() + hiTrackMetaData.getTotalCalories());
    }

    private static void d(ReportClimbData reportClimbData, HiTrackMetaData hiTrackMetaData) {
        reportClimbData.setSumDistance(reportClimbData.getSumDistance() + hiTrackMetaData.getTotalDistance());
        reportClimbData.setSumTime(reportClimbData.getSumTime() + hiTrackMetaData.getTotalTime());
        reportClimbData.setMaxHeight(Math.max(hiTrackMetaData.getMaxAlti(), reportClimbData.getMaxHeight()));
        reportClimbData.setSumHeight(reportClimbData.getSumHeight() + hiTrackMetaData.getCreepingWave());
        reportClimbData.setSportCount(reportClimbData.getSportCount() + 1);
    }

    public static void b(Map<Integer, ReportDataBean> map, HiHealthData hiHealthData) {
        ReportDataBean a2 = a(map, 259);
        if (a2 instanceof ReportRideData) {
            ReportRideData reportRideData = (ReportRideData) a2;
            reportRideData.setMaxDistance(Math.max(reportRideData.getMaxDistance(), hiHealthData.getInt("Track_Ride_Longest_Distance")));
        }
        ReportDataBean a3 = a(map, 262);
        if (a3 instanceof ReportSwimData) {
            ReportSwimData reportSwimData = (ReportSwimData) a3;
            reportSwimData.setMaxDistance(Math.max(reportSwimData.getMaxDistance(), hiHealthData.getInt("Track_Swim_Longest_Distance")));
        }
    }

    public static ReportDataBean a(Map<Integer, ReportDataBean> map, int i) {
        ReportDataBean reportDataBean = map.get(Integer.valueOf(i));
        if (reportDataBean == null) {
            if (i == 262) {
                reportDataBean = new ReportSwimData(0);
            } else if (i == 280) {
                reportDataBean = new ReportTrailRunData(0);
            } else if (i == 282) {
                reportDataBean = new ReportHikingData(0);
            } else if (i == 10001) {
                reportDataBean = new ReportFitnessData(0);
            } else if (i == 273) {
                reportDataBean = new ReportCrossTrainerData(0);
            } else if (i != 274) {
                switch (i) {
                    case 257:
                        reportDataBean = new ReportWalkData(0);
                        break;
                    case 258:
                        reportDataBean = new ReportRunData(0);
                        break;
                    case 259:
                        reportDataBean = new ReportRideData(0);
                        break;
                    case 260:
                        reportDataBean = new ReportClimbData(0);
                        break;
                    default:
                        reportDataBean = new ReportOtherData(i, 0);
                        break;
                }
            } else {
                reportDataBean = new ReportRowData(0);
            }
            map.put(Integer.valueOf(i), reportDataBean);
        }
        return reportDataBean;
    }

    public static void a(HashMap<String, Integer> hashMap, int i, int i2) {
        hashMap.put("type", Integer.valueOf(i));
        hashMap.put("sumDuration", 0);
        hashMap.put("sumDis", 0);
        hashMap.put("sumCount", 0);
        hashMap.put("sumSteps", 0);
        hashMap.put("avgSteps", 0);
        hashMap.put(ParsedFieldTag.MAX_STEPS, Integer.valueOf(i2));
        hashMap.put("haveStepDays", 0);
    }

    public static void e(HashMap<String, Integer> hashMap, HiHealthData hiHealthData) {
        int intValue = hashMap.get("sumDuration").intValue();
        int intValue2 = hashMap.get("sumDis").intValue();
        int intValue3 = hashMap.get("sumCount").intValue();
        int i = hiHealthData.getInt("Track_Run_Duration_Sum");
        int i2 = hiHealthData.getInt("Track_Run_Distance_Sum");
        int i3 = hiHealthData.getInt("Track_Run_Count_Sum");
        int i4 = hiHealthData.getInt("Track_Run_Abnormal_Count_Sum");
        int intValue4 = hashMap.get(ParsedFieldTag.MAX_STEPS).intValue();
        if (hiHealthData.getInt("sport_walk_step_sum") > intValue4) {
            intValue4 = hiHealthData.getInt("sport_walk_step_sum");
        }
        int intValue5 = hashMap.get("sumSteps").intValue();
        int i5 = hiHealthData.getInt("sport_walk_step_sum");
        int intValue6 = hashMap.get("haveStepDays").intValue();
        if (hiHealthData.getInt("sport_walk_step_sum") != 0) {
            intValue6++;
        }
        hashMap.put("sumDuration", Integer.valueOf(intValue + i));
        hashMap.put("sumDis", Integer.valueOf(intValue2 + i2));
        hashMap.put("sumCount", Integer.valueOf(intValue3 + (i3 - i4)));
        hashMap.put("sumSteps", Integer.valueOf(intValue5 + i5));
        hashMap.put(ParsedFieldTag.MAX_STEPS, Integer.valueOf(intValue4));
        hashMap.put("haveStepDays", Integer.valueOf(intValue6));
    }

    public static void c(Map<Integer, ReportDataBean> map, HiHealthData hiHealthData) {
        a(map, hiHealthData);
        e(map, hiHealthData);
        j(map, hiHealthData);
        d(map, hiHealthData);
        f(map, hiHealthData);
    }

    public static float c(HiHealthData hiHealthData) {
        float f = hiHealthData.getFloat("sport_run_calorie_sum");
        float f2 = hiHealthData.getFloat("sport_cycle_calorie_sum");
        float f3 = hiHealthData.getFloat("sport_climb_calorie_sum");
        return Math.max(f + f2 + f3 + hiHealthData.getFloat("sport_walk_calorie_sum"), hiHealthData.getFloat("sport_calorie_sum"));
    }

    private static void a(Map<Integer, ReportDataBean> map, HiHealthData hiHealthData) {
        ReportDataBean a2 = a(map, 258);
        if (a2 instanceof ReportRunData) {
            ReportRunData reportRunData = (ReportRunData) a2;
            reportRunData.setSumCalorie(reportRunData.getSumCalorie() + hiHealthData.getFloat("sport_run_calorie_sum"));
        }
    }

    private static void e(Map<Integer, ReportDataBean> map, HiHealthData hiHealthData) {
        ReportDataBean a2 = a(map, 259);
        if (a2 instanceof ReportRideData) {
            ReportRideData reportRideData = (ReportRideData) a2;
            reportRideData.setSumDistance(reportRideData.getSumDistance() + hiHealthData.getInt("Track_Ride_Distance_Sum"));
            reportRideData.setSumTime(reportRideData.getSumTime() + hiHealthData.getLong("Track_Ride_Duration_Sum"));
            reportRideData.setSumCalorie(reportRideData.getSumCalorie() + hiHealthData.getFloat("sport_cycle_calorie_sum"));
            reportRideData.setSportCount((reportRideData.getSportCount() + hiHealthData.getInt("Track_Ride_Count_Sum")) - hiHealthData.getInt("Track_Ride_Abnormal_Count_Sum"));
            reportRideData.setSumCreepingWave(reportRideData.getSumCreepingWave() + hiHealthData.getInt("Track_Ride_CreepingWave"));
        }
    }

    private static void j(Map<Integer, ReportDataBean> map, HiHealthData hiHealthData) {
        ReportDataBean a2 = a(map, 262);
        if (a2 instanceof ReportSwimData) {
            ReportSwimData reportSwimData = (ReportSwimData) a2;
            reportSwimData.setSumDistance(reportSwimData.getSumDistance() + hiHealthData.getInt("Track_Swim_Distance_Sum"));
            reportSwimData.setSumTime(reportSwimData.getSumTime() + hiHealthData.getLong("Track_Swim_Duration_Sum"));
            reportSwimData.setSportCount((reportSwimData.getSportCount() + hiHealthData.getInt("Track_Swim_Count_Sum")) - hiHealthData.getInt("Track_Swim_Abnormal_Count_Sum"));
        }
    }

    private static void d(Map<Integer, ReportDataBean> map, HiHealthData hiHealthData) {
        ReportDataBean a2 = a(map, 260);
        if (a2 instanceof ReportClimbData) {
            ReportClimbData reportClimbData = (ReportClimbData) a2;
            reportClimbData.setSumCalorie(reportClimbData.getSumCalorie() + hiHealthData.getFloat("sport_climb_calorie_sum"));
        }
    }

    private static void f(Map<Integer, ReportDataBean> map, HiHealthData hiHealthData) {
        ReportDataBean a2 = a(map, 257);
        if (a2 instanceof ReportWalkData) {
            ReportWalkData reportWalkData = (ReportWalkData) a2;
            reportWalkData.setSumCalorie(reportWalkData.getSumCalorie() + hiHealthData.getFloat("sport_walk_calorie_sum"));
        }
    }

    public static void e(final ReportFitnessData reportFitnessData, final AnnualReportFitnessRaw annualReportFitnessRaw) {
        reportFitnessData.getTaskList().add(new Callable<Void>() { // from class: mfj.17
            @Override // java.util.concurrent.Callable
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Void call() {
                mfj.d(ReportFitnessData.this, annualReportFitnessRaw);
                return null;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(ReportFitnessData reportFitnessData, AnnualReportFitnessRaw annualReportFitnessRaw) {
        List[] listArr = {null};
        if (a(annualReportFitnessRaw, (List<Attribute>[]) listArr)) {
            if (!koq.b(listArr[0])) {
                for (Attribute attribute : listArr[0]) {
                    Integer num = reportFitnessData.getTrainingPointsMap().get(attribute.getId());
                    if (num != null) {
                        reportFitnessData.getTrainingPointsMap().put(attribute.getId(), Integer.valueOf(num.intValue() + 1));
                    } else {
                        reportFitnessData.getTrainingPointsMap().put(attribute.getId(), 1);
                    }
                }
            }
            long acquireDuring = annualReportFitnessRaw.acquireDuring();
            reportFitnessData.setSumTime(reportFitnessData.getSumTime() + acquireDuring);
            reportFitnessData.setMaxTime(Math.max(reportFitnessData.getMaxTime(), acquireDuring));
            reportFitnessData.setSportCount(reportFitnessData.getSportCount() + 1);
            reportFitnessData.setSumCalorie(reportFitnessData.getSumCalorie() + annualReportFitnessRaw.acquireCalorie());
            List<Classify> primaryClassify = annualReportFitnessRaw.getPrimaryClassify();
            if (koq.b(primaryClassify)) {
                return;
            }
            String classifyName = primaryClassify.get(0).getClassifyName();
            if (primaryClassify.get(0).getClassifyId() == 14617 && primaryClassify.size() > 1) {
                classifyName = primaryClassify.get(1).getClassifyName();
            }
            Integer num2 = reportFitnessData.getFirstLevelMap().get(classifyName);
            if (num2 == null) {
                reportFitnessData.getFirstLevelMap().put(classifyName, 1);
            } else {
                reportFitnessData.getFirstLevelMap().put(classifyName, Integer.valueOf(num2.intValue() + 1));
            }
            String acquireWorkoutName = annualReportFitnessRaw.acquireWorkoutName();
            Integer num3 = reportFitnessData.getCourseMap().get(acquireWorkoutName);
            if (num3 == null) {
                reportFitnessData.getCourseMap().put(acquireWorkoutName, 1);
            } else {
                reportFitnessData.getCourseMap().put(acquireWorkoutName, Integer.valueOf(num3.intValue() + 1));
            }
        }
    }

    private static boolean a(AnnualReportFitnessRaw annualReportFitnessRaw, final List<Attribute>[] listArr) {
        String acquireWorkoutId = annualReportFitnessRaw.acquireWorkoutId();
        String acquireVersion = annualReportFitnessRaw.acquireVersion();
        final boolean[] zArr = {false};
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("PLGACHIEVE_AchieveReportHelper", "getTrainingPoints : courseApi is null.");
            return zArr[0];
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        courseApi.getCourseById(acquireWorkoutId, acquireVersion, null, new UiCallback<Workout>() { // from class: mfj.5
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                LogUtil.h("PLGACHIEVE_AchieveReportHelper", "getTrainingPoints : errorCode " + i, " errorInfo：" + str);
                listArr[0] = null;
                countDownLatch.countDown();
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onSuccess(Workout workout) {
                if (workout instanceof FitWorkout) {
                    FitWorkout fitWorkout = (FitWorkout) workout;
                    listArr[0] = fitWorkout.acquireTrainingpoints();
                    if (fitWorkout.getWorkoutType() == 1) {
                        zArr[0] = true;
                    }
                }
                countDownLatch.countDown();
            }
        });
        e(countDownLatch, 8000L, "getTrainingPoints");
        return zArr[0];
    }

    public static List<HiHealthData> c(long j, long j2, int i) {
        final List<HiHealthData>[] listArr = {null};
        Context context = BaseApplication.getContext();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        mcv.d(context).getAdapter().getSumData(context, j, j2, i, new AchieveCallback() { // from class: mfj.4
            @Override // com.huawei.pluginachievement.impl.AchieveCallback
            public void onResponse(int i2, Object obj) {
                if (obj == null) {
                    countDownLatch.countDown();
                    LogUtil.h("PLGACHIEVE_AchieveReportHelper", "getSumData obj  == null");
                } else {
                    try {
                        try {
                            listArr[0] = (List) obj;
                        } catch (ClassCastException unused) {
                            LogUtil.b("PLGACHIEVE_AchieveReportHelper", "getSumData data ClassCastException");
                        }
                    } finally {
                        countDownLatch.countDown();
                    }
                }
            }
        });
        e(countDownLatch, 8000L, "getSumData");
        return listArr[0];
    }

    private static List<HiHealthData> e(long j, long j2, int i) {
        final List<HiHealthData>[] listArr = {null};
        Context context = BaseApplication.getContext();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        mcv.d(context).getAdapter().getReportStatistics(context, j, j2, i, new AchieveCallback() { // from class: mfj.2
            @Override // com.huawei.pluginachievement.impl.AchieveCallback
            public void onResponse(int i2, Object obj) {
                if (obj == null) {
                    countDownLatch.countDown();
                    LogUtil.h("PLGACHIEVE_AchieveReportHelper", "getReportStatistics obj  == null");
                } else {
                    try {
                        try {
                            listArr[0] = (List) obj;
                        } catch (ClassCastException unused) {
                            LogUtil.b("PLGACHIEVE_AchieveReportHelper", "getReportDataSum");
                        }
                    } finally {
                        countDownLatch.countDown();
                    }
                }
            }
        });
        e(countDownLatch, 8000L, "getReportDataSum");
        return listArr[0];
    }

    public static List<HiHealthData> c(long j, long j2) {
        return e(j, j2, 1);
    }

    public static List<HiHealthData> a(long j, long j2) {
        return e(j, j2, 4);
    }

    public static List<HiHealthData> b(long j, long j2, final int i) {
        final List<HiHealthData>[] listArr = {null};
        Context context = BaseApplication.getContext();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        mcv.d(context).getAdapter().fetchSequenceDataBySportType(context, j, j2, i, new AchieveCallback() { // from class: mfj.1
            @Override // com.huawei.pluginachievement.impl.AchieveCallback
            public void onResponse(int i2, Object obj) {
                if (obj == null) {
                    countDownLatch.countDown();
                    LogUtil.h("PLGACHIEVE_AchieveReportHelper", "getSingleData obj  == null");
                } else {
                    try {
                        try {
                            listArr[0] = (List) obj;
                        } catch (ClassCastException unused) {
                            LogUtil.b("PLGACHIEVE_AchieveReportHelper", "fetchSequenceDataBySportType ", Integer.valueOf(i));
                        }
                    } finally {
                        countDownLatch.countDown();
                    }
                }
            }
        });
        e(countDownLatch, 8000L, "getSingleData" + i);
        return listArr[0];
    }

    public static ArrayList<AnnualReportFitnessRaw> b(long j, long j2) {
        final ArrayList<AnnualReportFitnessRaw>[] arrayListArr = {null};
        Context context = BaseApplication.getContext();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        mcv.d(context).getAdapter().getAnnualFitnessRecord(context, j, j2, new AchieveCallback() { // from class: mfj.10
            @Override // com.huawei.pluginachievement.impl.AchieveCallback
            public void onResponse(int i, Object obj) {
                if (obj == null) {
                    LogUtil.a("PLGACHIEVE_AchieveReportHelper", "fitnessRecords is null");
                    countDownLatch.countDown();
                } else {
                    if (obj instanceof ArrayList) {
                        arrayListArr[0] = (ArrayList) obj;
                    }
                    countDownLatch.countDown();
                }
            }
        });
        e(countDownLatch, 8000L, "getFitnessReportData");
        return arrayListArr[0];
    }

    public static void e(CountDownLatch countDownLatch, long j, String str) {
        try {
            if (countDownLatch.await(j, TimeUnit.MILLISECONDS)) {
                return;
            }
        } catch (InterruptedException unused) {
            LogUtil.b("PLGACHIEVE_AchieveReportHelper", "countDownLatch InterruptedException" + str);
        }
        LogUtil.h("PLGACHIEVE_AchieveReportHelper", str + " task is not finish!!");
    }

    public static void e(Map<String, Integer> map) {
        if (map.get("haveStepDays").intValue() != 0) {
            map.put("avgSteps", Integer.valueOf(Math.round((map.get("sumSteps").intValue() * 1.0f) / map.get("haveStepDays").intValue())));
        }
    }

    public static void c(Map<Integer, ReportDataBean> map) {
        if (map == null) {
            return;
        }
        Iterator<Map.Entry<Integer, ReportDataBean>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            ReportDataBean value = it.next().getValue();
            value.setSumTime(Math.round(value.getSumTime() / 1000.0d));
            value.setMaxTime(Math.round(value.getMaxTime() / 1000.0d));
        }
    }

    public static void d(Task<Void> task, final CountDownLatch countDownLatch) {
        task.addOnCompleteListener(new OnCompleteListener<Void>() { // from class: mfj.9
            @Override // com.huawei.hmf.tasks.OnCompleteListener
            public void onComplete(Task<Void> task2) {
                countDownLatch.countDown();
            }
        });
    }

    public static ArrayList<Map.Entry<String, Long>> e(long j, long j2) {
        HashMap hashMap = new HashMap(0);
        List<mcz> b = meh.c(BaseApplication.getContext()).b(8, new HashMap(2));
        if (koq.b(b)) {
            return new ArrayList<>(0);
        }
        for (mcz mczVar : b) {
            if (mczVar instanceof MedalLocation) {
                MedalLocation medalLocation = (MedalLocation) mczVar;
                if (medalLocation.acquireGainedCount() > 0) {
                    long h = nsn.h(medalLocation.acquireMedalGainedTime());
                    if (h >= j && h <= j2) {
                        hashMap.put(medalLocation.acquireMedalID(), Long.valueOf(h));
                    }
                }
            }
        }
        ArrayList<Map.Entry<String, Long>> arrayList = new ArrayList<>(0);
        if (hashMap.size() > 0) {
            Iterator it = hashMap.entrySet().iterator();
            while (it.hasNext()) {
                arrayList.add((Map.Entry) it.next());
            }
            Collections.sort(arrayList, new Comparator<Map.Entry<String, Long>>() { // from class: mfj.8
                @Override // java.util.Comparator
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public int compare(Map.Entry<String, Long> entry, Map.Entry<String, Long> entry2) {
                    return Long.compare(entry2.getValue().longValue(), entry.getValue().longValue());
                }
            });
        }
        return arrayList;
    }
}
