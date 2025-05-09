package com.huawei.health.plan.impl;

import android.os.Looper;
import com.google.gson.GsonBuilder;
import com.huawei.basefitnessadvice.callback.DataCallback;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.basefitnessadvice.model.PlanInfo;
import com.huawei.basefitnessadvice.model.PlanRecord;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.basefitnessadvice.model.intplan.RecordData;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.plan.impl.PlanImpl;
import com.huawei.health.plan.model.FitnessPlanParams;
import com.huawei.health.plan.model.PlanStat;
import com.huawei.health.plan.model.PlanStatistics;
import com.huawei.health.plan.model.UserFitnessPlanInfo;
import com.huawei.health.plan.model.intplan.IntPlanBean;
import com.huawei.health.plan.model.intplan.LeavePlanCalendarBean;
import com.huawei.health.plan.model.intplan.ReplacePlanBean;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.suggestion.ResultCallback;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.FitnessPackageInfo;
import com.huawei.pluginfitnessadvice.plandata.CourseDataBean;
import com.huawei.pluginfitnessadvice.plandata.CreateRunPlanParams;
import com.huawei.pluginfitnessadvice.plandata.UserInfoBean;
import com.huawei.up.model.UserInfomation;
import defpackage.ary;
import defpackage.ase;
import defpackage.epo;
import defpackage.epx;
import defpackage.eqa;
import defpackage.etl;
import defpackage.etr;
import defpackage.etx;
import defpackage.ety;
import defpackage.etz;
import defpackage.euc;
import defpackage.eug;
import defpackage.euh;
import defpackage.eun;
import defpackage.euv;
import defpackage.euy;
import defpackage.eve;
import defpackage.ffi;
import defpackage.ffl;
import defpackage.ggl;
import defpackage.ghz;
import defpackage.gib;
import defpackage.koq;
import defpackage.kwm;
import defpackage.mmw;
import defpackage.mnu;
import defpackage.mnw;
import defpackage.mny;
import defpackage.mob;
import defpackage.moc;
import defpackage.mof;
import defpackage.mog;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONObject;

@ApiDefine(uri = PlanApi.class)
/* loaded from: classes3.dex */
public class PlanImpl implements PlanApi {

    /* renamed from: a, reason: collision with root package name */
    private static final AtomicReference<CountDownLatch> f2924a = new AtomicReference<>();
    private int c;

