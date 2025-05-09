package com.huawei.health.course.api;

import com.huawei.basefitnessadvice.callback.DataCallback;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.Media;
import com.huawei.basefitnessadvice.model.NavigationFilter;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.basefitnessadvice.model.Topic;
import com.huawei.basefitnessadvice.model.TrainStatistics;
import com.huawei.basefitnessadvice.model.WorkoutListBean;
import com.huawei.basefitnessadvice.model.intplan.RecordData;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.suggestion.RunCourseRecommendCallback;
import com.huawei.hwbasemgr.IBaseStatusCallback;
import com.huawei.pluginfitnessadvice.AtomicAction;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.LongVideoInfo;
import com.huawei.pluginfitnessadvice.TrainAction;
import com.huawei.pluginfitnessadvice.Workout;
import com.huawei.pluginfitnessadvice.WorkoutPackageInfo;
import com.huawei.pluginfitnessadvice.audio.SleepAudioPackage;
import com.huawei.pluginfitnessadvice.audio.SleepAudioSeries;
import com.huawei.up.model.UserInfomation;
import defpackage.fey;
import defpackage.fff;
import defpackage.ffk;
import defpackage.ffl;
import defpackage.mmo;
import defpackage.mms;
import defpackage.mmx;
import defpackage.mob;
import java.util.List;

/* loaded from: classes.dex */
public interface CourseApi {
    WorkoutRecord acquireWorkoutRecordByExerciseTime(String str, long j, String str2);

    WorkoutRecord acquireWorkoutRecordByRecordId(int i);

    void addUserDefinedWorkout(FitWorkout fitWorkout, UiCallback<FitWorkout> uiCallback);

    void cancelDownloadCourseMediaFiles(fey feyVar);

    void cancelDownloadCourseMediaFiles(String str);

    void collectCourse(String str, int i, UiCallback<Boolean> uiCallback);

    void collectCourse(String str, String str2);

    RecordData coverRecordData(mob mobVar);

    void delCourseUseCache(UiCallback<Boolean> uiCallback);

    void deletePlayRecord(List<Integer> list, UiCallback<Boolean> uiCallback);

    void deleteUserDefinedWorkout(String str, int i, UiCallback<String> uiCallback);

    void deleteUserJoinedCourses(List<Workout> list, UiCallback<String> uiCallback);

    void downloadCourseMediaFileByPosition(String str, String str2, int i, UiCallback<String> uiCallback);

    void downloadCourseMediaFileList(List<Media> list, long j, UiCallback<String> uiCallback);

    void downloadCourseMediaFiles(fey feyVar, UiCallback<String> uiCallback);

    void downloadFile(String str, String str2, UiCallback<String> uiCallback, boolean z);

    void downloadSubtitles(String str, UiCallback<String> uiCallback);

    void getAggregatePageByType(int i, UiCallback<NavigationFilter> uiCallback);

    @Deprecated
    void getAudioBehaviorList(int i, int i2, int i3, int i4, UiCallback<List<SleepAudioSeries>> uiCallback);

    void getAudioBehaviorList(fff fffVar, UiCallback<List<SleepAudioSeries>> uiCallback);

    void getCourseActionInfo(String str, String str2, UiCallback<AtomicAction> uiCallback);

    void getCourseActionList(int i, int i2, int i3, int i4, UiCallback<List<AtomicAction>> uiCallback);

    void getCourseById(ffl fflVar, UiCallback<Workout> uiCallback);

    @Deprecated
    void getCourseById(String str, String str2, String str3, UiCallback<Workout> uiCallback);

    void getCourseByIds(List<ffl> list, boolean z, UiCallback<List<FitWorkout>> uiCallback);

    void getCourseByType(int i, int i2, int i3, UiCallback<List<Workout>> uiCallback);

    void getCourseFilters(String str, UiCallback<String> uiCallback);

    void getCourseLongVideoInfo(ffl fflVar, UiCallback<LongVideoInfo> uiCallback);

    @Deprecated
    void getCourseLongVideoInfo(String str, UiCallback<LongVideoInfo> uiCallback);

    int getCourseMediaFileSize(String str, String str2, int i);

    int getCourseMediaFilesLength(String str, String str2);

