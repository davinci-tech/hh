package com.huawei.health.plan.model.cloud;

import com.huawei.basefitnessadvice.callback.DataCallback;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.basefitnessadvice.model.WorkoutListBean;
import com.huawei.health.plan.model.PlanStat;
import com.huawei.health.plan.model.UserFitnessPlanInfo;
import com.huawei.health.plan.model.intplan.IntPlanBean;
import com.huawei.health.plan.model.intplan.LeavePlanCalendarBean;
import com.huawei.health.plan.model.intplan.ReplacePlanBean;
import com.huawei.health.plan.model.intplan.UserProfileBean;
import com.huawei.health.plan.model.model.BestRecordFitStat;
import com.huawei.health.plan.model.model.CreatePlanBean;
import com.huawei.health.plan.model.model.FinishPlanBean;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.pluginfitnessadvice.ChoreographedMultiActions;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.Workout;
import com.huawei.pluginfitnessadvice.plandata.CreateRunPlanParams;
import com.huawei.pluginfitnessadvice.plandata.UserInfoBean;
import defpackage.erc;
import defpackage.esl;
import defpackage.fff;
import defpackage.ffk;
import defpackage.ffl;
import defpackage.mob;
import defpackage.moc;
import defpackage.mof;
import java.util.List;

/* loaded from: classes3.dex */
public interface CloudApi {
    void addUserDefinedWorkout(String str, String str2, int i, List<ChoreographedMultiActions> list, DataCallback dataCallback);

    void cancelDownloadFile(String str);

    void cancelLeave(LeavePlanCalendarBean leavePlanCalendarBean, DataCallback dataCallback);

    void checkAllowCreateOldPlan(DataCallback dataCallback);

    void collectBehavior(String str, int i, DataCallback dataCallback);

    void collectBehavior(String str, DataCallback dataCallback);

    void createFitnessPackagePlan(UserFitnessPlanInfo userFitnessPlanInfo, DataCallback dataCallback);

    void createIntPlan(IntPlanBean intPlanBean, DataCallback dataCallback);

    void createPlan(CreatePlanBean createPlanBean, DataCallback dataCallback);

    void createPlan(CreateRunPlanParams createRunPlanParams, DataCallback dataCallback);

    void deleteBehavior(String str, int i, DataCallback dataCallback);

    void deletePlanExerciseRecord(int i, String str, String str2, DataCallback dataCallback);

    void deletePlayRecord(List<Integer> list, DataCallback dataCallback);

    void deleteUserDefinedWorkout(String str, int i, DataCallback dataCallback);

    void deleteUserWorkoutRecords(int i, int i2, DataCallback dataCallback);

    void downloadFile(String str, String str2, DataCallback dataCallback);

    void downloadFile(String str, String str2, DataCallback dataCallback, boolean z);

    void finishExercise(int i, WorkoutRecord workoutRecord, DataCallback dataCallback);

    void finishPlan(FinishPlanBean finishPlanBean, DataCallback dataCallback);

    void generateReport(int i, IntPlanBean intPlanBean, DataCallback dataCallback);

    void getAchievementForecast(int i, int i2, int i3, int i4, DataCallback dataCallback);

    void getActionList(int i, int i2, int i3, int i4, DataCallback dataCallback);

    void getAggregatePageByType(int i, DataCallback dataCallback);

    void getAllPlans(int i, DataCallback dataCallback);

    void getAudioBehaviorList(int i, int i2, int i3, int i4, DataCallback dataCallback);

    void getAudioBehaviorList(fff fffVar, DataCallback dataCallback);

    void getBehaviorList(int i, int i2, int i3, DataCallback dataCallback);

    void getBehaviorList(int i, int i2, int i3, String str, DataCallback dataCallback);

    void getCoachAdvice(String str, int i, DataCallback dataCallback);

    void getCurrentPlan(DataCallback dataCallback);

    void getCurrentReportIndex(DataCallback dataCallback);

    void getDoingIntPlan(DataCallback dataCallback);

    void getFilterTab(String str, DataCallback dataCallback);

    void getFinishedPlans(int i, int i2, DataCallback dataCallback);

    void getFitnessActionInfo(String str, String str2, DataCallback dataCallback);

    void getFitnessActionTemplate(String str, DataCallback dataCallback);

    void getFitnessMaxScore(String str, int i, DataCallback dataCallback);

    void getFitnessPkgInfo(int i, int i2, int i3, DataCallback dataCallback);

    void getFitnessRecWorkoutByCourseTypeList(int i, DataCallback dataCallback);

    void getFitnessWorkoutRecommendList(String str, DataCallback dataCallback);

    void getIntPlanReport(String str, int i, int i2, DataCallback dataCallback);

    void getLongVideoInfo(ffl fflVar, DataCallback dataCallback);

    void getMultiLanguage(long j, String str, DataCallback dataCallback);