    static {
        if (eve.b) {
            eug.c();
        }
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public void setPlanType(int i) {
        this.c = i;
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public void createPlan(epx epxVar, UiCallback<Plan> uiCallback) {
        if (epxVar == null || uiCallback == null) {
            LogUtil.b("PlanDataImpl", "createPlan: createPlanParams or callcack is null.");
            return;
        }
        int i = this.c;
        if (i == 3) {
            d(epxVar, uiCallback);
        } else if (i == 4) {
            c(epxVar, uiCallback);
        } else {
            LogUtil.b("PlanDataImpl", "createPlan: planType illegal.");
        }
    }

    private void c(epx epxVar, UiCallback<Plan> uiCallback) {
        FitnessPlanParams b = epxVar.b();
        etr.b().createFitPlan(b.acquireBeginDate(), b.acquireType(), b.acquireDifficulty(), b.acquireTimes(), b.acquireExcludedDate(), uiCallback);
    }

    private void d(epx epxVar, final UiCallback<Plan> uiCallback) {
        euc.e().c(epxVar, new UiCallback<UserFitnessPlanInfo>() { // from class: com.huawei.health.plan.impl.PlanImpl.3
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                uiCallback.onFailure(i, str);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onSuccess(UserFitnessPlanInfo userFitnessPlanInfo) {
                uiCallback.onSuccess(etz.a(userFitnessPlanInfo));
                ary.a().e("PLAN_UPDATE");
            }
        });
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public void checkAllowCreateOldPlan(final UiCallback<Boolean> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("PlanDataImpl", "checkAllowCreateOldPlan() callback is null");
        } else {
            LogUtil.a("PlanDataImpl", "checkAllowCreateOldPlan()");
            etr.b().checkAllowCreateOldPlan(new UiCallback<Boolean>() { // from class: com.huawei.health.plan.impl.PlanImpl.1
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    ReleaseLogUtil.c("PlanDataImpl", "checkAllowCreateOldPlan()  failed errorCode=", Integer.valueOf(i));
                    uiCallback.onFailure(i, str);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public void onSuccess(Boolean bool) {
                    uiCallback.onSuccess(bool);
                }
            });
        }
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public boolean isOpenRemind() {
        boolean isOpenRemind;
        if (eve.b) {
            return ety.c().b(this.c);
        }
        int i = this.c;
        if (i == 0) {
            isOpenRemind = etr.b().isOpenRemind();
        } else if (i == 3) {
            isOpenRemind = euc.e().a();
        } else {
            LogUtil.b("PlanDataImpl", "isOpenRemind planType illegal");
            isOpenRemind = false;
        }
        LogUtil.a("PlanDataImpl", "isOpenRemind mPlanType: ", Integer.valueOf(this.c), " isOpen: ", Boolean.valueOf(isOpenRemind));
        return isOpenRemind;
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public void finishPlan(int i, String str, UiCallback<String> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("PlanDataImpl", "finishPlan: callback is null.");
            return;
        }
        if (eve.b) {
            ety.c().b(i, str, this.c, uiCallback);
        } else if (this.c == 0) {
            etr.b().finishPlan(str, uiCallback);
        } else {
            LogUtil.b("PlanDataImpl", "finishPlan: planType illegal.");
        }
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public void getPlanProgress(String str, UiCallback<PlanRecord> uiCallback) {
        etr.b().getPlanProgress(str, uiCallback);
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public PlanRecord getPlanProgress(String str, boolean z) {
        int i = this.c;
        if (i == 0) {
            return etr.b().getPlanProgress(str);
        }
        if (i != 3) {
            LogUtil.b("PlanDataImpl", "getPlanProgress: planType illegal.");
            return null;
        }
        if (z) {
            return euc.e().c(str);
        }
        return euc.e().b(str);
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public boolean updatePlanProgress(WorkoutRecord workoutRecord) {
        boolean updatePlanProgress;
        workoutRecord.saveExerciseTime(ggl.i(ghz.d(workoutRecord.acquireExerciseTime())));
        if (eve.b) {
            boolean updatePlanProgress2 = etr.b().updatePlanProgress(workoutRecord);
            if (updatePlanProgress2) {
                HiHealthNativeApi.a(BaseApplication.getContext()).insertFitnessData(new GsonBuilder().serializeSpecialFloatingPointValues().create().toJson(workoutRecord).replaceAll("NaN", "0"));
            } else {
                LogUtil.h("PlanDataImpl", "insertWorkoutRecord error");
            }
            return updatePlanProgress2;
        }
        int i = this.c;
        if (i == 0) {
            updatePlanProgress = etr.b().updatePlanProgress(workoutRecord);
        } else if (i == 3) {
            euc.e().d(workoutRecord);
            updatePlanProgress = true;
        } else {
            LogUtil.b("PlanDataImpl", "updatePlanProgress: planType illegal.");
            updatePlanProgress = false;
        }
        if (updatePlanProgress) {
            HiHealthNativeApi.a(BaseApplication.getContext()).insertFitnessData(new GsonBuilder().serializeSpecialFloatingPointValues().create().toJson(workoutRecord).replaceAll("NaN", "0"));
        } else {
            LogUtil.h("PlanDataImpl", "insertWorkoutRecord error");
        }
        return updatePlanProgress;
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public void updatePlanName(String str, String str2) {
        int i = this.c;
        if (i == 0) {
            etr.b().updatePlanName(str, str2);
        } else if (i == 3) {
            euc.e().e(str2);
        } else {
            LogUtil.b("PlanDataImpl", "updatePlanName: planType illegal.");
        }
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public void updateRemindTime(boolean z, int i) {
        if (eve.b) {
            ety.c().b(z, i, this.c);
            return;
        }
        int i2 = this.c;
        if (i2 == 0) {
            etr.b().setExerciseRemind(z, i);
        } else {
            if (i2 == 3) {
                if (!z) {
                    i = -1;
                }
                euc.e().b(i);
                return;
            }
            LogUtil.b("PlanDataImpl", "updatePlanRemindTime: planType illegal.");
        }
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public int getRemindTime() {
        if (eve.b) {
            return ety.c().c(this.c);
        }
        int i = this.c;
        if (i == 0) {
            return etr.b().getRemindTime();
        }
        if (i == 3) {
            return euc.e().g();
        }
        LogUtil.b("PlanDataImpl", "getRemindTime: planType illegal.");
        return 0;
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public void getPlanRecords(int i, int i2, UiCallback<List<PlanRecord>> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("PlanDataImpl", "getPlanRecords: callback is null.");
            return;
        }
        int i3 = this.c;
        if (i3 == 0) {
            etr.b().getJoinedPlans(i, i2, uiCallback);
            return;
        }
        if (i3 == 3) {
            euc.e().a(uiCallback);
        } else if (i3 == 6) {
            etr.b().getOldRunPlanLocalRecords(i, i2, uiCallback);
        } else {
            LogUtil.b("PlanDataImpl", "getPlanRecords: planType illegal.");
        }
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public void getPlanStatistics(UiCallback<PlanStatistics> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("PlanDataImpl", "getPlanStatistics: callback is null.");
            return;
        }
        int i = this.c;
        if (i == -1) {
            etr.b().getPlanStatistics(4, uiCallback);
            return;
        }
        if (i == 0) {
            etr.b().getPlanStatistics(2, uiCallback);
        } else if (i == 3) {
            etr.b().getPlanStatistics(3, uiCallback);
        } else {
            LogUtil.b("PlanDataImpl", "getPlanStatistics: planType illegal.");
        }
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public void getRecommedPlans(int i, final UiCallback<List<PlanInfo>> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("PlanDataImpl", "getRecommedPlans: callback is null.");
            return;
        }
        if (eve.b && this.c != 0) {
            euh.a().d(this.c, i, uiCallback);
            return;
        }
        final ArrayList arrayList = new ArrayList();
        int i2 = this.c;
        if (i2 == -1) {
            LogUtil.a("PlanDataImpl", "getRecommedPlans PACKAGE");
            euh.a().d(-1, i, uiCallback);
        } else if (i2 == 0) {
            etr.b().getRecommedPlans(new UiCallback<List<mmw>>() { // from class: com.huawei.health.plan.impl.PlanImpl.4
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i3, String str) {
                    uiCallback.onFailure(i3, str);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(List<mmw> list) {
                    LogUtil.a("PlanDataImpl", "getRecommedPlans RUN" + list.size());
                    arrayList.addAll(list);
                    uiCallback.onSuccess(arrayList);
                }
            });
        } else if (i2 == 3) {
            e(i, arrayList, uiCallback);
        } else {
            LogUtil.b("PlanDataImpl", "getRecommedPlans: planType illegal.");
        }
    }

    private void e(int i, final List<PlanInfo> list, final UiCallback<List<PlanInfo>> uiCallback) {
        euh.a().b(i, -1, new UiCallback<List<FitnessPackageInfo>>() { // from class: com.huawei.health.plan.impl.PlanImpl.5
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i2, String str) {
                uiCallback.onFailure(i2, str);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onSuccess(List<FitnessPackageInfo> list2) {
                LogUtil.a("PlanDataImpl", "getRecommedPlans PACKAGE" + list2.size());
                list.addAll(list2);
                uiCallback.onSuccess(list);
            }
        });
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public List<FitWorkout> getWorkoutListFromLocalByDifficulties(int i, int i2, int i3, Integer[] numArr) {
        return new ArrayList(0);
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public List<ffi> getWeekWorkouts(Plan plan) {
        return euy.b(plan);
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public PlanStat getPlanBestRecord(String str, float f) {
        int i = this.c;
        if (i == 0) {
            return etr.b().getPlanStat(str);
        }
        if (i == 3) {
            return etz.c(f, str);
        }
        LogUtil.b("PlanDataImpl", "getPlanBestRecord: planType illegal.");
        return null;
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public void queryAllCompletedFitnessPlanFromCloud(ResultCallback resultCallback) {
        euc.e().e(resultCallback);
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public Plan getjoinedPlanById(String str) {
        int i = this.c;
        if (i == 0) {
            return etr.b().getJoinedPlan(str);
        }
        if (i == 3) {
            return etz.a(euc.e().d(str));
        }
        LogUtil.b("PlanDataImpl", "getjoinedPlanById: planType illegal.");
        return null;
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public String getFitnessPlanTempId(String str) {
        UserFitnessPlanInfo d = euc.e().d(str);
        if (d != null) {
            return d.acquirePlanTempId();
        }
        return null;
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public FitnessPackageInfo getFitnessPkgInfoByTempId(String str) {
        return euh.a().d(str);
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public float getFitnessPlanShouldCompleteCalorie(int i, WorkoutRecord workoutRecord, UserInfomation userInfomation) {
        if (i == 0) {
            return euc.e().b(workoutRecord, userInfomation);
        }
        if (i == 1) {
            return euc.e().e(workoutRecord, userInfomation);
        }
        LogUtil.b("PlanDataImpl", "getFitnessPlanShouldCompleteCalorie, period illegal.");
        return 0.0f;
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public float getFitnessPlanPackageTotalCalorie(FitnessPackageInfo fitnessPackageInfo) {
        return euc.e().c(fitnessPackageInfo);
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public void cancelTodayPlanRemind(Plan plan) {
        etl.e(plan);
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public Map<String, Integer> getPlanWorkoutOrders(String str) {
        return etr.b().getWorkoutOrders(str);
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public boolean updatePlanBestRecord(String str, int i, int i2) {
        return etr.b().updateBestRecord(str, i, i2);
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public void getAllPlans(int i, UiCallback<mnw> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("PlanDataImpl", "getAllPlans: callback is null.");
        } else {
            eun.a().getAllPlans(i, uiCallback);
        }
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public void getAchievementForecast(int i, int i2, int i3, int i4, UiCallback<mog> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("PlanDataImpl", "getAchievementForecast: callback is null.");
        } else {
            eun.a().getAchievementForecast(i, i2, i3, i4, uiCallback);
        }
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public void createIntelligentRunPlan(CreateRunPlanParams createRunPlanParams, UiCallback<Plan> uiCallback) {
        ety.c().d(createRunPlanParams, uiCallback);
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public void updatePlan(UserInfoBean userInfoBean, String str, UiCallback<IntPlan> uiCallback) {
        if (uiCallback == null || userInfoBean == null) {
            LogUtil.b("PlanDataImpl", "createPlan: callback or runPlanUserInfoBean is null.");
        } else {
            ety.c().a(userInfoBean, str, uiCallback);
        }
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public void getCurrentReportIndex(UiCallback<Integer> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("PlanDataImpl", "getAchievementForecast: callback is null.");
        } else {
            eun.a().getCurrentReportIndex(uiCallback);
        }
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public void postFeedback(mof mofVar, UiCallback<String> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("PlanDataImpl", "postFeedback: callback is null.");
        } else {
            ety.c().d(mofVar, uiCallback);
        }
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public void getTrainingReport(String str, int i, UiCallback<mny> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("PlanDataImpl", "getTrainingReport: callback is null.");
        } else {
            eun.a().getTrainingReport(str, i, uiCallback);
        }
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public void getCoachAdvice(int i, String str, IntPlanBean intPlanBean, boolean z, UiCallback<String> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("PlanDataImpl", "getCoachAdvice: callback is null.");
        } else if (intPlanBean != null) {
            ety.c().a(i, intPlanBean, uiCallback);
        } else {
            eun.a().getCoachAdvice(str, z, uiCallback);
        }
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public void updatePlanDate(String str, List<Integer> list, List<Integer> list2, UiCallback<String> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("PlanDataImpl", "updatePlanDate: callback is null.");
        } else {
            ety.c().e(str, list, list2, uiCallback);
        }
    }

    @Override // com.huawei.health.plan.api.PlanApi
    @Deprecated
    public void getWorkoutById(String str, String str2, String str3, UiCallback<FitWorkout> uiCallback) {
        if (uiCallback == null) {
            LogUtil.b("PlanDataImpl", "getWorkoutById: callback is null.");
        } else {
            etr.b().getWorkout(new ffl.d(str).d(str2).c(str3).b(), uiCallback);
        }
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public void updatePlanReport(String str, String str2, final UiCallback<String> uiCallback) {
        eqa.a().updatePlanReport(str, str2, new DataCallback() { // from class: com.huawei.health.plan.impl.PlanImpl.2
            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onFailure(int i, String str3) {
                LogUtil.b("PlanDataImpl", "updatePlanReport fail:", str3);
                uiCallback.onFailure(i, str3);
                OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_PUSH_PLAN_REPORT_60010009.value(), i);
            }

            @Override // com.huawei.basefitnessadvice.callback.DataCallback
            public void onSuccess(JSONObject jSONObject) {
                if (jSONObject == null) {
                    LogUtil.h("PlanDataImpl", "updatePlanReport onSuccess data is null.");
                    uiCallback.onSuccess(null);
                } else {
                    LogUtil.a("PlanDataImpl", "updatePlanReport success:", jSONObject.toString());
                    uiCallback.onSuccess(jSONObject.toString());
                }
            }
        });
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public void getTodayRunPanCourse(Plan plan, UiCallback<FitWorkout> uiCallback) {
        if (plan == null) {
            LogUtil.b("PlanDataImpl", "getTodayRunPanCourseName currentPlan == null");
            uiCallback.onFailure(0, "currentPlan == null");
            return;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        mnu d = ase.d(plan, gib.d(calendar.get(7)), ase.e(plan));
        if (koq.b(d.a()) || d.e()) {
            LogUtil.b("PlanDataImpl", "getTodayRunPanCourseName getCourseDataBeanList == null or getIsDayClockIn:", Boolean.valueOf(d.e()));
            uiCallback.onFailure(0, "getCourseDataBeanList == null");
            return;
        }
        for (CourseDataBean courseDataBean : d.a()) {
            if (courseDataBean != null && !courseDataBean.d()) {
                e(plan, courseDataBean.a(), uiCallback);
                return;
            }
        }
    }

    private void e(Plan plan, String str, final UiCallback<FitWorkout> uiCallback) {
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.b("PlanDataImpl", "getCourseCourse planApi : planApi is null.");
        } else {
            planApi.setPlanType(0);
            planApi.getWorkoutById(str, plan.acquireVersion(), CommonUtil.x(), new UiCallback<FitWorkout>() { // from class: com.huawei.health.plan.impl.PlanImpl.8
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str2) {
                    LogUtil.h("PlanDataImpl", "getCourseCourse getCourseName errorInfo: ", str2);
                    uiCallback.onFailure(i, str2);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(FitWorkout fitWorkout) {
                    LogUtil.a("PlanDataImpl", "getCourseCourse getWorkoutById onSuccess");
                    if (fitWorkout == null) {
                        uiCallback.onSuccess(null);
                        LogUtil.b("PlanDataImpl", "getCourseCourse getWorkoutById onSuccess data == null");
                    } else {
                        uiCallback.onSuccess(fitWorkout);
                    }
                }
            });
        }
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public void createFitnessPkg(String str, UiCallback<IntPlan> uiCallback) {
        if (str.isEmpty()) {
            LogUtil.b("PlanDataImpl", "createFitnessPkg: planTempId isEmpty.");
        } else {
            euc.e().c(new epx(str), uiCallback);
        }
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public void getAllFitnessPackage(int i, UiCallback<List<FitnessPackageInfo>> uiCallback) {
        euh.a().b(i, -1, uiCallback);
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public void getAllFitnessPackage(int i, int i2, UiCallback<List<FitnessPackageInfo>> uiCallback) {
        euh.a().b(i, i2, uiCallback);
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public IntPlan getCurrentIntPlan() {
        return ety.c().a();
    }

    @Override // com.huawei.health.plan.api.PlanApi
    /* renamed from: getCurrentIntPlan, reason: merged with bridge method [inline-methods] */
    public void b(final UiCallback<IntPlan> uiCallback) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: epg
                @Override // java.lang.Runnable
                public final void run() {
                    PlanImpl.this.b(uiCallback);
                }
            });
            return;
        }
        CountDownLatch countDownLatch = f2924a.get();
        if (countDownLatch != null) {
            try {
                countDownLatch.await(200L, TimeUnit.MILLISECONDS);
            } catch (InterruptedException unused) {
                LogUtil.b("PlanDataImpl", "await InterruptedException");
            }
        }
        ety.c().b(new UiCallback<epo>() { // from class: com.huawei.health.plan.impl.PlanImpl.10
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                uiCallback.onFailure(i, str);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onSuccess(epo epoVar) {
                uiCallback.onSuccess(epoVar);
            }
        });
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public void preLoadPlan() {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: epd
                @Override // java.lang.Runnable
                public final void run() {
                    PlanImpl.this.preLoadPlan();
                }
            });
        } else {
            f2924a.set(new CountDownLatch(1));
            ety.c().b(new UiCallback<epo>() { // from class: com.huawei.health.plan.impl.PlanImpl.9
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    PlanImpl.this.b();
                    LogUtil.b("PlanDataImpl", "preLoadPlan errorCode is ", Integer.valueOf(i), " errorInfo is ", str);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public void onSuccess(epo epoVar) {
                    PlanImpl.this.b();
                    LogUtil.a("PlanDataImpl", "preLoadPlan onSuccess.");
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        CountDownLatch andSet = f2924a.getAndSet(null);
        if (andSet != null) {
            andSet.countDown();
        }
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public void updateAction(moc mocVar, UiCallback<String> uiCallback) {
        ety.c().b(mocVar, uiCallback);
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public void updateIntPlanReport(String str, int i, int i2, String str2, long j, UiCallback<String> uiCallback) {
        ety.c().b(str, i, i2, str2, j, uiCallback);
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public void getIntPlanReport(String str, int i, int i2, UiCallback<String> uiCallback) {
        ety.c().b(str, i, i2, uiCallback);
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public void updateIntPlanInfo(String str, int i, int i2, String str2, UiCallback<String> uiCallback) {
        ety.c().a(str, i, i2, str2, uiCallback);
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public void createPlan(IntPlanBean intPlanBean, UiCallback<IntPlan> uiCallback) {
        ety.c().e(intPlanBean, uiCallback);
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public void setNeedUpdateCurrentPlan() {
        etx.b().e();
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public boolean isNeedSendFinishPlanToDevice(String str) {
        return euv.e(str);
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public String getNeedSendFinishPlanId() {
        return euv.e();
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public void updateSendFinishPlanDevice(String str) {
        euv.a(str);
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public void updateDayRecord(String str, mob mobVar) {
        ety.c().e(str, mobVar);
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public void updateDayRecord(IntPlan intPlan, WorkoutRecord workoutRecord, DataCallback dataCallback) {
        etr.b().updateDayRecord(intPlan, workoutRecord, dataCallback);
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public void deletePlanExerciseRecord(String str) {
        ety.c().e(str);
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public void replacePlanSchedule(ReplacePlanBean replacePlanBean, UiCallback<Boolean> uiCallback) {
        ety.c().c(replacePlanBean, uiCallback);
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public void leavePlan(LeavePlanCalendarBean leavePlanCalendarBean, UiCallback<IntPlan> uiCallback) {
        ety.c().b(leavePlanCalendarBean, uiCallback);
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public void cancelLeave(LeavePlanCalendarBean leavePlanCalendarBean, UiCallback<IntPlan> uiCallback) {
        ety.c().c(leavePlanCalendarBean, uiCallback);
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public void updatePlanCalendar(LeavePlanCalendarBean leavePlanCalendarBean, UiCallback<IntPlan> uiCallback) {
        ety.c().e(leavePlanCalendarBean, uiCallback);
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public void checkSportRecordOutPlanPunch(UiCallback<List<kwm>> uiCallback, List<kwm> list) {
        if (uiCallback == null) {
            ReleaseLogUtil.c("PlanDataImpl", "callback is null, return");
        } else {
            ety.c().b(new AnonymousClass7(uiCallback, list));
        }
    }

    /* renamed from: com.huawei.health.plan.impl.PlanImpl$7, reason: invalid class name */
    public class AnonymousClass7 extends UiCallback<epo> {
        final /* synthetic */ UiCallback c;
        final /* synthetic */ List d;

        AnonymousClass7(UiCallback uiCallback, List list) {
            this.c = uiCallback;
            this.d = list;
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            ReleaseLogUtil.c("PlanDataImpl", "errorCode is ", Integer.valueOf(i), " errorInfo is ", str);
            this.c.onFailure(i, str);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onSuccess(epo epoVar) {
            if (epoVar == null) {
                ReleaseLogUtil.c("PlanDataImpl", "data is null, return ");
                this.c.onSuccess(new ArrayList());
                return;
            }
            if (epoVar.getPlanType() != IntPlan.PlanType.AI_FITNESS_PLAN) {
                ReleaseLogUtil.e("PlanDataImpl", "not ai fitness plan, not show energy replacement card");
                this.c.onSuccess(new ArrayList());
                return;
            }
            final List<kwm> c = ase.c(BaseApplication.getContext(), (List<kwm>) this.d);
            if (koq.b(c)) {
                this.c.onSuccess(new ArrayList());
                return;
            }
            kwm kwmVar = c.get(0);
            int e = ase.e(epoVar, kwmVar.b());
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(kwmVar.b());
            int d = gib.d(calendar.get(7));
            if (ase.a(epoVar.getDayInfo(e, d)) != 0) {
                ReleaseLogUtil.e("PlanDataImpl", "not training day, callback empty array");
                this.c.onSuccess(new ArrayList());
            } else {
                final UiCallback uiCallback = this.c;
                ase.e(epoVar, e, d, new IBaseResponseCallback() { // from class: epe
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i, Object obj) {
                        PlanImpl.AnonymousClass7.this.b(c, uiCallback, i, obj);
                    }
                });
            }
        }

        public /* synthetic */ void b(List list, UiCallback uiCallback, int i, Object obj) {
            if (!koq.e(obj, RecordData.class)) {
                LogUtil.h("PlanDataImpl", "getTrackData onResult obj not instanceof List");
                uiCallback.onSuccess(PlanImpl.this.c((List<kwm>) list, new ArrayList()));
            } else {
                uiCallback.onSuccess(PlanImpl.this.c((List<kwm>) list, (List<RecordData>) obj));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<kwm> c(List<kwm> list, List<RecordData> list2) {
        ArrayList<kwm> arrayList = new ArrayList<>();
        for (kwm kwmVar : list) {
            Iterator<RecordData> it = list2.iterator();
            while (true) {
                if (it.hasNext()) {
                    RecordData next = it.next();
                    if (next.getIsInPlan() == 0 && kwmVar.c(next.getStartTime(), next.getEndTime())) {
                        arrayList.add(kwmVar);
                        break;
                    }
                }
            }
        }
        return arrayList;
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public int getNeedFinishPlanType() {
        return euv.a();
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public IntPlan convertRunPlan(Plan plan) {
        return eve.a(plan);
    }

    @Override // com.huawei.health.plan.api.PlanApi
    public void setNeedFinishPlanType(int i) {
        euv.d(i);
    }
}
