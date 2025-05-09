package com.huawei.ui.main.stories.health.activity.healthdata;

import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.template.BaseActivity;
import defpackage.nsy;
import defpackage.ruf;

/* loaded from: classes9.dex */
public class RunningDataSpecificationActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private LinearLayout f10082a;
    private LinearLayout b;
    private LinearLayout c;
    private LinearLayout d;
    private LinearLayout e;
    private LinearLayout f;
    private LinearLayout i;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        cancelAdaptRingRegion();
        setContentView(R.layout.activity_running_specification_layout);
        a();
        c();
    }

    private void a() {
        e();
        this.f = (LinearLayout) findViewById(R.id.running_speci_sss);
        this.i = (LinearLayout) findViewById(R.id.running_speci_ss);
        this.c = (LinearLayout) findViewById(R.id.running_speci_s);
        this.b = (LinearLayout) findViewById(R.id.running_speci_a);
        this.d = (LinearLayout) findViewById(R.id.running_speci_b);
        this.f10082a = (LinearLayout) findViewById(R.id.running_speci_c);
        this.e = (LinearLayout) findViewById(R.id.running_speci_d);
    }

    private void c() {
        d();
        b();
    }

    private void e() {
        CustomTitleBar customTitleBar = (CustomTitleBar) nsy.cMc_(this, R.id.running_detail_titlebar);
        customTitleBar.setTitleText(getString(R$string.IDS_fitness_core_sleep_explain_title));
        customTitleBar.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.RunningDataSpecificationActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RunningDataSpecificationActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void d() {
        Drawable drawable = getResources().getDrawable(R.drawable._2131431328_res_0x7f0b0fa0);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        HealthTextView dQV_ = ruf.dQV_(this.f, R.id.item_running_speci_left, this);
        dQV_.setCompoundDrawablesRelative(drawable, null, null, null);
        dQV_.setCompoundDrawablePadding(30);
        dQV_.setText(R$string.IDS_data_running_index_level_sss);
        ruf.dQV_(this.f, R.id.item_running_speci_center, this).setText(ruf.e(2, 65.0f, 0.0f, true));
        ruf.dQV_(this.f, R.id.item_running_speci_right, this).setText(ruf.e(2, 58.0f, 0.0f, true));
        Drawable drawable2 = getResources().getDrawable(R.drawable._2131431327_res_0x7f0b0f9f);
        drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
        HealthTextView dQV_2 = ruf.dQV_(this.i, R.id.item_running_speci_left, this);
        dQV_2.setCompoundDrawablesRelative(drawable2, null, null, null);
        dQV_2.setCompoundDrawablePadding(30);
        dQV_2.setText(R$string.IDS_data_running_index_level_ss);
        ruf.dQV_(this.i, R.id.item_running_speci_center, this).setText(ruf.e(0, 60.0f, 65.0f, true));
        ruf.dQV_(this.i, R.id.item_running_speci_right, this).setText(ruf.e(0, 53.0f, 58.0f, true));
        Drawable drawable3 = getResources().getDrawable(R.drawable._2131431326_res_0x7f0b0f9e);
        drawable3.setBounds(0, 0, drawable3.getMinimumWidth(), drawable3.getMinimumHeight());
        HealthTextView dQV_3 = ruf.dQV_(this.c, R.id.item_running_speci_left, this);
        dQV_3.setCompoundDrawablesRelative(drawable3, null, null, null);
        dQV_3.setCompoundDrawablePadding(30);
        dQV_3.setText(R$string.IDS_data_running_index_level_s);
        ruf.dQV_(this.c, R.id.item_running_speci_center, this).setText(ruf.e(0, 50.0f, 60.0f, true));
        ruf.dQV_(this.c, R.id.item_running_speci_right, this).setText(ruf.e(0, 45.0f, 53.0f, true));
    }

    private void b() {
        Drawable drawable = getResources().getDrawable(R.drawable._2131431322_res_0x7f0b0f9a);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        HealthTextView dQV_ = ruf.dQV_(this.b, R.id.item_running_speci_left, this);
        dQV_.setCompoundDrawablesRelative(drawable, null, null, null);
        dQV_.setCompoundDrawablePadding(30);
        dQV_.setText(R$string.IDS_data_running_index_level_a);
        ruf.dQV_(this.b, R.id.item_running_speci_center, this).setText(ruf.e(0, 40.0f, 50.0f, true));
        ruf.dQV_(this.b, R.id.item_running_speci_right, this).setText(ruf.e(0, 36.0f, 45.0f, true));
        Drawable drawable2 = getResources().getDrawable(R.drawable._2131431323_res_0x7f0b0f9b);
        drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
        HealthTextView dQV_2 = ruf.dQV_(this.d, R.id.item_running_speci_left, this);
        dQV_2.setCompoundDrawablesRelative(drawable2, null, null, null);
        dQV_2.setCompoundDrawablePadding(30);
        dQV_2.setText(R$string.IDS_data_running_index_level_b);
        ruf.dQV_(this.d, R.id.item_running_speci_center, this).setText(ruf.e(0, 30.0f, 40.0f, true));
        ruf.dQV_(this.d, R.id.item_running_speci_right, this).setText(ruf.e(0, 27.0f, 36.0f, true));
        Drawable drawable3 = getResources().getDrawable(R.drawable._2131431324_res_0x7f0b0f9c);
        drawable3.setBounds(0, 0, drawable3.getMinimumWidth(), drawable3.getMinimumHeight());
        HealthTextView dQV_3 = ruf.dQV_(this.f10082a, R.id.item_running_speci_left, this);
        dQV_3.setCompoundDrawablesRelative(drawable3, null, null, null);
        dQV_3.setCompoundDrawablePadding(30);
        dQV_3.setText(R$string.IDS_data_running_index_level_c);
        ruf.dQV_(this.f10082a, R.id.item_running_speci_center, this).setText(ruf.e(0, 25.0f, 30.0f, true));
        ruf.dQV_(this.f10082a, R.id.item_running_speci_right, this).setText(ruf.e(0, 22.0f, 27.0f, true));
        Drawable drawable4 = getResources().getDrawable(R.drawable._2131431325_res_0x7f0b0f9d);
        drawable4.setBounds(0, 0, drawable4.getMinimumWidth(), drawable4.getMinimumHeight());
        HealthTextView dQV_4 = ruf.dQV_(this.e, R.id.item_running_speci_left, this);
        dQV_4.setCompoundDrawablesRelative(drawable4, null, null, null);
        dQV_4.setCompoundDrawablePadding(30);
        dQV_4.setText(R$string.IDS_data_running_index_level_d);
        ruf.dQV_(this.e, R.id.item_running_speci_center, this).setText(ruf.e(1, 0.0f, 25.0f, true));
        ruf.dQV_(this.e, R.id.item_running_speci_right, this).setText(ruf.e(1, 0.0f, 22.0f, true));
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
