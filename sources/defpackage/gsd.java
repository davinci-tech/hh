package defpackage;

import android.text.TextUtils;
import android.util.SparseArray;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.health.weight.bean.WeightTargetDifferences;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes4.dex */
public class gsd {
    private static WeightTargetDifferences c;
    private static gsi e;
    private static final String d = String.valueOf(PrebakedEffectId.RT_HEARTBEAT);

    /* renamed from: a, reason: collision with root package name */
    private static final List<cwk> f12903a = new CopyOnWriteArrayList();
    private static final SparseArray<Map<Integer, List<HiHealthData>>> b = new SparseArray<>();

    private static void a(String str, String str2) {
        LogUtil.c("WeightCacheUtils", "setStringForSharedPreferences key ", str, " value ", str2);
        jfa.f(d, str, str2);
    }

    private static String e(String str) {
        return jfa.d(d, str, "");
    }

    private static void d(String str, long j) {
        LogUtil.c("WeightCacheUtils", "setLongForSharedPreferences key ", str, " value ", Long.valueOf(j));
        jfa.b(d, str, j);
    }

    private static long c(String str) {
        return jfa.d(d, str, 0L);
    }

    private static void e(String str, float f) {
        LogUtil.c("WeightCacheUtils", "setFloatForSharedPreferences key ", str, " value ", Float.valueOf(f));
        jfa.d(d, str, f);
    }

    private static float b(String str) {
        return jfa.c(d, str, 0.0f);
    }

    public static void b(long j) {
        d("SYNC_SAMPLE_CONFIG_TIME_MILLIS", j);
    }

    public static boolean n() {
        long c2 = c("SYNC_SAMPLE_CONFIG_TIME_MILLIS");
        ReleaseLogUtil.b("R_WeightCacheUtils", "isSyncSampleConfigSuccess syncSampleConfigTimeMillis ", Long.valueOf(c2));
        return c2 > 0;
    }

    public static long h() {
        return c("QUERY_WEIGHT_START_TIME_MILLIS");
    }

    public static void a(long j) {
        boolean a2 = jfa.a();
        ReleaseLogUtil.b("R_WeightCacheUtils", "setQueryWeightStartTimeMillis isPullAllStatus ", Boolean.valueOf(a2), " timeMillis ", Long.valueOf(j));
        if (a2) {
            d("QUERY_WEIGHT_START_TIME_MILLIS", j);
        }
    }

    public static float g() {
        return b("AI_PLAN_START_WEIGHT");
    }

    public static void d(float f) {
        e("AI_PLAN_START_WEIGHT", f);
    }

    public static float i() {
        return b("AI_PLAN_TARGET_WEIGHT");
    }

    public static void e(float f) {
        e("AI_PLAN_TARGET_WEIGHT", f);
    }

    public static float b() {
        return b("AI_PLAN_FAT_RATE");
    }

    public static void c(float f) {
        e("AI_PLAN_FAT_RATE", f);
    }

    public static boolean l() {
        long c2 = c("DIET_SUPPORT_TIME_MILLIS");
        ReleaseLogUtil.b("R_WeightCacheUtils", "isNeedGetSupportDietSetting timeMillis ", Long.valueOf(c2));
        return c2 <= 0 || Math.abs(System.currentTimeMillis() - c2) >= 3600000;
    }

    public static void v() {
        if (Utils.i()) {
            d("DIET_SUPPORT_TIME_MILLIS", System.currentTimeMillis());
        }
    }

    public static void y() {
        if (Utils.i()) {
            d("SHOW_USER_INFO_DIALOG_TIME_MILLIS", System.currentTimeMillis());
        }
    }

    public static boolean m() {
        long c2 = c("SHOW_USER_INFO_DIALOG_TIME_MILLIS");
        int e2 = jdl.e(c2, System.currentTimeMillis());
        ReleaseLogUtil.b("R_WeightCacheUtils", "isSupportShowUserInfoDialog timeMillis ", Long.valueOf(c2), " intervalDay ", Integer.valueOf(e2));
        return c2 <= 0 || Math.abs(e2) >= 8;
    }

    public static void c(long j) {
        if (Utils.i()) {
            d("FAT_REDUCTION_SHAPING_TIME_MILLIS", j);
        }
    }

    public static boolean a() {
        long c2 = c("FAT_REDUCTION_SHAPING_TIME_MILLIS");
        ReleaseLogUtil.b("R_WeightCacheUtils", "getFatReductionShapingState timeMillis ", Long.valueOf(c2));
        return c2 > 0;
    }

    public static void a(boolean z, String str, long j) {
        if (Utils.i() && z) {
            d(str, j);
        }
    }

    public static boolean a(String str) {
        long c2 = c(str);
        ReleaseLogUtil.b("R_WeightCacheUtils", "isSupportDietSetting timeMillis ", Long.valueOf(c2));
        return c2 > 0 && jdl.b(c2, System.currentTimeMillis()) <= 1;
    }

    public static void b(Map<String, String> map) {
        String e2;
        if (map == null) {
            ReleaseLogUtil.a("R_WeightCacheUtils", "setDietRecordCache dietRecord is null");
            e2 = "";
        } else {
            e2 = HiJsonUtil.e(map);
        }
        a("DIET_ANALYSIS", e2);
    }

