package defpackage;

import android.text.TextUtils;
import com.huawei.basefitnessadvice.callback.DataCallback;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.Media;
import com.huawei.basefitnessadvice.model.NavigationFilter;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.basefitnessadvice.model.RunWorkout;
import com.huawei.basefitnessadvice.model.Topic;
import com.huawei.basefitnessadvice.model.TrainStatistics;
import com.huawei.basefitnessadvice.model.WorkoutListBean;
import com.huawei.basefitnessadvice.model.intplan.RecordData;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.plan.model.model.fitness.FitnessHistoryModel;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.sport.utils.ResultUtil;
import com.huawei.health.suggestion.RunCourseRecommendCallback;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.hwbasemgr.IBaseStatusCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.pluginfitnessadvice.AtomicAction;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.LongVideoInfo;
import com.huawei.pluginfitnessadvice.TrainAction;
import com.huawei.pluginfitnessadvice.Workout;
import com.huawei.pluginfitnessadvice.WorkoutPackageInfo;
import com.huawei.pluginfitnessadvice.audio.SleepAudioPackage;
import com.huawei.pluginfitnessadvice.audio.SleepAudioSeries;
import com.huawei.up.model.UserInfomation;
import defpackage.ffk;
import defpackage.ffl;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;

@ApiDefine(uri = CourseApi.class)
@Singleton
/* loaded from: classes3.dex */
public class eou implements CourseApi {

    /* renamed from: a, reason: collision with root package name */
    private IBaseStatusCallback f12128a;

