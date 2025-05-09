package com.huawei.health.suggestion.ui.plan.activity;

import android.content.Context;
import android.content.res.Configuration;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.BaseActivity;
import com.huawei.health.suggestion.ui.plan.activity.FitnessPlanDetailActivity;
import com.huawei.health.suggestion.ui.plan.adapter.FitnessDetailAdapter;
import com.huawei.health.suggestion.ui.plan.mvp.PlanDetailContract;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.fye;
import defpackage.fyj;
import defpackage.nsf;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import java.util.List;

/* loaded from: classes4.dex */
public class FitnessPlanDetailActivity extends BaseActivity implements View.OnClickListener, PlanDetailContract.Iview {

    /* renamed from: a, reason: collision with root package name */
    private Group f3237a;
    private FitnessDetailAdapter b;
    private ConstraintLayout c;
    private CustomTitleBar e;
    private String g;
    private PlanDetailContract.Ipresenter i;
    private FrameLayout j;
    private int f = 0;
    private NoTitleCustomAlertDialog d = null;

    @Override // com.huawei.health.suggestion.ui.plan.mvp.PlanDetailContract.Iview
    public Context acquireContext() {
        return this;
    }

    @Override // com.huawei.health.suggestion.ui.BaseActivity
    public void initViewController() {
    }

    static /* synthetic */ int e(FitnessPlanDetailActivity fitnessPlanDetailActivity, int i) {
        int i2 = fitnessPlanDetailActivity.f + i;
        fitnessPlanDetailActivity.f = i2;
        return i2;
    }

    @Override // com.huawei.health.suggestion.ui.BaseActivity
    public void initData() {
        if (getIntent() == null) {
            LogUtil.h("Suggestion_FitnessPlanDetailActivity", "initData: getIntent is null");
            finish();
            return;
        }
        this.g = getIntent().getStringExtra("params_plan_id_key");
        int intExtra = getIntent().getIntExtra("params_plan_TYPE_key", IntPlan.PlanType.FIT_PLAN.getType());
        if (TextUtils.isEmpty(this.g)) {
            LogUtil.h("Suggestion_FitnessPlanDetailActivity", "initData: getIntent plan id is empty");
            finish();
            return;
        }
        LogUtil.h("Suggestion_FitnessPlanDetailActivity", "initData: planType is ", Integer.valueOf(intExtra));
        if (intExtra == IntPlan.PlanType.AI_RUN_PLAN.getType()) {
            this.i = new fye(this.g, this);
        } else {
            this.i = new fyj(this.g, this);
        }
    }

