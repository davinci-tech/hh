package com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.activity.WifiDevicePressureCalibrateResultActivity;
import com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.view.NoTimeClockView;
import defpackage.gnm;
import defpackage.nsn;
import defpackage.psm;
import health.compact.a.CommonUtil;
import health.compact.a.UnitUtil;

/* loaded from: classes6.dex */
public class WifiDevicePressureCalibrateResultActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private LinearLayout f9869a;
    private HealthTextView b;
    private Context c;
    private LinearLayout d;
    private HealthTextView e;
    private Intent f;
    private boolean g;
    private NoTimeClockView h;
    private HealthTextView i;
    private FrameLayout j;
    private HealthTextView k;
    private HealthButton n;
    private HealthTextView o;
    private CustomTitleBar s;
    private String m = "";
    private String t = "";
    private String l = "";

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_wifi_device_pressure_calibrate_result);
        this.c = this;
        psm.e().c(true);
        Intent intent = getIntent();
        this.f = intent;
        if (intent != null) {
            this.t = intent.getStringExtra("health_wifi_device_userId");
            this.l = this.f.getStringExtra("health_wifi_device_productId");
            boolean booleanExtra = this.f.getBooleanExtra("pressure_is_have_datas", false);
            this.g = booleanExtra;
            if (booleanExtra) {
                this.m = this.f.getStringExtra("mResultHrv");
            } else {
                LogUtil.a("WifiDevicePressureCalibrateResultActivity", "mIsHavedDatas is false");
            }
        }
        this.s = (CustomTitleBar) findViewById(R.id.hw_wifi_device_result_pressure_calibrate_title_layout);
        this.j = (FrameLayout) findViewById(R.id.hw_pressure_calibrate_no_time_clock_frame);
        this.d = (LinearLayout) findViewById(R.id.hw_pressure_calibrate_result_success);
        this.b = (HealthTextView) findViewById(R.id.hw_pressure_calibrate_result_number);
        this.f9869a = (LinearLayout) findViewById(R.id.hw_pressure_calibrate_result_fail);
        this.i = (HealthTextView) findViewById(R.id.hw_pressure_calibrate_result_fail_tv);
        this.e = (HealthTextView) findViewById(R.id.hw_wifi_device_pressure_calibrate_result_fail_reason);
        this.k = (HealthTextView) findViewById(R.id.hw_wifi_device_pressure_calibrate_result_level);
        this.o = (HealthTextView) findViewById(R.id.hw_wifi_device_pressure_calibrate_result_knowledge);
        this.n = (HealthButton) findViewById(R.id.hw_wifi_device_pressure_calibrate_result_button);
        NoTimeClockView noTimeClockView = new NoTimeClockView(this.c);
        this.h = noTimeClockView;
        this.j.addView(noTimeClockView);
        d();
        c();
    }

    private void d() {
        this.s.setLeftButtonClickable(true);
        this.s.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: psw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WifiDevicePressureCalibrateResultActivity.this.dte_(view);
            }
        });
        this.n.setOnClickListener(new View.OnClickListener() { // from class: psx
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WifiDevicePressureCalibrateResultActivity.this.dtf_(view);
            }
        });
    }

    public /* synthetic */ void dte_(View view) {
        finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dtf_(View view) {
        if (this.g) {
            finish();
        } else {
            a();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            finish();
            return false;
        }
        return super.onKeyDown(i, keyEvent);
    }

    private void c() {
        LogUtil.a("WifiDevicePressureCalibrateResultActivity", "initData()");
        if (this.g) {
            this.d.setVisibility(0);
            this.f9869a.setVisibility(8);
            this.e.setVisibility(8);
            this.k.setVisibility(0);
            this.o.setVisibility(0);
            this.b.setText(this.m);
            this.k.setText(String.format(this.c.getResources().getString(R$string.IDS_hw_show_card_pressure_measure_grade), psm.e().d(b(CommonUtil.m(this.c, this.m)))));
            this.o.setText(c(b(CommonUtil.m(this.c, this.m))));
            this.n.setText(this.c.getResources().getString(R$string.IDS_hw_show_card_pressure_calibrate_result_complete));
            return;
        }
        this.d.setVisibility(8);
        this.f9869a.setVisibility(0);
        this.i.setVisibility(0);
        this.e.setVisibility(0);
        this.k.setVisibility(8);
        this.o.setVisibility(8);
        this.i.setText(this.c.getResources().getString(R$string.IDS_hw_show_card_pressure_calibrate_answer_fail_text));
        this.e.setText(new SpannableStringBuilder(this.c.getResources().getString(R$string.IDS_device_mgr_not_found_device_tips1) + System.lineSeparator() + String.format(nsn.d(this.c, R$string.IDS_device_wifi_pressure_calibrate_result_fail_tips1), UnitUtil.e(1.0d, 1, 0)) + System.lineSeparator() + String.format(this.c.getResources().getString(R$string.IDS_device_wifi_pressure_calibrate_result_fail_tips2), UnitUtil.e(2.0d, 1, 0)) + System.lineSeparator() + System.lineSeparator() + this.c.getResources().getString(R$string.IDS_device_wifi_pressure_calibrate_result_fail_tips3)));
        this.n.setText(this.c.getResources().getString(R$string.IDS_hw_show_card_pressure_measure_again));
    }

    private int b(int i) {
        if (i >= 1 && i <= 29) {
            return 1;
        }
        if (i >= 30 && i <= 59) {
            return 2;
        }
        if (i >= 60 && i <= 79) {
            return 3;
        }
        if (i >= 80 && i <= 99) {
            return 4;
        }
        LogUtil.a("WifiDevicePressureCalibrateResultActivity", "checkPressureLevel() err");
        return 0;
    }

    private String c(int i) {
        if (i == 1) {
            return this.c.getResources().getString(R$string.IDS_hw_show_card_pressure_measure_calibrate_grade_text_1);
        }
        if (i == 2) {
            return this.c.getResources().getString(R$string.IDS_hw_show_card_pressure_measure_calibrate_grade_text_2);
        }
        if (i == 3) {
            return this.c.getResources().getString(R$string.IDS_hw_show_card_pressure_measure_calibrate_grade_text_3);
        }
        if (i == 4) {
            return this.c.getResources().getString(R$string.IDS_hw_show_card_pressure_measure_calibrate_grade_text_4);
        }
        LogUtil.a("WifiDevicePressureCalibrateResultActivity", "getPressureKnowledge() err");
        return "";
    }

    private void a() {
        Intent intent = new Intent(this.c, (Class<?>) WifiDevicePressureCalibrateGuideActivity.class);
        intent.putExtra("health_wifi_device_userId", this.t);
        intent.putExtra("health_wifi_device_productId", this.l);
        gnm.aPB_(this.c, intent);
        finish();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
