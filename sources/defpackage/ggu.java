package defpackage;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.basefitnessadvice.model.intplan.StatInfo;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.sport.utils.ResultUtil;
import com.huawei.health.suggestion.ui.plan.viewholder.IntCurrentPlanViewHolder;
import com.huawei.health.suggestion.ui.plan.viewholder.IntPlanListViewHolder;
import com.huawei.health.suggestion.util.JumpUtil;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitnessPackageInfo;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.uikit.hwtextview.widget.HwTextView;
import com.huawei.utils.RiskBiAnalytics;
import health.compact.a.EnvironmentInfo;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class ggu {
    private static Handler d = new Handler(Looper.getMainLooper());

    public static void aMu_(final ViewGroup viewGroup, final boolean z) {
        ((PlanApi) Services.a("CoursePlanService", PlanApi.class)).b(new UiCallback<IntPlan>() { // from class: ggu.4
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                LogUtil.b("Suggestion_intelligentPlanViewUtil", "getCurrentPlan onFailure");
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onSuccess(final IntPlan intPlan) {
                if (intPlan == null) {
                    if (z) {
                        ggu.aMy_(viewGroup);
                    }
                } else if (intPlan.getPlanType() == IntPlan.PlanType.AI_FITNESS_PLAN) {
                    HandlerExecutor.e(new Runnable() { // from class: ggu.4.3
                        @Override // java.lang.Runnable
                        public void run() {
                            new IntCurrentPlanViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sug_layout_intplan_progress, viewGroup, true), 1).a(intPlan);
                        }
                    });
                } else {
                    LogUtil.c("Suggestion_intelligentPlanViewUtil", "Only show with ai fitness plan");
                }
            }
        });
    }

    public static void aMy_(final ViewGroup viewGroup) {
        ((PlanApi) Services.a("CoursePlanService", PlanApi.class)).getAllFitnessPackage(-1, new UiCallback<List<FitnessPackageInfo>>() { // from class: ggu.5
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                LogUtil.h("Suggestion_intelligentPlanViewUtil", "loadRecommendPlanList onFailure.");
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onSuccess(List<FitnessPackageInfo> list) {
                for (FitnessPackageInfo fitnessPackageInfo : list) {
                    if (fitnessPackageInfo.getPlanType() == IntPlan.PlanType.AI_FITNESS_PLAN.getType()) {
                        new IntPlanListViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sug_layout_intplan_recommend_list_item, viewGroup, true), "weight").a(fitnessPackageInfo);
                        return;
                    }
                }
            }
        });
    }

    public static void e(Context context, final IntPlan intPlan, int i, final UiCallback uiCallback) {
        if (context == null) {
            LogUtil.b("Suggestion_intelligentPlanViewUtil", "finishPlanAndCreateReport error context is null");
            return;
        }
        boolean f = fyw.f(intPlan);
        NoTitleCustomAlertDialog.Builder czz_ = new NoTitleCustomAlertDialog.Builder(context).e(i).czz_(R.string._2130848409_res_0x7f022a99, new View.OnClickListener() { // from class: ggy
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ggu.aMw_(IntPlan.this, uiCallback, view);
            }
        });
        if (f) {
            czz_.czC_(R.string._2130848663_res_0x7f022b97, new View.OnClickListener() { // from class: ggw
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ggu.aMx_(IntPlan.this, uiCallback, view);
                }
            });
        }
        NoTitleCustomAlertDialog e = czz_.e();
        e.setCancelable(false);
        e.show();
    }

    static /* synthetic */ void aMw_(IntPlan intPlan, UiCallback uiCallback, View view) {
        d(intPlan, 0);
        a(intPlan, false, uiCallback);
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void aMx_(IntPlan intPlan, UiCallback uiCallback, View view) {
        a(intPlan, true, uiCallback);
        ViewClickInstrumentation.clickOnView(view);
    }

    public static void d(IntPlan intPlan, int i) {
        if (intPlan == null || intPlan.getMetaInfo() == null || intPlan.getPlanType() == null) {
            LogUtil.h("Suggestion_intelligentPlanViewUtil", "onEventEndPlanRate plan == null");
        } else {
            StatInfo stat = intPlan.getStat("progress");
            gge.e(stat == null ? 0.0f : ((Float) stat.getValue()).floatValue(), intPlan.getMetaInfo().getName(), intPlan.getPlanType().getType(), i);
        }
    }

    public static void a(final IntPlan intPlan, boolean z, final UiCallback uiCallback) {
        if (intPlan == null) {
            if (uiCallback != null) {
                uiCallback.onFailure(d, 20004, "currentPlan is null");
                return;
            }
            return;
        }
        if (fyw.a()) {
            if (uiCallback != null) {
                uiCallback.onFailure(d, -6, "data in sync");
                return;
            }
            return;
        }
        final PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            if (uiCallback != null) {
                uiCallback.onFailure(d, 9999, "intPlanApi is null");
            }
        } else if (fyw.f(intPlan) && intPlan.getPlanType() == IntPlan.PlanType.AI_FITNESS_PLAN) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: ggv
                @Override // java.lang.Runnable
                public final void run() {
                    ggu.d(IntPlan.this, uiCallback, planApi);
                }
            });
        } else {
            d(intPlan, planApi, z, uiCallback);
        }
    }

    static /* synthetic */ void d(IntPlan intPlan, UiCallback uiCallback, PlanApi planApi) {
        LogUtil.a("Suggestion_intelligentPlanViewUtil", "hasReport finishPlan planId:" + intPlan.getPlanId());
        fyd fydVar = new fyd();
        fydVar.d(intPlan);
        JSONObject d2 = fydVar.d(2, false);
        if (d2 == null) {
            if (uiCallback != null) {
                uiCallback.onFailure(d, ResultUtil.ResultCode.NO_NET, "getReport is null");
            }
        } else if (fydVar.d(d2, 2, System.currentTimeMillis())) {
            d(intPlan, planApi, true, uiCallback);
        } else if (uiCallback != null) {
            uiCallback.onFailure(d, -6, "updateReport fail");
        }
    }

    public static void d(final Context context, final double d2, final double d3) {
        if (cei.b().getBondedProductsForDeviceOnly(HealthDevice.HealthDeviceKind.HDK_WEIGHT).size() == 0 || EnvironmentInfo.k()) {
            JumpUtil.e(context, d2, d3, "/PluginHealthModel/IntelligentPlanView");
            return;
        }
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(context);
        builder.e(context.getString(R.string._2130848667_res_0x7f022b9b)).czE_(context.getString(R.string.IDS_weight_device_entry), new View.OnClickListener() { // from class: ggu.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ash.a("measure_weight_for_plan", "true");
                JumpUtil.a(context, HealthDevice.HealthDeviceKind.HDK_WEIGHT.name(), -1);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czA_(context.getString(R.string._2130848669_res_0x7f022b9d), new View.OnClickListener() { // from class: ggu.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                JumpUtil.e(context, d2, d3, "/PluginHealthModel/IntelligentPlanView");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.e().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d() {
        ash.d("plan_update_week");
        ash.d("ai_plan_sync_name");
        ash.d("ai_plan_sync");
        ash.d("ai_plan_create");
        ash.d("last_week_not_exercise");
        ash.d("last_two_week_not_exercise");
        ash.d("last_week_not_exercise_well");
        ash.d("ai_plan_finish");
        ash.d("showReportTime");
        ash.d("has_shown_plan_dialog_today");
        ash.d("has_shown_watch_tip_today");
        ash.d("read_oversea_dialog");
    }

    public static void b(Context context, boolean z, int i) {
        LogUtil.a("Suggestion_intelligentPlanViewUtil", "enter saveTempPlanToSp");
        if (z) {
            SharedPreferenceManager.e(context, "intelligent_plan", "intelligent_plan_create_temp", String.valueOf(z), new StorageParams());
            SharedPreferenceManager.e(context, "intelligent_plan", "intelligent_plan_sport_consumption", String.valueOf(i), new StorageParams());
            return;
        }
        SharedPreferenceManager.c(context, "intelligent_plan", "intelligent_plan_create_temp");
        SharedPreferenceManager.c(context, "intelligent_plan", "intelligent_plan_sport_consumption");
    }

    private static void d(final IntPlan intPlan, PlanApi planApi, final boolean z, final UiCallback uiCallback) {
        if (intPlan == null) {
            if (uiCallback != null) {
                uiCallback.onFailure(d, 20004, "currentPlan is null");
            }
        } else {
            LogUtil.a("Suggestion_intelligentPlanViewUtil", "finishPlan planId:" + intPlan.getPlanId());
            planApi.finishPlan(1, intPlan.getPlanId(), new UiCallback<String>() { // from class: ggu.3
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    ReleaseLogUtil.d("Suggestion_intelligentPlanViewUtil", "finishPlan errorCode: ", Integer.valueOf(i), "errorInfo: ", str);
                    RiskBiAnalytics.c(RiskBiAnalytics.FitPLANRiskType.RISK_TYPE_FINISH_PLAN.value(), "finishPlan fail errorCode: ", Integer.valueOf(i), "errorInfo: ", str, "PlanId: ", IntPlan.this.getPlanId());
                    UiCallback uiCallback2 = uiCallback;
                    if (uiCallback2 != null) {
                        if (i == 200025) {
                            uiCallback2.onFailure(ResultUtil.ResultCode.PRIVACY_POST_OFF, str);
                        } else {
                            uiCallback2.onFailure(ResultUtil.ResultCode.NO_NET, str);
                        }
                    }
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(String str) {
                    ggu.d();
                    fyx.a(BaseApplication.getContext().getString(R.string._2130846309_res_0x7f022265), IntPlan.this);
                    LogUtil.a("Suggestion_intelligentPlanViewUtil", "finishPlan OK");
                    UiCallback uiCallback2 = uiCallback;
                    if (uiCallback2 != null) {
                        uiCallback2.onSuccess("finish ok");
                    }
                    boolean f = fyw.f(IntPlan.this);
                    if (!f && fyw.p(IntPlan.this)) {
                        nrh.b(BaseApplication.getActivity(), R.string._2130848630_res_0x7f022b76);
                    }
                    if (f && z) {
                        JumpUtil.c(2, BaseApplication.getActivity(), IntPlan.this.getPlanType().getType(), IntPlan.this.getPlanId(), 2);
                    }
                }
            });
        }
    }

    public static String d(long j, IntPlan intPlan) {
        if (intPlan == null) {
            LogUtil.h("Suggestion_intelligentPlanViewUtil", "intPlan is null, return");
            return "";
        }
        int b = (int) (((gib.b(j) / 86400000) - ((intPlan.getPlanTimeInfo().getBeginDate() * 1000) / 86400000)) + 1);
        if (IntPlan.PlanType.AI_FITNESS_PLAN.equals(intPlan.getPlanType())) {
            b = jdl.d(ase.h(intPlan), DateFormatUtil.b(j));
        }
        int e = ase.e(intPlan);
        if (b > e) {
            b = e;
        }
        return b(b, intPlan);
    }

    private static String b(int i, IntPlan intPlan) {
        long j;
        if (i <= 0) {
            if (IntPlan.PlanType.AI_FITNESS_PLAN.equals(intPlan.getPlanType())) {
                j = DateFormatUtil.c(ase.h(intPlan));
            } else {
                j = ase.j(intPlan);
            }
            return nsf.b(R.string._2130846123_res_0x7f0221ab, DateUtils.formatDateTime(BaseApplication.getContext(), j, 24));
        }
        return ffy.c(BaseApplication.getContext(), i, ase.e(intPlan), 0);
    }

    public static void aMv_(IntPlan intPlan, HwTextView hwTextView, ImageView imageView) {
        if (intPlan == null) {
            LogUtil.h("Suggestion_intelligentPlanViewUtil", "plan is null");
            return;
        }
        if (hwTextView == null) {
            LogUtil.h("Suggestion_intelligentPlanViewUtil", "planReportButton is null");
            return;
        }
        if (imageView == null) {
            LogUtil.h("Suggestion_intelligentPlanViewUtil", "redDot is null");
            return;
        }
        if (fyw.x(intPlan) || fyw.t(intPlan)) {
            hwTextView.setVisibility(8);
            imageView.setVisibility(8);
            return;
        }
        if (intPlan.getPlanType() == IntPlan.PlanType.AI_RUN_PLAN) {
            aMA_(intPlan, hwTextView, imageView);
        } else if (intPlan.getPlanType() == IntPlan.PlanType.AI_FITNESS_PLAN) {
            aMz_(intPlan, hwTextView, imageView);
        } else {
            hwTextView.setVisibility(8);
            imageView.setVisibility(8);
        }
        aMB_(intPlan, hwTextView, imageView);
    }

    private static void aMB_(final IntPlan intPlan, final HwTextView hwTextView, final ImageView imageView) {
        hwTextView.setOnClickListener(new View.OnClickListener() { // from class: ggu.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (nsn.o()) {
                    LogUtil.h("Suggestion_intelligentPlanViewUtil", "plan_report_button click too fast.");
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                if (fyw.a()) {
                    LogUtil.h("Suggestion_intelligentPlanViewUtil", "is in sync data progress.");
                    nrh.d(HwTextView.this.getContext(), HwTextView.this.getContext().getString(R.string.IDS_hw_show_sync_cloud_history_data));
                    ViewClickInstrumentation.clickOnView(view);
                } else if (intPlan.getPlanType() == null) {
                    LogUtil.h("Suggestion_intelligentPlanViewUtil", "plan.getPlanType()");
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    JumpUtil.a(1, HwTextView.this.getContext(), intPlan.getPlanType().getType(), intPlan.getPlanId());
                    imageView.setVisibility(8);
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        });
    }

    private static void aMA_(IntPlan intPlan, HwTextView hwTextView, ImageView imageView) {
        if (fyw.v(intPlan)) {
            ReleaseLogUtil.e("Suggestion_intelligentPlanViewUtil", "show last week report");
            hwTextView.setVisibility(0);
            hwTextView.setText(ffy.d(BaseApplication.getContext(), R.string._2130848639_res_0x7f022b7f, new Object[0]));
            if (fyw.b()) {
                ReleaseLogUtil.e("Suggestion_intelligentPlanViewUtil", "show last week report red dot.");
                imageView.setVisibility(0);
                return;
            } else {
                imageView.setVisibility(8);
                return;
            }
        }
        hwTextView.setVisibility(8);
        imageView.setVisibility(8);
    }

    private static void aMz_(IntPlan intPlan, HwTextView hwTextView, ImageView imageView) {
        if (fyw.u(intPlan)) {
            ReleaseLogUtil.e("Suggestion_intelligentPlanViewUtil", "show this week report");
            hwTextView.setVisibility(0);
            hwTextView.setText(ffy.d(BaseApplication.getContext(), R.string._2130848640_res_0x7f022b80, new Object[0]));
            if (fyw.e()) {
                ReleaseLogUtil.e("Suggestion_intelligentPlanViewUtil", "show this week report red dot.");
                imageView.setVisibility(0);
                return;
            } else {
                imageView.setVisibility(8);
                return;
            }
        }
        aMA_(intPlan, hwTextView, imageView);
    }

    public static String e(float f, float f2) {
        if (UnitUtil.h()) {
            double d2 = f - f2;
            return ffy.b(R.plurals._2130903488_res_0x7f0301c0, (int) Math.ceil(UnitUtil.h(d2)), UnitUtil.e(UnitUtil.h(d2), 1, 1));
        }
        return ffy.b(R.plurals._2130903487_res_0x7f0301bf, (int) (f - f2), UnitUtil.e(Math.round(r4 * 10.0f) / 10.0f, 1, 1));
    }
}
