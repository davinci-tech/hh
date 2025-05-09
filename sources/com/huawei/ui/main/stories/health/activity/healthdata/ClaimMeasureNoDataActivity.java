package com.huawei.ui.main.stories.health.activity.healthdata;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.device.wifi.control.claim.ClaimWeightDataManager;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import defpackage.nrt;
import defpackage.nsf;
import health.compact.a.LanguageUtil;

/* loaded from: classes.dex */
public class ClaimMeasureNoDataActivity extends BaseActivity {
    private Context b;
    private ImageView d;
    private CustomTitleBar e;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_claim_measure_nodata_layout);
        LogUtil.a("PluginDevice_ClaimMeasureNoDataActivity", "onCreate...");
        this.b = BaseApplication.getContext();
        b();
    }

    private void b() {
        Drawable drawable;
        this.e = (CustomTitleBar) findViewById(R.id.custom_title_bar_weight_measure);
        this.d = (ImageView) findViewById(R.id.weight_measure_nodata_icon);
        if (LanguageUtil.bc(this.b)) {
            drawable = this.b.getResources().getDrawable(R.drawable._2131428443_res_0x7f0b045b);
        } else {
            drawable = this.b.getResources().getDrawable(R.drawable._2131428438_res_0x7f0b0456);
        }
        this.e.setLeftButtonDrawable(drawable, nsf.h(R$string.accessibility_go_back));
        this.e.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.ClaimMeasureNoDataActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ClaimMeasureNoDataActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        e();
    }

    private void e() {
        if (nrt.a(this.b)) {
            this.d.setImageDrawable(getResources().getDrawable(R.drawable._2131429992_res_0x7f0b0a68));
        } else {
            this.d.setImageDrawable(getResources().getDrawable(R.drawable._2131429991_res_0x7f0b0a67));
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        ClaimWeightDataManager.INSTANCE.startSync();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
