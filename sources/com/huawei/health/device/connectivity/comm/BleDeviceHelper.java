package com.huawei.health.device.connectivity.comm;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.device.callback.IDeviceEventHandler;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes3.dex */
public class BleDeviceHelper extends BroadcastReceiver {

    /* renamed from: a, reason: collision with root package name */
    private BluetoothDevice f2218a;
    private IDeviceEventHandler b;

    public BleDeviceHelper(BluetoothDevice bluetoothDevice) {
        this.f2218a = bluetoothDevice;
    }

    public String a() {
        String name;
        BluetoothDevice bluetoothDevice = this.f2218a;
        if (bluetoothDevice == null) {
            return null;
        }
        try {
            name = bluetoothDevice.getName();
        } catch (SecurityException e) {
            LogUtil.b("BleDeviceHelper", "getDeviceName SecurityException:", ExceptionUtils.d(e));
        }
        if (name != null) {
            return name;
        }
        return null;
    }

    public void a(IDeviceEventHandler iDeviceEventHandler) {
        this.b = iDeviceEventHandler;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        LogUtil.a("BleDeviceHelper", "receive broadcast ", intent.getAction());
        try {
            if ("android.bluetooth.device.action.BOND_STATE_CHANGED".equals(intent.getAction())) {
                BluetoothDevice bluetoothDevice = intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE") instanceof BluetoothDevice ? (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE") : null;
                if (bluetoothDevice != null && bluetoothDevice.getName() != null) {
                    if (bluetoothDevice.getName().equals(a()) && this.b != null) {
                        int bondState = bluetoothDevice.getBondState();
                        if (bondState == 12) {
                            LogUtil.a("BleDeviceHelper", "BleDevice is bonded");
                            this.b.onStateChanged(10);
                            return;
                        } else if (bondState != 10) {
                            LogUtil.a("BleDeviceHelper", "BleDevice is other bondState:", Integer.valueOf(bondState));
                            return;
                        } else {
                            LogUtil.a("BleDeviceHelper", "BleDevice is bond_none");
                            this.b.onStateChanged(8);
                            return;
                        }
                    }
                    return;
                }
                LogUtil.h("BleDeviceHelper", "BleDevice BroadcastReceiver device or deviceName = null");
            }
        } catch (RuntimeException e) {
            LogUtil.b("BleDeviceHelper", "onReceive:", ExceptionUtils.d(e));
        }
    }
}
