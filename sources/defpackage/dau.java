package defpackage;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.os.Bundle;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.device.api.DeviceInfoUtilsApi;
import com.huawei.health.device.callback.IHealthDeviceCallback;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.health.ecologydevice.callback.ConnectStatusCallback;
import com.huawei.health.ecologydevice.callback.DataChangedCallback;
import com.huawei.health.ecologydevice.open.GattMeasureController;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.Services;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/* loaded from: classes3.dex */
public class dau extends GattMeasureController implements ConnectStatusCallback, DataChangedCallback {
    private static final Object b = new Object();
    private static dau e;
    private IHealthDeviceCallback c;
    private BluetoothGatt d;
    private BluetoothGattCharacteristic f;
    private String g;
    private boolean h;
    private BluetoothGattService i;
    private int k;
    private BluetoothGattCharacteristic m;
    private String o;
    private dax j = new dax();

    /* renamed from: a, reason: collision with root package name */
    private BluetoothGattCallback f11572a = new BluetoothGattCallback() { // from class: dau.5
        @Override // android.bluetooth.BluetoothGattCallback
        public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
            dau.this.Ti_(bluetoothGatt, i, i2);
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onDescriptorWrite(BluetoothGatt bluetoothGatt, BluetoothGattDescriptor bluetoothGattDescriptor, int i) {
            LogUtil.a("EcologyDevice_MuMuMeasureController", "onDescriptorWrite status:", Integer.valueOf(i));
            super.onDescriptorWrite(bluetoothGatt, bluetoothGattDescriptor, i);
            if (dau.this.k != 2) {
                LogUtil.h("EcologyDevice_MuMuMeasureController", "onDescriptorWrite state is not connected");
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
            LogUtil.c("EcologyDevice_MuMuMeasureController", "onCharacteristicChanged");
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            super.onCharacteristicWrite(bluetoothGatt, bluetoothGattCharacteristic, i);
            LogUtil.a("EcologyDevice_MuMuMeasureController", "onCharacteristicWrite");
            try {
                dau.this.d.readCharacteristic(dau.this.f);
            } catch (SecurityException e2) {
                LogUtil.b("EcologyDevice_MuMuMeasureController", "onCharacteristicWrite SecurityException:", ExceptionUtils.d(e2));
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
            LogUtil.a("EcologyDevice_MuMuMeasureController", "onCharacteristicRead");
            if (cez.u.toString().equalsIgnoreCase(bluetoothGattCharacteristic.getUuid().toString())) {
                dau.this.b(bluetoothGattCharacteristic.getValue());
            }
        }

        @Override // android.bluetooth.BluetoothGattCallback
        public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
            super.onServicesDiscovered(bluetoothGatt, i);
            try {
                List<BluetoothGattService> services = bluetoothGatt.getServices();
                LogUtil.c("EcologyDevice_MuMuMeasureController", "onServicesDiscovered serviceList = ", services.toString());
                if (i == 0) {
                    LogUtil.c("EcologyDevice_MuMuMeasureController", "onServicesDiscovered GATT_SUCCESS");
                    Iterator<BluetoothGattService> it = services.iterator();
                    while (it.hasNext()) {
                        String uuid = it.next().getUuid().toString();
                        LogUtil.c("EcologyDevice_MuMuMeasureController", "onServicesDiscovered UUID: ", uuid);
                        if (cez.aa.toString().equalsIgnoreCase(uuid)) {
                            dau.this.Tj_(bluetoothGatt);
                            return;
                        }
                    }
                }
            } catch (SecurityException e2) {
                LogUtil.b("EcologyDevice_MuMuMeasureController", "onServicesDiscovered SecurityException:", ExceptionUtils.d(e2));
            }
        }
    };

    @Override // com.huawei.health.device.open.MeasureController
    public boolean start() {
        return true;
    }

    private dau() {
    }

    public static dau d() {
        dau dauVar;
        synchronized (b) {
            if (e == null) {
                e = new dau();
            }
            dauVar = e;
        }
        return dauVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Ti_(BluetoothGatt bluetoothGatt, int i, int i2) {
        LogUtil.c("EcologyDevice_MuMuMeasureController", "doConnectionStateChange status:", Integer.valueOf(i));
        if (i2 == 2) {
            try {
                this.d = bluetoothGatt;
                this.k = 2;
                bluetoothGatt.discoverServices();
                return;
            } catch (SecurityException e2) {
                LogUtil.b("EcologyDevice_MuMuMeasureController", "doConnectionStateChange SecurityException:", ExceptionUtils.d(e2));
                return;
            }
        }
        if (i2 == 0) {
            this.k = 0;
            cleanup();
            j();
            return;
        }
        LogUtil.a("EcologyDevice_MuMuMeasureController", "doConnectionStateChange other newState:", Integer.valueOf(i2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(byte[] bArr) {
        if (bArr == null || bArr.length < 1 || bArr[0] != -6) {
            return;
        }
        HealthData healthData = new HealthData();
        if (bArr.length >= 2) {
            d(bArr[1], bArr, healthData);
        }
    }

    private void d(byte b2, byte[] bArr, HealthData healthData) {
        if (b2 == 33) {
            c(bArr);
        } else {
            c(b2, bArr, healthData);
        }
    }

    private void c(byte[] bArr) {
        dba dbaVar = new dba(bArr);
        if (dbaVar.a() != null) {
            LogUtil.c("EcologyDevice_MuMuMeasureController", "parserDataCaseOne message is ", dbaVar.a().a());
            j();
            return;
        }
        LogUtil.c("EcologyDevice_MuMuMeasureController", "parserDataCaseOne ResultInfo :", dbaVar.toString());
        if (dbaVar.f()) {
            deo deoVar = new deo();
            deoVar.setDiastolic((short) dbaVar.b());
            if (a()) {
                this.c.onProgressChanged(null, deoVar);
                return;
            }
            return;
        }
        this.h = false;
        deo deoVar2 = new deo();
        deoVar2.setDiastolic((short) dbaVar.e());
        deoVar2.setSystolic((short) dbaVar.d());
        deoVar2.setHeartRate((short) dbaVar.c());
        if (a()) {
            this.c.onDataChanged((HealthDevice) null, deoVar2);
        }
    }

    private void j() {
        if (a()) {
            this.c.onStatusChanged(new HealthDevice() { // from class: dau.2
                @Override // com.huawei.health.device.model.HealthDevice
                public String getUniqueId() {
                    return "";
                }

                @Override // com.huawei.health.device.model.HealthDevice
                public String getDeviceName() {
                    return "";
                }

                @Override // com.huawei.health.device.model.HealthDevice
                public String getAddress() {
                    return "";
                }
            }, 3);
        }
    }

    private void c(byte b2, byte[] bArr, HealthData healthData) {
        if (b2 == 1 || b2 == 2) {
            if (a()) {
                this.c.onDataChanged((HealthDevice) null, healthData);
            }
        } else {
            if (b2 != 32) {
                return;
            }
            a(bArr);
        }
    }

    @Override // com.huawei.health.ecologydevice.open.GattMeasureController, com.huawei.health.device.open.MeasureController
    public boolean prepare(HealthDevice healthDevice, IHealthDeviceCallback iHealthDeviceCallback, Bundle bundle) {
        LogUtil.a("EcologyDevice_MuMuMeasureController", "Enter prepare");
        if (dkq.c().d()) {
            if (!super.prepare(healthDevice, iHealthDeviceCallback, bundle)) {
                return false;
            }
            this.c = iHealthDeviceCallback;
            this.g = UUID.randomUUID().toString();
            dau d = d();
            String name = dau.class.getName();
            ((DeviceInfoUtilsApi) Services.c("PluginDevice", DeviceInfoUtilsApi.class)).putUdsClassMap(name, d);
            ddw.c().a(this.g, new cwq(name));
            ddw.c().e(this.g, new cwm(name));
            LogUtil.a("EcologyDevice_MuMuMeasureController", "MuMuMeasureController uds prepare start");
            return true;
        }
        if (!super.prepare(healthDevice, iHealthDeviceCallback, bundle)) {
            return false;
        }
        this.c = iHealthDeviceCallback;
        return true;
    }

    public void Tj_(BluetoothGatt bluetoothGatt) {
        LogUtil.c("EcologyDevice_MuMuMeasureController", "Enter initService");
        this.d = bluetoothGatt;
        if (this.i != null) {
            c();
            return;
        }
        this.h = false;
        BluetoothGattService service = bluetoothGatt.getService(UUID.fromString(cez.aa.toString()));
        this.i = service;
        if (service != null) {
            this.f = service.getCharacteristic(UUID.fromString(cez.u.toString()));
            this.m = this.i.getCharacteristic(UUID.fromString(cez.an.toString()));
            c();
        }
    }

    public void c() {
        if (this.i == null) {
            return;
        }
        try {
            this.m.setValue(this.j.d());
            this.d.writeCharacteristic(this.m);
            this.h = true;
            cpp.b(new Runnable() { // from class: dav
                @Override // java.lang.Runnable
                public final void run() {
                    dau.this.b();
                }
            });
        } catch (SecurityException e2) {
            LogUtil.b("EcologyDevice_MuMuMeasureController", "startMeasure SecurityException:", ExceptionUtils.d(e2));
        }
    }

    /* synthetic */ void b() {
        BluetoothGattCharacteristic bluetoothGattCharacteristic;
        while (this.k == 2 && this.h && (bluetoothGattCharacteristic = this.m) != null) {
            try {
                bluetoothGattCharacteristic.setValue(this.j.e());
                this.d.writeCharacteristic(this.m);
                Thread.sleep(250L);
            } catch (InterruptedException | SecurityException e2) {
                LogUtil.b("EcologyDevice_MuMuMeasureController", "startMeasure Exception:", ExceptionUtils.d(e2));
            }
        }
    }

    private boolean a() {
        return this.c != null;
    }

    private void a(byte[] bArr) {
        byte b2 = bArr[2];
        Boolean b3 = day.b(bArr[3], 7);
        if (b3 != null) {
            if (b2 == 0) {
                this.h = b3.booleanValue();
            } else {
                this.h = !b3.booleanValue();
            }
        }
    }

    @Override // com.huawei.health.ecologydevice.open.GattMeasureController
    public BluetoothGattCallback getGattCallbackImpl() {
        return this.f11572a;
    }

    @Override // com.huawei.health.device.open.MeasureController
    public void cleanup() {
        BluetoothGatt bluetoothGatt = this.d;
        if (bluetoothGatt != null) {
            try {
                bluetoothGatt.close();
            } catch (SecurityException e2) {
                LogUtil.b("EcologyDevice_MuMuMeasureController", "cleanup SecurityException:", ExceptionUtils.d(e2));
                return;
            }
        }
        this.d = null;
        this.i = null;
        this.m = null;
        this.f = null;
        this.c = null;
        this.k = 0;
        if (dkq.c().d()) {
            ddw.c().a();
            ddw.c().a(this.g);
            ddw.c().d(this.g);
            ((DeviceInfoUtilsApi) Services.c("PluginDevice", DeviceInfoUtilsApi.class)).clearUdsClassMap();
            this.h = false;
        }
        i();
        this.g = null;
    }

    private static void i() {
        synchronized (b) {
            e = null;
        }
    }

    @Override // com.huawei.health.ecologydevice.open.GattMeasureController, com.huawei.health.device.open.MeasureController
    public void ending() {
        BluetoothGatt bluetoothGatt = this.d;
        if (bluetoothGatt != null) {
            try {
                bluetoothGatt.disconnect();
            } catch (SecurityException e2) {
                LogUtil.b("EcologyDevice_MuMuMeasureController", "ending SecurityException:", ExceptionUtils.d(e2));
                return;
            }
        }
        if (dkq.c().d()) {
            ddw.c().a();
        }
    }

    @Override // com.huawei.health.ecologydevice.callback.ConnectStatusCallback
    public void doDeviceConnected(int i, DeviceInfo deviceInfo) {
        LogUtil.a("EcologyDevice_MuMuMeasureController", "doConnectionStateChange doDeviceConnected");
        this.o = deviceInfo.getDeviceMac();
        dkq.c().a(this.j.d(), this.o);
        this.h = true;
        ThreadPoolManager.d().execute(new Runnable() { // from class: das
            @Override // java.lang.Runnable
            public final void run() {
                dau.this.e();
            }
        });
    }

    /* synthetic */ void e() {
        while (this.h) {
            dkq.c().a(this.j.e(), this.o);
            try {
                Thread.sleep(500L);
            } catch (InterruptedException unused) {
                LogUtil.b("EcologyDevice_MuMuMeasureController", "doDeviceConnected sleep error");
            }
        }
    }

    @Override // com.huawei.health.ecologydevice.callback.ConnectStatusCallback
    public void doDeviceDisconnect(int i) {
        cleanup();
        if (this.c != null) {
            LogUtil.h("EcologyDevice_MuMuMeasureController", "doConnectionStateChange disconnected");
            this.c.onStatusChanged(this.mDevice, 3);
        }
    }

    @Override // com.huawei.health.ecologydevice.callback.ConnectStatusCallback
    public void doDeviceConnecting(int i) {
        LogUtil.a("EcologyDevice_MuMuMeasureController", "device is connecting");
    }

    @Override // com.huawei.health.ecologydevice.callback.DataChangedCallback
    public void onDataChanged(biu biuVar) {
        LogUtil.a("EcologyDevice_MuMuMeasureController", "onDataChanged enter");
        if (biuVar == null) {
            return;
        }
        String b2 = biuVar.b();
        LogUtil.a("EcologyDevice_MuMuMeasureController", "onDataChanged enter", biuVar.a(), biuVar.b());
        if (cez.u.toString().equalsIgnoreCase(b2)) {
            b(biuVar.a());
        } else if (cez.an.toString().equalsIgnoreCase(b2)) {
            dkq.c().a(biuVar, cez.aa.toString(), cez.u.toString(), this.o);
        } else {
            LogUtil.a("EcologyDevice_MuMuMeasureController", "onDataChanged other uuid");
        }
    }
}
