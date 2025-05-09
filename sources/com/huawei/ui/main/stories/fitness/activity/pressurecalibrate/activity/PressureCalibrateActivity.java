package com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.arkuix.utils.ArkUIXConstants;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.activity.PressureCalibrateActivity;
import com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.fragment.PressureCalibrateQuestionActivity;
import com.huawei.ui.main.stories.health.activity.healthdata.KnitPressureActivity;
import defpackage.dum;
import defpackage.nrf;
import defpackage.nsn;
import defpackage.psm;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes.dex */
public class PressureCalibrateActivity extends BaseActivity {
    private int b;
    private boolean c;
    private boolean d;
    private boolean e;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_pressure_calibrate_main);
        psm.e().c(true);
        Intent intent = getIntent();
        if (intent != null) {
            this.e = intent.getBooleanExtra("pressure_is_have_datas", false);
            this.d = intent.getBooleanExtra("press_auto_monitor", false);
            this.c = intent.getBooleanExtra("from_health_record", false);
            this.b = intent.getIntExtra(ArkUIXConstants.FROM_TYPE, 0);
            psm.e().b(this.e);
        }
        LogUtil.a("PressureMeasureMessage", "mFromHealthRecord is ", Boolean.valueOf(this.c));
        long elapsedRealtime = SystemClock.elapsedRealtime();
        e();
        if (dum.d() != null) {
            ReleaseLogUtil.e("R_PressureMeasureMessage", "getInstance of Mediator success");
        }
        ReleaseLogUtil.e("R_PressureMeasureMessage", "onCreate finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    private void e() {
        a();
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.hw_pressure_calibrate_title_layout);
        customTitleBar.setLeftButtonClickable(true);
        customTitleBar.setLeftButtonClickable(true);
        customTitleBar.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: pso
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PressureCalibrateActivity.this.dsU_(view);
            }
        });
        ((HealthButton) findViewById(R.id.hw_pressure_calibrate_start_btn)).setOnClickListener(new View.OnClickListener() { // from class: psn
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PressureCalibrateActivity.this.dsV_(view);
            }
        });
        ((HealthTextView) findViewById(R.id.hw_pressure_calibrate_notify_text_click)).setText(String.format(getString(R$string.IDS_hw_show_card_pressure_calibrate_notify_textcontent), 60));
    }

    public /* synthetic */ void dsU_(View view) {
        c();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dsV_(View view) {
        if (nsn.o()) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        ReleaseLogUtil.e("R_PressureMeasureMessage", "TO PressureCalibrateResultActivity time = ", Long.valueOf(System.currentTimeMillis()));
        a(PressureCalibrateQuestionActivity.class);
        finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void a() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.hw_pressure_calibrate_iv_layout);
        ImageView imageView = (ImageView) findViewById(R.id.hw_pressure_calibrate_iv);
        ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
        layoutParams.height = nsn.j() / 3;
        linearLayout.setLayoutParams(layoutParams);
        imageView.getLayoutParams().height = linearLayout.getLayoutParams().height;
        imageView.getLayoutParams().width = linearLayout.getLayoutParams().width;
        imageView.setImageBitmap(nrf.cJK_(nrf.cHN_(R.drawable._2131428704_res_0x7f0b0560, BaseApplication.getContext()), imageView));
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            c();
            return false;
        }
        return super.onKeyDown(i, keyEvent);
    }

    private void c() {
        if (this.d || this.c || this.b == 1) {
            finish();
        } else {
            a(KnitPressureActivity.class);
            finish();
        }
    }

    private void a(Class cls) {
        Intent intent = new Intent(this, (Class<?>) cls);
        intent.putExtra("pressure_is_have_datas", this.e);
        intent.putExtra("press_auto_monitor", this.d);
        intent.putExtra("from_health_record", this.c);
        intent.putExtra(ArkUIXConstants.FROM_TYPE, this.b);
        LogUtil.a("PressureMeasureMessage", "go to Question have data = ", Boolean.valueOf(this.e));
        startActivity(intent);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
