package com.huawei.ui.main.stories.fitness.activity.pressure.activity;

import android.content.Context;
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
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.stories.health.activity.healthdata.KnitPressureActivity;
import defpackage.dum;
import defpackage.nrf;
import defpackage.nsn;
import defpackage.psm;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes.dex */
public class PressureMeasureActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private int f9863a;
    private LinearLayout b;
    private ImageView c;
    private Context d;
    private CustomTitleBar e;
    private Intent g;
    private HealthButton h;
    private boolean j;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_pressure_measure_main);
        psm.e().j(true);
        Intent intent = getIntent();
        this.g = intent;
        if (intent != null) {
            this.j = intent.getBooleanExtra("pressure_is_have_datas", false);
            this.f9863a = this.g.getIntExtra(ArkUIXConstants.FROM_TYPE, 0);
            psm.e().b(this.j);
            LogUtil.a("PressureMeasureMessage", "mIsHaveDatas == ", Boolean.valueOf(this.j));
        }
        this.d = this;
        this.e = (CustomTitleBar) findViewById(R.id.hw_pressure_measure_title_layout);
        this.h = (HealthButton) findViewById(R.id.hw_pressure_measure_start_btn);
        this.c = (ImageView) findViewById(R.id.hw_pressure_measure_iv);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.hw_pressure_measure_img_layout);
        this.b = linearLayout;
        ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
        layoutParams.height = nsn.j() / 3;
        this.b.setLayoutParams(layoutParams);
        this.c.getLayoutParams().height = this.b.getLayoutParams().height;
        this.c.getLayoutParams().width = this.b.getLayoutParams().width;
        this.c.setImageBitmap(nrf.cJK_(nrf.cHN_(R.drawable._2131428704_res_0x7f0b0560, BaseApplication.getContext()), this.c));
        long elapsedRealtime = SystemClock.elapsedRealtime();
        a();
        if (dum.d() != null) {
            ReleaseLogUtil.e("R_PressureMeasureMessage", "getInstance of Mediator success");
        }
        ReleaseLogUtil.e("R_PressureMeasureMessage", "onCreate finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    private void a() {
        this.h.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.activity.pressure.activity.PressureMeasureActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (nsn.o()) {
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                LogUtil.a("PressureMeasureMessage", "TO PressureMeasureResultActivity time = ", Long.valueOf(System.currentTimeMillis()));
                PressureMeasureActivity.this.g = new Intent(PressureMeasureActivity.this.d, (Class<?>) PressureMeasureResultActivity.class);
                PressureMeasureActivity.this.g.putExtra("isOpenMeasure", true);
                PressureMeasureActivity.this.g.putExtra("pressure_is_have_datas", PressureMeasureActivity.this.j);
                PressureMeasureActivity.this.g.putExtra(ArkUIXConstants.FROM_TYPE, PressureMeasureActivity.this.f9863a);
                PressureMeasureActivity pressureMeasureActivity = PressureMeasureActivity.this;
                pressureMeasureActivity.startActivity(pressureMeasureActivity.g);
                PressureMeasureActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.e.setLeftButtonClickable(true);
        this.e.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.activity.pressure.activity.PressureMeasureActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PressureMeasureActivity.this.c();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (this.f9863a == 1) {
            finish();
            return;
        }
        Intent intent = new Intent(this.d, (Class<?>) KnitPressureActivity.class);
        this.g = intent;
        intent.putExtra("pressure_is_have_datas", this.j);
        startActivity(this.g);
        finish();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            c();
            return false;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
