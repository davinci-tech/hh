package defpackage;

import android.content.Context;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.dfx.DfxBaseHandler;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiTimeInterval;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealthservice.store.merge.HiDicHealthDataMerge;
import com.huawei.hihealthservice.store.stat.HiDicHealthDataStat;
import com.huawei.hms.network.embedded.y5;
import com.huawei.threecircle.ThreeCircleConfigUtil;
import com.huawei.utils.FoundationCommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.util.LogUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TimeZone;
import java.util.concurrent.PriorityBlockingQueue;

/* loaded from: classes7.dex */
public class iwp {
    private static volatile Queue<String> d = new PriorityBlockingQueue();

    public static boolean g(int i) {
        return i == 40002 || i == 40003 || i == 47101;
    }

    private static int i(int i) {
        Context e2 = BaseApplication.e();
        return ikr.b(e2).a(iip.b().a(e2.getPackageName()), i, ijf.d(e2).a(FoundationCommonUtil.getAndroidId(e2))).b();
    }

    public static double c(HiHealthData hiHealthData, int i) {
        Context e2 = BaseApplication.e();
        int c = HiDateUtil.c(hiHealthData.getStartTime());
        int e3 = ikr.b(e2).e(0, hiHealthData.getUserId(), 0);
        igo d2 = ijd.c(e2).d(c, i, e3);
        double l = d2 == null ? 0.0d : d2.l();
        LogUtil.d("HiH_3CircUtils", "getUserValue statType=", Integer.valueOf(i), ",statDate=", Integer.valueOf(c), ",statClient=", Integer.valueOf(e3), ",result=", Double.valueOf(l));
        return l;
    }

    public static int d(HiHealthData hiHealthData) {
        Context e2 = BaseApplication.e();
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(hiHealthData.getStartTime(), hiHealthData.getEndTime());
        double[] dArr = {2.147483647E9d, -1.0d};
        a(e2, hiHealthData, hiDataReadOption, dArr, new int[]{DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_STEP_GOAL_VALUE.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_STEP_USER_VALUE.value()});
        a(e2, hiHealthData, hiDataReadOption, dArr, new int[]{DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_GOAL_VALUE.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_USER_VALUE.value()});
        a(e2, hiHealthData, hiDataReadOption, dArr, new int[]{DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_GOAL_VALUE.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_USER_VALUE.value()});
        a(e2, hiHealthData, hiDataReadOption, dArr, new int[]{DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_GOAL_VALUE.value(), DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_USER_VALUE.value()});
        return (hiHealthData.getType() == DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_GOAL_VALUE.value() || hiHealthData.getType() == DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_USER_VALUE.value()) ? Math.round(dArr[0] / 1000.0d) > Math.round(dArr[1] / 1000.0d) ? 0 : 1 : Double.compare(dArr[0], dArr[1]) > 0 ? 0 : 1;
    }

    private static void a(Context context, HiHealthData hiHealthData, HiDataReadOption hiDataReadOption, double[] dArr, int[] iArr) {
        int type = hiHealthData.getType();
        if (type == iArr[0] || type == iArr[1]) {
            List<HiHealthData> c = ijj.a(context).c(hiDataReadOption, iArr, iis.d().g(hiHealthData.getUserId()));
            if (c == null) {
                ReleaseLogUtil.a("HiH_3CircUtils", "setUserOrGoalValue goalUserPair null");
                return;
            }
            if (c.size() != 2) {
                ReleaseLogUtil.a("HiH_3CircUtils", "setUserOrGoalValue failed by invalid goalUserPair ", HiJsonUtil.e(c));
            }
            for (HiHealthData hiHealthData2 : c) {
                if (hiHealthData2.getType() == iArr[0]) {
                    dArr[0] = hiHealthData2.getValue();
                } else if (hiHealthData2.getType() == iArr[1] && hiHealthData2.getValue() > dArr[1]) {
                    dArr[1] = hiHealthData2.getValue();
                }
            }
            ReleaseLogUtil.b("HiH_3CircUtils", "setUserOrGoalValue goal[", Integer.valueOf(iArr[0]), "]=", Double.valueOf(dArr[0]), ",user[", Integer.valueOf(iArr[1]), "]=", Double.valueOf(dArr[1]));
        }
    }

