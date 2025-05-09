package com.huawei.ui.main.stories.fitness.activity.bloodoxygen.detailactivity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.ui.TipsFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.main.stories.fitness.activity.bloodoxygen.dayfragment.BloodOxygenHorizontalFragment;
import com.huawei.ui.main.stories.fitness.activity.bloodoxygen.dayfragment.BloodOxygenHorizontalTipsFragment;
import com.huawei.ui.main.stories.fitness.activity.bloodoxygen.detailactivity.HorizontalBloodOxygenDayActivity;
import defpackage.koq;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;

/* loaded from: classes9.dex */
public class HorizontalBloodOxygenDayActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private Context f9756a;
    private TipsFragment b = null;
    private BloodOxygenHorizontalFragment c = new BloodOxygenHorizontalFragment();

    private int b() {
        return R.layout.horizontal_bloodoxygen_chart;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRequestedOrientation(0);
        setContentView(b());
        this.f9756a = BaseApplication.getContext();
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        if (koq.b(supportFragmentManager.getFragments())) {
            FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
            beginTransaction.add(R.id.fragment_container, this.c);
            beginTransaction.commit();
        }
        if (c()) {
            return;
        }
        e();
        d(true);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void setTahitiModelOrientation() {
        setRequestedOrientation(0);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void setDefaultOrientation() {
        setRequestedOrientation(0);
    }

    private void e() {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        TipsFragment tipsFragment = this.b;
        if (tipsFragment != null) {
            beginTransaction.remove(tipsFragment);
            this.b = null;
        }
        BloodOxygenHorizontalTipsFragment bloodOxygenHorizontalTipsFragment = new BloodOxygenHorizontalTipsFragment();
        this.b = bloodOxygenHorizontalTipsFragment;
        bloodOxygenHorizontalTipsFragment.setOnNextClickListener(new View.OnClickListener() { // from class: pkm
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HorizontalBloodOxygenDayActivity.this.dqy_(view);
            }
        });
        this.b.setOnCloseClickListener(new View.OnClickListener() { // from class: pkj
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HorizontalBloodOxygenDayActivity.this.dqz_(view);
            }
        });
        beginTransaction.add(R.id.fragment_container, this.b);
        beginTransaction.commit();
    }

    public /* synthetic */ void dqy_(View view) {
        if (this.b != null) {
            a();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dqz_(View view) {
        if (this.b != null) {
            a();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void a() {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        TipsFragment tipsFragment = this.b;
        if (tipsFragment != null) {
            beginTransaction.remove(tipsFragment);
            this.b = null;
        }
        beginTransaction.commitAllowingStateLoss();
    }

    private boolean c() {
        String b = SharedPreferenceManager.b(this.f9756a, String.valueOf(10006), "blood_oxygen_horizontal_chart_tips_shown");
        if (TextUtils.isEmpty(b)) {
            return false;
        }
        return Boolean.parseBoolean(b);
    }

    private void d(boolean z) {
        SharedPreferenceManager.e(this.f9756a, String.valueOf(10006), "blood_oxygen_horizontal_chart_tips_shown", Boolean.toString(z), new StorageParams());
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
        LogUtil.a("HorizontalBloodOxygenDayActivity", "onConfigurationChanged");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (27 <= Build.VERSION.SDK_INT) {
            setRequestedOrientation(0);
        }
    }
}
