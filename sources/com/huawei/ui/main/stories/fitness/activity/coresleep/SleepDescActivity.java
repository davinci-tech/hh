package com.huawei.ui.main.stories.fitness.activity.coresleep;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.stories.fitness.activity.coresleep.SleepDescActivity;
import defpackage.bzs;
import defpackage.efb;
import defpackage.nsn;
import health.compact.a.LanguageUtil;

/* loaded from: classes6.dex */
public class SleepDescActivity extends BaseActivity {
    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_sleep_desc);
        d();
    }

    private void d() {
        View findViewById = findViewById(R.id.sleep_record_wear_layout);
        HealthDivider healthDivider = (HealthDivider) findViewById(R.id.divider);
        View findViewById2 = findViewById(R.id.sleep_record_phone_layout);
        if (!efb.b(BaseApplication.getContext())) {
            healthDivider.setVisibility(8);
            findViewById2.setVisibility(8);
        }
        HealthDivider healthDivider2 = (HealthDivider) findViewById(R.id.divider_manual);
        View findViewById3 = findViewById(R.id.sleep_manual_layout);
        if (!efb.b()) {
            healthDivider2.setVisibility(8);
            findViewById3.setVisibility(8);
        }
        ImageView imageView = (ImageView) findViewById(R.id.sleep_record_wear_arrow);
        ImageView imageView2 = (ImageView) findViewById(R.id.sleep_record_phone_arrow);
        ImageView imageView3 = (ImageView) findViewById(R.id.sleep_manual_arrow);
        if (LanguageUtil.bc(this)) {
            imageView.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
            imageView2.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
            imageView3.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
        } else {
            imageView.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
            imageView2.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
            imageView3.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
        }
        if (nsn.r()) {
            HealthTextView healthTextView = (HealthTextView) findViewById(R.id.sleep_record_detection_wear_text);
            HealthTextView healthTextView2 = (HealthTextView) findViewById(R.id.sleep_record_detection_phone_text);
            HealthTextView healthTextView3 = (HealthTextView) findViewById(R.id.sleep_manual_text);
            healthTextView.setMaxEms(7);
            nsn.b(healthTextView);
            nsn.b(healthTextView2);
            nsn.b(healthTextView3);
        }
        findViewById.setOnClickListener(new View.OnClickListener() { // from class: pmq
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SleepDescActivity.this.drd_(view);
            }
        });
        findViewById2.setOnClickListener(new View.OnClickListener() { // from class: pmo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SleepDescActivity.this.dre_(view);
            }
        });
        findViewById3.setOnClickListener(new View.OnClickListener() { // from class: pmv
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SleepDescActivity.this.drf_(view);
            }
        });
    }

    public /* synthetic */ void drd_(View view) {
        startActivity(new Intent(this, (Class<?>) SuggestActivity.class));
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dre_(View view) {
        bzs.e().loadH5ProApp(this, "com.huawei.health.h5.sleepdetection", new H5ProLaunchOption.Builder().addPath("#/aboutPage").addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi")).setForceDarkMode(1).setImmerse().showStatusBar().setStatusBarTextBlack(true));
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void drf_(View view) {
        bzs.e().loadH5ProApp(this, "com.huawei.health.h5.sleepdetection", new H5ProLaunchOption.Builder().addPath("#/inputAboutPage").addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi")).setForceDarkMode(1).setImmerse().showStatusBar().setStatusBarTextBlack(true));
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