    @Override // com.huawei.health.suggestion.ui.BaseActivity
    public void initLayout() {
        setContentView(R.layout.sug_activity_fitness_plan_detail);
        this.j = (FrameLayout) findViewById(R.id.detail_paid_view_fl);
        this.e = (CustomTitleBar) findViewById(R.id.detail_paid_titlebar);
        this.c = (ConstraintLayout) findViewById(R.id.detail_net_error);
        this.f3237a = (Group) findViewById(R.id.detail_content_group);
        HealthRecycleView healthRecycleView = (HealthRecycleView) findViewById(R.id.detail_paid_rv);
        healthRecycleView.setLayoutManager(new LinearLayoutManager(this));
        FitnessDetailAdapter fitnessDetailAdapter = new FitnessDetailAdapter();
        this.b = fitnessDetailAdapter;
        healthRecycleView.setAdapter(fitnessDetailAdapter);
        this.c.setOnClickListener(this);
        if (LanguageUtil.bc(this)) {
            this.e.setLeftButtonDrawable(getResources().getDrawable(R.mipmap._2131820922_res_0x7f11017a), nsf.h(R$string.accessibility_go_back));
        } else {
            this.e.setLeftButtonDrawable(getResources().getDrawable(R.mipmap._2131820919_res_0x7f110177), nsf.h(R$string.accessibility_go_back));
        }
        healthRecycleView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.huawei.health.suggestion.ui.plan.activity.FitnessPlanDetailActivity.2
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                FitnessPlanDetailActivity.e(FitnessPlanDetailActivity.this, i2);
                if (FitnessPlanDetailActivity.this.f > 100) {
                    FitnessPlanDetailActivity.this.e.setTitleBarBackgroundColor(FitnessPlanDetailActivity.this.getResources().getColor(R.color._2131296690_res_0x7f0901b2));
                } else {
                    FitnessPlanDetailActivity.this.e.setTitleBarBackgroundColor(FitnessPlanDetailActivity.this.getResources().getColor(R.color._2131296971_res_0x7f0902cb));
                }
            }
        });
        c();
    }

    private void c() {
        if (CommonUtil.aa(this)) {
            this.c.setVisibility(8);
            this.f3237a.setVisibility(0);
            PlanDetailContract.Ipresenter ipresenter = this.i;
            if (ipresenter != null) {
                ipresenter.getData();
                return;
            }
            return;
        }
        this.f3237a.setVisibility(8);
        this.c.setVisibility(0);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        PlanDetailContract.Ipresenter ipresenter = this.i;
        if (ipresenter != null) {
            ipresenter.onDestroy();
            this.i = null;
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        PlanDetailContract.Ipresenter ipresenter = this.i;
        if (ipresenter != null) {
            ipresenter.onResume();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (nsn.a(500)) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view.getId() == R.id.detail_net_error) {
            c();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.health.suggestion.ui.plan.mvp.PlanDetailContract.Iview
    public void addTradeView(View view) {
        if (this.j == null) {
            LogUtil.h("Suggestion_FitnessPlanDetailActivity", "addTradeView>mPaidViewContent is null");
        } else if (view != null) {
            LogUtil.a("Suggestion_FitnessPlanDetailActivity", "addTradeView: mTradeView is added");
            this.j.addView(view);
        } else {
            LogUtil.h("Suggestion_FitnessPlanDetailActivity", "addTradeView>mTradeView is_null,Id=", this.g);
        }
    }

    @Override // com.huawei.health.suggestion.ui.plan.mvp.PlanDetailContract.Iview
    public void recycleTradeView(View view) {
        FrameLayout frameLayout;
        if (view == null || (frameLayout = this.j) == null) {
            return;
        }
        frameLayout.removeView(view);
    }

    @Override // com.huawei.health.suggestion.ui.plan.mvp.PlanDetailContract.Iview
    public void bindData(List<String> list) {
        FitnessDetailAdapter fitnessDetailAdapter = this.b;
        if (fitnessDetailAdapter != null) {
            fitnessDetailAdapter.b(list);
        }
    }

    @Override // com.huawei.health.suggestion.ui.plan.mvp.PlanDetailContract.Iview
    public void bindTitleText(String str) {
        CustomTitleBar customTitleBar = this.e;
        if (customTitleBar != null) {
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            customTitleBar.setTitleText(str);
        }
    }

    @Override // com.huawei.health.suggestion.ui.plan.mvp.PlanDetailContract.Iview
    public void finishView() {
        finish();
    }

    @Override // com.huawei.health.suggestion.ui.plan.mvp.PlanDetailContract.Iview
    public void showDialog() {
        if (this.d == null) {
            NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this);
            builder.e(R.string._2130848610_res_0x7f022b62);
            builder.czC_(R.string._2130848480_res_0x7f022ae0, new View.OnClickListener() { // from class: ftj
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FitnessPlanDetailActivity.this.aGu_(view);
                }
            });
            builder.czz_(R.string._2130848409_res_0x7f022a99, new View.OnClickListener() { // from class: fti
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FitnessPlanDetailActivity.aGt_(view);
                }
            });
            this.d = builder.e();
        }
        this.d.show();
    }

    public /* synthetic */ void aGu_(View view) {
        PlanDetailContract.Ipresenter ipresenter = this.i;
        if (ipresenter != null) {
            ipresenter.viewCurrentPlan();
            finishView();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void aGt_(View view) {
        LogUtil.a("Suggestion_FitnessPlanDetailActivity", "onClick setNegativeButton");
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
