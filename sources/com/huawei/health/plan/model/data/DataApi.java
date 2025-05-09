package com.huawei.health.plan.model.data;

import android.content.Context;
import com.huawei.basefitnessadvice.callback.DataCallback;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.Media;
import com.huawei.basefitnessadvice.model.NavigationFilter;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.basefitnessadvice.model.PlanRecord;
import com.huawei.basefitnessadvice.model.RunWorkout;
import com.huawei.basefitnessadvice.model.Topic;
import com.huawei.basefitnessadvice.model.TrainStatistics;
import com.huawei.basefitnessadvice.model.WorkoutListBean;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.health.plan.model.PlanStat;
import com.huawei.health.plan.model.PlanStatistics;
import com.huawei.health.plan.model.model.DataSync;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.pluginfitnessadvice.AtomicAction;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.LongVideoInfo;
import com.huawei.pluginfitnessadvice.TrainAction;
import com.huawei.pluginfitnessadvice.Workout;
import com.huawei.pluginfitnessadvice.WorkoutPackageInfo;
import com.huawei.pluginfitnessadvice.audio.SleepAudioPackage;
import com.huawei.pluginfitnessadvice.audio.SleepAudioSeries;
import com.huawei.pluginfitnessadvice.plandata.CreateRunPlanParams;
import com.huawei.pluginfitnessadvice.plandata.UserInfoBean;
import defpackage.erc;
import defpackage.fey;
import defpackage.fff;
import defpackage.ffk;
import defpackage.ffl;
import defpackage.mmo;
import defpackage.mms;
import defpackage.mmw;
import defpackage.mmx;
import defpackage.mob;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/* loaded from: classes3.dex */
public interface DataApi {
    int acquireWorkoutMediaFileSize(String str, String str2, int i);

    void addUserDefinedWorkout(FitWorkout fitWorkout, UiCallback<FitWorkout> uiCallback);

    void cancelDownloadWorkoutMediaFiles(fey feyVar);

    void checkAllowCreateOldPlan(UiCallback<Boolean> uiCallback);

    void collectBehavior(String str, int i, UiCallback<Boolean> uiCallback);

    void collectBehavior(String str, UiCallback<String> uiCallback);

    void collectBehavior(String str, String str2);

    void createFitPlan(long j, int i, int i2, int i3, TreeSet<Integer> treeSet, UiCallback<Plan> uiCallback);

    void createIntelligentRunPlan(CreateRunPlanParams createRunPlanParams, UiCallback<Plan> uiCallback);

    Plan createPlanFromDb(String str);

    void delSyncRecord(DataSync dataSync);

    void delUseCache(UiCallback<Boolean> uiCallback);

    void deleteAllPlanRecords();

    void deleteBehavior(String str, int i);

    void deleteBehavior(String str, int i, UiCallback<String> uiCallback);

    void deletePlayRecord(List<Integer> list, UiCallback<Boolean> uiCallback);

    void deleteUserDefinedWorkout(String str, int i, UiCallback<String> uiCallback);

    void deleteWorkoutRecords(int i, int i2, IBaseResponseCallback iBaseResponseCallback);

    void downloadFile(String str, String str2, UiCallback<String> uiCallback, boolean z);

    void downloadSubtitles(String str, UiCallback<String> uiCallback);

    void downloadWorkoutMediaFile(List<Media> list, long j, UiCallback<String> uiCallback);

    void downloadWorkoutMediaFileByPosition(String str, String str2, UiCallback<String> uiCallback, int i);

    void downloadWorkoutMediaFiles(fey feyVar, UiCallback<String> uiCallback);

    void finishPlan(String str, UiCallback<String> uiCallback);

    void getActionList(int i, int i2, int i3, int i4, UiCallback<List<AtomicAction>> uiCallback);

    void getActions(List<String> list, UiCallback<List<TrainAction>> uiCallback);

    void getAggregatePageByType(int i, UiCallback<NavigationFilter> uiCallback);

    void getAudioBehaviorList(int i, int i2, int i3, int i4, UiCallback<List<SleepAudioSeries>> uiCallback);

    void getAudioBehaviorList(fff fffVar, UiCallback<List<SleepAudioSeries>> uiCallback);

    void getBehaviorList(int i, int i2, int i3, UiCallback<List<FitWorkout>> uiCallback);

    void getBehaviorList(int i, int i2, int i3, String str, UiCallback<List<Workout>> uiCallback);

    void getCollectBehavior(String str, UiCallback<Boolean> uiCallback);

    Plan getCurrentPlan();

    Plan getCurrentRunPlan();

    void getFilterFitnessCourses(WorkoutListBean workoutListBean, UiCallback<List<Workout>> uiCallback);

    void getFilterTab(String str, UiCallback<Boolean> uiCallback);

    FitWorkout getFitWorkout(String str, String str2);

    void getFitnessActionInfo(String str, String str2, UiCallback<AtomicAction> uiCallback);

    void getFitnessActionTemplate(String str, UiCallback<mms> uiCallback);

    void getFitnessCourseTopicList(int i, int i2, UiCallback<List<Topic>> uiCallback);

    void getFitnessCourseTopicList(int i, UiCallback<List<Topic>> uiCallback);

    void getFitnessMaxScore(String str, int i, UiCallback<mmo> uiCallback);

    Plan getJoinedPlan(String str);

    List<PlanRecord> getJoinedPlans(int i, int i2, UiCallback<List<PlanRecord>> uiCallback);

