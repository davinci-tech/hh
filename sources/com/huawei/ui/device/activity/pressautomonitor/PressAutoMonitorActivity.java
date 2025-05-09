package com.huawei.ui.device.activity.pressautomonitor;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.CompoundButton;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.health.constants.ObserveLabels;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwversionmgr.manager.HwVersionManager;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.base.CommonUiBaseResponse;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.device.interactors.DeviceSettingsInteractors;
import com.huawei.ui.main.stories.fitness.activity.pressurecalibrate.activity.PressureCalibrateActivity;
import com.huawei.ui.main.stories.health.activity.healthdata.KnitPressureActivity;
import defpackage.gge;
import defpackage.ixx;
import defpackage.jpt;
import defpackage.jqi;
import defpackage.nlg;
import defpackage.nsy;
import defpackage.pwr;
import defpackage.qba;
import health.compact.a.CommonUtil;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.HashMap;

/* loaded from: classes.dex */
public class PressAutoMonitorActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private DeviceSettingsInteractors f9202a;
    private jqi b;
    private Context c;
    private CustomTitleBar d;
    private boolean f;
    private boolean h;
    private boolean i;
    private boolean j;
    private HealthSwitchButton k;
    private pwr o;
    private Handler e = new c(this);
    private CompoundButton.OnCheckedChangeListener g = new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.device.activity.pressautomonitor.PressAutoMonitorActivity.4
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (!z) {
                nlg.c(PressAutoMonitorActivity.this.c, "pressure_auto_detector_agree_no_again_tip", "pressure_auto_detector_count");
            }
            ReleaseLogUtil.e("DEVMGR_PressAutoMonitorActivity", "press auto monitor isChecked: ", Boolean.valueOf(z));
            PressAutoMonitorActivity.this.k.setEnabled(false);
            PressAutoMonitorActivity.this.i = z;
            HashMap hashMap = new HashMap(0);
            int i = z ? 1 : 2;
            if (PressAutoMonitorActivity.this.f || !z) {
                if (PressAutoMonitorActivity.this.f9202a == null) {
                    PressAutoMonitorActivity.this.f9202a = DeviceSettingsInteractors.d(BaseApplication.getContext());
                }
                if (PressAutoMonitorActivity.this.f9202a != null) {
                    PressAutoMonitorActivity.this.f9202a.e(i);
                    PressAutoMonitorActivity.this.f9202a.a(PressAutoMonitorActivity.this.i);
                    ObserverManagerUtil.c(ObserveLabels.EMOTION_SWITCH_CHANGED, new Object[0]);
                    PressAutoMonitorActivity.this.e(z);
                    hashMap.put("click", "1");
                    hashMap.put("status", z ? "1" : "0");
                    ixx.d().d(BaseApplication.getContext(), AnalyticsValue.PRESS_AUTO_SWITCH.value(), hashMap, 0);
                    LogUtil.a("PressAutoMonitorActivity", "biPressSwitch click event finish, value: ", AnalyticsValue.PRESS_AUTO_SWITCH.value(), "biSwitchStatusMap: ", hashMap.toString());
                    ViewClickInstrumentation.clickOnView(compoundButton);
                    return;
                }
                LogUtil.h("PressAutoMonitorActivity", "onCheckedChange mDeviceSettingsInteractors is null");
                ViewClickInstrumentation.clickOnView(compoundButton);
                return;
            }
            PressAutoMonitorActivity.this.e();
            hashMap.put("click", "0");
            ixx.d().d(BaseApplication.getContext(), AnalyticsValue.PRESS_AUTO_SWITCH.value(), hashMap, 0);
            ViewClickInstrumentation.clickOnView(compoundButton);
        }
    };

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_settings_press_auto_monitor);
        this.c = this;
        this.b = jqi.a();
        d();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LogUtil.c("PressAutoMonitorActivity", "onDestroy()");
        setResult(0, null);
        this.k = null;
        CommonUtil.a(this.c);
    }

    private void d() {
        Intent intent = getIntent();
        this.h = false;
        if (intent != null) {
            LogUtil.a("PressAutoMonitorActivity", "enter intent != null");
            this.j = intent.getBooleanExtra("pressure_is_have_datas", false);
            this.h = intent.getBooleanExtra("from_card", false);
        }
        LogUtil.a("PressAutoMonitorActivity", "mIsFromCard: ", Boolean.valueOf(this.h), " mIsHavedDatas: ", Boolean.valueOf(this.j));
        this.f9202a = DeviceSettingsInteractors.d(BaseApplication.getContext());
        this.b.getSwitchSetting("press_auto_monitor_switch_status", new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.pressautomonitor.PressAutoMonitorActivity.2
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("PressAutoMonitorActivity", "initData errorCode = ", Integer.valueOf(i), " ; objectData = ", obj);
                PressAutoMonitorActivity.this.i = (i == 0 && (obj instanceof String)) ? !"false".equals((String) obj) : false;
                PressAutoMonitorActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.pressautomonitor.PressAutoMonitorActivity.2.5
                    @Override // java.lang.Runnable
                    public void run() {
                        PressAutoMonitorActivity.this.b();
                    }
                });
            }
        });
        c(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        CustomTitleBar customTitleBar = (CustomTitleBar) nsy.cMc_(this, R.id.setting_device_title_bar);
        this.d = customTitleBar;
        if (customTitleBar == null) {
            LogUtil.h("PressAutoMonitorActivity", "initView mDeviceTitleBar is null");
            return;
        }
        customTitleBar.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.pressautomonitor.PressAutoMonitorActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PressAutoMonitorActivity.this.onBackPressed();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.k = (HealthSwitchButton) nsy.cMc_(this, R.id.press_switch_button);
        HealthTextView healthTextView = (HealthTextView) nsy.cMc_(this, R.id.textview_relax_number);
        this.k.setChecked(this.i);
        this.k.setOnCheckedChangeListener(this.g);
        healthTextView.setText(this.c.getResources().getString(R.string._2130843088_res_0x7f0215d0, UnitUtil.e(1.0d, 1, 0), UnitUtil.e(29.0d, 1, 0)));
        ((HealthTextView) nsy.cMc_(this, R.id.textview_normal_number)).setText(this.c.getResources().getString(R.string._2130843088_res_0x7f0215d0, UnitUtil.e(30.0d, 1, 0), UnitUtil.e(59.0d, 1, 0)));
        ((HealthTextView) nsy.cMc_(this, R.id.textview_middle_number)).setText(this.c.getResources().getString(R.string._2130843088_res_0x7f0215d0, UnitUtil.e(60.0d, 1, 0), UnitUtil.e(79.0d, 1, 0)));
        ((HealthTextView) nsy.cMc_(this, R.id.textview_hight_number)).setText(this.c.getResources().getString(R.string._2130843088_res_0x7f0215d0, UnitUtil.e(80.0d, 1, 0), UnitUtil.e(99.0d, 1, 0)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this);
        builder.b(getResources().getString(R.string._2130837885_res_0x7f02017d));
        builder.e(getResources().getString(R.string._2130843323_res_0x7f0216bb));
        builder.cyS_(getResources().getString(R.string._2130841130_res_0x7f020e2a), new View.OnClickListener() { // from class: com.huawei.ui.device.activity.pressautomonitor.PressAutoMonitorActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ReleaseLogUtil.e("DEVMGR_PressAutoMonitorActivity", "Users did not check.");
                PressAutoMonitorActivity.this.e(false);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.cyV_(getResources().getString(R.string._2130843371_res_0x7f0216eb), new View.OnClickListener() { // from class: com.huawei.ui.device.activity.pressautomonitor.PressAutoMonitorActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ReleaseLogUtil.e("DEVMGR_PressAutoMonitorActivity", "Jump to Pressure Calibration Interface");
                DeviceInfo d = jpt.d("PressAutoMonitorActivity");
                if (d == null || !HwVersionManager.c(BaseApplication.getContext()).o(d.getDeviceIdentify())) {
                    PressAutoMonitorActivity.this.e.removeMessages(0);
                    PressAutoMonitorActivity.this.k.setEnabled(false);
                    PressAutoMonitorActivity.this.k.setChecked(PressAutoMonitorActivity.this.i);
                    HashMap hashMap = new HashMap(0);
                    hashMap.put("click", 1);
                    hashMap.put("havedevice", 1);
                    gge.e(AnalyticsValue.HEALTH_PRESSUER_ADJUST_CLICK_2160005.value(), hashMap);
                    Intent intent = new Intent(PressAutoMonitorActivity.this.c, (Class<?>) PressureCalibrateActivity.class);
                    intent.putExtra("press_auto_monitor", true);
                    PressAutoMonitorActivity.this.startActivityForResult(intent, 10);
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                LogUtil.a("PressAutoMonitorActivity", "Current equipment upgrades");
                qba.a(PressAutoMonitorActivity.this.c);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        a2.setCancelable(false);
        a2.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(boolean z) {
        this.i = z;
        this.e.sendEmptyMessageDelayed(0, 300L);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        LogUtil.a("PressAutoMonitorActivity", "onActivityResult");
        HealthSwitchButton healthSwitchButton = this.k;
        if (healthSwitchButton != null) {
            healthSwitchButton.setEnabled(false);
        }
        c(2);
    }

    private void c(final int i) {
        ReleaseLogUtil.e("DEVMGR_PressAutoMonitorActivity", "getCalibratedStatus");
        if (this.o == null) {
            this.o = new pwr();
        }
        LogUtil.a("PressAutoMonitorActivity", "isAlreadyDoPressureAdjust");
        this.o.e(new CommonUiBaseResponse() { // from class: com.huawei.ui.device.activity.pressautomonitor.PressAutoMonitorActivity.10
            @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
            public void onResponse(int i2, Object obj) {
                if (i2 != 0 || obj == null) {
                    PressAutoMonitorActivity.this.f = false;
                    LogUtil.a("PressAutoMonitorActivity", " errCode " + i2 + " mIsCalibrated false");
                } else {
                    LogUtil.a("PressAutoMonitorActivity", "errCode " + i2 + " objData " + obj);
                    PressAutoMonitorActivity.this.f = ((Boolean) obj).booleanValue();
                    StringBuilder sb = new StringBuilder("mIsCalibrated ==");
                    sb.append(PressAutoMonitorActivity.this.f);
                    LogUtil.a("PressAutoMonitorActivity", sb.toString());
                }
                ReleaseLogUtil.e("DEVMGR_PressAutoMonitorActivity", "getCalibratedStatus mIsCalibrated :", Boolean.valueOf(PressAutoMonitorActivity.this.f));
                if (i == 2) {
                    PressAutoMonitorActivity pressAutoMonitorActivity = PressAutoMonitorActivity.this;
                    pressAutoMonitorActivity.e(pressAutoMonitorActivity.f);
                    if (PressAutoMonitorActivity.this.f) {
                        if (PressAutoMonitorActivity.this.f9202a == null) {
                            PressAutoMonitorActivity.this.f9202a = DeviceSettingsInteractors.d(BaseApplication.getContext());
                        }
                        if (PressAutoMonitorActivity.this.f9202a != null) {
                            PressAutoMonitorActivity.this.f9202a.e(1);
                            PressAutoMonitorActivity.this.f9202a.a(PressAutoMonitorActivity.this.i);
                            HashMap hashMap = new HashMap(0);
                            hashMap.put("status", "1");
                            ixx.d().d(BaseApplication.getContext(), AnalyticsValue.PRESS_AUTO_SWITCH.value(), hashMap, 0);
                            return;
                        }
                        LogUtil.h("PressAutoMonitorActivity", "isAlreadyDoPressureAdjust mDeviceSettingsInteractors is null");
                    }
                }
            }
        });
    }

    /* loaded from: classes6.dex */
    class c extends Handler {
        WeakReference<PressAutoMonitorActivity> d;

        c(PressAutoMonitorActivity pressAutoMonitorActivity) {
            this.d = new WeakReference<>(pressAutoMonitorActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (this.d.get() == null) {
                return;
            }
            removeMessages(0);
            if (PressAutoMonitorActivity.this.k != null) {
                PressAutoMonitorActivity.this.k.setEnabled(true);
                LogUtil.a("PressAutoMonitorActivity", "mHandler mIsPressAutoMonitorFlag = " + PressAutoMonitorActivity.this.i);
                PressAutoMonitorActivity.this.k.setChecked(PressAutoMonitorActivity.this.i);
            }
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        LogUtil.a("PressAutoMonitorActivity", "mIsFromCard : " + this.h + "mIsHavedDatas : " + this.j);
        if (this.h) {
            if (this.j) {
                e(KnitPressureActivity.class);
                return;
            }
            if (this.o == null) {
                this.o = new pwr();
            }
            this.o.e(new CommonUiBaseResponse() { // from class: com.huawei.ui.device.activity.pressautomonitor.PressAutoMonitorActivity.7
                @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
                public void onResponse(int i, Object obj) {
                    if (i != 0 || obj == null) {
                        PressAutoMonitorActivity.this.f = false;
                        LogUtil.a("PressAutoMonitorActivity", "errCode " + i + " false ");
                    } else {
                        if (obj instanceof Boolean) {
                            PressAutoMonitorActivity.this.f = ((Boolean) obj).booleanValue();
                        }
                        LogUtil.a("PressAutoMonitorActivity", "errCode " + i + " objData " + obj);
                    }
                    PressAutoMonitorActivity.this.e(KnitPressureActivity.class);
                }
            });
            return;
        }
        super.onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(Class cls) {
        Intent intent = new Intent(this.c, (Class<?>) cls);
        LogUtil.a("PressAutoMonitorActivity", "to card activity = " + this.j);
        startActivity(intent);
        finish();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
