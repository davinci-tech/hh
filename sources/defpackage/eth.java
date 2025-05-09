package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.basefitnessadvice.callback.DataCallback;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.LangFile;
import com.huawei.basefitnessadvice.model.Media;
import com.huawei.basefitnessadvice.model.NavigationFilter;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.basefitnessadvice.model.PlanRecord;
import com.huawei.basefitnessadvice.model.PlanWorkout;
import com.huawei.basefitnessadvice.model.RunWorkout;
import com.huawei.basefitnessadvice.model.Topic;
import com.huawei.basefitnessadvice.model.TrainStatistics;
import com.huawei.basefitnessadvice.model.WorkoutListBean;
import com.huawei.basefitnessadvice.model.intplan.IntDayPlan;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.basefitnessadvice.model.intplan.IntWeekPlan;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.courseplanservice.api.OnStateListener;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.plan.model.PlanStat;
import com.huawei.health.plan.model.PlanStatistics;
import com.huawei.health.plan.model.data.DataApi;
import com.huawei.health.plan.model.data.DataSyncService;
import com.huawei.health.plan.model.model.BestRecordFitStat;
import com.huawei.health.plan.model.model.CreatePlanBean;
import com.huawei.health.plan.model.model.DataSync;
import com.huawei.health.plan.model.model.FinishPlanBean;
import com.huawei.health.plan.model.model.fitness.FitnessHistoryModel;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.sport.utils.ResultUtil;
import com.huawei.health.suggestion.PluginSuggestion;
import com.huawei.health.suggestion.config.MoveService;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.health.userprofilemgr.model.BaseResponseCallback;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.pluginfitnessadvice.AtomicAction;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.HealthWorkout;
import com.huawei.pluginfitnessadvice.LongVideoInfo;
import com.huawei.pluginfitnessadvice.TrainAction;
import com.huawei.pluginfitnessadvice.Workout;
import com.huawei.pluginfitnessadvice.WorkoutPackageInfo;
import com.huawei.pluginfitnessadvice.audio.SleepAudioPackage;
import com.huawei.pluginfitnessadvice.audio.SleepAudioSeries;
import com.huawei.pluginfitnessadvice.plandata.CreateRunPlanParams;
import com.huawei.pluginfitnessadvice.plandata.UserInfoBean;
import com.huawei.up.model.UserInfomation;
import com.huawei.utils.FoundationCommonUtil;
import defpackage.ffl;
import defpackage.kwy;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.SportIntensityUtil;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.utils.StringUtils;
import java.io.File;
import java.lang.ref.SoftReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class eth implements DataApi {
    private HashMap<String, SoftReference<Object>> e = new HashMap<>(10);
    private Handler c = new Handler(Looper.getMainLooper());

    /* renamed from: a, reason: collision with root package name */
    private int f12249a = -1;

    @Override // com.huawei.health.plan.model.data.DataApi
    public List<PlanRecord> getJoinedPlans(final int i, final int i2, final UiCallback<List<PlanRecord>> uiCallback) {
        if (uiCallback == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "getJoinedPlans callback == null");
            return null;
        }
        if (i < 0 || i2 < 0) {
            uiCallback.onFailure(this.c, -1, "pageStart or pageSize illegal");
            return null;
        }
        final String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "getJoinedPlans() getAccountInfo == null");
            uiCallback.onFailure(this.c, 20001, ResultUtil.d(20001));
            return null;
        }
        final List<PlanRecord> a2 = ett.i().t().a(i, i2, accountInfo);
        if (eve.e() && etx.b().c(i, i2)) {
            eqa.a().getFinishedPlans(i, i2, new DataCallback() { // from class: eth.4
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i3, String str) {
                    ReleaseLogUtil.c("Suggestion_DataImpl", "getJoinedPlans errorCode=", Integer.valueOf(i3));
                    uiCallback.onSuccess(eth.this.c, a2);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    if (jSONObject == null) {
                        uiCallback.onFailure(eth.this.c, -1, "data == null");
                        return;
                    }
                    etx.b().a(i, i2);
                    etn.d(jSONObject.optInt("total"), jSONObject.optInt("totalCalorie"), jSONObject.optInt("totalTrainingDays"));
                    ett.i().t().d(accountInfo, ffm.a(jSONObject.optJSONArray("planRecords")));
                    uiCallback.onSuccess(eth.this.c, ett.i().t().a(i, i2, accountInfo));
                }
            });
        } else {
            uiCallback.onSuccess(this.c, a2);
        }
        return a2;
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void getOldRunPlanLocalRecords(int i, int i2, UiCallback<List<PlanRecord>> uiCallback) {
        if (uiCallback == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "getJoinedPlans callback == null");
            return;
        }
        if (i < 0 || i2 < 0) {
            uiCallback.onFailure(this.c, -1, "pageStart or pageSize illegal");
            return;
        }
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "getJoinedPlans() getAccountInfo == null");
            uiCallback.onFailure(this.c, 20001, ResultUtil.d(20001));
        } else {
            List<PlanRecord> c = euj.c(i, i2, accountInfo);
            LogUtil.a("Suggestion_DataImpl", "getOldRunPlanLocalRecords size:", Integer.valueOf(c.size()));
            uiCallback.onSuccess(this.c, c);
        }
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public Plan getJoinedPlan(String str) {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "getJoinedPlan(String planId) getAccountInfo == null");
            return null;
        }
        return ett.i().t().a(accountInfo, str);
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public Plan getCurrentPlan() {
        return getCurrentRunPlan();
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void checkAllowCreateOldPlan(final UiCallback<Boolean> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("Suggestion_DataImpl", "checkAllowCreateOldPlan callback == null");
            return;
        }
        final String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            LogUtil.b("Suggestion_DataImpl", "checkAllowCreateOldPlan() getAccountInfo == null");
            uiCallback.onFailure(this.c, 20001, ResultUtil.d(20001));
        } else {
            eqa.a().checkAllowCreateOldPlan(new DataCallback() { // from class: eth.13
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i, String str) {
                    ReleaseLogUtil.c("Suggestion_DataImpl", "checkAllowCreateOldPlan() errorCode=", Integer.valueOf(i));
                    uiCallback.onFailure(eth.this.c, i, str);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    eth.this.c(jSONObject, accountInfo, (UiCallback<Boolean>) uiCallback);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(JSONObject jSONObject, String str, UiCallback<Boolean> uiCallback) {
        if (jSONObject == null || TextUtils.isEmpty(str) || uiCallback == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "checkAllowCreateOldPlan() data == null || accountInfo == null || callback == null");
        } else {
            uiCallback.onSuccess(this.c, ffm.a(jSONObject));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(JSONObject jSONObject, String str, boolean z, UiCallback<Plan> uiCallback) {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (jSONObject == null || TextUtils.isEmpty(accountInfo) || uiCallback == null) {
            LogUtil.b("Suggestion_DataImpl", "doGetCurrentPlan() data == null || accountInfo == null || callback == null");
            return;
        }
        LogUtil.c("Suggestion_DataImpl", "doGetCurrentPlan success with data:", jSONObject);
        Plan a2 = ffm.a(jSONObject, str);
        LogUtil.a("Suggestion_DataImpl", "executor doGetCurrentPlan callback");
        if (z) {
            uiCallback.onSuccess(this.c, a2);
        } else {
            uiCallback.onSuccess(a2);
        }
        syncLocalAndCloud(accountInfo, a2);
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void syncLocalAndCloud(String str, Plan plan) {
        Plan createPlanFromDb = createPlanFromDb(str);
        if (plan != null) {
            this.e.put(a("plan", str), new SoftReference<>(plan));
            b(str, plan, createPlanFromDb);
        } else {
            LogUtil.a("Suggestion_DataImpl", "doGetCurrentPlan from cloud is null");
            if (createPlanFromDb != null) {
                LogUtil.h("Suggestion_DataImpl", "cloud plan is null.need to finish local plan.");
                a(createPlanFromDb.acquireId(), 0L);
                this.e.remove(a("plan", str));
            }
        }
        LogUtil.a("Suggestion_DataImpl", "deal local and cloud plan finish");
    }

    private void b(String str, Plan plan, Plan plan2) {
        if (plan2 == null || ((plan.acquireId() != null && !plan.acquireId().equals(plan2.acquireId())) || !ett.i().n().a(plan.acquireId(), arx.a(), plan.acquireVersion()))) {
            if (plan2 != null && !plan.acquireId().equals(plan2.acquireId())) {
                LogUtil.a("Suggestion_DataImpl", "cloud plan is different from local plan.");
                a(plan2.acquireId(), 0L);
                a(str, plan);
            } else if (plan2 == null) {
                LogUtil.a("Suggestion_DataImpl", "local plan not exist.");
                a(str, plan);
            }
            ett.i().n().e(plan.acquireId(), arx.a(), plan.acquireVersion(), plan.toString());
        }
        if (plan2 != null) {
            if (!plan.acquireName().equals(plan2.acquireName())) {
                LogUtil.a("Suggestion_DataImpl", "plan name is changed.");
                ett.i().t().b(str, plan2.acquireId(), plan.acquireName());
            }
            LogUtil.a("Suggestion_DataImpl", "update cloud plan to local.");
            if (plan.getPlanCategory() == 1) {
                ett.i().n().d(arx.a(), plan, plan.acquireVersion());
            }
        }
        d(plan.getRemindTime() >= 0, plan.getRemindTime());
    }

    private Plan c(String str) {
        SoftReference<Object> softReference = this.e.get(a("plan", str));
        if (softReference == null || !(softReference.get() instanceof Plan)) {
            return null;
        }
        return (Plan) softReference.get();
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public Plan getCurrentRunPlan() {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "getCurrentPlan() getAccountInfo == null");
            return null;
        }
        Plan c = c(accountInfo);
        return c == null ? createPlanFromDb(accountInfo) : c;
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public Plan createPlanFromDb(String str) {
        LogUtil.a("Suggestion_DataImpl", "enter createPlanFromDb");
        if (TextUtils.isEmpty(str)) {
            LogUtil.b("Suggestion_DataImpl", "createPlan() getAccountInfo == null");
            return null;
        }
        Plan e2 = ett.i().t().e(str);
        if (e2 != null && !TextUtils.isEmpty(e2.acquireId())) {
            Plan e3 = ett.i().n().e(e2.acquireId(), arx.a(), e2.acquireVersion());
            if (e3 != null && !TextUtils.isEmpty(e2.acquireName())) {
                e3.putName(e2.acquireName());
            }
            this.e.put(a("plan", str), new SoftReference<>(e3));
            LogUtil.a("Suggestion_DataImpl", "exit createPlanFromDb");
            return e3;
        }
        LogUtil.a("Suggestion_DataImpl", "exit createPlanFromDb");
        return null;
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void updatePlanName(String str, String str2) {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "updatePlanName(String planId, String name) getAccountInfo == null");
            return;
        }
        ett.i().t().b(accountInfo, str, str2);
        Plan currentPlan = getCurrentPlan();
        if (currentPlan != null) {
            currentPlan.putName(str2);
            this.e.put(a("plan", accountInfo), new SoftReference<>(currentPlan));
        }
        ett.i().o().b(accountInfo, str, str2);
        ary.a().e("PLAN_UPDATE");
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void updateDayRecord(String str, final IntPlan intPlan, final mob mobVar) {
        if (!eve.e(mobVar, intPlan)) {
            ReleaseLogUtil.d("Suggestion_DataImpl", "record has exist");
            return;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mobVar.i());
        final int d = gib.d(calendar.get(7));
        final int e2 = gib.e(intPlan.getPlanTimeInfo().getBeginDate() * 1000, mobVar.i()) + 1;
        ReleaseLogUtil.e("Suggestion_DataImpl", "updateDayRecord:", Integer.valueOf(e2), " day:", Integer.valueOf(d));
        final int i = !TextUtils.isEmpty(str) ? 1 : 0;
        ReleaseLogUtil.e("Suggestion_DataImpl", "isInPlan:", Integer.valueOf(i), " completionRate:", Float.valueOf(mobVar.d()));
        if (c(str, intPlan, e2, d, mobVar)) {
            return;
        }
        mobVar.e(i);
        eqa.a().updateDayRecord(intPlan.getPlanType().getType(), intPlan.getPlanId(), e2, d, mobVar, new DataCallback() { // from class: eth.22
            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onFailure(int i2, String str2) {
                ReleaseLogUtil.c("Suggestion_DataImpl", "updateDayRecord fail errorCode:", Integer.valueOf(i2), " info:", str2, " ", mobVar.n());
            }

            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onSuccess(JSONObject jSONObject) {
                ReleaseLogUtil.e("Suggestion_DataImpl", "updateDayRecord success:", mobVar.n());
                etx.b().e();
                ary.a().e("PLAN_UPDATE");
                if (ase.a(intPlan.getDayInfo(e2, d)) == 0) {
                    ReleaseLogUtil.e("Suggestion_DataImpl", "not training day");
                    ObserverManagerUtil.c("UPDATE_AI_PLAN_COURSE_RECORD", Long.valueOf(mobVar.a()));
                }
                if (i == 0) {
                    gge.e(2);
                } else {
                    gge.e(1);
                }
            }
        });
    }

    private boolean c(String str, IntPlan intPlan, int i, int i2, mob mobVar) {
        if (intPlan.getPlanType().equals(IntPlan.PlanType.AI_FITNESS_PLAN) && a(i, i2, intPlan)) {
            ReleaseLogUtil.e("Suggestion_DataImpl", "ai fitness today has achieve goal.");
            return true;
        }
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.e("Suggestion_DataImpl", "plan id is empty.");
            return false;
        }
        if (str.equals(intPlan.getPlanId())) {
            return CommonUtil.c((double) mobVar.d());
        }
        ReleaseLogUtil.c("Suggestion_DataImpl", "updateDayRecord fail planId", str);
        return true;
    }

    private boolean a(int i, int i2, IntPlan intPlan) {
        IntWeekPlan weekInfoByOrder = intPlan.getWeekInfoByOrder(i);
        if (weekInfoByOrder == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "weekInfo not exist.", Integer.valueOf(i));
            return false;
        }
        IntDayPlan dayByOrder = weekInfoByOrder.getDayByOrder(i2);
        if (dayByOrder != null) {
            return dayByOrder.getPunchFlag() == 1;
        }
        ReleaseLogUtil.c("Suggestion_DataImpl", "dayInfo not exist.", Integer.valueOf(i2));
        return false;
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void updateDayRecord(final IntPlan intPlan, final WorkoutRecord workoutRecord, final DataCallback dataCallback) {
        int i;
        int i2;
        if (intPlan == null || workoutRecord == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "updateDayRecord plan == null or record == null");
            dataCallback.onSuccess(new JSONObject());
            return;
        }
        if (e(intPlan, workoutRecord, dataCallback)) {
            return;
        }
        if (TextUtils.isEmpty(workoutRecord.acquirePlanId())) {
            i2 = gib.e(intPlan.getPlanTimeInfo().getBeginDate() * 1000, workoutRecord.acquireExerciseTime()) + 1;
            i = e(workoutRecord);
        } else {
            int[] a2 = ase.a(workoutRecord, intPlan);
            int i3 = a2[0];
            i = a2[1];
            i2 = i3;
        }
        boolean b = eve.b(workoutRecord.acquireWorkoutId(), intPlan, i2, i);
        if (intPlan.getPlanType().equals(IntPlan.PlanType.AI_RUN_PLAN) && !b) {
            ReleaseLogUtil.e("Suggestion_DataImpl", "course is not in plan.", workoutRecord.acquireWorkoutId());
            dataCallback.onSuccess(new JSONObject());
            return;
        }
        ReleaseLogUtil.e("Suggestion_DataImpl", "updateClockTimes courseId:", workoutRecord.acquireWorkoutId(), " weekNumber:", Integer.valueOf(i2), " planDayIndex:", Integer.valueOf(i), " finishRate:", Float.valueOf(workoutRecord.acquireFinishRate()));
        final int i4 = i2;
        final int i5 = i;
        eqa.a().updateDayRecord(intPlan.getPlanType().getType(), intPlan.getPlanId(), i2, i, a(workoutRecord, b), new DataCallback() { // from class: eth.33
            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onFailure(int i6, String str) {
                ReleaseLogUtil.c("Suggestion_DataImpl", "updateDayRecord fail:", workoutRecord.acquireWorkoutId(), " errorInfo:", str);
                dataCallback.onFailure(i6, str);
            }

            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onSuccess(JSONObject jSONObject) {
                ReleaseLogUtil.e("Suggestion_DataImpl", "updateClockTimes success.", workoutRecord.acquireWorkoutId());
                eth.this.d(intPlan, workoutRecord.acquireWorkoutId(), i4, i5, workoutRecord.acquireFinishRate());
                dataCallback.onSuccess(jSONObject);
                eth.this.c((PlanApi) Services.a("CoursePlanService", PlanApi.class));
                gge.e(1);
            }
        });
        e(intPlan, workoutRecord);
    }

    private void e(IntPlan intPlan, final WorkoutRecord workoutRecord) {
        if (intPlan.getPlanType().equals(IntPlan.PlanType.AI_FITNESS_PLAN)) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: eti
                @Override // java.lang.Runnable
                public final void run() {
                    eth.b(WorkoutRecord.this);
                }
            });
        }
    }

    static /* synthetic */ void b(WorkoutRecord workoutRecord) {
        HashMap hashMap = new HashMap(10);
        hashMap.put("isAICourse", Boolean.valueOf(workoutRecord.isAiWorkout()));
        hashMap.put("completeMode", workoutRecord.getRecordModeType() == 1 ? "AIMode" : "commonMode");
        gge.e("1120050", hashMap);
    }

    private int e(WorkoutRecord workoutRecord) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(workoutRecord.acquireExerciseTime());
        return gib.d(calendar.get(7));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(PlanApi planApi) {
        if (planApi != null) {
            planApi.setNeedUpdateCurrentPlan();
            ary.a().e("PLAN_UPDATE");
        }
    }

    private boolean e(IntPlan intPlan, WorkoutRecord workoutRecord, DataCallback dataCallback) {
        if (!intPlan.getPlanType().equals(IntPlan.PlanType.AI_FITNESS_PLAN) && !intPlan.getPlanType().equals(IntPlan.PlanType.AI_RUN_PLAN) && !intPlan.getPlanType().equals(IntPlan.PlanType.FIT_PLAN)) {
            LogUtil.b("Suggestion_DataImpl", "no need to update record.", workoutRecord.acquireWorkoutId(), " planType:", Integer.valueOf(intPlan.getPlanType().getType()));
            dataCallback.onSuccess(new JSONObject());
            return true;
        }
        if (intPlan.getPlanId().equals(workoutRecord.acquirePlanId())) {
            return false;
        }
        LogUtil.b("Suggestion_DataImpl", "plan id is different. current plan id:", intPlan.getPlanId(), " record plan id:", workoutRecord.acquirePlanId());
        dataCallback.onSuccess(new JSONObject());
        return true;
    }

    private mob a(WorkoutRecord workoutRecord, boolean z) {
        mob mobVar = new mob();
        mobVar.b(workoutRecord.acquireWorkoutId());
        if (workoutRecord.isRunWorkout()) {
            mobVar.c(workoutRecord.acquireTrajectoryId());
        } else {
            mobVar.c(String.valueOf(workoutRecord.acquireExerciseTime()));
        }
        mobVar.c(workoutRecord.getCourseSportType());
        mobVar.c(workoutRecord.getDuration() / 1000);
        mobVar.b(workoutRecord.acquireExerciseTime() - workoutRecord.getDuration());
        mobVar.e(workoutRecord.acquireExerciseTime());
        mobVar.e(workoutRecord.acquireActualCalorie());
        mobVar.a(moe.j(workoutRecord.acquireActualDistance()));
        mobVar.d(workoutRecord.getTrainPoint());
        mobVar.b(workoutRecord.acquireFinishRate());
        mobVar.e(grz.a());
        if (z) {
            mobVar.e(1);
        } else {
            mobVar.e(0);
        }
        mobVar.e("");
        return mobVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(IntPlan intPlan, String str, int i, int i2, float f) {
        if (!intPlan.getPlanType().equals(IntPlan.PlanType.AI_FITNESS_PLAN) && !intPlan.getPlanType().equals(IntPlan.PlanType.AI_RUN_PLAN) && !intPlan.getPlanType().equals(IntPlan.PlanType.FIT_PLAN)) {
            LogUtil.b("Suggestion_DataImpl", "no need to update clock.", " planType:", Integer.valueOf(intPlan.getPlanType().getType()));
            return;
        }
        if (f < 60.0f && (intPlan.getPlanType().equals(IntPlan.PlanType.AI_RUN_PLAN) || intPlan.getPlanType().equals(IntPlan.PlanType.AI_FITNESS_PLAN) || intPlan.getPlanType().equals(IntPlan.PlanType.FIT_PLAN))) {
            LogUtil.b("Suggestion_DataImpl", "finish rate is to low.", Float.valueOf(f));
            return;
        }
        LogUtil.a("Suggestion_DataImpl", "updateClockTimeInLocal:", intPlan.getPlanType(), " courseId:", str, " weekNumber:", Integer.valueOf(i), " planDayIndex:", Integer.valueOf(i2));
        if (intPlan.getPlanType().equals(IntPlan.PlanType.AI_RUN_PLAN)) {
            Plan compatiblePlan = intPlan.getCompatiblePlan();
            compatiblePlan.setLatestClockInTime(System.currentTimeMillis());
            ase.e(compatiblePlan, i, i2, str);
            ett.i().n().d(arx.a(), compatiblePlan, compatiblePlan.acquireVersion());
            String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
            if (accountInfo != null) {
                this.e.put(a("plan", accountInfo), new SoftReference<>(compatiblePlan));
            } else {
                ReleaseLogUtil.c("Suggestion_DataImpl", "updateClockTimeInLocal(String planId, String name) getAccountInfo == null");
            }
        }
        ety.c().a(str, i, i2);
        PluginSuggestion.getInstance().startSendIntelligentPlan();
        ary.a().e("PLAN_UPDATE");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, Plan plan) {
        ett.i().t().c(str, plan);
        etx.b().a();
        etx.b().c();
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void postPlanName(String str, String str2, final UiCallback<String> uiCallback) {
        if (uiCallback == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "postPlanName() callback == null");
        } else {
            eqa.a().updatePlanName(str, str2, new DataCallback() { // from class: eth.44
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i, String str3) {
                    ReleaseLogUtil.c("Suggestion_DataImpl", "postPlanName() errorCode=", Integer.valueOf(i));
                    uiCallback.onFailure(eth.this.c, i, str3);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    uiCallback.onSuccess(eth.this.c, null);
                }
            });
        }
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void deleteWorkoutRecords(int i, int i2, IBaseResponseCallback iBaseResponseCallback) {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "deleteWorkoutRecords accountInfo == null");
            return;
        }
        LogUtil.a("Suggestion_DataImpl", "deleteWorkoutRecords ids = ", Integer.valueOf(i));
        ett.i().o().e(accountInfo, "", 9, String.valueOf(i));
        ary.a().e("WORKOUT_DELETE");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Plan plan) {
        if (plan == null) {
            LogUtil.b("Suggestion_DataImpl", "createJoinRunPlan(), plan == null");
            return;
        }
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            LogUtil.b("Suggestion_DataImpl", "createJoinRunPlan(Plan plan) getAccountInfo == null");
            return;
        }
        a(accountInfo, plan);
        ett.i().n().e(plan.acquireId(), arx.a(), plan.acquireVersion(), plan.toString());
        this.e.put(a("plan", accountInfo), new SoftReference<>(plan));
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void createFitPlan(long j, int i, int i2, int i3, TreeSet<Integer> treeSet, UiCallback<Plan> uiCallback) {
        if (uiCallback == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "createFitPlan() callback == null");
            return;
        }
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        UserProfileMgrApi e2 = e("createFitPlan : userProfileMgrApi is null.");
        if (e2 == null) {
            return;
        }
        UserInfomation userInfo = e2.getUserInfo();
        if (TextUtils.isEmpty(accountInfo) || userInfo == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "createFitPlan() getAccountInfo == null");
            uiCallback.onFailure(this.c, 20001, ResultUtil.d(20001));
        } else if (!eve.e()) {
            uiCallback.onFailure(this.c, -4, ResultUtil.d(-4));
        } else {
            a(accountInfo, new CreatePlanBean(userInfo.getWeightOrDefaultValue(), j, i, i2, i3, ffm.a(treeSet)), uiCallback);
        }
    }

    private void a(final String str, CreatePlanBean createPlanBean, final UiCallback<Plan> uiCallback) {
        eqa.a().createPlan(createPlanBean, new DataCallback() { // from class: eth.59
            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onFailure(int i, String str2) {
                ReleaseLogUtil.c("Suggestion_DataImpl", "createFitPlan() errorCode=", Integer.valueOf(i));
                uiCallback.onFailure(eth.this.c, i, str2);
            }

            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onSuccess(JSONObject jSONObject) {
                if (jSONObject == null) {
                    uiCallback.onFailure(eth.this.c, -1, "data == null");
                    return;
                }
                Plan a2 = ffm.a(jSONObject, "planInfo");
                if (a2 != null) {
                    eth.this.a(str, a2);
                    ett.i().n().e(a2.acquireId(), arx.a(), a2.acquireVersion(), a2.toString());
                    eth.this.e.put(eth.this.a("plan", str), new SoftReference(a2));
                    uiCallback.onSuccess(eth.this.c, a2);
                    return;
                }
                uiCallback.onSuccess(eth.this.c, null);
            }
        });
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public List<RunWorkout> getRunWorkouts() {
        Plan currentPlan = getCurrentPlan();
        if (currentPlan == null) {
            return null;
        }
        List<PlanWorkout> acquireWorkouts = currentPlan.acquireWorkouts();
        ArrayList arrayList = new ArrayList(acquireWorkouts.size());
        for (PlanWorkout planWorkout : acquireWorkouts) {
            if (planWorkout != null) {
                RunWorkout e2 = ffm.e(planWorkout);
                e2.putWorkoutDate(planWorkout.popDayInfo().acquireDate());
                arrayList.add(e2);
            }
        }
        return arrayList;
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public RunWorkout getRunWorkout(String str) {
        Plan currentPlan = getCurrentPlan();
        if (currentPlan == null) {
            return null;
        }
        for (PlanWorkout planWorkout : currentPlan.acquireWorkouts()) {
            if (planWorkout != null && str != null && str.equals(planWorkout.popWorkoutId())) {
                RunWorkout e2 = ffm.e(planWorkout);
                e2.putWorkoutDate(planWorkout.popDayInfo().acquireDate());
                return e2;
            }
        }
        return null;
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public Map<String, Integer> getWorkoutOrders(String str) {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "getWorkoutOrders() getAccountInfo == null");
            return new HashMap(0);
        }
        return euj.b(accountInfo, str);
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public boolean updatePlanProgress(WorkoutRecord workoutRecord) {
        LogUtil.a("Suggestion_DataImpl", "updatePlanProgress begin...");
        LoginInit loginInit = LoginInit.getInstance(BaseApplication.getContext());
        String accountInfo = loginInit.getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo) || workoutRecord == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "updatePlanProgress() getAccountInfo == null");
            return false;
        }
        workoutRecord.saveFinishRate(e(workoutRecord.acquireFinishRate()));
        ett.i().l().e(accountInfo, workoutRecord.acquireWorkoutId(), workoutRecord.acquireVersion());
        IntPlan c = c(workoutRecord);
        boolean e2 = eup.e(accountInfo, workoutRecord);
        if (e2) {
            postExerciseBehavior(workoutRecord, 1);
        }
        c(workoutRecord, accountInfo);
        if (eve.b) {
            PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
            boolean b = evf.b(workoutRecord);
            ReleaseLogUtil.e("Suggestion_DataImpl", "is old plan record:", Boolean.valueOf(b), workoutRecord.acquirePlanId(), " finishRate:", Float.valueOf(workoutRecord.acquireFinishRate()));
            if (!b && !TextUtils.isEmpty(workoutRecord.acquirePlanId()) && !CommonUtil.c(workoutRecord.acquireFinishRate()) && e2) {
                ett.i().o().b(accountInfo, workoutRecord.acquirePlanId(), workoutRecord.acquireExerciseTime());
                if (c != null && workoutRecord.acquirePlanId().equals(c.getPlanId())) {
                    int[] a2 = ase.a(workoutRecord, c);
                    d(c, workoutRecord.acquireWorkoutId(), a2[0], a2[1], workoutRecord.acquireFinishRate());
                }
            }
            a(workoutRecord, loginInit, e2, planApi);
            return e2;
        }
        return d(e2, workoutRecord, loginInit);
    }

    private IntPlan c(WorkoutRecord workoutRecord) {
        epo a2 = ety.c().a();
        if (!TextUtils.isEmpty(workoutRecord.acquireWorkoutId()) && TextUtils.isEmpty(workoutRecord.acquirePlanId()) && a2 != null) {
            long beginDate = a2.getPlanTimeInfo().getBeginDate() * 1000;
            int e2 = gib.e(beginDate, workoutRecord.acquireExerciseTime()) + 1;
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(workoutRecord.acquireExerciseTime());
            int d = gib.d(calendar.get(7));
            boolean b = eve.b(workoutRecord.acquireWorkoutId(), a2, e2, d);
            boolean c = eve.c(workoutRecord.acquireWorkoutId(), a2, e2, d);
            ReleaseLogUtil.e("Suggestion_DataImpl", "startTime:", Long.valueOf(beginDate), " weekOrder:", Integer.valueOf(e2), " dayOrder:", Integer.valueOf(d), " isInPlan:", Boolean.valueOf(b), "isUserAdd:", Boolean.valueOf(c));
            if (b || c) {
                workoutRecord.savePlanId(a2.getPlanId());
                workoutRecord.setPlanTrainDate(ggl.b(workoutRecord.acquireExerciseTime()));
                workoutRecord.saveRecordType(1);
            }
        }
        return a2;
    }

    private void c(WorkoutRecord workoutRecord, String str) {
        ett.i().o().d(str, workoutRecord.acquirePlanId(), workoutRecord.acquireExerciseTime());
        b(workoutRecord, str);
        if (workoutRecord.isSingle()) {
            ett.i().m().a(str, workoutRecord.acquireWorkoutId(), workoutRecord);
            if (ett.i().m().e(str, workoutRecord.acquireWorkoutId())) {
                ett.i().o().b(str, workoutRecord.acquireWorkoutId());
            }
        }
    }

    private void a(final WorkoutRecord workoutRecord, final LoginInit loginInit, final boolean z, PlanApi planApi) {
        planApi.b(new UiCallback<IntPlan>() { // from class: eth.69
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(IntPlan intPlan) {
                if (intPlan == null) {
                    LogUtil.b("Suggestion_DataImpl", "updatePlanProgress() currentPlan == null");
                    eth.this.d(z, workoutRecord, loginInit);
                    return;
                }
                if (evf.a(workoutRecord.acquirePlanId()) == 0 && intPlan.getPlanType() != IntPlan.PlanType.AI_RUN_PLAN) {
                    eth.this.d(z, workoutRecord, loginInit);
                    return;
                }
                if (intPlan.getPlanId() == null || !intPlan.getPlanId().equals(workoutRecord.acquirePlanId())) {
                    LogUtil.b("Suggestion_DataImpl", "updatePlanProgress() not current plan");
                    return;
                }
                if (TextUtils.isEmpty(workoutRecord.acquirePlanId())) {
                    return;
                }
                float b = eth.this.b(workoutRecord, loginInit, intPlan);
                if (intPlan.getPlanType().equals(IntPlan.PlanType.FIT_PLAN)) {
                    b = euc.e().d(workoutRecord);
                }
                ety.c().d(b);
                LogUtil.a("Suggestion_DataImpl", "saveRecord acquireFinishRate: ", Float.valueOf(workoutRecord.acquireFinishRate()), workoutRecord.acquirePlanId(), "  planRate:", Float.valueOf(b));
                etx.b().e();
                ary.a().e("PLAN_UPDATE");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d(boolean z, WorkoutRecord workoutRecord, LoginInit loginInit) {
        Plan currentPlan = getCurrentPlan();
        if (currentPlan == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "updatePlanProgress() currentPlan == null");
            return z;
        }
        if (currentPlan.acquireId() == null || !currentPlan.acquireId().equals(workoutRecord.acquirePlanId())) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "updatePlanProgress() not current plan");
            return z;
        }
        if (!TextUtils.isEmpty(workoutRecord.acquirePlanId()) && currentPlan.acquireId().equals(workoutRecord.acquirePlanId())) {
            b(workoutRecord, loginInit, currentPlan);
            ary.a().e("PLAN_UPDATE");
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float b(WorkoutRecord workoutRecord, LoginInit loginInit, IntPlan intPlan) {
        if (intPlan.getPlanType().equals(IntPlan.PlanType.AI_FITNESS_PLAN)) {
            return moe.a(ety.c().d());
        }
        return b(workoutRecord, loginInit, intPlan.getCompatiblePlan());
    }

    private float b(WorkoutRecord workoutRecord, LoginInit loginInit, Plan plan) {
        PlanRecord c;
        if (plan == null) {
            return 0.0f;
        }
        String accountInfo = loginInit.getAccountInfo(1011);
        ett.i().h().d(accountInfo, workoutRecord.acquirePlanId(), euj.b(accountInfo, workoutRecord.acquirePlanId(), plan.acquireType()));
        if (ett.i().h().e(accountInfo, workoutRecord.acquirePlanId())) {
            ett.i().o().c(accountInfo, workoutRecord.acquirePlanId());
        }
        if (plan.acquireType() == 0 && plan.getPlanCategory() != 0) {
            c = euj.d(accountInfo, workoutRecord.acquirePlanId());
        } else {
            c = euj.c(accountInfo, workoutRecord.acquirePlanId());
        }
        ett.i().t().e(accountInfo, c);
        return c.acquireFinishRate();
    }

    private void b(WorkoutRecord workoutRecord, String str) {
        JSONObject jSONObject;
        int i = 1;
        if (workoutRecord.acquireRecordType() == 1) {
            ash.a("planStatistics_need_refresh", "true");
            int a2 = evf.a(workoutRecord.acquirePlanId());
            if (a2 == 0) {
                i = 0;
            } else if (a2 != 3) {
                return;
            }
            try {
                jSONObject = new JSONObject(ash.b("planStatistics_" + str + "_type_" + i));
            } catch (JSONException e2) {
                LogUtil.b("Suggestion_DataImpl", LogAnonymous.b((Throwable) e2));
                jSONObject = null;
            }
            PlanStatistics b = ffq.b(jSONObject);
            long acquireDuration = b.acquireDuration();
            long acquireCalorie = b.acquireCalorie();
            if (i == 0) {
                b.saveDuration(acquireDuration + (workoutRecord.getDuration() * 1000));
            } else {
                b.saveDuration(acquireDuration + workoutRecord.getDuration());
            }
            b.saveCalorie(acquireCalorie + ((int) workoutRecord.acquireActualCalorie()));
            ash.a("planStatistics_" + str + "_type_" + i, ffq.e(b));
        }
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void getRecommendWorkouts(int i, int i2, Integer[] numArr, Integer[] numArr2, Integer[] numArr3, int i3, Integer[] numArr4, int i4, UiCallback<List<FitWorkout>> uiCallback) {
        if (uiCallback == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "getRecommendWorkouts(), callback == null");
            return;
        }
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "getRecommendWorkouts() getAccountInfo()==null");
            uiCallback.onFailure(this.c, 20001, ResultUtil.d(20001));
        } else if (!CommonUtil.aa(arx.b())) {
            uiCallback.onFailure(this.c, ResultUtil.ResultCode.NO_NET, ResultUtil.d(ResultUtil.ResultCode.NO_NET));
        } else {
            e(false, i, i2, numArr, numArr2, numArr3, i3, numArr4, accountInfo, 0, i4, uiCallback);
        }
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void getFilterFitnessCourses(WorkoutListBean workoutListBean, final UiCallback<List<Workout>> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("Suggestion_DataImpl", "getFilterFitnessCourses(), callback == null");
            return;
        }
        if (a(uiCallback) && c(uiCallback)) {
            if (WorkoutListBean.isFitWorkoutType(workoutListBean)) {
                final ArrayList arrayList = new ArrayList(10);
                e(workoutListBean, LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011), arrayList, new DataCallback() { // from class: eth.72
                    @Override // com.huawei.basefitnessadvice.callback.DataCallback
                    public void onFailure(int i, String str) {
                        LogUtil.h("Suggestion_DataImpl", "getWorkoutsFromCloud errorCode=", Integer.valueOf(i));
                        uiCallback.onFailure(i, str);
                    }

                    @Override // com.huawei.basefitnessadvice.callback.DataCallback
                    public void onSuccess(JSONObject jSONObject) {
                        uiCallback.onSuccess(mod.j(arrayList));
                    }
                });
            } else {
                b(workoutListBean, uiCallback);
            }
        }
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void searchWorkoutList(int i, int i2, String str, List<Integer> list, final UiCallback<List<FitWorkout>> uiCallback) {
        if (uiCallback == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "searchWorkoutList(), callback == null");
            return;
        }
        if (c(uiCallback) && d(uiCallback)) {
            String[] split = str.trim().split("\\s+");
            if (split.length > 3) {
                LogUtil.a("Suggestion_DataImpl", "searchWorkoutList searchText = ", str);
                str = split[0] + " " + split[1] + " " + split[2];
                LogUtil.a("Suggestion_DataImpl", "searchWorkoutList change searchText = ", str);
            }
            eqa.a().searchWorkoutList(i, i2, str, list, new DataCallback() { // from class: eth.73
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i3, String str2) {
                    ReleaseLogUtil.c("Suggestion_DataImpl", "searchWorkoutList onFailure() errorCode=", Integer.valueOf(i3));
                    uiCallback.onFailure(eth.this.c, -1, "data == null");
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    if (jSONObject == null) {
                        uiCallback.onFailure(eth.this.c, -1, "data == null");
                    } else {
                        uiCallback.onSuccess(eth.this.c, mnf.b(jSONObject));
                    }
                }
            });
        }
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void getJoinedWorkouts(int i, int i2, Integer[] numArr, Integer[] numArr2, Integer[] numArr3, int i3, Integer[] numArr4, int i4, UiCallback<List<FitWorkout>> uiCallback) {
        if (uiCallback == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "getJoinedWorkouts(), callback == null");
            return;
        }
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "getJoinedWorkouts() getAccountInfo()==null");
            uiCallback.onFailure(this.c, 20001, ResultUtil.d(20001));
        } else if (!CommonUtil.aa(arx.b())) {
            uiCallback.onFailure(this.c, ResultUtil.ResultCode.NO_NET, ResultUtil.d(ResultUtil.ResultCode.NO_NET));
        } else {
            e(false, i, i2, numArr, numArr2, numArr3, i3, numArr4, accountInfo, 1, i4, uiCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final WorkoutListBean workoutListBean, final String str, final List<FitWorkout> list, final DataCallback dataCallback) {
        if (dataCallback == null || list == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "getWorkoutsFromCloud(), callback == null || fitWorkouts == null");
            return;
        }
        final int pageStart = workoutListBean.getPageStart();
        final int pageSize = workoutListBean.getPageSize();
        final int min = Math.min(pageSize, 50);
        workoutListBean.setPageSize(min);
        LogUtil.c("Suggestion_DataImpl", "getWorkoutsFromCloud pageStart = ", Integer.valueOf(pageStart), " ,adjustPageSize = ", Integer.valueOf(min), ", userId = ", str);
        eqa.a().getWorkoutList(workoutListBean, new DataCallback() { // from class: eth.3
            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onFailure(int i, String str2) {
                ReleaseLogUtil.c("Suggestion_DataImpl", "getWorkouts errorCode=", Integer.valueOf(i));
                dataCallback.onFailure(i, str2);
            }

            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onSuccess(JSONObject jSONObject) {
                if (jSONObject == null) {
                    dataCallback.onFailure(-1, "data == null");
                    return;
                }
                List<FitWorkout> b = mnf.b(jSONObject);
                LogUtil.a("Suggestion_DataImpl", "getJoinedworkout getWorkoutsFromCloud :", Integer.valueOf(b.size()));
                list.clear();
                list.addAll(b);
                int optInt = jSONObject.optInt("total", -1);
                boolean z = (optInt >= 0 && pageStart + b.size() >= optInt) || b.size() < min;
                if (pageSize > 50 && !z) {
                    LogUtil.a("Suggestion_DataImpl", "start to executor getWorkoutsFromCloud again");
                    workoutListBean.setPageStart(pageStart + 50);
                    workoutListBean.setPageSize(pageSize - 50);
                    eth.this.e(workoutListBean, str, list, dataCallback);
                    return;
                }
                LogUtil.a("Suggestion_DataImpl", "getWorkoutsFromCloud executor success callback");
                dataCallback.onSuccess(jSONObject);
            }
        });
    }

    private void b(WorkoutListBean workoutListBean, final UiCallback<List<Workout>> uiCallback) {
        eqa.a().getNavigationCourseList(workoutListBean, new DataCallback() { // from class: eth.2
            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onFailure(int i, String str) {
                LogUtil.h("Suggestion_DataImpl", "getWorkoutsFromCloud errorCode=", Integer.valueOf(i));
                uiCallback.onFailure(i, str);
            }

            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onSuccess(JSONObject jSONObject) {
                if (jSONObject == null) {
                    uiCallback.onFailure(-1, "data == null");
                    return;
                }
                String optString = jSONObject.optString("navigationCourseInfos");
                if (TextUtils.isEmpty(optString)) {
                    LogUtil.h("Suggestion_DataImpl", "getNavigationCourseList packageList == null");
                    uiCallback.onFailure(ResultUtil.ResultCode.PARAMETER_INVALID, "getNavigationCourseList packageList == null");
                    return;
                }
                try {
                    List list = (List) new Gson().fromJson(optString, new TypeToken<List<HealthWorkout>>() { // from class: eth.2.1
                    }.getType());
                    if (list == null) {
                        LogUtil.h("Suggestion_DataImpl", "healthWorkouts == null");
                        uiCallback.onFailure(ResultUtil.ResultCode.PARAMETER_INVALID, "healthWorkouts == null");
                    } else {
                        LogUtil.a("Suggestion_DataImpl", "sleepAudioPackageListSize = ", Integer.valueOf(list.size()));
                        uiCallback.onSuccess(new ArrayList(list));
                    }
                } catch (JsonSyntaxException unused) {
                    LogUtil.b("Suggestion_DataImpl", "parse List<HealthWorkout> , jsonSyntaxException");
                    uiCallback.onFailure(ResultUtil.ResultCode.PARAMETER_INVALID, "parse List<HealthWorkout> , jsonSyntaxException");
                }
            }
        });
    }

    private void e(boolean z, int i, int i2, Integer[] numArr, Integer[] numArr2, Integer[] numArr3, int i3, Integer[] numArr4, final String str, final int i4, int i5, final UiCallback<List<FitWorkout>> uiCallback) {
        if (uiCallback == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "getWorkouts(), callback == null");
        } else if (!b()) {
            uiCallback.onFailure(this.c, -4, ResultUtil.d(-4));
        } else {
            final ArrayList arrayList = new ArrayList(10);
            e(new WorkoutListBean(i, i2, i3, numArr, numArr3, numArr2, numArr4, i4), str, arrayList, new DataCallback() { // from class: eth.1
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i6, String str2) {
                    ReleaseLogUtil.d("Suggestion_DataImpl", "getWorkoutsFromCloud errorCode=", Integer.valueOf(i6));
                    uiCallback.onFailure(eth.this.c, i6, str2);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    if (TextUtils.isEmpty(str) && jSONObject != null) {
                        ash.a("total", StringUtils.c(Integer.valueOf(jSONObject.optInt("total", arrayList.size()))));
                    }
                    if (i4 == 1 && !koq.b(arrayList)) {
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            if (((FitWorkout) it.next()).getIntervals() == -2) {
                                it.remove();
                            }
                        }
                    }
                    LogUtil.a("Suggestion_DataImpl", "getWorkouts executor success callback");
                    uiCallback.onSuccess(eth.this.c, arrayList);
                }
            });
        }
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void getWorkout(final ffl fflVar, final UiCallback<FitWorkout> uiCallback) {
        if (uiCallback == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "getWorkout(), callback == null");
        } else {
            LogUtil.a("Suggestion_DataImpl", "workoutId = ", fflVar.h(), " version ", fflVar.g(), " language ", fflVar.d(), "planId: ", fflVar.e());
            asc.e().b(new Runnable() { // from class: etj
                @Override // java.lang.Runnable
                public final void run() {
                    eth.this.a(uiCallback, fflVar);
                }
            });
        }
    }

    /* synthetic */ void a(UiCallback uiCallback, ffl fflVar) {
        UserInfomation userInfo;
        if (LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode()) {
            userInfo = new UserInfomation(UnitUtil.h() ? 1 : 0);
        } else {
            UserProfileMgrApi e2 = e("getWorkout : userProfileMgrApi is null.");
            if (e2 == null || b(e2, (UiCallback<FitWorkout>) uiCallback)) {
                return;
            } else {
                userInfo = e2.getUserInfo();
            }
        }
        String huidOrDefault = LoginInit.getInstance(BaseApplication.getContext()).getHuidOrDefault();
        if (a(userInfo, huidOrDefault, (UiCallback<FitWorkout>) uiCallback)) {
            return;
        }
        FitWorkout b = ett.i().l().b(huidOrDefault, fflVar.h(), fflVar.g(), fflVar.d());
        if (CommonUtil.bu()) {
            uiCallback.onSuccess(this.c, b);
            return;
        }
        if (!b()) {
            uiCallback.onSuccess(this.c, b);
            return;
        }
        if (((CourseApi) Services.c("CoursePlanService", CourseApi.class)).isNeedGetFromCloud(b, fflVar, userInfo)) {
            Object[] objArr = new Object[2];
            objArr[0] = "getWorkout() from cloud ";
            objArr[1] = Boolean.valueOf(b == null);
            ReleaseLogUtil.d("Suggestion_DataImpl", objArr);
            c(fflVar, userInfo, b, (UiCallback<FitWorkout>) uiCallback);
            return;
        }
        uiCallback.onSuccess(this.c, b);
    }

    private UserProfileMgrApi e(String str) {
        UserProfileMgrApi userProfileMgrApi = (UserProfileMgrApi) Services.a("HWUserProfileMgr", UserProfileMgrApi.class);
        if (userProfileMgrApi != null) {
            return userProfileMgrApi;
        }
        LogUtil.h("Suggestion_DataImpl", str);
        return null;
    }

    private boolean b(UserProfileMgrApi userProfileMgrApi, UiCallback<FitWorkout> uiCallback) {
        if (userProfileMgrApi.waitInit()) {
            return false;
        }
        ReleaseLogUtil.c("Suggestion_DataImpl", "getWorkout waitInit fail");
        uiCallback.onFailure(this.c, 20001, ResultUtil.d(20001));
        return true;
    }

    private boolean a(UserInfomation userInfomation, String str, UiCallback<FitWorkout> uiCallback) {
        if (userInfomation != null && (!TextUtils.isEmpty(str) || LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode())) {
            return false;
        }
        ReleaseLogUtil.c("Suggestion_DataImpl", "getWorkout() userInfo == null ||TextUtils.isEmpty(userId)");
        uiCallback.onFailure(this.c, 20001, ResultUtil.d(20001));
        return true;
    }

    private void c(final ffl fflVar, final UserInfomation userInfomation, final FitWorkout fitWorkout, final UiCallback<FitWorkout> uiCallback) {
        ReleaseLogUtil.e("Suggestion_DataImpl", "getWorkoutFromCloud() ", fflVar.h(), " ", fflVar.g(), " ", fflVar.d(), " ", fflVar.h(), " ", Boolean.valueOf(fflVar.c()));
        if (uiCallback == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "getWorkoutFromCloud(), callback == null");
        } else {
            eqa.a().getWorkoutInfo(new ffl.d(fflVar.h()).a(Integer.valueOf(userInfomation.getGenderOrDefaultValue())).d(fflVar.g()).c(fflVar.d()).e(fflVar.e()).d(fflVar.f()).c(fflVar.c()).b(), new DataCallback() { // from class: eth.5
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i, String str) {
                    OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_GET_WORKOUT_DETAIL_60010008.value(), i);
                    ReleaseLogUtil.c("Suggestion_DataImpl", "getWorkoutFromCloud() errorCode=", Integer.valueOf(i));
                    if (fitWorkout == null) {
                        uiCallback.onFailure(eth.this.c, i, str);
                    } else {
                        LogUtil.a("Suggestion_DataImpl", "getWorkout workout from db is not null");
                        uiCallback.onSuccess(eth.this.c, fitWorkout);
                    }
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    eth.this.e(jSONObject, fflVar, userInfomation, fitWorkout, (UiCallback<FitWorkout>) uiCallback);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(JSONObject jSONObject, ffl fflVar, UserInfomation userInfomation, FitWorkout fitWorkout, UiCallback<FitWorkout> uiCallback) {
        Object[] objArr = new Object[2];
        objArr[0] = "getWorkout() is data null ";
        objArr[1] = Boolean.valueOf(jSONObject == null);
        ReleaseLogUtil.e("Suggestion_DataImpl", objArr);
        FitWorkout b = mnf.b(arx.a(), jSONObject);
        if (b != null) {
            if (etd.a() != null && etd.a().equals(fflVar.d())) {
                etx.b().a(fflVar.h(), etn.b(userInfomation.getGenderOrDefaultValue()), fflVar.g(), fflVar.d());
                ett.i().l().b(LoginInit.getInstance(BaseApplication.getContext()).getHuidOrDefault(), b);
            }
            uiCallback.onSuccess(this.c, b);
            return;
        }
        if (fitWorkout != null) {
            LogUtil.a("Suggestion_DataImpl", "onSuccess, workout from db is not null");
            uiCallback.onSuccess(this.c, fitWorkout);
        } else {
            uiCallback.onFailure(this.c, 9999, ResultUtil.d(9999));
            OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_GET_WORKOUT_DETAIL_60010008.value(), 9999);
        }
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void getWorkoutsInfo(List<ffl> list, boolean z, final UiCallback<List<FitWorkout>> uiCallback) {
        eqa.a().getWorkoutsInfo(list, z, new DataCallback() { // from class: eth.7
            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onFailure(int i, String str) {
                OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_GET_WORKOUT_DETAIL_60010008.value(), i);
                ReleaseLogUtil.c("Suggestion_DataImpl", "getWorkoutsInfoFromCloud() errorCode=", Integer.valueOf(i));
                uiCallback.onFailure(eth.this.c, i, str);
            }

            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onSuccess(JSONObject jSONObject) {
                List<FitWorkout> a2 = mnf.a(arx.a(), jSONObject);
                if (a2 != null) {
                    LogUtil.a("Suggestion_DataImpl", "getFitWorkout size: ", Integer.valueOf(a2.size()));
                    uiCallback.onSuccess(eth.this.c, a2);
                } else {
                    uiCallback.onFailure(eth.this.c, 9999, ResultUtil.d(9999));
                    OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_GET_WORKOUT_DETAIL_60010008.value(), 9999);
                }
            }
        });
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void postPlanProgress(String str, final UiCallback<String> uiCallback) {
        if (uiCallback == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "postPlanProgress(), callback == null");
            return;
        }
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "postPlanProgress() getAccountInfo == null");
            uiCallback.onFailure(this.c, 20001, ResultUtil.d(20001));
            return;
        }
        final WorkoutRecord e2 = euj.e(accountInfo, str);
        if (e2 != null) {
            eqa.a().finishExercise(e2.acquireRecordType() == 0 ? 0 : 1, e2, new DataCallback() { // from class: eth.6
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i, String str2) {
                    ReleaseLogUtil.c("Suggestion_DataImpl", "postPlanProgress() errorCode=", Integer.valueOf(i));
                    uiCallback.onFailure(eth.this.c, i, str2);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    uiCallback.onSuccess(eth.this.c, null);
                    long acquireExerciseTime = e2.acquireExerciseTime() / 1000;
                    FitnessHistoryModel.getInstance().postFitnessHistoryRequest(acquireExerciseTime - 1, acquireExerciseTime + 1, 0);
                }
            });
            a(e2);
        } else {
            uiCallback.onSuccess(this.c, null);
        }
        ary.a().e("WORKOUT_FINISHED");
    }

    private void a(final WorkoutRecord workoutRecord) {
        ety.c().b(new UiCallback<epo>() { // from class: eth.10
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                LogUtil.a("Suggestion_DataImpl", "no plan, no need updateFitnessDayRecord");
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onSuccess(epo epoVar) {
                if (epoVar != null && epoVar.getPlanType().equals(IntPlan.PlanType.AI_FITNESS_PLAN) && TextUtils.isEmpty(workoutRecord.acquirePlanId())) {
                    ety.c().e("", ase.b(workoutRecord));
                }
            }
        });
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public PlanRecord getPlanProgress(String str) {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            ReleaseLogUtil.d("Suggestion_DataImpl", "getPlanProgress(String planId) getAccountInfo == null");
            return null;
        }
        Plan currentRunPlan = getCurrentRunPlan();
        if (str == null || currentRunPlan == null || !str.equals(currentRunPlan.acquireId())) {
            if (str != null) {
                return ett.i().t().e(accountInfo, str);
            }
            return null;
        }
        if (currentRunPlan.getPlanCategory() == 1) {
            PlanRecord d = euj.d(accountInfo, str);
            LogUtil.a("Suggestion_DataImpl", "getPlanProgress getIntelligentPlanProgress", d);
            return d;
        }
        LogUtil.a("Suggestion_DataImpl", "getPlanProgress getPlanProgress", euj.c(accountInfo, str));
        return euj.c(accountInfo, str);
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void getPlanProgress(final String str, final UiCallback<PlanRecord> uiCallback) {
        if (uiCallback == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "postPlanProgress(), callback == null");
            return;
        }
        LogUtil.a("Suggestion_DataImpl", "planId = ", str);
        if (evf.a(str) == 3) {
            LogUtil.c("Suggestion_DataImpl", "fitnessPlan ");
            uiCallback.onSuccess(this.c, euc.e().c(str));
        } else if (eve.e() && etx.b().a(str)) {
            e(str, new UiCallback<String>() { // from class: eth.9
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str2) {
                    ReleaseLogUtil.c("Suggestion_DataImpl", "getPlanProgress errorCode=", Integer.valueOf(i));
                    uiCallback.onSuccess(eth.this.c, eth.this.getPlanProgress(str));
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public void onSuccess(String str2) {
                    etx.b().d(str);
                    uiCallback.onSuccess(eth.this.c, eth.this.getPlanProgress(str));
                }
            });
        } else {
            uiCallback.onSuccess(this.c, getPlanProgress(str));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, long j) {
        LogUtil.a("Suggestion_DataImpl", "enter finishPlan");
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "finishPlan() getAccountInfo == null");
            return;
        }
        PlanRecord planProgress = getPlanProgress(str);
        ett.i().t().e(accountInfo, planProgress);
        c();
        if (j != 0) {
            ett.i().t().b(accountInfo, str, j);
        } else {
            ett.i().t().d(accountInfo, str);
        }
        etx.b().a();
        etx.b().c();
        if (eve.e() && !d(str)) {
            ett.i().o().b(str);
            eup.d(str);
            ett.i().h().b(accountInfo, str);
        }
        this.e.remove(a("plan", accountInfo));
        if (planProgress != null && planProgress.getPlanCategory() == 1) {
            euv.c(str);
            euv.d(IntPlan.PlanType.AI_RUN_PLAN.getType());
        }
        if (!eve.e()) {
            ety.c().c(true);
            ary.a().e("PLAN_UPDATE");
        }
        LogUtil.a("Suggestion_DataImpl", "exit finishPlan");
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void finishPlan(final String str, final UiCallback<String> uiCallback) {
        if (uiCallback == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "finishPlan(), callback == null");
            return;
        }
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "finishPlan() getAccountInfo == null");
            uiCallback.onFailure(this.c, 20001, ResultUtil.d(20001));
            return;
        }
        if (eve.e() && !CommonUtil.bu() && !d(str)) {
            if (!CommonUtil.aa(arx.b())) {
                uiCallback.onFailure(this.c, ResultUtil.ResultCode.NO_NET, ResultUtil.d(ResultUtil.ResultCode.NO_NET));
                return;
            } else if (ett.i().o().e(accountInfo) > 0) {
                etn.a(new OnStateListener() { // from class: eth.8
                    @Override // com.huawei.health.courseplanservice.api.OnStateListener
                    public void onFailure(int i, String str2) {
                        ReleaseLogUtil.c("Suggestion_DataImpl", "finishPlan errorCode=", Integer.valueOf(i));
                        eth.this.b(str, (UiCallback<String>) uiCallback);
                    }

                    @Override // com.huawei.health.courseplanservice.api.OnStateListener
                    public void onFinish() {
                        ReleaseLogUtil.d("Suggestion_DataImpl", "finishPlan: data sync completed");
                        eth.this.b(str, (UiCallback<String>) uiCallback);
                    }
                });
            } else {
                b(str, uiCallback);
            }
        } else {
            a(str, System.currentTimeMillis());
            uiCallback.onSuccess(this.c, null);
        }
        if (eve.b) {
            return;
        }
        ary.a().e("PLAN_UPDATE");
        ary.a().e("PLAN_DELETE");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final String str, final UiCallback<String> uiCallback) {
        if (uiCallback == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "postFinishPlan(), callback == null");
            return;
        }
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "postFinishPlan() getAccountInfo == null");
            uiCallback.onFailure(this.c, 20001, ResultUtil.d(20001));
            return;
        }
        final long currentTimeMillis = System.currentTimeMillis();
        final PlanRecord e2 = ett.i().t().e(accountInfo, str);
        if (e2 == null) {
            uiCallback.onFailure(this.c, 20009, ResultUtil.d(20009));
        } else if (e2.getPlanCategory() == 1) {
            new euo().c(str, new UiCallback<mny>() { // from class: eth.15
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str2) {
                    eth.this.b(e2.acquirePlanId(), e2.acquireFinishRate(), e2.acquireActualDistance(), e2.acquireActualCalorie(), 0.0f, currentTimeMillis, uiCallback);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public void onSuccess(mny mnyVar) {
                    if (mnyVar == null) {
                        eth.this.b(e2.acquirePlanId(), e2.acquireFinishRate(), e2.acquireActualDistance(), e2.acquireActualCalorie(), 0.0f, currentTimeMillis, uiCallback);
                        return;
                    }
                    mnz s = mnyVar.s();
                    eth.this.b(str, s.g(), s.e(), s.d(), 60000.0f * s.c(), currentTimeMillis, uiCallback);
                }
            });
        } else {
            b(e2.acquirePlanId(), e2.acquireFinishRate(), e2.acquireActualDistance(), e2.acquireActualCalorie(), 0.0f, currentTimeMillis, uiCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final String str, float f, float f2, float f3, float f4, final long j, final UiCallback<String> uiCallback) {
        eqa.a().finishPlan(new FinishPlanBean(str, f, f2, f3, f4, j), new DataCallback() { // from class: eth.11
            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onFailure(int i, String str2) {
                ReleaseLogUtil.c("Suggestion_DataImpl", "postFinishPlan errorCode=", Integer.valueOf(i));
                if (i != 200019) {
                    uiCallback.onFailure(eth.this.c, i, str2);
                } else {
                    onSuccess(null);
                }
            }

            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onSuccess(JSONObject jSONObject) {
                eth.this.a(str, j);
                uiCallback.onSuccess(eth.this.c, null);
            }
        });
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void postExerciseBehavior(WorkoutRecord workoutRecord, int i) {
        if (!DataSyncService.b(workoutRecord.acquireCourseBelongType(), i)) {
            LogUtil.h("Suggestion_DataImpl", "postExerciseBehavior skipped by belongType=", Integer.valueOf(workoutRecord.acquireCourseBelongType()), ", operaType=", Integer.valueOf(i));
            return;
        }
        LogUtil.a("Suggestion_DataImpl", "postExerciseBehavior workoutId=", workoutRecord.acquireWorkoutId(), ",version = ", workoutRecord.acquireVersion(), ",workoutPackageId = ", workoutRecord.acquireWorkoutPackageId(), ",courseBelongType = ", Integer.valueOf(workoutRecord.acquireCourseBelongType()), ",operationType=", Integer.valueOf(i));
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "postExerciseBehavior() getAccountInfo == null");
        } else {
            ett.i().o().c(accountInfo, workoutRecord, String.valueOf(i));
        }
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void postExerciseBehavior(WorkoutRecord workoutRecord, int i, final UiCallback<String> uiCallback) {
        if (uiCallback == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "postExerciseBehavior(), callback == null");
        } else {
            eqa.a().postBehavior(workoutRecord, i, new DataCallback() { // from class: eth.12
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i2, String str) {
                    ReleaseLogUtil.c("Suggestion_DataImpl", "postExerciseBehavior: errorCode= ", Integer.valueOf(i2));
                    uiCallback.onFailure(eth.this.c, i2, str);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    uiCallback.onSuccess(eth.this.c, null);
                }
            });
        }
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void collectBehavior(String str, String str2) {
        LogUtil.a("Suggestion_DataImpl", "collectBehavior workoutId=", str);
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "collectBehavior(String workoutId): getAccountInfo == null");
        } else {
            ary.a().e("COLLECTION_ADD");
            ett.i().o().e(accountInfo, str);
        }
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void collectBehavior(String str, final UiCallback<String> uiCallback) {
        if (uiCallback == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "collectBehavior(), callback == null");
        } else {
            eqa.a().collectBehavior(str, new DataCallback() { // from class: eth.14
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i, String str2) {
                    ReleaseLogUtil.c("Suggestion_DataImpl", "collectBehavior errorCode=", Integer.valueOf(i));
                    uiCallback.onFailure(eth.this.c, i, str2);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    uiCallback.onSuccess(eth.this.c, null);
                }
            });
        }
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void collectBehavior(String str, int i, final UiCallback<Boolean> uiCallback) {
        eqa.a().collectBehavior(str, i, new DataCallback() { // from class: eth.17
            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onFailure(int i2, String str2) {
                ReleaseLogUtil.c("Suggestion_DataImpl", "collectBehavior errorCode=", Integer.valueOf(i2));
                UiCallback uiCallback2 = uiCallback;
                if (uiCallback2 != null) {
                    uiCallback2.onFailure(eth.this.c, i2, str2);
                }
            }

            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onSuccess(JSONObject jSONObject) {
                LogUtil.a("Suggestion_DataImpl", "collectBehavior success");
                UiCallback uiCallback2 = uiCallback;
                if (uiCallback2 != null) {
                    uiCallback2.onSuccess(eth.this.c, null);
                }
            }
        });
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void getCollectBehavior(final String str, final UiCallback<Boolean> uiCallback) {
        getBehaviorList(0, Integer.MAX_VALUE, 2, new UiCallback<List<FitWorkout>>() { // from class: eth.18
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str2) {
                ReleaseLogUtil.c("Suggestion_DataImpl", "getCollectBehavior errorCode=", Integer.valueOf(i));
                uiCallback.onFailure(eth.this.c, i, str2);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onSuccess(List<FitWorkout> list) {
                boolean z;
                Iterator<FitWorkout> it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    } else if (it.next().acquireId().equals(str)) {
                        z = true;
                        break;
                    }
                }
                uiCallback.onSuccess(eth.this.c, Boolean.valueOf(z));
            }
        });
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void deleteBehavior(String str, int i) {
        LogUtil.a("Suggestion_DataImpl", "deleteBehavior workoutId=", str);
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "deleteBehavior(String workoutId): getAccountInfo == null");
        } else {
            ary.a().e("COLLECTION_DELETE");
            ett.i().o().b(accountInfo, i, str);
        }
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void deleteBehavior(String str, int i, final UiCallback<String> uiCallback) {
        if (uiCallback == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "deleteBehavior(), callback == null");
        } else {
            eqa.a().deleteBehavior(str, i, new DataCallback() { // from class: eth.19
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i2, String str2) {
                    ReleaseLogUtil.c("Suggestion_DataImpl", "deleteBehavior errorCode=", Integer.valueOf(i2));
                    uiCallback.onFailure(eth.this.c, i2, str2);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    uiCallback.onSuccess(eth.this.c, null);
                }
            });
        }
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void getBehaviorList(int i, int i2, int i3, String str, UiCallback<List<Workout>> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("Suggestion_DataImpl", "getBehaviorList(), callback == null");
            return;
        }
        if (i2 > 50) {
            LogUtil.b("Suggestion_DataImpl", "getBehaviorList(), pageSize > 50");
            uiCallback.onFailure(this.c, -5, ResultUtil.d(-5));
            return;
        }
        if (TextUtils.isEmpty(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011))) {
            LogUtil.b("Suggestion_DataImpl", "getBehaviorList(): getAccountInfo()==null");
            uiCallback.onFailure(this.c, 20001, ResultUtil.d(20001));
        } else if (!eve.e()) {
            uiCallback.onFailure(this.c, -4, ResultUtil.d(-4));
        } else {
            if (!CommonUtil.aa(arx.b())) {
                uiCallback.onFailure(this.c, ResultUtil.ResultCode.NO_NET, ResultUtil.d(ResultUtil.ResultCode.NO_NET));
                return;
            }
            int min = Math.min(i2, 50);
            LogUtil.a("Suggestion_DataImpl", "getBehaviorList pageStart = ", Integer.valueOf(i), " ,adjustPageSize = ", Integer.valueOf(min), " operationType:", Integer.valueOf(i3), " workoutType:", str);
            a(i, i3, str, min, uiCallback);
        }
    }

    private void a(int i, int i2, String str, int i3, final UiCallback<List<Workout>> uiCallback) {
        eqa.a().getBehaviorList(i, i3, i2, str, new DataCallback() { // from class: eth.20
            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onFailure(int i4, String str2) {
                LogUtil.b("Suggestion_DataImpl", "getBehaviorList getWorkouts: errorCode=", Integer.valueOf(i4));
                uiCallback.onFailure(i4, str2);
            }

            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onSuccess(JSONObject jSONObject) {
                if (jSONObject == null) {
                    uiCallback.onFailure(-1, "data == null");
                    return;
                }
                List<Workout> e2 = mnf.e(jSONObject);
                if (e2 == null) {
                    uiCallback.onFailure(-1, "workouts == null");
                } else {
                    LogUtil.c("Suggestion_DataImpl", "getBehaviorList onSuccess :", Integer.valueOf(e2.size()));
                    uiCallback.onSuccess(eth.this.c, e2);
                }
            }
        });
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void getActionList(int i, int i2, final int i3, int i4, final UiCallback<List<AtomicAction>> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("Suggestion_DataImpl", "getActionList(), callback == null");
        } else if (!b() || !CommonUtil.aa(arx.b())) {
            uiCallback.onFailure(this.c, ResultUtil.ResultCode.NO_NET, ResultUtil.d(ResultUtil.ResultCode.NO_NET));
        } else {
            eqa.a().getActionList(i, i2, i3, i4, new DataCallback() { // from class: eth.16
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i5, String str) {
                    LogUtil.b("Suggestion_DataImpl", "getActionList(): errorCode=", Integer.valueOf(i5));
                    uiCallback.onFailure(eth.this.c, i5, str);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    if (jSONObject == null) {
                        uiCallback.onSuccess(eth.this.c, null);
                    } else if (i3 == 1) {
                        uiCallback.onSuccess(eth.this.c, ffq.a(jSONObject.optJSONArray("actionList")));
                    } else {
                        uiCallback.onSuccess(eth.this.c, ffq.c(jSONObject.optJSONArray("actionList")));
                    }
                }
            });
        }
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void searchActionList(int i, int i2, int i3, String str, final UiCallback<List<AtomicAction>> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("Suggestion_DataImpl", "searchActionList(), callback == null");
        } else if (!b() || !CommonUtil.aa(arx.b())) {
            uiCallback.onFailure(this.c, ResultUtil.ResultCode.NO_NET, ResultUtil.d(ResultUtil.ResultCode.NO_NET));
        } else {
            eqa.a().searchActionList(i, i2, i3, str, new DataCallback() { // from class: eth.24
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i4, String str2) {
                    LogUtil.b("Suggestion_DataImpl", "searchActionList(): errorCode=", Integer.valueOf(i4));
                    uiCallback.onFailure(eth.this.c, i4, str2);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    if (jSONObject == null) {
                        uiCallback.onSuccess(eth.this.c, null);
                    } else {
                        uiCallback.onSuccess(eth.this.c, ffq.c(jSONObject.optJSONArray("actionList")));
                    }
                }
            });
        }
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void getFitnessActionInfo(String str, String str2, final UiCallback<AtomicAction> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("Suggestion_DataImpl", "getFitnessActionInfo(), callback == null");
        } else if (!b() || !CommonUtil.aa(arx.b())) {
            uiCallback.onFailure(this.c, ResultUtil.ResultCode.NO_NET, ResultUtil.d(ResultUtil.ResultCode.NO_NET));
        } else {
            eqa.a().getFitnessActionInfo(str, str2, new DataCallback() { // from class: eth.25
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i, String str3) {
                    LogUtil.b("Suggestion_DataImpl", "getFitnessActionInfo(): errorCode=", Integer.valueOf(i));
                    uiCallback.onFailure(eth.this.c, i, str3);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    if (jSONObject == null) {
                        LogUtil.b("Suggestion_DataImpl", "getFitnessActionInfo onSuccess data is null.");
                        uiCallback.onSuccess(eth.this.c, null);
                    } else {
                        uiCallback.onSuccess(eth.this.c, ffq.a(jSONObject.optJSONObject("actionWorkoutInfo")));
                    }
                }
            });
        }
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void getBehaviorList(int i, int i2, int i3, final UiCallback<List<FitWorkout>> uiCallback) {
        if (uiCallback == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "getBehaviorList(), callback == null");
            return;
        }
        if (TextUtils.isEmpty(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011))) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "getBehaviorList(): getAccountInfo()==null");
            uiCallback.onFailure(this.c, 20001, ResultUtil.d(20001));
        } else if (!eve.e()) {
            uiCallback.onFailure(this.c, -4, ResultUtil.d(-4));
        } else if (!CommonUtil.aa(arx.b())) {
            uiCallback.onFailure(this.c, ResultUtil.ResultCode.NO_NET, ResultUtil.d(ResultUtil.ResultCode.NO_NET));
        } else {
            final ArrayList arrayList = new ArrayList(10);
            e(i, i2, i3, arrayList, new DataCallback() { // from class: eth.23
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i4, String str) {
                    ReleaseLogUtil.c("Suggestion_DataImpl", "getBehaviorListCloud: errorCode= ", Integer.valueOf(i4));
                    uiCallback.onFailure(eth.this.c, i4, str);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    Collections.reverse(arrayList);
                    uiCallback.onSuccess(eth.this.c, arrayList);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final int i, final int i2, final int i3, final List<FitWorkout> list, final DataCallback dataCallback) {
        if (dataCallback == null || list == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "getBehaviorListCloud(), callback == null || fitWorkouts == null");
            return;
        }
        final int min = Math.min(i2, 50);
        LogUtil.c("Suggestion_DataImpl", "getBehaviorListCloud pageStart = ", Integer.valueOf(i), " ,adjustPageSize = ", Integer.valueOf(min));
        eqa.a().getBehaviorList(i, min, i3, new DataCallback() { // from class: eth.21
            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onFailure(int i4, String str) {
                ReleaseLogUtil.c("Suggestion_DataImpl", "getBehaviorListCloud getWorkouts: errorCode=", Integer.valueOf(i4));
                dataCallback.onFailure(i4, str);
            }

            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onSuccess(JSONObject jSONObject) {
                if (jSONObject == null) {
                    dataCallback.onFailure(-1, "data == null");
                    return;
                }
                List<FitWorkout> b = mnf.b(jSONObject);
                list.addAll(b);
                int optInt = jSONObject.optInt("total", -1);
                LogUtil.c("Suggestion_DataImpl", "getBehaviorListCloud onSuccess :", Integer.valueOf(b.size()), "TotalSize:", Integer.valueOf(optInt));
                int i4 = i;
                int i5 = min;
                int i6 = i4 + i5;
                boolean z = i6 >= optInt;
                int i7 = i2;
                if (i7 > 50 && !z) {
                    eth.this.e(i6, i7 - i5, i3, (List<FitWorkout>) list, dataCallback);
                } else {
                    dataCallback.onSuccess(jSONObject);
                }
            }
        });
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public List<WorkoutRecord> getWorkoutRecords(String str) {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            LogUtil.b("Suggestion_DataImpl", "getWorkoutRecords(): getAccountInfo == null");
            return new ArrayList(0);
        }
        return euj.a(accountInfo, str);
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public List<WorkoutRecord> getWorkoutRecords(final String str, final UiCallback<List<WorkoutRecord>> uiCallback) {
        if (uiCallback == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "getWorkoutRecords(), callback == null");
            return null;
        }
        final String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "getWorkoutRecords() getAccountInfo == null");
            uiCallback.onFailure(this.c, 20001, ResultUtil.d(20001));
            return null;
        }
        List<WorkoutRecord> a2 = euj.a(accountInfo, str);
        if (CommonUtil.bu()) {
            uiCallback.onSuccess(this.c, a2);
            return a2;
        }
        if (etx.b().a(str)) {
            if (!etn.b(str) && ett.i().t().b(accountInfo, str)) {
                uiCallback.onSuccess(this.c, a2);
            } else {
                e(str, new UiCallback<String>() { // from class: eth.27
                    @Override // com.huawei.basefitnessadvice.callback.UiCallback
                    public void onFailure(int i, String str2) {
                        ReleaseLogUtil.c("Suggestion_DataImpl", "getWorkoutRecords: errorCode=", Integer.valueOf(i));
                        uiCallback.onFailure(eth.this.c, i, str2);
                    }

                    @Override // com.huawei.basefitnessadvice.callback.UiCallback
                    /* renamed from: c, reason: merged with bridge method [inline-methods] */
                    public void onSuccess(String str2) {
                        etx.b().d(str);
                        uiCallback.onSuccess(eth.this.c, euj.a(accountInfo, str));
                    }
                });
            }
        } else {
            uiCallback.onSuccess(this.c, a2);
        }
        return a2;
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public List<WorkoutRecord> getWorkoutRecords(String str, String str2, String str3) {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "getWorkoutRecords() getAccountInfo == null");
            return new ArrayList();
        }
        return euj.b(accountInfo, str, str2, str3);
    }

    private void e(final String str, final UiCallback<String> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("Suggestion_DataImpl", "updateWorkoutRecords(), callback == null");
            return;
        }
        final String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            LogUtil.b("Suggestion_DataImpl", "updateWorkoutRecords(): getAccountInfo == null");
            uiCallback.onFailure(this.c, 20001, ResultUtil.d(20001));
        } else {
            eqa.a().getPlanProgress(str, new DataCallback() { // from class: eth.26
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i, String str2) {
                    ReleaseLogUtil.c("Suggestion_DataImpl", "updateWorkoutRecords() errorCode=", Integer.valueOf(i));
                    uiCallback.onFailure(i, str2);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    eth.this.b(jSONObject, str, accountInfo, (UiCallback<String>) uiCallback);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(JSONObject jSONObject, String str, String str2, UiCallback<String> uiCallback) {
        if (jSONObject != null) {
            int optInt = jSONObject.optInt("planType");
            LogUtil.a("Suggestion_DataImpl", "getPlanProgress:", Integer.valueOf(optInt));
            if (optInt == IntPlan.PlanType.AI_FITNESS_PLAN.getType() || optInt == IntPlan.PlanType.AI_RUN_PLAN.getType()) {
                eup.a(str, str2, ffm.e(str, jSONObject.optJSONArray("progress")));
            } else {
                eup.e(str2, ffm.e(str, jSONObject.optJSONArray("progress")));
            }
            ett.i().h().a(str2, str, etp.e(jSONObject.optJSONObject("bestRecords")));
        } else {
            LogUtil.b("Suggestion_DataImpl", "updateWorkoutRecords(), data == null");
        }
        if (etn.b(str)) {
            Plan currentPlan = etr.b().getCurrentPlan();
            if (currentPlan != null && currentPlan.getPlanCategory() == 1) {
                ett.i().t().e(str2, euj.d(str2, str));
            } else {
                ett.i().t().e(str2, euj.c(str2, str));
            }
        } else {
            ett.i().t().c(str2, str);
        }
        uiCallback.onSuccess(null);
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public boolean updateBestRecord(String str, int i, int i2) {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            LogUtil.b("Suggestion_DataImpl", "updateBestRecord getAccountInfo == null");
            return false;
        }
        if (i2 < 0) {
            LogUtil.h("Suggestion_DataImpl", "updateBestRecord value < 0");
            return false;
        }
        return ett.i().h().c(accountInfo, str, i, i2);
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public int getWorkoutCount(String str, String str2) {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            LogUtil.b("Suggestion_DataImpl", "getWorkoutCount() getAccountInfo == null");
            return 0;
        }
        if (eve.e()) {
            return ett.i().l().a(accountInfo, str, str2);
        }
        return euj.e(accountInfo, str, str2);
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void getWorkoutCount(final ffl fflVar, final UiCallback<Integer> uiCallback) {
        if (uiCallback == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "getWorkoutCount(), callback == null");
            return;
        }
        UserProfileMgrApi e2 = e("getWorkoutCount : userProfileMgrApi is null.");
        if (e2 == null) {
            return;
        }
        final UserInfomation userInfo = e2.getUserInfo();
        final String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (userInfo == null || TextUtils.isEmpty(accountInfo)) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "getWorkoutCount(): userInfo == null || accountInfo == null");
            uiCallback.onFailure(this.c, 20001, ResultUtil.d(20001));
        } else {
            uiCallback.onSuccess(this.c, Integer.valueOf(getWorkoutCount(fflVar.h(), fflVar.g())));
            if (etx.b().d(fflVar.h(), etn.b(userInfo.getGenderOrDefaultValue()), fflVar.g())) {
                eqa.a().getWorkoutInfo(fflVar, new DataCallback() { // from class: eth.29
                    @Override // com.huawei.basefitnessadvice.callback.DataCallback
                    public void onFailure(int i, String str) {
                        ReleaseLogUtil.c("Suggestion_DataImpl", "getWorkoutCount() errorCode=", Integer.valueOf(i));
                    }

                    @Override // com.huawei.basefitnessadvice.callback.DataCallback
                    public void onSuccess(JSONObject jSONObject) {
                        FitWorkout b = mnf.b(arx.a(), jSONObject);
                        if (b != null) {
                            etx.b().e(fflVar.h(), etn.b(userInfo.getGenderOrDefaultValue()), fflVar.g());
                            ett.i().l().b(accountInfo, b);
                            uiCallback.onSuccess(eth.this.c, Integer.valueOf(eth.this.getWorkoutCount(fflVar.h(), fflVar.g())));
                            return;
                        }
                        uiCallback.onFailure(eth.this.c, 9999, ResultUtil.d(9999));
                    }
                });
            }
        }
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public int getWorkoutMediaFilesLength(String str, String str2) {
        LoginInit loginInit = LoginInit.getInstance(BaseApplication.getContext());
        if (loginInit.getHuidOrDefault() == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "getWorkoutMediaFilesLength accountInfo == null");
            return 0;
        }
        if (CommonUtil.bu()) {
            return 0;
        }
        FitWorkout d = ett.i().l().d(loginInit.getHuidOrDefault(), str, str2);
        if (d != null) {
            squ.q(d.getTimbre());
        }
        return etq.a(d);
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public boolean isWorkoutMediaFileHasDownloaded(String str, String str2, int i) {
        if (LoginInit.getInstance(BaseApplication.getContext()).getHuidOrDefault() == null) {
            LogUtil.b("Suggestion_DataImpl", "getWorkoutMediaFilesLength accountInfo == null");
            return false;
        }
        int acquireWorkoutMediaFileSize = acquireWorkoutMediaFileSize(str, str2, i);
        LogUtil.a("Suggestion_DataImpl", "mediaFilesLength:", Integer.valueOf(acquireWorkoutMediaFileSize));
        return acquireWorkoutMediaFileSize == 0;
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public int acquireWorkoutMediaFileSize(String str, String str2, int i) {
        LoginInit loginInit = LoginInit.getInstance(BaseApplication.getContext());
        if (loginInit.getHuidOrDefault() == null) {
            LogUtil.b("Suggestion_DataImpl", "acquireWorkoutMediaFileSize accountInfo == null");
            return 0;
        }
        int c = etq.c(ett.i().l().d(loginInit.getHuidOrDefault(), str, str2), i);
        LogUtil.a("Suggestion_DataImpl", "acquireWorkoutMediaFileSize:", Integer.valueOf(c));
        return c;
    }

    private String d(fey feyVar) {
        return feyVar.c() == 1 ? "R" : FitWorkout.acquireComeFrom(feyVar.a());
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void downloadWorkoutMediaFiles(final fey feyVar, final UiCallback<String> uiCallback) {
        if (uiCallback == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "downloadWorkoutMediaFiles(), callback == null");
            return;
        }
        if (!CommonUtil.aa(arx.b())) {
            uiCallback.onFailure(this.c, ResultUtil.ResultCode.NO_NET, ResultUtil.d(ResultUtil.ResultCode.NO_NET));
            return;
        }
        final String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "downloadWorkoutMediaFiles(): userid is empty");
            uiCallback.onFailure(this.c, 20001, ResultUtil.d(20001));
        } else {
            squ.q(feyVar.e());
            final String d = d(feyVar);
            b(0L, d, feyVar.e(), new UiCallback<Boolean>() { // from class: eth.28
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    if (etq.e(d)) {
                        eth.this.a(feyVar.a(), feyVar.d(), (UiCallback<String>) uiCallback, accountInfo);
                    } else {
                        uiCallback.onFailure(eth.this.c, i, str);
                    }
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onSuccess(Boolean bool) {
                    eth.this.a(feyVar.a(), feyVar.d(), (UiCallback<String>) uiCallback, accountInfo);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2, UiCallback<String> uiCallback, String str3) {
        if (uiCallback == null) {
            LogUtil.b("Suggestion_DataImpl", "downloadWorkoutMediaFiles(), callback == null");
            return;
        }
        FitWorkout d = ett.i().l().d(str3, str, str2);
        ArrayList arrayList = new ArrayList();
        long c = etq.c(d, arrayList, false);
        if (c > squ.b() - 100000000) {
            uiCallback.onFailure(this.c, -7, ResultUtil.d(-7));
        } else {
            downloadWorkoutMediaFile(arrayList, c, uiCallback);
        }
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void downloadWorkoutMediaFileByPosition(String str, String str2, UiCallback<String> uiCallback, int i) {
        if (uiCallback == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "downloadWorkoutMediaFileByPosition(), callback == null");
            return;
        }
        LoginInit loginInit = LoginInit.getInstance(BaseApplication.getContext());
        if (loginInit.getHuidOrDefault() == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "downloadWorkoutMediaFiles(): accountInfo == null");
            uiCallback.onFailure(this.c, 20001, ResultUtil.d(20001));
            return;
        }
        FitWorkout d = ett.i().l().d(loginInit.getHuidOrDefault(), str, str2);
        ArrayList arrayList = new ArrayList();
        long a2 = etq.a(d, arrayList, i);
        LogUtil.a("Suggestion_DataImpl", "mediaFileLength:", Long.valueOf(a2));
        if (a2 > squ.b() - 100000000) {
            uiCallback.onFailure(this.c, -7, ResultUtil.d(-7));
        } else {
            downloadWorkoutMediaFile(arrayList, a2, uiCallback);
        }
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void downloadWorkoutMediaFile(List<Media> list, long j, UiCallback<String> uiCallback) {
        if (uiCallback == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "downloadWorkoutMediaFile(), callback == null");
        } else if (etn.e(list) == null) {
            uiCallback.onSuccess(this.c, null);
        } else {
            new eua(list, j, uiCallback).e();
        }
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void cancelDownloadWorkoutMediaFiles(fey feyVar) {
        if (feyVar == null) {
            LogUtil.b("Suggestion_DataImpl", "cancelDownloadWorkoutMediaFiles() param is null!");
        } else {
            eqa.a().cancelDownloadFile(squ.i(squ.h(d(feyVar))));
        }
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void downloadSubtitles(String str, final UiCallback<String> uiCallback) {
        if (!CommonUtil.aa(arx.b())) {
            uiCallback.onFailure(this.c, ResultUtil.ResultCode.NO_NET, ResultUtil.d(ResultUtil.ResultCode.NO_NET));
            return;
        }
        if (TextUtils.isEmpty(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011))) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "downloadSubtitles(): userid is empty");
            uiCallback.onFailure(this.c, 20001, ResultUtil.d(20001));
            return;
        }
        final String k = squ.k(str);
        File file = new File(k);
        if (file.exists()) {
            LogUtil.a("Suggestion_DataImpl", "downloadSubtitles file delete: ", Boolean.valueOf(file.delete()));
        }
        eqa.a().downloadFile(str, k, new DataCallback() { // from class: eth.30
            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onFailure(int i, String str2) {
                ReleaseLogUtil.c("Suggestion_DataImpl", "downloadSubtitles onFailure: errorCode=", Integer.valueOf(i));
                uiCallback.onFailure(i, str2);
            }

            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onSuccess(JSONObject jSONObject) {
                LogUtil.a("Suggestion_DataImpl", "downloadSubtitles success");
                uiCallback.onSuccess(k);
            }
        });
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void downloadFile(String str, final String str2, final UiCallback<String> uiCallback, boolean z) {
        if (c(uiCallback) && d(uiCallback)) {
            eqa.a().downloadFile(str, str2, new DataCallback() { // from class: eth.31
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i, String str3) {
                    ReleaseLogUtil.c("Suggestion_DataImpl", "downloadSubtitles onFailure: errorCode=", Integer.valueOf(i));
                    uiCallback.onFailure(i, str3);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    LogUtil.a("Suggestion_DataImpl", "downloadSubtitles success");
                    uiCallback.onSuccess(str2);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onProgress(long j, long j2, boolean z2) {
                    super.onProgress(j, j2, z2);
                    uiCallback.onProgress(j, j2);
                }
            }, z);
        }
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void setExerciseRemind(boolean z, int i) {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "setExerciseRemind getAccountInfo == null");
            return;
        }
        if (isOpenRemind() || z) {
            if (isOpenRemind() && z && i == getRemindTime()) {
                return;
            }
            d(z, i);
            Plan currentPlan = getCurrentPlan();
            if (currentPlan != null) {
                ett.i().o().c(accountInfo, currentPlan.acquireId(), String.valueOf(i));
                HashMap hashMap = new HashMap(3);
                hashMap.put("plan_name", currentPlan.acquireName());
                hashMap.put("type", Integer.valueOf(currentPlan.acquireType()));
                hashMap.put("state", Integer.valueOf(z ? 1 : 0));
                gge.e("1130004", hashMap);
            }
        }
    }

    private void d(boolean z, int i) {
        Plan currentRunPlan;
        if ((isOpenRemind() || z) && (currentRunPlan = getCurrentRunPlan()) != null) {
            LogUtil.a("Suggestion_DataImpl", "isOpenRemind = ", Boolean.valueOf(z), " remindTime = ", Integer.valueOf(i));
            if (z == isOpenRemind() && i == getRemindTime()) {
                return;
            }
            etl.e(currentRunPlan, Boolean.valueOf(z), i);
        }
    }

    private void c() {
        Plan currentRunPlan;
        if (isOpenRemind() && (currentRunPlan = getCurrentRunPlan()) != null) {
            etl.e(currentRunPlan, (Boolean) false, -1);
        }
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void postPlanRemind(String str, int i, final UiCallback<String> uiCallback) {
        if (uiCallback == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "postPlanRemind(), callback == null");
        } else {
            eqa.a().postPlanRemind(str, i, new DataCallback() { // from class: eth.32
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i2, String str2) {
                    ReleaseLogUtil.c("Suggestion_DataImpl", "postPlanRemind errorCode=", Integer.valueOf(i2));
                    uiCallback.onFailure(eth.this.c, i2, str2);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    uiCallback.onSuccess(eth.this.c, null);
                }
            });
        }
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void postBestRecord(final String str, final UiCallback<String> uiCallback) {
        if (uiCallback == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "postBestRecord(), callback == null");
            return;
        }
        final String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "postBestRecord(): getAccountInfo = null");
            uiCallback.onFailure(this.c, 20001, ResultUtil.d(20001));
            return;
        }
        PlanStat c = ett.i().h().c(accountInfo, str);
        if (c == null) {
            uiCallback.onSuccess(null);
        } else {
            eqa.a().postBestRecord(str, c, new DataCallback() { // from class: eth.35
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i, String str2) {
                    ReleaseLogUtil.c("Suggestion_DataImpl", "postBestRecord errorCode=", Integer.valueOf(i));
                    uiCallback.onFailure(eth.this.c, i, str2);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    ett.i().h().d(accountInfo, str);
                    uiCallback.onSuccess(eth.this.c, null);
                }
            });
        }
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void postBestRecordFit(final String str, final UiCallback<String> uiCallback) {
        if (uiCallback == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "postBestRecordFit(), callback == null");
            return;
        }
        final String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "postBestRecordFit(): getAccountInfo == null");
            uiCallback.onFailure(this.c, 20001, ResultUtil.d(20001));
            return;
        }
        BestRecordFitStat a2 = ett.i().m().a(accountInfo, str);
        if (a2 == null) {
            uiCallback.onSuccess(null);
        } else {
            eqa.a().postTrainBestRecords(a2, new DataCallback() { // from class: eth.34
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i, String str2) {
                    ReleaseLogUtil.c("Suggestion_DataImpl", "postBestRecordFit errorCode=", Integer.valueOf(i));
                    uiCallback.onFailure(eth.this.c, i, str2);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    ett.i().m().c(accountInfo, str);
                    uiCallback.onSuccess(eth.this.c, null);
                }
            });
        }
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void queryTrainCountByWorkoutId(final String str, final UiCallback<Integer> uiCallback) {
        if (uiCallback == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "queryTrainCountByWorkoutId(), callback == null");
            return;
        }
        final String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "queryTrainCountByWorkoutId(): getAccountInfo == null");
            uiCallback.onFailure(this.c, 20001, ResultUtil.d(20001));
        } else if (etx.b().b(str)) {
            eqa.a().queryTrainCountByWorkoutId(str, new DataCallback() { // from class: eth.38
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i, String str2) {
                    ReleaseLogUtil.c("Suggestion_DataImpl", "queryTrainCountByWorkoutId errorCode=", Integer.valueOf(i));
                    uiCallback.onSuccess(eth.this.c, Integer.valueOf(ett.i().s().b(accountInfo, str)));
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    if (jSONObject == null) {
                        LogUtil.b("Suggestion_DataImpl", "queryTrainCountByWorkoutId(), data == null");
                        uiCallback.onFailure(-1, "data == null");
                    } else {
                        int optInt = jSONObject.optInt("count");
                        ett.i().s().a(accountInfo, str, optInt);
                        uiCallback.onSuccess(eth.this.c, Integer.valueOf(optInt));
                    }
                }
            });
        } else {
            uiCallback.onSuccess(this.c, Integer.valueOf(ett.i().s().b(accountInfo, str)));
        }
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void postDeleteUserWorkout(List<Workout> list, final UiCallback<String> uiCallback) {
        if (uiCallback == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "postDeleteUserWorkout(), callback == null");
        } else if (TextUtils.isEmpty(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011))) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "postDeleteUserWorkout(): getAccountInfo == null");
            uiCallback.onFailure(this.c, 20001, ResultUtil.d(20001));
        } else {
            eqa.a().postDeleteUserWorkout(list, new DataCallback() { // from class: eth.39
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i, String str) {
                    ReleaseLogUtil.c("Suggestion_DataImpl", "postPlanRemind errorCode=", Integer.valueOf(i));
                    uiCallback.onFailure(eth.this.c, i, str);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    uiCallback.onSuccess(eth.this.c, null);
                }
            });
        }
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public boolean isOpenRemind() {
        return etl.d();
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public PlanStat getPlanStat(String str) {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "getPlanStat getAccountInfo == null");
            return new PlanStat();
        }
        return ett.i().h().a(accountInfo, str);
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public int getRemindTime() {
        return etl.e();
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void getWorkoutFilters(final List<Integer> list, final UiCallback<String> uiCallback) {
        c(uiCallback);
        eqa.a().getWorkoutFilters(list, new DataCallback() { // from class: eth.37
            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onFailure(int i, String str) {
                LogUtil.b("Suggestion_DataImpl", "getWorkoutFilters errorCode=", Integer.valueOf(i));
                uiCallback.onFailure(eth.this.c, i, str);
            }

            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onSuccess(JSONObject jSONObject) {
                etx.b().e(String.valueOf(list));
                etn.d(jSONObject);
                uiCallback.onSuccess(eth.this.c, jSONObject.toString());
            }
        });
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void getAggregatePageByType(int i, final UiCallback<NavigationFilter> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("Suggestion_DataImpl", "getAggregatePageByType(), callback == null");
        } else if (a(uiCallback) && c(uiCallback)) {
            eqa.a().getAggregatePageByType(i, new DataCallback() { // from class: eth.40
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i2, String str) {
                    LogUtil.b("Suggestion_DataImpl", "getAggregatePageByType errorCode=", Integer.valueOf(i2));
                    uiCallback.onFailure(i2, str);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    if (jSONObject == null) {
                        uiCallback.onFailure(-1, "data == null");
                        return;
                    }
                    try {
                        uiCallback.onSuccess((NavigationFilter) new Gson().fromJson(jSONObject.getString("aggregatePage"), NavigationFilter.class));
                    } catch (JsonSyntaxException | JSONException unused) {
                        LogUtil.b("Suggestion_DataImpl", "NavigationFilter Json Exception");
                        uiCallback.onFailure(-1, "NavigationFilter Json Exception");
                    }
                }
            });
        }
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public DataSync getSyncRecord(long j) {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "getSyncRecord getAccountInfo == null");
            return null;
        }
        return ett.i().o().c(accountInfo, j);
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void delSyncRecord(DataSync dataSync) {
        ett.i().o().e(dataSync);
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public boolean isBeyondSyncTimes(DataSync dataSync) {
        return LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011) != null && ett.i().o().a(dataSync);
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void syncData() {
        LogUtil.a("Suggestion_DataImpl", "syncData()");
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (accountInfo != null && ett.i().o().e(accountInfo) > 0) {
            etn.b();
        }
        LogUtil.a("Suggestion_DataImpl", "syncData() end");
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void getFitnessCourseTopicList(int i, final UiCallback<List<Topic>> uiCallback) {
        if (uiCallback == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "getFitnessCourseTopicList(), callback == null");
            return;
        }
        LogUtil.a("Suggestion_DataImpl", "getFitnessCourseTopicList pageNum = ", Integer.valueOf(i));
        String huidOrDefault = LoginInit.getInstance(BaseApplication.getContext()).getHuidOrDefault();
        if (huidOrDefault == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "huid == null");
            uiCallback.onFailure(this.c, 20001, ResultUtil.d(20001));
        } else if (CommonUtil.bu()) {
            c(i, uiCallback, huidOrDefault);
        } else if (b()) {
            eqa.a().queryTopicList(i, new DataCallback() { // from class: eth.36
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i2, String str) {
                    ReleaseLogUtil.c("Suggestion_DataImpl", "getFitnessCourseTopicList: errorCode=", Integer.valueOf(i2));
                    uiCallback.onFailure(eth.this.c, i2, str);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    List<Topic> c = mnf.c(jSONObject);
                    ReleaseLogUtil.e("Suggestion_DataImpl", "topicList num:", Integer.valueOf(c.size()));
                    uiCallback.onSuccess(eth.this.c, c);
                }
            });
        } else {
            uiCallback.onFailure(this.c, 20001, ResultUtil.d(20001));
        }
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void getFitnessCourseTopicList(int i, int i2, UiCallback<List<Topic>> uiCallback) {
        if (uiCallback == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "getFitnessCourseTopicList(), callback == null");
            return;
        }
        LogUtil.c("Suggestion_DataImpl", "getFitnessCourseTopicList pageNum = ", Integer.valueOf(i2));
        LogUtil.a("Suggestion_DataImpl", "getFitnessCourseTopicList courseCategory = ", Integer.valueOf(i));
        String huidOrDefault = LoginInit.getInstance(BaseApplication.getContext()).getHuidOrDefault();
        if (huidOrDefault == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "huid == null");
            uiCallback.onFailure(this.c, 20001, ResultUtil.d(20001));
        } else if (CommonUtil.bu()) {
            c(i2, uiCallback, huidOrDefault);
        } else if (b()) {
            d(i, i2, uiCallback, huidOrDefault);
        } else {
            uiCallback.onFailure(this.c, 20001, ResultUtil.d(20001));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final int i, final int i2, final UiCallback<List<Topic>> uiCallback, final String str) {
        eqa.a().queryTopicList(i, i2, new DataCallback() { // from class: eth.43
            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onFailure(int i3, String str2) {
                ReleaseLogUtil.c("Suggestion_DataImpl", "getFitnessCourseTopicList: errorCode=", Integer.valueOf(i3));
                uiCallback.onFailure(eth.this.c, i3, str2);
            }

            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onSuccess(JSONObject jSONObject) {
                LogUtil.c("Suggestion_DataImpl", "queryTopicList():", jSONObject);
                List<Topic> c = mnf.c(jSONObject);
                ReleaseLogUtil.e("Suggestion_DataImpl", "topicList num:", Integer.valueOf(c.size()));
                if (koq.b(c)) {
                    uiCallback.onSuccess(eth.this.c, c);
                    return;
                }
                ArrayList arrayList = new ArrayList();
                for (Topic topic : c) {
                    if (topic != null && topic.getCourseCategory() == i) {
                        arrayList.add(topic);
                    }
                }
                if (koq.b(arrayList)) {
                    eth.this.d(i, i2 + 1, uiCallback, str);
                } else {
                    uiCallback.onSuccess(eth.this.c, arrayList);
                }
            }
        });
    }

    private void c(int i, UiCallback<List<Topic>> uiCallback, String str) {
        if (uiCallback == null) {
            LogUtil.b("Suggestion_DataImpl", "getFitnessCourseTopicListForStoreDemoVersion(), callback == null");
            return;
        }
        List<Topic> arrayList = new ArrayList<>();
        if (arrayList.size() == 0) {
            String e2 = moh.e(moh.e("suggestion", "recommend_three_course.txt"));
            LogUtil.a("Suggestion_DataImpl", "recommendThreeCourse = ", e2);
            try {
                arrayList = mnf.c(new JSONObject(e2));
            } catch (JSONException e3) {
                LogUtil.b("Suggestion_DataImpl", "parse fileUrlJson exception, ", LogAnonymous.b((Throwable) e3));
            }
            String e4 = moh.e(moh.e("suggestion", "fitworkouts.txt"));
            LogUtil.a("Suggestion_DataImpl", "workoutStr = ", e4);
            Iterator it = new ArrayList(moj.b(e4, FitWorkout[].class)).iterator();
            while (it.hasNext()) {
                ett.i().l().b(str, (FitWorkout) it.next());
            }
        }
        uiCallback.onSuccess(this.c, arrayList);
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void getWorkoutsByTopicId(int i, int i2, final UiCallback<List<FitWorkout>> uiCallback) {
        if (uiCallback == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "getWorkoutsByTopicId(), callback == null");
            return;
        }
        LogUtil.c("Suggestion_DataImpl", "getWorkoutsByTopicId pageNum = ", Integer.valueOf(i), " , topicId = ", Integer.valueOf(i2));
        if (LoginInit.getInstance(BaseApplication.getContext()).getHuidOrDefault() == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "huid == null");
            uiCallback.onFailure(this.c, 20001, ResultUtil.d(20001));
        } else if (b()) {
            ReleaseLogUtil.e("Suggestion_DataImpl", "getWorkoutsByTopicId from cloud");
            eqa.a().getWorkOutsByTopicId(i, i2, new DataCallback() { // from class: eth.45
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i3, String str) {
                    ReleaseLogUtil.c("Suggestion_DataImpl", "getWorkoutsByTopicId: errorCode=", Integer.valueOf(i3));
                    uiCallback.onFailure(eth.this.c, i3, str);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    LogUtil.c("Suggestion_DataImpl", "getWorkoutsByTopicId(): ", jSONObject);
                    List<FitWorkout> b = mnf.b(jSONObject);
                    LogUtil.a("Suggestion_DataImpl", "getWorkoutsByTopicId workouts size:", Integer.valueOf(b.size()));
                    uiCallback.onSuccess(eth.this.c, b);
                }
            });
        } else {
            uiCallback.onFailure(this.c, 20001, ResultUtil.d(20001));
        }
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void getWorkoutsByType(int i, int i2, int i3, final UiCallback<List<FitWorkout>> uiCallback) {
        if (uiCallback == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "getWorkoutsByType(), callback == null");
            return;
        }
        LogUtil.c("Suggestion_DataImpl", "getWorkoutsByType, topicId = ", Integer.valueOf(i), " , workoutType = ", Integer.valueOf(i2));
        if (TextUtils.isEmpty(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011))) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "getWorkoutsByType： getAccountInfo == null");
            uiCallback.onFailure(this.c, 20001, ResultUtil.d(20001));
        } else if (b()) {
            eqa.a().getWorkoutsByType(i, i2, new DataCallback() { // from class: eth.41
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i4, String str) {
                    ReleaseLogUtil.c("Suggestion_DataImpl", "getWorkoutsByType: errorCode=", Integer.valueOf(i4));
                    uiCallback.onFailure(eth.this.c, i4, str);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    uiCallback.onSuccess(eth.this.c, mnf.b(jSONObject));
                }
            });
        } else {
            uiCallback.onFailure(this.c, 20001, ResultUtil.d(20001));
        }
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void getTrainStatistics(int i, final UiCallback<TrainStatistics> uiCallback) {
        LogUtil.c("Suggestion_DataImpl", "getTrainStatistics type = ", Integer.valueOf(i));
        if (uiCallback == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "getTrainStatistics() callback == null");
            return;
        }
        if (CommonUtil.bu()) {
            d(i, uiCallback);
        }
        final String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "getOperationPageLocal(): getAccountInfo == null");
            uiCallback.onFailure(20001, ResultUtil.d(20001));
        } else {
            eqa.a().queryTrainStatistics(i, new DataCallback() { // from class: eth.42
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i2, String str) {
                    JSONObject jSONObject;
                    try {
                        jSONObject = new JSONObject().put("userTrainStatisticsBean", new JSONObject(ash.b("trainStatistics_" + accountInfo)));
                    } catch (JSONException e2) {
                        LogUtil.b("Suggestion_DataImpl", LogAnonymous.b((Throwable) e2));
                        jSONObject = null;
                    }
                    uiCallback.onSuccess(eth.this.c, ffq.g(jSONObject));
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    eth.this.b(jSONObject, accountInfo, (UiCallback<TrainStatistics>) uiCallback);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(JSONObject jSONObject, String str, UiCallback<TrainStatistics> uiCallback) {
        if (uiCallback == null || TextUtils.isEmpty(str)) {
            LogUtil.b("Suggestion_DataImpl", "doGetTrainStatistics() callback == null || accountInfo == null");
            return;
        }
        TrainStatistics g = ffq.g(jSONObject);
        TrainStatistics a2 = ffq.a(ash.b("trainStatistics_" + str));
        if (a2.acquireDuration() > g.acquireDuration()) {
            g.saveDuration(a2.acquireDuration());
        }
        if (a2.acquireCalorie() > g.acquireCalorie()) {
            g.saveCalorie(a2.acquireCalorie());
        }
        if (a2.acquireTotalTimes() > g.acquireTotalTimes()) {
            g.saveTotalTimes(a2.acquireTotalTimes());
        }
        ash.a("trainStatistics_" + str, ffq.e(g));
        uiCallback.onSuccess(this.c, g);
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void getPlanStatistics(final int i, final UiCallback<PlanStatistics> uiCallback) {
        LogUtil.a("Suggestion_DataImpl", "getPlanStatistics type = ", Integer.valueOf(i));
        if (uiCallback == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "getPlanStatistics() callback == null");
            return;
        }
        final String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "getOperationPageLocal(): getAccountInfo == null");
            uiCallback.onFailure(20001, ResultUtil.d(20001));
        } else {
            if (!eve.e()) {
                ReleaseLogUtil.c("Suggestion_DataImpl", "getPlanStatistics is oversea");
                if (CommonUtil.bu()) {
                    uiCallback.onSuccess(this.c, e(accountInfo, i));
                    return;
                } else {
                    uiCallback.onFailure(99, "this version is oversea version");
                    return;
                }
            }
            eqa.a().queryPlanStatistics(i, new DataCallback() { // from class: eth.49
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i2, String str) {
                    ReleaseLogUtil.c("Suggestion_DataImpl", "queryPlanStatistics fail errorCode = ", Integer.valueOf(i2));
                    uiCallback.onSuccess(eth.this.c, eth.this.e(accountInfo, i));
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    LogUtil.a("Suggestion_DataImpl", "queryPlanStatistics success");
                    uiCallback.onSuccess(eth.this.c, eth.this.a(jSONObject, accountInfo, i));
                }
            });
        }
    }

    public void d(int i, final UiCallback<TrainStatistics> uiCallback) {
        if (uiCallback == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "getTrainStatisticsLocalForStoreDemo() callback == null");
        } else if (TextUtils.isEmpty(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011))) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "getTrainStatisticsLocalForStoreDemo getAccountInfo is null");
            uiCallback.onFailure(20001, ResultUtil.d(20001));
        } else {
            FitnessHistoryModel.getInstance().acquireDetailFitnessRecord(new kwy.a().a(0L).e(System.currentTimeMillis()).d(), new IBaseResponseCallback() { // from class: etg
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i2, Object obj) {
                    eth.this.c(uiCallback, i2, obj);
                }
            });
        }
    }

    /* synthetic */ void c(UiCallback uiCallback, int i, Object obj) {
        List list = koq.e(obj, WorkoutRecord.class) ? (List) obj : null;
        TrainStatistics trainStatistics = new TrainStatistics();
        if (koq.b(list)) {
            LogUtil.a("Suggestion_DataImpl", "fitnessHistory is null");
        } else {
            Iterator it = list.iterator();
            long j = 0;
            long j2 = 0;
            long j3 = 0;
            while (it.hasNext()) {
                if (((WorkoutRecord) it.next()) != null) {
                    j2 += gic.e((Object) moe.i(moe.b(r6.acquireActualCalorie())));
                    j3 += r6.getDuration();
                    j++;
                }
            }
            trainStatistics.saveTotalTimes(j);
            trainStatistics.saveCalorie(j2);
            trainStatistics.saveDuration(j3);
        }
        uiCallback.onSuccess(this.c, trainStatistics);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:13:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0038  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.huawei.health.plan.model.PlanStatistics e(java.lang.String r7, int r8) {
        /*
            r6 = this;
            r0 = 0
            java.lang.String r0 = r6.d(r7, r0)
            r1 = 1
            java.lang.String r7 = r6.d(r7, r1)
            r1 = 0
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch: org.json.JSONException -> L19
            r2.<init>(r0)     // Catch: org.json.JSONException -> L19
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch: org.json.JSONException -> L17
            r0.<init>(r7)     // Catch: org.json.JSONException -> L17
            r1 = r0
            goto L28
        L17:
            r7 = move-exception
            goto L1b
        L19:
            r7 = move-exception
            r2 = r1
        L1b:
            java.lang.String r7 = health.compact.a.LogAnonymous.b(r7)
            java.lang.Object[] r7 = new java.lang.Object[]{r7}
            java.lang.String r0 = "Suggestion_DataImpl"
            com.huawei.hwlogsmodel.LogUtil.b(r0, r7)
        L28:
            com.huawei.health.plan.model.PlanStatistics r7 = defpackage.ffq.b(r2)
            com.huawei.health.plan.model.PlanStatistics r0 = defpackage.ffq.b(r1)
            com.huawei.health.plan.model.PlanStatistics r1 = new com.huawei.health.plan.model.PlanStatistics
            r1.<init>()
            r2 = 4
            if (r8 != r2) goto L5d
            long r2 = r7.acquireDuration()
            long r4 = r0.acquireDuration()
            long r2 = r2 + r4
            r1.saveDuration(r2)
            long r2 = r7.acquireCalorie()
            long r4 = r0.acquireCalorie()
            long r2 = r2 + r4
            r1.saveCalorie(r2)
            int r7 = r7.acquireTotalPlan()
            int r8 = r0.acquireTotalPlan()
            int r7 = r7 + r8
            r1.saveTotalPlan(r7)
            goto L66
        L5d:
            r2 = 2
            if (r8 != r2) goto L61
            goto L67
        L61:
            r7 = 3
            if (r8 != r7) goto L66
            r7 = r0
            goto L67
        L66:
            r7 = r1
        L67:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.eth.e(java.lang.String, int):com.huawei.health.plan.model.PlanStatistics");
    }

    private String d(String str, int i) {
        return ash.b("planStatistics_" + str + "_type_" + i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PlanStatistics a(JSONObject jSONObject, String str, int i) {
        eth ethVar;
        String str2;
        PlanStatistics planStatistics = new PlanStatistics();
        if (jSONObject == null) {
            return planStatistics;
        }
        double d = 0.0d;
        int i2 = 0;
        long j = 0;
        long j2 = 0;
        for (PlanStatistics planStatistics2 : ffq.d(jSONObject)) {
            if (planStatistics2 == null) {
                LogUtil.h("Suggestion_DataImpl", "planStat == null");
            } else {
                if (i == 4) {
                    j2 += planStatistics2.acquireCalorie();
                    j += planStatistics2.acquireDuration();
                    d += planStatistics2.getTotalDistance();
                    i2 += planStatistics2.acquireTotalPlan();
                } else if (i == 2 && planStatistics2.acquireType() == 0) {
                    long acquireCalorie = planStatistics2.acquireCalorie();
                    long acquireDuration = planStatistics2.acquireDuration();
                    double totalDistance = planStatistics2.getTotalDistance();
                    ethVar = this;
                    str2 = str;
                    i2 = planStatistics2.acquireTotalPlan();
                    d = totalDistance;
                    j2 = acquireCalorie;
                    j = acquireDuration;
                    ethVar.b(str2, planStatistics2, i);
                } else if (i == 3 && planStatistics2.acquireType() == 1) {
                    j2 = planStatistics2.acquireCalorie();
                    j = planStatistics2.acquireDuration();
                    i2 = planStatistics2.acquireTotalPlan();
                }
                ethVar = this;
                str2 = str;
                ethVar.b(str2, planStatistics2, i);
            }
        }
        planStatistics.saveDuration(j);
        planStatistics.saveCalorie(j2);
        planStatistics.saveTotalPlan(i2);
        planStatistics.setTotalDistance(d);
        LogUtil.a("Suggestion_DataImpl", "totalDuration = ", Long.valueOf(j), " totalCalorie = ", Long.valueOf(j2), " totalPlan = ", Integer.valueOf(i2));
        return planStatistics;
    }

    private void b(String str, PlanStatistics planStatistics, int i) {
        ash.a("planStatistics_" + str + "_type_" + planStatistics.acquireType(), ffq.e(planStatistics));
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void savetrainstatis(WorkoutRecord workoutRecord) {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo) || workoutRecord == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "savetrainstatis(): getAccountInfo == null");
            return;
        }
        if (TextUtils.isEmpty(workoutRecord.acquireWorkoutId()) || TextUtils.isEmpty(workoutRecord.acquireWorkoutName())) {
            LogUtil.h("Suggestion_DataImpl", "savetrainstatis, record.acquireWorkoutId = ", workoutRecord.acquireWorkoutId(), ", record.acquireWorkoutName = ", workoutRecord.acquireWorkoutName());
            return;
        }
        TrainStatistics a2 = ffq.a(ash.b("trainStatistics_" + accountInfo));
        a2.saveDuration(a2.acquireDuration() + ((long) workoutRecord.getDuration()));
        a2.saveTotalTimes(a2.acquireTotalTimes() + 1);
        a2.saveCalorie(a2.acquireCalorie() + ((long) workoutRecord.acquireActualCalorie()));
        ash.a("trainStatistics_" + accountInfo, ffq.e(a2));
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public List<Map<String, Object>> getRecordsByDateRange(Date date, Date date2) {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            return null;
        }
        int a2 = ggl.a(date);
        int a3 = ggl.a(date2);
        LogUtil.a("Suggestion_DataImpl", "getRecordsByDateRange", Integer.valueOf(a2), Integer.valueOf(a3));
        return ett.i().k().c(accountInfo, a2, a3);
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void insertCalorieData(Context context, WorkoutRecord workoutRecord) {
        if (workoutRecord == null || context == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "insertCalorieData(), record == null");
            return;
        }
        HiHealthData hiHealthData = new HiHealthData(40054);
        hiHealthData.setDeviceUuid(FoundationCommonUtil.getAndroidId(context));
        hiHealthData.setEndTime(workoutRecord.acquireExerciseTime());
        hiHealthData.setStartTime(workoutRecord.acquireExerciseTime());
        float activeCalorie = workoutRecord.getActiveCalorie();
        float acquireActualCalorie = workoutRecord.acquireActualCalorie();
        if (activeCalorie <= 0.0f || activeCalorie >= acquireActualCalorie) {
            activeCalorie = acquireActualCalorie;
        }
        hiHealthData.setValue(activeCalorie);
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        hiDataInsertOption.addData(hiHealthData);
        HiHealthManager.d(context).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: eth.50
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj) {
                LogUtil.a("Suggestion_DataImpl", "insertCalorieDate errorCode:", Integer.valueOf(i));
            }
        });
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void insertCaloriePoints(Context context, Map<Long, HiHealthData> map) {
        if (map == null || map.isEmpty()) {
            LogUtil.b("Suggestion_DataImpl", "points are empty");
            return;
        }
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        for (Map.Entry<Long, HiHealthData> entry : map.entrySet()) {
            if (entry != null && entry.getValue() != null) {
                HiHealthData value = entry.getValue();
                HiHealthData hiHealthData = new HiHealthData(20010);
                hiHealthData.setStartTime(value.getStartTime());
                hiHealthData.setEndTime(value.getEndTime());
                hiHealthData.setValue(0);
                hiHealthData.setDeviceUuid(value.getDeviceUuid());
                hiDataInsertOption.addData(value);
                hiDataInsertOption.addData(hiHealthData);
            }
        }
        HiHealthManager.d(context).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: eth.48
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj) {
                LogUtil.a("Suggestion_DataImpl", "insertCalorieData errorCode:", Integer.valueOf(i));
                if (i == 0) {
                    HiHealthNativeApi.a(BaseApplication.getContext()).synCloud(eth.this.d(), null);
                    LogUtil.a("Suggestion_DataImpl", "save fitness calorie point success syncCloud begin...");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public HiSyncOption d() {
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(0);
        hiSyncOption.setSyncDataType(20000);
        hiSyncOption.setSyncMethod(2);
        return hiSyncOption;
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void insertFitnessIntensityData(Context context, List<Long> list) {
        if (this.f12249a == -1) {
            this.f12249a = SportIntensityUtil.b().f();
        }
        if (list == null || list.size() < this.f12249a || context == null) {
            return;
        }
        StringBuilder sb = new StringBuilder(10);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm''ss\"");
        for (Long l : list) {
            String format = simpleDateFormat.format(l);
            sb.append("-time:");
            sb.append(l);
            sb.append("-formatTime:");
            sb.append(format);
        }
        LogUtil.a("Suggestion_DataImpl", "insertFitnessIntensityData:", sb);
        String androidId = FoundationCommonUtil.getAndroidId(context);
        ArrayList arrayList = new ArrayList(list.size());
        for (Long l2 : list) {
            HiHealthData hiHealthData = new HiHealthData();
            hiHealthData.setDeviceUuid(androidId);
            hiHealthData.setTimeInterval(l2.longValue(), l2.longValue() + 60000);
            hiHealthData.setType(7);
            hiHealthData.setValue(4);
            arrayList.add(hiHealthData);
        }
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        hiDataInsertOption.setDatas(arrayList);
        HiHealthNativeApi.a(context).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: eth.47
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj) {
                LogUtil.a("Suggestion_DataImpl", "insertFitnessIntensityData, onResult(), errorCode = ", Integer.valueOf(i), " obj = ", obj);
            }
        });
    }

    private void b(long j, String str, String str2, UiCallback<Boolean> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("Suggestion_DataImpl", "downloadFitnessAudioXml(), callback == null");
        } else {
            eqa.a().getMultiLanguage(j, str2, new e(str, uiCallback));
        }
    }

    class e extends DataCallback {

        /* renamed from: a, reason: collision with root package name */
        private final String f12293a;
        private final UiCallback<Boolean> d;

        e(String str, UiCallback<Boolean> uiCallback) {
            this.f12293a = str;
            this.d = uiCallback;
        }

        @Override // com.huawei.basefitnessadvice.callback.DataCallback
        public void onFailure(int i, String str) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "downloadFitnessAudioXml onFailure: errorCode=", Integer.valueOf(i));
            String i2 = squ.i(squ.h(this.f12293a));
            if (i2 != null) {
                eth.this.c(i2, this.f12293a, this.d);
            } else {
                this.d.onFailure(i, str);
            }
        }

        @Override // com.huawei.basefitnessadvice.callback.DataCallback
        public void onSuccess(JSONObject jSONObject) {
            LogUtil.c("Suggestion_DataImpl", "downloadFitnessAudioXml onSuccess data = ", jSONObject);
            List<LangFile> c = ffq.c(jSONObject);
            ArrayList arrayList = new ArrayList();
            long j = 0;
            boolean z = false;
            for (LangFile langFile : c) {
                long acquireVersion = langFile.acquireVersion();
                if (j < acquireVersion) {
                    j = acquireVersion;
                }
                String acquireLangName = langFile.acquireLangName();
                if (langFile.acquireLangUrl() != null && acquireLangName != null) {
                    if (acquireLangName.startsWith("BPF001_EN")) {
                        arrayList.add(langFile);
                    }
                    String a2 = squ.a();
                    if (acquireLangName.startsWith(a2)) {
                        ReleaseLogUtil.e("Suggestion_DataImpl", "downloadFitnessAudioXml acquireLangName:", acquireLangName, "acquireLocaleAudioTag:", a2);
                        squ.r(a2);
                        eth.this.a(langFile);
                        z = true;
                    }
                }
            }
            eth.this.a(arrayList, z);
            if (j > squ.m(squ.d())) {
                squ.a(squ.d(), j);
            }
            String str = this.f12293a;
            ReleaseLogUtil.e("Suggestion_DataImpl", "come from = ", str, " getFileNameXml = ", squ.h(str));
            String i = squ.i(squ.h(this.f12293a));
            LogUtil.a("Suggestion_DataImpl", "multiLanguageUrl = ", i);
            eth.this.c(i, this.f12293a, this.d);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<LangFile> list, boolean z) {
        if (z) {
            return;
        }
        ReleaseLogUtil.e("Suggestion_DataImpl", "downloadFitnessAudioXml isMatchLang false");
        squ.r("BPF001_EN");
        for (LangFile langFile : list) {
            if (langFile != null) {
                a(langFile);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(LangFile langFile) {
        squ.a(langFile.acquireLangName(), langFile.getTimbre(), langFile.acquireLangUrl());
        squ.c(langFile.acquireLangUrl(), true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final String str, String str2, final UiCallback<Boolean> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("Suggestion_DataImpl", "downloadFitnessAudioXml(), callback == null");
            return;
        }
        if (str != null) {
            if (squ.g(str)) {
                String f = squ.f(str2);
                File file = new File(f);
                if (file.exists()) {
                    LogUtil.a("Suggestion_DataImpl", "downloadFitnessAudioXml file delete: ", Boolean.valueOf(file.delete()));
                }
                eqa.a().downloadFile(str, f, new DataCallback() { // from class: eth.46
                    @Override // com.huawei.basefitnessadvice.callback.DataCallback
                    public void onFailure(int i, String str3) {
                        ReleaseLogUtil.c("Suggestion_DataImpl", "downloadFitnessAudioXml downloadFile onFailure: errorCode=", Integer.valueOf(i));
                        uiCallback.onFailure(i, str3);
                    }

                    @Override // com.huawei.basefitnessadvice.callback.DataCallback
                    public void onSuccess(JSONObject jSONObject) {
                        LogUtil.a("Suggestion_DataImpl", "downloadFitnessAudioXml downloadFile success");
                        squ.c(str, false);
                        uiCallback.onSuccess(true);
                    }
                });
                return;
            }
            uiCallback.onSuccess(true);
            return;
        }
        uiCallback.onSuccess(true);
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public long getUseCacheSize() {
        return squ.j();
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void delUseCache(final UiCallback<Boolean> uiCallback) {
        LocalBroadcastManager.getInstance(arx.b()).registerReceiver(new BroadcastReceiver() { // from class: eth.53
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (context == null) {
                    UiCallback uiCallback2 = uiCallback;
                    if (uiCallback2 != null) {
                        uiCallback2.onFailure(eth.this.c, -1, "context == null");
                    }
                    context = arx.b();
                }
                LogUtil.h("Suggestion_DataImpl", "delUseCache onReceive Action");
                LocalBroadcastManager.getInstance(context).unregisterReceiver(this);
                UiCallback uiCallback3 = uiCallback;
                if (uiCallback3 != null) {
                    uiCallback3.onSuccess(eth.this.c, true);
                }
            }
        }, new IntentFilter("com.huawei.health.suggestion.config.MoveService.DEL_ACTION"));
        MoveService.b(arx.b(), 2);
    }

    private boolean d(String str) {
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            return false;
        }
        LogUtil.b("Suggestion_DataImpl", "switchToRunPlanReport, getPlanProgress : planApi is null.");
        planApi.setPlanType(0);
        Plan plan = planApi.getjoinedPlanById(str);
        return plan != null && plan.getPlanCategory() == 0 && Utils.o();
    }

    private boolean b() {
        return Utils.i();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(String str, String str2) {
        return str + "_" + str2;
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void getActions(final List<String> list, final UiCallback<List<TrainAction>> uiCallback) {
        LogUtil.a("Suggestion_DataImpl", "getActions actionIds = ", list);
        if (uiCallback == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "getActions(), callback == null");
            return;
        }
        UserProfileMgrApi e2 = e("getActions : userProfileMgrApi is null.");
        if (e2 == null) {
            return;
        }
        e2.getUserInfo(new BaseResponseCallback() { // from class: etf
            @Override // com.huawei.health.userprofilemgr.model.BaseResponseCallback
            public final void onResponse(int i, Object obj) {
                eth.this.c(uiCallback, list, i, (UserInfomation) obj);
            }
        });
    }

    /* synthetic */ void c(UiCallback uiCallback, List list, int i, UserInfomation userInfomation) {
        if (userInfomation == null) {
            LogUtil.h("Suggestion_DataImpl", "requestDailyData getUserInfo failed");
            uiCallback.onFailure(this.c, 20001, ResultUtil.d(20001));
            return;
        }
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "getActions(): userInfo == null ||TextUtils.isEmpty(userId)");
            uiCallback.onFailure(this.c, 20001, ResultUtil.d(20001));
        } else {
            c((List<String>) list, (UiCallback<List<TrainAction>>) uiCallback, accountInfo, userInfomation.getGender() == 0 ? userInfomation.getGender() : 1);
        }
    }

    private void c(List<String> list, final UiCallback<List<TrainAction>> uiCallback, final String str, int i) {
        if (!b() || !CommonUtil.aa(arx.b())) {
            uiCallback.onFailure(this.c, ResultUtil.ResultCode.NO_NET, ResultUtil.d(ResultUtil.ResultCode.NO_NET));
        } else {
            eqa.a().getTrainActions(list, i, new DataCallback() { // from class: eth.55
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i2, String str2) {
                    ReleaseLogUtil.c("Suggestion_DataImpl", "getTrainActions(): errorCode=", Integer.valueOf(i2));
                    uiCallback.onFailure(eth.this.c, i2, str2);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    if (jSONObject == null) {
                        LogUtil.b("Suggestion_DataImpl", "getTrainActions onSuccess data is null.");
                        uiCallback.onSuccess(eth.this.c, null);
                    } else {
                        LogUtil.h("Suggestion_DataImpl", "getTrainActions onSuccess");
                        uiCallback.onSuccess(eth.this.c, ffq.b(str, jSONObject.optJSONArray("actionInfoList")));
                    }
                }
            });
        }
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void deleteAllPlanRecords() {
        ett.i().t().d();
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            ReleaseLogUtil.d("Suggestion_DataImpl", "deleteAllPlanRecords getAccountInfo is null.");
            return;
        }
        ash.d("planStatistics_" + accountInfo + "_type_0");
        this.e.remove(a("plan", accountInfo));
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void getWorkoutList(int i, int i2, int i3, int i4, final UiCallback<List<FitWorkout>> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("Suggestion_DataImpl", "getWorkoutList(), callback == null");
        } else if (!b()) {
            uiCallback.onFailure(this.c, -4, ResultUtil.d(-4));
        } else {
            final ArrayList arrayList = new ArrayList(10);
            eqa.a().getWorkoutList(i, i2, i3, i4, new DataCallback() { // from class: eth.54
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i5, String str) {
                    LogUtil.b("Suggestion_DataImpl", "getWorkoutList errorCode=", Integer.valueOf(i5));
                    uiCallback.onFailure(i5, str);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    if (jSONObject == null) {
                        uiCallback.onFailure(-1, "data == null");
                        return;
                    }
                    List<FitWorkout> b = mnf.b(jSONObject);
                    if (koq.b(b)) {
                        uiCallback.onFailure(-1, "workouts == null");
                        return;
                    }
                    LogUtil.a("Suggestion_DataImpl", "getWorkoutList From Cloud :", Integer.valueOf(b.size()));
                    arrayList.clear();
                    arrayList.addAll(b);
                    uiCallback.onSuccess(eth.this.c, arrayList);
                }
            });
        }
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void getWorkoutRecommendList(final UiCallback<List<FitWorkout>> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("Suggestion_DataImpl", "getWorkoutRecommendList(), callback == null");
            return;
        }
        final ArrayList arrayList = new ArrayList();
        List<FitWorkout> b = eum.b();
        if (b != null && b.size() > 0) {
            uiCallback.onSuccess(b);
            return;
        }
        String c = eum.c();
        if (CommonUtil.aa(arx.b())) {
            eqa.a().getFitnessWorkoutRecommendList(c, new DataCallback() { // from class: eth.51
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i, String str) {
                    LogUtil.b("Suggestion_DataImpl", "getFitnessWorkoutRecommendList errorCode=", Integer.valueOf(i));
                    uiCallback.onFailure(i, str);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    if (jSONObject == null) {
                        LogUtil.b("Suggestion_DataImpl", "recommend data is null");
                        uiCallback.onFailure(-1, "Recommend data == null");
                    }
                    List<FitWorkout> b2 = mnf.b(jSONObject);
                    if (koq.b(b2)) {
                        LogUtil.b("Suggestion_DataImpl", "parse recommend data is null");
                        uiCallback.onFailure(-1, "RecommendWorkouts == null");
                    }
                    eum.b(b2);
                    arrayList.addAll(b2);
                    uiCallback.onSuccess(arrayList);
                }
            });
        } else {
            uiCallback.onSuccess(b);
        }
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void getRecWorkoutByCourseTypeList(final int i, final UiCallback<List<FitWorkout>> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("Suggestion_DataImpl", "getWorkoutRecommendList(), callback == null");
            return;
        }
        if (CommonUtil.bu()) {
            uiCallback.onSuccess(a());
            return;
        }
        final ArrayList arrayList = new ArrayList();
        List<FitWorkout> d = eum.d(i);
        if (d != null && d.size() > 0) {
            uiCallback.onSuccess(d);
        } else if (CommonUtil.aa(arx.b())) {
            eqa.a().getFitnessRecWorkoutByCourseTypeList(i, new DataCallback() { // from class: eth.52
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i2, String str) {
                    LogUtil.b("Suggestion_DataImpl", "getFitnessWorkoutRecommendList errorCode=", Integer.valueOf(i2));
                    uiCallback.onFailure(i2, str);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    if (jSONObject == null) {
                        LogUtil.b("Suggestion_DataImpl", "recommend data is null");
                        uiCallback.onFailure(-1, "Recommend data == null");
                    }
                    List<FitWorkout> b = mnf.b(jSONObject);
                    if (koq.b(b)) {
                        LogUtil.b("Suggestion_DataImpl", "parse recommend data is null");
                        uiCallback.onFailure(-1, "RecommendWorkouts == null");
                    }
                    eum.c(b, i);
                    arrayList.clear();
                    arrayList.addAll(b);
                    uiCallback.onSuccess(arrayList);
                }
            });
        } else {
            uiCallback.onSuccess(d);
        }
    }

    private List<FitWorkout> a() {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONArray(moh.e(moh.e("suggestion", "recommend_courses.txt")));
            for (int i = 0; i < jSONArray.length(); i++) {
                FitWorkout b = mnf.b(arx.a(), jSONArray.getJSONObject(i));
                if (b != null) {
                    LogUtil.a("Suggestion_DataImpl", "parse fitworkouts id:", b.acquireName(), b.acquireId());
                    arrayList.add(b);
                }
            }
        } catch (JSONException e2) {
            LogUtil.b("Suggestion_DataImpl", "parse fitworkouts exception, ", LogAnonymous.b((Throwable) e2));
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ett.i().l().b(LoginInit.getInstance(BaseApplication.getContext()).getHuidOrDefault(), (FitWorkout) it.next());
        }
        return arrayList;
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public List<WorkoutRecord> getWorkoutRecords(long j, long j2) {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            LogUtil.b("Suggestion_DataImpl", "getWorkoutRecords(), accountInfo == null");
            return new ArrayList();
        }
        return euj.c(accountInfo, j, j2);
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void getLongVideoInfo(ffl fflVar, final UiCallback<LongVideoInfo> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("Suggestion_DataImpl", "getLongVideoInfo(), callback == null");
        } else {
            eqa.a().getLongVideoInfo(fflVar, new DataCallback() { // from class: eth.56
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i, String str) {
                    LogUtil.b("Suggestion_DataImpl", "getLongVideoInfo() errorCode=", Integer.valueOf(i));
                    uiCallback.onFailure(eth.this.c, i, str);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    LogUtil.c("Suggestion_DataImpl", "getLongVideoInfo success");
                    LongVideoInfo e2 = ffq.e(jSONObject);
                    if (e2 != null) {
                        LogUtil.a("Suggestion_DataImpl", "onSuccess, workout from cloud is not null");
                        uiCallback.onSuccess(eth.this.c, e2);
                    } else {
                        uiCallback.onFailure(eth.this.c, 9999, ResultUtil.d(9999));
                    }
                }
            });
        }
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void getRecommedPlans(final UiCallback<List<mmw>> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("Suggestion_DataImpl", "getRecommedPlans callback == null");
        } else {
            asc.e().b(new Runnable() { // from class: etk
                @Override // java.lang.Runnable
                public final void run() {
                    eth.this.e(uiCallback);
                }
            });
        }
    }

    /* synthetic */ void e(UiCallback uiCallback) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i <= 3; i++) {
            mmw e2 = evf.e(i);
            if (e2 != null) {
                arrayList.add(e2);
            }
        }
        uiCallback.onSuccess(this.c, arrayList);
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void updatePlan(UserInfoBean userInfoBean, final int i, final UiCallback<Plan> uiCallback) {
        if (uiCallback == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "updatePlan() callback == null");
        } else {
            if (c(uiCallback, "createIntelligentRunPlan")) {
                return;
            }
            LogUtil.a("Suggestion_DataImpl", "updatePlan runPlanUserInfoBean:", new Gson().toJson(userInfoBean));
            eqa.a().updatePlan(userInfoBean, i, new DataCallback() { // from class: eth.58
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i2, String str) {
                    ReleaseLogUtil.c("Suggestion_DataImpl", "createIntelligentRunPlan fail errorCode = ", Integer.valueOf(i2));
                    uiCallback.onFailure(eth.this.c, i2, str);
                    OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_UPDATE_PLAN_60010006.value(), i2);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    LogUtil.a("Suggestion_DataImpl", "updatePlan success week:", Integer.valueOf(i));
                    String optString = jSONObject.optString("intPlanParam");
                    if (!TextUtils.isEmpty(optString)) {
                        LogUtil.a("Suggestion_DataImpl", "AlgorithmInfos : intPlanParam = ", optString);
                    }
                    etl.e(etr.b().getCurrentPlan(), (Boolean) false, -1);
                    etx.b().c();
                    eth.this.b(jSONObject, "planDetail", true, (UiCallback<Plan>) uiCallback);
                }
            });
        }
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void createIntelligentRunPlan(CreateRunPlanParams createRunPlanParams, final UiCallback<Plan> uiCallback) {
        if (uiCallback == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "createIntelligentRunPlan() callback == null");
        } else {
            if (c(uiCallback, "createIntelligentRunPlan")) {
                return;
            }
            LogUtil.a("Suggestion_DataImpl", "createIntelligentRunPlan runPlanUserInfoBean:", new Gson().toJson(createRunPlanParams));
            eqa.a().createPlan(createRunPlanParams, new DataCallback() { // from class: eth.57
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i, String str) {
                    ReleaseLogUtil.c("Suggestion_DataImpl", "createIntelligentRunPlan fail errorCode = ", Integer.valueOf(i), "errorInfo:", str);
                    if (i == 200010) {
                        etx.b().e();
                    }
                    uiCallback.onFailure(eth.this.c, i, str);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    if (jSONObject == null) {
                        LogUtil.b("Suggestion_DataImpl", "createIntelligentRunPlan onSuccess data is null.");
                        uiCallback.onSuccess(eth.this.c, null);
                        return;
                    }
                    LogUtil.a("Suggestion_DataImpl", "createIntelligentRunPlan success");
                    Plan a2 = ffm.a(jSONObject, "planDetail");
                    if (StringUtils.g(a2.getTimeZone())) {
                        a2.setTimeZone(ggl.b());
                    }
                    a2.setPlanCategory(1);
                    eth.this.b(a2);
                    uiCallback.onSuccess(eth.this.c, a2);
                    ary.a().e("PLAN_UPDATE");
                }
            });
            a(createRunPlanParams);
        }
    }

    private boolean c(UiCallback<Plan> uiCallback, String str) {
        if (LoginInit.getInstance(BaseApplication.getContext()) == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", str, ": loginInfo == null");
            uiCallback.onFailure(20001, ResultUtil.d(20001));
            return true;
        }
        if (eve.e()) {
            return false;
        }
        ReleaseLogUtil.c("Suggestion_DataImpl", str, " is oversea");
        uiCallback.onFailure(99, "this version is oversea version");
        return true;
    }

    private void a(CreateRunPlanParams createRunPlanParams) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("create_time", System.currentTimeMillis());
            jSONObject.put("movementTimes", d(createRunPlanParams));
            jSONObject.put("matchDate", d(createRunPlanParams.getPlanInfoBean().getEndTime()));
            jSONObject.put("type", 2);
            jSONObject.put(ParsedFieldTag.GOAL, createRunPlanParams.getPlanInfoBean().getPlanType());
            jSONObject.put("excludedDate", b(createRunPlanParams));
            HashMap hashMap = new HashMap(1);
            hashMap.put("data", jSONObject.toString());
            gge.e("1120008", hashMap);
        } catch (JSONException e2) {
            LogUtil.b("Suggestion_DataImpl", "exception = ", LogAnonymous.b((Throwable) e2));
        }
    }

    private String d(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        return ggl.a(calendar.getTime(), "yyyy-MM-dd");
    }

    private int d(CreateRunPlanParams createRunPlanParams) {
        TreeSet treeSet = new TreeSet();
        List<Integer> runDate = createRunPlanParams.getPlanInfoBean().getRunDate();
        List<Integer> exeDate = createRunPlanParams.getPlanInfoBean().getExeDate();
        treeSet.addAll(runDate);
        treeSet.addAll(exeDate);
        return treeSet.size();
    }

    private List<Integer> b(CreateRunPlanParams createRunPlanParams) {
        ArrayList arrayList = new ArrayList(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        List<Integer> runDate = createRunPlanParams.getPlanInfoBean().getRunDate();
        List<Integer> exeDate = createRunPlanParams.getPlanInfoBean().getExeDate();
        arrayList.removeAll(runDate);
        arrayList.removeAll(exeDate);
        return arrayList;
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void addUserDefinedWorkout(final FitWorkout fitWorkout, final UiCallback<FitWorkout> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("Suggestion_DataImpl", "addUserDefinedWorkout(), callback == null");
            return;
        }
        final LoginInit loginInit = LoginInit.getInstance(BaseApplication.getContext());
        if (loginInit.getUsetId() == null) {
            LogUtil.b("Suggestion_DataImpl", "addUserDefinedWorkout() getAccountInfo == null");
            uiCallback.onFailure(20001, ResultUtil.d(20001));
        } else if (CommonUtil.aa(arx.b())) {
            eqa.a().addUserDefinedWorkout(fitWorkout.acquireName(), fitWorkout.acquireDescription(), fitWorkout.getCourseDefineType(), fitWorkout.getCourseActions(), new DataCallback() { // from class: eth.60
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i, String str) {
                    ReleaseLogUtil.c("Suggestion_DataImpl", "addUserDefinedWorkout() errorCode=", Integer.valueOf(i));
                    uiCallback.onFailure(i, str);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    if (jSONObject != null) {
                        LogUtil.a("Suggestion_DataImpl", "addUserDefinedWorkout success.", jSONObject.toString());
                        String optString = jSONObject.optString(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID);
                        long optLong = jSONObject.optLong("createTime");
                        fitWorkout.saveId(optString);
                        fitWorkout.savePublishDate(optLong);
                        fitWorkout.saveModified(optLong);
                        ett.i().l().b(loginInit.getUsetId(), fitWorkout);
                        uiCallback.onSuccess(fitWorkout);
                        ary.a().e("CUSTOM_COURSE_UPDATE");
                        return;
                    }
                    LogUtil.b("Suggestion_DataImpl", "addUserDefinedWorkout(), data == null");
                    uiCallback.onFailure(9999, ResultUtil.d(9999));
                }
            });
        } else {
            uiCallback.onFailure(ResultUtil.ResultCode.NO_NET, ResultUtil.d(ResultUtil.ResultCode.NO_NET));
        }
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void modifyUserDefinedWorkout(final FitWorkout fitWorkout, final UiCallback<FitWorkout> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("Suggestion_DataImpl", "modifyUserDefinedWorkout(), callback == null");
            return;
        }
        final LoginInit loginInit = LoginInit.getInstance(BaseApplication.getContext());
        if (loginInit.getUsetId() == null) {
            LogUtil.b("Suggestion_DataImpl", "modifyUserDefinedWorkout() getAccountInfo == null");
            uiCallback.onFailure(20001, ResultUtil.d(20001));
        } else if (CommonUtil.aa(arx.b())) {
            eqa.a().modifyUserDefinedWorkout(fitWorkout, new DataCallback() { // from class: eth.61
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i, String str) {
                    ReleaseLogUtil.c("Suggestion_DataImpl", "modifyUserDefinedWorkout() errorCode=", Integer.valueOf(i));
                    uiCallback.onFailure(i, str);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    if (jSONObject != null) {
                        LogUtil.a("Suggestion_DataImpl", "modifyUserDefinedWorkout success.", jSONObject.toString());
                        long optLong = jSONObject.optLong(ParsedFieldTag.TASK_MODIFY_TIME);
                        if (optLong != 0) {
                            fitWorkout.saveModified(optLong);
                        }
                        ett.i().l().b(loginInit.getUsetId(), fitWorkout);
                        uiCallback.onSuccess(fitWorkout);
                        ary.a().e("CUSTOM_COURSE_UPDATE");
                        return;
                    }
                    LogUtil.b("Suggestion_DataImpl", "modifyUserDefinedWorkout(), data == null");
                    uiCallback.onFailure(9999, ResultUtil.d(9999));
                }
            });
        } else {
            uiCallback.onFailure(ResultUtil.ResultCode.NO_NET, ResultUtil.d(ResultUtil.ResultCode.NO_NET));
        }
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void deleteUserDefinedWorkout(final String str, int i, final UiCallback<String> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("Suggestion_DataImpl", "deleteUserDefinedWorkout(), callback == null");
            return;
        }
        final LoginInit loginInit = LoginInit.getInstance(BaseApplication.getContext());
        if (loginInit.getUsetId() == null) {
            LogUtil.b("Suggestion_DataImpl", "deleteUserDefinedWorkout() getAccountInfo == null");
            uiCallback.onFailure(20001, ResultUtil.d(20001));
        } else if (CommonUtil.aa(arx.b())) {
            eqa.a().deleteUserDefinedWorkout(str, i, new DataCallback() { // from class: eth.64
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i2, String str2) {
                    ReleaseLogUtil.c("Suggestion_DataImpl", "deleteUserDefinedWorkout errorCode=", Integer.valueOf(i2));
                    if (!TextUtils.isEmpty(ResultUtil.d(i2))) {
                        uiCallback.onFailure(i2, ResultUtil.d(i2));
                    } else {
                        uiCallback.onFailure(i2, str2);
                    }
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    if (jSONObject != null) {
                        LogUtil.a("Suggestion_DataImpl", "deleteUserDefinedWorkout success.", jSONObject.toString());
                        ett.i().l().d(loginInit.getUsetId(), str);
                        uiCallback.onSuccess(null);
                        ary.a().e("CUSTOM_COURSE_UPDATE");
                        return;
                    }
                    LogUtil.b("Suggestion_DataImpl", "modifyUserDefinedWorkout(), data == null");
                    uiCallback.onFailure(9999, ResultUtil.d(9999));
                }
            });
        } else {
            uiCallback.onFailure(ResultUtil.ResultCode.NO_NET, ResultUtil.d(ResultUtil.ResultCode.NO_NET));
        }
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void getFilterTab(String str, final UiCallback<Boolean> uiCallback) {
        if (TextUtils.isEmpty(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011))) {
            LogUtil.b("Suggestion_DataImpl", "getFilterTab(): getAccountInfo()==null");
            uiCallback.onFailure(this.c, 20001, ResultUtil.d(20001));
        } else if (!eve.e()) {
            uiCallback.onFailure(this.c, -4, ResultUtil.d(-4));
        } else if (!CommonUtil.aa(arx.b())) {
            uiCallback.onFailure(this.c, ResultUtil.ResultCode.NO_NET, ResultUtil.d(ResultUtil.ResultCode.NO_NET));
        } else {
            eqa.a().getFilterTab(str, new DataCallback() { // from class: eth.63
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i, String str2) {
                    LogUtil.b("Suggestion_DataImpl", "getFilterTab: errorCode = ", Integer.valueOf(i), "errorInfo = ", str2);
                    uiCallback.onFailure(eth.this.c, i, str2);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    JSONObject optJSONObject;
                    LogUtil.a("Suggestion_DataImpl", "getFilterTab: onSuccess , data = ", jSONObject.toString());
                    JSONArray optJSONArray = jSONObject.optJSONArray("dictionaryLists");
                    uiCallback.onSuccess(eth.this.c, Boolean.valueOf(((optJSONArray == null || optJSONArray.length() <= 0 || (optJSONObject = optJSONArray.optJSONObject(0)) == null) ? 0 : optJSONObject.optInt("display", 0)) == 1));
                }
            });
        }
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void queryAudiosPackageBySeriesId(String str, final UiCallback<List<SleepAudioPackage>> uiCallback) {
        if (c(uiCallback)) {
            eqa.a().queryAudiosPackageBySeriesId(str, new DataCallback() { // from class: eth.62
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i, String str2) {
                    LogUtil.b("Suggestion_DataImpl", "onFailure , errorCode = ", Integer.valueOf(i), " errorInfo = ", str2);
                    uiCallback.onFailure(eth.this.c, i, str2);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    String optString = jSONObject.optString("packageList");
                    if (TextUtils.isEmpty(optString)) {
                        LogUtil.h("Suggestion_DataImpl", "queryAudiosPackageBySeriesId packageList == null");
                        uiCallback.onFailure(eth.this.c, ResultUtil.ResultCode.PARAMETER_INVALID, "queryAudiosPackageBySeriesId packageList == null");
                        return;
                    }
                    LogUtil.a("Suggestion_DataImpl", "queryAudiosPackageBySeriesId packageList = ", optString);
                    try {
                        List list = (List) new Gson().fromJson(optString, new TypeToken<List<SleepAudioPackage>>() { // from class: eth.62.2
                        }.getType());
                        if (list != null) {
                            LogUtil.a("Suggestion_DataImpl", "sleepAudioPackageListSize = ", Integer.valueOf(list.size()));
                            uiCallback.onSuccess(eth.this.c, list);
                        } else {
                            LogUtil.h("Suggestion_DataImpl", "sleepAudioPackageList == null");
                            uiCallback.onFailure(eth.this.c, ResultUtil.ResultCode.PARAMETER_INVALID, "sleepAudioPackageList == null");
                        }
                    } catch (JsonSyntaxException unused) {
                        LogUtil.b("Suggestion_DataImpl", "parse sleepAudioPackageList , jsonSyntaxException");
                        uiCallback.onFailure(eth.this.c, ResultUtil.ResultCode.PARAMETER_INVALID, "parse sleepAudioPackageList , jsonSyntaxException");
                    }
                }
            });
        }
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void getAudioBehaviorList(int i, int i2, int i3, int i4, final UiCallback<List<SleepAudioSeries>> uiCallback) {
        if (c(uiCallback) && d(uiCallback)) {
            eqa.a().getAudioBehaviorList(i, i2, i3, i4, new DataCallback() { // from class: eth.65
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i5, String str) {
                    LogUtil.b("Suggestion_DataImpl", "onFailure , errorCode = ", Integer.valueOf(i5), " errorInfo = ", str);
                    uiCallback.onFailure(eth.this.c, i5, str);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    String optString = jSONObject.optString("sleepAudios");
                    if (TextUtils.isEmpty(optString)) {
                        LogUtil.h("Suggestion_DataImpl", "getAudioBehaviorList packageList == null");
                        uiCallback.onFailure(eth.this.c, ResultUtil.ResultCode.PARAMETER_INVALID, "getAudioBehaviorList packageList == null");
                        return;
                    }
                    LogUtil.a("Suggestion_DataImpl", "getAudioBehaviorList sleepAudios = ", optString);
                    try {
                        List list = (List) new Gson().fromJson(optString, new TypeToken<List<SleepAudioSeries>>() { // from class: eth.65.1
                        }.getType());
                        if (list != null) {
                            LogUtil.a("Suggestion_DataImpl", "sleepAudiosListSize = ", Integer.valueOf(list.size()));
                            uiCallback.onSuccess(eth.this.c, list);
                        } else {
                            LogUtil.h("Suggestion_DataImpl", "sleepAudiosList == null");
                            uiCallback.onFailure(eth.this.c, ResultUtil.ResultCode.PARAMETER_INVALID, "sleepAudiosList == null");
                        }
                    } catch (JsonSyntaxException unused) {
                        LogUtil.b("Suggestion_DataImpl", "parse sleepAudiosList , jsonSyntaxException");
                        uiCallback.onFailure(eth.this.c, ResultUtil.ResultCode.PARAMETER_INVALID, "parse sleepAudiosList , jsonSyntaxException");
                    }
                }
            });
        }
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void getAudioBehaviorList(fff fffVar, final UiCallback<List<SleepAudioSeries>> uiCallback) {
        if (c(uiCallback) && d(uiCallback)) {
            eqa.a().getAudioBehaviorList(fffVar, new DataCallback() { // from class: eth.70
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i, String str) {
                    LogUtil.b("Suggestion_DataImpl", "onFailure , errorCode = ", Integer.valueOf(i), " errorInfo = ", str);
                    uiCallback.onFailure(eth.this.c, i, str);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    String optString = jSONObject.optString("sleepAudios");
                    if (TextUtils.isEmpty(optString)) {
                        LogUtil.h("Suggestion_DataImpl", "getAudioBehaviorList packageList == null");
                        uiCallback.onFailure(eth.this.c, ResultUtil.ResultCode.PARAMETER_INVALID, "getAudioBehaviorList packageList == null");
                        return;
                    }
                    LogUtil.a("Suggestion_DataImpl", "getAudioBehaviorList sleepAudios = ", optString);
                    try {
                        List list = (List) new Gson().fromJson(optString, new TypeToken<List<SleepAudioSeries>>() { // from class: eth.70.5
                        }.getType());
                        if (list != null) {
                            LogUtil.a("Suggestion_DataImpl", "sleepAudiosListSize = ", Integer.valueOf(list.size()));
                            uiCallback.onSuccess(eth.this.c, list);
                        } else {
                            LogUtil.h("Suggestion_DataImpl", "sleepAudiosList == null");
                            uiCallback.onFailure(eth.this.c, ResultUtil.ResultCode.PARAMETER_INVALID, "sleepAudiosList == null");
                        }
                    } catch (JsonSyntaxException unused) {
                        LogUtil.b("Suggestion_DataImpl", "parse sleepAudiosList , jsonSyntaxException");
                        uiCallback.onFailure(eth.this.c, ResultUtil.ResultCode.PARAMETER_INVALID, "parse sleepAudiosList , jsonSyntaxException");
                    }
                }
            });
        }
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void operateFavoriteAudio(int i, int i2, final UiCallback<Boolean> uiCallback) {
        if (c(uiCallback) && d(uiCallback)) {
            eqa.a().operateFavoriteAudio(i, i2, new DataCallback() { // from class: eth.68
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i3, String str) {
                    LogUtil.b("Suggestion_DataImpl", "onFailure , errorCode = ", Integer.valueOf(i3), " errorInfo = ", str);
                    uiCallback.onFailure(eth.this.c, i3, str);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    LogUtil.a("Suggestion_DataImpl", "operateFavoriteAudio onSuccess , data = ", jSONObject);
                    uiCallback.onSuccess(eth.this.c, true);
                }
            });
        }
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void deletePlayRecord(List<Integer> list, final UiCallback<Boolean> uiCallback) {
        if (c(uiCallback) && d(uiCallback)) {
            if (koq.b(list)) {
                LogUtil.h("Suggestion_DataImpl", "deletePlayRecord idList is empty");
                uiCallback.onFailure(this.c, ResultUtil.ResultCode.PARAMETER_INVALID, "deletePlayRecord idList is empty");
            }
            eqa.a().deletePlayRecord(list, new DataCallback() { // from class: eth.67
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i, String str) {
                    LogUtil.b("Suggestion_DataImpl", "onFailure , errorCode = ", Integer.valueOf(i), " errorInfo = ", str);
                    uiCallback.onFailure(eth.this.c, i, str);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    LogUtil.a("Suggestion_DataImpl", "deletePlayRecord onSuccess , data = ", jSONObject);
                    uiCallback.onSuccess(eth.this.c, true);
                }
            });
        }
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void getWorkoutPackageById(ffk ffkVar, final UiCallback<WorkoutPackageInfo> uiCallback) {
        ReleaseLogUtil.e("Suggestion_DataImpl", "getWorkoutPackageById ", ffkVar.d(), " ", ffkVar.e());
        if (uiCallback == null) {
            ReleaseLogUtil.c("Suggestion_DataImpl", "getWorkoutPackageById, callback == null");
        } else {
            eqa.a().getWorkoutPackage(ffkVar, new DataCallback() { // from class: eth.66
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i, String str) {
                    ReleaseLogUtil.c("Suggestion_DataImpl", "getWorkoutPackage errorCode=", Integer.valueOf(i));
                    uiCallback.onFailure(i, str);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    Object[] objArr = new Object[2];
                    objArr[0] = "getWorkoutPackage is data null ";
                    objArr[1] = Boolean.valueOf(jSONObject == null);
                    ReleaseLogUtil.e("Suggestion_DataImpl", objArr);
                    uiCallback.onSuccess(mnc.e().parse(CommonUtil.x(), jSONObject));
                }
            });
        }
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public FitWorkout getFitWorkout(String str, String str2) {
        LoginInit loginInit = LoginInit.getInstance(BaseApplication.getContext());
        if (loginInit.getHuidOrDefault() == null) {
            LogUtil.b("Suggestion_DataImpl", "ifFitWorkoutExists accountInfo == null");
            return null;
        }
        return ett.i().l().d(loginInit.getHuidOrDefault(), str, str2);
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public List<Media> getMediaFiles(FitWorkout fitWorkout) {
        if (fitWorkout == null) {
            return new ArrayList();
        }
        return etq.a(fitWorkout, true);
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void getFitnessActionTemplate(String str, final UiCallback<mms> uiCallback) {
        if (uiCallback == null) {
            ReleaseLogUtil.d("Suggestion_DataImpl", "getFitnessActionTemplate(), callback == null");
        } else if (!b() || !CommonUtil.aa(arx.b())) {
            uiCallback.onFailure(this.c, ResultUtil.ResultCode.NO_NET, ResultUtil.d(ResultUtil.ResultCode.NO_NET));
        } else {
            eqa.a().getFitnessActionTemplate(str, new DataCallback() { // from class: eth.75
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i, String str2) {
                    ReleaseLogUtil.d("Suggestion_DataImpl", "getFitnessActionTemplate(): errorCode=", Integer.valueOf(i));
                    uiCallback.onFailure(eth.this.c, i, str2);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    if (jSONObject == null) {
                        ReleaseLogUtil.d("Suggestion_DataImpl", "getFitnessActionTemplate onSuccess data is null.");
                        uiCallback.onSuccess(eth.this.c, null);
                    } else {
                        uiCallback.onSuccess(eth.this.c, (mms) moj.e(jSONObject.toString(), mms.class));
                    }
                }
            });
        }
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void getFitnessMaxScore(String str, int i, final UiCallback<mmo> uiCallback) {
        if (uiCallback == null) {
            ReleaseLogUtil.d("Suggestion_DataImpl", "getFitnessMaxScore(), callback == null");
        } else if (!b() || !CommonUtil.aa(arx.b())) {
            uiCallback.onFailure(this.c, ResultUtil.ResultCode.NO_NET, ResultUtil.d(ResultUtil.ResultCode.NO_NET));
        } else {
            eqa.a().getFitnessMaxScore(str, i, new DataCallback() { // from class: eth.74
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i2, String str2) {
                    ReleaseLogUtil.d("Suggestion_DataImpl", "getFitnessMaxScore(): errorCode=", Integer.valueOf(i2));
                    uiCallback.onFailure(eth.this.c, i2, str2);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    if (jSONObject == null) {
                        ReleaseLogUtil.d("Suggestion_DataImpl", "getFitnessMaxScore onSuccess data is null.");
                        uiCallback.onSuccess(eth.this.c, null);
                    } else {
                        uiCallback.onSuccess(eth.this.c, (mmo) moj.e(jSONObject.toString(), mmo.class));
                    }
                }
            });
        }
    }

    @Override // com.huawei.health.plan.model.data.DataApi
    public void getUserRank(erc ercVar, final UiCallback<mmx> uiCallback) {
        if (uiCallback == null) {
            ReleaseLogUtil.d("Suggestion_DataImpl", "getUserRank(), callback == null");
        } else if (!b() || !CommonUtil.aa(arx.b())) {
            uiCallback.onFailure(this.c, ResultUtil.ResultCode.NO_NET, ResultUtil.d(ResultUtil.ResultCode.NO_NET));
        } else {
            eqa.a().getUserRank(ercVar, new DataCallback() { // from class: eth.71
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i, String str) {
                    ReleaseLogUtil.d("Suggestion_DataImpl", "getFitnessMaxScore(): errorCode=", Integer.valueOf(i));
                    uiCallback.onFailure(eth.this.c, i, str);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    if (jSONObject == null) {
                        ReleaseLogUtil.d("Suggestion_DataImpl", "getFitnessMaxScore onSuccess data is null.");
                        uiCallback.onSuccess(eth.this.c, null);
                    } else {
                        uiCallback.onSuccess(eth.this.c, (mmx) moj.e(jSONObject.toString(), mmx.class));
                    }
                }
            });
        }
    }

    private boolean d(UiCallback uiCallback) {
        if (!TextUtils.isEmpty(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011))) {
            return true;
        }
        LogUtil.h("Suggestion_DataImpl", "checkLogin false");
        uiCallback.onFailure(this.c, 20001, ResultUtil.d(20001));
        return false;
    }

    private boolean c(UiCallback uiCallback) {
        if (CommonUtil.aa(arx.b())) {
            return true;
        }
        LogUtil.h("Suggestion_DataImpl", "checkNetwork false");
        uiCallback.onFailure(this.c, ResultUtil.ResultCode.NO_NET, ResultUtil.d(ResultUtil.ResultCode.NO_NET));
        return false;
    }

    private boolean a(UiCallback uiCallback) {
        if (b()) {
            return true;
        }
        LogUtil.h("Suggestion_DataImpl", "checkAllowLogin false");
        uiCallback.onFailure(this.c, ResultUtil.ResultCode.NO_NET, ResultUtil.d(ResultUtil.ResultCode.NO_NET));
        return false;
    }

    private float e(float f) {
        ReleaseLogUtil.e("Suggestion_DataImpl", "handleFinishRate finishRate", Float.valueOf(f));
        if (f > 100.0f) {
            return 100.0f;
        }
        if (CommonUtil.c(f)) {
            return 0.0f;
        }
        return f;
    }
}
