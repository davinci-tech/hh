package com.huawei.health.ecologydevice.fitness;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.ecologydevice.callback.BroadcastObserver;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.koq;
import health.compact.a.HuaweiHealth;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes3.dex */
public class BroadcastSubscribeCenter {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f2281a = new Object();
    private static volatile BroadcastSubscribeCenter c;
    private BluetoothSwitchMonitorReceiver b;
    private c f;
    private boolean g;
    private boolean h;
    private ConcurrentHashMap<String, BroadcastObserver> e = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, BroadcastObserver> d = new ConcurrentHashMap<>();

    public static BroadcastSubscribeCenter e() {
        BroadcastSubscribeCenter broadcastSubscribeCenter;
        if (c == null) {
            synchronized (f2281a) {
                if (c == null) {
                    c = new BroadcastSubscribeCenter();
                }
                broadcastSubscribeCenter = c;
            }
            return broadcastSubscribeCenter;
        }
        return c;
    }

    public void b(List<String> list, String str, BroadcastObserver broadcastObserver) {
        if (koq.b(list) || TextUtils.isEmpty(str) || broadcastObserver == null) {
            LogUtil.h("Ecology_BroadcastSubscribeCentre", "registerReceiverCallback is error");
            return;
        }
        for (String str2 : list) {
            if (BroadcastObserver.BLUE_STATE_TYPE.equals(str2)) {
                this.e.put(str, broadcastObserver);
            } else if (BroadcastObserver.BLUE_PAIR_STATE_TYPE.equals(str2)) {
                this.d.put(str, broadcastObserver);
            } else {
                LogUtil.h("Ecology_BroadcastSubscribeCentre", "registerReceiverCallback is error");
            }
        }
    }

