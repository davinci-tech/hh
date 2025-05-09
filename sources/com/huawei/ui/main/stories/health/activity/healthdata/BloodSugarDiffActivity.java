package com.huawei.ui.main.stories.health.activity.healthdata;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import defpackage.noh;
import defpackage.nsn;
import java.text.DecimalFormat;

/* loaded from: classes6.dex */
public class BloodSugarDiffActivity extends BaseActivity {
    private HealthTextView b;
    private Intent d;
    private CustomTitleBar e;
    private HealthTextView f;
    private HealthTextView g;
    private HealthTextView h;
    private HealthTextView i;
    private HealthTextView j;
    private HealthTextView k;
    private HealthTextView l;
    private HealthTextView m;
    private HealthTextView n;
    private HealthTextView o;
    private HealthTextView r;
    private final DecimalFormat c = new DecimalFormat("0.0");

    /* renamed from: a, reason: collision with root package name */
    private int f10027a = 0;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (nsn.r()) {
            setContentView(R.layout.health_data_bloodsugar_diff_large);
        } else {
            setContentView(R.layout.health_data_bloodsugar_diff);
        }
        e();
        a();
    }

    private void a() {
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.blood_sugar_diff_title);
        this.e = customTitleBar;
        customTitleBar.setRightButtonVisibility(8);
        this.r = (HealthTextView) findViewById(R.id.blood_sugar_diff_time);
        this.k = (HealthTextView) findViewById(R.id.blood_sugar_diff_number);
        this.l = (HealthTextView) findViewById(R.id.blood_sugar_diff_status);
        this.o = (HealthTextView) findViewById(R.id.diff_before_title);
        this.j = (HealthTextView) findViewById(R.id.diff_before_status);
        this.f = (HealthTextView) findViewById(R.id.diff_before_message);
        this.n = (HealthTextView) findViewById(R.id.diff_before_time);
        this.g = (HealthTextView) findViewById(R.id.diff_after_title);
        this.h = (HealthTextView) findViewById(R.id.diff_after_status);
        this.b = (HealthTextView) findViewById(R.id.diff_after_message);
        this.i = (HealthTextView) findViewById(R.id.diff_after_time);
        this.m = (HealthTextView) findViewById(R.id.diff_explan);
        b();
    }

    private void e() {
        this.d = getIntent();
    }

    private void b() {
        Intent intent = this.d;
        if (intent == null) {
            LogUtil.h("BloodSugarDiffActivity", "initData intent can not null");
            return;
        }
        dzi_(this.e, intent.getStringExtra("bloodSugar_diff_title"));
        dzi_(this.r, this.d.getStringExtra("bloodSugar_diff_time"));
        dzi_(this.k, b(this.d.getFloatExtra("bloodSugar_diff_number", this.f10027a)));
        a(this.d.getIntExtra("bloodSugar_diff_status", this.f10027a), this.l);
        dzi_(this.o, String.valueOf(this.d.getFloatExtra("bloodSugar_diff_befor_title", this.f10027a)) + getString(R$string.IDS_hw_health_show_healthdata_bloodsugar_mmol));
        a(this.d.getIntExtra("bloodSugar_diff_befor_status", this.f10027a), this.j);
        dzi_(this.f, this.d.getStringExtra("bloodSugar_diff_befor_message"));
        dzi_(this.n, this.d.getStringExtra("bloodSUgar_diff_befor_time"));
        dzi_(this.g, String.valueOf(this.d.getFloatExtra("bloodSugar_diff_after_title", this.f10027a)) + getString(R$string.IDS_hw_health_show_healthdata_bloodsugar_mmol));
        a(this.d.getIntExtra("bloodSugar_diff_after_status", this.f10027a), this.h);
        dzi_(this.b, this.d.getStringExtra("bloodSugar_diff_after_message"));
        dzi_(this.i, this.d.getStringExtra("bloodSugar_diff_after_time"));
        this.m.setText(noh.a(this, R$string.IDS_hw_show_healthdata_bloodsugar_meal_difference_explanation));
    }

    private void a(int i, HealthTextView healthTextView) {
        if (i != 1) {
            if (i != 2) {
                switch (i) {
                    case 1001:
                        healthTextView.setText(getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_status_too_low));
                        healthTextView.setTextColor(getResources().getColor(R.color._2131296797_res_0x7f09021d));
                        break;
                    case 1002:
                        healthTextView.setText(getResources().getString(R$string.IDS_hw_health_show_healthdata_status_low));
                        healthTextView.setTextColor(getResources().getColor(R.color._2131296797_res_0x7f09021d));
                        break;
                    case 1003:
                        break;
                    case 1004:
                        break;
                    case 1005:
                        healthTextView.setText(getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_status_high));
                        healthTextView.setTextColor(getResources().getColor(R.color._2131296795_res_0x7f09021b));
                        break;
                    case 1006:
                        healthTextView.setText(getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_status_too_high));
                        healthTextView.setTextColor(getResources().getColor(R.color._2131296795_res_0x7f09021b));
                        break;
                    default:
                        LogUtil.a("BloodSugarDiffActivity", "no support blood type");
                        break;
                }
            }
            healthTextView.setText(getResources().getString(R$string.IDS_hw_health_show_healthdata_status_high));
            healthTextView.setTextColor(getResources().getColor(R.color._2131296795_res_0x7f09021b));
            return;
        }
        healthTextView.setText(getResources().getString(R$string.IDS_hw_health_show_healthdata_status_normal));
        healthTextView.setTextColor(getResources().getColor(R.color._2131296801_res_0x7f090221));
    }

    private void dzi_(View view, String str) {
        if (view == null || TextUtils.isEmpty(str)) {
            LogUtil.h("BloodSugarDiffActivity", "setViewData healthHwTextView or textMsg can not null");
            return;
        }
        if (view instanceof HealthTextView) {
            ((HealthTextView) view).setText(str);
        } else if (view instanceof CustomTitleBar) {
            ((CustomTitleBar) view).setTitleText(str);
        } else {
            LogUtil.h("BloodSugarDiffActivity", "view else case");
        }
    }

    private String b(float f) {
        String format;
        synchronized (this.c) {
            format = this.c.format(f);
        }
        return format;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
