package com.huawei.health.plan.api;

import com.huawei.basefitnessadvice.callback.DataCallback;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.basefitnessadvice.model.PlanInfo;
import com.huawei.basefitnessadvice.model.PlanRecord;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.health.plan.model.PlanStat;
import com.huawei.health.plan.model.PlanStatistics;
import com.huawei.health.plan.model.intplan.IntPlanBean;
import com.huawei.health.plan.model.intplan.LeavePlanCalendarBean;
import com.huawei.health.plan.model.intplan.ReplacePlanBean;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.suggestion.ResultCallback;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.FitnessPackageInfo;
import com.huawei.pluginfitnessadvice.plandata.CreateRunPlanParams;
import com.huawei.pluginfitnessadvice.plandata.UserInfoBean;
import com.huawei.up.model.UserInfomation;
import defpackage.epx;
import defpackage.ffi;
import defpackage.kwm;
import defpackage.mnw;
import defpackage.mny;
import defpackage.mob;
import defpackage.moc;
import defpackage.mof;
import defpackage.mog;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public interface PlanApi {
    void cancelLeave(LeavePlanCalendarBean leavePlanCalendarBean, UiCallback<IntPlan> uiCallback);

    void cancelTodayPlanRemind(Plan plan);

    void checkAllowCreateOldPlan(UiCallback<Boolean> uiCallback);

    void checkSportRecordOutPlanPunch(UiCallback<List<kwm>> uiCallback, List<kwm> list);

    IntPlan convertRunPlan(Plan plan);

    void createFitnessPkg(String str, UiCallback<IntPlan> uiCallback);

    void createIntelligentRunPlan(CreateRunPlanParams createRunPlanParams, UiCallback<Plan> uiCallback);

    void createPlan(IntPlanBean intPlanBean, UiCallback<IntPlan> uiCallback);

    void createPlan(epx epxVar, UiCallback<Plan> uiCallback);

    void deletePlanExerciseRecord(String str);

    void finishPlan(int i, String str, UiCallback<String> uiCallback);

    void getAchievementForecast(int i, int i2, int i3, int i4, UiCallback<mog> uiCallback);

    void getAllFitnessPackage(int i, int i2, UiCallback<List<FitnessPackageInfo>> uiCallback);

    void getAllFitnessPackage(int i, UiCallback<List<FitnessPackageInfo>> uiCallback);

    void getAllPlans(int i, UiCallback<mnw> uiCallback);

    void getCoachAdvice(int i, String str, IntPlanBean intPlanBean, boolean z, UiCallback<String> uiCallback);

    IntPlan getCurrentIntPlan();

    void getCurrentIntPlan(UiCallback<IntPlan> uiCallback);

    void getCurrentReportIndex(UiCallback<Integer> uiCallback);

    FitnessPackageInfo getFitnessPkgInfoByTempId(String str);

    float getFitnessPlanPackageTotalCalorie(FitnessPackageInfo fitnessPackageInfo);

    float getFitnessPlanShouldCompleteCalorie(int i, WorkoutRecord workoutRecord, UserInfomation userInfomation);

    String getFitnessPlanTempId(String str);

    void getIntPlanReport(String str, int i, int i2, UiCallback<String> uiCallback);

    int getNeedFinishPlanType();

    String getNeedSendFinishPlanId();

    PlanStat getPlanBestRecord(String str, float f);

    PlanRecord getPlanProgress(String str, boolean z);

    void getPlanProgress(String str, UiCallback<PlanRecord> uiCallback);

    void getPlanRecords(int i, int i2, UiCallback<List<PlanRecord>> uiCallback);

    void getPlanStatistics(UiCallback<PlanStatistics> uiCallback);

    Map<String, Integer> getPlanWorkoutOrders(String str);

    void getRecommedPlans(int i, UiCallback<List<PlanInfo>> uiCallback);

    int getRemindTime();

    void getTodayRunPanCourse(Plan plan, UiCallback<FitWorkout> uiCallback);

    void getTrainingReport(String str, int i, UiCallback<mny> uiCallback);

    List<ffi> getWeekWorkouts(Plan plan);

    @Deprecated
    void getWorkoutById(String str, String str2, String str3, UiCallback<FitWorkout> uiCallback);

    List<FitWorkout> getWorkoutListFromLocalByDifficulties(int i, int i2, int i3, Integer[] numArr);

    Plan getjoinedPlanById(String str);

    boolean isNeedSendFinishPlanToDevice(String str);

    boolean isOpenRemind();

    void leavePlan(LeavePlanCalendarBean leavePlanCalendarBean, UiCallback<IntPlan> uiCallback);

    void postFeedback(mof mofVar, UiCallback<String> uiCallback);

    void preLoadPlan();

    void queryAllCompletedFitnessPlanFromCloud(ResultCallback resultCallback);

    void replacePlanSchedule(ReplacePlanBean replacePlanBean, UiCallback<Boolean> uiCallback);

    void setNeedFinishPlanType(int i);

    void setNeedUpdateCurrentPlan();

    void setPlanType(int i);

    void updateAction(moc mocVar, UiCallback<String> uiCallback);

    void updateDayRecord(IntPlan intPlan, WorkoutRecord workoutRecord, DataCallback dataCallback);

    void updateDayRecord(String str, mob mobVar);

    void updateIntPlanInfo(String str, int i, int i2, String str2, UiCallback<String> uiCallback);

    void updateIntPlanReport(String str, int i, int i2, String str2, long j, UiCallback<String> uiCallback);

    void updatePlan(UserInfoBean userInfoBean, String str, UiCallback<IntPlan> uiCallback);

    boolean updatePlanBestRecord(String str, int i, int i2);

    void updatePlanCalendar(LeavePlanCalendarBean leavePlanCalendarBean, UiCallback<IntPlan> uiCallback);

    void updatePlanDate(String str, List<Integer> list, List<Integer> list2, UiCallback<String> uiCallback);

    void updatePlanName(String str, String str2);

    boolean updatePlanProgress(WorkoutRecord workoutRecord);

    void updatePlanReport(String str, String str2, UiCallback<String> uiCallback);

    void updateRemindTime(boolean z, int i);

    void updateSendFinishPlanDevice(String str);
}