    @Override // com.huawei.health.course.api.CourseApi
    public void collectCourse(String str, String str2) {
        etr.b().collectBehavior(str, str2);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void collectCourse(String str, int i, UiCallback<Boolean> uiCallback) {
        etr.b().collectBehavior(str, i, uiCallback);
    }

    @Override // com.huawei.health.course.api.CourseApi
    @Deprecated
    public void uncollectCourse(String str) {
        etr.b().deleteBehavior(str, 0);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void uncollectCourse(String str, int i) {
        etr.b().deleteBehavior(str, i);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void isCourseCollected(String str, UiCallback<Boolean> uiCallback) {
        etr.b().getCollectBehavior(str, uiCallback);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void getUserCourseList(int i, int i2, int i3, String str, final UiCallback<List<Workout>> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("CourseImpl", "getUserCourseList: callback is null.");
        } else {
            etr.b().getBehaviorList(i, i2, i3, str, new UiCallback<List<Workout>>() { // from class: eou.5
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i4, String str2) {
                    uiCallback.onFailure(i4, str2);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public void onSuccess(List<Workout> list) {
                    uiCallback.onSuccess(list);
                }
            });
        }
    }

    @Override // com.huawei.health.course.api.CourseApi
    @Deprecated
    public void postUserCourse(String str, String str2, int i) {
        WorkoutRecord workoutRecord = new WorkoutRecord();
        workoutRecord.saveWorkoutId(str);
        workoutRecord.saveVersion(str2);
        etr.b().postExerciseBehavior(workoutRecord, i);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void postUserCourse(WorkoutRecord workoutRecord, int i) {
        etr.b().postExerciseBehavior(workoutRecord, i);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void getJoinedCourses(final WorkoutListBean workoutListBean, final UiCallback<List<Workout>> uiCallback) {
        LogUtil.a("CourseImpl", "enter getJoinedCourses");
        if (workoutListBean == null || uiCallback == null) {
            LogUtil.b("CourseImpl", "getJoinedCourses: workoutListBean or call back is null.");
        } else {
            asc.e().b(new Runnable() { // from class: epc
                @Override // java.lang.Runnable
                public final void run() {
                    eou.this.a(workoutListBean, uiCallback);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void a(WorkoutListBean workoutListBean, final UiCallback<List<Workout>> uiCallback) {
        etr.b().getJoinedWorkouts(workoutListBean.getPageStart(), workoutListBean.getPageSize(), workoutListBean.getWorkoutType(), workoutListBean.getDifficulty(), workoutListBean.getTrainingPoints(), workoutListBean.getSupportWear(), workoutListBean.getEquipments(), workoutListBean.getCommodityFlag(), new UiCallback<List<FitWorkout>>() { // from class: eou.8
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                uiCallback.onFailure(i, str);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onSuccess(List<FitWorkout> list) {
                uiCallback.onSuccess(mod.j(list));
            }
        });
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void deleteUserJoinedCourses(List<Workout> list, UiCallback<String> uiCallback) {
        etr.b().postDeleteUserWorkout(list, uiCallback);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void searchCourseList(int i, int i2, String str, String str2, final UiCallback<List<Workout>> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("CourseImpl", "searchCourseList: callback is null.");
        } else {
            etr.b().searchWorkoutList(i, i2, str, evg.a(str2), new UiCallback<List<FitWorkout>>() { // from class: eou.7
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i3, String str3) {
                    uiCallback.onFailure(i3, str3);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public void onSuccess(List<FitWorkout> list) {
                    uiCallback.onSuccess(mod.j(list));
                }
            });
        }
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void getCourseFilters(String str, UiCallback<String> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("CourseImpl", "getCourseFilters: callback is null.");
        } else {
            etr.b().getWorkoutFilters(evg.a(str), uiCallback);
        }
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void getAggregatePageByType(int i, UiCallback<NavigationFilter> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("CourseImpl", "getCourseFilters: callback is null.");
        } else {
            etr.b().getAggregatePageByType(i, uiCallback);
        }
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void getCoursesWithinCurrentPlan(int i, UiCallback<List<Workout>> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("CourseImpl", "getCoursesWithinCurrentPlan: callback is null.");
            return;
        }
        if (i != 0) {
            LogUtil.b("CourseImpl", "getCoursesWithinCurrentPlan: planType not support yet.");
            uiCallback.onFailure(ResultUtil.ResultCode.PARAMETER_INVALID, ResultUtil.d(ResultUtil.ResultCode.PARAMETER_INVALID));
            return;
        }
        List<RunWorkout> runWorkouts = etr.b().getRunWorkouts();
        if (koq.b(runWorkouts)) {
            LogUtil.b("CourseImpl", "getCoursesWithinCurrentPlan: runWorkouts is empty.");
            uiCallback.onFailure(20004, ResultUtil.d(20004));
        } else {
            evc.a((RunWorkout[]) runWorkouts.toArray(new RunWorkout[0]));
            List<Workout> arrayList = new ArrayList<>(runWorkouts.size());
            arrayList.addAll(runWorkouts);
            uiCallback.onSuccess(arrayList);
        }
    }

    @Override // com.huawei.health.course.api.CourseApi
    public Workout getCourseWithinCurrentPlanById(int i, String str) {
        if (i != 0) {
            LogUtil.b("CourseImpl", "getCouseWithinCurrentPlanById: planType not support yet.");
            return null;
        }
        return etr.b().getRunWorkout(str);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void getCoursesBySecondClassifyId(int i, int i2, int i3, int i4, final UiCallback<List<Workout>> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("CourseImpl", "getCoursesBySecondClassifyId: callback is null.");
        } else {
            etr.b().getWorkoutList(i, i2, i3, i4, new UiCallback<List<FitWorkout>>() { // from class: eou.9
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i5, String str) {
                    uiCallback.onFailure(i5, str);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public void onSuccess(List<FitWorkout> list) {
                    uiCallback.onSuccess(mod.j(list));
                }
            });
        }
    }

    @Override // com.huawei.health.course.api.CourseApi
    /* renamed from: getCoursesByFilter, reason: merged with bridge method [inline-methods] */
    public void d(final WorkoutListBean workoutListBean, final UiCallback<List<Workout>> uiCallback) {
        if (workoutListBean == null || uiCallback == null) {
            LogUtil.b("CourseImpl", "getCoursesByFilter: workoutListBean is null or callback is null.");
        } else if (HandlerExecutor.c()) {
            jdx.b(new Runnable() { // from class: eoy
                @Override // java.lang.Runnable
                public final void run() {
                    eou.this.d(workoutListBean, uiCallback);
                }
            });
        } else {
            etr.b().getFilterFitnessCourses(workoutListBean, new AnonymousClass10(uiCallback));
        }
    }

    /* renamed from: eou$10, reason: invalid class name */
    class AnonymousClass10 extends UiCallback<List<Workout>> {
        final /* synthetic */ UiCallback b;

        AnonymousClass10(UiCallback uiCallback) {
            this.b = uiCallback;
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(final int i, final String str) {
            final UiCallback uiCallback = this.b;
            HandlerExecutor.e(new Runnable() { // from class: eph
                @Override // java.lang.Runnable
                public final void run() {
                    UiCallback.this.onFailure(i, str);
                }
            });
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onSuccess(final List<Workout> list) {
            final UiCallback uiCallback = this.b;
            HandlerExecutor.e(new Runnable() { // from class: epb
                @Override // java.lang.Runnable
                public final void run() {
                    UiCallback.this.onSuccess(list);
                }
            });
        }
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void getCoursesByTopicId(int i, int i2, final UiCallback<List<Workout>> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("CourseImpl", "getCoursesByTopicId: callback is null.");
        } else {
            etr.b().getWorkoutsByTopicId(i, i2, new UiCallback<List<FitWorkout>>() { // from class: eou.6
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i3, String str) {
                    uiCallback.onFailure(i3, str);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public void onSuccess(List<FitWorkout> list) {
                    uiCallback.onSuccess(mod.j(list));
                }
            });
        }
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void getCourseTopicList(int i, final UiCallback<List<Topic>> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("CourseImpl", "getCourseTopicList: callback is null.");
        } else {
            etr.b().getFitnessCourseTopicList(i, new UiCallback<List<Topic>>() { // from class: eou.15
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i2, String str) {
                    uiCallback.onFailure(i2, str);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public void onSuccess(List<Topic> list) {
                    uiCallback.onSuccess(list);
                }
            });
        }
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void getCourseTopicList(int i, int i2, final UiCallback<List<Topic>> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("CourseImpl", "getCourseTopicList: callback is null.");
        } else {
            etr.b().getFitnessCourseTopicList(i, i2, new UiCallback<List<Topic>>() { // from class: eou.12
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i3, String str) {
                    uiCallback.onFailure(i3, str);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public void onSuccess(List<Topic> list) {
                    uiCallback.onSuccess(list);
                }
            });
        }
    }

    @Override // com.huawei.health.course.api.CourseApi
    @Deprecated
    public void getCourseById(String str, String str2, String str3, UiCallback<Workout> uiCallback) {
        getCourseById(new ffl.d(str).d(str2).c(str3).b(), uiCallback);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void getCourseById(ffl fflVar, final UiCallback<Workout> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("CourseImpl", "getCourseById: callback is null.");
            return;
        }
        if (TextUtils.isEmpty(fflVar.d())) {
            fflVar = new ffl.d(fflVar.h()).d(fflVar.g()).a(fflVar.a()).e(fflVar.e()).c(etd.a()).c(fflVar.c()).b();
        }
        etr.b().getWorkout(fflVar, new UiCallback<FitWorkout>() { // from class: eou.11
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                uiCallback.onFailure(i, str);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onSuccess(FitWorkout fitWorkout) {
                uiCallback.onSuccess(fitWorkout);
            }
        });
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void getCourseByIds(List<ffl> list, boolean z, UiCallback<List<FitWorkout>> uiCallback) {
        if (koq.b(list)) {
            LogUtil.b("CourseImpl", "getCourseByIds: paramList is empty.");
            uiCallback.onFailure(ResultUtil.ResultCode.PARAMETER_INVALID, ResultUtil.d(ResultUtil.ResultCode.PARAMETER_INVALID));
        } else {
            etr.b().getWorkoutsInfo(list, z, uiCallback);
        }
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void getCourseByType(int i, int i2, int i3, final UiCallback<List<Workout>> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("CourseImpl", "getCourseByType: callback is null.");
        } else {
            etr.b().getWorkoutsByType(i, i2, i3, new UiCallback<List<FitWorkout>>() { // from class: eou.3
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i4, String str) {
                    uiCallback.onFailure(i4, str);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public void onSuccess(List<FitWorkout> list) {
                    uiCallback.onSuccess(mod.j(list));
                }
            });
        }
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void getRecommendCourses(String str, WorkoutListBean workoutListBean, UiCallback<List<Workout>> uiCallback) {
        if (str.equals("RUNNING_COURSE")) {
            LogUtil.b("CourseImpl", "getRecommendCourses: not support RUNNING_COURSE_WORKOUT_TYPE yet.");
        } else if (str.equals("FITNESS_COURSE")) {
            c(workoutListBean, uiCallback);
        } else {
            LogUtil.b("CourseImpl", "getRecommendCourses: courseType not match.");
        }
    }

    private void c(final WorkoutListBean workoutListBean, final UiCallback<List<Workout>> uiCallback) {
        if (workoutListBean == null || uiCallback == null) {
            LogUtil.b("CourseImpl", "doGetRecommendFitnessCourse: wokoutListBean or callback is null.");
        } else {
            asc.e().b(new Runnable() { // from class: epa
                @Override // java.lang.Runnable
                public final void run() {
                    eou.this.b(workoutListBean, uiCallback);
                }
            });
        }
    }

    /* synthetic */ void b(WorkoutListBean workoutListBean, final UiCallback uiCallback) {
        etr.b().getRecommendWorkouts(workoutListBean.getPageStart(), workoutListBean.getPageSize(), workoutListBean.getClassList(), workoutListBean.getDifficulty(), workoutListBean.getTrainingPoints(), workoutListBean.getSupportWear(), workoutListBean.getEquipments(), workoutListBean.getCommodityFlag(), new UiCallback<List<FitWorkout>>() { // from class: eou.1
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                uiCallback.onFailure(i, str);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(List<FitWorkout> list) {
                uiCallback.onSuccess(mod.j(list));
            }
        });
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void getRecommendCoursesByRules(final UiCallback<List<Workout>> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("CourseImpl", "getRecommendCoursesByRules: callback is null.");
        } else {
            etr.b().getWorkoutRecommendList(new UiCallback<List<FitWorkout>>() { // from class: eou.2
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    uiCallback.onFailure(i, str);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public void onSuccess(List<FitWorkout> list) {
                    uiCallback.onSuccess(mod.j(list));
                }
            });
        }
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void getRecWorkoutByCourseType(int i, final UiCallback<List<Workout>> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("CourseImpl", "getRecommendCoursesByRules: callback is null.");
        } else {
            etr.b().getRecWorkoutByCourseTypeList(i, new UiCallback<List<FitWorkout>>() { // from class: eou.4
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i2, String str) {
                    uiCallback.onFailure(i2, str);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onSuccess(List<FitWorkout> list) {
                    uiCallback.onSuccess(mod.j(list));
                }
            });
        }
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void getRecommendCourseByCloud(DataCallback dataCallback) {
        eqa.a().getRunRecommendCourse(dataCallback);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void getCourseRecommendIndexList(RunCourseRecommendCallback runCourseRecommendCallback) {
        new eux().d(runCourseRecommendCallback);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void getCourseActionList(int i, int i2, int i3, int i4, UiCallback<List<AtomicAction>> uiCallback) {
        etr.b().getActionList(i, i2, i3, i4, uiCallback);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void searchCourseActionList(int i, int i2, int i3, String str, UiCallback<List<AtomicAction>> uiCallback) {
        etr.b().searchActionList(i, i2, i3, str, uiCallback);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void getCourseActionInfo(String str, String str2, UiCallback<AtomicAction> uiCallback) {
        etr.b().getFitnessActionInfo(str, str2, uiCallback);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void getCourseTrainAction(List<String> list, UiCallback<List<TrainAction>> uiCallback) {
        etr.b().getActions(list, uiCallback);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public int getCourseMediaFilesLength(String str, String str2) {
        return etr.b().getWorkoutMediaFilesLength(str, str2);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public boolean isCourseMediaFileDownloaded(String str, String str2, int i) {
        return etr.b().isWorkoutMediaFileHasDownloaded(str, str2, i);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public int getCourseMediaFileSize(String str, String str2, int i) {
        return etr.b().acquireWorkoutMediaFileSize(str, str2, i);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void downloadCourseMediaFiles(fey feyVar, UiCallback<String> uiCallback) {
        etr.b().downloadWorkoutMediaFiles(feyVar, uiCallback);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void downloadCourseMediaFileByPosition(String str, String str2, int i, UiCallback<String> uiCallback) {
        etr.b().downloadWorkoutMediaFileByPosition(str, str2, uiCallback, i);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void downloadCourseMediaFileList(List<Media> list, long j, UiCallback<String> uiCallback) {
        etr.b().downloadWorkoutMediaFile(list, j, uiCallback);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void cancelDownloadCourseMediaFiles(fey feyVar) {
        etr.b().cancelDownloadWorkoutMediaFiles(feyVar);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void cancelDownloadCourseMediaFiles(String str) {
        eqa.a().cancelDownloadFile(str);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void downloadSubtitles(String str, UiCallback<String> uiCallback) {
        etr.b().downloadSubtitles(str, uiCallback);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void downloadFile(String str, String str2, UiCallback<String> uiCallback, boolean z) {
        etr.b().downloadFile(str, str2, uiCallback, z);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void queryTrainCountByCourseId(String str, UiCallback<Integer> uiCallback) {
        etr.b().queryTrainCountByWorkoutId(str, uiCallback);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void getCourseTrainStatistics(int i, UiCallback<TrainStatistics> uiCallback) {
        etr.b().getTrainStatistics(i, uiCallback);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void saveCourseTrainstatis(WorkoutRecord workoutRecord) {
        etr.b().savetrainstatis(workoutRecord);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public WorkoutRecord acquireWorkoutRecordByRecordId(int i) {
        return FitnessHistoryModel.getInstance().acquireWorkoutRecordByRecordId(i);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public long getCourseUseCacheSize() {
        return etr.b().getUseCacheSize();
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void delCourseUseCache(UiCallback<Boolean> uiCallback) {
        etr.b().delUseCache(uiCallback);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void updateCourseDataState() {
        LogUtil.a("CourseImpl", "CourseImpl updateCourseDataState");
        etx.b().a();
        ary.a().e("CACHE_CLEAR");
    }

    @Override // com.huawei.health.course.api.CourseApi
    public List<WorkoutRecord> getWorkoutRecords(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3)) {
            return etr.b().getWorkoutRecords(str);
        }
        return etr.b().getWorkoutRecords(str, str2, str3);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public List<WorkoutRecord> getWorkoutRecords(String str, UiCallback<List<WorkoutRecord>> uiCallback) {
        return etr.b().getWorkoutRecords(str, uiCallback);
    }

    @Override // com.huawei.health.course.api.CourseApi
    @Deprecated
    public void getCourseLongVideoInfo(String str, UiCallback<LongVideoInfo> uiCallback) {
        getCourseLongVideoInfo(new ffl.d(str).b(), uiCallback);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void getCourseLongVideoInfo(ffl fflVar, UiCallback<LongVideoInfo> uiCallback) {
        etr.b().getLongVideoInfo(fflVar, uiCallback);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void getWorkoutCount(String str, String str2, UiCallback<Integer> uiCallback) {
        etr.b().getWorkoutCount(new ffl.d(str).d(str2).b(), uiCallback);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void registerFitnessCourseStatusListen(IBaseStatusCallback iBaseStatusCallback) {
        LogUtil.a("CourseImpl", "registerFitnessCourseStatusListen enter");
        this.f12128a = iBaseStatusCallback;
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void markFitnessCourseStatusOccupied() {
        LogUtil.a("CourseImpl", "mark MotionTrack status onOccupied");
        IBaseStatusCallback iBaseStatusCallback = this.f12128a;
        if (iBaseStatusCallback != null) {
            iBaseStatusCallback.onOccupied();
        }
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void markFitnessCourseStatusAvailable() {
        LogUtil.a("CourseImpl", "mark MotionTrack status onOccupied");
        IBaseStatusCallback iBaseStatusCallback = this.f12128a;
        if (iBaseStatusCallback != null) {
            iBaseStatusCallback.onAvailable();
        }
    }

    @Override // com.huawei.health.course.api.CourseApi
    public int getTodayTaskStatus(long j) {
        return euc.e().d(j);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public boolean hasDoneTodayRunTask(Plan plan) {
        if (plan == null) {
            LogUtil.h("CourseImpl", "hasDoneTodayRunTask failed, plan == null");
            return false;
        }
        List<WorkoutRecord> workoutRecords = etr.b().getWorkoutRecords(plan.acquireId());
        long b = gib.b(System.currentTimeMillis());
        for (WorkoutRecord workoutRecord : workoutRecords) {
            if (workoutRecord != null && b == gib.b(workoutRecord.acquireExerciseTime())) {
                return true;
            }
        }
        return false;
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void addUserDefinedWorkout(FitWorkout fitWorkout, UiCallback<FitWorkout> uiCallback) {
        etr.b().addUserDefinedWorkout(fitWorkout, uiCallback);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void modifyUserDefinedWorkout(FitWorkout fitWorkout, UiCallback<FitWorkout> uiCallback) {
        etr.b().modifyUserDefinedWorkout(fitWorkout, uiCallback);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void deleteUserDefinedWorkout(String str, int i, UiCallback<String> uiCallback) {
        etr.b().deleteUserDefinedWorkout(str, i, uiCallback);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public WorkoutRecord acquireWorkoutRecordByExerciseTime(String str, long j, String str2) {
        return euj.e(str, j, str2);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void queryAudiosPackageBySeriesId(String str, UiCallback<List<SleepAudioPackage>> uiCallback) {
        etr.b().queryAudiosPackageBySeriesId(str, uiCallback);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void getAudioBehaviorList(int i, int i2, int i3, int i4, UiCallback<List<SleepAudioSeries>> uiCallback) {
        etr.b().getAudioBehaviorList(i, i2, i3, i4, uiCallback);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void getAudioBehaviorList(fff fffVar, UiCallback<List<SleepAudioSeries>> uiCallback) {
        etr.b().getAudioBehaviorList(fffVar, uiCallback);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void operateFavoriteAudio(int i, int i2, UiCallback<Boolean> uiCallback) {
        etr.b().operateFavoriteAudio(i, i2, uiCallback);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void deletePlayRecord(List<Integer> list, UiCallback<Boolean> uiCallback) {
        etr.b().deletePlayRecord(list, uiCallback);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void getWorkoutPackageById(ffk ffkVar, UiCallback<WorkoutPackageInfo> uiCallback) {
        if (TextUtils.isEmpty(ffkVar.e())) {
            ffkVar = new ffk.d(ffkVar.d()).e(etd.a()).c();
        }
        etr.b().getWorkoutPackageById(ffkVar, uiCallback);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public FitWorkout getFitWorkout(String str, String str2) {
        return etr.b().getFitWorkout(str, str2);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public List<Media> getMediaFiles(FitWorkout fitWorkout) {
        return etr.b().getMediaFiles(fitWorkout);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void getFitnessActionTemplate(String str, UiCallback<mms> uiCallback) {
        etr.b().getFitnessActionTemplate(str, uiCallback);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void getFitnessMaxScore(String str, int i, UiCallback<mmo> uiCallback) {
        etr.b().getFitnessMaxScore(str, i, uiCallback);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void getUserRank(String str, String str2, double d, long j, UiCallback<mmx> uiCallback) {
        erc ercVar = new erc();
        ercVar.d(str);
        ercVar.c(str2);
        ercVar.e(d);
        ercVar.d(j);
        etr.b().getUserRank(ercVar, uiCallback);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public RecordData coverRecordData(mob mobVar) {
        return new epv().c(mobVar);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public List<WorkoutRecord> getWorkoutRecords(long j, long j2) {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            LogUtil.b("CourseImpl", "getWorkoutRecords(), accountInfo == null");
            return new ArrayList();
        }
        return euj.c(accountInfo, j, j2);
    }

    @Override // com.huawei.health.course.api.CourseApi
    public void updateCourseDbData(final FitWorkout fitWorkout) {
        if (fitWorkout == null) {
            ReleaseLogUtil.d("CourseImpl", "updateCourseData fitWorkout null");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: eoz
                @Override // java.lang.Runnable
                public final void run() {
                    eou.this.a(fitWorkout);
                }
            });
        }
    }

    /* synthetic */ void a(FitWorkout fitWorkout) {
        String huidOrDefault = LoginInit.getInstance(BaseApplication.getContext()).getHuidOrDefault();
        FitWorkout b = ett.i().l().b(huidOrDefault, fitWorkout.acquireId(), fitWorkout.accquireVersion(), etd.a());
        boolean isNeedGetFromCloud = isNeedGetFromCloud(fitWorkout, new ffl.d(fitWorkout.acquireId()).d(fitWorkout.accquireVersion()).c(etd.a()).b(), ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo());
        ReleaseLogUtil.e("CourseImpl", "updateCourseDbData isNeedUpdate:", Boolean.valueOf(isNeedGetFromCloud));
        if (b == null || isNeedGetFromCloud) {
            etx.b().a(fitWorkout.acquireId(), "", fitWorkout.accquireVersion(), etd.a());
            ett.i().l().b(huidOrDefault, fitWorkout);
        }
    }

    @Override // com.huawei.health.course.api.CourseApi
    public boolean isNeedGetFromCloud(FitWorkout fitWorkout, ffl fflVar, UserInfomation userInfomation) {
        if (userInfomation != null) {
            return fitWorkout == null || fitWorkout.acquireCommodityFlag() != 0 || fitWorkout.getTemplateType() == 1 || fitWorkout.getIsAi() == 1 || etx.b().d(fflVar.h(), etn.b(userInfomation.getGenderOrDefaultValue()), fflVar.g(), fflVar.d());
        }
        ReleaseLogUtil.d("CourseImpl", "isNeedGetFromCloud userInfo == null");
        return true;
    }
}
