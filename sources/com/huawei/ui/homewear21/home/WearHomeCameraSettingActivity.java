package com.huawei.ui.homewear21.home;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.CompoundButton;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwdevice.mainprocess.mgr.settingcamera.CameraAuthStatusCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import defpackage.joj;

/* loaded from: classes6.dex */
public class WearHomeCameraSettingActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private boolean f9646a = false;
    private CameraAuthStatusCallback b = new CameraAuthStatusCallback() { // from class: com.huawei.ui.homewear21.home.WearHomeCameraSettingActivity.4
        @Override // com.huawei.hwdevice.mainprocess.mgr.settingcamera.CameraAuthStatusCallback
        public void authStatus(Boolean bool) {
            if (!String.valueOf(WearHomeCameraSettingActivity.this.c.isChecked()).equals(String.valueOf(bool))) {
                WearHomeCameraSettingActivity.this.f9646a = true;
            }
            WearHomeCameraSettingActivity.this.c.setChecked(bool.booleanValue());
        }
    };
    private HealthSwitchButton c;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.wear_home_other_settings_camera_layout);
        a();
    }

    private void a() {
        HealthSwitchButton healthSwitchButton = (HealthSwitchButton) findViewById(R.id.setting_camera_switch_button);
        this.c = healthSwitchButton;
        healthSwitchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.homewear21.home.WearHomeCameraSettingActivity.2
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (WearHomeCameraSettingActivity.this.f9646a) {
                    WearHomeCameraSettingActivity.this.f9646a = false;
                } else {
                    joj.a().c(z);
                }
                ViewClickInstrumentation.clickOnView(compoundButton);
            }
        });
    }

    private void c() {
        LogUtil.a("WearHomeCameraSettingActivity", "enter refreshSwitchStatus");
        joj.a().e(this.b);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        c();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        joj.a().b();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
