package com.huawei.health.suggestion.ui.tabfragments.provider;

import android.content.Context;
import android.os.Message;
import android.view.View;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.basefitnessadvice.model.intplan.IntAction;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.suggestion.FitnessExternalUtils;
import com.huawei.health.suggestion.ui.tabfragments.provider.RunPlanProvider;
import com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessPlanProvider;
import com.huawei.health.suggestion.util.ClickEventUtils;
import com.huawei.health.suggestion.util.JumpUtil;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.FitnessPackageInfo;
import com.huawei.pluginfitnessadvice.Workout;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import defpackage.ase;
import defpackage.fib;
import defpackage.fys;
import defpackage.fyw;
import defpackage.gds;
import defpackage.koq;
import defpackage.mod;
import defpackage.nrh;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes8.dex */
public class RunPlanProvider extends FitnessPlanProvider {
    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public int getCourseCategory() {
        return 258;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public int getPageType() {
        return 4;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public int getType() {
        return 258;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessPlanProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public boolean isCustomActive(Context context) {
        return LoginInit.getInstance(context).getIsLogined() && FitnessExternalUtils.b();
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessPlanProvider
    public void updateMyPlans(IntPlan intPlan) {
        ArrayList arrayList = new ArrayList(1);
        if (ase.l(intPlan)) {
            Message obtainMessage = this.mHandler.obtainMessage(0);
            arrayList.add(intPlan);
            obtainMessage.obj = arrayList;
            this.mHandler.sendMessage(obtainMessage);
            return;
        }
        getRecommendedPlanList();
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessPlanProvider
    public void getRecommendedPlanList() {
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.h("Track_Provider_Suggestion_RunPlanProvider", "refreshFitnessPlan, getRecommendPlans : planApi is null.");
        } else {
            planApi.getAllFitnessPackage(-1, new UiCallback<List<FitnessPackageInfo>>() { // from class: com.huawei.health.suggestion.ui.tabfragments.provider.RunPlanProvider.2
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    LogUtil.h("Track_Provider_Suggestion_RunPlanProvider", "onFailure errorCode = ", Integer.valueOf(i), " errorInfo = ", str);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public void onSuccess(List<FitnessPackageInfo> list) {
                    LogUtil.a("Track_Provider_Suggestion_RunPlanProvider", "onSuccess invoke");
                    Message obtainMessage = RunPlanProvider.this.mHandler.obtainMessage(1);
                    ArrayList arrayList = new ArrayList();
                    for (FitnessPackageInfo fitnessPackageInfo : list) {
                        if (ase.e(fitnessPackageInfo)) {
                            arrayList.add(fitnessPackageInfo);
                        }
                    }
                    obtainMessage.obj = arrayList;
                    RunPlanProvider.this.mHandler.sendMessage(obtainMessage);
                }
            });
        }
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessPlanProvider
    public void onClickTitleEvent(Context context) {
        HashMap hashMap = new HashMap();
        hashMap.put("target", Integer.toString(IntPlan.PlanType.AI_RUN_PLAN.getType()));
        JumpUtil.b(context, hashMap);
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessPlanProvider
    public void setMyPlanClickListener(final Context context, final List<IntPlan> list, Map<String, Object> map) {
        map.put("CLICK_EVENT_LISTENER", new OnClickSectionListener() { // from class: com.huawei.health.suggestion.ui.tabfragments.provider.RunPlanProvider.4
            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, int i2) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(String str) {
                if ("MORE_CLICK_EVENT".equals(str)) {
                    RunPlanProvider.this.onClickTitleEvent(context);
                }
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i) {
                if (koq.b(list, i)) {
                    LogUtil.b("Track_Provider_Suggestion_RunPlanProvider", "position is out of bounds");
                    return;
                }
                IntPlan intPlan = (IntPlan) list.get(i);
                if (intPlan == null) {
                    LogUtil.b("Track_Provider_Suggestion_RunPlanProvider", "workoutPlanInfo is null");
                    return;
                }
                if (fib.e().c()) {
                    if (intPlan.getCompatiblePlan() == null || !intPlan.getPlanType().equals(IntPlan.PlanType.RUN_PLAN)) {
                        JumpUtil.c(RunPlanProvider.this.mContext);
                        ase.d(intPlan, "2", new int[0]);
                    } else {
                        RunPlanProvider.this.e(intPlan.getCompatiblePlan());
                    }
                }
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, String str) {
                int i2;
                if (koq.b(list, i)) {
                    return;
                }
                IntPlan intPlan = (IntPlan) list.get(i);
                if (intPlan.getPlanType().equals(IntPlan.PlanType.RUN_PLAN)) {
                    LogUtil.h("Track_Provider_Suggestion_RunPlanProvider", "is old run plan.");
                    RunPlanProvider.this.e(intPlan.getCompatiblePlan());
                    return;
                }
                List<IntAction> b = fys.b(intPlan);
                boolean z = false;
                int i3 = 0;
                IntAction intAction = null;
                if (b.size() > 0) {
                    Iterator<IntAction> it = b.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        IntAction next = it.next();
                        i3++;
                        if (next.getPunchFlag() == 0) {
                            intAction = next;
                            break;
                        }
                    }
                    i2 = i3;
                    z = true;
                } else {
                    i2 = 0;
                }
                if (fyw.l(intPlan) && str.equals("BUTTON_CLICK_EVENT")) {
                    RunPlanProvider.this.d(intPlan, intAction, z, i2);
                }
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final IntPlan intPlan, IntAction intAction, boolean z, final int i) {
        CourseApi courseApi;
        if (this.mIsJump2VipView) {
            ClickEventUtils.e(ClickEventUtils.ClickEventType.JUMP_VIP, null);
            return;
        }
        if (!z && ase.g(intPlan) > 1) {
            JumpUtil.a(1, this.mContext, IntPlan.PlanType.AI_RUN_PLAN.getType(), intPlan.getCompatiblePlan().acquireId());
        } else {
            if (!z || intAction == null || (courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class)) == null) {
                return;
            }
            courseApi.getCourseById(intAction.getActionId(), null, null, new UiCallback<Workout>() { // from class: com.huawei.health.suggestion.ui.tabfragments.provider.RunPlanProvider.3
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i2, String str) {
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(Workout workout) {
                    FitWorkout a2 = mod.a(workout);
                    if (a2 == null) {
                        return;
                    }
                    long currentTimeMillis = System.currentTimeMillis();
                    if (a2.isRunModelCourse()) {
                        gds.e(RunPlanProvider.this.mContext, a2, intPlan, currentTimeMillis, i);
                    } else {
                        gds.d(RunPlanProvider.this.mContext, a2, intPlan, i, currentTimeMillis);
                    }
                }
            });
        }
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public String getSubViewTitle() {
        return BaseApplication.getContext().getResources().getString(R.string._2130837544_res_0x7f020028);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final Plan plan) {
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(BaseApplication.getActivity());
        builder.e(R.string._2130845216_res_0x7f021e20);
        builder.czz_(R.string._2130848409_res_0x7f022a99, new View.OnClickListener() { // from class: gey
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RunPlanProvider.this.aLV_(plan, view);
            }
        });
        builder.czC_(R.string._2130844969_res_0x7f021d29, new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.tabfragments.provider.RunPlanProvider.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("Track_Provider_Suggestion_RunPlanProvider", "showOfflineDialog start ai run plan");
                RunPlanProvider.this.c(plan);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        final NoTitleCustomAlertDialog e = builder.e();
        BaseApplication.getActivity().runOnUiThread(new Runnable() { // from class: gez
            @Override // java.lang.Runnable
            public final void run() {
                RunPlanProvider.a(NoTitleCustomAlertDialog.this);
            }
        });
    }

    public /* synthetic */ void aLV_(Plan plan, View view) {
        LogUtil.a("Track_Provider_Suggestion_RunPlanProvider", "showOfflineDialog ok");
        c(plan);
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void a(NoTitleCustomAlertDialog noTitleCustomAlertDialog) {
        if (noTitleCustomAlertDialog != null) {
            noTitleCustomAlertDialog.show();
        }
    }

    public void c(Plan plan) {
        if (plan == null) {
            LogUtil.h("Track_Provider_Suggestion_RunPlanProvider", "finishPlan currentPlan is null");
            return;
        }
        String acquireId = plan.acquireId();
        if (acquireId != null) {
            PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
            if (planApi == null) {
                LogUtil.b("Track_Provider_Suggestion_RunPlanProvider", "finishPlan, getCurrentPlan : planApi is null.");
            } else {
                planApi.setPlanType(0);
                planApi.finishPlan(0, acquireId, new UiCallback<String>() { // from class: com.huawei.health.suggestion.ui.tabfragments.provider.RunPlanProvider.1
                    @Override // com.huawei.basefitnessadvice.callback.UiCallback
                    public void onFailure(int i, String str) {
                        LogUtil.b("Track_Provider_Suggestion_RunPlanProvider", "finish  plan failed  ", str);
                        nrh.d(BaseApplication.getActivity(), str);
                    }

                    @Override // com.huawei.basefitnessadvice.callback.UiCallback
                    /* renamed from: a, reason: merged with bridge method [inline-methods] */
                    public void onSuccess(String str) {
                        LogUtil.a("Track_Provider_Suggestion_RunPlanProvider", "finish  plan onSuccess  ", str);
                        RunPlanProvider.this.onClickTitleEvent(BaseApplication.getContext());
                    }
                });
            }
        }
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider
    public String getLogTag() {
        return "Track_Provider_Suggestion_RunPlanProvider";
    }
}