    public void a(List<String> list, String str) {
        if (koq.b(list) || TextUtils.isEmpty(str)) {
            LogUtil.h("Ecology_BroadcastSubscribeCentre", "unregisterReceiverCallback is error");
            return;
        }
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            c(str, it.next());
        }
    }

    private void c(String str, String str2) {
        str2.hashCode();
        if (str2.equals(BroadcastObserver.BLUE_PAIR_STATE_TYPE)) {
            this.d.remove(str);
            if (this.d.size() == 0) {
                c();
                return;
            }
            return;
        }
        if (str2.equals(BroadcastObserver.BLUE_STATE_TYPE)) {
            this.e.remove(str);
            if (this.e.size() == 0) {
                b();
            }
        }
    }

    public void a() {
        LogUtil.a("Ecology_BroadcastSubscribeCentre", "register Bluetooth Switch BrocastReciver");
        synchronized (f2281a) {
            if (this.b == null) {
                this.b = new BluetoothSwitchMonitorReceiver();
            }
            if (HuaweiHealth.a() != null && !this.h) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
                HuaweiHealth.a().registerReceiver(this.b, intentFilter);
                this.h = true;
            }
        }
    }

    private void b() {
        synchronized (f2281a) {
            LogUtil.a("Ecology_BroadcastSubscribeCentre", "unregister BrocastReciver for ble pair");
            if (this.b != null && this.h && HuaweiHealth.a() != null) {
                this.h = false;
                HuaweiHealth.a().unregisterReceiver(this.b);
            }
        }
    }

    public class BluetoothSwitchMonitorReceiver extends BroadcastReceiver {
        public BluetoothSwitchMonitorReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                return;
            }
            LogUtil.a("Ecology_BroadcastSubscribeCentre", "Receive BluetoothSwitchMonitorReceiver : ", intent.getAction());
            String action = intent.getAction();
            if (action != null && "android.bluetooth.adapter.action.STATE_CHANGED".equals(action)) {
                BroadcastSubscribeCenter.this.Rl_(intent);
            }
        }
    }

    public void d() {
        synchronized (f2281a) {
            LogUtil.a("Ecology_BroadcastSubscribeCentre", "register BrocastReciver for ble pair");
            if (this.f == null) {
                this.f = new c();
            }
            if (HuaweiHealth.a() != null && !this.g) {
                try {
                    IntentFilter intentFilter = new IntentFilter();
                    intentFilter.addAction("android.bluetooth.device.action.BOND_STATE_CHANGED");
                    HuaweiHealth.a().registerReceiver(this.f, intentFilter);
                    this.g = true;
                } catch (SecurityException e) {
                    LogUtil.b("Ecology_BroadcastSubscribeCentre", "registerBondStateReceiver SecurityException:", ExceptionUtils.d(e));
                }
            }
        }
    }

    private void c() {
        synchronized (f2281a) {
            LogUtil.a("Ecology_BroadcastSubscribeCentre", "unregister BrocastReciver for ble pair");
            if (this.f != null && this.g && HuaweiHealth.a() != null) {
                this.g = false;
                HuaweiHealth.a().unregisterReceiver(this.f);
            }
        }
    }

    final class c extends BroadcastReceiver {
        c() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                return;
            }
            LogUtil.a("Ecology_BroadcastSubscribeCentre", "Receive BroadcastReceiverForBlePair : ", intent.getAction());
            if ("android.bluetooth.device.action.BOND_STATE_CHANGED".equals(intent.getAction())) {
                BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                if (bluetoothDevice != null) {
                    BroadcastSubscribeCenter.this.Rk_(bluetoothDevice);
                } else {
                    LogUtil.h("Ecology_BroadcastSubscribeCentre", "BroadcastReceiverForBlePair device = null");
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Rl_(Intent intent) {
        int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 0);
        if (intExtra == 10) {
            Iterator<BroadcastObserver> it = this.e.values().iterator();
            while (it.hasNext()) {
                it.next().onNotify(2, null);
            }
        } else {
            if (intExtra == 12) {
                LogUtil.a("Ecology_BroadcastSubscribeCentre", "BluetoothSwitchMonitorReceiver BluetoothAdapter.STATE_ON");
                Iterator<BroadcastObserver> it2 = this.e.values().iterator();
                while (it2.hasNext()) {
                    it2.next().onNotify(1, null);
                }
                return;
            }
            LogUtil.a("Ecology_BroadcastSubscribeCentre", "ReceiverCallback other blueState: ", Integer.valueOf(intExtra));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Rk_(BluetoothDevice bluetoothDevice) {
        try {
            String name = bluetoothDevice.getName();
            switch (bluetoothDevice.getBondState()) {
                case 10:
                    LogUtil.a("Ecology_BroadcastSubscribeCentre", name, " pair Failed");
                    Iterator<BroadcastObserver> it = this.e.values().iterator();
                    while (it.hasNext()) {
                        it.next().onNotify(4, bluetoothDevice);
                    }
                    break;
                case 11:
                    LogUtil.a("Ecology_BroadcastSubscribeCentre", name, " is pairing......");
                    Iterator<BroadcastObserver> it2 = this.e.values().iterator();
                    while (it2.hasNext()) {
                        it2.next().onNotify(5, bluetoothDevice);
                    }
                    break;
                case 12:
                    LogUtil.a("Ecology_BroadcastSubscribeCentre", name, " pair success");
                    Iterator<BroadcastObserver> it3 = this.e.values().iterator();
                    while (it3.hasNext()) {
                        it3.next().onNotify(3, bluetoothDevice);
                    }
                    break;
                default:
                    Object[] objArr = new Object[3];
                    objArr[0] = name;
                    objArr[1] = " other status：";
                    objArr[2] = Integer.valueOf(bluetoothDevice.getBondState());
                    LogUtil.a("Ecology_BroadcastSubscribeCentre", objArr);
                    break;
            }
        } catch (SecurityException e) {
            LogUtil.b("Ecology_BroadcastSubscribeCentre", "onDataTransfer SecurityException:", ExceptionUtils.d(e));
        }
    }
}
