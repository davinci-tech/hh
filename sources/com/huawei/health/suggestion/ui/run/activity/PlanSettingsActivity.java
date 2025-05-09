package com.huawei.health.suggestion.ui.run.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.suggestion.ui.BaseActivity;
import com.huawei.health.suggestion.ui.run.activity.PlanSettingsActivity;
import com.huawei.health.suggestion.util.JumpUtil;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import defpackage.ash;
import defpackage.fyo;
import defpackage.jeg;
import defpackage.nrt;
import defpackage.nrz;
import defpackage.nsn;
import health.compact.a.EnvironmentInfo;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;

/* loaded from: classes4.dex */
public class PlanSettingsActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private HealthDivider f3319a;
    private IntPlan b;
    private LinearLayout c;
    private boolean d;
    private Context e;
    private LinearLayout f;
    private HealthDivider h;
    private LinearLayout i;
    private HealthSwitchButton j;

    @Override // com.huawei.health.suggestion.ui.BaseActivity
    public void initData() {
    }

    @Override // com.huawei.health.suggestion.ui.BaseActivity
    public void initLayout() {
        setContentView(R.layout.sug_activity_plan_settings);
        e();
        this.e = this;
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.b("PlanSettingsActivity", "initData, getRemindTime : planApi is null.");
        } else {
            planApi.b(new AnonymousClass3());
        }
    }

    /* renamed from: com.huawei.health.suggestion.ui.run.activity.PlanSettingsActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends UiCallback<IntPlan> {
        AnonymousClass3() {
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.b("PlanSettingsActivity", "No current plan now.");
            PlanSettingsActivity.this.finish();
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onSuccess(final IntPlan intPlan) {
            if (intPlan == null) {
                LogUtil.h("PlanSettingsActivity", "getCurrentIntPlan data null.");
            } else {
                HandlerExecutor.e(new Runnable() { // from class: gcj
                    @Override // java.lang.Runnable
                    public final void run() {
                        PlanSettingsActivity.AnonymousClass3.this.c(intPlan);
                    }
                });
            }
        }

        public /* synthetic */ void c(IntPlan intPlan) {
            PlanSettingsActivity.this.b = intPlan;
            PlanSettingsActivity.this.b();
        }
    }

    @Override // com.huawei.health.suggestion.ui.BaseActivity
    public void initViewController() {
        this.j.setOnClickListener(this);
        this.c.setOnClickListener(this);
        this.f.setOnClickListener(this);
    }

    private void e() {
        this.i = (LinearLayout) findViewById(R.id.sync_calendar_layout);
        this.h = (HealthDivider) findViewById(R.id.sync_calendar_divider);
        if (EnvironmentInfo.k()) {
            ReleaseLogUtil.e("PlanSettingsActivity", "isMobileAppEngine is true");
            this.i.setVisibility(8);
            this.h.setVisibility(8);
        }
        this.j = (HealthSwitchButton) findViewById(R.id.sug_sync_calendar_enabled);
        ImageView imageView = (ImageView) findViewById(R.id.exe_day_edit_arrow);
        this.c = (LinearLayout) findViewById(R.id.exe_day_container);
        this.f3319a = (HealthDivider) findViewById(R.id.exe_day_divider);
        ImageView imageView2 = (ImageView) findViewById(R.id.remind_time_edit_arrow);
        this.f = (LinearLayout) findViewById(R.id.remind_time_container);
        if (LanguageUtil.bc(this)) {
            if (nrt.a(BaseApplication.getContext())) {
                BitmapDrawable cKm_ = nrz.cKm_(this.e, getResources().getDrawable(R.drawable._2131431570_res_0x7f0b1092));
                imageView.setImageDrawable(cKm_);
                imageView2.setImageDrawable(cKm_);
            } else {
                imageView.setImageResource(R$drawable.common_ui_arrow_left);
                imageView2.setImageResource(R$drawable.common_ui_arrow_left);
            }
        } else if (nrt.a(BaseApplication.getContext())) {
            imageView.setImageResource(R.drawable._2131431570_res_0x7f0b1092);
            imageView2.setImageResource(R.drawable._2131431570_res_0x7f0b1092);
        } else {
            imageView.setImageResource(R$drawable.common_ui_arrow_right);
            imageView2.setImageResource(R$drawable.common_ui_arrow_right);
        }
        String b = ash.b("ai_plan_sync");
        boolean z = (b == null || b.equals("")) ? false : true;
        this.d = z;
        this.j.setChecked(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (this.b == null) {
            LogUtil.a("PlanSettingsActivity", "setExeDayContainerView intPlan == null");
            return;
        }
        if (IntPlan.PlanType.AI_RUN_PLAN.equals(this.b.getPlanType()) || IntPlan.PlanType.AI_FITNESS_PLAN.equals(this.b.getPlanType())) {
            ViewGroup.LayoutParams layoutParams = this.c.getLayoutParams();
            if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                marginLayoutParams.setMarginStart(marginLayoutParams.getMarginStart() - ((Integer) BaseActivity.getSafeRegionWidth().first).intValue());
                marginLayoutParams.setMarginEnd(marginLayoutParams.getMarginEnd() - ((Integer) BaseActivity.getSafeRegionWidth().second).intValue());
                this.c.setLayoutParams(marginLayoutParams);
                return;
            }
            return;
        }
        LinearLayout linearLayout = this.c;
        if (linearLayout == null || this.f3319a == null) {
            return;
        }
        linearLayout.setVisibility(8);
        this.f3319a.setVisibility(8);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        IntPlan intPlan = this.b;
        if (intPlan == null) {
            LogUtil.b("PlanSettingsActivity", "showEditTrainingDay mCurrentPlan == null");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view == this.j) {
            if (this.d) {
                a();
            } else {
                h();
            }
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (intPlan.getPlanType() == IntPlan.PlanType.AI_RUN_PLAN) {
            aKe_(view);
        } else if (this.b.getPlanType() == IntPlan.PlanType.AI_FITNESS_PLAN || this.b.getPlanType() == IntPlan.PlanType.FIT_PLAN) {
            aKd_(view);
        } else {
            LogUtil.b("PlanSettingsActivity", "click non support type");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void aKe_(View view) {
        if (this.b.getCompatiblePlan() == null) {
            LogUtil.b("PlanSettingsActivity", "AI_RUN_PLAN compatiblePlan null.");
            return;
        }
        if (view == this.c) {
            Intent intent = new Intent(this, (Class<?>) RunPlanCreateActivity.class);
            intent.putExtra("type", 1);
            intent.putExtra("trainLevel", this.b.getCompatiblePlan().getTrainLevel());
            ArrayList<Integer> arrayList = new ArrayList<>(this.b.getCompatiblePlan().getRunDate());
            ArrayList<Integer> arrayList2 = new ArrayList<>(this.b.getCompatiblePlan().getExeDate());
            intent.putIntegerArrayListExtra("runDate", arrayList);
            intent.putIntegerArrayListExtra("exeDate", arrayList2);
            intent.putExtra("planId", this.b.getPlanId());
            startActivity(intent);
            return;
        }
        if (view == this.f) {
            Intent intent2 = new Intent(this, (Class<?>) ExerciseRemindActivity.class);
            intent2.putExtra("planType", this.b.getCompatiblePlan().acquireType());
            startActivity(intent2);
            return;
        }
        LogUtil.b("PlanSettingsActivity", "click nothing");
    }

    private void aKd_(View view) {
        if (view == this.c) {
            JumpUtil.d(this);
            Object[] objArr = new Object[2];
            objArr[0] = "Click exeDay editor of plan ";
            objArr[1] = this.b.getMetaInfo() != null ? this.b.getMetaInfo().getName() : "";
            LogUtil.a("PlanSettingsActivity", objArr);
            return;
        }
        if (view == this.f) {
            Intent intent = new Intent(this, (Class<?>) ExerciseRemindActivity.class);
            if (this.b.getCompatiblePlan() != null) {
                intent.putExtra("planType", this.b.getCompatiblePlan().acquireType());
            }
            startActivity(intent);
            Object[] objArr2 = new Object[2];
            objArr2[0] = "Click remind editor of plan ";
            objArr2[1] = this.b.getMetaInfo() != null ? this.b.getMetaInfo().getName() : "";
            LogUtil.a("PlanSettingsActivity", objArr2);
            return;
        }
        LogUtil.b("PlanSettingsActivity", "click nothing");
    }

    private void a() {
        if (fyo.e(getApplicationContext())) {
            d();
        } else {
            a(new String[]{"android.permission.WRITE_CALENDAR", "android.permission.READ_CALENDAR"}, false);
        }
    }

    private void a(String[] strArr, final boolean z) {
        jeg.d().bGx_(this, strArr, new PermissionsResultAction() { // from class: com.huawei.health.suggestion.ui.run.activity.PlanSettingsActivity.2
            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onGranted() {
                LogUtil.a("PlanSettingsActivity", "CalendarPermission Granted");
                if (z) {
                    PlanSettingsActivity.this.c();
                } else {
                    PlanSettingsActivity.this.d();
                }
            }

            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onDenied(String str) {
                LogUtil.a("PlanSettingsActivity", "Permission Denied, mCalendarSyncStatus: " + PlanSettingsActivity.this.d);
                PlanSettingsActivity.this.j.setChecked(PlanSettingsActivity.this.d);
                if (PlanSettingsActivity.this.shouldShowRequestPermissionRationale(str)) {
                    return;
                }
                nsn.e(PlanSettingsActivity.this.e, PermissionUtil.PermissionType.CALENDAR);
            }
        });
    }

    public void d() {
        this.d = false;
        this.j.setChecked(false);
        fyo.c(this.e, ash.b("ai_plan_sync_name"), this.b.getPlanType());
        ash.d("ai_plan_sync_name");
        ash.d("ai_plan_sync");
    }

    private void h() {
        if (fyo.e(getApplicationContext())) {
            c();
        } else {
            a(new String[]{"android.permission.WRITE_CALENDAR", "android.permission.READ_CALENDAR"}, true);
        }
    }

    public void c() {
        this.j.setChecked(true);
        this.d = true;
        fyo.a(this.b, this.e, 1);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
