package defpackage;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huawei.basefitnessadvice.model.DayInfo;
import com.huawei.basefitnessadvice.model.Introduction;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.basefitnessadvice.model.PlanRecord;
import com.huawei.basefitnessadvice.model.PlanWorkout;
import com.huawei.basefitnessadvice.model.RunWorkout;
import com.huawei.basefitnessadvice.model.WeekInfo;
import com.huawei.health.device.api.IndoorEquipManagerApi;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.pluginfitnessadvice.pricetagbean.PriceTagBean;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class ffm {
    public static Plan a(JSONObject jSONObject, String str) {
        Plan plan = null;
        if (jSONObject == null) {
            return null;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject(str);
        if (optJSONObject != null) {
            plan = new Plan();
            a(optJSONObject, plan);
            List<Integer> list = (List) new Gson().fromJson(optJSONObject.optString("runDate"), new TypeToken<List<Integer>>() { // from class: ffm.5
            }.getType());
            List<Integer> list2 = (List) new Gson().fromJson(optJSONObject.optString("exeDate"), new TypeToken<List<Integer>>() { // from class: ffm.1
            }.getType());
            List<moa> list3 = (List) new Gson().fromJson(optJSONObject.optString("weeklyPlans"), new TypeToken<List<moa>>() { // from class: ffm.3
            }.getType());
            plan.setRunDate(list);
            plan.setExeDate(list2);
            plan.setPlanWeekDataList(list3);
            plan.setTransactionStatus(optJSONObject.optInt("transactionStatus", 0));
            plan.setCommodityFlag(optJSONObject.optInt("commodityFlag"));
            plan.setCornerImgDisplay(optJSONObject.optInt("cornerImgDisplay"));
            plan.setPriceTagBeanList((List) new Gson().fromJson(optJSONObject.optString("cornerList"), new TypeToken<List<PriceTagBean>>() { // from class: ffm.2
            }.getType()));
            plan.setVersion(optJSONObject.optString("version"));
            plan.setRemindTime(optJSONObject.optInt("remindTime"));
            plan.setWeekTrainingDays(optJSONObject.optInt("totalDay"));
            LogUtil.a("Suggestion_DataRunPlanConvert", "setWeekTrainingDays:", Integer.valueOf(plan.getWeekTrainingDays()));
            b(plan);
            plan.setLatestClockInTime(System.currentTimeMillis());
            plan.saveIntroduction((Introduction) new Gson().fromJson(optJSONObject.optString("introduction"), Introduction.class));
            plan.setPlanCategory(jSONObject.optInt("intPlanCategory"));
            LogUtil.a("Suggestion_DataRunPlanConvert", "doGetCurrentPlan:", Integer.valueOf(plan.getPlanCategory()));
            if ("planDetail".equals(str)) {
                plan.setPlanCategory(1);
            } else {
                plan.saveWorkouts(c(jSONObject.optJSONArray("weekInfos")));
                plan.saveWorkoutCount(plan.acquireWorkouts().size());
            }
        }
        return plan;
    }

    private static void a(JSONObject jSONObject, Plan plan) {
        plan.saveId(jSONObject.optString("planId"));
        plan.putName(jSONObject.optString("name"));
        plan.saveType(jSONObject.optInt("type"));
        plan.saveTempId(jSONObject.optInt("planType"));
        plan.setGroupType(jSONObject.optInt("group_type"));
        plan.setDifficulty(jSONObject.optInt("difficulty"));
        plan.setTimeZone(jSONObject.optString("timeZone"));
        plan.saveWeekCount(jSONObject.optInt("weekCount"));
        plan.saveCalorie(jSONObject.optInt("calorie"));
        plan.setDistance(moe.d(jSONObject.optInt("distance")));
        plan.savePicture(jSONObject.optString("picture"));
        plan.setExcludedDates(gic.i(jSONObject.optString("excludedDate")));
        plan.setWeekTimes(jSONObject.optInt("movementTimes"));
        plan.setGoal(jSONObject.optInt(ParsedFieldTag.GOAL));
        c(jSONObject, plan);
        plan.setTrainLevel(jSONObject.optInt("trainLevel"));
        plan.setTargetTime(jSONObject.optInt("targetTime"));
        plan.setPbBeforePlan(jSONObject.optInt("pbBeforePlan"));
        plan.setPbCurrent(jSONObject.optInt("pbCurrent"));
        plan.setDescription(jSONObject.optString("description"));
    }

    private static void c(JSONObject jSONObject, Plan plan) {
        plan.saveEndDate(ghz.e(jSONObject.optLong("endDate"), gib.e(plan.getTimeZone()), "yyyy-MM-dd"));
        plan.saveStartDate(ghz.e(jSONObject.optLong(ParsedFieldTag.BEGIN_DATE), gib.e(plan.getTimeZone()), "yyyy-MM-dd"));
        plan.setStartTime(jSONObject.optLong(ParsedFieldTag.BEGIN_DATE));
        plan.setEndTime(jSONObject.optLong("endDate"));
        plan.saveDays(jdl.d(plan.acquireStartDate(), plan.getEndDate(), DateFormatUtil.DateFormatType.DATE_FORMAT_YYYY_MM_DD));
    }

    private static void b(Plan plan) {
        if (plan.acquireType() == 0) {
            for (moa moaVar : plan.getPlanWeekDataList()) {
                if (moaVar != null) {
                    int f = moaVar.f();
                    moaVar.d(ase.d(plan, f));
                    moaVar.e(ase.a(plan, f));
                }
            }
        }
    }

    public static Boolean a(JSONObject jSONObject) {
        if (jSONObject != null) {
            return Boolean.valueOf(jSONObject.optBoolean("allowCreateOldPlan"));
        }
        LogUtil.b("Suggestion_DataRunPlanConvert", "toAllowCreateOldPlan() isAllowCreateOldPlan is null");
        return true;
    }

    public static List<PlanWorkout> c(JSONArray jSONArray) {
        JSONObject optJSONObject;
        JSONArray optJSONArray;
        ArrayList arrayList = new ArrayList(16);
        if (jSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < jSONArray.length() && (optJSONObject = jSONArray.optJSONObject(i)) != null && (optJSONArray = optJSONObject.optJSONArray("courses")) != null; i++) {
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                JSONObject optJSONObject2 = optJSONArray.optJSONObject(i2);
                if (optJSONObject2 == null) {
                    return arrayList;
                }
                PlanWorkout planWorkout = new PlanWorkout();
                planWorkout.putName(optJSONObject2.optString("name"));
                planWorkout.putDescription(optJSONObject2.optString("description"));
                planWorkout.putWorkoutId(optJSONObject2.optString(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID));
                planWorkout.putExtendParams(optJSONObject2.optString("runningParas"));
                planWorkout.putVersion(optJSONObject2.optString("version"));
                planWorkout.putWeekInfo(d(optJSONObject, optJSONObject2));
                planWorkout.putDayInfo(e(optJSONObject2));
                arrayList.add(planWorkout);
            }
        }
        return arrayList;
    }

    private static DayInfo e(JSONObject jSONObject) {
        DayInfo dayInfo = new DayInfo();
        dayInfo.saveSinglesCount(jSONObject.optInt("displayorder"));
        long optLong = jSONObject.optLong("trainingDate");
        dayInfo.saveOrder(ggl.h(ghz.c(optLong)));
        dayInfo.saveDate(ghz.a(optLong, "yyyy-MM-dd"));
        return dayInfo;
    }

    private static WeekInfo d(JSONObject jSONObject, JSONObject jSONObject2) {
        WeekInfo weekInfo = new WeekInfo();
        weekInfo.setDuration(jSONObject2.optInt("duration"));
        weekInfo.saveOrder(jSONObject.optInt("displayorder"));
        weekInfo.saveSentence(jSONObject.optString("sentence"));
        weekInfo.saveWeekName(jSONObject.optString("stage"));
        weekInfo.setStage(jSONObject.optInt("stage"));
        return weekInfo;
    }

    public static List<PlanRecord> a(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList(16);
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    PlanRecord planRecord = new PlanRecord();
                    planRecord.savePlanId(optJSONObject.optString("planId"));
                    planRecord.savePlanName(optJSONObject.optString("name"));
                    planRecord.savePlanType(optJSONObject.optInt("type"));
                    planRecord.saveStartDate(ghz.a(optJSONObject.optLong(ParsedFieldTag.BEGIN_DATE), "yyyy-MM-dd"));
                    planRecord.saveEndDate(ghz.a(optJSONObject.optLong("endDate"), "yyyy-MM-dd"));
                    planRecord.saveCalorie(optJSONObject.optInt("calorie"));
                    planRecord.saveDistance(moe.d(optJSONObject.optInt("distance")));
                    planRecord.saveActualCalorie(optJSONObject.optInt("actualCalorie"));
                    planRecord.saveActualDistance(moe.d(optJSONObject.optInt("actualDistance")));
                    planRecord.saveWorkoutDays(optJSONObject.optInt("trainingDays"));
                    planRecord.saveWeekCount(optJSONObject.optInt("weekCount"));
                    planRecord.saveFinishRate((float) optJSONObject.optDouble("completionRate"));
                    planRecord.saveFinishDate(ghz.a(optJSONObject.optLong("finishTime")));
                    planRecord.saveExcludedDates(gic.i(optJSONObject.optString("excludedDate")));
                    planRecord.saveWeekTimes(optJSONObject.optInt("movementTimes"));
                    planRecord.saveGoal(optJSONObject.optInt(ParsedFieldTag.GOAL));
                    planRecord.saveWorkoutTimes(optJSONObject.optInt("trainingDays"));
                    planRecord.saveDifficulty(optJSONObject.optInt("difficulty"));
                    planRecord.saveVersion("version");
                    planRecord.setPlanCategory(optJSONObject.optInt("intPlanCategory"));
                    arrayList.add(planRecord);
                }
            }
        }
        return arrayList;
    }

    public static List<WorkoutRecord> e(String str, JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList(16);
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    WorkoutRecord workoutRecord = new WorkoutRecord();
                    a(str, optJSONObject, workoutRecord);
                    String optString = optJSONObject.optString(WorkoutRecord.Extend.RunWorkout.RUN_WORKOUT_TRAJECTORY_ID);
                    LogUtil.c("Suggestion_DataRunPlanConvert", "acquireTrajectoryId:", optString);
                    workoutRecord.saveTrajectoryId(e(workoutRecord, optString));
                    workoutRecord.saveActionSummary(optJSONObject.optString("actionSummary"));
                    workoutRecord.saveVersion(optJSONObject.optString("version"));
                    workoutRecord.saveWearType(optJSONObject.optInt("wearType"));
                    workoutRecord.saveExtend(optJSONObject.optString("extend"));
                    workoutRecord.saveHeartRateDataList(b(optJSONObject.optJSONArray("heartRateList")));
                    workoutRecord.saveInvalidHeartRateList(b(optJSONObject.optJSONArray("invalidHeartRateList")));
                    workoutRecord.saveCategory(workoutRecord.acquireCategoryFromExtend());
                    arrayList.add(workoutRecord);
                }
            }
        }
        return arrayList;
    }

    private static void a(String str, JSONObject jSONObject, WorkoutRecord workoutRecord) {
        workoutRecord.saveId(jSONObject.optInt("id"));
        workoutRecord.savePlanId(str);
        workoutRecord.saveWorkoutOrder(jSONObject.optInt("displayorder"));
        workoutRecord.saveWorkoutName(jSONObject.optString("name"));
        workoutRecord.saveWorkoutId(jSONObject.optString(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID));
        workoutRecord.saveWorkoutDate(ghz.a(jSONObject.optLong("trainingDate"), "yyyy-MM-dd"));
        workoutRecord.saveActualCalorie(jSONObject.optInt("actualCalorie"));
        workoutRecord.saveActualDistance(moe.d(jSONObject.optInt("actualDistance")));
        workoutRecord.saveRecordType(jSONObject.optInt("by"));
        workoutRecord.saveCalorie(jSONObject.optInt("calorie"));
        workoutRecord.saveDistance(moe.d(jSONObject.optInt("distance")));
        workoutRecord.saveExerciseTime(ggl.i(jSONObject.optLong(ParsedFieldTag.TASK_COMPLETE_TIME)));
        workoutRecord.saveOxygen(jSONObject.optDouble("oxygen"));
        workoutRecord.saveTrainingLoadPeak(jSONObject.optInt("trainingLoadPeak"));
        workoutRecord.saveFinishRate((float) jSONObject.optDouble("completionRate"));
        workoutRecord.setDuration(jSONObject.optInt("duration"));
        workoutRecord.saveUpperHeartRate(jSONObject.optInt("heartRateUp"));
        workoutRecord.saveLowerHeartRate(jSONObject.optInt("heartRateDown"));
        workoutRecord.saveBestPace(jSONObject.optInt("bestPace"));
        workoutRecord.saveWeekNum(jSONObject.optInt("weekSequence"));
        workoutRecord.saveTotalScore(jSONObject.optLong(ParsedFieldTag.NPES_TOTAL_SCORE));
        workoutRecord.saveRecordModeType(jSONObject.optInt("recordModeType"));
    }

    private static List<HeartRateData> b(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList(10);
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    int optInt = optJSONObject.optInt(IndoorEquipManagerApi.KEY_HEART_RATE);
                    long optLong = optJSONObject.optLong("time");
                    HeartRateData heartRateData = new HeartRateData();
                    heartRateData.saveTime(optLong);
                    heartRateData.saveHeartRate(optInt);
                    arrayList.add(heartRateData);
                }
            }
        }
        return arrayList;
    }

    public static List<WorkoutRecord> e(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList(16);
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    WorkoutRecord workoutRecord = new WorkoutRecord();
                    a(optJSONObject.optString("planId"), optJSONObject, workoutRecord);
                    String optString = optJSONObject.optString(WorkoutRecord.Extend.RunWorkout.RUN_WORKOUT_TRAJECTORY_ID);
                    LogUtil.c("Suggestion_DataRunPlanConvert", "acquireTrajectoryId:", optString);
                    e(workoutRecord, optString);
                    workoutRecord.saveTrajectoryId(optString);
                    workoutRecord.saveActionSummary(optJSONObject.optString("actionSummary"));
                    workoutRecord.saveVersion(optJSONObject.optString("version"));
                    workoutRecord.saveWearType(optJSONObject.optInt("wearType"));
                    workoutRecord.saveExtend(optJSONObject.optString("extend"));
                    workoutRecord.saveHeartRateDataList(b(optJSONObject.optJSONArray("heartRateList")));
                    workoutRecord.saveInvalidHeartRateList(b(optJSONObject.optJSONArray("invalidHeartRateList")));
                    workoutRecord.saveCategory(workoutRecord.acquireCategoryFromExtend());
                    arrayList.add(workoutRecord);
                }
            }
        }
        return arrayList;
    }

    private static String e(WorkoutRecord workoutRecord, String str) {
        Date e;
        if (str == null || !str.startsWith("gps_maptracking_") || (e = ggl.e(str.substring(16), "yyyyMMddHHmmssSSS")) == null) {
            return str;
        }
        String str2 = e.getTime() + "_" + workoutRecord.acquireExerciseTime();
        LogUtil.c("Suggestion_DataRunPlanConvert", "replaceTrajectoryId:", str2);
        return str2;
    }

    public static String a(TreeSet<Integer> treeSet) {
        if (treeSet == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder(16);
        Iterator<Integer> it = treeSet.iterator();
        while (it.hasNext()) {
            sb.append(it.next().intValue() + 1);
            sb.append(",");
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    public static RunWorkout e(PlanWorkout planWorkout) {
        RunWorkout runWorkout = new RunWorkout();
        if (planWorkout == null) {
            return runWorkout;
        }
        RunWorkout runWorkout2 = (RunWorkout) moj.e(planWorkout.popExtendParams(), RunWorkout.class);
        runWorkout2.saveId(planWorkout.popWorkoutId());
        runWorkout2.saveName(planWorkout.popName());
        runWorkout2.putWorkoutDate(planWorkout.popDayInfo().acquireDate());
        runWorkout2.saveDescription(planWorkout.popDescription());
        runWorkout2.saveVersion(planWorkout.popVersion());
        return runWorkout2;
    }
}
