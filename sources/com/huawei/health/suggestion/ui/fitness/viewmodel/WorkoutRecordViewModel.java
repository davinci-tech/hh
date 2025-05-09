package com.huawei.health.suggestion.ui.fitness.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.google.gson.Gson;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.courseplanservice.api.DataPlatformApi;
import com.huawei.health.courseplanservice.api.RecordApi;
import com.huawei.health.device.model.RecordAction;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.suggestion.CoachController;
import com.huawei.health.suggestion.FitnessExternalUtils;
import com.huawei.health.suggestion.ui.fitness.module.CoachData;
import com.huawei.health.suggestion.ui.fitness.module.Motion;
import com.huawei.health.suggestion.ui.fitness.viewmodel.WorkoutRecordViewModel;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.operation.utils.Constants;
import defpackage.fhp;
import defpackage.fjg;
import defpackage.fou;
import defpackage.fpq;
import defpackage.fyr;
import defpackage.gge;
import defpackage.ggl;
import defpackage.ggr;
import defpackage.mmt;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class WorkoutRecordViewModel extends ViewModel {

    /* renamed from: a, reason: collision with root package name */
    private ArrayList<RecordAction> f3224a;
    private float b;
    private float d;
    private int e;
    private String h;
    private long f = 0;
    private long g = 0;
    public volatile boolean c = false;
    private MutableLiveData<WorkoutRecord> j = new MutableLiveData<>();

    public void c(WorkoutRecord workoutRecord) {
        this.j.setValue(workoutRecord);
    }

    public WorkoutRecord a() {
        return this.j.getValue();
    }

    public void d(LifecycleOwner lifecycleOwner) {
        this.j.removeObservers(lifecycleOwner);
    }

    public void a(List<Motion> list, CoachData coachData, long j, long j2, long j3) {
        this.b = 0.0f;
        this.d = 0.0f;
        this.f3224a = new ArrayList<>(list.size());
        int i = 0;
        for (Motion motion : list) {
            if (motion != null) {
                int acquireActionTrainTime = motion.acquireActionTrainTime();
                this.f3224a.add(motion.getLongRecordAction());
                i += acquireActionTrainTime;
            }
        }
        if (coachData.acquireDuration() == 0) {
            LogUtil.h("Suggestion_WorkoutRecordViewModel", "calulateRecordData acquireDuration == 0");
            return;
        }
        this.b = (i * 100.0f) / coachData.acquireDuration();
        if (CoachController.d().i()) {
            this.d = CoachController.d().e() * 1000.0f;
        } else {
            this.d = ((System.currentTimeMillis() - j) - j2) - j3;
            ReleaseLogUtil.e("Suggestion_WorkoutRecordViewModel", "now:", Long.valueOf(System.currentTimeMillis()), "startTrainTime:", Long.valueOf(j), "pauseDuration:", Long.valueOf(j2), "loadingDuration:", Long.valueOf(j3));
        }
    }

    public void e(float f, float f2, float f3, boolean z) {
        WorkoutRecord a2 = a();
        a2.saveCalorie(f3);
        a2.saveExerciseTime(Calendar.getInstance().getTimeInMillis());
        a2.saveRecordType(!TextUtils.isEmpty(a2.acquirePlanId()) ? 1 : 0);
        a2.saveActualCalorie(f);
        a2.setActiveCalorie(f2);
        LogUtil.a("Suggestion_WorkoutRecordViewModel", "saveDataToRecord actualCalories ：", Float.valueOf(f));
        if (z) {
            a2.setSportRecordType(1);
        }
        a2.saveFinishRate(this.b);
        a2.setDuration((int) this.d);
        String json = new Gson().toJson(this.f3224a);
        if (Constants.NULL.equals(json)) {
            json = "";
        }
        a2.saveActionSummary(json);
    }

    public void e(final WorkoutRecord workoutRecord, ExecutorService executorService) {
        if (workoutRecord == null || executorService == null) {
            LogUtil.h("Suggestion_WorkoutRecordViewModel", "saveRecord() context or cachedThreadPool is null");
        } else {
            executorService.execute(new Runnable() { // from class: com.huawei.health.suggestion.ui.fitness.viewmodel.WorkoutRecordViewModel.5
                @Override // java.lang.Runnable
                public void run() {
                    PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
                    if (planApi == null) {
                        LogUtil.h("Suggestion_WorkoutRecordViewModel", "updatePlanProgress : planApiRun is null.");
                        return;
                    }
                    planApi.setPlanType(0);
                    planApi.updatePlanProgress(workoutRecord);
                    if (!TextUtils.isEmpty(workoutRecord.acquirePlanId()) && !FitnessExternalUtils.a()) {
                        LogUtil.a("Suggestion_WorkoutRecordViewModel", "saveAndFinish() planId:", workoutRecord.acquirePlanId());
                        PlanApi planApi2 = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
                        if (planApi2 == null) {
                            LogUtil.h("Suggestion_WorkoutRecordViewModel", "updatePlanProgress : planApiPkg is null.");
                            return;
                        } else {
                            planApi2.setPlanType(3);
                            planApi2.updatePlanProgress(workoutRecord);
                        }
                    }
                    fyr.d(0, fyr.b(workoutRecord.startTime()));
                }
            });
        }
    }

    public void a(String str, int i) {
        LogUtil.a("Suggestion_WorkoutRecordViewModel", "saveWorkoutCourseRecord");
        WorkoutRecord a2 = a();
        if (a2 == null) {
            LogUtil.h("Suggestion_WorkoutRecordViewModel", "workout record is null, return");
            return;
        }
        HiHealthData hiHealthData = new HiHealthData();
        hiHealthData.setDeviceUuid("-1");
        if (!this.c) {
            this.c = true;
            hiHealthData.setStartTime(a2.startTime());
            hiHealthData.setEndTime(a2.acquireExerciseTime());
            this.f = a2.startTime();
            this.g = a2.acquireExerciseTime();
            LogUtil.a("Suggestion_WorkoutRecordViewModel", "first sync, startTime: ", Long.valueOf(a2.startTime()), " endTime: ", Long.valueOf(a2.acquireExerciseTime()));
        } else {
            long j = this.f;
            if (j != 0 && this.g != 0) {
                hiHealthData.setStartTime(j);
                hiHealthData.setEndTime(this.g);
                LogUtil.a("Suggestion_WorkoutRecordViewModel", "update sync");
            }
        }
        hiHealthData.setType(DicDataTypeUtil.DataType.COURSE_RECORD.value());
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("trainTime", a2.getDuration() / 1000);
            jSONObject.put("lessonID", String.valueOf(a2.acquireWorkoutId()));
            jSONObject.put("lessonName", a2.acquireWorkoutName());
            jSONObject.put("resourceType", str);
            jSONObject.put("lessonDuration", i);
            if (a2.acquireCategory() == 137) {
                jSONObject.put("lessonType", 6);
            }
        } catch (JSONException e) {
            ReleaseLogUtil.c("Suggestion_WorkoutRecordViewModel", "value JSONException ", ExceptionUtils.d(e));
        }
        hiHealthData.setMetaData(jSONObject.toString());
        hiHealthData.setSequenceData("{}");
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        hiDataInsertOption.addData(hiHealthData);
        HiHealthManager.d(BaseApplication.getContext()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: fsk
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public final void onResult(int i2, Object obj) {
                WorkoutRecordViewModel.this.e(i2, obj);
            }
        });
    }

    public /* synthetic */ void e(int i, Object obj) {
        ReleaseLogUtil.e("Suggestion_WorkoutRecordViewModel", "error code is ", Integer.valueOf(i), " object is ", obj);
        if (i == 0) {
            c();
        }
    }

    private void c() {
        ReleaseLogUtil.e("Suggestion_WorkoutRecordViewModel", "is network connected : ", Boolean.valueOf(CommonUtil.aa(BaseApplication.getContext())));
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(0);
        hiSyncOption.setSyncDataType(DicDataTypeUtil.DataType.COURSE_RECORD.value());
        hiSyncOption.setSyncMethod(2);
        hiSyncOption.setForceSync(true);
        LogUtil.a("Suggestion_WorkoutRecordViewModel", "type is ", Integer.valueOf(DicDataTypeUtil.DataType.COURSE_RECORD.value()));
        hiSyncOption.setSyncId(System.currentTimeMillis());
        HiHealthNativeApi.a(BaseApplication.getContext()).synCloud(hiSyncOption, null);
    }

    public WorkoutRecord e(fjg fjgVar, long j, int i, long j2, int i2) {
        WorkoutRecord a2 = a();
        mmt mmtVar = new mmt();
        if (CoachController.d().i() && fjgVar != null) {
            a2.saveHeartRateDataList(fjgVar.getHeartRateList());
            a2.saveInvalidHeartRateList(fjgVar.getInvalidHeartRateList());
            mmtVar.a(fjgVar.getAverageHeartRate());
            mmtVar.c(j);
            mmtVar.j(i);
            mmtVar.g(i2);
        }
        if (!TextUtils.isEmpty(a2.acquirePlanId())) {
            a2.setPlanTrainDate(ggl.b(j2));
        }
        mmtVar.b(a2.acquireCategory());
        a2.saveExtend(mmtVar, false);
        a2.setStartTime(j);
        a2.saveWorkoutPackageId(this.h);
        a2.saveCourseBelongType(this.e);
        return a2;
    }

    public void d(String str, int i) {
        this.h = str;
        this.e = i;
    }

    public void c(final Context context, ExecutorService executorService) {
        if (context == null || executorService == null) {
            LogUtil.h("Suggestion_WorkoutRecordViewModel", "context == null || cachedThreadPool == null");
            return;
        }
        final WorkoutRecord a2 = a();
        if (TextUtils.isEmpty(a2.acquireWorkoutId())) {
            return;
        }
        LogUtil.a("Suggestion_WorkoutRecordViewModel", "saveAndFinish() workoutId:", a2.acquireWorkoutId());
        executorService.execute(new Runnable() { // from class: com.huawei.health.suggestion.ui.fitness.viewmodel.WorkoutRecordViewModel.3
            @Override // java.lang.Runnable
            public void run() {
                fou.e(context, a2);
            }
        });
    }

    public void e(final Map<Long, HiHealthData> map, ExecutorService executorService) {
        final Context context = BaseApplication.getContext();
        if (context == null || executorService == null) {
            LogUtil.h("Suggestion_WorkoutRecordViewModel", "insertCalories() context or cachedThreadPool is null");
        } else {
            final WorkoutRecord a2 = a();
            executorService.execute(new Runnable() { // from class: com.huawei.health.suggestion.ui.fitness.viewmodel.WorkoutRecordViewModel.4
                @Override // java.lang.Runnable
                public void run() {
                    DataPlatformApi dataPlatformApi = (DataPlatformApi) Services.a("CoursePlanService", DataPlatformApi.class);
                    if (dataPlatformApi == null) {
                        LogUtil.h("Suggestion_WorkoutRecordViewModel", "executeThread dataPlatformApi is null.");
                        return;
                    }
                    WorkoutRecord workoutRecord = a2;
                    boolean isLinkWear = workoutRecord != null ? workoutRecord.isLinkWear() : false;
                    if (!isLinkWear) {
                        dataPlatformApi.insertCalorieData(context, a2);
                        dataPlatformApi.insertCaloriePoints(context, map);
                    }
                    ReleaseLogUtil.e("Suggestion_WorkoutRecordViewModel", "insertCalories isFormWear:", Boolean.valueOf(isLinkWear));
                    CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
                    if (courseApi != null) {
                        courseApi.saveCourseTrainstatis(a2);
                    }
                    LoginInit loginInit = LoginInit.getInstance(BaseApplication.getContext());
                    if (loginInit.getUsetId() != null) {
                        RecordApi recordApi = (RecordApi) Services.a("CoursePlanService", RecordApi.class);
                        if (recordApi == null) {
                            LogUtil.h("Suggestion_WorkoutRecordViewModel", "executeThread recordApi is null.");
                        } else {
                            recordApi.addRecordFor(loginInit.getUsetId(), a2, true);
                            LogUtil.a("Suggestion_WorkoutRecordViewModel", "recordApi.addRecordFor");
                        }
                    }
                }
            });
        }
    }

    public void aFJ_(final Intent intent, final long j) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.health.suggestion.ui.fitness.viewmodel.WorkoutRecordViewModel.2
            @Override // java.lang.Runnable
            public void run() {
                OpAnalyticsUtil.getInstance().setEventWithActionType(5, OperationKey.HEALTH_APP_RUN_END_2050006.value());
                LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(16);
                linkedHashMap.put("actiontype", String.valueOf(5));
                OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_PVUV_85050001.value(), linkedHashMap);
                JSONObject jSONObject = new JSONObject();
                WorkoutRecord a2 = WorkoutRecordViewModel.this.a();
                if (a2 == null) {
                    LogUtil.h("Suggestion_WorkoutRecordViewModel", "doBi record == null");
                    return;
                }
                PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
                IntPlan currentIntPlan = planApi != null ? planApi.getCurrentIntPlan() : null;
                try {
                    if (fpq.b(currentIntPlan, a2.acquirePlanId())) {
                        jSONObject.put("plan_name", currentIntPlan.getMetaInfo().getName());
                        jSONObject.put("type", ggr.e(currentIntPlan));
                    }
                    if (gge.c()) {
                        jSONObject.put("start_time", j);
                    }
                    jSONObject.put("course_version", a2.acquireVersion());
                    jSONObject.put("calories", (int) a2.acquireActualCalorie());
                    jSONObject.put("duration", a2.getDuration());
                    jSONObject.put("isAICourse", ggr.d(a2));
                    WorkoutRecordViewModel.aFI_(intent, jSONObject);
                    a2.getBiJson(jSONObject);
                    HashMap hashMap = new HashMap(1);
                    hashMap.put("data", jSONObject.toString());
                    gge.e("1120005", hashMap);
                } catch (JSONException e) {
                    LogUtil.b("Suggestion_WorkoutRecordViewModel", "doBi()", LogAnonymous.b((Throwable) e));
                }
            }
        });
    }

    public static void aFI_(Intent intent, JSONObject jSONObject) {
        if (intent == null || jSONObject == null) {
            LogUtil.h("Suggestion_WorkoutRecordViewModel", "setBiMap getIntent() != null || object == null");
            return;
        }
        Map hashMap = new HashMap();
        if (intent.getSerializableExtra("BI_INTENT_COURSE") instanceof Map) {
            hashMap = (Map) intent.getSerializableExtra("BI_INTENT_COURSE");
        }
        if (hashMap == null) {
            LogUtil.h("Suggestion_WorkoutRecordViewModel", "setBiMap biMap == null");
            return;
        }
        for (Map.Entry entry : hashMap.entrySet()) {
            if (entry != null) {
                try {
                    jSONObject.put((String) entry.getKey(), entry.getValue());
                } catch (JSONException e) {
                    LogUtil.b("Suggestion_WorkoutRecordViewModel", "setBiMap jsonException ", LogAnonymous.b((Throwable) e));
                }
            }
        }
    }

    public void aFL_(final long j, final int i, final Intent intent, final long j2) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: fsm
            @Override // java.lang.Runnable
            public final void run() {
                WorkoutRecordViewModel.this.aFK_(intent, j2, i, j);
            }
        });
    }

    public /* synthetic */ void aFK_(Intent intent, long j, int i, long j2) {
        String stringExtra = intent != null ? intent.getStringExtra("entrance") : null;
        if (TextUtils.isEmpty(stringExtra)) {
            stringExtra = "otherModel";
        }
        HashMap hashMap = new HashMap(10);
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        IntPlan currentIntPlan = planApi != null ? planApi.getCurrentIntPlan() : null;
        WorkoutRecord a2 = a();
        if (fpq.b(currentIntPlan, a2.acquirePlanId())) {
            hashMap.put("type", Integer.valueOf(ggr.e(currentIntPlan)));
        }
        hashMap.put("start_time", Long.valueOf(j));
        hashMap.put("end_time", Long.valueOf(System.currentTimeMillis()));
        if (i != 0) {
            hashMap.put("finish_rate", gge.c((j2 / i) * 100.0f));
        }
        hashMap.put("workout_name", a2.acquireWorkoutName());
        hashMap.put("entrance", stringExtra);
        hashMap.put("course_version", a2.acquireVersion());
        hashMap.put("course_time", Integer.valueOf(i));
        hashMap.put("duration", Long.valueOf(j2));
        gge.e("1130026", hashMap);
    }

    public void d() {
        if (a() != null) {
            fhp.c().onChange(a().acquireWorkoutId(), 3, null);
        }
    }
}
