package com.huawei.ui.homewear21.home;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.widget.CompoundButton;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.jwe;
import defpackage.nsy;
import defpackage.oxa;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LocalBroadcast;

/* loaded from: classes6.dex */
public class SmartGestureActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {

    /* renamed from: a, reason: collision with root package name */
    private final BroadcastReceiver f9642a = new BroadcastReceiver() { // from class: com.huawei.ui.homewear21.home.SmartGestureActivity.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtil.a("SmartGestureActivity", "mNonLocalBroadcastReceiver()  intent : " + intent.getAction());
            if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                Parcelable parcelableExtra = intent.getParcelableExtra("deviceinfo");
                if (parcelableExtra == null || !(parcelableExtra instanceof DeviceInfo)) {
                    LogUtil.a("SmartGestureActivity", "mNonLocalBroadcastReceiver()  parcelable is error ");
                    return;
                }
                DeviceInfo deviceInfo = (DeviceInfo) parcelableExtra;
                if (deviceInfo == null) {
                    LogUtil.a("SmartGestureActivity", "mNonLocalBroadcastReceiver()  deviceInfo is null ");
                    return;
                }
                int deviceConnectState = deviceInfo.getDeviceConnectState();
                LogUtil.a("SmartGestureActivity", "mNonLocalBroadcastReceiver() deviceConnectState is ", Integer.valueOf(deviceConnectState));
                if (deviceConnectState == 2) {
                    SmartGestureActivity.this.b.setClickable(true);
                    return;
                } else {
                    SmartGestureActivity.this.b.setClickable(false);
                    return;
                }
            }
            LogUtil.a("SmartGestureActivity", "other action");
        }
    };
    private HealthSwitchButton b;
    private String c;
    protected CustomTitleBar d;
    private DeviceInfo e;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_smart_gesture);
        Intent intent = getIntent();
        if (intent != null) {
            this.c = intent.getStringExtra("device_id");
        } else {
            LogUtil.a("SmartGestureActivity", "intent is null");
        }
        if (TextUtils.isEmpty(this.c)) {
            this.e = oxa.a().f();
            LogUtil.a("SmartGestureActivity", "mDeviceMac is empty");
        } else {
            this.e = oxa.a().b(this.c);
        }
        this.d = (CustomTitleBar) nsy.cMc_(this, R.id.smart_gestures_bar);
        this.b = (HealthSwitchButton) nsy.cMc_(this, R.id.switch_smart_gestures_recognition);
        this.d.setTitleText(BaseApplication.getContext().getString(R.string._2130847542_res_0x7f022736));
        d();
        if (this.e == null) {
            this.b.setClickable(false);
            LogUtil.a("SmartGestureActivity", "mDeviceInfo is null");
            return;
        }
        final jwe e = jwe.e();
        boolean d = e.d();
        LogUtil.a("SmartGestureActivity", "isSmartGestureOpen is ", Boolean.valueOf(d));
        this.b.setChecked(d);
        if (this.e.getDeviceConnectState() != 2) {
            this.b.setClickable(false);
            LogUtil.a("SmartGestureActivity", "device do not connect.");
        } else {
            LogUtil.a("SmartGestureActivity", "device  is connected.");
            this.b.setClickable(true);
        }
        this.b.setOnCheckedChangeListener(this);
        e.d(new IBaseResponseCallback() { // from class: com.huawei.ui.homewear21.home.SmartGestureActivity.2
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("SmartGestureActivity", "setGestureSwitchCallback errorCode is ", Integer.valueOf(i));
                SmartGestureActivity.this.b.setChecked(e.d());
            }
        });
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        LogUtil.a("SmartGestureActivity", "isChecked: ", Boolean.valueOf(z));
        if (compoundButton.getId() == R.id.switch_smart_gestures_recognition) {
            jwe.e().d(this.e, z);
        } else {
            LogUtil.a("SmartGestureActivity", "onCheckedChanged default");
        }
        ViewClickInstrumentation.clickOnView(compoundButton);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        a();
    }

    private void d() {
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
            BroadcastManagerUtil.bFA_(this, this.f9642a, intentFilter, LocalBroadcast.c, null);
        } catch (IllegalStateException unused) {
            LogUtil.b("SmartGestureActivity", "registerBroadcast IllegalStateException");
        }
    }

    private void a() {
        try {
            BroadcastManagerUtil.bFJ_(this, this.f9642a);
        } catch (IllegalStateException unused) {
            LogUtil.b("SmartGestureActivity", "unregisterBroadcast IllegalStateException");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
