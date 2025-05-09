package defpackage;

import android.content.Context;
import com.huawei.health.courseplanservice.api.RecordApi;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.userlabelmgr.model.UpdateUserLabel;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.pluginfitnessadvice.FitWorkout;
import defpackage.kwy;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes3.dex */
public class dxp {

    /* renamed from: a, reason: collision with root package name */
    private static dxp f11897a;
    private static final Object c = new Object();
    private List<WorkoutRecord> b;
    private Context d = BaseApplication.getContext();
    private UpdateUserLabel e;

    private dxp() {
    }

    public static dxp d() {
        dxp dxpVar;
        synchronized (c) {
            if (f11897a == null) {
                f11897a = new dxp();
            }
            dxpVar = f11897a;
        }
        return dxpVar;
    }

    public void a() {
        LogUtil.a("Fitness_FitnessUserLabelHelper", "enter registerCallback()");
        if (this.e != null) {
            LogUtil.a("Fitness_FitnessUserLabelHelper", "registerCallback(), mUpdateUserLabel != null");
        } else {
            this.e = new UpdateUserLabel() { // from class: dxp.5
                @Override // com.huawei.health.userlabelmgr.model.UpdateUserLabel
                public void onUpdate() {
                    LogUtil.a("Fitness_FitnessUserLabelHelper", "registerCallback(), onUpdate()");
                    dxp.this.b();
                }
            };
            dxw.a(this.d).b(this.e);
        }
    }

    public void c() {
        LogUtil.a("Fitness_FitnessUserLabelHelper", "enter unregisterCallback()");
        if (this.e != null) {
            dxw.a(this.d).e(this.e);
            this.e = null;
        }
    }

