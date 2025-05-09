package defpackage;

import android.view.View;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.suggestion.PluginSuggestion;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import health.compact.a.Services;
import health.compact.a.Utils;

/* loaded from: classes4.dex */
public class gho {
    public void c() {
        asc.e().b(new Runnable() { // from class: gho.4
            @Override // java.lang.Runnable
            public void run() {
                PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
                if (planApi == null) {
                    LogUtil.b("Track_RunPlanUtils", "refreshMyPlanBanner, getCurrentPlan : planApi is null.");
                } else {
                    planApi.getCurrentIntPlan(new b());
                }
            }
        });
    }

    static class b extends UiCallback<IntPlan> {
        private b() {
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.h("Track_RunPlanUtils", "errorCode = ", Integer.valueOf(i), " errorInfo = ", str);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onSuccess(IntPlan intPlan) {
            if (intPlan == null) {
                LogUtil.b("Track_RunPlanUtils", "GetMyPlanCallback : data is null.");
                return;
            }
            Plan compatiblePlan = intPlan.getCompatiblePlan();
            if (compatiblePlan == null || !intPlan.getPlanType().equals(IntPlan.PlanType.RUN_PLAN)) {
                return;
            }
            gho.b(compatiblePlan);
        }
    }

    public static void b(final Plan plan) {
        HandlerExecutor.e(new Runnable() { // from class: ghr
            @Override // java.lang.Runnable
            public final void run() {
                gho.a(Plan.this);
            }
        });
    }

    static /* synthetic */ void a(final Plan plan) {
        if (BaseApplication.getActivity() == null) {
            LogUtil.h("Track_RunPlanUtils", "getActivity is null.");
            return;
        }
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(BaseApplication.getActivity());
        builder.e(R.string._2130845216_res_0x7f021e20);
        builder.czz_(R.string._2130848409_res_0x7f022a99, new View.OnClickListener() { // from class: ghv
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                gho.aMQ_(Plan.this, view);
            }
        });
        if (!Utils.l()) {
            builder.czC_(R.string._2130844969_res_0x7f021d29, new View.OnClickListener() { // from class: ghw
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    gho.aMR_(Plan.this, view);
                }
            });
        }
        NoTitleCustomAlertDialog e = builder.e();
        if (e != null) {
            e.setCancelable(false);
            e.show();
        }
    }

    static /* synthetic */ void aMQ_(Plan plan, View view) {
        LogUtil.a("Track_RunPlanUtils", "showOfflineDialog ok");
        c(plan, false);
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void aMR_(Plan plan, View view) {
        LogUtil.a("Track_RunPlanUtils", "showOfflineDialog start ai run plan");
        c(plan, true);
        ViewClickInstrumentation.clickOnView(view);
    }

    public static void c(Plan plan, final boolean z) {
        if (plan == null) {
            LogUtil.h("Track_RunPlanUtils", "finishPlan currentPlan is null");
            return;
        }
        final String acquireId = plan.acquireId();
        if (acquireId != null) {
            PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
            if (planApi == null) {
                LogUtil.b("Track_RunPlanUtils", "finishPlan, getCurrentPlan : planApi is null.");
            } else {
                planApi.setPlanType(0);
                planApi.finishPlan(0, acquireId, new UiCallback<String>() { // from class: gho.2
                    @Override // com.huawei.basefitnessadvice.callback.UiCallback
                    public void onFailure(int i, String str) {
                        LogUtil.b("Track_RunPlanUtils", "finish  plan failed  ", str);
                        nrh.c(BaseApplication.getActivity(), str);
                    }

                    @Override // com.huawei.basefitnessadvice.callback.UiCallback
                    /* renamed from: c, reason: merged with bridge method [inline-methods] */
                    public void onSuccess(String str) {
                        ary.a().e("PLAN_UPDATE");
                        LogUtil.a("Track_RunPlanUtils", "finish  plan onSuccess  ", str);
                        if (z) {
                            gho.e();
                        } else {
                            gho.b(acquireId);
                        }
                    }
                });
            }
        }
    }

    public static void b(String str) {
        PluginSuggestion pluginSuggestion = (PluginSuggestion) Services.a("PluginFitnessAdvice", PluginSuggestion.class);
        if (pluginSuggestion != null) {
            pluginSuggestion.switchToPlanReport(str);
        }
    }

    public static void e() {
        PluginSuggestion pluginSuggestion = (PluginSuggestion) Services.a("PluginFitnessAdvice", PluginSuggestion.class);
        if (pluginSuggestion != null) {
            pluginSuggestion.jumpToPlanTab();
        }
    }
}
