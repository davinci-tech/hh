package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.os.Bundle;
import com.google.flatbuffers.reflection.BaseType;
import com.huawei.devicesdk.entity.CharacterOperationType;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.device.api.DeviceInfoUtilsApi;
import com.huawei.health.device.callback.IHealthDeviceCallback;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.ecologydevice.callback.ConnectStatusCallback;
import com.huawei.health.ecologydevice.callback.DataChangedCallback;
import com.huawei.health.ecologydevice.open.GattMeasureController;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.profile.profile.ProfileExtendConstants;
import health.compact.a.Services;
import java.util.UUID;

/* loaded from: classes3.dex */
public class daj extends GattMeasureController implements ConnectStatusCallback, DataChangedCallback {
    private static daj c;
    private static final Object d = new Object();

    /* renamed from: a, reason: collision with root package name */
    private BluetoothGattCharacteristic f11569a;
    private BluetoothGattService b;
    private BluetoothGattDescriptor e;
    private BluetoothManager f;
    private BluetoothAdapter h;
    private BluetoothGattCharacteristic i;
    private Context j;
    private String k;
    private IHealthDeviceCallback l;
    private BluetoothGatt m;
    private String p;
    private boolean n = true;
    private int o = 0;
    private final BluetoothGattCallback g = new BluetoothGattCallback() { // from class: daj.3
        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            daj.this.Tc_(bluetoothGatt, i2);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            daj.this.Te_(bluetoothGatt, i);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            LogUtil.a("KangKangMeasureController", "onCharacteristicWrite success status:", Integer.valueOf(i));
            try {
                if (daj.this.n) {
                    daj.this.n = false;
                    LogUtil.c("KangKangMeasureController", "onCharacteristicWrite send a cmd");
                    try {
                        Thread.sleep(200L);
                    } catch (InterruptedException e) {
                        LogUtil.b("KangKangMeasureController", "onCharacteristicWrite ex = ", e.getMessage());
                    }
                    if (daj.this.i == null || daj.this.m == null) {
                        return;
                    }
                    daj.this.i.setValue(Integer.parseInt("A", 16), 17, 0);
                    daj.this.m.writeCharacteristic(daj.this.i);
                }
            } catch (NumberFormatException | SecurityException e2) {
                LogUtil.b("KangKangMeasureController", "onCharacteristicWrite e:", ExceptionUtils.d(e2));
                daj.this.a(0);
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            daj.this.Tb_(bluetoothGattCharacteristic);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            daj.this.Td_(bluetoothGatt, i);
        }
    };

    private daj() {
    }