    void getJoinedWorkouts(int i, int i2, Integer[] numArr, Integer[] numArr2, Integer[] numArr3, int i3, Integer[] numArr4, int i4, UiCallback<List<FitWorkout>> uiCallback);

    void getLongVideoInfo(ffl fflVar, UiCallback<LongVideoInfo> uiCallback);

    List<Media> getMediaFiles(FitWorkout fitWorkout);

    void getOldRunPlanLocalRecords(int i, int i2, UiCallback<List<PlanRecord>> uiCallback);

    PlanRecord getPlanProgress(String str);

    void getPlanProgress(String str, UiCallback<PlanRecord> uiCallback);

    PlanStat getPlanStat(String str);

    void getPlanStatistics(int i, UiCallback<PlanStatistics> uiCallback);

    void getRecWorkoutByCourseTypeList(int i, UiCallback<List<FitWorkout>> uiCallback);

    void getRecommedPlans(UiCallback<List<mmw>> uiCallback);

    void getRecommendWorkouts(int i, int i2, Integer[] numArr, Integer[] numArr2, Integer[] numArr3, int i3, Integer[] numArr4, int i4, UiCallback<List<FitWorkout>> uiCallback);

    List<Map<String, Object>> getRecordsByDateRange(Date date, Date date2);

    int getRemindTime();

    RunWorkout getRunWorkout(String str);

    List<RunWorkout> getRunWorkouts();

    DataSync getSyncRecord(long j);

    void getTrainStatistics(int i, UiCallback<TrainStatistics> uiCallback);

    long getUseCacheSize();

    void getUserRank(erc ercVar, UiCallback<mmx> uiCallback);

    void getWorkout(ffl fflVar, UiCallback<FitWorkout> uiCallback);

    int getWorkoutCount(String str, String str2);

    void getWorkoutCount(ffl fflVar, UiCallback<Integer> uiCallback);

    void getWorkoutFilters(List<Integer> list, UiCallback<String> uiCallback);

    void getWorkoutList(int i, int i2, int i3, int i4, UiCallback<List<FitWorkout>> uiCallback);

    int getWorkoutMediaFilesLength(String str, String str2);

    Map<String, Integer> getWorkoutOrders(String str);

    void getWorkoutPackageById(ffk ffkVar, UiCallback<WorkoutPackageInfo> uiCallback);

    void getWorkoutRecommendList(UiCallback<List<FitWorkout>> uiCallback);

    List<WorkoutRecord> getWorkoutRecords(long j, long j2);

    List<WorkoutRecord> getWorkoutRecords(String str);

    List<WorkoutRecord> getWorkoutRecords(String str, UiCallback<List<WorkoutRecord>> uiCallback);

    List<WorkoutRecord> getWorkoutRecords(String str, String str2, String str3);

    void getWorkoutsByTopicId(int i, int i2, UiCallback<List<FitWorkout>> uiCallback);

    void getWorkoutsByType(int i, int i2, int i3, UiCallback<List<FitWorkout>> uiCallback);

    void getWorkoutsInfo(List<ffl> list, boolean z, UiCallback<List<FitWorkout>> uiCallback);

    void insertCalorieData(Context context, WorkoutRecord workoutRecord);

    void insertCaloriePoints(Context context, Map<Long, HiHealthData> map);

    void insertFitnessIntensityData(Context context, List<Long> list);

    boolean isBeyondSyncTimes(DataSync dataSync);

    boolean isOpenRemind();

    boolean isWorkoutMediaFileHasDownloaded(String str, String str2, int i);

    void modifyUserDefinedWorkout(FitWorkout fitWorkout, UiCallback<FitWorkout> uiCallback);

    void operateFavoriteAudio(int i, int i2, UiCallback<Boolean> uiCallback);

    void postBestRecord(String str, UiCallback<String> uiCallback);

    void postBestRecordFit(String str, UiCallback<String> uiCallback);

    void postDeleteUserWorkout(List<Workout> list, UiCallback<String> uiCallback);

    void postExerciseBehavior(WorkoutRecord workoutRecord, int i);

    void postExerciseBehavior(WorkoutRecord workoutRecord, int i, UiCallback<String> uiCallback);

    void postPlanName(String str, String str2, UiCallback<String> uiCallback);

    void postPlanProgress(String str, UiCallback<String> uiCallback);

    void postPlanRemind(String str, int i, UiCallback<String> uiCallback);

    void queryAudiosPackageBySeriesId(String str, UiCallback<List<SleepAudioPackage>> uiCallback);

    void queryTrainCountByWorkoutId(String str, UiCallback<Integer> uiCallback);

    void savetrainstatis(WorkoutRecord workoutRecord);

    void searchActionList(int i, int i2, int i3, String str, UiCallback<List<AtomicAction>> uiCallback);

    void searchWorkoutList(int i, int i2, String str, List<Integer> list, UiCallback<List<FitWorkout>> uiCallback);

    void setExerciseRemind(boolean z, int i);

    void syncData();

    void syncLocalAndCloud(String str, Plan plan);

    boolean updateBestRecord(String str, int i, int i2);

    void updateDayRecord(IntPlan intPlan, WorkoutRecord workoutRecord, DataCallback dataCallback);

    void updateDayRecord(String str, IntPlan intPlan, mob mobVar);

    void updatePlan(UserInfoBean userInfoBean, int i, UiCallback<Plan> uiCallback);

    void updatePlanName(String str, String str2);

    boolean updatePlanProgress(WorkoutRecord workoutRecord);
}