    public static Map<String, String> c() {
        String e2 = e("DIET_ANALYSIS");
        if (TextUtils.isEmpty(e2)) {
            ReleaseLogUtil.a("R_WeightCacheUtils", "getDietAnalysisCache json ", e2);
            return null;
        }
        return (Map) HiJsonUtil.b(e2, new TypeToken<Map<String, String>>() { // from class: gsd.3
        }.getType());
    }

    public static void a(quh quhVar) {
        String e2;
        if (quhVar == null) {
            ReleaseLogUtil.a("R_WeightCacheUtils", "setDietRecordCache dietRecord is null");
            e2 = "";
        } else {
            e2 = HiJsonUtil.e(quhVar);
        }
        a("DIET_RECORD", e2);
    }

    public static quh e() {
        String e2 = e("DIET_RECORD");
        if (TextUtils.isEmpty(e2)) {
            ReleaseLogUtil.a("R_WeightCacheUtils", "getDietRecordCache json ", e2);
            return null;
        }
        return (quh) HiJsonUtil.e(e2, quh.class);
    }

    public static void e(List<quh> list) {
        String e2;
        if (CollectionUtils.d(list)) {
            ReleaseLogUtil.a("R_WeightCacheUtils", "setDietRecordListCache list is null");
            e2 = "";
        } else {
            e2 = HiJsonUtil.e(list);
        }
        a("DIET_RECORD_LIST", e2);
    }

    public static List<quh> d() {
        String e2 = e("DIET_RECORD_LIST");
        if (TextUtils.isEmpty(e2)) {
            ReleaseLogUtil.a("R_WeightCacheUtils", "getDietRecordListCache json ", e2);
            return Collections.emptyList();
        }
        return (List) HiJsonUtil.b(e2, new TypeToken<List<quh>>() { // from class: gsd.5
        }.getType());
    }

    public static void b(List<cwk> list) {
        if (CollectionUtils.d(list)) {
            ReleaseLogUtil.a("R_WeightCacheUtils", "setFoodList foodList ", list);
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (cwk cwkVar : f12903a) {
            if (cwkVar == null) {
                ReleaseLogUtil.a("R_WeightCacheUtils", "setFoodList cacheFood is null sFoodList ", f12903a);
            } else {
                String d2 = cwkVar.d();
                if (TextUtils.isEmpty(d2)) {
                    ReleaseLogUtil.a("R_WeightCacheUtils", "setFoodList cacheFoodId ", d2, " cacheFood ", cwkVar);
                } else {
                    for (cwk cwkVar2 : list) {
                        if (cwkVar2 == null) {
                            ReleaseLogUtil.a("R_WeightCacheUtils", "setFoodList food is null foodList ", list);
                        } else if (TextUtils.equals(d2, cwkVar2.d())) {
                            arrayList.add(cwkVar);
                        }
                    }
                }
            }
        }
        if (!CollectionUtils.d(arrayList)) {
            f12903a.removeAll(arrayList);
        } else {
            f12903a.addAll(list);
        }
    }

    public static List<cwk> j() {
        return f12903a;
    }

    public static void b(long j, Map<Integer, List<HiHealthData>> map) {
        int c2 = DateFormatUtil.c(j, TimeZone.getDefault());
        LogUtil.c("WeightCacheUtils", "setRestingCalorie timeMillis ", Long.valueOf(j), " date ", Integer.valueOf(c2), " restingCalorie ", map);
        try {
            b.append(c2, map);
        } catch (ArrayIndexOutOfBoundsException e2) {
            ReleaseLogUtil.c("R_WeightCacheUtils", "setRestingCalorie exception ", ExceptionUtils.d(e2));
        }
    }

    public static Map<Integer, List<HiHealthData>> e(long j) {
        int i;
        int c2 = DateFormatUtil.c(j, TimeZone.getDefault());
        ReleaseLogUtil.b("R_WeightCacheUtils", "getRestingCalorie timeMillis ", Long.valueOf(j), " date ", Integer.valueOf(c2));
        try {
            i = b.indexOfKey(c2);
        } catch (ArrayIndexOutOfBoundsException e2) {
            ReleaseLogUtil.c("WeightCacheUtils", "getRestingCalorie exception ", ExceptionUtils.d(e2));
            i = -1;
        }
        if (i >= 0) {
            SparseArray<Map<Integer, List<HiHealthData>>> sparseArray = b;
            if (i < sparseArray.size()) {
                return sparseArray.get(c2);
            }
        }
        ReleaseLogUtil.a("R_WeightCacheUtils", "getRestingCalorie indexOfKey ", Integer.valueOf(i));
        return null;
    }

    public static void b(WeightTargetDifferences weightTargetDifferences) {
        c = weightTargetDifferences;
    }

    public static WeightTargetDifferences k() {
        return c;
    }

    public static void a(gsi gsiVar) {
        e = gsiVar;
    }

    public static gsi f() {
        return e;
    }

    public static void o() {
        s();
        t();
        r();
        p();
        q();
        ArrayList arrayList = new ArrayList(2);
        arrayList.add("SYNC_SAMPLE_CONFIG_TIME_MILLIS");
        arrayList.add("QUERY_WEIGHT_START_TIME_MILLIS");
        jfa.c(d, arrayList);
        ReleaseLogUtil.b("R_WeightCacheUtils", "reset deleteKeyListByUserId list ", arrayList);
    }

    public static void s() {
        f12903a.clear();
    }

    public static void t() {
        b.clear();
    }

    public static void r() {
        c = null;
    }

    public static void p() {
        e = null;
    }

    public static void q() {
        d("DIET_SUPPORT_TIME_MILLIS", 0L);
    }
}