    public static daj a() {
        daj dajVar;
        synchronized (d) {
            if (c == null) {
                c = new daj();
            }
            dajVar = c;
        }
        return dajVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Td_(BluetoothGatt bluetoothGatt, int i) {
        LogUtil.a("KangKangMeasureController", "doDescriptorWrite success status:", Integer.valueOf(i));
        int i2 = this.o;
        if (i2 != 1) {
            if (i2 == 0) {
                this.o = 2;
                LogUtil.c("KangKangMeasureController", "doDescriptorWrite STATE_NONE only set STATE_CONNECTED");
                this.n = false;
                return;
            }
            LogUtil.h("KangKangMeasureController", "doDescriptorWrite other status:", Integer.valueOf(i));
            return;
        }
        LogUtil.c("KangKangMeasureController", "doDescriptorWrite send ble cmd 11");
        this.n = true;
        try {
            this.i.setValue(11, 17, 0);
            bluetoothGatt.writeCharacteristic(this.i);
            a(2);
            Thread.sleep(100L);
        } catch (InterruptedException | SecurityException e) {
            LogUtil.b("KangKangMeasureController", "doDescriptorWrite SecurityException:", ExceptionUtils.d(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Tb_(BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        LogUtil.c("KangKangMeasureController", "Enter doCharacteristicChange");
        byte[] value = bluetoothGattCharacteristic.getValue();
        if (value == null || value.length != 6) {
            return;
        }
        deo deoVar = new deo();
        deoVar.c(value);
        b(deoVar);
    }

    private void b(deo deoVar) {
        if (dao.d(deoVar.getDiastolic())) {
            synchronized (this) {
                IHealthDeviceCallback iHealthDeviceCallback = this.l;
                if (iHealthDeviceCallback != null) {
                    iHealthDeviceCallback.onFailed(this.mDevice, deoVar.getDiastolic());
                }
            }
            return;
        }
        synchronized (this) {
            IHealthDeviceCallback iHealthDeviceCallback2 = this.l;
            if (iHealthDeviceCallback2 != null) {
                iHealthDeviceCallback2.onDataChanged(this.mDevice, deoVar);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Te_(BluetoothGatt bluetoothGatt, int i) {
        LogUtil.a("KangKangMeasureController", "Enter doServiceDiscovered");
        if (i != 0) {
            LogUtil.h("KangKangMeasureController", "doServiceDiscovered other status : ", Integer.valueOf(i));
            return;
        }
        try {
            BluetoothGattService service = bluetoothGatt.getService(UUID.fromString("0000fff0-0000-1000-8000-00805f9b34fb"));
            this.b = service;
            if (service == null) {
                LogUtil.h("KangKangMeasureController", "doServiceDiscovered mBleGattService is null");
                return;
            }
            this.f11569a = service.getCharacteristic(UUID.fromString("0000fff1-0000-1000-8000-00805f9b34fb"));
            BluetoothGattCharacteristic characteristic = this.b.getCharacteristic(UUID.fromString("0000fff2-0000-1000-8000-00805f9b34fb"));
            this.i = characteristic;
            BluetoothGattCharacteristic bluetoothGattCharacteristic = this.f11569a;
            if (bluetoothGattCharacteristic == null || characteristic == null) {
                return;
            }
            this.e = bluetoothGattCharacteristic.getDescriptor(UUID.fromString(BleConstants.CLIENT_CHARACTERISTIC_CONFIG_DESCRIPTOR_UUID));
            Thread.sleep(50L);
            if (this.e != null) {
                bluetoothGatt.setCharacteristicNotification(this.f11569a, true);
                this.e.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                bluetoothGatt.writeDescriptor(this.e);
            }
        } catch (InterruptedException | SecurityException e) {
            LogUtil.b("KangKangMeasureController", "doServiceDiscovered Exception:", ExceptionUtils.d(e));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Tc_(BluetoothGatt bluetoothGatt, int i) {
        LogUtil.a("KangKangMeasureController", "doConnectionStateChange Connection State Change:", Integer.valueOf(i));
        this.m = bluetoothGatt;
        if (i == 1) {
            LogUtil.c("KangKangMeasureController", "doConnectionStateChange please wait connecting");
            return;
        }
        if (i == 2) {
            LogUtil.c("KangKangMeasureController", "doConnectionStateChange Connected to GATT server.");
            try {
                Thread.sleep(100L);
                bluetoothGatt.discoverServices();
                Thread.sleep(100L);
                return;
            } catch (InterruptedException | SecurityException e) {
                LogUtil.b("KangKangMeasureController", "doConnectionStateChange Exception:", ExceptionUtils.d(e));
                return;
            }
        }
        if (i == 0) {
            LogUtil.a("KangKangMeasureController", "doConnectionStateChange Disconnected from GATT server.");
            cleanup();
            this.n = true;
            a(0);
            return;
        }
        LogUtil.h("KangKangMeasureController", "doConnectionStateChange other state:", Integer.valueOf(i));
    }

    private void e(IHealthDeviceCallback iHealthDeviceCallback) {
        synchronized (this) {
            this.l = iHealthDeviceCallback;
        }
    }

    @Override // com.huawei.health.ecologydevice.open.GattMeasureController, com.huawei.health.device.open.MeasureController
    public boolean prepare(HealthDevice healthDevice, IHealthDeviceCallback iHealthDeviceCallback, Bundle bundle) {
        if (dkq.c().d()) {
            this.k = UUID.randomUUID().toString();
            daj a2 = a();
            String name = daj.class.getName();
            ((DeviceInfoUtilsApi) Services.c("PluginDevice", DeviceInfoUtilsApi.class)).putUdsClassMap(name, a2);
            ddw.c().a(this.k, new cwq(name));
            ddw.c().e(this.k, new cwm(name));
        }
        if (!super.prepare(healthDevice, iHealthDeviceCallback, bundle)) {
            return false;
        }
        this.j = cpp.a();
        h();
        e(iHealthDeviceCallback);
        return true;
    }

    @Override // com.huawei.health.device.open.MeasureController
    public boolean start() {
        f();
        return true;
    }

    @Override // com.huawei.health.ecologydevice.open.GattMeasureController, com.huawei.health.device.open.MeasureController
    public void ending() {
        BluetoothGatt bluetoothGatt = this.m;
        if (bluetoothGatt != null) {
            try {
                bluetoothGatt.disconnect();
            } catch (SecurityException e) {
                LogUtil.b("KangKangMeasureController", "ending SecurityException:", ExceptionUtils.d(e));
                return;
            }
        }
        if (dkq.c().d()) {
            ddw.c().a();
        }
    }

    @Override // com.huawei.health.device.open.MeasureController
    public void cleanup() {
        synchronized (this) {
            this.l = null;
        }
        BluetoothGatt bluetoothGatt = this.m;
        if (bluetoothGatt != null) {
            try {
                bluetoothGatt.close();
            } catch (SecurityException e) {
                LogUtil.b("KangKangMeasureController", "cleanup SecurityException:", ExceptionUtils.d(e));
                return;
            }
        }
        this.m = null;
        this.b = null;
        if (dkq.c().d()) {
            ddw.c().a();
            ddw.c().a(this.k);
            ddw.c().d(this.k);
            ((DeviceInfoUtilsApi) Services.c("PluginDevice", DeviceInfoUtilsApi.class)).clearUdsClassMap();
        }
        b();
        this.k = null;
    }

    private static void b() {
        synchronized (d) {
            c = null;
        }
    }

    @Override // com.huawei.health.ecologydevice.open.GattMeasureController
    public BluetoothGattCallback getGattCallbackImpl() {
        return this.g;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        synchronized (this) {
            this.o = i;
            IHealthDeviceCallback iHealthDeviceCallback = this.l;
            if (iHealthDeviceCallback != null) {
                iHealthDeviceCallback.onStatusChanged(this.mDevice, i);
            }
        }
    }

    private boolean h() {
        if (this.f == null) {
            Object systemService = this.j.getSystemService("bluetooth");
            if (systemService instanceof BluetoothManager) {
                this.f = (BluetoothManager) systemService;
            }
            if (this.f == null) {
                LogUtil.h("KangKangMeasureController", "Unable to initialize BluetoothManager.");
                return false;
            }
        }
        BluetoothAdapter adapter = this.f.getAdapter();
        this.h = adapter;
        if (adapter == null) {
            LogUtil.h("KangKangMeasureController", "initialize Unable to obtain a BluetoothAdapter.");
            return false;
        }
        try {
            Thread.sleep(ProfileExtendConstants.TIME_OUT);
            return true;
        } catch (InterruptedException e) {
            LogUtil.b("KangKangMeasureController", "initialize e = ", e.getMessage());
            return true;
        }
    }

    private void f() {
        int i = this.o;
        if (i == 1) {
            LogUtil.a("KangKangMeasureController", "startBloodPressureMeasurement state connecting ");
            return;
        }
        if (i == 2) {
            if (dkq.c().d()) {
                c();
                return;
            } else {
                e();
                return;
            }
        }
        LogUtil.h("KangKangMeasureController", "startBloodPressureMeasurement, state = ", Integer.valueOf(i));
        d();
    }

    private void d() {
        a(1);
        BluetoothAdapter bluetoothAdapter = this.h;
        if (bluetoothAdapter != null && !bluetoothAdapter.isEnabled()) {
            LogUtil.c("KangKangMeasureController", "enableBluetoothAdapterAndSetStateValue bluetooth not enabled ,start bluetooth ");
            a(0);
        } else if (this.m != null) {
            LogUtil.c("KangKangMeasureController", "enableBluetoothAdapterAndSetStateValue mGattObject is not null");
            this.n = true;
        } else {
            LogUtil.h("KangKangMeasureController", "enableBluetoothAdapterAndSetStateValue mGattObject is null");
            this.n = true;
        }
    }

    private void e() {
        try {
            if (this.m == null || this.i == null) {
                return;
            }
            LogUtil.c("KangKangMeasureController", "callbackOnStatusChangedAndSetBleValue state connected,send 40 cmd");
            synchronized (this) {
                this.l.onStatusChanged(this.mDevice, this.o);
            }
            this.n = true;
            this.i.setValue(Integer.parseInt("B", 16), 17, 0);
            this.m.writeCharacteristic(this.i);
            try {
                Thread.sleep(200L);
            } catch (InterruptedException e) {
                LogUtil.b("KangKangMeasureController", "callbackOnStatusChangedAndSetBleValue ex:", ExceptionUtils.d(e));
            }
        } catch (NumberFormatException | SecurityException e2) {
            LogUtil.b("KangKangMeasureController", "callbackOnStatusChangedAndSetBleValue e:", ExceptionUtils.d(e2));
            a(0);
        }
    }

    private void c() {
        synchronized (this) {
            if (this.l == null) {
                LogUtil.h("KangKangMeasureController", "mGetDataResultCallback == null");
                return;
            }
            try {
                synchronized (this) {
                    this.l.onStatusChanged(this.mDevice, this.o);
                }
                this.n = true;
                c(new byte[]{Byte.parseByte("B", 16)});
                try {
                    Thread.sleep(200L);
                } catch (InterruptedException unused) {
                    LogUtil.b("KangKangMeasureController", "callbackOnStatusChangedAndSetBleValue InterruptedException");
                }
            } catch (NumberFormatException unused2) {
                LogUtil.b("KangKangMeasureController", "callbackOnStatusChangedAndSetBleValue InterruptedException");
                a(0);
            }
        }
    }

    private void c(byte[] bArr) {
        biu biuVar = new biu();
        biuVar.a(cez.v.toString());
        biuVar.d(bArr);
        dkq.c().d(biuVar, cez.y.toString(), cez.v.toString(), this.p, CharacterOperationType.WRITE);
    }

    @Override // com.huawei.health.ecologydevice.callback.ConnectStatusCallback
    public void doDeviceConnected(int i, DeviceInfo deviceInfo) {
        LogUtil.c("KangKangMeasureController", "doConnectionStateChange Connected to GATT server bu uds");
        this.p = deviceInfo.getDeviceMac();
        ((DeviceInfoUtilsApi) Services.c("PluginDevice", DeviceInfoUtilsApi.class)).setCharacteristicNotifyByUds(this.p, cez.y.toString(), cez.x.toString(), true);
    }

    @Override // com.huawei.health.ecologydevice.callback.ConnectStatusCallback
    public void doDeviceDisconnect(int i) {
        LogUtil.a("KangKangMeasureController", "doConnectionStateChange Disconnected from GATT server by uds");
        cleanup();
        this.n = true;
        a(0);
    }

    @Override // com.huawei.health.ecologydevice.callback.ConnectStatusCallback
    public void doDeviceConnecting(int i) {
        LogUtil.c("KangKangMeasureController", "doConnectionStateChange please wait connecting by uds");
    }

    @Override // com.huawei.health.ecologydevice.callback.DataChangedCallback
    public void onDataChanged(biu biuVar) {
        if (biuVar == null) {
            LogUtil.h("KangKangMeasureController", "dataFrame == null");
            return;
        }
        if (biuVar.b() != null && cez.v.toString().equalsIgnoreCase(biuVar.b())) {
            j();
        }
        if (biuVar.b() == null || !cez.x.toString().equalsIgnoreCase(biuVar.b())) {
            return;
        }
        g();
        d(biuVar);
    }

    private void g() {
        int i = this.o;
        if (i != 1) {
            if (i == 0) {
                this.o = 2;
                LogUtil.a("KangKangMeasureController", "doDescriptorWrite STATE_NONE only set STATE_CONNECTED by uds");
                this.n = false;
                return;
            }
            LogUtil.h("KangKangMeasureController", "doDescriptorWrite other status : ", Integer.valueOf(i));
            return;
        }
        LogUtil.a("KangKangMeasureController", "doDescriptorWrite send ble cmd by uds");
        this.n = true;
        c(new byte[]{BaseType.Float});
        a(2);
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            LogUtil.b("KangKangMeasureController", "doDescriptorWrite ex = ", e.getMessage());
        }
    }

    private void j() {
        if (this.n) {
            this.n = false;
            LogUtil.a("KangKangMeasureController", "onCharacteristicWrite send a cmd by cmd");
            try {
                Thread.sleep(200L);
            } catch (InterruptedException unused) {
                LogUtil.b("KangKangMeasureController", "onCharacteristicWrite InterruptedException");
            }
            try {
                c(new byte[]{Byte.parseByte("A", 16)});
            } catch (NumberFormatException e) {
                LogUtil.b("KangKangMeasureController", "onCharacteristicWrite ex = ", e.getMessage());
                a(0);
            }
        }
    }

    private void d(biu biuVar) {
        byte[] a2 = biuVar.a();
        if (a2 == null || a2.length != 6) {
            return;
        }
        deo deoVar = new deo();
        deoVar.c(a2);
        b(deoVar);
    }
}
