package defpackage;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.RemoteException;
import android.text.TextUtils;
import com.amap.api.services.core.AMapException;
import com.huawei.hihealth.CharacteristicConstant;
import com.huawei.hihealth.ICommonCallback;
import com.huawei.hihealth.IDataOperateListener;
import com.huawei.hihealth.TrendQuery;
import com.huawei.hihealth.data.type.HealthTrendPeriodType;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.model.EventTypeInfo;
import com.huawei.hihealth.model.Goal;
import com.huawei.hihealth.model.GoalInfo;
import com.huawei.hihealth.model.HealthAlarmInfo;
import com.huawei.hihealth.model.SportStatusInfo;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes7.dex */
public class iqz {

    /* renamed from: a, reason: collision with root package name */
    private static final Set<Integer> f13550a;
    private static final Set<Integer> b;
    private static final Set<Integer> d;
    private static final Set<Integer> f;
    private static final Set<Integer> h;
    private static final Set<Integer> j;
    private static final Object g = new Object();
    private static HashMap<String, Integer> k = new HashMap<>();
    private static volatile int l = 0;
    private static final Map<Integer, String> e = Collections.unmodifiableMap(new HashMap<Integer, String>() { // from class: iqz.4
        {
            put(1, "1.0.0.0");
            put(2, "5.0.0.500");
            put(3, "5.1.0.300");
            put(4, "6.3.0.300");
            put(5, "6.3.0.300");
            put(6, "6.5.0.300");
            put(7, "6.7.0.300");
            put(8, "6.7.0.301");
            put(9, "7.0.0.300");
        }
    });
    private static final Set<Integer> c = ira.d();
    private static final List<Integer> i = HealthTrendPeriodType.c();