    void getCourseRecommendIndexList(RunCourseRecommendCallback runCourseRecommendCallback);

    void getCourseTopicList(int i, int i2, UiCallback<List<Topic>> uiCallback);

    void getCourseTopicList(int i, UiCallback<List<Topic>> uiCallback);

    void getCourseTrainAction(List<String> list, UiCallback<List<TrainAction>> uiCallback);

    void getCourseTrainStatistics(int i, UiCallback<TrainStatistics> uiCallback);

    long getCourseUseCacheSize();

    Workout getCourseWithinCurrentPlanById(int i, String str);

    void getCoursesByFilter(WorkoutListBean workoutListBean, UiCallback<List<Workout>> uiCallback);

    void getCoursesBySecondClassifyId(int i, int i2, int i3, int i4, UiCallback<List<Workout>> uiCallback);

    void getCoursesByTopicId(int i, int i2, UiCallback<List<Workout>> uiCallback);

    void getCoursesWithinCurrentPlan(int i, UiCallback<List<Workout>> uiCallback);

    FitWorkout getFitWorkout(String str, String str2);

    void getFitnessActionTemplate(String str, UiCallback<mms> uiCallback);

    void getFitnessMaxScore(String str, int i, UiCallback<mmo> uiCallback);

    void getJoinedCourses(WorkoutListBean workoutListBean, UiCallback<List<Workout>> uiCallback);

    List<Media> getMediaFiles(FitWorkout fitWorkout);

    void getRecWorkoutByCourseType(int i, UiCallback<List<Workout>> uiCallback);

    void getRecommendCourseByCloud(DataCallback dataCallback);

    void getRecommendCourses(String str, WorkoutListBean workoutListBean, UiCallback<List<Workout>> uiCallback);

    void getRecommendCoursesByRules(UiCallback<List<Workout>> uiCallback);

    int getTodayTaskStatus(long j);

    void getUserCourseList(int i, int i2, int i3, String str, UiCallback<List<Workout>> uiCallback);

    void getUserRank(String str, String str2, double d, long j, UiCallback<mmx> uiCallback);

    void getWorkoutCount(String str, String str2, UiCallback<Integer> uiCallback);

    void getWorkoutPackageById(ffk ffkVar, UiCallback<WorkoutPackageInfo> uiCallback);

    List<WorkoutRecord> getWorkoutRecords(long j, long j2);

    List<WorkoutRecord> getWorkoutRecords(String str, UiCallback<List<WorkoutRecord>> uiCallback);

    List<WorkoutRecord> getWorkoutRecords(String str, String str2, String str3);

    boolean hasDoneTodayRunTask(Plan plan);

    void isCourseCollected(String str, UiCallback<Boolean> uiCallback);

    boolean isCourseMediaFileDownloaded(String str, String str2, int i);

    boolean isNeedGetFromCloud(FitWorkout fitWorkout, ffl fflVar, UserInfomation userInfomation);

    void markFitnessCourseStatusAvailable();

    void markFitnessCourseStatusOccupied();

    void modifyUserDefinedWorkout(FitWorkout fitWorkout, UiCallback<FitWorkout> uiCallback);

    void operateFavoriteAudio(int i, int i2, UiCallback<Boolean> uiCallback);

    void postUserCourse(WorkoutRecord workoutRecord, int i);

    @Deprecated
    void postUserCourse(String str, String str2, int i);

    void queryAudiosPackageBySeriesId(String str, UiCallback<List<SleepAudioPackage>> uiCallback);

    void queryTrainCountByCourseId(String str, UiCallback<Integer> uiCallback);

    void registerFitnessCourseStatusListen(IBaseStatusCallback iBaseStatusCallback);

    void saveCourseTrainstatis(WorkoutRecord workoutRecord);

    void searchCourseActionList(int i, int i2, int i3, String str, UiCallback<List<AtomicAction>> uiCallback);

    void searchCourseList(int i, int i2, String str, String str2, UiCallback<List<Workout>> uiCallback);

    @Deprecated
    void uncollectCourse(String str);

    void uncollectCourse(String str, int i);

    void updateCourseDataState();

    void updateCourseDbData(FitWorkout fitWorkout);
}
