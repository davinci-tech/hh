package defpackage;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.huawei.haf.router.AppRouter;
import com.huawei.health.R;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.weight.bean.WeightTargetDifferences;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.activity.healthdata.WeightGoalActivity;
import com.huawei.ui.main.stories.health.views.WeightViewDialog;
import defpackage.qry;
import health.compact.a.LanguageUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.UnitUtil;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes7.dex */
public class qry {
    static /* synthetic */ void dHV_(DialogInterface dialogInterface) {
    }

    static /* synthetic */ void dIa_(DialogInterface dialogInterface) {
    }

    private static void d(Context context, d dVar, ResponseCallback<Integer> responseCallback) {
        c(context, dVar, responseCallback);
        e(dVar, 0);
    }

    private static void c(Context context, d dVar, ResponseCallback<Integer> responseCallback) {
        if (context == null || dVar == null) {
            LogUtil.h("WeightDialogUtil", "checkShowTargetDialog context is null");
        } else {
            h(context, dVar);
            a(context, dVar, responseCallback);
        }
    }

    public static void b(Context context, long j, long j2, d dVar, ResponseCallback<Integer> responseCallback) {
        int c;
        if (context == null || dVar == null) {
            LogUtil.h("WeightDialogUtil", "showMotivatingDialog context is null");
            qyw.b(responseCallback);
            return;
        }
        String a2 = rag.a("weight_target_Id");
        if (TextUtils.isEmpty(a2)) {
            qyw.b(responseCallback);
            return;
        }
        float a3 = dVar.a();
        float e = rag.e("last_percentage");
        boolean z = Math.round(a3) >= 100;
        boolean z2 = Math.round(e) >= 100;
        rag.d("last_percentage", a3);
        LogUtil.a("WeightDialogUtil", "showMotivatingDialog percentage ", Float.valueOf(a3), " lastPercentage ", Float.valueOf(e), " isComplete ", Boolean.valueOf(z), " isLastComplete ", Boolean.valueOf(z2));
        if (z) {
            if (z2) {
                qyw.e(responseCallback, -1);
                return;
            } else {
                dVar.e(6);
                d(context, dVar, responseCallback);
                return;
            }
        }
        int a4 = a(j, j2);
        if (a4 == 0) {
            LogUtil.h("WeightDialogUtil", "showMotivatingDialog regionType is 0");
            qyw.e(responseCallback, -1);
            return;
        }
        if (!TextUtils.isEmpty(a2) && e(a4, a2)) {
            qyw.e(responseCallback, -1);
            return;
        }
        if (a4 == 1) {
            c = c(25, dVar);
        } else if (a4 == 2) {
            c = c(50, dVar);
        } else if (a4 == 3) {
            c = c(75, dVar);
        } else if (a4 != 4) {
            LogUtil.h("WeightDialogUtil", "showMotivatingDialog is default");
            c = 5;
        } else {
            c = 7;
        }
        dVar.e(c);
        d(context, dVar, responseCallback);
        rag.e(a2 + a4, true);
    }

    private static int c(int i, d dVar) {
        if (Math.round(dVar.a()) >= 100) {
            return 6;
        }
        return (Math.round(dVar.a()) <= i || Math.round(dVar.a()) >= 100) ? 5 : 8;
    }

