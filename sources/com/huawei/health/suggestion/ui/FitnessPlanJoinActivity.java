package com.huawei.health.suggestion.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.R;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.suggestion.ui.FitnessPlanJoinActivity;
import com.huawei.health.suggestion.util.JumpUtil;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.pluginfitnessadvice.FitnessPackageInfo;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import defpackage.arx;
import defpackage.ash;
import defpackage.ffy;
import defpackage.fib;
import defpackage.moe;
import defpackage.nrh;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.util.Map;

/* loaded from: classes4.dex */
public class FitnessPlanJoinActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private HealthButton f3050a = null;
    private NoTitleCustomAlertDialog c = null;
    private boolean f = false;
    private Plan b = null;
    private String j = null;
    private ImageView e = null;
    private HealthTextView d = null;
    private HealthTextView l = null;
    private HealthTextView i = null;
    private HealthTextView h = null;
    private FitnessPackageInfo g = null;

    @Override // com.huawei.health.suggestion.ui.BaseActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        cancelMarginAdaptation();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
        b();
    }

    private void b() {
        if (nsn.l()) {
            nsn.cLF_(getBaseContext(), true, true, (LinearLayout) findViewById(R.id.back_button_and_title_name), (HealthTextView) findViewById(R.id.sug_description_textView));
        }
    }

    @Override // com.huawei.health.suggestion.ui.BaseActivity
    public void initData() {
        Intent intent = getIntent();
        if (intent != null && intent.getStringExtra("PLAN_TEMP_ID_KEY") != null) {
            this.j = intent.getStringExtra("PLAN_TEMP_ID_KEY");
        }
        if (TextUtils.isEmpty(this.j)) {
            LogUtil.h("Suggestion_FitnessPlanJoinActivity", "TextUtils.isEmpty(mPlanTempId)");
            finish();
        }
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.h("Suggestion_FitnessPlanJoinActivity", "initData : planApi is null.");
            return;
        }
        FitnessPackageInfo fitnessPkgInfoByTempId = planApi.getFitnessPkgInfoByTempId(this.j);
        this.g = fitnessPkgInfoByTempId;
        if (fitnessPkgInfoByTempId == null) {
            LogUtil.h("Suggestion_FitnessPlanJoinActivity", "null is mFitnessPackageInfo");
            finish();
        }
    }

    @Override // com.huawei.health.suggestion.ui.BaseActivity
    public void initLayout() {
        d();
        setContentView(R.layout.sug_activity_fitness_plan_join);
        e();
        c();
    }

    private void e() {
        if (this.g == null) {
            LogUtil.h("Suggestion_FitnessPlanJoinActivity", "null is mFitnessPackageInfo");
            return;
        }
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.h("Suggestion_FitnessPlanJoinActivity", "bindView : planApi is null.");
            return;
        }
        HealthButton healthButton = (HealthButton) findViewById(R.id.sug_create_fitness_plan);
        this.f3050a = healthButton;
        healthButton.setOnClickListener(this);
        ImageView imageView = (ImageView) findViewById(R.id.sug_fitness_back);
        this.e = imageView;
        imageView.setOnClickListener(this);
        this.d = (HealthTextView) findViewById(R.id.sug_day_textView);
        this.l = (HealthTextView) findViewById(R.id.sug_time_textView);
        this.i = (HealthTextView) findViewById(R.id.sug_fit_plan_title);
        if (nsn.r()) {
            ((HealthScrollView) findViewById(R.id.scroll_view_large)).setVisibility(0);
            findViewById(R.id.sug_description_textView).setVisibility(8);
            findViewById(R.id.sug_description_layout).getLayoutParams().height = 800;
            this.h = (HealthTextView) findViewById(R.id.sug_description_textView_large_mode);
        } else {
            this.h = (HealthTextView) findViewById(R.id.sug_description_textView);
        }
        this.i.setText(this.g.acquireName());
        this.h.setText(this.g.acquireDescription());
        int acquireTotalCourse = this.g.acquireTotalCourse();
        int round = Math.round(planApi.getFitnessPlanPackageTotalCalorie(this.g));
        SpannableString awT_ = ffy.awT_(this, "\\d", ffy.b(R.plurals._2130903470_res_0x7f0301ae, acquireTotalCourse, UnitUtil.e(acquireTotalCourse, 1, 0)), R.style.sug_text_result_k, R.style.sug_text_result_m);
        SpannableString awT_2 = ffy.awT_(this, "\\d", ffy.b(R.plurals._2130903474_res_0x7f0301b2, round, moe.i(moe.b(round))), R.style.sug_text_result_k, R.style.sug_text_result_m);
        this.d.setText(awT_);
        this.l.setText(awT_2);
    }

    private void c() {
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this);
        builder.e(R.string._2130848610_res_0x7f022b62);
        builder.czC_(R.string._2130848480_res_0x7f022ae0, new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.FitnessPlanJoinActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("Suggestion_FitnessPlanJoinActivity", "onClick setPositiveButton");
                JumpUtil.c(FitnessPlanJoinActivity.this);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.czz_(R.string._2130848409_res_0x7f022a99, new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.FitnessPlanJoinActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("Suggestion_FitnessPlanJoinActivity", "onClick setNegativeButton");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.c = builder.e();
    }

    @Override // com.huawei.health.suggestion.ui.BaseActivity
    public void initViewController() {
        LogUtil.c("Suggestion_FitnessPlanJoinActivity", "initViewController");
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.f3050a) {
            LoginInit.getInstance(BaseApplication.getContext()).browsingToLogin(new IBaseResponseCallback() { // from class: fjq
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    FitnessPlanJoinActivity.this.a(i, obj);
                }
            }, "");
        } else if (view == this.e) {
            super.onBackPressed();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void a(int i, Object obj) {
        if (i == 0) {
            a();
        } else {
            LogUtil.a("Suggestion_FitnessPlanJoinActivity", "errorCode is not success", Integer.valueOf(i));
        }
    }

    private void a() {
        if (!CommonUtil.aa(this)) {
            nrh.d(this, getString(R.string._2130839508_res_0x7f0207d4));
            return;
        }
        this.f3050a.setClickable(false);
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.h("Suggestion_FitnessPlanJoinActivity", "clickCreatePlanButton, getCurrentPlan : planApi is null.");
            return;
        }
        c(planApi);
        ash.a("planStatistics_need_refresh", "true");
        fib.e().d(4, new UiCallback<Map>() { // from class: com.huawei.health.suggestion.ui.FitnessPlanJoinActivity.5
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                LogUtil.h("Suggestion_FitnessPlanJoinActivity", "queryPlanStatistics errorCode = ", Integer.valueOf(i));
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onSuccess(Map map) {
                LogUtil.a("Suggestion_FitnessPlanJoinActivity", "queryPlanStatistics Success");
            }
        });
    }

    /* renamed from: com.huawei.health.suggestion.ui.FitnessPlanJoinActivity$2, reason: invalid class name */
    class AnonymousClass2 extends UiCallback<IntPlan> {
        final /* synthetic */ PlanApi c;

        AnonymousClass2(PlanApi planApi) {
            this.c = planApi;
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            HandlerExecutor.e(new Runnable() { // from class: com.huawei.health.suggestion.ui.FitnessPlanJoinActivity.2.4
                @Override // java.lang.Runnable
                public void run() {
                    FitnessPlanJoinActivity.this.f3050a.setClickable(true);
                    Toast.makeText(arx.b(), arx.b().getString(R.string._2130839508_res_0x7f0207d4), 0).show();
                }
            });
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onSuccess(IntPlan intPlan) {
            if (intPlan == null) {
                this.c.createFitnessPkg(FitnessPlanJoinActivity.this.j, new UiCallback<IntPlan>() { // from class: com.huawei.health.suggestion.ui.FitnessPlanJoinActivity.2.2
                    @Override // com.huawei.basefitnessadvice.callback.UiCallback
                    public void onFailure(int i, String str) {
                        FitnessPlanJoinActivity.this.c(i, str);
                    }

                    @Override // com.huawei.basefitnessadvice.callback.UiCallback
                    /* renamed from: d, reason: merged with bridge method [inline-methods] */
                    public void onSuccess(IntPlan intPlan2) {
                        HandlerExecutor.e(new Runnable() { // from class: com.huawei.health.suggestion.ui.FitnessPlanJoinActivity.2.2.4
                            @Override // java.lang.Runnable
                            public void run() {
                                JumpUtil.c(FitnessPlanJoinActivity.this);
                                FitnessPlanJoinActivity.this.finish();
                            }
                        });
                    }
                });
            } else {
                HandlerExecutor.e(new Runnable() { // from class: com.huawei.health.suggestion.ui.FitnessPlanJoinActivity.2.5
                    @Override // java.lang.Runnable
                    public void run() {
                        FitnessPlanJoinActivity.this.f3050a.setClickable(true);
                        FitnessPlanJoinActivity.this.c.show();
                    }
                });
            }
        }
    }

    private void c(PlanApi planApi) {
        planApi.b(new AnonymousClass2(planApi));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final int i, final String str) {
        HandlerExecutor.e(new Runnable() { // from class: com.huawei.health.suggestion.ui.FitnessPlanJoinActivity.1
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.h("Suggestion_FitnessPlanJoinActivity", "createFitnessPkg errorCode = ", Integer.valueOf(i), " errorInfo = ", str);
                Toast.makeText(arx.b(), arx.b().getString(R.string._2130839508_res_0x7f0207d4), 0).show();
                FitnessPlanJoinActivity.this.f3050a.setClickable(true);
            }
        });
    }

    protected void d() {
        getWindow().addFlags(Integer.MIN_VALUE);
        getWindow().setStatusBarColor(0);
        getWindow().setFlags(AppRouterExtras.COLDSTART, AppRouterExtras.COLDSTART);
        getWindow().setFlags(AMapEngineUtils.HALF_MAX_P20_WIDTH, AMapEngineUtils.HALF_MAX_P20_WIDTH);
    }
}
