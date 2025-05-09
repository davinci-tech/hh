package defpackage;

import com.huawei.health.R;
import com.huawei.health.healthdatamgr.api.HealthDataMgrApi;
import com.huawei.health.sport.view.SugChart;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateThresholdConfig;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateZoneMgr;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartZoneConf;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import com.huawei.hwfoundationmodel.trackmodel.StepRateData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.view.trackview.TrackLineChartHolder;
import com.huawei.up.model.UserInfomation;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class ffw {

    /* renamed from: a, reason: collision with root package name */
    private static int f12493a;
    private static volatile HeartZoneConf d;

    private static void d(List<StepRateData> list, List<ffh> list2) {
        ffh ffhVar = (ffh) Collections.max(list2, new Comparator<ffh>() { // from class: ffw.4
            @Override // java.util.Comparator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public int compare(ffh ffhVar2, ffh ffhVar3) {
                return Float.compare(ffhVar2.c(), ffhVar3.c());
            }
        });
        ffh ffhVar2 = (ffh) Collections.min(list2, new Comparator<ffh>() { // from class: ffw.6
            @Override // java.util.Comparator
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public int compare(ffh ffhVar3, ffh ffhVar4) {
                return Float.compare(ffhVar3.c() < 0.0f ? Float.MAX_VALUE : ffhVar3.c(), ffhVar4.c() >= 0.0f ? ffhVar4.c() : Float.MAX_VALUE);
            }
        });
        StepRateData stepRateData = (StepRateData) Collections.min(list, new Comparator<StepRateData>() { // from class: ffw.10
            @Override // java.util.Comparator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public int compare(StepRateData stepRateData2, StepRateData stepRateData3) {
                return Integer.compare(ffw.e(stepRateData2), ffw.e(stepRateData3));
            }
        });
        StepRateData stepRateData2 = (StepRateData) Collections.max(list, new Comparator<StepRateData>() { // from class: ffw.11
            @Override // java.util.Comparator
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public int compare(StepRateData stepRateData3, StepRateData stepRateData4) {
                return Integer.compare(ffw.e(stepRateData3), ffw.e(stepRateData4));
            }
        });
        if (Float.compare(ffhVar2.c(), e(stepRateData)) != 0) {
            ffhVar2.c(e(stepRateData));
        }
        if (Float.compare(ffhVar.c(), e(stepRateData2)) != 0) {
            ffhVar.c(e(stepRateData2));
        }
    }

    public static int b(HeartRateData heartRateData) {
        if (heartRateData != null && heartRateData.acquireHeartRate() <= 220) {
            return heartRateData.acquireHeartRate();
        }
        return 0;
    }

    private static int b(Object obj) {
        if (obj instanceof StepRateData) {
            return e((StepRateData) obj);
        }
        return b((HeartRateData) obj);
    }

    public static int e(StepRateData stepRateData) {
        if (stepRateData == null) {
            return 0;
        }
        return stepRateData.acquireStepRate();
    }

    public static int b(List<HeartRateData> list) {
        if (koq.b(list)) {
            return 0;
        }
        return b((HeartRateData) Collections.max(list, new Comparator<HeartRateData>() { // from class: ffw.15
            @Override // java.util.Comparator
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public int compare(HeartRateData heartRateData, HeartRateData heartRateData2) {
                return Integer.compare(ffw.b(heartRateData), ffw.b(heartRateData2));
            }
        }));
    }

    public static int e(List<HeartRateData> list) {
        if (koq.b(list)) {
            return 0;
        }
        return b((HeartRateData) Collections.min(list, new Comparator<HeartRateData>() { // from class: ffw.14
            @Override // java.util.Comparator
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public int compare(HeartRateData heartRateData, HeartRateData heartRateData2) {
                return Integer.compare(ffw.a(heartRateData), ffw.a(heartRateData2));
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int a(HeartRateData heartRateData) {
        if (heartRateData == null) {
            return 0;
        }
        if (heartRateData.acquireHeartRate() <= 0) {
            return Integer.MAX_VALUE;
        }
        return heartRateData.acquireHeartRate();
    }

    public static double c(List<koi> list) {
        if (koq.b(list)) {
            return 0.0d;
        }
        return ((koi) Collections.max(list, new Comparator<koi>() { // from class: ffw.12
            @Override // java.util.Comparator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public int compare(koi koiVar, koi koiVar2) {
                return Double.compare(ffw.c(koiVar), ffw.c(koiVar2));
            }
        })).e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double c(koi koiVar) {
        if (koiVar == null) {
            return 0.0d;
        }
        return koiVar.e();
    }

    public static double l(List<koi> list) {
        if (koq.b(list)) {
            return 0.0d;
        }
        return ((koi) Collections.min(list, new Comparator<koi>() { // from class: ffw.13
            @Override // java.util.Comparator
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public int compare(koi koiVar, koi koiVar2) {
                return Double.compare(ffw.d(koiVar), ffw.d(koiVar2));
            }
        })).e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double d(koi koiVar) {
        if (koiVar == null) {
            return 0.0d;
        }
        if (koiVar.e() <= 0.0d) {
            return 2.147483647E9d;
        }
        return koiVar.e();
    }

    public static int a(List<koh> list) {
        if (koq.b(list)) {
            return 0;
        }
        return ((koh) Collections.min(list, new Comparator<koh>() { // from class: ffw.18
            @Override // java.util.Comparator
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public int compare(koh kohVar, koh kohVar2) {
                return Double.compare(ffw.a(kohVar), ffw.a(kohVar2));
            }
        })).a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double a(koh kohVar) {
        if (kohVar == null) {
            return 0.0d;
        }
        if (kohVar.a() <= 0) {
            return 2.147483647E9d;
        }
        return kohVar.a();
    }

    public static int f(List<ffs> list) {
        if (koq.b(list)) {
            return 0;
        }
        return ((ffs) Collections.max(list, new Comparator<ffs>() { // from class: ffw.3
            @Override // java.util.Comparator
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public int compare(ffs ffsVar, ffs ffsVar2) {
                return Integer.compare(ffw.g(ffsVar), ffw.g(ffsVar2));
            }
        })).e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int g(ffs ffsVar) {
        if (ffsVar == null) {
            return 0;
        }
        return ffsVar.e();
    }

    public static int k(List<ffs> list) {
        if (koq.b(list)) {
            return 0;
        }
        return ((ffs) Collections.min(list, new Comparator<ffs>() { // from class: ffw.1
            @Override // java.util.Comparator
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public int compare(ffs ffsVar, ffs ffsVar2) {
                return Double.compare(ffw.f(ffsVar), ffw.f(ffsVar2));
            }
        })).b();
    }

    public static float m(List<ffs> list) {
        if (koq.b(list)) {
            return 0.0f;
        }
        return ((ffs) Collections.max(list, new Comparator() { // from class: fga
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compare;
                ffs ffsVar = (ffs) obj;
                ffs ffsVar2 = (ffs) obj2;
                compare = Double.compare(ffw.i(ffsVar), ffw.i(ffsVar2));
                return compare;
            }
        })).t();
    }

    private static float i(ffs ffsVar) {
        if (ffsVar != null && ffsVar.t() > 0.0f) {
            return ffsVar.t();
        }
        return 0.0f;
    }

    public static float o(List<ffs> list) {
        if (koq.b(list)) {
            return 0.0f;
        }
        return ((ffs) Collections.max(list, new Comparator() { // from class: ffx
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compare;
                ffs ffsVar = (ffs) obj;
                ffs ffsVar2 = (ffs) obj2;
                compare = Double.compare(ffw.k(ffsVar), ffw.k(ffsVar2));
                return compare;
            }
        })).r();
    }

    private static float k(ffs ffsVar) {
        if (ffsVar != null && ffsVar.r() > 0.0f) {
            return ffsVar.r();
        }
        return 0.0f;
    }

    public static float i(List<ffs> list) {
        if (koq.b(list)) {
            return 0.0f;
        }
        return ((ffs) Collections.max(list, new Comparator() { // from class: fge
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compare;
                ffs ffsVar = (ffs) obj;
                ffs ffsVar2 = (ffs) obj2;
                compare = Double.compare(ffw.c(ffsVar), ffw.c(ffsVar2));
                return compare;
            }
        })).m();
    }

    private static float c(ffs ffsVar) {
        if (ffsVar == null) {
            return 0.0f;
        }
        return Math.max(ffsVar.m(), 0.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double f(ffs ffsVar) {
        if (ffsVar == null) {
            return 0.0d;
        }
        if (ffsVar.b() <= 0) {
            return 2.147483647E9d;
        }
        return ffsVar.b();
    }

    public static int h(List<ffs> list) {
        if (koq.b(list)) {
            return 0;
        }
        return ((ffs) Collections.max(list, new Comparator<ffs>() { // from class: ffw.5
            @Override // java.util.Comparator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public int compare(ffs ffsVar, ffs ffsVar2) {
                return Integer.compare(ffw.j(ffsVar), ffw.j(ffsVar2));
            }
        })).l();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int j(ffs ffsVar) {
        if (ffsVar == null) {
            return 0;
        }
        if (ffsVar.l() <= 0) {
            return Integer.MIN_VALUE;
        }
        return ffsVar.l();
    }

    public static float n(List<ffs> list) {
        if (koq.b(list)) {
            return 0.0f;
        }
        return c(((ffs) Collections.min(list, new Comparator<ffs>() { // from class: ffw.2
            @Override // java.util.Comparator
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public int compare(ffs ffsVar, ffs ffsVar2) {
                return Integer.compare(ffw.h(ffsVar), ffw.h(ffsVar2));
            }
        })).o());
    }

    public static float c(int i) {
        if (i <= 0) {
            return 0.0f;
        }
        if (i <= 10) {
            return 0.1f;
        }
        return new BigDecimal(i * 0.01f).setScale(1, 1).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int h(ffs ffsVar) {
        if (ffsVar == null) {
            return 0;
        }
        if (ffsVar.o() <= 0) {
            return Integer.MAX_VALUE;
        }
        return ffsVar.o();
    }

    public static int[] c(List<HeartRateData> list, int i, long j, String str, UserInfomation userInfomation, int i2) {
        List<HeartRateData> list2 = list;
        if (koq.b(list)) {
            return new int[5];
        }
        int size = list.size();
        LogUtil.a("Track_HeartRateAndStepsUtils", "listSize = ", Integer.valueOf(size));
        int e = e(list, j, size);
        LogUtil.a("Track_HeartRateAndStepsUtils", "requestHeartInteDuration hasTrustHeartRate ", str);
        if ("1".equals(str)) {
            Collections.sort(list, new Comparator<HeartRateData>() { // from class: ffw.8
                @Override // java.util.Comparator
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public int compare(HeartRateData heartRateData, HeartRateData heartRateData2) {
                    return Long.compare(heartRateData.acquireTime(), heartRateData2.acquireTime());
                }
            });
            list2 = TrackLineChartHolder.getInstance().insertHeartRatePoint(list, e);
            if (list2 != null) {
                size = list2.size();
            }
        }
        LogUtil.a("Track_HeartRateAndStepsUtils", "timeInterval = ", Integer.valueOf(e));
        int[] iArr = new int[5];
        c(list2, iArr, i, e, size, userInfomation, i2);
        return iArr;
    }

    private static int e(List<HeartRateData> list, long j, int i) {
        if (i > 1) {
            int i2 = i < 6 ? i : 6;
            int i3 = 0;
            int i4 = 0;
            for (int i5 = 1; i5 < i2; i5++) {
                if (list.get(i5).acquireTime() - list.get(i5 - 1).acquireTime() < 40000) {
                    i3++;
                } else {
                    i4++;
                }
            }
            if (i3 > i4) {
                LogUtil.a("Track_HeartRateAndStepsUtils", "minIntervalNums > maxIntervalNums ", Integer.valueOf(i3), " ", Integer.valueOf(i4));
            } else {
                int i6 = i * 60;
                if (i6 > TimeUnit.MILLISECONDS.toSeconds(j)) {
                    LogUtil.a("Track_HeartRateAndStepsUtils", "beyond total time ", Integer.valueOf(i3), " ", Integer.valueOf(i4), " ", Integer.valueOf(i6));
                } else {
                    LogUtil.a("Track_HeartRateAndStepsUtils", "minIntervalNums > maxIntervalNums ", Integer.valueOf(i3), " ", Integer.valueOf(i4));
                    return 60;
                }
            }
        }
        return 5;
    }

    private static void c(List<HeartRateData> list, int[] iArr, int i, int i2, int i3, UserInfomation userInfomation, int i4) {
        HeartZoneConf e;
        if (i == -1) {
            i = 0;
        }
        if (i4 < 1 || i4 > 4) {
            i4 = 1;
        }
        if (i == 0 || i == 2 || i == 1 || i == 3) {
            e = e(i4, i, userInfomation);
        } else {
            e = fgd.c(i4, 0, userInfomation.getAgeOrDefaultValue());
            d();
        }
        if (list == null || e == null) {
            Object[] objArr = new Object[2];
            objArr[0] = "heartRateList is null tempHeartRateZoneConf == null ";
            objArr[1] = Boolean.valueOf(e == null);
            LogUtil.b("Track_HeartRateAndStepsUtils", objArr);
            return;
        }
        LogUtil.a("Track_HeartRateAndStepsUtils", "tempHeartRateZoneConf is  ", e);
        for (int i5 = 0; i5 < i3; i5++) {
            HeartRateData heartRateData = list.get(i5);
            if (heartRateData == null) {
                LogUtil.b("Track_HeartRateAndStepsUtils", "heartRateData   is  null");
            } else {
                int findRateRegion = e.findRateRegion(heartRateData.acquireHeartRate(), e.getClassifyMethod());
                if (findRateRegion != -1) {
                    iArr[findRateRegion] = iArr[findRateRegion] + i2;
                }
            }
        }
    }

    public static void d() {
        d = null;
    }

    public static HeartZoneConf e(int i, int i2, UserInfomation userInfomation) {
        if (i < 1 || i > 4) {
            i = 1;
        }
        Object[] objArr = new Object[2];
        objArr[0] = "mHeartRateZoneConf = null is  ";
        objArr[1] = Boolean.valueOf(d == null);
        LogUtil.a("Track_HeartRateAndStepsUtils", objArr);
        if (d == null) {
            if (i2 == -1) {
                i2 = 0;
            }
            if (i2 == 0) {
                d = fgd.e(i, userInfomation.getAgeOrDefaultValue());
            } else {
                d = fgd.c(i, userInfomation.getAgeOrDefaultValue(), i2 != 2 ? i2 : 0);
            }
        }
        return d;
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x0034 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:8:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int a() {
        /*
            android.content.Context r0 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            com.huawei.hihealth.api.HiHealthApi r0 = com.huawei.hihealth.api.HiHealthManager.d(r0)
            java.lang.String r1 = "custom.UserPreference_HeartRate_Classify_Method"
            com.huawei.hihealth.HiUserPreference r0 = r0.getUserPreference(r1)
            java.lang.String r1 = "Track_HeartRateAndStepsUtils"
            if (r0 == 0) goto L24
            java.lang.String r0 = r0.getValue()     // Catch: java.lang.NumberFormatException -> L1b
            int r0 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.NumberFormatException -> L1b
            goto L25
        L1b:
            java.lang.String r0 = " parse userPreference value fail "
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r0)
        L24:
            r0 = 0
        L25:
            java.lang.String r2 = "initHeartZoneClassifyMethod classifyMethod  is"
            java.lang.Integer r3 = java.lang.Integer.valueOf(r0)
            java.lang.Object[] r2 = new java.lang.Object[]{r2, r3}
            com.huawei.hwlogsmodel.LogUtil.c(r1, r2)
            if (r0 != 0) goto L35
            r0 = 2
        L35:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ffw.a():int");
    }

    public static int a(int i) {
        HeartRateZoneMgr heartRateZoneMgrByCache;
        int i2;
        HealthDataMgrApi healthDataMgrApi = (HealthDataMgrApi) Services.a("HWHealthDataMgr", HealthDataMgrApi.class);
        if (healthDataMgrApi == null) {
            LogUtil.h("Track_HeartRateAndStepsUtils", "requestClassifyMethod healthDataMgrApi null");
            heartRateZoneMgrByCache = null;
        } else {
            heartRateZoneMgrByCache = healthDataMgrApi.getHeartRateZoneMgrByCache();
        }
        if (heartRateZoneMgrByCache != null && heartRateZoneMgrByCache.getPostureType(i) != null) {
            i2 = heartRateZoneMgrByCache.getPostureType(i).getClassifyMethod();
        } else {
            LogUtil.b("Track_HeartRateAndStepsUtils", "allPostureData == null");
            i2 = 0;
        }
        if (i2 == 0) {
            return 2;
        }
        return i2;
    }

    private static int b(float f) {
        return (((int) (f + 2.5f)) / 5) * 5;
    }

    public static int c(float f) {
        return (((int) (f + 1.0f)) / 2) * 2;
    }

    public static void d(ArrayList arrayList, int i, SugChart sugChart) {
        float size;
        if (koq.b(arrayList) || sugChart == null) {
            return;
        }
        if (i != 3 || (arrayList.get(0) instanceof StepRateData)) {
            f12493a = 60;
            size = arrayList.size() * 60;
            if (arrayList.size() > 2) {
                size = b(arrayList, size);
            }
        } else {
            f12493a = 5;
            size = arrayList.size() * 5;
        }
        int minutes = (int) TimeUnit.SECONDS.toMinutes(Math.round(size - f12493a));
        LogUtil.a("Track_HeartRateAndStepsUtils", "count: ", Integer.valueOf(arrayList.size()), " tracktype: ", Integer.valueOf(i), " interval: ", Integer.valueOf(f12493a));
        a(arrayList, sugChart, size, minutes);
    }

    private static float b(ArrayList arrayList, float f) {
        int size;
        if (arrayList.get(1) instanceof StepRateData) {
            if (((StepRateData) arrayList.get(1)).acquireTime() - ((StepRateData) arrayList.get(0)).acquireTime() >= 40000) {
                return f;
            }
            f12493a = 5;
            size = arrayList.size();
        } else {
            if (((HeartRateData) arrayList.get(1)).acquireTime() - ((HeartRateData) arrayList.get(0)).acquireTime() >= 40000) {
                return f;
            }
            f12493a = 5;
            size = arrayList.size();
        }
        return size * 5;
    }

    private static void a(ArrayList arrayList, SugChart sugChart, float f, int i) {
        if (i > 15) {
            ArrayList arrayList2 = new ArrayList(16);
            arrayList2.add(Integer.valueOf(Math.abs(i - (b(i / 5) * 5))));
            arrayList2.add(Integer.valueOf(Math.abs(i - (b(i / 4) * 4))));
            arrayList2.add(Integer.valueOf(Math.abs(i - (b(i / 3) * 3))));
            for (int i2 = 0; i2 < arrayList2.size(); i2++) {
                if (((Integer) Collections.min(arrayList2)).compareTo((Integer) arrayList2.get(i2)) == 0) {
                    int i3 = 5 - i2;
                    d(i3, b(i / i3), arrayList, f, sugChart);
                    return;
                }
            }
            return;
        }
        if (i <= 3) {
            d(3, 1, arrayList, f, sugChart);
            return;
        }
        int i4 = i / 5;
        int i5 = i / 4;
        int i6 = i / 3;
        if (i % 5 == 0) {
            d(5, i4, arrayList, f, sugChart);
            return;
        }
        if (i % 4 == 0) {
            d(4, i5, arrayList, f, sugChart);
            return;
        }
        if (i % 3 == 0) {
            d(3, i6, arrayList, f, sugChart);
            return;
        }
        ArrayList arrayList3 = new ArrayList(16);
        arrayList3.add(Integer.valueOf(Math.abs(i - (i4 * 5))));
        arrayList3.add(Integer.valueOf(Math.abs(i - (i5 * 4))));
        arrayList3.add(Integer.valueOf(Math.abs(i - (i6 * 3))));
        a(arrayList, sugChart, f, i, arrayList3);
    }

    private static void a(ArrayList arrayList, SugChart sugChart, float f, int i, ArrayList<Integer> arrayList2) {
        for (int i2 = 0; i2 < arrayList2.size(); i2++) {
            if (((Integer) Collections.min(arrayList2)).compareTo(arrayList2.get(i2)) == 0) {
                int i3 = 5 - i2;
                d(i3, i / i3, arrayList, f, sugChart);
                return;
            }
        }
    }

    private static void d(int i, int i2, ArrayList arrayList, float f, SugChart sugChart) {
        int round;
        float f2 = i * i2;
        if (f - f12493a > TimeUnit.MINUTES.toSeconds((long) f2)) {
            LogUtil.a("Track_HeartRateAndStepsUtils", "max time: ", LogAnonymous.d(TimeUnit.MINUTES.toMillis(i * i2)));
        }
        if (TimeUnit.SECONDS.toMinutes((long) (f - f12493a)) > f2) {
            f2 = TimeUnit.SECONDS.toMinutes((long) (f - f12493a));
        }
        int i3 = f12493a;
        float f3 = i3;
        if (i3 == 5) {
            round = Math.round(((f2 * 60.0f) + f3) / 5.0f);
        } else {
            round = Math.round(1.0f + f2);
        }
        if (arrayList.size() > round) {
            round = arrayList.size();
        }
        if (round >= 150) {
            LogUtil.a("Track_HeartRateAndStepsUtils", Integer.valueOf(arrayList.size()), "point count: ", 150, " max time: ", LogAnonymous.d((int) TimeUnit.MINUTES.toMillis((long) f2)));
            f3 = ((60.0f * f2) + f12493a) / 150;
            round = 150;
        } else {
            LogUtil.a("Track_HeartRateAndStepsUtils", "count: ", Integer.valueOf(round), " actual count: ", Integer.valueOf(arrayList.size()), " max time: ", LogAnonymous.d((int) TimeUnit.MINUTES.toMillis((long) f2)));
        }
        ArrayList arrayList2 = new ArrayList(16);
        b(arrayList, f - f12493a, f3, round, arrayList2);
        if (arrayList.get(0) instanceof HeartRateData) {
            LogUtil.a("Track_HeartRateAndStepsUtils", "heart group: ", Integer.valueOf(i));
            b(arrayList, (ArrayList<ffh>) arrayList2);
            sugChart.setXNums(i, i2, f2);
            sugChart.d(HeartRateThresholdConfig.HEART_RATE_LIMIT);
            sugChart.c(arrayList2);
            return;
        }
        LogUtil.a("Track_HeartRateAndStepsUtils", "step group: ", Integer.valueOf(i));
        d(arrayList, arrayList2);
        sugChart.a(true);
        sugChart.setXNums(i, i2, f2);
        sugChart.d(300);
        sugChart.c(arrayList2);
    }

    private static void b(ArrayList arrayList, float f, float f2, int i, ArrayList<ffh> arrayList2) {
        for (int i2 = 0; i2 < i; i2++) {
            float f3 = i2 * f2;
            ffh ffhVar = new ffh(0.0f, "");
            if (f3 > f) {
                ffhVar = new ffh(-100.0f, "" + ((int) TimeUnit.SECONDS.toMinutes((long) f3)));
            } else {
                int[] iArr = new int[2];
                for (int i3 = iArr[0]; i3 < arrayList.size(); i3++) {
                    c(arrayList, f3, ffhVar, iArr, i3);
                }
            }
            arrayList2.add(ffhVar);
        }
    }

    private static void c(ArrayList arrayList, float f, ffh ffhVar, int[] iArr, int i) {
        iArr[1] = f12493a * i;
        if (Math.abs(r0 - f) < 1.0E-7d) {
            iArr[0] = i + 1;
            ffhVar.c(b(arrayList.get(i)));
            return;
        }
        if (iArr[1] < f) {
            int i2 = i + 1;
            if (i2 >= arrayList.size()) {
                LogUtil.c("Track_HeartRateAndStepsUtils", "mStepRateList out of bound");
                ffhVar.c(b(arrayList.get(arrayList.size() - 1)));
                return;
            }
            if (f12493a * i2 >= f) {
                iArr[0] = i2;
                double b = b(arrayList.get(i2)) - b(arrayList.get(i));
                int i3 = iArr[1];
                ffhVar.c((int) Math.round(r13 + ((b / (r3 - i3)) * (f - i3))));
                return;
            }
            return;
        }
        LogUtil.a("Track_HeartRateAndStepsUtils", "handleTotalData else");
    }

    private static void b(ArrayList arrayList, ArrayList<ffh> arrayList2) {
        ffh ffhVar = (ffh) Collections.max(arrayList2, new Comparator<ffh>() { // from class: ffw.9
            @Override // java.util.Comparator
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public int compare(ffh ffhVar2, ffh ffhVar3) {
                return Float.compare(ffhVar2.c(), ffhVar3.c());
            }
        });
        float b = b((List<HeartRateData>) arrayList);
        if (Float.compare(ffhVar.c(), b) != 0) {
            LogUtil.c("Track_HeartRateAndStepsUtils", "heart rate max");
            ffhVar.c(b);
        }
        ffh ffhVar2 = (ffh) Collections.min(arrayList2, new Comparator<ffh>() { // from class: ffw.7
            @Override // java.util.Comparator
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public int compare(ffh ffhVar3, ffh ffhVar4) {
                return Float.compare(ffhVar3.c() <= 0.0f ? Float.MAX_VALUE : ffhVar3.c(), ffhVar4.c() > 0.0f ? ffhVar4.c() : Float.MAX_VALUE);
            }
        });
        float e = e(arrayList);
        if (Float.compare(ffhVar2.c(), e) != 0) {
            LogUtil.c("Track_HeartRateAndStepsUtils", "heart rate min");
            ffhVar2.c(e);
        }
    }

    public static ArrayList<StepRateData> d(ArrayList<StepRateData> arrayList, int i, int i2) {
        int i3;
        int i4;
        StepRateData next;
        if (i != 3 || b(arrayList, i2)) {
            return arrayList;
        }
        LogUtil.a("Track_HeartRateAndStepsUtils", "requestStepRateList arrange StepRateList");
        ArrayList<StepRateData> arrayList2 = new ArrayList<>(16);
        Iterator<StepRateData> it = arrayList.iterator();
        loop0: while (true) {
            i3 = 0;
            i4 = 0;
            while (it.hasNext()) {
                next = it.next();
                i3++;
                i4 += next.acquireStepRate();
                if (i3 == 12) {
                    break;
                }
            }
            next.saveStepRate(i4 / i3);
            arrayList2.add(next);
        }
        if (i3 != 0) {
            arrayList.get(arrayList.size() - 1).saveStepRate(i4 / 12);
            arrayList2.add(arrayList.get(arrayList.size() - 1));
        }
        return arrayList2;
    }

    private static boolean b(ArrayList<StepRateData> arrayList, int i) {
        return arrayList == null || arrayList.size() < 3 || arrayList.get(1).acquireTime() - arrayList.get(0).acquireTime() > ((long) i);
    }

    public static int j(List<kog> list) {
        int i = 0;
        if (koq.b(list)) {
            return 0;
        }
        if (list.size() == 1) {
            if (list.get(0) != null) {
                return list.get(0).d();
            }
            return 0;
        }
        for (kog kogVar : list) {
            if (kogVar != null && kogVar.d() > i) {
                i = kogVar.d();
            }
        }
        return i;
    }

    public static int g(List<kol> list) {
        int i;
        if (koq.b(list)) {
            return 0;
        }
        if (list.size() == 1) {
            if (list.get(0) != null) {
                return list.get(0).c();
            }
            return 0;
        }
        int i2 = 0;
        while (true) {
            if (i2 >= list.size()) {
                i = Integer.MAX_VALUE;
                break;
            }
            if (list.get(i2) != null && list.get(i2).c() > 0) {
                i = list.get(i2).c();
                break;
            }
            i2++;
        }
        if (i2 == list.size() - 1) {
            return 0;
        }
        while (i2 < list.size()) {
            kol kolVar = list.get(i2);
            if (kolVar != null && kolVar.c() > 0 && kolVar.c() < i) {
                i = kolVar.c();
            }
            i2++;
        }
        if (i == Integer.MAX_VALUE) {
            return 0;
        }
        return i;
    }

    public static List<nns> d(List<kny> list) {
        ArrayList arrayList = new ArrayList();
        if (koq.b(list)) {
            LogUtil.h("Track_HeartRateAndStepsUtils", "no sportExtendDetails");
            return arrayList;
        }
        for (kny knyVar : list) {
            if (knyVar != null) {
                arrayList.add(new nns(knyVar.c(), knyVar.b(), knyVar.d(), knyVar.a(), BaseApplication.getContext().getColor(R.color._2131299283_res_0x7f090bd3)));
            }
        }
        return arrayList;
    }
}
