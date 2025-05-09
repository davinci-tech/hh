package com.huawei.health.ecologydevice.fitness.util;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
public class RopeStateMonitor extends BroadcastReceiver {

    /* renamed from: a, reason: collision with root package name */
    private StateChangeListener f2290a;
    private Context b;
    private IntentFilter d;

    public interface StateChangeListener {
        void onBondStateChanged(int i, BluetoothDevice bluetoothDevice);

        void onSwitchStateChanged(int i);
    }

    public RopeStateMonitor(Context context, StateChangeListener stateChangeListener) {
        this.b = context;
        this.f2290a = stateChangeListener;
    }

    public void c() {
        if (this.b == null) {
            LogUtil.a("PDROPE_RopeStateMonitor", "context is null, start failed. ");
        } else {
            b();
            this.b.registerReceiver(this, this.d);
        }
    }

    public void d() {
        Context context = this.b;
        if (context == null) {
            LogUtil.a("PDROPE_RopeStateMonitor", "Monitor has not started yet.");
            return;
        }
        try {
            context.unregisterReceiver(this);
            this.b = null;
        } catch (IllegalArgumentException unused) {
            LogUtil.b("PDROPE_RopeStateMonitor", "Receiver not registered");
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        LogUtil.a("PDROPE_RopeStateMonitor", "Receive BluetoothSwitchMonitorReceiver : ", intent.getAction());
        try {
            if ("android.bluetooth.adapter.action.STATE_CHANGED".equals(intent.getAction())) {
                int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 0);
                LogUtil.a("PDROPE_RopeStateMonitor", "BluetoothSwitchMonitorReceiver blueState: ", Integer.valueOf(intExtra));
                if (intExtra == 10) {
                    this.f2290a.onSwitchStateChanged(3);
                } else if (intExtra != 12) {
                    LogUtil.a("PDROPE_RopeStateMonitor", "BluetoothSwitchMonitorReceiver other ignored blueState: ", Integer.valueOf(intExtra));
                } else {
                    this.f2290a.onSwitchStateChanged(4);
                }
            }
            if ("android.bluetooth.device.action.BOND_STATE_CHANGED".equals(intent.getAction())) {
                BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                if (bluetoothDevice == null) {
                    LogUtil.h("PDROPE_RopeStateMonitor", "BroadcastReceiverForBlePair device = null");
                    return;
                }
                switch (bluetoothDevice.getBondState()) {
                    case 10:
                        this.f2290a.onBondStateChanged(0, bluetoothDevice);
                        break;
                    case 11:
                        this.f2290a.onBondStateChanged(1, bluetoothDevice);
                        break;
                    case 12:
                        this.f2290a.onBondStateChanged(2, bluetoothDevice);
                        break;
                    default:
                        Object[] objArr = new Object[3];
                        objArr[0] = bluetoothDevice.getName();
                        objArr[1] = " other status：";
                        objArr[2] = Integer.valueOf(bluetoothDevice.getBondState());
                        LogUtil.a("PDROPE_RopeStateMonitor", objArr);
                        break;
                }
            }
            Sg_(intent);
        } catch (SecurityException e) {
            LogUtil.b("PDROPE_RopeStateMonitor", "onReceive SecurityException:", ExceptionUtils.d(e));
        }
    }

    private void Sg_(Intent intent) {
        try {
            if ("android.bluetooth.device.action.PAIRING_REQUEST".equals(intent.getAction())) {
                BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                if (bluetoothDevice == null) {
                    LogUtil.h("PDROPE_RopeStateMonitor", "BroadcastReceiverForBlePair device = null");
                } else if (bluetoothDevice.getBondState() != 12) {
                    this.f2290a.onBondStateChanged(9, bluetoothDevice);
                }
            }
        } catch (SecurityException e) {
            LogUtil.b("PDROPE_RopeStateMonitor", "actionForRequestPair SecurityException:", ExceptionUtils.d(e));
        }
    }

    private void b() {
        try {
            IntentFilter intentFilter = new IntentFilter();
            this.d = intentFilter;
            intentFilter.addAction("android.bluetooth.device.action.BOND_STATE_CHANGED");
            this.d.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
            this.d.addAction("android.bluetooth.device.action.PAIRING_REQUEST");
        } catch (SecurityException e) {
            LogUtil.b("PDROPE_RopeStateMonitor", "initFilter SecurityException:", ExceptionUtils.d(e));
        }
    }
}
