package com.huawei.ui.main.stories.health.activity.healthdata;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.subtab.HealthSubTabWidget;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.fragment.StateDayDetailFragment;
import com.huawei.ui.main.stories.health.fragment.StateMonthDetailFragment;
import com.huawei.ui.main.stories.health.fragment.StateWeekDetailFragment;
import com.huawei.ui.main.stories.health.interactors.healthdata.RunningStateIndexData;
import com.huawei.ui.main.stories.template.BaseActivity;
import defpackage.nqx;
import defpackage.nsy;
import defpackage.ruf;

/* loaded from: classes6.dex */
public class StateIndexActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private Context f10083a;
    private CustomTitleBar b;
    private RunningStateIndexData c = new RunningStateIndexData();
    private nqx d;
    private HealthSubTabWidget e;
    private HealthViewPager g;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        cancelAdaptRingRegion();
        setContentView(R.layout.activity_state_index_layout);
        this.f10083a = this;
        d();
    }

    private void d() {
        CustomTitleBar customTitleBar = (CustomTitleBar) nsy.cMc_(this, R.id.state_detail_titlebar);
        this.b = customTitleBar;
        customTitleBar.setTitleText(getString(R$string.IDS_data_state_index_title));
        this.b.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.StateIndexActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                StateIndexActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        ruf.d(this, this.b, "TRAIN_CONDITION");
        this.e = (HealthSubTabWidget) findViewById(R.id.state_subTabLayout);
        HealthViewPager healthViewPager = (HealthViewPager) nsy.cMc_(this, R.id.state_detail_viewpager);
        this.g = healthViewPager;
        if (healthViewPager == null) {
            return;
        }
        healthViewPager.setScrollHeightArea(200);
        this.d = new nqx(this, this.g, this.e);
        if (getIntent() != null && (getIntent().getParcelableExtra("state_index_level_data") instanceof RunningStateIndexData)) {
            this.c = (RunningStateIndexData) getIntent().getParcelableExtra("state_index_level_data");
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable("state_index_level_data", this.c);
        StateDayDetailFragment stateDayDetailFragment = new StateDayDetailFragment();
        stateDayDetailFragment.setArguments(bundle);
        StateWeekDetailFragment stateWeekDetailFragment = new StateWeekDetailFragment();
        stateWeekDetailFragment.setArguments(bundle);
        StateMonthDetailFragment stateMonthDetailFragment = new StateMonthDetailFragment();
        stateMonthDetailFragment.setArguments(bundle);
        this.d.c(this.e.c(this.f10083a.getResources().getString(R$string.IDS_fitness_detail_radio_button_tab_day)), stateDayDetailFragment, true);
        this.d.c(this.e.c(this.f10083a.getResources().getString(R$string.IDS_fitness_detail_radio_button_tab_week)), stateWeekDetailFragment, false);
        this.d.c(this.e.c(this.f10083a.getResources().getString(R$string.IDS_fitness_detail_radio_button_tab_month)), stateMonthDetailFragment, false);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
