package com.huawei.ui.main.stories.me.activity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import androidx.core.content.ContextCompat;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.me.activity.PrivacyTrainingStatisticsActivity;
import defpackage.nsy;
import defpackage.rvo;
import defpackage.sck;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.Locale;

/* loaded from: classes.dex */
public class PrivacyTrainingStatisticsActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {

    /* renamed from: a, reason: collision with root package name */
    private CustomTitleBar f10346a;
    private boolean b;
    private HealthTextView c;
    private HealthSwitchButton d;
    private Context e;
    private int f = -1;
    private rvo g;
    private HealthSwitchButton h;
    private HealthTextView i;
    private boolean j;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.e = this;
        e();
        d();
    }

    private void e() {
        setContentView(R.layout.activity_privacy_training_statistics);
        CustomTitleBar customTitleBar = (CustomTitleBar) nsy.cMc_(this, R.id.training_statistics_set_titlebar);
        this.f10346a = customTitleBar;
        customTitleBar.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: rgr
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PrivacyTrainingStatisticsActivity.this.dNW_(view);
            }
        });
        this.h = (HealthSwitchButton) nsy.cMc_(this, R.id.sport_statistics_switch);
        this.i = (HealthTextView) nsy.cMc_(this, R.id.sport_statistics_text);
        this.d = (HealthSwitchButton) nsy.cMc_(this, R.id.health_statistics_switch);
        this.c = (HealthTextView) nsy.cMc_(this, R.id.health_statistics_text);
        this.h.setOnCheckedChangeListener(this);
        this.d.setOnCheckedChangeListener(this);
    }

    public /* synthetic */ void dNW_(View view) {
        LogUtil.a("PrivacyTrainingStatisticsActivity", "initView out the page");
        finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void d() {
        this.g = rvo.e(getApplicationContext());
        b();
        c();
    }

    private void c() {
        boolean equals = "true".equals(this.g.a(7));
        boolean equals2 = "true".equals(this.g.a(203));
        e(equals);
        boolean z = equals && equals2;
        this.b = z;
        this.d.setChecked(z);
    }

    private void b() {
        boolean equals = "true".equals(this.g.a(3));
        boolean equals2 = "true".equals(this.g.a(202));
        f(equals);
        boolean z = equals && equals2;
        this.j = z;
        this.h.setChecked(z);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
        LogUtil.a("PrivacyTrainingStatisticsActivity", "onConfigurationChanged");
        sck.b(this.e);
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (compoundButton == null) {
            LogUtil.h("PrivacyTrainingStatisticsActivity", "onCheckedChanged buttonView is null");
            ViewClickInstrumentation.clickOnView(compoundButton);
            return;
        }
        LogUtil.a("PrivacyTrainingStatisticsActivity", "onCheckedChanged isChecked() = ", Boolean.valueOf(z));
        int id = compoundButton.getId();
        this.f = id;
        if (id == R.id.sport_statistics_switch) {
            d(z);
        } else if (id == R.id.health_statistics_switch) {
            b(z);
        } else {
            LogUtil.h("PrivacyTrainingStatisticsActivity", "handleOtherSwitch else");
        }
        ViewClickInstrumentation.clickOnView(compoundButton);
    }

    private void d(boolean z) {
        if (z) {
            c(this.e.getString(R$string.IDS_privacy_sport_stats_open));
        } else {
            j(z);
            if (this.j) {
                a(false);
                a("0");
            }
        }
        this.j = true;
    }

    private void b(boolean z) {
        if (z) {
            c(this.e.getString(R$string.IDS_privacy_health_stats_con));
        } else {
            j(z);
            if (this.b) {
                a(false);
                a("0");
            }
        }
        this.b = true;
    }

    private void a(boolean z) {
        int i;
        int i2 = this.f;
        if (i2 == R.id.sport_statistics_switch) {
            i = 202;
        } else if (i2 == R.id.health_statistics_switch) {
            i = 203;
        } else {
            LogUtil.h("PrivacyTrainingStatisticsActivity", "savePersonalPrivacySettings else");
            i = -1;
        }
        LogUtil.a("PrivacyTrainingStatisticsActivity", "privacyId = ", Integer.valueOf(i), ", isOpen = ", Boolean.valueOf(z));
        if (i != -1) {
            this.g.e(i, z);
        }
    }

    private void c(String str) {
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this).e(str).czE_(this.e.getString(R$string.IDS_settings_button_ok).toUpperCase(Locale.getDefault()), new View.OnClickListener() { // from class: rgu
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PrivacyTrainingStatisticsActivity.this.dNX_(view);
            }
        }).czA_(this.e.getString(R$string.IDS_settings_button_cancal).toUpperCase(Locale.getDefault()), new View.OnClickListener() { // from class: rgs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PrivacyTrainingStatisticsActivity.this.dNY_(view);
            }
        }).e();
        e.setCancelable(false);
        e.show();
    }

    public /* synthetic */ void dNX_(View view) {
        a(true);
        a("1");
        j(true);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dNY_(View view) {
        c(false);
        j(false);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void j(boolean z) {
        int i = this.f;
        if (i == R.id.sport_statistics_switch) {
            this.h.setChecked(z);
        } else if (i == R.id.health_statistics_switch) {
            this.d.setChecked(z);
        } else {
            LogUtil.h("PrivacyTrainingStatisticsActivity", "setSwitchButtonCheck else");
        }
    }

    private void a(String str) {
        int i = this.f;
        if (i == R.id.sport_statistics_switch) {
            e(str);
            LogUtil.a("PrivacyTrainingStatisticsActivity", "handleUserOpenStatistics setUserCloseSportStatistics ", str);
        } else if (i == R.id.health_statistics_switch) {
            d(str);
            LogUtil.a("PrivacyTrainingStatisticsActivity", "handleUserOpenStatistics setUserCloseHealthStatistics ", str);
        } else {
            LogUtil.h("PrivacyTrainingStatisticsActivity", "handleUserOpenStatistics else");
        }
    }

    private void c(boolean z) {
        int i = this.f;
        if (i == R.id.sport_statistics_switch) {
            this.j = z;
        } else if (i == R.id.health_statistics_switch) {
            this.b = z;
        } else {
            LogUtil.h("PrivacyTrainingStatisticsActivity", "saveInitSatus else");
        }
    }

    private void f(boolean z) {
        this.h.setClickable(z);
        this.h.setEnabled(z);
        this.i.setTextColor(ContextCompat.getColor(this, z ? R.color._2131299236_res_0x7f090ba4 : R.color._2131299241_res_0x7f090ba9));
    }

    private void e(boolean z) {
        this.d.setClickable(z);
        this.d.setEnabled(z);
        this.c.setTextColor(ContextCompat.getColor(this, z ? R.color._2131299236_res_0x7f090ba4 : R.color._2131299241_res_0x7f090ba9));
    }

    private void e(String str) {
        SharedPreferenceManager.e(this.e, Integer.toString(10000), "key_user_close_training", str, new StorageParams());
    }

    private void d(String str) {
        SharedPreferenceManager.e(this.e, Integer.toString(10000), "key_user_close_health_training_statistics", str, new StorageParams());
    }
}