    public static void e(HiHealthData hiHealthData, Map<Integer, Double> map) {
        ReleaseLogUtil.b("HiH_3CircUtils", "updateThreeCircle paramMap=", HiJsonUtil.e(map));
        int i = i(hiHealthData.getUserId());
        TimeZone timeZone = TimeZone.getDefault();
        long e2 = HiDateUtil.e(hiHealthData.getStartTime(), timeZone);
        long b = HiDateUtil.b(hiHealthData.getStartTime(), timeZone);
        HiDicHealthDataMerge hiDicHealthDataMerge = new HiDicHealthDataMerge(BaseApplication.e());
        for (Map.Entry<Integer, Double> entry : map.entrySet()) {
            HiHealthData hiHealthData2 = new HiHealthData(entry.getKey().intValue());
            hiHealthData2.setClientId(i);
            hiHealthData2.setValue(c(entry.getValue()));
            hiHealthData2.setTimeInterval(e2, b);
            hiHealthData2.setUserId(hiHealthData.getUserId());
            hiHealthData2.setTimeZone(hiHealthData.getTimeZone());
            if (!hiDicHealthDataMerge.a(hiHealthData2)) {
                ReleaseLogUtil.a("HiH_3CircUtils", "updateThreeCircle merge initialize failed, typeId=", entry.getKey());
            } else {
                if (!hiDicHealthDataMerge.a(hiHealthData2, i, iis.d().g(hiHealthData.getUserId())) || !new HiDicHealthDataStat(BaseApplication.e()).c(hiHealthData2)) {
                    ReleaseLogUtil.c("HiH_3CircUtils", "updateThreeCircle() failed when updating ", entry.getKey());
                    return;
                }
                if (entry.getKey().intValue() == DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_STEP_USER_VALUE.value()) {
                    iwl.f(hiHealthData2);
                } else if (entry.getKey().intValue() == DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_USER_VALUE.value()) {
                    iwl.c(hiHealthData2);
                } else if (entry.getKey().intValue() == DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_USER_VALUE.value()) {
                    iwl.i(hiHealthData2);
                } else if (entry.getKey().intValue() == DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_USER_VALUE.value()) {
                    iwl.b(hiHealthData2);
                }
            }
        }
    }

    private static int c(Double d2) {
        if (d2 == null) {
            return 0;
        }
        int intValue = d2.intValue();
        if (Math.abs(d2.floatValue() - intValue) >= 1.0E-6d) {
            LogUtil.c("HiH_3CircUtils", "getIntFromDouble lost value, origin=", d2);
        }
        return intValue;
    }

    private static boolean d(int i, HiTimeInterval hiTimeInterval, int i2, int i3, int i4) {
        long startTime = hiTimeInterval.getStartTime();
        long endTime = hiTimeInterval.getEndTime();
        ReleaseLogUtil.b("HiH_3CircUtils", "isActiveHourInner startTime=", Long.valueOf(startTime), ", endTime=", Long.valueOf(endTime));
        Context e2 = BaseApplication.e();
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(startTime, endTime);
        hiDataReadOption.setType(new int[]{i});
        List<HiHealthData> d2 = iiy.e(e2).d(hiDataReadOption, i, iis.d().g(i4), (ifl) null);
        if (koq.b(d2)) {
            ReleaseLogUtil.a("HiH_3CircUtils", "isActiveHourInner not data between ", Long.valueOf(startTime), " and ", Long.valueOf(endTime));
            return false;
        }
        Iterator<HiHealthData> it = d2.iterator();
        double d3 = 0.0d;
        while (it.hasNext()) {
            double value = it.next().getValue();
            if (value >= i2) {
                return true;
            }
            d3 += value;
            if (d3 >= i3) {
                return true;
            }
        }
        return false;
    }

    public static boolean d(HiHealthData hiHealthData, int i) {
        if (hiHealthData == null) {
            return false;
        }
        long a2 = ggl.a(hiHealthData.getStartTime());
        if (d.contains(d(a2, hiHealthData.getUserId())) || !e(hiHealthData, -1)) {
            return false;
        }
        HiTimeInterval hiTimeInterval = new HiTimeInterval(a2, ggl.d(hiHealthData.getStartTime()));
        if (i == 2) {
            return d(i, hiTimeInterval, 40, 100, hiHealthData.getUserId());
        }
        if (i == 4) {
            return d(i, hiTimeInterval, 2400, y5.h, hiHealthData.getUserId());
        }
        if (i == 7) {
            return d(i, hiTimeInterval, 1, 1, hiHealthData.getUserId());
        }
        LogUtil.d("HiH_3CircUtils", "isActiveHourByType unsupported type=", Integer.valueOf(i));
        return false;
    }

    public static void e(HiHealthData hiHealthData) {
        long a2 = ggl.a(hiHealthData.getStartTime());
        long d2 = ggl.d(hiHealthData.getStartTime());
        ReleaseLogUtil.b("HiH_3CircUtils", "activeHour startTime=", Long.valueOf(a2), ", endTime=", Long.valueOf(d2));
        HiHealthData hiHealthData2 = new HiHealthData(DicDataTypeUtil.DataType.ACTIVE_HOUR_IS_ACTIVE.value());
        hiHealthData2.setValue(1.0d);
        hiHealthData2.setTimeInterval(a2, d2);
        int i = i(hiHealthData.getUserId());
        hiHealthData2.setUserId(hiHealthData.getUserId());
        hiHealthData2.setClientId(i);
        HiDicHealthDataMerge hiDicHealthDataMerge = new HiDicHealthDataMerge(BaseApplication.e());
        if (!hiDicHealthDataMerge.a(hiHealthData2)) {
            ReleaseLogUtil.a("HiH_3CircUtils", "activeHour failed by merge initialize");
            return;
        }
        if (hiDicHealthDataMerge.a(hiHealthData2, i, iis.d().g(hiHealthData.getUserId())) && new HiDicHealthDataStat(BaseApplication.e()).c(hiHealthData2)) {
            String d3 = d(a2, hiHealthData.getUserId());
            if (d.contains(d3)) {
                return;
            }
            if (d.size() >= 72) {
                d.poll();
            }
            d.add(d3);
            iwl.c().j(hiHealthData2);
        }
    }

