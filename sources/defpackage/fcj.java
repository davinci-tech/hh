package defpackage;

import android.text.TextUtils;
import com.huawei.health.sleep.sleepviewdata.SleepViewConstants;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.UnitUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes3.dex */
public class fcj {
    private static String d = "SleepDataUtils";

    public static int a(int i, int i2) {
        int i3 = i < 10 ? 100 - (i * 2) : 90 - i;
        if (i3 < 0) {
            i3 = 0;
        }
        if (i == 0 && i2 == 0) {
            return 0;
        }
        return i3;
    }

    public static int c(int i) {
        if (i > 85 && i <= 100) {
            return 0;
        }
        if (i < 75 || i > 85) {
            return (i <= 60 || i >= 75) ? 3 : 2;
        }
        return 1;
    }

    public static int d(int i) {
        int i2;
        if (i < 30) {
            i2 = 100 - ((i * 2) / 3);
        } else {
            i2 = i < 190 ? 95 - (i / 2) : 0;
        }
        LogUtil.a(d, "score = " + i2);
        return i2;
    }

    public static long e(long j, String str, int i) {
        if (TextUtils.isEmpty(str)) {
            str = "+8:00";
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        calendar.setTimeZone(TimeZone.getTimeZone("GMT" + str));
        calendar.add(5, i);
        calendar.set(11, 20);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    public static int b(double d2, long j) {
        int i = ((int) (((long) d2) - j)) / 60000;
        if (i < 0) {
            return 0;
        }
        return i;
    }

    public static int b(float f) {
        if (f >= 0.0f && f < 0.5f) {
            return 20;
        }
        if (f >= 0.5f && f < 0.6f) {
            return d(1, new double[]{f, 0.5d, 40.0d, 20.0d, 0.6000000238418579d, 0.5d, 20.0d});
        }
        if (f >= 0.6f && f < 0.8f) {
            return d(1, new double[]{f, 0.6000000238418579d, 60.0d, 40.0d, 0.800000011920929d, 0.6000000238418579d, 40.0d});
        }
        if (f >= 0.8f && f < 0.85f) {
            return d(1, new double[]{f, 0.800000011920929d, 70.0d, 60.0d, 0.8500000238418579d, 0.800000011920929d, 60.0d});
        }
        if (f >= 0.85f && f < 0.9f) {
            return d(1, new double[]{f, 0.8500000238418579d, 80.0d, 70.0d, 0.8999999761581421d, 0.8500000238418579d, 70.0d});
        }
        if (f >= 0.9f && f < 0.95f) {
            return d(1, new double[]{f, 0.8999999761581421d, 90.0d, 80.0d, 0.949999988079071d, 0.8999999761581421d, 80.0d});
        }
        if (f >= 0.95f) {
            return d(1, new double[]{f, 0.949999988079071d, 100.0d, 90.0d, 1.0d, 0.949999988079071d, 90.0d});
        }
        LogUtil.a(d, "effieiceny wrong !");
        return 100;
    }

    public static int b(int i) {
        if (i >= 0 && i < 240) {
            return 20;
        }
        if (i >= 240 && i < 288) {
            return d(0, new double[]{20.0d, i, 240.0d, 40.0d, 20.0d, 288.0d, 240.0d});
        }
        if (i >= 288 && i < 336) {
            return d(0, new double[]{40.0d, i, 288.0d, 60.0d, 40.0d, 336.0d, 288.0d});
        }
        if (i >= 336 && i < 384) {
            return d(0, new double[]{60.0d, i, 336.0d, 70.0d, 60.0d, 384.0d, 336.0d});
        }
        if (i >= 384 && i < 432) {
            return d(0, new double[]{70.0d, i, 384.0d, 80.0d, 70.0d, 432.0d, 384.0d});
        }
        if (i >= 432 && i < 480) {
            return d(0, new double[]{80.0d, i, 432.0d, 90.0d, 80.0d, 480.0d, 432.0d});
        }
        if (i >= 480 && i < 504) {
            return d(0, new double[]{90.0d, i, 480.0d, 100.0d, 90.0d, 504.0d, 480.0d});
        }
        if (i >= 504 && i < 516) {
            return d(0, new double[]{100.0d, i, 504.0d, 90.0d, 100.0d, 516.0d, 504.0d});
        }
        if (i >= 516 && i < 528) {
            return d(0, new double[]{90.0d, i, 516.0d, 80.0d, 90.0d, 528.0d, 516.0d});
        }
        if (i >= 528 && i < 570) {
            return d(0, new double[]{80.0d, i, 528.0d, 70.0d, 80.0d, 570.0d, 528.0d});
        }
        if (i >= 570 && i < 600) {
            return d(0, new double[]{70.0d, i, 570.0d, 60.0d, 70.0d, 600.0d, 570.0d});
        }
        return d(0, new double[]{60.0d, i, 600.0d, 40.0d, 60.0d, 1440.0d, 600.0d});
    }

    public static int e(int i) {
        if (i < 0) {
            return 0;
        }
        if (i >= 0 && i < 10) {
            return d(0, new double[]{100.0d, i, 0.0d, 90.0d, 100.0d, 10.0d, 0.0d});
        }
        if (i >= 10 && i < 20) {
            return d(0, new double[]{90.0d, i, 10.0d, 80.0d, 90.0d, 20.0d, 10.0d});
        }
        if (i >= 20 && i < 30) {
            return d(0, new double[]{80.0d, i, 20.0d, 70.0d, 80.0d, 30.0d, 20.0d});
        }
        if (i >= 30 && i < 60) {
            return d(0, new double[]{70.0d, i, 30.0d, 60.0d, 70.0d, 60.0d, 30.0d});
        }
        if (i < 60 || i >= 120) {
            return 40;
        }
        return d(0, new double[]{60.0d, i, 60.0d, 40.0d, 60.0d, 120.0d, 60.0d});
    }

    private static int d(int i, double[] dArr) {
        double d2;
        if (i == 0) {
            d2 = dArr[0] + (((dArr[1] - dArr[2]) * (dArr[3] - dArr[4])) / (dArr[5] - dArr[6]));
        } else {
            if (i != 1) {
                return 0;
            }
            d2 = (((dArr[0] - dArr[1]) * (dArr[2] - dArr[3])) / (dArr[4] - dArr[5])) + dArr[6];
        }
        return (int) d2;
    }

    public static SleepViewConstants.SleepTypeEnum e(HiHealthData hiHealthData, List<HiHealthData> list) {
        int i = hiHealthData.getInt("core_sleep_deep_key");
        int i2 = hiHealthData.getInt("core_sleep_shallow_key");
        int i3 = hiHealthData.getInt("core_sleep_wake_key");
        int i4 = hiHealthData.getInt("core_sleep_wake_count_key");
        int i5 = hiHealthData.getInt("core_sleep_wake_dream_key");
        int i6 = hiHealthData.getInt("core_sleep_day_sleep_time_key");
        int i7 = hiHealthData.getInt("core_sleep_score_key");
        int i8 = hiHealthData.getInt("core_sleep_total_sleep_time_key");
        int i9 = hiHealthData.getInt("common_sleep_duration_sleep_sum_key");
        int i10 = hiHealthData.getInt("data_session_manual_sleep_bed_time_key");
        int i11 = hiHealthData.getInt("sleep_device_category_key");
        long a2 = a(list);
        boolean z = i2 > 0 || i > 0 || i5 > 0;
        boolean z2 = i10 > 0;
        LogUtil.a(d, "deepSum is", Integer.valueOf(i), "shallowSum is", Integer.valueOf(i2), "coreWakeSum is", Integer.valueOf(i3), "wakeCount is", Integer.valueOf(i4), "dreamSum is", Integer.valueOf(i5), "noonSum is", Integer.valueOf(i6), "score is", Integer.valueOf(i7), "sleepTotalTime is", Integer.valueOf(i8), " manualBedTime is ", Integer.valueOf(i10), " isSum:", Boolean.valueOf(z), " isHasManual:", Boolean.valueOf(z2), " checkCommonTime:", Integer.valueOf(i9), " sleepDeviceCategory:", Integer.valueOf(i11));
        SleepViewConstants.SleepTypeEnum sleepTypeEnum = SleepViewConstants.SleepTypeEnum.COMMON;
        if (i11 != 0) {
            if (z) {
                sleepTypeEnum = g(i11);
            } else if (i9 != 0) {
                sleepTypeEnum = SleepViewConstants.SleepTypeEnum.COMMON;
            } else if (a(i11)) {
                sleepTypeEnum = SleepViewConstants.SleepTypeEnum.MANUAL;
            } else if (i6 != 0) {
                sleepTypeEnum = g(i11);
            }
        } else if (z) {
            if (i7 != 0 || i5 != 0) {
                sleepTypeEnum = SleepViewConstants.SleepTypeEnum.SCIENCE;
            } else if (a2 > 0) {
                sleepTypeEnum = SleepViewConstants.SleepTypeEnum.PHONE;
            }
        } else if (i9 != 0) {
            sleepTypeEnum = SleepViewConstants.SleepTypeEnum.COMMON;
        } else if (z2) {
            sleepTypeEnum = SleepViewConstants.SleepTypeEnum.MANUAL;
        } else if (i6 != 0) {
            sleepTypeEnum = SleepViewConstants.SleepTypeEnum.SCIENCE;
        }
        LogUtil.a(d, "getSleepDeviceCategory sleepType: ", sleepTypeEnum);
        return sleepTypeEnum;
    }

    private static SleepViewConstants.SleepTypeEnum g(int i) {
        SleepViewConstants.SleepTypeEnum sleepTypeEnum = SleepViewConstants.SleepTypeEnum.COMMON;
        if (i(i)) {
            sleepTypeEnum = SleepViewConstants.SleepTypeEnum.SCIENCE;
        } else if (f(i)) {
            sleepTypeEnum = SleepViewConstants.SleepTypeEnum.PHONE;
        }
        LogUtil.a(d, "judgeScienceAndPhone:", sleepTypeEnum);
        return sleepTypeEnum;
    }

    public static long a(List<HiHealthData> list) {
        long j = -1;
        if (koq.b(list)) {
            return -1L;
        }
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData.getLong("bedTime") != 0) {
                j = hiHealthData.getLong("bedTime");
            }
        }
        return j;
    }

