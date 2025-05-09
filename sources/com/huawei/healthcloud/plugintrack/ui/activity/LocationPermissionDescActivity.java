package com.huawei.healthcloud.plugintrack.ui.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.cardview.HealthCardView;
import defpackage.gso;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes8.dex */
public class LocationPermissionDescActivity extends BaseActivity implements View.OnClickListener {
    private HealthButton b;
    private HealthButton d;

    /* renamed from: a, reason: collision with root package name */
    private int f3650a = 258;
    private int e = -1;
    private float c = -1.0f;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.location_description_layout);
        b();
        e();
    }

    private void e() {
        Intent intent = getIntent();
        if (intent != null) {
            try {
                this.f3650a = intent.getIntExtra("map_tracking_sport_type_sportting", 258);
                this.e = intent.getIntExtra("sport_target_type_sportting", this.e);
                this.c = intent.getFloatExtra("sport_target_value_sportting", this.c);
            } catch (BadParcelableException unused) {
                ReleaseLogUtil.d("LocationPermissionDescActivity", "initData failed.");
            }
        }
    }

    private void b() {
        ((HealthCardView) findViewById(R.id.permission_desc_background)).setBackgroundResource(R.drawable.location_description_page_background);
        ((ImageView) findViewById(R.id.marker_logo)).setBackground(getResources().getDrawable(R.drawable._2131430170_res_0x7f0b0b1a));
        this.d = (HealthButton) findViewById(R.id.reject);
        this.b = (HealthButton) findViewById(R.id.agree);
        this.d.setOnClickListener(this);
        this.b.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.b) {
            finish();
            HandlerExecutor.d(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.LocationPermissionDescActivity.2
                @Override // java.lang.Runnable
                public void run() {
                    gso.e().a(LocationPermissionDescActivity.this.getApplicationContext(), LocationPermissionDescActivity.this.f3650a, LocationPermissionDescActivity.this.e, LocationPermissionDescActivity.this.c);
                }
            }, 100L);
        } else if (view == this.d) {
            finish();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
