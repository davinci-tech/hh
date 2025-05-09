package com.huawei.ui.main.stories.me.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.CompoundButton;
import com.huawei.haf.bundle.AppBundle;
import com.huawei.haf.bundle.AppBundleSetting;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;

/* loaded from: classes7.dex */
public class PluginSettingActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {

    /* renamed from: a, reason: collision with root package name */
    private final AppBundleSetting f10343a = AppBundle.c().getSetting();
    private HealthSwitchButton c;
    private HealthSwitchButton e;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_plugin_setting);
        HealthSwitchButton healthSwitchButton = (HealthSwitchButton) findViewById(R.id.auto_update_plugin_switch_button);
        this.e = healthSwitchButton;
        healthSwitchButton.setChecked(this.f10343a.isAllowAutoUpdateModule());
        this.e.setOnCheckedChangeListener(this);
        HealthSwitchButton healthSwitchButton2 = (HealthSwitchButton) findViewById(R.id.mobil_update_plugin_switch_button);
        this.c = healthSwitchButton2;
        healthSwitchButton2.setChecked(this.f10343a.isAllowUsingMobileUpdateModule());
        this.c.setOnCheckedChangeListener(this);
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (compoundButton == this.e) {
            this.f10343a.setAllowAutoUpdateModule(z);
            LogUtil.a("Bundle_PluginSettingActivity", "mAutoUpdateButton is ", Boolean.valueOf(z));
            ViewClickInstrumentation.clickOnView(compoundButton);
        } else {
            if (compoundButton == this.c) {
                this.f10343a.setAllowUsingMobileUpdateModule(z);
                LogUtil.a("Bundle_PluginSettingActivity", "mMobileUpdateButton is ", Boolean.valueOf(z));
                ViewClickInstrumentation.clickOnView(compoundButton);
                return;
            }
            ViewClickInstrumentation.clickOnView(compoundButton);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
