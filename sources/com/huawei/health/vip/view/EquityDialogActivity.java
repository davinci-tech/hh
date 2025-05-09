package com.huawei.health.vip.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;
import androidx.core.content.ContextCompat;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.h5pro.core.H5ProWebView;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.cardview.HealthCardView;
import defpackage.bzs;
import defpackage.nsf;
import defpackage.nsn;

/* loaded from: classes.dex */
public class EquityDialogActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private H5ProWebView f3505a;
    private FrameLayout b;
    private Handler d = new Handler();
    private HealthCardView e;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("EquityDialogActivity", "start EquityRenewalDialogActivity.");
        setBackgroundDrawableResource(R.color._2131296913_res_0x7f090291);
        setContentView(R.layout.activity_equity_dialog_view);
        this.f3505a = (H5ProWebView) findViewById(R.id.blood_pressure_webview);
        this.e = (HealthCardView) findViewById(R.id.blood_pressure_pay_cardview);
        d();
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.blood_pressure_pay_view_background);
        this.b = frameLayout;
        BaseActivity.setViewSafeRegion(true, frameLayout);
        this.b.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.vip.view.EquityDialogActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("EquityDialogActivity", "destroy EquityRenewalDialogActivity.");
                EquityDialogActivity.this.onBackPressed();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.d.postDelayed(new Runnable() { // from class: com.huawei.health.vip.view.EquityDialogActivity.2
            @Override // java.lang.Runnable
            public void run() {
                EquityDialogActivity.this.b();
            }
        }, 50L);
        c();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
        d();
    }

    private void d() {
        if (this.e.getLayoutParams() instanceof FrameLayout.LayoutParams) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.e.getLayoutParams();
            if (nsn.ag(this)) {
                layoutParams.topMargin = nsf.b(R.dimen._2131363100_res_0x7f0a051c);
                layoutParams.bottomMargin = nsf.b(R.dimen._2131363100_res_0x7f0a051c);
                layoutParams.gravity = 17;
                this.e.setRadius(nsf.b(R.dimen._2131363015_res_0x7f0a04c7));
                layoutParams.width = nsf.b(R.dimen._2131363059_res_0x7f0a04f3);
            } else {
                layoutParams.topMargin = nsf.b(R.dimen._2131363029_res_0x7f0a04d5);
                layoutParams.bottomMargin = nsf.b(R.dimen._2131362860_res_0x7f0a042c);
                layoutParams.gravity = 80;
                this.e.setRadius(0.0f);
                this.e.setCardBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color._2131296971_res_0x7f0902cb));
            }
            this.e.setLayoutParams(layoutParams);
        }
    }

    private void c() {
        LogUtil.a("EquityDialogActivity", "START H5 PRO view");
        HandlerExecutor.e(new Runnable() { // from class: com.huawei.health.vip.view.EquityDialogActivity.3
            @Override // java.lang.Runnable
            public void run() {
                bzs.e().loadEmbeddedH5(EquityDialogActivity.this.f3505a, "com.huawei.health.h5.vip", new H5ProLaunchOption.Builder().addPath("#/ExclusiveDoctorDialog?from=2"));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.e, "translationY", 800.0f, 0.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ofFloat);
        animatorSet.setDuration(300L);
        animatorSet.start();
    }

    private void a() {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.e, "translationY", 0.0f, 800.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ofFloat);
        animatorSet.setDuration(300L);
        animatorSet.start();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        a();
        this.d.postDelayed(new Runnable() { // from class: com.huawei.health.vip.view.EquityDialogActivity.1
            @Override // java.lang.Runnable
            public void run() {
                EquityDialogActivity.this.finish();
            }
        }, 200L);
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }
}
