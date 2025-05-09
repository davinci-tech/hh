package defpackage;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import com.huawei.basefitnessadvice.callback.DataCallback;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.intplan.IntAction;
import com.huawei.basefitnessadvice.model.intplan.IntDayPlan;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.basefitnessadvice.model.intplan.IntWeekPlan;
import com.huawei.basefitnessadvice.model.intplan.RecordData;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.suggestion.PluginSuggestion;
import com.huawei.health.suggestion.ui.plan.fragment.IntelligencePlanFragment;
import com.huawei.health.suggestion.ui.plan.viewholder.IntAiDialogViewHolder;
import com.huawei.health.suggestion.ui.plan.viewmodel.IntPlanDetailDataViewModel;
import com.huawei.health.suggestion.util.JumpUtil;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.plandata.UserInfoBean;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import defpackage.fxf;
import health.compact.a.EnvironmentInfo;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class fxf {

    /* renamed from: a, reason: collision with root package name */
    private IntelligencePlanFragment f12678a;
    private IntPlanDetailDataViewModel c;
    private IntPlan d;

    public fxf(IntPlanDetailDataViewModel intPlanDetailDataViewModel, IntelligencePlanFragment intelligencePlanFragment) {
        this.c = intPlanDetailDataViewModel;
        this.f12678a = intelligencePlanFragment;
    }

    public void a(IntPlan intPlan) {
        this.d = intPlan;
    }

    public void b(Context context, IntPlan intPlan) {
        if (context == null) {
            return;
        }
        a(intPlan);
        d();
        if (!e()) {
            k(context);
            return;
        }
        if (fyw.x(this.d)) {
            LogUtil.a("Suggestion_IntPlanInteract", "showPlanOverdueDialog expired dialog show");
            n(context);
        } else {
            if (fyw.t(this.d)) {
                return;
            }
            i(context);
            if (ase.k(this.d)) {
                LogUtil.a("Suggestion_IntPlanInteract", "init is h5 plan");
            } else {
                if (e(context)) {
                    return;
                }
                d(context);
            }
        }
    }

    public void e(Context context, IntPlan intPlan) {
        if (context == null || intPlan == null || fyw.x(intPlan) || fyw.t(intPlan) || fyw.a()) {
            return;
        }
        int g = ase.g(this.d);
        if (fyw.a(intPlan) && a(intPlan.getPlanId(), true, g)) {
            SharedPreferenceManager.c("MMKV_SUGGEST_MODULE_TAG", "weekReportDialogIndex", intPlan.getPlanId() + " " + g);
            l(context);
            return;
        }
        if (fyw.b(intPlan) && a(intPlan.getPlanId(), false, g)) {
            SharedPreferenceManager.c("MMKV_SUGGEST_MODULE_TAG", "weekReportDialogIndex", intPlan.getPlanId() + " " + (g - 1));
            o(context);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x004d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean a(java.lang.String r12, boolean r13, int r14) {
        /*
            r11 = this;
            java.lang.String r0 = "weekReportDialogIndex"
            java.lang.String r1 = ""
            java.lang.String r2 = "MMKV_SUGGEST_MODULE_TAG"
            java.lang.String r0 = health.compact.a.SharedPreferenceManager.e(r2, r0, r1)
            java.lang.String r3 = "isNeedShowWeekReportDialog:"
            java.lang.String r5 = " isThisWeek:"
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r13)
            java.lang.String r7 = " currentWeek:"
            java.lang.Integer r8 = java.lang.Integer.valueOf(r14)
            java.lang.String r9 = " cache:"
            r4 = r12
            r10 = r0
            java.lang.Object[] r1 = new java.lang.Object[]{r3, r4, r5, r6, r7, r8, r9, r10}
            java.lang.String r2 = "Suggestion_IntPlanInteract"
            com.huawei.hwlogsmodel.LogUtil.a(r2, r1)
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            r2 = 1
            r3 = 0
            if (r1 != 0) goto L4a
            java.lang.String r1 = " "
            java.lang.String[] r4 = r0.split(r1)
            int r4 = r4.length
            r5 = 2
            if (r4 != r5) goto L4a
            java.lang.String[] r0 = r0.split(r1)
            r1 = r0[r3]
            r0 = r0[r2]
            boolean r12 = r12.equals(r1)
            if (r12 == 0) goto L4a
            int r12 = health.compact.a.CommonUtil.h(r0)
            goto L4b
        L4a:
            r12 = r3
        L4b:
            if (r13 == 0) goto L52
            if (r14 <= r12) goto L50
            goto L51
        L50:
            r2 = r3
        L51:
            return r2
        L52:
            int r14 = r14 - r2
            if (r14 <= r12) goto L56
            goto L57
        L56:
            r2 = r3
        L57:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.fxf.a(java.lang.String, boolean, int):boolean");
    }

    private void i(Context context) {
        IntPlan intPlan = this.d;
        if (intPlan != null) {
            if (intPlan.getPlanType() == IntPlan.PlanType.AI_RUN_PLAN || this.d.getPlanType() == IntPlan.PlanType.AI_FITNESS_PLAN) {
                int g = ase.g(this.d);
                if (g <= 1) {
                    ash.d("plan_update_week", Integer.toString(1), this.d.getPlanId());
                    return;
                }
                int b = b();
                ReleaseLogUtil.e("Suggestion_IntPlanInteract", "currentWeek:", Integer.valueOf(g), " savedWeek:", Integer.valueOf(b));
                if (g > b) {
                    c(new AnonymousClass5(g, context));
                }
            }
        }
    }

    /* renamed from: fxf$5, reason: invalid class name */
    class AnonymousClass5 extends UiCallback<String> {
        final /* synthetic */ int c;
        final /* synthetic */ Context e;

        AnonymousClass5(int i, Context context) {
            this.c = i;
            this.e = context;
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.b("Suggestion_IntPlanInteract", "handlePlanUpdate fail err:", Integer.valueOf(i), " errInfo:", str);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onSuccess(String str) {
            if (fxf.this.d != null) {
                ash.d("plan_update_week", Integer.toString(this.c), fxf.this.d.getPlanId());
                String string = this.e.getString(R.string._2130848686_res_0x7f022bae);
                fxf.this.aHZ_(this.e, string, new View.OnClickListener() { // from class: fxx
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        fxf.AnonymousClass5.this.aIj_(view);
                    }
                });
                fyx.a(string, fxf.this.d);
                if (fyo.e(this.e)) {
                    ReleaseLogUtil.e("Suggestion_IntPlanInteract", "IntelligencePlanFragment has CalendarPermission");
                    fxf.this.b(this.e);
                    fxf.this.c(this.e);
                }
                ReleaseLogUtil.e("Suggestion_IntPlanInteract", "save update plan week:", Integer.valueOf(this.c));
            } else {
                ReleaseLogUtil.d("Suggestion_IntPlanInteract", "mCurrentPlan is null");
            }
            nrh.b(this.e, R.string._2130848595_res_0x7f022b53);
            fxf.this.a();
        }

        /* synthetic */ void aIj_(View view) {
            fxf.this.c.b();
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        IntPlan intPlan = this.d;
        if (intPlan != null) {
            if (intPlan.getPlanType() != null && gim.d(this.d.getPlanType().getType()) && fyc.d()) {
                LogUtil.a("Suggestion_IntPlanInteract", "send plan to Device :", ghb.e(this.d));
                fyc.b(this.d, BaseApplication.getContext());
            } else {
                PluginSuggestion.getInstance().startSendIntelligentPlan();
            }
        }
    }

    private int b() {
        try {
            return Integer.parseInt(ash.d("plan_update_week", this.d.getPlanId()));
        } catch (NumberFormatException e2) {
            LogUtil.h("Suggestion_IntPlanInteract", "update:", LogAnonymous.b((Throwable) e2));
            return 1;
        }
    }

    private void c(final UiCallback<String> uiCallback) {
        asc.e().b(new Runnable() { // from class: fxe
            @Override // java.lang.Runnable
            public final void run() {
                fxf.this.d(uiCallback);
            }
        });
    }

    /* synthetic */ void d(final UiCallback uiCallback) {
        HiHealthManager.d(BaseApplication.getContext()).fetchUserData(new HiCommonListener() { // from class: fxf.2
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i, Object obj) {
                if (!ffy.c(obj)) {
                    ReleaseLogUtil.c("Suggestion_IntPlanInteract", "FetchUserDataListener data is null.");
                    uiCallback.onFailure(0, "user info data is null");
                    return;
                }
                if (!koq.e(obj, HiUserInfo.class)) {
                    ReleaseLogUtil.d("Suggestion_IntPlanInteract", "FetchUserDataListener onSuccess data is not List<HiUserInfo>.");
                    uiCallback.onFailure(0, "user info data is not match");
                    return;
                }
                HiUserInfo hiUserInfo = (HiUserInfo) ((List) obj).get(0);
                if (hiUserInfo != null) {
                    fxf.this.c((UiCallback<String>) uiCallback, fxf.d(hiUserInfo));
                } else {
                    ReleaseLogUtil.d("Suggestion_IntPlanInteract", "FetchUserDataListener onSuccess userInfo is null.");
                    uiCallback.onFailure(0, "userInfo is null");
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i, Object obj) {
                LogUtil.h("Suggestion_IntPlanInteract", "fetchUserData fail:", Integer.valueOf(i), " ", obj);
                uiCallback.onFailure(i, obj.toString());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static UserInfoBean d(HiUserInfo hiUserInfo) {
        UserInfoBean userInfoBean = new UserInfoBean();
        userInfoBean.setSex(hiUserInfo.getGenderOrDefaultValue());
        userInfoBean.setAge(hiUserInfo.getAgeOrDefaultValue());
        userInfoBean.setHeight(hiUserInfo.getHeightOrDefaultValue());
        userInfoBean.setWeight((int) hiUserInfo.getWeightOrDefaultValue());
        return userInfoBean;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final UiCallback<String> uiCallback, UserInfoBean userInfoBean) {
        if (this.d == null) {
            LogUtil.b("Suggestion_IntPlanInteract", "updateCurrentPlan mCurrentPlan is null.");
            return;
        }
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.b("Suggestion_IntPlanInteract", "updatePlan : planApi is null.");
            uiCallback.onFailure(0, "planApi is null");
        } else {
            planApi.updatePlan(userInfoBean, this.d.getPlanId(), new UiCallback<IntPlan>() { // from class: fxf.4
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    ReleaseLogUtil.d("Suggestion_IntPlanInteract", "updatePlan fail,", Integer.valueOf(i), " ", str);
                    uiCallback.onFailure(i, str);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public void onSuccess(IntPlan intPlan) {
                    ReleaseLogUtil.e("Suggestion_IntPlanInteract", "updateCurrentPlan success");
                    fxf.this.d = intPlan;
                    uiCallback.onSuccess("update plan success");
                }
            });
        }
    }

    private void k(Context context) {
        if (ase.k(this.d)) {
            LogUtil.a("Suggestion_IntPlanInteract", "showPrivacySwitchDialog is h5 plan");
            return;
        }
        IntAiDialogViewHolder.e eVar = new IntAiDialogViewHolder.e();
        eVar.e = context.getString(R.string._2130848739_res_0x7f022be3);
        eVar.j = context.getString(R.string._2130841131_res_0x7f020e2b);
        eVar.b = new View.OnClickListener() { // from class: fxk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                fxf.this.aIg_(view);
            }
        };
        eVar.d = context.getString(R.string._2130841130_res_0x7f020e2a);
        eVar.f3287a = new View.OnClickListener() { // from class: fxs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                fxf.this.aIh_(view);
            }
        };
        this.c.d(eVar);
    }

    /* synthetic */ void aIg_(View view) {
        ggs.b(3, true);
        this.c.b();
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void aIh_(View view) {
        this.c.b();
        ViewClickInstrumentation.clickOnView(view);
    }

    private boolean e(final Context context) {
        String string;
        String format;
        if (this.d != null) {
            if (this.d.getPlanId().equals(ash.b("ai_plan_create"))) {
                LogUtil.a("Suggestion_IntPlanInteract", "checkFirstCreatePlanDialog OK");
                return false;
            }
            if (!fyw.k(this.d)) {
                ash.a("ai_plan_create", this.d.getPlanId());
            } else {
                boolean z = this.d.getMetaInfo().getPlanType() == IntPlan.PlanType.AI_FITNESS_PLAN;
                if (z && Utils.o()) {
                    LogUtil.a("Suggestion_IntPlanInteract", "current plan is ai fitness but is oversea");
                    return false;
                }
                LogUtil.a("Suggestion_IntPlanInteract", "checkFirstCreatePlanDialog show in the begin day");
                IntAiDialogViewHolder.e eVar = new IntAiDialogViewHolder.e();
                if (z) {
                    eVar.e = context.getString(R.string._2130848623_res_0x7f022b6f);
                    format = context.getString(R.string._2130846310_res_0x7f022266);
                } else {
                    eVar.e = String.format(Locale.ENGLISH, context.getString(R.string._2130848627_res_0x7f022b73), this.d.getMetaInfo().getName());
                    if (this.d.getMetaInfo().getPlanType() == IntPlan.PlanType.AI_RUN_PLAN) {
                        string = context.getString(R.string._2130846312_res_0x7f022268);
                    } else {
                        string = context.getString(R.string._2130846311_res_0x7f022267);
                    }
                    format = String.format(Locale.ENGLISH, string, this.d.getMetaInfo().getName());
                }
                eVar.j = b(context, R.string._2130848645_res_0x7f022b85);
                eVar.b = new View.OnClickListener() { // from class: fxp
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        fxf.this.aIa_(context, view);
                    }
                };
                eVar.d = b(context, R.string._2130848646_res_0x7f022b86);
                eVar.f3287a = new View.OnClickListener() { // from class: fxq
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        fxf.this.aIb_(view);
                    }
                };
                if (!EnvironmentInfo.k()) {
                    this.c.d(eVar);
                } else {
                    this.c.b();
                }
                if (!ash.b("notify_to_watch").equals(this.d.getPlanId())) {
                    LogUtil.a("Suggestion_IntPlanInteract", "send the plan's creating message to watch.");
                    fyx.a(format, this.d);
                    ash.a("notify_to_watch", this.d.getPlanId());
                }
                return true;
            }
        }
        return false;
    }

    /* synthetic */ void aIa_(Context context, View view) {
        h(context);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void aIb_(View view) {
        this.c.b();
        ash.a("ai_plan_create", this.d.getPlanId());
        ViewClickInstrumentation.clickOnView(view);
    }

    private static String b(Context context, int i) {
        if (context == null) {
            return "";
        }
        String language = context.getResources().getConfiguration().locale.getLanguage();
        boolean z = true;
        boolean z2 = "it".equalsIgnoreCase(language) || "de".equalsIgnoreCase(language) || "es".equalsIgnoreCase(language);
        if (!"fr".equalsIgnoreCase(language) && !"en".equalsIgnoreCase(language)) {
            z = false;
        }
        if (z2 || z) {
            return context.getString(i).toUpperCase();
        }
        return context.getString(i);
    }

    private void h(Context context) {
        if (fyo.e(context)) {
            LogUtil.a("Suggestion_IntPlanInteract", "IntelligencePlanFragment has CalendarPermission");
            c(context);
            return;
        }
        this.c.b();
        ash.a("ai_plan_create", this.d.getPlanId());
        LogUtil.a("Suggestion_IntPlanInteract", "request CalendarPermission");
        this.f12678a.a(new String[]{"android.permission.WRITE_CALENDAR", "android.permission.READ_CALENDAR"});
    }

    public void c(Context context) {
        if (context == null) {
            return;
        }
        this.c.b();
        ash.a("ai_plan_create", this.d.getPlanId());
        fyo.a(this.d, context, 0);
    }

    public void a(Context context) {
        if (this.d == null || context == null) {
            return;
        }
        j(context);
    }

    private void j(Context context) {
        if (fyo.e(context)) {
            b(context);
        } else {
            LogUtil.a("Suggestion_IntPlanInteract", "request CalendarPermission");
        }
    }

    public void b(Context context) {
        fyo.c(context, ash.b("ai_plan_sync_name"), (IntPlan.PlanType) null);
        ash.d("ai_plan_sync_name");
        ash.d("ai_plan_sync");
    }

    public boolean d(Context context) {
        IntPlan intPlan = this.d;
        if (intPlan != null && (intPlan.getPlanType().equals(IntPlan.PlanType.AI_FITNESS_PLAN) || this.d.getPlanType().equals(IntPlan.PlanType.AI_RUN_PLAN))) {
            String d2 = ash.d("has_shown_plan_dialog_today", this.d.getPlanId());
            String valueOf = String.valueOf(fyw.e(this.d));
            LogUtil.a("Suggestion_IntPlanInteract", "currentDay:", valueOf);
            if (valueOf.equals(d2)) {
                LogUtil.a("Suggestion_IntPlanInteract", "abnormal exercise dialog has been shown today.");
                return false;
            }
            int h = fyw.h(this.d);
            LogUtil.a("Suggestion_IntPlanInteract", "checkShowPlanAbnormalDialog absentWeekCnt:", Integer.valueOf(h));
            if (h >= 3) {
                f(context);
                return true;
            }
            if (!fyw.a(this.d, "last_two_week_not_exercise") && fyw.s(this.d)) {
                g(context);
                return true;
            }
            if (!fyw.a(this.d, "last_week_not_exercise_well") && fyw.r(this.d)) {
                m(context);
                return true;
            }
            if (fyw.c(this.d, 5)) {
                c(context, 5);
                return true;
            }
            if (fyw.c(this.d, 4)) {
                c(context, 4);
                return true;
            }
            if (fyw.c(this.d, 3)) {
                c(context, 3);
                return true;
            }
            if (fyw.c(this.d, 2)) {
                c(context, 2);
                return true;
            }
            if (fyw.c(this.d, 1)) {
                c(context, 1);
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aHZ_(Context context, String str, View.OnClickListener onClickListener) {
        IntAiDialogViewHolder.e eVar = new IntAiDialogViewHolder.e();
        eVar.e = str;
        eVar.j = context.getString(R.string._2130848409_res_0x7f022a99);
        eVar.b = onClickListener;
        this.c.d(eVar);
    }

    private void e(String str) {
        IntAiDialogViewHolder.e eVar = new IntAiDialogViewHolder.e();
        eVar.e = str;
        this.c.d(eVar);
    }

    private void g(Context context) {
        String d2 = ash.d("last_two_week_not_exercise", this.d.getPlanId());
        final String valueOf = String.valueOf(fyw.i(this.d));
        LogUtil.a("Suggestion_IntPlanInteract", "showLastWeekNotExerciseDialog lastRemindTime:", d2, " currentWeek:", valueOf);
        if (d2 == null || !d2.equals(valueOf)) {
            final String valueOf2 = String.valueOf(fyw.e(this.d));
            String string = context.getString(R.string._2130848625_res_0x7f022b71);
            aHZ_(context, string, new View.OnClickListener() { // from class: fxm
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    fxf.this.aId_(valueOf, valueOf2, view);
                }
            });
            d(valueOf2, string);
        }
    }

    /* synthetic */ void aId_(String str, String str2, View view) {
        ash.d("last_two_week_not_exercise", str, this.d.getPlanId());
        ash.d("has_shown_plan_dialog_today", str2, this.d.getPlanId());
        this.c.b();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void m(Context context) {
        String string;
        String d2 = ash.d("last_week_not_exercise_well", this.d.getPlanId());
        final String valueOf = String.valueOf(fyw.i(this.d));
        LogUtil.a("Suggestion_IntPlanInteract", "showLastWeekNotExerciseWellDialog lastRemindTime:", d2, " currentWeek:", valueOf);
        if (d2 == null || !d2.equals(valueOf)) {
            final String valueOf2 = String.valueOf(fyw.e(this.d));
            if (this.d.getPlanType().equals(IntPlan.PlanType.AI_FITNESS_PLAN)) {
                string = context.getString(R.string._2130848827_res_0x7f022c3b);
            } else {
                string = context.getString(R.string._2130848624_res_0x7f022b70);
            }
            aHZ_(context, string, new View.OnClickListener() { // from class: fxn
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    fxf.this.aIe_(valueOf, valueOf2, view);
                }
            });
            d(valueOf2, string);
        }
    }

    /* synthetic */ void aIe_(String str, String str2, View view) {
        ash.d("last_week_not_exercise_well", str, this.d.getPlanId());
        ash.d("has_shown_plan_dialog_today", str2, this.d.getPlanId());
        this.c.b();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void c(Context context, int i) {
        int i2;
        String d2 = ash.d("has_shown_plan_dialog_today", this.d.getPlanId());
        final String valueOf = String.valueOf(fyw.e(this.d));
        LogUtil.a("Suggestion_IntPlanInteract", "NotExerciseDialog lastRemindTime:", d2, " currentDay:", valueOf);
        if (i == 1) {
            i2 = R.string._2130848626_res_0x7f022b72;
        } else if (i == 2) {
            i2 = R.string._2130848632_res_0x7f022b78;
        } else if (i == 3) {
            i2 = R.string._2130848633_res_0x7f022b79;
        } else if (i == 4) {
            i2 = R.string._2130848634_res_0x7f022b7a;
        } else if (i != 5) {
            return;
        } else {
            i2 = R.string._2130848764_res_0x7f022bfc;
        }
        if (d2 == null || !d2.equals(valueOf)) {
            LogUtil.a("Suggestion_IntPlanInteract", "show NotExercise tip");
            String string = context.getString(i2);
            aHZ_(context, string, new View.OnClickListener() { // from class: fxj
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    fxf.this.aIc_(valueOf, view);
                }
            });
            d(valueOf, string);
        }
    }

    /* synthetic */ void aIc_(String str, View view) {
        ash.d("has_shown_plan_dialog_today", str, this.d.getPlanId());
        this.c.b();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void n(Context context) {
        e(context.getString(R.string._2130848484_res_0x7f022ae4));
    }

    private void f(Context context) {
        e(context.getString(R.string._2130848597_res_0x7f022b55));
    }

    private void d(String str, String str2) {
        LogUtil.a("Suggestion_IntPlanInteract", "shownWatchTipToday");
        String d2 = ash.d("has_shown_watch_tip_today", this.d.getPlanId());
        if (d2 == null || !d2.equals(str)) {
            fyx.a(str2, this.d);
            ash.d("has_shown_watch_tip_today", str, this.d.getPlanId());
        }
    }

    private void l(final Context context) {
        new NoTitleCustomAlertDialog.Builder(context).e(R.string._2130848628_res_0x7f022b74).czC_(R.string._2130848478_res_0x7f022ade, new View.OnClickListener() { // from class: fxh
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                fxf.this.aIi_(context, view);
            }
        }).czz_(R.string._2130839509_res_0x7f0207d5, new View.OnClickListener() { // from class: fxl
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e().show();
        fyx.a(context.getString(R.string._2130848628_res_0x7f022b74), this.d);
    }

    /* synthetic */ void aIi_(Context context, View view) {
        ObserverManagerUtil.c("WATCH_PLAN_REPORT_DIALOG", new Object[0]);
        JumpUtil.a(1, context, this.d.getPlanType().getType(), this.d.getPlanId());
        ViewClickInstrumentation.clickOnView(view);
    }

    private void o(final Context context) {
        new NoTitleCustomAlertDialog.Builder(context).e(R.string._2130848629_res_0x7f022b75).czC_(R.string._2130848478_res_0x7f022ade, new View.OnClickListener() { // from class: fxo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                fxf.this.aIf_(context, view);
            }
        }).czz_(R.string._2130839509_res_0x7f0207d5, new View.OnClickListener() { // from class: fxi
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e().show();
        fyx.a(context.getString(R.string._2130848629_res_0x7f022b75), this.d);
    }

    /* synthetic */ void aIf_(Context context, View view) {
        ObserverManagerUtil.c("WATCH_PLAN_REPORT_DIALOG", new Object[0]);
        JumpUtil.a(1, context, this.d.getPlanType().getType(), this.d.getPlanId());
        ViewClickInstrumentation.clickOnView(view);
    }

    private boolean e() {
        return ggs.b(3);
    }

    public void d() {
        if (this.d == null) {
            LogUtil.a("Suggestion_IntPlanInteract", "no doing plan.");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: fxr
                @Override // java.lang.Runnable
                public final void run() {
                    fxf.this.c();
                }
            });
        }
    }

    /* synthetic */ void c() {
        e(this.d);
        if (this.d.getPlanType().equals(IntPlan.PlanType.AI_RUN_PLAN) || this.d.getPlanType().equals(IntPlan.PlanType.AI_FITNESS_PLAN)) {
            d(this.d);
        }
    }

    private static void e(IntPlan intPlan) {
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("Suggestion_IntPlanInteract", "lastWeekStatistics courseApi is null");
            return;
        }
        int g = ase.g(intPlan);
        long a2 = ase.a(intPlan, g);
        long e2 = ase.e(intPlan, g);
        LogUtil.a("Suggestion_IntPlanInteract", "checkInPlanPunch weekNum:", Integer.valueOf(g), " weekStartTime:", Long.valueOf(a2), " weekEndTime:", Long.valueOf(e2), " planType:", Integer.valueOf(intPlan.getPlanType().getType()));
        String j = gib.j(a2 * 1000);
        String j2 = gib.j(e2 * 1000);
        LogUtil.a("Suggestion_IntPlanInteract", "getWorkoutRecords startDate = ", j, " endDate = ", j2);
        List<WorkoutRecord> workoutRecords = courseApi.getWorkoutRecords(intPlan.getPlanId(), j, j2);
        if (koq.b(workoutRecords)) {
            LogUtil.a("Suggestion_IntPlanInteract", "plan workoutRecords empty.");
            return;
        }
        IntWeekPlan weekInfoByOrder = intPlan.getWeekInfoByOrder(g);
        if (weekInfoByOrder == null) {
            LogUtil.b("Suggestion_IntPlanInteract", "weekPlan == null:", Integer.valueOf(g));
            return;
        }
        Calendar calendar = Calendar.getInstance();
        ArrayList arrayList = new ArrayList();
        for (WorkoutRecord workoutRecord : workoutRecords) {
            if (workoutRecord.getPlanTrainDate() == 0) {
                LogUtil.b("Suggestion_IntPlanInteract", "workoutRecord getPlanTrainDate() == 0:", workoutRecord.acquireWorkoutId());
            } else {
                calendar.setTimeInMillis(ggl.e(workoutRecord.getPlanTrainDate()));
                int d2 = gib.d(calendar.get(7));
                boolean c = c(weekInfoByOrder, d2, workoutRecord);
                boolean e3 = e(weekInfoByOrder, d2, workoutRecord);
                LogUtil.a("Suggestion_IntPlanInteract", "plan train date:", Integer.valueOf(workoutRecord.getPlanTrainDate()), " planDayIndex:", Integer.valueOf(d2), " workoutId:", workoutRecord.acquireWorkoutId(), " isRecordCourseInPlan:", Boolean.valueOf(e3), " isUploadCloud:", Boolean.valueOf(c));
                if (e3 && !c) {
                    arrayList.add(workoutRecord);
                }
            }
        }
        d(intPlan, arrayList);
    }

    private static boolean c(IntWeekPlan intWeekPlan, int i, WorkoutRecord workoutRecord) {
        IntDayPlan dayByOrder = intWeekPlan.getDayByOrder(i);
        if (dayByOrder == null) {
            return false;
        }
        if (c(workoutRecord, dayByOrder)) {
            LogUtil.a("Suggestion_IntPlanInteract", "action has clocked.");
            return true;
        }
        for (int i2 = 0; i2 < dayByOrder.getRecordDataCnt(); i2++) {
            RecordData recordData = dayByOrder.getRecordData(i2);
            LogUtil.a("Suggestion_IntPlanInteract", "record data:", recordData.getWorkoutId(), " ", recordData.getRecordId());
            if (recordData.getWorkoutId().equals(workoutRecord.acquireWorkoutId())) {
                return true;
            }
        }
        return false;
    }

    private static boolean c(WorkoutRecord workoutRecord, IntDayPlan intDayPlan) {
        boolean z = false;
        for (int i = 0; i < intDayPlan.getInPlanActionCnt(); i++) {
            IntAction inPlanAction = intDayPlan.getInPlanAction(i);
            if (inPlanAction != null && inPlanAction.getActionId() != null && inPlanAction.getActionId().equals(workoutRecord.acquireWorkoutId()) && inPlanAction.getPunchFlag() == 1) {
                z = true;
            }
        }
        return z;
    }

    private static boolean e(IntWeekPlan intWeekPlan, int i, WorkoutRecord workoutRecord) {
        IntDayPlan dayByOrder = intWeekPlan.getDayByOrder(i);
        if (dayByOrder == null) {
            return false;
        }
        boolean z = false;
        for (int i2 = 0; i2 < dayByOrder.getInPlanActionCnt(); i2++) {
            if (dayByOrder.getInPlanAction(i2).getActionId().equals(workoutRecord.acquireWorkoutId())) {
                z = true;
            }
        }
        for (int i3 = 0; i3 < dayByOrder.getOutPlanActionCnt(); i3++) {
            if (dayByOrder.getOutPlanAction(i3).getActionId().equals(workoutRecord.acquireWorkoutId())) {
                z = true;
            }
        }
        return z;
    }

    private static void d(IntPlan intPlan, List<WorkoutRecord> list) {
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.h("Suggestion_IntPlanInteract", "checkPlanPunchThisWeek : planApi is null.");
            return;
        }
        for (WorkoutRecord workoutRecord : list) {
            planApi.updateDayRecord(intPlan, workoutRecord, new a(workoutRecord));
        }
    }

    static class a extends DataCallback {
        private final WorkoutRecord c;

        public a(WorkoutRecord workoutRecord) {
            this.c = workoutRecord;
        }

        @Override // com.huawei.basefitnessadvice.callback.DataCallback
        public void onFailure(int i, String str) {
            WorkoutRecord workoutRecord = this.c;
            if (workoutRecord == null) {
                LogUtil.b("Suggestion_IntPlanInteract", "updateDayRecord onFailure errorCode:", Integer.valueOf(i), "errorInfo:", str);
            } else {
                LogUtil.b("Suggestion_IntPlanInteract", "updateDayRecord onFailure,", workoutRecord.acquireWorkoutId(), Integer.valueOf(this.c.getPlanTrainDate()), " errorCode:", Integer.valueOf(i), " ", str);
            }
        }

        @Override // com.huawei.basefitnessadvice.callback.DataCallback
        public void onSuccess(JSONObject jSONObject) {
            LogUtil.a("Suggestion_IntPlanInteract", "updateDayRecord onSuccess.");
        }
    }

    private static void d(IntPlan intPlan) {
        int g = ase.g(intPlan);
        IntWeekPlan weekInfoByOrder = intPlan.getWeekInfoByOrder(g);
        if (weekInfoByOrder == null) {
            LogUtil.b("Suggestion_IntPlanInteract", "checkOutPlanPunch weekPlan == null:", Integer.valueOf(g));
            return;
        }
        long a2 = ase.a(intPlan, g) * 1000;
        long e2 = ase.e(intPlan, g) * 1000;
        if (IntPlan.PlanType.AI_RUN_PLAN.equals(intPlan.getPlanType()) && intPlan.getPlanTimeInfo() != null && TimeUnit.SECONDS.toMillis(intPlan.getPlanTimeInfo().getEndDate()) == e2) {
            e2 = jdl.e(e2);
        }
        long j = e2;
        LogUtil.a("Suggestion_IntPlanInteract", "checkOutPlanPunch weekNum:", Integer.valueOf(g), " weekStartTime:", Long.valueOf(a2), " weekEndTime:", Long.valueOf(j), " planType:", Integer.valueOf(intPlan.getPlanType().getType()));
        d(intPlan.getPlanType().getType(), a2, j, new e(weekInfoByOrder));
    }

    static class e implements IBaseResponseCallback {
        private final IntWeekPlan b;

        public e(IntWeekPlan intWeekPlan) {
            this.b = intWeekPlan;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            if (!koq.e(obj, HiHealthData.class)) {
                LogUtil.b("Suggestion_IntPlanInteract", "getRunTrackData ListTypeNotMatch.");
                return;
            }
            List list = (List) obj;
            if (fxf.b((List<HiHealthData>) list)) {
                Calendar calendar = Calendar.getInstance();
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    MotionPathSimplify e = kwz.e((HiHealthData) it.next());
                    if (fxf.c(e)) {
                        long requestStartTime = e.requestStartTime();
                        calendar.setTimeInMillis(requestStartTime);
                        int d = gib.d(calendar.get(7));
                        boolean d2 = fxf.d(this.b, d, e);
                        LogUtil.a("Suggestion_IntPlanInteract", "motionPathSimplify startTime:", Long.valueOf(requestStartTime), " endTime:", Long.valueOf(e.requestEndTime()), " sportType:", Integer.valueOf(e.requestSportType()), " distance:", Integer.valueOf(e.requestTotalDistance()), " planDayIndex:", Integer.valueOf(d), " isRecordUploadCloud:", Boolean.valueOf(d2));
                        if (!d2) {
                            fxf.e(e);
                        }
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b(List<HiHealthData> list) {
        if (!list.isEmpty() && list.get(0) != null) {
            return true;
        }
        LogUtil.h("Suggestion_IntPlanInteract", "requestTrackSimplifyData size 0");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean c(MotionPathSimplify motionPathSimplify) {
        if (motionPathSimplify == null) {
            LogUtil.h("Suggestion_IntPlanInteract", "motionPathSimplify == null.");
            return false;
        }
        if (motionPathSimplify.requestSportDataSource() == 2) {
            LogUtil.h("Suggestion_IntPlanInteract", "motionPathSimplifySPORT_DATA_SOURCE_USER_INPUT_SPORT.");
            return false;
        }
        if (motionPathSimplify.requestAbnormalTrack() == 0) {
            return true;
        }
        LogUtil.h("Suggestion_IntPlanInteract", "motionPathSimplify is abnormal.");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean d(IntWeekPlan intWeekPlan, int i, MotionPathSimplify motionPathSimplify) {
        IntDayPlan dayByOrder = intWeekPlan.getDayByOrder(i);
        if (dayByOrder == null) {
            return false;
        }
        for (int i2 = 0; i2 < dayByOrder.getRecordDataCnt(); i2++) {
            RecordData recordData = dayByOrder.getRecordData(i2);
            LogUtil.a("Suggestion_IntPlanInteract", "record data:", recordData.getRecordId(), " ", motionPathSimplify.requestStartTime() + "_" + motionPathSimplify.requestEndTime());
            if (recordData.getRecordId().equals(motionPathSimplify.requestStartTime() + "_" + motionPathSimplify.requestEndTime())) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x004d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void e(com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify r14) {
        /*
            java.lang.String r0 = "CoursePlanService"
            java.lang.Class<com.huawei.health.plan.api.PlanApi> r1 = com.huawei.health.plan.api.PlanApi.class
            java.lang.Object r0 = health.compact.a.Services.a(r0, r1)
            com.huawei.health.plan.api.PlanApi r0 = (com.huawei.health.plan.api.PlanApi) r0
            java.lang.String r1 = "Suggestion_IntPlanInteract"
            if (r0 != 0) goto L18
            java.lang.String r14 = "updateOutPlanDayRecord : planApi is null."
            java.lang.Object[] r14 = new java.lang.Object[]{r14}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r14)
            return
        L18:
            mob r2 = defpackage.ase.e(r14)
            java.lang.String r3 = "planInfo"
            java.lang.String r4 = ""
            java.lang.String r3 = r14.getExtendDataString(r3, r4)
            java.lang.String r5 = "plan_id"
            java.lang.String r5 = r14.getExtendDataString(r5, r4)
            boolean r6 = r4.equals(r3)
            if (r6 != 0) goto L45
            org.json.JSONObject r6 = new org.json.JSONObject     // Catch: org.json.JSONException -> L3c
            r6.<init>(r3)     // Catch: org.json.JSONException -> L3c
            java.lang.String r3 = "planId"
            java.lang.String r3 = r6.optString(r3)     // Catch: org.json.JSONException -> L3c
            goto L46
        L3c:
            java.lang.String r3 = "toJson error"
            java.lang.Object[] r3 = new java.lang.Object[]{r3}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r3)
        L45:
            r3 = r4
        L46:
            boolean r4 = r4.equals(r5)
            if (r4 != 0) goto L4d
            goto L4e
        L4d:
            r5 = r3
        L4e:
            java.lang.String r6 = "updateOutPlanDayRecord planId:"
            java.lang.String r8 = " workoutId:"
            java.lang.String r9 = r2.n()
            java.lang.String r10 = " startTime:"
            long r3 = r2.i()
            java.lang.Long r11 = java.lang.Long.valueOf(r3)
            java.lang.String r12 = " planCourseTime:"
            java.lang.String r3 = "plan_course_time"
            int r14 = r14.getExtendDataInt(r3)
            java.lang.Integer r13 = java.lang.Integer.valueOf(r14)
            r7 = r5
            java.lang.Object[] r14 = new java.lang.Object[]{r6, r7, r8, r9, r10, r11, r12, r13}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r1, r14)
            r0.updateDayRecord(r5, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.fxf.e(com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify):void");
    }

    public static void d(int i, long j, long j2, IBaseResponseCallback iBaseResponseCallback) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(j, j2);
        hiDataReadOption.setType(i == IntPlan.PlanType.AI_RUN_PLAN.getType() ? new int[]{258, 264, OldToNewMotionPath.SPORT_TYPE_CROSS_COUNTRY_RACE} : null);
        hiDataReadOption.setSortOrder(1);
        HiHealthNativeApi.a(arx.b()).fetchSequenceDataBySportType(hiDataReadOption, new d(iBaseResponseCallback));
    }

    static class d implements HiDataReadResultListener {
        private final IBaseResponseCallback d;

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
        }

        public d(IBaseResponseCallback iBaseResponseCallback) {
            this.d = iBaseResponseCallback;
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            if (this.d == null) {
                ReleaseLogUtil.d("Suggestion_IntPlanInteract", "MyHiDataReadResultListener intPlanInteract == null");
                return;
            }
            ArrayList arrayList = new ArrayList();
            if (!(obj instanceof SparseArray)) {
                LogUtil.h("Suggestion_IntPlanInteract", "data error.");
                this.d.d(-1, arrayList);
                return;
            }
            SparseArray sparseArray = (SparseArray) obj;
            if (!koq.e(sparseArray.get(i), HiHealthData.class)) {
                LogUtil.h("Suggestion_IntPlanInteract", "requestTrackSimplifyData onResult obj not instanceof List");
                this.d.d(-1, arrayList);
            } else {
                this.d.d(-1, (List) sparseArray.get(i));
            }
        }
    }
}