    public static boolean a(HiHealthData hiHealthData) {
        return e(hiHealthData, -30);
    }

    public static List<HiHealthData> d(List<HiHealthData> list) {
        if (koq.b(list)) {
            return Collections.emptyList();
        }
        HashMap hashMap = new HashMap(16);
        for (HiHealthData hiHealthData : list) {
            String str = hiHealthData.getType() + "_" + ggl.a(hiHealthData.getStartTime()) + "_" + hiHealthData.getUserId();
            if (!hashMap.containsKey(str)) {
                hashMap.put(str, hiHealthData);
            }
        }
        return new ArrayList(hashMap.values());
    }

    private static boolean e(HiHealthData hiHealthData, int i) {
        long currentTimeMillis = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(currentTimeMillis);
        calendar.add(5, i);
        calendar.set(14, 0);
        calendar.set(13, 0);
        calendar.set(12, 0);
        calendar.set(11, 0);
        return hiHealthData.getStartTime() >= calendar.getTimeInMillis() && hiHealthData.getStartTime() <= currentTimeMillis + 86400000;
    }

    private static String d(long j, int i) {
        return j + "_" + i;
    }

    public static double a(ThreeCircleConfigUtil.ThreeCircleConfig threeCircleConfig) {
        return ThreeCircleConfigUtil.d(threeCircleConfig) ? 1.0d : 0.0d;
    }

    public static boolean b(int i) {
        return i == DicDataTypeUtil.DataType.ACTIVE_HOUR_IS_ACTIVE.value() || i == DicDataTypeUtil.DataType.ACTIVE_HOUR.value();
    }

    public static boolean j(int i) {
        return i == DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_STEP_GOAL_VALUE.value() || i == DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_STEP_USER_VALUE.value();
    }

    public static boolean d(int i) {
        return i == DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_GOAL_VALUE.value() || i == DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_USER_VALUE.value();
    }

    public static boolean e(int i) {
        return i == DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_GOAL_VALUE.value() || i == DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_USER_VALUE.value();
    }

    public static boolean a(int i) {
        return i == DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_GOAL_VALUE.value() || i == DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_USER_VALUE.value();
    }

    public static boolean c(int i) {
        boolean z = i == DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_STEP_GOAL_STATE.value() || i == DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_GOAL_STATE.value() || i == DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_GOAL_STATE.value() || i == DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_GOAL_STATE.value();
        if (z) {
            LogUtil.c("HiH_3CircUtils", "isInvalidInsertType() detected invalid type=", Integer.valueOf(i));
        }
        return z;
    }

    public static boolean h(int i) {
        return i == DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_STEP_GOAL_STATE_STAT.value() || i == DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_GOAL_STATE_STAT.value() || i == DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_GOAL_STATE_STAT.value() || i == DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_GOAL_STATE_STAT.value() || i == DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_STEP_IS_RING_STAT.value();
    }

    public static boolean f(int i) {
        return i == DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_STEP_GOAL_STATE_STAT.value() || i == DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_STEP_GOAL_VALUE_STAT.value() || i == DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_STEP_USER_VALUE_STAT.value() || i == DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_STEP_IS_RING_STAT.value() || i == DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_GOAL_VALUE_STAT.value() || i == DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_USER_VALUE_STAT.value() || i == DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_GOAL_STATE_STAT.value() || i == DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_IS_RING_STAT.value() || i == DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_GOAL_VALUE_STAT.value() || i == DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_USER_VALUE_STAT.value() || i == DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_GOAL_STATE_STAT.value() || i == DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_IS_RING_STAT.value() || i == DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_GOAL_VALUE_STAT.value() || i == DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_USER_VALUE_STAT.value() || i == DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_GOAL_STATE_STAT.value() || i == DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_IS_RING_STAT.value();
    }

    public static void c(String str) {
        e eVar = new e("ThreeCircleDfx", "_threeCircleLog_%d", 2, 5120, false);
        eVar.e(str, eVar.getSaveFile());
    }

    static class e extends DfxBaseHandler {
        public e(String str, String str2, int i, int i2, boolean z) {
            super(str, str2, i, i2, z);
        }

        public void e(String str, File file) {
            super.saveInfo(str, file);
        }
    }
}
