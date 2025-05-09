package com.huawei.hwbtsdk.btcommon;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hwbtsdk.btdatatype.callback.BtSwitchStateCallback;
import health.compact.a.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class BluetoothSwitchStateUtil {

    /* renamed from: a, reason: collision with root package name */
    private Context f6179a;
    private final List<BtSwitchStateCallback> c = new ArrayList(16);
    private final BroadcastReceiver e = new BroadcastReceiver() { // from class: com.huawei.hwbtsdk.btcommon.BluetoothSwitchStateUtil.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            BluetoothAdapter defaultAdapter;
            if (intent == null) {
                LogUtil.a("BluetoothSwitchStateUtil", "intent is null");
                return;
            }
            String action = intent.getAction();
            LogUtil.c("BluetoothSwitchStateUtil", "mBluetoothSwitchStateReceiver action:", action);
            if (!"android.bluetooth.adapter.action.STATE_CHANGED".equals(action) || (defaultAdapter = BluetoothAdapter.getDefaultAdapter()) == null || BluetoothSwitchStateUtil.this.c == null || BluetoothSwitchStateUtil.this.c.size() == 0) {
                return;
            }
            LogUtil.c("BluetoothSwitchStateUtil", "mBluetoothSwitchStateCallbackList size :", Integer.valueOf(BluetoothSwitchStateUtil.this.c.size()));
            int state = defaultAdapter.getState();
            if (state == 12) {
                synchronized (BluetoothSwitchStateUtil.this.c) {
                    Iterator it = BluetoothSwitchStateUtil.this.c.iterator();
                    while (it.hasNext()) {
                        ((BtSwitchStateCallback) it.next()).onBtSwitchStateCallback(3);
                    }
                }
                return;
            }
            LogUtil.a("BluetoothSwitchStateUtil", "bluetoothSwitchState is error:", Integer.valueOf(state));
        }
    };

    public BluetoothSwitchStateUtil(Context context) {
        this.f6179a = null;
        if (context == null) {
            LogUtil.a("BluetoothSwitchStateUtil", "context is null");
            return;
        }
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            this.f6179a = applicationContext;
        } else {
            this.f6179a = context;
        }
        d();
    }

    private void d() {
        this.f6179a.registerReceiver(this.e, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"));
    }

    public void b() {
        Context context = this.f6179a;
        if (context == null) {
            return;
        }
        try {
            context.unregisterReceiver(this.e);
        } catch (IllegalArgumentException unused) {
            LogUtil.e("BluetoothSwitchStateUtil", "destroy IllegalArgumentException");
        }
        LogUtil.c("BluetoothSwitchStateUtil", "destroy finish.");
    }

    private List<BtSwitchStateCallback> a() {
        List<BtSwitchStateCallback> list;
        synchronized (this) {
            list = this.c;
        }
        return list;
    }

    public void a(BtSwitchStateCallback btSwitchStateCallback) {
        List<BtSwitchStateCallback> list;
        if (btSwitchStateCallback == null || (list = this.c) == null || list.contains(btSwitchStateCallback)) {
            return;
        }
        synchronized (a()) {
            this.c.add(btSwitchStateCallback);
            LogUtil.c("BluetoothSwitchStateUtil", "Register mBtSwitchStateCallbackList size :", Integer.valueOf(this.c.size()));
        }
    }

    public void c(BtSwitchStateCallback btSwitchStateCallback) {
        List<BtSwitchStateCallback> list;
        if (btSwitchStateCallback == null || (list = this.c) == null || !list.contains(btSwitchStateCallback)) {
            return;
        }
        synchronized (a()) {
            this.c.remove(btSwitchStateCallback);
            LogUtil.c("BluetoothSwitchStateUtil", "Unregister mBtSwitchStateCallbackList size :", Integer.valueOf(this.c.size()));
        }
    }

    public void d(BtSwitchStateCallback btSwitchStateCallback) {
        if (btSwitchStateCallback == null) {
            LogUtil.a("BluetoothSwitchStateUtil", "openBluetoothSwitch callback is null");
            return;
        }
        try {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter == null) {
                LogUtil.a("BluetoothSwitchStateUtil", "openBluetoothSwitch adapter is null");
                btSwitchStateCallback.onBtSwitchStateCallback(1);
                return;
            }
            if (defaultAdapter.isEnabled()) {
                btSwitchStateCallback.onBtSwitchStateCallback(3);
                return;
            }
            a(btSwitchStateCallback);
            if (Build.VERSION.SDK_INT < 33) {
                LogUtil.c("BluetoothSwitchStateUtil", "openBluetoothSwitch:", Boolean.valueOf(defaultAdapter.enable()));
                return;
            }
            try {
                Intent intent = new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE");
                Activity wa_ = BaseApplication.wa_();
                if (wa_ != null) {
                    wa_.startActivityForResult(intent, 35189);
                } else {
                    intent.setFlags(268435456);
                    BaseApplication.e().startActivity(intent);
                }
            } catch (ActivityNotFoundException unused) {
                btSwitchStateCallback.onBtSwitchStateCallback(1);
                LogUtil.e(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "BluetoothSwitchStateUtil", "btSwitchEnable ActivityNotFoundException");
            }
        } catch (SecurityException e) {
            LogUtil.e("BluetoothSwitchStateUtil", "cancelBRDiscoveryForConnect SecurityException:", ExceptionUtils.d(e));
        }
    }
}
