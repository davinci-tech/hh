package com.huawei.ui.device.activity.bloodpressure;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.CompoundButton;
import com.huawei.basichealth.bloodpressure.BloodPressureSyncManager;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import defpackage.ixx;
import defpackage.jqi;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class BloodPressureSettingActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private HealthSwitchButton f9074a;
    private CompoundButton.OnCheckedChangeListener b = new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.device.activity.bloodpressure.BloodPressureSettingActivity.4
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            LogUtil.a("BloodPressureSettingActivity", "blood pressure switch button is ", Boolean.valueOf(z));
            BloodPressureSettingActivity.this.f9074a.setChecked(z);
            BloodPressureSettingActivity.this.a(z);
            ViewClickInstrumentation.clickOnView(compoundButton);
        }
    };
    private jqi e;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_blood_pressure_setting);
        this.e = jqi.a();
        this.f9074a = (HealthSwitchButton) findViewById(R.id.blood_switch_btn);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        e();
    }

    private void e() {
        this.e.getSwitchSetting("blood_pressure_remind", new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.bloodpressure.BloodPressureSettingActivity.5
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("BloodPressureSettingActivity", "CORE_SLEEP_BUTTON errorCode = ", Integer.valueOf(i));
                final boolean equals = (i == 0 && (obj instanceof String)) ? "1".equals((String) obj) : false;
                LogUtil.a("BloodPressureSettingActivity", "getBloodBtStatus():", Boolean.valueOf(equals));
                BloodPressureSettingActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.bloodpressure.BloodPressureSettingActivity.5.3
                    @Override // java.lang.Runnable
                    public void run() {
                        BloodPressureSettingActivity.this.f9074a.setChecked(equals);
                        BloodPressureSettingActivity.this.f9074a.setOnCheckedChangeListener(BloodPressureSettingActivity.this.b);
                        BloodPressureSettingActivity.this.e(equals);
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final boolean z) {
        this.e.setSwitchSetting("blood_pressure_remind", Integer.toString(z ? 1 : 0), new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.bloodpressure.BloodPressureSettingActivity.2
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("BloodPressureSettingActivity", "setBloodPressureSwitchToCloud errorCode: ", Integer.valueOf(i));
                if (i == 0) {
                    BloodPressureSettingActivity.this.e(z);
                }
            }
        });
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        hashMap.put("status", (z ? 1 : 0) + "");
        String value = AnalyticsValue.SETTING_2090015.value();
        ixx.d().d(BaseApplication.getContext(), value, hashMap, 0);
        LogUtil.a("BloodPressureSettingActivity", "BI biBloodPressureSwitch click event finish, value: ", value, "typeMap: ", hashMap.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(boolean z) {
        LogUtil.a("BloodPressureSettingActivity", "send BloodPressure switch status command:", Boolean.valueOf(z));
        BloodPressureSyncManager.c().b(z ? 1 : 0);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
