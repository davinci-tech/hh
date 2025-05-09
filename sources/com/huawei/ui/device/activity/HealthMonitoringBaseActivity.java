package com.huawei.ui.device.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Parcelable;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import defpackage.jpt;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LocalBroadcast;

/* loaded from: classes.dex */
public abstract class HealthMonitoringBaseActivity extends BaseActivity {
    private static final String KEY_EXTRA_DEVICE = "deviceinfo";
    private static final String TAG = "HealthMonitoringBaseActivity";
    private Context mContext;
    protected String mDeviceMac = null;
    private final BroadcastReceiver mStateChangedBroadcastReceiver = new BroadcastReceiver() { // from class: com.huawei.ui.device.activity.HealthMonitoringBaseActivity.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h(HealthMonitoringBaseActivity.TAG, "mStateChangedBroadcastReceiver intent is null");
                return;
            }
            LogUtil.a(HealthMonitoringBaseActivity.TAG, "mStateChangedBroadcastReceiver intent = ", intent.getAction());
            try {
                Parcelable parcelableExtra = intent.getParcelableExtra(HealthMonitoringBaseActivity.KEY_EXTRA_DEVICE);
                if (parcelableExtra instanceof DeviceInfo) {
                    HealthMonitoringBaseActivity.this.connectedStateChanged((DeviceInfo) parcelableExtra);
                } else {
                    LogUtil.h(HealthMonitoringBaseActivity.TAG, "mStateChangedBroadcastReceiver parcelable not instanceof DeviceInfo");
                }
            } catch (ClassCastException unused) {
                LogUtil.b(HealthMonitoringBaseActivity.TAG, "mStateChangedBroadcastReceiver ClassCastException");
            }
        }
    };

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = this;
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        String stringExtra = intent.getStringExtra("device_id");
        this.mDeviceMac = stringExtra;
        if (stringExtra == null) {
            LogUtil.a(TAG, "onCreate mDeviceMac is null");
            DeviceInfo d = jpt.d(TAG);
            if (d != null) {
                this.mDeviceMac = d.getDeviceIdentify();
            }
        }
        registerConnectionChangeReceiver();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connectedStateChanged(DeviceInfo deviceInfo) {
        String str;
        if (deviceInfo == null || (str = this.mDeviceMac) == null) {
            LogUtil.h(TAG, "connectedStateChanged deviceInfo or mDeviceMac is null");
        } else if (!str.equalsIgnoreCase(deviceInfo.getDeviceIdentify())) {
            LogUtil.h(TAG, "connectedStateChanged device mac is not equals");
        } else if (deviceInfo.getDeviceConnectState() != 2) {
            finish();
        }
    }

    private void registerConnectionChangeReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        try {
            BroadcastManagerUtil.bFC_(this.mContext, this.mStateChangedBroadcastReceiver, intentFilter, LocalBroadcast.c, null);
        } catch (IllegalArgumentException unused) {
            LogUtil.b(TAG, "registerConnectionChangeReceiver IllegalArgumentException");
        }
    }

    private void unRegisterConnectionChangeReceiver() {
        try {
            this.mContext.unregisterReceiver(this.mStateChangedBroadcastReceiver);
        } catch (IllegalArgumentException unused) {
            LogUtil.b(TAG, "unRegisterConnectionChangeReceiver IllegalArgumentException");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        unRegisterConnectionChangeReceiver();
    }
}
