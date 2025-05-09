package defpackage;

import com.google.gson.Gson;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.ExerciseProfile;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.basefitnessadvice.model.PlanWorkout;
import com.huawei.basefitnessadvice.model.RunWorkout;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.exercise.modle.IExerciseAdviceCallback;
import com.huawei.exercise.modle.RunPlanInfo;
import com.huawei.exercise.modle.RunPlanParameter;
import com.huawei.exercise.modle.RunPlanRecordInfo;
import com.huawei.exercise.modle.RunPlanReminder;
import com.huawei.exercise.modle.RunPlanStruct;
import com.huawei.exercise.modle.RunWorkoutPlanStruct;
import com.huawei.exercise.modle.TrainingStruct;
import com.huawei.exercise.modle.WorkoutRecordStatistic;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwfoundationmodel.trackmodel.ISportDataCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.Workout;
import health.compact.a.CommonUtil;
import health.compact.a.HEXUtils;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class bnl {
    private static String b = "yyyy-MM-dd";
    private static long c = 86400000;
    private static volatile bnl d = null;
    private static int e = -1;
    private Plan g;
    private boolean j;
    private String n;
    private List<PlanWorkout> s;
    private List<RunWorkout> t;
    private boolean i = false;
    private boolean l = false;
    private String k = "";
    private boolean m = false;
    private RunPlanRecordInfo x = null;
    private boolean q = false;
    private IExerciseAdviceCallback v = null;
    private IBaseResponseCallback p = null;
    private boolean o = false;
    private boolean f = false;
    private int r = 0;
    private IBaseResponseCallback u = new IBaseResponseCallback() { // from class: bnl.5
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.c("Track_HealthAdapter_HWExerciseAdviceManager", "mServiceConnectedListener objData: " + obj);
            if (100000 != i || bnl.this.v == null) {
                return;
            }
            LogUtil.a("Track_HealthAdapter_HWExerciseAdviceManager", "ServiceConnected");
            bnl bnlVar = bnl.this;
            bnlVar.e(bnlVar.v.getDeviceCapability());
            bnl.this.f();
            if (bnl.this.j) {
                return;
            }
            bnl.this.v.registerConnectionStatusChangeNotification(bnl.this.h);
            bnl.this.j = true;
        }
    };
    private IBaseResponseCallback h = new IBaseResponseCallback() { // from class: bnl.2
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.c("Track_HealthAdapter_HWExerciseAdviceManager", "registerConnectionStatusChangeNotification objData: " + obj);
            try {
                JSONObject jSONObject = new JSONObject(String.valueOf(obj));
                if ("deviceInstantConnected".equals(jSONObject.get("state")) || "health_deviceInstantConnected".equals(jSONObject.get("state"))) {
                    if (bnl.this.v != null) {
                        bnl bnlVar = bnl.this;
                        bnlVar.e(bnlVar.v.getDeviceCapability());
                    }
                    bnl.this.f();
                }
            } catch (JSONException e2) {
                LogUtil.b("Track_HealthAdapter_HWExerciseAdviceManager", "mConnectStateChangedCallback error=" + e2.getMessage());
            }
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private IBaseResponseCallback f444a = new IBaseResponseCallback() { // from class: bnl.3
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            if (100000 == i) {
                LogUtil.c("Track_HealthAdapter_HWExerciseAdviceManager", String.valueOf(obj));
                RunPlanParameter runPlanParameter = (RunPlanParameter) moj.e((String) obj, RunPlanParameter.class);
                LogUtil.a("Track_HealthAdapter_HWExerciseAdviceManager", "runplanlog Run_plan_sync_size_pre=" + runPlanParameter.getRun_plan_sync_size_pre() + ",Run_plan_sync_size_sub=" + runPlanParameter.getRun_plan_sync_size_sub() + ",deviceSHAValue=" + runPlanParameter.getRun_plan_sign());
                StringBuilder sb = new StringBuilder("runplanlog getAdviceParamCallback mDeviceSupportCapacity");
                sb.append(bnl.this.i);
                LogUtil.a("Track_HealthAdapter_HWExerciseAdviceManager", sb.toString());
                bnl.this.a(runPlanParameter);
                return;
            }
            LogUtil.b("Track_HealthAdapter_HWExerciseAdviceManager", "runplanlog getAdviceParam fail err=" + i);
        }
    };
    private IBaseResponseCallback y = new IBaseResponseCallback() { // from class: bnl.8
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            if (100000 == i) {
                LogUtil.a("Track_HealthAdapter_HWExerciseAdviceManager", "pushNewPlanCallback response success");
            } else {
                LogUtil.b("Track_HealthAdapter_HWExerciseAdviceManager", "pushNewPlanCallback return fail: " + i);
            }
            bnl.this.g();
        }
    };
    private IBaseResponseCallback w = new IBaseResponseCallback() { // from class: bnl.7
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            if (100000 == i) {
                LogUtil.a("Track_HealthAdapter_HWExerciseAdviceManager", "SetExercisePlanReminderCallback success");
                return;
            }
            LogUtil.b("Track_HealthAdapter_HWExerciseAdviceManager", "SetExercisePlanReminderCallback fail err=" + i);
        }
    };

    public static bnl a() {
        if (d == null) {
            synchronized (bnl.class) {
                if (d == null) {
                    d = new bnl();
                }
            }
        }
        return d;
    }

    public void a(IExerciseAdviceCallback iExerciseAdviceCallback) {
        LogUtil.a("Track_HealthAdapter_HWExerciseAdviceManager", "registerExerciseAdviceCallback enter");
        this.v = iExerciseAdviceCallback;
        e();
    }

    public void e(Object obj) {
        if (obj == null) {
            LogUtil.b("Track_HealthAdapter_HWExerciseAdviceManager", "setRunPlanETEReport() recordInfo == null");
            return;
        }
        LogUtil.c("Track_HealthAdapter_HWExerciseAdviceManager", "setRunPlanETEReport=" + String.valueOf(obj));
        try {
            this.x = (RunPlanRecordInfo) moj.e(new JSONArray(obj.toString()).get(0).toString(), RunPlanRecordInfo.class);
            this.q = true;
            if (!this.i || this.l || this.r == 3) {
                return;
            }
            gso.e().d((ISportDataCallback) null);
            this.f = true;
        } catch (JSONException e2) {
            LogUtil.b("Track_HealthAdapter_HWExerciseAdviceManager", "setETERport error=" + e2.getMessage());
        }
    }

    public void a(Object obj) {
        LogUtil.c("Track_HealthAdapter_HWExerciseAdviceManager", "setWorkoutETEReport=" + String.valueOf(obj));
        if (obj == null) {
            LogUtil.b("Track_HealthAdapter_HWExerciseAdviceManager", "setWorkoutETEReport() recordInfo == null");
            return;
        }
        try {
            this.x = new RunPlanRecordInfo();
            WorkoutRecordStatistic workoutRecordStatistic = (WorkoutRecordStatistic) moj.e(new JSONObject(obj.toString()).toString(), WorkoutRecordStatistic.class);
            this.x.setRun_plan_record_info_id(workoutRecordStatistic.getWorkout_record_id());
            this.x.setRun_plan_record_info_status(workoutRecordStatistic.getWorkout_record_status());
            this.x.setRun_plan_record_info_load_peak(workoutRecordStatistic.getWorkout_load_peak());
            this.x.setRun_plan_record_info_etraining_effect(workoutRecordStatistic.getWorkout_etraining_effect());
            this.x.setRun_plan_record_info_anaerobic_exercise_etraining_effect(workoutRecordStatistic.getWorkout_anaerobic_exercise_etraining_effect());
            this.x.setRun_plan_record_info_Epoc(workoutRecordStatistic.getWorkout_Epoc());
            this.x.setRun_plan_record_info_maxMET(workoutRecordStatistic.getWorkout_maxMET());
            this.x.setRun_plan_record_info_recovery_time(workoutRecordStatistic.getWorkout_recovery_time());
            this.x.setRun_plan_record_info_achieve_percent(0);
            this.x.setRunPlanAlgoType(workoutRecordStatistic.getmAlgoType());
            this.q = true;
        } catch (JSONException e2) {
            LogUtil.b("Track_HealthAdapter_HWExerciseAdviceManager", "setETERport error=" + e2.getMessage());
        }
    }

    public boolean j() {
        return this.f;
    }

    public void b(boolean z) {
        ReleaseLogUtil.e("Track_HealthAdapter_HWExerciseAdviceManager", "setLinkStatus = " + z);
        this.o = z;
        if (z) {
            this.m = true;
        } else {
            this.m = false;
        }
    }

    private bnl() {
        LogUtil.a("Track_HealthAdapter_HWExerciseAdviceManager", "new HWExerciseAdviceManager");
    }

    public void e() {
        IExerciseAdviceCallback iExerciseAdviceCallback = this.v;
        if (iExerciseAdviceCallback != null) {
            iExerciseAdviceCallback.registerServiceConnectedListener(this.u);
        }
    }

    public void f() {
        IExerciseAdviceCallback iExerciseAdviceCallback;
        if (!this.i || (iExerciseAdviceCallback = this.v) == null) {
            return;
        }
        iExerciseAdviceCallback.getDeviceFitnessPlanParamter(this.f444a);
        LogUtil.a("Track_HealthAdapter_HWExerciseAdviceManager", "getRunPlanParameter");
    }

    public void b() {
        this.q = false;
        this.f = false;
        this.x = null;
    }

    public RunPlanRecordInfo c() {
        LogUtil.a("Track_HealthAdapter_HWExerciseAdviceManager", "getDeviceRunPlanETEResult capacity=" + this.i + ", ETEUSing state=" + this.m);
        if (this.i && 2 == i() && this.m) {
            for (int i = 0; i < 3000; i += 500) {
                LogUtil.c("Track_HealthAdapter_HWExerciseAdviceManager", "getDeviceRunPlanETEResult checktime=" + i);
                if (this.q) {
                    LogUtil.a("Track_HealthAdapter_HWExerciseAdviceManager", "getDeviceRunPlanETEResult return runplan record");
                    IBaseResponseCallback iBaseResponseCallback = this.p;
                    if (iBaseResponseCallback != null) {
                        iBaseResponseCallback.d(0, this.x);
                    }
                    return this.x;
                }
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e2) {
                    LogUtil.b("Track_HealthAdapter_HWExerciseAdviceManager", "getDeviceRunPlanETEResult exception e=" + e2.getMessage());
                    if (this.p != null) {
                        this.p.d(100001, null);
                    }
                    return null;
                }
            }
            this.m = false;
        }
        IBaseResponseCallback iBaseResponseCallback2 = this.p;
        if (iBaseResponseCallback2 != null) {
            iBaseResponseCallback2.d(100001, null);
        }
        return null;
    }

    public void h() {
        f();
    }

    public void a(final RunPlanParameter runPlanParameter) {
        LogUtil.a("Track_HealthAdapter_HWExerciseAdviceManager", "runplanlog newExercisePlan mDeviceSupportCapacity:" + this.i);
        if (this.i) {
            PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
            if (planApi == null) {
                LogUtil.h("Track_HealthAdapter_HWExerciseAdviceManager", "getCurrentPlan planApi is null.");
            } else {
                planApi.b(new UiCallback<IntPlan>() { // from class: bnl.4
                    @Override // com.huawei.basefitnessadvice.callback.UiCallback
                    public void onFailure(int i, String str) {
                        LogUtil.b("Track_HealthAdapter_HWExerciseAdviceManager", "runplanlog getCurrentPlan fail, errorcode=" + i + ",errorinfo=" + str);
                    }

                    @Override // com.huawei.basefitnessadvice.callback.UiCallback
                    /* renamed from: c, reason: merged with bridge method [inline-methods] */
                    public void onSuccess(IntPlan intPlan) {
                        if (intPlan == null || ((!intPlan.getPlanType().equals(IntPlan.PlanType.AI_RUN_PLAN) && !intPlan.getPlanType().equals(IntPlan.PlanType.RUN_PLAN)) || intPlan.getCompatiblePlan() == null)) {
                            bnl.this.d();
                            return;
                        }
                        bnl.this.g = intPlan.getCompatiblePlan();
                        bnl bnlVar = bnl.this;
                        bnlVar.s = bnlVar.g.acquireWorkouts();
                        if (bnl.this.g.acquireType() != 0) {
                            LogUtil.a("Track_HealthAdapter_HWExerciseAdviceManager", "runplanlog plan is not run plan!");
                            return;
                        }
                        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
                        if (courseApi != null) {
                            bnl.this.d(courseApi, runPlanParameter);
                        } else {
                            LogUtil.h("Track_HealthAdapter_HWExerciseAdviceManager", "newExercisePlan, onSuccess: courseApi is null.");
                        }
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(CourseApi courseApi, final RunPlanParameter runPlanParameter) {
        courseApi.getCoursesWithinCurrentPlan(0, new UiCallback<List<Workout>>() { // from class: bnl.1
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                LogUtil.b("Track_HealthAdapter_HWExerciseAdviceManager", "runplanlog getRunWorkout list fail errorcode=" + i + ",errorInfo=" + str);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onSuccess(List<Workout> list) {
                List<RunWorkout> c2 = mod.c(list);
                bnl.this.t = c2;
                List b2 = bnl.this.b(c2);
                if (b2 != null) {
                    RunPlanInfo c3 = bnl.this.c((List<RunWorkoutPlanStruct>) b2, runPlanParameter);
                    bnl.this.n = c3.getRun_plan_total_sign();
                    if (bnl.this.k.equals(runPlanParameter.getRun_plan_sign())) {
                        LogUtil.a("Track_HealthAdapter_HWExerciseAdviceManager", "runplanlog SHA value is same");
                        bnl.this.g();
                    } else {
                        LogUtil.a("Track_HealthAdapter_HWExerciseAdviceManager", "runplanlog SHA value is diffirent");
                        bnl bnlVar = bnl.this;
                        bnlVar.d(c3, bnlVar.y);
                    }
                }
            }
        });
    }

    public void d() {
        RunPlanInfo runPlanInfo = new RunPlanInfo();
        this.k = "00000000000000000000000000000000";
        runPlanInfo.setRun_plan_total_sign("00000000000000000000000000000000");
        runPlanInfo.setRun_plan_sign(HEXUtils.b(this.k));
        runPlanInfo.setRun_plan_start_date(System.currentTimeMillis());
        ArrayList arrayList = new ArrayList();
        RunPlanStruct runPlanStruct = new RunPlanStruct();
        runPlanStruct.setRun_plan_name("finish");
        runPlanStruct.setRun_plan_date(System.currentTimeMillis());
        arrayList.add(runPlanStruct);
        runPlanInfo.setRunPlanStructList(arrayList);
        this.g = null;
        this.t = null;
        LogUtil.a("Track_HealthAdapter_HWExerciseAdviceManager", "SHA value is not same");
        d(runPlanInfo, this.y);
    }

    public void g() {
        IExerciseAdviceCallback iExerciseAdviceCallback;
        IExerciseAdviceCallback iExerciseAdviceCallback2;
        LogUtil.a("Track_HealthAdapter_HWExerciseAdviceManager", "setRunPlanReminder");
        int n = n();
        if (n == e) {
            LogUtil.c("Track_HealthAdapter_HWExerciseAdviceManager", "getRemindTime time is -1 error");
            RunPlanReminder runPlanReminder = new RunPlanReminder();
            runPlanReminder.setRun_plan_reminder_switch(0);
            runPlanReminder.setRun_plan_reminder_time_hour(0);
            runPlanReminder.setRun_plan_reminder_time_minute(0);
            JSONObject c2 = c("runPlanReminder", runPlanReminder);
            if (c2 == null || (iExerciseAdviceCallback2 = this.v) == null) {
                return;
            }
            iExerciseAdviceCallback2.setRunPlanReminderSwitch(c2, this.w);
            return;
        }
        LogUtil.c("Track_HealthAdapter_HWExerciseAdviceManager", "setRunPlanReminder reminderTime=" + n);
        RunPlanReminder runPlanReminder2 = new RunPlanReminder();
        runPlanReminder2.setRun_plan_reminder_switch(1);
        runPlanReminder2.setRun_plan_reminder_time_hour(n / 60);
        runPlanReminder2.setRun_plan_reminder_time_minute(n % 60);
        JSONObject c3 = c("runPlanReminder", runPlanReminder2);
        if (c3 == null || (iExerciseAdviceCallback = this.v) == null) {
            return;
        }
        iExerciseAdviceCallback.setRunPlanReminderSwitch(c3, this.w);
    }

    private int n() {
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.b("Track_HealthAdapter_HWExerciseAdviceManager", "setRunPlanReminder planApi is null.");
            return e;
        }
        planApi.setPlanType(0);
        if (planApi.isOpenRemind()) {
            return planApi.getRemindTime();
        }
        return e;
    }

    private int i() {
        IExerciseAdviceCallback iExerciseAdviceCallback = this.v;
        DeviceInfo currentDeviceInfo = iExerciseAdviceCallback != null ? iExerciseAdviceCallback.getCurrentDeviceInfo() : null;
        if (currentDeviceInfo != null) {
            return currentDeviceInfo.getDeviceConnectState();
        }
        return 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(RunPlanInfo runPlanInfo, IBaseResponseCallback iBaseResponseCallback) {
        IExerciseAdviceCallback iExerciseAdviceCallback;
        LogUtil.a("Track_HealthAdapter_HWExerciseAdviceManager", "setRunPlan");
        JSONObject c2 = c("runPlanInfo", runPlanInfo);
        if (c2 == null || (iExerciseAdviceCallback = this.v) == null) {
            return;
        }
        iExerciseAdviceCallback.pushFitnessPlan(c2, iBaseResponseCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<RunWorkoutPlanStruct> b(List<RunWorkout> list) {
        ArrayList arrayList = new ArrayList();
        Plan plan = this.g;
        if (plan == null) {
            return null;
        }
        long e2 = e(plan.acquireStartDate(), b);
        long e3 = e(this.g.getEndDate(), b);
        int size = this.s.size();
        LogUtil.c("Track_HealthAdapter_HWExerciseAdviceManager", "starttime=" + e2 + ",endtime=" + e3 + ",workoutcount=" + size);
        for (int i = 0; i < size; i++) {
            PlanWorkout planWorkout = this.s.get(i);
            RunWorkoutPlanStruct runWorkoutPlanStruct = new RunWorkoutPlanStruct();
            if (planWorkout.popWorkoutId() == null) {
                LogUtil.c("Track_HealthAdapter_HWExerciseAdviceManager", "planworkout get workoutid = null, workout name=" + planWorkout.popName());
                runWorkoutPlanStruct.setDate(e2);
                runWorkoutPlanStruct.setRunWorkout(null);
                runWorkoutPlanStruct.setWorkoutName(planWorkout.popName());
                arrayList.add(runWorkoutPlanStruct);
            } else {
                if (list == null) {
                    LogUtil.h("Track_HealthAdapter_HWExerciseAdviceManager", "insertRestDayDate runWorkouts == null");
                    return null;
                }
                for (int i2 = 0; i2 < list.size(); i2++) {
                    RunWorkout runWorkout = list.get(i2);
                    if (runWorkout.acquireId().equalsIgnoreCase(planWorkout.popWorkoutId())) {
                        runWorkoutPlanStruct.setDate(e2);
                        runWorkoutPlanStruct.setRunWorkout(runWorkout);
                        runWorkoutPlanStruct.setWorkoutName(planWorkout.popName());
                        arrayList.add(runWorkoutPlanStruct);
                    }
                }
            }
            e2 += c;
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public RunPlanInfo c(List<RunWorkoutPlanStruct> list, RunPlanParameter runPlanParameter) {
        RunPlanInfo runPlanInfo = new RunPlanInfo();
        runPlanInfo.setRun_plan_total_sign(this.g.acquireId());
        ArrayList arrayList = new ArrayList();
        runPlanInfo.setRun_plan_start_date(e(this.g.acquireStartDate(), b));
        for (int i = 0; i < list.size(); i++) {
            if (e(list.get(i).getDate(), runPlanParameter)) {
                RunPlanStruct runPlanStruct = new RunPlanStruct();
                if (list.get(i).getRunWorkout() != null) {
                    RunWorkout runWorkout = list.get(i).getRunWorkout();
                    LogUtil.c("Track_HealthAdapter_HWExerciseAdviceManager", "workout name =" + list.get(i).getWorkoutName() + ",workout id=" + runWorkout.acquireId());
                    runPlanStruct.setRun_plan_name(list.get(i).getWorkoutName());
                    runPlanStruct.setRun_plan_workout_id(CommonUtil.m(BaseApplication.getContext(), runWorkout.acquireId()));
                    runPlanStruct.setRun_plan_train_effect(runWorkout.getTrainingEffect());
                    runPlanStruct.setRun_plan_repeats(runWorkout.getRepeats());
                    runPlanStruct.setRun_plan_distance(runWorkout.acquireDistance() * 1000);
                    runPlanStruct.setRun_plan_date(e(runWorkout.acquireWorkoutDate(), b));
                    c(runWorkout, runPlanStruct);
                } else {
                    runPlanStruct.setRun_plan_name(list.get(i).getWorkoutName());
                    runPlanStruct.setRun_plan_date(list.get(i).getDate());
                }
                arrayList.add(runPlanStruct);
            }
        }
        this.k = c(arrayList);
        LogUtil.a("Track_HealthAdapter_HWExerciseAdviceManager", "======advice======plan sha==" + this.k);
        runPlanInfo.setRun_plan_sign(this.k);
        runPlanInfo.setRunPlanStructList(arrayList);
        return runPlanInfo;
    }

    private void c(RunWorkout runWorkout, RunPlanStruct runPlanStruct) {
        ArrayList arrayList = new ArrayList();
        if (runWorkout.getWarmup() != null) {
            ExerciseProfile warmup = runWorkout.getWarmup();
            TrainingStruct trainingStruct = new TrainingStruct();
            trainingStruct.setTraining_duration(warmup.getDuration());
            trainingStruct.setTraining_hr_limit_high(warmup.getHeartRate().getMax());
            trainingStruct.setTraining_hr_limit_low(warmup.getHeartRate().getMin());
            trainingStruct.setTraining_intensity_limit_high(warmup.getIntensity().getMax());
            trainingStruct.setTraining_intensity_limit_low(warmup.getIntensity().getMin());
            trainingStruct.setTraining_speed_limit_high(warmup.getRunningSpeed().getMax());
            trainingStruct.setTraining_speed_limit_low(warmup.getRunningSpeed().getMin());
            trainingStruct.setTraining_type(1);
            arrayList.add(trainingStruct);
        }
        if (runWorkout.getWork() != null) {
            ExerciseProfile work = runWorkout.getWork();
            TrainingStruct trainingStruct2 = new TrainingStruct();
            trainingStruct2.setTraining_duration(work.getDuration());
            trainingStruct2.setTraining_hr_limit_high(work.getHeartRate().getMax());
            trainingStruct2.setTraining_hr_limit_low(work.getHeartRate().getMin());
            trainingStruct2.setTraining_intensity_limit_high(work.getIntensity().getMax());
            trainingStruct2.setTraining_intensity_limit_low(work.getIntensity().getMin());
            trainingStruct2.setTraining_speed_limit_high(work.getRunningSpeed().getMax());
            trainingStruct2.setTraining_speed_limit_low(work.getRunningSpeed().getMin());
            trainingStruct2.setTraining_type(2);
            arrayList.add(trainingStruct2);
        }
        d(runWorkout, arrayList);
        a(runWorkout, arrayList);
        runPlanStruct.setTrainingStructList(arrayList);
    }

    private void d(RunWorkout runWorkout, List<TrainingStruct> list) {
        if (runWorkout.getRest() != null) {
            ExerciseProfile rest = runWorkout.getRest();
            TrainingStruct trainingStruct = new TrainingStruct();
            trainingStruct.setTraining_duration(rest.getDuration());
            trainingStruct.setTraining_hr_limit_high(rest.getHeartRate().getMax());
            trainingStruct.setTraining_hr_limit_low(rest.getHeartRate().getMin());
            trainingStruct.setTraining_intensity_limit_high(rest.getIntensity().getMax());
            trainingStruct.setTraining_intensity_limit_low(rest.getIntensity().getMin());
            trainingStruct.setTraining_speed_limit_high(rest.getRunningSpeed().getMax());
            trainingStruct.setTraining_speed_limit_low(rest.getRunningSpeed().getMin());
            trainingStruct.setTraining_type(3);
            list.add(trainingStruct);
        }
    }

    private void a(RunWorkout runWorkout, List<TrainingStruct> list) {
        if (runWorkout.getCooldown() != null) {
            ExerciseProfile cooldown = runWorkout.getCooldown();
            TrainingStruct trainingStruct = new TrainingStruct();
            trainingStruct.setTraining_duration(cooldown.getDuration());
            trainingStruct.setTraining_hr_limit_high(cooldown.getHeartRate().getMax());
            trainingStruct.setTraining_hr_limit_low(cooldown.getHeartRate().getMin());
            trainingStruct.setTraining_intensity_limit_high(cooldown.getIntensity().getMax());
            trainingStruct.setTraining_intensity_limit_low(cooldown.getIntensity().getMin());
            trainingStruct.setTraining_speed_limit_high(cooldown.getRunningSpeed().getMax());
            trainingStruct.setTraining_speed_limit_low(cooldown.getRunningSpeed().getMin());
            trainingStruct.setTraining_type(4);
            list.add(trainingStruct);
        }
    }

    private long e(String str, String str2) {
        if (str == null) {
            return 0L;
        }
        try {
            if ("".equals(str)) {
                return 0L;
            }
            return new SimpleDateFormat(str2).parse(str).getTime();
        } catch (ParseException e2) {
            LogUtil.b("Track_HealthAdapter_HWExerciseAdviceManager", "SimpleDateFormat parse error: e= " + e2.getMessage());
            return 0L;
        }
    }

    private boolean e(long j, RunPlanParameter runPlanParameter) {
        long e2 = e(new SimpleDateFormat(b).format(new Date(System.currentTimeMillis())), b);
        LogUtil.c("Track_HealthAdapter_HWExerciseAdviceManager", "check time today=" + e2 + ",checktime=" + j);
        if (runPlanParameter == null) {
            return false;
        }
        int run_plan_sync_size_pre = runPlanParameter.getRun_plan_sync_size_pre();
        int run_plan_sync_size_sub = runPlanParameter.getRun_plan_sync_size_sub();
        long j2 = c;
        return e2 - (((long) run_plan_sync_size_pre) * j2) <= j && e2 + (j2 * ((long) run_plan_sync_size_sub)) > j;
    }

    private String c(List<RunPlanStruct> list) {
        StringBuffer stringBuffer = new StringBuffer();
        Iterator<RunPlanStruct> it = list.iterator();
        while (it.hasNext()) {
            stringBuffer.append(it.next().toString());
        }
        return knq.e(stringBuffer.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                if (jSONObject.has("isSupportExerciseAdvice")) {
                    this.i = jSONObject.getBoolean("isSupportExerciseAdvice");
                }
                if (jSONObject.has("isSupportWorkoutExerciseDisplayLink")) {
                    this.l = jSONObject.getBoolean("isSupportWorkoutExerciseDisplayLink");
                }
            } catch (JSONException unused) {
                LogUtil.b("Track_HealthAdapter_HWExerciseAdviceManager", "capability ");
            }
        } else {
            LogUtil.a("Track_HealthAdapter_HWExerciseAdviceManager", "DeviceCapability is null");
        }
        LogUtil.a("Track_HealthAdapter_HWExerciseAdviceManager", "get Device Support runplan Capacity, capacity=" + this.i + ", mIsSupportDisplayLink=" + this.l);
    }

    private JSONObject c(String str, Object obj) {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject();
            try {
                jSONObject.put(str, new Gson().toJson(obj));
            } catch (JSONException e2) {
                e = e2;
                LogUtil.b("Track_HealthAdapter_HWExerciseAdviceManager", "toJsonObject error=" + e.getMessage());
                return jSONObject;
            }
        } catch (JSONException e3) {
            e = e3;
            jSONObject = null;
        }
        return jSONObject;
    }
}
