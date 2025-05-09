package com.huawei.health.suggestion.ui.fitness.helper;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.intplan.IntAction;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.suggestion.ui.fitness.helper.IntPlanPreSportRemind;
import com.huawei.health.suggestion.util.JumpUtil;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.ase;
import defpackage.ffl;
import defpackage.ffy;
import defpackage.fys;
import defpackage.fyw;
import defpackage.gds;
import defpackage.ggk;
import defpackage.ggm;
import defpackage.gib;
import defpackage.ixx;
import defpackage.mod;
import defpackage.nrf;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class IntPlanPreSportRemind {

    /* renamed from: a, reason: collision with root package name */
    private NoTitleCustomAlertDialog f3159a;
    private CustomAlertDialog b;
    private boolean d = false;
    private Context e;

    public interface Action {
        void execute();
    }

    public IntPlanPreSportRemind(Context context) {
        this.e = context;
    }

    public void c(final String str, final Action action) {
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.h("Suggestion_IntPlanPreSportRemind", "TrainDetail PlanApi is null.");
            b(action);
        } else if (this.d) {
            LogUtil.a("Suggestion_IntPlanPreSportRemind", "planTrainRemindCheck mIsIgnorePlan true");
            b(action);
        } else {
            planApi.b(new UiCallback<IntPlan>() { // from class: com.huawei.health.suggestion.ui.fitness.helper.IntPlanPreSportRemind.1
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str2) {
                    LogUtil.h("Suggestion_IntPlanPreSportRemind", "getCurrentIntPlan : onFailure.");
                    IntPlanPreSportRemind.this.b(action);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public void onSuccess(IntPlan intPlan) {
                    if (intPlan == null || intPlan.getPlanType().equals(IntPlan.PlanType.FIT_PLAN) || fyw.t(intPlan)) {
                        LogUtil.h("Suggestion_IntPlanPreSportRemind", "Skip check course in plan");
                        IntPlanPreSportRemind.this.b(action);
                        return;
                    }
                    List<IntAction> c = fys.c(intPlan);
                    if (c.size() == 0 || fyw.o(intPlan)) {
                        IntPlanPreSportRemind.this.b(action);
                        return;
                    }
                    for (IntAction intAction : c) {
                        if (intAction.getActionId() != null && intAction.getActionId().equals(str)) {
                            if (ase.n(intPlan)) {
                                IntPlanPreSportRemind.this.e();
                                return;
                            } else {
                                IntPlanPreSportRemind.this.b(action);
                                return;
                            }
                        }
                    }
                    IntPlanPreSportRemind.this.b(c, intPlan, action);
                }
            });
        }
    }

    /* renamed from: com.huawei.health.suggestion.ui.fitness.helper.IntPlanPreSportRemind$4, reason: invalid class name */
    public class AnonymousClass4 implements Runnable {
        AnonymousClass4() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (IntPlanPreSportRemind.this.f3159a != null && IntPlanPreSportRemind.this.f3159a.isShowing()) {
                LogUtil.h("Suggestion_IntPlanPreSportRemind", "mPlanUpdateDialog has show.");
                return;
            }
            IntPlanPreSportRemind intPlanPreSportRemind = IntPlanPreSportRemind.this;
            intPlanPreSportRemind.f3159a = new NoTitleCustomAlertDialog.Builder(intPlanPreSportRemind.e).e(R.string._2130848761_res_0x7f022bf9).czC_(R.string._2130848478_res_0x7f022ade, new View.OnClickListener() { // from class: fos
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    IntPlanPreSportRemind.AnonymousClass4.this.aCc_(view);
                }
            }).e();
            IntPlanPreSportRemind.this.f3159a.setCancelable(false);
            IntPlanPreSportRemind.this.f3159a.show();
        }

        public /* synthetic */ void aCc_(View view) {
            JumpUtil.c(IntPlanPreSportRemind.this.e);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        HandlerExecutor.e(new AnonymousClass4());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<IntAction> list, final IntPlan intPlan, final Action action) {
        LogUtil.a("Suggestion_IntPlanPreSportRemind", "unfinishedCourseList size:", Integer.valueOf(list.size()));
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<IntAction> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new ffl.d(it.next().getActionId()).b());
        }
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("Suggestion_IntPlanPreSportRemind", "getCourseList : courseApi is null.");
        } else {
            courseApi.getCourseByIds(arrayList, false, new UiCallback<List<FitWorkout>>() { // from class: com.huawei.health.suggestion.ui.fitness.helper.IntPlanPreSportRemind.3
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    LogUtil.h("Suggestion_IntPlanPreSportRemind", "getCourseById : getCourseById onFailure.");
                    IntPlanPreSportRemind.this.b(action);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onSuccess(List<FitWorkout> list2) {
                    LogUtil.a("Suggestion_IntPlanPreSportRemind", "getCourseById : getCourseById onSuccess.");
                    if (list2 == null) {
                        return;
                    }
                    int size = list2.size();
                    LogUtil.a("Suggestion_IntPlanPreSportRemind", "FitWorkout amount:", Integer.valueOf(size));
                    ArrayList arrayList2 = new ArrayList(size);
                    Iterator<FitWorkout> it2 = list2.iterator();
                    while (it2.hasNext()) {
                        arrayList2.add(mod.a(it2.next()));
                    }
                    IntPlanPreSportRemind.this.e(arrayList2, intPlan, action);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(List<FitWorkout> list, IntPlan intPlan, final Action action) {
        View aBZ_;
        CustomAlertDialog customAlertDialog = this.b;
        if (customAlertDialog != null && customAlertDialog.isShowing()) {
            LogUtil.h("Suggestion_IntPlanPreSportRemind", "PlanRemindDialog has show.");
            return;
        }
        HashMap hashMap = new HashMap(1);
        hashMap.put("popup", 1);
        ixx.d().d(this.e, AnalyticsValue.INT_PLAN_1120042.value(), hashMap, 0);
        LinearLayout linearLayout = new LinearLayout(this.e);
        linearLayout.setOrientation(1);
        CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(this.e);
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                LogUtil.a("Suggestion_IntPlanPreSportRemind", "index ", Integer.valueOf(i), ", get the view of the last course.");
                aBZ_ = aBZ_(list.get(i), 1, intPlan);
            } else {
                LogUtil.a("Suggestion_IntPlanPreSportRemind", "index ", Integer.valueOf(i), ", get the view of a course.");
                aBZ_ = aBZ_(list.get(i), 0, intPlan);
            }
            linearLayout.addView(aBZ_);
        }
        builder.e(R.string._2130848726_res_0x7f022bd6).cyp_(linearLayout).cyo_(R.string._2130844445_res_0x7f021b1d, new DialogInterface.OnClickListener() { // from class: fov
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                IntPlanPreSportRemind.this.aCb_(action, dialogInterface, i2);
            }
        });
        CustomAlertDialog c = builder.c();
        this.b = c;
        c.show();
    }

    public /* synthetic */ void aCb_(Action action, DialogInterface dialogInterface, int i) {
        this.d = true;
        CustomAlertDialog customAlertDialog = this.b;
        if (customAlertDialog != null) {
            customAlertDialog.dismiss();
        }
        HashMap hashMap = new HashMap(1);
        hashMap.put("click", 1);
        ixx.d().d(this.e, AnalyticsValue.INT_PLAN_1120043.value(), hashMap, 0);
        b(action);
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    private View aBZ_(final FitWorkout fitWorkout, int i, final IntPlan intPlan) {
        View inflate = LayoutInflater.from(this.e).inflate(R.layout.course_unfinished_card_view, (ViewGroup) null);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.plan_course_item_background);
        if (intPlan.getPlanType() == IntPlan.PlanType.AI_RUN_PLAN) {
            nrf.cIS_(imageView, fitWorkout.acquireMidPicture(), nrf.e, 0, R$color.common_ui_custom_dialog_transparent_bg);
        } else {
            nrf.cIS_(imageView, fitWorkout.getTopicPreviewPicUrl(), nrf.e, 0, R$color.common_ui_custom_dialog_transparent_bg);
        }
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.course_name);
        String acquireName = fitWorkout.acquireName();
        LogUtil.a("Suggestion_IntPlanPreSportRemind", "getUnfinishedCourseView courseName: ", acquireName);
        healthTextView.setText(acquireName);
        ((HealthTextView) inflate.findViewById(R.id.course_difficulty)).setText(ffy.awT_(this.e, "L[1-4]{1}", ggm.d(fitWorkout.acquireDifficulty()), R.style.sug_card_nomsize, R.style.sug_card_nomsize));
        ((HealthTextView) inflate.findViewById(R.id.course_time)).setText(ggk.aMt_(this.e, fitWorkout.acquireDuration()));
        ((HealthButton) inflate.findViewById(R.id.start_training_button)).setOnClickListener(new View.OnClickListener() { // from class: fow
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IntPlanPreSportRemind.this.aCa_(fitWorkout, intPlan, view);
            }
        });
        if (i == 1) {
            inflate.findViewById(R.id.course_item_interval).setVisibility(8);
        } else {
            inflate.findViewById(R.id.course_item_interval).setVisibility(0);
        }
        return inflate;
    }

    public /* synthetic */ void aCa_(FitWorkout fitWorkout, IntPlan intPlan, View view) {
        this.b.dismiss();
        if (fitWorkout.isRunModelCourse()) {
            b(fitWorkout, intPlan);
        } else {
            e(fitWorkout, intPlan);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void e(FitWorkout fitWorkout, IntPlan intPlan) {
        int g = ase.g(intPlan);
        int d = gib.d(Calendar.getInstance().get(7));
        long d2 = ase.d(intPlan, g, d);
        gds.b(this.e, fitWorkout, intPlan, d2);
        ReleaseLogUtil.e("Suggestion_IntPlanPreSportRemind", "startFitnessCourse ", Integer.valueOf(g), " ", Integer.valueOf(d), Long.valueOf(d2));
    }

    private void b(FitWorkout fitWorkout, IntPlan intPlan) {
        int g = ase.g(intPlan);
        int d = gib.d(Calendar.getInstance().get(7));
        long d2 = ase.d(intPlan, g, d);
        gds.d(this.e, fitWorkout, intPlan, d2);
        ReleaseLogUtil.e("Suggestion_IntPlanPreSportRemind", "startRunCourse ", Integer.valueOf(g), " ", Integer.valueOf(d), Long.valueOf(d2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final Action action) {
        HandlerExecutor.e(new Runnable() { // from class: com.huawei.health.suggestion.ui.fitness.helper.IntPlanPreSportRemind.2
            @Override // java.lang.Runnable
            public void run() {
                Action action2 = action;
                if (action2 != null) {
                    action2.execute();
                }
            }
        });
    }
}
