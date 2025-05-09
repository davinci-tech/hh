package com.huawei.health.suggestion.h5pro;

import com.huawei.health.h5pro.service.anotation.H5ProMethod;
import com.huawei.health.h5pro.service.anotation.H5ProService;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.h5pro.SeriesCourseH5Cbk;
import defpackage.caj;
import defpackage.fnz;
import defpackage.fyr;
import defpackage.moe;
import defpackage.squ;
import health.compact.a.Services;

@H5ProService(name = "FitnessService", users = {"", "", "9ea3f06fe2dea3e2c7c21ea1ba1e07c370a786c68417a4a96cb986576883e10f"})
/* loaded from: classes.dex */
public class AiFitnessServiceH5Repository {
    private static final String TAG = "AiFitnessServiceH5Repository";

    @H5ProMethod(name = "saveTrainingResult")
    public static void saveTrainingResult(WorkoutRecord workoutRecord, SeriesCourseH5Cbk<Integer> seriesCourseH5Cbk) {
        if (seriesCourseH5Cbk == null) {
            LogUtil.h(TAG, "saveTrainingResult callback == null");
            return;
        }
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.h(TAG, "saveTrainingResult : planApi is null");
            seriesCourseH5Cbk.onFailure(-1, "PlanApi is null");
        }
        LogUtil.a(TAG, "H5 begin saveTrainingResult");
        planApi.updatePlanProgress(workoutRecord);
        seriesCourseH5Cbk.onSuccess(0);
        fyr.d(0, fyr.b(workoutRecord.startTime()));
    }

    @H5ProMethod(name = "getVideoLocalUrl")
    public static void getVideoLocalUrl(String str, SeriesCourseH5Cbk<String> seriesCourseH5Cbk) {
        if (seriesCourseH5Cbk == null) {
            LogUtil.h(TAG, "getVideoLocalUrl callback == null");
        } else {
            seriesCourseH5Cbk.onSuccess(squ.l(str));
        }
    }

    @H5ProMethod(name = "jumpSportNoun")
    public static void jumpSportNounPage(String str, SeriesCourseH5Cbk<Integer> seriesCourseH5Cbk) {
        caj.a().a(str);
        seriesCourseH5Cbk.onSuccess(0);
    }

    @H5ProMethod(name = "calcWorkoutFullCalorie")
    public static void calcWorkoutFullCalorie(int i, SeriesCourseH5Cbk<Integer> seriesCourseH5Cbk) {
        seriesCourseH5Cbk.onSuccess(Integer.valueOf(Math.round(moe.b(i * fnz.e()))));
    }
}
