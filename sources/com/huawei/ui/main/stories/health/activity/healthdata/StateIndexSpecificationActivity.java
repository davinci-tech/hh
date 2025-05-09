package com.huawei.ui.main.stories.health.activity.healthdata;

import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.template.BaseActivity;
import defpackage.nsy;
import defpackage.ruf;
import health.compact.a.LanguageUtil;

/* loaded from: classes9.dex */
public class StateIndexSpecificationActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private RelativeLayout f10087a;
    private RelativeLayout b;
    private RelativeLayout c;
    private RelativeLayout d;
    private CustomTitleBar e;
    private RelativeLayout i;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_state_specification_layout);
        a();
        e();
    }

    private void a() {
        CustomTitleBar customTitleBar = (CustomTitleBar) nsy.cMc_(this, R.id.state_speci_detail_titlebar);
        this.e = customTitleBar;
        customTitleBar.setTitleText(getString(R$string.IDS_fitness_core_sleep_explain_title));
        this.e.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.StateIndexSpecificationActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                StateIndexSpecificationActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void e() {
        this.f10087a = (RelativeLayout) findViewById(R.id.item_state_level_very_well);
        this.i = (RelativeLayout) findViewById(R.id.item_state_level_well);
        this.d = (RelativeLayout) findViewById(R.id.item_state_level_normal);
        this.c = (RelativeLayout) findViewById(R.id.item_state_level_pool);
        this.b = (RelativeLayout) findViewById(R.id.item_state_level_very_pool);
        c();
        Drawable drawable = getResources().getDrawable(R.drawable._2131431327_res_0x7f0b0f9f);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        HealthTextView dQV_ = ruf.dQV_(this.c, R.id.item_state_speci_left, this);
        dQV_.setCompoundDrawablesRelative(drawable, null, null, null);
        dQV_.setCompoundDrawablePadding(30);
        dQV_.setText(getString(R$string.IDS_data_index_pool));
        ruf.dQV_(this.c, R.id.item_state_speci_bottom, this).setText(getString(R$string.IDS_data_index_pool_explain));
        HealthTextView dQV_2 = ruf.dQV_(this.c, R.id.item_state_speci_right, this);
        String e = ruf.e(0, -16.0f, -6.0f, false);
        if (LanguageUtil.b(BaseApplication.getContext())) {
            dQV_2.setText(e + Constants.LINK);
        } else {
            dQV_2.setText(e);
        }
        Drawable drawable2 = getResources().getDrawable(R.drawable._2131431328_res_0x7f0b0fa0);
        drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
        HealthTextView dQV_3 = ruf.dQV_(this.b, R.id.item_state_speci_left, this);
        dQV_3.setCompoundDrawablesRelative(drawable2, null, null, null);
        dQV_3.setCompoundDrawablePadding(30);
        dQV_3.setText(getString(R$string.IDS_data_index_very_pool));
        ruf.dQV_(this.b, R.id.item_state_speci_bottom, this).setText(getString(R$string.IDS_data_index_very_pool_explain));
        ruf.dQV_(this.b, R.id.item_state_speci_right, this).setText(ruf.e(1, 0.0f, -16.0f, false));
    }

    private void c() {
        Drawable drawable = getResources().getDrawable(R.drawable._2131431325_res_0x7f0b0f9d);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        HealthTextView dQV_ = ruf.dQV_(this.f10087a, R.id.item_state_speci_left, this);
        dQV_.setCompoundDrawablesRelative(drawable, null, null, null);
        dQV_.setCompoundDrawablePadding(30);
        dQV_.setText(getString(R$string.IDS_data_index_very_well));
        ruf.dQV_(this.f10087a, R.id.item_state_speci_bottom, this).setText(getString(R$string.IDS_data_index_very_well_explain));
        ruf.dQV_(this.f10087a, R.id.item_state_speci_right, this).setText(ruf.e(2, 3.0f, 0.0f, false));
        Drawable drawable2 = getResources().getDrawable(R.drawable._2131431323_res_0x7f0b0f9b);
        drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
        HealthTextView dQV_2 = ruf.dQV_(this.i, R.id.item_state_speci_left, this);
        dQV_2.setCompoundDrawablesRelative(drawable2, null, null, null);
        dQV_2.setCompoundDrawablePadding(30);
        dQV_2.setText(getString(R$string.IDS_data_index_well));
        ruf.dQV_(this.i, R.id.item_state_speci_bottom, this).setText(getString(R$string.IDS_data_day_tab_tip));
        ruf.dQV_(this.i, R.id.item_state_speci_right, this).setText(ruf.e(0, 0.5f, 3.0f, false));
        Drawable drawable3 = getResources().getDrawable(R.drawable._2131431322_res_0x7f0b0f9a);
        drawable3.setBounds(0, 0, drawable3.getMinimumWidth(), drawable3.getMinimumHeight());
        HealthTextView dQV_3 = ruf.dQV_(this.d, R.id.item_state_speci_left, this);
        dQV_3.setCompoundDrawablesRelative(drawable3, null, null, null);
        dQV_3.setCompoundDrawablePadding(30);
        dQV_3.setText(getString(R$string.IDS_data_index_normal));
        ruf.dQV_(this.d, R.id.item_state_speci_bottom, this).setText(getString(R$string.IDS_data_index_normal_explain));
        HealthTextView dQV_4 = ruf.dQV_(this.d, R.id.item_state_speci_right, this);
        String e = ruf.e(0, -6.0f, 0.5f, false);
        if (LanguageUtil.b(BaseApplication.getContext())) {
            dQV_4.setText(e + Constants.LINK);
            return;
        }
        dQV_4.setText(e);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
