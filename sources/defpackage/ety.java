package defpackage;

import android.os.Handler;
import android.os.Looper;
import com.google.gson.Gson;
import com.huawei.basefitnessadvice.callback.DataCallback;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.basefitnessadvice.model.intplan.IntAction;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.basefitnessadvice.model.intplan.StatInfo;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.plan.model.UserFitnessPlanInfo;
import com.huawei.health.plan.model.intplan.IntPlanBean;
import com.huawei.health.plan.model.intplan.LeavePlanCalendarBean;
import com.huawei.health.plan.model.intplan.ReplacePlanBean;
import com.huawei.health.plan.model.intplan.UserProfileBean;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.sport.utils.ResultUtil;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.health.userprofilemgr.model.BaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.pluginfitnessadvice.FitnessPackageInfo;
import com.huawei.pluginfitnessadvice.plandata.CreateRunPlanParams;
import com.huawei.pluginfitnessadvice.plandata.UserInfoBean;
import com.huawei.up.model.UserInfomation;
import com.huawei.utils.RiskBiAnalytics;
import defpackage.esl;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.utils.StringUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class ety {

    /* renamed from: a, reason: collision with root package name */
    private volatile epo f12304a;
    private Handler c;

    static class c {
        static ety b = new ety();
    }

    private ety() {
        this.c = new Handler(Looper.getMainLooper());
    }

    public static ety c() {
        return c.b;
    }

    public void b(UiCallback<epo> uiCallback) {
        if (!eve.e() || Utils.o()) {
            String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
            if (accountInfo == null) {
                LogUtil.b("Suggestion_IntPlanManager", "getCurrentIntPlan createPlanFromDb userId == null");
                uiCallback.onFailure(20001, ResultUtil.d(20001));
                return;
            }
            Plan createPlanFromDb = etr.b().createPlanFromDb(accountInfo);
            if ((createPlanFromDb != null && createPlanFromDb.getPlanCategory() == 0) || !eve.e()) {
                b(createPlanFromDb);
                uiCallback.onSuccess(this.f12304a);
                return;
            }
        }
        if (g() || etx.b().d()) {
            ReleaseLogUtil.e("Suggestion_IntPlanManager", "getDoingPlan plan from cloud begin");
            d(uiCallback);
        } else {
            uiCallback.onSuccess(this.f12304a);
        }
    }

    public epo a() {
        if (g()) {
            return null;
        }
        return this.f12304a;
    }

    epo b(UserFitnessPlanInfo userFitnessPlanInfo) {
        this.f12304a = eve.c(userFitnessPlanInfo);
        return this.f12304a;
    }

    public void d(CreateRunPlanParams createRunPlanParams, final UiCallback<Plan> uiCallback) {
        if (uiCallback == null || createRunPlanParams == null) {
            LogUtil.b("Suggestion_IntPlanManager", "createPlan: callback or runPlanUserInfoBean is null.");
        } else {
            etr.b().createIntelligentRunPlan(createRunPlanParams, new UiCallback<Plan>() { // from class: ety.3
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    ReleaseLogUtil.d("Suggestion_IntPlanManager", "createIntelligentRunPlan fail errorCode ", Integer.valueOf(i), "errorInfo ", str);
                    RiskBiAnalytics.c(RiskBiAnalytics.FitPLANRiskType.RISK_TYPE_CREATE_PLAN.value(), "createIntelligentRunPlan fail errorCode ", Integer.valueOf(i), "errorInfo ", str);
                    uiCallback.onFailure(i, str);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onSuccess(Plan plan) {
                    LogUtil.b("Suggestion_IntPlanManager", "createIntelligentRunPlan success. ", plan);
                    ety.this.f12304a = eve.a(plan);
                    ety.this.f12304a.e.d(System.currentTimeMillis() / 1000);
                    uiCallback.onSuccess(plan);
                    ary.a().e("PLAN_CREATE");
                    ase.d(ety.this.f12304a, "5", new int[0]);
                }
            });
        }
    }

    private void d(final UiCallback<epo> uiCallback) {
        eqa.a().getDoingIntPlan(new DataCallback() { // from class: ety.11
            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onFailure(int i, String str) {
                ReleaseLogUtil.c("Suggestion_IntPlanManager", "getDoingPlan fail with errorCode ", Integer.valueOf(i));
                uiCallback.onFailure(i, str);
            }

            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onSuccess(JSONObject jSONObject) {
                if (jSONObject == null) {
                    ReleaseLogUtil.e("Suggestion_IntPlanManager", "getDoingPlan from cloud is null");
                    ety.this.f();
                    ety.this.e();
                    ety.this.d((Plan) null);
                    uiCallback.onSuccess(null);
                    return;
                }
                LogUtil.c("Suggestion_IntPlanManager", "getDoingPlan success with data", jSONObject);
                int optInt = jSONObject.optInt("planType", IntPlan.PlanType.NA_PLAN.getType());
                if (optInt != IntPlan.PlanType.NA_PLAN.getType()) {
                    ety.this.c(optInt, jSONObject, uiCallback);
                    ase.t(ety.this.f12304a);
                    if (ety.this.g()) {
                        return;
                    }
                    etx.b().c();
                    if (ety.this.f12304a.e != null) {
                        etl.a(ety.this.f12304a, true, ety.this.f12304a.e.getRemindTime());
                        return;
                    }
                    return;
                }
                ReleaseLogUtil.d("Suggestion_IntPlanManager", "getDoingPlan get empty planType");
                ety.this.f();
                ety.this.e();
                ety.this.d((Plan) null);
                uiCallback.onSuccess(null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (g()) {
            ReleaseLogUtil.d("Suggestion_IntPlanManager", "syncClodNoAiFitPlan mIntPlan null true");
        } else if (IntPlan.PlanType.AI_FITNESS_PLAN.equals(this.f12304a.getPlanType())) {
            ReleaseLogUtil.d("Suggestion_IntPlanManager", "syncClodNoAiFitPlan send finish plan");
            euv.d(IntPlan.PlanType.AI_FITNESS_PLAN.getType());
            euv.c(this.f12304a.getPlanId());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, JSONObject jSONObject, UiCallback<epo> uiCallback) {
        if (i == IntPlan.PlanType.FIT_PLAN.getType()) {
            String optString = jSONObject.optString("fitnessPlan");
            if (!StringUtils.g(optString)) {
                this.f12304a = eve.c((UserFitnessPlanInfo) new Gson().fromJson(optString, UserFitnessPlanInfo.class));
                b();
            }
            uiCallback.onSuccess(this.f12304a);
            return;
        }
        if (i == IntPlan.PlanType.AI_FITNESS_PLAN.getType()) {
            c(jSONObject.optJSONObject("intReducingPlan"));
            uiCallback.onSuccess(this.f12304a);
        } else if (i == IntPlan.PlanType.AI_RUN_PLAN.getType()) {
            b(jSONObject.optJSONObject("smartRunningPlan"), uiCallback);
        } else if (i == IntPlan.PlanType.RUN_PLAN.getType()) {
            Plan a2 = ffm.a(jSONObject.optJSONObject("generalRunningPlan"), "planInfo");
            b(a2);
            uiCallback.onSuccess(this.f12304a);
            d(a2);
        }
    }

    private void b() {
        String j = gib.j(this.f12304a.e.getBeginDate() * 1000);
        String j2 = gib.j(this.f12304a.e.getEndDate() * 1000);
        LogUtil.a("Suggestion_IntPlanManager", "getFitnessStatFromLocal startTime = ", j, " endTime = ", j2);
        List<WorkoutRecord> workoutRecords = etr.b().getWorkoutRecords(this.f12304a.getPlanId(), j, j2);
        LogUtil.a("Suggestion_IntPlanManager", "getFitnessStatFromLocal workoutRecords size = ", Integer.valueOf(workoutRecords.size()));
        HashSet hashSet = new HashSet(workoutRecords.size());
        float f = 0.0f;
        float f2 = 0.0f;
        float f3 = 0.0f;
        for (int i = 0; i < workoutRecords.size(); i++) {
            WorkoutRecord workoutRecord = workoutRecords.get(i);
            if (workoutRecord.acquireWorkoutDate() != null) {
                hashSet.add(workoutRecord.acquireWorkoutDate());
            }
            f = (float) (f + (workoutRecord.acquireActualDistance() * 1000.0d));
            f3 += workoutRecord.getDuration();
            f2 += workoutRecord.acquireActualCalorie();
            LogUtil.a("Suggestion_IntPlanManager", "workoutRecord actualDistance = ", Float.valueOf(workoutRecord.acquireActualDistance()), " duration = ", Integer.valueOf(workoutRecord.getDuration()), " actualCalorie = ", Float.valueOf(workoutRecord.acquireActualCalorie()));
        }
        this.f12304a.e(new epw(StatInfo.STAT_TYPE_PUNCH_DAY, Integer.valueOf(hashSet.size())));
        this.f12304a.e(new epw("distance", Float.valueOf(f)));
        this.f12304a.e(new epw("duration", Float.valueOf(f3)));
        this.f12304a.e(new epw("calorie", Float.valueOf(f2)));
        LogUtil.a("Suggestion_IntPlanManager", "getFitnessStatFromLocal distance = ", Float.valueOf(f), " duration = ", Float.valueOf(f3), " calorie = ", Float.valueOf(f2));
    }

    private void b(JSONObject jSONObject, UiCallback<epo> uiCallback) {
        if (jSONObject == null) {
            return;
        }
        Plan a2 = ffm.a(jSONObject, "planInfo");
        this.f12304a = eve.a(a2);
        a(a2, uiCallback);
        d(a2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        this.f12304a = epo.a((IntPlanBean) new Gson().fromJson(jSONObject.toString(), IntPlanBean.class));
        j();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final Plan plan, final UiCallback<epo> uiCallback) {
        if (plan == null) {
            LogUtil.h("Suggestion_IntPlanManager", "getRunStatFromLocal plan is null.");
            return;
        }
        long startTime = plan.getStartTime() * 1000;
        long endTime = plan.getEndTime() * 1000;
        LogUtil.a("Suggestion_IntPlanManager", "getRunStatFromLocal startTime = ", Long.valueOf(startTime), " endTime = ", Long.valueOf(endTime));
        asb.d().d(startTime, endTime, new DataCallback() { // from class: ety.17
            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onFailure(int i, String str) {
                LogUtil.b("Suggestion_IntPlanManager", "getOutPlanRunData failure errorCode = ", Integer.valueOf(i), " errorInfo = ", str);
                ety.this.d((JSONObject) null, plan, (UiCallback<epo>) uiCallback);
            }

            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onSuccess(JSONObject jSONObject) {
                LogUtil.a("Suggestion_IntPlanManager", "getOutPlanRunData success data = ", jSONObject);
                ety.this.d(jSONObject, plan, (UiCallback<epo>) uiCallback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(JSONObject jSONObject, Plan plan, UiCallback<epo> uiCallback) {
        float f;
        float f2;
        float f3 = 0.0f;
        if (jSONObject != null) {
            double d = 0.0f;
            f = (float) (jSONObject.optDouble("distance") + d);
            f2 = (float) (jSONObject.optDouble("duration") + d);
            f3 = (float) (d + jSONObject.optDouble("calorie"));
        } else {
            f = 0.0f;
            f2 = 0.0f;
        }
        String a2 = gib.a(plan.getStartTime() * 1000, gib.e(plan.getTimeZone()));
        String a3 = gib.a(plan.getEndTime() * 1000, gib.e(plan.getTimeZone()));
        LogUtil.a("Suggestion_IntPlanManager", "addPlanRunData startTime = ", a2, " endTime = ", a3);
        List<WorkoutRecord> workoutRecords = etr.b().getWorkoutRecords(plan.acquireId(), a2, a3);
        LogUtil.a("Suggestion_IntPlanManager", "addPlanRunData workoutRecords size = ", Integer.valueOf(workoutRecords.size()));
        for (int i = 0; i < workoutRecords.size(); i++) {
            WorkoutRecord workoutRecord = workoutRecords.get(i);
            if (workoutRecord.isRunWorkout()) {
                LogUtil.a("Suggestion_IntPlanManager", "is run workout.", workoutRecord.acquireWorkoutName(), workoutRecord.acquireWorkoutId());
            } else {
                f = (float) (f + (workoutRecord.acquireActualDistance() * 1000.0d));
                f2 += workoutRecord.getDuration();
                f3 += workoutRecord.acquireActualCalorie();
                LogUtil.a("Suggestion_IntPlanManager", "workoutRecord actualDistance = ", Float.valueOf(workoutRecord.acquireActualDistance()), " duration = ", Integer.valueOf(workoutRecord.getDuration()), " actualCalorie = ", Float.valueOf(workoutRecord.acquireActualCalorie()));
            }
        }
        if (!g()) {
            this.f12304a.e(new epw("distance", Float.valueOf(f)));
            this.f12304a.e(new epw("duration", Float.valueOf(f2)));
            this.f12304a.e(new epw("calorie", Float.valueOf(f3)));
            LogUtil.a("Suggestion_IntPlanManager", "addPlanRunData distance = ", Float.valueOf(f), " duration = ", Float.valueOf(f2), " calorie = ", Float.valueOf(f3));
        } else {
            LogUtil.b("Suggestion_IntPlanManager", "addPlanRunData mIntPlan == null");
        }
        uiCallback.onSuccess(this.f12304a);
    }

    private void b(Plan plan) {
        if (plan == null) {
            LogUtil.h("Suggestion_IntPlanManager", "convertOldRunningPlan plan is null.");
            return;
        }
        epo a2 = epo.a();
        a2.b(plan);
        epq epqVar = new epq();
        epqVar.h(plan.acquireId());
        epqVar.a(IntPlan.PlanType.RUN_PLAN);
        a2.d(epqVar);
        epr eprVar = new epr();
        eprVar.c(plan.getStartTime());
        eprVar.e(plan.getEndTime());
        a2.d(eprVar);
        a2.e(new epw("progress", Float.valueOf(0.0f)));
        this.f12304a = a2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Plan plan) {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (accountInfo != null) {
            etr.b().syncLocalAndCloud(accountInfo, plan);
        } else {
            LogUtil.b("Suggestion_IntPlanManager", "syncLocalAndCloud userId == null");
        }
    }

    public void e(final IntPlanBean intPlanBean, final UiCallback<IntPlan> uiCallback) {
        if (!g()) {
            uiCallback.onFailure(this.c, -1, "createPlan error with a current plan");
            return;
        }
        if (intPlanBean.getMetaInfo() == null) {
            uiCallback.onFailure(this.c, -1, "INVALID META INFO");
        }
        List<FitnessPackageInfo> b = euh.a().b(intPlanBean.getMetaInfo().getPlanType());
        if (b != null && b.size() > 0) {
            eqa.a().createIntPlan(intPlanBean, new DataCallback() { // from class: ety.18
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i, String str) {
                    ReleaseLogUtil.c("Suggestion_IntPlanManager", "createPlan fail with errorCode ", Integer.valueOf(i));
                    uiCallback.onFailure(ety.this.c, i, str);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    if (jSONObject == null) {
                        uiCallback.onFailure(ety.this.c, -1, "create plan null");
                        return;
                    }
                    JSONObject optJSONObject = jSONObject.optJSONObject("intPlan");
                    if (optJSONObject == null) {
                        uiCallback.onFailure(ety.this.c, -1, "create plan null");
                        return;
                    }
                    IntPlanBean intPlanBean2 = (IntPlanBean) new Gson().fromJson(optJSONObject.toString(), IntPlanBean.class);
                    ReleaseLogUtil.e("Suggestion_IntPlanManager", "createPlan success with data", jSONObject);
                    ety.this.f12304a = epo.a(intPlanBean2);
                    ety.this.j();
                    if (ety.this.f12304a.e != null) {
                        etl.a(ety.this.f12304a, true, ety.this.f12304a.e.getRemindTime());
                    }
                    uiCallback.onSuccess(ety.this.c, ety.this.f12304a);
                    ary.a().e("PLAN_CREATE");
                    ase.d(ety.this.f12304a, "5", 0, 1, 0);
                    if (intPlanBean.getTemp() == 1) {
                        ety.this.e();
                        ReleaseLogUtil.e("Suggestion_IntPlanManager", "intPlanBean getTemp null");
                    }
                }
            });
        } else {
            ReleaseLogUtil.c("Suggestion_IntPlanManager", "createPlan no plan in plan list");
            uiCallback.onFailure(this.c, -1, "no this planType in all package list");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        d(moe.a(d()));
        try {
            if (this.f12304a.getGoal("weight") != null) {
                epj epjVar = new epj();
                epjVar.e("weight");
                epjVar.b(Float.valueOf(Float.parseFloat(this.f12304a.getGoal("weight").getGoalDst().toString())));
                this.f12304a.a(epjVar);
            }
        } catch (NumberFormatException unused) {
            LogUtil.b("Suggestion_IntPlanManager", "initIntPlan get goal fail");
        }
        LogUtil.a("Suggestion_IntPlanManager", "initIntPlan success!!");
    }

    public void b(final moc mocVar, final UiCallback<String> uiCallback) {
        if (g() || mocVar.c() == null || !mocVar.c().equals(this.f12304a.getPlanId())) {
            return;
        }
        eqa.a().updateAction(mocVar, new DataCallback() { // from class: ety.20
            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onFailure(int i, String str) {
                ReleaseLogUtil.c("Suggestion_IntPlanManager", "updateAction fail!!");
                uiCallback.onFailure(ety.this.c, i, str);
            }

            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onSuccess(JSONObject jSONObject) {
                ety.this.b(jSONObject, mocVar, (UiCallback<String>) uiCallback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(JSONObject jSONObject, moc mocVar, UiCallback<String> uiCallback) {
        if (jSONObject == null) {
            uiCallback.onSuccess(this.c, "EMPTY_DATA");
            return;
        }
        int e2 = mocVar.e();
        int b = mocVar.b();
        for (epp eppVar : this.f12304a.d()) {
            if (eppVar.getWeekOrder() == e2) {
                for (epl eplVar : eppVar.b()) {
                    if (eplVar.getDayOrder() == b) {
                        List<epm> a2 = eplVar.a();
                        if (mocVar.d() == 0) {
                            for (mnr mnrVar : mocVar.a()) {
                                if (a2.size() < 3) {
                                    epm epmVar = new epm();
                                    epmVar.e(mnrVar.a());
                                    epmVar.e(IntAction.ActionType.getActionType(mnrVar.c()));
                                    a2.add(epmVar);
                                }
                            }
                        } else if (1 == mocVar.d()) {
                            ArrayList arrayList = new ArrayList();
                            for (mnr mnrVar2 : mocVar.a()) {
                                for (epm epmVar2 : a2) {
                                    if (mnrVar2.a().equals(epmVar2.getActionId()) && mnrVar2.c() == epmVar2.getActionType().getType()) {
                                        arrayList.add(epmVar2);
                                    }
                                }
                            }
                            a2.removeAll(arrayList);
                        } else {
                            LogUtil.b("Suggestion_IntPlanManager", "PlanActionOpeType error");
                        }
                    }
                }
            }
        }
        ary.a().e("PLAN_UPDATE");
        LogUtil.a("Suggestion_IntPlanManager", "updateAction onSuccess", jSONObject.toString());
        uiCallback.onSuccess(this.c, jSONObject.toString());
    }

    public void b(int i, String str, int i2, UiCallback<String> uiCallback) {
        if (StringUtils.g(str) || uiCallback == null) {
            ReleaseLogUtil.c("Suggestion_IntPlanManager", "stopPlan invalid callback, planId is null or empty");
            return;
        }
        ReleaseLogUtil.e("Suggestion_IntPlanManager", "finish planId : " + str);
        etx.b().e();
        if (g() || !this.f12304a.getPlanId().equals(str) || IntPlan.PlanType.RUN_PLAN.equals(this.f12304a.getPlanType())) {
            if (i2 == 0) {
                etr.b().finishPlan(str, uiCallback);
                e();
                return;
            } else {
                ReleaseLogUtil.c("Suggestion_IntPlanManager", "stopPlan current plan is null or planId is not current plan");
                return;
            }
        }
        e eVar = new e(i, uiCallback);
        if (this.f12304a.getPlanType() == IntPlan.PlanType.AI_RUN_PLAN) {
            etr.b().finishPlan(str, eVar);
            return;
        }
        if (this.f12304a.getPlanType() == IntPlan.PlanType.FIT_PLAN && this.f12304a.getMetaInfo().getDisplayStyle() == 2) {
            e(str, eVar);
        } else if (this.f12304a.getPlanType() == IntPlan.PlanType.AI_FITNESS_PLAN || this.f12304a.getPlanType() == IntPlan.PlanType.FIT_PLAN) {
            b(new esl.a().b(str).e(this.f12304a.getPlanType().getType()).a(), eVar);
        } else {
            LogUtil.b("Suggestion_IntPlanManager", "current planType is not support");
        }
    }

    private void e(final String str, final UiCallback<String> uiCallback) {
        new euo().c(str, new UiCallback<mny>() { // from class: ety.19
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str2) {
                ReleaseLogUtil.d("Suggestion_IntPlanManager", "buildFinalPlanReport onFailure errorCode: ", Integer.valueOf(i), "errorInfo: ", str2);
                if (i == 20004 || i == 200019) {
                    uiCallback.onSuccess(ety.this.c, "EMPTY_DATA");
                } else {
                    uiCallback.onFailure(ety.this.c, i, str2);
                }
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onSuccess(mny mnyVar) {
                ety.this.b(new esl.a().b(str).e(ety.this.f12304a.getPlanType().getType()).a(), (UiCallback<String>) uiCallback);
            }
        });
    }

    class e extends UiCallback<String> {

        /* renamed from: a, reason: collision with root package name */
        private final int f12319a;
        private final UiCallback<String> d;

        e(int i, UiCallback<String> uiCallback) {
            this.f12319a = i;
            this.d = uiCallback;
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.b("Suggestion_IntPlanManager", "finishPlan fail errorInfo", str);
            this.d.onFailure(i, str);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onSuccess(String str) {
            if (!ety.this.g()) {
                StatInfo stat = ety.this.f12304a.getStat("progress");
                float floatValue = stat == null ? 0.0f : ((Float) stat.getValue()).floatValue();
                if (ety.this.f12304a.getPlanType() == IntPlan.PlanType.AI_RUN_PLAN) {
                    gge.d(ety.this.f12304a.getMetaInfo().getName(), 0, String.valueOf(floatValue), ety.this.f12304a.getPlanId());
                } else {
                    gge.d(ety.this.f12304a.getMetaInfo().getName(), 1, String.valueOf(floatValue), ety.this.f12304a.getPlanId());
                }
                HashMap hashMap = new HashMap(3);
                hashMap.put("click", 1);
                hashMap.put("type", ety.this.f12304a.getPlanType() == null ? IntPlan.PlanType.NA_PLAN : Integer.valueOf(ety.this.f12304a.getPlanType().getType()));
                hashMap.put("endType", Integer.valueOf(this.f12319a));
                ixx.d().d(BaseApplication.getContext(), AnalyticsValue.INT_PLAN_2030078.value(), hashMap, 0);
                ety.this.e();
            }
            ary.a().e("PLAN_DELETE");
            LogUtil.a("Suggestion_IntPlanManager", "finishPlan success");
            this.d.onSuccess(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final esl eslVar, final UiCallback<String> uiCallback) {
        eqa.a().stopIntPlan(eslVar, new DataCallback() { // from class: ety.24
            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onFailure(int i, String str) {
                ReleaseLogUtil.c("Suggestion_IntPlanManager", "stopIntPlan fail ", Integer.valueOf(i), " errorInfo", str);
                if (i == 20004 || i == 200019) {
                    uiCallback.onSuccess(ety.this.c, "EMPTY_DATA");
                } else {
                    uiCallback.onFailure(ety.this.c, i, str);
                }
            }

            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onSuccess(JSONObject jSONObject) {
                euv.c(eslVar.a());
                euv.d(eslVar.d());
                if (jSONObject == null) {
                    ReleaseLogUtil.c("Suggestion_IntPlanManager", "stopIntPlan data is null.");
                    uiCallback.onSuccess(ety.this.c, "EMPTY_DATA");
                } else {
                    LogUtil.a("Suggestion_IntPlanManager", "stopIntPlan success ", jSONObject);
                    etl.e(ety.this.f12304a, (Boolean) false, -1);
                    uiCallback.onSuccess(ety.this.c, jSONObject.toString());
                }
            }
        });
    }

    public void a(UserInfoBean userInfoBean, final String str, final UiCallback<IntPlan> uiCallback) {
        if (g() || !this.f12304a.getPlanId().equals(str)) {
            uiCallback.onFailure(this.c, -1, "updatePlan plan is null or invalid planId");
            return;
        }
        if (this.f12304a.getPlanType() == IntPlan.PlanType.AI_FITNESS_PLAN) {
            UserProfileBean userProfileBean = new UserProfileBean();
            userProfileBean.setGender(userInfoBean.getSex());
            userProfileBean.setWeight(userInfoBean.getWeight());
            userProfileBean.setAge(userInfoBean.getAge());
            userProfileBean.setHeight(userInfoBean.getHeight());
            LogUtil.a("Suggestion_IntPlanManager", "updatePlan with data", new Gson().toJson(userProfileBean));
            e(str, userProfileBean, uiCallback);
            return;
        }
        etr.b().updatePlan(userInfoBean, ase.g(this.f12304a), new UiCallback<Plan>() { // from class: ety.25
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str2) {
                uiCallback.onFailure(ety.this.c, i, str2);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onSuccess(Plan plan) {
                if (plan != null) {
                    ReleaseLogUtil.e("Suggestion_IntPlanManager", "updateAIRunPlan success");
                    StatInfo stat = !ety.this.g() ? ety.this.f12304a.getStat("progress") : null;
                    ety.this.f12304a = eve.a(plan);
                    if (stat != null && (stat instanceof epw)) {
                        ety.this.f12304a.e((epw) stat);
                    }
                    ase.t(ety.this.f12304a);
                    etx.b().c(str);
                    ety.this.a(plan, new UiCallback<epo>() { // from class: ety.25.4
                        @Override // com.huawei.basefitnessadvice.callback.UiCallback
                        public void onFailure(int i, String str2) {
                            ary.a().e("PLAN_SWITCH");
                            uiCallback.onSuccess(ety.this.c, ety.this.f12304a);
                        }

                        @Override // com.huawei.basefitnessadvice.callback.UiCallback
                        /* renamed from: e, reason: merged with bridge method [inline-methods] */
                        public void onSuccess(epo epoVar) {
                            ary.a().e("PLAN_SWITCH");
                            uiCallback.onSuccess(ety.this.c, ety.this.f12304a);
                        }
                    });
                    return;
                }
                ReleaseLogUtil.d("Suggestion_IntPlanManager", "updateAIRunPlan data is null");
                uiCallback.onFailure(ety.this.c, ResultUtil.ResultCode.PARAMETER_INVALID, "no plan exist");
            }
        });
    }

    private void e(String str, UserProfileBean userProfileBean, final UiCallback<IntPlan> uiCallback) {
        eqa.a().updateIntPlan(userProfileBean, str, this.f12304a.getPlanType().getType(), new DataCallback() { // from class: ety.22
            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onFailure(int i, String str2) {
                ReleaseLogUtil.c("Suggestion_IntPlanManager", "updatePlan fail ", str2);
                uiCallback.onFailure(ety.this.c, i, str2);
            }

            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onSuccess(JSONObject jSONObject) {
                if (jSONObject == null) {
                    uiCallback.onFailure(ety.this.c, ResultUtil.ResultCode.PARAMETER_INVALID, "no plan exist");
                    return;
                }
                JSONObject optJSONObject = jSONObject.optJSONObject("intPlan");
                if (optJSONObject == null) {
                    uiCallback.onFailure(ety.this.c, ResultUtil.ResultCode.PARAMETER_INVALID, "no plan exist");
                    return;
                }
                IntPlanBean intPlanBean = (IntPlanBean) new Gson().fromJson(optJSONObject.toString(), IntPlanBean.class);
                LogUtil.a("Suggestion_IntPlanManager", "updatePlan success with data", jSONObject);
                etl.e(ety.this.f12304a, (Boolean) false, -1);
                ety.this.f12304a = epo.a(intPlanBean);
                ety.this.j();
                ase.t(ety.this.f12304a);
                if (ety.this.f12304a.e != null) {
                    etl.a(ety.this.f12304a, true, ety.this.f12304a.e.getRemindTime());
                }
                etx.b().c();
                ary.a().e("PLAN_SWITCH");
                uiCallback.onSuccess(ety.this.c, ety.this.f12304a);
            }
        });
    }

    public void b(String str, int i, int i2, String str2, final long j, final UiCallback<String> uiCallback) {
        final String str3 = "planReport:" + str;
        final String str4 = "planReportTime:" + str;
        ash.a(str3, str2);
        ash.a(str4, String.valueOf(j));
        eqa.a().updateIntPlanReport(str, i, i2, str2, j, new DataCallback() { // from class: ety.5
            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onFailure(int i3, String str5) {
                uiCallback.onFailure(ety.this.c, i3, str5);
            }

            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onSuccess(JSONObject jSONObject) {
                ash.d(str3);
                ash.d(str4);
                if (jSONObject != null) {
                    uiCallback.onSuccess(ety.this.c, jSONObject.toString());
                } else {
                    LogUtil.b("Suggestion_IntPlanManager", "updatePlanReport onSuccess data is null.");
                    uiCallback.onSuccess(ety.this.c, "EMPTY_DATA");
                }
                if (ety.this.g()) {
                    return;
                }
                ety.this.f12304a.e.g(j);
            }
        });
    }

    public void b(final String str, final int i, final int i2, final UiCallback<String> uiCallback) {
        eqa.a().getIntPlanReport(str, i, i2, new DataCallback() { // from class: ety.4
            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onFailure(int i3, String str2) {
                uiCallback.onFailure(ety.this.c, i3, str2);
            }

            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onSuccess(JSONObject jSONObject) {
                long j;
                if (jSONObject == null) {
                    LogUtil.b("Suggestion_IntPlanManager", "getPlanReport onSuccess data is null.");
                    uiCallback.onSuccess(ety.this.c, "EMPTY_DATA");
                    return;
                }
                LogUtil.a("Suggestion_IntPlanManager", "getPlanReport:", jSONObject.toString());
                String str2 = "planReport:" + str;
                String str3 = "planReportTime:" + str;
                String b = ash.b(str3);
                try {
                    j = jSONObject.getLong("reportUpdateTime");
                } catch (JSONException unused) {
                    LogUtil.b("Suggestion_IntPlanManager", "getPlanReport error");
                    j = 0;
                }
                if (StringUtils.a(b) && CommonUtil.g(b) > j) {
                    String b2 = ash.b(str2);
                    ety.this.b(str, i, i2, b2, CommonUtil.g(b), new UiCallback<String>() { // from class: ety.4.2
                        @Override // com.huawei.basefitnessadvice.callback.UiCallback
                        /* renamed from: e, reason: merged with bridge method [inline-methods] */
                        public void onSuccess(String str4) {
                        }

                        @Override // com.huawei.basefitnessadvice.callback.UiCallback
                        public void onFailure(int i3, String str4) {
                            LogUtil.b("Suggestion_IntPlanManager", "updatePlanReport errorCode:", Integer.valueOf(i3), " errorInfo:", str4);
                        }
                    });
                    JSONObject jSONObject2 = new JSONObject();
                    try {
                        LogUtil.a("Suggestion_IntPlanManager", "putPlanReport");
                        jSONObject2.put("report", b2);
                    } catch (JSONException e2) {
                        ReleaseLogUtil.c("Suggestion_IntPlanManager", "putPlanReport error ", ExceptionUtils.d(e2));
                    }
                    uiCallback.onSuccess(ety.this.c, jSONObject2.toString());
                    return;
                }
                ash.d(str2);
                ash.d(str3);
                uiCallback.onSuccess(ety.this.c, jSONObject.toString());
            }
        });
    }

    public void a(String str, int i, final int i2, final String str2, final UiCallback<String> uiCallback) {
        eqa.a().updatePlanInfo(str, i, String.valueOf(i2), str2, new DataCallback() { // from class: ety.1
            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onFailure(int i3, String str3) {
                uiCallback.onFailure(ety.this.c, i3, str3);
            }

            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onSuccess(JSONObject jSONObject) {
                if (i2 == 2 && StringUtils.i(str2)) {
                    try {
                        etl.e(ety.this.f12304a, (Boolean) null, Integer.parseInt(str2));
                    } catch (NumberFormatException e2) {
                        LogUtil.b("Suggestion_IntPlanManager", "updatePlanInfo() exception: ", LogAnonymous.b((Throwable) e2));
                    }
                }
                if (jSONObject != null) {
                    uiCallback.onSuccess(ety.this.c, jSONObject.toString());
                } else {
                    ReleaseLogUtil.c("Suggestion_IntPlanManager", "updatePlanInfo onSuccess data is null.");
                    uiCallback.onSuccess(ety.this.c, "EMPTY_DATA");
                }
            }
        });
    }

    public void a(String str, int i, int i2) {
        if (g()) {
            return;
        }
        epm a2 = eve.a(this.f12304a, str, i, i2);
        if (a2 != null) {
            a2.e(1);
            d(i, i2);
        }
        d(d());
    }

    private void d(int i, int i2) {
        List<epm> e2;
        if (g() || (e2 = eve.e(this.f12304a, i, i2)) == null) {
            return;
        }
        Iterator<epm> it = e2.iterator();
        while (it.hasNext()) {
            if (it.next().getPunchFlag() == 0) {
                return;
            }
        }
        epl a2 = eve.a(this.f12304a, i, i2);
        if (a2 != null) {
            a2.a(1);
        }
    }

    public int c(int i) {
        if (g() || this.f12304a.getPlanId() == null) {
            if (i == 0) {
                return etr.b().getRemindTime();
            }
            LogUtil.b("Suggestion_IntPlanManager", "getRemindTime current plan is null");
            return 0;
        }
        if (this.f12304a.getPlanType().equals(IntPlan.PlanType.AI_RUN_PLAN)) {
            return etr.b().getRemindTime();
        }
        if (this.f12304a.getPlanType().equals(IntPlan.PlanType.AI_FITNESS_PLAN) || this.f12304a.getPlanType().equals(IntPlan.PlanType.FIT_PLAN)) {
            return etl.b();
        }
        LogUtil.h("Suggestion_IntPlanManager", "getRemindTime planType is not support");
        return 0;
    }

    public boolean b(int i) {
        if (g() || this.f12304a.getPlanId() == null) {
            if (i == 0) {
                return etr.b().isOpenRemind();
            }
            LogUtil.b("Suggestion_IntPlanManager", "getRemindTime current plan is null");
            return false;
        }
        if (this.f12304a.getPlanType().equals(IntPlan.PlanType.AI_RUN_PLAN)) {
            return etr.b().isOpenRemind();
        }
        if (this.f12304a.getPlanType().equals(IntPlan.PlanType.AI_FITNESS_PLAN) || this.f12304a.getPlanType().equals(IntPlan.PlanType.FIT_PLAN)) {
            return etl.c();
        }
        LogUtil.h("Suggestion_IntPlanManager", "getRemindTime planType is not support");
        return false;
    }

    public void b(final boolean z, final int i, int i2) {
        LogUtil.c("Suggestion_IntPlanManager", "remindTime:" + i + ";oldPlanType:" + i2);
        if (g() || this.f12304a.getPlanId() == null) {
            if (i2 == 0) {
                etr.b().setExerciseRemind(z, i);
                return;
            } else {
                LogUtil.b("Suggestion_IntPlanManager", "updateRemindTime mIntPlan is null");
                return;
            }
        }
        UiCallback<String> uiCallback = new UiCallback<String>() { // from class: ety.2
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i3, String str) {
                LogUtil.b("Suggestion_IntPlanManager", "updateRemindTime fail errorInfo", str);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onSuccess(String str) {
                if (ety.this.f12304a.e != null) {
                    ety.this.f12304a.e.b(z ? i : -1L);
                }
                LogUtil.c("Suggestion_IntPlanManager", "updateRemindTime success");
            }
        };
        if (this.f12304a.getPlanType() == IntPlan.PlanType.AI_RUN_PLAN) {
            etr.b().setExerciseRemind(z, i);
            uiCallback.onSuccess(null);
        } else if (this.f12304a.getPlanType() == IntPlan.PlanType.AI_FITNESS_PLAN || this.f12304a.getPlanType() == IntPlan.PlanType.FIT_PLAN) {
            a(this.f12304a.getPlanId(), this.f12304a.getPlanType().getType(), 2, z ? String.valueOf(i) : "-1", uiCallback);
        } else {
            LogUtil.h("Suggestion_IntPlanManager", "updateRemindTime remind planType not support");
        }
    }

    public void e(String str, List<Integer> list, final List<Integer> list2, final UiCallback<String> uiCallback) {
        if (g() || this.f12304a.getPlanId() == null || !this.f12304a.getPlanId().equals(str)) {
            LogUtil.b("Suggestion_IntPlanManager", "updatePlanDate current plan is null or planId is not current plan");
            uiCallback.onFailure(this.c, -1, "updatePlanDate error with plan is null or planId is not current plan");
            return;
        }
        UiCallback<String> uiCallback2 = new UiCallback<String>() { // from class: ety.8
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str2) {
                ReleaseLogUtil.c("Suggestion_IntPlanManager", "updatePlanDate fail errorInfo", str2);
                uiCallback.onFailure(i, str2);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onSuccess(String str2) {
                if (ety.this.f12304a.c != null) {
                    ety.this.f12304a.c.c(ety.this.c((List<Integer>) list2));
                    epq epqVar = ety.this.f12304a.c;
                    List list3 = list2;
                    epqVar.a(list3 == null ? 0 : list3.size());
                }
                ReleaseLogUtil.e("Suggestion_IntPlanManager", "updatePlanDate success");
                uiCallback.onSuccess(str2);
            }
        };
        if (this.f12304a.getPlanType() == IntPlan.PlanType.AI_RUN_PLAN) {
            eun.a().updatePlanDate(str, list, list2, uiCallback2);
        } else if (this.f12304a.getPlanType() == IntPlan.PlanType.AI_FITNESS_PLAN) {
            a(str, IntPlan.PlanType.AI_FITNESS_PLAN.getType(), 1, c(list2), uiCallback2);
        } else {
            LogUtil.h("Suggestion_IntPlanManager", "updatePlanDate planType not support");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String c(List<Integer> list) {
        if (list == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(16);
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            stringBuffer.append(String.valueOf(it.next()));
            stringBuffer.append(",");
        }
        if (stringBuffer.toString().contains(",")) {
            stringBuffer.deleteCharAt(stringBuffer.lastIndexOf(","));
        }
        return stringBuffer.toString();
    }

    public void d(mof mofVar, UiCallback<String> uiCallback) {
        if (g() || this.f12304a.getPlanId() == null || !this.f12304a.getPlanId().equals(mofVar.c())) {
            LogUtil.b("Suggestion_IntPlanManager", "postFeedback current plan is null or planId is not current plan");
            uiCallback.onSuccess(this.c, null);
            return;
        }
        long e2 = mofVar.e();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(e2);
        int d = gib.d(calendar.get(7));
        int e3 = ase.e(this.f12304a, e2);
        ReleaseLogUtil.e("Suggestion_IntPlanManager", "postFeedBack weekNumber:", Integer.valueOf(e3), "dayNumber:", Integer.valueOf(d), " planTime:", Long.valueOf(e2));
        mofVar.c(e3);
        mofVar.d(d);
        if (this.f12304a.getPlanType() == IntPlan.PlanType.AI_RUN_PLAN) {
            eun.a().postFeedback(mofVar, uiCallback);
        } else if (this.f12304a.getPlanType() == IntPlan.PlanType.AI_FITNESS_PLAN) {
            c(mofVar, uiCallback);
        } else {
            uiCallback.onSuccess(this.c, null);
            LogUtil.h("Suggestion_IntPlanManager", "postFeedback planType not support");
        }
    }

    private void c(mof mofVar, final UiCallback<String> uiCallback) {
        eqa.a().postIntFeedback(mofVar, new DataCallback() { // from class: ety.10
            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onFailure(int i, String str) {
                ReleaseLogUtil.c("Suggestion_IntPlanManager", "postIntFeedback fail ", Integer.valueOf(i), " errorInfo", str);
                uiCallback.onFailure(ety.this.c, i, str);
            }

            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onSuccess(JSONObject jSONObject) {
                if (jSONObject == null) {
                    ReleaseLogUtil.c("Suggestion_IntPlanManager", "postIntFeedback onSuccess data is null.");
                    uiCallback.onSuccess(ety.this.c, "EMPTY_DATA");
                } else {
                    LogUtil.a("Suggestion_IntPlanManager", "postIntFeedback success ", jSONObject);
                    uiCallback.onSuccess(ety.this.c, jSONObject.toString());
                }
            }
        });
    }

    public void a(int i, IntPlanBean intPlanBean, final UiCallback<String> uiCallback) {
        eqa.a().generateReport(i, intPlanBean, new DataCallback() { // from class: ety.9
            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onFailure(int i2, String str) {
                uiCallback.onFailure(ety.this.c, i2, str);
            }

            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onSuccess(JSONObject jSONObject) {
                if (jSONObject == null) {
                    uiCallback.onSuccess(ety.this.c, "EMPTY_DATA");
                } else {
                    uiCallback.onSuccess(ety.this.c, jSONObject.optString("reviewResult"));
                }
            }
        });
    }

    public float d() {
        return eve.e(this.f12304a);
    }

    public void d(float f) {
        if (g()) {
            return;
        }
        this.f12304a.e(new epw("progress", Float.valueOf(f)));
    }

    public void c(boolean z) {
        if (!g() && z) {
            e();
        }
        ary.a().e("PLAN_SWITCH");
    }

    public void e(final String str, final mob mobVar) {
        b(new UiCallback<epo>() { // from class: ety.6
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str2) {
                ety.this.d(str, mobVar);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onSuccess(epo epoVar) {
                ety.this.d(str, mobVar);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final String str, final mob mobVar) {
        if (g()) {
            LogUtil.h("Suggestion_IntPlanManager", "updateDayRecord plan not exist.");
        } else if (ase.n(this.f12304a)) {
            ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getUserInfo(new BaseResponseCallback() { // from class: eud
                @Override // com.huawei.health.userprofilemgr.model.BaseResponseCallback
                public final void onResponse(int i, Object obj) {
                    ety.this.e(mobVar, str, i, (UserInfomation) obj);
                }
            });
        } else if (a(this.f12304a, mobVar)) {
            etr.b().updateDayRecord(str, this.f12304a, mobVar);
        }
    }

    /* synthetic */ void e(final mob mobVar, final String str, int i, UserInfomation userInfomation) {
        if (i != 0 || userInfomation == null) {
            LogUtil.h("Suggestion_IntPlanManager", "initUserInfo failed, errorCode = ", Integer.valueOf(i));
        } else {
            a(d(userInfomation), this.f12304a.getPlanId(), new UiCallback<IntPlan>() { // from class: ety.7
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i2, String str2) {
                    LogUtil.b("Suggestion_IntPlanManager", "updateDayRecord updatePlan fail.");
                    ety etyVar = ety.this;
                    if (etyVar.a(etyVar.f12304a, mobVar)) {
                        etr.b().updateDayRecord(str, ety.this.f12304a, mobVar);
                    }
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public void onSuccess(IntPlan intPlan) {
                    ety etyVar = ety.this;
                    if (etyVar.a(etyVar.f12304a, mobVar)) {
                        etr.b().updateDayRecord(str, ety.this.f12304a, mobVar);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(IntPlan intPlan, mob mobVar) {
        if (intPlan != null) {
            return IntPlan.PlanType.AI_RUN_PLAN.equals(intPlan.getPlanType()) ? mobVar.f() == 280 || mobVar.f() == 264 || mobVar.f() == 258 : IntPlan.PlanType.AI_FITNESS_PLAN.equals(intPlan.getPlanType()) && ase.f();
        }
        LogUtil.b("Suggestion_IntPlanManager", "intPlan=null");
        return false;
    }

    private static UserInfoBean d(UserInfomation userInfomation) {
        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean.setSex(userInfomation.getGenderOrDefaultValue());
        userInfoBean.setAge(userInfomation.getAgeOrDefaultValue());
        userInfoBean.setHeight(userInfomation.getHeightOrDefaultValue());
        userInfoBean.setWeight((int) userInfomation.getWeightOrDefaultValue());
        return userInfoBean;
    }

    public void e(String str) {
        if (g()) {
            LogUtil.b("Suggestion_IntPlanManager", "deletePlanExerciseRecord mIntPlan == null");
        } else {
            eqa.a().deletePlanExerciseRecord(this.f12304a.getPlanType().getType(), this.f12304a.getPlanId(), str, new DataCallback() { // from class: ety.14
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i, String str2) {
                    ReleaseLogUtil.c("Suggestion_IntPlanManager", "deletePlanExerciseRecord fail:", str2);
                }

                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    ReleaseLogUtil.e("Suggestion_IntPlanManager", "deletePlanExerciseRecord Success");
                    etx.b().e();
                    ary.a().e("PLAN_UPDATE");
                }
            });
        }
    }

    public void c(ReplacePlanBean replacePlanBean, final UiCallback<Boolean> uiCallback) {
        eqa.a().replacePlanSchedule(replacePlanBean, new DataCallback() { // from class: ety.12
            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onFailure(int i, String str) {
                ReleaseLogUtil.c("Suggestion_IntPlanManager", "replacePlanSchedule fail:", str);
                uiCallback.onFailure(i, str);
            }

            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onSuccess(JSONObject jSONObject) {
                ReleaseLogUtil.e("Suggestion_IntPlanManager", "replacePlanSchedule success:", jSONObject);
                uiCallback.onSuccess(true);
                etx.b().e();
                ary.a().e("PLAN_ADJUST");
            }
        });
    }

    public void b(LeavePlanCalendarBean leavePlanCalendarBean, final UiCallback<IntPlan> uiCallback) {
        eqa.a().leavePlan(leavePlanCalendarBean, new DataCallback() { // from class: ety.13
            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onFailure(int i, String str) {
                ReleaseLogUtil.c("Suggestion_IntPlanManager", "leavePlan fail:", str);
                uiCallback.onFailure(i, str);
            }

            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onSuccess(JSONObject jSONObject) {
                ReleaseLogUtil.e("Suggestion_IntPlanManager", "leavePlan success:", jSONObject);
                JSONObject optJSONObject = jSONObject.optJSONObject("intPlan");
                if (optJSONObject != null) {
                    ety.this.c(optJSONObject);
                    uiCallback.onSuccess(ety.this.f12304a);
                    etx.b().e();
                    ary.a().e("PLAN_ADJUST");
                    return;
                }
                uiCallback.onFailure(-1, "leavePlan success but return plan null");
            }
        });
    }

    public void c(LeavePlanCalendarBean leavePlanCalendarBean, final UiCallback<IntPlan> uiCallback) {
        eqa.a().cancelLeave(leavePlanCalendarBean, new DataCallback() { // from class: ety.15
            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onFailure(int i, String str) {
                ReleaseLogUtil.c("Suggestion_IntPlanManager", "cancelLeave fail:", str);
                uiCallback.onFailure(i, str);
            }

            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onSuccess(JSONObject jSONObject) {
                ReleaseLogUtil.e("Suggestion_IntPlanManager", "cancelLeave success:", jSONObject);
                JSONObject optJSONObject = jSONObject.optJSONObject("intPlan");
                if (optJSONObject != null) {
                    ety.this.c(optJSONObject);
                    uiCallback.onSuccess(ety.this.f12304a);
                    etx.b().e();
                    ary.a().e("PLAN_ADJUST");
                    return;
                }
                uiCallback.onFailure(-1, "cancelLeave success but return plan null");
            }
        });
    }

    public void e(LeavePlanCalendarBean leavePlanCalendarBean, final UiCallback<IntPlan> uiCallback) {
        eqa.a().updatePlanCalendar(leavePlanCalendarBean, new DataCallback() { // from class: ety.16
            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onFailure(int i, String str) {
                ReleaseLogUtil.c("Suggestion_IntPlanManager", "updatePlanCalendar fail:", str);
                uiCallback.onFailure(i, str);
            }

            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onSuccess(JSONObject jSONObject) {
                ReleaseLogUtil.e("Suggestion_IntPlanManager", "updatePlanCalendar success:", jSONObject);
                JSONObject optJSONObject = jSONObject.optJSONObject("intPlan");
                if (optJSONObject != null) {
                    ety.this.c(optJSONObject);
                    uiCallback.onSuccess(ety.this.f12304a);
                    etx.b().e();
                    ary.a().e("PLAN_ADJUST");
                    return;
                }
                uiCallback.onFailure(-1, "updatePlanCalendar success but return plan null");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean g() {
        return this.f12304a == null || this.f12304a.j();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (this.f12304a != null) {
            this.f12304a.e();
        }
    }
}