    private static void a(final Context context, final d dVar, final ResponseCallback<Integer> responseCallback) {
        final AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        WeightViewDialog c = b(context, dVar).dIz_(dVar.g(), new View.OnClickListener() { // from class: qry.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (d.this.b() == 6 || d.this.b() == 7) {
                    Intent intent = new Intent(context, (Class<?>) WeightGoalActivity.class);
                    intent.putExtra("from", 1);
                    qry.e(d.this, 1);
                    gnm.aPB_(context, intent);
                    qyw.e((ResponseCallback<Integer>) responseCallback, 2);
                    atomicBoolean.set(false);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).dIx_(dVar.h(), new View.OnClickListener() { // from class: qry.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (d.this.b() == 6) {
                    Intent intent = new Intent(context, (Class<?>) WeightGoalActivity.class);
                    intent.putExtra("IS_SELECT_KEEP_WEIGHT", true);
                    qry.e(d.this, 2);
                    gnm.aPB_(BaseApplication.getActivity(), intent);
                    qyw.e((ResponseCallback<Integer>) responseCallback, 2);
                    atomicBoolean.set(false);
                } else if (d.this.b() == 7) {
                    qry.e(d.this, 4);
                } else {
                    LogUtil.c("WeightDialogUtil", "other dialog");
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).dIy_(dVar.j(), new View.OnClickListener() { // from class: qse
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                qry.dHW_(qry.d.this, view);
            }
        }).c();
        if (c == null) {
            ReleaseLogUtil.a("WeightDialogUtil", "showDialog dialog is null");
            qyw.b(responseCallback);
        } else {
            c.show();
            c.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: qsf
                @Override // android.content.DialogInterface.OnDismissListener
                public final void onDismiss(DialogInterface dialogInterface) {
                    qry.dHX_(atomicBoolean, responseCallback, dialogInterface);
                }
            });
            qyw.e(responseCallback, 0);
        }
    }

    static /* synthetic */ void dHW_(d dVar, View view) {
        e(dVar, 4);
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void dHX_(AtomicBoolean atomicBoolean, ResponseCallback responseCallback, DialogInterface dialogInterface) {
        if (atomicBoolean.get()) {
            qyw.e((ResponseCallback<Integer>) responseCallback, 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(d dVar, int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        if (dVar.l() == 2) {
            hashMap.put("task", 0);
        } else {
            hashMap.put("task", 1);
        }
        int b = dVar.b();
        if (b == 5) {
            hashMap.put("type", 3);
        } else if (b == 6) {
            hashMap.put("type", 1);
        } else if (b == 7) {
            hashMap.put("type", -1);
        } else if (b == 8) {
            hashMap.put("type", 2);
        } else {
            LogUtil.h("WeightDialogUtil", "initTargetDialogBean is default");
        }
        hashMap.put("event", Integer.valueOf(i));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.WEIGHT_TARGET_MOMENTS_CLICK_2160123.value(), hashMap, 0);
    }

    private static void h(Context context, d dVar) {
        int b = dVar.b();
        if (b == 1) {
            f(context, dVar);
            return;
        }
        if (b == 2) {
            j(context, dVar);
            return;
        }
        if (b == 3) {
            g(context, dVar);
            return;
        }
        if (b == 4) {
            i(context, dVar);
        } else if (b == 5) {
            d(context, dVar);
        } else {
            o(context, dVar);
        }
    }

    private static void o(Context context, d dVar) {
        int b = dVar.b();
        if (b == 6) {
            a(context, dVar);
            return;
        }
        if (b == 7) {
            e(context, dVar);
        } else if (b == 8) {
            c(context, dVar);
        } else {
            LogUtil.h("WeightDialogUtil", "initTargetDialogBean is default");
        }
    }

    private static void f(Context context, d dVar) {
        dVar.b(R$string.IDS_hwh_home_target_begins);
        dVar.c(R.drawable._2131431740_res_0x7f0b113c);
        dVar.c(b(context, dVar, ""));
        dVar.b(context.getString(R$string.IDS_hwh_home_ok));
        dVar.a(1);
        dVar.a(true);
    }

    private static void j(Context context, d dVar) {
        String string;
        dVar.b(R$string.IDS_hwh_home_go_beyond_the_goal);
        dVar.c(R.drawable._2131431743_res_0x7f0b113f);
        if (dVar.l() == 2) {
            string = context.getString(R$string.IDS_hwh_home_go_beyond_the_goal_content);
        } else {
            string = context.getString(R$string.IDS_hwh_home_go_beyond_the_goal_increase_content);
        }
        dVar.c(b(context, dVar, string));
        dVar.b(context.getString(R$string.IDS_hwh_home_ok));
        dVar.a(1);
        dVar.a(true);
    }

    private static void g(Context context, d dVar) {
        String string;
        dVar.b(R$string.IDS_hwh_home_backward_target);
        dVar.c(R.drawable._2131431739_res_0x7f0b113b);
        if (dVar.l() == 2) {
            string = context.getString(R$string.IDS_hwh_home_backward_target_content);
        } else {
            string = context.getString(R$string.IDS_hwh_home_backward_target_increase_content);
        }
        dVar.c(b(context, dVar, string));
        dVar.b(context.getString(R$string.IDS_hwh_home_ok));
        dVar.a(1);
        dVar.a(true);
    }

    private static void i(Context context, d dVar) {
        String string;
        dVar.b(R$string.IDS_hwh_home_stable_progress);
        dVar.c(R.drawable._2131431742_res_0x7f0b113e);
        if (dVar.l() == 2) {
            string = context.getString(R$string.IDS_hwh_home_stable_progress_content);
        } else {
            string = context.getString(R$string.IDS_hwh_home_stable_progress_increase_content);
        }
        dVar.c(b(context, dVar, string));
        dVar.b(context.getString(R$string.IDS_hwh_home_ok));
        dVar.a(1);
        dVar.a(true);
    }

    private static void d(Context context, d dVar) {
        String string;
        dVar.b(R$string.IDS_hwh_home_keep_pumping);
        dVar.c(R.drawable._2131430891_res_0x7f0b0deb);
        if (dVar.l() == 2) {
            string = context.getString(R$string.IDS_hwh_home_keep_pumping_content);
        } else {
            string = context.getString(R$string.IDS_hwh_home_keep_pumping_increase_content);
        }
        dVar.c(b(context, dVar, string));
        dVar.b(context.getString(R$string.IDS_hwh_home_keep_up_good_work));
        dVar.a(1);
        dVar.a(false);
    }

    private static void c(Context context, d dVar) {
        String string;
        dVar.b(R$string.IDS_hwh_home_winner_is_in_hand);
        dVar.c(R.drawable._2131430889_res_0x7f0b0de9);
        if (dVar.l() == 2) {
            string = context.getString(R$string.IDS_hwh_home_keep_pumping_content);
        } else {
            string = context.getString(R$string.IDS_hwh_home_keep_pumping_increase_content);
        }
        dVar.c(b(context, dVar, string));
        dVar.b(context.getString(R$string.IDS_hwh_home_keep_up_good_work));
        dVar.a(false);
        dVar.a(1);
    }

    private static void a(Context context, d dVar) {
        String string;
        if (dVar.l() == 2) {
            string = context.getString(R$string.IDS_hwh_home_weight_reduction_goal_achievement_content);
            dVar.b(R$string.IDS_hwh_home_weight_reduction_goal_achievement);
        } else {
            dVar.b(R$string.IDS_hwh_home_weight_gain_goal_achievement);
            string = context.getString(R$string.IDS_hwh_home_weight_gain_goal_achievement_content);
        }
        dVar.c(R.drawable._2131430890_res_0x7f0b0dea);
        dVar.c(b(context, dVar, string));
        dVar.b(context.getString(R$string.IDS_hwh_home_challenge_new_goals));
        dVar.d(context.getString(R$string.IDS_health_hold_weight));
        dVar.a(context.getString(R$string.IDS_wl_food_cancel));
        dVar.a(false);
        dVar.a(3);
    }

    private static void e(Context context, d dVar) {
        String string;
        dVar.b(R$string.IDS_hwh_home_goal_was_not_achieved);
        dVar.c(R.drawable._2131430892_res_0x7f0b0dec);
        if (dVar.l() == 2) {
            string = context.getString(R$string.IDS_hwh_home_goal_was_not_achieved_content);
        } else {
            string = context.getString(R$string.IDS_hwh_home_goal_was_not_achieved_increase_content);
        }
        dVar.c(b(context, dVar, string));
        dVar.b(context.getString(R$string.IDS_hwh_home_set_new_weight_goal));
        dVar.d(context.getString(R$string.IDS_wl_food_cancel));
        dVar.a(false);
        dVar.a(2);
    }

    private static WeightViewDialog.Builder b(Context context, d dVar) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.weight_target_view, (ViewGroup) null);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.motivating_iv);
        ImageView imageView2 = (ImageView) inflate.findViewById(R.id.target_iv);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.title_text_tv);
        HealthTextView healthTextView2 = (HealthTextView) inflate.findViewById(R.id.content_text_tv);
        healthTextView.setText(dVar.f());
        healthTextView2.setText(dVar.e());
        if (dVar.n()) {
            imageView2.setVisibility(0);
            imageView.setVisibility(8);
            imageView2.setBackgroundResource(dVar.c());
        } else {
            imageView2.setVisibility(8);
            imageView.setVisibility(0);
            imageView.setBackgroundResource(dVar.c());
        }
        WeightViewDialog.Builder builder = new WeightViewDialog.Builder(context);
        builder.b(true).a(dVar.i()).dIA_(inflate);
        return builder;
    }

    private static String b(d dVar) {
        return qsj.e(UnitUtil.a(dVar.d()), 1);
    }

    private static String e(d dVar) {
        if (dVar.a() == -1.0f) {
            LogUtil.a("WeightDialogUtil", "getBodyPercentage is -1");
            return "";
        }
        return UnitUtil.e(Math.max(dVar.a(), 0.0f), 2, 0);
    }

    public static String b(Context context, d dVar, String str) {
        if (context == null || dVar == null) {
            LogUtil.h("WeightDialogUtil", "getMotivatingConstr context or dialogBean is null");
            return "";
        }
        String b = b(dVar);
        String e = e(dVar);
        if (LanguageUtil.bp(context) || LanguageUtil.y(context)) {
            e = "\u200f" + e;
        }
        switch (dVar.b()) {
            case 1:
                return context.getString(R$string.IDS_hwh_home_before_starting_target_towing);
            case 2:
            case 3:
            case 4:
                return String.format(Locale.ENGLISH, str, b, e);
            case 5:
            case 7:
            case 8:
                return String.format(Locale.ENGLISH, str, b, e);
            case 6:
                return String.format(Locale.ENGLISH, str, b);
            default:
                LogUtil.h("WeightDialogUtil", "getDialogContent is default");
                return "";
        }
    }

    /* loaded from: classes9.dex */
    public static class d {
        private String b;
        private boolean c;
        private int e;
        private int g;
        private int j;
        private int l = 1;
        private int m = 2;
        private float d = -1.0f;

        /* renamed from: a, reason: collision with root package name */
        private float f16562a = -1.0f;
        private String i = "";
        private String f = "";
        private String h = "";

        public int b() {
            return this.e;
        }

        public void e(int i) {
            this.e = i;
        }

        public int c() {
            return this.j;
        }

        public void c(int i) {
            this.j = i;
        }

        public int f() {
            return this.g;
        }

        public void b(int i) {
            this.g = i;
        }

        public String e() {
            return this.b;
        }

        public void c(String str) {
            this.b = str;
        }

        public int i() {
            return this.l;
        }

        public void a(int i) {
            this.l = i;
        }

        public boolean n() {
            return this.c;
        }

        public void a(boolean z) {
            this.c = z;
        }

        public int l() {
            return this.m;
        }

        public void d(int i) {
            this.m = i;
        }

        public float d() {
            return this.d;
        }

        public void d(float f) {
            this.d = f;
        }

        public float a() {
            return this.f16562a;
        }

        public void b(float f) {
            this.f16562a = f;
        }

        public String g() {
            return this.i;
        }

        public void b(String str) {
            this.i = str;
        }

        public String h() {
            return this.f;
        }

        public void d(String str) {
            this.f = str;
        }

        public String j() {
            return this.h;
        }

        public void a(String str) {
            this.h = str;
        }
    }

    public static int a(long j, long j2) {
        double currentTimeMillis = (System.currentTimeMillis() - j) / (j2 - j);
        if (currentTimeMillis >= 1.0d) {
            return 4;
        }
        if (currentTimeMillis >= 0.75d) {
            return 3;
        }
        if (currentTimeMillis >= 0.5d) {
            return 2;
        }
        return currentTimeMillis >= 0.25d ? 1 : 0;
    }

    public static void c(WeightTargetDifferences weightTargetDifferences) {
        if (weightTargetDifferences == null) {
            ReleaseLogUtil.a("WeightDialogUtil", "initTargetId differences is null");
            return;
        }
        cfi mainUser = MultiUsersManager.INSTANCE.getMainUser();
        if (mainUser == null) {
            ReleaseLogUtil.a("WeightDialogUtil", "initTargetId user is null differences ", weightTargetDifferences);
            return;
        }
        String a2 = rag.a("weight_target_Id");
        if (TextUtils.isEmpty(a2)) {
            ReleaseLogUtil.b("WeightDialogUtil", "initTargetId targetId ", a2);
            d(weightTargetDifferences.b() + mainUser.i());
        }
    }

    public static void d(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("WeightDialogUtil", "setNewTarget targetId is null");
            return;
        }
        String a2 = rag.a("weight_target_Id");
        if (TextUtils.isEmpty(a2)) {
            rag.c("weight_target_Id", str);
            return;
        }
        rag.d(new String[]{a2 + 1, a2 + 2, a2 + 3, a2 + 4});
        rag.c("weight_target_Id", str);
    }

    public static boolean e(int i, String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("WeightDialogUtil", "checkingProgress targetId is null");
            return false;
        }
        return rag.c(str + i);
    }

    public static void a(final Context context, boolean z) {
        String string;
        if (context == null) {
            return;
        }
        boolean h = UnitUtil.h();
        if (h) {
            string = String.format(context.getString(R$string.IDS_hwh_home_weight_bmi_value_set_two_imp), 703);
        } else {
            string = context.getString(R$string.IDS_hwh_home_weight_bmi_value_set_two);
        }
        if (!z) {
            c(context, string);
            return;
        }
        View inflate = View.inflate(context, R.layout.activity_weight_goal_dialog, null);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.hw_show_calibration_height);
        HealthTextView healthTextView2 = (HealthTextView) inflate.findViewById(R.id.bmi_des);
        if (h) {
            healthTextView2.setText(String.format(context.getResources().getString(R$string.IDS_hwh_home_weight_bmi_value_two_imp), 703));
        } else {
            healthTextView2.setText(context.getResources().getString(R$string.IDS_hwh_home_weight_bmi_value_two));
        }
        final NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(context).czC_(R$string.IDS_hw_pressure_known, new View.OnClickListener() { // from class: qsg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        RelativeLayout relativeLayout = (RelativeLayout) e.findViewById(R.id.dialog_rlyt_content);
        relativeLayout.removeAllViews();
        relativeLayout.addView(inflate);
        healthTextView.setOnClickListener(new View.OnClickListener() { // from class: qsh
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                qry.dHT_(context, e, view);
            }
        });
        e.setCancelable(false);
        e.show();
    }

    static /* synthetic */ void dHT_(Context context, NoTitleCustomAlertDialog noTitleCustomAlertDialog, View view) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("bundleKeyJumpUserInfoActivity", true);
        AppRouter.b("/HWUserProfileMgr/UserInfoActivity").zF_(bundle).c(context);
        noTitleCustomAlertDialog.dismiss();
        ViewClickInstrumentation.clickOnView(view);
    }

    public static void b(Context context) {
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(context);
        builder.e(context.getString(R$string.IDS_hwh_home_weight_target_cannot_same_the_weight)).czE_(context.getString(R$string.IDS_common_notification_know_tips), new View.OnClickListener() { // from class: qrw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        NoTitleCustomAlertDialog e = builder.e();
        e.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: qsd
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                qry.dHV_(dialogInterface);
            }
        });
        e.show();
    }

    private static void c(final Context context, String str) {
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(context);
        builder.e(str).czA_(context.getString(R$string.IDS_hw_common_ui_dialog_cancel), new View.OnClickListener() { // from class: qsc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czE_(context.getString(R$string.IDS_fitness_weight_notice_go_to_set), new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.util.WeightDialogUtil$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                qry.dHZ_(context, view);
            }
        });
        NoTitleCustomAlertDialog e = builder.e();
        e.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: qsb
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                qry.dIa_(dialogInterface);
            }
        });
        e.show();
    }

    public static /* synthetic */ void dHZ_(Context context, View view) {
        AppRouter.b("/HWUserProfileMgr/UserInfoActivity").c(context);
        sdk.c().a(false);
        ViewClickInstrumentation.clickOnView(view);
    }
}