    void getNavigationCourseList(WorkoutListBean workoutListBean, DataCallback dataCallback);

    void getPlanProgress(String str, DataCallback dataCallback);

    void getRunRecommendCourse(DataCallback dataCallback);

    void getTrainActions(List<String> list, int i, DataCallback dataCallback);

    void getTrainingReport(String str, DataCallback dataCallback);

    void getUserBestRecords(DataCallback dataCallback);

    void getUserRank(erc ercVar, DataCallback dataCallback);

    void getWorkOutsByTopicId(int i, int i2, DataCallback dataCallback);

    void getWorkOutsByTopicId(int i, int i2, String str, DataCallback dataCallback);

    void getWorkoutFilters(List<Integer> list, DataCallback dataCallback);

    void getWorkoutInfo(ffl fflVar, DataCallback dataCallback);

    void getWorkoutList(int i, int i2, int i3, int i4, DataCallback dataCallback);

    void getWorkoutList(WorkoutListBean workoutListBean, DataCallback dataCallback);

    void getWorkoutPackage(ffk ffkVar, DataCallback dataCallback);

    void getWorkoutsByType(int i, int i2, DataCallback dataCallback);

    void getWorkoutsByType(int i, int i2, String str, DataCallback dataCallback);

    void getWorkoutsInfo(List<ffl> list, boolean z, DataCallback dataCallback);

    void leavePlan(LeavePlanCalendarBean leavePlanCalendarBean, DataCallback dataCallback);

    void modifyUserDefinedWorkout(FitWorkout fitWorkout, DataCallback dataCallback);

    void operateFavoriteAudio(int i, int i2, DataCallback dataCallback);

    void postBehavior(WorkoutRecord workoutRecord, int i, DataCallback dataCallback);

    void postBestRecord(String str, PlanStat planStat, DataCallback dataCallback);

    void postDeleteUserWorkout(List<Workout> list, DataCallback dataCallback);

    void postFeedback(mof mofVar, DataCallback dataCallback);

    void postIntFeedback(mof mofVar, DataCallback dataCallback);

    void postPlanRemind(String str, int i, DataCallback dataCallback);

    void postRunPlan(Plan plan, DataCallback dataCallback);

    void postTrainBestRecords(BestRecordFitStat bestRecordFitStat, DataCallback dataCallback);

    void queryAudiosPackageBySeriesId(String str, DataCallback dataCallback);

    void queryFitnessPkgPlan(String str, DataCallback dataCallback);

    void queryFitnessPlanSummary(DataCallback dataCallback);

    void queryMyDoingFitnessPlan(DataCallback dataCallback);

    void queryOperationPage(int i, DataCallback dataCallback);

    void queryPlanStatistics(int i, DataCallback dataCallback);

    void queryTopicList(int i, int i2, DataCallback dataCallback);

    void queryTopicList(int i, int i2, String str, DataCallback dataCallback);

    void queryTopicList(int i, DataCallback dataCallback);

    void queryTopicList(int i, String str, DataCallback dataCallback);

    void queryTrainCount(DataCallback dataCallback);

    void queryTrainCountByWorkoutId(String str, DataCallback dataCallback);

    void queryTrainList(long j, long j2, int i, DataCallback dataCallback);

    void queryTrainStatistics(int i, DataCallback dataCallback);

    void replacePlanSchedule(ReplacePlanBean replacePlanBean, DataCallback dataCallback);

    void searchActionList(int i, int i2, int i3, String str, DataCallback dataCallback);

    void searchWorkoutList(int i, int i2, String str, List<Integer> list, DataCallback dataCallback);

    void setEvent(float f, int i, long j, DataCallback dataCallback);

    void stopIntPlan(esl eslVar, DataCallback dataCallback);

    void updateAction(moc mocVar, DataCallback dataCallback);

    void updateClockTimes(int i, String str, int i2, int i3, String str2, DataCallback dataCallback);

    void updateDayRecord(int i, String str, int i2, int i3, mob mobVar, DataCallback dataCallback);

    void updateFitnessPackagePlan(UserFitnessPlanInfo userFitnessPlanInfo, DataCallback dataCallback);

    void updateIntPlan(UserProfileBean userProfileBean, String str, int i, DataCallback dataCallback);

    void updateIntPlanReport(String str, int i, int i2, String str2, long j, DataCallback dataCallback);

    void updatePlan(UserInfoBean userInfoBean, int i, DataCallback dataCallback);

    void updatePlanCalendar(LeavePlanCalendarBean leavePlanCalendarBean, DataCallback dataCallback);

    void updatePlanDate(String str, List<Integer> list, List<Integer> list2, DataCallback dataCallback);

    void updatePlanInfo(String str, int i, String str2, String str3, DataCallback dataCallback);

    void updatePlanName(String str, String str2, DataCallback dataCallback);

    void updatePlanReport(String str, String str2, DataCallback dataCallback);
}
