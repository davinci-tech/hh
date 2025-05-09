package com.huawei.ui.device.activity.bloodoxygen;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.device.interactors.DeviceSettingsInteractors;
import com.huawei.ui.main.R$string;
import defpackage.cun;
import defpackage.ixx;
import defpackage.jft;
import defpackage.jqi;
import defpackage.nsy;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes6.dex */
public class HighLandBloodOxygenSettingActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private DeviceInfo f9072a;
    private HealthSwitchButton b;
    private jqi c;
    private CompoundButton.OnCheckedChangeListener d = new AnonymousClass5();
    private DeviceSettingsInteractors e;
    private jft f;

    /* renamed from: com.huawei.ui.device.activity.bloodoxygen.HighLandBloodOxygenSettingActivity$5, reason: invalid class name */
    class AnonymousClass5 implements CompoundButton.OnCheckedChangeListener {
        AnonymousClass5() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, final boolean z) {
            ReleaseLogUtil.e("DEVMGR_HighLandBloodOxygenSettingActivity", "highland blood switch button is ", Boolean.valueOf(z));
            HighLandBloodOxygenSettingActivity.this.c.getSwitchSetting("custom.blood.oxygen.switch", new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.bloodoxygen.HighLandBloodOxygenSettingActivity.5.3
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a("HighLandBloodOxygenSettingActivity", "initBloodOxygenItem errorCode = ", Integer.valueOf(i));
                    String str = (i == 0 && (obj instanceof String)) ? (String) obj : "0";
                    if (z) {
                        SharedPreferenceManager.e(BaseApplication.getContext(), "1005", "custom.blood.oxygen.switch", str, (StorageParams) null);
                    }
                    if ("1".equals(str) && z) {
                        HighLandBloodOxygenSettingActivity.this.b(true);
                    } else if ("0".equals(str) && !z) {
                        HighLandBloodOxygenSettingActivity.this.b(false);
                    } else {
                        HighLandBloodOxygenSettingActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.bloodoxygen.HighLandBloodOxygenSettingActivity.5.3.5
                            @Override // java.lang.Runnable
                            public void run() {
                                HighLandBloodOxygenSettingActivity.this.a(z);
                            }
                        });
                    }
                }
            });
            ViewClickInstrumentation.clickOnView(compoundButton);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        ReleaseLogUtil.e("DEVMGR_HighLandBloodOxygenSettingActivity", "setHighLandBloodOxygen isChecked:", Boolean.valueOf(z));
        final String str = z ? "1" : "0";
        this.c.setSwitchSetting("highland.blood.oxygen.switch", str, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.bloodoxygen.HighLandBloodOxygenSettingActivity.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (i == 0) {
                    HighLandBloodOxygenSettingActivity.this.f.c(HighLandBloodOxygenSettingActivity.this.f9072a, CommonUtil.m(BaseApplication.getContext(), str));
                    HighLandBloodOxygenSettingActivity.this.a(str);
                } else {
                    LogUtil.h("HighLandBloodOxygenSettingActivity", "setSwitchSetting is error");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z, boolean z2) {
        ReleaseLogUtil.e("DEVMGR_HighLandBloodOxygenSettingActivity", "setCycleBloodOxygen isCheck:", Boolean.valueOf(z), ",isHighLandCheck：", Boolean.valueOf(z2));
        String str = z ? "1" : "0";
        if (this.e != null) {
            LogUtil.a("HighLandBloodOxygenSettingActivity", "send BloodOxygen switch status command");
            this.e.b(CommonUtil.m(BaseApplication.getContext(), str));
            b(z2);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_high_land_health_settings);
        d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final boolean z) {
        int i;
        ReleaseLogUtil.e("DEVMGR_HighLandBloodOxygenSettingActivity", "showDialog isHighLandChecked:", Boolean.valueOf(z));
        final boolean z2 = true;
        if (z) {
            i = R$string.IDS_highland_alert_on;
        } else {
            String b = SharedPreferenceManager.b(BaseApplication.getContext(), "1005", "custom.blood.oxygen.switch");
            if ("1".equals(b)) {
                LogUtil.a("HighLandBloodOxygenSettingActivity", "showDialog beforeBloodOxygenStatus:", b);
                b(true, false);
                return;
            } else {
                i = R$string.IDS_highland_alert_off;
                z2 = false;
            }
        }
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this);
        builder.e(i);
        builder.czC_(R$string.IDS_highland_know, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.bloodoxygen.HighLandBloodOxygenSettingActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                HighLandBloodOxygenSettingActivity.this.b(z2, z);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        NoTitleCustomAlertDialog e = builder.e();
        e.setCancelable(false);
        e.show();
    }

    private void d() {
        this.b = (HealthSwitchButton) nsy.cMc_(this, R.id.auto_monitor_button);
        HealthTextView healthTextView = (HealthTextView) nsy.cMc_(this, R.id.highland_tv_sub);
        String format = String.format("%d", 2500);
        healthTextView.setText(getString(R.string._2130845106_res_0x7f021db2, new Object[]{format}));
        ((HealthTextView) nsy.cMc_(this, R.id.highland_content)).setText(getString(R.string._2130845120_res_0x7f021dc0, new Object[]{format}));
        this.f = jft.a();
        this.c = jqi.a();
        this.e = DeviceSettingsInteractors.d(BaseApplication.getContext());
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "HighLandBloodOxygenSettingActivity");
        if (deviceList == null || deviceList.isEmpty()) {
            LogUtil.h("HighLandBloodOxygenSettingActivity", "deviceList is null");
        } else {
            this.f9072a = deviceList.get(0);
            this.c.getSwitchSetting("highland.blood.oxygen.switch", new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.bloodoxygen.HighLandBloodOxygenSettingActivity.2
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a("HighLandBloodOxygenSettingActivity", "highland bloodOxygen errorCode = ", Integer.valueOf(i));
                    final String str = (i == 0 && (obj instanceof String)) ? (String) obj : "0";
                    HighLandBloodOxygenSettingActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.bloodoxygen.HighLandBloodOxygenSettingActivity.2.5
                        @Override // java.lang.Runnable
                        public void run() {
                            HighLandBloodOxygenSettingActivity.this.b.setChecked("1".equals(str));
                            HighLandBloodOxygenSettingActivity.this.b.setOnCheckedChangeListener(HighLandBloodOxygenSettingActivity.this.d);
                        }
                    });
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        hashMap.put("status", str);
        String value = AnalyticsValue.SETTING_2030090.value();
        ixx.d().d(BaseApplication.getContext(), value, hashMap, 0);
        LogUtil.a("HighLandBloodOxygenSettingActivity", "BI biHighLandBloodOxygenSwitch click event finish, value: ", value, "typeMap: ", hashMap.toString());
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