    public static int c(List<Integer> list, int i, int i2) {
        int i3 = 0;
        if (i2 == 0 || list == null) {
            return 0;
        }
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue() - i;
            i3 += intValue * intValue;
        }
        return Math.round(i3 / i2);
    }

    public static int c(fdp fdpVar) {
        int v = ((fdl) fdpVar.c()).v() + ((fdo) fdpVar.f()).am() + ((fdq) fdpVar.j()).bp() + ((fdn) fdpVar.d()).ad();
        int y = ((fdl) fdpVar.c()).y();
        int as = ((fdo) fdpVar.f()).as();
        int bs = ((fdq) fdpVar.j()).bs();
        int ab = ((fdn) fdpVar.d()).ab();
        if (v > 0) {
            return Math.round((((y + as) + bs) + ab) / v);
        }
        return 0;
    }

    /* renamed from: fcj$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] d;

        static {
            int[] iArr = new int[SleepViewConstants.SleepTypeEnum.values().length];
            d = iArr;
            try {
                iArr[SleepViewConstants.SleepTypeEnum.SCIENCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                d[SleepViewConstants.SleepTypeEnum.PHONE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                d[SleepViewConstants.SleepTypeEnum.MANUAL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                d[SleepViewConstants.SleepTypeEnum.COMMON.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static fdi b(fdp fdpVar) {
        int i = AnonymousClass2.d[fdpVar.i().ordinal()];
        if (i == 1) {
            return fdpVar.j();
        }
        if (i == 2) {
            return fdpVar.f();
        }
        if (i == 3) {
            return fdpVar.d();
        }
        if (i == 4) {
            return fdpVar.c();
        }
        return new fdi();
    }

    public static boolean i(int i) {
        return i == Integer.parseInt("06E", 16) || i == Integer.parseInt("06D", 16);
    }

    public static boolean f(int i) {
        return nrq.b(i);
    }

    public static boolean a(int i) {
        return i == CommonUtil.c("001", 16);
    }

    public static boolean d(fdp fdpVar) {
        return fdpVar.i() == SleepViewConstants.SleepTypeEnum.PHONE && fdpVar.f().ad();
    }

    public static boolean e(fdp fdpVar) {
        return fdpVar.i() == SleepViewConstants.SleepTypeEnum.SCIENCE && fdpVar.j().bb();
    }

    public static int e(long j, int i) {
        if (i != 365) {
            return i;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j * 1000);
        int i2 = calendar.get(1);
        return ((i2 % 4 != 0 || i2 % 100 == 0) && i2 % 400 != 0) ? 365 : 366;
    }

    public static void d(HiHealthData hiHealthData, fdk fdkVar) {
        if (c(hiHealthData)) {
            long b = b(hiHealthData, fdkVar.n());
            long b2 = b(hiHealthData, hiHealthData.getLong("risingTime"));
            if (b < b2) {
                fdkVar.a(b2);
            } else if (b == b2) {
                fdkVar.d(b2);
            }
        }
    }

    public static boolean c(HiHealthData hiHealthData) {
        return hiHealthData.getLong("bedTime") < b(hiHealthData, hiHealthData.getLong("risingTime"));
    }

    public static long b(HiHealthData hiHealthData, long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        if (calendar.get(11) <= 20) {
            return nrq.d(j, hiHealthData.getTimeZone(), -1);
        }
        return nrq.d(j, hiHealthData.getTimeZone(), 0);
    }

    public static int d(SleepViewConstants.SleepTypeEnum sleepTypeEnum, HiHealthData hiHealthData, long j, int[] iArr) {
        long startTime;
        if (sleepTypeEnum == SleepViewConstants.SleepTypeEnum.MANUAL) {
            startTime = nrq.e(hiHealthData.getLong("data_session_manual_sleep_get_up_time_key"), hiHealthData.getTimeZone());
        } else {
            startTime = hiHealthData.getStartTime();
        }
        if (iArr[2] == 3) {
            return jdl.e(j, startTime) - 1;
        }
        int k = jdl.k(j);
        int k2 = jdl.k(startTime);
        if (k2 < k) {
            k2 += 12;
        }
        return k2 - k;
    }

    public static void a(HiHealthData hiHealthData, fdr fdrVar) {
        double d2 = hiHealthData.getDouble("core_sleep_fall_key");
        double d3 = hiHealthData.getDouble("core_sleep_wake_up_key");
        fdrVar.b((long) d2);
        fdrVar.c((long) d3);
        fdrVar.n(hiHealthData.getInt("core_sleep_deep_key"));
        fdrVar.z(hiHealthData.getInt("core_sleep_shallow_key"));
        fdrVar.u(hiHealthData.getInt("core_sleep_wake_dream_key"));
        fdrVar.af(hiHealthData.getInt("core_sleep_wake_key"));
        fdrVar.aa(hiHealthData.getInt("core_sleep_wake_count_key"));
        fdrVar.ad(hiHealthData.getInt("core_sleep_score_key"));
        fdrVar.ab(hiHealthData.getInt("core_sleep_snore_freq_key"));
        fdrVar.h(hiHealthData.getInt("core_sleep_deep_sleep_part_key"));
        fdrVar.b(hiHealthData.getInt("core_sleep_total_sleep_time_key"));
        fdrVar.a(hiHealthData.getDouble("core_sleep_valid_data_key"));
        fdrVar.y(hiHealthData.getInt("core_sleep_day_sleep_time_key"));
        fdrVar.e(hiHealthData.getTimeZone());
        fdrVar.e(hiHealthData.getStartTime());
        fdrVar.e(hiHealthData.getInt("core_sleep_bed_time_key"));
        fdrVar.d(hiHealthData.getLong("core_sleep_go_bed_key"));
        fdrVar.a(hiHealthData.getLong("core_sleep_off_bed_key"));
        fdrVar.e(Double.valueOf(hiHealthData.getDouble("minBreathrate")));
        fdrVar.c(Double.valueOf(hiHealthData.getDouble("maxBreathrate")));
        fdrVar.v(hiHealthData.getInt("minHeartrate"));
        fdrVar.q(hiHealthData.getInt("maxHeartrate"));
        fdrVar.b(Double.valueOf(hiHealthData.getDouble("minOxygenSaturation")));
        fdrVar.a(Double.valueOf(hiHealthData.getDouble("maxOxygenSaturation")));
        fdrVar.d(hiHealthData.getInt("core_sleep_latency_key"));
        fdrVar.a(c("avgBreathrate", hiHealthData));
        fdrVar.p(c("minBreathrateBaseline", hiHealthData));
        fdrVar.l(c("maxBreathrateBaseline", hiHealthData));
        fdrVar.j(c("breathrateDayToBaseline", hiHealthData));
        fdrVar.i(c("avgHeartrate", hiHealthData));
        fdrVar.x(c("minHeartrateBaseline", hiHealthData));
        fdrVar.r(c("maxHeartrateBaseline", hiHealthData));
        fdrVar.k(c("heartrateDayToBaseline", hiHealthData));
        fdrVar.g(c("avgHrv", hiHealthData));
        fdrVar.t(c("minHrvBaseline", hiHealthData));
        fdrVar.o(c("maxHrvBaseline", hiHealthData));
        fdrVar.m(c("hrvDayToBaseline", hiHealthData));
        fdrVar.f(c("avgOxygenSaturation", hiHealthData));
        fdrVar.w(c("minOxygenSaturationBaseline", hiHealthData));
        fdrVar.s(c("maxOxygenSaturationBaseline", hiHealthData));
        fdrVar.ac(c("oxygenSaturationDayToBaseline", hiHealthData));
    }

    private static int c(String str, HiHealthData hiHealthData) {
        if (hiHealthData.containsKey(str)) {
            return hiHealthData.getInt(str);
        }
        return -2;
    }

    public static void b(HiHealthData hiHealthData, List<HiHealthData> list, fdj fdjVar) {
        double d2 = hiHealthData.getDouble("core_sleep_fall_key");
        double d3 = hiHealthData.getDouble("core_sleep_wake_up_key");
        fdjVar.b((long) d2);
        fdjVar.c((long) d3);
        fdjVar.e(hiHealthData.getInt("data_session_manual_sleep_bed_time_key"));
        fdjVar.b(hiHealthData.getInt("core_sleep_total_sleep_time_key"));
        fdjVar.e(hiHealthData.getStartTime());
        if (koq.c(list)) {
            Iterator<HiHealthData> it = list.iterator();
            while (it.hasNext()) {
                c(fdjVar, it.next(), SleepViewConstants.SleepTypeEnum.MANUAL);
            }
        }
    }

    public static void c(fdi fdiVar, HiHealthData hiHealthData, SleepViewConstants.SleepTypeEnum sleepTypeEnum) {
        if (sleepTypeEnum == SleepViewConstants.SleepTypeEnum.PHONE && hiHealthData.containsKey("bedTime") && (fdiVar instanceof fdk)) {
            fdiVar.d(hiHealthData.containsKey("bedTime") ? nrq.c(fdiVar.d(), hiHealthData.getLong("bedTime"), 0) : -1L);
            fdiVar.a(hiHealthData.containsKey("risingTime") ? nrq.c(fdiVar.a(), hiHealthData.getLong("risingTime"), 1) : -1L);
            fdk fdkVar = (fdk) fdiVar;
            d(hiHealthData, fdkVar);
            e(hiHealthData, fdkVar);
            return;
        }
        if (sleepTypeEnum == SleepViewConstants.SleepTypeEnum.MANUAL && hiHealthData.containsKey("data_session_manual_sleep_go_to_bed_time_key")) {
            fdiVar.d(nrq.c(fdiVar.d(), hiHealthData.getLong("data_session_manual_sleep_go_to_bed_time_key"), 0));
            fdiVar.a(nrq.c(fdiVar.a(), hiHealthData.getLong("data_session_manual_sleep_get_up_time_key"), 1));
            fdiVar.b(nrq.c(fdiVar.e(), hiHealthData.getLong("data_session_manual_sleep_sleep_time_key"), 0));
            fdiVar.c(nrq.c(fdiVar.n(), hiHealthData.getLong("data_session_manual_sleep_wake_time_key"), 1));
        }
    }

    public static void e(HiHealthData hiHealthData, fdk fdkVar) {
        fdkVar.d(hiHealthData.containsKey("latency") ? hiHealthData.getInt("latency") : -1);
        fdkVar.h(hiHealthData.getInt("sleepScore"));
    }

    public static void d(HiHealthData hiHealthData, List<HiHealthData> list, fdk fdkVar) {
        double d2 = hiHealthData.getDouble("core_sleep_fall_key");
        double d3 = hiHealthData.getDouble("core_sleep_wake_up_key");
        fdkVar.b((long) d2);
        fdkVar.c((long) d3);
        fdkVar.a(hiHealthData.getInt("core_sleep_deep_key"));
        fdkVar.i(hiHealthData.getInt("core_sleep_shallow_key"));
        fdkVar.c(hiHealthData.getInt("core_sleep_day_sleep_time_key"));
        fdkVar.j(hiHealthData.getInt("core_sleep_wake_key"));
        fdkVar.b(hiHealthData.getInt("core_sleep_total_sleep_time_key"));
        fdkVar.e(hiHealthData.getTimeZone());
        fdkVar.e(hiHealthData.getStartTime());
        fdkVar.e(fdkVar.h() + fdkVar.z());
        fdkVar.f(hiHealthData.getInt("core_sleep_wake_count_key"));
        d(list, fdkVar);
    }

    public static void d(List<HiHealthData> list, fdk fdkVar) {
        if (koq.c(list)) {
            for (HiHealthData hiHealthData : list) {
                fdkVar.m(hiHealthData.getInt("wakeUpFeeling"));
                if (!b(hiHealthData)) {
                    c(fdkVar, hiHealthData, SleepViewConstants.SleepTypeEnum.PHONE);
                    fdkVar.g(hiHealthData.getInt("sleepEndReason"));
                }
            }
        }
    }

    private static boolean b(HiHealthData hiHealthData) {
        return hiHealthData.getInt("sleepEndReason") == 0 && hiHealthData.getInt("bedTime") == 0 && hiHealthData.getInt("risingTime") == 0 && hiHealthData.getInt("sleepScore") == 0 && hiHealthData.getInt("latency") == 0 && hiHealthData.getInt("wakeCount") == 0 && hiHealthData.getInt("wakeUpFeeling") > 0;
    }

    public static void c(HiHealthData hiHealthData, fdm fdmVar) {
        double d2 = hiHealthData.getDouble("core_sleep_fall_key");
        double d3 = hiHealthData.getDouble("core_sleep_wake_up_key");
        fdmVar.a(hiHealthData.getInt("sleep_deep_key") / 60);
        fdmVar.c(hiHealthData.getInt("sleep_shallow_key") / 60);
        fdmVar.f(hiHealthData.getInt("sleep_wake_count_key"));
        fdmVar.g(hiHealthData.getInt("common_sleep_duration_sleep_sum_key") / 60);
        fdmVar.b((long) d2);
        fdmVar.c((long) d3);
        fdmVar.b(hiHealthData.getInt("common_sleep_duration_sleep_sum_key") / 60);
        fdmVar.e(hiHealthData.getTimeZone());
        fdmVar.e(hiHealthData.getStartTime());
    }

    public static void a(fdp fdpVar, HiHealthData hiHealthData, List<HiHealthData> list, List<HiHealthData> list2) {
        SleepViewConstants.SleepTypeEnum e = e(hiHealthData, list2);
        fdpVar.c(e);
        if (e == SleepViewConstants.SleepTypeEnum.SCIENCE) {
            fdr fdrVar = new fdr();
            a(hiHealthData, fdrVar);
            fdpVar.c(fdrVar);
        } else if (e == SleepViewConstants.SleepTypeEnum.COMMON) {
            fdm fdmVar = new fdm();
            c(hiHealthData, fdmVar);
            fdpVar.c(fdmVar);
        } else if (e == SleepViewConstants.SleepTypeEnum.MANUAL) {
            fdj fdjVar = new fdj();
            b(hiHealthData, list, fdjVar);
            fdpVar.a(fdjVar);
        } else if (e == SleepViewConstants.SleepTypeEnum.PHONE) {
            fdk fdkVar = new fdk();
            d(hiHealthData, list2, fdkVar);
            fdpVar.d(fdkVar);
        }
        if (e == SleepViewConstants.SleepTypeEnum.PHONE || !koq.c(list2)) {
            return;
        }
        fdk fdkVar2 = new fdk();
        d(hiHealthData, list2, fdkVar2);
        fdpVar.d(fdkVar2);
    }

    public static void c(List<HiHealthData> list, fds fdsVar, boolean z) {
        if (koq.b(list, 0)) {
            return;
        }
        ArrayList<HiHealthData> arrayList = new ArrayList(list);
        for (HiHealthData hiHealthData : arrayList) {
            if (hiHealthData.getType() == 22106 || hiHealthData.getType() == 22107) {
                fdsVar.j(5);
                fdsVar.c(hiHealthData.getString("device_uniquecode"));
                LogUtil.a(d, "getDeviceType mSleepDeviceType is SLEEP_FROM_MANUAL, mDeviceId is ", fdsVar.i());
                return;
            }
        }
        HiHealthData hiHealthData2 = (HiHealthData) arrayList.get(0);
        int i = hiHealthData2.getInt("trackdata_deviceType");
        String string = hiHealthData2.getString("device_uniquecode");
        if (i == 0) {
            LogUtil.a(d, "getDeviceType deviceType is 0");
            return;
        }
        fdsVar.c(string);
        if (i == 32) {
            fdsVar.j(3);
        } else if (i == 506) {
            fdsVar.j(4);
        } else if (z) {
            fdsVar.j(1);
        } else {
            fdsVar.j(2);
        }
        LogUtil.a(d, "getDeviceType mSleepDeviceType is ", Integer.valueOf(fdsVar.g()));
    }

    public static boolean c(List<HiHealthData> list) {
        Iterator<HiHealthData> it = list.iterator();
        while (it.hasNext()) {
            if (!(it.next() instanceof HiHealthData)) {
                LogUtil.b(d, "judgeIfAllHiHealthData not HiHealthData");
                return false;
            }
        }
        return true;
    }

    public static List<Integer> d(List<Integer> list) {
        if (list.size() <= 3) {
            return list;
        }
        double a2 = UnitUtil.a(j(h(list)), 1);
        ArrayList arrayList = new ArrayList();
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(Double.valueOf(Math.abs(it.next().intValue() - a2)));
        }
        double a3 = UnitUtil.a(j(arrayList), 1);
        ArrayList arrayList2 = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            if (((Double) arrayList.get(i)).doubleValue() <= 3.0d * a3) {
                arrayList2.add(list.get(i));
            }
        }
        return ((double) arrayList2.size()) > ((double) list.size()) * 0.7d ? arrayList2 : list;
    }

    private static List<Double> h(List<Integer> list) {
        ArrayList arrayList = new ArrayList();
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(Double.valueOf(it.next().intValue()));
        }
        return arrayList;
    }

    private static double j(List<Double> list) {
        if (list.size() == 0) {
            return Double.NaN;
        }
        Double[] dArr = (Double[]) list.toArray(new Double[0]);
        Arrays.sort(dArr);
        if (dArr.length % 2 == 0) {
            return (dArr[dArr.length / 2].doubleValue() + dArr[(dArr.length / 2) - 1].doubleValue()) / 2.0d;
        }
        return dArr[dArr.length / 2].doubleValue();
    }

    public static int e(List<Integer> list) {
        Iterator<Integer> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            i += it.next().intValue();
        }
        return i;
    }

    public static List<List<HiHealthData>> e(List<HiHealthData> list, List<HiHealthData> list2, List<HiHealthData> list3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(list);
        arrayList.add(list2);
        arrayList.add(list3);
        return arrayList;
    }

    public static List<fdp> d(int i, long j, int[] iArr, List<List<HiHealthData>> list) {
        LogUtil.a(d, "sleepDatas:", list.toString());
        ArrayList arrayList = new ArrayList();
        int i2 = iArr[0];
        int e = i2 == 14 ? i2 / 2 : e(j, i2);
        int[] iArr2 = {iArr[0], iArr[1], i};
        List asList = Arrays.asList(new HiHealthData[e]);
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        for (HiHealthData hiHealthData : list.get(0)) {
            asList.set(d(SleepViewConstants.SleepTypeEnum.SCIENCE, hiHealthData, j, iArr2), hiHealthData);
        }
        for (HiHealthData hiHealthData2 : list.get(1)) {
            a(d(SleepViewConstants.SleepTypeEnum.MANUAL, hiHealthData2, j, iArr2), hiHealthData2, hashMap);
        }
        for (HiHealthData hiHealthData3 : list.get(2)) {
            int d2 = d(SleepViewConstants.SleepTypeEnum.PHONE, hiHealthData3, j, iArr2);
            if (d2 > 0 && c(hiHealthData3)) {
                a(d2 - 1, hiHealthData3, hashMap2);
                a(d2, hiHealthData3, hashMap2);
            } else {
                a(d2, hiHealthData3, hashMap2);
            }
        }
        for (int i3 = 0; i3 < e; i3++) {
            fdp fdpVar = new fdp(SleepViewConstants.ViewTypeEnum.DAY);
            if (asList.get(i3) != null) {
                a(fdpVar, (HiHealthData) asList.get(i3), (List) hashMap.get(Integer.valueOf(i3)), (List) hashMap2.get(Integer.valueOf(i3)));
            }
            arrayList.add(fdpVar);
        }
        LogUtil.a(d, "sleepViewDatas:", arrayList.toString());
        return arrayList;
    }

    private static void a(int i, HiHealthData hiHealthData, Map<Integer, List<HiHealthData>> map) {
        if (koq.c(map.get(Integer.valueOf(i)))) {
            List<HiHealthData> list = map.get(Integer.valueOf(i));
            list.add(hiHealthData);
            map.put(Integer.valueOf(i), list);
        } else {
            ArrayList arrayList = new ArrayList();
            arrayList.add(hiHealthData);
            map.put(Integer.valueOf(i), arrayList);
        }
    }

    public static void d(String[] strArr, String[] strArr2) {
        if (strArr == null || strArr2 == null || strArr.length != strArr2.length) {
            LogUtil.a(d, "sort fail");
            return;
        }
        for (int i = 0; i < strArr.length - 1; i++) {
            int i2 = 0;
            while (i2 < (strArr.length - 1) - i) {
                int i3 = i2 + 1;
                if (CommonUtil.g(strArr[i2]) > CommonUtil.g(strArr[i3])) {
                    String str = strArr[i2];
                    strArr[i2] = strArr[i3];
                    strArr[i3] = str;
                    String str2 = strArr2[i2];
                    strArr[i2] = str;
                    strArr[i3] = str2;
                }
                i2 = i3;
            }
        }
    }

    public static Map<String, SleepViewConstants.SleepTypeEnum> b(List<fdp> list) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        Date date = new Date();
        for (fdp fdpVar : list) {
            if (fdpVar.i() == SleepViewConstants.SleepTypeEnum.COMMON) {
                date.setTime(fdpVar.c().f());
            } else if (fdpVar.i() == SleepViewConstants.SleepTypeEnum.PHONE) {
                date.setTime(fdpVar.f().f());
            } else if (fdpVar.i() == SleepViewConstants.SleepTypeEnum.SCIENCE) {
                date.setTime(fdpVar.j().f());
            } else if (fdpVar.i() == SleepViewConstants.SleepTypeEnum.MANUAL) {
                date.setTime(fdpVar.d().f());
            }
            concurrentHashMap.put(new SimpleDateFormat("yyyy-MM-dd").format(date), fdpVar.i());
        }
        return concurrentHashMap;
    }
}
