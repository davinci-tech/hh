package defpackage;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.device.model.RecordAction;
import com.huawei.health.fitnessadvice.api.FitnessAdviceApi;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.sport.CourseUpdateListener;
import com.huawei.health.sport.ITargetUpdateListener;
import com.huawei.health.sport.model.CourseEnvParams;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.sport.model.data.RecordPlanInfo;
import com.huawei.health.suggestion.PluginSuggestion;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwfoundationmodel.trackmodel.ISportDataCallback;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.operation.utils.Constants;
import com.huawei.pluginfitnessadvice.ChoreographedSingleAction;
import com.huawei.pluginfitnessadvice.FitWorkout;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.util.LogUtil;
import health.compact.a.utils.StringUtils;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class gtj implements ITargetUpdateListener, ISportDataCallback {
    private static final Object c = new Object();
    private List<ChoreographedSingleAction> b;
    private int e;
    private List<RecordAction> f;
    private CourseUpdateListener g;
    private FitWorkout i;
    private RecordPlanInfo j;
    private String m;
    private WorkoutRecord n;
    private int h = -1;
    private cab d = new cab();
    private int o = 0;
    private String l = Integer.toString(20002);

    /* renamed from: a, reason: collision with root package name */
    private Map<String, Object> f12924a = new HashMap();

    public void aTC_(Bundle bundle) {
        if (bundle != null) {
            FitWorkout fitWorkout = (FitWorkout) bundle.getParcelable("runCourseDetail");
            RecordPlanInfo recordPlanInfo = (RecordPlanInfo) bundle.getParcelable("coursePlanInfo");
            this.o = bundle.getInt("runningCourse", 0);
            this.m = bundle.getString("workout_package_id");
            this.e = bundle.getInt("course_belong_type");
            if (bundle.getSerializable("BI_INTENT_COURSE") instanceof Map) {
                this.f12924a = (Map) bundle.getSerializable("BI_INTENT_COURSE");
            }
            this.f12924a = Collections.synchronizedMap(this.f12924a);
            c(fitWorkout, recordPlanInfo);
        }
    }

    public void c(FitWorkout fitWorkout, RecordPlanInfo recordPlanInfo) {
        if (fitWorkout == null) {
            return;
        }
        this.i = fitWorkout;
        this.j = recordPlanInfo;
        k();
        d(fitWorkout);
        j();
        e(true);
        n();
    }

    private void d(FitWorkout fitWorkout) {
        if (fitWorkout.isNewRunCourse()) {
            this.b = ggs.c(this.i);
            return;
        }
        this.b = ggs.a(ggs.e(this.i));
        FitWorkout fitWorkout2 = this.i;
        fitWorkout2.saveMeasurementType(kxe.e(fitWorkout2.acquireMeasurementType()));
    }

    public int a() {
        return this.o;
    }

    public boolean c() {
        FitWorkout fitWorkout = this.i;
        if (fitWorkout != null) {
            return fitWorkout.isCustomCourse();
        }
        return false;
    }

    public boolean e() {
        FitWorkout fitWorkout = this.i;
        if (fitWorkout != null) {
            return fitWorkout.isMusicRun();
        }
        return false;
    }

    public Map<String, Object> d() {
        HashMap hashMap;
        synchronized (this.f12924a) {
            hashMap = new HashMap(this.f12924a);
        }
        return hashMap;
    }

    private void j() {
        if (!i()) {
            LogUtil.c("Track_CourseManager", "initRecordData() failed with invalid course info.");
            return;
        }
        if (this.n != null) {
            LogUtil.c("Track_CourseManager", "initRecordData() success with mWorkoutRecord has init.");
            return;
        }
        WorkoutRecord workoutRecord = new WorkoutRecord();
        this.n = workoutRecord;
        workoutRecord.saveWorkoutId(this.i.acquireId());
        this.n.saveCalorie(this.i.acquireCalorie());
        this.n.saveCategory(this.i.getCategory());
        this.n.saveDistance((float) this.i.acquireDistance());
        this.n.saveWorkoutId(this.i.acquireId());
        this.n.saveWorkoutName(this.i.acquireName());
        this.n.saveWorkoutDate(DateFormatUtil.b(System.currentTimeMillis(), DateFormatUtil.DateFormatType.DATE_FORMAT_YYYY_MM_DD));
        if (TextUtils.isEmpty(this.i.accquireVersion())) {
            this.n.saveVersion("2.0");
        } else {
            this.n.saveVersion(this.i.accquireVersion());
        }
        RecordPlanInfo recordPlanInfo = this.j;
        if (recordPlanInfo != null) {
            e(this.n, recordPlanInfo);
        }
        this.n.setTargetType(this.i.acquireMeasurementType());
        this.n.setTargetValue((long) (this.i.acquireMeasurementType() == 1 ? this.i.acquireDuration() : this.i.acquireDistance()));
        this.n.setCourseDefineType(this.i.getCourseDefineType());
        this.n.setCourseSportType(gtx.c(BaseApplication.e()).n());
        this.n.setCourseModifiedTime(this.i.getModified() == 0 ? this.i.getPublishDate() : this.i.getModified());
        this.n.saveFinishRate(0.0f);
        this.n.setSportRecordType(1);
        this.n.saveWorkoutPackageId(this.m);
        this.n.saveCourseBelongType(this.e);
    }

    private void e(WorkoutRecord workoutRecord, RecordPlanInfo recordPlanInfo) {
        workoutRecord.saveRecordType(1);
        workoutRecord.setPlanTrainDate(recordPlanInfo.getPlanTrainDate());
        workoutRecord.savePlanId(recordPlanInfo.getPlanId());
        workoutRecord.saveWorkoutOrder(recordPlanInfo.getWorkoutOrder());
    }

    private void e(final boolean z) {
        cab cabVar = new cab();
        this.d = cabVar;
        cabVar.b(new CourseEnvParams.OnInitCompleteListener() { // from class: gti
            @Override // com.huawei.health.sport.model.CourseEnvParams.OnInitCompleteListener
            public final void onInitComplete() {
                gtj.this.b(z);
            }
        });
        this.d.e();
    }

    /* synthetic */ void b(boolean z) {
        if (i()) {
            List<RecordAction> list = this.f;
            if ((list == null || list.size() != this.b.size()) && this.d.c() != null) {
                this.f = ggs.e(this.b, this.d.c().getMaxThreshold());
            }
            if (z) {
                gtx.c(BaseApplication.e()).a(ghp.e(this.b, this.d));
            }
            gtx.c(BaseApplication.e()).b(this);
            gtx.c(BaseApplication.e()).a(this, 1L);
            this.d.e(gtx.c(BaseApplication.e()).al());
            this.g = PluginSuggestion.getInstance().getRunCourseVoiceManager(this.b, this.i, this.o == 2, this.d);
            gtx.c(BaseApplication.e()).e("courseVoice", this.g);
            gtx.c(BaseApplication.e()).b("courseVoice", this.g);
            List<ffd> l = gtx.c(BaseApplication.e()).l();
            if (koq.c(l)) {
                ffd ffdVar = l.get(0);
                if (z || ffdVar == null || ffdVar.c() < 1) {
                    return;
                }
                int c2 = ffdVar.c() - 1;
                this.h = c2;
                this.g.seekToAction(c2);
            }
        }
    }

    public void e(MotionPathSimplify motionPathSimplify) {
        l();
        FitWorkout fitWorkout = this.i;
        if (fitWorkout == null) {
            LogUtil.c("Track_CourseManager", "restoreData with null workout");
            return;
        }
        if (motionPathSimplify != null) {
            this.o = motionPathSimplify.requestRunCourseType();
            String extendDataString = motionPathSimplify.getExtendDataString("planInfo");
            if (!TextUtils.isEmpty(extendDataString)) {
                this.j = (RecordPlanInfo) nrv.b(extendDataString, RecordPlanInfo.class);
            }
        } else {
            this.o = ggs.d(fitWorkout) ? 2 : 1;
        }
        d(this.i);
        j();
        e(false);
    }

    public void c(MotionPathSimplify motionPathSimplify) {
        WorkoutRecord workoutRecord;
        if (!i() || motionPathSimplify == null || (workoutRecord = this.n) == null) {
            return;
        }
        motionPathSimplify.saveRunCourseId(workoutRecord.acquireWorkoutId());
        motionPathSimplify.saveRunCourseType(this.o);
        motionPathSimplify.addExtendDataMap("courseName", this.n.acquireWorkoutName());
        motionPathSimplify.addExtendDataMap("sportRecordType", String.valueOf(0));
        motionPathSimplify.addExtendDataMap("attachedRecordType", String.valueOf(1));
        RecordPlanInfo recordPlanInfo = this.j;
        if (recordPlanInfo != null) {
            motionPathSimplify.addExtendDataMap("planInfo", nrv.a(recordPlanInfo));
        }
        FitWorkout fitWorkout = this.i;
        if (fitWorkout != null) {
            motionPathSimplify.addExtendDataMap("calorie", String.valueOf(fitWorkout.acquireCalorie()));
            motionPathSimplify.addExtendDataMap("distance", String.valueOf(this.i.acquireDistance()));
            motionPathSimplify.addExtendDataMap("courseVersion", this.i.accquireVersion());
            motionPathSimplify.addExtendDataMap("wearType", String.valueOf(0));
        }
        m();
    }

    public boolean i() {
        return this.i != null && koq.c(this.b);
    }

    @Override // com.huawei.health.sport.ITargetUpdateListener
    public void onTargetDataUpdate(ffd ffdVar) {
        RecordAction recordAction;
        if (ffdVar == null || !koq.d(this.f, ffdVar.c() - 1) || (recordAction = this.f.get(ffdVar.c() - 1)) == null) {
            return;
        }
        a(recordAction, ffdVar);
        m();
    }

    private void a(RecordAction recordAction, ffd ffdVar) {
        recordAction.setFinishRate(ffdVar.a() * 100.0f);
        int actionTargetType = recordAction.getActionTargetType();
        if (actionTargetType == 1) {
            recordAction.setFinishedAction((int) TimeUnit.MILLISECONDS.toSeconds((long) (ffdVar.j() - ffdVar.d())));
        } else if (actionTargetType == 255) {
            recordAction.setFinishedAction((int) TimeUnit.MILLISECONDS.toSeconds((long) (ffdVar.j() - ffdVar.d())));
            recordAction.setFinishRate(100.0f);
        } else if (actionTargetType == 3) {
            recordAction.setFinishedAction(Math.round((ffdVar.j() - ffdVar.d()) / 1000.0f));
        } else if (actionTargetType == 4 || actionTargetType == 5) {
            recordAction.setFinishedAction(Math.round(ffdVar.j()));
        } else {
            recordAction.setFinishedAction((int) (ffdVar.j() - ffdVar.d()));
        }
        int actionTargetType2 = recordAction.getActionTargetType();
        if ((actionTargetType2 == 0 || actionTargetType2 == 1 || actionTargetType2 == 3) && recordAction.getFinishedAction() > recordAction.getActionTargetValue()) {
            recordAction.setFinishedAction((int) recordAction.getActionTargetValue());
        }
    }

    @Override // com.huawei.health.sport.ITargetUpdateListener
    public void onStateUpdate(int i, String str) {
        LogUtil.d("Track_CourseManager", "change action", Integer.valueOf(i), str);
        int i2 = this.h + 1;
        this.h = i2;
        CourseUpdateListener courseUpdateListener = this.g;
        if (courseUpdateListener != null) {
            courseUpdateListener.switchAction(i2);
        }
    }

    @Override // com.huawei.hwfoundationmodel.trackmodel.ISportDataCallback
    public void getSportInfo(Bundle bundle) {
        if (bundle.getInt("sportState", 0) == 3) {
            LogUtil.d("Track_CourseManager", "sportState is stop.");
            return;
        }
        CourseUpdateListener courseUpdateListener = this.g;
        if (courseUpdateListener != null) {
            if (this.h == -1) {
                this.h = 0;
                courseUpdateListener.switchAction(0);
            }
            this.g.getSportInfo(bundle);
        }
    }

    @Override // com.huawei.hwfoundationmodel.trackmodel.ISportDataCallback
    public void onSummary(MotionPathSimplify motionPathSimplify) {
        h();
        CourseUpdateListener courseUpdateListener = this.g;
        if (courseUpdateListener != null) {
            courseUpdateListener.onSummary(motionPathSimplify);
        }
    }

    private float g() {
        float f = 0.0f;
        if (koq.b(this.f)) {
            LogUtil.c("Track_CourseManager", "getFinishRate isEmpty(mChoreographedMultiActionsAll) true");
            return 0.0f;
        }
        int size = this.f.size();
        for (RecordAction recordAction : this.f) {
            if (recordAction == null) {
                LogUtil.c("Track_CourseManager", "getFinishRate action == null");
            } else {
                float finishRate = recordAction.getFinishRate();
                LogUtil.d("Track_CourseManager", "actionFinish:", recordAction.getActionName(), " rate:", Float.valueOf(finishRate));
                f += finishRate;
            }
        }
        return ghp.d(f, size * 100);
    }

    private void k() {
        c("runCourseDetail", (String) this.i);
        c("coursePlanInfo", (String) this.j);
    }

    private void l() {
        this.i = (FitWorkout) d("runCourseDetail", FitWorkout.class);
        this.j = (RecordPlanInfo) d("coursePlanInfo", RecordPlanInfo.class);
        WorkoutRecord workoutRecord = (WorkoutRecord) d("runCourseRecordTmp", WorkoutRecord.class);
        this.n = workoutRecord;
        if (workoutRecord != null) {
            try {
                this.f = (List) nrv.c(workoutRecord.acquireActionSummary(), new TypeToken<List<RecordAction>>() { // from class: gtj.2
                }.getType());
            } catch (JsonSyntaxException unused) {
                LogUtil.e("Track_CourseManager", "JsonSyntaxException");
            }
        }
    }

    private void m() {
        synchronized (c) {
            this.n.saveActionSummary(nrv.a(this.f));
            c("runCourseRecordTmp", (String) this.n);
        }
    }

    private <T> void c(String str, T t) {
        if (t == null) {
            LogUtil.c("Track_CourseManager", "saveDataToSp object == null");
            return;
        }
        try {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.serializeSpecialFloatingPointValues();
            SharedPreferenceManager.e(com.huawei.hwcommonmodel.application.BaseApplication.getContext(), this.l, str, gsonBuilder.create().toJson(t), (StorageParams) null);
        } catch (ConcurrentModificationException e) {
            ReleaseLogUtil.c("Track_CourseManager", "saveDataToSp ConcurrentModificationException:", LogAnonymous.b((Throwable) e));
        }
    }

    private <T> T d(String str, Type type) {
        return (T) nrv.c(SharedPreferenceManager.b(com.huawei.hwcommonmodel.application.BaseApplication.getContext(), this.l, str), type);
    }

    public void b() {
        SharedPreferenceManager.c(BaseApplication.e(), this.l, "runCourseDetail");
        SharedPreferenceManager.c(BaseApplication.e(), this.l, "coursePlanInfo");
        SharedPreferenceManager.c(BaseApplication.e(), this.l, "runCourseRecordTmp");
    }

    public void b(MotionPathSimplify motionPathSimplify, MotionPath motionPath) {
        if (!i() || motionPathSimplify == null) {
            LogUtil.c("Track_CourseManager", "saveDataToDb failed isValid = false motionPathSimplify is null.");
            return;
        }
        e(motionPathSimplify, motionPath);
        FitnessAdviceApi fitnessAdviceApi = (FitnessAdviceApi) Services.a("PluginFitnessAdvice", FitnessAdviceApi.class);
        if (fitnessAdviceApi != null) {
            LogUtil.c("Track_CourseManager", "saveDataToDb is:", Boolean.valueOf(fitnessAdviceApi.uploadFitnessRecordData(this.n)));
        }
        b();
        f();
    }

    private void e(MotionPathSimplify motionPathSimplify, MotionPath motionPath) {
        cab cabVar;
        synchronized (c) {
            if (this.n != null && motionPathSimplify != null && i()) {
                this.n.saveActualDistance(moe.d(motionPathSimplify.requestTotalDistance()));
                this.n.saveActualCalorie(motionPathSimplify.requestTotalCalories());
                this.n.setDuration((int) motionPathSimplify.requestTotalTime());
                if (this.i.isNewRunCourse()) {
                    this.n.saveFinishRate(g());
                } else if (this.n.getTargetType() == 1) {
                    this.n.saveFinishRate(ghp.d(r1.getDuration(), moe.c((int) this.n.getTargetValue())));
                } else {
                    WorkoutRecord workoutRecord = this.n;
                    workoutRecord.saveFinishRate(ghp.d(workoutRecord.acquireDistance(), (int) this.n.getTargetValue()));
                }
                if (motionPath != null && (cabVar = this.d) != null) {
                    int d = caz.d(motionPath, cabVar, this.n.startTime());
                    motionPathSimplify.addExtendDataMap(HwExerciseConstants.JSON_NAME_TRAINING_POINTS, String.valueOf(d));
                    this.n.setTrainPoint(d);
                }
                ReleaseLogUtil.e("Track_CourseManager", "updateRecordCourseSummary acquireFinishRate:", Float.valueOf(this.n.acquireFinishRate()));
                motionPathSimplify.addExtendDataMap("completionRate", String.valueOf(this.n.acquireFinishRate()));
                motionPathSimplify.addExtendDataMap(HwExerciseConstants.JSON_NAME_PLAN_COURSE_TIME, String.valueOf(this.n.getPlanTrainDate()));
                ReleaseLogUtil.e("Track_CourseManager", "updateRecordCourseSummary getPlanTrainDate:", Integer.valueOf(this.n.getPlanTrainDate()));
                this.n.setStartTime(motionPathSimplify.requestStartTime());
                this.n.saveExerciseTime(motionPathSimplify.requestEndTime());
                this.n.saveTrajectoryId(motionPathSimplify.requestStartTime() + "_" + motionPathSimplify.requestEndTime());
                motionPathSimplify.addExtendDataMap("courseExtend", this.n.acquireExtend());
                gwo.b(BaseApplication.e(), c(this.n.acquireActionSummary()), "motion_path2.txt");
                return;
            }
            LogUtil.c("Track_CourseManager", "updateRecordCourseSummary failed with workout is null or is invalid.");
        }
    }

    public static String c(String str) {
        List list;
        if (TextUtils.isEmpty(str)) {
            LogUtil.c("Track_CourseManager", "getActionSummary isEmpty");
            return str;
        }
        try {
            list = (List) nrv.c(str, new TypeToken<List<RecordAction>>() { // from class: gtj.5
            }.getType());
        } catch (JsonSyntaxException unused) {
            LogUtil.e("Track_CourseManager", "JsonSyntaxException in getActionSummaryFileDetail");
            list = null;
        }
        if (koq.b(list)) {
            LogUtil.c("Track_CourseManager", "actionList isEmpty");
            return null;
        }
        StringBuilder sb = new StringBuilder();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            sb.append(((RecordAction) it.next()).getTrackFileString());
        }
        return sb.toString();
    }

    private void n() {
        WorkoutRecord workoutRecord = new WorkoutRecord();
        WorkoutRecord workoutRecord2 = this.n;
        if (workoutRecord2 != null) {
            workoutRecord.savePlanId(workoutRecord2.acquirePlanId());
            workoutRecord.saveWorkoutId(this.n.acquireWorkoutId());
            workoutRecord.saveExerciseTime(System.currentTimeMillis());
            if (StringUtils.g(workoutRecord.acquirePlanId())) {
                return;
            }
            LogUtil.d("Track_CourseManager", "setTrainingCourse:" + workoutRecord.acquireWorkoutId());
            ash.a("intPlanTrainingCourse", new Gson().toJson(workoutRecord));
        }
    }

    private void h() {
        LogUtil.d("Track_CourseManager", "removeTrainingCourse");
        ash.d("intPlanTrainingCourse");
    }

    private void f() {
        final PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null || this.n == null) {
            return;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: gtp
            @Override // java.lang.Runnable
            public final void run() {
                gtj.this.a(planApi);
            }
        });
    }

    /* synthetic */ void a(PlanApi planApi) {
        planApi.b(new UiCallback<IntPlan>() { // from class: gtj.4
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                gtj.this.o();
                HashMap hashMap = new HashMap(1);
                hashMap.put("data", nrv.a(gtj.this.f12924a));
                gge.e("1120005", hashMap);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(IntPlan intPlan) {
                gtj.this.o();
                if (intPlan != null && TextUtils.equals(intPlan.getPlanId(), gtj.this.n.acquirePlanId())) {
                    gtj.this.f12924a.put("plan_name", intPlan.getMetaInfo().getName());
                    gtj.this.f12924a.put("type", Integer.valueOf(gtj.this.d(intPlan)));
                }
                HashMap hashMap = new HashMap(1);
                hashMap.put("data", nrv.a(gtj.this.f12924a));
                gge.e("1120005", hashMap);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        this.f12924a.put("start_time", Long.valueOf(this.n.startTime()));
        this.f12924a.put("end_time", Long.valueOf(this.n.acquireExerciseTime()));
        this.f12924a.put("course_version", this.n.acquireVersion());
        this.f12924a.put("calories", Integer.valueOf((int) this.n.acquireActualCalorie()));
        this.f12924a.put("distance", Float.valueOf(this.n.acquireActualDistance()));
        this.f12924a.put("duration", Integer.valueOf(this.n.getDuration()));
        this.f12924a.put("finish_rate", Float.valueOf(this.n.acquireFinishRate()));
        this.f12924a.put("isAICourse", Constants.NULL);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int d(IntPlan intPlan) {
        if (intPlan.getPlanType().equals(IntPlan.PlanType.AI_RUN_PLAN)) {
            return 0;
        }
        return intPlan.getPlanType().equals(IntPlan.PlanType.AI_FITNESS_PLAN) ? 1 : 2;
    }
}
