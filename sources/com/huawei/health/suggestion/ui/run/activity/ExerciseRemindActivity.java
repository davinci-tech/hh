package com.huawei.health.suggestion.ui.run.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.servicesui.R$string;
import com.huawei.health.suggestion.ui.BaseActivity;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.commonui.timepicker.HealthTimePickerDialog;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.ash;
import defpackage.fyr;
import defpackage.gic;
import defpackage.ixx;
import defpackage.moe;
import defpackage.nsj;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import java.util.Date;
import java.util.HashMap;

/* loaded from: classes4.dex */
public class ExerciseRemindActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private CustomTitleBar f3318a;
    private HealthTimePickerDialog b;
    private int c;
    private int d;
    private HealthTextView e;
    private HealthSwitchButton f;
    private HealthTextView g;
    private int h = -1;
    private ImageView i;

    @Override // com.huawei.health.suggestion.ui.BaseActivity
    public void initLayout() {
        setContentView(R.layout.sug_run_activity_exercise_remind);
        h();
    }

    @Override // com.huawei.health.suggestion.ui.BaseActivity
    public void initViewController() {
        g();
    }

    @Override // com.huawei.health.suggestion.ui.BaseActivity
    public void initData() {
        int a2;
        Intent intent = getIntent();
        if (intent != null) {
            this.h = intent.getIntExtra("planType", -1);
        }
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.b("ExerciseRemindActivity", "initData, getRemindTime : planApi is null.");
            return;
        }
        planApi.setPlanType(this.h);
        int remindTime = planApi.getRemindTime();
        if (this.h == 0) {
            a2 = gic.a(ash.b("runLastRemindTime"), 1080);
        } else {
            a2 = gic.a(ash.b("intLastRemindTime"), 1080);
        }
        this.d = moe.a(remindTime > -1 ? remindTime : a2);
        if (remindTime <= -1) {
            remindTime = a2;
        }
        this.c = moe.b(remindTime);
    }

    private void h() {
        this.f = (HealthSwitchButton) findViewById(R.id.sug_sc_enabled);
        this.e = (HealthTextView) findViewById(R.id.sug_txt_remind_time);
        this.g = (HealthTextView) findViewById(R.id.sug_txt_title_exercise_remind);
        this.f3318a = (CustomTitleBar) findViewById(R.id.sug_titleBar);
        this.i = (ImageView) findViewById(R.id.sug_iv_arrow);
        if (LanguageUtil.bc(this)) {
            this.i.setImageResource(R$drawable.common_ui_arrow_left);
        } else {
            this.i.setImageResource(R$drawable.common_ui_arrow_right);
        }
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi != null) {
            planApi.setPlanType(this.h);
            if (planApi.isOpenRemind()) {
                this.f.setChecked(true);
                e(true);
                this.e.setText(c());
            }
        }
        this.f.setChecked(false);
        e(false);
        this.e.setText(c());
    }

    private void i() {
        HealthTimePickerDialog healthTimePickerDialog = this.b;
        if (healthTimePickerDialog != null && healthTimePickerDialog.isShowing()) {
            this.b.dismiss();
        }
        HealthTimePickerDialog healthTimePickerDialog2 = new HealthTimePickerDialog(this, new HealthTimePickerDialog.TimeSelectedListener() { // from class: com.huawei.health.suggestion.ui.run.activity.ExerciseRemindActivity.3
            @Override // com.huawei.ui.commonui.timepicker.HealthTimePickerDialog.TimeSelectedListener
            public void onTimeSelected(int i, int i2) {
                ExerciseRemindActivity.this.d = i;
                ExerciseRemindActivity.this.c = i2;
                ExerciseRemindActivity.this.e();
            }
        });
        this.b = healthTimePickerDialog2;
        healthTimePickerDialog2.e(0, 0, 0, this.d, this.c);
        this.b.b(getString(R$string.sug_exercise_remind));
    }

    private void g() {
        this.f.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.health.suggestion.ui.run.activity.ExerciseRemindActivity.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ExerciseRemindActivity.this.e(z);
                ViewClickInstrumentation.clickOnView(compoundButton);
            }
        });
        this.f3318a.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.run.activity.ExerciseRemindActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ExerciseRemindActivity.this.a();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.f3318a.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.run.activity.ExerciseRemindActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ExerciseRemindActivity.this.d();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.e.setOnClickListener(this);
        this.i.setOnClickListener(this);
    }

    private String c() {
        return nsj.c(getApplicationContext(), new Date(0, 0, 0, this.d, this.c, 0).getTime(), 1);
    }

    private int b() {
        return (this.d * 60) + this.c;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.e || view == this.i) {
            f();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.b("ExerciseRemindActivity", "conformSetExerciseRemind, updateRemindTime : planApi is null.");
            return;
        }
        planApi.setPlanType(this.h);
        int b = b();
        if (this.f.isChecked()) {
            planApi.updateRemindTime(true, b);
        } else {
            planApi.updateRemindTime(false, -1);
        }
        if (this.h == 0) {
            ash.a("runLastRemindTime", String.valueOf(b));
        } else {
            ash.a("intLastRemindTime", String.valueOf(b));
        }
        planApi.b(new UiCallback<IntPlan>() { // from class: com.huawei.health.suggestion.ui.run.activity.ExerciseRemindActivity.4
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                LogUtil.b("ExerciseRemindActivity", "No current plan now.");
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onSuccess(IntPlan intPlan) {
                if (intPlan != null) {
                    LogUtil.h("ExerciseRemindActivity", "getCurrentIntPlan data null.");
                    HashMap hashMap = new HashMap();
                    hashMap.put("click", 1);
                    hashMap.put("type", Integer.valueOf(intPlan.getPlanType().getType()));
                    hashMap.put("plan_name", intPlan.getMetaInfo().getName());
                    ixx.d().d(BaseApplication.getContext(), AnalyticsValue.INT_PLAN_1120046.value(), hashMap, 0);
                }
            }
        });
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        this.e.setText(c());
        this.b.dismiss();
    }

    private void f() {
        i();
        this.b.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(boolean z) {
        this.e.setEnabled(z);
        this.i.setEnabled(z);
        if (z) {
            this.g.setAlpha(1.0f);
            this.e.setAlpha(1.0f);
            this.i.setAlpha(1.0f);
        } else {
            this.g.setAlpha(0.2f);
            this.e.setAlpha(0.4f);
            this.i.setAlpha(0.4f);
            fyr.d();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.e.setText(c());
        i();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
