package defpackage;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.huawei.basefitnessadvice.callback.DataCallback;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.sport.utils.ResultUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.model.unite.GetRunLevelRsp;
import com.huawei.hwcloudmodel.model.unite.UserRunLevelData;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.AchieveDataApi;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class euo {
    private epo b;
    private mny c;
    private PlanApi d;
    private Plan e;

    private boolean e(int i, int i2) {
        if (i != 3) {
            if (i != 4) {
                if (i != 5) {
                    if (i != 6 || i2 >= 13785) {
                        return false;
                    }
                } else if (i2 >= 6659) {
                    return false;
                }
            } else if (i2 >= 3003) {
                return false;
            }
        } else if (i2 >= 1448) {
            return false;
        }
        return true;
    }

    public void a(final String str, final int i, final UiCallback<mny> uiCallback) {
        if (uiCallback == null) {
            LogUtil.h("Suggestion_PlanReportManager", "getTrainReport callback is null");
            return;
        }
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        this.d = planApi;
        if (planApi == null) {
            LogUtil.b("Suggestion_PlanReportManager", "createFitnessPlan, createPlan : planApi is null.");
            c(str, i, uiCallback);
        } else {
            ety.c().b(new UiCallback<epo>() { // from class: euo.4
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i2, String str2) {
                    LogUtil.h("Suggestion_PlanReportManager", "getDoingIntPlan fail errCode:", Integer.valueOf(i2), ",errInfo:", str2);
                    euo.this.c(str, i, (UiCallback<mny>) uiCallback);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public void onSuccess(epo epoVar) {
                    if (euo.this.b(epoVar)) {
                        LogUtil.a("Suggestion_PlanReportManager", "getCurrentPlan onSuccess data:", euo.this.b.getMetaInfo().getName(), euo.this.b.getPlanId());
                        if (!euo.this.b.getPlanId().equals(str)) {
                            euo.this.c(str, i, (UiCallback<mny>) uiCallback);
                            return;
                        }
                        euo euoVar = euo.this;
                        euoVar.e = euoVar.b.getCompatiblePlan();
                        euo.this.c();
                        euo.this.b(str, i, (UiCallback<mny>) uiCallback);
                        return;
                    }
                    LogUtil.h("Suggestion_PlanReportManager", "getTrainReport mCurrentPlan is null");
                    euo.this.c(str, i, (UiCallback<mny>) uiCallback);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        mny mnyVar = new mny();
        this.c = mnyVar;
        Plan plan = this.e;
        if (plan != null) {
            mnyVar.j(plan.acquireName());
            this.c.d(this.e.getPicture());
            this.c.c(this.e.getPbBeforePlan());
            this.c.e(this.e.getTargetTime());
            this.c.d(this.e.acquireGoal());
            this.c.h(UnitUtil.a("yyyy/MM/dd", ggl.b(this.e.acquireStartDate(), "yyyy-MM-dd") * 1000));
            this.c.a(UnitUtil.a("yyyy/MM/dd", ggl.b(this.e.getEndDate(), "yyyy-MM-dd") * 1000));
        }
        epo epoVar = this.b;
        if (epoVar == null) {
            LogUtil.h("Suggestion_PlanReportManager", "initTrainReport mIntPlan == null");
            return;
        }
        this.c.i(epoVar.getTimeZone());
        if (this.b.getPlanTimeInfo() != null) {
            long beginDate = this.b.getPlanTimeInfo().getBeginDate();
            long endDate = this.b.getPlanTimeInfo().getEndDate();
            LogUtil.a("Suggestion_PlanReportManager", "startTime - endTime:", Long.valueOf(beginDate), "--", Long.valueOf(endDate));
            this.c.b(beginDate);
            this.c.d(endDate);
        }
        if (this.b.getIntroduction() != null) {
            this.c.b(this.b.getIntroduction().acquireReportPageUrl());
            this.c.c(this.b.getIntroduction().acquireReportPageTahitiUrl());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str, int i, final UiCallback<mny> uiCallback) {
        LogUtil.a("Suggestion_PlanReportManager", "getReportFromCloud planType:", Integer.valueOf(i));
        if (i == IntPlan.PlanType.FIT_PLAN.getType()) {
            e(str, i, uiCallback);
        } else {
            eqa.a().getTrainingReport(str, new DataCallback() { // from class: euo.3
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i2, String str2) {
                    LogUtil.h("Suggestion_PlanReportManager", "getTrainingReport fail");
                    uiCallback.onFailure(i2, str2);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    euo.this.b(jSONObject, uiCallback);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(JSONObject jSONObject, UiCallback<mny> uiCallback) {
        if (jSONObject == null) {
            LogUtil.b("Suggestion_PlanReportManager", "onSuccess return null.");
            return;
        }
        LogUtil.c("Suggestion_PlanReportManager", "getTrainingReport onSuccess data", jSONObject.toString());
        String optString = jSONObject.optString("report");
        if (TextUtils.isEmpty(optString)) {
            LogUtil.h("Suggestion_PlanReportManager", "report isEmpty");
            uiCallback.onFailure(9999, ResultUtil.d(9999));
        } else {
            uiCallback.onSuccess((mny) new Gson().fromJson(optString, new TypeToken<mny>() { // from class: euo.2
            }.getType()));
        }
    }

    private void e(String str, int i, final UiCallback<mny> uiCallback) {
        eqa.a().getIntPlanReport(str, i, 2, new DataCallback() { // from class: euo.8
            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onFailure(int i2, String str2) {
                LogUtil.h("Suggestion_PlanReportManager", "getReportFromCloudToGeneral fail errorCode: ", Integer.valueOf(i2), str2);
                uiCallback.onFailure(i2, str2);
            }

            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onSuccess(JSONObject jSONObject) {
                euo.this.b(jSONObject, uiCallback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str, int i, final UiCallback<mny> uiCallback) {
        LogUtil.a("Suggestion_PlanReportManager", "get current plan report.");
        final int c2 = ase.c(this.e, System.currentTimeMillis()) - 1;
        LogUtil.a("Suggestion_PlanReportManager", "current plan weekNumber:", Integer.valueOf(c2), "planType:", Integer.valueOf(i));
        this.c.b(c2);
        c(str, i, new UiCallback<mny>() { // from class: euo.7
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i2, String str2) {
                euo.this.d(c2, (UiCallback<mny>) uiCallback);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onSuccess(mny mnyVar) {
                if (mnyVar == null || (mnyVar.q() != c2 && mnyVar.q() != 100)) {
                    euo.this.d(c2, (UiCallback<mny>) uiCallback);
                } else {
                    uiCallback.onSuccess(mnyVar);
                }
            }
        });
    }

    public void c(String str, UiCallback<mny> uiCallback) {
        if (uiCallback == null) {
            LogUtil.h("Suggestion_PlanReportManager", "buildFinalPlanReport callback is null");
            return;
        }
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        this.d = planApi;
        if (planApi == null) {
            LogUtil.b("Suggestion_PlanReportManager", "buildFinalPlanReport, createPlan : planApi is null.");
            uiCallback.onFailure(9999, null);
        } else {
            a(str, uiCallback);
        }
    }

    private void a(final String str, final UiCallback<mny> uiCallback) {
        ety.c().b(new UiCallback<epo>() { // from class: euo.6
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str2) {
                LogUtil.b("Suggestion_PlanReportManager", "buildFinalPlanReport fail with errorCode ", Integer.valueOf(i));
                uiCallback.onFailure(i, str2);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onSuccess(epo epoVar) {
                if (!euo.this.b(epoVar)) {
                    uiCallback.onSuccess(null);
                    return;
                }
                LogUtil.a("Suggestion_PlanReportManager", "getCurrentPlan onSuccess data:", euo.this.b.getMetaInfo().getName(), euo.this.b.getPlanId());
                if (euo.this.b.getPlanId().equals(str)) {
                    euo.this.c();
                    euo.this.c.b(100);
                    List<moa> planWeekDataList = euo.this.e.getPlanWeekDataList();
                    List<epp> d = euo.this.b.d();
                    if (koq.b(d)) {
                        LogUtil.h("Suggestion_PlanReportManager", "weekInfoList empty.");
                        uiCallback.onSuccess(null);
                        return;
                    }
                    ArrayList arrayList = new ArrayList(d.size());
                    ArrayList arrayList2 = new ArrayList(d.size());
                    int e = gib.e(euo.this.e.getStartTime() * 1000, System.currentTimeMillis());
                    if (euo.this.b.getPlanType().equals(IntPlan.PlanType.AI_RUN_PLAN)) {
                        euo.this.d(planWeekDataList, arrayList, arrayList2);
                    } else {
                        planWeekDataList = eve.d(d);
                        euo.this.c(d, arrayList, arrayList2);
                    }
                    euo.this.c.c(planWeekDataList);
                    euo.this.d(arrayList, arrayList2);
                    if (euo.this.b.getPlanType().equals(IntPlan.PlanType.AI_RUN_PLAN)) {
                        euo.this.d((UiCallback<mny>) uiCallback, e + 1);
                        return;
                    } else {
                        euo.this.c((UiCallback<mny>) uiCallback);
                        return;
                    }
                }
                uiCallback.onFailure(1001, "plan id different.");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(epo epoVar) {
        if (epoVar == null) {
            LogUtil.h("Suggestion_PlanReportManager", "isValidInitPlanData from cloud is null");
            return false;
        }
        this.e = epoVar.getCompatiblePlan();
        this.b = epoVar;
        if (epoVar.getCompatiblePlan() != null) {
            return true;
        }
        LogUtil.b("Suggestion_PlanReportManager", "mIntPlan.getCompatiblePlan() == null");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<epp> list, List<Integer> list2, List<Integer> list3) {
        Iterator<epp> it = list.iterator();
        while (it.hasNext()) {
            List<epl> b = it.next().b();
            if (koq.b(b)) {
                list2.add(0);
                list3.add(0);
            } else {
                b(list2, list3, b);
            }
        }
    }

    private void b(List<Integer> list, List<Integer> list2, List<epl> list3) {
        int i = 0;
        int i2 = 0;
        for (epl eplVar : list3) {
            if (eplVar != null && !koq.b(eplVar.d())) {
                i++;
                if (eplVar.getPunchFlag() == 1) {
                    i2++;
                }
            }
        }
        list.add(Integer.valueOf(i));
        list2.add(Integer.valueOf(i2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<moa> list, List<Integer> list2, List<Integer> list3) {
        for (moa moaVar : list) {
            int i = moaVar.i();
            int i2 = 0;
            if (koq.b(moaVar.j())) {
                list2.add(Integer.valueOf(i));
                list3.add(0);
            } else {
                for (mnu mnuVar : moaVar.j()) {
                    i = ase.a(mnuVar, i);
                    if (mnuVar.e()) {
                        i2++;
                    }
                }
                list2.add(Integer.valueOf(i));
                list3.add(Integer.valueOf(i2));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final UiCallback<mny> uiCallback, final int i) {
        Date date = new Date(this.c.o() * 1000);
        Date date2 = new Date(System.currentTimeMillis());
        LogUtil.a("Suggestion_PlanReportManager", "start:", date.toString(), " today:", date2.toString());
        eme.b().getUserRunLevelDataByRq(ggl.a(date), ggl.a(date2), 0, new IBaseResponseCallback() { // from class: euw
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i2, Object obj) {
                euo.this.a(i, uiCallback, i2, obj);
            }
        });
    }

    /* synthetic */ void a(int i, UiCallback uiCallback, int i2, Object obj) {
        if (i2 == 200 && (obj instanceof GetRunLevelRsp)) {
            GetRunLevelRsp getRunLevelRsp = (GetRunLevelRsp) obj;
            if (c(getRunLevelRsp)) {
                b(getRunLevelRsp.getUserRunLevelData());
                this.e.setPbCurrent(this.c.f());
                this.c.e(b(i));
                c((UiCallback<mny>) uiCallback);
                return;
            }
            a((UiCallback<mny>) uiCallback, i);
            return;
        }
        LogUtil.h("Suggestion_PlanReportManager", "can not get rq data.", Integer.valueOf(i2));
        a((UiCallback<mny>) uiCallback, i);
    }

    private void a(final UiCallback<mny> uiCallback, final int i) {
        int acquireGoal = this.e.acquireGoal();
        if (acquireGoal > 0 && acquireGoal <= 6) {
            int[] c2 = c(acquireGoal);
            int i2 = c2[0];
            int i3 = c2[1];
            LogUtil.a("Suggestion_PlanReportManager", "pbType:", Integer.valueOf(i2), " pbTime:", Integer.valueOf(i3), "weekCount: ", Integer.valueOf(this.e.getWeekCount()));
            this.d.getAchievementForecast(i2, i3, this.e.acquireGoal(), this.e.getWeekCount(), new UiCallback<mog>() { // from class: euo.10
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i4, String str) {
                    ReleaseLogUtil.c("Suggestion_PlanReportManager", "getGoalForecast: ", str);
                    euo.this.c.a(euo.this.e.getPbCurrent());
                    euo.this.c.e(euo.this.b(i));
                    euo.this.c((UiCallback<mny>) uiCallback);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(mog mogVar) {
                    if (mogVar == null) {
                        LogUtil.b("Suggestion_PlanReportManager", "getGoalForecast onSuccess data is null.");
                        return;
                    }
                    LogUtil.a("Suggestion_PlanReportManager", "TrainingPredictionBean:", mogVar);
                    euo.this.c.a(mogVar.b());
                    euo.this.e.setPbCurrent(mogVar.b());
                    euo.this.c.e(euo.this.b(i));
                    euo.this.c((UiCallback<mny>) uiCallback);
                }
            });
            return;
        }
        this.c.e(b(i));
        c(uiCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String b(int i) {
        int pbBeforePlan = this.e.getPbBeforePlan();
        int pbCurrent = this.e.getPbCurrent();
        boolean z = ((double) pbCurrent) < ((double) pbBeforePlan) * 0.97d;
        LogUtil.a("Suggestion_PlanReportManager", "pbCurrent:", Integer.valueOf(pbCurrent), " pbBeforePlan:", Integer.valueOf(pbBeforePlan));
        int c2 = ase.c(this.e);
        float f = i == 0 ? 0.0f : c2 / i;
        LogUtil.a("Suggestion_PlanReportManager", "curWeekNo:", Integer.valueOf(i), "totalClocks:", Integer.valueOf(c2), "avgClock: ", Float.valueOf(f));
        double d = f;
        double d2 = (((((-0.0132d) * d) * d) + (d * 0.2635d)) - 0.0493d) * 100.0d;
        double d3 = d2 < 0.0d ? 0.0d : d2;
        String d4 = d(i, d3, e(this.e.acquireGoal(), z, f, i, e(this.e.acquireGoal(), pbBeforePlan)));
        LogUtil.a("Suggestion_PlanReportManager", "percent =", Double.valueOf(d3), "final coach advice:", d4);
        return d4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, final UiCallback<mny> uiCallback) {
        LogUtil.a("Suggestion_PlanReportManager", "generatePlanReport:", Integer.valueOf(i));
        if (i < 1) {
            uiCallback.onFailure(1001, ResultUtil.d(1001));
            return;
        }
        List<moa> planWeekDataList = this.e.getPlanWeekDataList();
        if (koq.b(planWeekDataList)) {
            LogUtil.h("Suggestion_PlanReportManager", "generatePlanReport weekDataBeans is empty");
            return;
        }
        ArrayList arrayList = new ArrayList(planWeekDataList.size());
        ArrayList arrayList2 = new ArrayList(planWeekDataList.size());
        final moa e = e(i, planWeekDataList, arrayList, arrayList2);
        if (e == null) {
            LogUtil.h("Suggestion_PlanReportManager", "getLaseWeekDataBean return null.");
            return;
        }
        this.c.c(planWeekDataList);
        final mnx mnxVar = new mnx();
        mnxVar.c(e.c());
        long startTime = this.e.getStartTime();
        long e2 = e.e();
        e(startTime * 1000, e2 * 1000, arrayList, arrayList2, new IBaseResponseCallback() { // from class: eus
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i2, Object obj) {
                euo.this.c(mnxVar, e, uiCallback, i2, obj);
            }
        });
    }

    /* synthetic */ void c(mnx mnxVar, moa moaVar, final UiCallback uiCallback, int i, Object obj) {
        d(mnxVar, moaVar.a(), moaVar.e(), new IBaseResponseCallback() { // from class: eur
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i2, Object obj2) {
                euo.this.a(uiCallback, i2, obj2);
            }
        });
    }

    /* synthetic */ void a(final UiCallback uiCallback, int i, Object obj) {
        d(new UiCallback<String>() { // from class: euo.9
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i2, String str) {
                LogUtil.a("Suggestion_PlanReportManager", "getCoachAdvice:", Integer.valueOf(i2), str);
                euo.this.c.e("");
                euo.this.e((UiCallback<mny>) uiCallback);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(String str) {
                LogUtil.a("Suggestion_PlanReportManager", "getCoachAdvice:", str);
                euo.this.c.e(str);
                euo.this.e((UiCallback<mny>) uiCallback);
            }
        });
    }

    private moa e(int i, List<moa> list, List<Integer> list2, List<Integer> list3) {
        moa moaVar = null;
        for (moa moaVar2 : list) {
            if (moaVar2.f() == i) {
                LogUtil.a("Suggestion_PlanReportManager", "weekDataBean:", moaVar2.toString());
                moaVar = moaVar2;
            }
            int i2 = moaVar2.i();
            int i3 = 0;
            if (koq.c(moaVar2.j())) {
                for (mnu mnuVar : moaVar2.j()) {
                    i2 = ase.a(mnuVar, i2);
                    if (moaVar2.f() <= i && mnuVar.e()) {
                        i3++;
                    }
                }
            }
            list2.add(Integer.valueOf(i2));
            list3.add(Integer.valueOf(i3));
        }
        return moaVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<Integer> list, List<Integer> list2) {
        mnz mnzVar = new mnz();
        LogUtil.a("Suggestion_PlanReportManager", "setTotalStatistics rate:", Float.valueOf(c("progress")));
        mnzVar.a(c("progress"));
        mnzVar.a(list);
        mnzVar.b(list2);
        LogUtil.a("Suggestion_PlanReportManager", "setTotalStatistics totalDuration: ", Float.valueOf(c("duration")), "totalActualDistance: ", Float.valueOf(c("distance")), "totalActualCalorie: ", Float.valueOf(c("calorie")));
        mnzVar.a(Math.round(c("duration") / 60000.0f));
        mnzVar.e(c("distance") / 1000.0f);
        mnzVar.c(c("calorie"));
        this.c.d(mnzVar);
    }

    private void e(long j, long j2, List<Integer> list, List<Integer> list2, IBaseResponseCallback iBaseResponseCallback) {
        mnz mnzVar = new mnz();
        float d = eve.d(this.b, j2);
        LogUtil.a("Suggestion_PlanReportManager", "setTotalStatistics rate:", Float.valueOf(d));
        mnzVar.a(d);
        mnzVar.a(list);
        mnzVar.b(list2);
        this.c.d(mnzVar);
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("Suggestion_PlanReportManager", "lastWeekStatistics courseApi is null");
            return;
        }
        String j3 = gib.j(j);
        String j4 = gib.j(j2);
        LogUtil.a("Suggestion_PlanReportManager", "startDate:", j3, " endDate:", j4);
        List<WorkoutRecord> workoutRecords = courseApi.getWorkoutRecords(this.e.acquireId(), j3, j4);
        if (koq.b(workoutRecords)) {
            LogUtil.h("Suggestion_PlanReportManager", "workoutRecords is null");
            asb.d().d(j, j2, new c(this, iBaseResponseCallback, 101));
            return;
        }
        float f = 0.0f;
        int i = 0;
        for (WorkoutRecord workoutRecord : workoutRecords) {
            if (!workoutRecord.isFitnessRecordFromDevice() && !workoutRecord.isRunWorkout()) {
                i += workoutRecord.getDuration();
                f += workoutRecord.acquireActualCalorie();
            }
            LogUtil.a("Suggestion_PlanReportManager", "record:", workoutRecord.acquireWorkoutName(), " duration:", Integer.valueOf(workoutRecord.getDuration()), " calories:", Float.valueOf(workoutRecord.acquireActualCalorie()), " isFitnessRecordFromDevice:", Boolean.valueOf(workoutRecord.isFitnessRecordFromDevice()), " isRunWorkout:", Boolean.valueOf(workoutRecord.isRunWorkout()));
        }
        mnzVar.a(i);
        mnzVar.c(f);
        asb.d().d(j, j2, new c(this, iBaseResponseCallback, 101));
    }

    private float c(String str) {
        epo epoVar = this.b;
        if (epoVar == null || epoVar.getStat(str) == null) {
            LogUtil.h("Suggestion_PlanReportManager", "getTargetValue null", str);
            return 0.0f;
        }
        return ((Float) this.b.getStat(str).getValue()).floatValue();
    }

    static class c extends DataCallback {

        /* renamed from: a, reason: collision with root package name */
        WeakReference<euo> f12337a;
        IBaseResponseCallback b;
        int d;

        c(euo euoVar, IBaseResponseCallback iBaseResponseCallback, int i) {
            this.f12337a = new WeakReference<>(euoVar);
            this.b = iBaseResponseCallback;
            this.d = i;
        }

        @Override // com.huawei.basefitnessadvice.callback.DataCallback
        public void onFailure(int i, String str) {
            LogUtil.a("Suggestion_PlanReportManager", "RunDataCallback onFail,", Integer.valueOf(i), str);
            euo euoVar = this.f12337a.get();
            if (euoVar == null) {
                this.b.d(0, null);
                return;
            }
            if (this.d == 102) {
                euoVar.c.h().d(Math.round(r1.a() / 60000.0f));
            }
            if (this.d == 101) {
                euoVar.c.s().a(Math.round(r6.c() / 60000.0f));
            }
            this.b.d(i, null);
        }

        @Override // com.huawei.basefitnessadvice.callback.DataCallback
        public void onSuccess(JSONObject jSONObject) {
            LogUtil.a("Suggestion_PlanReportManager", "RunDataCallback onSuccess,", jSONObject.toString());
            euo euoVar = this.f12337a.get();
            if (euoVar == null) {
                this.b.d(0, null);
                return;
            }
            double optDouble = jSONObject.optDouble("distance");
            double optDouble2 = jSONObject.optDouble("duration");
            double optDouble3 = jSONObject.optDouble("calorie");
            if (this.d == 102) {
                mnx h = euoVar.c.h();
                h.d(Math.round((h.a() + ((int) optDouble2)) / 60000.0f));
                h.b(h.c() + (optDouble / 1000.0d));
                h.c(h.e() + ((float) optDouble3));
            }
            if (this.d == 101) {
                mnz s = euoVar.c.s();
                s.a(Math.round((s.c() + ((int) optDouble2)) / 60000.0f));
                s.e(s.e() + ((float) (optDouble / 1000.0d)));
                s.c(s.d() + ((float) optDouble3));
            }
            this.b.d(0, null);
        }
    }

    private void d(mnx mnxVar, long j, long j2, IBaseResponseCallback iBaseResponseCallback) {
        List<WorkoutRecord> b = b(mnxVar, j, j2, iBaseResponseCallback);
        if (b == null) {
            return;
        }
        float f = 0.0f;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        for (WorkoutRecord workoutRecord : b) {
            if (!workoutRecord.isFitnessRecordFromDevice() && !workoutRecord.isRunWorkout()) {
                i6 += workoutRecord.getDuration();
                f += workoutRecord.acquireActualCalorie();
            }
            LogUtil.a("Suggestion_PlanReportManager", "record:", workoutRecord.acquireWorkoutName(), " duration:", Integer.valueOf(workoutRecord.getDuration()), " calories:", Float.valueOf(workoutRecord.acquireActualCalorie()), " distance:", Float.valueOf(workoutRecord.acquireActualDistance()));
            List<Integer> intensityZone = workoutRecord.getIntensityZone();
            if (intensityZone != null && intensityZone.size() == 5) {
                i += intensityZone.get(0).intValue();
                i2 += intensityZone.get(1).intValue();
                i3 += intensityZone.get(2).intValue();
                i4 += intensityZone.get(3).intValue();
                i5 += intensityZone.get(4).intValue();
            }
        }
        mnxVar.d(Arrays.asList(Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5)));
        mnxVar.d(i6);
        mnxVar.c(f);
        this.c.d(mnxVar);
        asb.d().d(j * 1000, 1000 * j2, new c(this, iBaseResponseCallback, 102));
    }

    private List<WorkoutRecord> b(mnx mnxVar, long j, long j2, IBaseResponseCallback iBaseResponseCallback) {
        long j3 = j * 1000;
        String j4 = gib.j(j3);
        long j5 = j2 * 1000;
        String j6 = gib.j(j5);
        LogUtil.a("Suggestion_PlanReportManager", "startDate:", j4, " endDate:", j6);
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("Suggestion_PlanReportManager", "lastWeekStatistics courseApi is null");
            return null;
        }
        this.c.d(mnxVar);
        List<WorkoutRecord> workoutRecords = courseApi.getWorkoutRecords(this.e.acquireId(), j4, j6);
        if (!koq.b(workoutRecords)) {
            return workoutRecords;
        }
        LogUtil.h("Suggestion_PlanReportManager", "workoutRecords is null");
        asb.d().d(j3, j5, new c(this, iBaseResponseCallback, 102));
        return null;
    }

    private void d(UiCallback<String> uiCallback) {
        PlanApi planApi = this.d;
        if (planApi == null) {
            LogUtil.b("Suggestion_PlanReportManager", "getCoachAdvice : planApi is null.");
        } else {
            planApi.setPlanType(0);
            this.d.getCoachAdvice(1, this.e.acquireId(), null, true, uiCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(UiCallback<mny> uiCallback) {
        Date date = new Date(System.currentTimeMillis());
        int a2 = ggl.a(date);
        LogUtil.a("Suggestion_PlanReportManager", "today:", date.toString());
        eme.b().getUserRunLevelDataByRq(a2, a2, 0, new a(this, uiCallback));
    }

    static class a implements IBaseResponseCallback {
        private WeakReference<euo> d;
        private UiCallback<mny> e;

        a(euo euoVar, UiCallback<mny> uiCallback) {
            this.d = new WeakReference<>(euoVar);
            this.e = uiCallback;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            euo euoVar = this.d.get();
            if (euoVar == null || this.e == null) {
                LogUtil.h("Suggestion_PlanReportManager", "RqRunningData onResponse activity or mHandler is null");
                return;
            }
            if (i == 200 && (obj instanceof GetRunLevelRsp)) {
                GetRunLevelRsp getRunLevelRsp = (GetRunLevelRsp) obj;
                if (euoVar.c(getRunLevelRsp)) {
                    euoVar.b(getRunLevelRsp.getUserRunLevelData());
                    euoVar.c((UiCallback<mny>) null);
                    euoVar.e(euoVar.c.f());
                    this.e.onSuccess(euoVar.c);
                    return;
                }
                euoVar.a(this.e);
                return;
            }
            LogUtil.h("Suggestion_PlanReportManager", "can not get rq data.", Integer.valueOf(i));
            euoVar.a(this.e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(UserRunLevelData userRunLevelData) {
        int acquireGoal = this.e.acquireGoal();
        LogUtil.a("Suggestion_PlanReportManager", "setPredictData:", Integer.valueOf(acquireGoal));
        if (acquireGoal == 3) {
            this.c.a(userRunLevelData.getTimeForFiveKm());
            return;
        }
        if (acquireGoal == 4) {
            this.c.a(userRunLevelData.getTimeForTenKm());
        } else if (acquireGoal == 5) {
            this.c.a(userRunLevelData.getHalfMarathon());
        } else {
            if (acquireGoal != 6) {
                return;
            }
            this.c.a(userRunLevelData.getMarathon());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c(GetRunLevelRsp getRunLevelRsp) {
        if (getRunLevelRsp != null && getRunLevelRsp.getUserRunLevelData() != null) {
            UserRunLevelData userRunLevelData = getRunLevelRsp.getUserRunLevelData();
            LogUtil.a("Suggestion_PlanReportManager", "FiveKm:", Integer.valueOf(userRunLevelData.getTimeForFiveKm()), " TenKm:", Integer.valueOf(userRunLevelData.getTimeForTenKm()), " HalfMarathon:", Integer.valueOf(userRunLevelData.getHalfMarathon()), " Marathon:", Integer.valueOf(userRunLevelData.getMarathon()));
            if (userRunLevelData.getTimeForFiveKm() > 0 && userRunLevelData.getTimeForTenKm() > 0 && userRunLevelData.getHalfMarathon() > 0 && userRunLevelData.getMarathon() > 0) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final UiCallback<mny> uiCallback) {
        if (this.d == null) {
            LogUtil.b("Suggestion_PlanReportManager", "predictAchievement : planApi is null.");
            return;
        }
        int acquireGoal = this.e.acquireGoal();
        if (acquireGoal > 0 && acquireGoal <= 6) {
            int[] c2 = c(acquireGoal);
            int i = c2[0];
            int i2 = c2[1];
            LogUtil.a("Suggestion_PlanReportManager", "pbType:", Integer.valueOf(i), " pbTime:", Integer.valueOf(i2));
            this.d.getAchievementForecast(i, i2, this.e.acquireGoal(), this.e.getWeekCount(), new UiCallback<mog>() { // from class: euo.12
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i3, String str) {
                    LogUtil.b("Suggestion_PlanReportManager", "getGoalForecast: ", str);
                    euo.this.c((UiCallback<mny>) null);
                    uiCallback.onSuccess(euo.this.c);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(mog mogVar) {
                    if (mogVar == null) {
                        LogUtil.b("Suggestion_PlanReportManager", "getGoalForecast onSuccess data is null.");
                        return;
                    }
                    LogUtil.a("Suggestion_PlanReportManager", "TrainingPredictionBean:", Integer.valueOf(mogVar.b()));
                    euo.this.c.a(mogVar.b());
                    euo.this.c((UiCallback<mny>) null);
                    euo.this.e(mogVar.b());
                    uiCallback.onSuccess(euo.this.c);
                }
            });
            return;
        }
        c((UiCallback<mny>) null);
        uiCallback.onSuccess(this.c);
    }

    private int[] c(int i) {
        LogUtil.a("Suggestion_PlanReportManager", "goal=", Integer.valueOf(i));
        int[] iArr = new int[2];
        int pbCurrent = this.e.getPbCurrent();
        if (pbCurrent == 0) {
            pbCurrent = this.e.getPbBeforePlan();
        }
        iArr[0] = i;
        iArr[1] = pbCurrent;
        AchieveDataApi achieveDataApi = (AchieveDataApi) Services.a("PluginAchievement", AchieveDataApi.class);
        if (achieveDataApi == null) {
            LogUtil.b("Suggestion_PlanReportManager", "can not get achieveDataApi.");
            return iArr;
        }
        mdd singleDayData = achieveDataApi.getSingleDayData();
        if (singleDayData == null) {
            return iArr;
        }
        mcy[] d = d(singleDayData);
        mcy mcyVar = null;
        int i2 = i;
        while (i > 1) {
            int i3 = i - 1;
            mcy mcyVar2 = d[i3];
            if (mcyVar2 != null) {
                if (mcyVar == null) {
                    i2 = i;
                    mcyVar = mcyVar2;
                } else if (mcyVar.d() < d[i3].d()) {
                    mcyVar = d[i3];
                    i2 = i;
                }
            }
            i--;
        }
        if (mcyVar != null && mcyVar.d() > this.e.getStartTime() * 1000) {
            int round = (int) Math.round(mcyVar.e());
            iArr[0] = i2;
            iArr[1] = round;
        }
        return iArr;
    }

    private mcy[] d(mdd mddVar) {
        mcy[] mcyVarArr = new mcy[6];
        mcyVarArr[1] = mddVar.d();
        mcyVarArr[2] = mddVar.b();
        mcyVarArr[3] = mddVar.a();
        mcyVarArr[4] = mddVar.e();
        mcyVarArr[5] = mddVar.c();
        return mcyVarArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        etx.b().e();
        Plan currentPlan = etr.b().getCurrentPlan();
        if (currentPlan == null) {
            LogUtil.b("Suggestion_PlanReportManager", "getAchievementForecast onSuccess plan is null.");
        } else {
            currentPlan.setPbCurrent(i);
            ett.i().n().d(arx.a(), currentPlan, currentPlan.acquireVersion());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final UiCallback<mny> uiCallback) {
        a();
        String json = new GsonBuilder().create().toJson(this.c);
        LogUtil.a("Suggestion_PlanReportManager", "uploadReport:", json, "planType:", Integer.valueOf(this.b.getPlanType().getType()));
        if (this.b.getPlanType() == IntPlan.PlanType.FIT_PLAN) {
            d(json, uiCallback);
        } else {
            this.d.updatePlanReport(this.e.acquireId(), json, new UiCallback<String>() { // from class: euo.1
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    UiCallback uiCallback2 = uiCallback;
                    if (uiCallback2 != null) {
                        uiCallback2.onSuccess(euo.this.c);
                    }
                    LogUtil.b("Suggestion_PlanReportManager", "updatePlanReport failed.", Integer.valueOf(i), str);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onSuccess(String str) {
                    UiCallback uiCallback2 = uiCallback;
                    if (uiCallback2 != null) {
                        uiCallback2.onSuccess(euo.this.c);
                    }
                    LogUtil.b("Suggestion_PlanReportManager", "updatePlanReport success.");
                }
            });
        }
    }

    private void a() {
        mny mnyVar;
        if (this.e == null || (mnyVar = this.c) == null) {
            LogUtil.b("Suggestion_PlanReportManager", "setCurrentWeekEndTime bean null");
            return;
        }
        if (mnyVar.q() == 100) {
            this.c.d(this.b.getPlanTimeInfo().getEndDate());
            return;
        }
        int e = ase.e(this.e) - 1;
        LogUtil.a("Suggestion_PlanReportManager", "setCurrentWeekEndTime lastWeekNo", Integer.valueOf(e));
        for (moa moaVar : this.e.getPlanWeekDataList()) {
            if (moaVar != null && moaVar.f() == e) {
                LogUtil.a("Suggestion_PlanReportManager", "setCurrentWeekEndTime weekNum:", Integer.valueOf(moaVar.f()));
                this.c.d(moaVar.e());
                return;
            }
        }
    }

    private void d(String str, final UiCallback<mny> uiCallback) {
        LogUtil.a("Suggestion_PlanReportManager", "uploadReportToGeneral report size:", Integer.valueOf(str.length()));
        this.d.updateIntPlanReport(this.b.getPlanId(), this.b.getPlanType().getType(), 2, str, System.currentTimeMillis(), new UiCallback<String>() { // from class: euo.5
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str2) {
                UiCallback uiCallback2 = uiCallback;
                if (uiCallback2 != null) {
                    uiCallback2.onFailure(i, str2);
                }
                LogUtil.b("Suggestion_PlanReportManager", "uploadReportToGeneral failed.", Integer.valueOf(i), str2);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(String str2) {
                UiCallback uiCallback2 = uiCallback;
                if (uiCallback2 != null) {
                    uiCallback2.onSuccess(euo.this.c);
                }
                LogUtil.a("Suggestion_PlanReportManager", "uploadReportToGeneral success.");
            }
        });
    }

    private String d(int i, double d, String str) {
        return ffy.b(R.plurals._2130903314_res_0x7f030112, i, Integer.valueOf(i), UnitUtil.e(d, 2, 0), str);
    }

    private String e(int i, boolean z, float f, int i2, boolean z2) {
        if (i == 11) {
            return BaseApplication.getContext().getResources().getString(R.string._2130844727_res_0x7f021c37);
        }
        if (i == 12) {
            return BaseApplication.getContext().getResources().getString(R.string._2130844728_res_0x7f021c38);
        }
        if (z) {
            return BaseApplication.getContext().getResources().getString(R.string._2130844729_res_0x7f021c39);
        }
        if (f < 3.0f) {
            return BaseApplication.getContext().getResources().getString(R.string._2130844730_res_0x7f021c3a);
        }
        if (i2 < 6) {
            return BaseApplication.getContext().getResources().getString(R.string._2130844731_res_0x7f021c3b);
        }
        if (z2) {
            return BaseApplication.getContext().getResources().getString(R.string._2130844733_res_0x7f021c3d);
        }
        return BaseApplication.getContext().getResources().getString(R.string._2130844732_res_0x7f021c3c);
    }
}
