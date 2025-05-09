package com.huawei.health.suggestion.ui.plan.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.plan.adapter.IntPlanDetailAdapter;
import com.huawei.health.suggestion.ui.plan.viewmodel.IntPlanDetailDataViewModel;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.gdw;
import defpackage.ggs;
import defpackage.koq;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class PlanListActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private IntPlanDetailDataViewModel f3238a;
    private IntPlanDetailAdapter b;
    private Observer<gdw> c = new Observer() { // from class: com.huawei.health.suggestion.ui.plan.activity.PlanListActivity$$ExternalSyntheticLambda0
        @Override // androidx.lifecycle.Observer
        public final void onChanged(Object obj) {
            PlanListActivity.this.c((gdw) obj);
        }
    };
    private Intent d;
    private CustomTitleBar e;
    private HealthRecycleView h;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.sug_layout_recommend_fitplanlist);
        this.d = getIntent();
        this.e = (CustomTitleBar) findViewById(R.id.sug_fitness_title);
        a();
        HealthRecycleView healthRecycleView = (HealthRecycleView) findViewById(R.id.plan_list_recycle_view);
        this.h = healthRecycleView;
        ggs.a(this, healthRecycleView);
        IntPlanDetailAdapter intPlanDetailAdapter = new IntPlanDetailAdapter(this, getSupportFragmentManager(), this.h);
        this.b = intPlanDetailAdapter;
        this.h.setAdapter(intPlanDetailAdapter);
        this.f3238a = (IntPlanDetailDataViewModel) new ViewModelProvider(this).get(IntPlanDetailDataViewModel.class);
        e();
        this.f3238a.e(this.c);
        this.f3238a.d(false);
    }

    private void a() {
        Intent intent;
        if (this.e == null || (intent = this.d) == null) {
            return;
        }
        String stringExtra = intent.getStringExtra("CUSTOM_TITLE");
        if (TextUtils.isEmpty(stringExtra)) {
            return;
        }
        this.e.setTitleText(stringExtra);
    }

    private void e() {
        Intent intent;
        if (this.f3238a == null || (intent = this.d) == null) {
            return;
        }
        ArrayList<Integer> integerArrayListExtra = intent.getIntegerArrayListExtra("FILTERED_PLAN_TYPES");
        if (koq.b(integerArrayListExtra)) {
            return;
        }
        this.f3238a.d(integerArrayListExtra);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(gdw gdwVar) {
        LogUtil.a("PlanListActivity", "notifyUpdateItemData enter");
        if (gdwVar == null) {
            LogUtil.h("PlanListActivity", "notifyUpdateItemData itemData is null");
            return;
        }
        IntPlanDetailAdapter intPlanDetailAdapter = this.b;
        if (intPlanDetailAdapter != null) {
            intPlanDetailAdapter.e(gdwVar);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
