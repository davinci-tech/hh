package defpackage;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.devicesdk.callback.BtDeviceHfpStateCallback;
import com.huawei.devicesdk.callback.BtSwitchCallback;
import com.huawei.devicesdk.entity.ConnectMode;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.devicesdk.entity.ExternalDeviceCapability;
import com.huawei.devicesdk.reconnect.BleReconnectScanCallback;
import com.huawei.haf.application.BroadcastManager;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.unitedevice.entity.UniteDevice;
import com.huawei.watchface.api.HwWatchFaceApi;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes3.dex */
public class bjy {

    /* renamed from: a, reason: collision with root package name */
    private List<String> f415a;
    private Map<String, DeviceInfo> b;
    private Map<String, DeviceInfo> c;
    private final BleReconnectScanCallback d;
    private final BtSwitchCallback e;
    private boolean f;
    private BroadcastReceiver g;
    private final BtDeviceHfpStateCallback h;
    private bkw i;
    private Map<String, Boolean> j;
    private long l;

    private bjy() {
        this.l = 0L;
        this.j = new HashMap(16);
        this.f415a = new ArrayList(5);
        this.c = new ConcurrentHashMap(4);
        this.b = new ConcurrentHashMap(4);
        BtSwitchCallback btSwitchCallback = new BtSwitchCallback() { // from class: bjy.3
            @Override // com.huawei.devicesdk.callback.BtSwitchCallback
            public void onBtSwitchStateCallback(int i) {
                if (i == 1) {
                    ReleaseLogUtil.b("DEVMGR_ReconnectManager", "BLUETOOTH_STATE_OFF!");
                    bjy.this.j();
                } else {
                    if (i == 2) {
                        ReleaseLogUtil.b("DEVMGR_ReconnectManager", "BLUETOOTH_STATE_TURNING_OFF!");
                        return;
                    }
                    if (i == 3) {
                        ReleaseLogUtil.b("DEVMGR_ReconnectManager", "BLUETOOTH_STATE_ON!");
                        bjy.this.f();
                    } else {
                        if (i != 4) {
                            return;
                        }
                        ReleaseLogUtil.b("DEVMGR_ReconnectManager", "BLUETOOTH_STATE_TURNING_ON!");
                    }
                }
            }
        };
        this.e = btSwitchCallback;
        this.g = new BroadcastReceiver() { // from class: bjy.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (intent == null) {
                    return;
                }
                String action = intent.getAction();
                LogUtil.c("ReconnectManager", "mForegroundStatusChangeReceiver action: ", action);
                if (action == null) {
                    return;
                }
                if (HwWatchFaceApi.ACTION_FOREGROUND_STATUS.equals(action)) {
                    boolean booleanExtra = intent.getBooleanExtra("isForeground", false);
                    ReleaseLogUtil.b("DEVMGR_ReconnectManager", "ForegroundStatus isForeground: ", Boolean.valueOf(booleanExtra));
                    if (bjy.this.f == booleanExtra) {
                        return;
                    }
                    bjy.this.f = booleanExtra;
                    if (bjy.this.f) {
                        if (Math.abs(System.currentTimeMillis() - bjy.this.l) < 60000) {
                            LogUtil.a("ReconnectManager", "startAllReconnect less one minutes, return");
                            return;
                        }
                        bmw.e(100083, "", "", "");
                        bjy.this.l = System.currentTimeMillis();
                        ReleaseLogUtil.b("DEVMGR_ReconnectManager", "ForegroundStatus startAllReconnect");
                        bjy.this.c(2);
                        return;
                    }
                    return;
                }
                LogUtil.a("ReconnectManager", "mForegroundStatusChangeReceiver other action");
            }
        };
        BtDeviceHfpStateCallback btDeviceHfpStateCallback = new BtDeviceHfpStateCallback() { // from class: bjy.5
            @Override // com.huawei.devicesdk.callback.BtDeviceHfpStateCallback
            public void onBtDeviceHfpConnected(BluetoothDevice bluetoothDevice, String str) {
                if (bluetoothDevice != null) {
                    try {
                        if (!TextUtils.isEmpty(bluetoothDevice.getAddress())) {
                            DeviceInfo j = bjx.a().j(bluetoothDevice.getAddress());
                            if (j == null) {
                                ReleaseLogUtil.a("DEVMGR_ReconnectManager", "deviceInfo is null, onBtDeviceHfpConnected fail.");
                                return;
                            }
                            if (!bmc.b(j.getDeviceName(), j.getDeviceMac())) {
                                ReleaseLogUtil.a("DEVMGR_ReconnectManager", "onBTDeviceHFPConnected Br device connect too often.");
                                return;
                            }
                            int deviceBtType = j.getDeviceBtType();
                            ReleaseLogUtil.b("DEVMGR_ReconnectManager", "Enter onBtDeviceHfpConnected.", blt.a(bluetoothDevice.getAddress()), ",btType:", Integer.valueOf(deviceBtType));
                            if (!"android.bluetooth.device.action.ACL_CONNECTED".equals(str)) {
                                if (deviceBtType == 1 || deviceBtType == 2) {
                                    ReleaseLogUtil.b("DEVMGR_ReconnectManager", "onBTDeviceHFPConnected. type: ", Integer.valueOf(deviceBtType));
                                    bjy.this.sd_(bluetoothDevice, 2);
                                    return;
                                }
                                return;
                            }
                            String deviceBtMode = j.getDeviceBtMode();
                            ReleaseLogUtil.b("DEVMGR_ReconnectManager", "onBTDeviceHFPConnected device manufacture:", deviceBtMode);
                            if (bjy.this.b(deviceBtType, deviceBtMode)) {
                                ReleaseLogUtil.b("DEVMGR_ReconnectManager", "onBTDeviceHFPConnected connect diana or ble05 device.");
                                bjy.this.sd_(bluetoothDevice, 1);
                                return;
                            }
                            return;
                        }
                    } catch (SecurityException e) {
                        LogUtil.e("ReconnectManager", "connect SecurityException ", ExceptionUtils.d(e));
                        return;
                    }
                }
                LogUtil.a("ReconnectManager", "btDevice is invalid, onBtDeviceHfpConnected fail.");
            }
        };
        this.h = btDeviceHfpStateCallback;
        this.d = new BleReconnectScanCallback() { // from class: bjy.2
            @Override // com.huawei.devicesdk.reconnect.BleReconnectScanCallback
            public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
                ReleaseLogUtil.b("DEVMGR_ReconnectManager", "Enter onLeScan, find want device.");
                try {
                    Boolean bool = (Boolean) bjy.this.j.get(bluetoothDevice.getAddress());
                    if (bool != null && bool.booleanValue()) {
                        LogUtil.c("ReconnectManager", "gatt connect timeout scanned the device, report");
                        bjy.this.j.put(bluetoothDevice.getAddress(), false);
                        bmw.e(100094, bmh.b(bluetoothDevice.getName()), "", "1");
                    }
                    bjy.this.sd_(bluetoothDevice, 3);
                } catch (SecurityException e) {
                    LogUtil.e("ReconnectManager", "mBleScanCallback SecurityException ", ExceptionUtils.d(e));
                }
            }
        };
        LogUtil.c("DEVMGR_ReconnectManager", "ReconnectManager onCreate");
        bkw d2 = bkw.d();
        this.i = d2;
        d2.b(btDeviceHfpStateCallback);
        this.i.b(btSwitchCallback);
        this.i.j();
        BroadcastManager.wj_(this.g);
    }

    public Map<String, Boolean> a() {
        return this.j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(int i, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if ("010303".equals(str) && i == 1) {
            return true;
        }
        return "010505".equals(str) && i == 2;
    }

    public static bjy d() {
        return d.e;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sd_(BluetoothDevice bluetoothDevice, int i) {
        if (bluetoothDevice == null || TextUtils.isEmpty(bluetoothDevice.getAddress())) {
            LogUtil.a("ReconnectManager", "btDevice is invalid, connect fail.");
        } else {
            ReleaseLogUtil.b("DEVMGR_ReconnectManager", "ready to connect device is ", blt.b(bluetoothDevice.getAddress()), "reconnectType: ", Integer.valueOf(i));
            d(bjx.a().j(bluetoothDevice.getAddress()));
        }
    }

    public void c(int i) {
        if (!bkt.e().b()) {
            LogUtil.a("ReconnectManager", "startAllReconnect bt switch is off");
            return;
        }
        ReleaseLogUtil.b("DEVMGR_ReconnectManager", "Enter startAllReconnect.");
        h();
        bkb.e().e(this.d, i);
    }

    private void h() {
        ArrayList<DeviceInfo> arrayList = new ArrayList(4);
        for (DeviceInfo deviceInfo : this.b.values()) {
            if ("main_relationship".equals(snu.e().getDeviceRelation(deviceInfo.getDeviceMac()))) {
                LogUtil.c("ReconnectManager", "start reconnect main_relationship.", blt.a(deviceInfo));
                d(deviceInfo);
            } else {
                arrayList.add(deviceInfo);
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        Collections.sort(arrayList, new Comparator<DeviceInfo>() { // from class: bjy.4
            @Override // java.util.Comparator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public int compare(DeviceInfo deviceInfo2, DeviceInfo deviceInfo3) {
                return deviceInfo2.getLastConnectedTime() >= deviceInfo3.getLastConnectedTime() ? -1 : 1;
            }
        });
        Set<String> a2 = bkt.e().a();
        for (DeviceInfo deviceInfo2 : arrayList) {
            if (deviceInfo2 != null && !TextUtils.isEmpty(deviceInfo2.getDeviceMac()) && a2.contains(deviceInfo2.getDeviceMac())) {
                LogUtil.c("ReconnectManager", "start reconnect br device.", blt.a(deviceInfo2));
                d(deviceInfo2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        ReleaseLogUtil.b("DEVMGR_ReconnectManager", "startBleReconnect start reconnect ble device.");
        bkb.e().e(this.d, 3);
    }

    public void d(DeviceInfo deviceInfo, int i) {
        if (deviceInfo == null || deviceInfo.getDeviceBtType() != 2) {
            return;
        }
        if (!deviceInfo.isUsing()) {
            ReleaseLogUtil.a("DEVMGR_ReconnectManager", "current device is not using");
            return;
        }
        String e = bme.e(deviceInfo.getDeviceMac());
        ReleaseLogUtil.b("DEVMGR_ReconnectManager", "connect reason is ", Integer.valueOf(i), " relation is ", e);
        int i2 = i % 1000;
        if (i2 == 133) {
            bkb.e().d(300000L);
            return;
        }
        if (i2 == 304) {
            this.j.put(deviceInfo.getDeviceMac(), true);
            bkb.e().c();
            return;
        }
        ExternalDeviceCapability a2 = bjx.a().a(deviceInfo.getDeviceMac());
        UniteDevice uniteDevice = new UniteDevice();
        uniteDevice.setIdentify(deviceInfo.getDeviceMac());
        uniteDevice.setCapability(a2);
        boolean e2 = bla.e(uniteDevice, 109);
        LogUtil.c("ReconnectManager", "reconnectDevice isSupportMulti: ", Boolean.valueOf(e2));
        if (bky.g() && ("main_relationship".equalsIgnoreCase(e) || e2)) {
            ReleaseLogUtil.a("DEVMGR_ReconnectManager", "current device is start all scan");
            bkb.e().d(true);
            bkb.e().d(5000L);
            this.f415a.add(deviceInfo.getDeviceMac());
            return;
        }
        bkb.e().c();
    }

    public void d(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("ReconnectManager", "removeDisconnectDevices mac is empty.");
            return;
        }
        if (this.f415a.size() > 0) {
            if (this.f415a.contains(str)) {
                this.f415a.remove(str);
            }
            if (this.f415a.size() == 0 && bkb.e().d()) {
                bkb.e().i();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        bkb.e().g();
    }

    public void d(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.a("ReconnectManager", "device info is invalid.");
            return;
        }
        bix h = bjx.a().h(deviceInfo.getDeviceMac());
        if (h == null) {
            LogUtil.c("ReconnectManager", "device status is incorrect.");
            return;
        }
        if (deviceInfo.isUsing() && h.b() == ConnectMode.GENERAL) {
            int deviceConnectState = deviceInfo.getDeviceConnectState();
            ReleaseLogUtil.b("DEVMGR_ReconnectManager", "start reconnect device.", blt.b(deviceInfo), " deviceConnectState: ", Integer.valueOf(deviceConnectState));
            if (deviceConnectState == 2 || deviceConnectState == 1 || deviceConnectState == 9 || deviceConnectState == 13 || deviceConnectState == 10 || deviceConnectState == 14 || deviceConnectState == 11) {
                LogUtil.c("ReconnectManager", "start reconnect device, but the device connect status is ", Integer.valueOf(deviceInfo.getDeviceConnectState()));
                return;
            } else {
                e();
                bgl.c().connectDevice(deviceInfo, true, ConnectMode.GENERAL);
                return;
            }
        }
        ReleaseLogUtil.b("DEVMGR_ReconnectManager", " isUsing:", Boolean.valueOf(deviceInfo.isUsing()), " connectMode:", h.b(), "The connected hfp is not wanted device, so stop to connect spp.");
    }

    private void e() {
        if (bky.b()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: bjy.9
                @Override // java.lang.Runnable
                public void run() {
                    LogUtil.c("ReconnectManager", "PowerKitManager reconnectDevice request");
                    bmd.d("ReconnectManager", 65535, OpAnalyticsConstants.H5_LOADING_DELAY, "user-reconnectDevice");
                }
            });
        }
    }

    public void c(DeviceInfo deviceInfo) {
        if (deviceInfo != null && !TextUtils.isEmpty(deviceInfo.getDeviceMac()) && deviceInfo.isUsing()) {
            LogUtil.c("ReconnectManager", "address: ", blt.a(deviceInfo.getDeviceMac()), " isUsing: ", Boolean.valueOf(deviceInfo.isUsing()));
            bix h = bjx.a().h(deviceInfo.getDeviceMac());
            if (h != null && h.b() != null && h.b() == ConnectMode.GENERAL) {
                if (deviceInfo.getDeviceBtType() == 2) {
                    this.c.put(deviceInfo.getDeviceMac(), deviceInfo);
                    bio.a(this.c.values());
                    ReleaseLogUtil.b("DEVMGR_ReconnectManager", "reconnect ble device size is ", Integer.valueOf(this.c.size()));
                    return;
                } else {
                    this.b.put(deviceInfo.getDeviceMac(), deviceInfo);
                    bio.d(this.b.values());
                    ReleaseLogUtil.b("DEVMGR_ReconnectManager", "reconnect br device size is ", Integer.valueOf(this.b.size()));
                    return;
                }
            }
            ReleaseLogUtil.a("DEVMGR_ReconnectManager", "deviceStatus is null.");
            return;
        }
        LogUtil.a("ReconnectManager", "addReconnectDevice device is null.");
    }

    public void b() {
        LogUtil.c("ReconnectManager", "destroy enter.");
        BroadcastManager.wx_(this.g);
    }

    public void a(DeviceInfo deviceInfo) {
        ReleaseLogUtil.a("DEVMGR_ReconnectManager", "enter removeReconnectDevice");
        if (deviceInfo == null || TextUtils.isEmpty(deviceInfo.getDeviceMac())) {
            return;
        }
        this.b.remove(deviceInfo.getDeviceMac());
        this.c.remove(deviceInfo.getDeviceMac());
        bio.d(this.b.values());
        bio.a(this.c.values());
        this.j.remove(deviceInfo.getDeviceMac());
    }

    Map<String, DeviceInfo> c() {
        return this.c;
    }

    static class d {
        private static bjy e = new bjy();
    }
}
