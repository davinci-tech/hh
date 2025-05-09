package defpackage;

import android.text.format.DateUtils;
import com.huawei.health.courseplanservice.api.RecordApi;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes4.dex */
public class fil {
    public static boolean d(WorkoutRecord workoutRecord) {
        if (workoutRecord == null) {
            ReleaseLogUtil.c("Suggestion_SuggestionBinder", "postFitnessRecord record == null.");
            return false;
        }
        ReleaseLogUtil.e("Suggestion_SuggestionBinder", "postFitnessRecord() ", workoutRecord.acquireWorkoutId());
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.b("Suggestion_SuggestionBinder", "uploadFitnessRecordData, getCurrentPlan : planApi is null.");
            return false;
        }
        planApi.setPlanType(0);
        boolean updatePlanProgress = planApi.updatePlanProgress(workoutRecord);
        if (updatePlanProgress) {
            a(workoutRecord);
            fyr.d(0, fyr.b(workoutRecord.startTime()));
            LoginInit loginInit = LoginInit.getInstance(BaseApplication.getContext());
            if (DateUtils.isToday(workoutRecord.acquireExerciseTime()) && loginInit.getUsetId() != null) {
                RecordApi recordApi = (RecordApi) Services.a("CoursePlanService", RecordApi.class);
                if (recordApi == null) {
                    LogUtil.h("Suggestion_SuggestionBinder", "uploadFitnessRecordData recordApi is null.");
                    return updatePlanProgress;
                }
                recordApi.addRecordFor(loginInit.getUsetId(), workoutRecord, true);
            }
        }
        return updatePlanProgress;
    }

    private static void a(WorkoutRecord workoutRecord) {
        LogUtil.a("Suggestion_SuggestionBinder", "setRefreshRedDotAndUpdateFitnessRecord");
        if (workoutRecord == null) {
            return;
        }
        if (workoutRecord.getSportRecordType() != 0) {
            ReleaseLogUtil.d("Suggestion_SuggestionBinder", "record is not main record.", workoutRecord.acquireWorkoutId(), " endTime:", Long.valueOf(workoutRecord.acquireExerciseTime()));
            return;
        }
        if (workoutRecord.isFitnessRecordFromDevice() || workoutRecord.isFitnessRecordFromTv()) {
            SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "HOME_HEALTH_SHOW_FITNESS_DOT", String.valueOf(true), new StorageParams());
        }
        ary.a().e("WORKOUT_FINISHED");
    }
}