    public void b() {
        RecordApi recordApi = (RecordApi) Services.a("CoursePlanService", RecordApi.class);
        if (recordApi == null) {
            LogUtil.h("Fitness_FitnessUserLabelHelper", "generateLabels recordApi is null.");
        } else {
            recordApi.acquireDetailFitnessRecords(new kwy.a().a(0L).e(System.currentTimeMillis()).d(), new IBaseResponseCallback() { // from class: dxp.4
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (koq.e(obj, WorkoutRecord.class)) {
                        if (dxp.this.b == null) {
                            dxp.this.b = new ArrayList(10);
                        }
                        dxp.this.b.addAll((List) obj);
                        dxp dxpVar = dxp.this;
                        dxpVar.b((List<WorkoutRecord>) dxpVar.b);
                        dxp dxpVar2 = dxp.this;
                        dxpVar2.c((List<WorkoutRecord>) dxpVar2.b);
                        dxp dxpVar3 = dxp.this;
                        dxpVar3.e(dxpVar3.b);
                        dxp dxpVar4 = dxp.this;
                        dxpVar4.d((List<WorkoutRecord>) dxpVar4.b);
                        dxp dxpVar5 = dxp.this;
                        dxpVar5.a((List<WorkoutRecord>) dxpVar5.b);
                        dxp.this.e();
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<WorkoutRecord> list) {
        WorkoutRecord workoutRecord;
        if (koq.b(list)) {
            LogUtil.a("Fitness_FitnessUserLabelHelper", "generateNewUserLabels, records are empty");
            a("health_sport_fitness_newuser", "");
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        int size = list.size() - 1;
        while (true) {
            if (size < 0) {
                workoutRecord = null;
                break;
            } else {
                if (list.get(size) != null) {
                    workoutRecord = list.get(size);
                    break;
                }
                size--;
            }
        }
        if (workoutRecord == null) {
            LogUtil.h("Fitness_FitnessUserLabelHelper", "generateNewUserLabels, earliestRecord == null");
            a("health_sport_fitness_newuser", "");
        } else {
            a("health_sport_fitness_newuser", currentTimeMillis - workoutRecord.acquireExerciseTime() < TimeUnit.DAYS.toMillis(30L) ? "SportFinessNewUser" : "");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<WorkoutRecord> list) {
        WorkoutRecord workoutRecord;
        if (koq.b(list)) {
            LogUtil.a("Fitness_FitnessUserLabelHelper", "generateSilenceUserLabels, records are empty");
            a("health_sport_fitness_silence", "");
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        Iterator<WorkoutRecord> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                workoutRecord = null;
                break;
            } else {
                workoutRecord = it.next();
                if (workoutRecord != null) {
                    break;
                }
            }
        }
        if (workoutRecord == null) {
            LogUtil.h("Fitness_FitnessUserLabelHelper", "generateNewUserLabels, latestRecord == null");
            a("health_sport_fitness_silence", "");
        } else {
            a("health_sport_fitness_silence", currentTimeMillis - workoutRecord.acquireExerciseTime() > TimeUnit.DAYS.toMillis(30L) ? "SportFinessSilence" : "");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(List<WorkoutRecord> list) {
        if (koq.b(list)) {
            LogUtil.a("Fitness_FitnessUserLabelHelper", "generateFrequencyLabel, records are empty");
            a("health_sport_fitness_frequency", "SportFitness_0");
            return;
        }
        HashSet hashSet = new HashSet(10);
        for (WorkoutRecord workoutRecord : list) {
            if (a(workoutRecord)) {
                hashSet.add(workoutRecord.acquireWorkoutDate());
            }
        }
        String c2 = c(hashSet.size());
        if (c2 == null) {
            c2 = "";
        }
        a("health_sport_fitness_frequency", c2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<WorkoutRecord> list) {
        WorkoutRecord workoutRecord;
        String str = "";
        if (koq.b(list)) {
            LogUtil.a("Fitness_FitnessUserLabelHelper", "generateLatestWorkoutLabels, records are empty");
            a("health_sport_last_fitness", "");
            return;
        }
        Iterator<WorkoutRecord> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                workoutRecord = null;
                break;
            }
            workoutRecord = it.next();
            if (workoutRecord != null && !c(workoutRecord)) {
                break;
            }
        }
        if (workoutRecord == null) {
            LogUtil.a("Fitness_FitnessUserLabelHelper", "generateNewUserLabels, latestRecord == null");
            a("health_sport_last_fitness", "");
            return;
        }
        long currentTimeMillis = System.currentTimeMillis() - workoutRecord.acquireExerciseTime();
        if (currentTimeMillis <= b(7L)) {
            str = "SportLastFitness_0";
        } else if (currentTimeMillis > b(7L) && currentTimeMillis <= b(30L)) {
            str = "SportLastFitness_1";
        } else if (currentTimeMillis > b(30L) && currentTimeMillis <= b(60L)) {
            str = "SportLastFitness_2";
        } else if (currentTimeMillis > b(60L) && currentTimeMillis <= b(90L)) {
            str = "SportLastFitness_3";
        } else if (currentTimeMillis > b(90L)) {
            str = "SportLastFitness_4";
        } else {
            LogUtil.h("Fitness_FitnessUserLabelHelper", "generateLatestWorkoutLabels label is empty");
        }
        a("health_sport_last_fitness", str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<WorkoutRecord> list) {
        if (koq.b(list)) {
            LogUtil.a("Fitness_FitnessUserLabelHelper", "generateIntensityLabels, records are empty");
            a("health_sport_fitness_intensity", "");
            return;
        }
        Integer[] numArr = {1, 2};
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.h("Fitness_FitnessUserLabelHelper", "generateIntensityLabels planApi == null");
            return;
        }
        List<FitWorkout> workoutListFromLocalByDifficulties = planApi.getWorkoutListFromLocalByDifficulties(1, 0, 50, numArr);
        if (koq.b(workoutListFromLocalByDifficulties)) {
            LogUtil.a("Fitness_FitnessUserLabelHelper", "generateIntensityLabels, workouts are empty");
            a("health_sport_fitness_intensity", "");
        } else {
            String c2 = c(list, workoutListFromLocalByDifficulties);
            a("health_sport_fitness_intensity", c2 != null ? c2 : "");
        }
    }

    private long b(long j) {
        if (j < 0) {
            return 0L;
        }
        return TimeUnit.DAYS.toMillis(j);
    }

    private void a(String str, String str2) {
        if (str == null || str2 == null) {
            LogUtil.h("Fitness_FitnessUserLabelHelper", "insertLabel, key == null || value == null");
        } else {
            dxw.a(this.d).b(str, str2, LoginInit.getInstance(this.d).getAccountInfo(1011));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        this.b = null;
    }

    private boolean c(WorkoutRecord workoutRecord) {
        if (workoutRecord == null) {
            LogUtil.h("Fitness_FitnessUserLabelHelper", "isOptOutRecord record == null");
            return false;
        }
        String acquireWorkoutId = workoutRecord.acquireWorkoutId();
        acquireWorkoutId.hashCode();
        return acquireWorkoutId.equals("R001") || acquireWorkoutId.equals("R002");
    }

    private boolean a(WorkoutRecord workoutRecord) {
        return (workoutRecord == null || c(workoutRecord) || System.currentTimeMillis() - workoutRecord.acquireExerciseTime() > b(365L)) ? false : true;
    }

    private String c(List<WorkoutRecord> list, List<FitWorkout> list2) {
        if (koq.b(list) || koq.b(list2)) {
            LogUtil.a("Fitness_FitnessUserLabelHelper", "getIntensityLabelValue, records or workouts is empty");
            return null;
        }
        HashSet hashSet = new HashSet(10);
        for (FitWorkout fitWorkout : list2) {
            if (fitWorkout != null && fitWorkout.acquireId() != null) {
                hashSet.add(fitWorkout.acquireId());
            }
        }
        int i = 0;
        for (WorkoutRecord workoutRecord : list) {
            if (workoutRecord != null && hashSet.contains(workoutRecord.acquireWorkoutId())) {
                i++;
            }
        }
        if (i >= 1 && i <= 2) {
            return "SportIntensity_0";
        }
        if (i > 2 && i <= 5) {
            return "SportIntensity_1";
        }
        if (i > 5) {
            return "SportIntensity_2";
        }
        return null;
    }

    private String c(int i) {
        if (i == 0) {
            return "SportFitness_0";
        }
        if (i >= 1 && i < 8) {
            return "SportFitness_1";
        }
        if (i >= 8 && i < 12) {
            return "SportFitness_2";
        }
        if (i >= 12) {
            return "SportFitness_3";
        }
        return null;
    }
}
