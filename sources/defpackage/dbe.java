package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.devicesdk.callback.DeviceScanCallback;
import com.huawei.devicesdk.callback.DeviceStatusChangeCallback;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.devicesdk.entity.ScanMode;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.health.ecologydevice.callback.NfcMeasureCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.unitedevice.entity.UniteDevice;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/* loaded from: classes3.dex */
public class dbe extends daz {
    private static dbe i;
    private static final Object j = new Object();
    private BluetoothDevice k;
    private BluetoothAdapter m;
    private boolean n;
    private BluetoothManager o;
    private String r;
    private UniteDevice w;
    private boolean s = false;
    private boolean l = false;
    private ScanCallback t = new ScanCallback() { // from class: dbe.5
        @Override // android.bluetooth.le.ScanCallback
        public void onScanResult(int i2, ScanResult scanResult) {
            super.onScanResult(i2, scanResult);
            dbe.this.g();
            if (scanResult != null && scanResult.getDevice() != null) {
                try {
                    LogUtil.a("RocheGlucoseManager", "get device in scanning: ", scanResult.getDevice().getName());
                    dbe.this.To_(scanResult.getDevice());
                    return;
                } catch (SecurityException e) {
                    LogUtil.b("RocheGlucoseManager", "onScanResult SecurityException:", ExceptionUtils.d(e));
                    return;
                }
            }
            LogUtil.a("RocheGlucoseManager", "result is null or result.getDevice is null");
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onBatchScanResults(List<ScanResult> list) {
            super.onBatchScanResults(list);
            LogUtil.a("RocheGlucoseManager", "onBatchScanResults : results=" + list.size());
        }

        @Override // android.bluetooth.le.ScanCallback
        public void onScanFailed(int i2) {
            super.onScanFailed(i2);
            LogUtil.a("RocheGlucoseManager", "onScanFailed : errorCode=" + i2);
        }
    };
    private DeviceScanCallback p = new DeviceScanCallback() { // from class: dbe.3
        @Override // com.huawei.devicesdk.callback.DeviceScanCallback
        public void scanResult(UniteDevice uniteDevice, byte[] bArr, String str, int i2) {
            LogUtil.a("RocheGlucoseManager", "device: ", uniteDevice, "status: ", Integer.valueOf(i2));
            if (i2 == 20 && uniteDevice != null) {
                String deviceName = uniteDevice.getDeviceInfo().getDeviceName();
                LogUtil.a("RocheGlucoseManager", "scanResult deviceName: ", deviceName);
                if (!TextUtils.isEmpty(deviceName)) {
                    if (dbe.this.n) {
                        return;
                    }
                    dbe.this.c(uniteDevice);
                    return;
                }
                LogUtil.h("RocheGlucoseManager", "deviceName is empty");
                return;
            }
            LogUtil.h("RocheGlucoseManager", "scanResult: " + i2);
        }
    };
    private DeviceStatusChangeCallback q = new DeviceStatusChangeCallback() { // from class: dbe.4
        @Override // com.huawei.devicesdk.callback.DeviceStatusChangeCallback
        public void onConnectStatusChanged(DeviceInfo deviceInfo, int i2, int i3) {
            LogUtil.a("RocheGlucoseManager", "device: ", deviceInfo.toString(), "status: ", Integer.valueOf(i2), "errCode: ", Integer.valueOf(i3));
            dbe.this.f11574a = 16;
            String deviceName = deviceInfo.getDeviceName();
            String deviceMac = deviceInfo.getDeviceMac();
            if (deviceName == null) {
                if (deviceMac == null || !deviceMac.equals(dbe.this.d)) {
                    LogUtil.h("RocheGlucoseManager", "not this bond device");
                    return;
                }
            } else if (!deviceName.equals(dbe.this.c) && !deviceMac.equals(dbe.this.d)) {
                LogUtil.a("RocheGlucoseManager", "Ble pair failed: name and address are not equal");
                return;
            }
            if (i2 == 30) {
                LogUtil.a("RocheGlucoseManager", "bonded_device_success");
                dbe.this.f11574a = 16;
                ddw.c().d(dbe.this.r);
                dbe.this.f = 12;
                dew.b(deviceMac, 1);
            } else if (i2 == 31) {
                LogUtil.a("RocheGlucoseManager", "bonded_device_ing");
                dbe.this.f11574a = 15;
                dbe.this.f = 11;
            } else if (i2 == 32) {
                LogUtil.a("RocheGlucoseManager", "bonded_device_failed");
                dbe.this.f11574a = 14;
                dbe.this.f = 10;
                ddw.c().d(dbe.this.r);
            } else if (i2 == 33) {
                dew.b(deviceMac, 0);
            } else {
                LogUtil.a("RocheGlucoseManager", "unknown status", Integer.valueOf(i2));
            }
            dbe.this.e(106);
        }
    };

    public static dbe e() {
        dbe dbeVar;
        synchronized (j) {
            if (i == null) {
                i = new dbe();
            }
            dbeVar = i;
        }
        return dbeVar;
    }

    @Override // defpackage.daz
    public boolean d(NfcMeasureCallback nfcMeasureCallback) {
        if (nfcMeasureCallback == null) {
            LogUtil.h("RocheGlucoseManager", "nfcMeasureCallback == null");
            return false;
        }
        super.d(nfcMeasureCallback);
        f();
        if (this.m != null) {
            LogUtil.a("RocheGlucoseManager", "Init already.");
            return true;
        }
        if (this.o == null) {
            Object systemService = BaseApplication.getContext().getSystemService("bluetooth");
            if (systemService instanceof BluetoothManager) {
                this.o = (BluetoothManager) systemService;
            }
        }
        BluetoothManager bluetoothManager = this.o;
        if (bluetoothManager == null) {
            LogUtil.b("RocheGlucoseManager", "Unable to initAdapter BluetoothManager.");
            return false;
        }
        BluetoothAdapter adapter = bluetoothManager.getAdapter();
        this.m = adapter;
        if (adapter != null) {
            return true;
        }
        LogUtil.b("RocheGlucoseManager", "Unable to obtain BluetoothAdapter.");
        return false;
    }

    private void f() {
        if (this.b == null) {
            this.b = HandlerCenter.yt_(new a(), "RocheGlucoseManager");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        if (this.g) {
            k();
        } else {
            d(true);
        }
    }

    private void k() {
        a(103, 5000L);
        if (this.h < 1) {
            a(104, OpAnalyticsConstants.H5_LOADING_DELAY);
        }
        this.n = false;
        ddw.c().b(ScanMode.BLE, ddw.c().b(this.c, this.d), this.p);
    }

    private void d(boolean z) {
        a(103, 5000L);
        if (this.h < 1) {
            a(104, OpAnalyticsConstants.H5_LOADING_DELAY);
        }
        if (this.m != null && (!TextUtils.isEmpty(this.c) || !TextUtils.isEmpty(this.d))) {
            LogUtil.a("RocheGlucoseManager", "SDK is higher than LOLLIPOP");
            if (z) {
                n();
                return;
            } else {
                o();
                return;
            }
        }
        LogUtil.c("RocheGlucoseManager", "scanBleDevice stop scan");
    }

    public void g() {
        if (this.s) {
            LogUtil.a("RocheGlucoseManager", "stopScanDevice ");
            this.s = false;
            try {
                BluetoothLeScanner bluetoothLeScanner = this.m.getBluetoothLeScanner();
                if (bluetoothLeScanner != null) {
                    bluetoothLeScanner.stopScan(this.t);
                }
                this.n = true;
            } catch (SecurityException e) {
                LogUtil.b("RocheGlucoseManager", "stopScanDevice SecurityException:", ExceptionUtils.d(e));
            }
        }
    }

    private void n() {
        ScanFilter build;
        this.s = true;
        ArrayList arrayList = new ArrayList(10);
        if (!TextUtils.isEmpty(this.c)) {
            build = new ScanFilter.Builder().setDeviceName(this.c).build();
        } else {
            build = new ScanFilter.Builder().setDeviceAddress(this.d).build();
        }
        arrayList.add(build);
        LogUtil.a("RocheGlucoseManager", "now scanner start scan");
        try {
            BluetoothLeScanner bluetoothLeScanner = this.m.getBluetoothLeScanner();
            if (bluetoothLeScanner != null) {
                bluetoothLeScanner.startScan(arrayList, new ScanSettings.Builder().setScanMode(1).build(), this.t);
            } else {
                LogUtil.b("RocheGlucoseManager", "scanner = null");
            }
        } catch (SecurityException e) {
            LogUtil.b("RocheGlucoseManager", "scanEnableBle SecurityException:", ExceptionUtils.d(e));
        }
    }

    private void o() {
        this.s = false;
        LogUtil.a("RocheGlucoseManager", "now scanner stop scan");
        try {
            BluetoothLeScanner bluetoothLeScanner = this.m.getBluetoothLeScanner();
            if (bluetoothLeScanner != null) {
                bluetoothLeScanner.stopScan(this.t);
            } else {
                LogUtil.a("RocheGlucoseManager", "null = scanner");
            }
        } catch (SecurityException e) {
            LogUtil.b("RocheGlucoseManager", "onBondStateChanged SecurityException:", ExceptionUtils.d(e));
        }
    }

    public void a() {
        try {
            Set<BluetoothDevice> bondedDevices = BluetoothAdapter.getDefaultAdapter().getBondedDevices();
            if (bondedDevices != null && bondedDevices.size() > 0) {
                Iterator<BluetoothDevice> it = bondedDevices.iterator();
                while (it.hasNext()) {
                    Tn_(it.next());
                }
                if (this.l) {
                    To_(this.k);
                    return;
                } else {
                    m();
                    return;
                }
            }
            m();
        } catch (SecurityException e) {
            LogUtil.b("RocheGlucoseManager", "getConnectedDeviceList SecurityException:", ExceptionUtils.d(e));
        }
    }

    private void Tn_(BluetoothDevice bluetoothDevice) {
        if ((TextUtils.isEmpty(this.c) || !this.c.equals(bluetoothDevice.getName())) && (TextUtils.isEmpty(this.d) || !this.d.equals(bluetoothDevice.getAddress()))) {
            return;
        }
        this.l = true;
        this.k = bluetoothDevice;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void To_(BluetoothDevice bluetoothDevice) {
        LogUtil.a("RocheGlucoseManager", "now will distinguish device.");
        if (bluetoothDevice == null) {
            return;
        }
        if ((TextUtils.isEmpty(this.c) || !this.c.equals(bluetoothDevice.getName())) && (TextUtils.isEmpty(this.d) || !this.d.equals(bluetoothDevice.getAddress()))) {
            return;
        }
        LogUtil.a("RocheGlucoseManager", "found the device we need successfully");
        d(103);
        d(104);
        if (this.e != null) {
            this.k = bluetoothDevice;
            this.e.onDataChanged(cxh.Ra_(bluetoothDevice), new HealthData());
            e(108);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(UniteDevice uniteDevice) {
        LogUtil.a("RocheGlucoseManager", "now will deviceScanSucByUds");
        if (uniteDevice == null) {
            return;
        }
        if ((TextUtils.isEmpty(this.c) || !this.c.equals(uniteDevice.getDeviceInfo().getDeviceName())) && (TextUtils.isEmpty(this.d) || !this.d.equals(uniteDevice.getDeviceInfo().getDeviceMac()))) {
            return;
        }
        LogUtil.a("RocheGlucoseManager", "found the device we need successfully by uds");
        d(103);
        d(104);
        if (this.e != null) {
            this.w = uniteDevice;
            this.e.onDataChanged(cxh.d(uniteDevice), new HealthData());
            e(108);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        if (this.s) {
            g();
        }
        this.h++;
        if (this.h == 1) {
            this.e.onStatusChanged(10, this.f, this.h);
        }
        a(102, 2000L);
    }

    private void j() {
        synchronized (j) {
            i = null;
        }
    }

    public void c() {
        e(101);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        try {
            BluetoothDevice bluetoothDevice = this.k;
            if (bluetoothDevice != null && bluetoothDevice.getBondState() != 12) {
                this.k.createBond();
            }
            if (this.g) {
                this.r = UUID.randomUUID().toString();
                ddw.c().a(this.r, this.q);
                ddw.c().d(this.w);
            }
        } catch (SecurityException e) {
            LogUtil.b("RocheGlucoseManager", "createBondDevice SecurityException:", ExceptionUtils.d(e));
        }
    }

    public void d() {
        a();
    }

    @Override // defpackage.daz
    public void b() {
        super.b();
        j();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean Tp_(Class cls, BluetoothDevice bluetoothDevice) {
        if (cls == null || bluetoothDevice == null) {
            LogUtil.h("RocheGlucoseManager", "clazz == null || bleDevice == null");
            return false;
        }
        try {
            Object invoke = cls.getMethod("removeBond", new Class[0]).invoke(bluetoothDevice, new Object[0]);
            if (invoke instanceof Boolean) {
                return ((Boolean) invoke).booleanValue();
            }
            return false;
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            LogUtil.b("RocheGlucoseManager", "removeDevice exception");
            return false;
        }
    }

    class a implements Handler.Callback {
        private a() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 101:
                    dbe.this.h();
                    return true;
                case 102:
                    dbe.this.m();
                    return true;
                case 103:
                    dbe.this.i();
                    return true;
                case 104:
                    dbe.this.e.onStatusChanged(13, dbe.this.f, dbe.this.h);
                    dbe.this.g();
                    dbe.this.b();
                    return true;
                case 105:
                    dbe.this.e.onStatusChanged(12, dbe.this.f, dbe.this.h);
                    return true;
                case 106:
                    dbe.this.e.onStatusChanged(dbe.this.f11574a, dbe.this.f, dbe.this.h);
                    if (dbe.this.f11574a == 16) {
                        dbe.this.e.onDataChanged(dbe.this.g ? cxh.d(dbe.this.w) : cxh.Ra_(dbe.this.k), new HealthData());
                        dbe.this.g();
                    }
                    return true;
                case 107:
                    dbe dbeVar = dbe.this;
                    dbeVar.Tp_(dbeVar.k.getClass(), dbe.this.k);
                    return true;
                case 108:
                    dbe.this.g();
                    dbe.this.e.onStatusChanged(17, dbe.this.f, dbe.this.h);
                    return true;
                default:
                    LogUtil.a("RocheGlucoseManager", "default case");
                    return false;
            }
        }
    }
}
