package com.huawei.ui.device.activity.update;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import com.huawei.haf.bundle.extension.ComponentInfo;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwdevice.mainprocess.mgr.hwotamanager.HwOtaUpgradeManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwversionmgr.manager.HwVersionManager;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.bwf;
import defpackage.cvs;
import defpackage.cwc;
import defpackage.cwi;
import defpackage.ixx;
import defpackage.jkj;
import defpackage.jpt;
import defpackage.jqi;
import defpackage.kxz;
import defpackage.nrr;
import defpackage.nsn;
import defpackage.oaf;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.Utils;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;

/* loaded from: classes6.dex */
public class WearDeviceUpdateDialogActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private HealthCheckBox f9265a;
    private HealthCheckBox b;
    private LinearLayout c;
    private LinearLayout d;
    private oaf e;
    private View f;
    private String i;
    private String j;
    private jqi l;
    private LayoutInflater m;
    private boolean n;
    private HealthTextView q;
    private LinearLayout r;
    private HealthTextView s;
    private Context g = null;
    private boolean k = false;
    private String o = null;
    private final BroadcastReceiver h = new BroadcastReceiver() { // from class: com.huawei.ui.device.activity.update.WearDeviceUpdateDialogActivity.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h("WearDeviceUpdateDialogActivity", "mDeviceStatusReceiver intent is null");
                return;
            }
            String action = intent.getAction();
            if (action == null) {
                LogUtil.h("WearDeviceUpdateDialogActivity", "mDeviceStatusReceiver action is null");
                return;
            }
            if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(action)) {
                LogUtil.a("WearDeviceUpdateDialogActivity", "mDeviceStatusReceiver ACTION_CONNECTION_STATE_CHANGED");
                Parcelable parcelableExtra = intent.getParcelableExtra("deviceinfo");
                DeviceInfo deviceInfo = parcelableExtra instanceof DeviceInfo ? (DeviceInfo) parcelableExtra : null;
                if (deviceInfo != null && deviceInfo.getDeviceIdentify() != null && !deviceInfo.getDeviceIdentify().equals(WearDeviceUpdateDialogActivity.this.o)) {
                    LogUtil.h("WearDeviceUpdateDialogActivity", "mDeviceStatusReceiver deviceInfo changed");
                    jkj.d().b(true);
                    WearDeviceUpdateDialogActivity.this.finish();
                    return;
                }
                LogUtil.h("WearDeviceUpdateDialogActivity", "mDeviceStatusReceiver other");
            }
        }
    };

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.g = this;
        LogUtil.a("WearDeviceUpdateDialogActivity", "onCreate()");
        this.e = oaf.b(this.g);
        this.l = jqi.a();
        Window window = getWindow();
        window.setGravity(80);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -2;
        onWindowAttributesChanged(attributes);
        a();
        e();
        setFinishOnTouchOutside(false);
        d();
        overridePendingTransition(R.anim._2130772071_res_0x7f010067, 0);
    }

    private void d() {
        BroadcastManagerUtil.bFC_(this.g, this.h, new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED"), LocalBroadcast.c, null);
    }

    private void f() {
        try {
            LogUtil.a("WearDeviceUpdateDialogActivity", "Enter unregisterBindDeviceBroadcast()!");
            unregisterReceiver(this.h);
        } catch (IllegalArgumentException e) {
            LogUtil.b("WearDeviceUpdateDialogActivity", "unregisterBindDeviceBroadcast failed:", ExceptionUtils.d(e));
        }
    }

    private void e() {
        Intent intent = getIntent();
        if (intent != null) {
            this.i = intent.getStringExtra("show_device_name");
            String stringExtra = intent.getStringExtra("mac");
            this.o = stringExtra;
            LogUtil.a("WearDeviceUpdateDialogActivity", "initUpdateMode mMac is ", CommonUtil.l(stringExtra));
            DeviceInfo e = jpt.e(this.o, "WearDeviceUpdateDialogActivity");
            if (e == null) {
                LogUtil.h("WearDeviceUpdateDialogActivity", "initUpdateMode deviceInfo is null");
                finish();
                return;
            }
            this.i = e.getDeviceName();
            this.n = cwi.c(e, 108) && !Utils.l();
            this.j = e.getDeviceUdid();
            boolean booleanExtra = intent.getBooleanExtra(ParamConstants.CallbackMethod.ON_SHOW, false);
            this.k = booleanExtra;
            LogUtil.a("WearDeviceUpdateDialogActivity", "initUpdateMode, mIsShowBox = ", Boolean.valueOf(booleanExtra));
            a(intent.getStringExtra("name"), intent.getStringExtra("type"));
            h();
        }
    }

    private void h() {
        jqi.a().getSwitchSetting("wlan_auto_update", new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.update.WearDeviceUpdateDialogActivity.3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("WearDeviceUpdateDialogActivity", "initUpdateMode errorCode = ", Integer.valueOf(i), " objectData ", obj);
                if (i != 0 || !(obj instanceof String)) {
                    WearDeviceUpdateDialogActivity.this.c(false);
                } else {
                    WearDeviceUpdateDialogActivity.this.c(!"2".equals((String) obj));
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final boolean z) {
        runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.update.WearDeviceUpdateDialogActivity.5
            @Override // java.lang.Runnable
            public void run() {
                if (z) {
                    WearDeviceUpdateDialogActivity.this.d.setVisibility(8);
                    WearDeviceUpdateDialogActivity.this.b.setChecked(true);
                } else if (!WearDeviceUpdateDialogActivity.this.k) {
                    WearDeviceUpdateDialogActivity.this.d.setVisibility(8);
                } else {
                    if (!Utils.o()) {
                        WearDeviceUpdateDialogActivity.this.b.setChecked(true);
                    } else {
                        WearDeviceUpdateDialogActivity.this.b.setChecked(false);
                    }
                    WearDeviceUpdateDialogActivity.this.d.setVisibility(0);
                }
                WearDeviceUpdateDialogActivity.this.j();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        DeviceCapability e = cvs.e(this.o);
        if (e == null || !e.getIsSupportAutoUpdate() || this.n) {
            LogUtil.h("WearDeviceUpdateDialogActivity", "setAutoUpdateStatus autoInstall is not support");
            this.c.setVisibility(8);
            i();
            return;
        }
        LogUtil.a("WearDeviceUpdateDialogActivity", "enter autoInstall");
        DeviceInfo e2 = jpt.e(this.o, "WearDeviceUpdateDialogActivity");
        if (e2 == null) {
            LogUtil.h("WearDeviceUpdateDialogActivity", "setAutoUpdateStatus currentDeviceInfo == null");
            i();
        } else {
            jqi.a().getSwitchSetting("auto_update_status", e2.getDeviceUdid(), new IBaseResponseCallback() { // from class: com.huawei.ui.device.activity.update.WearDeviceUpdateDialogActivity.1
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a("WearDeviceUpdateDialogActivity", "getAutoSwitchStatus errorCode = ", Integer.valueOf(i));
                    if (i != 0 || !(obj instanceof String)) {
                        WearDeviceUpdateDialogActivity.this.b("false");
                    } else {
                        WearDeviceUpdateDialogActivity.this.b(String.valueOf(obj));
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final String str) {
        runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.activity.update.WearDeviceUpdateDialogActivity.4
            @Override // java.lang.Runnable
            public void run() {
                if (TextUtils.equals("true", str)) {
                    WearDeviceUpdateDialogActivity.this.c.setVisibility(8);
                } else {
                    if (!Utils.o()) {
                        WearDeviceUpdateDialogActivity.this.f9265a.setChecked(true);
                    } else {
                        WearDeviceUpdateDialogActivity.this.f9265a.setChecked(false);
                    }
                    WearDeviceUpdateDialogActivity.this.c.setVisibility(0);
                }
                WearDeviceUpdateDialogActivity.this.i();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        LogUtil.h("WearDeviceUpdateDialogActivity", "showDialog Entering");
        if (nsn.s() && (this.r.getLayoutParams() instanceof LinearLayout.LayoutParams)) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.r.getLayoutParams();
            layoutParams.height = nrr.e(this, 300.0f);
            this.r.setLayoutParams(layoutParams);
        }
        CustomAlertDialog c = new CustomAlertDialog.Builder(this, R.style.UpdateVersionCustomDialog).e(R.string._2130841460_res_0x7f020f74).cyp_(this.f).cyn_(R.string._2130841855_res_0x7f0210ff, new DialogInterface.OnClickListener() { // from class: com.huawei.ui.device.activity.update.WearDeviceUpdateDialogActivity.9
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                WearDeviceUpdateDialogActivity.this.finish();
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        }).cyo_(R.string._2130841856_res_0x7f021100, new DialogInterface.OnClickListener() { // from class: com.huawei.ui.device.activity.update.WearDeviceUpdateDialogActivity.6
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (!nsn.o()) {
                    WearDeviceUpdateDialogActivity.this.c();
                    WearDeviceUpdateDialogActivity.this.b();
                    WearDeviceUpdateDialogActivity.this.b(true);
                    ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
                    return;
                }
                LogUtil.h("WearDeviceUpdateDialogActivity", "user choose update return");
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        }).c();
        if (c == null || c.isShowing()) {
            return;
        }
        c.setCancelable(false);
        c.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        DeviceInfo e;
        if (this.d.getVisibility() == 0) {
            HashMap hashMap = new HashMap(16);
            if (this.b.isChecked()) {
                this.l.setSwitchSetting("wlan_auto_update", "1", null);
                hashMap.put("type", "1");
                d(true);
            } else {
                this.l.setSwitchSetting("wlan_auto_update", "2", null);
                hashMap.put("type", "0");
            }
            hashMap.put("click", 1);
            ixx.d().d(BaseApplication.getContext(), AnalyticsValue.SETTING_1090006.value(), hashMap, 0);
        }
        if (this.c.getVisibility() != 0 || (e = jpt.e(this.o, "WearDeviceUpdateDialogActivity")) == null) {
            return;
        }
        LogUtil.a("WearDeviceUpdateDialogActivity", "enter mCheckAutoInstall");
        HashMap hashMap2 = new HashMap(16);
        String deviceUdid = e.getDeviceUdid();
        if (this.f9265a.isChecked()) {
            oaf.b(BaseApplication.getContext()).c(this.o, true);
            jqi.a().setSwitchSetting("auto_update_status", "true", deviceUdid, null);
            hashMap2.put("type", "1");
        } else {
            oaf.b(BaseApplication.getContext()).c(this.o, false);
            jqi.a().setSwitchSetting("auto_update_status", "false", deviceUdid, null);
            hashMap2.put("type", "0");
        }
        hashMap2.put("click", 1);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.SETTING_1090037.value(), hashMap2, 0);
    }

    private void d(boolean z) {
        if (this.n) {
            HwOtaUpgradeManager.a().d(this.j, String.valueOf(z));
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (kxz.j(this.g, this.o) == null || !TextUtils.isEmpty(kxz.j(this.g, this.o))) {
            return;
        }
        LogUtil.a("WearDeviceUpdateDialogActivity", "onResume() getBandCheckNewVersion is empty!");
        finish();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        CommonUtil.a(this.g);
        super.onDestroy();
        this.g = null;
        if (this.e != null) {
            LogUtil.a("WearDeviceUpdateDialogActivity", "onDestroy updateInteractor release");
            this.e = null;
        }
        f();
        LogUtil.a("WearDeviceUpdateDialogActivity", "onDestroy()");
    }

    private void a() {
        LogUtil.a("WearDeviceUpdateDialogActivity", "Enter initView!");
        if (getSystemService("layout_inflater") instanceof LayoutInflater) {
            this.m = (LayoutInflater) getSystemService("layout_inflater");
        }
        LayoutInflater layoutInflater = this.m;
        if (layoutInflater == null) {
            LogUtil.h("WearDeviceUpdateDialogActivity", "initView mInflater is null.");
            finish();
            return;
        }
        View inflate = layoutInflater.inflate(R.layout.activity_device_update_new_dialog, (ViewGroup) null);
        this.f = inflate;
        if (inflate == null) {
            LogUtil.h("WearDeviceUpdateDialogActivity", "initView view is null.");
            finish();
            return;
        }
        this.s = (HealthTextView) inflate.findViewById(R.id.band_detail);
        this.r = (LinearLayout) this.f.findViewById(R.id.update_dialog_ll);
        this.d = (LinearLayout) this.f.findViewById(R.id.AppUpdateDialog_ota_auto_update_band);
        this.b = (HealthCheckBox) this.f.findViewById(R.id.AppUpdateDialog_checkbox_band);
        this.q = (HealthTextView) this.f.findViewById(R.id.AppUpdateDialog_wlan_auto_update_band);
        if (Utils.o()) {
            this.q.setText(R.string.IDS_wlan_auto_update_device_new_overseas);
        } else {
            this.q.setText(R.string.IDS_wlan_auto_update_device_new);
        }
        this.c = (LinearLayout) this.f.findViewById(R.id.update_dialog_ota_auto_install_band);
        this.f9265a = (HealthCheckBox) this.f.findViewById(R.id.update_dialog_install_checkbox_band);
    }

    private void a(String str, String str2) {
        String format;
        LogUtil.a("WearDeviceUpdateDialogActivity", "Enter showAppNewVersion", str2);
        if (!TextUtils.isEmpty(str2) && str2.equals("2")) {
            format = this.g.getString(R.string._2130845406_res_0x7f021ede);
        } else {
            format = String.format(Locale.ENGLISH, this.g.getString(R.string.IDS_device_update_detail), str, this.i);
        }
        this.s.setText(format);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (this.n) {
            d(this.b.isChecked());
            Intent intent = new Intent();
            intent.putExtra("UNIQUE_ID", this.j);
            intent.setClassName(BaseApplication.getContext().getPackageName(), ComponentInfo.PluginWearAbility_A_0);
            bwf.a().launchActivity(this.g, intent);
            finish();
            return;
        }
        LogUtil.a("WearDeviceUpdateDialogActivity", "enterBandUpdate enter");
        try {
            Intent intent2 = new Intent();
            intent2.setClass(this.g, UpdateVersionActivity.class);
            intent2.putExtra("device_id", this.o);
            this.g.startActivity(intent2);
        } catch (ActivityNotFoundException e) {
            LogUtil.b("WearDeviceUpdateDialogActivity", "enterDeviceOtaActivity e ", ExceptionUtils.d(e));
        }
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        LogUtil.a("WearDeviceUpdateDialogActivity", "enter sendOtaUpdateEvent.");
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(16);
        DeviceInfo e = jpt.e(this.o, "WearDeviceUpdateDialogActivity");
        if (e == null) {
            LogUtil.a("WearDeviceUpdateDialogActivity", "enter sendOtaUpdateEvent decide is null.");
            return;
        }
        int productType = e.getProductType();
        if (productType != -1) {
            linkedHashMap.put(DeviceCategoryFragment.DEVICE_TYPE, String.valueOf(productType));
            linkedHashMap.put("device_classfication", String.valueOf(cwc.b(productType)));
        }
        if (z) {
            LogUtil.a("WearDeviceUpdateDialogActivity", "sendOtaUpdateEvent isSelectAutoUpdate::", Boolean.valueOf(this.b.isChecked()));
            if (this.b.isChecked()) {
                linkedHashMap.put("operationID", "3");
            } else {
                linkedHashMap.put("operationID", "4");
            }
        } else {
            linkedHashMap.put("operationID", "1");
        }
        oaf oafVar = this.e;
        if (oafVar != null) {
            linkedHashMap.put("deviceNewVersion", oafVar.b(this.o));
        }
        linkedHashMap.put("versionID", HwVersionManager.c(this.g).i(this.o));
        OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_DEVICE_OTA_UPDATE_80020004.value(), linkedHashMap);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
