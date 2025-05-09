package com.huawei.ui.device.activity.update;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.ixx;
import defpackage.jkj;
import defpackage.jkk;
import defpackage.jqi;
import defpackage.nrh;
import defpackage.nsy;
import defpackage.oaf;
import defpackage.oau;
import health.compact.a.Utils;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class UpdateSettingActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private boolean f9252a;
    private HealthSwitchButton b;
    private LinearLayout c;
    private CustomTitleBar d;
    private boolean e;
    private LinearLayout f;
    private String g;
    private String h;
    private boolean i;
    private HealthTextView j;
    private HealthSwitchButton k;
    private HealthTextView l;
    private HealthTextView m;
    private RelativeLayout n;
    private HealthTextView o;
    private HealthSwitchButton p;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("UpdateSettingActivity", "onCreate");
        setContentView(R.layout.activity_device_update_setting);
        d();
        e();
    }

    private void d() {
        Intent intent = getIntent();
        if (intent != null) {
            this.e = intent.getBooleanExtra("device_support_wlan", false);
            this.f9252a = intent.getBooleanExtra("device_support_auto", false);
            this.i = intent.getBooleanExtra("device_support_wlan_transmit", false);
            this.h = intent.getStringExtra("device_unique");
            this.g = intent.getStringExtra("device_id");
        }
        LogUtil.a("UpdateSettingActivity", "mIsSupportWlan :", Boolean.valueOf(this.e), "mIsSupportAuto :", Boolean.valueOf(this.f9252a));
    }

    private void e() {
        CustomTitleBar customTitleBar = (CustomTitleBar) nsy.cMc_(this, R.id.update_setting_title);
        this.d = customTitleBar;
        customTitleBar.setTitleText(BaseApplication.getContext().getResources().getString(R.string._2130841425_res_0x7f020f51));
        this.d.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.update.UpdateSettingActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                UpdateSettingActivity.this.onBackPressed();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        g();
        h();
        f();
    }

    private void f() {
        this.c = (LinearLayout) nsy.cMc_(this, R.id.update_setting_auto);
        HealthSwitchButton healthSwitchButton = (HealthSwitchButton) nsy.cMc_(this, R.id.update_setting_auto_switch);
        this.b = healthSwitchButton;
        healthSwitchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.device.activity.update.UpdateSettingActivity.5
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                int i;
                LogUtil.a("UpdateSettingActivity", "auto switch isChecked :", Boolean.valueOf(z));
                HashMap hashMap = new HashMap(16);
                if (UpdateSettingActivity.this.j()) {
                    if (z) {
                        jqi.a().setSwitchSetting("auto_update_status", "true", UpdateSettingActivity.this.h, null);
                        hashMap.put("type", "1");
                        i = 23;
                    } else {
                        jqi.a().setSwitchSetting("auto_update_status", "false", UpdateSettingActivity.this.h, null);
                        hashMap.put("type", "0");
                        i = 24;
                    }
                    oaf.b(BaseApplication.getContext()).c(UpdateSettingActivity.this.g, z);
                    hashMap.put("click", 1);
                    ixx.d().d(BaseApplication.getContext(), AnalyticsValue.SETTING_1090037.value(), hashMap, 0);
                    jkk.d().a(UpdateSettingActivity.this.g, i);
                    ViewClickInstrumentation.clickOnView(compoundButton);
                    return;
                }
                nrh.b(BaseApplication.getContext(), R.string._2130843052_res_0x7f0215ac);
                ViewClickInstrumentation.clickOnView(compoundButton);
            }
        });
        if (this.f9252a) {
            this.c.setVisibility(0);
            b();
        }
    }

    private void h() {
        this.f = (LinearLayout) nsy.cMc_(this, R.id.update_setting_transmit);
        this.k = (HealthSwitchButton) nsy.cMc_(this, R.id.update_setting_transmit_switch);
        this.l = (HealthTextView) nsy.cMc_(this, R.id.update_setting_transmit_title);
        this.j = (HealthTextView) nsy.cMc_(this, R.id.update_setting_transmit_content);
        this.k.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.device.activity.update.UpdateSettingActivity.4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                LogUtil.a("UpdateSettingActivity", "transmit switch isChecked :", Boolean.valueOf(z));
                if (UpdateSettingActivity.this.j()) {
                    oau.a(z, UpdateSettingActivity.this.g);
                    ViewClickInstrumentation.clickOnView(compoundButton);
                } else {
                    nrh.b(BaseApplication.getContext(), R.string._2130843052_res_0x7f0215ac);
                    ViewClickInstrumentation.clickOnView(compoundButton);
                }
            }
        });
        if (Utils.o()) {
            this.l.setText(R.string.IDS_device_transmit_text_overseas);
            this.j.setText(R.string.IDS_device_transmit_tips_overseas);
        } else {
            this.l.setText(R.string.IDS_device_transmit_text);
            this.j.setText(R.string.IDS_device_transmit_tips);
        }
        if (this.i) {
            this.f.setVisibility(0);
            c();
        }
    }

    private void c() {
        jqi.a().getSwitchSetting("auto_open_wlan_status", this.g, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.update.UpdateSettingActivity.2
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("UpdateSettingActivity", "getTransmitSwitch errorCode = ", Integer.valueOf(i));
                if (i == 0 && (obj instanceof String)) {
                    if (TextUtils.equals("1", String.valueOf(obj))) {
                        UpdateSettingActivity.this.b("auto_open_wlan_status", true);
                    } else {
                        UpdateSettingActivity.this.b("auto_open_wlan_status", false);
                    }
                }
            }
        });
    }

    private void g() {
        this.n = (RelativeLayout) nsy.cMc_(this, R.id.update_setting_wlan);
        this.m = (HealthTextView) nsy.cMc_(this, R.id.update_setting_wlan_content);
        this.o = (HealthTextView) nsy.cMc_(this, R.id.update_setting_wlan_only_content);
        if (Utils.o()) {
            this.m.setText(R.string.IDS_wlan_auto_update_device_new_overseas);
            this.o.setText(R.string.IDS_device_only_update_overseas);
        } else {
            this.m.setText(R.string.IDS_wlan_auto_update_device_new);
            this.o.setText(R.string.IDS_device_only_update);
        }
        HealthSwitchButton healthSwitchButton = (HealthSwitchButton) nsy.cMc_(this, R.id.update_setting_wlan_switch);
        this.p = healthSwitchButton;
        healthSwitchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.device.activity.update.UpdateSettingActivity.1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                int i;
                LogUtil.a("UpdateSettingActivity", "wlan switch isChecked :", Boolean.valueOf(z));
                HashMap hashMap = new HashMap(16);
                if (z) {
                    jqi.a().setSwitchSetting("wlan_auto_update", "1", null);
                    hashMap.put("type", "1");
                    i = 21;
                } else {
                    jqi.a().setSwitchSetting("wlan_auto_update", "2", null);
                    hashMap.put("type", "0");
                    i = 22;
                }
                hashMap.put("click", 1);
                ixx.d().d(BaseApplication.getContext(), AnalyticsValue.SETTING_1090006.value(), hashMap, 0);
                jkk.d().a(UpdateSettingActivity.this.g, i);
                ViewClickInstrumentation.clickOnView(compoundButton);
            }
        });
        if (this.e) {
            this.n.setVisibility(0);
            a();
        }
    }

    private void a() {
        jqi.a().getSwitchSetting("wlan_auto_update", new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.update.UpdateSettingActivity.10
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("UpdateSettingActivity", "getWlanSwitchStatus errorCode = ", Integer.valueOf(i));
                boolean z = i == 0 && (obj instanceof String) && !TextUtils.equals("2", (String) obj);
                LogUtil.a("UpdateSettingActivity", "getSwitchStatus status :", Boolean.valueOf(z));
                if (z) {
                    UpdateSettingActivity.this.b("wlan_auto_update", true);
                } else {
                    UpdateSettingActivity.this.b("wlan_auto_update", false);
                }
            }
        });
    }

    private void b() {
        jqi.a().getSwitchSetting("auto_update_status", this.h, new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.update.UpdateSettingActivity.8
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("UpdateSettingActivity", "getAutoSwitchStatus errorCode = ", Integer.valueOf(i));
                if (i == 0 && (obj instanceof String)) {
                    if (TextUtils.equals("true", String.valueOf(obj))) {
                        UpdateSettingActivity.this.b("auto_update_status", true);
                    } else {
                        UpdateSettingActivity.this.b("auto_update_status", false);
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final String str, final boolean z) {
        runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.update.UpdateSettingActivity.7
            @Override // java.lang.Runnable
            public void run() {
                char c;
                String str2 = str;
                str2.hashCode();
                int hashCode = str2.hashCode();
                if (hashCode == 105734552) {
                    if (str2.equals("auto_update_status")) {
                        c = 0;
                    }
                    c = 65535;
                } else if (hashCode != 312087338) {
                    if (hashCode == 1760488028 && str2.equals("wlan_auto_update")) {
                        c = 2;
                    }
                    c = 65535;
                } else {
                    if (str2.equals("auto_open_wlan_status")) {
                        c = 1;
                    }
                    c = 65535;
                }
                if (c == 0) {
                    UpdateSettingActivity.this.b.setChecked(z);
                    return;
                }
                if (c == 1) {
                    UpdateSettingActivity.this.k.setChecked(z);
                } else if (c == 2) {
                    UpdateSettingActivity.this.p.setChecked(z);
                } else {
                    LogUtil.h("UpdateSettingActivity", "updateUiState error");
                }
            }
        });
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        HealthSwitchButton healthSwitchButton;
        LogUtil.a("UpdateSettingActivity", "onBackPressed()");
        if (this.f9252a && (healthSwitchButton = this.b) != null) {
            if (healthSwitchButton.isChecked()) {
                setResult(1000);
            } else {
                setResult(2000);
            }
        }
        super.onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean j() {
        DeviceInfo e = jkj.d().e(this.g);
        if (e != null && e.getDeviceConnectState() == 2) {
            return true;
        }
        LogUtil.a("UpdateSettingActivity", "device disconnected");
        return false;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
