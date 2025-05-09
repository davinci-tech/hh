package com.huawei.ui.main.stories.health.activity.healthdata;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import com.huawei.haf.common.os.SystemInfo;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.radiobutton.HealthRadioButton;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.activity.healthdata.WeightUnitSettingActivity;
import defpackage.gnp;
import defpackage.ixx;
import defpackage.kor;
import defpackage.nro;
import defpackage.nsf;
import defpackage.nsk;
import defpackage.qsj;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.util.HashMap;

/* loaded from: classes.dex */
public class WeightUnitSettingActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f10105a;
    private LinearLayout b;
    private int c;
    private Context d;
    private HealthRadioButton e;
    private LinearLayout f;
    private HealthRadioButton g;
    private HealthRadioButton h;
    private HealthTextView i;
    private LinearLayout j;
    private CustomTitleBar k;
    private int m;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setBackgroundDrawable(null);
        setContentView(R.layout.activity_weight_unit_setting);
        LogUtil.a("PluginDevice_WeightUnitSettingActivity", "onCreate...");
        this.d = BaseApplication.getContext();
        a();
    }

    private void a() {
        Drawable drawable;
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.weight_unit_setting_title_bar);
        this.k = customTitleBar;
        customTitleBar.setTitleBarBackgroundColor(getColor(R.color._2131296690_res_0x7f0901b2));
        if (LanguageUtil.bc(this.d)) {
            drawable = this.d.getResources().getDrawable(R.drawable._2131428443_res_0x7f0b045b);
        } else {
            drawable = this.d.getResources().getDrawable(R.drawable._2131428438_res_0x7f0b0456);
        }
        this.k.setLeftButtonDrawable(drawable, nsf.h(R$string.accessibility_go_back));
        this.k.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: qfx
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WeightUnitSettingActivity.this.dBS_(view);
            }
        });
        this.b = (LinearLayout) findViewById(R.id.weight_catty_settings_unit_layout);
        this.e = (HealthRadioButton) findViewById(R.id.weight_catty_unit_radio_button);
        this.f = (LinearLayout) findViewById(R.id.weight_kg_settings_unit_layout);
        this.g = (HealthRadioButton) findViewById(R.id.weight_kg_unit_radio_button);
        this.j = (LinearLayout) findViewById(R.id.weight_lbs_settings_unit_layout);
        this.i = (HealthTextView) findViewById(R.id.weight_lbs_unit_name_text);
        this.h = (HealthRadioButton) findViewById(R.id.weight_lbs_unit_radio_button);
        this.f10105a = (HealthTextView) findViewById(R.id.weight_unit_setting_hint);
        if (LanguageUtil.h(this.d) && !Utils.o()) {
            this.b.setVisibility(0);
        }
        this.i.setText(getString(R$string.IDS_weight_unit_lbs, new Object[]{""}));
        this.e.setClickable(false);
        this.g.setClickable(false);
        this.h.setClickable(false);
        this.c = UnitUtil.a();
        this.m = UnitUtil.a();
        a(this.c);
        this.b.setOnClickListener(new View.OnClickListener() { // from class: qgc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WeightUnitSettingActivity.this.dBT_(view);
            }
        });
        this.f.setOnClickListener(new View.OnClickListener() { // from class: qfz
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WeightUnitSettingActivity.this.dBU_(view);
            }
        });
        this.j.setOnClickListener(new View.OnClickListener() { // from class: qfy
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WeightUnitSettingActivity.this.dBV_(view);
            }
        });
        this.f10105a.setTypeface(nsk.cKN_());
    }

    public /* synthetic */ void dBS_(View view) {
        if (this.c != UnitUtil.a()) {
            setResult(500);
        }
        if (this.m != UnitUtil.a()) {
            qsj.dIi_(this, true);
        }
        LogUtil.a("PluginDevice_WeightUnitSettingActivity", "initView mUnit ", Integer.valueOf(this.m), " mCutUnit ", Integer.valueOf(this.c));
        finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dBT_(View view) {
        c(1);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dBU_(View view) {
        c(2);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dBV_(View view) {
        c(3);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void c(int i) {
        a(i);
        d(i);
        ObserverManagerUtil.c("WEIGHT_DATA_CHANGE_UNIT_H5", new Object[0]);
    }

    private void a(int i) {
        this.e.setChecked(false);
        this.g.setChecked(false);
        this.h.setChecked(false);
        if (i == 1 && LanguageUtil.h(this.d) && !Utils.o()) {
            this.e.setChecked(true);
        } else if (i == 3) {
            this.h.setChecked(true);
        } else {
            this.g.setChecked(true);
        }
    }

    private void d(int i) {
        UnitUtil.c(i);
        this.c = i;
        if (SystemInfo.h()) {
            LogUtil.a("PluginDevice_WeightUnitSettingActivity", "isNewHonor");
            gnp.a(this);
        }
        LogUtil.a("PluginDevice_WeightUnitSettingActivity", "saveWeightUnit : writeUnitToDb");
        kor.a().a("custom.weight_unit", String.valueOf(i));
        nro.e(this, -1);
    }

    private void e(int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        if (i == 1) {
            hashMap.put("type", "catty");
        }
        if (i == 2) {
            hashMap.put("type", "kilogram");
        }
        if (i == 3) {
            hashMap.put("type", "pounds");
        }
        ixx.d().d(this.d, AnalyticsValue.WEIGHT_PAGE_UNIT_SETTING_2160119.value(), hashMap, 0);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.m != UnitUtil.a()) {
            qsj.dIi_(this, true);
        }
        LogUtil.a("PluginDevice_WeightUnitSettingActivity", "onBackPressed mUnit ", Integer.valueOf(this.m), " mCutUnit ", Integer.valueOf(this.c));
        super.onBackPressed();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        e(this.c);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
