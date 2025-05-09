package com.huawei.health.suggestion.ui.plan.activity;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.fragment.app.FragmentTransaction;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.R;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.suggestion.ui.BaseStateActivity;
import com.huawei.health.suggestion.ui.callback.UiPagingCallback;
import com.huawei.health.suggestion.ui.plan.fragment.AiPlanFragment;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.gdr;
import defpackage.ggf;
import defpackage.nrz;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import java.lang.ref.WeakReference;
import java.util.Calendar;

/* loaded from: classes.dex */
public class AiPlanActivity extends BaseStateActivity {
    private static final Float c = Float.valueOf(35.0f);
    private static final Float e = Float.valueOf(28.0f);

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f3236a;
    private ImageView b;
    private Context d;
    private Handler g = new b(Looper.getMainLooper(), this);
    private Plan h;
    private HealthTextView i;

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().addFlags(Integer.MIN_VALUE);
        getWindow().setStatusBarColor(0);
        getWindow().setFlags(AppRouterExtras.COLDSTART, AppRouterExtras.COLDSTART);
        clearBackgroundDrawable();
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initLayout() {
        setContentView(R.layout.sug_activity_ai_plan);
        if (getIntent() == null) {
            LogUtil.b("Suggestion_AiPlanActivity", "intent is null");
            return;
        }
        this.d = this;
        this.b = (ImageView) findViewById(R.id.plan_ai_background);
        this.i = (HealthTextView) findViewById(R.id.hi_coach);
        this.f3236a = (HealthTextView) findViewById(R.id.hi_coach_advice);
        b(this.i, c.floatValue());
        b(this.f3236a, e.floatValue());
        if (nsn.s()) {
            nsn.b(this.f3236a);
        }
        this.i.setText(ggf.e());
        boolean bc = LanguageUtil.bc(this.d);
        BitmapDrawable cKn_ = nrz.cKn_(this.d, R.drawable._2131427534_res_0x7f0b00ce);
        if (bc) {
            this.b.setImageDrawable(cKn_);
        } else {
            this.b.setImageDrawable(this.d.getResources().getDrawable(R.drawable._2131427534_res_0x7f0b00ce));
        }
        cancelAdaptRingRegion();
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.sug_plan_content);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.topMargin = -((int) this.d.getResources().getDimension(R.dimen._2131362359_res_0x7f0a0237));
        layoutParams.addRule(3, R.id.plan_ai_layout);
        linearLayout.setLayoutParams(layoutParams);
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initViewController() {
        LogUtil.a("Suggestion_AiPlanActivity", "initViewController()");
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initData() {
        d();
        LogUtil.a("Suggestion_AiPlanActivity", "initData()");
        new AiPlanFragment();
        AiPlanFragment c2 = AiPlanFragment.c(0);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.sug_plan_content, c2);
        beginTransaction.commitAllowingStateLoss();
    }

    private void d() {
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.b("Suggestion_AiPlanActivity", "getMyFitnessPlansInfo, getCurrentPlan : planApi is null.");
        } else {
            planApi.b(new c(this));
        }
    }

    /* loaded from: classes4.dex */
    static class c extends UiPagingCallback<IntPlan> {
        WeakReference<AiPlanActivity> b;

        c(AiPlanActivity aiPlanActivity) {
            this.b = new WeakReference<>(aiPlanActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.health.suggestion.ui.callback.UiPagingCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onSuccessFirst(IntPlan intPlan) {
            AiPlanActivity aiPlanActivity = this.b.get();
            if (aiPlanActivity == null) {
                return;
            }
            if (intPlan != null && IntPlan.PlanType.AI_RUN_PLAN.equals(intPlan.getPlanType())) {
                aiPlanActivity.h = intPlan.getCompatiblePlan();
            }
            Handler handler = aiPlanActivity.g;
            if (handler != null) {
                handler.sendMessage(handler.obtainMessage(1));
            }
        }

        @Override // com.huawei.health.suggestion.ui.callback.UiPagingCallback
        public void onFailureFirst(int i, String str) {
            Handler handler;
            AiPlanActivity aiPlanActivity = this.b.get();
            if (aiPlanActivity == null || (handler = aiPlanActivity.g) == null) {
                return;
            }
            handler.sendMessage(handler.obtainMessage(1));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        String string;
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        Plan plan = this.h;
        if (plan == null) {
            LogUtil.h("Suggestion_AiPlanActivity", "setCoachTipText is mRunPlan = null");
            this.f3236a.setText(ggf.d());
            return;
        }
        int e2 = gdr.e(plan, timeInMillis);
        if (e2 == 0) {
            string = this.d.getResources().getString(R.string._2130844829_res_0x7f021c9d);
        } else if (e2 == 1) {
            string = this.d.getResources().getString(R.string._2130844830_res_0x7f021c9e);
        } else if (e2 == 2) {
            string = this.d.getResources().getString(R.string._2130844831_res_0x7f021c9f);
        } else if (e2 == 3) {
            string = this.d.getResources().getString(R.string._2130844832_res_0x7f021ca0);
        } else if (e2 == 4) {
            string = this.d.getResources().getString(R.string._2130844833_res_0x7f021ca1);
        } else if (e2 == 5) {
            string = this.d.getResources().getString(R.string._2130844834_res_0x7f021ca2);
        } else {
            string = this.d.getResources().getString(R.string._2130844828_res_0x7f021c9c);
        }
        LogUtil.a("Suggestion_AiPlanActivity", "setCoachTipText tipString is", string);
        this.f3236a.setText(string);
    }

    /* loaded from: classes4.dex */
    static class b extends BaseHandler<AiPlanActivity> {
        b(Looper looper, AiPlanActivity aiPlanActivity) {
            super(looper, aiPlanActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: aGs_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(AiPlanActivity aiPlanActivity, Message message) {
            if (message.what == 1) {
                aiPlanActivity.b();
            }
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        HealthTextView healthTextView = this.i;
        if (healthTextView != null) {
            healthTextView.setText(ggf.e());
        }
        if (this.f3236a != null) {
            b();
        }
    }

    private void b(HealthTextView healthTextView, float f) {
        if (nsn.s()) {
            healthTextView.setTextSize(1, f);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
