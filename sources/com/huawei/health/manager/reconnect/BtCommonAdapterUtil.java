package com.huawei.health.manager.reconnect;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes.dex */
class BtCommonAdapterUtil {
    private static final Object b = new Object();
    private static BtCommonAdapterUtil c;

    /* renamed from: a, reason: collision with root package name */
    private BluetoothAdapter f2795a;
    private BtDeviceHfpStateCallback d = null;
    private BroadcastReceiver e = new BroadcastReceiver() { // from class: com.huawei.health.manager.reconnect.BtCommonAdapterUtil.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || context == null) {
                LogUtil.h("BtCommonAdapterUtil", "context or intent is null");
                return;
            }
            String action = intent.getAction();
            Object[] objArr = new Object[4];
            objArr[0] = "EMUI mHfpStateReceiver action: ";
            objArr[1] = action;
            objArr[2] = ", mBtDeviceHfpStateCallback: ";
            objArr[3] = Boolean.valueOf(BtCommonAdapterUtil.this.d == null);
            ReleaseLogUtil.e("DEVMGR_BtCommonAdapterUtil", objArr);
            if ("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED".equals(action) || "android.bluetooth.input.profile.action.CONNECTION_STATE_CHANGED".equals(action)) {
                BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                if (bluetoothDevice == null) {
                    LogUtil.h("0xA0200006", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BtCommonAdapterUtil", "btDevice is null so return.");
                    return;
                }
                int intExtra = intent.getIntExtra("android.bluetooth.profile.extra.STATE", 0);
                if (BtCommonAdapterUtil.this.d != null && intExtra == 2) {
                    ReleaseLogUtil.e("DEVMGR_BtCommonAdapterUtil", "HFP device connected.");
                    BtCommonAdapterUtil.this.d.onBtDeviceHfpConnected(bluetoothDevice, action);
                }
            }
            BtCommonAdapterUtil.this.ali_(intent, action);
        }
    };

    private BtCommonAdapterUtil() {
        this.f2795a = null;
        this.f2795a = BluetoothAdapter.getDefaultAdapter();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ali_(Intent intent, String str) {
        if ("android.bluetooth.device.action.ACL_CONNECTED".equals(str)) {
            BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
            if (bluetoothDevice == null) {
                LogUtil.h("0xA0200006", HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BtCommonAdapterUtil", "btDevice is null so return.");
                return;
            }
            BtDeviceHfpStateCallback btDeviceHfpStateCallback = this.d;
            if (btDeviceHfpStateCallback != null) {
                btDeviceHfpStateCallback.onBtDeviceHfpConnected(bluetoothDevice, str);
            }
        }
    }

    public static BtCommonAdapterUtil d() {
        BtCommonAdapterUtil btCommonAdapterUtil;
        synchronized (b) {
            if (c == null) {
                c = new BtCommonAdapterUtil();
            }
            btCommonAdapterUtil = c;
        }
        return btCommonAdapterUtil;
    }

    public void b(BtDeviceHfpStateCallback btDeviceHfpStateCallback) {
        this.d = btDeviceHfpStateCallback;
    }

    public void b() {
        IntentFilter intentFilter = new IntentFilter("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.input.profile.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.device.action.ACL_CONNECTED");
        try {
            BaseApplication.e().registerReceiver(this.e, intentFilter);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("BtCommonAdapterUtil", "register HfpStateReceiver: IllegalArgumentException");
        } catch (SecurityException unused2) {
            LogUtil.b("BtCommonAdapterUtil", "register HfpStateReceiver: SecurityException");
        }
    }

    public void e() {
        try {
            BaseApplication.e().unregisterReceiver(this.e);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("BtCommonAdapterUtil", "unregister HfpStateReceiver: IllegalArgumentException");
        } catch (SecurityException unused2) {
            LogUtil.b("BtCommonAdapterUtil", "unregister HfpStateReceiver: SecurityException");
        }
    }

    public boolean c() {
        BluetoothAdapter bluetoothAdapter = this.f2795a;
        return bluetoothAdapter != null && bluetoothAdapter.isEnabled() && this.f2795a.getState() == 12;
    }
}
