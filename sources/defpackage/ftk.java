package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.basefitnessadvice.model.RunWorkout;
import com.huawei.basefitnessadvice.model.Summary;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.device.api.IndoorEquipManagerApi;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.suggestion.RunCallback;
import com.huawei.health.suggestion.model.SportInfo;
import com.huawei.health.suggestion.ui.voice.IVoiceContentConstructor;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.mlsdk.asr.MLAsrConstants;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.Workout;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class ftk implements RunCallback {
    private static volatile ftk d;
    private static final Object e = new Object();

    /* renamed from: a, reason: collision with root package name */
    private long f12642a;
    private int b;
    private int c;
    private List<Float> f;
    private float g;
    private int h;
    private float i;
    private int j;
    private int k;
    private String m;
    private float n;

    @Override // com.huawei.health.suggestion.RunCallback
    public void onConnectStatus(int i) {
    }

    private ftk() {
        c();
    }

    private void c() {
        this.f = new ArrayList();
    }

    public static ftk e() {
        if (d == null) {
            synchronized (e) {
                if (d == null) {
                    d = new ftk();
                }
            }
        }
        return d;
    }

    private void c(int i) {
        LogUtil.a("Suggestion_RunManager", "play stateSound-", Integer.valueOf(i));
        Object constructContent = a().constructContent(1, Integer.valueOf(i));
        if (constructContent instanceof Integer) {
            fjl.e(arx.b()).e(((Integer) constructContent).intValue());
        } else {
            LogUtil.h("Suggestion_RunManager", "playStateSound else");
        }
    }

    private IVoiceContentConstructor a() {
        if (b()) {
            return gfu.e();
        }
        return gft.b();
    }

    private boolean b() {
        return MLAsrConstants.LAN_ZH.equalsIgnoreCase(arx.b().getResources().getConfiguration().locale.getLanguage());
    }

    private void b(SportInfo sportInfo) {
        int i;
        int i2;
        int i3;
        float acquireDistance = sportInfo.acquireDistance();
        int acquireTime = sportInfo.acquireTime();
        if (acquireDistance >= 4.99f && (acquireTime < (i3 = this.c) || i3 <= 0)) {
            this.c = acquireTime;
        }
        if (acquireDistance >= 9.99f && (acquireTime < (i2 = this.k) || i2 <= 0)) {
            this.k = acquireTime;
        }
        if (acquireDistance >= 21.087f && (acquireTime < (i = this.b) || i <= 0)) {
            this.b = acquireTime;
        }
        if (acquireDistance >= 42.183002f) {
            int i4 = this.j;
            if (acquireTime < i4 || i4 <= 0) {
                this.j = acquireTime;
            }
        }
    }

    private WorkoutRecord e(Summary summary) {
        WorkoutRecord workoutRecord = new WorkoutRecord();
        e(summary, workoutRecord);
        String str = this.m;
        if (str == null) {
            Plan e2 = gdr.e();
            str = e2 == null ? null : e2.acquireId();
        }
        if (str == null || !str.equals(summary.acquirePlanId())) {
            LogUtil.h("Suggestion_RunManager", "getRecord summary.acquirePlanId():", summary.acquirePlanId(), ",currPlanId：", str);
            return null;
        }
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("Suggestion_RunManager", "getRecord, getCouseWithinCurrentPlanById: courseApi is null.");
            return null;
        }
        Workout courseWithinCurrentPlanById = courseApi.getCourseWithinCurrentPlanById(0, summary.acquireWorkoutId());
        if (courseWithinCurrentPlanById == null) {
            LogUtil.h("Suggestion_RunManager", "getRecord getCouseWithinCurrentPlanById workout == null");
            return null;
        }
        if (!(courseWithinCurrentPlanById instanceof RunWorkout)) {
            LogUtil.h("Suggestion_RunManager", "getRecord, getCouseWithinCurrentPlanById: workout real type not RunWorkout.");
            return null;
        }
        return c(courseWithinCurrentPlanById, summary, workoutRecord);
    }

    private void e(Summary summary, WorkoutRecord workoutRecord) {
        workoutRecord.saveActualDistance(summary.acquireDistance());
        workoutRecord.saveActualCalorie(summary.acquireCalorie());
        workoutRecord.setDuration(summary.acquireDuring());
        workoutRecord.saveExerciseTime(summary.acquireExerciseTime());
        workoutRecord.saveTrajectoryId(summary.acquireSportId());
        workoutRecord.saveCalorie(summary.acquireCalorie());
        workoutRecord.saveWorkoutOrder(1);
        workoutRecord.saveRecordType(1);
        workoutRecord.saveOxygen(moe.e(summary.acquireMaxMet()));
        if (summary.acquireBestPace() != 0) {
            workoutRecord.saveBestPace(summary.acquireBestPace());
        } else {
            workoutRecord.saveBestPace((int) (summary.acquireDuring() / summary.acquireDistance()));
        }
    }

    private WorkoutRecord c(Workout workout, Summary summary, WorkoutRecord workoutRecord) {
        RunWorkout runWorkout = (RunWorkout) workout;
        LogUtil.c("Suggestion_RunManager", String.valueOf(runWorkout));
        float c = gdr.c(summary.acquireDistance(), runWorkout.acquireDistance());
        workoutRecord.saveDistance(runWorkout.acquireDistance());
        workoutRecord.saveFinishRate(c);
        workoutRecord.saveWorkoutId(runWorkout.acquireId());
        workoutRecord.saveWorkoutName(runWorkout.acquireName());
        workoutRecord.saveWorkoutDate(runWorkout.acquireWorkoutDate());
        workoutRecord.savePlanId(summary.acquirePlanId());
        workoutRecord.saveVersion(runWorkout.accquireVersion());
        return workoutRecord;
    }

    private void c(WorkoutRecord workoutRecord) {
        int acquireBestPace = workoutRecord.acquireBestPace();
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.h("Suggestion_RunManager", "saveRecord : planApi is null.");
            return;
        }
        if (workoutRecord.acquireActualDistance() >= 1.0f) {
            planApi.updatePlanBestRecord(workoutRecord.acquirePlanId(), 4, acquireBestPace);
        }
        planApi.setPlanType(0);
        planApi.updatePlanProgress(workoutRecord);
        fyr.d(0, fyr.b(workoutRecord.startTime()));
    }

    private SportInfo aGp_(Bundle bundle) {
        SportInfo sportInfo = new SportInfo();
        if (bundle != null) {
            int i = bundle.getInt("distance", 0);
            int i2 = bundle.getInt("duration", 0);
            int i3 = bundle.getInt(IndoorEquipManagerApi.KEY_HEART_RATE, 0);
            int i4 = bundle.getInt("calorie", 0);
            String string = bundle.getString("sportId", "");
            sportInfo.saveTime(i2);
            sportInfo.saveHeartRate(i3);
            sportInfo.savePace(moe.e(d()));
            sportInfo.saveCalorie(i4);
            sportInfo.saveSportId(string);
            sportInfo.saveDistance(moe.d(i));
        }
        return sportInfo;
    }

    private void e(SportInfo sportInfo) {
        if (sportInfo.acquireTime() - this.f12642a >= 1800) {
            this.f12642a = sportInfo.acquireTime();
        }
        float acquireDistance = sportInfo.acquireDistance();
        float f = this.g;
        if (acquireDistance >= f && acquireDistance <= f + 0.1f && ((int) Math.floor(10.0f * acquireDistance)) % 10 == 5) {
            LogUtil.a("Suggestion_RunManager", "play half");
            this.g = 0.0f;
        }
        b(sportInfo);
        sportInfo.acquireTime();
        if (acquireDistance >= this.i) {
            if (acquireDistance - (((int) (acquireDistance / 0.5f)) * 0.5f) > 0.4f) {
                this.i = (r1 + 2) * 0.5f;
            } else {
                this.i = (r1 + 1) * 0.5f;
            }
        }
        if (((int) acquireDistance) > ((int) this.n)) {
            this.n = acquireDistance;
        }
    }

    private void d(int i) {
        if (i != this.h) {
            this.h = i;
        }
    }

    private void aGq_(Bundle bundle) {
        float f = bundle.getFloat("speed", 0.0f);
        if (this.f.size() >= 10) {
            this.f.remove(0);
        }
        this.f.add(Float.valueOf(f));
    }

    private float d() {
        Float[] fArr;
        float f = 0.0f;
        if (!koq.c(this.f)) {
            return 0.0f;
        }
        synchronized (e) {
            List<Float> list = this.f;
            fArr = (Float[]) list.toArray(new Float[list.size()]);
        }
        for (Float f2 : fArr) {
            f += f2.floatValue();
        }
        return f / fArr.length;
    }

    private void f() {
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.h("Suggestion_RunManager", "updateBestRecord : planApi is null.");
            return;
        }
        int i = this.c;
        if (i > 0) {
            planApi.updatePlanBestRecord(this.m, 0, i);
        }
        int i2 = this.k;
        if (i2 > 0) {
            planApi.updatePlanBestRecord(this.m, 1, i2);
        }
        int i3 = this.b;
        if (i3 > 0) {
            planApi.updatePlanBestRecord(this.m, 2, i3);
        }
        int i4 = this.j;
        if (i4 > 0) {
            planApi.updatePlanBestRecord(this.m, 3, i4);
        }
    }

    public void a(Summary summary, boolean z) {
        LogUtil.a("Suggestion_RunManager", "summary", summary, " isFormRunCallback:", Boolean.valueOf(z));
        if (summary != null) {
            if (z) {
                f();
            }
            WorkoutRecord e2 = e(summary);
            if (e2 != null && e2.acquireActualDistance() > 0.1d) {
                if (((PlanApi) Services.a("CoursePlanService", PlanApi.class)) == null) {
                    LogUtil.b("Suggestion_RunManager", "onSummary, getCurrentPlan : planApi is null.");
                    return;
                }
                Plan e3 = gdr.e();
                if (e3 == null || e3.acquireType() != 0 || !e3.acquireId().equals(summary.acquirePlanId())) {
                    LogUtil.a("Suggestion_RunManager", "planId is null or is not currentPlan or planType not run, not save record");
                    return;
                }
                c(e2);
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("plan_name", e3.acquireName());
                    jSONObject.put("type", 0);
                    if (gge.c()) {
                        jSONObject.put("start_time", summary.acquiretStartTime());
                        jSONObject.put("end_time", summary.acquireEndTime());
                        jSONObject.put("finish_rate", gge.c(e2.acquireFinishRate()));
                    }
                    jSONObject.put("workout_name", summary.acquireWorkoutName());
                    HashMap hashMap = new HashMap(1);
                    hashMap.put("data", jSONObject.toString());
                    gge.e("1120005", hashMap);
                } catch (JSONException e4) {
                    LogUtil.b("Suggestion_RunManager", "e = ", e4.getMessage());
                }
            }
        }
        if (z) {
            this.m = null;
            this.c = 0;
            this.k = 0;
            this.b = 0;
            this.j = 0;
        }
    }

    @Override // com.huawei.health.suggestion.RunCallback
    public void onSummary(Summary summary) {
        a(summary, true);
    }

    @Override // com.huawei.health.suggestion.RunCallback
    public void onUpdate(Bundle bundle) {
        LogUtil.c("SportCallback", "onUpdate-", bundle);
        if (bundle == null) {
            return;
        }
        int i = bundle.getInt("sportState", 0);
        d(i);
        if (i == 1) {
            aGq_(bundle);
            e(aGp_(bundle));
        }
    }

    @Override // com.huawei.health.suggestion.RunCallback
    public void realTimeGuidance(int i, List<Integer> list) {
        LogUtil.a("Suggestion_RunManager", "phraseId:", Integer.valueOf(i), ",integerList:", Arrays.toString(list.toArray()));
        if (i != 1 && i != 2) {
            if (i != 3) {
                if (i != 6) {
                    if (i != 7) {
                        if (i != 10) {
                            if (i != 11) {
                                if (i != 14 && i != 29) {
                                    switch (i) {
                                        case 21:
                                        case 22:
                                        case 23:
                                        case 24:
                                            break;
                                        case 25:
                                        case 26:
                                            break;
                                        default:
                                            switch (i) {
                                                case 33:
                                                case 34:
                                                case 35:
                                                    c(1);
                                                    break;
                                            }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            c(5);
            return;
        }
        c(3);
    }

    @Override // com.huawei.health.suggestion.RunCallback
    public void dialog(int i, final View.OnClickListener onClickListener, final View.OnClickListener onClickListener2, Context context) {
        if (i != 1) {
            LogUtil.b("RunCallback", "unknow dialog type");
        } else if (context != null) {
            new CustomTextAlertDialog.Builder(context).b(moi.e(context, R.string._2130848356_res_0x7f022a64, new Object[0])).e(moi.e(context, R.string._2130848441_res_0x7f022ab9, moi.e(context, R$string.IDS_scan_device, new Object[0]))).cyU_(R.string._2130848401_res_0x7f022a91, new View.OnClickListener() { // from class: ftk.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    View.OnClickListener onClickListener3 = onClickListener;
                    if (onClickListener3 != null) {
                        onClickListener3.onClick(view);
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).cyR_(com.huawei.health.servicesui.R$string.IDS_plugin_fitnessadvice_cancal, new View.OnClickListener() { // from class: ftk.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    View.OnClickListener onClickListener3 = onClickListener2;
                    if (onClickListener3 != null) {
                        onClickListener3.onClick(view);
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).a().show();
        }
    }
}