    static {
        HashSet hashSet = new HashSet();
        hashSet.add(10001);
        hashSet.add(10002);
        hashSet.add(10006);
        hashSet.add(31001);
        hashSet.add(61001);
        hashSet.add(10062);
        hashSet.add(61002);
        Integer valueOf = Integer.valueOf(AMapException.CODE_AMAP_CLIENT_UPLOADAUTO_STARTED_ERROR);
        hashSet.add(valueOf);
        hashSet.add(10065);
        hashSet.add(10067);
        hashSet.add(Integer.valueOf(DicDataTypeUtil.DataType.ELECTROCARDIOGRAM.value()));
        hashSet.add(10068);
        hashSet.add(Integer.valueOf(DicDataTypeUtil.DataType.ARRHYTHMIA_PPG_TYPE.value()));
        hashSet.add(Integer.valueOf(DicDataTypeUtil.DataType.ARRHYTHMIA_RESULT_TYPE.value()));
        hashSet.add(Integer.valueOf(DicDataTypeUtil.DataType.VASCULAR_HEALTH.value()));
        hashSet.add(Integer.valueOf(DicDataTypeUtil.DataType.VASCULAR_HEALTH_RESULT.value()));
        hashSet.add(10069);
        hashSet.add(30029);
        hashSet.add(2104);
        hashSet.add(Integer.valueOf(HiHealthDataType.b));
        hashSet.add(2103);
        hashSet.add(2109);
        hashSet.add(Integer.valueOf(DicDataTypeUtil.DataType.OSA_SET.value()));
        hashSet.add(10066);
        hashSet.add(22101);
        hashSet.add(22102);
        hashSet.add(22103);
        hashSet.add(22104);
        hashSet.add(22105);
        hashSet.add(2002);
        hashSet.add(Integer.valueOf(DicDataTypeUtil.DataType.SLEEP_SCORE.value()));
        hashSet.add(10010);
        hashSet.add(10011);
        hashSet.add(Integer.valueOf(DicDataTypeUtil.DataType.PHYSIOLOGICAL_CYCLE.value()));
        hashSet.add(Integer.valueOf(DicDataTypeUtil.DataType.PHYSIOLOGICAL_CYCLE_REMARKS.value()));
        hashSet.add(Integer.valueOf(DicDataTypeUtil.DataType.DRINK_WATER_SET.value()));
        hashSet.add(Integer.valueOf(DicDataTypeUtil.DataType.PHYSIOLOGICAL_CYCLE_LINKAGES.value()));
        hashSet.add(Integer.valueOf(DicDataTypeUtil.DataType.BODY_SHAPE.value()));
        hashSet.add(22106);
        hashSet.add(22107);
        hashSet.add(Integer.valueOf(DicDataTypeUtil.DataType.SLEEP_ON_OFF_BED_RECORD.value()));
        hashSet.add(Integer.valueOf(DicDataTypeUtil.DataType.MINDFULNESS_TYPE.value()));
        hashSet.add(50001);
        hashSet.add(Integer.valueOf(DicDataTypeUtil.DataType.SLEEP_RECORD.value()));
        hashSet.add(Integer.valueOf(DicDataTypeUtil.DataType.MONTHLY_SLEEP.value()));
        hashSet.add(Integer.valueOf(DicDataTypeUtil.DataType.COURSE_RECORD.value()));
        hashSet.add(Integer.valueOf(DicDataTypeUtil.DataType.MEDICAL_EXAM_REPORT.value()));
        hashSet.add(Integer.valueOf(DicDataTypeUtil.DataType.FATTY_LIVER_SET.value()));
        hashSet.add(Integer.valueOf(DicDataTypeUtil.DataType.DYNAMIC_BP_REPORT.value()));
        f = Collections.unmodifiableSet(hashSet);
        HashSet hashSet2 = new HashSet();
        hashSet2.add(10001);
        hashSet2.add(10002);
        hashSet2.add(10006);
        hashSet2.add(31001);
        hashSet2.add(10061);
        hashSet2.add(valueOf);
        hashSet2.add(Integer.valueOf(DicDataTypeUtil.DataType.ELECTROCARDIOGRAM.value()));
        hashSet2.add(Integer.valueOf(DicDataTypeUtil.DataType.ARRHYTHMIA_PPG_TYPE.value()));
        hashSet2.add(Integer.valueOf(DicDataTypeUtil.DataType.VASCULAR_HEALTH.value()));
        hashSet2.add(Integer.valueOf(DicDataTypeUtil.DataType.VASCULAR_HEALTH_RESULT.value()));
        hashSet2.add(30029);
        hashSet2.add(2104);
        hashSet2.add(Integer.valueOf(HiHealthDataType.b));
        hashSet2.add(Integer.valueOf(DicDataTypeUtil.DataType.ARRHYTHMIA_RESULT_TYPE.value()));
        hashSet2.add(2103);
        hashSet2.add(2109);
        hashSet2.add(Integer.valueOf(DicDataTypeUtil.DataType.OSA_SET.value()));
        hashSet2.add(2002);
        hashSet2.add(Integer.valueOf(DicDataTypeUtil.DataType.SLEEP_SCORE.value()));
        hashSet2.add(10010);
        hashSet2.add(Integer.valueOf(DicDataTypeUtil.DataType.PHYSIOLOGICAL_CYCLE.value()));
        hashSet2.add(Integer.valueOf(DicDataTypeUtil.DataType.PHYSIOLOGICAL_CYCLE_REMARKS.value()));
        hashSet2.add(Integer.valueOf(DicDataTypeUtil.DataType.PHYSIOLOGICAL_CYCLE_LINKAGES.value()));
        hashSet2.add(Integer.valueOf(DicDataTypeUtil.DataType.BODY_SHAPE.value()));
        hashSet2.add(22106);
        hashSet2.add(22107);
        hashSet2.add(Integer.valueOf(DicDataTypeUtil.DataType.SLEEP_ON_OFF_BED_RECORD.value()));
        hashSet2.add(Integer.valueOf(DicDataTypeUtil.DataType.MINDFULNESS_TYPE.value()));
        hashSet2.add(Integer.valueOf(DicDataTypeUtil.DataType.MEDICAL_EXAM_REPORT.value()));
        hashSet2.add(Integer.valueOf(DicDataTypeUtil.DataType.DYNAMIC_BP_REPORT.value()));
        f13550a = Collections.unmodifiableSet(hashSet2);
        HashSet hashSet3 = new HashSet();
        hashSet3.add(264);
        Integer valueOf2 = Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_INDOOR_BIKE);
        hashSet3.add(valueOf2);
        Integer valueOf3 = Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_ROW_MACHINE);
        hashSet3.add(valueOf3);
        Integer valueOf4 = Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_CROSS_TRAINER);
        hashSet3.add(valueOf4);
        b = Collections.unmodifiableSet(hashSet3);
        HashSet hashSet4 = new HashSet();
        hashSet4.add(257);
        hashSet4.add(258);
        hashSet4.add(259);
        hashSet4.add(264);
        hashSet4.add(valueOf2);
        hashSet4.add(valueOf3);
        hashSet4.add(valueOf4);
        hashSet4.add(Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_OTHER_SPORT));
        j = Collections.unmodifiableSet(hashSet4);
        HashSet hashSet5 = new HashSet();
        hashSet5.add(Integer.valueOf(CharacteristicConstant.EnhanceSportType.SPORT_TYPE_ROWER.getEnhanceSportTypeValue()));
        hashSet5.add(Integer.valueOf(CharacteristicConstant.EnhanceSportType.SPORT_TYPE_ROWERSTRENGTH.getEnhanceSportTypeValue()));
        hashSet5.add(Integer.valueOf(CharacteristicConstant.EnhanceSportType.SPORT_TYPE_ROW_MACHINE.getEnhanceSportTypeValue()));
        hashSet5.add(Integer.valueOf(CharacteristicConstant.EnhanceSportType.SPORT_TYPE_ROPE_SKIPPING.getEnhanceSportTypeValue()));
        hashSet5.add(Integer.valueOf(CharacteristicConstant.EnhanceSportType.SPORT_TYPE_TREADMILL.getEnhanceSportTypeValue()));
        hashSet5.add(Integer.valueOf(CharacteristicConstant.EnhanceSportType.SPORT_TYPE_CROSS_TRAINER.getEnhanceSportTypeValue()));
        hashSet5.add(Integer.valueOf(CharacteristicConstant.EnhanceSportType.SPORT_TYPE_INDOOR_BIKE.getEnhanceSportTypeValue()));
        hashSet5.add(Integer.valueOf(CharacteristicConstant.EnhanceSportType.SPORT_TYPE_WALK.getEnhanceSportTypeValue()));
        hashSet5.add(Integer.valueOf(CharacteristicConstant.EnhanceSportType.SPORT_TYPE_RUN.getEnhanceSportTypeValue()));
        hashSet5.add(Integer.valueOf(CharacteristicConstant.EnhanceSportType.SPORT_TYPE_BIKE.getEnhanceSportTypeValue()));
        hashSet5.add(Integer.valueOf(CharacteristicConstant.EnhanceSportType.SPORT_TYPE_OTHER_SPORT.getEnhanceSportTypeValue()));
        h = Collections.unmodifiableSet(hashSet5);
        HashSet hashSet6 = new HashSet();
        hashSet6.add(2104);
        hashSet6.add(Integer.valueOf(DicDataTypeUtil.DataType.OSA_LEVEL.value()));
        hashSet6.add(Integer.valueOf(DicDataTypeUtil.DataType.OSA_AVG_CNT_PER_HOUR.value()));
        hashSet6.add(22000);
        hashSet6.add(44306);
        hashSet6.add(Integer.valueOf(DicDataTypeUtil.DataType.PPG_OF_VASCULAR_HEALTH.value()));
        hashSet6.add(Integer.valueOf(DicDataTypeUtil.DataType.ECG_OF_VASCULAR_HEALTH.value()));
        hashSet6.add(Integer.valueOf(DicDataTypeUtil.DataType.VASCULAR_HEALTH.value()));
        hashSet6.add(Integer.valueOf(DicDataTypeUtil.DataType.VASCULAR_HEALTH_RESULT.value()));
        hashSet6.add(Integer.valueOf(DicDataTypeUtil.DataType.OSA_SET.value()));
        hashSet6.add(Integer.valueOf(DicDataTypeUtil.DataType.OSA_DETAIL_HALF_HOUR.value()));
        hashSet6.add(Integer.valueOf(DicDataTypeUtil.DataType.BLOOD_PRESSURE_SET.value()));
        hashSet6.add(Integer.valueOf(DicDataTypeUtil.DataType.PULSE_WAVE_VELOCITY.value()));
        hashSet6.add(Integer.valueOf(DicDataTypeUtil.DataType.VASCULAR_PULSE.value()));
        hashSet6.add(Integer.valueOf(DicDataTypeUtil.DataType.ARTERIAL_ELASTICITY.value()));
        hashSet6.add(Integer.valueOf(DicDataTypeUtil.DataType.ACTIVITY_RECORD.value()));
        hashSet6.add(22100);
        hashSet6.add(2002);
        hashSet6.add(Integer.valueOf(HiHealthDataType.b));
        hashSet6.add(30264);
        hashSet6.add(30283);
        hashSet6.add(30265);
        hashSet6.add(30273);
        hashSet6.add(30274);
        hashSet6.add(30257);
        hashSet6.add(30258);
        hashSet6.add(30259);
        hashSet6.add(30260);
        hashSet6.add(30262);
        hashSet6.add(30266);
        hashSet6.add(30281);
        hashSet6.add(30001);
        hashSet6.add(30002);
        hashSet6.add(10001);
        hashSet6.add(10007);
        hashSet6.add(Integer.valueOf(DicDataTypeUtil.DataType.ARRHYTHMIA_RESULT_TYPE.value()));
        hashSet6.add(Integer.valueOf(DicDataTypeUtil.DataType.ARRHYTHMIA_PPG_TYPE.value()));
        hashSet6.add(Integer.valueOf(DicDataTypeUtil.DataType.ELECTROCARDIOGRAM.value()));
        hashSet6.add(Integer.valueOf(DicDataTypeUtil.DataType.SPO2_RESP_INFECTION.value()));
        hashSet6.add(Integer.valueOf(DicDataTypeUtil.DataType.RRI_RESP_INFECTION.value()));
        hashSet6.add(Integer.valueOf(DicDataTypeUtil.DataType.TEMPERATURE_RESP_INFECTION.value()));
        hashSet6.add(Integer.valueOf(DicDataTypeUtil.DataType.SLEEP_FRAGMENT_RESP_INFECTION.value()));
        hashSet6.add(Integer.valueOf(DicDataTypeUtil.DataType.PHYSIOLOGICAL_CYCLE.value()));
        hashSet6.add(Integer.valueOf(DicDataTypeUtil.DataType.PHYSIOLOGICAL_CYCLE_REMARKS.value()));
        hashSet6.add(Integer.valueOf(DicDataTypeUtil.DataType.DRINK_WATER_SET.value()));
        hashSet6.add(Integer.valueOf(DicDataTypeUtil.DataType.PHYSIOLOGICAL_CYCLE_LINKAGES.value()));
        hashSet6.add(Integer.valueOf(DicDataTypeUtil.DataType.CNTBPORIGINPPGDATA.value()));
        hashSet6.add(Integer.valueOf(DicDataTypeUtil.DataType.BODY_SHAPE.value()));
        hashSet6.add(Integer.valueOf(DicDataTypeUtil.DataType.BODY_SHAPE_USER_MODIFY_VALUE.value()));
        hashSet6.add(Integer.valueOf(DicDataTypeUtil.DataType.SLEEP_ON_OFF_BED_RECORD.value()));
        hashSet6.add(Integer.valueOf(DicDataTypeUtil.DataType.MINDFULNESS_TYPE.value()));
        hashSet6.add(Integer.valueOf(DicDataTypeUtil.DataType.WEIGHT_BODYFAT_BROAD.value()));
        hashSet6.add(Integer.valueOf(DicDataTypeUtil.DataType.SLEEP_RECORD.value()));
        hashSet6.add(Integer.valueOf(DicDataTypeUtil.DataType.MONTHLY_SLEEP.value()));
        hashSet6.add(Integer.valueOf(DicDataTypeUtil.DataType.COURSE_RECORD.value()));
        hashSet6.add(Integer.valueOf(DicDataTypeUtil.DataType.BG_DAILY_PROB_WIN.value()));
        hashSet6.add(Integer.valueOf(DicDataTypeUtil.DataType.BG_COMBINED_PPG_FEATURE.value()));
        hashSet6.add(Integer.valueOf(DicDataTypeUtil.DataType.ENVIRONMENT_NOISE_TYPE.value()));
        hashSet6.add(Integer.valueOf(DicDataTypeUtil.DataType.DAILY_TARGET_PROBLEM.value()));
        hashSet6.add(Integer.valueOf(DicDataTypeUtil.DataType.MEDICAL_EXAM_REPORT.value()));
        hashSet6.add(Integer.valueOf(DicDataTypeUtil.DataType.CURRENT_BASAL_METABOLISM.value()));
        hashSet6.add(Integer.valueOf(DicDataTypeUtil.DataType.FATTY_LIVER_SET.value()));
        hashSet6.add(Integer.valueOf(DicDataTypeUtil.DataType.DYNAMIC_BP_REPORT.value()));
        hashSet6.add(Integer.valueOf(DicDataTypeUtil.DataType.SPORT_BLOOD_PRESSURE_RESULT.value()));
        hashSet6.add(Integer.valueOf(DicDataTypeUtil.DataType.OVARY_HEALTH_RESULT_SET.value()));
        hashSet6.add(Integer.valueOf(DicDataTypeUtil.DataType.OVARY_HEALTH_DAILY_STATUS_SET.value()));
        hashSet6.add(Integer.valueOf(DicDataTypeUtil.DataType.BLOODPRESSURE_RISK_RESEARCH_RESULT.value()));
        hashSet6.add(Integer.valueOf(DicDataTypeUtil.DataType.MEDICAL_EXAM_REPORT_RESEARCH.value()));
        d = Collections.unmodifiableSet(hashSet6);
    }

    public static void a(String str, String str2) {
        int i2;
        try {
            i2 = (int) Double.parseDouble(str2);
        } catch (NumberFormatException e2) {
            ReleaseLogUtil.c("HiH_HiHlthKitVerUt", "transform version error", LogAnonymous.b((Throwable) e2));
            i2 = 1;
        }
        synchronized (g) {
            k.put(str, Integer.valueOf(i2));
        }
    }

    public static int c(String str) {
        synchronized (g) {
            if (TextUtils.isEmpty(str)) {
                return 1;
            }
            int intValue = k.get(str) != null ? k.get(str).intValue() : 1;
            ReleaseLogUtil.e("HiH_HiHlthKitVerUt", "getVersionValue packName= ", str, " versionValue = ", Integer.valueOf(intValue));
            return intValue;
        }
    }

    public static boolean d(String str, int i2) {
        return c(str) >= i2;
    }

    public static String c(String str, boolean z) {
        int c2 = c(str);
        switch (c2) {
            case 2:
                if (!z) {
                    break;
                }
                break;
        }
        return e.get(Integer.valueOf(c2));
    }

    public static int c(Context context) {
        if (l != 0) {
            return l;
        }
        l = a(context);
        return l;
    }

    private static int a(Context context) {
        if (context == null) {
            ReleaseLogUtil.c("HiH_HiHlthKitVerUt", "getApiLevelForMetaData context is null");
            return 0;
        }
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            ReleaseLogUtil.c("HiH_HiHlthKitVerUt", "getApiLevelForMetaData PackageManager is null");
            return 0;
        }
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null && applicationInfo.metaData != null) {
                int i2 = applicationInfo.metaData.getInt("com.huawei.hihealthservice.service.api_level");
                LogUtil.a("HiHlthKitVerUt", "getApiLevelForMetaData apiLevel:" + i2);
                return i2;
            }
        } catch (PackageManager.NameNotFoundException e2) {
            ReleaseLogUtil.c("HiH_HiHlthKitVerUt", "getApiLevelForMetaData PackageManager.NameNotFoundException", LogAnonymous.b((Throwable) e2));
        }
        return 0;
    }

    private static boolean a(int i2) {
        ReleaseLogUtil.e("HiH_HiHlthKitVerUt", "isSupportSave type:", Integer.valueOf(i2));
        return f.contains(Integer.valueOf(i2));
    }

    private static boolean e(int i2) {
        ReleaseLogUtil.e("HiH_HiHlthKitVerUt", "isSupportDelete type:", Integer.valueOf(i2));
        return f13550a.contains(Integer.valueOf(i2));
    }

    private static boolean d(int i2) {
        ReleaseLogUtil.e("HiH_HiHlthKitVerUt", "isSupportStartSport type:", Integer.valueOf(i2));
        return j.contains(Integer.valueOf(i2));
    }

    private static boolean j(int i2) {
        ReleaseLogUtil.e("HiH_HiHlthKitVerUt", "isSupportStartSportEnhance type:", Integer.valueOf(i2));
        return h.contains(Integer.valueOf(i2));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static boolean c(String str, int i2) {
        char c2;
        str.hashCode();
        switch (str.hashCode()) {
            case -2129779808:
                if (str.equals("connectSportDevice")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case -1739464756:
                if (str.equals("saveSamples")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case -1584340942:
                if (str.equals("startSport")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case -782746084:
                if (str.equals("startSportEnhance")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case -777733323:
                if (str.equals("deleteSample")) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            case 1052266887:
                if (str.equals("saveSample")) {
                    c2 = 5;
                    break;
                }
                c2 = 65535;
                break;
            case 1660070878:
                if (str.equals("deleteSamples")) {
                    c2 = 6;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        switch (c2) {
            case 0:
                return b(i2);
            case 1:
            case 5:
                return a(i2);
            case 2:
                return d(i2);
            case 3:
                return j(i2);
            case 4:
            case 6:
                return e(i2);
            default:
                ReleaseLogUtil.d("HiH_HiHlthKitVerUt", "api is not supported");
                return false;
        }
    }

    private static boolean b(int i2) {
        ReleaseLogUtil.e("HiH_HiHlthKitVerUt", "isSupportConnectSportDevice type:", Integer.valueOf(i2));
        return b.contains(Integer.valueOf(i2));
    }

    public static <T> boolean e(String str, int i2, irc ircVar, T t) throws RemoteException {
        if (c(str, i2)) {
            return true;
        }
        ReleaseLogUtil.d("HiH_HiHlthKitVerUt", "not supported api:", str, ",type:", Integer.valueOf(i2));
        if (t instanceof IDataOperateListener) {
            ((IDataOperateListener) t).onResult(30, ipd.c(30));
        } else if (t instanceof ICommonCallback) {
            ((ICommonCallback) t).onResult(30, ipd.b(30));
        } else {
            ReleaseLogUtil.d("HiH_HiHlthKitVerUt", "callback is not supported api");
        }
        ircVar.d();
        return false;
    }

    public static boolean c(int i2) {
        ReleaseLogUtil.e("HiH_HiHlthKitVerUt", "isSupportQueryDict type:", Integer.valueOf(i2));
        return d.contains(Integer.valueOf(i2));
    }

    public static boolean d(EventTypeInfo eventTypeInfo) {
        if (!a(eventTypeInfo)) {
            ReleaseLogUtil.d("HiH_HiHlthKitVerUt", "eventInfo is invalid. ");
            return false;
        }
        if ((eventTypeInfo instanceof HealthAlarmInfo) || (eventTypeInfo instanceof SportStatusInfo)) {
            return true;
        }
        Iterator<Goal> it = ((GoalInfo) eventTypeInfo).getGoals().iterator();
        boolean z = true;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Goal next = it.next();
            z = (next.getGoalType() == 1 && iqi.containDataType(next.getDataType())) || (next.getGoalType() == 2 && iqh.supportType(next.getDataType()));
            if (!z) {
                ReleaseLogUtil.d("HiH_HiHlthKitVerUt", "eventInfo dataType is invalid. ");
                break;
            }
        }
        return z;
    }

    public static boolean b(TrendQuery trendQuery) {
        if (trendQuery == null || trendQuery.getDataTypes().length == 0 || trendQuery.getTrendPeriods().length == 0) {
            ReleaseLogUtil.d("HiH_HiHlthKitVerUt", "trendQuery is empty");
            return false;
        }
        if (!c.containsAll(b(trendQuery.getDataTypes()))) {
            ReleaseLogUtil.d("HiH_HiHlthKitVerUt", "trend data type is not supported");
            return false;
        }
        if (i.containsAll(b(trendQuery.getTrendPeriods()))) {
            return true;
        }
        ReleaseLogUtil.d("HiH_HiHlthKitVerUt", "trendPeriods is not supported");
        return false;
    }

    private static List<Integer> b(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        for (int i2 : iArr) {
            arrayList.add(Integer.valueOf(i2));
        }
        return arrayList;
    }

    private static boolean a(EventTypeInfo eventTypeInfo) {
        return (eventTypeInfo instanceof GoalInfo) || (eventTypeInfo instanceof HealthAlarmInfo) || (eventTypeInfo instanceof SportStatusInfo);
    }
}
