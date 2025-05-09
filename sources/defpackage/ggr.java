package defpackage;

import android.text.TextUtils;
import com.huawei.basefitnessadvice.model.FilterCondition;
import com.huawei.basefitnessadvice.model.FilterOption;
import com.huawei.basefitnessadvice.model.SearchCondition;
import com.huawei.basefitnessadvice.model.intplan.IntAction;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.basefitnessadvice.model.intplan.RecordData;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.marketing.utils.MarketingBiUtils;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.suggestion.ui.fitness.module.Motion;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.utils.Constants;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.pluginfitnessadvice.AtomicAction;
import com.huawei.pluginfitnessadvice.BelongInfo;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.HealthWorkout;
import com.huawei.trade.datatype.ProductSummary;
import com.huawei.trade.datatype.TradeViewInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class ggr {
    public static void c(Motion motion) {
        if (motion == null) {
            return;
        }
        String acquireId = motion.acquireId();
        HashMap hashMap = new HashMap(10);
        hashMap.put("click", 1);
        hashMap.put("date", Long.valueOf(System.currentTimeMillis()));
        hashMap.put("type", "");
        hashMap.put("acton_id", acquireId);
        hashMap.put("action_name", motion.acquireName());
        gge.e(AnalyticsValue.HEALTH_FITNESS_ACTION_DETAIL_1130029.value(), hashMap);
    }

    public static void e(FitWorkout fitWorkout) {
        if (fitWorkout == null || fitWorkout.getCourseBelongInfoByType(0) == null) {
            LogUtil.h("Suggestion_FitnessBiUtils", "doSeriesCourseBi mFitWorkout is not SeriesCourse");
            return;
        }
        BelongInfo courseBelongInfoByType = fitWorkout.getCourseBelongInfoByType(0);
        if (courseBelongInfoByType == null) {
            LogUtil.h("Suggestion_FitnessBiUtils", "startSeriesCourseBi belongInfo is null.");
            return;
        }
        HashMap hashMap = new HashMap(10);
        hashMap.put("click", 1);
        Integer commodityFlag = courseBelongInfoByType.getCommodityFlag();
        if (commodityFlag != null) {
            if (commodityFlag.intValue() == 2) {
                hashMap.put("type", 1);
            } else if (commodityFlag.intValue() == 1) {
                hashMap.put("type", 3);
            } else {
                hashMap.put("type", 2);
            }
        }
        hashMap.put("name", courseBelongInfoByType.getName());
        hashMap.put("id", courseBelongInfoByType.getId());
        gge.e(AnalyticsValue.SERIES_COURSE_USE_DEMAND.value(), hashMap);
    }

    public static void a(FitWorkout fitWorkout, Map<String, Object> map) {
        if (fitWorkout == null || map == null) {
            LogUtil.h("Suggestion_FitnessBiUtils", "doViewCourseDetailBi failed with workout is null");
            return;
        }
        HashMap hashMap = new HashMap(map);
        hashMap.put("click", 1);
        hashMap.put("topic_version", fitWorkout.accquireVersion());
        if (fitWorkout.isCustomCourse()) {
            hashMap.put("workout_name", fpq.e(BaseApplication.e()));
        } else {
            hashMap.put("workout_name", fitWorkout.acquireName());
        }
        String a2 = ffy.a(fitWorkout.acquireClasses());
        if (TextUtils.isEmpty(a2)) {
            a2 = ffy.c(fitWorkout.getPrimaryClassify());
        }
        hashMap.put("course_type", a2);
        hashMap.put("body_position", ffy.e(fitWorkout.acquireTrainingpoints()));
        hashMap.put("course_rate", fitWorkout.acquireDifficulty() + "");
        hashMap.put("course_time", fitWorkout.acquireDuration() + "");
        hashMap.put("workout_id", fitWorkout.acquireId());
        if (fitWorkout.getIsAi() == 1) {
            hashMap.put("isAICourse", true);
        } else {
            hashMap.put("isAICourse", false);
        }
        c("1130017", hashMap);
    }

    public static void d(String str, long j) {
        HashMap hashMap = new HashMap();
        hashMap.put("entrance", str);
        hashMap.put("stay_time", Long.valueOf(j));
        gge.e("1130016", hashMap);
    }

    public static void e(FitWorkout fitWorkout, Map<String, Object> map, boolean z) {
        c("1130008", fitWorkout, map, z);
    }

    public static void c(FitWorkout fitWorkout, Map<String, Object> map, boolean z) {
        c("1130007", fitWorkout, map, z);
    }

    private static void c(String str, FitWorkout fitWorkout, Map<String, Object> map, boolean z) {
        if (fitWorkout == null || map == null) {
            LogUtil.h("Suggestion_FitnessBiUtils", "doCourseBi failed with workout is null");
            return;
        }
        HashMap hashMap = new HashMap(map);
        hashMap.put("click", 1);
        hashMap.put("course_version", fitWorkout.accquireVersion());
        if (fitWorkout.isCustomCourse()) {
            hashMap.put("workout_name", fpq.e(BaseApplication.e()));
        } else {
            hashMap.put("workout_name", fitWorkout.acquireName());
        }
        String a2 = ffy.a(fitWorkout.acquireClasses());
        if (TextUtils.isEmpty(a2)) {
            a2 = ffy.c(fitWorkout.getPrimaryClassify());
        }
        hashMap.put("course_type", a2);
        hashMap.put("body_position", ffy.e(fitWorkout.acquireTrainingpoints()));
        hashMap.put("course_rate", String.valueOf(fitWorkout.acquireDifficulty()));
        hashMap.put("course_time", String.valueOf(fitWorkout.acquireDuration()));
        hashMap.put("workout_id", fitWorkout.acquireId());
        if (fitWorkout.getIsAi() == 0) {
            hashMap.put("isAICourse", null);
        } else {
            hashMap.put("isAICourse", Boolean.valueOf(z));
        }
        c(str, hashMap);
    }

    public static void b(int i, String str, String str2) {
        HashMap hashMap = new HashMap(4);
        hashMap.put("click", 1);
        hashMap.put("event", Integer.valueOf(i));
        hashMap.put("workout_id", str);
        hashMap.put("workout_name", str2);
        c("1120051", hashMap);
    }

    public static void a() {
        OpAnalyticsUtil.getInstance().setEventWithActionType(5, OperationKey.HEALTH_APP_RUN_START_2050005.value());
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("actiontype", String.valueOf(5));
        OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_PVUV_85050001.value(), linkedHashMap);
    }

    public static void c(final String str, final Map<String, Object> map) {
        if (map == null) {
            LogUtil.a("Suggestion_FitnessBiUtils", "sendBiDataEvent key:", str);
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: ggq
                @Override // java.lang.Runnable
                public final void run() {
                    gge.e(str, map);
                }
            });
        }
    }

    public static void c(int i, int i2) {
        HashMap hashMap = new HashMap(3);
        hashMap.put("click", 1);
        hashMap.put(WorkoutRecord.Extend.FIT_EXTEND_COURSE_TYPE, Integer.valueOf(i));
        hashMap.put("from", Integer.valueOf(i2));
        c("1130063", hashMap);
    }

    public static void b(int i, String str, int i2) {
        HashMap hashMap = new HashMap(4);
        hashMap.put("click", 1);
        hashMap.put(WorkoutRecord.Extend.FIT_EXTEND_COURSE_TYPE, Integer.valueOf(i));
        hashMap.put("word", str);
        hashMap.put("result", Integer.valueOf(i2));
        c("1130036", hashMap);
    }

    public static void b(String str) {
        HashMap hashMap = new HashMap(10);
        hashMap.put("click", 1);
        hashMap.put("type", str);
        c("1130053", hashMap);
    }

    public static void d(int i) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", 1);
        hashMap.put(WorkoutRecord.Extend.FIT_EXTEND_COURSE_TYPE, Integer.valueOf(i));
        c("1130064", hashMap);
    }

    public static void c(int i, String str) {
        HashMap hashMap = new HashMap(3);
        hashMap.put("click", 1);
        hashMap.put(WorkoutRecord.Extend.FIT_EXTEND_COURSE_TYPE, Integer.valueOf(i));
        hashMap.put("labelType", str);
        c("1130065", hashMap);
    }

    public static Map<String, Object> d(String str, SearchCondition searchCondition) {
        HashMap hashMap = new HashMap(5);
        if (searchCondition == null) {
            LogUtil.h("Suggestion_FitnessBiUtils", "getFilterMap workoutListBean == null");
            return hashMap;
        }
        hashMap.put("labelId", str);
        if (koq.b(searchCondition.getFilterConditions())) {
            return hashMap;
        }
        hashMap.put("filters", b(searchCondition));
        return hashMap;
    }

    private static Map<String, List<String>> b(SearchCondition searchCondition) {
        HashMap hashMap = new HashMap();
        for (FilterCondition filterCondition : searchCondition.getFilterConditions()) {
            if (filterCondition != null && !koq.b(filterCondition.getFilterOptions())) {
                ArrayList arrayList = new ArrayList();
                for (FilterOption filterOption : filterCondition.getFilterOptions()) {
                    if (filterOption != null) {
                        arrayList.add(filterOption.getName());
                    }
                }
                hashMap.put(filterCondition.getName(), arrayList);
            }
        }
        return hashMap;
    }

    public static Map<String, Object> a(FitWorkout fitWorkout, String str, String str2, int i) {
        HashMap hashMap = new HashMap(10);
        hashMap.put("click", 1);
        hashMap.put("workout_name", fitWorkout.acquireName());
        hashMap.put("workout_id", fitWorkout.acquireId());
        hashMap.put("body_position", ffy.e(fitWorkout.acquireTrainingpoints()));
        hashMap.put("course_rate", String.valueOf(fitWorkout.acquireDifficulty()));
        hashMap.put("course_time", String.valueOf(fitWorkout.acquireDuration()));
        hashMap.put("resourceType", Integer.valueOf(fpq.b(fitWorkout)));
        hashMap.put("pageId", Integer.valueOf(gge.b(str2)));
        hashMap.put("labelId", str);
        hashMap.put("pullOrder", Integer.valueOf(i));
        String a2 = ffy.a(fitWorkout.acquireClasses());
        if (TextUtils.isEmpty(a2)) {
            a2 = ffy.c(fitWorkout.getPrimaryClassify());
        }
        hashMap.put("course_type", a2);
        return hashMap;
    }

    public static void e(Map<String, Object> map) {
        if (map == null) {
            LogUtil.h("Suggestion_FitnessBiUtils", "sendAllCourseExposureMapClick map == null");
            return;
        }
        HashMap hashMap = new HashMap(map);
        hashMap.put("event", 1);
        c("1130068", hashMap);
    }

    public static void c(Map<String, Object> map, String str) {
        if (map == null) {
            LogUtil.h("Suggestion_FitnessBiUtils", "sendAllCourseExposureMapClick map == null");
            return;
        }
        HashMap hashMap = new HashMap(map);
        hashMap.put("event", 0);
        c(str, hashMap);
    }

    public static Map<String, Object> a(HealthWorkout healthWorkout, int i) {
        if (healthWorkout == null) {
            return new HashMap();
        }
        HashMap hashMap = new HashMap(7);
        hashMap.put("click", 1);
        hashMap.put("workout_name", healthWorkout.acquireName());
        hashMap.put("itemId", healthWorkout.acquireId());
        int acquireDuration = healthWorkout.acquireDuration();
        if (acquireDuration == 0) {
            acquireDuration = healthWorkout.getChapterCount();
        }
        hashMap.put("course_time", String.valueOf(acquireDuration));
        hashMap.put("resourceType", Integer.valueOf(healthWorkout.getCommodityFlag()));
        hashMap.put("pullOrder", Integer.valueOf(i));
        hashMap.put("algId", 0);
        return hashMap;
    }

    public static void e(String str) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", 1);
        hashMap.put("labelType", str);
        c("1130073", hashMap);
    }

    public static void d(String str) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", 1);
        hashMap.put("labelId", str);
        c("1130072", hashMap);
    }

    public static void a(Map<String, Object> map) {
        if (map == null) {
            LogUtil.h("Suggestion_FitnessBiUtils", "sendAllCourseExposureMapClick map == null");
            return;
        }
        HashMap hashMap = new HashMap(map);
        hashMap.put("event", 1);
        c("1130069", hashMap);
    }

    public static String d(WorkoutRecord workoutRecord) {
        if (workoutRecord == null || !workoutRecord.isAiWorkout()) {
            return Constants.NULL;
        }
        if (workoutRecord.getRecordModeType() == 1) {
            return Boolean.toString(true);
        }
        return Boolean.toString(false);
    }

    public static int e(IntPlan intPlan) {
        if (intPlan.getPlanType().equals(IntPlan.PlanType.AI_RUN_PLAN)) {
            return 0;
        }
        return intPlan.getPlanType().equals(IntPlan.PlanType.AI_FITNESS_PLAN) ? 1 : 2;
    }

    public static void d(int i, int i2) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        if (i == 0) {
            hashMap.put(BleConstants.SPORT_TYPE, "SP10001");
        } else {
            hashMap.put(BleConstants.SPORT_TYPE, "SP" + i);
        }
        hashMap.put("from", 1);
        hashMap.put("event", Integer.valueOf(i2));
        gge.e(AnalyticsValue.INT_PLAN_1120055.value(), hashMap);
    }

    public static void c() {
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", 1);
        hashMap.put("type", 1);
        gge.e(AnalyticsValue.INT_PLAN_2040152.value(), hashMap);
    }

    public static void b(int i, int i2) {
        HashMap hashMap = new HashMap(4);
        if (i == 10) {
            hashMap.put("type", 1);
        } else if (i == 20) {
            hashMap.put("type", 2);
        } else if (i == 30) {
            hashMap.put("type", 3);
        } else {
            LogUtil.h("Suggestion_FitnessBiUtils", "setEventForFoodRecord type ", Integer.valueOf(i));
        }
        hashMap.put("entrance", 6);
        hashMap.put("node", Integer.valueOf(i2));
        hashMap.put("click", 1);
        gge.e(AnalyticsValue.INT_PLAN_2040117.value(), hashMap);
    }

    public static void b() {
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", 1);
        hashMap.put("enter", 0);
        gge.e(AnalyticsValue.INT_PLAN_2040150.value(), hashMap);
    }

    public static void d(boolean z, String str, String str2) {
        HashMap hashMap = new HashMap(4);
        hashMap.put("click", 1);
        hashMap.put("result", String.valueOf(z));
        hashMap.put("originCourse", str);
        hashMap.put("NewCourse", str2);
        gge.e(AnalyticsValue.INT_PLAN_1120054.value(), hashMap);
    }

    public static void a(int i, List<IntAction> list) {
        HashMap hashMap = new HashMap(3);
        hashMap.put("isOffDay", Boolean.valueOf(koq.b(list)));
        hashMap.put("click", 1);
        hashMap.put("event", Integer.valueOf(i));
        gge.e(AnalyticsValue.INT_PLAN_1120035.value(), hashMap);
    }

    public static void d(int i, int i2, int i3) {
        HashMap hashMap = new HashMap(4);
        hashMap.put("click", 1);
        hashMap.put("from", Integer.valueOf(i));
        hashMap.put("event", Integer.valueOf(i2));
        if (i3 > 0) {
            hashMap.put("leaveDays", Integer.valueOf(i3));
        }
        gge.e(AnalyticsValue.INT_PLAN_1120053.value(), hashMap);
    }

    public static void c(List<IntAction> list, List<IntAction> list2, List<RecordData> list3, Map<String, FitWorkout> map) {
        HashMap hashMap = new HashMap(4);
        hashMap.put("finishNum", Integer.valueOf(a(list) + a(list2)));
        int size = koq.b(list2) ? 0 : list2.size();
        if (koq.c(list)) {
            size += list.size();
        }
        hashMap.put("goalNum", Integer.valueOf(size));
        boolean c = c(list, map);
        if (!c) {
            c = c(list2, map);
        }
        hashMap.put("hasAICourse", String.valueOf(c));
        if (koq.c(list3)) {
            ArrayList arrayList = new ArrayList();
            for (RecordData recordData : list3) {
                if (recordData != null) {
                    arrayList.add(Integer.valueOf(recordData.getSportType()));
                }
            }
            hashMap.put(BleConstants.SPORT_TYPE, arrayList);
        }
        gge.e(AnalyticsValue.INT_PLAN_1120038.value(), hashMap);
    }

    private static int a(List<IntAction> list) {
        int i = 0;
        if (koq.b(list)) {
            return 0;
        }
        for (IntAction intAction : list) {
            if (intAction != null) {
                i += intAction.getPunchFlag();
            }
        }
        return i;
    }

    private static boolean c(List<IntAction> list, Map<String, FitWorkout> map) {
        FitWorkout fitWorkout;
        if (koq.b(list) || map == null) {
            return false;
        }
        while (true) {
            boolean z = false;
            for (IntAction intAction : list) {
                if (!z && intAction != null && (fitWorkout = map.get(intAction.getActionId())) != null) {
                    if (fitWorkout.getIsAi() == 1) {
                        z = true;
                    }
                }
            }
            return z;
        }
    }

    public static int a(int i) {
        if (i == 0) {
            return 10001;
        }
        if (i == 4 || ggs.d(i)) {
            return 0;
        }
        return i;
    }

    public static void d(FitWorkout fitWorkout) {
        if (fitWorkout == null) {
            return;
        }
        HashMap hashMap = new HashMap(5);
        hashMap.put("click", 1);
        hashMap.put("workout_name", fitWorkout.acquireName());
        hashMap.put("workout_id", fitWorkout.acquireId());
        hashMap.put("resourceType", Integer.valueOf(fpq.b(fitWorkout)));
        String a2 = ffy.a(fitWorkout.acquireClasses());
        if (TextUtils.isEmpty(a2)) {
            a2 = ffy.c(fitWorkout.getPrimaryClassify());
        }
        hashMap.put("course_type", a2);
        c("1130075", hashMap);
    }

    public static void e(int i, AtomicAction atomicAction, Map<String, Object> map) {
        HashMap hashMap;
        if (atomicAction == null) {
            return;
        }
        if (map == null) {
            hashMap = new HashMap(5);
        } else {
            hashMap = new HashMap(map);
        }
        hashMap.put("click", 1);
        hashMap.put("event", Integer.valueOf(i));
        hashMap.put("type", ffy.e(atomicAction.getTrainingPoints()));
        hashMap.put("action_id", atomicAction.getId());
        hashMap.put("action_name", atomicAction.getName());
        c(AnalyticsValue.HEALTH_FITNESS_ACTION_DETAIL_1130029.value(), hashMap);
    }

    public static void c(FitWorkout fitWorkout, int i, int i2, int i3) {
        if (fitWorkout == null) {
            return;
        }
        int b = fpq.b(fitWorkout);
        int i4 = b == 1 ? 3 : b == 2 ? 2 : 0;
        HashMap hashMap = new HashMap(10);
        hashMap.put("click", 1);
        hashMap.put("workout_name", fitWorkout.acquireName());
        hashMap.put("workout_id", fitWorkout.acquireId());
        hashMap.put(HwExerciseConstants.JSON_NAME_WORKOUT_TYPE, Integer.valueOf(fitWorkout.acquireCommodityFlag()));
        hashMap.put("previewDuration", Integer.valueOf(i));
        hashMap.put("previewSetDuration", Integer.valueOf(i2));
        if (i4 != 0) {
            hashMap.put("resourceType", Integer.valueOf(i4));
        }
        if (i3 != 0) {
            hashMap.put("event", Integer.valueOf(i3));
        }
        c(AnalyticsValue.TRADE_DETAIL_VIEW_VIDEOS.value(), hashMap);
    }

    public static void a(fpy fpyVar) {
        if (fpyVar == null) {
            LogUtil.h("Suggestion_FitnessBiUtils", "preWatchBuyCourse auditionCourseData == null");
            return;
        }
        HashMap hashMap = new HashMap(10);
        hashMap.put("click", 1);
        TradeViewInfo e = fpyVar.e();
        if (e != null) {
            hashMap.put("type", Integer.valueOf(e.getResType()));
            Map<String, String> biParams = e.getBiParams();
            if (biParams != null) {
                MarketingBiUtils.a(hashMap, biParams);
            }
        } else {
            LogUtil.h("Suggestion_FitnessBiUtils", "preWatchBuyCourse tradeViewInfo == null");
        }
        ProductSummary b = fpyVar.b();
        if (b != null) {
            d(hashMap, b, "1");
            c(AnalyticsValue.TRADE_BUY.value(), hashMap);
        } else {
            ProductSummary d = fpyVar.d();
            if (d != null) {
                d(hashMap, d, "-1");
            }
            c(AnalyticsValue.TRADE_BUY.value(), hashMap);
        }
    }

    private static void d(Map<String, Object> map, ProductSummary productSummary, String str) {
        map.put("name", productSummary.getProductName());
        map.put(ParsedFieldTag.PRICE, String.valueOf(productSummary.getMicroPrice() / 1000000.0f));
        map.put("id", productSummary.getProductId());
        map.put("priceType", str);
    }
}
